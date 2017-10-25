package com.pilipa.fapiaobao.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.lcodecore.tkrefreshlayout.RefreshListenerAdapter;
import com.lcodecore.tkrefreshlayout.TwinklingRefreshLayout;
import com.lcodecore.tkrefreshlayout.header.progresslayout.ProgressLayout;
import com.pilipa.fapiaobao.R;
import com.pilipa.fapiaobao.adapter.MyPublishAdapter;
import com.pilipa.fapiaobao.adapter.MyReceiptAdapter;
import com.pilipa.fapiaobao.base.BaseFragment;
import com.pilipa.fapiaobao.ui.DemandActivity;
import com.pilipa.fapiaobao.ui.ProvidedActivity;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by lyt on 2017/10/17.
 */

public class MyReceiptViewPagerFragment extends BaseFragment implements AdapterView.OnItemClickListener{
    @Bind(R.id.recyclerview)
    ListView listView;
    @Bind(R.id.trl)
    TwinklingRefreshLayout trl;
    private MyReceiptAdapter mAdapter;
    public MyReceiptViewPagerFragment() {

    }
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_mypublish_viewpager_item;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
    @Override
    protected void initWidget(View root) {

        super.initWidget(root);
        ProgressLayout headerView = new ProgressLayout(getContext());
        trl.setOnRefreshListener(refreshListenerAdapter);
        trl.setHeaderView(headerView);
        trl.setOverScrollRefreshShow(false);
        trl.setOverScrollBottomShow(false);
        trl.setOverScrollTopShow(false);
        trl.setEnableOverScroll(false);
        listView.setAdapter(mAdapter = new MyReceiptAdapter(mContext));
        listView.setOnItemClickListener(this);
    }

    @Override
    protected void initData() {
        super.initData();

    }
    private RefreshListenerAdapter refreshListenerAdapter = new RefreshListenerAdapter() {
        @Override
        public void onPullingDown(TwinklingRefreshLayout refreshLayout, float fraction) {
            super.onPullingDown(refreshLayout, fraction);
            showToast("onPullingDown");
        }

        @Override
        public void onPullingUp(TwinklingRefreshLayout refreshLayout, float fraction) {
            super.onPullingUp(refreshLayout, fraction);
            showToast("onPullingUp");
        }

        @Override
        public void onPullDownReleasing(TwinklingRefreshLayout refreshLayout, float fraction) {
            super.onPullDownReleasing(refreshLayout, fraction);
            showToast("onPullDownReleasing");
        }

        @Override
        public void onPullUpReleasing(TwinklingRefreshLayout refreshLayout, float fraction) {
            super.onPullUpReleasing(refreshLayout, fraction);
            showToast("onPullUpReleasing");
        }

        @Override
        public void onRefresh(TwinklingRefreshLayout refreshLayout) {
            super.onRefresh(refreshLayout);
            showToast("onRefresh");
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    trl.finishRefreshing();
                }
            },2000);
        }

        @Override
        public void onLoadMore(TwinklingRefreshLayout refreshLayout) {
            super.onLoadMore(refreshLayout);
            showToast("onLoadMore");
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    trl.finishLoadmore();
                }
            },2000);
        }

        @Override
        public void onFinishRefresh() {
            super.onFinishRefresh();
            showToast("onFinishRefresh");
        }

        @Override
        public void onFinishLoadMore() {
            super.onFinishLoadMore();
            showToast("onFinishLoadMore");
        }

        @Override
        public void onRefreshCanceled() {
            super.onRefreshCanceled();
            showToast("onRefreshCanceled");

        }

        @Override
        public void onLoadmoreCanceled() {
            super.onLoadmoreCanceled();
            showToast("onLoadmoreCanceled");
        }
    };

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        startActivity(new Intent(mContext, ProvidedActivity.class));
    }
}
