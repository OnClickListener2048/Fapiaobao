package com.pilipa.fapiaobao.adapter.publish;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.pilipa.fapiaobao.R;
import com.pilipa.fapiaobao.net.bean.invoice.AllInvoiceType;

import java.util.List;

/**
 * Created by edz on 2017/10/29.
 */

public class AllInvoiceAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements AllInvoiceTypeRecyclerAdapter.OnLabelClickListener {
    String TAG = "AllInvoiceAdapter";
    private AllInvoiceType allInvoiceType;
    private OnLabelClickListener onLabelClickListener;
    private Context mContext;

    public AllInvoiceAdapter(AllInvoiceType allInvoiceType) {
        this.allInvoiceType = allInvoiceType;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        this.mContext = parent.getContext();
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_all_invoice, parent, false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof Holder) {
            Holder viewHolder = (Holder) holder;
            if (allInvoiceType != null) {
                AllInvoiceType.DataBean.InvoiceCategoryBean invoiceCategory = allInvoiceType.getData().get(position).getInvoiceCategory();
                final List<AllInvoiceType.DataBean.InvoiceTypeListBean> invoiceTypeList = allInvoiceType.getData().get(position).getInvoiceTypeList();
                if (invoiceTypeList != null) {
                    if (invoiceTypeList.size() > 0) {
                        if (invoiceCategory != null) {
                            viewHolder.tv_title.setText(invoiceCategory.getLabel());
                        }
                        viewHolder.recyclerView.setLayoutManager(new GridLayoutManager(mContext,3, LinearLayoutManager.VERTICAL,false));
                        viewHolder.recyclerView.setNestedScrollingEnabled(false);
                        AllInvoiceTypeRecyclerAdapter adapter = new AllInvoiceTypeRecyclerAdapter(invoiceTypeList);
                        adapter.setOnLabelClickListener(this);
                        viewHolder.recyclerView.setAdapter(adapter);
                    }
                }
            }
        }
    }

    @Override
    public void onLabelClick(String label) {
        onLabelClickListener.onLabelClick(label);
    }

    @Override
    public void onLabelNameClick(String label, String name) {
        onLabelClickListener.onLabelNameClick(label,name);
    }

    public void setOnLabelClickListener(OnLabelClickListener onLabelClickListener) {
        this.onLabelClickListener = onLabelClickListener;
    }

    @Override
    public int getItemCount() {
        if (allInvoiceType.getData() == null) {
            return 0;
        }
        return allInvoiceType.getData().size();
    }

    public interface OnLabelClickListener {
        void onLabelClick(String label);

        void onLabelNameClick(String label, String name);
    }

    static class Holder extends RecyclerView.ViewHolder {

        private final TextView tv_title;
        private final RecyclerView recyclerView;

        public Holder(View itemView) {
            super(itemView);
            tv_title = (TextView) itemView.findViewById(R.id.invoice_title);
            recyclerView = (RecyclerView) itemView.findViewById(R.id.recyclerView_invoice);
        }
    }

}
