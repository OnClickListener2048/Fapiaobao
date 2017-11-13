package com.pilipa.fapiaobao.ui;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.method.ReplacementTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mylibrary.utils.EncodeUtils;
import com.google.gson.Gson;
import com.pilipa.fapiaobao.R;
import com.pilipa.fapiaobao.account.AccountHelper;
import com.pilipa.fapiaobao.base.BaseActivity;
import com.pilipa.fapiaobao.base.BaseApplication;
import com.pilipa.fapiaobao.entity.Company;
import com.pilipa.fapiaobao.net.Api;
import com.pilipa.fapiaobao.net.bean.LoginWithInfoBean;
import com.pilipa.fapiaobao.net.bean.invoice.MacherBeanToken;
import com.pilipa.fapiaobao.net.bean.me.NormalBean;
import com.pilipa.fapiaobao.zxing.android.CaptureActivity;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by wjn on 2017/10/23.
 */

public class AddCompanyInfoActivity extends BaseActivity {
    private static final String TAG = "AddCompanyInfoActivity";

    @Bind(R.id.edt_company_name)
    EditText edtCompany_name;
    @Bind(R.id.edt_taxno)
    EditText edtTaxno;
    @Bind(R.id.edt_company_address)
    EditText edtCompanyAddress;
    @Bind(R.id.edt_company_number)
    EditText edtCompanyNumber;
    @Bind(R.id.edt_bank_name)
    EditText edtBankName;
    @Bind(R.id.edt_bank_account)
    EditText edtBankAccount;
    private static final int REQUEST_CODE_SCAN = 0x0033;
    private static final String DECODED_CONTENT_KEY = "codedContent";
    private static final String DECODED_BITMAP_KEY = "codedBitmap";
    @Bind(R.id.tv_mustfill_company_name)
    TextView tvMustfillCompanyName;
    @Bind(R.id.tv_mustfill_texno)
    TextView tvMustfillTexno;
    @Bind(R.id.tv_mustfill_address)
    TextView tvMustfillAddress;
    @Bind(R.id.tv_mustfill_company_phone)
    TextView tvMustfillCompanyPhone;
    @Bind(R.id.tv_mustfill_bank_name)
    TextView tvMustfillBankName;
    @Bind(R.id.tv_mustfill_bank_number)
    TextView tvMustfillBankNumber;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_company_info_add;
    }

    @OnClick({R.id.add_back, R.id.btn_save, R.id.img_scan})
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.img_scan: {
                startActivityForResult(new Intent(this, CaptureActivity.class), REQUEST_CODE_SCAN);
            }
            break;
            case R.id.add_back: {
                finish();
            }
            break;
            case R.id.btn_save: {
                addCompany();
            }
            break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case REQUEST_CODE_SCAN:
                if (resultCode == RESULT_OK) {
                    String codedContent = data.getStringExtra(DECODED_CONTENT_KEY);
                    Log.d("codedContent", codedContent);
                    Gson gson = new Gson();
                    String decode = EncodeUtils.urlDecode(codedContent);
                    MacherBeanToken.DataBean.CompanyBean companyBean = gson.fromJson(decode, MacherBeanToken.DataBean.CompanyBean.class);
                    edtCompany_name.setText(companyBean.getName());
                    edtTaxno.setText(companyBean.getTaxno().toUpperCase());
                    edtCompanyAddress.setText(companyBean.getAddress());
                    edtCompanyNumber.setText(companyBean.getPhone());
                    edtBankName.setText(companyBean.getDepositBank());
                    edtBankAccount.setText(companyBean.getAccount());
                }
                break;
        }
    }

    @Override
    public void initView() {
        edtTaxno.setTransformationMethod(new ReplacementTransformationMethod() {
            @Override
            protected char[] getOriginal() {
                char[] originalCharArr = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
                return originalCharArr;
            }

            @Override
            protected char[] getReplacement() {
                char[] replacementCharArr = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};
                return replacementCharArr;
            }
        });


        tvMustfillTexno.setVisibility(View.GONE);
        tvMustfillAddress.setVisibility(View.GONE);
        tvMustfillBankName.setVisibility(View.GONE);
        tvMustfillBankNumber.setVisibility(View.GONE);
        tvMustfillCompanyName.setVisibility(View.GONE);
        tvMustfillCompanyPhone.setVisibility(View.GONE);

        boolean isFromPublish = getIntent().getBooleanExtra("isPublish", false);
//        boolean elec = getIntent().getBooleanExtra(DemandsPublishActivity.RECEIPTELEC_DATA, false);
//        boolean paperNormal = getIntent().getBooleanExtra(DemandsPublishActivity.RECEIPTPAPERNORMAL_DATA, false);
        boolean paperSpecial = getIntent().getBooleanExtra(DemandsPublishActivity.RECEIPTPAPERSPECIAL_DATA, false);

        if (isFromPublish) {
            tvMustfillTexno.setVisibility(View.VISIBLE);
            tvMustfillAddress.setVisibility(View.VISIBLE);
            tvMustfillBankName.setVisibility(View.VISIBLE);
            tvMustfillBankNumber.setVisibility(View.VISIBLE);
            tvMustfillCompanyName.setVisibility(View.VISIBLE);
            tvMustfillCompanyPhone.setVisibility(View.VISIBLE);

            if (paperSpecial) {
                tvMustfillTexno.setText("必填");
                tvMustfillAddress.setText("必填");
                tvMustfillBankName.setText("必填");
                tvMustfillBankNumber.setText("必填");
                tvMustfillCompanyName.setText("必填");
                tvMustfillCompanyPhone.setText("必填");
            } else {
                tvMustfillTexno.setText("必填");
                tvMustfillAddress.setText("选填");
                tvMustfillBankName.setText("选填");
                tvMustfillBankNumber.setText("选填");
                tvMustfillCompanyName.setText("必填");
                tvMustfillCompanyPhone.setText("选填");
            }
        }

    }

    @Override
    public void initData() {

    }

    public void addCompany() {
        if (TextUtils.isEmpty(edtCompany_name.getText())) {
            BaseApplication.showToast("请输入单位名称");
            return;
        }
        if (TextUtils.isEmpty(edtTaxno.getText())) {
            BaseApplication.showToast("请输入税号");
            return;
        }

        Company company = new Company();
        company.setName(edtCompany_name.getText().toString().trim());
        company.setTaxno(edtTaxno.getText().toString().trim().toUpperCase());
        company.setAddress(edtCompanyAddress.getText().toString().trim());
        company.setPhone(edtCompanyNumber.getText().toString().trim());
        company.setDepositBank(edtBankName.getText().toString().trim());
        company.setAccount(edtBankAccount.getText().toString().trim());
        createCompany(company);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    private void createCompany(final Company company) {
        AccountHelper.isTokenValid(new Api.BaseViewCallback<LoginWithInfoBean>() {
            @Override
            public void setData(LoginWithInfoBean loginWithInfoBean) {
                if (loginWithInfoBean.getStatus() == 200) {
                    Api.companyCreate(company, AccountHelper.getToken(), new Api.BaseViewCallback<NormalBean>() {
                        @Override
                        public void setData(NormalBean normalBean) {
                            if (normalBean.getStatus() == 200) {
                                Toast.makeText(AddCompanyInfoActivity.this, "添加成功", Toast.LENGTH_SHORT).show();
                                setResult(RESULT_OK);
                                AddCompanyInfoActivity.this.finish();
                                Log.d(TAG, "createCompany;success");
                            }
                        }
                    });
                }
            }
        });
    }
}
