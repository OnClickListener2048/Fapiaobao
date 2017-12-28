package com.pilipa.fapiaobao.ui.fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Parcelable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.example.mylibrary.utils.TLog;
import com.lcodecore.tkrefreshlayout.RefreshListenerAdapter;
import com.lcodecore.tkrefreshlayout.TwinklingRefreshLayout;
import com.lcodecore.tkrefreshlayout.header.progresslayout.ProgressLayout;
import com.lzy.okgo.OkGo;
import com.pilipa.fapiaobao.R;
import com.pilipa.fapiaobao.account.AccountHelper;
import com.pilipa.fapiaobao.adapter.MyCompanyAdapter;
import com.pilipa.fapiaobao.base.BaseActivity;
import com.pilipa.fapiaobao.base.BaseNoNetworkFragment;
import com.pilipa.fapiaobao.net.Api;
import com.pilipa.fapiaobao.net.bean.LoginWithInfoBean;
import com.pilipa.fapiaobao.net.bean.me.FavoriteCompanyBean;
import com.pilipa.fapiaobao.ui.FavCompanyDetailsActivity;
import com.pilipa.fapiaobao.ui.constants.Constant;
import com.pilipa.fapiaobao.utils.SharedPreferencesHelper;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

import static android.app.Activity.RESULT_OK;
import static com.pilipa.fapiaobao.net.Constant.REQUEST_NO_CONTENT;
import static com.pilipa.fapiaobao.net.Constant.REQUEST_SUCCESS;
import static com.pilipa.fapiaobao.net.Constant.STATE_DEMAND_CLOSE;

/**
 * Created by lyt on 2017/10/17.
 */

public class MyFavoriteCompanyViewPagerFragment extends BaseNoNetworkFragment implements AdapterView.OnItemClickListener {
    private static final String TAG = "MyCompanyViewPagerFragment";

    @Bind(R.id.recyclerview)
    ListView listView;
    @Bind(R.id.trl)
    TwinklingRefreshLayout trl;
    private MyCompanyAdapter mAdapter;
    public List<FavoriteCompanyBean.DataBean> mData = new ArrayList();
    private boolean mIsInited;
    private boolean mIsPrepared;
    private View emptyView;


    public MyFavoriteCompanyViewPagerFragment() {

    }
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_viewpager_item;
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

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
    @Override
    protected void initWidget(View root) {

        super.initWidget(root);
        ProgressLayout headerView = new ProgressLayout(getContext());
        trl.setOnRefreshListener(refreshListenerAdapter);
        trl.setHeaderView(headerView);
        trl.setOverScrollRefreshShow(false);
        trl.setOverScrollBottomShow(false);
        trl.setOverScrollTopShow(false);
        trl.setEnableOverScroll(false);
        listView.setAdapter(mAdapter = new MyCompanyAdapter(mContext));
        listView.setOnItemClickListener(this);
        emptyView = View.inflate(mContext, R.layout.layout_details_empty_view, null);
        emptyView.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
        emptyView.setVisibility(View.GONE);
        ((ViewGroup)listView.getParent()).addView(emptyView);

    }

    @Override
    protected void initData() {
        super.initData();
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            lazyLoad();
        }
    }

    private void lazyLoad() {
        if (getUserVisibleHint() && mIsPrepared && !mIsInited) {
            getFavCompanyList();
        }
    }


    @Override
    protected void onNoNetworkLayoutClicks(View v) {
        getFavCompanyList();
    }

    private RefreshListenerAdapter refreshListenerAdapter = new RefreshListenerAdapter() {
        @Override
        public void onPullingDown(TwinklingRefreshLayout refreshLayout, float fraction) {
            super.onPullingDown(refreshLayout, fraction);
        }

        @Override
        public void onPullingUp(TwinklingRefreshLayout refreshLayout, float fraction) {
            super.onPullingUp(refreshLayout, fraction);
        }

        @Override
        public void onPullDownReleasing(TwinklingRefreshLayout refreshLayout, float fraction) {
            super.onPullDownReleasing(refreshLayout, fraction);
        }

        @Override
        public void onPullUpReleasing(TwinklingRefreshLayout refreshLayout, float fraction) {
            super.onPullUpReleasing(refreshLayout, fraction);
        }

        @Override
        public void onRefresh(TwinklingRefreshLayout refreshLayout) {
            super.onRefresh(refreshLayout);
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    getFavCompanyList();
                    if(trl != null)
                        trl.finishRefreshing();
                }
            },2000);
        }

        @Override
        public void onLoadMore(TwinklingRefreshLayout refreshLayout) {
            super.onLoadMore(refreshLayout);
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    if(trl != null)
                        trl.finishLoadmore();
                }
            },2000);
        }

        @Override
        public void onFinishRefresh() {
            super.onFinishRefresh();
        }

        @Override
        public void onFinishLoadMore() {
            super.onFinishLoadMore();
        }

        @Override
        public void onRefreshCanceled() {
            super.onRefreshCanceled();
        }

        @Override
        public void onLoadmoreCanceled() {
            super.onLoadmoreCanceled();
        }
    };

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(mContext, FavCompanyDetailsActivity.class);
        intent.putParcelableArrayListExtra("favCompanyList", (ArrayList<? extends Parcelable>) mData);
        intent.putExtra("mPreviousPos",position);
        startActivityForResult(intent, Constant.REQUEST_REFRESH_CODE);
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        TLog.d(TAG,"requestCode----"+requestCode);
        TLog.d(TAG,"resultCode----"+resultCode);
        if (requestCode == Constant.REQUEST_REFRESH_CODE && resultCode == RESULT_OK) {
            lazyLoad();
        }
    }

    public void getFavCompanyList(){

            Api.favoriteCompanyList(AccountHelper.getToken(),this,new Api.BaseRawResponse<FavoriteCompanyBean>() {
                @Override
                public void onStart() {
                    ((BaseActivity) getActivity()).showProgressDialog();
                }

                @Override
                public void onFinish() {
                    ((BaseActivity) getActivity()).hideProgressDialog();
                }

                @Override
                public void onError() {
                    showNetWorkErrorLayout();
                }

                @Override
                public void onTokenInvalid() {

                }

                @Override
                public void setData(FavoriteCompanyBean favoriteCompanyBean) {
                    mIsInited = true;
                    hideNetWorkErrorLayout();
                    if (favoriteCompanyBean.getStatus() == REQUEST_SUCCESS) {

                    List<FavoriteCompanyBean.DataBean> list =  favoriteCompanyBean.getData();
                    mData.clear();
                    mData=list ;
                    mAdapter.initData(mData);
                    listView.setAdapter(mAdapter);
                        Log.d(TAG, "FavoriteCompany success");
                    }

                    if (favoriteCompanyBean.getStatus() == REQUEST_NO_CONTENT) {
                        if (listView != null) {
                            listView.setAdapter(null);
                            listView.setEmptyView(emptyView);
                        }
                        if (mAdapter!= null) {
                            mAdapter.clearData();
                        }
                    }
                }
            });
    }
    @Override
    public void onPause() {
        mIsInited = false;
        OkGo.cancelTag(OkGo.getInstance().getOkHttpClient(),this);
        super.onPause();
    }

    @Override
    public void initDataInResume() {

    }

}
