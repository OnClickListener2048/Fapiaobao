package com.pilipa.fapiaobao.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.pilipa.fapiaobao.R;
import com.pilipa.fapiaobao.net.bean.me.CompaniesBean;
import com.pilipa.fapiaobao.net.bean.me.FavoriteCompanyBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lyt on 2017/10/23.
 */

public class MyCompanyAdapter extends BaseAdapter {
    private Context mContext = null;
    private List<?> mMarkerData = null;
    public MyCompanyAdapter(Context context)
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
            convertView = mInflater.inflate(R.layout.item_company_manager, null);
            viewHolder.name =(TextView) convertView.findViewById(R.id.name);
            convertView.setTag(viewHolder);
        }
        else
        {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        if(mMarkerData.get(position).getClass() .equals(CompaniesBean.DataBean.class)){
            CompaniesBean.DataBean bean =(CompaniesBean.DataBean) mMarkerData.get(position);
            viewHolder.name.setText(bean.getName());
        }else{
            FavoriteCompanyBean.DataBean bean =(FavoriteCompanyBean.DataBean) mMarkerData.get(position);
            viewHolder.name.setText(bean.getName());
        }

        return convertView;
    }

    public void addData(List list) {
        if (list==null) {
            this.mMarkerData.addAll(list);
        }

        notifyDataSetChanged();
    }

    public void initData(List list) {
        this.mMarkerData = list;
        notifyDataSetChanged();
    }

    private static class ViewHolder
    {
        TextView name;
    }
}
