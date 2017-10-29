package com.pilipa.fapiaobao.ui;

import android.content.Intent;
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
    CompanyDetailsAdapter companyDetailsAdapter;
    private ArrayList<MyCompanyDetailsPagerFragment> FragmentList;
    protected int mPreviousPos = 0;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_company_details;
    }

    @OnClick({R.id.details_back,R.id.img_add})
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.details_back:{
                finish();
            }break;
            case R.id.img_add:{
                startActivity(new Intent(this,AddCompanyInfoActivity.class));
            }break;
        }
    }

    @Override
    public void initView() {
        FragmentList  = new ArrayList<>();
        MyCompanyDetailsPagerFragment fragment1 = MyCompanyDetailsPagerFragment.newInstance("1");
        MyCompanyDetailsPagerFragment fragment2= MyCompanyDetailsPagerFragment.newInstance("2");
        MyCompanyDetailsPagerFragment fragment3= MyCompanyDetailsPagerFragment.newInstance("1");
        FragmentList.add(fragment1);
        FragmentList.add(fragment2);
        FragmentList.add(fragment3);

        companyDetailsAdapter = new CompanyDetailsAdapter(getSupportFragmentManager(), FragmentList);
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
