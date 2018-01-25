package com.pilipa.fapiaobao.ui.fragment;

import android.Manifest;
import android.app.Activity;
import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.mylibrary.utils.RegexUtils;
import com.example.mylibrary.utils.TLog;
import com.pilipa.fapiaobao.AppOperator;
import com.pilipa.fapiaobao.MainActivity;
import com.pilipa.fapiaobao.R;
import com.pilipa.fapiaobao.account.AccountHelper;
import com.pilipa.fapiaobao.adapter.publish.AllInvoiceAdapter;
import com.pilipa.fapiaobao.adapter.supply.FinanceAdapter;
import com.pilipa.fapiaobao.base.BaseActivity;
import com.pilipa.fapiaobao.base.BaseApplication;
import com.pilipa.fapiaobao.base.BaseFinanceFragment;
import com.pilipa.fapiaobao.net.Api;
import com.pilipa.fapiaobao.net.bean.LoginWithInfoBean;
import com.pilipa.fapiaobao.net.bean.invoice.AllInvoiceType;
import com.pilipa.fapiaobao.net.bean.invoice.DefaultInvoiceBean;
import com.pilipa.fapiaobao.net.bean.invoice.MacherBeanToken;
import com.pilipa.fapiaobao.net.bean.me.FavoriteCompanyBean;
import com.pilipa.fapiaobao.net.bean.me.MessageListBean;
import com.pilipa.fapiaobao.ui.EstimateLocationActivity;
import com.pilipa.fapiaobao.ui.FavCompanyChooseActivity;
import com.pilipa.fapiaobao.ui.MessageCenterActivity;
import com.pilipa.fapiaobao.ui.Op;
import com.pilipa.fapiaobao.ui.constants.Constant;
import com.pilipa.fapiaobao.ui.deco.FinanceItemDeco;
import com.pilipa.fapiaobao.ui.deco.GridInsetFinance;
import com.pilipa.fapiaobao.ui.zxing.SimpleCaptureActivity;
import com.pilipa.fapiaobao.utils.DialogUtil;
import com.pilipa.fapiaobao.utils.SharedPreferencesHelper;
import com.pilipa.fapiaobao.utils.TDevice;
import com.tbruyelle.rxpermissions2.RxPermissions;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

import static com.pilipa.fapiaobao.base.BaseApplication.PUSH_RECEIVE;
import static com.pilipa.fapiaobao.base.BaseApplication.set;
import static com.pilipa.fapiaobao.net.Constant.REQUEST_SUCCESS;

/**
 * Created by edz on 2017/10/23.
 */

public class FinanceFragment extends BaseFinanceFragment implements AllInvoiceAdapter.OnLabelClickListener, FinanceAdapter.OnLabelClickListener {
    public static final String EXTRA_DATA_LABEL = "extra_data_label";
    public static final String EXTRA_DATA_LABEL_NAME = "extra_data_label_name";
    public static final String DECODED_CONTENT_KEY = "codedContent";
    public static final String DECODED_BITMAP_KEY = "codedBitmap";
    public static final int REQUEST_CODE_SCAN = 0x0234;
   public static String TAG = "FinanceFragment";
//    @Bind(R.id.scan)
//    ImageView scan;
//    @Bind(R.id.notification)
//    ImageView notification;
    @Bind(R.id.recyclerview)
    public RecyclerView recyclerview;
    @Bind(R.id.recyclerview_more_kind)
    RecyclerView recyclerviewMoreKind;
    @Bind(R.id.pull_to_find_more)
    TextView pullToFindMore;
    @Bind(R.id.srollview)
    NestedScrollView srollview;
    @Bind(R.id.new_notification)
    ImageView newNotification;
    @Bind(R.id.rl_pull_to_find_more)
    RelativeLayout rlPullToFindMore;
    @Bind(R.id.title)
    TextView title;
    @Bind(R.id.select_your_receipt_kind)
    TextView selectYourReceiptKind;
    @Bind(R.id.fl_notification)
    FrameLayout flNotification;
    FinanceAdapter financeAdapter;
    private LoginWithInfoBean loginBean;
    private AllInvoiceAdapter adapter;
    private MainActivity activity;
    private Dialog scanDialog;
    private boolean delayIntentRefresh;
    private BroadcastReceiver mBoradcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals(BaseApplication.PUSH_RECEIVE)) {
                if (newNotification != null) {
                    newNotification.setVisibility(View.VISIBLE);
                }
            } else if (Constant.UPLOAD_SUCCESS.equals(intent.getAction())) {
                delayIntentRefresh = true;
            }
        }
    };
    private boolean isCacheSuccess = false;
    private Dialog mDialog;


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
        switch (requestCode) {
            case REQUEST_CODE_SCAN:
                if (resultCode == Activity.RESULT_OK) {
                    String content = data.getStringExtra(DECODED_CONTENT_KEY);
                    if (RegexUtils.isURL(content) || content.contains("http")) {
                        checkFavCompanies(content);
                    }else{
                        showDialog();
                    }
                }
                break;
            default:
        }
    }

    private void checkFavCompanies(final String content) {
        Api.favoriteCompanyList(AccountHelper.getToken(), this, new Api.BaseRawResponse<FavoriteCompanyBean>() {
            @Override
            public void onTokenInvalid() {
                login();
            }

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

            }

            @Override
            public void setData(FavoriteCompanyBean favoriteCompanyBean) {
                Intent intent = new Intent();
                intent.putExtra("url", content);
                intent.putExtra(Constant.TAG, TAG);
                if (favoriteCompanyBean == null || favoriteCompanyBean.getData() == null || favoriteCompanyBean.getData().size() == 0) {
                    intent.setClass(mContext, Op.class);
                    startActivity(intent);
                } else if (favoriteCompanyBean.getData().size() == 1) {
                    intent.setClass(mContext, Op.class);
                    intent.putExtra(Constant.COMPANY_INFO, makeCompany(favoriteCompanyBean.getData().get(0)));
                    startActivity(intent);
                } else {
                    intent.putExtra(Constant.COMPANIES_BEAN, favoriteCompanyBean);
                    intent.setClass(mContext, FavCompanyChooseActivity.class);
                    startActivity(intent);
                }
            }
        });
    }

    private MacherBeanToken.DataBean.CompanyBean makeCompany(FavoriteCompanyBean.DataBean companiesBean) {
        MacherBeanToken.DataBean.CompanyBean companyBean = new MacherBeanToken.DataBean.CompanyBean();
        companyBean.setAccount(companiesBean.getAccount());
        companyBean.setAddress(companiesBean.getAddress());
        companyBean.setDepositBank(companiesBean.getDepositBank());
        companyBean.setId(companiesBean.getId());
        companyBean.setIsNewRecord(companiesBean.isIsNewRecord());
        companyBean.setName(companiesBean.getName());
        companyBean.setPhone(companiesBean.getPhone());
        companyBean.setTaxno(companiesBean.getTaxno());
        return companyBean;
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
        TLog.d(TAG, "recyclerview.addItemDecoration");
        recyclerview.addItemDecoration(new GridInsetFinance(2, 0, true));
        recyclerviewMoreKind.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
        recyclerviewMoreKind.setNestedScrollingEnabled(false);
        recyclerviewMoreKind.addItemDecoration(new FinanceItemDeco(mContext, LinearLayoutManager.VERTICAL, (int) TDevice.dipToPx(getResources(), 23), R.color.white));
        initBroadcast();
    }

    private void initBroadcast() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(BaseApplication.PUSH_RECEIVE);
        intentFilter.addAction(Constant.UPLOAD_SUCCESS);
        mContext.registerReceiver(mBoradcastReceiver, intentFilter);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        try {
            mContext.unregisterReceiver(mBoradcastReceiver);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void initData() {
        super.initData();
        activity = (MainActivity) getActivity();
        findAllInvoiceTypes("");
    }


    private void findUserInvoiceType(String token, final AllInvoiceType allInvoiceType) {

        Api.<DefaultInvoiceBean>findUserInvoiceType(token, new Api.BaseRawResponseWithCache<DefaultInvoiceBean>() {
            @Override
            public void onCacheSuccess(DefaultInvoiceBean defaultInvoiceBean) {
                isCacheSuccess = true;
                fillupData(defaultInvoiceBean, allInvoiceType);
            }

            @Override
            public void onTokenInvalid() {
                TLog.log("onTokenInvalide");
                findDefaultInvoiceType(allInvoiceType);
            }

            @Override
            public void onStart() {

            }

            @Override
            public void onFinish() {
                isCacheSuccess = false;
            }

            @Override
            public void onError() {
                if (!isCacheSuccess) {
                    showNetWorkErrorLayout();
                }
            }

            @Override
            public void setData(DefaultInvoiceBean defaultInvoiceBean) {
                hideNetWorkErrorLayout();
                fillupData(defaultInvoiceBean, allInvoiceType);
            }
        });
    }

    private void fillupData(DefaultInvoiceBean defaultInvoiceBean, AllInvoiceType allInvoiceType) {
        try {
            if (defaultInvoiceBean.getData() != null && defaultInvoiceBean.getData().size() > 0) {
                financeAdapter = new FinanceAdapter(defaultInvoiceBean);
                financeAdapter.setOnLabelClickListener(FinanceFragment.this);
                recyclerview.setAdapter(financeAdapter);
                updateInvoiceHighlight(defaultInvoiceBean, allInvoiceType);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void updateInvoiceHighlight(final DefaultInvoiceBean defaultInvoiceBean, final AllInvoiceType allInvoiceType) {
        final ArrayList<DefaultInvoiceBean.DataBean> data1 = defaultInvoiceBean.getData();
        TLog.log("data1.size()" + data1.size());
        Observable.fromIterable(allInvoiceType.getData())
                .flatMap(new Function<AllInvoiceType.DataBean, ObservableSource<AllInvoiceType.DataBean.InvoiceTypeListBean>>() {
                    @Override
                    public ObservableSource<AllInvoiceType.DataBean.InvoiceTypeListBean> apply(AllInvoiceType.DataBean dataBean) throws Exception {
                        return Observable.fromIterable(dataBean.getInvoiceTypeList());
                    }
                }).subscribeOn(Schedulers.from(AppOperator.getExecutor()))
                .observeOn(Schedulers.from(AppOperator.getExecutor()))
                .subscribe(new Observer<AllInvoiceType.DataBean.InvoiceTypeListBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(AllInvoiceType.DataBean.InvoiceTypeListBean invoiceTypeListBean) {
                        for (DefaultInvoiceBean.DataBean dataBean : data1) {
                            if (TextUtils.equals(dataBean.getId(), invoiceTypeListBean.getId())) {
                                TLog.log("invoiceTypeListBean.setSelected(true);" + invoiceTypeListBean.getName());
                                invoiceTypeListBean.setSelected(true);
                            }
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        activity.hideProgressDialog();
                    }

                    @Override
                    public void onComplete() {
                        adapter.notifyDataSetChanged();
                        activity.showGuideViewFromChildFragment();
                        delayIntentRefresh = false;
                    }
                });
    }


    private void findDefaultInvoiceType(final AllInvoiceType allInvoiceType) {
        Api.<DefaultInvoiceBean>findDefaultInvoiceType(new Api.BaseRawResponseWithCache<DefaultInvoiceBean>() {
            @Override
            public void onCacheSuccess(DefaultInvoiceBean defaultInvoiceBean) {
                isCacheSuccess = true;
                hideNetWorkErrorLayout();
                fillupData(defaultInvoiceBean,allInvoiceType);
            }

            @Override
            public void onTokenInvalid() {

            }

            @Override
            public void onStart() {
                activity.showProgressDialog();
            }

            @Override
            public void onFinish() {
                activity.hideProgressDialog();
                isCacheSuccess = false;
            }

            @Override
            public void onError() {
                if (!isCacheSuccess) {
                    showNetWorkErrorLayout();
                }
            }

            @Override
            public void setData(DefaultInvoiceBean defaultInvoiceBean) {
                hideNetWorkErrorLayout();
                fillupData(defaultInvoiceBean,allInvoiceType);
            }
        });
    }

    private void findAllInvoiceTypes(String token) {
        Api.findAllInvoice(token, new Api.BaseViewCallbackWithOnStart<AllInvoiceType>() {

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
                showNetWorkErrorLayout();
            }


            @Override
            public void setData(AllInvoiceType allInvoiceType) {
                hideNetWorkErrorLayout();
                if (allInvoiceType.getStatus() == 200) {
                    adapter = new AllInvoiceAdapter(allInvoiceType);
                    adapter.setOnLabelClickListener(FinanceFragment.this);
                    recyclerviewMoreKind.setAdapter(adapter);
                    loginBean = SharedPreferencesHelper.loadFormSource(mContext, LoginWithInfoBean.class);
                    if (loginBean != null) {
                        findUserInvoiceType(loginBean.getData().getToken(), allInvoiceType);
                    } else {
                        findDefaultInvoiceType(allInvoiceType);
                    }
                }
            }
        });
    }


    @OnClick({ R.id.pull_to_find_more, R.id.rl_pull_to_find_more, R.id.fl_notification, R.id.title,R.id.fl_scan})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.fl_scan:
                quickResponse();
                break;
            case R.id.fl_notification:
                if (!Constant.NOTOKEN.equals(AccountHelper.getToken())) {
                    newNotification.setVisibility(View.GONE);
                    BaseApplication.set(BaseApplication.PUSH_RECEIVE, false);
                    startActivity(new Intent(mContext, MessageCenterActivity.class));
                } else {
                    login();
                }

                break;
            case R.id.pull_to_find_more:
            case R.id.rl_pull_to_find_more:
                srollview.setSmoothScrollingEnabled(true);
                pullToFindMore.measure(0, 0);
                srollview.smoothScrollTo(0, rlPullToFindMore.getTop());
                break;
            case R.id.title:
                srollview.smoothScrollTo(0, 0);
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
                    TLog.log("REQUEST_CODE_SCAN" + aBoolean);
                    startActivityForResult(new Intent(mContext, SimpleCaptureActivity.class), REQUEST_CODE_SCAN);
                }
            }

            @Override
            public void onError(Throwable e) {
                TLog.d(TAG,"Throwable e"+e.getMessage());
            }

            @Override
            public void onComplete() {
                TLog.d(TAG," rxPermissions.request  onComplete");
            }
        });
    }

    @Override
    public void onLabelClick(String id) {

    }

    @Override
    public void onLabelNameClick(String label, String name) {
        Intent intent = new Intent();
        intent.putExtra(EXTRA_DATA_LABEL, label);
        intent.putExtra(EXTRA_DATA_LABEL_NAME, name);
        intent.setClass(mContext, EstimateLocationActivity.class);
        startActivity(intent);
    }


    @Override
    protected void onNoNetworkLayoutClicks(View v) {
        initData();
    }

    private void messageList() {
        if (!"notoken".equals(AccountHelper.getToken())) {
            Api.messageList(AccountHelper.getToken(), new Api.BaseRawResponse<MessageListBean>() {
                @Override
                public void onStart() {

                }

                @Override
                public void onFinish() {

                }

                @Override
                public void onError() {

                }

                @Override
                public void onTokenInvalid() {

                }

                @Override
                public void setData(MessageListBean messageListBean) {
                    boolean hasNewMsg = false;
                    if (messageListBean.getStatus() == REQUEST_SUCCESS) {
                        List<MessageListBean.DataBean> data = messageListBean.getData();
                        for (int i = 0; i < data.size(); i++) {
                            if (data.get(i).getUnreadMessages() > 0) {
                                hasNewMsg = true;
                                TLog.d("MainActivity", "message center had new message");
                                break;
                            } else {
                                TLog.d("MainActivity", "message center no message1");
                            }
                        }
                        if (hasNewMsg) {
                            set(PUSH_RECEIVE, true);
                        } else {
                            set(PUSH_RECEIVE, false);
                        }
                    }
                }
            });
        }
    }

    private void showDialog() {
        if (mDialog == null) {
            mDialog = DialogUtil.getInstance().createDialog(mContext, 0, R.layout.layout_scan_tip, new DialogUtil.OnKnownListener() {
                @Override
                public void onKnown(View view) {
                    mDialog.dismiss();
                }
            }, null, null);
        }
        showDialog(mDialog);
    }


//    public void setScanDialog() {
//        scanDialog = new Dialog(getActivity(), R.style.BottomDialog);
//        LinearLayout root = (LinearLayout) LayoutInflater.from(getActivity()).inflate(
//                R.layout.layout_scan_tip, null);
//        //初始化视图
//        root.findViewById(R.id.btn_confirm).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                scanDialog.dismiss();
//            }
//        });
//        scanDialog.setContentView(root);
//        Window dialogWindow = scanDialog.getWindow();
//        dialogWindow.setGravity(Gravity.CENTER);
////        dialogWindow.setWindowAnimations(R.style.dialogstyle); // 添加动画
//        WindowManager.LayoutParams lp = dialogWindow.getAttributes(); // 获取对话框当前的参数值
//        lp.x = 0; // 新位置X坐标
//        lp.y = 0; // 新位置Y坐标
//        lp.width = (int) getResources().getDisplayMetrics().widthPixels; // 宽度
//        root.measure(0, 0);
//        lp.height = root.getMeasuredHeight();
//
//        lp.alpha = 9f; // 透明度
//        dialogWindow.setAttributes(lp);
//        scanDialog.show();
//    }

    @Override
    public void initDataInResume() {
        messageList();
        newNotification.setVisibility(BaseApplication.get(BaseApplication.PUSH_RECEIVE, false) ? View.VISIBLE : View.GONE);
        if (delayIntentRefresh) {
            findAllInvoiceTypes("");
        }
    }
}
