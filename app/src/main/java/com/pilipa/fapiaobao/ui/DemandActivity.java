package com.pilipa.fapiaobao.ui;

import android.os.Bundle;
import android.view.View;

import com.pilipa.fapiaobao.R;
import com.pilipa.fapiaobao.base.BaseActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by lyt on 2017/10/16.
 */

public class DemandActivity extends BaseActivity {
    @Override
    protected int getLayoutId() {
        return R.layout.activity_demand;
    }

    @OnClick({R.id.demand_back})
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.demand_back:{
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
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
