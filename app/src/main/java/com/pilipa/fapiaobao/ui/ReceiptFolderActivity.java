package com.pilipa.fapiaobao.ui;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.pilipa.fapiaobao.R;
import com.pilipa.fapiaobao.adapter.TabPageIndicatorAdapter;
import com.pilipa.fapiaobao.base.BaseActivity;
import com.pilipa.fapiaobao.base.BaseApplication;
import com.pilipa.fapiaobao.ui.fragment.UnusedPagerFragment;
import com.pilipa.fapiaobao.ui.fragment.ProvidePagerFragment;
import com.pilipa.fapiaobao.ui.fragment.UnusedReceiptFragment;
import com.pilipa.fapiaobao.ui.model.StaticDataCreator;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by wjn on 2017/10/23.
 */

public class ReceiptFolderActivity extends BaseActivity {

    @Bind(R.id.tl_tabLayout)
    TabLayout tlTabLayout;
    @Bind(R.id.vp_verpager)
    ViewPager vpVerpager;
    private List<Fragment> fragmentList;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_receipt_folder;
    }

    @OnClick({R.id.folder_back})
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.folder_back:{
                finish();
            }break;
        }
    }

    @Override
    public void initView() {
        List list = StaticDataCreator.initReceiptFolderTabData(BaseApplication.context());
        fragmentList = new ArrayList<>();
        fragmentList.add(new ProvidePagerFragment());
        fragmentList.add(UnusedReceiptFragment.newInstance(new Bundle()));
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
