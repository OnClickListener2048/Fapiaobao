package com.pilipa.fapiaobao.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.pilipa.fapiaobao.R;
import com.pilipa.fapiaobao.base.BaseActivity;
import com.pilipa.fapiaobao.base.BaseApplication;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by edz on 2017/10/26.
 */

public class PubSuccessActivity extends BaseActivity {
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
    private UMShareListener umShareListener = new UMShareListener() {
        @Override
        public void onStart(SHARE_MEDIA share_media) {

        }

        @Override
        public void onResult(SHARE_MEDIA share_media) {
            Toast.makeText(PubSuccessActivity.this, "分享成功", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onError(SHARE_MEDIA share_media, Throwable throwable) {
            Toast.makeText(PubSuccessActivity.this, "分享失败", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onCancel(SHARE_MEDIA share_media) {
            Toast.makeText(PubSuccessActivity.this, "分享取消", Toast.LENGTH_SHORT).show();
        }
    };
    private String demand;
    private UMShareAPI umShareAPI;

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
    }

    @Override
    public void initData() {
        demand = getIntent().getStringExtra("demand");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.btn_continue_to_supply, R.id.btn_watch, R.id.WeChat, R.id.moments, R.id.qq, R.id.qzone, R.id.weibo})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_continue_to_supply:
                Intent intent = new Intent();
                intent.setClass(this, PubActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_watch:
                //TODO 跳转到发布详情页面
                Intent intent1 = new Intent();
                intent1.putExtra("demandId", demand);
                intent1.setClass(this, DemandActivity.class);
                startActivity(intent1);
                break;
            case R.id.WeChat:
                if (umShareAPI.isInstall(this, SHARE_MEDIA.WEIXIN)) {
                    new ShareAction(this)
                            .setPlatform(SHARE_MEDIA.WEIXIN)//传入平台
                            .withText("hello")//分享内容
                            .setCallback(umShareListener)//回调监听器
                            .share();
                } else {
                    BaseApplication.showToast("请安装微信客户端");
                }

                break;
            case R.id.moments:
                if (umShareAPI.isInstall(this, SHARE_MEDIA.WEIXIN_CIRCLE)) {
                    new ShareAction(this)
                            .setPlatform(SHARE_MEDIA.WEIXIN_CIRCLE)//传入平台
                            .withText("hello")//分享内容
                            .setCallback(umShareListener)//回调监听器
                            .share();
                } else {
                    BaseApplication.showToast("请安装微信客户端");
                }

                break;
            case R.id.qq:
                if (umShareAPI.isInstall(this, SHARE_MEDIA.QQ)) {
                    new ShareAction(this)
                            .setPlatform(SHARE_MEDIA.QQ)//传入平台
                            .withText("hello")//分享内容
                            .setCallback(umShareListener)//回调监听器
                            .share();
                } else {
                    BaseApplication.showToast("请安装QQ客户端");
                }

                break;
            case R.id.qzone:
                if (umShareAPI.isInstall(this, SHARE_MEDIA.QZONE)) {
                    new ShareAction(this)
                            .setPlatform(SHARE_MEDIA.QZONE)//传入平台
                            .withText("hello")//分享内容
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
                            .withText("hello")//分享内容
                            .setCallback(umShareListener)//回调监听器
                            .share();
                } else {
                    BaseApplication.showToast("请安装新浪客户端");
                }

                break;
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
