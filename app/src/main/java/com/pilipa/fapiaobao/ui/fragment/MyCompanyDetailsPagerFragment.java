package com.pilipa.fapiaobao.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.pilipa.fapiaobao.R;
import com.pilipa.fapiaobao.account.AccountHelper;
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

    private TextView tv_companyName,tv_receptCode,tv_address,tv_phoneNum,tv_bankName,tv_account;
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
    public static MyCompanyDetailsPagerFragment newInstance(Bundle bundle) {
        MyCompanyDetailsPagerFragment myCompanyDetailsPagerFragment = new MyCompanyDetailsPagerFragment();
        myCompanyDetailsPagerFragment.setArguments(bundle);
        return myCompanyDetailsPagerFragment;
    }
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Bundle arguments = getArguments();
        String companyId =(String) arguments.get("companyId");

        getCompanyDetails(companyId);
        tv_companyName = (TextView) view.findViewById(R.id.tv_companyName);
        tv_receptCode = (TextView) view.findViewById(R.id.tv_receptCode);
        tv_address = (TextView) view.findViewById(R.id.tv_address);
        tv_phoneNum = (TextView) view.findViewById(R.id.tv_phoneNum);
        tv_bankName = (TextView) view.findViewById(R.id.tv_bankName);
        tv_account = (TextView) view.findViewById(R.id.tv_account);


        ImageView img_qr_code1 = (ImageView) view.findViewById(R.id.img_qr_code1);
        LinearLayout ll_qr_code2 = (LinearLayout) view.findViewById(R.id.ll_qr_code2);

//        if(companyId.equals("1")){
//            ll_qr_code2.setVisibility(View.VISIBLE);
//            img_qr_code1.setVisibility(View.GONE);
//        }else{
//            img_qr_code1.setVisibility(View.VISIBLE);
//            ll_qr_code2.setVisibility(View.GONE);
//        }

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
            Api.companyDetails(id, new Api.BaseViewCallback<CompanyDetailsBean>() {
                @Override
                public void setData(CompanyDetailsBean companyDetailsBean) {
                    if (companyDetailsBean.getStatus() == REQUEST_SUCCESS) {
                        CompanyDetailsBean.DataBean bean = companyDetailsBean.getData();
                        tv_companyName.setText(bean.getName());
                        tv_receptCode.setText(bean.getTaxno());
                        tv_address.setText(bean.getAddress());
                        tv_phoneNum.setText(bean.getPhone());
                        tv_bankName.setText(bean.getDepositBank());
                        tv_account.setText(bean.getAccount());
                        Log.d(TAG, "getCompanyDetails" + bean.getName().toString());
                    }
                }
            });
    }
    public void deleteCompany(String id){
        if (AccountHelper.getToken() != null && AccountHelper.getToken() != "") {
            Api.deleteCompany(id, AccountHelper.getToken(), new Api.BaseViewCallback<NormalBean>() {
                @Override
                public void setData(NormalBean normalBean) {
                    if (normalBean.getStatus() == REQUEST_SUCCESS) {
                        Log.d(TAG, "getCompanyDetails success");
                    }
                }
            });
        }
    }
}
