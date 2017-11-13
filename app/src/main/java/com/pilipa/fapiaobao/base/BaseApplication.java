package com.pilipa.fapiaobao.base;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.multidex.MultiDex;
import android.support.v4.content.SharedPreferencesCompat;
import android.util.Log;
import android.view.Gravity;
import android.widget.Toast;

import com.example.mylibrary.utils.Utils;
import com.example.mylibrary.widget.SimplexToast;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lljjcoder.city_20170724.bean.ProvinceBean;
import com.lljjcoder.city_20170724.utils;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.interceptor.HttpLoggingInterceptor;
import com.pilipa.fapiaobao.AppOperator;
import com.pilipa.fapiaobao.Constants.Bugly;
import com.pilipa.fapiaobao.account.AccountHelper;
import com.pilipa.fapiaobao.thirdparty.tencent.push.PushConstant;
import com.pilipa.fapiaobao.wxapi.Constants;
import com.tencent.bugly.crashreport.CrashReport;
import com.umeng.commonsdk.UMConfigure;
import com.umeng.message.IUmengRegisterCallback;
import com.umeng.message.PushAgent;
import com.umeng.socialize.Config;
import com.umeng.socialize.PlatformConfig;
import com.umeng.socialize.UMShareAPI;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;

import okhttp3.OkHttpClient;

/**
 * Created by lyt on 2017/10/9.
 */

public class BaseApplication extends Application {
    private static final String PREF_NAME = "p";
    public static final String DEVICE_TOKEN = "mPushDeviceToken";
    static Context _context;
    private static final int RETRY_COUNT = 3;
    private static final int READ_TIMEOUT = 10*1000;
    private static final int WRITE_TIMEOUT = 7*1000;
    private static final int CONNECT_TIMEOUT = 10*1000;
    public static ArrayList<BaseActivity> activities = new ArrayList<>();
    public static ArrayList<ProvinceBean> mProvinceBeanArrayList = new ArrayList<>();

    public void initOkGo(Application app) {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor("FAPIAOLOG");
        loggingInterceptor.setPrintLevel(HttpLoggingInterceptor.Level.BODY);
        loggingInterceptor.setColorLevel(Level.INFO);
        builder.addInterceptor(loggingInterceptor);
        OkGo.getInstance().init(app)
                .setOkHttpClient(builder.build());
        //全局的读取超时时间
        builder.readTimeout(READ_TIMEOUT, TimeUnit.MILLISECONDS);
        //全局的写入超时时间
        builder.writeTimeout(WRITE_TIMEOUT, TimeUnit.MILLISECONDS);
        //全局的连接超时时间
        builder.connectTimeout(CONNECT_TIMEOUT, TimeUnit.MILLISECONDS);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        MultiDex.install(this);
        _context = getApplicationContext();
        //LeakCanary.install(this);
        //OkGoClient.init();
        initOkGo(this);
        Utils.init(this);
        AccountHelper.init(this);

//        OkGo.getInstance().init(this);
        CrashReport.initCrashReport(getApplicationContext(), Bugly.APP_ID, Bugly.TOOGLE);
        UMConfigure.init(this, PushConstant.APP_KEY, PushConstant.Umeng_Message_Secret, UMConfigure.DEVICE_TYPE_PHONE, PushConstant.Umeng_Message_Secret);
        PushAgent mPushAgent = PushAgent.getInstance(_context);
        mPushAgent.onAppStart();
        mPushAgent.register(new IUmengRegisterCallback() {
            @Override
            public void onSuccess(String s) {
                Log.d("deviceToken",s);
                set("deviceToken",s);
            }

            @Override
            public void onFailure(String s, String s1) {

            }
        });
        Config.DEBUG = true;
        PlatformConfig.setWeixin("wx967daebe835fbeac", "5bb696d9ccd75a38c8a0bfe0675559b3");
        PlatformConfig.setQQZone("1106395149", "gtn8LLR4FxWRvwWb");
        PlatformConfig.setSinaWeibo("3639386105", "63143b3cc202fed0c17baf57030a88a0", "http://sns.whalecloud.com");
        PlatformConfig.setWeixin(Constants.APP_ID, "7df3fe092b8d88ebc28a94b84b5388c3");
        UMShareAPI.get(this);
    }

    public static void saveActivity(BaseActivity baseActivity) {
        for (BaseActivity activity : activities) {
            if (!activities.contains(baseActivity)) {
                activities.add(baseActivity);
            }
        }
    }

    public static void initAreaSelector() {

        AppOperator.getExecutor().execute(new Runnable() {
            @Override
            public void run() {
                String cityJson = utils.getJson(context(), "city_20170724.json");
                Type type = new TypeToken<ArrayList<ProvinceBean>>() {
                }.getType();

                mProvinceBeanArrayList = new Gson().fromJson(cityJson, type);
            }
        });
    }

    public static BaseActivity getActivity(Class clazz) {
        for (BaseActivity activity : activities) {
            if (activity.getClass() == clazz) {
                return activity;
            }
        }

        return null;
    }

    public static void finishAllActivites() {
        for (BaseActivity activity : activities) {
            activity.finish();
        }
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
}
