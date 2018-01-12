package com.pilipa.fapiaobao.net.callback;

import android.content.Intent;
import android.net.ParseException;
import android.text.TextUtils;

import com.example.mylibrary.utils.NetworkUtils;
import com.example.mylibrary.utils.TLog;
import com.example.mylibrary.utils.TimeUtils;
import com.google.gson.JsonParseException;
import com.lzy.okgo.callback.AbsCallback;
import com.lzy.okgo.exception.HttpException;
import com.lzy.okgo.request.base.Request;
import com.pilipa.fapiaobao.R;
import com.pilipa.fapiaobao.base.BaseApplication;
import com.pilipa.fapiaobao.net.Api;
import com.pilipa.fapiaobao.ui.constants.Constant;
import com.pilipa.fapiaobao.utils.TDevice;

import org.json.JSONException;

import java.io.InterruptedIOException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.net.ConnectException;
import java.net.UnknownHostException;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import okhttp3.Response;

/**
 * Created by edz on 2018/1/8.
 */

public abstract class JsonConvertor<T> extends AbsCallback<T> {
    private static final String TAG = JsonConvert.class.getSimpleName();
    private Type type;
    private Class<T> clazz;

    public JsonConvertor() {
    }

    public JsonConvertor(Type type) {
        this.type = type;
    }

    public JsonConvertor(Class<T> clazz) {
        this.clazz = clazz;
    }

    @Override
    public void onStart(Request<T, ? extends Request> request) {
        super.onStart(request);
    }

    @Override
    public T convertResponse(Response response) throws Throwable {

        if (type == null) {
            if (clazz == null) {
                Type genType = getClass().getGenericSuperclass();
                type = ((ParameterizedType) genType).getActualTypeArguments()[0];
            } else {
                JsonConvert<T> convert = new JsonConvert<>(clazz);
                return convert.convertResponse(response);
            }
        }

        JsonConvert<T> convert = new JsonConvert<>(type);
        return convert.convertResponse(response);
    }

    @Override
    public void onError(com.lzy.okgo.model.Response<T> response) {
        super.onError(response);
        final Throwable e = response.getException();
        Intent intent = new Intent();
        if (e instanceof HttpException) {     //   HTTP错误
            onException(ExceptionReason.BAD_NETWORK);
            intent.setAction(Constant.BAD_NETWORK);
        } else if (e instanceof ConnectException
                || e instanceof UnknownHostException) {   //   连接错误
            onException(ExceptionReason.CONNECT_ERROR);
            intent.setAction(Constant.CONNECT_ERROR);
        } else if (e instanceof InterruptedIOException) {   //  连接超时
            onException(ExceptionReason.CONNECT_TIMEOUT);
            intent.setAction(Constant.CONNECT_TIMEOUT);
        } else if (e instanceof JsonParseException
                || e instanceof JSONException
                || e instanceof ParseException) {   //  解析错误
            onException(ExceptionReason.PARSE_ERROR);
            intent.setAction(Constant.PARSE_ERROR);
        } else if (e instanceof IllegalStateException) {
            handleException(response);
        } else {
            onException(ExceptionReason.UNKNOWN_ERROR);
            intent.setAction(Constant.UNKNOWN_ERROR);
        }
        if (TDevice.hasInternet() && NetworkUtils.isConnected()) {
            Observable.create(new ObservableOnSubscribe<Boolean>() {
                @Override
                public void subscribe(@NonNull ObservableEmitter<Boolean> e) throws Exception {
                    e.onNext(NetworkUtils.isAvailableByPing());
                    e.onComplete();
                }
            }).subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Consumer<Boolean>() {
                        @Override
                        public void accept(@NonNull Boolean aBoolean) throws Exception {
                            if (aBoolean) {
                                Api.RECORD_LOG("Date========" + TimeUtils.millis2String(System.currentTimeMillis()) + "\\n" +
                                        "Throwable.toString========" + e.toString() + "\\n" +
                                        "Throwable.getMessage========" + e.getMessage() + "\\n"
                                );
                            }
                        }
                    });

        }
        BaseApplication.context().sendBroadcast(intent);
    }

    private void handleException(com.lzy.okgo.model.Response<T> response) {
        String message = response.getException().getMessage();

        if (message == null) return;

        if (TextUtils.equals(message, String.valueOf(com.pilipa.fapiaobao.net.Constant.REQUEST_NO_CONTENT))) {
            onNoContent();
        }
    }

    protected void onNoContent() {

    }

    @Override
    public void onCacheSuccess(com.lzy.okgo.model.Response<T> response) {
        super.onCacheSuccess(response);
        TLog.log("onCacheSuccess" + response.message());
    }

    private void onException(ExceptionReason reason) {
        switch (reason) {
            case CONNECT_ERROR:
                BaseApplication.showToast(R.string.connect_error);
                break;
            case CONNECT_TIMEOUT:
                BaseApplication.showToast(R.string.connect_timeout);
                break;
            case BAD_NETWORK:
                BaseApplication.showToast(R.string.bad_network);
                break;
            case PARSE_ERROR:
                BaseApplication.showToast(R.string.parse_error);
                break;
            case UNKNOWN_ERROR:
                BaseApplication.showToast(R.string.unknown_error);
                break;
            default:
                break;
        }
    }

    private enum ExceptionReason {
        BAD_NETWORK,
        CONNECT_ERROR,
        CONNECT_TIMEOUT,
        PARSE_ERROR,
        UNKNOWN_ERROR,
    }
}
