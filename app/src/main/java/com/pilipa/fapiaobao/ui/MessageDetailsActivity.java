package com.pilipa.fapiaobao.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.pilipa.fapiaobao.R;
import com.pilipa.fapiaobao.adapter.MessageDetailsAdapter;
import com.pilipa.fapiaobao.base.BaseActivity;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.pilipa.fapiaobao.net.Constant.MSG_TYPE_GOT_BONUS;
import static com.pilipa.fapiaobao.net.Constant.MSG_TYPE_INCOMPETENT_INVOICE;
import static com.pilipa.fapiaobao.net.Constant.MSG_TYPE_NEWCOME_INVOICE;
import static com.pilipa.fapiaobao.net.Constant.MSG_TYPE_SERVICE_NOTIFICATION;

/**
 * Created by wjn on 2017/10/23.
 */

public class MessageDetailsActivity extends BaseActivity  {
    @Bind(R.id.tv_title)
    TextView tv_title;
    @Bind(R.id.listview)
    ListView listview;
    @Override
    protected int getLayoutId() {
        return R.layout.activity_message_details;
    }

    @OnClick({R.id.details_back,R.id.img_add})
    @Override
    public void onClick(View v) {
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void initView() {
        MessageDetailsAdapter adapter = new MessageDetailsAdapter(this);
        listview.setAdapter(adapter);
        String title = getIntent().getStringExtra("title");
        ArrayList dataList1 = getIntent().getParcelableArrayListExtra("dataList1");
        ArrayList dataList2 = getIntent().getParcelableArrayListExtra("dataList2");
        ArrayList dataList3 = getIntent().getParcelableArrayListExtra("dataList3");
        ArrayList dataList4 = getIntent().getParcelableArrayListExtra("dataList4");
        if(dataList1 != null){
            adapter.initData(dataList1,MSG_TYPE_NEWCOME_INVOICE);
        }else if(dataList2 != null){
            adapter.initData(dataList2,MSG_TYPE_GOT_BONUS);
        }else if(dataList3 != null){
            adapter.initData(dataList3,MSG_TYPE_INCOMPETENT_INVOICE);
        }else if(dataList4 != null){
            adapter.initData(dataList4,MSG_TYPE_SERVICE_NOTIFICATION);
        }
        tv_title.setText(title);
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

}
