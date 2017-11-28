package com.pilipa.fapiaobao.adapter;

import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mylibrary.utils.TLog;
import com.pilipa.fapiaobao.R;
import com.pilipa.fapiaobao.net.bean.invoice.DefaultInvoiceBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by edz on 2017/11/2.
 */

public class TabAdapterActive extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final ArrayList<DefaultInvoiceBean.DataBean> data;
    private boolean isEditing;
    private ItemDeleteListener itemDeleteListener;



    public TabAdapterActive(ArrayList<DefaultInvoiceBean.DataBean> data) {
        this.data = data;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_tab_item, parent, false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {

        if (holder instanceof Holder) {
            final Holder tabHolder = (Holder) holder;
            tabHolder.iv_delete.setVisibility(isEditing ? View.VISIBLE : View.GONE);
            final DefaultInvoiceBean.DataBean dataBean = data.get(position);
            tabHolder.tv_content.setText(dataBean.getName());
            tabHolder.iv_delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (tabHolder.getAdapterPosition()!= -1) {
                        data.remove(tabHolder.getAdapterPosition());
                        notifyItemRemoved(tabHolder.getAdapterPosition());
                        itemDeleteListener.onItemDelete(dataBean);
                    }

                }
            });
        }
    }

    public ArrayList<DefaultInvoiceBean.DataBean> getCurrentItemSetData() {
        return data == null ? new ArrayList<DefaultInvoiceBean.DataBean>() : data;
    }

    public interface ItemDeleteListener {
        void onItemDelete(DefaultInvoiceBean.DataBean dataBean);
    }

    public void setOnItemDeleteListener(ItemDeleteListener itemDeleteListener) {
        this.itemDeleteListener = itemDeleteListener;
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public void addItem(DefaultInvoiceBean.DataBean dataBean) {
        if (data != null) {
           if (isHasItem(dataBean)) return;

            data.add(dataBean);
            notifyItemInserted(data.size());
        }
    }

    private boolean isHasItem(DefaultInvoiceBean.DataBean dataBean) {

        for (DefaultInvoiceBean.DataBean bean : data) {
            if (TextUtils.equals(bean.getName(),dataBean.getName())) {
                return true;
            }
        }
        return false;
    }


    public void setEditMode(boolean b) {
        isEditing = b;
        notifyDataSetChanged();
    }

    public boolean getEditMode() {
        return isEditing;
    }

    static class Holder extends RecyclerView.ViewHolder {

        private final TextView tv_content;
        private final ImageView iv_delete;

        public Holder(View itemView) {
            super(itemView);
            tv_content = (TextView) itemView.findViewById(R.id.tv_content);
            iv_delete = (ImageView) itemView.findViewById(R.id.iv_delete);
        }
    }

}
