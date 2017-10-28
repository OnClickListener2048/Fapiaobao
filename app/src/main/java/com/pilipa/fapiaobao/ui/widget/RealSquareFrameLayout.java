package com.pilipa.fapiaobao.ui.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.FrameLayout;

/**
 * Created by edz on 2017/10/27.
 */

public class RealSquareFrameLayout extends FrameLayout {
    public RealSquareFrameLayout(Context context) {
        super(context);
    }

    public RealSquareFrameLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        super.onMeasure(widthMeasureSpec, widthMeasureSpec);
    }
}
