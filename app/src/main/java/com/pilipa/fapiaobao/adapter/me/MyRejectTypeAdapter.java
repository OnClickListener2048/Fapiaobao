package com.pilipa.fapiaobao.adapter.me;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.pilipa.fapiaobao.R;
import com.pilipa.fapiaobao.net.bean.RejectTypeBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lyt on 2017/10/23.
 */

public class MyRejectTypeAdapter extends BaseAdapter {
    private Context mContext = null;
    private List<?> mMarkerData = null;
    public MyRejectTypeAdapter(Context context)
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
            convertView = mInflater.inflate(R.layout.type_spinner_item, null);
            viewHolder.text1 =(TextView) convertView.findViewById(R.id.text1);
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
        RejectTypeBean.DataBean bean=(RejectTypeBean.DataBean) mMarkerData.get(position);
        viewHolder.text1.setText(bean.getLabel());
        return convertView;
    }

    public void initData(List list) {
        mMarkerData = list;
        notifyDataSetChanged();
    }

    private static class ViewHolder
    {
        TextView text1;
        View headerLine, footerLine;
    }
}
