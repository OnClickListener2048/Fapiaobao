package com.pilipa.fapiaobao.adapter.me;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.util.Log;

import com.pilipa.fapiaobao.ui.fragment.MyCompanyDetailsPagerFragment;

import java.util.ArrayList;

/**
 * Created by edz on 2017/10/20.
 */

public class CompanyDetailsAdapter extends FragmentPagerAdapter {
    private static final String TAG = "PreviewPagerAdapter";
    public ArrayList<MyCompanyDetailsPagerFragment> arrayList;
    private Context context;
    public CompanyDetailsAdapter(Context context,FragmentManager fm, ArrayList<MyCompanyDetailsPagerFragment> fragmentList) {
        super(fm);
        this.context = context;
        this.arrayList = fragmentList;
    }

    @Override
    public Fragment getItem(int position) {
        return arrayList.get(position);
    }

    @Override
    public int getItemPosition(Object object) {
        return PagerAdapter.POSITION_NONE;
    }

    @Override
    public long getItemId(int position) {
        if (arrayList.size() == position) {
            return arrayList.get(arrayList.size() - 1).hashCode();
        }

        int hashcode = arrayList.get(position).hashCode();
        return hashcode;
    }

    @Override
    public int getCount() {
        return arrayList.size();
    }

    public void remove(int mPreviousPos) {
        if (arrayList != null && arrayList.size() != 0) {
            arrayList.remove(arrayList.get(mPreviousPos));
            Log.d(TAG, "remove: mPreviousPos"+mPreviousPos);
            notifyDataSetChanged();

            if(arrayList.size() == 0){
                Intent intent = new Intent();
                intent.setAction("remove");
                context.sendBroadcast(intent);
            }
        }
    }
}
