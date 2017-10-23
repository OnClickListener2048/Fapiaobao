package com.pilipa.fapiaobao.ui;

import android.os.Bundle;
import android.view.View;

import com.pilipa.fapiaobao.R;
import com.pilipa.fapiaobao.base.BaseActivity;

import butterknife.ButterKnife;

/**
 * Created by lyt on 2017/10/17.
 */

public class FeedbackActivity extends BaseActivity {


    @Override
    protected int getLayoutId() {
        return R.layout.activity_feedback;
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
