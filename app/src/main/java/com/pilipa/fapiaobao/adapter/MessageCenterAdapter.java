package com.pilipa.fapiaobao.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.pilipa.fapiaobao.R;
import com.pilipa.fapiaobao.net.bean.me.MessageListBean;

import static com.pilipa.fapiaobao.net.Constant.MSG_TYPE_GOT_BONUS;
import static com.pilipa.fapiaobao.net.Constant.MSG_TYPE_INCOMPETENT_INVOICE;
import static com.pilipa.fapiaobao.net.Constant.MSG_TYPE_NEWCOME_INVOICE;
import static com.pilipa.fapiaobao.net.Constant.MSG_TYPE_SERVICE_NOTIFICATION;

/**
 * Created by lyt on 2017/10/23.
 */

public class MessageCenterAdapter extends BaseAdapter {
    private Context mContext = null;
    private MessageListBean.DataBean bean = new MessageListBean.DataBean();
    public MessageCenterAdapter(Context context)
    {
        mContext = context;
    }
    @Override
    public int getCount() {
        return 4;
    }

    @Override
    public Object getItem(int position) {
        return bean;
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
        switch ((position+1)+""){
            case MSG_TYPE_NEWCOME_INVOICE:{
                viewHolder.tv_title.setText("新到发票");
                if(bean.get_$1() != null){
                    viewHolder.tv_date.setText(bean.get_$1().get(0).getCreateDate());
                    viewHolder.tv_size.setText(bean.get_$1().size()+"");
                }
            }break;
            case MSG_TYPE_GOT_BONUS:{
                viewHolder.tv_title.setText("红包到帐");
                if(bean.get_$2() != null){
                    viewHolder.tv_date.setText(bean.get_$2().get(0).getCreateDate());
                    viewHolder.tv_size.setText(bean.get_$2().size()+"");
                }
            }break;
            case MSG_TYPE_INCOMPETENT_INVOICE:{
                viewHolder.tv_title.setText("不合格发票");
                if(bean.get_$3() != null){
                    viewHolder.tv_date.setText(bean.get_$3().get(0).getCreateDate());
                    viewHolder.tv_size.setText(bean.get_$3().size()+"");
                }
            }break;
            case MSG_TYPE_SERVICE_NOTIFICATION:{
                viewHolder.tv_title.setText("服务通知");
                if(bean.get_$4() != null){
                    viewHolder.tv_date.setText(bean.get_$4().get(0).getCreateDate());
                    viewHolder.tv_size.setText(bean.get_$4().size()+"");
                }
            }break;
        }
        return convertView;
    }

    public void initData(MessageListBean.DataBean dataBean) {
        this.bean = dataBean;
        notifyDataSetChanged();
    }

    private static class ViewHolder
    {
        TextView tv_title,tv_date,tv_size;
    }
}
