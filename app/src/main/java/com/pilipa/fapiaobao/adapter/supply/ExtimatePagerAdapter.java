package com.pilipa.fapiaobao.adapter.supply;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

import com.pilipa.fapiaobao.ui.fragment.EstimatePagerFragment;

import java.util.List;

/**
 * Created by edz on 2017/10/30.
 */

public class ExtimatePagerAdapter extends FragmentPagerAdapter {
    private  List<EstimatePagerFragment> list;

    public ExtimatePagerAdapter(FragmentManager fm, List<EstimatePagerFragment> data) {
        super(fm);
        this.list = data;
    }


    public void update(List<EstimatePagerFragment> list)  {
//        if (this.list != null) {
//            this.list.clear();
//            this.list.addAll(list);
//            notifyDataSetChanged();
//        } else {
//            this.list = list;
//            notifyDataSetChanged();
//        }
        this.list = list;
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
        return list.get(position);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        super.destroyItem(container, position, object);
    }

}
