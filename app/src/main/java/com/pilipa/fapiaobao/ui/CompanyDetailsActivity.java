package com.pilipa.fapiaobao.ui;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.CompoundButton;

import com.example.mylibrary.utils.SizeUtils;
import com.pilipa.fapiaobao.R;
import com.pilipa.fapiaobao.adapter.CardFragmentPagerAdapter;
import com.pilipa.fapiaobao.adapter.CardPagerAdapter;
import com.pilipa.fapiaobao.adapter.TabPageIndicatorAdapter;
import com.pilipa.fapiaobao.base.BaseActivity;
import com.pilipa.fapiaobao.base.BaseApplication;
import com.pilipa.fapiaobao.entity.CardItem;
import com.pilipa.fapiaobao.ui.fragment.MyPublishViewPagerFragment;
import com.pilipa.fapiaobao.ui.fragment.MyReceiptViewPagerFragment;
import com.pilipa.fapiaobao.ui.model.StaticDataCreator;
import com.pilipa.fapiaobao.ui.widget.ShadowTransformer;
import com.pilipa.fapiaobao.utils.TDevice;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by wjn on 2017/10/23.
 */

public class CompanyDetailsActivity extends BaseActivity implements CompoundButton.OnCheckedChangeListener  {
    @Bind(R.id.vp_verpager)
    ViewPager mViewPager;
    private CardPagerAdapter mCardAdapter;
    private ShadowTransformer mCardShadowTransformer;
    private CardFragmentPagerAdapter mFragmentCardAdapter;
    private ShadowTransformer mFragmentCardShadowTransformer;

    private boolean mShowingFragments = false;
    @Override
    protected int getLayoutId() {
        return R.layout.activity_company_details;
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void initView() {
        mCardAdapter = new CardPagerAdapter();
        mCardAdapter.addCardItem(new CardItem(111,1111));
        mCardAdapter.addCardItem(new CardItem(222,2222));
        mCardAdapter.addCardItem(new CardItem(222,2222));
        mFragmentCardAdapter = new CardFragmentPagerAdapter(getSupportFragmentManager(),
                TDevice.dp2px(2) );

        mCardShadowTransformer = new ShadowTransformer(mViewPager, mCardAdapter);
        mFragmentCardShadowTransformer = new ShadowTransformer(mViewPager, mFragmentCardAdapter);

        mViewPager.setAdapter(mCardAdapter);
        mViewPager.setPageTransformer(false, mCardShadowTransformer);
        mViewPager.setOffscreenPageLimit(3);
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
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
        mCardShadowTransformer.enableScaling(b);
        mFragmentCardShadowTransformer.enableScaling(b);
    }
}
