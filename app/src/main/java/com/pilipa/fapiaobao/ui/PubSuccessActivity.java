package com.pilipa.fapiaobao.ui;

import android.Manifest;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mylibrary.utils.ImageUtils;
import com.pilipa.fapiaobao.R;
import com.pilipa.fapiaobao.account.AccountHelper;
import com.pilipa.fapiaobao.base.BaseActivity;
import com.pilipa.fapiaobao.base.BaseApplication;
import com.pilipa.fapiaobao.net.Api;
import com.pilipa.fapiaobao.net.Constant;
import com.pilipa.fapiaobao.net.bean.me.NormalBean;
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

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

import static com.pilipa.fapiaobao.base.BaseApplication.SHARE_SOCORE;
import static com.pilipa.fapiaobao.base.BaseApplication.set;

/**
 * Created by edz on 2017/10/26.
 */

public class PubSuccessActivity extends BaseActivity {
    private static final String TAG = "PubSuccessActivity";

    @Bind(R.id.title)
    TextView title;
    @Bind(R.id.publish_success_back)
    ImageView publishSuccessBack;
    @Bind(R.id.btn_continue_to_supply)
    Button btnContinueToSupply;
    @Bind(R.id.btn_watch)
    Button btnWatch;
    @Bind(R.id.WeChat)
    TextView WeChat;
    @Bind(R.id.moments)
    TextView moments;
    @Bind(R.id.qq)
    TextView qq;
    @Bind(R.id.qzone)
    TextView qzone;
    @Bind(R.id.weibo)
    TextView weibo;

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
                    Log.d(TAG, "updateData:shareScoreAdd success");
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
    private String demand;
    private UMShareAPI umShareAPI;
    private UMWeb web;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_publish_success;
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void initView() {
        umShareAPI = UMShareAPI.get(this);

        api = WXAPIFactory.createWXAPI(this, Constants.APP_ID);
        api.registerApp(Constants.APP_ID);
    }

    @Override
    public void initData() {
        demand = getIntent().getStringExtra("demand");

        web = new UMWeb(Constant.MATCH);
        web.setTitle(getString(R.string.share_pub_title));//标题
        UMImage umImage = new UMImage(this, R.mipmap.share_redbag);
        web.setThumb(umImage);  //缩略图
        web.setDescription(getString(R.string.share_pub_description));//描述
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);
    }

    @OnClick({R.id.btn_continue_to_supply, R.id.btn_watch, R.id.WeChat, R.id.moments, R.id.qq, R.id.qzone, R.id.weibo})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_continue_to_supply:
                Intent intent = new Intent();
                intent.setClass(this, PubActivity.class);
                startActivity(intent);
                finish();
                break;
            case R.id.btn_watch:
                //TODO 跳转到发布详情页面
                Intent intent1 = new Intent();
                intent1.putExtra("demandId", demand);
                intent1.setClass(this, DemandActivity.class);
                startActivity(intent1);
                finish();
                break;
            case R.id.WeChat:
                if (api.isWXAppInstalled()) {
                    WXWebpageObject wxWebpageObject = new WXWebpageObject();
                    wxWebpageObject.webpageUrl = Constant.MATCH;

                    WXMediaMessage wxMediaMessage = new WXMediaMessage(wxWebpageObject);
                    wxMediaMessage.description = getString(R.string.share_pub_description);
                    wxMediaMessage.title = getString(R.string.share_pub_title);
                    wxMediaMessage.thumbData = ImageUtils.drawable2Bytes(getResources().getDrawable(R.mipmap.share_redbag), Bitmap.CompressFormat.JPEG);

                    SendMessageToWX.Req req = new SendMessageToWX.Req();
                    req.transaction = String.valueOf(System.currentTimeMillis());
                    req.message = wxMediaMessage;

                    req.scene = SendMessageToWX.Req.WXSceneSession;
                    api.sendReq(req);
                    //记录用户分享状态
                    set(SHARE_SOCORE, true);
                } else {
                    BaseApplication.showToast("请安装微信客户端");
                }
                break;
            case R.id.moments:
                if (umShareAPI.isInstall(this, SHARE_MEDIA.WEIXIN_CIRCLE)) {
                    new ShareAction(this)
                            .setPlatform(SHARE_MEDIA.WEIXIN_CIRCLE)//传入平台
                            .withMedia(web)
                            .setCallback(umShareListener)//回调监听器
                            .share();
                } else {
                    BaseApplication.showToast("请安装微信客户端");
                }

                break;
            case R.id.qq:
                RxPermissions rxPermissions = new RxPermissions(PubSuccessActivity.this);
                rxPermissions.request(Manifest.permission.READ_EXTERNAL_STORAGE).subscribe(new Observer<Boolean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Boolean aBoolean) {
                        if (aBoolean) {
                            if (umShareAPI.isInstall(PubSuccessActivity.this, SHARE_MEDIA.QQ)) {
                                new ShareAction(PubSuccessActivity.this)
                                        .setPlatform(SHARE_MEDIA.QQ)//传入平台
                                        .withMedia(web)
                                        .setCallback(umShareListener)//回调监听器
                                        .share();
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



                break;
            case R.id.qzone:
                if (umShareAPI.isInstall(this, SHARE_MEDIA.QZONE)) {
                    new ShareAction(this)
                            .setPlatform(SHARE_MEDIA.QZONE)//传入平台
                            .withMedia(web)
                            .setCallback(umShareListener)//回调监听器
                            .share();
                } else {
                    BaseApplication.showToast("请安装QQ空间客户端");
                }

                break;
            case R.id.weibo:
                if (umShareAPI.isInstall(this, SHARE_MEDIA.SINA)) {
                    new ShareAction(this)
                            .setPlatform(SHARE_MEDIA.SINA)//传入平台
                            .withMedia(web)
                            .setCallback(umShareListener)//回调监听器
                            .share();
                } else {
                    BaseApplication.showToast("请安装新浪客户端");
                }

                break;
            default:
        }
    }

    @OnClick(R.id.publish_success_back)
    public void onViewClicked() {
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        UMShareAPI.get(this).release();
    }
}
