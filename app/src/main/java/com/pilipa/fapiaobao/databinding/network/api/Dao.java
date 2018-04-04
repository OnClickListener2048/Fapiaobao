package com.pilipa.fapiaobao.databinding.network.api;


import com.pilipa.fapiaobao.databinding.network.retrofit.RetrofitDao;

import okhttp3.HttpUrl;

/**
 * <请描述这个类是干什么的>
 *
 * @data: 2016/7/7 11:28
 * @version: V1.0
 */
public class Dao {
    private static ApiService mApiService;

    public static ApiService getApiService() {
        if (mApiService == null) {
            synchronized (Dao.class) {
                if (mApiService == null) {
                    mApiService = RetrofitDao.buildRetrofit(new RetrofitDao.IBuildPublicParams() {
                        @Override
                        public HttpUrl.Builder buildPublicParams(HttpUrl.Builder builder) {
                            return Dao.buildPublicParams(builder);
                        }
                    }).create(ApiService.class);
                }
            }
        }
        return mApiService;
    }

    private static HttpUrl.Builder buildPublicParams(HttpUrl.Builder builder) {
        return builder;
    }


}
