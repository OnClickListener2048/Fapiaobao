package com.pilipa.fapiaobao.ui;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.pilipa.fapiaobao.R;
import com.pilipa.fapiaobao.account.AccountHelper;
import com.pilipa.fapiaobao.base.BaseActivity;
import com.pilipa.fapiaobao.net.Api;
import com.pilipa.fapiaobao.net.bean.LoginWithInfoBean;
import com.pilipa.fapiaobao.utils.SharedPreferencesHelper;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by lyt on 2017/10/17.
 */

public class MyWalletActivity extends BaseActivity {
    @Bind(R.id.tv_bouns)
    TextView tv_bouns;
    @Bind(R.id.tv_amount)
    TextView tv_amount;
    private Dialog mDialog;

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
                startActivity(new Intent(this,RechargeDetailsActivity.class));
            }break;
            case R.id.my_red_envelope:{
                Intent intent = new Intent();
                intent.putExtra("bonus", tv_bouns.getText().toString().trim());
                intent.setClass(this, MyRedEnvelopeActivity.class);
                startActivity(intent);
            }break;
        }
        AccountHelper.isTokenValid(new Api.BaseViewCallback<LoginWithInfoBean>() {
            @Override
            public void setData(LoginWithInfoBean normalBean) {
                if (normalBean.getStatus() == 200) {
                    switch (v.getId()){
                        case R.id.btn_recharge:{
                            startActivity(new Intent(MyWalletActivity.this,RechargeActivity.class));
                        }break;
                        case R.id.btn_withdraw:{
                            Intent intent = new Intent(MyWalletActivity.this,Withdraw2WXActivity.class);
                            intent.putExtra("amount",tv_amount.getText().toString().trim());
                            startActivity(intent);
                        }break;
                    }
                }else {
                    startActivity(new Intent(MyWalletActivity.this, LoginActivity.class));
                }
            }
        });

    }

    @Override
    public void initView() {
    }

    @Override
    protected void onStart() {
        super.onStart();
        AccountHelper.isTokenValid(new Api.BaseViewCallback<LoginWithInfoBean>() {
            @Override
            public void setData(LoginWithInfoBean loginWithInfoBean) {
                if (loginWithInfoBean.getStatus() == 200) {
                    if (AccountHelper.getToken() != null && AccountHelper.getToken() != "") {
                        SharedPreferencesHelper.save(MyWalletActivity.this, loginWithInfoBean);
                        tv_bouns.setText(loginWithInfoBean.getData().getCustomer().getBonus()+"");
                        tv_amount.setText(loginWithInfoBean.getData().getCustomer().getAmount()+"");
                    }
                }
            }
        });
    }

    @Override
    public void initData() {



    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

}
