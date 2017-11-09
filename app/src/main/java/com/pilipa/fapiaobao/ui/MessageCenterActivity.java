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

import static com.pilipa.fapiaobao.net.Constant.MSG_TYPE_GOT_BONUS;
import static com.pilipa.fapiaobao.net.Constant.MSG_TYPE_INCOMPETENT_INVOICE;
import static com.pilipa.fapiaobao.net.Constant.MSG_TYPE_NEWCOME_INVOICE;
import static com.pilipa.fapiaobao.net.Constant.MSG_TYPE_SERVICE_NOTIFICATION;

/**
 * Created by wjn on 2017/10/23.
 */

public class MessageCenterActivity extends BaseActivity implements AdapterView.OnItemClickListener {
    private static final String TAG = "MessageCenterActivity";

    @Bind(R.id.listview)
    ListView listView;
    private MessageCenterAdapter messageCenterAdapter;
    private List<MessageListBean.DataBean> list = new ArrayList();
    private MessageListBean.DataBean receiceData ;
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
                    Api.messageList(loginWithInfoBean.getData().getToken(), new Api.BaseViewCallback<MessageListBean>() {
                        @Override
                        public void setData(MessageListBean messageListBean) {
                            if(messageListBean.getStatus() == 200){
                                receiceData = messageListBean.getData();
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
        Intent intent = null;
        switch ((position+1)+""){
            case MSG_TYPE_NEWCOME_INVOICE:{
                 intent = new Intent(MessageCenterActivity.this,MessageDetailsActivity.class);
                intent.putExtra("title","新票到账");
                intent.putParcelableArrayListExtra("dataList1",receiceData.get_$1());
            }break;
            case MSG_TYPE_GOT_BONUS:{
                intent = new Intent(MessageCenterActivity.this,MessageDetailsActivity.class);
                intent.putExtra("title","收到红包");
                intent.putParcelableArrayListExtra("dataList2",receiceData.get_$2());
            }break;
            case MSG_TYPE_INCOMPETENT_INVOICE:{
                intent = new Intent(MessageCenterActivity.this,MessageDetailsActivity.class);
                intent.putExtra("title","新票到账");
                intent.putParcelableArrayListExtra("dataList3",receiceData.get_$3());

            }break;
            case MSG_TYPE_SERVICE_NOTIFICATION:{
                intent = new Intent(MessageCenterActivity.this,MessageDetailsActivity.class);
                intent.putExtra("title","服务通知");
                intent.putParcelableArrayListExtra("dataList4",receiceData.get_$4());
            }break;
        }
        if(intent != null){
//            startActivity(intent);
        }
    }
}
