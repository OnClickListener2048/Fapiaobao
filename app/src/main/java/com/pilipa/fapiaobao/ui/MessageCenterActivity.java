package com.pilipa.fapiaobao.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.pilipa.fapiaobao.R;
import com.pilipa.fapiaobao.account.AccountHelper;
import com.pilipa.fapiaobao.adapter.MessageCenterAdapter;
import com.pilipa.fapiaobao.base.BaseActivity;
import com.pilipa.fapiaobao.net.Api;
import com.pilipa.fapiaobao.net.bean.me.MessageListBean;
import com.pilipa.fapiaobao.net.bean.me.NormalBean;
import com.pilipa.fapiaobao.ui.constants.Constant;
import com.pilipa.fapiaobao.utils.TDevice;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by wjn on 2017/10/23.
 */

public class MessageCenterActivity extends BaseActivity implements AdapterView.OnItemClickListener{
    private static final String TAG = "MessageCenterActivity";

    @Bind(R.id.no_content)
    LinearLayout noContent;
    @Bind(R.id.title)
    TextView title;
    @Bind(R.id.lv_content)
    ListView lvContent;
    @Bind(R.id.tips)
    TextView tips;
    private MessageCenterAdapter messageCenterAdapter;
    private List<MessageListBean.DataBean> list = new ArrayList();
    private MessageListBean.DataBean receiceData;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_message_center;
    }

    @OnClick({R.id._back})
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id._back: {
                finish();
            }
            break;
        }
    }

    @Override
    public void initView() {
        messageCenterAdapter = new MessageCenterAdapter(this);
        lvContent.setAdapter(messageCenterAdapter);
        lvContent.setOnItemClickListener(this);

    }

    @Override
    public void initData() {

    }

    @Override
    protected void onResume() {
        messageList();
        super.onResume();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    private void messageList() {
        if (!Constant.NOTOKEN.equals(AccountHelper.getToken())) {
            Api.messageList(AccountHelper.getToken(), new Api.BaseRawResponse<MessageListBean>() {
                @Override
                public void onStart() {

                }

                @Override
                public void onFinish() {

                }

                @Override
                public void onError() {

                }

                @Override
                public void onTokenInvalid() {
                    noContent.setVisibility(View.VISIBLE);
                    tips.setText("登陆后才能查看到通知哦");
                }

                @Override
                public void setData(MessageListBean messageListBean) {
                    if (messageListBean.getStatus() == 200) {
                        noContent.setVisibility(View.GONE);
                        list = messageListBean.getData();
                        messageCenterAdapter.initData(list);

                    } else if (messageListBean.getStatus() == 400) {
                        noContent.setVisibility(View.VISIBLE);
                        tips.setText("暂时还没有消息哦");
                    }
                }
            });
        } else {
            noContent.setVisibility(View.VISIBLE);
            tips.setText("登陆后才能查看到通知哦");
        }
    }

    private void messageRead(final String type) {
        if (TDevice.hasInternet()) {

                        Api.messageRead(AccountHelper.getToken(),type, new Api.BaseViewCallback<NormalBean>() {
                            @Override
                            public void setData(NormalBean normalBean) {

                            }
                        });

        } else {
            noContent.setVisibility(View.VISIBLE);
            tips.setText("当前没有网络哦~");
        }

    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(MessageCenterActivity.this,MessageDetailsActivity.class);
        intent.putExtra("title",list.get(position).getMessageTypeName());
        intent.putExtra("type",list.get(position).getMessageType());
        startActivity(intent);
        messageRead(list.get(position).getMessageType());
    }
}
