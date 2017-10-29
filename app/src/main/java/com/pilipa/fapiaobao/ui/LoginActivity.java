package com.pilipa.fapiaobao.ui;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mylibrary.utils.RegexUtils;
import com.pilipa.fapiaobao.MainActivity;
import com.pilipa.fapiaobao.R;
import com.pilipa.fapiaobao.base.BaseActivity;
import com.pilipa.fapiaobao.base.BaseApplication;
import com.pilipa.fapiaobao.net.Api;
import com.pilipa.fapiaobao.net.bean.LoginBean;
import com.pilipa.fapiaobao.net.bean.ShortMessageBean;
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
import static com.pilipa.fapiaobao.net.Constant.REQUEST_SUCCESS;

/**
 * Created by lyt on 2017/10/12.
 */

public class LoginActivity extends BaseActivity implements View.OnFocusChangeListener {
    private static final String TAG = "LoginActivity";

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

    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    public void onClick(View view) {

    }

    @Override
    public void initView() {
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
                break;
        }
    }

    private void weChatLogin() {
        SendAuth.Req req = new SendAuth.Req();
        req.scope = "snsapi_userinfo";
        req.state = "wechat_sdk_demo_test";
        api.sendReq(req);
    }
    private void login(String platform,String credenceName,String credenceCode){
        String checked = checkedLogin();
        tvWarnning.setVisibility(checked == null ? View.GONE : View.VISIBLE);
        tvWarnning.setText(checked);
        ivLoginPhone.setSelected(!mobileExact);
        String mPushDeviceToken = BaseApplication.get(BaseApplication.DEVICE_TOKEN,null);
        mPushDeviceToken ="Ag1qIlbdHuh6QfzTwoQLp32PB5UcyK2lydJcx7-HG35z";
        Log.d(TAG, "login:  mPushDeviceToken"+mPushDeviceToken);
        if (checked != null) {
            BaseApplication.showToast(checked);
            return;
        } else {
            Api.login(platform,credenceName,credenceCode,mPushDeviceToken,new Api.BaseViewCallback<LoginBean>() {
                        @Override
                        public void setData(LoginBean loginBean) {
                            if(loginBean.getStatus() == REQUEST_SUCCESS){
                                SharedPreferencesHelper.save(LoginActivity.this,loginBean);
                                Intent intent = new Intent();
                                intent.setClass(LoginActivity.this, MainActivity.class);
                                startActivity(intent);
                            }
                        }
                    });
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
                        BaseApplication.showToast("短信发送成功");
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
