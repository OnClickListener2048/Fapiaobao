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

public class MessageCenterAdapter extends BaseAdapter {
    List list;
    private Context mContext = null;
    private List<?> mMarkerData = null;
    public MessageCenterAdapter(Context context)
    {
        mContext = context;
    }
    @Override
    public int getCount() {
        return 5;
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
            convertView = mInflater.inflate(R.layout.item_message_center, null);
            viewHolder.tv_title =(TextView) convertView.findViewById(R.id.tv_title);
            convertView.setTag(viewHolder);
        }
        else
        {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.tv_title.setText("新票到账"+position);

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
        TextView tv_title;
    }
}
