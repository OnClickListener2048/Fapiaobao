package com.pilipa.fapiaobao.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mylibrary.utils.TLog;
import com.pilipa.fapiaobao.R;
import com.pilipa.fapiaobao.interf.InitDataInResume;
import com.pilipa.fapiaobao.utils.TDevice;

/**
 * Created by edz on 2017/12/21.
 */

public abstract class BaseFinanceFragment extends BaseFragment implements InitDataInResume{
    private static final String TAG = BaseFinanceFragment.class.getSimpleName();

    private ViewGroup container;
    private View viewNoNetwork;
    private ViewGroup.LayoutParams layoutParams;
    private boolean mIsInit;


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        TLog.d(TAG, "onViewCreated");
        if (!mIsInit) {
        initNetErrorLayout(view);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        return super.onCreateView(inflater, container, savedInstanceState);
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
                checkIfNoNetwork();
            }


        });
        mIsInit = true;
    }

    protected abstract void onNoNetworkLayoutClicks(View v);

    private void checkIfNoNetwork() {
        if (TDevice.hasInternet()) {
            container.removeView(viewNoNetwork);
            onNoNetworkLayoutClicks(viewNoNetwork);
        } else {
            BaseApplication.showToast(R.string.connect_error);
            if (viewNoNetwork.getParent()== null) {
                container.addView(viewNoNetwork, layoutParams);
            }
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        if (viewNoNetwork.getParent() != null) {
            TLog.d("onDetach", "hideNetWorkErrorLayout() ");
            container.removeView(viewNoNetwork);
        }
    }

    protected void showNetWorkErrorLayout() {
        if (viewNoNetwork.getParent()== null) {
            TLog.d("showNetWorkErrorLayout()", "showNetWorkErrorLayout() ");
            container.addView(viewNoNetwork, layoutParams);
        }
    }

    protected void hideNetWorkErrorLayout() {
        if (viewNoNetwork.getParent() != null) {
            TLog.d("BaseFinanceFragment", "hideNetWorkErrorLayout() ");
            container.removeView(viewNoNetwork);
        }
    }


}
