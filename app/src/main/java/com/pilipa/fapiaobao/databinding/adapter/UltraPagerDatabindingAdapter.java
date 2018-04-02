package com.pilipa.fapiaobao.databinding.adapter;

import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.example.mylibrary.utils.ActivityUtils;
import com.example.mylibrary.utils.SPUtils;
import com.pilipa.fapiaobao.Constants.Config;
import com.pilipa.fapiaobao.R;
import com.pilipa.fapiaobao.base.BaseApplication;
import com.pilipa.fapiaobao.databinding.constant.Constant;
import com.pilipa.fapiaobao.databinding.lanucher.guide.GuideDatabindingActivity;
import com.pilipa.fapiaobao.databinding.main.MainDatabindingActivity;

import java.util.List;

/**
 * Created by edz on 2018/4/2.
 */

public class UltraPagerDatabindingAdapter extends PagerAdapter {


    private final List<Integer> list;

    public UltraPagerDatabindingAdapter(List<Integer> list) {
        this.list = list;
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
            viewGroup.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    container.getContext().startActivity(new Intent(container.getContext(), MainDatabindingActivity.class));
                    BaseApplication.set(Config.IS_FIRST_COMING, false);
                    SPUtils.getInstance().put(Constant.SHOW_GUIDE, true);
                    ActivityUtils.finishActivity(GuideDatabindingActivity.class);
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
