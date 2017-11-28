package com.pilipa.fapiaobao.adapter;

import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mylibrary.utils.TLog;
import com.pilipa.fapiaobao.R;
import com.pilipa.fapiaobao.net.bean.invoice.AllInvoiceType;
import com.pilipa.fapiaobao.net.bean.invoice.DefaultInvoiceBean;
import com.pilipa.fapiaobao.ui.widget.AdjustTextView;

import java.util.List;

/**
 * Created by edz on 2017/11/28.
 */

public class AllInvoiceMoreTypesRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    private final List<AllInvoiceType.DataBean.InvoiceTypeListBean> invoiceTypeList;
    private OnItemClickListener onItemClickListener;

    public AllInvoiceMoreTypesRecyclerAdapter(List<AllInvoiceType.DataBean.InvoiceTypeListBean> invoiceTypeList) {
        this.invoiceTypeList = invoiceTypeList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_invoice_type, parent, false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        final Holder itemHolder = (Holder) holder;
        final AllInvoiceType.DataBean.InvoiceTypeListBean invoiceTypeListBean = invoiceTypeList.get(position);
        itemHolder.textView.setText(invoiceTypeListBean.getName());
        TLog.log("invoiceTypeListBean.isSelected()" + invoiceTypeListBean.isSelected());
        if (invoiceTypeListBean.isSelected()) {
            itemHolder.textView.setSelected(true);
        } else {
            itemHolder.textView.setSelected(false);
        }

        itemHolder.textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!invoiceTypeListBean.isSelected()) {
                    itemHolder.textView.setSelected(true);
                    invoiceTypeListBean.setSelected(true);
                    onItemClickListener.onItemClick(invoiceTypeListBean);
                    notifyItemChanged(position);
                }
            }
        });
    }




    public interface OnItemClickListener {
        void onItemClick(AllInvoiceType.DataBean.InvoiceTypeListBean dataBean);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }


    @Override
    public int getItemCount() {
        return invoiceTypeList.size();
    }


    static class Holder extends RecyclerView.ViewHolder {

        private final AdjustTextView textView;

        public Holder(View itemView) {
            super(itemView);
            textView = (AdjustTextView) itemView.findViewById(R.id.invoice_type);
        }


    }
}

