package com.pilipa.fapiaobao.ui;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v7.app.AppCompatActivity;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;

import com.pilipa.fapiaobao.AppOperator;
import com.pilipa.fapiaobao.R;
import com.pilipa.fapiaobao.adapter.UltraPagerAdapter;
import com.pilipa.fapiaobao.base.BaseActivity;
import com.tmall.ultraviewpager.UltraViewPager;

/**
 * Created by lyt on 2017/10/12.
 */

public class LeadActivity extends AppCompatActivity {
    private int[] src = {R.mipmap.lead1,R.mipmap.lead2,R.mipmap.lead3};

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lead);

//        getWindow().getDecorView().setSystemUiVisibility(
//                UltraViewPager.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
//                        | View.SYSTEM_UI_FLAG_LOW_PROFILE
//                        | View.SYSTEM_UI_FLAG_FULLSCREEN
//                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
//                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);

        AppOperator.runOnThread(new Runnable() {
            @Override
            public void run() {
                downloadLeadImage();
            }
        });

        initWidget();

    }

    private void initWidget() {
        UltraViewPager ultraViewPager = (UltraViewPager)findViewById(R.id.ultra_viewpager);
        ultraViewPager.setScrollMode(UltraViewPager.ScrollMode.HORIZONTAL);
        PagerAdapter adapter = new UltraPagerAdapter(false,src,this);
        ultraViewPager.setAdapter(adapter);
        ultraViewPager.disableAutoScroll();

    }

    private void downloadLeadImage() {


    }

}
