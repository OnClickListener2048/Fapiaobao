package com.pilipa.fapiaobao.base;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.example.mylibrary.utils.ImageUtils;
import com.example.mylibrary.utils.TLog;
import com.lzy.okgo.OkGo;
import com.pilipa.fapiaobao.R;
import com.pilipa.fapiaobao.interf.BaseView;
import com.pilipa.fapiaobao.ui.LoginActivity;
import com.pilipa.fapiaobao.utils.BitmapUtils;
import com.pilipa.fapiaobao.utils.TDevice;
import com.tbruyelle.rxpermissions2.RxPermissions;
import com.umeng.message.PushAgent;

import java.io.IOException;

import butterknife.ButterKnife;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by lyt on 2017/10/12.
 */

public abstract class BaseActivity extends AppCompatActivity implements View.OnClickListener, BaseView {
    protected LayoutInflater mInflater;
    private Fragment mFragment;
    private ActionBar mActionBar;
    private ProgressDialog progressDialog;
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
                        BaseApplication.showToast("网络异常");
                    }
                }
            }
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initWindow();
        initAMap();
        onBeforeSetContentLayout();
        if (getLayoutId() != 0) {
            setContentView(getLayoutId());
        }


        mInflater = getLayoutInflater();
        ButterKnife.bind(this);

        init(savedInstanceState);
        initView();
        initData();
        initProgressDialog();
        PushAgent.getInstance(this).onAppStart();
    }

    private void initProgressDialog() {
        if (progressDialog == null) {
            progressDialog = new ProgressDialog(this);
        }
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.setTitle("提示");
        progressDialog.setMessage("正在加载中");
    }

    public void showProgressDialog() {
        if (progressDialog != null) {
            if (!progressDialog.isShowing()) {
            progressDialog.show();
            }
        }
    }


    public void showProgressDialog(String description) {
        if (progressDialog != null) {
            if (!progressDialog.isShowing()) {
                progressDialog.setMessage(description);
                progressDialog.show();
            }
        }
    }

    public void updateDialogWithDescription(String description) {
        if (progressDialog != null) {
            progressDialog.setMessage(description);
        }
    }

    public void updateDialog(String description) {
        if (progressDialog != null) {
            progressDialog.setMessage(String.format(getString(R.string.upload_receipt_dialog),description));
        }
    }

    public void hideProgressDialog() {
        if (progressDialog != null) {
            if (progressDialog.isShowing()) {
            progressDialog.dismiss();
            }
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        TLog.log("OkGo.cancelTag(OkGo.getInstance().getOkHttpClient(),this);"+this.getClass().getSimpleName());
        OkGo.cancelTag(OkGo.getInstance().getOkHttpClient(),this);
    }

    public void login() {
        BaseApplication.showToast("请先登录");
        startActivity(new Intent(this, LoginActivity.class));
    }


    protected void onBeforeSetContentLayout() {

    }

    protected boolean hasActionBar() {
        return getSupportActionBar() != null;
    }

    protected int getLayoutId() {
        return 0;
    }

    protected View inflateView(int resId) {
        return mInflater.inflate(resId, null);
    }

    protected int getActionBarTitle() {
        return R.string.app_name;
    }

    protected boolean hasBackButton() {
        return false;
    }

    protected void init(Bundle savedInstanceState) {
    }
    protected void addFragment(int frameLayoutId, Fragment fragment) {
        if (fragment != null) {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            if (fragment.isAdded()) {
                if (mFragment != null) {
                    transaction.hide(mFragment).show(fragment);
                } else {
                    transaction.show(fragment);
                }
            } else {
                if (mFragment != null) {
                    transaction.hide(mFragment).add(frameLayoutId, fragment);
                } else {
                    transaction.add(frameLayoutId, fragment);
                }
            }
            mFragment = fragment;
            transaction.commit();
        }
    }
    protected void addCaptureFragment(int frameLayoutId, Fragment fragment) {
        if (fragment != null) {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.add(frameLayoutId, fragment).show(fragment);
            transaction.commit();
        }
    }

    protected void addCaptureFragment2(int frameLayoutId, Fragment fragment) {
        if (fragment != null) {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.add(frameLayoutId, fragment).show(fragment);
            transaction.commit();
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (null != this.getCurrentFocus()) {
            /**
             * 点击空白位置 隐藏软键盘
             */
            InputMethodManager mInputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            return mInputMethodManager.hideSoftInputFromWindow(this.getCurrentFocus().getWindowToken(), 0);
        }
        return super.onTouchEvent(event);
    }


    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            View v = getCurrentFocus();
            if (isShouldHideInput(v, ev)) {

                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                if (imm != null) {
                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                }
            }
            return super.dispatchTouchEvent(ev);
        }
        // 必不可少，否则所有的组件都不会有TouchEvent了
        if (getWindow().superDispatchTouchEvent(ev)) {
            return true;
        }
        return onTouchEvent(ev);
    }

    public boolean isShouldHideInput(View v, MotionEvent event) {
        if (v != null && (v instanceof EditText)) {
            int[] leftTop = {0, 0};
            //获取输入框当前的location位置
            v.getLocationInWindow(leftTop);
            int left = leftTop[0];
            int top = leftTop[1];
            int bottom = top + v.getHeight();
            int right = left + v.getWidth();
            if (event.getX() > left && event.getX() < right
                    && event.getY() > top && event.getY() < bottom) {
                // 点击的是输入框区域，保留点击EditText的事件
                return false;
            } else {
                return true;
            }
        }
        return false;
    }


    private void initWindow() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
//            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//            getWindow().getDecorView().setSystemUiVisibility(
//                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
//                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
//                            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION // hide nav bar
//                        | View.SYSTEM_UI_FLAG_FULLSCREEN // hide status bar
//                            | View.SYSTEM_UI_FLAG_IMMERSIVE
//            );

        }

    }


        protected void initActionBar(ActionBar actionBar) {
        if (actionBar == null)
            return;
        if (hasBackButton()) {
            mActionBar.setDisplayHomeAsUpEnabled(true);
            mActionBar.setHomeButtonEnabled(true);
        } else {
            actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_TITLE);
            actionBar.setDisplayUseLogoEnabled(false);
            int titleRes = getActionBarTitle();
            if (titleRes != 0) {
                actionBar.setTitle(titleRes);
            }
        }
    }


    public String upLoadReceipt(Uri uri) {
        ContentResolver cr = getContentResolver();
        Bitmap bmp;
        Bitmap bitmap = null;
        try {
            bmp = MediaStore.Images.Media.getBitmap(cr,uri);
            try {
                bitmap = ImageUtils.compressByQuality(bmp, (long) (200 * 1024),true);
            } catch (OutOfMemoryError error) {
                error.printStackTrace();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return BitmapUtils.bitmapToBase64(bitmap);
    }


    private void initAMap() {
        RxPermissions rxPermissions = new RxPermissions(this);
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
}
