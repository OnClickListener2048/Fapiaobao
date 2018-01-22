package com.pilipa.fapiaobao.adapter.supply;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.mylibrary.utils.TimeUtils;
import com.pilipa.fapiaobao.R;
import com.pilipa.fapiaobao.net.bean.me.MessageListBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lyt on 2017/10/23.
 */

public class MessageCenterAdapter extends BaseAdapter {
    private Context mContext = null;
    private List<MessageListBean.DataBean> list = new ArrayList();
    public MessageCenterAdapter(Context context)
    {
        mContext = context;
    }
    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
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
            viewHolder.tv_size =(TextView) convertView.findViewById(R.id.tv_size);
            convertView.setTag(viewHolder);
        }
        else
        {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        MessageListBean.DataBean bean = list.get(position);
        viewHolder.tv_title.setText(bean.getMessageTypeName());
        if(bean.getNewComeDate() != 0L){
            viewHolder.tv_date.setText(TimeUtils.millis2String(bean.getNewComeDate()));
        }else{
            viewHolder.tv_date.setText(TimeUtils.millis2String(System.currentTimeMillis()));
        }
        if(bean.getUnreadMessages() != 0){
            viewHolder.tv_size.setText(String.valueOf(bean.getUnreadMessages()));
        }else{
            viewHolder.tv_size.setText("");
        }

        return convertView;
    }

    public void initData(List<MessageListBean.DataBean> listBean) {
        this.list = listBean;
        notifyDataSetChanged();
    }

    private static class ViewHolder
    {
        TextView tv_title,tv_date,tv_size;
    }
}
