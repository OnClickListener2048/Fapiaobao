package com.pilipa.fapiaobao.databinding.base.activity;

import android.arch.lifecycle.LifecycleOwner;
import android.content.DialogInterface;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;

import com.example.mylibrary.utils.TLog;
import com.pilipa.fapiaobao.R;
import com.pilipa.fapiaobao.databinding.base.viewmodel.BaseViewModel;
import com.pilipa.fapiaobao.databinding.network.impl.BaseImpl;
import com.pilipa.fapiaobao.ui.dialog.LoadingDialog;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * Created by edz on 2018/3/30.
 */

public abstract class BaseDatabindingActivity<VB extends ViewDataBinding, VM extends BaseViewModel> extends AppCompatActivity implements LifecycleOwner, BaseImpl, DialogInterface.OnKeyListener, DialogInterface.OnDismissListener {

    protected VB mBinding;
    protected VM mBaseViewModel;
    private CompositeDisposable disposables2Stop;// 管理Stop取消订阅者者
    private CompositeDisposable disposables2Destroy;// 管理Destroy取消订阅者者
    private LoadingDialog progressDialog;
    @SuppressWarnings("unchecked")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getLayoutId() != 0) {
            mBinding = DataBindingUtil.setContentView(this, getLayoutId());
        }

        if (disposables2Destroy != null) {
            throw new IllegalStateException("onCreate called multiple times");
        }
        disposables2Destroy = new CompositeDisposable();
        initProgressDialog();
        mBaseViewModel = (VM) new BaseViewModel(mBinding);
        getLifecycle().addObserver(mBaseViewModel);
        init();

    }

    protected abstract void init();

    private void initProgressDialog() {
        if (progressDialog == null) {
            progressDialog = new LoadingDialog(this, R.style.CustomDialog);
        }
        progressDialog.setOnDismissListener(this);
    }

    /**
     * @return
     */
    protected abstract int getLayoutId();

    protected VB getBinding() {
        return mBinding;
    }

    protected VM getViewModel() {
        return mBaseViewModel;
    }

    @Override
    public boolean addRxStop(Disposable disposable) {
        if (disposables2Stop == null) {
            disposables2Stop = new CompositeDisposable();
        }
        disposables2Stop.add(disposable);
        return true;
    }

    @Override
    public boolean addRxDestroy(Disposable disposable) {
        if (disposables2Destroy == null) {
            throw new IllegalStateException(
                    "addUtilDestroy should be called between onCreate and onDestroy");
        }
        disposables2Destroy.add(disposable);
        return true;
    }

    @Override
    public void remove(Disposable disposable) {
        if (disposables2Stop == null && disposables2Destroy == null) {
            throw new IllegalStateException("remove should not be called after onDestroy");
        }
        if (disposables2Stop != null) {
            disposables2Stop.remove(disposable);
        }
        if (disposables2Destroy != null) {
            disposables2Destroy.remove(disposable);
        }
    }

    public void showProgressDialog() {
        TLog.log("showProgressDialog" + getClass().getSimpleName());
        if (isFinishing()) return;
        if (progressDialog == null) return;
        if (progressDialog.isShowing()) return;
        progressDialog.show();
    }


    public void showProgressDialog(String description) {
        if (!isFinishing()) {
            if (progressDialog != null) {
                if (!progressDialog.isShowing()) {
                    progressDialog.setMessage(description);
                    progressDialog.show();
                }
            }
        }
    }

    public void updateDialogWithDescription(String description) {
        if (progressDialog != null) {
            progressDialog.setMessage(description);
        }
    }

    public void updateDialog(String description) {
        if (progressDialog != null) {
            progressDialog.setMessage(String.format(getString(R.string.upload_receipt_dialog), description));
        }
    }

    public void hideProgressDialog() {
        TLog.log("hideProgressDialog" + getClass().getSimpleName());
        if (progressDialog != null) {
            if (progressDialog.isShowing()) {
                progressDialog.dismiss();
            }
        }
    }

    /**
     * 显示ProgressDialog
     */
    @Override
    public void showProgress(String msg) {
        showProgressDialog();
    }

    /**
     * 取消ProgressDialog
     */
    @Override
    public void dismissProgress() {
        hideProgressDialog();
    }

    @Override
    public void onStart() {
        super.onStart();
        if (disposables2Stop == null) {
            disposables2Stop = new CompositeDisposable();
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        if (disposables2Stop == null) {
            throw new IllegalStateException("onStop called multiple times or onStart not called");
        }
        disposables2Stop.dispose();
        disposables2Stop = null;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (disposables2Destroy == null) {
            throw new IllegalStateException(
                    "onDestroy called multiple times or onCreate not called");
        }
        disposables2Destroy.dispose();
        disposables2Destroy = null;
    }

    @Override
    public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
        return false;
    }

    @Override
    public void onDismiss(DialogInterface dialog) {
        remove(disposables2Stop);
    }
}
