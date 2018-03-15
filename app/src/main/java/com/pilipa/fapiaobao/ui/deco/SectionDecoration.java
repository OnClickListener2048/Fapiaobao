package com.pilipa.fapiaobao.ui.deco;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.text.TextPaint;
import android.view.View;

import com.example.mylibrary.utils.ScreenUtils;
import com.example.mylibrary.utils.SizeUtils;
import com.pilipa.fapiaobao.R;
import com.pilipa.fapiaobao.base.BaseApplication;

/**
 * Created by edz on 2018/2/5.
 */

public class SectionDecoration extends RecyclerView.ItemDecoration {

    private final int mScreenWidth;
    private final Paint mRedPaint;
    private final ObtainTextCallback mObtainTextCallback;
    private final TextPaint mTextPaint;
    private final Paint.FontMetrics mFontMetrics;
    private int item_height;
    private Paint paint;

    public SectionDecoration(ObtainTextCallback obtainTextCallback) {
        mObtainTextCallback = obtainTextCallback;
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(BaseApplication.context().getResources().getColor(R.color.No_Action_bar_default_bg));
        item_height = SizeUtils.dp2px(25);
        mScreenWidth = ScreenUtils.getScreenWidth();

        mRedPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mRedPaint.setColor(Color.parseColor("#52ff0000"));

        mTextPaint = new TextPaint(Paint.ANTI_ALIAS_FLAG);
        mTextPaint.setColor(Color.BLACK);
        mTextPaint.setTextSize(SizeUtils.dp2px(15));
        mTextPaint.setTextAlign(Paint.Align.LEFT);
        mFontMetrics = new Paint.FontMetrics();
        mTextPaint.getFontMetrics(mFontMetrics);

    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDraw(c, parent, state);
        int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View v = parent.getChildAt(i);
            int position = parent.getChildAdapterPosition(v);

            int top = v.getTop() - item_height;
            int bottom = v.getTop();


            c.drawRect(0, top, mScreenWidth, bottom, paint);
            c.drawText(mObtainTextCallback.getText(position), SizeUtils.dp2px(10), bottom - SizeUtils.dp2px(2), mTextPaint);

        }
    }

    @Override
    public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDrawOver(c, parent, state);
        View child0 = parent.getChildAt(0);
        int position = parent.getChildAdapterPosition(child0);
        String text = mObtainTextCallback.getText(position);
        if (child0.getBottom() <= item_height) {
            c.drawRect(0, 0, mScreenWidth, child0.getBottom(), paint);
            c.drawText(text, SizeUtils.dp2px(10), child0.getBottom() - SizeUtils.dp2px(2), mTextPaint);
        } else {
            c.drawRect(0, 0, mScreenWidth, item_height, paint);
            c.drawText(text, SizeUtils.dp2px(10), item_height - SizeUtils.dp2px(2), mTextPaint);
        }
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        outRect.top = item_height;
    }


    public interface ObtainTextCallback {
        String getText(int position);
    }


}
