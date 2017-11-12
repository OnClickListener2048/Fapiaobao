package com.pilipa.fapiaobao.ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
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

        String title = getIntent().getStringExtra("title");
        tv_title.setText(title);
        ArrayList<? extends Parcelable> arrayList = getIntent().getParcelableArrayListExtra("extra_list");

        MessageDetailsAdapter adapter = new MessageDetailsAdapter(this,arrayList);
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

}
