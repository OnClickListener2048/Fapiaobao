package com.pilipa.fapiaobao.adapter;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.pilipa.fapiaobao.Constants.Config;
import com.pilipa.fapiaobao.MainActivity;
import com.pilipa.fapiaobao.R;
import com.pilipa.fapiaobao.base.BaseApplication;
import com.pilipa.fapiaobao.ui.LeadActivity;

/**
 * Created by lyt on 2017/10/12.
 */

public class UltraPagerAdapter extends PagerAdapter {
    private boolean isMultiScr;
    private int[] src;
    Activity activity;

    public UltraPagerAdapter(boolean isMultiScr, int[] src, LeadActivity leadActivity) {
        this.isMultiScr = isMultiScr;
        this.src = src;
        this.activity = leadActivity;
    }

    @Override
    public int getCount() {
        return src.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(final ViewGroup container, final int position) {
        RelativeLayout relativeLayout = new RelativeLayout(container.getContext());
        ImageView mImageView = new ImageView(container.getContext());
        mImageView.setScaleType(ImageView.ScaleType.FIT_XY);
        mImageView.setImageResource(src[position]);
        RelativeLayout.LayoutParams layoutParams =
                new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT
                        , ViewGroup.LayoutParams.MATCH_PARENT);
        if (position == src.length - 1) {
            ViewGroup viewGroup = (ViewGroup) LayoutInflater.from(container.getContext()).inflate(R.layout.activity_launch_pager, null);
            viewGroup.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    container.getContext().startActivity(new Intent(container.getContext(), MainActivity.class));
                    BaseApplication.set(Config.IS_FIRST_COMING, false);
                    activity.finish();
                }
            });
            relativeLayout.addView(viewGroup, layoutParams);
        } else {
            relativeLayout.addView(mImageView, layoutParams);
        }
        container.addView(relativeLayout);
        return relativeLayout;
    }



    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        RelativeLayout view = (RelativeLayout) object;
        container.removeView(view);
    }
}