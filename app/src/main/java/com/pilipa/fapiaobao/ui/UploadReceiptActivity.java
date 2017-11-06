package com.pilipa.fapiaobao.ui;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.pilipa.fapiaobao.R;
import com.pilipa.fapiaobao.base.BaseActivity;
import com.pilipa.fapiaobao.base.BaseApplication;
import com.pilipa.fapiaobao.ui.fragment.FinanceFragment;
import com.pilipa.fapiaobao.ui.fragment.UploadNormalReceiptFragment;
import com.pilipa.fapiaobao.ui.model.Image;
import com.pilipa.fapiaobao.zxing.android.CaptureActivity;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by edz on 2017/10/20.
 */

public class UploadReceiptActivity extends BaseActivity {
    public static final String TAG = UploadReceiptActivity.class.getSimpleName();
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
    @Bind(R.id.upload_receipt)
    Button uploadReceipt;
    private UploadNormalReceiptFragment paperNormalReceiptFragment;
    private UploadNormalReceiptFragment paperSpecialReceiptFragment;
    private UploadNormalReceiptFragment paperElecReceiptFragment;
    private static final String DECODED_CONTENT_KEY = "codedContent";
    private static final String DECODED_BITMAP_KEY = "codedBitmap";
    private static final int REQUEST_CODE_SCAN = 0x0002;

    public static final String PAPER_NORMAL_RECEIPT_DATA = "paper_normal_receipt_data";
    public static final String PAPER_SPECIAL_RECEIPT_DATA = "paper_special_receipt_data";
    public static final String PAPER_ELEC_RECEIPT_DATA = "paper_elec_receipt_data";
    public static final String IS_ELEC_RECEIPT_DATA = "is_elec_receipt_data" ;
    private double amount;
    private double bonus;
    private String demandsId;
    private String label;
    private String company_id;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_upload_receipt;
    }


    @Override
    public void initView() {
        company_id = getIntent().getStringExtra("company_id");
        label = getIntent().getStringExtra(FinanceFragment.EXTRA_DATA_LABEL);
        amount = getIntent().getDoubleExtra("amount",0);
        bonus = getIntent().getDoubleExtra("bonus",0);
        demandsId = getIntent().getStringExtra("demandsId");
        paperNormalReceiptFragment = UploadNormalReceiptFragment.newInstance(new Bundle());
        addCaptureFragment(R.id.container_paper_normal_receipt, paperNormalReceiptFragment);
        paperSpecialReceiptFragment = UploadNormalReceiptFragment.newInstance(new Bundle());
        addCaptureFragment(R.id.container_paper_special_receipt, paperSpecialReceiptFragment);

        Bundle bundle = new Bundle();
        bundle.putBoolean(IS_ELEC_RECEIPT_DATA,true);
        paperElecReceiptFragment = UploadNormalReceiptFragment.newInstance(bundle);
        addCaptureFragment(R.id.container_paper_elec_receipt, paperElecReceiptFragment);
    }

    @Override
    public void initData() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.upload_back, R.id.upload_scan})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.upload_back:
                finish();
                break;
            case R.id.upload_scan:
                startActivityForResult(new Intent(this, CaptureActivity.class), REQUEST_CODE_SCAN);
                break;
        }
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
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
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

    }

    @OnClick(R.id.upload_receipt)
    public void onViewClicked() {
        Intent intent = new Intent(this, UploadReceiptPreviewActivity.class);
        intent.putExtra(FinanceFragment.EXTRA_DATA_LABEL, label);
        intent.putExtra("amount", amount);
        intent.putExtra("bonus", bonus);
        intent.putExtra("demandsId", demandsId);
        intent.putExtra("company_id", company_id);
        Bundle bundle = new Bundle();
        ArrayList<Image> currentImagesPN = paperNormalReceiptFragment.getCurrentImages();
        ArrayList<Image> currentImagesPS = paperSpecialReceiptFragment.getCurrentImages();
        ArrayList<Image> currentImagesPE = paperElecReceiptFragment.getCurrentImages();
        if (currentImagesPE.size() <= 1 && currentImagesPN.size() <= 1 && currentImagesPS.size() <= 1) {
            BaseApplication.showToast("请上传发票");
            return;

        }
        bundle.putParcelableArrayList(PAPER_NORMAL_RECEIPT_DATA, currentImagesPN);
        bundle.putParcelableArrayList(PAPER_SPECIAL_RECEIPT_DATA, currentImagesPS);
        bundle.putParcelableArrayList(PAPER_ELEC_RECEIPT_DATA, currentImagesPE);
        intent.putExtra(TAG, bundle);
        intent.putExtra(FinanceFragment.EXTRA_DATA_LABEL, label);
        startActivity(intent);
    }
}
