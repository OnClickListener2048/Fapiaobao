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

import static com.pilipa.fapiaobao.net.Constant.MSG_TYPE_GOT_BONUS;
import static com.pilipa.fapiaobao.net.Constant.MSG_TYPE_INCOMPETENT_INVOICE;
import static com.pilipa.fapiaobao.net.Constant.MSG_TYPE_NEWCOME_INVOICE;
import static com.pilipa.fapiaobao.net.Constant.MSG_TYPE_SERVICE_NOTIFICATION;

/**
 * Created by lyt on 2017/10/23.
 */

public class MessageDetailsAdapter extends BaseAdapter {
    private Context mContext = null;
    private ArrayList<?> list = new ArrayList<>();
    private String flag;
    public MessageDetailsAdapter(Context context)
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
            convertView = mInflater.inflate(R.layout.item_message_details, null);
            viewHolder.tv_title =(TextView) convertView.findViewById(R.id.tv_title);
            viewHolder.tv_date =(TextView) convertView.findViewById(R.id.tv_date);
            viewHolder.tv_time =(TextView) convertView.findViewById(R.id.tv_time);
            convertView.setTag(viewHolder);
        }
        else
        {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        switch (flag){
            case MSG_TYPE_NEWCOME_INVOICE:{
                MessageListBean.DataBean._$1Bean  bean =(MessageListBean.DataBean._$1Bean) list.get(position);
                viewHolder.tv_title.setText(bean.getMessage().getContent());
                viewHolder.tv_date.setText(bean.getCreateDate());
                viewHolder.tv_time.setText(bean.getCreateDate());
            }break;
            case MSG_TYPE_GOT_BONUS:{
                MessageListBean.DataBean._$2Bean  bean =(MessageListBean.DataBean._$2Bean) list.get(position);
                viewHolder.tv_title.setText(bean.getMessage().getContent());
                viewHolder.tv_date.setText(bean.getCreateDate());
                viewHolder.tv_time.setText(bean.getCreateDate());
            }break;
            case MSG_TYPE_INCOMPETENT_INVOICE:{
                MessageListBean.DataBean._$3Bean  bean =(MessageListBean.DataBean._$3Bean) list.get(position);
                viewHolder.tv_title.setText(bean.getMessage().getContent());
                viewHolder.tv_date.setText(bean.getCreateDate());
                viewHolder.tv_time.setText(bean.getCreateDate());
            }break;
            case MSG_TYPE_SERVICE_NOTIFICATION:{
                MessageListBean.DataBean._$4Bean  bean =(MessageListBean.DataBean._$4Bean) list.get(position);
                viewHolder.tv_title.setText(bean.getMessage().getContent());
                viewHolder.tv_date.setText(bean.getCreateDate());
                viewHolder.tv_time.setText(bean.getCreateDate());
            }break;
        }
//        viewHolder.tv_title.setText(list.);

        return convertView;
    }

    public void initData(ArrayList<?> list,String flag) {
        this.list = list;
        this.flag = flag;
        notifyDataSetChanged();
    }

    private static class ViewHolder
    {
        TextView tv_title,tv_date,tv_time;
    }
}
