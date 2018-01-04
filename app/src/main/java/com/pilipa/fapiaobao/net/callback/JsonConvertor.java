package com.pilipa.fapiaobao.net.callback;

import com.example.mylibrary.utils.TLog;
import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import com.lzy.okgo.callback.AbsCallback;
import com.pilipa.fapiaobao.net.bean.base.BaseResponseBean;

import java.io.Reader;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * Created by edz on 2018/1/8.
 */

public abstract class JsonConvertor<T> extends AbsCallback<T> {

    @Override
    public T convertResponse(Response response) throws Throwable {
        //获取AbsCallback<T>
        Type type = getClass().getGenericSuperclass();
        TLog.log(type.toString());
        Type[] actualTypeArguments = ((ParameterizedType) type).getActualTypeArguments();
        Type type0 = actualTypeArguments[0];
        TLog.log(type0.toString());

        if (!(type0 instanceof ParameterizedType)) {
            throw new IllegalArgumentException("没有填写泛型参数");
        }

        Type rawType = ((ParameterizedType) type0).getRawType();
        Type typeActualTypeArguement = ((ParameterizedType) type0).getActualTypeArguments()[0];

        ResponseBody responseBody = response.body();
        if (responseBody == null) {
            return null;
        }

        Gson gson = new Gson();
        Reader reader = responseBody.charStream();
        JsonReader jsonReader = new JsonReader(reader);
        if (rawType != BaseResponseBean.class) {
            T data = gson.fromJson(jsonReader, type);
            response.close();
            return data;
        }

        return null;
    }
}
