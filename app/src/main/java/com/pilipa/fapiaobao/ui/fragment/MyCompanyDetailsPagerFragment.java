package com.pilipa.fapiaobao.ui.fragment;

import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lcodecore.tkrefreshlayout.TwinklingRefreshLayout;
import com.pilipa.fapiaobao.R;
import com.pilipa.fapiaobao.base.BaseFragment;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by lyt on 2017/10/17.
 */

public class MyCompanyDetailsPagerFragment extends BaseFragment{
    @Bind(R.id.cardView)
    CardView mCardView;
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_company_details;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        return rootView;
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
    public CardView getCardView() {
        return mCardView;
    }
}
