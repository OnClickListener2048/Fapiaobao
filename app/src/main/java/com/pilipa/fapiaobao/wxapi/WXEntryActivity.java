package com.pilipa.fapiaobao.wxapi;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.pilipa.fapiaobao.R;
import com.pilipa.fapiaobao.base.BaseApplication;
import com.pilipa.fapiaobao.net.Api;
import com.pilipa.fapiaobao.net.bean.LoginWithInfoBean;
import com.pilipa.fapiaobao.net.bean.WXAccessModel;
import com.pilipa.fapiaobao.net.bean.WXUserInfo;
import com.pilipa.fapiaobao.net.bean.WXmodel;
import com.pilipa.fapiaobao.net.callback.JsonCallBack;
import com.pilipa.fapiaobao.ui.LoginActivity;
import com.pilipa.fapiaobao.utils.SharedPreferencesHelper;
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
        setContentView(R.layout.item_type);
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
            Intent intent = new Intent();
            intent.setClass(this, LoginActivity.class);
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
                    finish();
//                    startActivity(intent);
                    //发送取消
                    break;
                case BaseResp.ErrCode.ERR_AUTH_DENIED:
                    Log.i("savedInstanceState", "发送取消ERR_AUTH_DENIEDERR_AUTH_DENIEDERR_AUTH_DENIED");
                    BaseApplication.showToast("发送被拒绝");
                    finish();
//                    startActivity(intent);
                    //发送被拒绝
                    break;
                default:
                    Log.i("savedInstanceState", "发送返回breakbreakbreak");
                    BaseApplication.showToast("发送返回");
                    finish();
//                    startActivity(intent);
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
                if (response.isSuccessful()) {

                }
                getWXUserInfo(response.body());
            }
        });

    }

    /**
     * openid : OPENID
     * nickname : NICKNAME
     * sex : 1
     * province : PROVINCE
     * city : CITY
     * country : COUNTRY
     * headimgurl : http://wx.qlogo.cn/mmopen/g3MonUZtNHkdmzicIlibx6iaFqAc56vxLSUfpb6n5WKSYVY0ChQKkiaJSgQ1dZuTOgvLLrhJbERQQ4eMsv84eavHiaiceqxibJxCfHe/0
     * privilege : ["PRIVILEGE1","PRIVILEGE2"]
     * unionid :  o6_bmasdasdsad6_2sgVt7hMZOPfL
     */

    private void getWXUserInfo(final WXmodel body) {
        String deviceToken;
        deviceToken = BaseApplication.get("deviceToken", "");

        Api.login("1", body.getOpenid(), body.getAccess_token(), deviceToken, new Api.BaseViewCallback<LoginWithInfoBean>() {
            @Override
            public void setData(final LoginWithInfoBean loginWithInfoBean) {
                if (loginWithInfoBean.getStatus()==200) {

                    BaseApplication.showToast("微信绑定成功");
                    Intent intent = new Intent();
                    Bundle bundle = new Bundle();
                    bundle.putParcelable("wx_info",body);
                    intent.putExtra("extra_bundle", bundle);
                    intent.setAction(LoginActivity.WX_LOGIN_ACTION);
                    sendBroadcast(intent);

//                    String url = "https://api.weixin.qq.com/sns/userinfo?access_token=%s&openid=%s";
//                    String urlToken = String.format(url, body.getAccess_token(), body.getOpenid());
//                    OkGo.<WXUserInfo>get(urlToken).execute(new JsonCallBack<WXUserInfo>(WXUserInfo.class) {
//
//                        @Override
//                        public void onSuccess(Response<WXUserInfo> response) {
//                            if (response.isSuccessful()) {
//
//                                setResult(RESULT_OK,intent);
//                                finish();
//                            }
//
//                        }
//                    });

                }
            }
        });

    }
}
