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
import com.pilipa.fapiaobao.adapter.MyReceiptAdapter;
import com.pilipa.fapiaobao.base.BaseApplication;
import com.pilipa.fapiaobao.base.BaseFragment;
import com.pilipa.fapiaobao.net.Api;
import com.pilipa.fapiaobao.net.Constant;
import com.pilipa.fapiaobao.net.bean.me.OrderListBean;
import com.pilipa.fapiaobao.ui.ProvidedActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by lyt on 2017/10/17.
 */

public class ProvidePagerFragment extends BaseFragment implements AdapterView.OnItemClickListener{
    private static final String TAG = "ProvidePagerFragment";

    @Bind(R.id.recyclerview)
    ListView listView;
    @Bind(R.id.trl)
    TwinklingRefreshLayout trl;
    private MyReceiptAdapter mAdapter;
    private int pageNo=0;
    private int pageSize=100;
    List<OrderListBean.DataBean> mDataList =new ArrayList<>();
    public ProvidePagerFragment() {

    }
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
        listView.setAdapter(mAdapter = new MyReceiptAdapter(mContext));
        listView.setOnItemClickListener(this);
        View emptyView = View.inflate(mContext, R.layout.layout_details_empty_view, null);
        emptyView.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
        ((ViewGroup)listView.getParent()).addView(emptyView);
        listView.setEmptyView(emptyView);
    }

    @Override
    protected void initData() {
        orderList(pageNo,pageSize);
        super.initData();

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
        Intent intent = new Intent(mContext, ProvidedActivity.class);
        intent.putExtra("OrderId",mDataList.get(position).getId());
        intent.putExtra("CompanyId",mDataList.get(position).getCompany().getId());
        startActivity(intent);
    }
    private void orderList(int pageNo,int pageSize){
        if (AccountHelper.getToken() != null && AccountHelper.getToken() != "") {
                Api.orderList(AccountHelper.getToken(),pageNo+"",pageSize+"", new Api.BaseViewCallback<OrderListBean>() {
                    @Override
                    public void setData(OrderListBean orderListBean) {
                        if (orderListBean.getStatus() == Constant.REQUEST_SUCCESS) {

                        List<OrderListBean.DataBean> list =  orderListBean.getData();
                        mDataList = list;
                        mAdapter.initData(mDataList);
                        listView.setAdapter(mAdapter);


                            Log.d(TAG, "updateData:orderList success");
                        }

                        if (orderListBean.getStatus() == Constant.REQUEST_NO_CONTENT) {
                            if (listView != null) {
                                listView.setAdapter(null);
                            }
                            if (mAdapter!= null) {
                                mAdapter.clearData();
                            }
                        }
                    }
                });
        }else{
            BaseApplication.showToast("登录超期");
        }


    }


}
