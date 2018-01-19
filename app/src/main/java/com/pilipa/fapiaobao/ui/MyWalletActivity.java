package com.pilipa.fapiaobao.ui;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.example.mylibrary.utils.TLog;
import com.pilipa.fapiaobao.R;
import com.pilipa.fapiaobao.account.AccountHelper;
import com.pilipa.fapiaobao.base.BaseActivity;
import com.pilipa.fapiaobao.net.Api;
import com.pilipa.fapiaobao.net.bean.LoginWithInfoBean;
import com.pilipa.fapiaobao.ui.constants.Constant;
import com.pilipa.fapiaobao.utils.SharedPreferencesHelper;

import java.util.concurrent.TimeUnit;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;


/**
 * Created by lyt on 2017/10/17.
 */

public class MyWalletActivity extends BaseActivity {
    private static final String TAG= "MyWalletActivity";
    @Bind(R.id.tv_bouns)
    TextView tv_bouns;
    @Bind(R.id.tv_amount)
    TextView tv_amount;
    private AlertDialog alertDialog;
    private BroadcastReceiver mBroadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (TextUtils.equals(LoginActivity.WX_LOGIN_ACTION, intent.getAction())) {
                refreshAccount();
            } else if (TextUtils.equals(Constant.USER_LOGIN, intent.getAction())) {
                refreshAccount();
            }
        }
    };
    @Override
    protected int getLayoutId() {
        return R.layout.activity_wallet;
    }

    @OnClick({R.id._back,R.id.btn_recharge,R.id.btn_withdraw,R.id.tv_recharge_details,R.id.my_red_envelope})
    @Override
    public void onClick(final View v) {
        switch (v.getId()){
            case R.id._back:{
                finish();
            }break;
            case R.id.tv_recharge_details:{
                if (AccountHelper.isLogin()) {
                    startActivity(new Intent(this, RechargeDetailsActivity.class));
                } else {
                    login();
                }
            }break;

            default:
                if(!Constant.NOTOKEN.equals(AccountHelper.getToken())){
                    switch (v.getId()){
                        case R.id.btn_recharge:{
                            startActivityForResult(new Intent(MyWalletActivity.this,RechargeActivity.class), Constant.REQUEST_REFRESH_CODE);
                        }break;
                        case R.id.btn_withdraw:{
                            Intent intent = new Intent(MyWalletActivity.this,Withdraw2WXActivity.class);
                            startActivityForResult(intent,Constant.REQUEST_REFRESH_CODE);
                        }break;
                        case R.id.my_red_envelope:{
                            Intent intent = new Intent();
                            intent.putExtra("bonus", tv_bouns.getText().toString().trim());
                            intent.setClass(this, MyRedEnvelopeActivity.class);
                            startActivityForResult(intent,Constant.REQUEST_REFRESH_CODE);
                        }break;
                    }
                }else {
                    login();
                }
                break;
        }
    }

    @Override
    public void initView() {
        initDialog();
    }

    public void initDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(false);
        builder.setMessage(getString(R.string.demand_publish_quit));
        builder.setTitle(getString(R.string.demand_publish_title));
        alertDialog = builder.create();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == Constant.REQUEST_REFRESH_CODE && resultCode == RESULT_OK) {
            String title = data.getStringExtra(Constant.TITLE);
            String message = data.getStringExtra(Constant.MESSAGE);
            alertDialog.setTitle(title);
            alertDialog.setMessage(message);
            alertDialog.show();
            Observable.timer(1000, TimeUnit.MILLISECONDS)
                    .subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Consumer<Long>() {
                        @Override
                        public void accept(@NonNull Long aLong) throws Exception {
                            refreshAccount();
                            alertDialog.dismiss();
                        }
                    });
        }
    }

    @Override
    protected void onResume() {
        TLog.d(TAG,"onResume");
        super.onResume();

    }

    private void refreshAccount() {
        AccountHelper.isTokenValid(new Api.BaseRawResponse<LoginWithInfoBean>() {
            @Override
            public void onStart() {
                showProgressDialog();
            }

            @Override
            public void onFinish() {
                hideProgressDialog();
            }

            @Override
            public void onError() {

            }

            @Override
            public void onTokenInvalid() {

            }

            @Override
            public void setData(LoginWithInfoBean loginWithInfoBean) {
                if (loginWithInfoBean.getStatus() == 200) {
                    SharedPreferencesHelper.save(MyWalletActivity.this, loginWithInfoBean);
                    tv_bouns.setText(String.format("%.2f", loginWithInfoBean.getData().getCustomer().getBonus())+"");//钱包金额
                    tv_amount.setText(String.format("%.2f", loginWithInfoBean.getData().getCustomer().getAmount())+"");//钱包金额
                }
            }
        });
    }


    @Override
    public void initData() {
        refreshAccount();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(LoginActivity.WX_LOGIN_ACTION);
        intentFilter.addAction(Constant.WX_LOGIN_CANCEL);
        intentFilter.addAction(Constant.USER_LOGIN);
        registerReceiver(mBroadcastReceiver, intentFilter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        try {
            unregisterReceiver(mBroadcastReceiver);
        } catch (Exception e) {
            TLog.e(TAG, "unregisterReceiver");
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

}
