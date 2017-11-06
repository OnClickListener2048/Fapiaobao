package com.pilipa.fapiaobao.adapter;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.pilipa.fapiaobao.R;
import com.pilipa.fapiaobao.net.bean.invoice.AllInvoiceType;
import com.pilipa.fapiaobao.ui.widget.LabelsView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by edz on 2017/10/29.
 */

public class AllInvoiceAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    String TAG = "AllInvoiceAdapter";
    AllInvoiceType allInvoiceType;
    private OnLabelClickListener onLabelClickListener;

    public AllInvoiceAdapter(AllInvoiceType allInvoiceType) {
        this.allInvoiceType = allInvoiceType;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
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

                ArrayList<String> labelList = new ArrayList<>();
                if (invoiceTypeList != null) {
                    if (invoiceTypeList.size() > 0) {

                        if (invoiceCategory != null) {
                            viewHolder.tv_title.setText(invoiceCategory.getLabel());
                        }
                        for (AllInvoiceType.DataBean.InvoiceTypeListBean invoiceTypeListBean : invoiceTypeList) {
                            labelList.add(invoiceTypeListBean.getName());
                        }

                        viewHolder.labelsView.setLabels(labelList);
                        viewHolder.labelsView.setOnLabelClickListener(new LabelsView.OnLabelClickListener() {
                            @Override
                            public void onLabelClick(View label, String labelText, int position) {
                                onLabelClickListener.onLabelClick(invoiceTypeList.get(position).getId());
                                onLabelClickListener.onLabelNameClick(invoiceTypeList.get(position).getId(),invoiceTypeList.get(position).getName());
                            }
                        });
                    }
                }
            }
        }
    }

    public interface OnLabelClickListener {
        void onLabelClick(String label);

        void onLabelNameClick(String label, String name);
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

    static class Holder extends RecyclerView.ViewHolder {

        private final TextView tv_title;
        private final LabelsView labelsView;

        public Holder(View itemView) {
            super(itemView);
            tv_title = (TextView) itemView.findViewById(R.id.invoice_title);
            labelsView = (LabelsView) itemView.findViewById(R.id.labels);
        }
    }

}
