package com.pilipa.fapiaobao.databinding.base.activity;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.pilipa.fapiaobao.databinding.base.viewmodel.BaseViewModel;

/**
 * Created by edz on 2018/3/30.
 */

public abstract class BaseDatabindingActivity<VB extends ViewDataBinding, VM extends BaseViewModel> extends AppCompatActivity {

    protected VB mBinding;
    protected VM mBaseViewModel;

    @SuppressWarnings("unchecked")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, getLayoutId());
        mBaseViewModel = (VM) new BaseViewModel(this, mBinding);
        init();
    }

    protected abstract void init();

    /**
     * @return
     */
    protected abstract int getLayoutId();

    protected VB getBinding() {
        return mBinding;
    }

    protected VM getViewModel() {
        return mBaseViewModel;
    }

    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        super.setContentView(layoutResID);


    }


}
