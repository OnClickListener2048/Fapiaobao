package com.pilipa.fapiaobao.adapter.supply;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.mylibrary.utils.TimeUtils;
import com.pilipa.fapiaobao.R;
import com.pilipa.fapiaobao.net.bean.me.MessageDetailsBean;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.pilipa.fapiaobao.net.Constant.INVOICE_BABY_RESPONSE;
import static com.pilipa.fapiaobao.net.Constant.MSG_TYPE_SERVICE_NOTIFICATION;
import static com.pilipa.fapiaobao.net.Constant.WELCOME_NOTIFICATION;

/**
 * Created by lyt on 2017/10/23.
 */

public class MessageDetailsAdapter extends BaseAdapter {
    private List<MessageDetailsBean.DataBean> list = new ArrayList();
    private Context mContext = null;
    private String flag;
    private String lastDate =null;//上一条的日期
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
            viewHolder.tv_piece = (TextView) convertView.findViewById(R.id.sum_piece);

            convertView.setTag(viewHolder);
        }
        else
        {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        MessageDetailsBean.DataBean bean = list.get(position);
        String remarks = bean.getMessage().getRemarks();
        if (remarks != null) {
            Double aDouble = Double.valueOf(remarks);
            viewHolder.tv_piece.setText(parent.getContext().getString(R.string.end_with_piece, String.valueOf(aDouble.intValue())));
        }

        if(MSG_TYPE_SERVICE_NOTIFICATION.equals(bean.getMessage().getType())){
            viewHolder.tv_time.setVisibility(View.GONE);
            viewHolder.tv_piece.setVisibility(View.GONE);
            JSONObject jsonObject = null;
            try {
                jsonObject = new JSONObject(bean.getMessage().getContent());
                String  type = jsonObject.getString("type");
                switch (type){
                    case INVOICE_BABY_RESPONSE:
                        viewHolder.tv_title.setText(mContext.getResources().getString(R.string.response_piaobao));
                        break;
                    case WELCOME_NOTIFICATION:
                        viewHolder.tv_title.setText(jsonObject.getString("4"));
                        break;
                    default:
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }else{
            viewHolder.tv_time.setVisibility(View.VISIBLE);
            viewHolder.tv_piece.setVisibility(View.VISIBLE);
            viewHolder.tv_title.setText(bean.getMessage().getContent());
        }
        SimpleDateFormat dft1=new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat dft2=new SimpleDateFormat("HH:mm");
        Date date = TimeUtils.string2Date(bean.getCreateDate());
//        viewHolder.tv_time.setText(TimeUtils.date2String(date,dft2));
//        lastDate = TimeUtils.date2String(date,dft1);
        lastDate = TimeUtils.date2String(date);
        viewHolder.tv_date.setText(lastDate);
//        if(lastDate == null){
//            lastDate = TimeUtils.date2String(date,dft1);
//        }
//        if(lastDate.equals(TimeUtils.date2String(date,dft1))){
//            if(position == 0){
//                viewHolder.tv_date.setVisibility(View.VISIBLE);
//                viewHolder.tv_date.setText(TimeUtils.date2String(date,dft1));
//            }else{
//                viewHolder.tv_date.setVisibility(View.GONE);
//            }
//        }else{
//            viewHolder.tv_date.setVisibility(View.VISIBLE);
//            viewHolder.tv_date.setText(TimeUtils.date2String(date,dft1));
//            lastDate = TimeUtils.date2String(date,dft1);
//        }


        return convertView;
    }

    public void initData(List<MessageDetailsBean.DataBean> listBean) {
        this.list = listBean;
        notifyDataSetChanged();
    }
    private static class ViewHolder
    {
        TextView tv_title, tv_date, tv_time, tv_piece;
    }
}
