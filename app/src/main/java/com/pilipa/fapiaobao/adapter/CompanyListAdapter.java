package com.pilipa.fapiaobao.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.pilipa.fapiaobao.R;
import com.pilipa.fapiaobao.net.bean.me.CompaniesBean;

import java.util.List;

/**
 * Created by edz on 2017/11/6.
 */

public class CompanyListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    List<CompaniesBean.DataBean> list;
    boolean isAll = false;
    private OnCompanyClickListener onCompanyClickListener;
    private CompaniesBean.DataBean dataBean;

    public CompanyListAdapter(boolean isAll) {
        this.isAll = isAll;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_simple_text_company, parent, false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof Holder) {
            Holder viewHolder = (Holder) holder;
            if (list != null) {
                dataBean = list.get(position);
                viewHolder.simpleText.setText(dataBean.getName());
            }

            viewHolder.simpleText.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onCompanyClickListener.onCompanyClick(dataBean);
                }
            });
        }
    }

    public void addCompanyInfo(List list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return isAll?list.size():5;
    }

    public interface OnCompanyClickListener {
        void onCompanyClick(CompaniesBean.DataBean dataBean);
    }

    public void setOnCompanyClickListener(OnCompanyClickListener onCompanyClickListener) {
        this.onCompanyClickListener = onCompanyClickListener;
    }

    static class Holder extends RecyclerView.ViewHolder {

        private final TextView simpleText;

        public Holder(View itemView) {
            super(itemView);
            simpleText = (TextView) itemView.findViewById(R.id.item_simple_text);
        }
    }
}

