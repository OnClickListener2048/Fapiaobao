package com.pilipa.fapiaobao.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.lcodecore.tkrefreshlayout.RefreshListenerAdapter;
import com.lcodecore.tkrefreshlayout.TwinklingRefreshLayout;
import com.lcodecore.tkrefreshlayout.header.progresslayout.ProgressLayout;
import com.pilipa.fapiaobao.R;
import com.pilipa.fapiaobao.account.AccountHelper;
import com.pilipa.fapiaobao.adapter.MyPublishAdapter;
import com.pilipa.fapiaobao.base.BaseFragment;
import com.pilipa.fapiaobao.net.Api;
import com.pilipa.fapiaobao.net.bean.publish.DemandsListBean;
import com.pilipa.fapiaobao.ui.DemandActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

import static com.pilipa.fapiaobao.net.Constant.REQUEST_NO_CONTENT;
import static com.pilipa.fapiaobao.net.Constant.REQUEST_SUCCESS;
import static com.pilipa.fapiaobao.net.Constant.STATE_DEMAND_CLOSE;

/**
 * Created by lyt on 2017/10/17.
 */
public class UnusedPagerFragment_close extends BaseFragment implements AdapterView.OnItemClickListener{
    private static final String TAG = "UnusedPagerFragment_close";

    @Bind(R.id.recyclerview)
    ListView listView;
    @Bind(R.id.trl)
    TwinklingRefreshLayout trl;
    private MyPublishAdapter mAdapter;
    private List<DemandsListBean.DataBean> dataBeanList = new ArrayList<>();
    private View emptyView;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_viewpager_item;
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
    }

    @Override
    protected void initData() {
        super.initData();
        mAdapter = new MyPublishAdapter(mContext);
        listView.setAdapter(mAdapter);
        listView.setOnItemClickListener(this);
        emptyView = View.inflate(mContext, R.layout.layout_details_empty_view, null);
        emptyView.setVisibility(View.GONE);
        emptyView.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
        ((ViewGroup)listView.getParent()).addView(emptyView);
        listView.setEmptyView(emptyView);
    }

    @Override
    public void onResume() {
        demandsList(STATE_DEMAND_CLOSE);
        Log.d(TAG,"onResume");
        super.onResume();

    }

    private RefreshListenerAdapter refreshListenerAdapter = new RefreshListenerAdapter() {
        @Override
        public void onPullingDown(TwinklingRefreshLayout refreshLayout, float fraction) {
            super.onPullingDown(refreshLayout, fraction);
        }

        @Override
        public void onPullingUp(TwinklingRefreshLayout refreshLayout, float fraction) {
            super.onPullingUp(refreshLayout, fraction);
        }

        @Override
        public void onPullDownReleasing(TwinklingRefreshLayout refreshLayout, float fraction) {
            super.onPullDownReleasing(refreshLayout, fraction);
        }

        @Override
        public void onPullUpReleasing(TwinklingRefreshLayout refreshLayout, float fraction) {
            super.onPullUpReleasing(refreshLayout, fraction);
        }

        @Override
        public void onRefresh(TwinklingRefreshLayout refreshLayout) {
            super.onRefresh(refreshLayout);
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    demandsList(STATE_DEMAND_CLOSE);
                    if(trl != null)
                        trl.finishRefreshing();
                }
            },2000);
        }

        @Override
        public void onLoadMore(TwinklingRefreshLayout refreshLayout) {
            super.onLoadMore(refreshLayout);
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
        }

        @Override
        public void onFinishLoadMore() {
            super.onFinishLoadMore();
        }

        @Override
        public void onRefreshCanceled() {
            super.onRefreshCanceled();

        }

        @Override
        public void onLoadmoreCanceled() {
            super.onLoadmoreCanceled();
        }
    };

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(mContext, DemandActivity.class);
        intent.putExtra("demandId",dataBeanList.get(position).getId());
        startActivity(intent);
    }

    public void demandsList(String state){
        if (AccountHelper.getToken() != null && AccountHelper.getToken() != "") {
            Api.demandsList(AccountHelper.getToken(),state,this,new Api.BaseViewCallback<DemandsListBean>() {
                @Override
                public void setData(DemandsListBean demandsListBean) {
                    if (demandsListBean.getStatus() == REQUEST_SUCCESS) {

                        List<DemandsListBean.DataBean> list = demandsListBean.getData();
                        dataBeanList = list;
                        mAdapter.initData(list);
                        listView.setAdapter(mAdapter);

                        Log.d(TAG, "demandsList success");
                    }

                    if (demandsListBean.getStatus() == REQUEST_NO_CONTENT) {
                        if (listView != null) {
                            listView.setAdapter(null);
                        }
                        if (mAdapter!= null) {
                            mAdapter.clearData();
                        }
                    }
                }



            });
        }
    }

}
