package com.pilipa.fapiaobao.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.pilipa.fapiaobao.R;

import java.util.List;

/**
 * Created by lyt on 2017/10/23.
 */

public class MyCompanyDetailsAdapter extends BaseAdapter {
    List list;
    private OnNextClickListener onNextClickListener;

    private Context mContext = null;
    private List<?> mMarkerData = null;
    public MyCompanyDetailsAdapter(Context context)
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
            convertView = mInflater.inflate(R.layout.fragment_company_details, null);
            viewHolder.img_details_viewpager_delete =(ImageView) convertView.findViewById(R.id.img_details_viewpager_delete);
            viewHolder.img_details_viewpager_share =(ImageView) convertView.findViewById(R.id.img_details_viewpager_share);
            viewHolder.img_details_viewpager_next =(ImageView) convertView.findViewById(R.id.img_details_viewpager_next);
            viewHolder.img_details_viewpager_next.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(onNextClickListener != null){
                        onNextClickListener.onNextClick();
                    }
                }
            });
            convertView.setTag(viewHolder);
        }
        else
        {
            viewHolder = (ViewHolder) convertView.getTag();
        }

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
        ImageView img_details_viewpager_delete;
        ImageView img_details_viewpager_share;
        ImageView img_details_viewpager_next;
    }
    public void setOnNextClickListener(OnNextClickListener onNextClickListener) {
        this.onNextClickListener = onNextClickListener;
    }

    public interface OnNextClickListener {
        void onNextClick();
    }
}
