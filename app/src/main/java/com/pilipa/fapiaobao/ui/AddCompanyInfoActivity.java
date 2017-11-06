package com.pilipa.fapiaobao.ui;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.pilipa.fapiaobao.R;
import com.pilipa.fapiaobao.account.AccountHelper;
import com.pilipa.fapiaobao.base.BaseActivity;
import com.pilipa.fapiaobao.base.BaseApplication;
import com.pilipa.fapiaobao.entity.Company;
import com.pilipa.fapiaobao.net.Api;
import com.pilipa.fapiaobao.net.bean.LoginWithInfoBean;
import com.pilipa.fapiaobao.net.bean.me.NormalBean;

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

    @Override
    protected int getLayoutId() {
        return R.layout.activity_company_info_add;
    }

    @OnClick({R.id.add_back,R.id.btn_save})
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.add_back:{
                finish();
            }break;
            case R.id.btn_save:{
                addCompany();
            }break;
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
