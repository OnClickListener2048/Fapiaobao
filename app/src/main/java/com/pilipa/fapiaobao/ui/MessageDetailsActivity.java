package com.pilipa.fapiaobao.ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.example.mylibrary.utils.TLog;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.pilipa.fapiaobao.R;
import com.pilipa.fapiaobao.account.AccountHelper;
import com.pilipa.fapiaobao.adapter.MessageDetailsAdapter;
import com.pilipa.fapiaobao.base.BaseNoNetworkActivity;
import com.pilipa.fapiaobao.net.Api;
import com.pilipa.fapiaobao.net.Constant;
import com.pilipa.fapiaobao.net.bean.me.FeedbackMessageBean;
import com.pilipa.fapiaobao.net.bean.me.MessageDetailsBean;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.pilipa.fapiaobao.net.Constant.INVOICE_BABY_RESPONSE;
import static com.pilipa.fapiaobao.net.Constant.MSG_TYPE_GOT_BONUS;
import static com.pilipa.fapiaobao.net.Constant.MSG_TYPE_INCOMPETENT_INVOICE;
import static com.pilipa.fapiaobao.net.Constant.MSG_TYPE_NEWCOME_INVOICE;
import static com.pilipa.fapiaobao.net.Constant.MSG_TYPE_SERVICE_NOTIFICATION;

/**
 * Created by wjn on 2017/10/23.
 */

public class MessageDetailsActivity extends BaseNoNetworkActivity implements AdapterView.OnItemClickListener, OnLoadmoreListener, OnRefreshListener {
    private  final String TAG =getClass().getSimpleName();
    @Bind(R.id.title)
    TextView tv_title;
    @Bind(R.id.lv_content)
    ListView listview;
    @Bind(R.id.tips)
    TextView tips;
    @Bind(R.id.no_content)
    LinearLayout noContent;
    private String type;
    private String bonus;//红包金额
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
        type = getIntent().getStringExtra("type");
        tv_title.setText(title);
        adapter = new MessageDetailsAdapter(this);
        listview.setAdapter(adapter);
        listview.setOnItemClickListener(this);
    }

    @Override
    public void initData() {
        messageDetails(type);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @Override
    public void onNoNetworkLayoutClicks(View view) {
        initData();
    }

    private void messageDetails(final String type) {

        bonus = String.valueOf(AccountHelper.getUser().getData().getCustomer().getBonus());
        Api.messageDetails(type, AccountHelper.getToken(), new Api.BaseRawResponse<MessageDetailsBean>() {
            @Override
            public void onStart() {
                showProgressDialog();
            }

            @Override
            public void onFinish() {
                hideProgressDialog();
            }

            @Override
            public void onError() {
                showNetWorkErrorLayout();
            }

            @Override
            public void onTokenInvalid() {
                hideNetWorkErrorLayout();
            }

            @Override
            public void setData(MessageDetailsBean messageDetailsBean) {
                hideNetWorkErrorLayout();
                if (messageDetailsBean.getStatus() == Constant.REQUEST_SUCCESS) {
                    noContent.setVisibility(View.GONE);
                    list = messageDetailsBean.getData();
                    adapter.initData(list);
                } else if (messageDetailsBean.getStatus() == Constant.REQUEST_NO_CONTENT) {
                    noContent.setVisibility(View.VISIBLE);
                    tips.setText("暂时还没有消息哦~");
                } else {
                    noContent.setVisibility(View.VISIBLE);
                    tips.setText("登陆后才能查看到哦");
                }
            }
        });
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        MessageDetailsBean.DataBean bean = list.get(position);
        switch (type) {
            case MSG_TYPE_NEWCOME_INVOICE:
                Intent intent1 = new Intent(MessageDetailsActivity.this, DemandActivity.class);
                intent1.putExtra("demandId", bean.getMessage().getDemand().getId());
                startActivity(intent1);
                break;
            case MSG_TYPE_GOT_BONUS:
                Intent intent2 = new Intent(MessageDetailsActivity.this, MyRedEnvelopeActivity.class);
                intent2.putExtra("bonus", bonus);
                startActivity(intent2);
                break;
            case MSG_TYPE_INCOMPETENT_INVOICE:
                Intent intent3 = new Intent(MessageDetailsActivity.this, ProvidedActivity.class);
                intent3.putExtra("OrderId", bean.getMessage().getOrderId());
                intent3.putExtra("CompanyId", bean.getMessage().getCompanyId());
                startActivity(intent3);
                break;
            case MSG_TYPE_SERVICE_NOTIFICATION:
                JSONObject jsonObject = null;
                try {
                    jsonObject = new JSONObject(bean.getMessage().getContent());
                    String type = jsonObject.getString("type");
                    if (INVOICE_BABY_RESPONSE.equals(type)) {
                        JSONArray suggestionList = jsonObject.getJSONArray("suggestionList");
                        List<FeedbackMessageBean.DataBean.ListBean.SuggestionListBean> retList = new Gson().fromJson(suggestionList.toString(),
                                new TypeToken<List<FeedbackMessageBean.DataBean.ListBean.SuggestionListBean>>() {
                                }.getType());
                        Intent intent4 = new Intent(MessageDetailsActivity.this, MyQuestionsActivity.class);
                        intent4.putParcelableArrayListExtra("suggestionList", (ArrayList<? extends Parcelable>) retList);
                        intent4.putExtra("flag", true);//标记是(true)否(false)来自推送
                        startActivity(intent4);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                break;
        }
    }

    @Override
    public void initDataInResume() {
        initData();
    }

    @Override
    public void onLoadmore(RefreshLayout refreshlayout) {
        TLog.d(TAG,"onLoadmore");
    }

    @Override
    public void onRefresh(RefreshLayout refreshlayout) {
        TLog.d(TAG,"onRefresh");
    }
}
