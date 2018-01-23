package com.pilipa.fapiaobao.adapter.me;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by lyt on 2017/10/16.
 */

public class TabPageIndicatorAdapter extends FragmentPagerAdapter {
    private List titleList;
    private List<Fragment> fragmnetList;

    public TabPageIndicatorAdapter(FragmentManager fm,List titleList,List fragmnetList) {
        super(fm);
        this.titleList =titleList;
        this.fragmnetList =fragmnetList;
//         titleList = StaticDataCreator.initMyPublishTabData(BaseApplication.context());
    }

    @Override
    public Fragment getItem(int position) {
//        Fragment fragment = new UnusedPagerFragment();
        return fragmnetList.get(position);
    }


    @Override
    public int getCount() {
        return titleList.size();
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
        this.fragmnetList = list;
        notifyDataSetChanged();
    }
}
