package com.pilipa.fapiaobao.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.pilipa.fapiaobao.R;
import com.pilipa.fapiaobao.net.bean.invoice.DefaultInvoiceBean;
import com.pilipa.fapiaobao.net.bean.me.FavoriteCompanyBean;

import java.util.List;

/**
 * Created by edz on 2017/11/27.
 */

public class MyCompanyDetailsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    private final List<FavoriteCompanyBean.DataBean.InvoiceTypeListBean> list;
    private ItemClickListener itemClickListener;

    public MyCompanyDetailsAdapter(List<FavoriteCompanyBean.DataBean.InvoiceTypeListBean> list) {
        this.list = list;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_tab_item, parent, false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        if (holder instanceof Holder) {
            Holder typesHolder = (Holder) holder;
            final FavoriteCompanyBean.DataBean.InvoiceTypeListBean invoiceTypeListBean = list.get(position);
            typesHolder.iv_delete.setVisibility(View.GONE);
            typesHolder.tv_content.setText(invoiceTypeListBean.getName());
            typesHolder.tv_content.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    itemClickListener.onItemClick(invoiceTypeListBean);
                }
            });
        }
    }

    public interface ItemClickListener {
        void onItemClick(FavoriteCompanyBean.DataBean.InvoiceTypeListBean dataBean);
    }

    public void setOnItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }


    @Override
    public int getItemCount() {
        return list.size();
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
