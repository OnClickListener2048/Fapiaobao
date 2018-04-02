package com.pilipa.fapiaobao.databinding.base.viewmodel;

import android.databinding.ObservableField;
import android.databinding.ObservableInt;
import android.databinding.ViewDataBinding;
import android.view.View;

/**
 * Created by edz on 2018/4/2.
 */

public class BaseTitleViewModel<VB extends ViewDataBinding> extends BaseViewModel {

    private ITitleBar iTitleBar;
    public ObservableInt mResLeftIcon = new ObservableInt(iTitleBar.getLeftIcon());
    public ObservableInt mResRightIcon = new ObservableInt(iTitleBar.getRightIcon());
    public ObservableField<String> mResTitle = new ObservableField<>(iTitleBar.getCenterTitle());

    private View.OnClickListener listener;
    private View.OnClickListener rightListener;
    private View.OnClickListener centerListener;

    public BaseTitleViewModel(VB vb, ITitleBar iTitleBar) {
        super(vb);
        this.iTitleBar = iTitleBar;
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

    public interface ITitleBar {
        int getLeftIcon();

        int getRightIcon();

        String getCenterTitle();
    }


}
