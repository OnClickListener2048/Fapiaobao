package com.pilipa.fapiaobao.ui.fragment;

import android.Manifest;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.mylibrary.utils.RegexUtils;
import com.example.mylibrary.utils.TLog;
import com.pilipa.fapiaobao.MainActivity;
import com.pilipa.fapiaobao.R;
import com.pilipa.fapiaobao.account.AccountHelper;
import com.pilipa.fapiaobao.adapter.AllInvoiceAdapter;
import com.pilipa.fapiaobao.adapter.FinanceAdapter;
import com.pilipa.fapiaobao.base.BaseApplication;
import com.pilipa.fapiaobao.base.BaseFragment;
import com.pilipa.fapiaobao.net.Api;
import com.pilipa.fapiaobao.net.bean.LoginWithInfoBean;
import com.pilipa.fapiaobao.net.bean.invoice.AllInvoiceType;
import com.pilipa.fapiaobao.net.bean.invoice.DefaultInvoiceBean;
import com.pilipa.fapiaobao.net.bean.me.MessageListBean;
import com.pilipa.fapiaobao.ui.EstimateActivity;
import com.pilipa.fapiaobao.ui.MessageCenterActivity;
import com.pilipa.fapiaobao.ui.Op;
import com.pilipa.fapiaobao.ui.deco.FinanceItemDeco;
import com.pilipa.fapiaobao.ui.deco.GridInsetFinance;
import com.pilipa.fapiaobao.utils.SharedPreferencesHelper;
import com.pilipa.fapiaobao.utils.TDevice;
import com.pilipa.fapiaobao.zxing.android.CaptureActivity;
import com.tbruyelle.rxpermissions2.RxPermissions;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

import static com.pilipa.fapiaobao.base.BaseApplication.PUSH_RECEIVE;
import static com.pilipa.fapiaobao.base.BaseApplication.set;
import static com.pilipa.fapiaobao.net.Constant.REQUEST_SUCCESS;

/**
 * Created by edz on 2017/10/23.
 */

public class FinanceFragment extends BaseFragment implements AllInvoiceAdapter.OnLabelClickListener, FinanceAdapter.OnLabelClickListener {
    String TAG = "FinanceFragment";
    @Bind(R.id.scan)
    ImageView scan;
    @Bind(R.id.notification)
    ImageView notification;
    @Bind(R.id.recyclerview)
    public RecyclerView recyclerview;
    @Bind(R.id.recyclerview_more_kind)
    RecyclerView recyclerviewMoreKind;
    @Bind(R.id.pull_to_find_more)
    TextView pullToFindMore;
    @Bind(R.id.srollview)
    NestedScrollView srollview;
    @Bind(R.id.new_notification)
    TextView newNotification;
    public static final String EXTRA_DATA_LABEL = "extra_data_label";
    public static final String EXTRA_DATA_LABEL_NAME = "extra_data_label_name";
    @Bind(R.id.rl_pull_to_find_more)
    RelativeLayout rlPullToFindMore;
    @Bind(R.id.title)
    TextView title;
    @Bind(R.id.select_your_receipt_kind)
    TextView selectYourReceiptKind;
    private LoginWithInfoBean loginBean;
    FinanceAdapter financeAdapter;
    public static final String DECODED_CONTENT_KEY = "codedContent";
    public static final String DECODED_BITMAP_KEY = "codedBitmap";
    public static final int REQUEST_CODE_SCAN = 0x0234;

    private BroadcastReceiver mBoradcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals(BaseApplication.PUSH_RECEIVE)) {
                if (newNotification != null) {
                    newNotification.setVisibility(View.VISIBLE);
                }
            }
        }
    };

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_finance;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);

        messageList();

        return rootView;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        TLog.log("onActivityResultonActivityResultonActivityResult");
        switch (requestCode) {
            case REQUEST_CODE_SCAN:
                if (resultCode == Activity.RESULT_OK) {
                    String content = data.getStringExtra(DECODED_CONTENT_KEY);
                    TLog.log(content);
                    TLog.log("RegexUtils.isURL(content)" + RegexUtils.isURL(content));
                    if (RegexUtils.isURL(content)) {
                        Intent intent = new Intent();
                        intent.setClass(mContext, Op.class);
                        intent.putExtra("url", content);
                        startActivity(intent);
                    }
                }
                break;
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
        srollview.setSmoothScrollingEnabled(true);
        GridLayoutManager manager = new GridLayoutManager(mContext, 2, LinearLayoutManager.VERTICAL, false);
        recyclerview.setLayoutManager(manager);
        recyclerview.addItemDecoration(new GridInsetFinance(2, 20, true));
        recyclerviewMoreKind.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
        recyclerviewMoreKind.setNestedScrollingEnabled(false);
        recyclerviewMoreKind.addItemDecoration(new FinanceItemDeco(mContext, LinearLayoutManager.VERTICAL, (int) TDevice.dipToPx(getResources(), 23), R.color.white));

        initBroadcast();

    }

    private void initBroadcast() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(BaseApplication.PUSH_RECEIVE);
        mContext.registerReceiver(mBoradcastReceiver, intentFilter);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mContext.unregisterReceiver(mBoradcastReceiver);
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


    @OnClick({R.id.scan, R.id.notification,R.id.pull_to_find_more, R.id.rl_pull_to_find_more})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.scan:
                quickResponse();
                break;
            case R.id.notification:
                AccountHelper.isTokenValid(new Api.BaseViewCallback<LoginWithInfoBean>() {
                    @Override
                    public void setData(LoginWithInfoBean normalBean) {
                        if (normalBean.getStatus() == 200) {
                            newNotification.setVisibility(View.GONE);
                            BaseApplication.set(BaseApplication.PUSH_RECEIVE, false);
                            startActivity(new Intent(mContext, MessageCenterActivity.class));
                        } else {
                            login();
                        }
                    }
                });
                break;
            case R.id.pull_to_find_more:
            case R.id.rl_pull_to_find_more:
                srollview.setSmoothScrollingEnabled(true);
                pullToFindMore.measure(0, 0);

                srollview.smoothScrollTo(0,  rlPullToFindMore.getTop());
                break;
        }
    }

    private void quickResponse() {
        RxPermissions rxPermissions = new RxPermissions(getActivity());
        rxPermissions.request(Manifest.permission.CAMERA).subscribe(new Observer<Boolean>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Boolean aBoolean) {
                if (aBoolean) {
                    startActivityForResult(new Intent(mContext, CaptureActivity.class), REQUEST_CODE_SCAN);
                }
            }

            @Override
            public void onError(Throwable e) {
            }

            @Override
            public void onComplete() {
            }
        });
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

    @Override
    public void onResume() {
        super.onResume();
        newNotification.setVisibility(BaseApplication.get(BaseApplication.PUSH_RECEIVE, false) ? View.VISIBLE : View.GONE);
    }

    private void messageList() {
        if (TDevice.hasInternet()) {
            AccountHelper.isTokenValid(new Api.BaseViewCallback<LoginWithInfoBean>() {
                @Override
                public void setData(LoginWithInfoBean loginWithInfoBean) {
                    if (loginWithInfoBean.getStatus() == 200) {
                        Api.messageList(loginWithInfoBean.getData().getToken(), new Api.BaseViewCallback<MessageListBean>() {
                            @Override
                            public void setData(MessageListBean messageListBean) {
                                boolean hasNewMsg = false;
                                if(messageListBean.getStatus() == REQUEST_SUCCESS){
                                    List<MessageListBean.DataBean> data = messageListBean.getData();
                                    for (int i = 0; i <data.size() ; i++) {
                                        if(data.get(i).getUnreadMessages() > 0 ){
                                            hasNewMsg =true;
                                            TLog.d("MainActivity","message center had new message");
                                            break;
                                        }else{
                                            TLog.d("MainActivity","message center no message1");
                                        }
                                    }
                                    if(hasNewMsg){
                                        set(PUSH_RECEIVE, true);
                                    }else{
                                        set(PUSH_RECEIVE, false);
                                    }
                                }
                            }
                        });
                    }
                }
            });
        }
    }

}
