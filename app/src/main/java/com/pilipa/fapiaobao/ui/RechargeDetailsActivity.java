package com.pilipa.fapiaobao.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.pilipa.fapiaobao.R;
import com.pilipa.fapiaobao.account.AccountHelper;
import com.pilipa.fapiaobao.base.BaseActivity;
import com.pilipa.fapiaobao.base.BaseApplication;
import com.pilipa.fapiaobao.net.Api;
import com.pilipa.fapiaobao.net.bean.LoginWithInfoBean;
import com.pilipa.fapiaobao.net.bean.me.AmountHistoryBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by lyt on 2017/10/17.
 */

public class RechargeDetailsActivity extends BaseActivity {
    @Bind(R.id.listview)
    ListView listview;
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
        amountHistory("0","10");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    private void amountHistory(final String pageNo,final String pageSize) {
        AccountHelper.isTokenValid(new Api.BaseViewCallback<LoginWithInfoBean>() {
            @Override
            public void setData(LoginWithInfoBean normalBean) {
                if (normalBean.getStatus() == 200) {
                    Api.amountHistory(AccountHelper.getToken(),pageNo,pageSize, new Api.BaseViewCallback<AmountHistoryBean>() {
                        @Override
                        public void setData(AmountHistoryBean amountHistoryBean) {
                            mData.addAll(amountHistoryBean.getData());
                            mAdapter.addData(mData);
                            Log.d("", "initData:amountHistory success");
                        }
                    });
                }else {
                    BaseApplication.showToast("token验证失败请重新登陆");
                    startActivity(new Intent(RechargeDetailsActivity.this, LoginActivity.class));
                    finish();
                }
            }
        });
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
                viewHolder.tvAmountOffered.setText("+"+bean.getFee());
            }else{
                viewHolder.tvAmountOffered.setText(bean.getFee()+"");
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
