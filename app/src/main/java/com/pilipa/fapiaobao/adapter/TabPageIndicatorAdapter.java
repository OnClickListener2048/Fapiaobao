package com.pilipa.fapiaobao.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.pilipa.fapiaobao.base.BaseApplication;
import com.pilipa.fapiaobao.ui.fragment.MyPublishViewPagerFragment;
import com.pilipa.fapiaobao.ui.model.StaticDataCreator;

import java.util.List;

/**
 * Created by lyt on 2017/10/16.
 */

public class TabPageIndicatorAdapter extends FragmentPagerAdapter {
    private final List titleList;
    private List list;

    public TabPageIndicatorAdapter(FragmentManager fm) {
        super(fm);
         titleList = StaticDataCreator.initMyPublishTabData(BaseApplication.context());
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = new MyPublishViewPagerFragment();
        return fragment;
    }


    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return (CharSequence) titleList.get(position);
    }

    public void addData(List list) {
        if (list != null) {
            list.addAll(list);
        }
        notifyDataSetChanged();
    }

    public void initData(List list) {
        this.list = list;
        notifyDataSetChanged();
    }
}
