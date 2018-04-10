package com.pilipa.fapiaobao.databinding.network.retrofit;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.lzy.okgo.interceptor.HttpLoggingInterceptor;
import com.pilipa.fapiaobao.BuildConfig;
import com.pilipa.fapiaobao.databinding.network.NetworkConfig;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;

import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 */
public class RetrofitDao {

    private static final String DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";

    private Retrofit mRetrofit;

    private RetrofitDao(IBuildPublicParams iBuildPublicParams, CookieJar cookieJar) {
        if (mRetrofit == null) {
            if (NetworkConfig.getBaseUrl() == null || NetworkConfig.getBaseUrl().trim().equals("")) {
                throw new RuntimeException("网络模块必须设置在Application处调用 请求的地址 调用方法：NetworkConfig.setBaseUrl(String url)");
            }
            HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor(BuildConfig.APPLICATION_ID);
            loggingInterceptor.setColorLevel(Level.INFO);
            loggingInterceptor.setPrintLevel(HttpLoggingInterceptor.Level.BODY);
            OkHttpClient okHttpClient = new OkHttpClient.Builder()
                    .connectTimeout(BuildConfig.CONNECT_TIMEOUT, TimeUnit.SECONDS)
                    .writeTimeout(BuildConfig.WRITE_TIMEOUT, TimeUnit.SECONDS)
                    .readTimeout(BuildConfig.READ_TIMEOUT, TimeUnit.SECONDS)
                    .cookieJar(cookieJar)
                    .addInterceptor(loggingInterceptor)
                    .build();
            Gson gson = new GsonBuilder().setDateFormat(DATE_FORMAT).create();
            mRetrofit = new Retrofit.Builder()
                    .baseUrl(NetworkConfig.getBaseUrl())
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .client(okHttpClient)
                    .build();
        }
    }

    public static Retrofit buildRetrofit(IBuildPublicParams iBuildPublicParams, CookieJar cookieJar) {
        return new RetrofitDao(iBuildPublicParams, cookieJar).mRetrofit;
    }

    public static Retrofit buildRetrofit(IBuildPublicParams iBuildPublicParams) {
        return new RetrofitDao(iBuildPublicParams, new CookieJar() {
            @Override
            public void saveFromResponse(HttpUrl url, List<Cookie> cookies) {

            }

            @Override
            public List<Cookie> loadForRequest(HttpUrl url) {
                return new ArrayList<>();
            }
        }).mRetrofit;
    }

    public interface IBuildPublicParams {
        HttpUrl.Builder buildPublicParams(HttpUrl.Builder builder);
    }
}
