package com.pilipa.fapiaobao.ui;

import android.Manifest;
import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;

import com.example.mylibrary.utils.ImageUtils;
import com.example.mylibrary.utils.TLog;
import com.pilipa.fapiaobao.R;
import com.pilipa.fapiaobao.account.AccountHelper;
import com.pilipa.fapiaobao.adapter.me.CompanyDetailsAdapter;
import com.pilipa.fapiaobao.base.BaseActivity;
import com.pilipa.fapiaobao.base.BaseApplication;
import com.pilipa.fapiaobao.entity.Company;
import com.pilipa.fapiaobao.net.Api;
import com.pilipa.fapiaobao.net.bean.me.FavBean;
import com.pilipa.fapiaobao.net.bean.me.FavoriteCompanyBean;
import com.pilipa.fapiaobao.net.bean.me.NormalBean;
import com.pilipa.fapiaobao.ui.fragment.MyCompanyDetailsPagerFragment;
import com.pilipa.fapiaobao.utils.DialogUtil;
import com.pilipa.fapiaobao.wxapi.Constants;
import com.tbruyelle.rxpermissions2.RxPermissions;
import com.tencent.mm.opensdk.modelmsg.SendMessageToWX;
import com.tencent.mm.opensdk.modelmsg.WXMediaMessage;
import com.tencent.mm.opensdk.modelmsg.WXWebpageObject;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.media.UMWeb;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

import static com.pilipa.fapiaobao.base.BaseApplication.SHARE_SOCORE;
import static com.pilipa.fapiaobao.base.BaseApplication.set;
import static com.pilipa.fapiaobao.net.Constant.REQUEST_SUCCESS;

/**
 * Created by wjn on 2017/10/23.
 */

public class FavCompanyDetailsActivity extends BaseActivity implements MyCompanyDetailsPagerFragment.OnDelClickListener
        ,MyCompanyDetailsPagerFragment.OnNextClickListener {
    public BroadcastReceiver mBroadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals("remove")) {
                finish();
            }
        }
    };
    protected int mPreviousPos = 0;
    @Bind(R.id.vp_verpager)
    ViewPager mViewPager;
    CompanyDetailsAdapter companyDetailsAdapter;
    ArrayList<FavoriteCompanyBean.DataBean> companyList;
    private ArrayList<MyCompanyDetailsPagerFragment> FragmentList;
    private String deleteId;
    private Dialog dialog;
    private IWXAPI api;
    private UMShareListener umShareListener = new UMShareListener() {
        @Override
        public void onStart(SHARE_MEDIA share_media) {

        }

        @Override
        public void onResult(SHARE_MEDIA share_media) {
            Api.shareScoreAdd(AccountHelper.getToken(), new Api.BaseViewCallback<NormalBean>() {
                @Override
                public void setData(NormalBean normalBean) {
                }
            });
        }

        @Override
        public void onError(SHARE_MEDIA share_media, Throwable throwable) {
        }

        @Override
        public void onCancel(SHARE_MEDIA share_media) {
        }
    };
    private UMWeb web;
    private UMShareAPI umShareAPI;
    private Dialog mCameraDialog;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_company_details;
    }

    @OnClick({R.id.details_back,R.id.img_add})
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.details_back:{
                finish();
            }break;
            case R.id.img_add:{
                startActivity(new Intent(this,AddCompanyInfoActivity.class));
            }break;
            default:
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1000){

        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void initView() {
        initShare();
          companyList = getIntent().getParcelableArrayListExtra("favCompanyList");
        mPreviousPos = getIntent().getIntExtra("mPreviousPos",0);
        FragmentList  = new ArrayList<>();
        for (int i = 0; i <companyList.size() ; i++) {
            Bundle b = new Bundle();
            Company company = new Company();
            company.setId(companyList.get(i).getId());
            company.setName(companyList.get(i).getName());
            company.setTaxno(companyList.get(i).getTaxno());
            company.setAddress(companyList.get(i).getAddress());
            company.setPhone(companyList.get(i).getPhone());
            company.setDepositBank(companyList.get(i).getDepositBank());
            company.setAccount(companyList.get(i).getAccount());
            company.setQrcode(companyList.get(i).getQrcode());
            company.setInvoiceTypeList(companyList.get(i).getInvoiceTypeList());

            b.putParcelable("company",company);
            MyCompanyDetailsPagerFragment fragment1 = MyCompanyDetailsPagerFragment.newInstance(b);
            fragment1.setOnDelClickListener(this);
            FragmentList.add(fragment1);
        }
        companyDetailsAdapter = new CompanyDetailsAdapter(this,getSupportFragmentManager(), FragmentList);
        mViewPager.setAdapter(companyDetailsAdapter);
        mViewPager.setCurrentItem(mPreviousPos);

        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("remove");

        registerReceiver(mBroadcastReceiver, intentFilter);
        initDialog();
    }

    private void initDialog() {
        dialog = DialogUtil.getInstance().createDialog(this, R.style.BottomDialog, R.layout.layout_delete_tip, null, new DialogUtil.OnConfirmListener() {
            @Override
            public void onConfirm(View view) {
                deleteFavCompany(deleteId);
            }
        }, new DialogUtil.OnCancelListener() {
            @Override
            public void onCancel(View view) {
                dialog.dismiss();
            }
        });
    }

    @Override
    public void initData() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @Override
    public void onDelClick(String deleteId) {
        mPreviousPos =  mViewPager.getCurrentItem();
        this.deleteId = deleteId;
//        setDialog(deleteId);
        showDialog(dialog);
    }
    public void deleteFavCompany(String id){
        Api.deleteFavoriteCompany(id, AccountHelper.getToken(),new Api.BaseRawResponse<FavBean>() {
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
            public void onTokenInvalid() {

            }

            @Override
            public void setData(FavBean normalBean) {
                if(normalBean.getStatus() == REQUEST_SUCCESS){
                    companyDetailsAdapter.remove(mPreviousPos);
                    dialog.dismiss();
                    setResult(RESULT_OK);
                    BaseApplication.showToast("删除成功");
                }
            }
        });
    }

    @Override
    protected void onDestroy() {
        unregisterReceiver(mBroadcastReceiver);
        super.onDestroy();
    }

    @Override
    public void onNextClick() {

    }

    private void initShare() {
        api = WXAPIFactory.createWXAPI(this, Constants.APP_ID);
        api.registerApp(Constants.APP_ID);
        umShareAPI = UMShareAPI.get(this);
    }

    public void setShareContent(String s) {
        web = new UMWeb(s);
        web.setTitle(getString(R.string.please_open_check));
        UMImage umImage = new UMImage(this, R.mipmap.icon);
        web.setThumb(umImage);
        web.setDescription(getString(R.string.tohave_fapiaobao));
    }


    public void setDialog(final String url) {
        mCameraDialog = new Dialog(this, R.style.BottomDialog);
        LinearLayout root = (LinearLayout) LayoutInflater.from(this).inflate(
                R.layout.layout_share_, null);
        //初始化视图
        root.findViewById(R.id.weixin).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (api.isWXAppInstalled()) {
                    WXWebpageObject wxWebpageObject = new WXWebpageObject();
                    wxWebpageObject.webpageUrl = url;

                    WXMediaMessage wxMediaMessage = new WXMediaMessage(wxWebpageObject);
                    wxMediaMessage.title = getString(R.string.please_open_check);
                    wxMediaMessage.description = getString(R.string.tohave_fapiaobao);
                    wxMediaMessage.thumbData = ImageUtils.drawable2Bytes(getResources().getDrawable(R.mipmap.icon), Bitmap.CompressFormat.JPEG);

                    SendMessageToWX.Req req = new SendMessageToWX.Req();
                    req.transaction = String.valueOf(System.currentTimeMillis());
                    req.message = wxMediaMessage;

                    req.scene = SendMessageToWX.Req.WXSceneSession;
                    api.sendReq(req);
                    mCameraDialog.dismiss();
                    //记录用户分享状态
                    set(SHARE_SOCORE, true);
                } else {
                    BaseApplication.showToast("请安装微信客户端");
                }

            }
        });
        root.findViewById(R.id.weibo).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (umShareAPI.isInstall(FavCompanyDetailsActivity.this, SHARE_MEDIA.SINA)) {
                    new ShareAction(FavCompanyDetailsActivity.this)
                            .setPlatform(SHARE_MEDIA.SINA)//传入平台
                            .withMedia(web)
                            .setCallback(umShareListener)//回调监听器
                            .share();
                    mCameraDialog.dismiss();
                } else {
                    BaseApplication.showToast("请安装新浪微博客户端");
                }

            }
        });
        root.findViewById(R.id.moments).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (umShareAPI.isInstall(FavCompanyDetailsActivity.this, SHARE_MEDIA.WEIXIN_CIRCLE)) {
                    new ShareAction(FavCompanyDetailsActivity.this)
                            .setPlatform(SHARE_MEDIA.WEIXIN_CIRCLE)//传入平台
                            .withMedia(web)
                            .setCallback(umShareListener)//回调监听器
                            .share();
                    mCameraDialog.dismiss();
                } else {
                    BaseApplication.showToast("请安装微信客户端");
                }

            }
        });
        root.findViewById(R.id.qq).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RxPermissions rxPermissions = new RxPermissions(FavCompanyDetailsActivity.this);
                rxPermissions.request(Manifest.permission.READ_EXTERNAL_STORAGE).subscribe(new Observer<Boolean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Boolean aBoolean) {
                        if (aBoolean) {
                            if (umShareAPI.isInstall(FavCompanyDetailsActivity.this, SHARE_MEDIA.QQ)) {
                                TLog.d(" if (umShareAPI.isInstall(getActivity(), SHARE_MEDIA.QQ)) {", "");
                                new ShareAction(FavCompanyDetailsActivity.this)
                                        .setPlatform(SHARE_MEDIA.QQ)//传入平台
                                        .withMedia(web)
                                        .setCallback(umShareListener)//回调监听器
                                        .share();
                                mCameraDialog.dismiss();
                            } else {
                                BaseApplication.showToast("请安装QQ客户端");
                            }
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
        });
        root.findViewById(R.id.qzone).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (umShareAPI.isInstall(FavCompanyDetailsActivity.this, SHARE_MEDIA.QZONE)) {
                    new ShareAction(FavCompanyDetailsActivity.this)
                            .setPlatform(SHARE_MEDIA.QZONE)//传入平台
                            .withMedia(web)
                            .setCallback(umShareListener)//回调监听器
                            .share();
                    mCameraDialog.dismiss();
                } else {
                    BaseApplication.showToast("请安装QQ空间客户端");
                }
            }
        });

        root.findViewById(R.id.btn_cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCameraDialog.dismiss();
            }
        });
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

}
