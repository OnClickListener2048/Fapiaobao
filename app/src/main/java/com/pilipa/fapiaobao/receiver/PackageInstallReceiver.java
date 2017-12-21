package com.pilipa.fapiaobao.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;

import com.example.mylibrary.utils.TLog;
import com.pilipa.fapiaobao.base.BaseApplication;
import com.pilipa.fapiaobao.utils.TDevice;

import java.util.Objects;

/**
 * Created by edz on 2017/11/10.
 */

public class PackageInstallReceiver extends BroadcastReceiver {
    private static PackageInstallReceiver mReceiver = new PackageInstallReceiver();
    private static IntentFilter mIntentFilter;

    public static void registerReceiver(Context context) {
        mIntentFilter = new IntentFilter();
        mIntentFilter.addDataScheme("package");
        mIntentFilter.addAction(Intent.ACTION_PACKAGE_ADDED);
        mIntentFilter.addAction(Intent.ACTION_PACKAGE_REMOVED);
        mIntentFilter.addAction(Intent.ACTION_PACKAGE_REPLACED);
        context.registerReceiver(mReceiver, mIntentFilter);
    }

    public static void unregisterReceiver(Context context) {
        context.unregisterReceiver(mReceiver);
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        if (Intent.ACTION_PACKAGE_ADDED.equals(action)) {
            TLog.log("有应用被添加");
//            Toast.makeText(context, "", Toast.LENGTH_LONG).show();
        } else if (Intent.ACTION_PACKAGE_REMOVED.equals(action)) {
            TLog.log("有应用被删除");
        } else if (Intent.ACTION_PACKAGE_REPLACED.equals(action)) {
//            Toast.makeText(context, "有应用被替换", Toast.LENGTH_LONG).show();
            TLog.log("被替换");
            String packageName = intent.getData().getSchemeSpecificPart();
            TLog.log(packageName);
            TLog.log(TDevice.getRunningActivityName(BaseApplication.context()));
            if (Objects.equals(packageName, TDevice.getRunningActivityName(BaseApplication.context()))) {
                TDevice.IS_UPDATE = true;
            }
        }
    }
}
