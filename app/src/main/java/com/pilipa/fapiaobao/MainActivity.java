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
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
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
import com.pilipa.fapiaobao.ui.fragment.FinanceFragment;
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
    private static final int UPDATE = 924;


    private  Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == UPDATE) {
                if (TDevice.UPDATE) {
                    checkOutVersion();
                }
            }
        }
    };

    private PopupWindow popWnd;
    private ProgressBar progressBar;
    private TextView tvProgress;
    private LinearLayout ll_progress;
    private PopupWindow popUpProgress;
    private Guide guide;

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

        mHandler.sendEmptyMessageDelayed(UPDATE, 3000);

        if (BaseApplication.get("IS_FIRST_IN_MAIN", true)) {
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

        bg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        if (mLocationClient != null) {

        mLocationClient.startLocation();
        }
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

        builder.addComponent(new SimpleComponent());
        guide = builder.createGuide();
        guide.setShouldCheckLocInWindow(false);
        guide.show(MainActivity.this);
    }


    public void showGuideView2() {

        FinanceFragment fragment = (FinanceFragment) mNavBar.navItemTex.getFragment();
        RecyclerView.LayoutManager layoutManager = fragment.recyclerview.getLayoutManager();
        View childAt = layoutManager.getChildAt(1);
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
                BaseApplication.set("IS_FIRST_IN_MAIN",false);
            }
        });

        builder1.addComponent(new SimpleComponent());
        Guide guide = builder1.createGuide();
        guide.setShouldCheckLocInWindow(false);
        guide.show(MainActivity.this);
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

                                            progressBar.setProgress((int) ((progress.currentSize / (float) dataBean.getSize()) * 100));
                                            tvProgress.setText((int) ((((float) progress.currentSize / (float) dataBean.getSize()) * 100)) + "%");
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
                    TDevice.UPDATE = false;
                    popWnd.dismiss();
                }
            }
        });
    }




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
