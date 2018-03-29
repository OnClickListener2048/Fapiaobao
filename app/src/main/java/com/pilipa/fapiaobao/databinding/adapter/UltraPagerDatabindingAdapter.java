package com.pilipa.fapiaobao.databinding.adapter;

import android.content.Context;
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
import com.pilipa.fapiaobao.databinding.base.activity.BaseDatabindingActivity;

import java.util.List;

/**
 * Created by edz on 2018/4/2.
 */

public class UltraPagerDatabindingAdapter extends PagerAdapter {


    private final List<Integer> list;
    private final BaseDatabindingActivity context;

    public UltraPagerDatabindingAdapter(List<Integer> list, Context context) {
        this.list = list;
        this.context = (BaseDatabindingActivity) context;
    }

    @Override
    public int getCount() {
        return list == null ? 0 : list.size();
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
        mImageView.setImageResource(list.get(position));
        RelativeLayout.LayoutParams layoutParams =
                new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT
                        , ViewGroup.LayoutParams.MATCH_PARENT);
        if (position == list.size() - 1) {
            ViewGroup viewGroup = (ViewGroup) LayoutInflater.from(container.getContext()).inflate(R.layout.activity_launch_pager, null);
            viewGroup.setOnClickListener(v -> {
                container.getContext().startActivity(new Intent(container.getContext(), MainActivity.class));
                BaseApplication.set(Config.IS_FIRST_COMING, false);
                context.finish();
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
