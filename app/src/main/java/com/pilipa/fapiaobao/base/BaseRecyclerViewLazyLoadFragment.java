package com.pilipa.fapiaobao.base;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.pilipa.fapiaobao.R;

import butterknife.Bind;
import butterknife.ButterKnife;


/**
 * Created by edz on 2018/3/13.
 */

public abstract class BaseRecyclerViewLazyLoadFragment extends BaseFragment {
    @Bind(R.id.recyclerView)
    protected RecyclerView mRecyclerView;
    protected boolean mIsInited;
    protected boolean mIsPrepared;

    @Override
    protected int getLayoutId() {
        return R.layout.base_fragment_recyclerview;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        mIsPrepared = true;
        lazyLoad();
        return rootView;
    }

    private void lazyLoad() {
        if (getUserVisibleHint() && mIsPrepared && !mIsInited) {
            fetchData();
        }
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            lazyLoad();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        mIsInited = false;
    }

    public abstract void fetchData();

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
