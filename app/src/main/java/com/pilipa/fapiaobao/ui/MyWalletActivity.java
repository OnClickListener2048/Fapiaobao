package com.pilipa.fapiaobao.ui;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.pilipa.fapiaobao.R;
import com.pilipa.fapiaobao.account.AccountHelper;
import com.pilipa.fapiaobao.base.BaseActivity;
import com.pilipa.fapiaobao.net.Api;
import com.pilipa.fapiaobao.net.bean.LoginWithInfoBean;
import com.pilipa.fapiaobao.utils.SharedPreferencesHelper;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by lyt on 2017/10/17.
 */

public class MyWalletActivity extends BaseActivity {
    @Bind(R.id.tv_bouns)
    TextView tv_bouns;
    @Bind(R.id.tv_amount)
    TextView tv_amount;
    private Dialog mDialog;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_wallet;
    }

    @OnClick({R.id._back,R.id.btn_recharge,R.id.btn_withdraw,R.id.tv_recharge_details,R.id.my_red_envelope})
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id._back:{
                finish();
            }break;
            case R.id.btn_recharge:{
                startActivity(new Intent(this,RechargeActivity.class));
            }break;
            case R.id.btn_withdraw:{
                setDialog();
            }break;
            case R.id.tv_recharge_details:{
                startActivity(new Intent(this,RechargeDetailsActivity.class));
            }break;
            case R.id.my_red_envelope:{
                Intent intent = new Intent();
                intent.putExtra("bonus", tv_bouns.getText().toString().trim());
                intent.setClass(this, MyRedEnvelopeActivity.class);
                startActivity(intent);
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
    protected void onStart() {
        super.onStart();
        AccountHelper.isTokenValid(new Api.BaseViewCallback<LoginWithInfoBean>() {
            @Override
            public void setData(LoginWithInfoBean loginWithInfoBean) {
                if (loginWithInfoBean.getStatus() == 200) {
                    if (AccountHelper.getToken() != null && AccountHelper.getToken() != "") {
                        SharedPreferencesHelper.save(MyWalletActivity.this, loginWithInfoBean);
                        tv_bouns.setText(loginWithInfoBean.getData().getCustomer().getBonus()+"");
                        tv_amount.setText(loginWithInfoBean.getData().getCustomer().getAmount()+"");
                    }
                }
            }
        });
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
