package com.pilipa.fapiaobao.ui;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.zxing.WriterException;
import com.pilipa.fapiaobao.R;
import com.pilipa.fapiaobao.base.BaseActivity;
import com.pilipa.fapiaobao.base.BaseApplication;
import com.pilipa.fapiaobao.net.bean.invoice.MacherBeanToken;
import com.pilipa.fapiaobao.net.bean.invoice.MatchBean;
import com.pilipa.fapiaobao.zxing.encode.CodeCreator;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by edz on 2017/10/24.
 */

public class ConfirmActivity extends BaseActivity {

    @Bind(R.id.title)
    TextView title;
    @Bind(R.id.confirm_back)
    ImageView confirmBack;
    @Bind(R.id.filter)
    TextView filter;
    @Bind(R.id.collect)
    ImageView collect;
    @Bind(R.id.qr)
    ImageView qr;
    @Bind(R.id.look_directly)
    TextView lookDirectly;
    @Bind(R.id.translate)
    LinearLayout translate;
    @Bind(R.id.company_name)
    TextView companyName;
    @Bind(R.id.tex_number)
    TextView texNumber;
    @Bind(R.id.company_address)
    TextView companyAddress;
    @Bind(R.id.number)
    TextView number;
    @Bind(R.id.bank)
    TextView bank;
    @Bind(R.id.bank_account)
    TextView bankAccount;
    @Bind(R.id.translate_details)
    LinearLayout translateDetails;
    @Bind(R.id.upload_receipt)
    Button uploadReceipt;
    private  MacherBeanToken.DataBean.CompanyBean company_info;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_confirm;
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void initView() {
        translate.setVisibility(View.VISIBLE);
        translateDetails.setVisibility(View.GONE);
    }

    @Override
    public void initData() {
        company_info = getIntent().getParcelableExtra("company_info");
        companyName.setText(company_info.getName());
        companyAddress.setText(company_info.getAddress());
        texNumber.setText(company_info.getTaxno());
        number.setText(company_info.getPhone());
        bank.setText(company_info.getDepositBank());
        bankAccount.setText(company_info.getAccount());


        try {
            Bitmap qrCode = CodeCreator.createQRCode(this,company_info.toString());
            qr.setImageBitmap(qrCode);
        } catch (Exception e) {
            BaseApplication.showToast("二维码生成失败");
            e.printStackTrace();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.confirm_back, R.id.filter, R.id.collect, R.id.qr, R.id.look_directly, R.id.translate, R.id.company_name, R.id.tex_number, R.id.company_address, R.id.number, R.id.bank, R.id.bank_account, R.id.translate_details, R.id.upload_receipt})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.confirm_back:
                this.finish();
                break;
            case R.id.filter:
                break;
            case R.id.collect:


                break;
            case R.id.qr:
                break;
            case R.id.look_directly:
                translate.setVisibility(View.GONE);
                translateDetails.setVisibility(View.VISIBLE);
                break;
            case R.id.translate:
                break;
            case R.id.company_name:
                break;
            case R.id.tex_number:
                break;
            case R.id.company_address:
                break;
            case R.id.number:
                break;
            case R.id.bank:
                break;
            case R.id.bank_account:
                break;
            case R.id.translate_details:
                break;
            case R.id.upload_receipt:
                startActivity(new Intent(this,UploadReceiptActivity.class));
                break;
        }
    }
}
