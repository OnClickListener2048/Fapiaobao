package com.pilipa.fapiaobao.databinding.base.fragment;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.example.mylibrary.utils.LogUtils;
import com.pilipa.fapiaobao.R;
import com.pilipa.fapiaobao.databinding.LayoutDefaultTitlebarBinding;
import com.pilipa.fapiaobao.databinding.base.viewmodel.BaseTitleViewModel;
import com.pilipa.fapiaobao.databinding.base.viewmodel.BaseViewModel;

/**
 * Created by edz on 2018/4/4.
 */

public abstract class BaseTitleDatabindingFragment<VB extends ViewDataBinding, VM extends BaseViewModel> extends BaseDatabindingFragment<VB, VM> {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View contentView = super.onCreateView(inflater, container, savedInstanceState);
        if (contentView != null) {
            LogUtils.d(getLayoutId() + "------" + container);
            ViewGroup superContentView = (ViewGroup) LayoutInflater.from(getContext()).inflate(R.layout.fragment_base, (ViewGroup) contentView, false);
            Toolbar toolbar = superContentView.findViewById(R.id.toolbar);
            RelativeLayout content = superContentView.findViewById(R.id.content);
            content.addView(contentView, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            LayoutDefaultTitlebarBinding titlebarBinding = DataBindingUtil.inflate(LayoutInflater.from(getContext()), getDefaultTitleBarLayout(), null, false);
            toolbar.addView(titlebarBinding.getRoot(), new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));

            BaseTitleViewModel baseTitleViewModel = new BaseTitleViewModel(titlebarBinding);
            titlebarBinding.setViewmodel(baseTitleViewModel);
            LogUtils.d("return superContentView;");
            initTitleBar(baseTitleViewModel);
            return superContentView;
        }
        LogUtils.d("super.onCreateView(inflater, container, savedInstanceState);");
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    protected abstract void initTitleBar(BaseTitleViewModel baseTitleViewModel);

    public int getDefaultTitleBarLayout() {
        return R.layout.layout_default_titlebar;
    }

}
