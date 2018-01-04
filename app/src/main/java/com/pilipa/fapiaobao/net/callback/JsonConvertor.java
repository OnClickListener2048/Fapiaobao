package com.pilipa.fapiaobao.net.callback;

import com.example.mylibrary.utils.TLog;
import com.lzy.okgo.callback.AbsCallback;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import okhttp3.Response;

/**
 * Created by edz on 2018/1/8.
 */

public abstract class JsonConvertor<T> extends AbsCallback<T> {

    @Override
    public T convertResponse(Response response) throws Throwable {
        Type type = getClass().getGenericSuperclass();
        TLog.log(type.toString());
        Type[] actualTypeArguments = ((ParameterizedType) type).getActualTypeArguments();
        Type type0 = actualTypeArguments[0];
        TLog.log(type0.toString());


        return null;
    }
}
