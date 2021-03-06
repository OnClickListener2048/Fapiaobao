package com.pilipa.fapiaobao.ui.fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.example.mylibrary.utils.TLog;
import com.lcodecore.tkrefreshlayout.header.progresslayout.ProgressLayout;
import com.lzy.okgo.OkGo;
import com.pilipa.fapiaobao.R;
import com.pilipa.fapiaobao.account.AccountHelper;
import com.pilipa.fapiaobao.adapter.me.MyPublishAdapter;
import com.pilipa.fapiaobao.base.BaseActivity;
import com.pilipa.fapiaobao.base.BaseNoNetworkFragment;
import com.pilipa.fapiaobao.net.Api;
import com.pilipa.fapiaobao.net.bean.publish.DemandsListBean;
import com.pilipa.fapiaobao.ui.DemandActivity;
import com.pilipa.fapiaobao.ui.constants.Constant;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import butterknife.Bind;
import butterknife.ButterKnife;

import static com.pilipa.fapiaobao.net.Constant.REQUEST_NO_CONTENT;
import static com.pilipa.fapiaobao.net.Constant.REQUEST_SUCCESS;
import static com.pilipa.fapiaobao.net.Constant.STATE_DEMAND_ING;

/**
 * Created by lyt on 2017/10/17.
 */
public class UnusedPagerFragment_ing extends BaseNoNetworkFragment implements AdapterView.OnItemClickListener {
    private static final String TAG = "UnusedPagerFragment_ing";

    @Bind(R.id.recyclerview)
    ListView listView;
    private MyPublishAdapter mAdapter;
    private List<DemandsListBean.DataBean> dataBeanList = new ArrayList<>();
    private boolean mIsInited;
    private boolean mIsPrepared;
    private View emptyView;

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
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            lazyLoad();
        }
    }

    private void lazyLoad() {
        if (getUserVisibleHint() && mIsPrepared && !mIsInited) {
            demandsList(STATE_DEMAND_ING);
        }
    }


    @Override
    protected void onNoNetworkLayoutClicks(View v) {
        demandsList(STATE_DEMAND_ING);
    }

    @Override
    protected void initWidget(View root) {

        super.initWidget(root);
        ProgressLayout headerView = new ProgressLayout(getContext());
    }

    @Override
    protected void initData() {
        super.initData();
        mAdapter = new MyPublishAdapter(mContext);
        listView.setAdapter(mAdapter);
        listView.setOnItemClickListener(this);
        emptyView = View.inflate(mContext, R.layout.layout_details_empty_view, null);
        emptyView.setVisibility(View.GONE);
        emptyView.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
        ((ViewGroup) listView.getParent()).addView(emptyView);
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(mContext, DemandActivity.class);
        intent.putExtra("demandId",dataBeanList.get(position).getId());
        startActivityForResult(intent, Constant.REQUEST_REFRESH_CODE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == Constant.REQUEST_REFRESH_CODE && resultCode == Activity.RESULT_OK) {
            lazyLoad();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        OkGo.cancelTag(OkGo.getInstance().getOkHttpClient(),this);
        mIsInited = false;
    }
    public void demandsList(String state){
        if (AccountHelper.getToken() != null && !Objects.equals(AccountHelper.getToken(), "")) {
            Api.demandsList(AccountHelper.getToken(),state,this,new Api.BaseRawResponse<DemandsListBean>() {


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
                public void setData(DemandsListBean demandsListBean) {
                    mIsInited = true;
                    hideNetWorkErrorLayout();
                    int status = demandsListBean.getStatus();
                    if (status == REQUEST_SUCCESS) {
                        List<DemandsListBean.DataBean> list =  demandsListBean.getData();
                        dataBeanList = list;
                        mAdapter.initData(list);
                        if (listView!= null) {
                        listView.setAdapter(mAdapter);
                        }

                        Log.d(TAG, "demandsList success");
                    }
                    if (status == REQUEST_NO_CONTENT) {
                        TLog.log("status == REQUEST_NO_CONTENT");
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
    }



    @Override
    public void initDataInResume() {
//        demandsList(STATE_DEMAND_ING);
    }
}
