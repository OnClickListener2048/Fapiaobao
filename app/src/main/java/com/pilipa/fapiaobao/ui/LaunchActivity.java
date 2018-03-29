package com.pilipa.fapiaobao.ui;

import android.Manifest;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.example.mylibrary.utils.LogUtils;
import com.example.mylibrary.utils.SPUtils;
import com.example.mylibrary.utils.TLog;
import com.lzy.okgo.model.Response;
import com.pilipa.fapiaobao.Constants.Config;
import com.pilipa.fapiaobao.MainActivity;
import com.pilipa.fapiaobao.base.BaseApplication;
import com.pilipa.fapiaobao.net.Api;
import com.pilipa.fapiaobao.net.Constant;
import com.pilipa.fapiaobao.net.bean.me.NormalBean;
import com.pilipa.fapiaobao.net.callback.JsonCallBack;
import com.pilipa.fapiaobao.utils.TDevice;
import com.tbruyelle.rxpermissions2.RxPermissions;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by lyt on 2017/10/12.
 */

public class LaunchActivity extends AppCompatActivity {
    private static final int REDIRECT_DELAY = 2000;

    private static final int UI_ANIMATION_DELAY = 100;
    private static final int APK_INSTALL_CODE = 300;
    private final Handler mHideHandler = new Handler();
    public AMapLocationClient mLocationClient = null;
    public AMapLocationClientOption mLocationOption = new AMapLocationClientOption();
    public AMapLocationListener mLocationListener = new AMapLocationListener() {


        @Override
        public void onLocationChanged(AMapLocation aMapLocation) {
            if (aMapLocation != null) {
                if (aMapLocation.getErrorCode() == 0) {
                    //可在其中解析amapLocation获取相应内容。
                    TLog.log(aMapLocation.toString());
                    TLog.log(aMapLocation.getCity());
                    TLog.log(aMapLocation.getProvince());
                    BaseApplication.set("location", aMapLocation.getCity());
                } else {
                    //定位失败时，可通过ErrCode（错误码）信息来确定失败的原因，errInfo是错误信息，详见错误码表。
                    Log.e("AmapError", "location Error, ErrCode:"
                            + aMapLocation.getErrorCode() + ", errInfo:"
                            + aMapLocation.getErrorInfo());
                    if (!TDevice.hasInternet()) {
                        BaseApplication.showToast("网络异常，定位失败");
                    }
                }
            }
        }
    };
    private View mContentView;
    private Runnable mHidePart2Runnable = new Runnable() {
        @Override
        public void run() {
//            mContentView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LOW_PROFILE|
//                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
//                            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
//                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
//                            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION // hide nav bar
//                            | View.SYSTEM_UI_FLAG_FULLSCREEN // hide status bar
//                            | View.SYSTEM_UI_FLAG_IMMERSIVE
//            );
        }
    };
    private Runnable mRedirectToHandler = new Runnable() {
        @Override
        public void run() {
            if (BaseApplication.get(Config.IS_FIRST_COMING, true)) {
                startActivity(new Intent(LaunchActivity.this, LeadActivity.class));
                finish();
            } else {
                redirectTo();
            }
        }
    };
    private final Runnable mHideRunnable = new Runnable() {
        @Override
        public void run() {
            hide();
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_launch);

//        mContentView = findViewById(R.id.fullscreen_content);
//        TextView version = (TextView) findViewById(R.id.version);
//        version.setText("v"+TDevice.getVersionName());
        initAMap();
        boolean needCacu = SPUtils.getInstance().getBoolean("needCacu", true);
        if (needCacu) {
            uploadData();
        }
    }

    private void uploadData() {
        if (TDevice.hasInternet()) {
            if (getValue() == null) {
                LogUtils.d("PIAOBAO_CHANNEL==null");
                return;
            }

            Api.cacu(getValue(), this, new JsonCallBack<NormalBean>(NormalBean.class) {

                @Override
                public void onSuccess(Response<NormalBean> response) {
                    NormalBean body = response.body();
                    if (body != null) {
                        if (body.getStatus() == Constant.REQUEST_SUCCESS) {
                            SPUtils.getInstance().put("needCacu", false);
                        }
                    }
                }
            });
        }
    }

    private String getValue() {
        ApplicationInfo appInfo = null;
        try {
            appInfo = this.getPackageManager()
                    .getApplicationInfo(getPackageName(),
                            PackageManager.GET_META_DATA);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        String msg = null;
        if (appInfo != null) {
            msg = appInfo.metaData.getString("PIAOBAO_CHANNEL");
        }
        return msg;
    }

    private void redirectTo() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    private void initAMap() {
        RxPermissions rxPermissions = new RxPermissions(LaunchActivity.this);
        rxPermissions.request(Manifest.permission.ACCESS_FINE_LOCATION).subscribe(new Observer<Boolean>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Boolean aBoolean) {
                if (aBoolean) {
                    //初始化定位
                    mLocationClient = new AMapLocationClient(BaseApplication.context());
                    //设置定位回调监听
                    mLocationClient.setLocationListener(mLocationListener);
                    //设置定位模式为AMapLocationMode.Hight_Accuracy，高精度模式。
                    mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
                    //获取一次定位结果：
                    //该方法默认为false。
                    mLocationOption.setOnceLocation(true);
                    //获取最近3s内精度最高的一次定位结果：
                    //设置setOnceLocationLatest(boolean b)接口为true，启动定位时SDK会返回最近3s内精度最高的一次定位结果。如果设置其为true，setOnceLocation(boolean b)接口也会被设置为true，反之不会，默认为false。
                    mLocationOption.setOnceLocationLatest(true);
                    //设置是否返回地址信息（默认返回地址信息）
                    mLocationOption.setNeedAddress(true);
                    //单位是毫秒，默认30000毫秒，建议超时时间不要低于8000毫秒。
                    mLocationOption.setHttpTimeOut(20000);
                    //关闭缓存机制
                    mLocationOption.setLocationCacheEnable(false);
                    //给定位客户端对象设置定位参数
                    mLocationClient.setLocationOption(mLocationOption);
                    //启动定位
                    mLocationClient.startLocation();
                }
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });


    }



    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        delayedHide(100);
    }

    private void delayedHide(int delayMillis) {
        mHideHandler.removeCallbacks(mHideRunnable);
        mHideHandler.postDelayed(mHideRunnable, delayMillis);
    }

    private void hide() {
        // Hide UI firs
        mHideHandler.postDelayed(mHidePart2Runnable, UI_ANIMATION_DELAY);
        mHideHandler.postDelayed(mRedirectToHandler, REDIRECT_DELAY);
    }
}

