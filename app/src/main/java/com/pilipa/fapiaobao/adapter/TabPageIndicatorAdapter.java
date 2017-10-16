package com.pilipa.fapiaobao.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by lyt on 2017/10/16.
 */

public class TabPageIndicatorAdapter extends FragmentPagerAdapter {
    private List list;

    public TabPageIndicatorAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return null;
    }

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return super.getPageTitle(position);
    }

    public void addData(List list) {
        if (list != null) {
            list.addAll(list);
        }
    }

    public void initData(List list) {
        this.list = list;
    }
}
