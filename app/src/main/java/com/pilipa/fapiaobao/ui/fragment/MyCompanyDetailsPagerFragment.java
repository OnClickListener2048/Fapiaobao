package com.pilipa.fapiaobao.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.pilipa.fapiaobao.R;
import com.pilipa.fapiaobao.base.BaseFragment;
import com.pilipa.fapiaobao.net.Api;
import com.pilipa.fapiaobao.net.bean.me.CompanyDetailsBean;
import com.pilipa.fapiaobao.net.bean.me.NormalBean;

import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.pilipa.fapiaobao.net.Constant.REQUEST_SUCCESS;

/**
 * Created by lyt on 2017/10/17.
 */

public class MyCompanyDetailsPagerFragment extends BaseFragment{
    private static final String TAG = "MyCompanyDetailsPagerFragment";

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
    public static MyCompanyDetailsPagerFragment newInstance(String id) {
        MyCompanyDetailsPagerFragment myCompanyDetailsPagerFragment = new MyCompanyDetailsPagerFragment();
        Bundle bundle = new Bundle();
        bundle.putString("id",id);
        myCompanyDetailsPagerFragment.setArguments(bundle);

        return myCompanyDetailsPagerFragment;
    }
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        String id = getArguments().getString("id");


        ImageView img_qr_code1 = (ImageView) view.findViewById(R.id.img_qr_code1);
        LinearLayout ll_qr_code2 = (LinearLayout) view.findViewById(R.id.ll_qr_code2);

        if(id.equals("1")){
            ll_qr_code2.setVisibility(View.VISIBLE);
            img_qr_code1.setVisibility(View.GONE);
        }else{
            img_qr_code1.setVisibility(View.VISIBLE);
            ll_qr_code2.setVisibility(View.GONE);
        }

    }

    @OnClick({R.id.img_details_viewpager_share, R.id.img_details_viewpager_delete,R.id.img_details_viewpager_next})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img_details_viewpager_share:
                break;
            case R.id.img_details_viewpager_delete:
                break;
            case R.id.img_details_viewpager_next:

                break;
        }
    }
    @Override
    protected void initData() {
        super.initData();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    public  void getCompanyDetails(String id){
        Api.companyDetails(id,new Api.BaseViewCallback<CompanyDetailsBean>() {
            @Override
            public void setData(CompanyDetailsBean companyDetailsBean) {
                if(companyDetailsBean.getStatus() == REQUEST_SUCCESS){
                    CompanyDetailsBean.DataBean bean = companyDetailsBean.getData();
                    Log.d(TAG, "getCompanyDetails"+bean.getName().toString());
                }
            }
        });
    }
    public void deleteCompany(String id,String token){
        Api.deleteCompany(id,token,new Api.BaseViewCallback<NormalBean>() {
            @Override
            public void setData(NormalBean normalBean) {
                if(normalBean.getStatus() == REQUEST_SUCCESS){
                    Log.d(TAG, "getCompanyDetails success");
                }
            }
        });
    }
}