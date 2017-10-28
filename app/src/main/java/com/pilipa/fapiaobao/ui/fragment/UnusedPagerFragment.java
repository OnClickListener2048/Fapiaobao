package com.pilipa.fapiaobao.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ExpandableListView;

import com.lcodecore.tkrefreshlayout.RefreshListenerAdapter;
import com.lcodecore.tkrefreshlayout.TwinklingRefreshLayout;
import com.lcodecore.tkrefreshlayout.header.progresslayout.ProgressLayout;
import com.pilipa.fapiaobao.R;
import com.pilipa.fapiaobao.adapter.ExpandableListAdapter;
import com.pilipa.fapiaobao.adapter.MyPublishAdapter;
import com.pilipa.fapiaobao.base.BaseFragment;
import com.pilipa.fapiaobao.ui.DemandActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by lyt on 2017/10/17.
 */
@Deprecated
public class UnusedPagerFragment extends BaseFragment implements AdapterView.OnItemClickListener{
    @Bind(R.id.simple_expandable_listview)
    ExpandableListView expandableListView;
    @Bind(R.id.trl)
    TwinklingRefreshLayout trl;
    private MyPublishAdapter mAdapter;
    public UnusedPagerFragment() {

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
        expandableListView.setGroupIndicator(null);

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
    }

    @Override
    protected void initData() {
        super.initData();
        setItems();
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
                    if(trl != null)
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
                    if(trl != null)
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
        startActivity(new Intent(mContext, DemandActivity.class));
    }

    void setItems(){

        // Array list for header
        ArrayList<String> header = new ArrayList<String>();

        // Array list for child items
        List<String> child1 = new ArrayList<String>();
        List<String> child2 = new ArrayList<String>();
        List<String> child3 = new ArrayList<String>();
        List<String> child4 = new ArrayList<String>();

        // Hash map for both header and child
        HashMap<String, List<String>> hashMap = new HashMap<String, List<String>>();

        // Adding headers to list
        for (int i = 1; i < 5; i++) {
            header.add("天津阿什顿有限公司 " + i);
        }
        // Adding child data
        for (int i = 1; i < 5; i++) {
            child1.add("Group 1  " + " : Child" + i);
        }
        // Adding child data
        for (int i = 1; i < 5; i++) {
            child2.add("Group 2  " + " : Child" + i);
        }
        // Adding child data
        for (int i = 1; i < 6; i++) {
            child3.add("Group 3  " + " : Child" + i);
        }
        // Adding child data
        for (int i = 1; i < 7; i++) {
            child4.add("Group 4  " + " : Child" + i);
        }

        // Adding header and childs to hash map
        hashMap.put(header.get(0), child1);
        hashMap.put(header.get(1), child2);
        hashMap.put(header.get(2), child3);
        hashMap.put(header.get(3), child4);

        ExpandableListAdapter adapter = new ExpandableListAdapter(mContext, header, hashMap);
        // Setting adpater over expandablelistview
        expandableListView.setAdapter(adapter);

    }
}
