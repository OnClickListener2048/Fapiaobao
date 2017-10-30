package com.pilipa.fapiaobao.adapter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;

import com.pilipa.fapiaobao.net.bean.invoice.MacherBeanToken;
import com.pilipa.fapiaobao.net.bean.invoice.MatchBean;
import com.pilipa.fapiaobao.ui.fragment.EstimatePagerFragment;

/**
 * Created by edz on 2017/10/30.
 */

public class ExtimatePagerAdapter extends FragmentPagerAdapter {
    private final MacherBeanToken matchBean;

    public ExtimatePagerAdapter(FragmentManager fm, MacherBeanToken matchBean) {
        super(fm);
        this.matchBean = matchBean;
    }

    @Override
    public int getCount() {
        if (matchBean != null) {
            if (matchBean.getData()!= null) {
                return matchBean.getData().size();
            }
        }
        return 0;
    }


    @Override
    public int getItemPosition(Object object) {
        return PagerAdapter.POSITION_NONE;
    }

    @Override
    public Fragment getItem(int position) {
        Bundle bundle = new Bundle();
        bundle.putParcelable("estimate_item", matchBean.getData().get(position));
        return EstimatePagerFragment.newInstance(bundle);
    }

}
