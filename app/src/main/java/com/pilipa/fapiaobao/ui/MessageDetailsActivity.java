package com.pilipa.fapiaobao.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.pilipa.fapiaobao.R;
import com.pilipa.fapiaobao.account.AccountHelper;
import com.pilipa.fapiaobao.adapter.MessageDetailsAdapter;
import com.pilipa.fapiaobao.base.BaseActivity;
import com.pilipa.fapiaobao.net.Api;
import com.pilipa.fapiaobao.net.bean.LoginWithInfoBean;
import com.pilipa.fapiaobao.net.bean.me.MessageDetailsBean;
import com.pilipa.fapiaobao.utils.TDevice;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by wjn on 2017/10/23.
 */

public class MessageDetailsActivity extends BaseActivity  {
    @Bind(R.id.title)
    TextView tv_title;
    @Bind(R.id.lv_content)
    ListView listview;
    @Bind(R.id.tips)
    TextView tips;
    @Bind(R.id.no_content)
    LinearLayout noContent;
    private MessageDetailsAdapter adapter;
    private List<MessageDetailsBean.DataBean> list = new ArrayList();

    @Override
    protected int getLayoutId() {
        return R.layout.activity_message_details;
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
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void initView() {

        String title = getIntent().getStringExtra("title");
        String type = getIntent().getStringExtra("type");
        tv_title.setText(title);
        messageDetails(type);
        adapter = new MessageDetailsAdapter(this);
        listview.setAdapter(adapter);
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
    private void messageDetails(final String type) {
        if (TDevice.hasInternet()) {
            AccountHelper.isTokenValid(new Api.BaseViewCallback<LoginWithInfoBean>() {
                @Override
                public void setData(LoginWithInfoBean loginWithInfoBean) {
                    if (loginWithInfoBean.getStatus() == 200) {
                        Api.messageDetails(type,loginWithInfoBean.getData().getToken(), new Api.BaseViewCallback<MessageDetailsBean>() {
                            @Override
                            public void setData(MessageDetailsBean messageDetailsBean) {
                                if (messageDetailsBean.getStatus() == 200) {
                                    noContent.setVisibility(View.GONE);
                                    list.addAll(messageDetailsBean.getData());
                                    adapter.initData(list);
                                } else if (messageDetailsBean.getStatus() == 400) {
                                    noContent.setVisibility(View.VISIBLE);
                                    tips.setText("没有内容");
                                } else {
                                    noContent.setVisibility(View.VISIBLE);
                                    tips.setText("登陆后才能查看到哦");
                                }
                            }
                        });
                    }
                }
            });
        } else {
//            noContent.setVisibility(View.VISIBLE);
//            tips.setText("当前没有网络哦~");
        }

    }
}
