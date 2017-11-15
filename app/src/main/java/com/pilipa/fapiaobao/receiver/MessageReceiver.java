package com.pilipa.fapiaobao.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;

import com.pilipa.fapiaobao.base.BaseApplication;

/**
 * Created by edz on 2017/11/10.
 */

public class MessageReceiver extends BroadcastReceiver {
    private static MessageReceiver mReceiver = new MessageReceiver();
    private static IntentFilter mIntentFilter;
    public static void registerReceiver(Context context) {
        mIntentFilter = new IntentFilter();
        mIntentFilter.addAction("org.agoo.android.intent.action.RECEIVE");
        context.registerReceiver(mReceiver, mIntentFilter);
    }

    public static void unregisterReceiver(Context context) {
        context.unregisterReceiver(mReceiver);
    }
    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        BaseApplication.showToast("接受推送");
    }
}
