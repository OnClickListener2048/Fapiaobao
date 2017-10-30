package com.pilipa.fapiaobao.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.pilipa.fapiaobao.R;
import com.pilipa.fapiaobao.adapter.AllInvoiceAdapter;
import com.pilipa.fapiaobao.adapter.FinanceAdapter;
import com.pilipa.fapiaobao.base.BaseFragment;
import com.pilipa.fapiaobao.net.Api;
import com.pilipa.fapiaobao.net.bean.LoginBean;
import com.pilipa.fapiaobao.net.bean.invoice.AllInvoiceType;
import com.pilipa.fapiaobao.net.bean.invoice.DefaultInvoiceBean;
import com.pilipa.fapiaobao.ui.EstimateActivity;
import com.pilipa.fapiaobao.ui.deco.FinanceItemDeco;
import com.pilipa.fapiaobao.ui.deco.GridInsetFinance;
import com.pilipa.fapiaobao.utils.SharedPreferencesHelper;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by edz on 2017/10/23.
 */

public class FinanceFragment extends BaseFragment implements AllInvoiceAdapter.OnLabelClickListener {
    String TAG = "FinanceFragment";
    @Bind(R.id.scan)
    ImageView scan;
    @Bind(R.id.notification)
    ImageView notification;
    @Bind(R.id.recyclerview)
    RecyclerView recyclerview;
    @Bind(R.id.recyclerview_more_kind)
    RecyclerView recyclerviewMoreKind;
    private FinanceAdapter adapter;
    public static final String EXTRA_DATA_LABEL = "extra_data_label";
    private LoginBean loginBean;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_finance;
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

        GridLayoutManager manager = new GridLayoutManager(mContext, 2, LinearLayoutManager.VERTICAL, false);
        recyclerview.setLayoutManager(manager);
        recyclerview.addItemDecoration(new GridInsetFinance(2, 20, true));

        recyclerviewMoreKind.setLayoutManager(new LinearLayoutManager(mContext,LinearLayoutManager.VERTICAL,false));
        recyclerviewMoreKind.setNestedScrollingEnabled(false);
        recyclerviewMoreKind.addItemDecoration(new FinanceItemDeco(mContext, LinearLayoutManager.VERTICAL, 0, R.color.cancel));

    }

    @Override
    protected void initData() {
        super.initData();
        Api.findAllInvoice(new Api.BaseViewCallback<AllInvoiceType>() {

            private AllInvoiceAdapter adapter;

            @Override
            public void setData(AllInvoiceType allInvoiceType) {
                adapter = new AllInvoiceAdapter(allInvoiceType);
                adapter.setOnLabelClickListener(FinanceFragment.this);
                recyclerviewMoreKind.setAdapter(adapter);
            }
        });


//        loginBean = SharedPreferencesHelper.loadFormSource(mContext, LoginBean.class);
//        if (loginBean != null) {
//            Api.findUserInvoiceType(loginBean.getData().getToken(), new Api.BaseViewCallback<DefaultInvoiceBean>() {
//                @Override
//                public void setData(DefaultInvoiceBean defaultInvoiceBean) {
//                    adapter = new FinanceAdapter(defaultInvoiceBean);
//                    adapter.setOnLabelClickListener(FinanceFragment.this);
//                    recyclerview.setAdapter(adapter);
//                }
//            });
//        } else {
//            Api.<DefaultInvoiceBean>findDefaultInvoiceType(new Api.BaseViewCallback<DefaultInvoiceBean>() {
//
//
//
//                @Override
//                public void setData(DefaultInvoiceBean allInvoiceType) {
//                    adapter = new FinanceAdapter(allInvoiceType);
//                    adapter.setOnLabelClickListener(FinanceFragment.this);
//                    recyclerview.setAdapter(adapter);
//                }
//            });
//        }


    }

    @OnClick({R.id.scan, R.id.notification})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.scan:
                break;
            case R.id.notification:
                break;
        }
    }

    @Override
    public void onLabelClick(String id) {
        LoginBean loginBean = SharedPreferencesHelper.loadFormSource(mContext, LoginBean.class);
        if (loginBean != null) {
            Api.updateInvoiceType(loginBean.getData().getToken(), id, new Api.BaseViewCallback<String>() {
                @Override
                public void setData(String s) {
                    Log.d(TAG, "setData: s" + s);
                }
            });
        }


        Intent intent = new Intent();
        intent.putExtra(EXTRA_DATA_LABEL, id);
        intent.setClass(mContext, EstimateActivity.class);
        startActivity(intent);
    }
}
