package com.pilipa.fapiaobao.ui;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.pilipa.fapiaobao.R;
import com.pilipa.fapiaobao.adapter.UltraPagerAdapter;
import com.tmall.ultraviewpager.UltraViewPager;

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
        initWidget();
        if (Build.VERSION.SDK_INT >= 19) {
            View decorView = getWindow().getDecorView();
            int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
            decorView.setSystemUiVisibility(option);
        }


        if (Build.VERSION.SDK_INT >= 21) {
            View decorView = getWindow().getDecorView();
            int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
            decorView.setSystemUiVisibility(option);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }
    }

    private void initWidget() {
        UltraViewPager ultraViewPager = (UltraViewPager)findViewById(R.id.ultra_viewpager);
        ultraViewPager.setScrollMode(UltraViewPager.ScrollMode.HORIZONTAL);
        PagerAdapter adapter = new UltraPagerAdapter(false,src,this);
        ultraViewPager.setAdapter(adapter);
        ultraViewPager.disableAutoScroll();
//        ultraViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
//            @Override
//            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
//                if (position == 2) {
//                    Observable.zip(Observable.just(position), Observable.just(positionOffsetPixels), (integer, integer2) -> integer == 2 && integer2 == 0)
//                            .filter(aBool3ean -> aBool3ean)
//                            .map((Function<Boolean, Object>) aBoolean -> aBoolean).take(1)
//                            .throttleFirst(3000, TimeUnit.MILLISECONDS)
//                            .subscribeOn(AndroidSchedulers.mainThread())
//                            .observeOn(AndroidSchedulers.mainThread())
//                            .subscribe(new Observer<Object>() {
//                                Disposable d;
//
//                                @Override
//                                public void onSubscribe(@NonNull Disposable d) {
//                                    TLog.log("public void onSubscribe(@NonNull Disposable d) {");
//                                    this.d = d;
//                                }
//
//                                @Override
//                                public void onNext(@NonNull Object o) {
//                                    TLog.log(" public void onNext(@NonNull Object o) {");
//                                }
//
//                                @Override
//                                public void onError(@NonNull Throwable e) {
//                                    TLog.log("public void onError(@NonNull Throwable e) {" + e.getMessage());
//                                }
//
//                                @Override
//                                public void onComplete() {
//                                    d.dispose();
//                                    TLog.log("public void onComplete() {");
//                                }
//                            });
//
//                }
//
//
////                if (position == 2 && positionOffsetPixels == 0) {
////                    Observable
////                            .empty()
////
////                            .throttleFirst(2000, TimeUnit.MILLISECONDS)
////                            .subscribeOn(AndroidSchedulers.mainThread())
////                            .observeOn(AndroidSchedulers.mainThread())
////                            .subscribe(new Observer<Object>() {
////                                Disposable d;
////
////                                @Override
////                                public void onSubscribe(@NonNull Disposable d) {
////                                    this.d = d;
////                                }
////
////                                @Override
////                                public void onNext(@NonNull Object o) {
////
////                                }
////
////                                @Override
////                                public void onError(@NonNull Throwable e) {
////
////                                }
////
////                                @Override
////                                public void onComplete() {
////                                    TLog.log(" public void onComplete() {");
////                                    d.dispose();
////                                }
////                            });
////
////                }
//
//            }
//
//            @Override
//            public void onPageSelected(int position) {
//                currentItem = position;//获取位置，即第几页
//            }
//
//            @Override
//            public void onPageScrollStateChanged(int state) {
//            }
//        });
//
//        ultraViewPager.setOnTouchListener((v, event) -> false);

    }



    private void downloadLeadImage() {
    }

}
