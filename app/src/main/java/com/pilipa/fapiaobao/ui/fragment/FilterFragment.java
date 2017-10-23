package com.pilipa.fapiaobao.ui.fragment;

import android.os.Bundle;

import com.pilipa.fapiaobao.R;
import com.pilipa.fapiaobao.base.BaseFragment;

/**
 * Created by edz on 2017/10/23.
 */

public class FilterFragment extends BaseFragment {

    public static FilterFragment newInstance(Bundle bundle) {
        FilterFragment filterFragment = new FilterFragment();
        filterFragment.setArguments(bundle);
        return filterFragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_filter;
    }
}
