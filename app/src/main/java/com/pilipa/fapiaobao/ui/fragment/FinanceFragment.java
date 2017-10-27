package com.pilipa.fapiaobao.ui.fragment;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.pilipa.fapiaobao.R;
import com.pilipa.fapiaobao.adapter.FinanceAdapter;
import com.pilipa.fapiaobao.adapter.FinanceMoreKindAdapter;
import com.pilipa.fapiaobao.base.BaseFragment;
import com.pilipa.fapiaobao.ui.deco.FinanceItemDeco;
import com.pilipa.fapiaobao.ui.deco.GridInset;
import com.pilipa.fapiaobao.ui.deco.GridInsetFinance;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by edz on 2017/10/23.
 */

public class FinanceFragment extends BaseFragment {
    @Bind(R.id.scan)
    ImageView scan;
    @Bind(R.id.notification)
    ImageView notification;
    @Bind(R.id.recyclerview)
    RecyclerView recyclerview;
    @Bind(R.id.recyclerview_more_kind)
    RecyclerView recyclerviewMoreKind;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_finance;
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

    @Override
    protected void initWidget(View root) {
        super.initWidget(root);

        GridLayoutManager manager = new GridLayoutManager(mContext, 2, LinearLayoutManager.VERTICAL, false);
        recyclerview.setLayoutManager(manager);
        recyclerview.addItemDecoration(new GridInsetFinance(2, 20, true));
        recyclerview.setAdapter(new FinanceAdapter());
        recyclerviewMoreKind.setLayoutManager(new LinearLayoutManager(mContext,LinearLayoutManager.VERTICAL,false));
        recyclerviewMoreKind.addItemDecoration(new FinanceItemDeco(mContext,LinearLayoutManager.VERTICAL,10,R.color.cancel));
        recyclerviewMoreKind.setAdapter(new FinanceMoreKindAdapter());
    }

    @OnClick({R.id.scan, R.id.notification})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.scan:
                break;
            case R.id.notification:
                break;
        }
    }
}
