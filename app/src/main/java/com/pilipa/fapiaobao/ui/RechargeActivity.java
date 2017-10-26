package com.pilipa.fapiaobao.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.pilipa.fapiaobao.R;
import com.pilipa.fapiaobao.base.BaseActivity;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by lyt on 2017/10/24.
 */

public class RechargeActivity extends BaseActivity {
    @Bind(R.id.tv_recharge_10)
    TextView tv_recharge_10;
    @Bind(R.id.tv_recharge_30)
    TextView tv_recharge_30;
    @Bind(R.id.tv_recharge_100)
    TextView tv_recharge_100;
    @Bind(R.id.tv_recharge_500)
    TextView tv_recharge_500;
    @Override
    protected int getLayoutId() {
        return R.layout.activity_rechange;
    }

    @OnClick({R.id._back,R.id.tv_recharge_10,R.id.tv_recharge_30,R.id.tv_recharge_100,R.id.tv_recharge_500})
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id._back: {
                finish();
            }break;
            case R.id.tv_recharge_10: {
                selected(1);
            }break;
            case R.id.tv_recharge_30: {
                selected(2);
            }break;
            case R.id.tv_recharge_100: {
                selected(3);
            }break;
            case R.id.tv_recharge_500: {
                selected(4);
            }break;
        }
    }

    @Override
    public void initView() {
    }

    @Override
    public void initData() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
        selected(1);

    }
    public void selected(int selectedId) {
        tv_recharge_10.setSelected(selectedId == 1);
        tv_recharge_30.setSelected(selectedId == 2);
        tv_recharge_100.setSelected(selectedId == 3);
        tv_recharge_500.setSelected(selectedId == 4);
    }


}
