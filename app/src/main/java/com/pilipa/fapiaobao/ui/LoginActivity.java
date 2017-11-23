package com.pilipa.fapiaobao.ui;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mylibrary.utils.RegexUtils;
import com.example.mylibrary.utils.TLog;
import com.pilipa.fapiaobao.R;
import com.pilipa.fapiaobao.base.BaseActivity;
import com.pilipa.fapiaobao.base.BaseApplication;
import com.pilipa.fapiaobao.net.Api;
import com.pilipa.fapiaobao.net.bean.LoginWithInfoBean;
import com.pilipa.fapiaobao.net.bean.ShortMessageBean;
import com.pilipa.fapiaobao.net.bean.WXmodel;
import com.pilipa.fapiaobao.ui.constants.Constant;
import com.pilipa.fapiaobao.utils.CountDownTimerUtils;
import com.pilipa.fapiaobao.utils.SharedPreferencesHelper;
import com.pilipa.fapiaobao.wxapi.Constants;
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.pilipa.fapiaobao.net.Constant.LOGIN_PLATFORM_MSG;
import static com.pilipa.fapiaobao.net.Constant.REGISTRATION;

/**
 * Created by lyt on 2017/10/12.
 */

public class LoginActivity extends BaseActivity implements View.OnFocusChangeListener {
    String TAG = "LoginActivity";
    @Bind(R.id.et_username)
    EditText etUsername;
    @Bind(R.id.et_password)
    EditText etPassword;
    @Bind(R.id.tv_newAccount)
    TextView tvNewAccount;
    @Bind(R.id.btn_login)
    Button btnLogin;
    @Bind(R.id.WeChat_login)
    TextView WeChatLogin;
    @Bind(R.id.laws)
    TextView laws;
    @Bind(R.id.tv_warnning)
    TextView tvWarnning;
    @Bind(R.id.iv_login_phone)
    ImageView ivLoginPhone;
    @Bind(R.id.iv_verification)
    ImageView ivVerification;
    @Bind(R.id.require_verify)
    TextView requireVerify;
    private CountDownTimerUtils countDownTimerUtils;
    private IWXAPI api;
    private boolean mobileExact;
    public static final String WX_LOGIN_ACTION = "com.pilipa.fapiaobao.wxlogin";
    private BroadcastReceiver mBroadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, final Intent intent) {
            if (intent.getAction().equals(WX_LOGIN_ACTION)) {
                String deviceToken = BaseApplication.get("deviceToken","");
                Bundle bundle = intent.getBundleExtra("extra_bundle");
                WXmodel wx_info = bundle.getParcelable("wx_info");
                assert wx_info != null;
                Api.login("1", wx_info.getOpenid(), wx_info.getAccess_token(), deviceToken, new Api.BaseViewCallback<LoginWithInfoBean>() {
                    @Override
                    public void setData(final LoginWithInfoBean loginWithInfoBean) {
                        if (loginWithInfoBean.getStatus()==200) {
                            BaseApplication.showToast("微信登录成功");
                            SharedPreferencesHelper.save(LoginActivity.this, loginWithInfoBean);
                            finish();
                        }
                    }
                });
            }

        }
    };

    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    public void onClick(View view) {

    }

    @Override
    public void initView() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(WX_LOGIN_ACTION);
        registerReceiver(mBroadcastReceiver, intentFilter);
        countDownTimerUtils = new CountDownTimerUtils(requireVerify, 60000, 1000);
        regexToWX();

        etUsername.setOnFocusChangeListener(this);
    }

    private void regexToWX() {
        api = WXAPIFactory.createWXAPI(this, Constants.APP_ID, true);
        api.registerApp(Constants.APP_ID);
    }

    @Override
    public void initData() {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(mBroadcastReceiver);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.btn_login, R.id.WeChat_login, R.id.laws})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_login:
                login(LOGIN_PLATFORM_MSG
                      ,etUsername.getText().toString().trim()
                      ,etPassword.getText().toString().trim());

                break;
            case R.id.WeChat_login:
                weChatLogin();
                break;
            case R.id.laws:
                Intent intent = new Intent();
                intent.putExtra("url", REGISTRATION);
                intent.setClass(this, Op.class);
                startActivity(intent);
                break;
        }
    }

    private void weChatLogin() {
        if (api.isWXAppInstalled()) {
            SendAuth.Req req = new SendAuth.Req();
            req.scope = "snsapi_userinfo";
            req.state = "wechat_sdk_demo_test";
            api.sendReq(req);
        } else {
            BaseApplication.showToast(getString(R.string.please_install_WX));
        }

    }

    private void login(String platform,String credenceName,String credenceCode){
        String checked = checkedLogin();
        tvWarnning.setVisibility(checked == null ? View.GONE : View.VISIBLE);
        tvWarnning.setText(checked);
        ivLoginPhone.setSelected(!mobileExact);
        if (checked != null) {
            BaseApplication.showToast(checked);
            return;
        } else {
            String deviceToken = BaseApplication.get("deviceToken",null);
            Log.d(TAG, "login :deviceToken "+deviceToken);
            Api.login(platform,credenceName,credenceCode,deviceToken,new Api.BaseViewCallback<LoginWithInfoBean>() {
                        @Override
                        public void setData(LoginWithInfoBean loginWithInfoBean) {
                            Log.d(TAG, "setData: SharedPreferencesHelper.save(LoginActivity.this,loginBean);success");
                            loginWithInfoBean.getData().setCustomer(loginWithInfoBean.getData().getCustomer());
                            SharedPreferencesHelper.save(LoginActivity.this, loginWithInfoBean.getData().getCustomer());
                            boolean save = SharedPreferencesHelper.save(LoginActivity.this, loginWithInfoBean);
                            Log.d(TAG, "setData:save "+save);
                            if (Constant.LOGIN_TO_PUBLISH.equals(getIntent().getAction())) {
                                startActivity(new Intent(LoginActivity.this,DemandsPublishActivity.class));
                            }
                            finish();

                        }
                    });
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            TLog.log("RESULT_OK");
        }
    }

    private String checkedLogin(){
        mobileExact = RegexUtils.isMobileExact(etUsername.getText().toString().trim());
        if(etUsername.getText().toString().trim().isEmpty()){
            return "手机号不能为空";
        } else if (etPassword.getText().toString().trim().isEmpty()){
            return "验证码不能为空";
        } else if (!mobileExact){
            return "请输入正确的手机号码";
        } else {
            return null;
        }
    }

    @OnClick(R.id.require_verify)
    public void onViewClicked() {
        mobileExact = RegexUtils.isMobileExact(etUsername.getText().toString().trim());
        tvWarnning.setVisibility(mobileExact ? View.GONE : View.VISIBLE);
        ivLoginPhone.setSelected(!mobileExact);
        if (!mobileExact) {
            BaseApplication.showToast("请输入正确的手机号码");
            return;
        } else {
            //TODO 请求短信验证码
            Api.sendMessageToVerify(etUsername.getText().toString().trim(), new Api.BaseViewCallback<ShortMessageBean>() {
                @Override
                public void setData(ShortMessageBean shortMessageBean) {
                        countDownTimerUtils.start();
                        BaseApplication.showToast(shortMessageBean.getData());
                }
            });
        }
    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        if (!hasFocus) {


        }
    }
}
