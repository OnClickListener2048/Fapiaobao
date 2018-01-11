package com.pilipa.fapiaobao.ui.widget;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.support.annotation.ColorInt;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.pilipa.fapiaobao.R;
import com.scwang.smartrefresh.layout.api.RefreshHeader;
import com.scwang.smartrefresh.layout.api.RefreshKernel;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.RefreshState;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;

/**
 * Created by edz on 2018/1/10.
 */

public class DemandHeader implements RefreshHeader {

    private final View view;
    private final AnimationDrawable animationDrawable;

    public DemandHeader(Context context) {
        view = View.inflate(context, R.layout.footer_refreshing_feedback, null);
        TextView textViewLoading = (TextView) view.findViewById(R.id.loading);
        textViewLoading.setVisibility(View.GONE);
        ImageView imageViewLoading = (ImageView) view.findViewById(R.id.loading_progress);
        animationDrawable = (AnimationDrawable) imageViewLoading.getDrawable();
    }

    @Override
    public void onPullingDown(float percent, int offset, int headerHeight, int extendHeight) {

    }

    @Override
    public void onReleasing(float percent, int offset, int headerHeight, int extendHeight) {

    }

    @NonNull
    @Override
    public View getView() {
        return view;
    }

    @Override
    public SpinnerStyle getSpinnerStyle() {
        return SpinnerStyle.Translate;
    }

    @Override
    public void setPrimaryColors(@ColorInt int... colors) {

    }

    @Override
    public void onInitialized(RefreshKernel kernel, int height, int extendHeight) {

    }

    @Override
    public void onHorizontalDrag(float percentX, int offsetX, int offsetMax) {

    }

    @Override
    public void onStartAnimator(RefreshLayout layout, int height, int extendHeight) {
        animationDrawable.start();
    }

    @Override
    public int onFinish(RefreshLayout layout, boolean success) {
        animationDrawable.stop();
        return 10;
    }

    @Override
    public boolean isSupportHorizontalDrag() {
        return false;
    }

    @Override
    public void onStateChanged(RefreshLayout refreshLayout, RefreshState oldState, RefreshState newState) {

    }
}
