package com.pilipa.fapiaobao.ui;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.pilipa.fapiaobao.R;
import com.pilipa.fapiaobao.adapter.TabPageIndicatorAdapter;
import com.pilipa.fapiaobao.base.BaseActivity;
import com.pilipa.fapiaobao.base.BaseApplication;
import com.pilipa.fapiaobao.ui.fragment.UnusedPagerFragment_close;
import com.pilipa.fapiaobao.ui.fragment.UnusedPagerFragment_finish;
import com.pilipa.fapiaobao.ui.fragment.UnusedPagerFragment_ing;
import com.pilipa.fapiaobao.ui.model.StaticDataCreator;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by lyt on 2017/10/17.
 */

public class HistoryActivity2 extends BaseActivity {
    private static final String TAG = "HistoryActivity2";

    @Bind(R.id.tl_publish_history)
    TabLayout tlPublishHistory;
    @Bind(R.id.vp_publish_history)
    ViewPager vpPublishHistory;
    private List<Fragment> fragmentList;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_publish_history2;
    }

    @OnClick({R.id.publish_history_back})
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.publish_history_back:{
                finish();
            }break;
        }
    }
    @Override
    public void initView() {
        List list = StaticDataCreator.initMyPublishTabData(BaseApplication.context());
        fragmentList = new ArrayList<>();
        fragmentList.add(new UnusedPagerFragment_ing());
        fragmentList.add(new UnusedPagerFragment_finish());
        fragmentList.add(new UnusedPagerFragment_close());
        vpPublishHistory.setAdapter(new TabPageIndicatorAdapter(getSupportFragmentManager(),list,fragmentList));
        tlPublishHistory.setupWithViewPager(vpPublishHistory);
        vpPublishHistory.setOffscreenPageLimit(3);
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
