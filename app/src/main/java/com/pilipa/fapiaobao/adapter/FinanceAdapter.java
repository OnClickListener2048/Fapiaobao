package com.pilipa.fapiaobao.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.example.mylibrary.utils.ImageLoader;
import com.pilipa.fapiaobao.R;
import com.pilipa.fapiaobao.net.bean.invoice.AllInvoiceType;
import com.pilipa.fapiaobao.net.bean.invoice.DefaultInvoiceBean;
import com.pilipa.fapiaobao.ui.EstimateActivity;
import com.pilipa.fapiaobao.ui.widget.AdjustTextView;


/**
 * Created by edz on 2017/10/23.
 */

public class FinanceAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final String TAG = "FinanceAdapter";
    private Context mContext;
    DefaultInvoiceBean allInvoiceType;
    private RequestManager with;
    private DefaultInvoiceBean.DataBean dataBean;
    private OnLabelClickListener onLabelClickListener;

    public FinanceAdapter(DefaultInvoiceBean allInvoiceType) {
        this.allInvoiceType = allInvoiceType;
        Log.d(TAG, "FinanceAdapter: "+allInvoiceType.toString());
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        with = Glide.with(parent.getContext());
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_finance, parent, false);
        return new Financeholder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        if (holder != null) {
            Financeholder financeholder  = (Financeholder) holder;
            if (allInvoiceType.getData() != null && allInvoiceType.getData().size() > 0) {


                dataBean = allInvoiceType.getData().get(position);



                ImageLoader.loadImage(with,financeholder.iv_finance, dataBean.getMiddleSize(),R.mipmap.receipt_012);
                financeholder.tv_finance.setText(dataBean.getName());

            }

            financeholder.iv_finance.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onLabelClickListener.onLabelClick(allInvoiceType.getData().get(position).getId());
                    onLabelClickListener.onLabelNameClick(allInvoiceType.getData().get(position).getId(),allInvoiceType.getData().get(position).getName());
                }
            });
        }
    }

    public void setOnLabelClickListener(OnLabelClickListener onLabelClickListener) {
        this.onLabelClickListener = onLabelClickListener;
    }

    public interface OnLabelClickListener {
        void onLabelClick(String label);

        void onLabelNameClick(String label, String name);
    }

    @Override
    public int getItemCount() {
        if (allInvoiceType.getData().size()>12) {
            return 12;
        }
        return allInvoiceType.getData().size();
    }

    static class Financeholder extends RecyclerView.ViewHolder {

        private final AdjustTextView tv_finance;
        private final ImageView iv_finance;

        public Financeholder(View itemView) {
            super(itemView);
            tv_finance = (AdjustTextView) itemView.findViewById(R.id.tv_finance_kind);
            iv_finance = (ImageView) itemView.findViewById(R.id.iv_finance_kind);
        }
    }

}
