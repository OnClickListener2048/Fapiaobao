package com.pilipa.fapiaobao.ui;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.pilipa.fapiaobao.R;
import com.pilipa.fapiaobao.base.BaseActivity;
import com.pilipa.fapiaobao.net.Api;
import com.pilipa.fapiaobao.net.bean.invoice.AllInvoiceVariety;
import com.pilipa.fapiaobao.ui.fragment.UploadPreviewReceiptFragment;

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


    @Override
    public void onClick(View v) {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_upload_preview;
    }

    @Override
    public void initView() {
        amount = getIntent().getDoubleExtra("amount", 0);
        bonus = getIntent().getDoubleExtra("bonus", 0);
        receiptMoney.setText(amount + "");
        estimateMoney.setText(bonus + "");
    }

    @Override
    public void initData() {
        Bundle bundleExtra = getIntent().getBundleExtra(UploadReceiptActivity.TAG);
        final ArrayList<Parcelable> listPN = bundleExtra.getParcelableArrayList(UploadReceiptActivity.PAPER_NORMAL_RECEIPT_DATA);
        final ArrayList<Parcelable> listPS = bundleExtra.getParcelableArrayList(UploadReceiptActivity.PAPER_SPECIAL_RECEIPT_DATA);
        final ArrayList<Parcelable> listPE = bundleExtra.getParcelableArrayList(UploadReceiptActivity.PAPER_ELEC_RECEIPT_DATA);

        Api.findAllInvoiceVariety(new Api.BaseViewCallback<AllInvoiceVariety>() {
            @Override
            public void setData(AllInvoiceVariety allInvoiceVariety) {
                if (listPN != null) {
                    if (listPN.size() > 1) {
                        Bundle bundle = new Bundle();
                        bundle.putString("invoice_variety", allInvoiceVariety.getData().get(0).getValue());
                        bundle.putParcelableArrayList(PAPER_NORMAL_RECEIPT_DATA, listPN);
                        paperNormalReceiptFragment = UploadPreviewReceiptFragment.newInstance(bundle);
                        addCaptureFragment(R.id.container_paper_normal_receipt, paperNormalReceiptFragment);
                    }
                }

                if (listPS != null) {
                    if (listPS.size() > 1) {
                        Bundle bundle = new Bundle();
                        bundle.putString("invoice_variety", allInvoiceVariety.getData().get(1).getValue());
                        bundle.putParcelableArrayList(PAPER_SPECIAL_RECEIPT_DATA, listPS);
                        paperSpecialReceiptFragment = UploadPreviewReceiptFragment.newInstance(bundle);
                        addCaptureFragment(R.id.container_paper_special_receipt, paperSpecialReceiptFragment);
                    }
                }

                if (listPE != null) {
                    if (listPE.size() > 1) {
                        Bundle bundle = new Bundle();
                        bundle.putString("invoice_variety", allInvoiceVariety.getData().get(2).getValue());
                        bundle.putParcelableArrayList(PAPER_ELEC_RECEIPT_DATA, listPE);
                        paperElecReceiptFragment = UploadPreviewReceiptFragment.newInstance(bundle);
                        addCaptureFragment(R.id.container_paper_elec_receipt, paperElecReceiptFragment);
                    }
                }

                int count0 = 0;
                if (paperNormalReceiptFragment != null) {
                    Log.d(TAG, "initData: paperNormalReceiptFragment");
                    count0 = paperNormalReceiptFragment.getCurrentImageCount();
                }

                int count1 = 0;
                if (paperSpecialReceiptFragment != null) {
                    Log.d(TAG, "initData: paperSpecialReceiptFragment");
                    count1 = paperSpecialReceiptFragment.getCurrentImageCount();
                }

                int count2 = 0;
                if (paperElecReceiptFragment != null) {
                    Log.d(TAG, "initData: paperElecReceiptFragment");
                    count2 = paperElecReceiptFragment.getCurrentImageCount();
                }

                count = count0 + count1 + count2;
                receiptNumber.setText(count + "");


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
                if (paperElecReceiptFragment != null) {
                    paperElecReceiptFragment.uploadInvoice();

                }
                if (paperNormalReceiptFragment != null) {
                    paperNormalReceiptFragment.uploadInvoice();
                }

                if (paperSpecialReceiptFragment != null) {
                    paperSpecialReceiptFragment.uploadInvoice();
                }

                break;
        }
    }
}
