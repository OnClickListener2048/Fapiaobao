package com.pilipa.fapiaobao.ui;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.mylibrary.utils.NetworkUtils;
import com.example.mylibrary.utils.TLog;
import com.pilipa.fapiaobao.R;
import com.pilipa.fapiaobao.account.AccountHelper;
import com.pilipa.fapiaobao.base.BaseActivity;
import com.pilipa.fapiaobao.base.BaseApplication;
import com.pilipa.fapiaobao.net.Api;
import com.pilipa.fapiaobao.net.Constant;
import com.pilipa.fapiaobao.net.bean.WXmodel;
import com.pilipa.fapiaobao.net.bean.me.NormalBean;
import com.pilipa.fapiaobao.net.bean.wx.PrepayBean;
import com.pilipa.fapiaobao.receiver.WXPayReceiver;
import com.pilipa.fapiaobao.wxapi.Constants;
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.pilipa.fapiaobao.net.Constant.LOGIN_PLATFORM_WX;
import static com.pilipa.fapiaobao.net.Constant.RECHARGE;
import static com.pilipa.fapiaobao.ui.LoginActivity.WX_LOGIN_ACTION;

/**
 * Created by lyt on 2017/10/24.
 */

public class RechargeActivity extends BaseActivity  {
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

    @OnClick({R.id._back, R.id.tv_recharge_10, R.id.tv_recharge_30, R.id.tv_recharge_100, R.id.tv_recharge_500,R.id.recharge})
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
    }


    @OnClick(R.id.go_recharge)
    public void onViewClicked() {
//        AccountHelper.isTokenValid(new Api.BaseViewCallback<LoginWithInfoBean>() {
//            @Override
//            public void setData(LoginWithInfoBean loginWithInfoBean) {
//                AccountHelper.updateCustomer(loginWithInfoBean.getData().getCustomer());
//                weChatLogin();
//            }
//        });

        /* 充值 直接调用 后台充值接口 不需要绑定*/
        if (api.isWXAppInstalled() && api.isWXAppSupportAPI()) {
            recharge();
        } else {
            BaseApplication.showToast(getString(R.string.please_install_WX_app));
        }

    }

    private void weChatLogin() {
        SendAuth.Req req = new SendAuth.Req();
        req.scope = "snsapi_userinfo";
        req.state = "wechat_sdk_demo_test";
        api.sendReq(req);
    }
}
