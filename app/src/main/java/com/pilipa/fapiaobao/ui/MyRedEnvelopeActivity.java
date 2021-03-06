package com.pilipa.fapiaobao.ui;

import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.example.mylibrary.utils.NetworkUtils;
import com.example.mylibrary.utils.TLog;
import com.pilipa.fapiaobao.R;
import com.pilipa.fapiaobao.account.AccountHelper;
import com.pilipa.fapiaobao.base.BaseActivity;
import com.pilipa.fapiaobao.base.BaseApplication;
import com.pilipa.fapiaobao.net.Api;
import com.pilipa.fapiaobao.net.Constant;
import com.pilipa.fapiaobao.net.bean.LoginWithInfoBean;
import com.pilipa.fapiaobao.net.bean.WXmodel;
import com.pilipa.fapiaobao.net.bean.me.NormalBean;
import com.pilipa.fapiaobao.net.bean.wx.PrepayBean;
import com.pilipa.fapiaobao.ui.widget.RiseNumberTextView;
import com.pilipa.fapiaobao.utils.DialogUtil;
import com.pilipa.fapiaobao.wxapi.Constants;
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.pilipa.fapiaobao.net.Constant.ACCOUNT_TYPE_RED;
import static com.pilipa.fapiaobao.net.Constant.LOGIN_PLATFORM_WX;
import static com.pilipa.fapiaobao.net.Constant.REQUEST_SUCCESS;
import static com.pilipa.fapiaobao.ui.LoginActivity.WX_LOGIN_ACTION;

/**
 * Created by lyt on 2017/10/17.
 */

public class MyRedEnvelopeActivity extends BaseActivity {
    private static final String TAG = "MyRedEnvelopeActivity";

    @Bind(R.id.bonus)
    RiseNumberTextView tv_bonus;
    @Bind(R.id.btn_withdraw)
    TextView btnWithdraw;
    private Dialog mDialog;
    private String bonus;
    private BroadcastReceiver mBroadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {

            if (TextUtils.equals(WX_LOGIN_ACTION, intent.getAction())) {
                btnWithdraw.setEnabled(true);
                hideProgressDialog();
                TLog.d(TAG,WX_LOGIN_ACTION +" success");
                String deviceToken = BaseApplication.get("deviceToken","");
                Bundle bundle = intent.getBundleExtra("extra_bundle");
                WXmodel wx_info = bundle.getParcelable("wx_info");
                  /*微信提现
                * 1.调起微信登录获取当前登录OPENID
                * 2.获取本地OPENID 去绑定微信——>提现/校对与绑定是否一致*/
                if (wx_info != null) {
                    if(AccountHelper.getUser().getData().getCustomer().getOpenid()==null){
                        bind(wx_info.getOpenid());
                    }else{
                        if(wx_info.getOpenid().equals(AccountHelper.getUser().getData().getCustomer().getOpenid())){
                            withdaw(wx_info.getOpenid());
                        }else{
                            btnWithdraw.setEnabled(true);
                            BaseApplication.showToast("系统检测到您登录的微信账号与绑定的不一致");
                        }
                    }
                }
            } else if (com.pilipa.fapiaobao.ui.constants.Constant.WX_LOGIN_CANCEL.equals(intent.getAction())) {
                hideProgressDialog();
            }
        }
    };
    private IWXAPI api;
    private Dialog mWithdrawTipDialog;
    private Dialog mReloadDialog;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_red_envelope;
    }

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
                    withdaw(openID);
                    /*更新openid 避免重复绑定*/
                    AccountHelper.updateCustomerOpenId(openID);

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
                , new Api.BaseViewCallbackWithOnStart<PrepayBean>() {
                    @Override
                    public void onStart() {
                        showProgressDialog();
                        btnWithdraw.setEnabled(false);
                    }

                    @Override
                    public void onFinish() {
                        hideProgressDialog();
                        btnWithdraw.setEnabled(true);
                    }

                    @Override
                    public void onError() {

                    }

                    @Override
                    public void setData(PrepayBean normalBean) {
                        if (normalBean.getStatus() ==Constant.REQUEST_SUCCESS) {
                            Intent intent = new Intent();
                            intent.putExtra(com.pilipa.fapiaobao.ui.constants.Constant.TITLE, getString(R.string.withdraw_success));
                            intent.putExtra(com.pilipa.fapiaobao.ui.constants.Constant.MESSAGE, getString(R.string.withdraw_message,bonus));
                            setResult(RESULT_OK,intent);
                            finish();
                        }else if(normalBean.getStatus() ==Constant.INSUFFICIENT_ACCOUNT){
                            BaseApplication.showToast(getString(R.string.insufficient_account));
                        }else{
                            BaseApplication.showToast(normalBean.getMsg());
                        }
                    }
                });
    }

    @OnClick({R.id._back, R.id.btn_withdraw, R.id.btn_recharge})
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id._back: {
                setResult(RESULT_CANCELED);
                finish();
            }
            break;
            case R.id.btn_withdraw: {
                if(Double.parseDouble(bonus)>0.0){
                    if(Double.parseDouble(bonus)<1.0){
                        BaseApplication.showToast("提现金额不能少于1元");
                    }else{
                        showWithdrawTipDialog();
                    }
                }else{
                    BaseApplication.showToast("红包余额不足");
                }
            }
            break;
            case R.id.btn_recharge: {
                if(Double.parseDouble(bonus)>0.0){
                    showReloadDialog();
                }else{
                    BaseApplication.showToast("红包余额不足");
                }
            }
            break;
            default:
        }
    }

    @Override
    public void initView() {
        regexToWX();
        IntentFilter filter = new IntentFilter();
        filter.addAction(LoginActivity.WX_LOGIN_ACTION);
        filter.addAction(com.pilipa.fapiaobao.ui.constants.Constant.WX_LOGIN_CANCEL);
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
        setupViews(bonus);
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
                hideProgressDialog();
            }

            @Override
            public void onTokenInvalid() {

            }

            @Override
            public void setData(LoginWithInfoBean loginWithInfoBean) {
                if(loginWithInfoBean.getStatus() == REQUEST_SUCCESS){
                    AccountHelper.updateCustomer(loginWithInfoBean.getData().getCustomer());
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

    private void setupViews(String bonus) {
        // 设置数据
        tv_bonus.withNumber(Float.parseFloat(bonus));
        // 设置动画播放时间
        tv_bonus.setDuration(1000);
        // 监听动画播放结束
        tv_bonus.setOnEndListener(new RiseNumberTextView.EndListener() {

            @Override
            public void onEndFinish() {
            }
        });
        tv_bonus.start();
    }

    private void showWithdrawTipDialog() {
        if (mWithdrawTipDialog == null) {
            mWithdrawTipDialog = DialogUtil.getInstance().createDialog(this, 0, R.layout.layout_withdraw_tip, null, new DialogUtil.OnConfirmListener() {
                @Override
                public void onConfirm(View view) {
                    weChatLogin();
                    mWithdrawTipDialog.dismiss();
                }
            }, new DialogUtil.OnCancelListener() {
                @Override
                public void onCancel(View view) {
                    mWithdrawTipDialog.dismiss();
                }
            });
        }
        showDialog(mWithdrawTipDialog);
    }

//    private void setDialog() {
//        mDialog = new Dialog(this, R.style.BottomDialog);
//        LinearLayout root = (LinearLayout) LayoutInflater.from(this).inflate(
//                R.layout.layout_withdraw_tip, null);
//       TextView tv_bouns = (TextView)root.findViewById(R.id.tv_bouns);
//        tv_bouns.setText(bonus);
//        //初始化视图
//        root.findViewById(R.id.btn_confirm).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                mDialog.dismiss();
//            }
//        });
//        root.findViewById(R.id.btn_cancel1).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                mDialog.dismiss();
//            }
//        });
//        mDialog.setContentView(root);
//        Window dialogWindow = mDialog.getWindow();
//        dialogWindow.setGravity(Gravity.CENTER);
////        dialogWindow.setWindowAnimations(R.style.dialogstyle); // 添加动画
//        WindowManager.LayoutParams lp = dialogWindow.getAttributes(); // 获取对话框当前的参数值
//        lp.x = 0; // 新位置X坐标
//        lp.y = 0; // 新位置Y坐标
//        lp.width = (int) getResources().getDisplayMetrics().widthPixels; // 宽度
//        root.measure(0, 0);
//        lp.height = root.getMeasuredHeight();
//
//        lp.alpha = 9f; // 透明度
//        dialogWindow.setAttributes(lp);
//        mDialog.show();
//    }

    private void reload() {
        Api.reload(AccountHelper.getToken(), new Api.BaseViewCallback<NormalBean>() {
            @Override
            public void setData(NormalBean normalBean) {
                if (normalBean.getStatus() == Constant.REQUEST_SUCCESS) {
                    Intent intent = new Intent();
                    intent.putExtra(com.pilipa.fapiaobao.ui.constants.Constant.TITLE, getString(R.string.recharge_success));
                    intent.putExtra(com.pilipa.fapiaobao.ui.constants.Constant.MESSAGE, getString(R.string.recharge_message, bonus));
                    setResult(RESULT_OK, intent);
                    finish();
                } else {
                    BaseApplication.showToast(normalBean.getMsg());
                    setResult(RESULT_CANCELED);
                    finish();
                }
            }
        });
    }

    private void showReloadDialog() {
        if (mReloadDialog == null) {
            mReloadDialog = DialogUtil.getInstance().createDialog(this, 0, R.layout.layout_reload_tip, null, new DialogUtil.OnConfirmListener() {
                @Override
                public void onConfirm(View view) {
                    reload();
                    mReloadDialog.dismiss();
                }
            }, new DialogUtil.OnCancelListener() {
                @Override
                public void onCancel(View view) {
                    mReloadDialog.dismiss();
                }
            });
        }
        showDialog(mReloadDialog);
    }

//    private void setReloadDialog() {
//        mDialog = new Dialog(this, R.style.BottomDialog);
//        LinearLayout root = (LinearLayout) LayoutInflater.from(this).inflate(
//                R.layout.layout_reload_tip, null);
//        //初始化视图
//        root.findViewById(R.id.btn_confirm).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                reload();
//                mDialog.dismiss();
//            }
//        });
//        root.findViewById(R.id.btn_cancel1).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                mDialog.dismiss();
//            }
//        });
//        mDialog.setContentView(root);
//        Window dialogWindow = mDialog.getWindow();
//        dialogWindow.setGravity(Gravity.CENTER);
////        dialogWindow.setWindowAnimations(R.style.dialogstyle); // 添加动画
//        WindowManager.LayoutParams lp = dialogWindow.getAttributes(); // 获取对话框当前的参数值
//        lp.x = 0; // 新位置X坐标
//        lp.y = 0; // 新位置Y坐标
//        lp.width = (int) getResources().getDisplayMetrics().widthPixels; // 宽度
//        root.measure(0, 0);
//        lp.height = root.getMeasuredHeight();
//
//        lp.alpha = 9f; // 透明度
//        dialogWindow.setAttributes(lp);
//        mDialog.show();
//    }

    private void regexToWX() {
        api = WXAPIFactory.createWXAPI(this, Constants.APP_ID, true);
        api.registerApp(Constants.APP_ID);
    }
    private void weChatLogin() {
        if (api.isWXAppInstalled()) {
            showProgressDialog();
            btnWithdraw.setEnabled(false);
            SendAuth.Req req = new SendAuth.Req();
            req.scope = "snsapi_userinfo";
            req.state = "wechat_sdk_demo_test";
            api.sendReq(req);
        } else {
            BaseApplication.showToast(getString(R.string.please_install_WX_app));
        }
    }

}
