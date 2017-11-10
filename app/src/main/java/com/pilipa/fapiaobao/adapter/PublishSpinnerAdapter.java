package com.pilipa.fapiaobao.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.pilipa.fapiaobao.net.bean.publish.ExpressCompanyBean;
import com.pilipa.fapiaobao.utils.TDevice;

/**
 * Created by edz on 2017/10/29.
 */

public class PublishSpinnerAdapter extends BaseAdapter {

    ExpressCompanyBean expressCompanyBean;

    public PublishSpinnerAdapter(ExpressCompanyBean expressCompanyBean) {
        this.expressCompanyBean = expressCompanyBean;
    }

    @Override
    public int getCount() {
        return expressCompanyBean.getData().size();
    }

    @Override
    public Object getItem(int position) {
        return expressCompanyBean.getData().get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater _LayoutInflater = LayoutInflater.from(parent.getContext());
        TextView textView = (TextView) _LayoutInflater.inflate(android.R.layout.simple_dropdown_item_1line, null);
        textView.setTextSize(TDevice.spToPx(parent.getResources(),13));
        textView.setText(expressCompanyBean.getData().get(position).getLabel());
        return textView;
    }
}
