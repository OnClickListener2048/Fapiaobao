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
import com.pilipa.fapiaobao.net.bean.LoginWithInfoBean;
import com.pilipa.fapiaobao.net.bean.WXmodel;
import com.pilipa.fapiaobao.net.bean.me.NormalBean;
import com.pilipa.fapiaobao.net.bean.wx.PrepayBean;
import com.pilipa.fapiaobao.receiver.WXPayReceiver;
import com.pilipa.fapiaobao.utils.SharedPreferencesHelper;
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
    TextView tv_recharge_10;
    @Bind(R.id.tv_recharge_30)
    TextView tv_recharge_30;
    @Bind(R.id.tv_recharge_100)
    TextView tv_recharge_100;
    @Bind(R.id.tv_recharge_500)
    TextView tv_recharge_500;
    @Bind(R.id.go_recharge)
    Button goRecharge;
    private IWXAPI api;
    private double amount;
    public  BroadcastReceiver mBroadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (TextUtils.equals(intent.getAction(), WXPayReceiver.pay_success)) {
                RechargeActivity.this.setResult(RESULT_OK);
                finish();
            } else if (TextUtils.equals(intent.getAction(), WXPayReceiver.pay_fail)) {
                RechargeActivity.this.setResult(RESULT_CANCELED);
                finish();
            }
            if (intent.getAction().equals(WX_LOGIN_ACTION)) {
                TLog.d(TAG,WX_LOGIN_ACTION +" success");
                String deviceToken = BaseApplication.get("deviceToken","");
                Bundle bundle = intent.getBundleExtra("extra_bundle");
                final WXmodel wx_info = bundle.getParcelable("wx_info");
                Api.bindWX(AccountHelper.getUser().getData().getCustomer().getId(),LOGIN_PLATFORM_WX , wx_info.getOpenid(), new Api.BaseViewCallback<NormalBean>() {
                    @Override
                    public void setData(NormalBean normalBean) {
                        if (normalBean.getStatus() == 200) {
                            BaseApplication.showToast("微信绑定成功");
                            Api.wxRecharge(AccountHelper.getToken(), NetworkUtils.getIPAddress(true), 1,wx_info.getOpenid(), new Api.BaseViewCallback<PrepayBean>() {
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
                        }else if(normalBean.getStatus() == 707){
                            BaseApplication.showToast(normalBean.getMsg());
                        }
                    }
                });
            }
        }
    };

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
        tv_recharge_10.setSelected(selectedId == 1);
        tv_recharge_30.setSelected(selectedId == 2);
        tv_recharge_100.setSelected(selectedId == 3);
        tv_recharge_500.setSelected(selectedId == 4);
    }


    @OnClick(R.id.go_recharge)
    public void onViewClicked() {
        LoginWithInfoBean loginWithInfoBean = SharedPreferencesHelper.loadFormSource(this, LoginWithInfoBean.class);
        if (loginWithInfoBean != null) {
        if(loginWithInfoBean.getData().getCustomer().getOpenid() != null){


            Api.wxRecharge(loginWithInfoBean.getData().getToken(), NetworkUtils.getIPAddress(true), 1, loginWithInfoBean.getData().getCustomer().getOpenid(),new Api.BaseViewCallback<PrepayBean>() {
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
        } else {
            weChatLogin();
        }
        }
    }

    private void weChatLogin() {
        SendAuth.Req req = new SendAuth.Req();
        req.scope = "snsapi_userinfo";
        req.state = "wechat_sdk_demo_test";
        api.sendReq(req);
    }
}
