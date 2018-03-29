package com.pilipa.fapiaobao;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.blog.www.guideview.Component;
import com.blog.www.guideview.Guide;
import com.blog.www.guideview.GuideBuilder;
import com.example.mylibrary.utils.TLog;
import com.lzy.okgo.model.Progress;
import com.lzy.okgo.request.base.Request;
import com.pilipa.fapiaobao.base.BaseActivity;
import com.pilipa.fapiaobao.base.BaseApplication;
import com.pilipa.fapiaobao.net.Api;
import com.pilipa.fapiaobao.net.Constant;
import com.pilipa.fapiaobao.net.bean.update.VersionMode;
import com.pilipa.fapiaobao.receiver.PackageInstallReceiver;
import com.pilipa.fapiaobao.ui.component.SimpleComponent;
import com.pilipa.fapiaobao.ui.dialog.LoadingDialog;
import com.pilipa.fapiaobao.ui.fragment.FinanceFragment;
import com.pilipa.fapiaobao.ui.fragment.NavFragment;
import com.pilipa.fapiaobao.ui.widget.NavigationButton;
import com.pilipa.fapiaobao.utils.TDevice;
import com.tbruyelle.rxpermissions2.RxPermissions;

import java.io.File;
import java.util.concurrent.TimeUnit;

import butterknife.Bind;
import butterknife.ButterKnife;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

import static com.pilipa.fapiaobao.ui.constants.Constant.FORCE_UPDATE;
import static com.pilipa.fapiaobao.ui.constants.Constant.IS_FIRST_IN_MAIN;


public class MainActivity extends BaseActivity implements NavFragment.OnNavigationReselectListener {
    private static final String TAG = MainActivity.class.getSimpleName();
    private static final int UPDATE = 924;
    public NavFragment mNavBar;
    @Bind(R.id.bg)
    FrameLayout bg;
    private PopupWindow popWnd;
    private ProgressBar progressBar;
    private TextView tvProgress;
    private LinearLayout ll_progress;
    private PopupWindow popUpProgress;
    private Guide guide;
    private TextView textViewCancel;
    private TextView tvContent;
    private View popupContentView;
    private boolean update;
    private LoadingDialog progressDialog2;
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == UPDATE) {
                if (TDevice.UPDATE) {
                    checkOutVersion();
                }
            }
        }
    };
    private long mExitTime;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void initView() {


        initProgressDialog2();
        checkOutVersion();
        FragmentManager manager = getSupportFragmentManager();
        mNavBar = ((NavFragment) manager.findFragmentById(R.id.fag_nav));
        mNavBar.setup(this, manager, R.id.main_container, this);
        PackageInstallReceiver.registerReceiver(this);
//        mHandler.sendEmptyMessageDelayed(UPDATE, 3000);
        bg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        initPopup();
        initPopupProgress();
    }

    private void initProgressDialog2() {
        if (progressDialog2 == null) {
            progressDialog2 = new LoadingDialog(this, R.style.CustomDialog);
        }
        progressDialog2.setOnKeyListener(this);
    }

    public void showProgressDialog2() {
        if (isFinishing()) return;
        if (progressDialog2 == null) return;
        if (progressDialog2.isShowing()) return;
        progressDialog2.show();
    }

    public void hideProgressDialog2() {

        Observable.timer(500, TimeUnit.MILLISECONDS)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnError(new Consumer<Throwable>() {
                    @Override
                    public void accept(@NonNull Throwable throwable) throws Exception {
                    }
                })
                .subscribe(new Consumer<Object>() {
                    @Override
                    public void accept(@NonNull Object o) throws Exception {
                        if (progressDialog2 != null) {
                            if (progressDialog2.isShowing()) {
                                progressDialog2.dismiss();
                            }
                        }
                    }
                });
    }

    public void showGuideViewFromChildFragment() {
        if (BaseApplication.get(IS_FIRST_IN_MAIN, true)) {
            mNavBar.navItemPublish.post(new Runnable() {
                @Override
                public void run() {
                    showGuideView();
                }
            });
        }
    }

    @Override
    public void initData() {
    }

    public void showGuideView() {
        GuideBuilder builder = new GuideBuilder();
        builder.setTargetView(mNavBar.navItemPublish)
                .setAlpha(150)
                .setHighTargetGraphStyle(Component.CIRCLE)
                .setOverlayTarget(false)
                .setOutsideTouchable(false);

        builder.setOnVisibilityChangedListener(new GuideBuilder.OnVisibilityChangedListener() {
            @Override
            public void onShown() {

            }

            @Override
            public void onDismiss() {
                showGuideView2();
            }
        });

        builder.addComponent(new SimpleComponent(){
            @Override
            public View getView(LayoutInflater inflater) {
                ImageView imageView = new ImageView(inflater.getContext());
                imageView.setImageResource(R.mipmap.finance_002);
                return imageView;
            }

            @Override
            public int getYOffset() {
                return -180;
            }

            @Override
            public int getXOffset() {
                return 130;
            }
        });
        guide = builder.createGuide();
        guide.setShouldCheckLocInWindow(false);
        guide.show(MainActivity.this);
    }

    public void showGuideView2() {

        FinanceFragment fragment = (FinanceFragment) mNavBar.navItemTex.getFragment();
        RecyclerView.LayoutManager layoutManager = fragment.recyclerview.getLayoutManager();
        View childAt = layoutManager.getChildAt(1);
        if(childAt != null){
            final GuideBuilder builder1 = new GuideBuilder();

            builder1.setTargetView(childAt)
                    .setAlpha(150)
                    .setHighTargetGraphStyle(Component.ROUNDRECT)
                    .setOverlayTarget(false)
                    .setOutsideTouchable(false);
            builder1.setOnVisibilityChangedListener(new GuideBuilder.OnVisibilityChangedListener() {
                @Override
                public void onShown() {
                }

                @Override
                public void onDismiss() {
                    BaseApplication.set(IS_FIRST_IN_MAIN,false);
                    if (popWnd.isShowing()) return;
                    if (!update) return;
                    bg.setAlpha(0.4f);
                    bg.setVisibility(View.VISIBLE);
                    popWnd.showAtLocation(View.inflate(MainActivity.this, R.layout.fragment_finance, null), Gravity.CENTER, 0, 0);
                }
            });

            builder1.addComponent(new SimpleComponent(){
                @Override
                public View getView(LayoutInflater inflater) {
                    ImageView imageView = new ImageView(inflater.getContext());
                    imageView.setImageResource(R.mipmap.finance_001);
                    return imageView;
                }
                // -30
                @Override
                public int getXOffset() {
                    return -30;
                }

                @Override
                public int getYOffset() {
                    return -35;
                }
            });
            Guide guide = builder1.createGuide();
            guide.setShouldCheckLocInWindow(false);
            guide.show(MainActivity.this);
        }

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
    public void onPostCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onPostCreate(savedInstanceState, persistentState);

    }

    @Override
    public void onUserInteraction() {
        super.onUserInteraction();
//        TLog.d(TAG, "onUserInteraction");
//        if (BaseApplication.get(IS_FIRST_IN_MAIN,true)) return;
//                if (!popWnd.isShowing()) {
//                    popWnd.showAtLocation(View.inflate(this, R.layout.fragment_finance, null), Gravity.CENTER, 0, 0);
//                    bg.setAlpha(0.4f);
//                    bg.setVisibility(View.VISIBLE);
//                }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void checkOutVersion() {
        Api.getUpdateInfo(new Api.BaseViewCallbackWithOnStart<VersionMode>() {
            @Override
            public void onStart() {
                TLog.d(TAG," showProgressDialog();");
                showProgressDialog2();
            }

            @Override
            public void onFinish() {
                TLog.d(TAG," hideProgressDialog();");
                hideProgressDialog2();
            }

            @Override
            public void onError() {

            }

            @Override
            public void setData(VersionMode versionMode) {
                if (versionMode.getStatus() == Constant.REQUEST_SUCCESS) {
                    if (versionMode.getData() != null) {
                        TLog.d(TAG, "versionMode.getData().getVersion()" + versionMode.getData().getVersion());
                        TLog.d(TAG, "TDevice.getVersionCode()" + TDevice.getVersionCode());
                        if (Integer.valueOf(versionMode.getData().getVersion()) > TDevice.getVersionCode()) {
                            initPopupData(versionMode.getData());
                            if (BaseApplication.get(IS_FIRST_IN_MAIN, true)) {
                                update = true;
                                return;
                            }
                            if (popWnd.isShowing()) return;
                            bg.setAlpha(0.4f);
                            bg.setVisibility(View.VISIBLE);
                            popWnd.showAtLocation(View.inflate(MainActivity.this, R.layout.fragment_finance, null), Gravity.CENTER, 0, 0);
                        }
                    }
                }
            }
        });
    }

    private void initPopupProgress() {
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
//        popUpProgress.showAtLocation(View.inflate(this, R.layout.fragment_finance, null), Gravity.CENTER, 0, 0);
    }

    private void initPopupData(final VersionMode.DataBean dataBean) {
        if (com.pilipa.fapiaobao.ui.constants.Constant.FORCE_UPDATE.equals(dataBean.getForced())) {
            textViewCancel.setText(getString(R.string.quit_app));
        }
        if (com.pilipa.fapiaobao.ui.constants.Constant.UNFORCE_UPDATE.equals(dataBean.getForced())) {
            textViewCancel.setText(getString(R.string.donnot_update));
        }
        tvContent.setText(dataBean.getRemarks());

        popupContentView.findViewById(R.id.update).setOnClickListener(new View.OnClickListener() {
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
                                    downloadApk(dataBean);

                                }
                            }

                            @Override
                            public void onError(Throwable e) {
                                bg.setVisibility(View.INVISIBLE);
                                if (dataBean.getForced().equals(FORCE_UPDATE)) {
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

        popupContentView.findViewById(R.id.cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bg.setVisibility(View.INVISIBLE);
                if (FORCE_UPDATE.equals(dataBean.getForced())) {
                    MainActivity.this.finish();
                    System.exit(0);
                } else {
                    TDevice.UPDATE = false;
                    popWnd.dismiss();
                }
            }
        });
    }

    private void downloadApk(final VersionMode.DataBean dataBean) {
        Api.downloadApk(Constant.VERSION_BASE_URL+dataBean.getUrl(), new Api.BaseViewCallBackWithProgress<File>() {
            @Override
            public void setProgress(Progress progress) {
                TLog.log(progress.currentSize+"progress.currentSize");
                TLog.log(progress.totalSize+"progress.totalSize");

                progressBar.setProgress((int) ((progress.currentSize / (float) dataBean.getSize()) * 100));
                tvProgress.setText((int) ((((float) progress.currentSize / (float) dataBean.getSize()) * 100)) + "%");
            }

            @Override
            public void onStart(Request<File, ? extends Request> request) {
                popUpProgress.showAtLocation(View.inflate(MainActivity.this, R.layout.fragment_finance, null), Gravity.CENTER, 0, 0);
            }

            @Override
            public void onFinish() {
                bg.setVisibility(View.GONE);
            }

            @Override
            public void onError() {
                popUpProgress.dismiss();
                BaseApplication.showToast(getString(R.string.download_fail));
            }

            @Override
            public void setData(File file) {
                TDevice.openFile(MainActivity.this,file);
                popUpProgress.dismiss();
            }


        });
    }

    private void initPopup() {
        popupContentView = LayoutInflater.from(this).inflate(R.layout.popup_download_alert, null);
        textViewCancel = (TextView) popupContentView.findViewById(R.id.cancel);
        popWnd = new PopupWindow(this);
        popWnd.setAnimationStyle(R.style.download_alert_layout_style);
        popWnd.setContentView(popupContentView);
        popWnd.setBackgroundDrawable(getResources().getDrawable(R.drawable.bg_rect));
        popWnd.setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);
        popWnd.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        popWnd.setOutsideTouchable(false);
        tvContent = (TextView) popupContentView.findViewById(R.id.remark);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    //对返回键进行监听
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {

            exit();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    public void exit() {
        if ((System.currentTimeMillis() - mExitTime) > 2000) {
            BaseApplication.showToast(getString(R.string.click_twice_to_exit));
            mExitTime = System.currentTimeMillis();
        } else {
            finish();
            System.exit(0);
        }
    }


}
