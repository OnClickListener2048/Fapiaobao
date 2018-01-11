package com.pilipa.fapiaobao.ui;

import android.Manifest;
import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
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
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.example.mylibrary.utils.RegexUtils;
import com.example.mylibrary.utils.TLog;
import com.makeramen.roundedimageview.RoundedImageView;
import com.pilipa.fapiaobao.R;
import com.pilipa.fapiaobao.account.AccountHelper;
import com.pilipa.fapiaobao.base.BaseActivity;
import com.pilipa.fapiaobao.base.BaseApplication;
import com.pilipa.fapiaobao.compat.MediaStoreCompat;
import com.pilipa.fapiaobao.net.Api;
import com.pilipa.fapiaobao.net.Constant;
import com.pilipa.fapiaobao.net.bean.LoginWithInfoBean;
import com.pilipa.fapiaobao.net.bean.WXmodel;
import com.pilipa.fapiaobao.net.bean.me.NormalBean;
import com.pilipa.fapiaobao.net.bean.me.UpdateCustomerBean;
import com.pilipa.fapiaobao.ui.dialog.TimePickerDialog;
import com.pilipa.fapiaobao.ui.model.Image;
import com.pilipa.fapiaobao.utils.BitmapUtils;
import com.pilipa.fapiaobao.utils.ButtonUtils;
import com.pilipa.fapiaobao.wxapi.Constants;
import com.tbruyelle.rxpermissions2.RxPermissions;
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.zhihu.matisse.Matisse;
import com.zhihu.matisse.MimeType;
import com.zhihu.matisse.engine.impl.GlideEngine;
import com.zhihu.matisse.internal.entity.CaptureStrategy;

import java.io.File;
import java.io.IOException;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

import static com.pilipa.fapiaobao.base.BaseApplication.PUSH_RECEIVE;
import static com.pilipa.fapiaobao.base.BaseApplication.set;
import static com.pilipa.fapiaobao.net.Constant.LOGIN_PLATFORM_WX;
import static com.pilipa.fapiaobao.ui.LoginActivity.WX_LOGIN_ACTION;
import static com.pilipa.fapiaobao.utils.BitmapUtils.readPictureDegree;
import static com.pilipa.fapiaobao.utils.BitmapUtils.rotaingImageView;


/**
 * Created by lyt on 2017/10/24.
 */

public class UserInfoActivity extends BaseActivity {
    public static final int REQUEST_CODE_CAPTURE = 10;
    public static final int REQUEST_CODE_CHOOSE = 20;
    private static final String TAG = "UserInfoActivity";
    @Bind(R.id.edt_userName)
    EditText edtUserName;
    @Bind(R.id.edt_email)
    EditText edt_email;
    @Bind(R.id.edt_phone)
    EditText edtPhone;
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
    @Bind(R.id.tv_wx)
    TextView tv_wx;
    @Bind(R.id.tv_birthday)
    EditText tv_birthday;
    @Bind(R.id.bind_phone)
    TextView bindPhone;
    LoginWithInfoBean.DataBean.CustomerBean customerBean;
    private Dialog mCameraDialog;
    private Dialog mDialog;
    private MediaStoreCompat mediaStoreCompat;
    private int mPreviousPosition = -1;
    private RequestManager requestManager;
    private LoginWithInfoBean loginBean;
    private Image image;
    private TimePickerDialog dialog;
    private BroadcastReceiver mBroadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals(WX_LOGIN_ACTION)) {
                TLog.d(TAG, WX_LOGIN_ACTION + " success");
                String deviceToken = BaseApplication.get(com.pilipa.fapiaobao.ui.constants.Constant.DEVICE_TOKEN, "");
                Bundle bundle = intent.getBundleExtra(com.pilipa.fapiaobao.ui.constants.Constant.EXTRA_BUNDLE);
                WXmodel wx_info = bundle.getParcelable(com.pilipa.fapiaobao.ui.constants.Constant.WX_INFO);
                if (wx_info != null) {
                    Api.bindWX(AccountHelper.getUser().getData().getCustomer().getId(), LOGIN_PLATFORM_WX, wx_info.getOpenid(), "0", new Api.BaseViewCallbackWithOnStart<NormalBean>() {
                        @Override
                        public void onStart() {
                            showProgressDialog();
                        }

                        @Override
                        public void onFinish() {
                            hideProgressDialog();
                        }

                        @Override
                        public void onError() {

                        }

                        @Override
                        public void setData(NormalBean normalBean) {
                            if (normalBean.getStatus() == Constant.REQUEST_SUCCESS) {
//                            tv_wx.setText(getString(R.string.haveBound));
//                                hightlightPartText(tv_wx,getString(R.string.haveBound),AccountHelper.getUser().getData().getCustomer().getNickname());
                                tv_wx.setText(getString(R.string.haveBound));
                                tv_wx.setOnClickListener(null);
                                BaseApplication.showToast(getString(R.string.WX_bind_success));
                            } else if (normalBean.getStatus() == 707) {
                                BaseApplication.showToast(normalBean.getMsg());
                            }
                        }
                    });
                }
            } else if (intent.getAction().equals(com.pilipa.fapiaobao.ui.constants.Constant.BIND_PHONE_ACTION)) {
                boolean isbind = intent.getBooleanExtra("isbind", false);
                String phone = intent.getStringExtra(com.pilipa.fapiaobao.ui.constants.Constant.PHONE);
                if (isbind) {
//                    updateUserInfo(AccountHelper.getUserCustormer());
                    AccountHelper.getUserCustormer().setTelephone(phone);
                    AccountHelper.getUser().getData().getCustomer().setTelephone(phone);
                    edtPhone.setText(phone);
                    edtPhone.setVisibility(View.VISIBLE);
                    bindPhone.setVisibility(View.GONE);
                }
            }
        }
    };
    private IWXAPI api;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_userinfo;
    }

    @OnClick({R.id.userinfo_back, R.id.img_head, R.id.img_logout, R.id.btn_save, R.id.tv_birthday})
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_birthday: {//
                if (!ButtonUtils.isFastDoubleClick(R.id.tv_wx)) {
                    dialog.showDatePickerDialog(new TimePickerDialog.TimePickerDialogInterface() {
                        @Override
                        public void positiveListener() {
                            tv_birthday.setText(getString(R.string.placeholder_birthday, dialog.getYear(), dialog.getMonth(), dialog.getDay()));
                        }

                        @Override
                        public void negativeListener() {

                        }
                    });
                }
            }
            break;
            case R.id.tv_wx: {//绑定微信
                if (!ButtonUtils.isFastDoubleClick(R.id.tv_wx)) {
                    bindWX();
                }
            }
            break;
            case R.id.edt_phone: {//绑定手机

            }
            break;
            case R.id.btn_save: {
                if (!ButtonUtils.isFastDoubleClick(R.id.tv_wx)) {
                    LoginWithInfoBean.DataBean.CustomerBean customer = new LoginWithInfoBean.DataBean.CustomerBean();
                    if (!edtUserName.getText().toString().trim().isEmpty()) {
                        customer.setNickname(edtUserName.getText().toString().trim());
                    } else {
                        BaseApplication.showToast(getString(R.string.user_name_can_not_be_null));
                        return;
                    }
                    customer.setBirthday(tv_birthday.getText().toString().trim());
                    switch (radioGroup.getCheckedRadioButtonId()) {
                        case R.id.rb_female:
                            customer.setGender(Constant.GENDER_FEMALE);
                            break;
                        case R.id.rb_male:
                            customer.setGender(Constant.GENDER_MALE);
                            break;
                        case R.id.rb_secrecy:
                            customer.setGender(Constant.GENDER_SECRECY);
                            break;
                        default:
                    }
                    if (!edt_email.getText().toString().trim().isEmpty()) {
                        boolean emailExact = RegexUtils.isEmail(edt_email.getText().toString().trim());
                        if (emailExact) {
                            customer.setEmail(edt_email.getText().toString().trim());
                        } else {
                            BaseApplication.showToast(getString(R.string.email_format_not_right));
                            return;
                        }
                    }
//                    if (!(bindPhone.getVisibility() == View.VISIBLE && edtPhone.getVisibility() == View.GONE)) {
//                        boolean mobileExact = RegexUtils.isMobileExact(edtPhone.getText().toString().trim());
//                        if (mobileExact) {
//                            customer.setTelephone(edtPhone.getText().toString().trim());
//                        } else {
//                            BaseApplication.showToast(getString(R.string.phone_number_format_not_right));
//                            return;
//                        }
//                    }
                    updateUserInfo(customer);
                }
            }
            break;
            case R.id.userinfo_back: {
                finish();
            }
            break;
            case R.id.img_head: {
                setDialog();
            }
            break;
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
            case R.id.btn_cancel://拍照取消
                mCameraDialog.dismiss();
                break;
            case R.id.btn_cancel1://登出取消
                mDialog.dismiss();
                break;
            case R.id.btn_confirm://确认登出\
                logoutByToken();

                break;
            case R.id.img_logout:
                setLogoutDialog();
                break;
            default:
        }
    }

    @Override
    public void initView() {
        regexToWX();
        dialog = new TimePickerDialog(this);
        mediaStoreCompat = new MediaStoreCompat(this);
        requestManager = Glide.with(this);

        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(WX_LOGIN_ACTION);
        intentFilter.addAction(com.pilipa.fapiaobao.ui.constants.Constant.BIND_PHONE_ACTION);
        registerReceiver(mBroadcastReceiver, intentFilter);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(mBroadcastReceiver);
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
                            .captureStrategy(//com.pilipa.fapiaobao.fileprovider
                                    new CaptureStrategy(true, MediaStoreCompat.authority))
                            .maxSelectable(1)
                            .gridExpectedSize(
                                    getResources().getDimensionPixelSize(R.dimen.grid_expected_size))
                            .restrictOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)
                            .thumbnailScale(0.4f)
                            .imageEngine(new GlideEngine())
                            .theme(R.style.Matisse_Pilipa)
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
            TLog.d(TAG, "updateData:getCurrentPhotoPath success" + path);
//            try {
//                MediaStore.Images.Media.insertImage(this.getContentResolver(), path, new File(path).getName(), null);
//                this.sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, contentUri));
//            } catch (FileNotFoundException e) {
//                e.printStackTrace();
//            }
            image = new Image();
            image.isFromNet = false;
            image.name = new File(path).getName();
            image.isCapture = false;
            image.position = mPreviousPosition;
            image.uri = contentUri;
            image.path = path;
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
                String path = BitmapUtils.getRealFilePath(UserInfoActivity.this, uri);
                Log.d(TAG, "updateData:getPhotoUri success" + path);
                image = new Image();
                image.isCapture = false;
                image.position = mPreviousPosition;
                mPreviousPosition++;
                image.uri = uri;
                image.path = path;
                image.isFromNet = false;
                requestManager
                        .load(uri)
                        .asBitmap()
                        .thumbnail(0.1f)
                        .into(img_head);
            }

        }
        //上传头像
        if (image != null) {
            Bitmap bm = null;
            Bitmap newbitmap = null;
            try {
                int degree = readPictureDegree(image.path);
                bm = BitmapUtils.getBitmapFormUri(this, image.uri);
                newbitmap = rotaingImageView(degree, bm);
            } catch (IOException e) {
                e.printStackTrace();
            }
            customerBean = new LoginWithInfoBean.DataBean.CustomerBean();

            String bmpStr = BitmapUtils.bitmapToBase64(newbitmap);
            customerBean.setHeadimg(bmpStr);
            updateCustomer(AccountHelper.getToken(),customerBean);

        }
    }

    private void updateCustomer(String token, final LoginWithInfoBean.DataBean.CustomerBean customerBean) {
        Api.updateCustomer(token, customerBean, new Api.BaseRawResponse<UpdateCustomerBean>() {
            @Override
            public void onTokenInvalid() {
                login();
            }

            @Override
            public void setData(UpdateCustomerBean updateCustomerBean) {
                AccountHelper.updateCustomer(customerBean);
                Log.d(TAG, "updateData:updateUserInfo success");
            }

            @Override
            public void onStart() {
                showProgressDialog();
            }

            @Override
            public void onFinish() {
                hideProgressDialog();
            }

            @Override
            public void onError() {
            }

        });
    }

    @Override
    protected void onResume() {

//        setUserData(AccountHelper.getUser().getData().getCustomer());

        super.onResume();
    }

    private void hightlightPartText(TextView textView, String highlightString, String lowlightString) {
        String lowlight = "（"+lowlightString+"）";
        SpannableString spannableString = new SpannableString(lowlight+highlightString);  //获取按钮上的文字
        ForegroundColorSpan lowlightSpan = new ForegroundColorSpan(Color.parseColor("#959595"));
        spannableString.setSpan(lowlightSpan,0,lowlight.length(), Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
        textView.setText(spannableString);
    }

    @Override
    public void initData() {
        if (AccountHelper.getUser().getData().getCustomer().getHeadimg() != null) {
            String thumbnail = AccountHelper.getUser().getData().getCustomer().getHeadimg().replace("invoice", "thumbnail");
            requestManager
                    .load(thumbnail)
                    .asBitmap()
                    .placeholder(R.mipmap.ic_head_circle_default_small)
                    .error(R.mipmap.ic_head_circle_default_small)
                    .thumbnail(0.1f)
                    .into(img_head);
        }
        setUserData(AccountHelper.getUser().getData().getCustomer());
    }

    public void setUserData(LoginWithInfoBean.DataBean.CustomerBean customer) {
        tv_birthday.setText(customer.getBirthday());
        edtUserName.setText(customer.getNickname());
        edtPhone.setText(customer.getTelephone());
        edt_email.setText(customer.getEmail());

        if (customer.getOpenid() == null) {
            tv_wx.setText(getString(R.string.gobind));
            tv_wx.setOnClickListener(this);
        } else {
//            hightlightPartText(tv_wx,getString(R.string.haveBound),customer.getNickname());
            tv_wx.setText(getString(R.string.haveBound));
            tv_wx.setOnClickListener(null);
        }
        if ("".equals(customer.getTelephone())) {
            bindPhone.setVisibility(View.VISIBLE);
            bindPhone.setText(getString(R.string.gobind));
            edtPhone.setVisibility(View.GONE);
        } else {
            bindPhone.setVisibility(View.GONE);
            hightlightPartText(edtPhone, getString(R.string.haveBound), customer.getTelephone());
            edtPhone.setVisibility(View.VISIBLE);
        }
        if (Constant.GENDER_FEMALE.equals(customer.getGender())) {
            radioGroup.check(R.id.rb_female);
        } else if (Constant.GENDER_MALE.equals(customer.getGender())) {
            radioGroup.check(R.id.rb_male);
        } else if (Constant.GENDER_SECRECY.equals(customer.getGender())) {
            radioGroup.check(R.id.rb_secrecy);
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
        lp.width = getResources().getDisplayMetrics().widthPixels; // 宽度
        root.measure(0, 0);
        lp.height = root.getMeasuredHeight();

        lp.alpha = 9f; // 透明度
        dialogWindow.setAttributes(lp);
        mCameraDialog.show();
    }

    private void setUBindDialog() {
        mDialog = new Dialog(this, R.style.BottomDialog);
        LinearLayout root = (LinearLayout) LayoutInflater.from(this).inflate(
                R.layout.layout_ubind_tip, null);
        //初始化视图
        root.findViewById(R.id.btn_confirm).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uBindWX();
                mDialog.dismiss();
            }
        });
        root.findViewById(R.id.btn_cancel1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDialog.dismiss();
            }
        });
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

    private void updateUserInfo(final LoginWithInfoBean.DataBean.CustomerBean customer) {

                Api.updateCustomer(AccountHelper.getToken(), customer, new Api.BaseRawResponse<UpdateCustomerBean>() {
                    @Override
                    public void onTokenInvalid() {
                        login();
                    }

                    @Override
                    public void setData(UpdateCustomerBean updateCustomerBean) {
                        Toast.makeText(UserInfoActivity.this, getString(R.string.user_info_save_success), Toast.LENGTH_SHORT).show();
                        AccountHelper.updateCustomer(customer);
                        setUserData(customer);
                        UserInfoActivity.this.finish();
                        Log.d(TAG, "updateData:updateUserInfo success");
                    }

                    @Override
                    public void onStart() {
                        showProgressDialog();
                    }

                    @Override
                    public void onFinish() {
                        hideProgressDialog();
                    }

                    @Override
                    public void onError() {
                    }

                });

    }

    private void logoutByToken() {
        Api.logoutByToken(AccountHelper.getToken(), new Api.BaseViewCallbackWithOnStart<NormalBean>() {
            @Override
            public void onStart() {
                showProgressDialog();
            }

            @Override
            public void onFinish() {
                hideProgressDialog();
            }

            @Override
            public void onError() {

            }

            @Override
            public void setData(NormalBean normalBean) {
                if (normalBean.getStatus() == Constant.REQUEST_SUCCESS) {
                    Toast.makeText(UserInfoActivity.this, getString(R.string.logout_success), Toast.LENGTH_SHORT).show();
                    mDialog.dismiss();
                    AccountHelper.logout();
                    UserInfoActivity.this.finish();
                    set(PUSH_RECEIVE, false);

                    Log.d(TAG, "updateData:logoutByToken success");
                } else if (normalBean.getStatus() == Constant.REQUEST_NO_CONTENT) {
                    BaseApplication.showToast(getString(R.string.no_content_in_the_server));
                }
            }
        });
    }

    private void bindWX() {
        if (!com.pilipa.fapiaobao.ui.constants.Constant.NOTOKEN.equals(AccountHelper.getToken())) {
            if (AccountHelper.getUser().getData().getCustomer().getOpenid() == null) {
                weChatLogin();
            }
        } else {
            startActivity(new Intent(UserInfoActivity.this, LoginActivity.class));
            finish();
        }
    }

    private void uBindWX() {
        Api.uBindWX(AccountHelper.getUser().getData().getCustomer().getId()
                , LOGIN_PLATFORM_WX
                , AccountHelper.getUser().getData().getCustomer().getOpenid()
                , new Api.BaseViewCallback<NormalBean>() {
                    @Override
                    public void setData(NormalBean normalBean) {
                        if (normalBean.getStatus() == Constant.REQUEST_SUCCESS) {
                            tv_wx.setText(getString(R.string.gobind));
                            tv_wx.setOnClickListener(UserInfoActivity.this);
                            AccountHelper.clearCustomerOpenId();
                            BaseApplication.showToast(getString(R.string.WX_unbind_success));
                        } else if (normalBean.getStatus() == 707) {
                            BaseApplication.showToast(normalBean.getMsg());
                        }
                    }
                });
    }

    private void regexToWX() {
        api = WXAPIFactory.createWXAPI(this, Constants.APP_ID, true);
        api.registerApp(Constants.APP_ID);
    }

    private void weChatLogin() {
        if (api.isWXAppInstalled()) {
            SendAuth.Req req = new SendAuth.Req();
            req.scope = "snsapi_userinfo";
            req.state = "wechat_sdk_demo_test";
            api.sendReq(req);
        } else {
            BaseApplication.showToast(getString(R.string.please_install_WX));
        }
    }

    @OnClick(R.id.bind_phone)
    public void onViewClicked() {
        if (!ButtonUtils.isFastDoubleClick(R.id.edt_phone)) {
            Intent intent = new Intent(UserInfoActivity.this, BindPhoneActivity.class);
            startActivity(intent);
        }
    }
}
