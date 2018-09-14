package com.pilipa.fapiaobao.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;

import com.pilipa.fapiaobao.R;
import com.pilipa.fapiaobao.adapter.me.TabPageIndicatorAdapter;
import com.pilipa.fapiaobao.base.BaseActivity;
import com.pilipa.fapiaobao.base.BaseApplication;
import com.pilipa.fapiaobao.ui.constants.Constant;
import com.pilipa.fapiaobao.ui.fragment.MyCompanyViewPagerFragment;
import com.pilipa.fapiaobao.ui.fragment.MyFavoriteCompanyViewPagerFragment;
import com.pilipa.fapiaobao.ui.model.StaticDataCreator;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by wjn on 2017/10/23.
 */

public class CompanyManagerActivity extends BaseActivity implements TabLayout.OnTabSelectedListener{

    @Bind(R.id.tl_tabLayout)
    TabLayout tlTabLayout;
    @Bind(R.id.vp_verpager)
    ViewPager vpVerpager;
    @Bind(R.id.img_add)
    ImageView imgAdd;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_company_manager;
    }
    @OnClick({R.id.img_add,R.id.company_manager_back})
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.img_add:{
                Intent intent = new Intent(this, AddCompanyInfoActivity.class);
                startActivityForResult(intent, Constant.REQUEST_REFRESH_CODE);
            }break;
            case R.id.company_manager_back:{
                finish();
            }break;
        }
    }
    @Override
    public void initView() {
        List list = StaticDataCreator.initCompanyManagerTabData(BaseApplication.context());
        List<Fragment> fragmentList = new ArrayList<>();
        fragmentList.add(new MyCompanyViewPagerFragment());
        fragmentList.add(new MyFavoriteCompanyViewPagerFragment());
        vpVerpager.setAdapter(new TabPageIndicatorAdapter(getSupportFragmentManager(),list, fragmentList));
        tlTabLayout.setupWithViewPager(vpVerpager);
        tlTabLayout.setOnTabSelectedListener(this);

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

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        if(tlTabLayout.getSelectedTabPosition() == 1){
            imgAdd.setVisibility(View.GONE);
        }else{
            imgAdd.setVisibility(View.VISIBLE);
        }
        vpVerpager.setCurrentItem(tlTabLayout.getSelectedTabPosition());
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        List<Fragment> fragments = getSupportFragmentManager().getFragments();
        for (Fragment fragment : fragments) {
            fragment.onActivityResult(requestCode,resultCode,data);
        }
    }
}
