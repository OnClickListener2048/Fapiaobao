package com.pilipa.fapiaobao.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;

import com.example.mylibrary.utils.TLog;
import com.pilipa.fapiaobao.AppOperator;
import com.pilipa.fapiaobao.R;
import com.pilipa.fapiaobao.adapter.UltraPagerAdapter;
import com.tmall.ultraviewpager.UltraViewPager;

import java.util.concurrent.TimeUnit;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;

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
                if (position == 2) {
                    Observable.zip(Observable.just(position), Observable.just(positionOffsetPixels), new BiFunction<Integer, Integer, Boolean>() {
                        @Override
                        public Boolean apply(@NonNull Integer integer, @NonNull Integer integer2) throws Exception {
                            return integer == 2 && integer2 == 0;
                        }
                    })
                            .filter(new Predicate<Boolean>() {
                                @Override
                                public boolean test(@NonNull Boolean aBoolean) throws Exception {
                                    return aBoolean;
                                }
                            }).map(new Function<Boolean, Object>() {
                        @Override
                        public Object apply(@NonNull Boolean aBoolean) throws Exception {
                            return aBoolean;
                        }
                    }).take(1)
                            .throttleFirst(3000, TimeUnit.MILLISECONDS)
                            .subscribeOn(AndroidSchedulers.mainThread())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(new Observer<Object>() {
                                Disposable d;

                                @Override
                                public void onSubscribe(@NonNull Disposable d) {
                                    TLog.log("public void onSubscribe(@NonNull Disposable d) {");
                                    this.d = d;
                                }

                                @Override
                                public void onNext(@NonNull Object o) {
                                    TLog.log(" public void onNext(@NonNull Object o) {");
                                }

                                @Override
                                public void onError(@NonNull Throwable e) {
                                    TLog.log("public void onError(@NonNull Throwable e) {" + e.getMessage());
                                }

                                @Override
                                public void onComplete() {
                                    d.dispose();
                                    TLog.log("public void onComplete() {");
                                }
                            });

                }


//                if (position == 2 && positionOffsetPixels == 0) {
//                    Observable
//                            .empty()
//
//                            .throttleFirst(2000, TimeUnit.MILLISECONDS)
//                            .subscribeOn(AndroidSchedulers.mainThread())
//                            .observeOn(AndroidSchedulers.mainThread())
//                            .subscribe(new Observer<Object>() {
//                                Disposable d;
//
//                                @Override
//                                public void onSubscribe(@NonNull Disposable d) {
//                                    this.d = d;
//                                }
//
//                                @Override
//                                public void onNext(@NonNull Object o) {
//
//                                }
//
//                                @Override
//                                public void onError(@NonNull Throwable e) {
//
//                                }
//
//                                @Override
//                                public void onComplete() {
//                                    TLog.log(" public void onComplete() {");
//                                    d.dispose();
//                                }
//                            });
//
//                }

            }

            @Override
            public void onPageSelected(int position) {
                currentItem = position;//获取位置，即第几页
            }

            @Override
            public void onPageScrollStateChanged(int state) {
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
