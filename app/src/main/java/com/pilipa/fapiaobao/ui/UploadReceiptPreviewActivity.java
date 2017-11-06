package com.pilipa.fapiaobao.ui;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mylibrary.utils.TLog;
import com.google.gson.Gson;
import com.pilipa.fapiaobao.R;
import com.pilipa.fapiaobao.account.AccountHelper;
import com.pilipa.fapiaobao.base.BaseActivity;
import com.pilipa.fapiaobao.base.BaseApplication;
import com.pilipa.fapiaobao.net.Api;
import com.pilipa.fapiaobao.net.bean.LoginWithInfoBean;
import com.pilipa.fapiaobao.net.bean.invoice.AllInvoiceVariety;
import com.pilipa.fapiaobao.net.bean.invoice.OrderBean;
import com.pilipa.fapiaobao.net.bean.invoice.RedBagBean;
import com.pilipa.fapiaobao.net.bean.invoice.UploadInvoiceToken;
import com.pilipa.fapiaobao.ui.fragment.FinanceFragment;
import com.pilipa.fapiaobao.ui.fragment.UploadPreviewReceiptFragment;
import com.pilipa.fapiaobao.ui.model.Image;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by edz on 2017/10/25.
 */

public class UploadReceiptPreviewActivity extends BaseActivity {
    private static final String TAG = "UploadReceiptPreviewActivity";
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
    @Bind(R.id.continue_to_upload)
    TextView continueToUpload;
    @Bind(R.id.confirm)
    Button confirm;
    int count =0;
    private UploadPreviewReceiptFragment paperNormalReceiptFragment;
    private UploadPreviewReceiptFragment paperSpecialReceiptFragment;
    private UploadPreviewReceiptFragment paperElecReceiptFragment;
    public static final String PAPER_NORMAL_RECEIPT_DATA = "paper_normal_receipt_data";
    public static final String PAPER_SPECIAL_RECEIPT_DATA = "paper_special_receipt_data";
    public static final String PAPER_ELEC_RECEIPT_DATA = "paper_elec_receipt_data";
    private double amount;
    private double bonus;
    private String demandsId;
    private String label;
    private double sum;
    private String company_id;


    @Override
    public void onClick(View v) {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_upload_preview;
    }

    @Override
    public void initView() {
        label = getIntent().getStringExtra(FinanceFragment.EXTRA_DATA_LABEL);
        TLog.log("label"+label);
        amount = getIntent().getDoubleExtra("amount", 0);
//        bonus = getIntent().getDoubleExtra("bonus", 0);
        demandsId = getIntent().getStringExtra("demandsId");
//        receiptMoney.setText(amount + "");
        company_id = getIntent().getStringExtra("company_id");

    }

    @Override
    public void initData() {
        Bundle bundleExtra = getIntent().getBundleExtra(UploadReceiptActivity.TAG);
        final ArrayList<Image> listPN = bundleExtra.getParcelableArrayList(UploadReceiptActivity.PAPER_NORMAL_RECEIPT_DATA);
        final ArrayList<Image> listPS = bundleExtra.getParcelableArrayList(UploadReceiptActivity.PAPER_SPECIAL_RECEIPT_DATA);
        final ArrayList<Image> listPE = bundleExtra.getParcelableArrayList(UploadReceiptActivity.PAPER_ELEC_RECEIPT_DATA);

        Api.findAllInvoiceVariety(new Api.BaseViewCallback<AllInvoiceVariety>() {

            @Override
            public void setData(AllInvoiceVariety allInvoiceVariety) {
                sum = 0;
                for (Image image : listPE) {
                    if (!image.isCapture) {
                        TLog.log("image.amount" + image.amount);
                        sum += Double.valueOf(image.amount);
                    }
                }
                for (Image image : listPS) {
                    if (!image.isCapture) {
                        TLog.log("image.amount" + image.amount);
                        sum += Double.valueOf(image.amount);
                    }
                }

                for (Image image : listPN) {
                    if (!image.isCapture) {
                        TLog.log("image.amount" + image.amount);
                        sum += Double.valueOf(image.amount);
                    }
                }
                int count0 = 0;
                if (listPN != null) {
                    if (listPN.size() > 1) {
                        Bundle bundle = new Bundle();
                        bundle.putString("invoice_variety", allInvoiceVariety.getData().get(0).getValue());
                        bundle.putParcelableArrayList(PAPER_NORMAL_RECEIPT_DATA, listPN);
                        paperNormalReceiptFragment = UploadPreviewReceiptFragment.newInstance(bundle);
                        addCaptureFragment(R.id.container_paper_normal_receipt, paperNormalReceiptFragment);
                        if (paperNormalReceiptFragment != null) {
                            count0 = listPN.size()-1;
                        }
                    }
                }
                TLog.log(count0+"");
                int count1 = 0;
                if (listPS != null) {
                    if (listPS.size() > 1) {
                        Bundle bundle = new Bundle();
                        bundle.putString("invoice_variety", allInvoiceVariety.getData().get(1).getValue());
                        bundle.putParcelableArrayList(PAPER_SPECIAL_RECEIPT_DATA, listPS);
                        paperSpecialReceiptFragment = UploadPreviewReceiptFragment.newInstance(bundle);
                        addCaptureFragment(R.id.container_paper_special_receipt, paperSpecialReceiptFragment);
                        if (paperSpecialReceiptFragment != null) {
                            count1 = listPS.size()-1;
                        }
                    }
                }
                int count2 = 0;
                if (listPE != null) {
                    if (listPE.size() > 1) {
                        Bundle bundle = new Bundle();
                        bundle.putString("invoice_variety", allInvoiceVariety.getData().get(2).getValue());
                        bundle.putParcelableArrayList(PAPER_ELEC_RECEIPT_DATA, listPE);
                        paperElecReceiptFragment = UploadPreviewReceiptFragment.newInstance(bundle);
                        addCaptureFragment(R.id.container_paper_elec_receipt, paperElecReceiptFragment);
                        if (paperElecReceiptFragment != null) {
                            count2 = listPE.size()-1;
                        }
                    }
                }

                count = count0 + count1 + count2;


                receiptNumber.setText(count + "");
                receiptMoney.setText(String.valueOf(sum));

                AccountHelper.isTokenValid(new Api.BaseViewCallback<LoginWithInfoBean>() {
                    @Override
                    public void setData(LoginWithInfoBean loginWithInfoBean) {
                        Api.estimateRedBag(loginWithInfoBean.getData().getToken(), demandsId, sum, new Api.BaseViewCallbackWithOnStart<RedBagBean>() {
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
                            public void setData(RedBagBean redBagBean) {
                                if (redBagBean.getStatus() == 200) {
                                    estimateMoney.setText(String.valueOf(redBagBean.getData().getBonus()));
                                }
                            }
                        });
                    }
                });
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
    protected void onStart() {
        super.onStart();
//        int count0 = 0;
//        if (paperNormalReceiptFragment!= null) {
//            Log.d(TAG, "initData: paperNormalReceiptFragment");
//            count0 = paperNormalReceiptFragment.getCurrentImageCount();
//        }
//
//        int count1 = 0;
//        if (paperSpecialReceiptFragment!= null) {
//            Log.d(TAG, "initData: paperSpecialReceiptFragment");
//            count1 = paperSpecialReceiptFragment.getCurrentImageCount();
//        }
//
//        int count2 = 0;
//        if (paperElecReceiptFragment!= null) {
//            Log.d(TAG, "initData: paperElecReceiptFragment");
//            count2 = paperElecReceiptFragment.getCurrentImageCount();
//        }
//
//        count = count0 + count1 + count2;
//        receiptNumber.setText(count+"");
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

    @OnClick({R.id.continue_to_upload, R.id.confirm})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.continue_to_upload:
                break;
            case R.id.confirm:
                showProgressDialog();
                upload();
                break;
        }
    }

    private void upload() {
        AccountHelper.isTokenValid(new Api.BaseViewCallback<LoginWithInfoBean>() {
            @Override
            public void setData(LoginWithInfoBean loginWithInfoBean) {
                if (loginWithInfoBean.getStatus() == 200) {
                    UploadInvoiceToken uploadInvoiceToken = new UploadInvoiceToken();
                    uploadInvoiceToken.setDemandId(demandsId);
                    uploadInvoiceToken.setInvoiceType(label);
                    uploadInvoiceToken.setToken(loginWithInfoBean.getData().getToken());
                    uploadInvoiceToken.setTotalAmount((int) sum);


                    ArrayList<UploadInvoiceToken.InvoiceListBean> invoiceListBeanArrayList = new ArrayList<UploadInvoiceToken.InvoiceListBean>();

                    if (paperNormalReceiptFragment != null) {
                        ArrayList<Image> currentImagesPN = paperNormalReceiptFragment.getCurrentImages();
                        for (Image image : currentImagesPN) {
                            UploadInvoiceToken.InvoiceListBean invoiceListBeanPN = new UploadInvoiceToken.InvoiceListBean();
                            invoiceListBeanPN.setAmount(Integer.valueOf(image.amount));
                            invoiceListBeanPN.setPicture(upLoadReceipt(image.uri));
                            invoiceListBeanPN.setVariety("1");
                            invoiceListBeanArrayList.add(invoiceListBeanPN);
                        }
                    }


                    if (paperSpecialReceiptFragment != null) {
                        ArrayList<Image> currentImagesPS = paperSpecialReceiptFragment.getCurrentImages();
                        for (Image image : currentImagesPS) {
                            UploadInvoiceToken.InvoiceListBean invoiceListBeanPN = new UploadInvoiceToken.InvoiceListBean();
                            invoiceListBeanPN.setAmount(Integer.valueOf(image.amount));
                            invoiceListBeanPN.setPicture(upLoadReceipt(image.uri));
                            invoiceListBeanPN.setVariety("2");
                            invoiceListBeanArrayList.add(invoiceListBeanPN);
                        }
                    }

                    if (paperElecReceiptFragment != null) {
                        ArrayList<Image> currentImagesPE = paperElecReceiptFragment.getCurrentImages();
                        for (Image image : currentImagesPE) {
                            UploadInvoiceToken.InvoiceListBean invoiceListBeanPN = new UploadInvoiceToken.InvoiceListBean();
                            invoiceListBeanPN.setAmount(Integer.valueOf(image.amount));

                            invoiceListBeanPN.setVariety("3");

                            if (image.isFromNet) {
                                invoiceListBeanPN.setId(image.path);
                            } else {
                                invoiceListBeanPN.setPicture(upLoadReceipt(image.uri));
                            }
                            invoiceListBeanArrayList.add(invoiceListBeanPN);
                        }
                    }

                    uploadInvoiceToken.setInvoiceList(invoiceListBeanArrayList);
                    Gson gson = new Gson();
                    String json = gson.toJson(uploadInvoiceToken);
                    Api.createOrder(json, new Api.BaseViewCallbackWithOnStart<OrderBean>() {

                        private Intent intent;

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
                        public void setData(OrderBean orderBean) {
                            if (orderBean.getStatus() == 200) {
                                BaseApplication.showToast("发票上传成功");
                                Intent intent = new Intent(UploadReceiptPreviewActivity.this, UploadSuccessActivity.class);
                                intent.putExtra("order_id", orderBean.getData().getOrderId());
                                intent.putExtra("company_id", company_id);
                                startActivity(intent);
                                finish();
                            }
                        }
                    });

                }
            }
        });
    }

    public void onPubSuccess() {
            startActivity(new Intent(this, UploadSuccessActivity.class));
    }
}
