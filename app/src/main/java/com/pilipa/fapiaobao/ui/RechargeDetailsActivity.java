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
import com.pilipa.fapiaobao.net.bean.me.UpdateCustomerBean;

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
        listview.setAdapter(new RechargeDetailsAdapter(this));
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
    class RechargeDetailsAdapter extends BaseAdapter {
        List list;
        private Context mContext = null;
        private List<?> mMarkerData = null;
        public RechargeDetailsAdapter(Context context)
        {
            mContext = context;
        }
        @Override
        public int getCount() {
            return 8;
        }

        @Override
        public Object getItem(int position) {
            return null;
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
//                viewHolder.tvAmountOffered =(TextView) convertView.findViewById(R.id.tv_amount_offered);
                convertView.setTag(viewHolder);
            }
            else
            {
                viewHolder = (ViewHolder) convertView.getTag();
            }
//            viewHolder.tvAmountOffered.setText("200");

            return convertView;
        }

        public void addData(List list) {
            if (list==null) {
                this.list.addAll(list);
            }

            notifyDataSetChanged();
        }
        private  class ViewHolder
        {
            TextView tvAmountOffered;
        }
    }
    private void amountHistory(final LoginWithInfoBean.DataBean.CustomerBean customer) {
        AccountHelper.isTokenValid(new Api.BaseViewCallback<LoginWithInfoBean>() {
            @Override
            public void setData(LoginWithInfoBean normalBean) {
                if (normalBean.getStatus() == 200) {
                    Api.amountHistory(AccountHelper.getToken(), new Api.BaseViewCallback<UpdateCustomerBean>() {
                        @Override
                        public void setData(UpdateCustomerBean updateCustomerBean) {
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
}
