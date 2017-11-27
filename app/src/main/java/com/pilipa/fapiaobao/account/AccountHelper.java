package com.pilipa.fapiaobao.account;

import android.annotation.SuppressLint;
import android.app.Application;
import android.text.TextUtils;
import android.util.Log;

import com.example.mylibrary.utils.TLog;
import com.pilipa.fapiaobao.net.Api;
import com.pilipa.fapiaobao.net.bean.LoginWithInfoBean;
import com.pilipa.fapiaobao.utils.SharedPreferencesHelper;

/**
 * Created by edz on 2017/10/30.
 */

public class AccountHelper {
    private static final String TAG = AccountHelper.class.getSimpleName();
    private Application application;
    @SuppressLint("StaticFieldLeak")
    private static AccountHelper instances;
    private LoginWithInfoBean user;

    private AccountHelper(Application application) {
        this.application = application;
    }

    public static void init(Application application) {
        if (instances == null)
            instances = new AccountHelper(application);
        else {
            // reload from source
            instances.user = SharedPreferencesHelper.loadFormSource(instances.application, LoginWithInfoBean.class);
            TLog.d(TAG, "init reload:" + instances.user);
        }
    }

    public static boolean isLogin() {
        return !TextUtils.isEmpty(getToken());
    }
    public static void logout(){
        SharedPreferencesHelper.remove(instances.application,LoginWithInfoBean.class);
//        BaseApplication.finishAllActivites();
//        ActivityUtils.finishAllActivities();
        Log.d(TAG, "logout: success");
    }
    public static String getToken() {
        if (getUser() == null || getUser().getData() == null) {
            return "notoken";
        }
        String token = getUser().getData().getToken();
        Log.d(TAG, "getToken: "+token);
        return token == null ? "notoken" : token;
    }

    public static LoginWithInfoBean.DataBean.CustomerBean getUserCustormer() {
        return SharedPreferencesHelper.loadFormSource(instances.application, LoginWithInfoBean.DataBean.CustomerBean.class);
    }

    public synchronized static LoginWithInfoBean getUser() {
            instances.user = SharedPreferencesHelper.loadFormSource(instances.application, LoginWithInfoBean.class);
        return instances.user;
    }

    public static void isTokenValid(Api.BaseViewCallback baseViewCallback) {
        Api.loginByToken(getToken(), baseViewCallback);
    }

    public static void updateCustomer(LoginWithInfoBean.DataBean.CustomerBean customer){
        instances.user = SharedPreferencesHelper.loadFormSource(instances.application, LoginWithInfoBean.class);
        if (getUser() != null || getUser().getData() != null) {
            instances.user.getData().getCustomer().setTelephone(customer.getTelephone());
            instances.user.getData().getCustomer().setGender(customer.getGender());
            instances.user.getData().getCustomer().setBirthday(customer.getBirthday());
            instances.user.getData().getCustomer().setNickname(customer.getNickname());
            instances.user.getData().getCustomer().setHeadimg(customer.getHeadimg());
            instances.user.getData().getCustomer().setEmail(customer.getEmail());

            if(customer.getOpenid() != null){
                instances.user.getData().getCustomer().setOpenid(customer.getOpenid());
            }
            SharedPreferencesHelper.save(instances.application,instances.user);
        }
    }
}
