package com.pilipa.fapiaobao.databinding.network.api;


import com.example.mylibrary.utils.NetworkUtils;
import com.google.gson.JsonParseException;
import com.pilipa.fapiaobao.databinding.network.ReturnCode;
import com.pilipa.fapiaobao.databinding.network.api.Exception.ErrorThrowable;
import com.pilipa.fapiaobao.databinding.network.api.Exception.RetryWhenNetworkException;
import com.pilipa.fapiaobao.net.Constant;
import com.pilipa.fapiaobao.net.bean.base.BaseResponseBean;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * <请描述这个类是干什么的>
 *
 * @data: 2016/7/7 13:39
 * @version: V1.0
 */
public class ResponseHandle {

    public static <T> ObservableTransformer<T, T> applySchedulersWithToken() {
        return new ObservableTransformer<T, T>() {
            @Override
            public ObservableSource<T> apply(@NonNull Observable<T> upstream) {
                return upstream.subscribeOn(Schedulers.io())
                        .observeOn(Schedulers.io())
                        .onErrorResumeNext(new ErrorResume())
                        .retryWhen(new RetryWhenNetworkException())
                        .observeOn(AndroidSchedulers.mainThread());
            }
        };
    }

    static class ReadData<E> implements Function<BaseResponseBean<E>, Observable<E>> {

        @Override
        public Observable<E> apply(@NonNull BaseResponseBean<E> eBaseResponseBean) throws Exception {
            if (eBaseResponseBean.getStatus() == Constant.REQUEST_SUCCESS) {
                return Observable.just(eBaseResponseBean.getData());
            } else {
                return Observable.error(new ErrorThrowable(eBaseResponseBean.getStatus(), eBaseResponseBean.getStatus() == Constant.TOKEN_INVALIDE ? "" : eBaseResponseBean.getMsg()));
            }
        }
    }

    static class ErrorResume<E> implements Function<Throwable, Observable<BaseResponseBean<E>>> {

        @Override
        public Observable<BaseResponseBean<E>> apply(@NonNull Throwable throwable) throws Exception {
            if (throwable instanceof UnknownHostException || throwable instanceof JsonParseException) {
                if (!NetworkUtils.isConnected()) {
                    return Observable.error(new ErrorThrowable(ReturnCode.LOCAL_NO_NETWORK, "断网了"));
                }
                return Observable.error(new ErrorThrowable(ReturnCode.LOCAL_SERVER_ERROR, "服务器断了"));
            } else if (throwable instanceof SocketTimeoutException || throwable instanceof ConnectException) {
                return Observable.error(new ErrorThrowable(ReturnCode.LOCAL_TIMEOUT_ERROR, "连接超时"));
            } else if (throwable instanceof ErrorThrowable) {
                return Observable.error(throwable);
            }
            return Observable.error(new ErrorThrowable(ReturnCode.LOCAL_UNKNOWN_ERROR, "未知错误"));
        }
    }


}
