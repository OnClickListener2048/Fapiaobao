package com.pilipa.fapiaobao.base;

import android.app.Activity;
import android.app.Application;
import android.app.Notification;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Parcelable;
import android.os.StrictMode;
import android.support.multidex.MultiDex;
import android.support.v4.content.SharedPreferencesCompat;
import android.util.Log;
import android.view.Gravity;
import android.widget.RemoteViews;
import android.widget.Toast;

import com.example.mylibrary.utils.AppUtils;
import com.example.mylibrary.utils.TLog;
import com.example.mylibrary.utils.Utils;
import com.example.mylibrary.widget.SimplexToast;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.interceptor.HttpLoggingInterceptor;
import com.pilipa.fapiaobao.Constants.Bugly;
import com.pilipa.fapiaobao.R;
import com.pilipa.fapiaobao.account.AccountHelper;
import com.pilipa.fapiaobao.net.Api;
import com.pilipa.fapiaobao.net.bean.me.FeedbackMessageBean;
import com.pilipa.fapiaobao.net.bean.me.NormalBean;
import com.pilipa.fapiaobao.thirdparty.tencent.push.PushConstant;
import com.pilipa.fapiaobao.ui.DemandActivity;
import com.pilipa.fapiaobao.ui.EstimateLocationActivity;
import com.pilipa.fapiaobao.ui.MessageCenterActivity;
import com.pilipa.fapiaobao.ui.MyQuestionsActivity;
import com.pilipa.fapiaobao.ui.MyRedEnvelopeActivity;
import com.pilipa.fapiaobao.ui.ProvidedActivity;
import com.pilipa.fapiaobao.ui.fragment.FinanceFragment;
import com.pilipa.fapiaobao.utils.TDevice;
import com.pilipa.fapiaobao.wxapi.Constants;
import com.tencent.bugly.crashreport.CrashReport;
import com.tencent.smtt.sdk.QbSdk;
import com.umeng.commonsdk.UMConfigure;
import com.umeng.message.IUmengRegisterCallback;
import com.umeng.message.PushAgent;
import com.umeng.message.UmengMessageHandler;
import com.umeng.message.UmengNotificationClickHandler;
import com.umeng.message.entity.UMessage;
import com.umeng.socialize.Config;
import com.umeng.socialize.PlatformConfig;
import com.umeng.socialize.UMShareAPI;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;

import okhttp3.OkHttpClient;

import static com.pilipa.fapiaobao.net.Constant.GOT_BONUS;
import static com.pilipa.fapiaobao.net.Constant.INCOMPETENT_INVOICE;
import static com.pilipa.fapiaobao.net.Constant.INVOICE_BABY_RESPONSE;
import static com.pilipa.fapiaobao.net.Constant.INVOICE_POST;
import static com.pilipa.fapiaobao.net.Constant.NEWCOME_INVOICE;
import static com.pilipa.fapiaobao.net.Constant.NEW_DEMAND;
import static com.pilipa.fapiaobao.net.Constant.SERVICE_NOTIFICATION;

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
    private PushAgent mPushAgent;
    public static final String PUSH_RECEIVE = "push_receive";
    public static final String SHARE_SOCORE = "share_socore";
    private int mFinalCount;
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
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        _context = getApplicationContext();
        //LeakCanary.install(this);
        //OkGoClient.init();
        initOkGo(this);
        Utils.init(this);
        AccountHelper.init(this);

        CrashReport.initCrashReport(getApplicationContext(), Bugly.APP_ID, Bugly.TOOGLE);
        UMConfigure.init(this, PushConstant.APP_KEY, PushConstant.Umeng_Message_Secret, UMConfigure.DEVICE_TYPE_PHONE, PushConstant.Umeng_Message_Secret);
        mPushAgent = PushAgent.getInstance(_context);
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

        mPushAgent.setDisplayNotificationNumber(10);


        Config.DEBUG = true;
        PlatformConfig.setWeixin("wx967daebe835fbeac", "5bb696d9ccd75a38c8a0bfe0675559b3");
        PlatformConfig.setQQZone("1106395149", "gtn8LLR4FxWRvwWb");
        PlatformConfig.setSinaWeibo("3639386105", "63143b3cc202fed0c17baf57030a88a0", "http://sns.whalecloud.com");
        PlatformConfig.setWeixin(Constants.APP_ID, "7df3fe092b8d88ebc28a94b84b5388c3");
        UMShareAPI.get(this);
        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());
        builder.detectFileUriExposure();
        notificationClick();
        UmengCustom();

        registerActivityLifecycleCallbacks(new ActivityLifecycleCallbacks() {
            @Override
            public void onActivityCreated(Activity activity, Bundle savedInstanceState) {

            }

            @Override
            public void onActivityStarted(Activity activity) {
                mFinalCount++;
                //如果mFinalCount ==1，说明是从后台到前台
                Log.e("onActivityStarted", mFinalCount +"");
                if (mFinalCount == 1){
                    //说明从后台回到了前台
                    TLog.d("BaseApplication","后台回到了前台");
                    //记录用户分享状态
                    boolean b = get(SHARE_SOCORE,false);
                    if(b){
                        set(SHARE_SOCORE, false);
                        Api.shareScoreAdd(AccountHelper.getToken(), new Api.BaseViewCallback<NormalBean>() {
                            @Override
                            public void setData(NormalBean normalBean) {
                                Log.d("BaseApplication", "updateData:shareScoreAdd success");
                            }
                        });
                    }
                }
            }

            @Override
            public void onActivityResumed(Activity activity) {

            }

            @Override
            public void onActivityPaused(Activity activity) {

            }

            @Override
            public void onActivityStopped(Activity activity) {
                mFinalCount--;
                //如果mFinalCount ==0，说明是前台到后台
                Log.i("onActivityStopped", mFinalCount +"");
                if (mFinalCount == 0){
                    //说明从前台回到了后台
                }
            }

            @Override
            public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

            }

            @Override
            public void onActivityDestroyed(Activity activity) {

            }
        });

        QbSdk.preInit(this, new QbSdk.PreInitCallback() {
            @Override
            public void onCoreInitFinished() {
                Log.d("preInit", " onCoreInitFinished  ");
            }

            @Override
            public void onViewInitFinished(boolean b) {
                Log.d("preInit", " onViewInitFinished is " + b);
            }
        });
//
        QbSdk.PreInitCallback cb = new QbSdk.PreInitCallback() {

            @Override
            public void onViewInitFinished(boolean arg0) {
                //x5內核初始化完成的回调，为true表示x5内核加载成功，否则表示x5内核加载失败，会自动切换到系统内核。
                Log.d("initX5Environment", " onViewInitFinished is " + arg0);
            }

            @Override
            public void onCoreInitFinished() {
                Log.d("initX5Environment", " onCoreInitFinished  ");
            }
        };
        //x5内核初始化接口
        QbSdk.initX5Environment(getApplicationContext(),  cb);
        QbSdk.setDownloadWithoutWifi(true);
    }
    private void notificationClick() {
        UmengNotificationClickHandler notificationClickHandler = new UmengNotificationClickHandler() {
            @Override
            public void dealWithCustomAction(Context context, UMessage msg) {
                if (!AppUtils.isAppForeground()) {
                    AppUtils.launchApp("com.pilipa.fapiaobao");
                }
                TLog.log("msg.custom"+msg.custom);
                try {
                    JSONObject jsonObject = new JSONObject(msg.custom);
                    String type = (String) jsonObject.get("type");
                    switch (type) {
                        case NEWCOME_INVOICE:
                            String demandid = (String) jsonObject.get("demandId");
                            Intent intent = new Intent();
                            intent.putExtra("demandId", demandid);
                            intent.setClass(getApplicationContext(), DemandActivity.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(intent);
                            break;
                        case INCOMPETENT_INVOICE:
                            String  company_id = (String) jsonObject.get("companyId");
                            String  order_id = (String) jsonObject.get("orderId");
                            Intent intent1 = new Intent();
                            intent1.putExtra("CompanyId", company_id);
                            intent1.putExtra("OrderId", order_id);
                            intent1.setClass(getApplicationContext(), ProvidedActivity.class);
                            intent1.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(intent1);
                            break;
                        case SERVICE_NOTIFICATION:
                            Intent intent2 = new Intent();
                            intent2.setClass(getApplicationContext(), MessageCenterActivity.class);
                            intent2.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(intent2);
                            break;
                        case GOT_BONUS:
                            String  bonus = (String) jsonObject.get("bonus");
                            BigDecimal bigDecimal = new BigDecimal(bonus);
                            Intent intent3 = new Intent();
                            intent3.putExtra("bonus", String.valueOf(bigDecimal));
                            intent3.setClass(getApplicationContext(), MyRedEnvelopeActivity.class);
                            intent3.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(intent3);
                            TLog.log("startActivity(intent3);");
                            break;
                        case NEW_DEMAND:
                            String  companyId = (String) jsonObject.get("companyId");
                            String  invoiceTypeId = (String) jsonObject.get("invoiceTypeId");
                            String  invoiceVarieties = (String) jsonObject.get("invoiceVarieties");
                            String  invoiceTypeName = (String) jsonObject.get("invoiceTypeName");
                            String  city = (String) jsonObject.get("city");
                            Intent intent4 = new Intent();
                            intent4.putExtra(FinanceFragment.EXTRA_DATA_LABEL, invoiceTypeId);
                            intent4.putExtra("companyId", companyId);
                            intent4.putExtra("city", city);
                            intent4.putExtra("invoiceVarieties", invoiceVarieties);
                            intent4.putExtra(FinanceFragment.EXTRA_DATA_LABEL_NAME, invoiceTypeName);
                            intent4.setClass(getApplicationContext(), EstimateLocationActivity.class);
                            intent4.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(intent4);
                            TLog.log("startActivity(intent4);");
                            break;
                        case INVOICE_BABY_RESPONSE:
                            JSONArray suggestionList = jsonObject.getJSONArray("suggestionList");
                            List<FeedbackMessageBean.DataBean.ListBean.SuggestionListBean> retList = new Gson().fromJson(suggestionList.toString(),
                                    new TypeToken<List<FeedbackMessageBean.DataBean.ListBean.SuggestionListBean>>() {}.getType());

                            Intent intent5 = new Intent();
                            intent5.putParcelableArrayListExtra("suggestionList", (ArrayList<? extends Parcelable>) retList);
                            intent5.putExtra("flag", true);
                            intent5.setClass(getApplicationContext(), MyQuestionsActivity.class);
                            intent5.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(intent5);
                            TLog.log("startActivity(intent5);");
                            break;
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        };
        mPushAgent.setNotificationClickHandler(notificationClickHandler);
    }

    private void UmengCustom() {
        UmengMessageHandler messageHandler = new UmengMessageHandler() {
            @Override
            public Notification getNotification(Context context, UMessage msg) {

                switch (msg.builder_id) {
                    case 1:
                        Notification.Builder builder = new Notification.Builder(context);
                        RemoteViews myNotificationView = new RemoteViews(context.getPackageName(),
                                R.layout.notification_view);
                        myNotificationView.setTextViewText(R.id.notification_title, msg.title);
                        myNotificationView.setTextViewText(R.id.notification_text, msg.text);
                        myNotificationView.setImageViewBitmap(R.id.notification_large_icon,
                                getLargeIcon(context, msg));
                        myNotificationView.setImageViewResource(R.id.notification_small_icon,
                                getSmallIconId(context, msg));
                        builder.setContent(myNotificationView)
                                .setSmallIcon(getSmallIconId(context, msg))
                                .setTicker(msg.ticker)
                                .setAutoCancel(true);

                        return builder.getNotification();
                    default:
                        JSONObject jsonObject = null;
                        try {
                            jsonObject = new JSONObject(msg.custom);
                            String type = (String) jsonObject.get("type");
                            TLog.d("BaseApplication UmengCustom custom type ",type);
                            if(!INVOICE_POST.equals(type)){//邮寄信息不做处理
                                //默认为0，若填写的builder_id并不存在，也使用默认。
                                Intent intent = new Intent();
                                intent.setAction(PUSH_RECEIVE);
                                sendBroadcast(intent);
                                set(PUSH_RECEIVE, true);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        return super.getNotification(context, msg);
                }
            }
        };
        mPushAgent.setMessageHandler(messageHandler);
    }

    public static void saveActivity(BaseActivity baseActivity) {
        for (BaseActivity activity : activities) {
            if (!activities.contains(baseActivity)) {
                activities.add(baseActivity);
            }
        }
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
            if (TDevice.showToast) SimplexToast.show(context, message, gravity, duration);
    }
}
