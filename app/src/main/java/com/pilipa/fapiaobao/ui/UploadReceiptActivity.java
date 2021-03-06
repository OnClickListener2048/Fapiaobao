package com.pilipa.fapiaobao.ui;

import android.Manifest;
import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.mylibrary.utils.RegexUtils;
import com.example.mylibrary.utils.TLog;
import com.pilipa.fapiaobao.R;
import com.pilipa.fapiaobao.base.BaseActivity;
import com.pilipa.fapiaobao.base.BaseApplication;
import com.pilipa.fapiaobao.net.bean.invoice.MacherBeanToken;
import com.pilipa.fapiaobao.ui.constants.Constant;
import com.pilipa.fapiaobao.ui.fragment.FinanceFragment;
import com.pilipa.fapiaobao.ui.fragment.UploadNormalReceiptFragment;
import com.pilipa.fapiaobao.ui.model.Image;
import com.pilipa.fapiaobao.ui.zxing.SimpleCaptureActivity;
import com.pilipa.fapiaobao.utils.DialogUtil;
import com.tbruyelle.rxpermissions2.RxPermissions;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;

/**
 * Created by edz on 2017/10/20.
 */

public class UploadReceiptActivity extends BaseActivity {
    public static final String TAG = UploadReceiptActivity.class.getSimpleName();
    public static final String PAPER_NORMAL_RECEIPT_DATA = "paper_normal_receipt_data";
    public static final String PAPER_SPECIAL_RECEIPT_DATA = "paper_special_receipt_data";
    public static final String PAPER_ELEC_RECEIPT_DATA = "paper_elec_receipt_data";
    public static final String IS_ELEC_RECEIPT_DATA = "is_elec_receipt_data";
    private static final String DECODED_CONTENT_KEY = "codedContent";
    private static final String DECODED_BITMAP_KEY = "codedBitmap";
    private static final int REQUEST_CODE_SCAN = 0x0002;
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
    @Bind(R.id.ll_container_paper_normal_receipt)
    LinearLayout llContainerPaperNormalReceipt;
    @Bind(R.id.ll_container_paper_special_receipt)
    LinearLayout llContainerPaperSpecialReceipt;
    @Bind(R.id.ll_container_paper_elec_receipt)
    LinearLayout llContainerPaperElecReceipt;
    private UploadNormalReceiptFragment paperNormalReceiptFragment;
    private UploadNormalReceiptFragment paperSpecialReceiptFragment;
    private UploadNormalReceiptFragment paperElecReceiptFragment;
    private double amount;
    private double bonus;
    private String demandsId;
    private String label;
    private String company_id;
    private int type;
    private Dialog scanDialog;

    private  MacherBeanToken.DataBean.CompanyBean company_info;
    private Dialog mDialog;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_upload_receipt;
    }


    @Override
    public void initView() {
        type = getIntent().getIntExtra("type", 0);
        company_id = getIntent().getStringExtra("company_id");
        label = getIntent().getStringExtra(FinanceFragment.EXTRA_DATA_LABEL);
        amount = getIntent().getDoubleExtra("amount", 0);
        bonus = getIntent().getDoubleExtra("bonus", 0);
        company_info = getIntent().getParcelableExtra(Constant.COMPANY_INFO);
        TLog.log("company_info== null?"+company_info);
        demandsId = getIntent().getStringExtra("demandsId");
        paperNormalReceiptFragment = UploadNormalReceiptFragment.newInstance(new Bundle());
        addCaptureFragment(R.id.container_paper_normal_receipt, paperNormalReceiptFragment);
        paperSpecialReceiptFragment = UploadNormalReceiptFragment.newInstance(new Bundle());
        addCaptureFragment(R.id.container_paper_special_receipt, paperSpecialReceiptFragment);
        Bundle bundle = new Bundle();
        bundle.putBoolean(IS_ELEC_RECEIPT_DATA, true);
        paperElecReceiptFragment = UploadNormalReceiptFragment.newInstance(bundle);
        addCaptureFragment(R.id.container_paper_elec_receipt, paperElecReceiptFragment);


        resetLayout();
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        TLog.d(TAG, "onNewIntent");
        paperElecReceiptFragment.resultFromElec(intent);
    }

    private void resetLayout() {
        if (type != 0) {
            if (type == 1) {
                llContainerPaperNormalReceipt.setVisibility(View.VISIBLE);
            } else {
                llContainerPaperNormalReceipt.setVisibility(View.GONE);
            }

            if (type == 2) {
                llContainerPaperSpecialReceipt.setVisibility(View.VISIBLE);
            } else {
                llContainerPaperSpecialReceipt.setVisibility(View.GONE);
            }

            if (type == 3) {
                llContainerPaperElecReceipt.setVisibility(View.VISIBLE);
            } else {
                llContainerPaperElecReceipt.setVisibility(View.GONE);
            }
        }
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

                break;
        }
    }

    public void scan() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            RxPermissions rxPermissions = new RxPermissions(this);
            rxPermissions.request(Manifest.permission.CAMERA
                    ,Manifest.permission.WRITE_EXTERNAL_STORAGE
   )
                    .subscribe(new Observer<Boolean>() {
                        @Override
                        public void onSubscribe(@NonNull Disposable d) {

                        }

                        @Override
                        public void onNext(@NonNull Boolean aBoolean) {
                            if (aBoolean) {
                                Intent intent = new Intent(UploadReceiptActivity.this, SimpleCaptureActivity.class);
                                startActivityForResult(intent, REQUEST_CODE_SCAN);
                            }
                        }

                        @Override
                        public void onError(@NonNull Throwable e) {

                        }

                        @Override
                        public void onComplete() {

                        }
                    });
        } else {

            Intent intent = new Intent(this, SimpleCaptureActivity.class);
        startActivityForResult(intent, REQUEST_CODE_SCAN);
        }
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case REQUEST_CODE_SCAN:
                if (resultCode == Activity.RESULT_OK) {
                    String content = data.getStringExtra(DECODED_CONTENT_KEY);
                    TLog.log(content);
                    if (RegexUtils.isURL(content)||content.contains("http")) {
                        Intent intent = new Intent();
                        intent.putExtra(Constant.COMPANY_INFO, company_info);
                        intent.putExtra(Constant.IS_FROM_UPLOADRECEIPT_ACTIVITY, true);
                        intent.setClass(this, Op.class);
                        intent.putExtra("url", content);
                        startActivity(intent);
                    }else{
                        showDialog();
                    }
                }
                break;
            default:
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
        intent.putExtra("type", type);
        intent.putExtra("amount", amount);
        intent.putExtra("bonus", bonus);
        intent.putExtra("demandsId", demandsId);
        intent.putExtra("company_id", company_id);
        Bundle bundle = new Bundle();
        ArrayList<Image> currentImagesPN = paperNormalReceiptFragment.getCurrentImages();
        ArrayList<Image> currentImagesPS = paperSpecialReceiptFragment.getCurrentImages();
        ArrayList<Image> currentImagesPE = paperElecReceiptFragment.getCurrentImages();
        if (currentImagesPE.size() <= 1 && currentImagesPN.size() <= 1 && currentImagesPS.size() <= 1) {
            BaseApplication.showToast(getString(R.string.please_upload_invoice_atleast_one));
            return;

        }
        bundle.putParcelableArrayList(PAPER_NORMAL_RECEIPT_DATA, currentImagesPN);
        bundle.putParcelableArrayList(PAPER_SPECIAL_RECEIPT_DATA, currentImagesPS);
        bundle.putParcelableArrayList(PAPER_ELEC_RECEIPT_DATA, currentImagesPE);
        intent.putExtra(TAG, bundle);
        intent.putExtra(FinanceFragment.EXTRA_DATA_LABEL, label);
        startActivity(intent);
    }

    private void showDialog() {
        if (mDialog == null) {
            mDialog = DialogUtil.getInstance().createDialog(this, 0, R.layout.layout_scan_tip, new DialogUtil.OnKnownListener() {
                @Override
                public void onKnown(View view) {
                    mDialog.dismiss();
                }
            }, null, null);
        }
        showDialog(mDialog);
    }

//    public void setScanDialog() {
//        scanDialog = new Dialog(this, R.style.BottomDialog);
//        LinearLayout root = (LinearLayout) LayoutInflater.from(this).inflate(
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
}
