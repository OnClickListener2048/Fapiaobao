package com.pilipa.fapiaobao.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.pilipa.fapiaobao.R;
import com.pilipa.fapiaobao.interf.InitDataInResume;
import com.pilipa.fapiaobao.utils.TDevice;

/**
 * Created by edz on 2017/12/21.
 */

public abstract class BaseNoNetworkActivity extends BaseActivity implements InitDataInResume{

    public View viewNoNetwork;
    private ViewGroup container;
    private ViewGroup.LayoutParams layoutParams;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initNetErrorLayout();
    }


    private void initNetErrorLayout() {
        container = (ViewGroup) findViewById(R.id.container);
        viewNoNetwork = LayoutInflater.from(this).inflate(R.layout.layout_no_network, null);
        layoutParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);


        viewNoNetwork.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onNoNetworkLayoutClicks(v);
                checkIfNoNetwork();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        initDataInResume();
        checkIfNoNetwork();
    }

    protected void checkIfNoNetwork() {
        if (TDevice.hasInternet()) {
            container.removeView(viewNoNetwork);
        } else {
            if (viewNoNetwork.getParent()== null) {
                container.addView(viewNoNetwork, layoutParams);
            }
        }
    }



    public abstract void onNoNetworkLayoutClicks(View view);

    protected void showNetWorkErrorLayout() {
        if (viewNoNetwork.getParent()== null) {
            container.addView(viewNoNetwork, layoutParams);
        }
    }

    protected void hideNetWorkErrorLayout() {
        container.removeView(viewNoNetwork);
    }
}
