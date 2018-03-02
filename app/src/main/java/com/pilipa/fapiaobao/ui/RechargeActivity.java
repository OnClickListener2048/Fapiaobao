package com.pilipa.fapiaobao.ui;

import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.mylibrary.utils.NetworkUtils;
import com.example.mylibrary.utils.TLog;
import com.pilipa.fapiaobao.R;
import com.pilipa.fapiaobao.account.AccountHelper;
import com.pilipa.fapiaobao.adapter.me.RechargeAdapter;
import com.pilipa.fapiaobao.base.BaseActivity;
import com.pilipa.fapiaobao.base.BaseApplication;
import com.pilipa.fapiaobao.net.Api;
import com.pilipa.fapiaobao.net.Constant;
import com.pilipa.fapiaobao.net.bean.WXmodel;
import com.pilipa.fapiaobao.net.bean.me.NormalBean;
import com.pilipa.fapiaobao.net.bean.wx.PrepayBean;
import com.pilipa.fapiaobao.receiver.WXPayReceiver;
import com.pilipa.fapiaobao.ui.deco.KeyboardDeco;
import com.pilipa.fapiaobao.wxapi.Constants;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import java.util.ArrayList;
import java.util.Objects;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.pilipa.fapiaobao.net.Constant.LOGIN_PLATFORM_WX;
import static com.pilipa.fapiaobao.net.Constant.RECHARGE;
import static com.pilipa.fapiaobao.ui.LoginActivity.WX_LOGIN_ACTION;

/**
 * Created by lyt on 2017/10/24.
 */

public class RechargeActivity extends BaseActivity implements RechargeAdapter.OnValueClickListener {
    private static final String TAG = "RechargeActivity";
    @Bind(R.id.tv_recharge_10)
    TextView tvRecharge10;
    @Bind(R.id.tv_recharge_30)
    TextView tvRecharge30;
    @Bind(R.id.tv_recharge_100)
    TextView tvRecharge100;
    @Bind(R.id.tv_recharge_500)
    TextView tvRecharge500;
    @Bind(R.id.go_recharge)
    Button goRecharge;
    @Bind(R.id.other_recharge)
    TextView otherRecharge;
    private String[] keyboardValue = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "space", "0", "delete"};
    private IWXAPI api;
    private double amount;
    public  BroadcastReceiver mBroadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (TextUtils.equals(intent.getAction(), WXPayReceiver.pay_success)) {
                Intent intent1 = new Intent();
                intent1.putExtra(com.pilipa.fapiaobao.ui.constants.Constant.TITLE, getString(R.string.recharge_success));
                intent1.putExtra(com.pilipa.fapiaobao.ui.constants.Constant.MESSAGE, getString(R.string.recharge_message,String.valueOf(amount)));
                RechargeActivity.this.setResult(RESULT_OK,intent1);
                finish();
            } else if (TextUtils.equals(intent.getAction(), WXPayReceiver.pay_fail)) {
                RechargeActivity.this.setResult(RESULT_CANCELED);
                finish();
            }
            if (WX_LOGIN_ACTION.equals(intent.getAction())) {
                TLog.d(TAG,WX_LOGIN_ACTION +" success");

                String deviceToken = BaseApplication.get("deviceToken","");
                Bundle bundle = intent.getBundleExtra("extra_bundle");
                final WXmodel wx_info = bundle.getParcelable("wx_info");
                if (wx_info!= null) {
                    if(AccountHelper.getUser().getData().getCustomer().getOpenid().isEmpty()){
                        bind(wx_info.getOpenid());
                    }else{
                        if(wx_info.getOpenid().equals(AccountHelper.getUser().getData().getCustomer().getOpenid())){
                            recharge();
                        }else{
                            BaseApplication.showToast("系统检测到您登录的微信账号与绑定的不一致");
                        }
                    }
                }
            }
        }
    };
    private TextView mTvCancel;
    private TextView mTvValue;
    private Button mBtnConfirm;
    private RecyclerView mKeyboardRecyclerView;
    private Dialog mMCameraDialog;

    private void bind(final String openID){
        Api.bindWX(AccountHelper.getUser().getData().getCustomer().getId(), LOGIN_PLATFORM_WX, openID, "0", new Api.BaseViewCallbackWithOnStart<NormalBean>() {
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
            public void setData(NormalBean normalBean) {
                if (normalBean.getStatus() == Constant.REQUEST_SUCCESS) {
                    BaseApplication.showToast(getString(R.string.WX_bind_success));
                    recharge();
                }else if(normalBean.getStatus() == 707){
                    BaseApplication.showToast(normalBean.getMsg());
                }
            }
        });
    }

    private void recharge(){
        Api.wxRecharge(AccountHelper.getToken(), NetworkUtils.getIPAddress(true), amount, new Api.BaseViewCallbackWithOnStart<PrepayBean>() {
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
            public void setData(PrepayBean prepayBean) {

                PrepayBean.DataBean data = prepayBean.getData();

                PayReq request = new PayReq();

                request.appId = data.getAppId();

                request.partnerId = data.getPartnerId();

                request.prepayId = data.getPrepayId();

                request.packageValue = "Sign=WXPay";

                request.nonceStr = data.getNonceStr();

                request.timeStamp = data.getTimeStamp();

                request.sign = data.getSign();

                api.sendReq(request);

            }
        });
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_rechange;
    }

    @OnClick({R.id._back, R.id.tv_recharge_10, R.id.tv_recharge_30, R.id.tv_recharge_100, R.id.tv_recharge_500, R.id.recharge, R.id.other_recharge})
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.recharge: {
                Intent intent = new Intent();
                intent.putExtra("url", RECHARGE);
                intent.setClass(this, Op.class);
                startActivity(intent);
            }
            break;
            case R.id._back: {
                setResult(RESULT_CANCELED);
                finish();
            }
            break;
            case R.id.tv_recharge_10: {
                selected(1);
                amount = 10;
            }
            break;
            case R.id.tv_recharge_30: {
                selected(2);
                amount = 30;
            }
            break;
            case R.id.tv_recharge_100: {
                selected(3);
                amount = 100;
            }
            break;
            case R.id.tv_recharge_500: {
                selected(4);
                amount = 500;
            }
            break;
            case R.id.other_recharge: {
                selected(5);
//                BottomSheetBehavior behavior = BottomSheetBehavior.from(findViewById(R.id.scroll));
//                behavior.setSkipCollapsed(true);
//                if (behavior.getState() == BottomSheetBehavior.STATE_EXPANDED) {
//                    behavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
//                } else {
//                    behavior.setState(BottomSheetBehavior.STATE_EXPANDED);
//                }
                mTvValue.setText("");
                showDialog(mMCameraDialog);
                mBtnConfirm.setEnabled(false);
            }
            default:
                break;
        }
    }

    @Override
    public void initView() {

        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(WXPayReceiver.pay_fail);
        intentFilter.addAction(WXPayReceiver.pay_success);
        intentFilter.addAction(WX_LOGIN_ACTION);

        registerReceiver(mBroadcastReceiver, intentFilter);
        api = WXAPIFactory.createWXAPI(this, Constants.APP_ID);
        api.registerApp(Constants.APP_ID);


        initDialog();
    }

    private void initDialog() {

        mMCameraDialog = new Dialog(this, R.style.bottomDialog);
        LinearLayout root = (LinearLayout) LayoutInflater.from(this).inflate(
                R.layout.bottom_sheet_other_recharge, null);
        mMCameraDialog.setContentView(root);
        Window dialogWindow = mMCameraDialog.getWindow();
        dialogWindow.setGravity(Gravity.BOTTOM);
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        lp.x = 0;
        lp.y = 0;
        lp.width = getResources().getDisplayMetrics().widthPixels;
        root.measure(0, 0);
        lp.height = ViewGroup.LayoutParams.WRAP_CONTENT;

        lp.alpha = 9f;
        dialogWindow.setAttributes(lp);
        mTvCancel = (TextView) root.findViewById(R.id.tv_cancel);
        mTvValue = (TextView) root.findViewById(R.id.tv_value);
        mBtnConfirm = (Button) root.findViewById(R.id.btn_confirm);
        mKeyboardRecyclerView = (RecyclerView) root.findViewById(R.id.recyclerView);
        mTvCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mMCameraDialog.hide();
            }
        });
        mBtnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mMCameraDialog.hide();
                otherRecharge.setText(getString(R.string.recharge_other, getValue(mTvValue)));
                amount = Double.valueOf(getValue(mTvValue));
            }
        });
        ArrayList<String> values = new ArrayList();
        for (String s : keyboardValue) {
            values.add(s);
        }

        RechargeAdapter rechargeAdapter = new RechargeAdapter(values);
        rechargeAdapter.setOnValueClickListener(this);
        mKeyboardRecyclerView.setLayoutManager(new GridLayoutManager(this, 3));
        mKeyboardRecyclerView.setAdapter(rechargeAdapter);
        mKeyboardRecyclerView.addItemDecoration(new KeyboardDeco(this));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(mBroadcastReceiver);
    }

    @Override
    public void initData() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
        selected(1);
        amount = 10;

    }

    public void selected(int selectedId) {
        tvRecharge10.setSelected(selectedId == 1);
        tvRecharge30.setSelected(selectedId == 2);
        tvRecharge100.setSelected(selectedId == 3);
        tvRecharge500.setSelected(selectedId == 4);
        otherRecharge.setSelected(selectedId == 5);
    }


    @OnClick(R.id.go_recharge)
    public void onViewClicked() {
        /* 充值 直接调用 后台充值接口 不需要绑定*/
        if (api.isWXAppInstalled() && api.isWXAppSupportAPI()) {
            recharge();
        } else {
            BaseApplication.showToast(getString(R.string.please_install_WX_app));
        }
    }

    private String getValue(TextView textView) {
        return textView.getText().toString().trim();
    }


    private void check() {
        String value = getValue(mTvValue);
        if ("".equals(value)) {
            mBtnConfirm.setEnabled(false);
            return;
        }
        int iValue = Integer.valueOf(value);
        if (iValue > 200) {
            mBtnConfirm.setEnabled(false);
        } else {
            mBtnConfirm.setEnabled(true);
        }
    }


    @Override
    public void onValueClick(String value) {
        switch (value) {
            case "0":
                if (Objects.equals(getValue(mTvValue), "")) return;
                mTvValue.append(value);
                break;
            case "1":
            case "2":
            case "3":
            case "4":
            case "5":
            case "6":
            case "7":
            case "8":
            case "9":
                mTvValue.append(value);
            case "space":
                break;
            case "delete":
                if (!Objects.equals(getValue(mTvValue), "")) {
                    mTvValue.setText(getValue(mTvValue).substring(0, getValue(mTvValue).length() - 1));
                }
                break;

            default:
        }
        check();
    }
}
