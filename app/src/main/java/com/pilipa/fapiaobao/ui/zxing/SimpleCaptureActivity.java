package com.pilipa.fapiaobao.ui.zxing;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;

import com.example.mylibrary.utils.TLog;
import com.pilipa.fapiaobao.R;
import com.pilipa.fapiaobao.base.BaseApplication;
import com.pilipa.fapiaobao.ui.constants.Constant;
import com.pilipa.fapiaobao.ui.dialog.LoadingDialog;
import com.pilipa.fapiaobao.utils.DialogUtil;

import io.github.xudaojie.qrcodelib.CaptureActivity;

/**
 * Created by edz on 2018/1/25.
 */

public class SimpleCaptureActivity extends CaptureActivity implements DialogInterface.OnKeyListener, DialogInterface.OnDismissListener {

    private LoadingDialog progressDialog;
    private Dialog mDialog;
    private TextView mTvContent;
    private boolean mIsJustify;
    private boolean mOnJustify;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mIsJustify = getIntent().getBooleanExtra(Constant.INVOICE_JUSTIFY, false);
        mOnJustify = getIntent().getBooleanExtra(Constant.ONLY_JUSTIFY, false);
        if (mIsJustify) {
            mQrTitle.setText(getString(R.string.qr_title_invoice_justify));
        }
        initDialog();
    }

    @Override
    protected void handleResult(String resultString) {
        super.handleResult(resultString);
        if (TextUtils.isEmpty(resultString)) {
            BaseApplication.showToast(R.string.qrScan_failed);
            restartPreview();
        } else {

            if (mOnJustify) {
                String[] strings = resultString.split(",");
                TLog.d("SimpleCaptureActivity", "strings.length" + strings.length);
                if (strings.length > 6) {
                    Intent intent = new Intent();
                    intent.putExtra(Constant.RESULTS, strings);
                    intent.setClass(this, ResultActivity.class);
                    startActivity(intent);
                } else {
                    mDialog.show();
                }
            } else {
                if (mIsJustify) {
                    String[] strings = resultString.split(",");
                    TLog.d("SimpleCaptureActivity", "strings.length" + strings.length);
                    if (strings.length > 6) {
                        Intent intent = new Intent();
                        intent.putExtra(Constant.RESULTS, strings);
                        intent.setClass(this, ResultActivity.class);
                        startActivity(intent);
                    } else {
                        Intent intent = getIntent();
                        intent.putExtra("codedContent", resultString);
                        intent.putExtra("codedBitmap", resultString);
                        setResult(RESULT_OK, intent);
                        finish();
                    }
                } else {
                    Intent intent = getIntent();
                    intent.putExtra("codedContent", resultString);
                    intent.putExtra("codedBitmap", resultString);
                    setResult(RESULT_OK, intent);
                    finish();
                }
            }
        }
    }

    private void initProgressDialog() {
        if (progressDialog == null) {
            progressDialog = new LoadingDialog(this, R.style.CustomDialog);
        }
        progressDialog.setOnKeyListener(this);
        progressDialog.setOnDismissListener(this);
    }

    private void hideProgressDialog() {
        if (progressDialog != null) {
            if (progressDialog.isShowing()) {
                progressDialog.dismiss();
            }
        }
    }

    public void showProgressDialog() {
        TLog.log("showProgressDialog" + getClass().getSimpleName());
        if (isFinishing()) return;
        if (progressDialog == null) return;
        if (progressDialog.isShowing()) return;
        progressDialog.show();
    }

    private void initDialog() {
        mDialog = DialogUtil.getInstance().createDialog(this, 0, R.layout.dialog_invoice_justify, new DialogUtil.OnKnownListener() {
            @Override
            public void onKnown(View view) {
                mDialog.dismiss();
                restartPreview();
            }
        }, null, null);
        mTvContent = (TextView) DialogUtil.getInstance().getRootView().findViewById(R.id.scan_tip);
    }

    protected void showDialog(Dialog dialog) {
        if (isFinishing()) return;
        dialog.show();
    }

    @Override
    public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
        return keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0;
    }

    @Override
    public void onDismiss(DialogInterface dialog) {

    }

}
