package com.pilipa.fapiaobao.ui;

import android.os.Bundle;
import android.view.View;

import com.pilipa.fapiaobao.R;
import com.pilipa.fapiaobao.base.BaseActivity;
import com.pilipa.fapiaobao.ui.widget.ColorArcProgressBar;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by wjn on 2017/10/23.
 */

public class CreditRatingActivity extends BaseActivity {
    @Bind(R.id.bar2)
    ColorArcProgressBar colorArcProgressBar;
    @Override
    protected int getLayoutId() {
        return R.layout.activity_credit_rating;
    }

    @OnClick({R.id.details_back})
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.details_back:{
                finish();
            }break;
        }
    }

    @Override
    public void initView() {
        colorArcProgressBar.setCurrentValues(92);
    }

    @Override
    public void initData() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}
