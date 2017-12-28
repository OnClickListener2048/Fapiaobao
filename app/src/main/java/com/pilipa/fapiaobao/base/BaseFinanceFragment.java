package com.pilipa.fapiaobao.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.pilipa.fapiaobao.R;
import com.pilipa.fapiaobao.interf.InitDataInResume;
import com.pilipa.fapiaobao.utils.TDevice;

/**
 * Created by edz on 2017/12/21.
 */

public abstract class BaseFinanceFragment extends BaseFragment implements InitDataInResume{


    private ViewGroup container;
    private View viewNoNetwork;
    private ViewGroup.LayoutParams layoutParams;


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initNetErrorLayout(view);
    }



    @Override
    public void onResume() {
        super.onResume();
        initDataInResume();
    }

    private void initNetErrorLayout(View view) {
        container = (ViewGroup) view.findViewById(R.id.container);
        viewNoNetwork = LayoutInflater.from(mContext).inflate(R.layout.layout_no_network, null);
        layoutParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);


        viewNoNetwork.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onNoNetworkLayoutClicks(v);
                checkIfNoNetwork();
            }


        });
    }

    protected abstract void onNoNetworkLayoutClicks(View v);

    private void checkIfNoNetwork() {
        if (TDevice.hasInternet()) {
            container.removeView(viewNoNetwork);
        } else {
            if (viewNoNetwork.getParent()== null) {
                container.addView(viewNoNetwork, layoutParams);
            }
        }
    }


    protected void showNetWorkErrorLayout() {
        if (viewNoNetwork.getParent()== null) {
            container.addView(viewNoNetwork, layoutParams);
        }
    }

    protected void hideNetWorkErrorLayout() {
        container.removeView(viewNoNetwork);
    }


}
