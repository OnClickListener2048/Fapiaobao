package com.pilipa.fapiaobao.adapter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.mylibrary.utils.TLog;
import com.pilipa.fapiaobao.net.bean.invoice.MacherBeanToken;
import com.pilipa.fapiaobao.ui.fragment.EstimatePagerFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by edz on 2017/10/30.
 */

public class ExtimatePagerAdapter extends FragmentPagerAdapter {
    private  List<MacherBeanToken.DataBean> list;

    public ExtimatePagerAdapter(FragmentManager fm, List<MacherBeanToken.DataBean> data) {
        super(fm);
        this.list = data;
    }


    public void update(List<MacherBeanToken.DataBean> list)  {
        if (this.list != null) {
            this.list.clear();
            this.list.addAll(list);
        } else {
            this.list = list;
        }
        notifyDataSetChanged();
    }




    @Override
    public int getCount() {
        if (list != null) {
            return list.size();
        }
        return 0;
    }

    @Override
    public long getItemId(int position) {
        return list.get(position).hashCode();
    }


    @Override
    public int getItemPosition(Object object) {
        return super.getItemPosition(object);

    }

    @Override
    public Fragment getItem(int position) {
        Bundle bundle = new Bundle();
        bundle.putParcelable("estimate_item", list.get(position));
        TLog.log("EstimatePagerFragment.newInstance(bundle)");
        return EstimatePagerFragment.newInstance(bundle);
    }

}
