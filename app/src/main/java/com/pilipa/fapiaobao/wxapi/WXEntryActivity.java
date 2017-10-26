package com.pilipa.fapiaobao.wxapi;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.pilipa.fapiaobao.base.BaseApplication;
import com.pilipa.fapiaobao.net.callback.JsonCallBack;
import com.pilipa.fapiaobao.net.model.WXAccessModel;
import com.pilipa.fapiaobao.net.model.WXUserInfo;
import com.pilipa.fapiaobao.net.model.WXmodel;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

/**
 * Created by edz on 2017/10/26.
 */

public class WXEntryActivity extends Activity implements IWXAPIEventHandler {
    private static final String TAG = "WXEntryActivity";
    private IWXAPI api;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        api = WXAPIFactory.createWXAPI(this, Constants.APP_ID);
        api.handleIntent(getIntent(), this);
    }

    @Override
    public void onReq(BaseReq baseReq) {

    }

    @Override
    public void onResp(BaseResp baseResp) {
        if (baseResp instanceof SendAuth.Resp) {
            SendAuth.Resp newResp = (SendAuth.Resp) baseResp;
            //获取微信传回的code

            switch (baseResp.errCode) {
                case BaseResp.ErrCode.ERR_OK:
                    //发送成功
                    String url = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=%s&secret=%s&grant_type=authorization_code&code=%s";
                    String tokenUrl = String.format(url, Constants.APP_ID, Constants.APP_SECRET, newResp.code);
                    OkGo.<WXmodel>get(tokenUrl).execute(new JsonCallBack<WXmodel>(WXmodel.class) {
                        @Override
                        public void onSuccess(Response<WXmodel> response) {
                            WXmodel body = response.body();
                            refreshAccessToken(body);
                        }
                    });
                    Log.i("savedInstanceState", "ERR_OK");
                    BaseApplication.showToast("ERR_OK");
                    break;
                case BaseResp.ErrCode.ERR_USER_CANCEL:
                    Log.i("savedInstanceState", "发送取消ERR_USER_CANCEL");
                    BaseApplication.showToast("发送取消");
                    //发送取消
                    break;
                case BaseResp.ErrCode.ERR_AUTH_DENIED:
                    Log.i("savedInstanceState", "发送取消ERR_AUTH_DENIEDERR_AUTH_DENIEDERR_AUTH_DENIED");
                    BaseApplication.showToast("发送被拒绝");
                    //发送被拒绝
                    break;
                default:
                    Log.i("savedInstanceState", "发送返回breakbreakbreak");
                    BaseApplication.showToast("发送返回");
                    //发送返回
                    break;
            }
            finish();


        }
    }

    private void refreshAccessToken(final WXmodel body) {
        String url = "https://api.weixin.qq.com/sns/auth?access_token=%s&openid=%s";
        String urlToken = String.format(url, body.getAccess_token(), body.getOpenid());
        OkGo.<WXAccessModel>get(urlToken).execute(new JsonCallBack<WXAccessModel>(WXAccessModel.class) {
            @Override
            public void onSuccess(Response<WXAccessModel> response) {
                if (response.body().getErrcode() == 0 && "ok".equals(response.body().getErrmsg())) {
                    getWXUserInfo(body);
                } else {
                    refreshToken(body);
                }
            }
        });
    }

    private void refreshToken(WXmodel body) {
        String url = "https://api.weixin.qq.com/sns/oauth2/refresh_token?appid=%s&grant_type=refresh_token&refresh_token=%s";
        String urlToken = String.format(url, Constants.APP_ID, body.getRefresh_token());
        OkGo.<WXmodel>get(urlToken).execute(new JsonCallBack<WXmodel>(WXmodel.class) {
            @Override
            public void onSuccess(Response<WXmodel> response) {
                getWXUserInfo(response.body());
            }
        });

    }

    private void getWXUserInfo(WXmodel body) {
        String url = "https://api.weixin.qq.com/sns/userinfo?access_token=%s&openid=%s";
        String urlToken = String.format(url, body.getAccess_token(), body.getOpenid());
        OkGo.<WXUserInfo>get(urlToken).execute(new JsonCallBack<WXUserInfo>(WXUserInfo.class) {
            @Override
            public void onSuccess(Response<WXUserInfo> response) {
                BaseApplication.showToast(response.body().toString());
            }
        });
    }
}
