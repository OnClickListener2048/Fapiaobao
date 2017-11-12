package com.pilipa.fapiaobao.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.pilipa.fapiaobao.R;
import com.pilipa.fapiaobao.account.AccountHelper;
import com.pilipa.fapiaobao.adapter.MessageCenterAdapter;
import com.pilipa.fapiaobao.base.BaseActivity;
import com.pilipa.fapiaobao.net.Api;
import com.pilipa.fapiaobao.net.bean.LoginWithInfoBean;
import com.pilipa.fapiaobao.net.bean.me.MessageListBean;
import com.pilipa.fapiaobao.utils.TDevice;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by wjn on 2017/10/23.
 */

public class MessageCenterActivity extends BaseActivity {
    private static final String TAG = "MessageCenterActivity";

    @Bind(R.id.no_content)
    LinearLayout noContent;
    @Bind(R.id.title)
    TextView title;
    @Bind(R.id._back)
    ImageView Back;
    @Bind(R.id.rl_title)
    RelativeLayout rlTitle;
    @Bind(R.id.tv_new_title)
    TextView tvNewTitle;
    @Bind(R.id.tv_new_size)
    TextView tvNewSize;
    @Bind(R.id.tv_new_date)
    TextView tvNewDate;
    @Bind(R.id.ll_new)
    LinearLayout llNew;
    @Bind(R.id.tv_redbag_title)
    TextView tvRedbagTitle;
    @Bind(R.id.tv_redbag_size)
    TextView tvRedbagSize;
    @Bind(R.id.tv_redbag_date)
    TextView tvRedbagDate;
    @Bind(R.id.ll_redbag)
    LinearLayout llRedbag;
    @Bind(R.id.tv_unqualify_title)
    TextView tvUnqualifyTitle;
    @Bind(R.id.tv_unqualify_size)
    TextView tvUnqualifySize;
    @Bind(R.id.tv_unqualify_date)
    TextView tvUnqualifyDate;
    @Bind(R.id.ll_unqualify)
    LinearLayout llUnqualify;
    @Bind(R.id.tv_service_title)
    TextView tvServiceTitle;
    @Bind(R.id.tv_service_size)
    TextView tvServiceSize;
    @Bind(R.id.tv_service_date)
    TextView tvServiceDate;
    @Bind(R.id.ll_service)
    LinearLayout llService;
    @Bind(R.id.ll_content)
    LinearLayout llContent;
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
        if (TDevice.hasInternet()) {
            AccountHelper.isTokenValid(new Api.BaseViewCallback<LoginWithInfoBean>() {
                @Override
                public void setData(LoginWithInfoBean loginWithInfoBean) {
                    if (loginWithInfoBean.getStatus() == 200) {
                        Api.messageList(loginWithInfoBean.getData().getToken(), new Api.BaseViewCallback<MessageListBean>() {
                            @Override
                            public void setData(MessageListBean messageListBean) {
                                if (messageListBean.getStatus() == 200) {
                                    llContent.setVisibility(View.VISIBLE);
                                    noContent.setVisibility(View.GONE);
                                    if (receiceData != null) {
                                        setNotifications(receiceData);
                                    }
                                } else if (messageListBean.getStatus() == 400) {
                                    llContent.setVisibility(View.GONE);
                                    noContent.setVisibility(View.VISIBLE);
                                    tips.setText("没有内容");
                                } else {
                                    llContent.setVisibility(View.GONE);
                                    noContent.setVisibility(View.VISIBLE);
                                    tips.setText("登陆后才能查看到红包通知哦");
                                }
                            }
                        });
                    }
                }
            });
        } else {
            llContent.setVisibility(View.GONE);
            noContent.setVisibility(View.VISIBLE);
            tips.setText("当前没有网络哦~");
        }

    }

    private void setNotifications(MessageListBean.DataBean receiceData) {
    }


    @OnClick({R.id.ll_new, R.id.ll_redbag, R.id.ll_unqualify, R.id.ll_service, R.id.ll_content})
    public void onViewClicked(View view) {
        switch (view.getId()) {

            case R.id.ll_new:
                break;
            case R.id.ll_redbag:
                break;
            case R.id.ll_unqualify:
                break;
            case R.id.ll_service:
                break;
            case R.id.ll_content:
                break;
        }
    }
}
