package com.pilipa.fapiaobao.databinding.base.viewmodel;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.OnLifecycleEvent;
import android.databinding.BaseObservable;
import android.databinding.ViewDataBinding;

import com.pilipa.fapiaobao.databinding.base.activity.BaseDatabindingActivity;

/**
 * Created by edz on 2018/4/2.
 */

public class BaseViewModel<VB extends ViewDataBinding> extends BaseObservable implements LifecycleObserver {

    private final VB vb;


    @SuppressWarnings("unchecked")
    public BaseViewModel(VB vb) {
        this.vb = vb;
    }

    public VB getVb() {
        return vb;
    }

    /**
     * 全局定位
     */
    protected void locate() {

    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    protected void onStart() {
    }

    protected BaseDatabindingActivity getDatabindingActivity() {
        return (BaseDatabindingActivity) vb.getRoot().getContext();
    }


}
