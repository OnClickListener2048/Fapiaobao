package com.pilipa.fapiaobao.adapter;

import android.content.Context;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.mylibrary.utils.TimeUtils;
import com.pilipa.fapiaobao.R;
import com.pilipa.fapiaobao.net.bean.me.MessageListBean;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by lyt on 2017/10/23.
 */

public class MessageDetailsAdapter extends BaseAdapter {
    private final ArrayList<? extends Parcelable> arrayList;
    private Context mContext = null;
    private String flag;

    public MessageDetailsAdapter(Context context, ArrayList<? extends Parcelable> arrayList)
    {
        mContext = context;
        this.arrayList = arrayList;
    }
    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return arrayList.get(position);
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

            DateFormat dateFormat = DateFormat.getDateInstance();
            try {
                Date date1 = dateFormat.parse("yyyy-MM-dd");
                Date date2 = dateFormat.parse("HH:mm");
                String s1 = TimeUtils.date2String(date1);
                String s2 = TimeUtils.date2String(date2);
                viewHolder.tv_date.setText(s1);
                viewHolder.tv_time.setText(s2);
            } catch (ParseException e) {
                e.printStackTrace();
            }

            convertView.setTag(viewHolder);
        }
        else
        {
            viewHolder = (ViewHolder) convertView.getTag();
        }


        return convertView;
    }


    private static class ViewHolder
    {
        TextView tv_title,tv_date,tv_time;
    }
}
