package com.pilipa.fapiaobao.ui.widget;

import android.content.Context;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.SlidingDrawer;

import com.example.mylibrary.utils.TLog;

/**
 * Created by dagou on 2018/2/4.
 */

public class DrawerRecyclerView extends RecyclerView {
    private static final String TAG = "DrawerRecyclerView";
    private float mDownY;
    private float mMoveY;
    private float mUpY;
    private SlidingDrawer slidingDrawer;
    private int mCenterX;
    private int mCenterY;

    public DrawerRecyclerView(Context context) {
        super(context);
        initView();
    }


    public DrawerRecyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public DrawerRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initView();
    }

    private void initView() {
    }

    public void setSlidingDrawer(SlidingDrawer slidingDrawer) {
        this.slidingDrawer = slidingDrawer;
    }


    @Override
    public boolean onTouchEvent(MotionEvent e) {
        if (!canScrollVertically(-1)) {
            int action = e.getAction();
            switch (action) {
                case MotionEvent.ACTION_DOWN: {
                    mDownY = e.getY();
                    TLog.d(TAG, "downY" + mDownY);
                    Rect rect = new Rect();
                    slidingDrawer.getHandle().getHitRect(rect);
                    mCenterX = rect.centerX();
                    mCenterY = rect.centerY();
                    e.setLocation(mCenterX, mCenterY);
                    slidingDrawer.onInterceptTouchEvent(e);
                    return false;
                }


                case MotionEvent.ACTION_MOVE: {
                    mMoveY = e.getY() - mMoveY;
                    if (mMoveY > 0) {
                        TLog.d(TAG, "向上滑动" + mMoveY);
                    } else {
                        TLog.d(TAG, "向下滑动" + mMoveY);
                    }
                    TLog.d(TAG, "moveY" + mMoveY);

                    if (e.getY() > mDownY) {
                        e.setLocation(e.getX(), e.getY() + mMoveY);
                        slidingDrawer.getHandle().onTouchEvent(e);
                        mMoveY = e.getY();
                        return false;
                    }
                    mMoveY = e.getY();
                }
                break;

                case MotionEvent.ACTION_UP: {
                    mUpY = e.getY();
                    TLog.d(TAG, "upY" + mUpY);
                }
                break;
                default:
            }


        }
        return super.onTouchEvent(e);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent e) {


        return super.onInterceptTouchEvent(e);
    }


}
