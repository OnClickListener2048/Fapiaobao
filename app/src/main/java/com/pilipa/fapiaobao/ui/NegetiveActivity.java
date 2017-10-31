package com.pilipa.fapiaobao.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.pilipa.fapiaobao.R;
import com.pilipa.fapiaobao.adapter.MessageCenterAdapter;
import com.pilipa.fapiaobao.base.BaseActivity;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by wjn on 2017/10/23.
 */

public class NegetiveActivity extends BaseActivity {
    private static final String TAG = "NegetiveActivity";

    @Bind(R.id.listview)
    ListView listView;
    private MessageCenterAdapter messageCenterAdapter;
    @Override
    protected int getLayoutId() {
        return R.layout.activity_negetive;
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
}
