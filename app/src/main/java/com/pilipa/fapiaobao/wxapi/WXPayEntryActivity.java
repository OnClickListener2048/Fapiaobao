package com.pilipa.fapiaobao.wxapi;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.example.mylibrary.utils.TLog;
import com.pilipa.fapiaobao.receiver.WXPayReceiver;
import com.tencent.mm.opensdk.constants.ConstantsAPI;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

/**
 * Created by edz on 2017/10/28.
 */

public class WXPayEntryActivity extends Activity implements IWXAPIEventHandler {
    public static String TAG = "WXPayEntryActivity";
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
        TLog.d(TAG,"baseResp.errStr==="+baseResp.errStr);
        TLog.d(TAG,"baseResp.errCode==="+baseResp.errCode);
        TLog.d(TAG,"baseResp.transaction==="+baseResp.transaction);
        TLog.d(TAG,"baseResp.toString()==="+baseResp.toString());
        TLog.d(TAG,"baseResp.getType()==="+baseResp.getType());
        if (baseResp.getType() == ConstantsAPI.COMMAND_PAY_BY_WX) {
            if (baseResp.errCode == 0) {
//                BaseApplication.showToast("支付成功");
                Intent intent = new Intent();
                intent.setAction(WXPayReceiver.pay_success);
                sendBroadcast(intent);
                setResult(RESULT_OK);
                finish();
            } else if (baseResp.errCode == -2) {
                setResult(RESULT_CANCELED);
                Intent intent = new Intent();
                intent.setAction(WXPayReceiver.pay_fail);
                sendBroadcast(intent);
                finish();
            }
        }


        finish();
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        api.handleIntent(intent, this);
    }


}
