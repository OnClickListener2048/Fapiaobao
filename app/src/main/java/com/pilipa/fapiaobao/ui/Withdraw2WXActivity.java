package com.pilipa.fapiaobao.ui;

import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.text.InputFilter;
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
import com.pilipa.fapiaobao.net.bean.LoginWithInfoBean;
import com.pilipa.fapiaobao.net.bean.WXmodel;
import com.pilipa.fapiaobao.net.bean.me.NormalBean;
import com.pilipa.fapiaobao.net.bean.wx.PrepayBean;
import com.pilipa.fapiaobao.ui.constants.Constant;
import com.pilipa.fapiaobao.ui.widget.CashierInputFilter;
import com.pilipa.fapiaobao.utils.DialogUtil;
import com.pilipa.fapiaobao.wxapi.Constants;
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import java.math.BigDecimal;
import java.util.Objects;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.pilipa.fapiaobao.net.Constant.ACCOUNT_TYPE_WALLET;
import static com.pilipa.fapiaobao.net.Constant.LOGIN_PLATFORM_WX;
import static com.pilipa.fapiaobao.ui.LoginActivity.WX_LOGIN_ACTION;

/**
 * Created by lyt on 2017/10/17.
 */

public class Withdraw2WXActivity extends BaseActivity {
    private static final String TAG = "Withdraw2WXActivity";

    @Bind(R.id.amount)
    TextView tv_amount;
    @Bind(R.id.withdraw_current)
    TextView withdraw_current;
    @Bind(R.id.withdraw_max)
    TextView withdraw_max;
    @Bind(R.id.btn_withdraw)
    TextView btnWithdraw;
    private Dialog mDialog;
    private Dialog mTipDialog;
    private BigDecimal yue;

    private BroadcastReceiver mBroadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals(WX_LOGIN_ACTION)) {
                btnWithdraw.setEnabled(true);
                TLog.d(TAG, WX_LOGIN_ACTION + " success");
                String deviceToken = BaseApplication.get("deviceToken", "");
                Bundle bundle = intent.getBundleExtra("extra_bundle");
                WXmodel wx_info = bundle.getParcelable(Constant.WX_INFO);
                /*微信提现
                * 1.调起微信登录获取当前登录OPENID
                * 2.获取本地OPENID 去绑定微信——>提现/校对与绑定是否一致*/
                if (wx_info != null) {
                    if (AccountHelper.getUser().getData().getCustomer().getOpenid() == null) {
                        bind(wx_info.getOpenid());
                    } else {
                        if (wx_info.getOpenid().equals(AccountHelper.getUser().getData().getCustomer().getOpenid())) {
                            withdaw(wx_info.getOpenid());
                        } else {
                            BaseApplication.showToast(getString(R.string.ummatched_wx_openid));
                        }
                    }
                }
            } else if (TextUtils.equals(intent.getAction(), Constant.WX_LOGIN_CANCEL)) {
                hideProgressDialog();
            }
        }
    };
    private IWXAPI api;
    private Dialog mWithdrawTipDialog;
    private Dialog mWithdrawToWxDialog;

    private void bind(final String openID) {
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
                if (normalBean.getStatus() == com.pilipa.fapiaobao.net.Constant.REQUEST_SUCCESS) {
                    BaseApplication.showToast(getString(R.string.WX_bind_success));
                    withdaw(openID);
                    /*更新openid 避免重复绑定*/
                    AccountHelper.updateCustomerOpenId(openID);
                } else if (normalBean.getStatus() == 707) {
                    BaseApplication.showToast(normalBean.getMsg());
                }
            }
        });
    }

    private void withdaw(String openID) {
        Api.withdaw(AccountHelper.getToken()
                , ACCOUNT_TYPE_WALLET
                , NetworkUtils.getIPAddress(true)
                , Double.parseDouble(tv_amount.getText().toString().trim().isEmpty() ? "0" : tv_amount.getText().toString().trim())
                , openID
                , new Api.BaseViewCallbackWithOnStart<PrepayBean>() {
                    @Override
                    public void onStart() {
                        showProgressDialog();
                    }

                    @Override
                    public void onFinish() {
                        hideProgressDialog();
                        btnWithdraw.setEnabled(true);
                    }

                    @Override
                    public void onError() {
                        hideProgressDialog();
                        btnWithdraw.setEnabled(true);
                    }

                    @Override
                    public void setData(PrepayBean normalBean) {

                        if (normalBean.getStatus() == com.pilipa.fapiaobao.net.Constant.REQUEST_SUCCESS) {
                            Intent intent = new Intent();
                            intent.putExtra(Constant.TITLE, getString(R.string.withdraw_success));
                            intent.putExtra(Constant.MESSAGE, getString(R.string.withdraw_message,tv_amount.getText().toString().trim()));
                            setResult(RESULT_OK,intent);

                            finish();
                        } else if (normalBean.getStatus() == com.pilipa.fapiaobao.net.Constant.INSUFFICIENT_ACCOUNT) {
                            BaseApplication.showToast(getString(R.string.insufficient_account));//账户余额不足
                        } else {
                            BaseApplication.showToast(normalBean.getMsg());
                        }
                    }
                });
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_withdraw_to_wx;
    }

    @OnClick({R.id._back, R.id.btn_withdraw, R.id.withdraw_all, R.id.question})
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.question: {
                showWithdrawWarnninnDialog();
            }
            break;
            case R.id._back: {
                setResult(RESULT_CANCELED);
                finish();
            }
            break;
            case R.id.btn_withdraw: {
                if (Double.parseDouble(tv_amount.getText().toString().trim().isEmpty() ? "0" : tv_amount.getText().toString().trim()) < 1.0) {
                    BaseApplication.showToast(getString(R.string.withdraw_balance_cannot_below_one_yuan));
                } else if (Double.parseDouble(withdraw_max.getText().toString()) < Double.parseDouble(tv_amount.getText().toString())) {
                    BaseApplication.showToast(getString(R.string.insufficient_account));
                } else if (Double.parseDouble(withdraw_max.getText().toString()) <= (double) 0) {
                    BaseApplication.showToast(getString(R.string.insufficient_account));
                } else {
                    showWithdrawTipDialog();
                }
            }
            break;
            case R.id.withdraw_all: {
                if (yue != null) {
                    tv_amount.setText(String.valueOf(yue.setScale(2, BigDecimal.ROUND_HALF_UP)));
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
        filter.addAction(Constant.WX_LOGIN_CANCEL);
        registerReceiver(mBroadcastReceiver, filter);

        InputFilter[] cashierInputFilter = {new CashierInputFilter()};
        tv_amount.setFilters(cashierInputFilter);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(mBroadcastReceiver);
    }

    @Override
    public void initData() {
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
                if (loginWithInfoBean.getStatus() == com.pilipa.fapiaobao.net.Constant.REQUEST_SUCCESS) {
                    AccountHelper.updateCustomer(loginWithInfoBean.getData().getCustomer());
                    if (AccountHelper.getToken() != null && !Objects.equals(AccountHelper.getToken(), "")) {
                        double d = loginWithInfoBean.getData().getCustomer().getAmount()
                                - loginWithInfoBean.getData().getCustomer().getFrozen();
                        BigDecimal amount = new BigDecimal(loginWithInfoBean.getData().getCustomer().getAmount());
                        yue = new BigDecimal(d);
                        withdraw_max.setText(String.valueOf(yue.setScale(2, BigDecimal.ROUND_HALF_UP)));
                        withdraw_current.setText(String.valueOf(amount.setScale(2, BigDecimal.ROUND_HALF_UP)));
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

    private void showWithdrawWarnninnDialog() {
        if (mWithdrawToWxDialog == null) {
            mWithdrawToWxDialog = DialogUtil.getInstance().createDialog(this, 0, R.layout.layout_withdraw2wx_tip, new DialogUtil.OnKnownListener() {
                @Override
                public void onKnown(View view) {
                    mWithdrawToWxDialog.dismiss();
                }
            }, null, null);
        }
        showDialog(mWithdrawToWxDialog);
    }

//    private void setTipDialog() {
//        mTipDialog = new Dialog(this, R.style.BottomDialog);
//        LinearLayout root = (LinearLayout) LayoutInflater.from(this).inflate(
//                R.layout.layout_withdraw2wx_tip, null);
//        root.findViewById(R.id.btn_cancel1).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                mTipDialog.dismiss();
//            }
//        });
//        mTipDialog.setContentView(root);
//        Window dialogWindow = mTipDialog.getWindow();
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
//        mTipDialog.show();
//    }

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
//        TextView tv_bouns = (TextView) root.findViewById(R.id.tv_bouns);
//        double amount = Double.parseDouble(tv_amount.getText().toString().trim().isEmpty() ? "0" : tv_amount.getText().toString().trim());
//        if (amount > (double) 0) {
//            tv_bouns.setText(String.valueOf(amount));
//            //初始化视图
//            root.findViewById(R.id.btn_confirm).setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                   /*提现流程： weChatLogin  openid--Y--> withdraw
//                   *                              --N-->  bind  ----> withdraw*/
//                    weChatLogin();
//                    mDialog.dismiss();
//                }
//            });
//            root.findViewById(R.id.btn_cancel1).setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    mDialog.dismiss();
//                }
//            });
//            mDialog.setContentView(root);
//            Window dialogWindow = mDialog.getWindow();
//            dialogWindow.setGravity(Gravity.CENTER);
////        dialogWindow.setWindowAnimations(R.style.dialogstyle); // 添加动画
//            WindowManager.LayoutParams lp = dialogWindow.getAttributes(); // 获取对话框当前的参数值
//            lp.x = 0; // 新位置X坐标
//            lp.y = 0; // 新位置Y坐标
//            lp.width = (int) getResources().getDisplayMetrics().widthPixels; // 宽度
//            root.measure(0, 0);
//            lp.height = root.getMeasuredHeight();
//
//            lp.alpha = 9f; // 透明度
//            dialogWindow.setAttributes(lp);
//            mDialog.show();
//        } else {
//            BaseApplication.showToast(getString(R.string.withdraw_balance_not_right));
//        }
//    }

    private void regexToWX() {
        api = WXAPIFactory.createWXAPI(this, Constants.APP_ID, true);
        api.registerApp(Constants.APP_ID);
    }

    private void weChatLogin() {

        if (api.isWXAppInstalled()) {
            btnWithdraw.setEnabled(false);
            showProgressDialog();
            SendAuth.Req req = new SendAuth.Req();
            req.scope = "snsapi_userinfo";
            req.state = "wechat_sdk_demo_test";
            api.sendReq(req);
        } else {
            BaseApplication.showToast(getString(R.string.please_install_WX_app));
        }

    }

}
