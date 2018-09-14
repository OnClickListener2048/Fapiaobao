package com.pilipa.fapiaobao.ui;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.target.Target;
import com.example.mylibrary.utils.ActivityUtils;
import com.jakewharton.rxbinding2.view.RxView;
import com.pilipa.fapiaobao.R;
import com.pilipa.fapiaobao.account.AccountHelper;
import com.pilipa.fapiaobao.base.BaseActivity;
import com.pilipa.fapiaobao.base.BaseApplication;
import com.pilipa.fapiaobao.net.Api;
import com.pilipa.fapiaobao.net.bean.me.NormalBean;
import com.pilipa.fapiaobao.ui.constants.Constant;
import com.pilipa.fapiaobao.ui.fragment.FinanceFragment;
import com.pilipa.fapiaobao.ui.model.Image;
import com.pilipa.fapiaobao.ui.receipt_folder_image_select.ReceiptActivityToken;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;

/**
 * Created by edz on 2017/12/15.
 */

public class PdfPreviewActivity extends BaseActivity {
    private static final String TAG = PdfPreviewActivity.class.getSimpleName();
    @Bind(R.id.pdfView)
    ImageView pdfView;
    @Bind(R.id.save_to_invoice_clip)
    Button saveToInvoiceClip;
    @Bind(R.id._back)
    ImageView Back;
    @Bind(R.id.relativeLayout)
    RelativeLayout relativeLayout;
    private String tag;
    private boolean isFromUploadReceiptActivity;
    private String pdfUrl;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_pdf_preview;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
    }


    @Override
    public void initView() {
        super.initView();
        isFromUploadReceiptActivity = getIntent().getBooleanExtra(Constant.IS_FROM_UPLOADRECEIPT_ACTIVITY, false);
        pdfUrl = getIntent().getStringExtra(Constant.PDF_EXTRA);
        tag = getIntent().getStringExtra(Constant.TAG);
        if (ActivityUtils.isActivityExistsInStack(UploadReceiptActivity.class)) {
            isFromUploadReceiptActivity = true;
        } else {
            isFromUploadReceiptActivity = false;
        }
        saveToInvoiceClip.setText(isFromUploadReceiptActivity ? getString(R.string.upload_receipt) : getString(R.string.save_to_invoice_clip));
        Uri uri = getIntent().getData();
        if (uri != null) {
            String data = uri.getPath();
            String host = uri.getHost();
            if (data != null && host != null) {
                transformPdf("https://" + host + data);
            } else {
                startNormal();
            }
        } else {
            startNormal();
        }
    }

    private void transformPdf(String url) {
        Api.transform_pdf(url, new Api.BaseViewCallbackWithOnStart<NormalBean>() {
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

            }

            @Override
            public void setData(NormalBean normalBean) {
                if (normalBean.getStatus() == com.pilipa.fapiaobao.net.Constant.REQUEST_SUCCESS) {
                    if (!ActivityUtils.isActivityExistsInStack(UploadReceiptActivity.class)) {
                        tag = FinanceFragment.TAG;
                    }

                    pdfUrl = normalBean.getData();
                    startNormal();
                }
            }
        });
    }

    private void startNormal() {
        loadPdfImage(pdfUrl);
        listenViews(pdfUrl, isFromUploadReceiptActivity);
    }

    private void loadPdfImage(String pdfUrl) {
        Glide.with(this)
                .load(pdfUrl)
                .asBitmap()
                .error(R.mipmap.error_big)
                .placeholder(R.mipmap.loading_big)
                .override(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL)
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .skipMemoryCache(true)
                .dontTransform()
                .into(pdfView);
    }

    private void listenViews(final String pdfUrl, final boolean isFromUploadReceiptActivity) {
        RxView.clicks(saveToInvoiceClip)
                .throttleFirst(2000, TimeUnit.MILLISECONDS)
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Object>() {
                    @Override
                    public void accept(@NonNull Object o) throws Exception {
//                        if (isFromUploadReceiptActivity) sendToUpload(pdfUrl);
//                        else uploadPdf(pdfUrl);
                        if (isFromUploadReceiptActivity) {
                            sendToUpload(pdfUrl);
                        } else {
                            uploadPdf(pdfUrl);
                        }
                    }
                });
    }

    private void sendToUpload(String pdfUrl) {
        ArrayList<Image> singlePdf = new ArrayList<>();
        Image pdf = new Image();
        pdf.isFromNet = true;
        pdf.isSelected = false;
        pdf.isCapture = false;
        pdf.name = String.valueOf(System.currentTimeMillis());
        pdf.path = pdfUrl;
        pdf.position = -1;
        pdf.setPdf(true);
        pdf.id = String.valueOf(System.currentTimeMillis());
        singlePdf.add(pdf);
        Intent intent = new Intent();
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList(ReceiptActivityToken.RESULT_RECEIPT_FOLDER, singlePdf);
        intent.putExtra(ReceiptActivityToken.EXTRA_DATA_FROM_TOKEN, bundle);
        intent.setClass(this, UploadReceiptActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);

        ActivityUtils.finishActivity(Op.class);
        finish();
    }




    @Override
    public void initData() {
        super.initData();
    }

    @OnClick({R.id._back})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id._back:
                finish();
                break;
            default:
        }
    }

    private void uploadPdf(final String pdfUrl) {
        Api.upload_pdf(pdfUrl, AccountHelper.getToken(), new Api.BaseRawResponse<NormalBean>() {
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

            }

            @Override
            public void onTokenInvalid() {
                login();
            }

            @Override
            public void setData(NormalBean normalBean) {
                BaseApplication.showToast(normalBean.getData());
                if (normalBean.getStatus() == com.pilipa.fapiaobao.net.Constant.REQUEST_SUCCESS) {
                    if(ReceiptFolderActivity.class.getSimpleName().equals(tag)){
                        Intent intent = new Intent();
                        Image pdf = new Image();
                        pdf.isFromNet = true;
                        pdf.isSelected = false;
                        pdf.isCapture = false;
                        pdf.name = String.valueOf(System.currentTimeMillis());
                        pdf.path = pdfUrl;
                        pdf.position = -1;
                        pdf.setPdf(true);
                        pdf.id = String.valueOf(System.currentTimeMillis());
                        intent.setAction("UPLOAD_PDF_SUCCESS");
                        Bundle bundle = new Bundle();
                        bundle.putParcelable("pdf",pdf);
                        intent.putExtra("bundle",bundle);
                        sendBroadcast(intent);
                    }
                    if (FinanceFragment.TAG.equals(tag)) {
                        Intent intent = new Intent();
                        intent.setClass(PdfPreviewActivity.this, ReceiptFolderActivity.class);
                        intent.putExtra(Constant.CHOOSE_RECEIPT_FOLDER,true);
                        startActivity(intent);
                    }
                    ActivityUtils.finishActivity(Op.class);
                    finish();
                }
            }
        });
    }
}
