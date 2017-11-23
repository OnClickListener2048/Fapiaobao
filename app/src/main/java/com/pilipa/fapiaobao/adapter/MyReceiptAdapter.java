package com.pilipa.fapiaobao.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.pilipa.fapiaobao.R;
import com.pilipa.fapiaobao.net.bean.me.OrderListBean;

import java.util.ArrayList;
import java.util.List;

import static com.pilipa.fapiaobao.net.Constant.STATE_FLYING;
import static com.pilipa.fapiaobao.net.Constant.STATE_GONE;
import static com.pilipa.fapiaobao.net.Constant.STATE_GOT_ALL;
import static com.pilipa.fapiaobao.net.Constant.STATE_GOT_PARTIALITY;

/**
 * Created by lyt on 2017/10/23.
 */

public class MyReceiptAdapter extends BaseAdapter {
    private Context mContext = null;
    private List<?> mMarkerData = new ArrayList<>();
    public MyReceiptAdapter(Context context)
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
            convertView = mInflater.inflate(R.layout.item_receipt, null);
            viewHolder.tvReceipttype =(TextView) convertView.findViewById(R.id.tv_receipt_type);
            viewHolder.tvReceiveBouns =(TextView) convertView.findViewById(R.id.tv_receive_bouns);
            viewHolder.tvAmountOffered =(TextView) convertView.findViewById(R.id.tv_amount_offered);
            viewHolder.tvReceiveTime =(TextView) convertView.findViewById(R.id.tv_receive_time);
            viewHolder.tvArrivalState =(TextView) convertView.findViewById(R.id.tv_arrival_state);
            viewHolder.bouns_state =(TextView) convertView.findViewById(R.id.bouns_state);
            convertView.setTag(viewHolder);
        }
        else
        {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        OrderListBean.DataBean bean =(OrderListBean.DataBean) mMarkerData.get(position);
        if(bean.getInvoiceType()!=null){
            viewHolder.tvReceipttype.setText(bean.getInvoiceType().getName());
        }
        viewHolder.tvReceiveBouns.setText(String.format("%.2f",bean.getBonus())+"");
        viewHolder.tvAmountOffered.setText(String.format("%.2f",bean.getAmount())+"");
        viewHolder.tvReceiveTime.setText(bean.getCreateDate().substring(0,10));
        String state = bean.getState();
        if(STATE_FLYING.equals(state)){
            viewHolder.tvArrivalState.setText("红包飞来中");
            viewHolder.bouns_state.setText("预计收到");
            viewHolder.tvArrivalState.setTextColor(Color.parseColor("#f6b37f"));
        }else if(STATE_GOT_ALL.equals(state)){
            viewHolder.tvArrivalState.setText("红包到帐");
            viewHolder.tvArrivalState.setTextColor(Color.parseColor("#fd8e8e"));
            viewHolder.bouns_state.setText("收到红包");
        }else if(STATE_GOT_PARTIALITY.equals(state)){
            viewHolder.tvArrivalState.setText("部分到帐");
            viewHolder.tvArrivalState.setTextColor(Color.parseColor("#fd8e8e"));
            viewHolder.bouns_state.setText("收到红包");
        }else if(STATE_GONE.equals(state)){
            viewHolder.tvArrivalState.setText("红包飞走了");
            viewHolder.tvArrivalState.setTextColor(Color.parseColor("#89c997"));
            viewHolder.bouns_state.setText("预计收到");
        }

        return convertView;
    }

    public void addData(List list) {
        if (list==null) {
            this.mMarkerData.addAll(list);
        }

        notifyDataSetChanged();
    }

    public void clearData() {
        if (mMarkerData!= null) {
            mMarkerData.clear();
            notifyDataSetChanged();
        }
    }



    public void initData(List list) {
        if(list != null){
            this.mMarkerData = list;
        }
        notifyDataSetChanged();
    }

    private static class ViewHolder
    {
        TextView tvAmountOffered,tvReceipttype,tvReceiveBouns,tvReceiveTime,tvArrivalState,bouns_state;
    }
}
