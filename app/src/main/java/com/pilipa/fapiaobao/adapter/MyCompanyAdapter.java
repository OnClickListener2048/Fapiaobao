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

public class MyCompanyAdapter extends BaseAdapter {
    List list;
    private Context mContext = null;
    private List<?> mMarkerData = null;
    public MyCompanyAdapter(Context context)
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
            convertView = mInflater.inflate(R.layout.item_company_manager, null);
            viewHolder.name =(TextView) convertView.findViewById(R.id.name);
            convertView.setTag(viewHolder);
        }
        else
        {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.name.setText("公司名称"+position);

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
        TextView name;
    }
}
