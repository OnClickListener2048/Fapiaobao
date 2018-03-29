package com.pilipa.fapiaobao.databinding.lanucher;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.pilipa.fapiaobao.databinding.base.activity.BaseDatabindingActivity;

/**
 * Created by edz on 2018/3/30.
 */

public class LanucherDatabindingActivity extends BaseDatabindingActivity {


    @Override
    protected void init() {
        LanucherViewModel lanucherViewModel = (LanucherViewModel) getViewModel();
        lanucherViewModel.start();
    }

    @Override
    protected int getLayoutId() {
        return 0;
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
    }


}
