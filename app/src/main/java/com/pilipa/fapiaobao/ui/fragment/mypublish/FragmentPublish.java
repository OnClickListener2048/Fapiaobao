package com.pilipa.fapiaobao.ui.fragment.mypublish;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.lzy.okgo.model.Response;
import com.pilipa.fapiaobao.R;
import com.pilipa.fapiaobao.base.BaseActivity;
import com.pilipa.fapiaobao.base.BaseApplication;
import com.pilipa.fapiaobao.base.BaseRecyclerViewLazyLoadFragment;
import com.pilipa.fapiaobao.net.Api;
import com.pilipa.fapiaobao.net.bean.base.BaseResponseBean;
import com.pilipa.fapiaobao.net.bean.me.demandlist.DemandListItem;
import com.pilipa.fapiaobao.net.callback.DialogJsonConverter;
import com.pilipa.fapiaobao.ui.constants.Constant;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by edz on 2018/3/14.
 */

public class FragmentPublish extends BaseRecyclerViewLazyLoadFragment {
    private static final String TAG = FragmentPublish.class.getSimpleName();
    private String mState;
    private DemandAdapter mDemandAdapter;

    public static FragmentPublish newInstance(Bundle bundle) {
        FragmentPublish fragmentPublish = new FragmentPublish();
        fragmentPublish.setArguments(bundle);
        return fragmentPublish;
    }

    @Override
    protected void initData() {
        super.initData();
        Bundle arguments = getArguments();
        mState = arguments.getString(Constant.STATE_DEMAND);
    }

    @Override
    protected void initWidget(View root) {
        super.initWidget(root);
        mDemandAdapter = new DemandAdapter(R.layout.item_demandlist);
        mRecyclerView.setAdapter(mDemandAdapter);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        mDemandAdapter.bindToRecyclerView(mRecyclerView);

    }


    @Override
    public void fetchData() {
        Api.demandList(mState, this, new DialogJsonConverter<BaseResponseBean<List<DemandListItem>>>((BaseActivity) getActivity()) {

            @Override
            public void onSuccess(Response<BaseResponseBean<List<DemandListItem>>> response) {
                if (response.body() == null) return;
                mIsInited = true;
                handleData(response.body().getData());
            }


            @Override
            public void onError(Response<BaseResponseBean<List<DemandListItem>>> response) {
                super.onError(response);
                if (response.getException() == null) return;
                if (response.getException() instanceof IllegalStateException) {
                    mDemandAdapter.setEmptyView(R.layout.layout_details_empty_view);
                } else {
                    mDemandAdapter.setEmptyView(R.layout.layout_no_network);
                }

                mDemandAdapter.getEmptyView().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        fetchData();
                    }
                });
            }
        });
    }

    private void handleData(List<DemandListItem> data) {
        List<DemandListItem.ListBean> listBeanList = new ArrayList<>();
        for (DemandListItem item : data) {
            listBeanList.addAll(item.getList());
        }
        mDemandAdapter.setNewData(listBeanList);
    }


    public static class DemandAdapter extends BaseQuickAdapter<DemandListItem.ListBean, BaseViewHolder> {


        public DemandAdapter(@LayoutRes int layoutResId) {
            super(layoutResId);
        }

        @Override
        protected void convert(BaseViewHolder helper, DemandListItem.ListBean item) {
            helper.setText(R.id.tv_total_amount, String.valueOf(item.getTotalAmount()))
                    .setText(R.id.tv_wait_collected, String.valueOf(item.getLeftAmount()))
                    .setText(R.id.tv_already_collected, String.valueOf(item.getTotalAmount() - item.getLeftAmount()))
                    .setText(R.id.tv_remaining_time, BaseApplication.context().getString(R.string.remaining_data, String.valueOf(item.getLeftDate())));
        }

    }


}
