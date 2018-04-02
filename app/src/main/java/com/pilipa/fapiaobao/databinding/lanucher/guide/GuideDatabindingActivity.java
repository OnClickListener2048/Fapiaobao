package com.pilipa.fapiaobao.databinding.lanucher.guide;

import android.content.Context;
import android.content.Intent;

import com.pilipa.fapiaobao.R;
import com.pilipa.fapiaobao.databinding.ActivityDatabindingGuideBinding;
import com.pilipa.fapiaobao.databinding.base.activity.BaseDatabindingActivity;

/**
 * Created by edz on 2018/4/2.
 */

public class GuideDatabindingActivity extends BaseDatabindingActivity {
    public static void show(Context context) {
        context.startActivity(new Intent(context, GuideDatabindingActivity.class));
    }

    @Override
    protected void init() {
        ActivityDatabindingGuideBinding activityDatabindingGuideBinding = (ActivityDatabindingGuideBinding) getBinding();
        activityDatabindingGuideBinding.setGuideviewmodel(new GuideViewModel(activityDatabindingGuideBinding));
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_databinding_guide;
    }
}
