package com.pilipa.fapiaobao.ui;

import android.content.Intent;
import android.os.Bundle;
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
import com.pilipa.fapiaobao.utils.CountDownTimerUtils;
import com.pilipa.fapiaobao.wxapi.Constants;
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by lyt on 2017/10/12.
 */

public class LoginActivity extends BaseActivity implements View.OnFocusChangeListener {

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
                Intent intent = new Intent();
                intent.setClass(this, MainActivity.class);
                startActivity(intent);
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

    @OnClick(R.id.require_verify)
    public void onViewClicked() {
        countDownTimerUtils.start();
        if (!mobileExact) {
            BaseApplication.showToast("请输入正确的手机号码");
            return;
        } else {
            //TODO 请求短信验证码
        }
    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        if (!hasFocus) {
            mobileExact = RegexUtils.isMobileExact(etUsername.getText().toString().trim());
            tvWarnning.setVisibility(mobileExact ? View.GONE : View.VISIBLE);
            ivLoginPhone.setSelected(!mobileExact);
        }
    }
}
