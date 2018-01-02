package com.pilipa.fapiaobao.adapter;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.pilipa.fapiaobao.R;

/**
 * Created by edz on 2017/10/29.
 */

public class PublishSpinnerAdapter extends BaseAdapter {

    private String[] expressCompanyBean;

    public PublishSpinnerAdapter(String[] expressCompanyBean) {
        this.expressCompanyBean = expressCompanyBean;
    }

    @Override
    public int getCount() {
        return expressCompanyBean.length;
    }

    @Override
    public Object getItem(int position) {
        return expressCompanyBean[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater _LayoutInflater = LayoutInflater.from(parent.getContext());
        LinearLayout ll = (LinearLayout) _LayoutInflater.inflate(R.layout.item_simple_text_spinner, null);
        TextView textView = (TextView) ll.findViewById(R.id.spinner_item);
        textView.setTextColor(Color.parseColor("#434343"));
        textView.setText(expressCompanyBean[position]);
        return ll;
    }

}
