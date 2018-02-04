package com.pilipa.fapiaobao.ui.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.SlidingDrawer;

/**
 * Created by dagou on 2018/2/5.
 */

public class CusSlidingDrawer extends SlidingDrawer {
    public CusSlidingDrawer(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CusSlidingDrawer(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent event) {

        return super.onInterceptTouchEvent(event);
    }
}
