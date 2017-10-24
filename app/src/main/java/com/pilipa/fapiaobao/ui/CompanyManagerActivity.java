package com.pilipa.fapiaobao.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;

import com.pilipa.fapiaobao.R;
import com.pilipa.fapiaobao.adapter.TabPageIndicatorAdapter;
import com.pilipa.fapiaobao.base.BaseActivity;
import com.pilipa.fapiaobao.base.BaseApplication;
import com.pilipa.fapiaobao.ui.fragment.MyCompanyViewPagerFragment;
import com.pilipa.fapiaobao.ui.fragment.MyPublishViewPagerFragment;
import com.pilipa.fapiaobao.ui.model.StaticDataCreator;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by wjn on 2017/10/23.
 */

public class CompanyManagerActivity extends BaseActivity {

    @Bind(R.id.tl_tabLayout)
    TabLayout tlTabLayout;
    @Bind(R.id.vp_verpager)
    ViewPager vpVerpager;
    @Bind(R.id.img_add)
    ImageView imgAdd;
    private List<Fragment> fragmentList;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_company_manager;
    }
    @OnClick({R.id.img_add})
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.img_add:{
                startActivity(new Intent(this,AddCompanyInfoActivity.class));
            }break;
        }
    }

    @Override
    public void initView() {
        List list = StaticDataCreator.initCompanyManagerTabData(BaseApplication.context());
        fragmentList = new ArrayList<>();
        fragmentList.add(new MyCompanyViewPagerFragment());
        fragmentList.add(new MyCompanyViewPagerFragment());

        vpVerpager.setAdapter(new TabPageIndicatorAdapter(getSupportFragmentManager(),list,fragmentList));
        tlTabLayout.setupWithViewPager(vpVerpager);
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
