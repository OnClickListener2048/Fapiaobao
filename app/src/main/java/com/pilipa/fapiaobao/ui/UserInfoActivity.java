package com.pilipa.fapiaobao.ui;

import android.Manifest;
import android.app.Dialog;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.makeramen.roundedimageview.RoundedImageView;
import com.pilipa.fapiaobao.R;
import com.pilipa.fapiaobao.base.BaseActivity;
import com.pilipa.fapiaobao.base.BaseApplication;
import com.pilipa.fapiaobao.compat.MediaStoreCompat;
import com.pilipa.fapiaobao.entity.Customer;
import com.pilipa.fapiaobao.net.Api;
import com.pilipa.fapiaobao.net.Constant;
import com.pilipa.fapiaobao.net.bean.LoginBean;
import com.pilipa.fapiaobao.ui.model.Image;
import com.pilipa.fapiaobao.utils.SharedPreferencesHelper;
import com.tbruyelle.rxpermissions2.RxPermissions;
import com.zhihu.matisse.Matisse;
import com.zhihu.matisse.MimeType;
import com.zhihu.matisse.engine.impl.GlideEngine;
import com.zhihu.matisse.internal.entity.CaptureStrategy;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;


/**
 * Created by lyt on 2017/10/24.
 */

public class UserInfoActivity extends BaseActivity {
    private static final String TAG = "UserInfoActivity";

    @Bind(R.id.edt_userName)
    EditText edtUserName;
    @Bind(R.id.edt_phone)
    EditText edtPhone;
    @Bind(R.id.edt_birthday)
    EditText edtBirthday;
    @Bind(R.id.radioGroup)
    RadioGroup radioGroup;
    @Bind(R.id.rb_male)
    RadioButton rbMale;
    @Bind(R.id.rb_female)
    RadioButton rbFemale;
    @Bind(R.id.rb_secrecy)
    RadioButton rbSecrecy;
    @Bind(R.id.userinfo_back)
    ImageView img_back;
    @Bind(R.id.img_head)
    RoundedImageView img_head;
    private Dialog mCameraDialog;
    private Dialog mDialog;
    private MediaStoreCompat mediaStoreCompat;
    public static final int REQUEST_CODE_CAPTURE = 10;
    public static final int REQUEST_CODE_CHOOSE = 20;
    private int mPreviousPosition = -1;
    private RequestManager requestManager;
    private LoginBean loginBean;
    @Override
    protected int getLayoutId() {
        return R.layout.activity_userinfo;
    }

    @OnClick({R.id.userinfo_back, R.id.img_head, R.id.img_logout,R.id.btn_save})
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_save: {
                Customer customer = new Customer();
                customer.setNickname("或");
                updateUserInfo(customer);
            }break;
            case R.id.userinfo_back: {
                finish();
            }break;
            case R.id.img_head: {
                setDialog();
            }break;
            case R.id.btn_choose_img:
                openMedia();
                mCameraDialog.dismiss();
                break;
            case R.id.btn_open_camera:
                if (MediaStoreCompat.hasCameraFeature(this)) {
                    mediaStoreCompat.dispatchCaptureIntent(this, REQUEST_CODE_CAPTURE);
                }
                mCameraDialog.dismiss();
                break;
            case R.id.btn_cancel:
                mCameraDialog.dismiss();
                break;
            case R.id.btn_cancel1:
                mDialog.dismiss();
                break;
            case R.id.btn_confirm:
                mDialog.dismiss();
                break;
            case R.id.img_logout:
                setLogoutDialog();
                break;
        }
    }

    @Override
    public void initView() {
        mediaStoreCompat = new MediaStoreCompat(this);
        requestManager = Glide.with(this);
    }

    private void openMedia() {
        RxPermissions rxPermissions = new RxPermissions(this);
        rxPermissions.request(Manifest.permission.WRITE_EXTERNAL_STORAGE).subscribe(new Observer<Boolean>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Boolean aBoolean) {
                if (aBoolean) {
                    Matisse.from(UserInfoActivity.this)
                            .choose(MimeType.of(MimeType.JPEG, MimeType.PNG))
                            .countable(true)
                            .captureStrategy(
                                    new CaptureStrategy(true, "com.pilipa.fapiaobao.fileprovider"))
                            .maxSelectable(1)
                            .gridExpectedSize(
                                    getResources().getDimensionPixelSize(R.dimen.grid_expected_size))
                            .restrictOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)
                            .thumbnailScale(0.4f)
                            .imageEngine(new GlideEngine())
                            .forResult(REQUEST_CODE_CHOOSE);
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
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_CAPTURE && resultCode == RESULT_OK) {
            Uri contentUri = mediaStoreCompat.getCurrentPhotoUri();
            String path = mediaStoreCompat.getCurrentPhotoPath();
            try {
                MediaStore.Images.Media.insertImage(this.getContentResolver(), path, new File(path).getName(), null);
                this.sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, contentUri));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            Image image = new Image();
            image.isFromNet = false;
            image.name = new File(path).getName();
            image.isCapture = false;
            image.position = mPreviousPosition;
            image.uri = contentUri;
            requestManager
                    .load(contentUri)
                    .asBitmap()
                    .thumbnail(0.1f)
                    .into(img_head);
            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
                this.revokeUriPermission(contentUri,
                        Intent.FLAG_GRANT_WRITE_URI_PERMISSION | Intent.FLAG_GRANT_READ_URI_PERMISSION);
            }
        } else if (requestCode == REQUEST_CODE_CHOOSE && resultCode == RESULT_OK) {
            List<Uri> uris = Matisse.obtainResult(data);
            for (Uri uri : uris) {
                Image image = new Image();
                image.isCapture = false;
                image.position = mPreviousPosition;
                mPreviousPosition++;
                image.uri = uri;
                image.isFromNet = false;
                requestManager
                        .load(uri)
                        .asBitmap()
                        .thumbnail(0.1f)
                        .into(img_head);
            }

        }

    }

    @Override
    public void initData() {
        loginBean =  SharedPreferencesHelper.loadFormSource(this,LoginBean.class);
        LoginBean.DataBean.CustomerBean customer = loginBean.getData().getCustomer();

        if(loginBean != null){
//            edtBirthday.setText(customer.getBirthday());
//            edtUserName.setText(customer.getNickname());
//            edtPhone.setText(customer.getTelephone());
//            if (customer.getGender().equals(Constant.GENDER_FEMALE)){
//                radioGroup.check(R.id.rb_female);
//            } else if (customer.getGender().equals(Constant.GENDER_MALE)){
//                radioGroup.check(R.id.rb_male);
//            } else if (customer.getGender().equals(Constant.GENDER_SECRECY)){
//                radioGroup.check(R.id.rb_secrecy);
//            }

        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    private void setDialog() {
        mCameraDialog = new Dialog(this, R.style.BottomDialog);
        LinearLayout root = (LinearLayout) LayoutInflater.from(this).inflate(
                R.layout.dialog_bottom, null);
        //初始化视图
        root.findViewById(R.id.btn_choose_img).setOnClickListener(this);
        root.findViewById(R.id.btn_open_camera).setOnClickListener(this);
        root.findViewById(R.id.btn_cancel).setOnClickListener(this);
        mCameraDialog.setContentView(root);
        Window dialogWindow = mCameraDialog.getWindow();
        dialogWindow.setGravity(Gravity.BOTTOM);
//        dialogWindow.setWindowAnimations(R.style.dialogstyle); // 添加动画
        WindowManager.LayoutParams lp = dialogWindow.getAttributes(); // 获取对话框当前的参数值
        lp.x = 0; // 新位置X坐标
        lp.y = 0; // 新位置Y坐标
        lp.width = (int) getResources().getDisplayMetrics().widthPixels; // 宽度
        root.measure(0, 0);
        lp.height = root.getMeasuredHeight();

        lp.alpha = 9f; // 透明度
        dialogWindow.setAttributes(lp);
        mCameraDialog.show();
    }

    private void setLogoutDialog() {
        mDialog = new Dialog(this, R.style.BottomDialog);
        LinearLayout root = (LinearLayout) LayoutInflater.from(this).inflate(
                R.layout.layout_logout_tip, null);
        //初始化视图
        root.findViewById(R.id.btn_confirm).setOnClickListener(this);
        root.findViewById(R.id.btn_cancel1).setOnClickListener(this);
        mDialog.setContentView(root);
        Window dialogWindow = mDialog.getWindow();
        dialogWindow.setGravity(Gravity.CENTER);
//        dialogWindow.setWindowAnimations(R.style.dialogstyle); // 添加动画
        WindowManager.LayoutParams lp = dialogWindow.getAttributes(); // 获取对话框当前的参数值
        lp.x = 0; // 新位置X坐标
        lp.y = 0; // 新位置Y坐标
        lp.width = (int) getResources().getDisplayMetrics().widthPixels; // 宽度
        root.measure(0, 0);
        lp.height = root.getMeasuredHeight();

        lp.alpha = 9f; // 透明度
        dialogWindow.setAttributes(lp);
        mDialog.show();
    }

    private void updateUserInfo(Customer customer){
        if(loginBean != null){
            String token = loginBean.getData().getToken();
            Log.d(TAG, "updateData:updateUserInfo userToken"+token);
            if (token == null) {
                BaseApplication.showToast("");
                return;
            } else {
                Api.updateCustomer(token,customer, new Api.BaseViewCallback<Object>() {
                    @Override
                    public void setData(Object loginBean) {
                        Log.d(TAG, "updateData:updateUserInfo userToken"+loginBean.toString());
                    }
                });
            }
        }
    }

}
