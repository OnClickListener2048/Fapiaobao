package com.pilipa.fapiaobao.net.callback;

import android.content.Intent;
import android.net.ParseException;

import com.example.mylibrary.utils.TLog;
import com.example.mylibrary.utils.TimeUtils;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParseException;
import com.google.gson.stream.JsonReader;
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
import java.lang.reflect.Type;
import java.net.ConnectException;
import java.net.UnknownHostException;
import java.util.Arrays;

import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * Created by lyt on 2017/10/12.
 */

public abstract class JsonCallBack<T> extends AbsCallback<T> {
    String TAG = JsonCallBack.class.getSimpleName();
    private Type type;
    private Class<T> clazz;

    protected JsonCallBack(Type type) {
        this.type = type;
    }

    protected JsonCallBack(Class<T> clazz) {
        this.clazz = clazz;
    }

    @Override
    public void onStart(Request<T, ? extends Request> request) {
        super.onStart(request);
    }


    @Override
    public void onFinish() {
        super.onFinish();
    }

    @Override
    public T convertResponse(Response response) throws Throwable {
        ResponseBody body = response.body();
        if (body == null) {
            return null;
        }

        T data = null;
        Gson gson = new GsonBuilder().serializeNulls().create();
        JsonReader jsonReader = new JsonReader(body.charStream());
        if (type != null) {
            data = gson.fromJson(jsonReader, type);
        }
        if (clazz != null) {
            data = gson.fromJson(jsonReader, clazz);
        }
        return data;
    }

    @Override
    public void onError(com.lzy.okgo.model.Response<T> response) {
        super.onError(response);
        Throwable e = response.getException();
        T body = response.body();
//        if (TDevice.hasInternet()) {
//            if (!response.isSuccessful()) {
//                BaseApplication.showToast("网络异常");
//            }
//        } else {
//            BaseApplication.showToast("请检查网络~");
//        }

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
        } else {
            onException(ExceptionReason.UNKNOWN_ERROR);
            intent.setAction(Constant.UNKNOWN_ERROR);
        }
        TLog.d(TAG, Arrays.toString(e.getStackTrace()));
        TLog.d(TAG,e.getMessage());
        if (TDevice.hasInternet()) {
            Api.RECORD_LOG("Date="+TimeUtils.millis2String(System.currentTimeMillis())+"\n"+
                    "Throwable.toString"+e.toString()+"\n"+
            "Throwable.getMessage"+e.getMessage()+"\n"
            );
        }
        BaseApplication.context().sendBroadcast(intent);
    }

    private enum  ExceptionReason {
        BAD_NETWORK,
        CONNECT_ERROR,
        CONNECT_TIMEOUT,
        PARSE_ERROR,
        UNKNOWN_ERROR,
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
}
