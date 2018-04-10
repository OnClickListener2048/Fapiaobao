package com.pilipa.fapiaobao.databinding.network.api.consumer;

import com.example.mylibrary.utils.ToastUtils;
import com.pilipa.fapiaobao.databinding.network.api.Exception.ErrorThrowable;
import com.pilipa.fapiaobao.databinding.network.impl.BaseImpl;
import com.pilipa.fapiaobao.net.Constant;

import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;

/**
 * Created by edz on 2018/4/10.
 */

public abstract class FObserver<E> implements Observer<E> {

    protected BaseImpl mBase;

    public FObserver(BaseImpl base) {
        mBase = base;
    }

    @Override
    public void onSubscribe(@NonNull Disposable d) {
        mBase.addRxStop(d);
    }

    @Override
    public void onNext(E e) {
        onSuccess(e);
    }

    protected abstract void onSuccess(E e);

    @Override
    public void onError(Throwable t) {
        if (t instanceof ErrorThrowable) {
            ErrorThrowable errorThrowable = (ErrorThrowable) t;
            if (errorThrowable.code == Constant.REQUEST_NO_CONTENT) {
                onNoContent();
            } else if (errorThrowable.code == Constant.TOKEN_INVALIDE) {
                onTokenInvalide();
            } else {
                ToastUtils.showShort(errorThrowable.msg + errorThrowable.code);
            }
        }
    }

    protected void onTokenInvalide() {

    }


    protected void onNoContent() {

    }

    @Override
    public void onComplete() {
    }
}
