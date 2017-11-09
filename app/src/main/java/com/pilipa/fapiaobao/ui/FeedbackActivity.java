package com.pilipa.fapiaobao.ui;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.pilipa.fapiaobao.R;
import com.pilipa.fapiaobao.account.AccountHelper;
import com.pilipa.fapiaobao.base.BaseActivity;
import com.pilipa.fapiaobao.base.BaseApplication;
import com.pilipa.fapiaobao.net.Api;
import com.pilipa.fapiaobao.net.bean.LoginWithInfoBean;
import com.pilipa.fapiaobao.net.bean.me.FeedBackBean;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by lyt on 2017/10/17.
 */

public class FeedbackActivity extends BaseActivity {

    private Dialog mDialog;
    private EditText edtSuggest;
    @Override
    protected int getLayoutId() {
        return R.layout.activity_feedback;
    }

    @OnClick({R.id.feedback_back,R.id.btn_feedback_confirm})
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.feedback_back:{
                finish();
            }break;
            case R.id.btn_feedback_confirm:{
                String str = edtSuggest.getText().toString().trim();
                if(!str.isEmpty()){
                    suggestion(str);
                }else{
                    BaseApplication.showToast("请认真填写您的意见或建议");
                }
            }break;
        }
    }

    @Override
    public void initView() {
        edtSuggest = (EditText) findViewById(R.id.editText);
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
                R.layout.layout_feedback_tip, null);
        //初始化视图
        root.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDialog.dismiss();
                FeedbackActivity.this.finish();
            }
        });
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
    private void suggestion(final String str) {
        AccountHelper.isTokenValid(new Api.BaseViewCallback<LoginWithInfoBean>() {
            @Override
            public void setData(LoginWithInfoBean loginWithInfoBean) {
                if (loginWithInfoBean.getStatus() == 200) {
                    Api.suggestion(AccountHelper.getToken(),str, new Api.BaseViewCallback<FeedBackBean>() {
                        @Override
                        public void setData(FeedBackBean feedBackBean) {
                            if(feedBackBean.getStatus() == 200){
                                edtSuggest.setText(null);
                                setDialog();
                            }
                            Log.d("", "initData:suggestion success");
                        }
                    });
                }else {
                    startActivity(new Intent(FeedbackActivity.this, LoginActivity.class));
                    finish();
                }
            }
        });
    }
}
