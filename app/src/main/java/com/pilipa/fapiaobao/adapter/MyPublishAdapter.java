package com.pilipa.fapiaobao.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.pilipa.fapiaobao.R;
import com.pilipa.fapiaobao.net.bean.publish.DemandsListBean;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by lyt on 2017/10/23.
 */

public class MyPublishAdapter extends BaseAdapter {
    private Context mContext = null;
    private List<?> mMarkerData = null;
    public MyPublishAdapter(Context context)
    {
        mContext = context;
        mMarkerData= new ArrayList<>();
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
            convertView = mInflater.inflate(R.layout.item_publish, null);
            viewHolder.tvEndOfDistance =(TextView) convertView.findViewById(R.id.tv_end_of_distance);
            viewHolder.tvAmount =(TextView) convertView.findViewById(R.id.tv_amount);
            viewHolder.tvAlreadyCollected =(TextView) convertView.findViewById(R.id.tv_alreadyCollected);
            viewHolder.tvToCollectedPrice =(TextView) convertView.findViewById(R.id.tv_to_collected_price);
            convertView.setTag(viewHolder);
        }
        else
        {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        DemandsListBean.DataBean bean = (DemandsListBean.DataBean) mMarkerData.get(position);
        viewHolder.tvAmount.setText( String.format("%.2f",bean.getTotalAmount())+"");
        double toAmount = sub(bean.getTotalAmount(),bean.getLeftAmount());
        viewHolder.tvAlreadyCollected.setText(String.format("%.2f",toAmount)+"");
        viewHolder.tvToCollectedPrice.setText(String.format("%.2f",bean.getLeftAmount())+"");
        String endFormat = mContext.getResources().getString(R.string.end_of_distance);
        viewHolder.tvEndOfDistance.setText(String.format(endFormat, bean.getLeftDate()));

        return convertView;
    }

    public void addData(List list) {
        if (list==null) {
            mMarkerData.addAll(list);
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
        mMarkerData = list;
        notifyDataSetChanged();
    }

    private static class ViewHolder
    {
        TextView tvEndOfDistance ,tvAmount,tvAlreadyCollected,tvToCollectedPrice;
    }

    /**
          * 两个Double数相减
          * @param v1
          * @param v2
          * @return Double
          */
    public static double sub(Double v1,Double v2){
        BigDecimal b1 = new BigDecimal(v1.toString());
        BigDecimal b2 = new BigDecimal(v2.toString());
        return b1.subtract(b2).doubleValue();
    }
}
