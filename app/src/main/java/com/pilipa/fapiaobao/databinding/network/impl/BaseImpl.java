package com.pilipa.fapiaobao.databinding.network.impl;

import io.reactivex.disposables.Disposable;

/**
 * Created by edz on 2018/4/10.
 */

public interface BaseImpl {
    boolean addRxStop(Disposable disposable);

    boolean addRxDestroy(Disposable disposable);

    void remove(Disposable disposable);

    /**
     * 显示ProgressDialog
     */
    void showProgress(String msg);

    /**
     * 取消ProgressDialog
     */
    void dismissProgress();
}
