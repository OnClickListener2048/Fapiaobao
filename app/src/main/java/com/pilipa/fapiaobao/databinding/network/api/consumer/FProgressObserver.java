package com.pilipa.fapiaobao.databinding.network.api.consumer;

import com.pilipa.fapiaobao.databinding.network.impl.BaseImpl;

import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;

/**
 * Created by edz on 2018/4/10.
 */

public abstract class FProgressObserver<T> extends FObserver<T> {
    protected FProgressObserver(BaseImpl base) {
        super(base);
    }

    @Override
    public void onSubscribe(@NonNull Disposable d) {
        super.onSubscribe(d);
        mBase.showProgress(null);

    }

    @Override
    public void onComplete() {
        super.onComplete();
        mBase.dismissProgress();
    }
}
