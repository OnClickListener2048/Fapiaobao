package com.pilipa.fapiaobao.ui;

import android.content.Context;
import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;

import com.example.mylibrary.utils.TLog;
import com.pilipa.fapiaobao.AppOperator;
import com.pilipa.fapiaobao.MainActivity;
import com.pilipa.fapiaobao.R;
import com.pilipa.fapiaobao.adapter.UltraPagerAdapter;
import com.tmall.ultraviewpager.UltraViewPager;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;

/**
 * Created by lyt on 2017/10/12.
 */

public class LeadActivity extends AppCompatActivity {
    static String TAG = "LeadActivity";
    private int[] src = {R.mipmap.lead1,R.mipmap.lead2,R.mipmap.lead3};
    private int currentItem;

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
        ultraViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                TLog.d("onPageScrolled", position + "position");
                TLog.d("onPageScrolled", positionOffsetPixels + "positionOffsetPixels");
                if (position == 2 && positionOffsetPixels == 0) {
                    Observable
                            .empty()
                            .take(1)
                            .subscribeOn(AndroidSchedulers.mainThread())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(new Consumer<Object>() {
                                @Override
                                public void accept(@NonNull Object o) throws Exception {
                                    startActivity(new Intent(LeadActivity.this, MainActivity.class));
                                    LeadActivity.this.finish();
                                }
                            });

                }

            }

            @Override
            public void onPageSelected(int position) {
                currentItem = position;//获取位置，即第几页
                TLog.d("onPageSelected", position + "position");
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                TLog.d("onPageScrollStateChanged", state + "state");
            }
        });

        ultraViewPager.setOnTouchListener(new View.OnTouchListener() {


            @Override
            public boolean onTouch(View v, MotionEvent event) {

                return false;
            }
        });
    }



    private void downloadLeadImage() {


    }

}
