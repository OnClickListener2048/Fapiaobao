package com.pilipa.fapiaobao.databinding.main;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.pilipa.fapiaobao.databinding.base.activity.BaseDatabindingActivity;

/**
 * Created by edz on 2018/3/30.
 */

public class MainDatabindingActivity extends BaseDatabindingActivity {

    public static void show(Context context) {
        context.startActivity(new Intent(context, MainDatabindingActivity.class));
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void init() {

    }

    @Override
    protected int getLayoutId() {
        return 0;
    }
}
