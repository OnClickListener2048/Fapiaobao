package com.pilipa.fapiaobao.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.pilipa.fapiaobao.R;
import com.pilipa.fapiaobao.base.BaseActivity;
import com.pilipa.fapiaobao.ui.fragment.FilterFragment;
import com.pilipa.fapiaobao.ui.widget.LabelsView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author edz
 * @date 2017/10/23
 */

public class EstimateActivity extends BaseActivity {

    @Bind(R.id.dl_container)
    FrameLayout dlContainer;
    @Bind(R.id.dl)
    DrawerLayout dl;
    @Bind(R.id.filter)
    TextView filter;
    @Bind(R.id.estimate_back)
    ImageView estimateBack;
    @Bind(R.id.et_estimate)
    EditText etEstimate;
    @Bind(R.id.estimate_please)
    RelativeLayout estimatePlease;
    @Bind(R.id.test_redbag)
    Button testRedbag;
    @Bind(R.id.publish_cautions)
    TextView publishCautions;
    @Bind(R.id.labels)
    LabelsView labels;
    @Bind(R.id.go)
    Button go;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_estimate;
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void initView() {
        new Thread(){
            @Override
            public void run() {
                addFragment(dlContainer.getId(), FilterFragment.newInstance(new Bundle()));
            }
        }.start();

    }

    @Override
    public void initData() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick(R.id.filter)
    public void onViewClicked() {
        if (dl.isDrawerOpen(Gravity.END)) {
            dl.closeDrawer(Gravity.END);
        } else {

            dl.openDrawer(Gravity.END);
        }

    }

    @OnClick({R.id.test_redbag, R.id.go})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.test_redbag:
                break;
            case R.id.go:
                startActivity(new Intent(this,ConfirmActivity.class));
                break;
        }
    }
}
