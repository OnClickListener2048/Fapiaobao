package com.pilipa.fapiaobao.adapter.me;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.pilipa.fapiaobao.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lyt on 2017/10/23.
 */

public class MyInvoiceNameAdapter extends BaseAdapter {
    private Context mContext = null;
    private List<?> mMarkerData = null;
    public MyInvoiceNameAdapter(Context context)
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
            convertView = mInflater.inflate(R.layout.item_cost_type, null);
            viewHolder.text1 =(TextView) convertView.findViewById(R.id.tv_cost_type);
            convertView.setTag(viewHolder);
        }
        else
        {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        String bean=(String) mMarkerData.get(position);
        Log.d("MyInvoiceNameAdapter",bean);
        viewHolder.text1.setText(bean);
        return convertView;
    }

    public void initData(List list) {
        mMarkerData = list;
        notifyDataSetChanged();
    }

    private static class ViewHolder
    {
        TextView text1;
    }
}
