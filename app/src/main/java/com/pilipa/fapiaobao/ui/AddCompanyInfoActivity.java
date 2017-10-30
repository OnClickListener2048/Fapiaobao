package com.pilipa.fapiaobao.ui;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.pilipa.fapiaobao.R;
import com.pilipa.fapiaobao.base.BaseActivity;
import com.pilipa.fapiaobao.entity.Company;
import com.pilipa.fapiaobao.net.Api;
import com.pilipa.fapiaobao.net.bean.LoginWithInfoBean;
import com.pilipa.fapiaobao.net.bean.me.NormalBean;
import com.pilipa.fapiaobao.utils.SharedPreferencesHelper;

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
                Company company = new Company();
                company.setName("123");
                company.setTaxno("3121");
                company.setAddress("123");
                company.setPhone("213");
                company.setDepositBank("123");
                company.setAccount("131");
                createCompany(company);
            }break;
        }
    }

    @Override
    public void initView() {
    }

    @Override
    public void initData() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    private void createCompany(Company company){
        LoginWithInfoBean loginBean =  SharedPreferencesHelper.loadFormSource(this,LoginWithInfoBean.class);
        if(loginBean != null){
           String token = loginBean.getData().getToken();
            Api.companyCreate(company,token,new Api.BaseViewCallback<NormalBean>() {
                @Override
                public void setData(NormalBean loginWithInfoBean) {
                    Log.d(TAG, "createCompany;success");
                }
            });
        }

    }
}
