package com.pilipa.fapiaobao.adapter.publish;

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
    private boolean isAll = false;
    private OnCompanyClickListener onCompanyClickListener;

    public CompanyListAdapter(boolean isAll) {
        this.isAll = isAll;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_simple_text_company, parent, false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof Holder) {
            Holder viewHolder = (Holder) holder;
            if (list != null) {
                CompaniesBean.DataBean dataBean = list.get(position);
                viewHolder.simpleText.setText(dataBean.getName());
            }

            viewHolder.simpleText.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onCompanyClickListener.onCompanyClick(list.get(holder.getAdapterPosition()));
                }
            });

            viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onCompanyClickListener.onCompanyClick(list.get(holder.getAdapterPosition()));
                }
            });
        }
    }

    public void addCompanyInfo(List<CompaniesBean.DataBean> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if (isAll) {
            if (list != null) {
                return list.size();
            }
        } else {
            if (list!= null) {
                if (list.size() >= 5) {
                    return 5;
                } else {
                    return list.size();
                }
            }
        }
        return 0;
    }

    public void setOnCompanyClickListener(OnCompanyClickListener onCompanyClickListener) {
        this.onCompanyClickListener = onCompanyClickListener;
    }

    public interface OnCompanyClickListener {
        void onCompanyClick(CompaniesBean.DataBean dataBean);
    }

    static class Holder extends RecyclerView.ViewHolder {

        private final TextView simpleText;

        public Holder(View itemView) {
            super(itemView);
            simpleText = (TextView) itemView.findViewById(R.id.item_simple_text);
        }
    }
}

