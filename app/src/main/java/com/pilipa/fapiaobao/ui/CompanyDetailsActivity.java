package com.pilipa.fapiaobao.ui;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.pilipa.fapiaobao.R;
import com.pilipa.fapiaobao.adapter.CompanyDetailsAdapter;
import com.pilipa.fapiaobao.base.BaseActivity;
import com.pilipa.fapiaobao.ui.fragment.MyCompanyDetailsPagerFragment;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by wjn on 2017/10/23.
 */

public class CompanyDetailsActivity extends BaseActivity {
    @Bind(R.id.vp_verpager)
    ViewPager mViewPager;
    private ArrayList<MyCompanyDetailsPagerFragment> FragmentList;
    protected int mPreviousPos = 0;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_company_details;
    }

    @OnClick({R.id.details_back})
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.details_back:{
                finish();
            }break;
        }
    }

    @Override
    public void initView() {
        FragmentList  = new ArrayList<>();
        FragmentList.add(MyCompanyDetailsPagerFragment.newInstance("1"));
        FragmentList.add(MyCompanyDetailsPagerFragment.newInstance("2"));
        CompanyDetailsAdapter companyDetailsAdapter = new CompanyDetailsAdapter(getSupportFragmentManager(), FragmentList);
        mViewPager.setAdapter(companyDetailsAdapter);
        mViewPager.setCurrentItem(mPreviousPos);
    }
    @Override
    public void initData() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
