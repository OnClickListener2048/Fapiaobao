package com.pilipa.fapiaobao.ui;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;
import com.pilipa.fapiaobao.R;
import com.pilipa.fapiaobao.account.AccountHelper;
import com.pilipa.fapiaobao.base.BaseActivity;
import com.pilipa.fapiaobao.base.BaseApplication;
import com.pilipa.fapiaobao.entity.Company;
import com.pilipa.fapiaobao.net.Api;
import com.pilipa.fapiaobao.net.bean.LoginWithInfoBean;
import com.pilipa.fapiaobao.net.bean.me.CompanyDetailsBean;
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
    private static final int REQUEST_CODE_SCAN = 0x0000;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_company_info_add;
    }

    @OnClick({R.id.add_back,R.id.btn_save,R.id.img_scan})
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.img_scan:{
                startActivityForResult(new Intent(this, CaptureActivity.class), REQUEST_CODE_SCAN);
            }break;
            case R.id.add_back:{
                finish();
            }break;
            case R.id.btn_save:{
                addCompany();
            }break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == REQUEST_CODE_SCAN){
           String codedContent=  data.getStringExtra("codedContent");
            Log.d("codedContent",codedContent);
            if(codedContent != null){
                Gson gson = new Gson();
                CompanyDetailsBean.DataBean bean = gson.fromJson(codedContent, CompanyDetailsBean.DataBean.class);
                edtCompany_name.setText(bean.getName());
                edtTaxno.setText(bean.getTaxno());
                edtCompanyAddress.setText(bean.getAddress());
                edtCompanyNumber.setText(bean.getPhone());
                edtBankName.setText(bean.getDepositBank());
                edtBankAccount.setText(bean.getAccount());
            }
        }
    }

    @Override
    public void initView() {
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
            company.setTaxno(edtTaxno.getText().toString().trim());
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
