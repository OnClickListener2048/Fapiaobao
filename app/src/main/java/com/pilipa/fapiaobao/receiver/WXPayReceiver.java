package com.pilipa.fapiaobao.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Created by edz on 2017/11/7.
 */

public abstract class WXPayReceiver extends BroadcastReceiver {
    public static final String pay_success = "com.pilipa.fapiaobao.wxpay.success";
    public static final String pay_fail = "com.pilipa.fapiaobao.wxpay.failed";
}
