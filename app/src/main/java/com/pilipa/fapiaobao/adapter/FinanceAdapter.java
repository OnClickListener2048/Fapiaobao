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


/**
 * Created by edz on 2017/10/23.
 */

public class FinanceAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final String TAG = "FinanceAdapter";
    private Context mContext;
    DefaultInvoiceBean allInvoiceType;
    private RequestManager with;
    private DefaultInvoiceBean.DataBean dataBean;
    private AllInvoiceAdapter.OnLabelClickListener onLabelClickListener;

    public FinanceAdapter(DefaultInvoiceBean allInvoiceType) {
        this.allInvoiceType = allInvoiceType;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        with = Glide.with(parent.getContext());
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_finance, parent, false);
        return new Financeholder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder != null) {
            Financeholder financeholder  = (Financeholder) holder;
            if (allInvoiceType.getData() != null && allInvoiceType.getData().size() > 0) {
                dataBean = allInvoiceType.getData().get(position);
                ImageLoader.loadImage(with,financeholder.iv_finance, dataBean.getMiddleSize());
                financeholder.tv_finance.setText(dataBean.getName());
                financeholder.iv_finance.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        onLabelClickListener.onLabelClick(dataBean.getId());
                    }
                });
            }


        }
    }

    public void setOnLabelClickListener(AllInvoiceAdapter.OnLabelClickListener onLabelClickListener) {
        this.onLabelClickListener = onLabelClickListener;

    }

    @Override
    public int getItemCount() {
        return 12;
    }

    static class Financeholder extends RecyclerView.ViewHolder {

        private final TextView tv_finance;
        private final ImageView iv_finance;

        public Financeholder(View itemView) {
            super(itemView);
            tv_finance = (TextView) itemView.findViewById(R.id.tv_finance_kind);
            iv_finance = (ImageView) itemView.findViewById(R.id.iv_finance_kind);
        }
    }

}
