package com.pilipa.fapiaobao.adapter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.mylibrary.utils.TLog;
import com.pilipa.fapiaobao.net.bean.invoice.MacherBeanToken;
import com.pilipa.fapiaobao.ui.fragment.EstimatePagerFragment;

import java.util.ArrayList;

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
            if (matchBean.getData() != null) {
                if (matchBean.getData().size()>0) {
                    return matchBean.getData().size();
                }
            }
        }
        return 0;
    }

    @Override
    public long getItemId(int position) {
        if (matchBean != null) {
            if (matchBean.getData() != null) {
                if (matchBean.getData().size()>0) {
                    return matchBean.getData().get(position).hashCode();
                }
            }
        }
        return 0;
    }


    @Override
    public int getItemPosition(Object object) {
        return super.getItemPosition(object);

    }

    @Override
    public Fragment getItem(int position) {
        Bundle bundle = new Bundle();
        bundle.putParcelable("estimate_item", matchBean.getData().get(position));
        TLog.log("EstimatePagerFragment.newInstance(bundle)");
        return EstimatePagerFragment.newInstance(bundle);
    }

}
