package com.pilipa.fapiaobao.ui;

import android.app.Dialog;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.mylibrary.utils.NetworkUtils;
import com.pilipa.fapiaobao.R;
import com.pilipa.fapiaobao.account.AccountHelper;
import com.pilipa.fapiaobao.base.BaseActivity;
import com.pilipa.fapiaobao.base.BaseApplication;
import com.pilipa.fapiaobao.net.Api;
import com.pilipa.fapiaobao.net.bean.LoginWithInfoBean;
import com.pilipa.fapiaobao.net.bean.me.NormalBean;
import com.pilipa.fapiaobao.wxapi.Constants;
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.pilipa.fapiaobao.net.Constant.ACCOUNT_TYPE_WALLET;

/**
 * Created by lyt on 2017/10/17.
 */

public class Withdraw2WXActivity extends BaseActivity {
    @Bind(R.id.amount)
    TextView tv_amount;
    private Dialog mDialog;
    private String amount;
    @Override
    protected int getLayoutId() {
        return R.layout.activity_withdraw_to_wx;
    }

    @OnClick({R.id._back, R.id.btn_withdraw})
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id._back: {
                finish();
            }
            break;
            case R.id.btn_withdraw: {
                setDialog();
            }
            break;
        }
    }

    @Override
    public void initView() {
        regexToWX();
    }

    @Override
    public void initData() {
        amount = getIntent().getStringExtra("amount");
        this.tv_amount.setText(amount);
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
       TextView tv_bouns = (TextView)root.findViewById(R.id.tv_bouns);
        tv_bouns.setText(amount);
        //初始化视图
        root.findViewById(R.id.btn_confirm).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AccountHelper.isTokenValid(new Api.BaseViewCallback<LoginWithInfoBean>() {
                    @Override
                    public void setData(LoginWithInfoBean loginWithInfoBean) {
                        if(loginWithInfoBean.getData().getCustomer().getOpenid() != null){
                            Api.withdaw(loginWithInfoBean.getData().getToken()
                                    ,ACCOUNT_TYPE_WALLET
                                    ,NetworkUtils.getIPAddress(true)
                                    ,Double.parseDouble(tv_amount.getText().toString())
                                    ,loginWithInfoBean.getData().getCustomer().getOpenid()
                                    , new Api.BaseViewCallback<NormalBean>() {
                                        @Override
                                        public void setData(NormalBean normalBean) {
                                            if (normalBean.getStatus() ==200) {
                                                BaseApplication.showToast("提现成功");
                                                finish();
                                            }
                                        }
                                    });
                        }else{
                            weChatLogin();
                        }
                    }
                });
                mDialog.dismiss();
            }
        });
        root.findViewById(R.id.btn_cancel1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDialog.dismiss();
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

    private IWXAPI api;
    private void regexToWX() {
        api = WXAPIFactory.createWXAPI(this, Constants.APP_ID, true);
        api.registerApp(Constants.APP_ID);
    }
    private void weChatLogin() {
        SendAuth.Req req = new SendAuth.Req();
        req.scope = "snsapi_userinfo";
        req.state = "wechat_sdk_demo_test";
        api.sendReq(req);
    }

}
