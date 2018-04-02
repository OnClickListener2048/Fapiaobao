package com.pilipa.fapiaobao.databinding.base.activity;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.pilipa.fapiaobao.R;
import com.pilipa.fapiaobao.databinding.LayoutDefaultTitlebarBinding;
import com.pilipa.fapiaobao.databinding.base.viewmodel.BaseTitleViewModel;
import com.pilipa.fapiaobao.databinding.base.viewmodel.BaseViewModel;

/**
 * Created by edz on 2018/4/2.
 */

public abstract class BaseDatabindingTitleActivity<VB extends ViewDataBinding, VM extends BaseViewModel> extends BaseDatabindingActivity<VB, VM> implements BaseTitleViewModel.ITitleBar {


    @SuppressWarnings("unchecked")
    @Override
    public void setContentView(View contentView) {
        ViewGroup superContentView = (ViewGroup) LayoutInflater.from(this).inflate(R.layout.activity_base, (ViewGroup) contentView, false);
        Toolbar toolbar = (Toolbar) superContentView.findViewById(R.id.toolbar);
        RelativeLayout content = (RelativeLayout) superContentView.findViewById(R.id.content);
        content.addView(contentView, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        LayoutDefaultTitlebarBinding titlebarBinding = DataBindingUtil.inflate(LayoutInflater.from(this), getDefaultTitleBarLayout(), null, false);
        toolbar.addView(titlebarBinding.getRoot(), new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        initTitleBar(titlebarBinding.getRoot());
        titlebarBinding.setViewmodel(new BaseTitleViewModel(titlebarBinding, this));
        super.setContentView(superContentView);
    }


    public int getDefaultTitleBarLayout() {
        return R.layout.layout_default_titlebar;
    }

    public abstract void initTitleBar(View titleBar);


}
