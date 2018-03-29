package com.pilipa.fapiaobao.databinding.base.viewmodel;

import android.content.Context;
import android.databinding.BaseObservable;
import android.databinding.ViewDataBinding;

/**
 * Created by edz on 2018/4/2.
 */

public class BaseViewModel<VB extends ViewDataBinding> extends BaseObservable {

    private final VB vb;
    private Context mContext;


    public BaseViewModel(Context context, VB vb) {
        mContext = context;
        this.vb = vb;
    }

    public VB getVb() {
        return vb;
    }

    public Context getContext() {
        return mContext;
    }

    /**
     * 全局定位
     */
    protected void locate() {

    }
}
