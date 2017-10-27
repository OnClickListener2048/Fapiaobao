package com.pilipa.fapiaobao.ui;

import android.app.Dialog;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;

import com.pilipa.fapiaobao.R;
import com.pilipa.fapiaobao.base.BaseActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by lyt on 2017/10/17.
 */

public class MyRedEnvelopeActivity extends BaseActivity {
    private Dialog mDialog;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_red_envelope;
    }

    @OnClick({R.id._back,R.id.btn_withdraw,R.id.btn_recharge})
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id._back:{
                finish();
            }break;
            case R.id.btn_withdraw:{
                setDialog();
            }break;
            case R.id.btn_recharge:{
            }break;
            case R.id.btn_confirm:{
                mDialog.dismiss();
            }break;
            case R.id.btn_cancel1:{
                mDialog.dismiss();
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
    }
    private void setDialog() {
        mDialog = new Dialog(this, R.style.BottomDialog);
        LinearLayout root = (LinearLayout) LayoutInflater.from(this).inflate(
                R.layout.layout_withdraw_tip, null);
        //初始化视图
        root.findViewById(R.id.btn_confirm).setOnClickListener(this);
        root.findViewById(R.id.btn_cancel1).setOnClickListener(this);
        mDialog.setContentView(root);
        Window dialogWindow = mDialog.getWindow();
        dialogWindow.setGravity(Gravity.CENTER);
//        dialogWindow.setWindowAnimations(R.style.dialogstyle); // 添加动画
        WindowManager.LayoutParams lp = dialogWindow.getAttributes(); // 获取对话框当前的参数值
        lp.x = 0; // 新位置X坐标
        lp.y = 0; // 新位置Y坐标
        lp.width = (int) getResources().getDisplayMetrics().widthPixels; // 宽度
        root.measure(0, 0);
        lp.height = root.getMeasuredHeight();

        lp.alpha = 9f; // 透明度
        dialogWindow.setAttributes(lp);
        mDialog.show();
    }
}
