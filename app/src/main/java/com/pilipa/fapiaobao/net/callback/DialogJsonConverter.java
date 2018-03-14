package com.pilipa.fapiaobao.net.callback;

import com.lzy.okgo.request.base.Request;
import com.pilipa.fapiaobao.base.BaseActivity;

import java.lang.reflect.Type;

/**
 * Created by edz on 2018/3/14.
 */

public abstract class DialogJsonConverter<T> extends JsonConvertor<T> {

    private BaseActivity baseActivity;

    public DialogJsonConverter(BaseActivity baseActivity) {
        this.baseActivity = baseActivity;
    }

    public DialogJsonConverter(Type type) {
        super(type);
    }

    public DialogJsonConverter(Class<T> clazz) {
        super(clazz);
    }

    @Override
    public void onStart(Request<T, ? extends Request> request) {
        super.onStart(request);
        baseActivity.showProgressDialog();
    }

    @Override
    public void onFinish() {
        super.onFinish();
        baseActivity.hideProgressDialog();
    }


}
