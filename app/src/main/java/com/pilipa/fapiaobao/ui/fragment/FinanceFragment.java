package com.pilipa.fapiaobao.ui.fragment;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.pilipa.fapiaobao.MainActivity;
import com.pilipa.fapiaobao.R;
import com.pilipa.fapiaobao.adapter.AllInvoiceAdapter;
import com.pilipa.fapiaobao.adapter.FinanceAdapter;
import com.pilipa.fapiaobao.base.BaseApplication;
import com.pilipa.fapiaobao.base.BaseFragment;
import com.pilipa.fapiaobao.net.Api;
import com.pilipa.fapiaobao.net.bean.LoginWithInfoBean;
import com.pilipa.fapiaobao.net.bean.invoice.AllInvoiceType;
import com.pilipa.fapiaobao.net.bean.invoice.AllInvoiceVariety;
import com.pilipa.fapiaobao.net.bean.invoice.DefaultInvoiceBean;
import com.pilipa.fapiaobao.ui.EstimateActivity;
import com.pilipa.fapiaobao.ui.deco.FinanceItemDeco;
import com.pilipa.fapiaobao.ui.deco.GridInsetFinance;
import com.pilipa.fapiaobao.utils.SharedPreferencesHelper;
import com.pilipa.fapiaobao.zxing.android.CaptureActivity;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by edz on 2017/10/23.
 */

public class FinanceFragment extends BaseFragment implements AllInvoiceAdapter.OnLabelClickListener, FinanceAdapter.OnLabelClickListener
{
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
    public static final String EXTRA_DATA_LABEL_NAME = "extra_data_label_name";
    private LoginWithInfoBean loginBean;
    FinanceAdapter financeAdapter;
    private static final String DECODED_CONTENT_KEY = "codedContent";
    private static final String DECODED_BITMAP_KEY = "codedBitmap";
    private static final int REQUEST_CODE_SCAN = 0x0000;

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
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_SCAN && resultCode == 200) {
            if (data != null) {

                String content = data.getStringExtra(DECODED_CONTENT_KEY);
                Bitmap bitmap = data.getParcelableExtra(DECODED_BITMAP_KEY);
                BaseApplication.showToast(content);
            }
        }
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

        final MainActivity activity = (MainActivity) getActivity();

        Api.findAllInvoice(new Api.BaseViewCallbackWithOnStart<AllInvoiceType>() {

            @Override
            public void onStart() {
                activity.showProgressDialog();
            }

            @Override
            public void onFinish() {
                activity.hideProgressDialog();
            }

            @Override
            public void onError() {
                activity.hideProgressDialog();
            }

            private AllInvoiceAdapter adapter;


            @Override
            public void setData(AllInvoiceType allInvoiceType) {
                adapter = new AllInvoiceAdapter(allInvoiceType);
                adapter.setOnLabelClickListener(FinanceFragment.this);
                recyclerviewMoreKind.setAdapter(adapter);
            }
        });


        loginBean = SharedPreferencesHelper.loadFormSource(mContext, LoginWithInfoBean.class);
        if (loginBean != null) {
            Api.<DefaultInvoiceBean>findUserInvoiceType(loginBean.getData().getToken(), new Api.BaseViewCallback<DefaultInvoiceBean>() {
                @Override
                public void setData(DefaultInvoiceBean defaultInvoiceBean) {
                    if (defaultInvoiceBean.getData() != null && defaultInvoiceBean.getData().size() > 0) {
                        financeAdapter = new FinanceAdapter(defaultInvoiceBean);
                        financeAdapter.setOnLabelClickListener(FinanceFragment.this);
                        recyclerview.setAdapter(financeAdapter);
                    } else if (defaultInvoiceBean.getStatus() == 701) {
                        Api.<DefaultInvoiceBean>findDefaultInvoiceType(new Api.BaseViewCallback<DefaultInvoiceBean>() {
                            @Override
                            public void setData(DefaultInvoiceBean allInvoiceType) {
                                if (allInvoiceType.getData() != null && allInvoiceType.getData().size() > 0) {
                                    financeAdapter = new FinanceAdapter(allInvoiceType);
                                    financeAdapter.setOnLabelClickListener(FinanceFragment.this);
                                    //TODO 请求回来前 跳转页面 空指针
                                    recyclerview.setAdapter(financeAdapter);
                                }
                            }
                        });
                    }
                }
            });
        } else {
            Api.<DefaultInvoiceBean>findDefaultInvoiceType(new Api.BaseViewCallback<DefaultInvoiceBean>() {
                @Override
                public void setData(DefaultInvoiceBean allInvoiceType) {
                    if (allInvoiceType.getData() != null && allInvoiceType.getData().size() > 0) {
                        financeAdapter = new FinanceAdapter(allInvoiceType);
                        financeAdapter.setOnLabelClickListener(FinanceFragment.this);
                        recyclerview.setAdapter(financeAdapter);
                    }
                }
            });
        }


    }

    @OnClick({R.id.scan, R.id.notification})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.scan:
                startActivityForResult(new Intent(mContext, CaptureActivity.class), REQUEST_CODE_SCAN);
                break;
            case R.id.notification:
                break;
        }
    }

    @Override
    public void onLabelClick(String id) {

    }

    @Override
    public void onLabelNameClick(String label, String name) {
        LoginWithInfoBean loginBean = SharedPreferencesHelper.loadFormSource(mContext, LoginWithInfoBean.class);
        if (loginBean != null) {
            Api.updateInvoiceType(loginBean.getData().getToken(), label, new Api.BaseViewCallback<String>() {
                @Override
                public void setData(String s) {
                    Log.d(TAG, "setData: s" + s);
                }
            });
        }
        Intent intent = new Intent();
        intent.putExtra(EXTRA_DATA_LABEL, label);
        intent.putExtra(EXTRA_DATA_LABEL_NAME, name);
        intent.setClass(mContext, EstimateActivity.class);
        startActivity(intent);


    }
}
