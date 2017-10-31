package com.pilipa.fapiaobao.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.lcodecore.tkrefreshlayout.RefreshListenerAdapter;
import com.lcodecore.tkrefreshlayout.TwinklingRefreshLayout;
import com.lcodecore.tkrefreshlayout.header.progresslayout.ProgressLayout;
import com.pilipa.fapiaobao.R;
import com.pilipa.fapiaobao.account.AccountHelper;
import com.pilipa.fapiaobao.adapter.MyCompanyAdapter;
import com.pilipa.fapiaobao.base.BaseFragment;
import com.pilipa.fapiaobao.net.Api;
import com.pilipa.fapiaobao.net.bean.LoginWithInfoBean;
import com.pilipa.fapiaobao.net.bean.invoice.CompanyCollectBean;
import com.pilipa.fapiaobao.net.bean.me.FavoriteCompanyBean;
import com.pilipa.fapiaobao.net.bean.me.NormalBean;
import com.pilipa.fapiaobao.ui.CompanyDetailsActivity;
import com.pilipa.fapiaobao.utils.SharedPreferencesHelper;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

import static com.pilipa.fapiaobao.net.Constant.REQUEST_SUCCESS;

/**
 * Created by lyt on 2017/10/17.
 */

public class MyFavoriteCompanyViewPagerFragment extends BaseFragment implements AdapterView.OnItemClickListener{
    private static final String TAG = "MyCompanyViewPagerFragment";

    @Bind(R.id.recyclerview)
    ListView listView;
    @Bind(R.id.trl)
    TwinklingRefreshLayout trl;
    private MyCompanyAdapter mAdapter;
    public List<FavoriteCompanyBean.DataBean> mData = new ArrayList();

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
    }

    @Override
    protected void initData() {
        getFavCompanyList();


        LoginWithInfoBean loginBean =  SharedPreferencesHelper.loadFormSource(mContext,LoginWithInfoBean.class);
        if(loginBean != null){
          String token =  loginBean.getData().getToken();
//          favCompanyCreate(favCompany);
        }

        super.initData();
    }
    private RefreshListenerAdapter refreshListenerAdapter = new RefreshListenerAdapter() {
        @Override
        public void onPullingDown(TwinklingRefreshLayout refreshLayout, float fraction) {
            super.onPullingDown(refreshLayout, fraction);
            showToast("onPullingDown");
        }

        @Override
        public void onPullingUp(TwinklingRefreshLayout refreshLayout, float fraction) {
            super.onPullingUp(refreshLayout, fraction);
            showToast("onPullingUp");
        }

        @Override
        public void onPullDownReleasing(TwinklingRefreshLayout refreshLayout, float fraction) {
            super.onPullDownReleasing(refreshLayout, fraction);
            showToast("onPullDownReleasing");
        }

        @Override
        public void onPullUpReleasing(TwinklingRefreshLayout refreshLayout, float fraction) {
            super.onPullUpReleasing(refreshLayout, fraction);
            showToast("onPullUpReleasing");
        }

        @Override
        public void onRefresh(TwinklingRefreshLayout refreshLayout) {
            super.onRefresh(refreshLayout);
            showToast("onRefresh");
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    if(trl != null)
                        trl.finishRefreshing();
                }
            },2000);
        }

        @Override
        public void onLoadMore(TwinklingRefreshLayout refreshLayout) {
            super.onLoadMore(refreshLayout);
            showToast("onLoadMore");
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
            showToast("onFinishRefresh");
        }

        @Override
        public void onFinishLoadMore() {
            super.onFinishLoadMore();
            showToast("onFinishLoadMore");
        }

        @Override
        public void onRefreshCanceled() {
            super.onRefreshCanceled();
            showToast("onRefreshCanceled");

        }

        @Override
        public void onLoadmoreCanceled() {
            super.onLoadmoreCanceled();
            showToast("onLoadmoreCanceled");
        }
    };

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        startActivity(new Intent(mContext, CompanyDetailsActivity.class));
    }

    public void getFavCompanyList(){
        if (AccountHelper.getToken() != null && AccountHelper.getToken() != "") {

            Api.favoriteCompanyList(AccountHelper.getToken(),new Api.BaseViewCallback<FavoriteCompanyBean>() {
                @Override
                public void setData(FavoriteCompanyBean favoriteCompanyBean) {
                    List<FavoriteCompanyBean.DataBean> list =  favoriteCompanyBean.getData();
                    mData.addAll(list) ;
                    mAdapter.initData(list);
                    Log.d(TAG, "FavoriteCompany success");
                }
            });
        }

    }
    public void deleteFavCompany(String id,String token){
        Api.deleteFavoriteCompany(id,token,new Api.BaseViewCallback<NormalBean>() {
            @Override
            public void setData(NormalBean normalBean) {
                if(normalBean.getStatus() == REQUEST_SUCCESS){
                    Log.d(TAG, "getCompanyDetails success");
                }
            }
        });
    }
    public void favCompanyCreate(CompanyCollectBean favCompany){
        Api.favCompanyCreate(favCompany,new Api.BaseViewCallback<NormalBean>() {
            @Override
            public void setData(NormalBean normalBean) {
                if(normalBean.getStatus() == REQUEST_SUCCESS){
                    Log.d(TAG, "favCompanyCreate success");
                }
            }
        });
    }


}
