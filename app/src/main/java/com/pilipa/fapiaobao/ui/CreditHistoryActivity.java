package com.pilipa.fapiaobao.ui;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.pilipa.fapiaobao.R;
import com.pilipa.fapiaobao.account.AccountHelper;
import com.pilipa.fapiaobao.base.BaseActivity;
import com.pilipa.fapiaobao.net.Api;
import com.pilipa.fapiaobao.net.bean.me.CreditHistroyBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

import static com.pilipa.fapiaobao.net.Constant.REQUEST_NO_CONTENT;
import static com.pilipa.fapiaobao.net.Constant.REQUEST_SUCCESS;

/**
 * Created by wjn on 2017/10/23.
 */

public class CreditHistoryActivity extends BaseActivity {
    private static final String TAG = "NegetiveActivity";

    @Bind(R.id.title)
    TextView title;
    @Bind(R.id.listview)
    ListView listView;
    @Bind(R.id.ll_no_record)
    LinearLayout ll_no_record;
    @Bind(R.id.tv_history)
    TextView tv_history;
    private RechargeDetailsAdapter rechargeDetailsAdapter;
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
        rechargeDetailsAdapter = new RechargeDetailsAdapter(this);
        listView.setAdapter(rechargeDetailsAdapter);

    }

    @Override
    public void initData() {
        findCreditHistory("0","10");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        title.setText("信用历史");
        tv_history.setText("暂时还没有消息哦~");
    }
    public void findCreditHistory(String pageNo,String pageSize){
        if (AccountHelper.getToken() != null && AccountHelper.getToken() != "") {
            Api.findCreditHistory(AccountHelper.getToken(),pageNo,pageSize,new Api.BaseViewCallback<CreditHistroyBean>() {
                @Override
                public void setData(CreditHistroyBean creditHistroyBean) {
                    if(creditHistroyBean.getStatus() == REQUEST_SUCCESS){
                        rechargeDetailsAdapter.addData(creditHistroyBean.getData());
                        ll_no_record.setVisibility(View.GONE);
                        Log.d(TAG, "findCreditHistory"+"");
                    }else if(creditHistroyBean.getStatus() == REQUEST_NO_CONTENT){
                        ll_no_record.setVisibility(View.VISIBLE);
                    }
                }
            });
        }
    }
    class RechargeDetailsAdapter extends BaseAdapter {
        private Context mContext = null;
        private List<?> mMarkerData = null;
        public RechargeDetailsAdapter(Context context)
        {
            mContext = context;
            mMarkerData = new ArrayList<>();
        }
        @Override
        public int getCount() {
            return mMarkerData.size();
        }

        @Override
        public Object getItem(int position) {
            return mMarkerData.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder viewHolder = null;
            if (null == convertView)
            {
                viewHolder = new ViewHolder();
                LayoutInflater mInflater = LayoutInflater.from(mContext);
                convertView = mInflater.inflate(R.layout.item_recharge_details, null);
                viewHolder.tvAmountOffered =(TextView) convertView.findViewById(R.id.tv_amount_offered);
                viewHolder.title =(TextView) convertView.findViewById(R.id.title);
                viewHolder.createDate =(TextView) convertView.findViewById(R.id.createDate);
                viewHolder.subTitle =(TextView) convertView.findViewById(R.id.subTitle);
                convertView.setTag(viewHolder);
            }
            else
            {
                viewHolder = (ViewHolder) convertView.getTag();
            }
            CreditHistroyBean.DataBean bean =(CreditHistroyBean.DataBean)mMarkerData.get(position);
            if(bean.getScore() >= (double) 0){
                viewHolder.tvAmountOffered.setText("+"+bean.getScore());
            }else{
                viewHolder.tvAmountOffered.setText(bean.getScore()+"");
            }
            if("1".equals(bean.getType())){
                viewHolder.title.setText("交易成功");
            }else if("0".equals(bean.getType())){
                viewHolder.title.setText("不实交易");
            }
            viewHolder.createDate.setText(bean.getCreateDate());
            viewHolder.subTitle.setText("信用积分");

            return convertView;
        }

        public void addData(List list) {
            mMarkerData.addAll(list);
            notifyDataSetChanged();
        }
        private  class ViewHolder
        {
            TextView tvAmountOffered,title,createDate,subTitle;
        }
    }
}
