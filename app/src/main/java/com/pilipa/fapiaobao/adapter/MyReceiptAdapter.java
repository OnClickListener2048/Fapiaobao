package com.pilipa.fapiaobao.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.pilipa.fapiaobao.R;

import java.util.List;

/**
 * Created by lyt on 2017/10/23.
 */

public class MyReceiptAdapter extends BaseAdapter {
    List list;
    private Context mContext = null;
    private List<?> mMarkerData = null;
    public MyReceiptAdapter(Context context)
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
            convertView = mInflater.inflate(R.layout.item_receipt, null);
            viewHolder.tvAmountOffered =(TextView) convertView.findViewById(R.id.tv_amount_offered);
            convertView.setTag(viewHolder);
        }
        else
        {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.tvAmountOffered.setText("200");

        return convertView;
    }

    public void addData(List list) {
        if (list==null) {
            this.list.addAll(list);
        }

        notifyDataSetChanged();
    }

    public void initData(List list) {
        this.list = list;
        notifyDataSetChanged();
    }

    private static class ViewHolder
    {
        TextView tvAmountOffered;
    }
}
