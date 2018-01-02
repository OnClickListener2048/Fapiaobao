package com.pilipa.fapiaobao.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.pilipa.fapiaobao.R;
import com.pilipa.fapiaobao.adapter.FavoriteCompaniesSelectorAdapter;
import com.pilipa.fapiaobao.base.BaseActivity;
import com.pilipa.fapiaobao.net.bean.invoice.MacherBeanToken;
import com.pilipa.fapiaobao.net.bean.me.FavoriteCompanyBean;
import com.pilipa.fapiaobao.ui.constants.Constant;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by edz on 2018/1/2.
 */

public class FavCompanyChooseActivity extends BaseActivity implements BaseQuickAdapter.OnItemClickListener {

    @Bind(R.id.company_select_back)
    ImageView companySelectBack;
    @Bind(R.id.recyclerview)
    RecyclerView recyclerview;
    private String url;
    private String tag;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_collection_companies;
    }


    @Override
    public void initView() {
        super.initView();
    }

    @Override
    public void initData() {
        super.initData();
        FavoriteCompanyBean favoriteCompanyBean = getIntent().getParcelableExtra(Constant.COMPANIES_BEAN);
        url = getIntent().getStringExtra("url");
        tag = getIntent().getStringExtra(Constant.TAG);
        FavoriteCompaniesSelectorAdapter favoriteCompaniesSelectorAdapter = new FavoriteCompaniesSelectorAdapter(R.layout.item_simple_text_company, favoriteCompanyBean.getData());
        recyclerview.setItemAnimator(new DefaultItemAnimator());
        recyclerview.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recyclerview.setAdapter(favoriteCompaniesSelectorAdapter);
        favoriteCompaniesSelectorAdapter.setOnItemClickListener(this);

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick(R.id.company_select_back)
    public void onViewClicked() {
        finish();
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        Intent intent = new Intent();
        intent.putExtra(Constant.COMPANY_INFO, makeCompany((FavoriteCompanyBean.DataBean) adapter.getItem(position)));
        intent.putExtra("url", url);
        intent.putExtra(Constant.TAG, tag);
        intent.setClass(this, Op.class);
        startActivity(intent);
        finish();
    }

    private MacherBeanToken.DataBean.CompanyBean makeCompany(FavoriteCompanyBean.DataBean companiesBean) {
        MacherBeanToken.DataBean.CompanyBean companyBean = new MacherBeanToken.DataBean.CompanyBean();
        companyBean.setAccount(companiesBean.getAccount());
        companyBean.setAddress(companiesBean.getAddress());
        companyBean.setDepositBank(companiesBean.getDepositBank());
        companyBean.setId(companiesBean.getId());
        companyBean.setIsNewRecord(companiesBean.isIsNewRecord());
        companyBean.setName(companiesBean.getName());
        companyBean.setPhone(companiesBean.getPhone());
        companyBean.setTaxno(companiesBean.getTaxno());
        return companyBean;
    }


}
