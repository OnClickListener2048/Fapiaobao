package com.pilipa.fapiaobao.net.callback;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;
import com.lzy.okgo.callback.AbsCallback;
import com.lzy.okgo.request.base.Request;
import com.pilipa.fapiaobao.base.BaseApplication;
import com.pilipa.fapiaobao.utils.TDevice;

import java.lang.reflect.Type;

import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * Created by lyt on 2017/10/12.
 */

public abstract class JsonCallBack<T> extends AbsCallback<T> {
    private Type type;
    private Class<T> clazz;

    public JsonCallBack(Type type) {
        this.type = type;
    }

    public JsonCallBack(Class<T> clazz) {
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
        if (TDevice.hasInternet()) {
            if (!response.isSuccessful()) {
                BaseApplication.showToast("网络异常");
            }
        } else {
            BaseApplication.showToast("请检查网络~");
            return;
        }

    }
}
