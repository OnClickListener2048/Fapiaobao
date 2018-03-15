package com.pilipa.fapiaobao.base;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

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
    @Bind(R.id.ll_empty_view)
    LinearLayout mLlEmptyView;
    @Bind(R.id.ll_no_network)
    LinearLayout mLlNoNetwork;

    @Override
    protected int getLayoutId() {
        return R.layout.base_fragment_recyclerview;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.HONEYCOMB) {
            mRecyclerView.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        }
        mIsPrepared = true;
        lazyLoad();

        return rootView;
    }

    private void lazyLoad() {
        if (getUserVisibleHint() && mIsPrepared && !mIsInited) {
            fetchData();
        }
    }


    protected void noNetwork() {
        mRecyclerView.setVisibility(View.GONE);
        mLlEmptyView.setVisibility(View.GONE);
        mLlNoNetwork.setVisibility(View.VISIBLE);
        mLlNoNetwork.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onEmptyViewClick(v);
            }
        });
    }

    protected void noContent() {
        mRecyclerView.setVisibility(View.GONE);
        mLlEmptyView.setVisibility(View.VISIBLE);
        mLlNoNetwork.setVisibility(View.GONE);
        mLlEmptyView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onNoContentViewClick(v);
            }
        });
    }

    public abstract void onNoContentViewClick(View view);

    public abstract void onEmptyViewClick(View view);

    protected void fetchSuccess() {
        mRecyclerView.setVisibility(View.VISIBLE);
        mLlEmptyView.setVisibility(View.GONE);
        mLlNoNetwork.setVisibility(View.GONE);
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
