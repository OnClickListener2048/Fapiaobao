package com.example.mylibrary.utils;

import android.text.TextUtils;
import android.util.Log;

import com.example.mylibrary.BuildConfig;

/**
 * Created by lyt on 2017/10/9.
 */

public class TLog {
    private static final String LOG_TAG = "OSChinaLog";
    private static boolean DEBUG = BuildConfig.DEBUG;

    private TLog() {
    }

    public static void error(String log) {
        if (DEBUG && !TextUtils.isEmpty(log)) Log.e(LOG_TAG, "" + log);
    }

    public static void log(String log) {
        if (DEBUG && !TextUtils.isEmpty(log)) Log.i(LOG_TAG, log);
    }

    public static void log(String tag, String log) {
        if (DEBUG && !TextUtils.isEmpty(tag) && !TextUtils.isEmpty(log)) Log.i(tag, log);
    }

    public static void d(String tag, String log) {
        if (DEBUG && !TextUtils.isEmpty(tag) && !TextUtils.isEmpty(log)) Log.d(tag, log);
    }

    public static void e(String tag, String log) {
        if (DEBUG && !TextUtils.isEmpty(tag) && !TextUtils.isEmpty(log)) Log.e(tag, log);
    }

    public static void i(String tag, String log) {
        if (DEBUG && !TextUtils.isEmpty(tag) && !TextUtils.isEmpty(log)) Log.i(tag, log);
    }
}
