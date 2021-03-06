package com.pilipa.fapiaobao.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.lcodecore.tkrefreshlayout.header.progresslayout.ProgressLayout;
import com.lzy.okgo.OkGo;
import com.pilipa.fapiaobao.R;
import com.pilipa.fapiaobao.account.AccountHelper;
import com.pilipa.fapiaobao.adapter.me.MyReceiptAdapter;
import com.pilipa.fapiaobao.base.BaseActivity;
import com.pilipa.fapiaobao.base.BaseNoNetworkFragment;
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

public class ProvidePagerFragment extends BaseNoNetworkFragment implements AdapterView.OnItemClickListener {
    private static final String TAG = "ProvidePagerFragment";

    @Bind(R.id.recyclerview)
    ListView listView;
    List<OrderListBean.DataBean> mDataList = new ArrayList<>();
    private MyReceiptAdapter mAdapter;
    private int pageNo=0;
    private int pageSize=100;
    private boolean mIsInited;
    private boolean mIsPrepared;
    private View emptyView;


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
        mIsPrepared = true;
        lazyLoad();
        return rootView;
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            lazyLoad();
        }
    }

    private void lazyLoad() {
        if (getUserVisibleHint() && mIsPrepared && !mIsInited) {
            orderList(pageNo,pageSize);
        }
    }
    @Override
    public void onPause() {
        super.onPause();
        OkGo.cancelTag(OkGo.getInstance().getOkHttpClient(),this);
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
        listView.setAdapter(mAdapter = new MyReceiptAdapter(mContext));
        listView.setOnItemClickListener(this);
        emptyView = View.inflate(mContext, R.layout.layout_details_empty_view, null);
        emptyView.setVisibility(View.GONE);
        emptyView.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
        ((ViewGroup)listView.getParent()).addView(emptyView);

    }

    @Override
    protected void initData() {

        super.initData();

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(mContext, ProvidedActivity.class);
        intent.putExtra("OrderId",mDataList.get(position).getId());
        intent.putExtra("CompanyId",mDataList.get(position).getCompany().getId());
        startActivity(intent);
    }
    private void orderList(int pageNo,int pageSize){
        Api.orderList(AccountHelper.getToken(), pageNo + "", pageSize + "", ProvidePagerFragment.this, new Api.BaseRawResponse<OrderListBean>() {
            @Override
            public void onStart() {
                ((BaseActivity) getActivity()).showProgressDialog();
            }

            @Override
            public void onFinish() {
                ((BaseActivity) getActivity()).hideProgressDialog();
            }

            @Override
            public void onError() {
                showNetWorkErrorLayout();
            }

            @Override
            public void onTokenInvalid() {

            }

            @Override
            public void setData(OrderListBean orderListBean) {
                mIsInited = true;
                hideNetWorkErrorLayout();
                if (orderListBean.getStatus() == Constant.REQUEST_SUCCESS) {

                    List<OrderListBean.DataBean> list =  orderListBean.getData();
                    mDataList = list;
                    mAdapter.initData(list);
                    listView.setAdapter(mAdapter);
                    Log.d(TAG, "updateData:orderList success");
                }

                if (orderListBean.getStatus() == Constant.REQUEST_NO_CONTENT) {
                    if (listView != null) {
                        listView.setAdapter(null);
                        listView.setEmptyView(emptyView);
                    }
                    if (mAdapter!= null) {
                        mAdapter.clearData();
                    }
                }
            }
        });
    }


    @Override
    public void initDataInResume() {

    }

    @Override
    protected void onNoNetworkLayoutClicks(View v) {
        initData();
    }
}
