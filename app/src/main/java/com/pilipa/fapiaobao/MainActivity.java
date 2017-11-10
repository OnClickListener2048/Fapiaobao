package com.pilipa.fapiaobao;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.example.mylibrary.utils.AppUtils;
import com.example.mylibrary.utils.TLog;
import com.lzy.okgo.model.Progress;
import com.lzy.okgo.request.base.Request;
import com.pilipa.fapiaobao.base.BaseActivity;
import com.pilipa.fapiaobao.base.BaseApplication;
import com.pilipa.fapiaobao.compat.MediaStoreCompat;
import com.pilipa.fapiaobao.net.Api;
import com.pilipa.fapiaobao.net.Constant;
import com.pilipa.fapiaobao.net.bean.update.VersionMode;
import com.pilipa.fapiaobao.receiver.PackageInstallReceiver;
import com.pilipa.fapiaobao.ui.fragment.NavFragment;
import com.pilipa.fapiaobao.ui.widget.NavigationButton;
import com.pilipa.fapiaobao.utils.TDevice;
import com.tbruyelle.rxpermissions2.RxPermissions;

import java.io.File;

import butterknife.Bind;
import butterknife.ButterKnife;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class MainActivity extends BaseActivity implements NavFragment.OnNavigationReselectListener {

    @Bind(R.id.bg)
    FrameLayout bg;
    private NavFragment mNavBar;

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

    private PopupWindow popWnd;
    private ProgressBar progressBar;
    private TextView tvProgress;
    private LinearLayout ll_progress;
    private PopupWindow popUpProgress;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void initView() {
        FragmentManager manager = getSupportFragmentManager();
        mNavBar = ((NavFragment) manager.findFragmentById(R.id.fag_nav));
        mNavBar.setup(this, manager, R.id.main_container, this);
        PackageInstallReceiver.registerReceiver(this);
    }

    @Override
    public void initData() {
        initAMap();
        checkOutVersion();
        bg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        TDevice.UPDATE = false;
        TDevice.IS_UPDATE = false;
        PackageInstallReceiver.unregisterReceiver(this);
    }

    @Override
    public void onReselect(NavigationButton navigationButton) {

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }


    private void checkOutVersion() {
        Api.getUpdateInfo(new Api.BaseViewCallback<VersionMode>() {
            @Override
            public void setData(VersionMode versionMode) {
                if (versionMode.getStatus() == 200) {
                    if (versionMode.getData() != null) {
                        if (Integer.valueOf(versionMode.getData().getVersion()) > TDevice.getVersionCode()) {
                            showPopup(versionMode.getData());
                            bg.setAlpha(0.4f);
                            bg.setVisibility(View.VISIBLE);
                        }
                    }
                }
            }
        });
    }

    private void showPopupProgress() {
        View contentView = LayoutInflater.from(this).inflate(R.layout.popup_progress, null);
        progressBar = (ProgressBar) contentView.findViewById(R.id.progressbar);
        progressBar.setMax(100);
        tvProgress = (TextView) contentView.findViewById(R.id.text_progressbar);
        ll_progress = (LinearLayout) contentView.findViewById(R.id.ll_progress);

        popUpProgress = new PopupWindow(this);
        popUpProgress.setContentView(contentView);
        popUpProgress.setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);
        popUpProgress.setBackgroundDrawable(getResources().getDrawable(R.drawable.bg_rect));
        popUpProgress.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        popUpProgress.setOutsideTouchable(false);
        popUpProgress.showAtLocation(View.inflate(this, R.layout.fragment_finance, null), Gravity.CENTER, 0, 0);
    }


    private void showPopup(final VersionMode.DataBean dataBean) {
        View contentView = LayoutInflater.from(this).inflate(R.layout.popup_download_alert, null);
        popWnd = new PopupWindow(this);
        popWnd.setContentView(contentView);
        popWnd.setBackgroundDrawable(getResources().getDrawable(R.drawable.bg_rect));
        popWnd.setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);
        popWnd.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        popWnd.setOutsideTouchable(false);
        popWnd.showAtLocation(View.inflate(this, R.layout.fragment_finance, null), Gravity.CENTER, 0, 0);
        TextView tvContent = (TextView) contentView.findViewById(R.id.remark);
        tvContent.setText(dataBean.getRemarks());

        contentView.findViewById(R.id.update).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popWnd.dismiss();
                RxPermissions rxPermissions = new RxPermissions(MainActivity.this);
                rxPermissions
                        .request(Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE)
                        .subscribe(new Observer<Boolean>() {
                            @Override
                            public void onSubscribe(Disposable d) {

                            }

                            @Override
                            public void onNext(Boolean aBoolean) {
                                if (aBoolean) {
                                    Api.downloadApk(Constant.VERSION_BASE_URL+dataBean.getUrl(), new Api.BaseViewCallBackWithProgress<File>() {
                                        @Override
                                        public void setProgress(Progress progress) {
                                            TLog.log(progress.currentSize+"progress.currentSize");
                                            TLog.log(progress.totalSize+"progress.totalSize");

                                            progressBar.setProgress((int) ((progress.currentSize / (float) progress.totalSize) * 100));
                                            tvProgress.setText((int) ((((float) progress.currentSize / (float) progress.totalSize) * 100)) + "%");
                                        }

                                        @Override
                                        public void onStart(Request<File, ? extends Request> request) {
                                            showPopupProgress();
                                        }

                                        @Override
                                        public void onFinish() {

                                            bg.setVisibility(View.GONE);
                                        }

                                        @Override
                                        public void setData(File file) {
                                            TDevice.openFile(MainActivity.this,file);
                                            popUpProgress.dismiss();
                                        }
                                    });
                                }
                            }

                            @Override
                            public void onError(Throwable e) {
                                bg.setVisibility(View.INVISIBLE);
                                if (dataBean.getForced().equals("1")) {
                                    MainActivity.this.finish();
                                    System.exit(0);
                                } else {
                                    popWnd.dismiss();
                                }
                            }

                            @Override
                            public void onComplete() {

                            }
                        });
            }
        });

        contentView.findViewById(R.id.cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bg.setVisibility(View.INVISIBLE);
                if (dataBean.getForced().equals("1")) {
                    MainActivity.this.finish();
                    System.exit(0);
                } else {
                    popWnd.dismiss();
                }
            }
        });
    }

    private void initAMap() {
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
