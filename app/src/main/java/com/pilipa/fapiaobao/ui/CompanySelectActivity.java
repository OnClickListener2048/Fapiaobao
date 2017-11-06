package com.pilipa.fapiaobao.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.pilipa.fapiaobao.R;
import com.pilipa.fapiaobao.account.AccountHelper;
import com.pilipa.fapiaobao.adapter.CompanyListAdapter;
import com.pilipa.fapiaobao.base.BaseActivity;
import com.pilipa.fapiaobao.net.Api;
import com.pilipa.fapiaobao.net.bean.LoginWithInfoBean;
import com.pilipa.fapiaobao.net.bean.me.CompaniesBean;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by edz on 2017/11/6.
 */

public class CompanySelectActivity extends BaseActivity implements CompanyListAdapter.OnCompanyClickListener {

    @Bind(R.id.title)
    TextView title;
    @Bind(R.id.company_select_back)
    ImageView companySelectBack;
    @Bind(R.id.filter)
    TextView filter;
    @Bind(R.id.recyclerview)
    RecyclerView recyclerview;

    public static final String EXTRA_BUNDLE  = "extra_bundle";
    public static final String EXTRA_SELECT_COMPANY  = "extra_select_company";

    private CompanyListAdapter companyListAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_company_select;
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void initView() {
        recyclerview.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        companyListAdapter = new CompanyListAdapter(true);
        companyListAdapter.setOnCompanyClickListener(this);
        recyclerview.setAdapter(companyListAdapter);
    }

    @Override
    public void initData() {
        AccountHelper.isTokenValid(new Api.BaseViewCallback<LoginWithInfoBean>() {
            @Override
            public void setData(LoginWithInfoBean loginWithInfoBean) {
                if (loginWithInfoBean.getStatus() ==200) {
                    Api.companiesList(loginWithInfoBean.getData().getToken(), new Api.BaseViewCallback<CompaniesBean>() {
                        @Override
                        public void setData(CompaniesBean companiesBean) {
                            if (companiesBean.getStatus() == 200 && companiesBean.getData() != null) {
                                companyListAdapter.addCompanyInfo(companiesBean.getData());
                            }
                        }
                    });
                }
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick(R.id.company_select_back)
    public void onViewClicked() {
        setResult(RESULT_CANCELED);
        finish();
    }

    @Override
    public void onCompanyClick(CompaniesBean.DataBean dataBean) {
        Intent intent = new Intent();
        Bundle bundle = new Bundle();
        bundle.putParcelable(EXTRA_SELECT_COMPANY,dataBean);
        intent.putExtra(EXTRA_BUNDLE, bundle);
        setResult(RESULT_OK,intent);
        finish();
    }
}
