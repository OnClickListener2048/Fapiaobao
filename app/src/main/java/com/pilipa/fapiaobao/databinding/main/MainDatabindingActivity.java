package com.pilipa.fapiaobao.databinding.main;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.KeyEvent;

import com.pilipa.fapiaobao.R;
import com.pilipa.fapiaobao.databinding.ActivityDatabindingMainBinding;
import com.pilipa.fapiaobao.databinding.base.activity.BaseDatabindingActivity;
import com.pilipa.fapiaobao.databinding.main.viewmodel.MainViewModel;

/**
 * Created by edz on 2018/3/30.
 */

@SuppressWarnings("unchecked")
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
