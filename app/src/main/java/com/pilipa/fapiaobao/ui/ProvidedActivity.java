package com.pilipa.fapiaobao.ui;

import android.os.Bundle;
import android.view.View;

import com.pilipa.fapiaobao.R;
import com.pilipa.fapiaobao.base.BaseActivity;

import butterknife.ButterKnife;

/**
 * Created by wjn on 2017/10/24.
 */

public class ProvidedActivity extends BaseActivity {


    @Override
    protected int getLayoutId() {
        return R.layout.activity_provided;
    }

    @Override
    public void onClick(View v) {

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
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
