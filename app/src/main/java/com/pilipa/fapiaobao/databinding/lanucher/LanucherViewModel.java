package com.pilipa.fapiaobao.databinding.lanucher;

import android.databinding.ViewDataBinding;
import android.os.Handler;
import android.os.Message;

import com.example.mylibrary.utils.ActivityUtils;
import com.example.mylibrary.utils.SPUtils;
import com.pilipa.fapiaobao.databinding.base.viewmodel.BaseViewModel;
import com.pilipa.fapiaobao.databinding.constant.Constant;
import com.pilipa.fapiaobao.databinding.lanucher.guide.GuideDatabindingActivity;
import com.pilipa.fapiaobao.databinding.main.MainDatabindingActivity;

/**
 * Created by edz on 2018/4/2.
 */

public class LanucherViewModel<VB extends ViewDataBinding> extends BaseViewModel {

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 0) {
                boolean showGuide = SPUtils.getInstance().getBoolean(Constant.SHOW_GUIDE);
                if (showGuide) {
                    ActivityUtils.startActivity(MainDatabindingActivity.class);
                } else {
                    ActivityUtils.startActivity(GuideDatabindingActivity.class);
                }
                ActivityUtils.finishActivity(LanucherDatabindingActivity.class);
            }
        }
    };

    public LanucherViewModel(VB vb) {
        super(vb);
    }

    public void start() {
        handler.sendEmptyMessageDelayed(0, 3000);
    }

}
