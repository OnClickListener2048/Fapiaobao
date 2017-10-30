package com.pilipa.fapiaobao.ui;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

import com.pilipa.fapiaobao.R;
import com.pilipa.fapiaobao.adapter.MessageCenterAdapter;
import com.pilipa.fapiaobao.base.BaseActivity;
import com.pilipa.fapiaobao.base.BaseApplication;
import com.pilipa.fapiaobao.entity.Customer;
import com.pilipa.fapiaobao.net.Api;
import com.pilipa.fapiaobao.net.bean.LoginWithInfoBean;
import com.pilipa.fapiaobao.utils.SharedPreferencesHelper;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by wjn on 2017/10/23.
 */

public class MessageCenterActivity extends BaseActivity {
    private static final String TAG = "MessageCenterActivity";

    @Bind(R.id.listview)
    ListView listView;
    private MessageCenterAdapter messageCenterAdapter;
    @Override
    protected int getLayoutId() {
        return R.layout.activity_message_center;
    }

    @OnClick({R.id._back})
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id._back:{
                finish();
            }break;
        }
    }

    @Override
    public void initView() {
        messageCenterAdapter = new MessageCenterAdapter(this);
        listView.setAdapter(messageCenterAdapter);
    }

    @Override
    public void initData() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    private void updateUserInfo(Customer customer){
        LoginWithInfoBean loginBean = SharedPreferencesHelper.loadFormSource(MessageCenterActivity.this,LoginWithInfoBean.class);
        if(loginBean != null){
            String token = loginBean.getData().getToken();
            Log.d(TAG, "updateData: userToken"+token);
            if (token == null) {
                BaseApplication.showToast("");
                return;
            } else {
                Api.updateCustomer(token,customer, new Api.BaseViewCallback<LoginWithInfoBean>() {
                    @Override
                    public void setData(LoginWithInfoBean loginBean) {

                    }
                });
            }
        }
    }
}
