package com.pilipa.fapiaobao.databinding.network.api.Exception;

import io.reactivex.Observable;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;

/**
 * @author xiaolong
 * @version v1.0
 * @function <描述功能>
 */
public class RetryWhenNetworkException<T> implements Function<Throwable, Observable<T>> {

    @Override
    public Observable<T> apply(@NonNull Throwable throwable) throws Exception {
        return null;
    }
}
