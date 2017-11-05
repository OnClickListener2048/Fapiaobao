package com.pilipa.fapiaobao.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.pilipa.fapiaobao.R;
import com.pilipa.fapiaobao.adapter.TabAdapterActive;
import com.pilipa.fapiaobao.adapter.TabAdapterInactive;
import com.pilipa.fapiaobao.base.BaseActivity;
import com.pilipa.fapiaobao.net.Api;
import com.pilipa.fapiaobao.net.bean.LoginWithInfoBean;
import com.pilipa.fapiaobao.net.bean.invoice.AllInvoiceType;
import com.pilipa.fapiaobao.net.bean.invoice.DefaultInvoiceBean;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by edz on 2017/11/2.
 */

public class MoreTypesActivity extends BaseActivity implements TabAdapterActive.ItemDeleteListener, TabAdapterInactive.OnItemClickListener {

    private static ArrayList<DefaultInvoiceBean.DataBean> data;
    @Bind(R.id.tv_operator)
    TextView tvOperator;
    @Bind(R.id.tv_done)
    TextView tvDone;
    @Bind(R.id.layout_top)
    RelativeLayout layoutTop;
    @Bind(R.id.view_recycler_active)
    RecyclerView viewRecyclerActive;
    @Bind(R.id.view_recycler_inactive)
    RecyclerView viewRecyclerInactive;
    @Bind(R.id.layout_wrapper)
    LinearLayout layoutWrapper;
    @Bind(R.id.view_scroller)
    NestedScrollView viewScroller;
    @Bind(R.id.view_wrapper)
    LinearLayout viewWrapper;
    @Bind(R.id.title)
    TextView title;
    @Bind(R.id.morekind_back)
    ImageView morekindBack;
    @Bind(R.id.upload_scan)
    TextView uploadScan;
    private LoginWithInfoBean loginBean;
    private TabAdapterActive tabAdapterActive;
    public static String EXTRA_BUNDLE = "extra_bundle";

    @Override
    protected int getLayoutId() {
        return R.layout.view_tab_picker;
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void initView() {
        viewRecyclerActive.setNestedScrollingEnabled(false);
        viewRecyclerActive.setLayoutManager(new GridLayoutManager(this, 4));
        viewRecyclerInactive.setNestedScrollingEnabled(false);
        viewRecyclerInactive.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
    }

    @Override
    public void initData() {

        Bundle bundleExtra = getIntent().getBundleExtra(EXTRA_BUNDLE);
        ArrayList<DefaultInvoiceBean.DataBean> bean = (ArrayList<DefaultInvoiceBean.DataBean>) bundleExtra.getSerializable("new_data");
        tabAdapterActive = new TabAdapterActive(bean);
        tabAdapterActive.setOnItemDeleteListener(MoreTypesActivity.this);
        viewRecyclerActive.setAdapter(tabAdapterActive);


        Api.findAllInvoice(new Api.BaseViewCallbackWithOnStart<AllInvoiceType>() {
            @Override
            public void onStart() {
                showProgressDialog();
            }

            @Override
            public void onFinish() {
                hideProgressDialog();
            }

            @Override
            public void onError() {
                hideProgressDialog();
            }

            @Override
            public void setData(AllInvoiceType allInvoiceType) {
                if (allInvoiceType.getData() != null && allInvoiceType.getData().size() > 0) {
                    TabAdapterInactive tabAdapterInactive = new TabAdapterInactive(allInvoiceType);
                    tabAdapterInactive.setOnItemClickListener(MoreTypesActivity.this);
                    viewRecyclerInactive.setAdapter(tabAdapterInactive);
                }
            }
        });

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.morekind_back, R.id.tv_done})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.morekind_back:
                setResult(RESULT_CANCELED);
                finish();
                break;
            case R.id.tv_done:
                if (tabAdapterActive != null) {
                    tabAdapterActive.setEditMode(!tabAdapterActive.getEditMode());
                }
                break;
        }
    }

    @Override
    public void onItemDelete(DefaultInvoiceBean.DataBean dataBean) {
        if (data != null) {
            data.remove(dataBean);

        }
    }


    @Override
    public void onItemClick(AllInvoiceType.DataBean.InvoiceTypeListBean dataBean) {
        if (tabAdapterActive != null) {
            DefaultInvoiceBean.DataBean data = new DefaultInvoiceBean.DataBean();
            data.setCategory(dataBean.getCategory());
            data.setCategorySort(dataBean.getCategorySort());
            data.setId(dataBean.getId());
            data.setCreateDate(dataBean.getCreateDate());
            data.setFrequentFlag(dataBean.getFrequentFlag());
            data.setIsNewRecord(dataBean.isIsNewRecord());
            data.setLargeSize(dataBean.getLargeSize());
            data.setMiddleSize(dataBean.getMiddleSize());
            data.setUpdateDate(dataBean.getUpdateDate());
            data.setRemarks(dataBean.getRemarks());
            data.setSmallSize(dataBean.getSmallSize());
            data.setName(dataBean.getName());
            tabAdapterActive.addItem(data);
        }
    }

    @OnClick(R.id.upload_scan)
    public void onViewClicked() {
        Intent intent = new Intent();
        Bundle bundle = new Bundle();
        bundle.putSerializable("new_data",tabAdapterActive.getCurrentItemSetData());
        intent.putExtra(EXTRA_BUNDLE, bundle);
        setResult(RESULT_OK,intent);
        finish();
    }
}
