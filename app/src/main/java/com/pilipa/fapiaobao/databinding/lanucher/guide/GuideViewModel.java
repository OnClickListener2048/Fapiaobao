package com.pilipa.fapiaobao.databinding.lanucher.guide;

import android.content.Context;
import android.databinding.BindingAdapter;
import android.databinding.ObservableField;
import android.databinding.ViewDataBinding;
import android.support.v4.view.PagerAdapter;

import com.pilipa.fapiaobao.R;
import com.pilipa.fapiaobao.databinding.adapter.UltraPagerDatabindingAdapter;
import com.pilipa.fapiaobao.databinding.base.viewmodel.BaseViewModel;
import com.tmall.ultraviewpager.UltraViewPager;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by edz on 2018/4/2.
 */

public class GuideViewModel<VB extends ViewDataBinding> extends BaseViewModel {

    ObservableField<List<Integer>> mListObservableField = new ObservableField<>(getRes());


    public GuideViewModel(Context context, VB vb) {
        super(context, vb);
    }

    @BindingAdapter("data")
    public static void setData(UltraViewPager ultraViewPager, List<Integer> list) {
        ultraViewPager.setScrollMode(UltraViewPager.ScrollMode.HORIZONTAL);
        PagerAdapter adapter = new UltraPagerDatabindingAdapter(list, ultraViewPager.getContext());
        ultraViewPager.setAdapter(adapter);
        ultraViewPager.disableAutoScroll();
        ultraViewPager.setOnTouchListener((v, event) -> false);
    }

    private <T> List<Integer> getRes() {
        ArrayList<Integer> arrayList = new ArrayList<>(3);
        arrayList.add(R.mipmap.lead1);
        arrayList.add(R.mipmap.lead2);
        arrayList.add(R.mipmap.lead3);
        return arrayList;
    }


}
