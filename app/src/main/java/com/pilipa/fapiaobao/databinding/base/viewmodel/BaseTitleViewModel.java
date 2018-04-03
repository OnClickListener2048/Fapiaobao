package com.pilipa.fapiaobao.databinding.base.viewmodel;

import android.databinding.ObservableField;
import android.databinding.ObservableInt;
import android.databinding.ViewDataBinding;
import android.view.View;

/**
 * Created by edz on 2018/4/2.
 */

public class BaseTitleViewModel<VB extends ViewDataBinding> extends BaseViewModel {

    public ObservableInt mResLeftIcon = new ObservableInt();
    public ObservableInt mResRightIcon = new ObservableInt();
    public ObservableField<String> mResTitle = new ObservableField<>();

    private View.OnClickListener listener;
    private View.OnClickListener rightListener;
    private View.OnClickListener centerListener;

    public BaseTitleViewModel(VB vb) {
        super(vb);
    }

    public void onLeftIconClick(View view) {
        if (listener != null) {
            listener.onClick(view);
        }
    }

    public void onRightIconClick(View view) {
        if (rightListener != null) {
            rightListener.onClick(view);
        }
    }

    public void onTitleClick(View view) {
        if (centerListener != null) {
            centerListener.onClick(view);
        }
    }

    public void setOnLeftClickListener(View.OnClickListener listener) {
        this.listener = listener;
    }

    public void setOnRightClickListener(View.OnClickListener listener) {
        rightListener = listener;
    }

    public void setOnCenterClickListener(View.OnClickListener listener) {
        centerListener = listener;
    }


}
