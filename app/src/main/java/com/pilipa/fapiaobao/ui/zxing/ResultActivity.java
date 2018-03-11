package com.pilipa.fapiaobao.ui.zxing;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.mylibrary.utils.TLog;
import com.lzy.okgo.exception.HttpException;
import com.lzy.okgo.model.Response;
import com.lzy.okgo.request.base.Request;
import com.pilipa.fapiaobao.R;
import com.pilipa.fapiaobao.base.BaseActivity;
import com.pilipa.fapiaobao.base.BaseApplication;
import com.pilipa.fapiaobao.net.Api;
import com.pilipa.fapiaobao.net.bean.me.Invoice_Verify;
import com.pilipa.fapiaobao.net.callback.JsonCallBack;
import com.pilipa.fapiaobao.ui.constants.Constant;
import com.pilipa.fapiaobao.ui.dialog.LoadingDialog;

import java.net.SocketTimeoutException;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by edz on 2018/2/9.
 */

public class ResultActivity extends BaseActivity {

    @Bind(R.id.title)
    TextView mTitle;
    @Bind(R.id.back)
    ImageView mBack;
    @Bind(R.id.rl_title)
    RelativeLayout mRlTitle;
    @Bind(R.id.iv_icon)
    ImageView mIvIcon;
    @Bind(R.id.tv_invoice_justify)
    TextView mTvInvoiceJustify;
    @Bind(R.id.invoice_code)
    TextView mInvoiceCode;
    @Bind(R.id.tv_invoice_code)
    TextView mTvInvoiceCode;
    @Bind(R.id.invoice_date)
    TextView mInvoiceDate;
    @Bind(R.id.tv_invoice_date)
    TextView mTvInvoiceDate;
    @Bind(R.id.invoice_name)
    TextView mInvoiceName;
    @Bind(R.id.tv_invoice_name)
    TextView mTvInvoiceName;
    @Bind(R.id.invoice_value)
    TextView mInvoiceValue;
    @Bind(R.id.tv_invoice_value)
    TextView mTvInvoiceValue;
    @Bind(R.id.ll_invoice_info)
    LinearLayout mLlInvoiceInfo;
    @Bind(R.id.btn_scan_next)
    Button mBtnScanNext;
    @Bind(R.id.tv_invoice_content)
    TextView mTvInvoiceContent;
    private Invoice_Verify.DataBean mData;
    private LoadingDialog progressDialog;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_capture_result;
    }

    @Override
    public void initData() {
        super.initData();
        Intent intent = getIntent();
        if (intent != null) {
            TLog.log("intent != null");
            String[] stringArrayExtra = intent.getStringArrayExtra(Constant.RESULTS);
            String invoice = stringArrayExtra[1];
            String code = stringArrayExtra[2];
            String value = stringArrayExtra[4];
            String number = stringArrayExtra[3];
            String date = stringArrayExtra[5];
            String verification = stringArrayExtra[6];
            if (TextUtils.equals("01", invoice)) {
                TLog.log("TextUtils.equals(\"01\",invoice");
                //专票
                verify(code, number, date, value);
            }

            if (TextUtils.equals("04", invoice) || TextUtils.equals("10", invoice)) {
                TLog.log("TextUtils.equals(\"04,\",invoice");
                //普票
                String substring = verification.substring(14);
                verify(code, number, date, substring);
            }
        }
    }

    @Override
    public void initView() {
        super.initView();
        initProgressDialog();
    }

    private void initProgressDialog() {
        if (progressDialog == null) {
            progressDialog = new LoadingDialog(this, R.style.CustomDialog, true);
        }
        progressDialog.setOnKeyListener(this);
        progressDialog.setOnDismissListener(this);
    }

    private void hideProgressDialog2() {
        if (progressDialog != null) {
            if (progressDialog.isShowing()) {
                progressDialog.dismiss();
            }
        }
    }


    public void showProgressDialog2() {
        TLog.log("showProgressDialog" + getClass().getSimpleName());
        if (isFinishing()) return;
        if (progressDialog == null) return;
        if (progressDialog.isShowing()) return;
        progressDialog.show();
    }


    private void verify(String code, String number, String date, String money) {
        Api.invoice_verification(code, number, date, money, this, new JsonCallBack<Invoice_Verify>(Invoice_Verify.class) {
            @Override
            public void onSuccess(Response<Invoice_Verify> response) {
                Invoice_Verify body = response.body();
                if (body != null) {
                    consume(body);
                }
            }

            @Override
            public void onStart(Request<Invoice_Verify, ? extends Request> request) {
                super.onStart(request);
                showProgressDialog2();
            }

            @Override
            public void onFinish() {
                super.onFinish();
                hideProgressDialog2();
            }

            @Override
            public void onError(Response<Invoice_Verify> response) {
                super.onError(response);
                Throwable exception = response.getException();
                if (exception instanceof SocketTimeoutException || exception instanceof HttpException) {
                    mIvIcon.setImageResource(R.mipmap.justify_failed);
                    mLlInvoiceInfo.setVisibility(View.GONE);
                    mTvInvoiceContent.setText("发票信息上传失败,请重试~");
                    mBtnScanNext.setText("重新扫描");
                    mTvInvoiceJustify.setText("验证失败");
                }
            }
        });
    }

    private void consume(Invoice_Verify body) {
        String status = body.getStatus();
        switch (status) {
            case "0":
                mData = body.getData();
                if (mData == null) return;

                String errorId = mData.getErrorId();
                if (errorId != null) {
                    switch (errorId) {
                        case "002":
                            mIvIcon.setImageResource(R.mipmap.justify_failed);
                            mLlInvoiceInfo.setVisibility(View.GONE);
                            mTvInvoiceContent.setText("此票验证已超过当天查验次数请明天再查哦~");

                            mBtnScanNext.setText("重新扫描");
                            mTvInvoiceJustify.setText("验证失败");
                            break;
                        case "003":
                        case "004":
                            mIvIcon.setImageResource(R.mipmap.justify_failed);
                            mLlInvoiceInfo.setVisibility(View.GONE);
                            mTvInvoiceContent.setText("您的查验太频繁,请稍后再试哦~");
                            mBtnScanNext.setText("重新扫描");
                            mTvInvoiceJustify.setText("验证失败");
                            break;
                        case "rgerr":
                            mIvIcon.setImageResource(R.mipmap.justify_failed);
                            mLlInvoiceInfo.setVisibility(View.GONE);
                            mTvInvoiceContent.setText("当日开具的发票请于次日进行查验哦~");
                            mBtnScanNext.setText("重新扫描");
                            mTvInvoiceJustify.setText("验证失败");
                            break;
                        case "005":
                            mIvIcon.setImageResource(R.mipmap.justify_failed);
                            mLlInvoiceInfo.setVisibility(View.GONE);
                            mTvInvoiceContent.setText("发票信息上传失败,请重试~");
                            mBtnScanNext.setText("重新扫描");
                            mTvInvoiceJustify.setText("验证失败");
                            break;
                        case "006":
                        case "009":
                            mIvIcon.setImageResource(R.mipmap.justify_fake);
                            mLlInvoiceInfo.setVisibility(View.GONE);
                            mTvInvoiceContent.setVisibility(View.GONE);
                            mTvInvoiceJustify.setText("疑似假票");
                            mBtnScanNext.setText("继续扫描");
                            break;
                        default:
                    }
                } else {
                    mIvIcon.setImageResource(R.mipmap.justify_true);
                    mLlInvoiceInfo.setVisibility(View.VISIBLE);
                    mTvInvoiceContent.setVisibility(View.GONE);
                    mTvInvoiceJustify.setText("真票");
                    mBtnScanNext.setText("继续扫描");
                    mTvInvoiceCode.setText(mData.get发票代码());
                    mTvInvoiceDate.setText(mData.get开票日期());
                    mTvInvoiceName.setText(mData.get购买方名称());
                    mTvInvoiceValue.setText(String.valueOf(mData.get价税合计()));
                }
                break;

            case "-1":
                BaseApplication.showToast(body.getMsg());
                mIvIcon.setImageResource(R.mipmap.justify_failed);
                mLlInvoiceInfo.setVisibility(View.GONE);
                mTvInvoiceContent.setText("发票信息上传失败,请重试~");
                mBtnScanNext.setText("重新扫描");
                mTvInvoiceJustify.setText("验证失败");
                break;
            default:
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.back, R.id.btn_scan_next})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back:
            case R.id.btn_scan_next:
                finish();
                break;
            default:
        }
    }
}
