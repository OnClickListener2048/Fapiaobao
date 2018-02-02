package com.pilipa.fapiaobao.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;

import com.pilipa.fapiaobao.R;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import butterknife.Bind;

/**
 * @author edz
 * @date 2018/2/3
 */

public abstract class BaseRefreshLayout extends BaseNoNetworkActivity {


    @Bind(R.id.container)
    FrameLayout mContainer;
    @Bind(R.id.smartRefreshLayout)
    SmartRefreshLayout mSmartRefreshLayout;
    private View mContentView;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_base_refresh;
    }

    protected abstract int getRefreshLayoutId();


    private void initSmartRefreshLayout() {
        mSmartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                refreshData();
            }
        });

        mSmartRefreshLayout.setDisableContentWhenRefresh(true);
    }

    @Override
    public void initView() {
        super.initView();
        initSmartRefreshLayout();
    }

    protected abstract void refreshData();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContainer = (FrameLayout) findViewById(R.id.container);
        mSmartRefreshLayout = (SmartRefreshLayout) findViewById(R.id.smartRefreshLayout);
        mContentView = LayoutInflater.from(this).inflate(getRefreshLayoutId(), null);
        mContainer.addView(mContentView);
    }
}
