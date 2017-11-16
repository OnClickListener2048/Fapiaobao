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
import android.widget.Toast;

import com.example.mylibrary.utils.NetworkUtils;
import com.example.mylibrary.utils.TLog;
import com.pilipa.fapiaobao.MainActivity;
import com.pilipa.fapiaobao.R;
import com.pilipa.fapiaobao.account.AccountHelper;
import com.pilipa.fapiaobao.base.BaseActivity;
import com.pilipa.fapiaobao.base.BaseApplication;
import com.pilipa.fapiaobao.net.Api;
import com.pilipa.fapiaobao.net.bean.LoginWithInfoBean;
import com.pilipa.fapiaobao.net.bean.WXmodel;
import com.pilipa.fapiaobao.net.bean.me.NormalBean;
import com.pilipa.fapiaobao.net.bean.wx.PrepayBean;
import com.pilipa.fapiaobao.utils.SharedPreferencesHelper;
import com.pilipa.fapiaobao.wxapi.Constants;
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import java.math.BigDecimal;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.pilipa.fapiaobao.net.Constant.ACCOUNT_TYPE_WALLET;
import static com.pilipa.fapiaobao.net.Constant.LOGIN_PLATFORM_WX;

/**
 * Created by lyt on 2017/10/17.
 */

public class Withdraw2WXActivity extends BaseActivity {
    @Bind(R.id.amount)
    TextView tv_amount;
    @Bind(R.id.withdraw_current)
    TextView withdraw_current;
    @Bind(R.id.withdraw_max)
    TextView withdraw_max;
    private Dialog mDialog;
    private Dialog mTipDialog;
    private BigDecimal yue;

    private BroadcastReceiver mBroadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals(LoginActivity.WX_LOGIN_ACTION)) {
                String deviceToken = BaseApplication.get("deviceToken","");
                Bundle bundle = intent.getBundleExtra("extra_bundle");
                WXmodel wx_info = bundle.getParcelable("wx_info");
                if (wx_info != null) {
                    Api.bindWX(AccountHelper.getUser().getData().getCustomer().getId(), LOGIN_PLATFORM_WX, wx_info.getOpenid(), new Api.BaseViewCallback<NormalBean>() {
                        @Override
                        public void setData(NormalBean normalBean) {
                            if (normalBean.getStatus()==707) {
                                BaseApplication.showToast("绑定失败"+normalBean.getMsg());
                            }
                        }
                    });
                }
            }
        }
    };

    @Override
    protected int getLayoutId() {
        return R.layout.activity_withdraw_to_wx;
    }

    @OnClick({R.id._back, R.id.btn_withdraw, R.id.withdraw_all,R.id.question})
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.question: {
                setTipDialog();
            }
            break;
            case R.id._back: {
                finish();
            }
            break;
            case R.id.btn_withdraw: {
                setDialog();
            }
            break;
            case R.id.withdraw_all: {
                tv_amount.setText(yue.setScale(2,BigDecimal.ROUND_HALF_UP)+"");
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
        AccountHelper.isTokenValid(new Api.BaseViewCallback<LoginWithInfoBean>() {
            @Override
            public void setData(LoginWithInfoBean loginWithInfoBean) {
                if (loginWithInfoBean.getStatus() == 200) {
                    if (AccountHelper.getToken() != null && AccountHelper.getToken() != "") {
                        BigDecimal max = new BigDecimal("20000");
                        double d = loginWithInfoBean.getData().getCustomer().getAmount()
                                -loginWithInfoBean.getData().getCustomer().getFrozen();
                        yue = new BigDecimal(d);
                        withdraw_max.setText("当前余额为"+yue.setScale(2,BigDecimal.ROUND_HALF_UP)+"元,");
                        withdraw_current.setText("最大提现额度为"+max.setScale(2,BigDecimal.ROUND_HALF_UP)+"元");
                    }
                }
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    private void setTipDialog() {
        mTipDialog = new Dialog(this, R.style.BottomDialog);
        LinearLayout root = (LinearLayout) LayoutInflater.from(this).inflate(
                R.layout.layout_withdraw2wx_tip, null);
        root.findViewById(R.id.btn_cancel1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mTipDialog.dismiss();
            }
        });
        mTipDialog.setContentView(root);
        Window dialogWindow = mTipDialog.getWindow();
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
        mTipDialog.show();
    }

    private void setDialog() {
        mDialog = new Dialog(this, R.style.BottomDialog);
        LinearLayout root = (LinearLayout) LayoutInflater.from(this).inflate(
                R.layout.layout_withdraw_tip, null);
       TextView tv_bouns = (TextView)root.findViewById(R.id.tv_bouns);
        double amount  = Double.parseDouble(tv_amount.getText().toString().trim().isEmpty() ?"0":tv_amount.getText().toString().trim());
        if(amount > (double)0){
            tv_bouns.setText(amount+"");
            //初始化视图
            root.findViewById(R.id.btn_confirm).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AccountHelper.isTokenValid(new Api.BaseViewCallback<LoginWithInfoBean>() {
                        @Override
                        public void setData(LoginWithInfoBean loginWithInfoBean) {
                            if(loginWithInfoBean.getData().getCustomer().getOpenid() != null){
                                Api.withdaw(loginWithInfoBean.getData().getToken()
                                        ,ACCOUNT_TYPE_WALLET
                                        ,NetworkUtils.getIPAddress(true)
                                        ,Double.parseDouble(tv_amount.getText().toString())
                                        ,loginWithInfoBean.getData().getCustomer().getOpenid()
                                        , new Api.BaseViewCallback<PrepayBean>() {
                                            @Override
                                            public void setData(PrepayBean normalBean) {
                                                if (normalBean.getStatus() ==200) {
                                                    BaseApplication.showToast("提现成功");
                                                    finish();
                                                }else {
                                                    BaseApplication.showToast("当日充值不支持当日提现");
                                                    finish();
                                                }
                                            }
                                        });
                            }else{
                                BaseApplication.showToast("请先绑定微信账号");
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
        }else{
            Toast.makeText(this,"提现金额不正确",Toast.LENGTH_SHORT).show();
        }

    }

    private void bindWX() {

    }

    private IWXAPI api;
    private void regexToWX() {
        api = WXAPIFactory.createWXAPI(this, Constants.APP_ID, true);
        api.registerApp(Constants.APP_ID);
    }
    private void weChatLogin() {
        if (api.isWXAppInstalled()) {
            SendAuth.Req req = new SendAuth.Req();
            req.scope = "snsapi_userinfo";
            req.state = "wechat_sdk_demo_test";
            api.sendReq(req);
        } else {
            BaseApplication.showToast("请安装微信客户端");
        }

    }

}
