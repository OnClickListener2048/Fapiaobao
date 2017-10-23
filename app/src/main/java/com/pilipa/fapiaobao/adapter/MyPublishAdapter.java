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

public class MyPublishAdapter extends BaseAdapter {
    List list;
    private Context mContext = null;
    private List<?> mMarkerData = null;
    public MyPublishAdapter(Context context)
    {
        mContext = context;
    }
    @Override
    public int getCount() {
        return 30;
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
            convertView = mInflater.inflate(R.layout.item_publish, null);
            viewHolder.tvEndOfDistance =(TextView) convertView.findViewById(R.id.tv_end_of_distance);
            convertView.setTag(viewHolder);
        }
        else
        {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        String endFormat = mContext.getResources().getString(R.string.end_of_distance);
        viewHolder.tvEndOfDistance.setText(String.format(endFormat, 100));
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
        TextView tvEndOfDistance;
    }
}
