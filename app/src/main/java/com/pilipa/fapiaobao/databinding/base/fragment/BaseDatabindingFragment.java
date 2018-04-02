package com.pilipa.fapiaobao.databinding.base.fragment;

import android.arch.lifecycle.LifecycleOwner;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.pilipa.fapiaobao.databinding.base.viewmodel.BaseViewModel;

/**
 * Created by edz on 2018/4/2.
 */

public abstract class BaseDatabindingFragment<VB extends ViewDataBinding, VM extends BaseViewModel> extends Fragment implements LifecycleOwner {


    protected VB mVB;
    protected VM mBaseViewModel;
    private View mRoot;

    @SuppressWarnings("unchecked")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (mRoot != null) {
            ViewGroup parent = (ViewGroup) mRoot.getParent();
            if (parent != null) {
                parent.removeView(mRoot);
            }
        } else {
            if (getLayoutId() != 0) {
                mVB = DataBindingUtil.inflate(inflater, getLayoutId(), container, false);
                mRoot = mVB.getRoot();
                mBaseViewModel = (VM) new BaseViewModel(mVB);
                getLifecycle().addObserver(mBaseViewModel);
            }
        }
        return mRoot;
    }

    protected abstract int getLayoutId();



}
