package com.pilipa.fapiaobao.ui;

import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
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
import com.pilipa.fapiaobao.wxapi.Constants;
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.pilipa.fapiaobao.net.Constant.ACCOUNT_TYPE_RED;
import static com.pilipa.fapiaobao.net.Constant.LOGIN_PLATFORM_WX;
import static com.pilipa.fapiaobao.ui.LoginActivity.WX_LOGIN_ACTION;

/**
 * Created by lyt on 2017/10/17.
 */

public class MyRedEnvelopeActivity extends BaseActivity {
    private static final String TAG = "MyRedEnvelopeActivity";

    @Bind(R.id.bonus)
    TextView tv_bonus;
    private Dialog mDialog;
    private String bonus;
    @Override
    protected int getLayoutId() {
        return R.layout.activity_red_envelope;
    }
    private WXmodel wx_info;
    private BroadcastReceiver mBroadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals(WX_LOGIN_ACTION)) {
                TLog.d(TAG,WX_LOGIN_ACTION +" success");
                String deviceToken = BaseApplication.get("deviceToken","");
                Bundle bundle = intent.getBundleExtra("extra_bundle");
                wx_info = bundle.getParcelable("wx_info");
                if(AccountHelper.getUser().getData().getCustomer().getOpenid()==null){
                    bind(wx_info.getOpenid());
                }else{
                    if(wx_info.getOpenid().equals(AccountHelper.getUser().getData().getCustomer().getOpenid())){
                        withdaw(wx_info.getOpenid());
                    }else{
                        BaseApplication.showToast("系统检测到您登录的微信账号与绑定的不一致");
                    }
                }
            }
        }
    };

    private void bind(final String openID){
        Api.bindWX(AccountHelper.getUser().getData().getCustomer().getId(),LOGIN_PLATFORM_WX , openID, new Api.BaseViewCallback<NormalBean>() {
            @Override
            public void setData(NormalBean normalBean) {
                if (normalBean.getStatus() == 200) {
                    BaseApplication.showToast("微信绑定成功");
                    withdaw(openID);
                }else if(normalBean.getStatus() == 707){
                    BaseApplication.showToast(normalBean.getMsg());
                }
            }
        });
    }
    private void withdaw(String openID){
        Api.withdaw(AccountHelper.getToken()
                ,ACCOUNT_TYPE_RED
                ,NetworkUtils.getIPAddress(true)
                ,Double.parseDouble(tv_bonus.getText().toString())
                ,openID
                , new Api.BaseViewCallback<PrepayBean>() {
                    @Override
                    public void setData(PrepayBean normalBean) {
                        if (normalBean.getStatus() ==200) {
                            BaseApplication.showToast("提现成功");
                            finish();
                        }else if(normalBean.getStatus() ==888){
                            BaseApplication.showToast("账户余额不足");
                        }
                    }
                });
    }

    @OnClick({R.id._back, R.id.btn_withdraw, R.id.btn_recharge})
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id._back: {
                finish();
            }
            break;
            case R.id.btn_withdraw: {
                if(Double.parseDouble(bonus)>0.0){
                    setDialog();
                }else{
                    BaseApplication.showToast("账户余额不足");
                }
            }
            break;
            case R.id.btn_recharge: {
                if(Double.parseDouble(bonus)>0.0){
                    setReloadDialog();
                }else{
                    BaseApplication.showToast("账户余额不足");
                }
            }
            break;
        }
    }

    @Override
    public void initView() {
        regexToWX();
        IntentFilter filter = new IntentFilter();
        filter.addAction(LoginActivity.WX_LOGIN_ACTION);
        registerReceiver(mBroadcastReceiver, filter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(mBroadcastReceiver);
    }

    @Override
    public void initData() {
        bonus = getIntent().getStringExtra("bonus");
        this.tv_bonus.setText(bonus);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    private void setDialog() {
        mDialog = new Dialog(this, R.style.BottomDialog);
        LinearLayout root = (LinearLayout) LayoutInflater.from(this).inflate(
                R.layout.layout_withdraw_tip, null);
       TextView tv_bouns = (TextView)root.findViewById(R.id.tv_bouns);
        tv_bouns.setText(bonus);
        //初始化视图
        root.findViewById(R.id.btn_confirm).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AccountHelper.isTokenValid(new Api.BaseViewCallback<LoginWithInfoBean>() {
                    @Override
                    public void setData(LoginWithInfoBean loginWithInfoBean) {
                        if(loginWithInfoBean.getData().getCustomer().getOpenid() != null){
                            withdaw(loginWithInfoBean.getData().getCustomer().getOpenid());
                        }else{
                            weChatLogin();
                        }
                    }
                });
                mDialog.dismiss();
            }
        });
        root.findViewById(R.id.btn_cancel1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDialog.dismiss();
            }
        });
        mDialog.setContentView(root);
        Window dialogWindow = mDialog.getWindow();
        dialogWindow.setGravity(Gravity.CENTER);
//        dialogWindow.setWindowAnimations(R.style.dialogstyle); // 添加动画
        WindowManager.LayoutParams lp = dialogWindow.getAttributes(); // 获取对话框当前的参数值
        lp.x = 0; // 新位置X坐标
        lp.y = 0; // 新位置Y坐标
        lp.width = (int) getResources().getDisplayMetrics().widthPixels; // 宽度
        root.measure(0, 0);
        lp.height = root.getMeasuredHeight();

        lp.alpha = 9f; // 透明度
        dialogWindow.setAttributes(lp);
        mDialog.show();
    }

    private void setReloadDialog() {
        mDialog = new Dialog(this, R.style.BottomDialog);
        LinearLayout root = (LinearLayout) LayoutInflater.from(this).inflate(
                R.layout.layout_reload_tip, null);
        //初始化视图
        root.findViewById(R.id.btn_confirm).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AccountHelper.isTokenValid(new Api.BaseViewCallback<LoginWithInfoBean>() {
                    @Override
                    public void setData(LoginWithInfoBean loginWithInfoBean) {
                        Api.reload(loginWithInfoBean.getData().getToken(), new Api.BaseViewCallback<NormalBean>() {
                            @Override
                            public void setData(NormalBean normalBean) {
                                if (normalBean.getStatus() ==200) {
                                    BaseApplication.showToast("充值成功");
                                    finish();
                                }else {
                                    BaseApplication.showToast(normalBean.getMsg());
                                    finish();
                                }
                            }
                        });
                    }
                });
                mDialog.dismiss();
            }
        });
        root.findViewById(R.id.btn_cancel1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDialog.dismiss();
            }
        });
        mDialog.setContentView(root);
        Window dialogWindow = mDialog.getWindow();
        dialogWindow.setGravity(Gravity.CENTER);
//        dialogWindow.setWindowAnimations(R.style.dialogstyle); // 添加动画
        WindowManager.LayoutParams lp = dialogWindow.getAttributes(); // 获取对话框当前的参数值
        lp.x = 0; // 新位置X坐标
        lp.y = 0; // 新位置Y坐标
        lp.width = (int) getResources().getDisplayMetrics().widthPixels; // 宽度
        root.measure(0, 0);
        lp.height = root.getMeasuredHeight();

        lp.alpha = 9f; // 透明度
        dialogWindow.setAttributes(lp);
        mDialog.show();
    }
    private IWXAPI api;
    private void regexToWX() {
        api = WXAPIFactory.createWXAPI(this, Constants.APP_ID, true);
        api.registerApp(Constants.APP_ID);
    }
    private void weChatLogin() {
        SendAuth.Req req = new SendAuth.Req();
        req.scope = "snsapi_userinfo";
        req.state = "wechat_sdk_demo_test";
        api.sendReq(req);
    }

}
