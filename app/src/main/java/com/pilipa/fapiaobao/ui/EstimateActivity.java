package com.pilipa.fapiaobao.ui;

import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.view.Gravity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.pilipa.fapiaobao.R;
import com.pilipa.fapiaobao.base.BaseActivity;
import com.pilipa.fapiaobao.ui.fragment.FilterFragment;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author edz
 * @date 2017/10/23
 */

public class EstimateActivity extends BaseActivity {

    @Bind(R.id.dl_container)
    FrameLayout dlContainer;
    @Bind(R.id.dl)
    DrawerLayout dl;
    @Bind(R.id.filter)
    TextView filter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_estimate;
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void initView() {
        addFragment(dlContainer.getId(), FilterFragment.newInstance(new Bundle()));
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

    @OnClick(R.id.filter)
    public void onViewClicked() {
        if (dl.isDrawerOpen(Gravity.END)) {
            dl.closeDrawer(Gravity.END);
        } else {

            dl.openDrawer(Gravity.END);
        }

    }
}
