package com.pilipa.fapiaobao.databinding.lanucher.guide;

import android.databinding.BindingAdapter;
import android.databinding.ObservableArrayList;
import android.databinding.ViewDataBinding;
import android.support.v4.view.PagerAdapter;
import android.view.MotionEvent;
import android.view.View;

import com.pilipa.fapiaobao.R;
import com.pilipa.fapiaobao.databinding.adapter.UltraPagerDatabindingAdapter;
import com.pilipa.fapiaobao.databinding.base.viewmodel.BaseViewModel;
import com.tmall.ultraviewpager.UltraViewPager;

import java.util.List;

/**
 * Created by edz on 2018/4/2.
 */

public class GuideViewModel<VB extends ViewDataBinding> extends BaseViewModel {

    public ObservableArrayList<Integer> mListObservableField = new ObservableArrayList<>();

    public GuideViewModel(VB vb) {
        super(vb);
        getRes();
    }

    @BindingAdapter({"data"})
    public static void setData(UltraViewPager ultraViewPager, List<Integer> list) {
        ultraViewPager.setScrollMode(UltraViewPager.ScrollMode.HORIZONTAL);
        PagerAdapter adapter = new UltraPagerDatabindingAdapter(list);
        ultraViewPager.setAdapter(adapter);
        ultraViewPager.disableAutoScroll();
        ultraViewPager.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return false;
            }
        });
    }

    private void getRes() {
        mListObservableField.add(R.mipmap.lead1);
        mListObservableField.add(R.mipmap.lead2);
        mListObservableField.add(R.mipmap.lead3);
    }


}
