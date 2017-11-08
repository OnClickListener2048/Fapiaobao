package com.pilipa.fapiaobao.ui;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.pilipa.fapiaobao.R;
import com.pilipa.fapiaobao.account.AccountHelper;
import com.pilipa.fapiaobao.adapter.MessageCenterAdapter;
import com.pilipa.fapiaobao.base.BaseActivity;
import com.pilipa.fapiaobao.net.Api;
import com.pilipa.fapiaobao.net.bean.LoginWithInfoBean;
import com.pilipa.fapiaobao.net.bean.me.MessageListBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by wjn on 2017/10/23.
 */

public class MessageCenterActivity extends BaseActivity implements AdapterView.OnItemClickListener {
    private static final String TAG = "MessageCenterActivity";

    @Bind(R.id.listview)
    ListView listView;
    private MessageCenterAdapter messageCenterAdapter;
    private List<MessageListBean.DataBean> list = new ArrayList();
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
        listView.setOnItemClickListener(this);
    }

    @Override
    public void initData() {
        messageList();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    private void messageList() {
        AccountHelper.isTokenValid(new Api.BaseViewCallback<LoginWithInfoBean>() {
            @Override
            public void setData(LoginWithInfoBean loginWithInfoBean) {
                if (loginWithInfoBean.getStatus() == 200) {
                    Api.messageList(AccountHelper.getToken(), new Api.BaseViewCallback<MessageListBean>() {
                        @Override
                        public void setData(MessageListBean messageListBean) {
                            if(messageListBean.getStatus() == 200){
                                List<MessageListBean.DataBean> beanList = messageListBean.getData();
                                list.addAll(beanList);
                                messageCenterAdapter.initData(messageListBean.getData());
                            }
                            Log.d("", "initData:suggestion success");
                        }
                    });
                }else {
                    startActivity(new Intent(MessageCenterActivity.this, LoginActivity.class));
                    finish();
                }
            }
        });
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(MessageCenterActivity.this,MessageDetailsActivity.class);
//        intent.putExtra()
        startActivity(intent);
    }
}
