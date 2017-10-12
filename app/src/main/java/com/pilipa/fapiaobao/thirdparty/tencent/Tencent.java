package com.pilipa.fapiaobao.thirdparty.tencent;

import com.example.mylibrary.utils.TLog;
import com.pilipa.fapiaobao.base.BaseActivity;
import com.pilipa.fapiaobao.base.BaseApplication;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.UiError;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by dagou on 2017/10/13.
 */

public class Tencent {
    private static com.tencent.tauth.Tencent mTencent;
    private static String Scope = "all";
    public static final String APP_ID = "1106395149";
    public static final String KEY = "gtn8LLR4FxWRvwWb";

    public static void login(BaseActivity baseActivity) {
        mTencent = com.tencent.tauth.Tencent.createInstance(Tencent.APP_ID, BaseApplication.context());
        if (!mTencent.isSessionValid()) {
            mTencent.login(baseActivity, Scope, new IUiListener() {
                @Override
                public void onComplete(Object o) {
                    JSONObject jsonObject = (JSONObject) o;
                    try {
                        String ret = (String) jsonObject.get("ret");
                        TLog.log(ret);
                        String pf = (String) jsonObject.get("pf");
                        TLog.log(pf);
                        String expires_in = (String) jsonObject.get("expires_in");
                        TLog.log(expires_in);
                        String openid = (String) jsonObject.get("openid");
                        TLog.log(openid);
                        String pfkey = (String) jsonObject.get("pfkey");
                        TLog.log(ret);
                        String msg = (String) jsonObject.get("msg");
                        TLog.log(ret);
                        String access_token = (String) jsonObject.get("access_token");
                        TLog.log(ret);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    TLog.log("onComplete ");
                }

                @Override
                public void onError(UiError uiError) {
                    TLog.log("onError");
                }

                @Override
                public void onCancel() {
                    TLog.log("onCancel");
                }
            });

        }

    }
}
