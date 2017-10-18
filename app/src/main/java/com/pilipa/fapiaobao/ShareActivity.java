package com.pilipa.fapiaobao;

import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.pilipa.fapiaobao.base.BaseActivity;
import com.pilipa.fapiaobao.base.BaseApplication;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by lyt on 2017/10/17.
 */

public class ShareActivity extends BaseActivity {

    @Bind(R.id.tv_share)
    TextView tvShare;
    private UMShareListener umShareListener = new UMShareListener() {
        @Override
        public void onStart(SHARE_MEDIA share_media) {
            BaseApplication.showToast("onStart");
        }

        @Override
        public void onResult(SHARE_MEDIA share_media) {
            BaseApplication.showToast("onResult");
        }

        @Override
        public void onError(SHARE_MEDIA share_media, Throwable throwable) {
            BaseApplication.showToast(throwable.getMessage().toString());
        }

        @Override
        public void onCancel(SHARE_MEDIA share_media) {
            BaseApplication.showToast("onCancel");
        }
    };

    @Override
    protected int getLayoutId() {
        return R.layout.activity_share;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick(R.id.tv_share)
    public void onViewClicked() {
        new ShareAction(ShareActivity.this)
                .withText("hello")
                .withMedia(new UMImage(this, R.drawable.ic_arrow))
                .setDisplayList(SHARE_MEDIA.SINA, SHARE_MEDIA.QQ, SHARE_MEDIA.WEIXIN)
                .setCallback(umShareListener)
                .open();
    }
}
