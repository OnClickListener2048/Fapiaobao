package com.pilipa.fapiaobao.adapter.publish;

import android.app.Dialog;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mylibrary.utils.TLog;
import com.pilipa.fapiaobao.R;
import com.pilipa.fapiaobao.net.bean.invoice.AllInvoiceType;
import com.pilipa.fapiaobao.ui.widget.AdjustTextView;
import com.pilipa.fapiaobao.utils.DialogUtil;

import java.util.List;

/**
 * Created by edz on 2017/11/28.
 */

public class AllInvoiceTypeRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    private final List<AllInvoiceType.DataBean.InvoiceTypeListBean> invoiceTypeList;
    private OnLabelClickListener onLabelClickListener;
    private Context mContext;
    private Dialog mDialog;

    public AllInvoiceTypeRecyclerAdapter(List<AllInvoiceType.DataBean.InvoiceTypeListBean> invoiceTypeList) {
        this.invoiceTypeList = invoiceTypeList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_invoice_type, parent, false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Holder itemHolder = (Holder) holder;
        final AllInvoiceType.DataBean.InvoiceTypeListBean invoiceTypeListBean = invoiceTypeList.get(position);
        itemHolder.textView.setText(invoiceTypeListBean.getName());
        TLog.d("s", "invoiceTypeListBean.getremarks" + invoiceTypeListBean.getRemarks());
        if ("yes".equals(invoiceTypeListBean.getRemarks())) {
            itemHolder.textView.setSelected(true);
        } else {
            itemHolder.textView.setSelected(false);
        }

        itemHolder.textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ("yes".equals(invoiceTypeListBean.getRemarks())) {
                    onLabelClickListener.onLabelClick(invoiceTypeListBean.getId());
                    onLabelClickListener.onLabelNameClick(invoiceTypeListBean.getId(), invoiceTypeListBean.getName());
                } else {
                    showDialog();
                }
            }
        });
    }

    private void showDialog() {
        mDialog = DialogUtil.getInstance().createDialog(mContext, 0, R.layout.dialog_estimate_tips, new DialogUtil.OnKnownListener() {
            @Override
            public void onKnown(View view) {
                mDialog.dismiss();
            }
        }, null, null);
        DialogUtil.getInstance().setRootContentText(R.id.scan_tip, "暂时没有财务人员需要这类发票哦~");
        mDialog.show();
    }

    public void setOnLabelClickListener(OnLabelClickListener onLabelClickListener) {
        this.onLabelClickListener = onLabelClickListener;
    }

    @Override
    public int getItemCount() {
        return invoiceTypeList.size();
    }

    public interface OnLabelClickListener {
        void onLabelClick(String label);

        void onLabelNameClick(String label, String name);
    }

    static class Holder extends RecyclerView.ViewHolder{

        private final AdjustTextView textView;

        public Holder(View itemView) {
            super(itemView);
            textView = (AdjustTextView) itemView.findViewById(R.id.invoice_type);
        }


    }
}

