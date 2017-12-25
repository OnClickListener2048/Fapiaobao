package com.pilipa.fapiaobao.ui;

import android.content.Context;
import android.graphics.Color;
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
import com.pilipa.fapiaobao.base.BaseNoNetworkActivity;
import com.pilipa.fapiaobao.net.Api;
import com.pilipa.fapiaobao.net.bean.me.AmountHistoryBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.pilipa.fapiaobao.net.Constant.REQUEST_SUCCESS;

/**
 * Created by lyt on 2017/10/17.
 */

public class RechargeDetailsActivity extends BaseNoNetworkActivity {
    @Bind(R.id.listview)
    ListView listview;
    @Bind(R.id.ll_no_record)
    LinearLayout ll_no_record;
    private List<AmountHistoryBean.DataBean> mData = new ArrayList<>();
    private RechargeDetailsAdapter mAdapter;
    @Override
    protected int getLayoutId() {
        return R.layout.activity_rechange_details;
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
        mAdapter = new RechargeDetailsAdapter(this);
        listview.setAdapter(mAdapter);
    }

    @Override
    public void initData() {
        amountHistory("0","100");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @Override
    public void onNoNetworkLayoutClicks(View view) {
        amountHistory("0", "100");
    }

    private void amountHistory(final String pageNo,final String pageSize) {
        Api.amountHistory(AccountHelper.getToken(), pageNo, pageSize, new Api.BaseRawResponse<AmountHistoryBean>() {
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

            }

            @Override
            public void setData(AmountHistoryBean amountHistoryBean) {
                hideNetWorkErrorLayout();
                if(amountHistoryBean.getStatus() == REQUEST_SUCCESS){
                    ll_no_record.setVisibility(View.GONE);
                    mData.addAll(amountHistoryBean.getData());
                    mAdapter.addData(mData);
                    Log.d("", "initData:amountHistory success");
                }else {
                    ll_no_record.setVisibility(View.VISIBLE);
                }
            }
        });
    }

    @Override
    public void initDataInResume() {

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
            AmountHistoryBean.DataBean bean =(AmountHistoryBean.DataBean)mMarkerData.get(position);
            if(bean.getFee() >= (double) 0){
                viewHolder.tvAmountOffered.setText("+"+String.format("%.2f", bean.getFee())+"");//钱包金额
                viewHolder.tvAmountOffered.setTextColor(Color.parseColor("#ef5c5c"));

            }else{
                viewHolder.tvAmountOffered.setText(String.format("%.2f", bean.getFee())+"");//钱包金额
                viewHolder.tvAmountOffered.setTextColor(Color.parseColor("#13b5b1"));

            }
            viewHolder.title.setText(bean.getTitle());
            viewHolder.createDate.setText(bean.getCreateDate());
            viewHolder.subTitle.setText(bean.getSubTitle());
            
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
