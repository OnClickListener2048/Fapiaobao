package com.pilipa.fapiaobao.databinding.main.viewmodel;

import android.databinding.ViewDataBinding;

import com.example.mylibrary.utils.ActivityUtils;
import com.pilipa.fapiaobao.R;
import com.pilipa.fapiaobao.base.BaseApplication;
import com.pilipa.fapiaobao.databinding.base.viewmodel.BaseViewModel;
import com.pilipa.fapiaobao.databinding.main.MainDatabindingActivity;

/**
 * Created by edz on 2018/4/3.
 */

public class MainViewModel extends BaseViewModel {

    private long mExitTime;

    public MainViewModel(ViewDataBinding viewDataBinding) {
        super(viewDataBinding);
    }

    public void exit() {
        if ((System.currentTimeMillis() - mExitTime) > 2000) {
            BaseApplication.showToast(BaseApplication.context().getString(R.string.click_twice_to_exit));
            mExitTime = System.currentTimeMillis();
        } else {
            ActivityUtils.finishActivity(MainDatabindingActivity.class);
            System.exit(0);
        }
    }


}
