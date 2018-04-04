package com.pilipa.fapiaobao.databinding.main;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.KeyEvent;

import com.orhanobut.logger.Logger;
import com.pilipa.fapiaobao.R;
import com.pilipa.fapiaobao.databinding.ActivityDatabindingMainBinding;
import com.pilipa.fapiaobao.databinding.base.activity.BaseDatabindingActivity;
import com.pilipa.fapiaobao.databinding.main.viewmodel.MainViewModel;
import com.pilipa.fapiaobao.databinding.network.api.DataManager;
import com.pilipa.fapiaobao.net.bean.base.BaseResponseBean;
import com.pilipa.fapiaobao.net.bean.me.demandlist.DemandListItem;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;

/**
 * Created by edz on 2018/3/30.
 */

public class MainDatabindingActivity extends BaseDatabindingActivity {

    private MainViewModel mMainViewModel;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityDatabindingMainBinding activityDatabindingMainBinding = (ActivityDatabindingMainBinding) getBinding();
        mMainViewModel = new MainViewModel(activityDatabindingMainBinding);
        activityDatabindingMainBinding.setMainviewmodel(mMainViewModel);
    }

    @Override
    protected void init() {
        DataManager.getDemandList("0", "9cda8b3d-eb0a-4e52-9ac6-d955684e2845")
                .subscribe(new Observer<BaseResponseBean<List<DemandListItem>>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        Logger.d("onSubscribe");
                    }

                    @Override
                    public void onNext(@NonNull BaseResponseBean<List<DemandListItem>> listBaseResponseBean) {
                        Logger.d(listBaseResponseBean);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Logger.d("onError" + e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_databinding_main;
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
            mMainViewModel.exit();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

}
