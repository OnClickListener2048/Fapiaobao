package com.pilipa.fapiaobao.ui;

import android.os.Bundle;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.pilipa.fapiaobao.R;
import com.pilipa.fapiaobao.adapter.TabPageIndicatorAdapter;
import com.pilipa.fapiaobao.base.BaseActivity;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by lyt on 2017/10/16.
 */
@Deprecated
public class HistoryActivity extends BaseActivity {

    @Bind(R.id.tab_indicator_publish_history)
    com.viewpagerindicator.TabPageIndicator tabIndicatorPublishHistory;
    @Bind(R.id.viewpager_publish_history)
    ViewPager viewpagerPublishHistory;
    private FragmentPagerAdapter fragmentPagerAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_publish_history;

    }

    @Override
    public void onClick(View v) {

    }



    @Override
    public void initView() {
        fragmentPagerAdapter = new TabPageIndicatorAdapter(getSupportFragmentManager());
        viewpagerPublishHistory.setAdapter(fragmentPagerAdapter);
        tabIndicatorPublishHistory.setViewPager(viewpagerPublishHistory);
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
