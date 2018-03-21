package com.pilipa.fapiaobao.ui.fragment.mypublish;

import android.app.Activity;
import android.content.Intent;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.example.mylibrary.utils.SizeUtils;
import com.example.mylibrary.utils.TLog;
import com.lzy.okgo.model.Response;
import com.pilipa.fapiaobao.R;
import com.pilipa.fapiaobao.base.BaseActivity;
import com.pilipa.fapiaobao.base.BaseApplication;
import com.pilipa.fapiaobao.base.BaseRecyclerViewLazyLoadFragment;
import com.pilipa.fapiaobao.net.Api;
import com.pilipa.fapiaobao.net.bean.base.BaseResponseBean;
import com.pilipa.fapiaobao.net.bean.me.demandlist.DemandListItem;
import com.pilipa.fapiaobao.net.callback.DialogJsonConverter;
import com.pilipa.fapiaobao.ui.DemandActivity;
import com.pilipa.fapiaobao.ui.constants.Constant;
import com.pilipa.fapiaobao.ui.deco.FlexibleDividerDecoration;
import com.pilipa.fapiaobao.ui.deco.HorizontalDividerItemDecoration;
import com.pilipa.fapiaobao.ui.fragment.mypublish.bean.CompanyNameBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by edz on 2018/3/14.
 */

public class FragmentPublish extends BaseRecyclerViewLazyLoadFragment {
    private static final String TAG = FragmentPublish.class.getSimpleName();
    private String mState;

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
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        TLog.d(TAG, "onActivityResult");
        TLog.d(TAG, "Constant.REQUEST_REFRESH_CODE" + requestCode);
        TLog.d(TAG, "resultCode" + resultCode);
        if (requestCode == Constant.REQUEST_REFRESH_CODE && resultCode == Activity.RESULT_OK) {
            TLog.d(TAG, "onActivityResult");
            TLog.d(TAG, "Constant.REQUEST_REFRESH_CODE" + requestCode);
            TLog.d(TAG, "resultCode" + resultCode);
            fetchData();
        }
    }

    @Override
    public void onNoContentViewClick(View view) {
        fetchData();
    }

    @Override
    public void onEmptyViewClick(View view) {
        fetchData();
    }

    @Override
    public void fetchData() {
        Api.demandList(mState, this, new DialogJsonConverter<BaseResponseBean<List<DemandListItem>>>((BaseActivity) getActivity()) {

            @Override
            public void onSuccess(Response<BaseResponseBean<List<DemandListItem>>> response) {
                if (response.body() == null) return;
                fetchSuccess();
                handleData(response.body().getData());
            }


            @Override
            public void onError(Response<BaseResponseBean<List<DemandListItem>>> response) {
                super.onError(response);
                if (response.getException() == null) return;
                if (response.getException() instanceof IllegalStateException) {
                    noContent();
                } else {
                    noNetwork();
                }

            }

            @Override
            public void onFinish() {
                super.onFinish();
                mIsInited = true;
            }
        });
    }

    private void handleData(List<DemandListItem> data) {
        ArrayList<MultiItemEntity> multiItemEntityArrayList = new ArrayList<>();
        for (DemandListItem item : data) {
            CompanyNameBean companyNameBean = new CompanyNameBean();
            companyNameBean.setName(item.getName());
            List<DemandListItem.ListBean> itemList = item.getList();
            for (DemandListItem.ListBean listBean : itemList) {
                companyNameBean.addSubItem(listBean);
            }
            multiItemEntityArrayList.add(companyNameBean);
        }
        DemandAdapter demandAdapter = new DemandAdapter(multiItemEntityArrayList, this);
        demandAdapter.openLoadAnimation(DemandAdapter.ALPHAIN);
        mRecyclerView.setAdapter(demandAdapter);
        mRecyclerView.addItemDecoration(new HorizontalDividerItemDecoration.Builder(getActivity())
                .paintProvider(demandAdapter)
                .visibilityProvider(demandAdapter)
                .marginProvider(demandAdapter)
                .build());
        demandAdapter.expandAll();
    }


    public static class DemandAdapter extends BaseMultiItemQuickAdapter<MultiItemEntity, BaseViewHolder> implements FlexibleDividerDecoration.PaintProvider, FlexibleDividerDecoration.VisibilityProvider,
            HorizontalDividerItemDecoration.MarginProvider {


        private final FragmentPublish fragmentPublish;

        /**
         * Same as QuickAdapter#QuickAdapter(Context,int) but with
         * some initialization data.
         *
         * @param data            A new list is created out of this one to avoid mutable list
         * @param fragmentPublish
         */
        public DemandAdapter(List<MultiItemEntity> data, FragmentPublish fragmentPublish) {
            super(data);
            this.fragmentPublish = fragmentPublish;
            addItemType(com.pilipa.fapiaobao.net.Constant.TYPE_COMPANY_NAME, R.layout.item_company_name);
            addItemType(com.pilipa.fapiaobao.net.Constant.TYPE_ITEM_NAME, R.layout.item_demandlist);
        }

        @Override
        protected void convert(final BaseViewHolder helper, MultiItemEntity item) {

            switch (helper.getItemViewType()) {
                case com.pilipa.fapiaobao.net.Constant.TYPE_COMPANY_NAME:

                    final CompanyNameBean companyNameBean = (CompanyNameBean) item;
                    helper.setText(R.id.tv_company_name, companyNameBean.getName())
                            .setImageResource(R.id.iv_arrow, companyNameBean.isExpanded() ? R.mipmap.collapse : R.mipmap.expand)
                            .setVisible(R.id.view_dashline, !companyNameBean.isExpanded());

                    helper.itemView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            int pos = helper.getAdapterPosition();
                            if (companyNameBean.isExpanded()) {
                                collapse(pos);
                            } else {
                                expand(pos);
                            }
                        }
                    });

                    break;
                case com.pilipa.fapiaobao.net.Constant.TYPE_ITEM_NAME:
                    final DemandListItem.ListBean listBean = (DemandListItem.ListBean) item;
                    helper.setText(R.id.tv_total_amount, String.valueOf(listBean.getTotalAmount()))
                            .setText(R.id.tv_wait_collected, String.valueOf(listBean.getLeftAmount()))
                            .setText(R.id.tv_already_collected, String.valueOf(listBean.getTotalAmount() - listBean.getLeftAmount()))
                            .setText(R.id.tv_remaining_time, BaseApplication.context().getString(R.string.remaining_data, String.valueOf(listBean.getLeftDate())))
                            .setVisible(R.id.iv_incoming_invoice, (!TextUtils.equals("无", listBean.getRemarks()) && TextUtils.equals("0", fragmentPublish.mState)));
                    helper.itemView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(mContext, DemandActivity.class);
                            intent.putExtra("demandId", listBean.getId());
                            intent.putExtra(Constant.STATE_DEMAND, fragmentPublish.mState);
                            fragmentPublish.startActivityForResult(intent, Constant.REQUEST_REFRESH_CODE);
                        }
                    });
                    break;
                default:
            }
        }


        @Override
        public Paint dividerPaint(int position, RecyclerView parent) {
            Paint paint = new Paint();
            paint.setColor(BaseApplication.context().getResources().getColor(R.color.gray_hint));
            paint.setAntiAlias(true);
            paint.setPathEffect(new DashPathEffect(new float[]{SizeUtils.dp2px(5), SizeUtils.dp2px(5)}, 0));
            return paint;
        }

        @Override
        public boolean shouldHideDivider(int position, RecyclerView parent) {
            return false;
        }

        @Override
        public int dividerLeftMargin(int position, RecyclerView parent) {
            return 0;
        }

        @Override
        public int dividerRightMargin(int position, RecyclerView parent) {
            return 0;
        }
    }


}
