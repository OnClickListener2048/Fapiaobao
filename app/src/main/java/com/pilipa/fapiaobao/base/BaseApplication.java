package com.pilipa.fapiaobao.base;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.v4.content.SharedPreferencesCompat;
import android.view.Gravity;
import android.widget.Toast;

import com.example.mylibrary.utils.TLog;
import com.example.mylibrary.widget.SimplexToast;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.pilipa.fapiaobao.Constants.Bugly;
import com.pilipa.fapiaobao.thirdparty.tencent.push.PushConstant;
import com.tencent.bugly.crashreport.CrashReport;
import com.umeng.commonsdk.UMConfigure;
import com.umeng.message.IUmengRegisterCallback;
import com.umeng.message.PushAgent;
import com.umeng.socialize.PlatformConfig;
import com.umeng.socialize.UMShareAPI;

/**
 * Created by lyt on 2017/10/9.
 */

public class BaseApplication extends Application {
    private static final String PREF_NAME = "p";
    static Context _context;

    @Override
    public void onCreate() {
        super.onCreate();
        _context = getApplicationContext();
        //LeakCanary.install(this);
        CrashReport.initCrashReport(getApplicationContext(), Bugly.APP_ID, Bugly.TOOGLE);
        UMConfigure.init(this, PushConstant.APP_KEY, PushConstant.Umeng_Message_Secret, UMConfigure.DEVICE_TYPE_PHONE, PushConstant.Umeng_Message_Secret);
        PushAgent mPushAgent = PushAgent.getInstance(_context);
        mPushAgent.onAppStart();
        mPushAgent.register(new IUmengRegisterCallback() {
            @Override
            public void onSuccess(String s) {
                TLog.log(s);
            }

            @Override
            public void onFailure(String s, String s1) {

            }
        });

//        PlatformConfig.setWeixin("wx967daebe835fbeac", "5bb696d9ccd75a38c8a0bfe0675559b3");
        PlatformConfig.setQQZone("1106395149", "gtn8LLR4FxWRvwWb");
        PlatformConfig.setSinaWeibo("3639386105", "63143b3cc202fed0c17baf57030a88a0", "http://sns.whalecloud.com");
        PlatformConfig.setWeixin("wxb704e8dd773cc94e", "7df3fe092b8d88ebc28a94b84b5388c3");
        UMShareAPI.get(this);
        setupImageLoader();
    }

    public static synchronized BaseApplication context() {
        return (BaseApplication) _context;
    }

    public static void set(String key, int value) {
        SharedPreferences.Editor editor = getPreferences().edit();
        editor.putInt(key, value);
        SharedPreferencesCompat.EditorCompat.getInstance().apply(editor);
    }

    public static void set(String key, boolean value) {
        SharedPreferences.Editor editor = getPreferences().edit();
        editor.putBoolean(key, value);
        SharedPreferencesCompat.EditorCompat.getInstance().apply(editor);
    }

    public static void set(String key, String value) {
        SharedPreferences.Editor editor = getPreferences().edit();
        editor.putString(key, value);
        SharedPreferencesCompat.EditorCompat.getInstance().apply(editor);
    }

    public static boolean get(String key, boolean defValue) {
        return getPreferences().getBoolean(key, defValue);
    }

    public static String get(String key, String defValue) {
        return getPreferences().getString(key, defValue);
    }

    public static int get(String key, int defValue) {
        return getPreferences().getInt(key, defValue);
    }

    public static long get(String key, long defValue) {
        return getPreferences().getLong(key, defValue);
    }

    public static float get(String key, float defValue) {
        return getPreferences().getFloat(key, defValue);
    }

    public static SharedPreferences getPreferences() {
        return context().getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
    }

    public static void showToast(int message) {
        showToast(message, Toast.LENGTH_LONG, 0);
    }

    public static void showToast(String message) {
        showToast(message, Toast.LENGTH_LONG, 0, Gravity.BOTTOM);
    }

    public static void showToast(int message, int icon) {
        showToast(message, Toast.LENGTH_LONG, icon);
    }

    public static void showToast(String message, int icon) {
        showToast(message, Toast.LENGTH_LONG, icon, Gravity.BOTTOM);
    }

    public static void showToastShort(int message) {
        showToast(message, Toast.LENGTH_SHORT, 0);
    }

    public static void showToastShort(String message) {
        showToast(message, Toast.LENGTH_SHORT, 0, Gravity.BOTTOM);
    }

    public static void showToastShort(int message, Object... args) {
        showToast(message, Toast.LENGTH_SHORT, 0, Gravity.BOTTOM, args);
    }

    public static void showToast(int message, int duration, int icon) {
        showToast(message, duration, icon, Gravity.BOTTOM);
    }

    public static void showToast(int message, int duration, int icon,
                                 int gravity) {
        showToast(context().getString(message), duration, icon, gravity);
    }

    public static void showToast(int message, int duration, int icon,
                                 int gravity, Object... args) {
        showToast(context().getString(message, args), duration, icon, gravity);
    }

    public static void showToast(String message, int duration, int icon, int gravity) {
        Context context = _context;
        if (context != null)
            SimplexToast.show(context, message, gravity, duration);
    }
    // 设置图片加载配置 by wjn 2017-10-23
    private void setupImageLoader() {
        ImageLoaderConfiguration config;
        // TODO 未来考虑优化设置,暂时采用默认设置.
        config = ImageLoaderConfiguration.createDefault(this);
        ImageLoader.getInstance().init(config);
    }

}
