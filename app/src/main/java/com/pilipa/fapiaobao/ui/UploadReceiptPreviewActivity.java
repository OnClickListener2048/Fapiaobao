package com.pilipa.fapiaobao.ui;

import android.app.Activity;
import android.app.Dialog;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.mylibrary.utils.ActivityUtils;
import com.example.mylibrary.utils.RegexUtils;
import com.example.mylibrary.utils.TLog;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.pilipa.fapiaobao.AppOperator;
import com.pilipa.fapiaobao.R;
import com.pilipa.fapiaobao.account.AccountHelper;
import com.pilipa.fapiaobao.base.BaseActivity;
import com.pilipa.fapiaobao.base.BaseApplication;
import com.pilipa.fapiaobao.net.Api;
import com.pilipa.fapiaobao.net.bean.invoice.MacherBeanToken;
import com.pilipa.fapiaobao.net.bean.invoice.OrderBean;
import com.pilipa.fapiaobao.net.bean.invoice.RedBagBean;
import com.pilipa.fapiaobao.net.bean.invoice.UploadInvoiceToken;
import com.pilipa.fapiaobao.ui.constants.Constant;
import com.pilipa.fapiaobao.ui.fragment.FinanceFragment;
import com.pilipa.fapiaobao.ui.fragment.UploadPreviewReceiptFragment;
import com.pilipa.fapiaobao.ui.model.Image;
import com.pilipa.fapiaobao.ui.receipt_folder_image_select.ReceiptActivityToken;
import com.pilipa.fapiaobao.ui.zxing.SimpleCaptureActivity;
import com.pilipa.fapiaobao.utils.ButtonUtils;
import com.pilipa.fapiaobao.utils.DialogUtil;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by edz on 2017/10/25.
 */

public class UploadReceiptPreviewActivity extends BaseActivity {
    public static final String PAPER_NORMAL_RECEIPT_DATA = "paper_normal_receipt_data";
    public static final String PAPER_SPECIAL_RECEIPT_DATA = "paper_special_receipt_data";
    public static final String PAPER_ELEC_RECEIPT_DATA = "paper_elec_receipt_data";
    private static final String TAG = "UploadReceiptPreviewActivity";
    private static final String DECODED_CONTENT_KEY = "codedContent";
    private static final int REQUEST_CODE_SCAN = 0x0002;
    public boolean mOnNewIntent = false;
    @Bind(R.id.title)
    TextView title;
    @Bind(R.id.upload_back)
    ImageView uploadBack;
    @Bind(R.id.upload_scan)
    ImageView uploadScan;
    @Bind(R.id.container_paper_normal_receipt)
    FrameLayout containerPaperNormalReceipt;
    @Bind(R.id.container_paper_special_receipt)
    FrameLayout containerPaperSpecialReceipt;
    @Bind(R.id.container_paper_elec_receipt)
    FrameLayout containerPaperElecReceipt;
    @Bind(R.id.receipt_number)
    TextView receiptNumber;
    @Bind(R.id.receipt_money)
    TextView receiptMoney;
    @Bind(R.id.estimate_money)
    TextView estimateMoney;
    @Bind(R.id.confirm)
    Button confirm;
    int count = 0;
    @Bind(R.id.ll_container_paper_normal_receipt)
    LinearLayout llContainerPaperNormalReceipt;
    @Bind(R.id.ll_container_paper_special_receipt)
    LinearLayout llContainerPaperSpecialReceipt;
    @Bind(R.id.ll_container_paper_elec_receipt)
    LinearLayout llContainerPaperElecReceipt;
    private UploadPreviewReceiptFragment paperNormalReceiptFragment;
    private UploadPreviewReceiptFragment paperSpecialReceiptFragment;
    private UploadPreviewReceiptFragment paperElecReceiptFragment;
    private double amount;
    private double bonus;
    private String demandsId;
    private String label;
    private double sum;
    private String company_id;
    private int type = 0;
    private double aDouble;
    private ArrayList<Image> mListPN;
    private ArrayList<Image> mListPS;
    private ArrayList<Image> mListPE;
    private MacherBeanToken.DataBean.CompanyBean company_info;
    private Dialog mDialog;

    @Override
    public void onClick(View v) {

    }

    public void scan() {
        Intent intent = new Intent(this, SimpleCaptureActivity.class);
        startActivityForResult(intent, REQUEST_CODE_SCAN);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_upload_preview;
    }

    @Override
    public void initView() {
        type = getIntent().getIntExtra("type", 0);
        label = getIntent().getStringExtra(FinanceFragment.EXTRA_DATA_LABEL);
        TLog.log("label" + label);
        amount = getIntent().getDoubleExtra("amount", 0);
        demandsId = getIntent().getStringExtra("demandsId");
        company_id = getIntent().getStringExtra("company_id");
        company_info = getIntent().getParcelableExtra("company_info");
    }

    @Override
    protected void onResume() {
        super.onResume();
        TLog.d(TAG, "onResume");
        if (!mOnNewIntent) {

        }

    }

    public void cacu() {
        reCaculate();
        estimateRedBag();
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        TLog.d(TAG, "onNewIntent");
        paperElecReceiptFragment.resultFromElec(intent);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart: ");
    }

    @Override
    public void onAttachFragment(android.support.v4.app.Fragment fragment) {
        super.onAttachFragment(fragment);
        Log.d(TAG, "onAttachFragment: ");
        if (paperElecReceiptFragment != null && !paperElecReceiptFragment.mIsDone) {
            if (mOnNewIntent) {
                paperElecReceiptFragment.resultFromElec(getIntent());
            }
        }

    }

    @Override
    public void initData() {
        Bundle bundleExtra = getIntent().getBundleExtra(ConfirmActivity.TAG);
        if (bundleExtra == null) {
            demandsId = getIntent().getStringExtra(Constant.DEMANDS_ID);
            if (paperElecReceiptFragment == null) {
                mOnNewIntent = true;
                TLog.d(TAG, "mOnNewIntent" + mOnNewIntent);
                TLog.d(TAG, getIntent().toString());
                Bundle bundle = new Bundle();
                bundle.putString("invoice_variety", "3");
                Bundle extra = getIntent().getBundleExtra(ReceiptActivityToken.EXTRA_DATA_FROM_TOKEN);
                TLog.d(TAG, "extra" + extra);
                TLog.d(TAG, "extra.getParcelableArrayList(ReceiptActivityToken.RESULT_RECEIPT_FOLDER)" + extra.getParcelableArrayList(ReceiptActivityToken.RESULT_RECEIPT_FOLDER));
                bundle.putParcelableArrayList(PAPER_ELEC_RECEIPT_DATA, extra.getParcelableArrayList(ReceiptActivityToken.RESULT_RECEIPT_FOLDER));
                paperElecReceiptFragment = UploadPreviewReceiptFragment.newInstance(bundle);
                addCaptureFragment(R.id.container_paper_elec_receipt, paperElecReceiptFragment);
                llContainerPaperElecReceipt.setVisibility(View.VISIBLE);
            }
            return;
        }
        mListPN = bundleExtra.getParcelableArrayList(UploadReceiptActivity.PAPER_NORMAL_RECEIPT_DATA);
        mListPS = bundleExtra.getParcelableArrayList(UploadReceiptActivity.PAPER_SPECIAL_RECEIPT_DATA);
        mListPE = bundleExtra.getParcelableArrayList(UploadReceiptActivity.PAPER_ELEC_RECEIPT_DATA);


        if (mListPN != null) {
            llContainerPaperNormalReceipt.setVisibility(View.VISIBLE);
            Bundle bundle = new Bundle();
            bundle.putString("invoice_variety", "1");
            bundle.putParcelableArrayList(PAPER_NORMAL_RECEIPT_DATA, mListPN);
            paperNormalReceiptFragment = UploadPreviewReceiptFragment.newInstance(bundle);
            addCaptureFragment(R.id.container_paper_normal_receipt, paperNormalReceiptFragment);
        } else {
            llContainerPaperNormalReceipt.setVisibility(View.GONE);
        }

        if (mListPS != null) {
            llContainerPaperSpecialReceipt.setVisibility(View.VISIBLE);
            Bundle bundle = new Bundle();
            bundle.putString("invoice_variety", "2");
            bundle.putParcelableArrayList(PAPER_SPECIAL_RECEIPT_DATA, mListPS);
            paperSpecialReceiptFragment = UploadPreviewReceiptFragment.newInstance(bundle);
            addCaptureFragment(R.id.container_paper_special_receipt, paperSpecialReceiptFragment);
        } else {
            llContainerPaperSpecialReceipt.setVisibility(View.GONE);
        }

        if (mListPE != null) {
            llContainerPaperElecReceipt.setVisibility(View.VISIBLE);
            Bundle bundle = new Bundle();
            bundle.putString("invoice_variety", "3");
            bundle.putParcelableArrayList(PAPER_ELEC_RECEIPT_DATA, mListPE);
            paperElecReceiptFragment = UploadPreviewReceiptFragment.newInstance(bundle);
            addCaptureFragment(R.id.container_paper_elec_receipt, paperElecReceiptFragment);
        } else {
            llContainerPaperElecReceipt.setVisibility(View.GONE);
        }

//        Api.findAllInvoiceVariety(new Api.BaseViewCallback<AllInvoiceVariety>() {
//
//            @Override
//            public void setData(AllInvoiceVariety allInvoiceVariety) {
//                sum = 0;
//                for (Image image : mListPE) {
//                    if (!image.isCapture) {
//                        TLog.log("image.amount" + image.amount);
//                        sum += Double.valueOf(image.amount);
//                    }
//                }
//                for (Image image : mListPS) {
//                    if (!image.isCapture) {
//                        TLog.log("image.amount" + image.amount);
//                        sum += Double.valueOf(image.amount);
//                    }
//                }
//
//                for (Image image : mListPN) {
//                    if (!image.isCapture) {
//                        TLog.log("image.amount" + image.amount);
//                        sum += Double.valueOf(image.amount);
//                    }
//                }
//                int count0 = 0;
//                if (mListPN.size() > 1) {
//                    llContainerPaperNormalReceipt.setVisibility(View.VISIBLE);
//                    Bundle bundle = new Bundle();
//                    bundle.putString("invoice_variety", allInvoiceVariety.getData().get(0).getValue());
//                    bundle.putParcelableArrayList(PAPER_NORMAL_RECEIPT_DATA, mListPN);
//                    paperNormalReceiptFragment = UploadPreviewReceiptFragment.newInstance(bundle);
//                    addCaptureFragment(R.id.container_paper_normal_receipt, paperNormalReceiptFragment);
//                    if (paperNormalReceiptFragment != null) {
//                        count0 = mListPN.size() - 1;
//                    }
//                } else {
//                    llContainerPaperNormalReceipt.setVisibility(View.GONE);
//                }
//                TLog.log(count0 + "");
//                int count1 = 0;
//                if (mListPS.size() > 1) {
//                    llContainerPaperSpecialReceipt.setVisibility(View.VISIBLE);
//                    Bundle bundle = new Bundle();
//                    bundle.putString("invoice_variety", allInvoiceVariety.getData().get(1).getValue());
//                    bundle.putParcelableArrayList(PAPER_SPECIAL_RECEIPT_DATA, mListPS);
//                    paperSpecialReceiptFragment = UploadPreviewReceiptFragment.newInstance(bundle);
//                    addCaptureFragment(R.id.container_paper_special_receipt, paperSpecialReceiptFragment);
//                    if (paperSpecialReceiptFragment != null) {
//                        count1 = mListPS.size() - 1;
//                    }
//
//                } else {
//                    llContainerPaperSpecialReceipt.setVisibility(View.GONE);
//                }
//
//                int count2 = 0;
//                if (mListPE.size() > 1) {
//                    llContainerPaperElecReceipt.setVisibility(View.VISIBLE);
//                    Bundle bundle = new Bundle();
//                    bundle.putString("invoice_variety", allInvoiceVariety.getData().get(2).getValue());
//                    bundle.putParcelableArrayList(PAPER_ELEC_RECEIPT_DATA, mListPE);
//                    paperElecReceiptFragment = UploadPreviewReceiptFragment.newInstance(bundle);
//                    addCaptureFragment(R.id.container_paper_elec_receipt, paperElecReceiptFragment);
//                    if (paperElecReceiptFragment != null) {
//                        count2 = mListPE.size() - 1;
//                    }
//                } else {
//                    llContainerPaperElecReceipt.setVisibility(View.GONE);
//                }
//
//                count = count0 + count1 + count2;
//
//                TLog.log("sum"+sum);
//                receiptNumber.setText(String.valueOf(count));
//                BigDecimal bigDecimal = new BigDecimal(sum);
//                TLog.log("BigDecimal bigDecimal = new BigDecimal(sum);"+sum);
//                BigDecimal bigDecimal1 = bigDecimal.setScale(2, RoundingMode.HALF_DOWN);
//                TLog.log(" bigDecimal.setScale(2, RoundingMode.UP);"+bigDecimal1);
//                receiptMoney.setText(String.valueOf(bigDecimal1));
//
//
//
//            }
//        });
    }

    private void reCaculate() {
        sum = getAmountSum(paperElecReceiptFragment != null ? paperElecReceiptFragment.getCurrentImages() : null)
                + getAmountSum(paperNormalReceiptFragment != null ? paperNormalReceiptFragment.getCurrentImages() : null)
                + getAmountSum(paperSpecialReceiptFragment != null ? paperSpecialReceiptFragment.getCurrentImages() : null);
        BigDecimal bigDecimal = new BigDecimal(sum);
        BigDecimal bigDecimal1 = bigDecimal.setScale(2, RoundingMode.HALF_DOWN);
        receiptMoney.setText(String.valueOf(bigDecimal1));

        int count = 0;
        if (paperNormalReceiptFragment != null) {
            count += paperNormalReceiptFragment.getCurrentImages().size() - 1;
        }

        if (paperSpecialReceiptFragment != null) {
            count += paperSpecialReceiptFragment.getCurrentImages().size() - 1;
        }

        if (paperElecReceiptFragment != null) {
            count += paperElecReceiptFragment.getCurrentImages().size() - 1;
        }
        receiptNumber.setText(String.valueOf(count));

    }

    private double getAmountSum(ArrayList<Image> arrayList) {
        if (arrayList == null) {
            return 0;
        }
        double sum = 0;
        for (Image image : arrayList) {
            if (!image.isCapture()) {
                if (image.amount != null) {
                    sum = sum + Double.valueOf(image.amount);
                }
            }
        }
        return sum;
    }

    private void estimateRedBag() {
        Api.estimateRedBag(AccountHelper.getToken(), demandsId, sum, new Api.BaseRawResponse<RedBagBean>() {
            @Override
            public void onTokenInvalid() {

            }

            @Override
            public void onStart() {
                TLog.log(" Api.estimateRedBag(l");
                showProgressDialog();
            }

            @Override
            public void onFinish() {
                hideProgressDialog();
            }

            @Override
            public void onError() {
            }

            @Override
            public void setData(RedBagBean redBagBean) {
                if (redBagBean.getStatus() == com.pilipa.fapiaobao.net.Constant.REQUEST_SUCCESS) {
                    TLog.log("redBagBean.getStatus() == 200");
                    UploadReceiptPreviewActivity.this.aDouble = redBagBean.getData().getBonus();
                    BigDecimal bigDecimal = new BigDecimal(UploadReceiptPreviewActivity.this.aDouble);
                    BigDecimal bigDecimal1 = bigDecimal.setScale(2, BigDecimal.ROUND_HALF_UP);
                    estimateMoney.setText(String.valueOf(bigDecimal1));
                }
            }
        });
    }

    @Override
    public View onCreateView(String name, Context context, AttributeSet attrs) {
        return super.onCreateView(name, context, attrs);
    }

    @Override
    public void onAttachFragment(Fragment fragment) {
        super.onAttachFragment(fragment);

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick(R.id.upload_back)
    public void onViewClicked() {
        finish();
    }

    @OnClick({R.id.confirm})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.confirm:
                showProgressDialog();
                if (!ButtonUtils.isFastDoubleClick(R.id.confirm)) {
                    upload();
                }
                break;
            default:
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case REQUEST_CODE_SCAN:
                if (resultCode == Activity.RESULT_OK) {
                    String content = data.getStringExtra(DECODED_CONTENT_KEY);
                    TLog.log(content);
                    if (RegexUtils.isURL(content) || content.contains("http")) {
                        Intent intent = new Intent();
                        intent.putExtra(Constant.COMPANY_INFO, company_info);
                        intent.putExtra(Constant.IS_FROM_UPLOADRECEIPT_ACTIVITY, true);
                        intent.setClass(this, Op.class);
                        intent.putExtra("url", content);
                        startActivity(intent);
                    } else {
                        showTipDialog();
                    }
                }
                break;
            default:
        }
    }

    private void showTipDialog() {

        if (mDialog == null) {
            mDialog = DialogUtil.getInstance().createDialog(this, 0, R.layout.layout_scan_tip, new DialogUtil.OnKnownListener() {
                @Override
                public void onKnown(View view) {
                    mDialog.dismiss();
                }
            }, null, null);
            View rootView = DialogUtil.getInstance().getRootView();
            if (rootView != null) {
                TextView textView = (TextView) rootView.findViewById(R.id.scan_tip);
                textView.setText(getString(R.string.cannot_parse_this_kind_of_code_from_now_on));
            }
        }
        showDialog(mDialog);
    }

    private void upload() {
        makeUpParams();
    }

    private void makeUpParams() {
        TLog.log("private void makeUpParams(LoginWithInfoBean loginWithInfoBean) {");
        final UploadInvoiceToken uploadInvoiceToken = new UploadInvoiceToken();
        uploadInvoiceToken.setDemandId(demandsId);
        uploadInvoiceToken.setInvoiceType(label);
        uploadInvoiceToken.setToken(AccountHelper.getToken());
        uploadInvoiceToken.setTotalAmount(sum);

        final ArrayList<UploadInvoiceToken.InvoiceListBean> invoiceListBeanArrayList = new ArrayList<>();


        Observable.create(new ObservableOnSubscribe<UploadInvoiceToken.InvoiceListBean>() {
            @Override
            public void subscribe(ObservableEmitter<UploadInvoiceToken.InvoiceListBean> e) throws Exception {
                if (paperNormalReceiptFragment != null) {
                    ArrayList<Image> currentImagesPN = paperNormalReceiptFragment.getCurrentImages();
                    for (Image image : currentImagesPN) {
                        TLog.log(" for (Image image : currentImagesPN) {");
                        UploadInvoiceToken.InvoiceListBean invoiceListBean = new UploadInvoiceToken.InvoiceListBean();
                        if (image.isCapture) {
                            invoiceListBean.setCapture(true);
                            e.onNext(invoiceListBean);
                        } else {
                            TLog.log("image.amount" + image.amount);
                            invoiceListBean.setAmount(Double.valueOf(image.amount));
                            TLog.log("image.uri" + image.uri);
                            invoiceListBean.setPicture(upLoadReceipt(image));
                            TLog.log("image.uri" + image.uri);
                            invoiceListBean.setVariety("1");
                            TLog.log("e.onNext(invoiceListBean);");
                            e.onNext(invoiceListBean);
                        }
                    }
                }


                if (paperSpecialReceiptFragment != null) {
                    ArrayList<Image> currentImagesPS = paperSpecialReceiptFragment.getCurrentImages();
                    for (Image image : currentImagesPS) {
                        TLog.log("for (Image image : currentImagesPS) {");
                        UploadInvoiceToken.InvoiceListBean invoiceListBean = new UploadInvoiceToken.InvoiceListBean();
                        if (image.isCapture) {
                            invoiceListBean.setCapture(true);
                            e.onNext(invoiceListBean);
                        } else {
                            invoiceListBean.setAmount(Double.valueOf(image.amount));
                            invoiceListBean.setPicture(upLoadReceipt(image));
                            invoiceListBean.setVariety("2");
                            e.onNext(invoiceListBean);
                        }

                    }
                }

                if (paperElecReceiptFragment != null) {
                    ArrayList<Image> currentImagesPE = paperElecReceiptFragment.getCurrentImages();
                    for (Image image : currentImagesPE) {
                        TLog.log("for (Image image : currentImagesPE) {");
                        UploadInvoiceToken.InvoiceListBean invoiceListBean = new UploadInvoiceToken.InvoiceListBean();
                        if (image.amount == null) {
                            image.setCapture(true);
                        }
                        if (image.isCapture) {
                            TLog.d(TAG, " if (image.isCapture) {");
                            invoiceListBean.setCapture(true);
                            e.onNext(invoiceListBean);
                        } else {
                            TLog.d(TAG, "invoiceListBean.setAmount(Double.valueOf(image.amount));");
                            TLog.d(TAG, "image.amount" + image.amount);
                            TLog.d(TAG, "image.isFromNet" + image.isFromNet());
                            TLog.d(TAG, "image.isPdf" + image.isPdf);
                            TLog.d(TAG, "image.path" + image.path);
                            TLog.d(TAG, "image.id" + image.id);
                            invoiceListBean.setAmount(Double.valueOf(image.amount));
                            invoiceListBean.setVariety("3");

                            if (image.isFromNet) {
                                if (image.isPdf) {
                                    invoiceListBean.setUrl(subStringUrl(image.path));
                                } else {
                                    invoiceListBean.setId(image.id);
                                }
                            } else {
                                invoiceListBean.setPicture(upLoadReceipt(image));
                            }
                            e.onNext(invoiceListBean);
                        }
                    }
                }
                TLog.log(" e.onComplete();");
                e.onComplete();
            }
        })
                .subscribeOn(Schedulers.from(AppOperator.getExecutor()))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<UploadInvoiceToken.InvoiceListBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        showProgressDialog(getString(R.string.analysing));
                    }

                    @Override
                    public void onNext(UploadInvoiceToken.InvoiceListBean invoiceListBean) {
                        TLog.log(" public void onNext(UploadInvoiceToken.InvoiceListBean invoiceListBean) {");
                        if (!invoiceListBean.isCapture()) {
                            invoiceListBeanArrayList.add(invoiceListBean);
                            updateDialog(String.valueOf(invoiceListBeanArrayList.size() + "/" + count));
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        TLog.d(TAG, e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                        Observable.create(new ObservableOnSubscribe<String>() {
                            @Override
                            public void subscribe(ObservableEmitter<String> e) throws Exception {
                                uploadInvoiceToken.setInvoiceList(invoiceListBeanArrayList);
                                Gson gson = new Gson();
                                String json = gson.toJson(uploadInvoiceToken);
                                e.onNext(json);
                                e.onComplete();
                            }
                        }).subscribeOn(Schedulers.from(AppOperator.getExecutor()))
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribe(new Observer<String>() {
                                    @Override
                                    public void onSubscribe(Disposable d) {
                                        updateDialogWithDescription(getString(R.string.wrapping));
                                    }

                                    @Override
                                    public void onNext(String json) {
                                        Api.createOrder(json, UploadReceiptPreviewActivity.this, new Api.BaseViewCallbackWithOnStart<OrderBean>() {
                                            @Override
                                            public void onStart() {
                                                updateDialogWithDescription(getString(R.string.uploading));
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
                                            public void setData(OrderBean orderBean) {
                                                if (orderBean.getStatus() == 200) {
                                                    BaseApplication.showToast(getString(R.string.upload_invoice_success));
                                                    Intent intent = new Intent(UploadReceiptPreviewActivity.this, UploadSuccessActivity.class);
                                                    intent.putExtra("order_id", orderBean.getData().getOrderId());
                                                    intent.putExtra("company_id", company_id);
                                                    intent.putExtra("bonus", aDouble);
                                                    Intent intent1 = new Intent();
                                                    intent1.setAction(Constant.UPLOAD_SUCCESS);
                                                    sendBroadcast(intent1);
                                                    startActivity(intent);
                                                    ActivityUtils.finishToActivity(EstimateLocationActivity.class, true);
                                                } else if (orderBean.getStatus() == 886) {
                                                    TLog.log("orderBean。getStatus" + orderBean.getStatus());
                                                    BaseApplication.showToast(orderBean.getMsg());
                                                } else if (orderBean.getStatus() == 887) {
                                                    TLog.log("orderBean。getStatus" + orderBean.getStatus());
                                                    BaseApplication.showToast(orderBean.getMsg());
                                                }
                                            }
                                        });
                                    }

                                    @Override
                                    public void onError(Throwable e) {

                                    }

                                    @Override
                                    public void onComplete() {
                                    }
                                });
                    }
                });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        TLog.log(" OkGo.cancelTag(OkGo.getInstance().getOkHttpClient(),this);");
        OkGo.cancelTag(OkGo.getInstance().getOkHttpClient(), this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        TLog.log(" OkGo.cancelTag(OkGo.getInstance().getOkHttpClient(),this);");
        OkGo.cancelTag(OkGo.getInstance().getOkHttpClient(), this);
    }

    private String subStringUrl(String originUrl) {
        return originUrl.substring(originUrl.indexOf("/invoice"));
    }

    public void onPubSuccess() {
        startActivity(new Intent(this, UploadSuccessActivity.class));
    }
}
