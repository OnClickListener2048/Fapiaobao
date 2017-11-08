package com.pilipa.fapiaobao.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.pilipa.fapiaobao.R;
import com.pilipa.fapiaobao.net.bean.me.MessageListBean;

import java.util.ArrayList;
import java.util.List;

import static com.pilipa.fapiaobao.net.Constant.MSG_TYPE_GOT_BONUS;
import static com.pilipa.fapiaobao.net.Constant.MSG_TYPE_INCOMPETENT_INVOICE;
import static com.pilipa.fapiaobao.net.Constant.MSG_TYPE_NEWCOME_INVOICE;
import static com.pilipa.fapiaobao.net.Constant.MSG_TYPE_SERVICE_NOTIFICATION;

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
            convertView = mInflater.inflate(R.layout.item_message_center, null);
            viewHolder.tv_title =(TextView) convertView.findViewById(R.id.tv_title);
            viewHolder.tv_date =(TextView) convertView.findViewById(R.id.tv_date);
            convertView.setTag(viewHolder);
        }
        else
        {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        MessageListBean.DataBean bean = (MessageListBean.DataBean)mMarkerData.get(position);
        switch (bean.getMessage().getType()){
            case MSG_TYPE_NEWCOME_INVOICE:{
                viewHolder.tv_title.setText("新到发票");
            }break;
            case MSG_TYPE_GOT_BONUS:{
                viewHolder.tv_title.setText("红包到帐");
            }break;
            case MSG_TYPE_INCOMPETENT_INVOICE:{
                viewHolder.tv_title.setText("不合格发票");
            }break;
            case MSG_TYPE_SERVICE_NOTIFICATION:{
                viewHolder.tv_title.setText("服务通知");
            }break;
        }
        viewHolder.tv_date.setText(bean.getCreateDate());
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
        TextView tv_title,tv_date;
    }
}
