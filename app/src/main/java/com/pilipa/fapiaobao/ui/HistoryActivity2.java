package com.pilipa.fapiaobao.ui;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.example.mylibrary.utils.ImageUtils;
import com.pilipa.fapiaobao.R;
import com.pilipa.fapiaobao.adapter.TabPageIndicatorAdapter;
import com.pilipa.fapiaobao.base.BaseActivity;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by lyt on 2017/10/17.
 */

public class HistoryActivity2 extends BaseActivity {

    @Bind(R.id.tl_publish_history)
    TabLayout tlPublishHistory;
    @Bind(R.id.vp_publish_history)
    ViewPager vpPublishHistory;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_publish_history2;
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void initView() {
        vpPublishHistory.setAdapter(new TabPageIndicatorAdapter(getSupportFragmentManager()));
        tlPublishHistory.setupWithViewPager(vpPublishHistory);
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
