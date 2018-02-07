package com.pilipa.fapiaobao.adapter.me;

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
    private List<?> mMarkerData = new ArrayList<>();
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
            viewHolder.footerLine = convertView.findViewById(R.id.footer_dash_line);
            viewHolder.headerLine = convertView.findViewById(R.id.header_dash_line);
            convertView.setTag(viewHolder);
        }
        else
        {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.headerLine.setVisibility(position == 0 ? View.VISIBLE : View.GONE);
        viewHolder.footerLine.setVisibility(View.VISIBLE);
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
        TextView name;
        View headerLine, footerLine;
    }
}
