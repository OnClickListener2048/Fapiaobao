package com.pilipa.fapiaobao.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.mylibrary.utils.RegexUtils;
import com.pilipa.fapiaobao.R;
import com.pilipa.fapiaobao.account.AccountHelper;
import com.pilipa.fapiaobao.base.BaseActivity;
import com.pilipa.fapiaobao.base.BaseApplication;
import com.pilipa.fapiaobao.net.Api;
import com.pilipa.fapiaobao.net.bean.ShortMessageBean;
import com.pilipa.fapiaobao.net.bean.me.NormalBean;
import com.pilipa.fapiaobao.ui.constants.Constant;
import com.pilipa.fapiaobao.utils.CountDownTimerUtils;

import butterknife.Bind;
import butterknife.OnClick;

import static com.pilipa.fapiaobao.net.Constant.LOGIN_PLATFORM_MSG;

/**
 * Created by wjn on 2017/10/23.
 */

public class BindPhoneActivity extends BaseActivity {
    private static final String TAG = "AddCompanyInfoActivity";

    @Bind(R.id.et_username)
    EditText etUsername;
    @Bind(R.id.et_password)
    EditText etPassword;
    @Bind(R.id.require_verify)
    TextView requireVerify;
    private CountDownTimerUtils countDownTimerUtils;
    private boolean mobileExact;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_bind_phone;
    }

    @OnClick({R.id._back,R.id.require_verify,R.id.btn_confirm})
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id._back: {
                finish();
            }break;
            case R.id.btn_confirm: {
                String checked = checkedLogin();
                if (checked != null) {
                    BaseApplication.showToast(checked);
                    return;
                } else {
                    bind(etUsername.getText().toString().trim());
                }
            }break;
            case R.id.require_verify: {
                mobileExact = RegexUtils.isMobileExact(etUsername.getText().toString().trim());
                if (!mobileExact) {
                    BaseApplication.showToast("请输入正确的手机号码");
                    return;
                } else {
                    //TODO 请求短信验证码
                    Api.sendMessageToVerify(etUsername.getText().toString().trim(), new Api.BaseViewCallbackWithOnStart<ShortMessageBean>() {
                        @Override
                        public void onStart() {
                            countDownTimerUtils.start();
                        }

                        @Override
                        public void onFinish() {

                        }

                        @Override
                        public void onError() {

                        }

                        @Override
                        public void setData(ShortMessageBean shortMessageBean) {

                            BaseApplication.showToast(shortMessageBean.getData());
                        }
                    });
                }
            }break;

        }
    }
    private void bind(final String phone) {
        Api.bindWX(AccountHelper.getUser().getData().getCustomer().getId(),LOGIN_PLATFORM_MSG ,phone,etPassword.getText().toString().trim(), new Api.BaseViewCallback<NormalBean>() {
            @Override
            public void setData(NormalBean normalBean) {
                if (normalBean.getStatus() == 200) {
                    BaseApplication.showToast(normalBean.getData());
                    Intent intent = new Intent();
                    intent.setAction(Constant.BIND_PHONE_ACTION);
                    intent.putExtra("phone", phone);
                    intent.putExtra("isbind", true);
                    sendBroadcast(intent);
                    finish();
                }else if(normalBean.getStatus() == 708){
                    BaseApplication.showToast(normalBean.getMsg());
                }else if(normalBean.getStatus() == 704){
                    BaseApplication.showToast(normalBean.getMsg());
                }
            }
        });
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
    @Override
    public void initView() {
        countDownTimerUtils = new CountDownTimerUtils(requireVerify, 60000, 1000);

    }

    @Override
    public void initData() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


}
