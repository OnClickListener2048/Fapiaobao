package com.pilipa.fapiaobao.ui.widget;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.example.mylibrary.utils.ScreenUtils;
import com.example.mylibrary.utils.TLog;
import com.pilipa.fapiaobao.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by edz on 2018/2/2.
 */

public class TipsPopup extends PopupWindow implements PopupWindow.OnDismissListener {
    private static final String TAG = TipsPopup.class.getSimpleName();
    @Bind(R.id.tv_elec)
    TextView mTvElec;
    @Bind(R.id.tv_normal)
    TextView mTvNormal;
    @Bind(R.id.tv_special)
    TextView mTvSpecial;
    @Bind(R.id.llcontent)
    LinearLayout mLlcontent;
    @Bind(R.id.iv_elec)
    ImageView mIvElec;
    @Bind(R.id.iv_normal)
    ImageView mIvNormal;
    @Bind(R.id.iv_special)
    ImageView mIvSpecial;

    public TipsPopup(Activity context) {
        super(context);
        initView(context);
    }

    public TextView getTvElec() {
        return mTvElec;
    }

    public TextView getTvNormal() {
        return mTvNormal;
    }

    public TextView getTvSpecial() {
        return mTvSpecial;
    }

    public LinearLayout getLlcontent() {
        return mLlcontent;
    }

    public ImageView getIvElec() {
        return mIvElec;
    }

    public ImageView getIvNormal() {
        return mIvNormal;
    }

    public ImageView getIvSpecial() {
        return mIvSpecial;
    }

    public void showTvElec() {
        mTvElec.setVisibility(View.VISIBLE);
    }

    public void showTvNormal() {
        mTvNormal.setVisibility(View.VISIBLE);
    }

    public void showTvSpecial() {
        mTvSpecial.setVisibility(View.VISIBLE);
    }

    public void hideTvElec() {
        mTvElec.setVisibility(View.GONE);
    }

    public void hideTvNormal() {
        mTvNormal.setVisibility(View.GONE);
    }

    public void hideTvSpecial() {
        mTvSpecial.setVisibility(View.GONE);
    }

    public void moveToPosition(final View view, final float values) {
        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(view, "translationX", 0, values)
                .setDuration(300);
        objectAnimator.setInterpolator(new AccelerateDecelerateInterpolator());
        objectAnimator.start();
        objectAnimator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
//                RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) view.getLayoutParams();
//                layoutParams.leftMargin = (int) values;
//                view.setLayoutParams(layoutParams);
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
    }

    public void moveBack(final View view) {
        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(view, "translationX", 0, 0)
                .setDuration(400);
        objectAnimator.setInterpolator(new AccelerateDecelerateInterpolator());
        objectAnimator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {

            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        objectAnimator.start();

    }

    private int getPopupWindowSpareWidth() {
        return ScreenUtils.getScreenWidth() / 12 / 2;
    }

    public void moveToPosition(final View target, final View projection) {
        if (target.getVisibility() == View.VISIBLE) {
            target.setVisibility(View.GONE);
        } else if (target.getVisibility() == View.GONE) {
            target.setVisibility(View.VISIBLE);
            getIvElec().post(new Runnable() {
                @Override
                public void run() {
                    int triangelWidth = target.getRight();
                    TLog.d(TAG, "triangelWidth" + triangelWidth);
                    int projectionRight = projection.getRight();
                    TLog.d(TAG, "projectionRight" + projectionRight);
                    int projectionLeft = projection.getLeft();
                    TLog.d(TAG, "projectionLeft" + projectionLeft);
                    int start = projectionLeft + (projectionRight - projectionLeft) / 2;
                    int end = triangelWidth / 2 + getPopupWindowSpareWidth();
                    moveToPosition(target, start - end);
                }
            });
        }

    }

    public void updateContent(boolean elec, boolean normal, boolean special) {
        if (elec) {
            showTvElec();
        } else {
            hideTvElec();
        }

        if (normal) {
            showTvNormal();
        } else {
            hideTvNormal();
        }

        if (special) {
            showTvSpecial();
        } else {
            hideTvSpecial();
        }

        if (!(elec || normal || special)) {
            dismiss();
        }
    }

    private void initView(Context context) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View contentView = inflater.inflate(R.layout.popup_tips, null);
        this.setContentView(contentView);
        ButterKnife.bind(this, contentView);
        int h = ScreenUtils.getScreenHeight();
        int w = ScreenUtils.getScreenWidth();
        // 设置SelectPicPopupWindow的View
        // 设置SelectPicPopupWindow弹出窗体的宽
        // 设置SelectPicPopupWindow弹出窗体的高
        // 设置SelectPicPopupWindow弹出窗体可点击
        setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        setWidth(w * 11 / 12);
        this.setFocusable(false);
        this.setOutsideTouchable(false);
        // 刷新状态
        this.update();
        // 实例化一个ColorDrawable颜色为半透明
        ColorDrawable dw = new ColorDrawable();
        // 点back键和其他地方使其消失,设置了这个才能触发OnDismisslistener ，设置其他控件变化等操作
        this.setBackgroundDrawable(dw);
        setAnimationStyle(R.style.previewPopupAnimation);
        setOnDismissListener(this);

        mIvElec.setVisibility(View.GONE);
        mIvNormal.setVisibility(View.GONE);
        mIvSpecial.setVisibility(View.GONE);

    }


    @Override
    public void onDismiss() {

    }
}
