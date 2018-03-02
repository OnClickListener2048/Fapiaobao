package com.pilipa.fapiaobao.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.example.mylibrary.utils.TLog;
import com.pilipa.fapiaobao.R;
import com.pilipa.fapiaobao.account.AccountHelper;
import com.pilipa.fapiaobao.adapter.supply.MessageCenterAdapter;
import com.pilipa.fapiaobao.base.BaseApplication;
import com.pilipa.fapiaobao.base.BaseNoNetworkActivity;
import com.pilipa.fapiaobao.net.Api;
import com.pilipa.fapiaobao.net.bean.me.MessageListBean;
import com.pilipa.fapiaobao.net.bean.me.NormalBean;
import com.pilipa.fapiaobao.ui.constants.Constant;
import com.pilipa.fapiaobao.utils.TDevice;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

import static com.pilipa.fapiaobao.base.BaseApplication.PUSH_RECEIVE;
import static com.pilipa.fapiaobao.base.BaseApplication.set;
import static com.pilipa.fapiaobao.net.Constant.REQUEST_SUCCESS;

/**
 * Created by wjn on 2017/10/23.
 */

public class MessageCenterActivity extends BaseNoNetworkActivity implements AdapterView.OnItemClickListener{
    private static final String TAG = "MessageCenterActivity";

    @Bind(R.id.no_content)
    LinearLayout noContent;
    @Bind(R.id.title)
    TextView title;
    @Bind(R.id.lv_content)
    ListView lvContent;
    @Bind(R.id.tips)
    TextView tips;
    @Bind(R.id.smartRefreshLayout)
    SmartRefreshLayout mSmartRefreshLayout;
    private MessageCenterAdapter messageCenterAdapter;
    private List<MessageListBean.DataBean> list = new ArrayList();


    @Override
    protected int getLayoutId() {
        return R.layout.activity_message_center;
    }

    private void initSmartRefreshLayout() {
        mSmartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                messageList();
            }
        });

        mSmartRefreshLayout.setDisableContentWhenRefresh(true);
    }


    @OnClick({R.id._back})
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id._back: {
                finish();
            }
            break;
            default:
        }
    }

    @Override
    public void initView() {
        messageCenterAdapter = new MessageCenterAdapter(this);
        lvContent.setAdapter(messageCenterAdapter);
        lvContent.setOnItemClickListener(this);
        initSmartRefreshLayout();
    }

    @Override
    public void initData() {

    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void onNoNetworkLayoutClicks(View view) {
        messageList();
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
                    mSmartRefreshLayout.finishRefresh();
                }

                @Override
                public void onError() {
showNetWorkErrorLayout();
                }

                @Override
                public void onTokenInvalid() {
                    hideNetWorkErrorLayout();
                    noContent.setVisibility(View.VISIBLE);
                    tips.setText("登陆后才能查看到通知哦");
                }

                @Override
                public void setData(MessageListBean messageListBean) {
                    hideNetWorkErrorLayout();
                    if (messageListBean.getStatus() == 200) {
                        noContent.setVisibility(View.GONE);
                        list = messageListBean.getData();
                        messageCenterAdapter.initData(list);

                    } else if (messageListBean.getStatus() == 400) {
                        noContent.setVisibility(View.VISIBLE);
                        tips.setText("暂时还没有消息哦");
                    }

                    boolean hasNewMsg = false;
                    if (messageListBean.getStatus() == REQUEST_SUCCESS) {
                        List<MessageListBean.DataBean> data = messageListBean.getData();
                        if (data != null) {
                            for (int i = 0; i < data.size(); i++) {
                                if (data.get(i).getUnreadMessages() > 0) {
                                    hasNewMsg = true;
                                    TLog.d("MainActivity", "message center had new message");
                                    break;
                                } else {
                                    TLog.d("MainActivity", "message center no message1");
                                }
                            }
                            if (hasNewMsg) {
                                set(PUSH_RECEIVE, true);
                            } else {
                                set(PUSH_RECEIVE, false);
                            }

                        }
                    }
                }
            });
        } else {
            noContent.setVisibility(View.VISIBLE);
            tips.setText("登陆后才能查看到通知哦");
        }
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        String messageType = list.get(position).getMessageType();
        if (com.pilipa.fapiaobao.net.Constant.MSG_TYPE_GOT_BONUS.equals(messageType)) {
            if (list.get(position).getUnreadMessages() > 0) {
                startActivity(new Intent(this, MyRedEnvelopeActivity.class));
                messageRead(messageType, com.pilipa.fapiaobao.net.Constant.MSG_TYPE_GOT_BONUS);
            } else {
                BaseApplication.showToast("暂无红包");
            }
        } else {
            Intent intent = new Intent(MessageCenterActivity.this, MessageDetailsActivity.class);
            intent.putExtra("title", list.get(position).getMessageTypeName());
            intent.putExtra("type", messageType);
            startActivity(intent);
        }


    }

    private void messageRead(final String id, String type) {
        if (TDevice.hasInternet()) {
            /*修改消息已读状态 （当前类别全部修改）*/
            lvContent.setVisibility(View.VISIBLE);
            Api.messageRead(type, id, AccountHelper.getToken(), new Api.BaseViewCallback<NormalBean>() {
                @Override
                public void setData(NormalBean normalBean) {
                    TLog.d(" private void messageRead(final String type) {", normalBean.getStatus() + "");
                }
            });

        } else {
            lvContent.setVisibility(View.GONE);
            noContent.setVisibility(View.VISIBLE);
            tips.setText("当前没有网络哦~");
        }

    }

    @Override
    public void initDataInResume() {
        mSmartRefreshLayout.autoRefresh(10);
    }
}
