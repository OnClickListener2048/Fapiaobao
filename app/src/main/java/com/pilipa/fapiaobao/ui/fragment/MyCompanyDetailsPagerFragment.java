package com.pilipa.fapiaobao.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.pilipa.fapiaobao.R;
import com.pilipa.fapiaobao.base.BaseFragment;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by lyt on 2017/10/17.
 */

public class MyCompanyDetailsPagerFragment extends BaseFragment{
    private static final String FRAG = "fragment_adapter";
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
    public static MyCompanyDetailsPagerFragment newInstance(String flag) {
        MyCompanyDetailsPagerFragment myCompanyDetailsPagerFragment = new MyCompanyDetailsPagerFragment();
        Bundle bundle = new Bundle();
        bundle.putString(FRAG,flag);
        myCompanyDetailsPagerFragment.setArguments(bundle);
        return myCompanyDetailsPagerFragment;
    }
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        String pos = getArguments().getString(FRAG);


        ImageView img_qr_code1 = (ImageView) view.findViewById(R.id.img_qr_code1);
        LinearLayout ll_qr_code2 = (LinearLayout) view.findViewById(R.id.ll_qr_code2);
        if(pos.equals("1")){
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
}
