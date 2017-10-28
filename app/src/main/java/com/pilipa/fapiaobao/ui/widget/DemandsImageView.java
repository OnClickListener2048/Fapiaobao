package com.pilipa.fapiaobao.ui.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;

/**
 * Created by edz on 2017/10/27.
 */

public class DemandsImageView extends android.support.v7.widget.AppCompatImageView {


    public DemandsImageView(Context context) {
        super(context);
    }

    public DemandsImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public DemandsImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int mode = MeasureSpec.getMode(widthMeasureSpec);
        int size = (MeasureSpec.getSize(widthMeasureSpec)*12/15)+10;
        int i = MeasureSpec.makeMeasureSpec(size, mode);
        super.onMeasure(widthMeasureSpec, i);
    }
}
