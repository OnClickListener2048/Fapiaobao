package com.pilipa.fapiaobao.base;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.example.mylibrary.utils.TLog;
import com.lzy.okgo.OkGo;
import com.pilipa.fapiaobao.R;
import com.pilipa.fapiaobao.interf.BaseView;
import com.pilipa.fapiaobao.ui.LoginActivity;
import com.pilipa.fapiaobao.ui.constants.Constant;
import com.pilipa.fapiaobao.ui.fragment.ProgressDialogFragment;
import com.pilipa.fapiaobao.ui.model.Image;
import com.pilipa.fapiaobao.utils.BitmapUtils;
import com.pilipa.fapiaobao.utils.TDevice;
import com.tbruyelle.rxpermissions2.RxPermissions;
import com.umeng.message.PushAgent;

import java.io.IOException;

import butterknife.ButterKnife;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

import static com.pilipa.fapiaobao.utils.BitmapUtils.readPictureDegree;
import static com.pilipa.fapiaobao.utils.BitmapUtils.rotaingImageView;


/**
 * Created by lyt on 2017/10/12.
 */

public abstract class BaseActivity extends AppCompatActivity implements View.OnClickListener, BaseView, DialogInterface.OnKeyListener {
    protected LayoutInflater mInflater;
    private Fragment mFragment;
    private ActionBar mActionBar;
    private ProgressDialog progressDialog;

    private Bitmap compressedImageBitmap;

    private BroadcastReceiver mBroadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            switch (intent.getAction()) {
                case Constant.CONNECT_TIMEOUT:
                case Constant.BAD_NETWORK:
                case Constant.CONNECT_ERROR:
                case Constant.UNKNOWN_ERROR:
                case Constant.PARSE_ERROR:
                    hideProgressDialog();

            }
        }
    };
    private ProgressDialogFragment progressDialogFragment;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initWindow();

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
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(Constant.BAD_NETWORK);
        intentFilter.addAction(Constant.CONNECT_ERROR);
        intentFilter.addAction(Constant.CONNECT_TIMEOUT);
        intentFilter.addAction(Constant.UNKNOWN_ERROR);
        intentFilter.addAction(Constant.PARSE_ERROR);
        registerReceiver(mBroadcastReceiver, intentFilter);

        initDialogFragment();

    }




    private void initDialogFragment() {
        progressDialogFragment = ProgressDialogFragment.newInstance();
    }

    public void showDialogFragment(String tag) {
        progressDialogFragment.show(getSupportFragmentManager(),tag);
    }

    public void hideDialogFragment(String tag) {
        String tag1 = progressDialogFragment.getTag();
        if (TextUtils.equals(tag, tag1)) {
            progressDialogFragment.dismiss();
        }
    }


    private void initProgressDialog() {
        if (progressDialog == null) {
            progressDialog = new ProgressDialog(this);
        }
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.setTitle("提示");
        progressDialog.setMessage("正在加载中");
        progressDialog.setOnKeyListener(this);
    }

    public void showProgressDialog() {
        if (!isFinishing()) {
            if (progressDialog != null) {
                if (!progressDialog.isShowing()) {
                    progressDialog.show();
                }
            }
        }
    }


    public void showProgressDialog(String description) {
        if (!isFinishing()) {
            if (progressDialog != null) {
                if (!progressDialog.isShowing()) {
                    progressDialog.setMessage(description);
                    progressDialog.show();
                }
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

    @Override
    protected void onDestroy() {
        super.onDestroy();
        progressDialog.dismiss();
        progressDialog=null;
        try {
            unregisterReceiver(mBroadcastReceiver);
        } catch (Exception e) {
            e.printStackTrace();
        }
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


    public String upLoadReceipt(Image image) throws IOException {
        Bitmap bm = null;
        Bitmap newbitmap = null;
        try {
            int degree = readPictureDegree(image.path);
            bm = BitmapUtils.getBitmapFormUri(this, image.uri);
            newbitmap = rotaingImageView(degree, bm);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return BitmapUtils.bitmapToBase64(newbitmap);
    }




    @Override
    public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
        return keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0;
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {

    }
}
