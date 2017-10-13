package com.pilipa.fapiaobao.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.pilipa.fapiaobao.R;
import com.pilipa.fapiaobao.base.BaseActivity;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by lyt on 2017/10/12.
 */

public class LoginActivity extends BaseActivity {



    @Bind(R.id.et_username)
    EditText etUsername;
    @Bind(R.id.et_password)
    EditText etPassword;
    @Bind(R.id.eye_login)
    ImageView eyeLogin;
    @Bind(R.id.tv_newAccount)
    TextView tvNewAccount;
    @Bind(R.id.login)
    Button login;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    public void onClick(View view) {

    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {

    }


    @OnClick({R.id.et_username, R.id.et_password, R.id.eye_login, R.id.tv_newAccount, R.id.login})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.et_username:
                break;
            case R.id.et_password:
                break;
            case R.id.eye_login:
                break;
            case R.id.tv_newAccount:
                break;
            case R.id.login:
                break;
        }
    }


}
