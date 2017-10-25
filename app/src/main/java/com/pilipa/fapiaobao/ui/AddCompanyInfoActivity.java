package com.pilipa.fapiaobao.ui;

import android.os.Bundle;
import android.view.View;

import com.pilipa.fapiaobao.R;
import com.pilipa.fapiaobao.base.BaseActivity;

import butterknife.OnClick;

/**
 * Created by wjn on 2017/10/23.
 */

public class AddCompanyInfoActivity extends BaseActivity {


    @Override
    protected int getLayoutId() {
        return R.layout.activity_company_info_add;
    }

    @OnClick({R.id.add_back})
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.add_back:{
                finish();
            }break;
        }
    }

    @Override
    public void initView() {
    }

    @Override
    public void initData() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}
