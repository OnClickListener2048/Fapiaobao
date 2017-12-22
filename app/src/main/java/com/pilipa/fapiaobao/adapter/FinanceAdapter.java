package com.pilipa.fapiaobao.adapter;

import android.content.Context;
import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.mylibrary.utils.ImageLoader;
import com.pilipa.fapiaobao.R;
import com.pilipa.fapiaobao.net.bean.invoice.DefaultInvoiceBean;
import com.pilipa.fapiaobao.ui.widget.AdjustTextView;
import com.pilipa.fapiaobao.utils.TDevice;

import java.util.ArrayList;


/**
 * Created by edz on 2017/10/23.
 */

public class FinanceAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final String TAG = "FinanceAdapter";
    private DefaultInvoiceBean allInvoiceType;
    private RequestManager with;
    private OnLabelClickListener onLabelClickListener;
    private ArrayList<DefaultInvoiceBean.DataBean> data;

    public FinanceAdapter(DefaultInvoiceBean allInvoiceType) {
        this.allInvoiceType = allInvoiceType;
        Log.d(TAG, "FinanceAdapter: "+allInvoiceType.toString());
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context mContext = parent.getContext();
        with = Glide.with(parent.getContext());
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_finance, parent, false);
        return new Financeholder(view);
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
        if (holder != null) {
            Financeholder financeholder  = (Financeholder) holder;
            if (allInvoiceType.getData() != null && allInvoiceType.getData().size() > 0) {
                data = allInvoiceType.getData();
                DefaultInvoiceBean.DataBean dataBean = data.get(position);
//                ImageLoader.loadImage(with,financeholder.iv_finance, dataBean.getMiddleSize(),R.mipmap.receipt_012);
//
                String middleSize = dataBean.getMiddleSize();
                if ("https://www.youpiao8.cn".equals(middleSize)) {
                    with.load(R.mipmap.receipt_014)
                            .skipMemoryCache(true)
                            .diskCacheStrategy(DiskCacheStrategy.NONE)
                            .placeholder(R.mipmap.receipt_012)
                            .into(financeholder.iv_finance);
                } else {
                    with.load(middleSize)
                            .skipMemoryCache(true)
                            .diskCacheStrategy(DiskCacheStrategy.NONE)
                            .placeholder(R.mipmap.receipt_012)
                            .into(financeholder.iv_finance);
                }

                financeholder.tv_finance.setText(dataBean.getName());
            }
            financeholder.fl_container.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onLabelClickListener.onLabelClick(allInvoiceType.getData().get(holder.getAdapterPosition()).getId());
                    onLabelClickListener.onLabelNameClick(allInvoiceType.getData().get(holder.getAdapterPosition()).getId(),allInvoiceType.getData().get(holder.getAdapterPosition()).getName());
                }
            });
        }
    }

    public void removeAllItems() {
        if (data!= null) {
        data.clear();
        }
        notifyDataSetChanged();
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

    private static class Financeholder extends RecyclerView.ViewHolder {

        private final AdjustTextView tv_finance;
        private final ImageView iv_finance;
        private final FrameLayout fl_container;

        public Financeholder(View itemView) {
            super(itemView);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                itemView.setTranslationZ(TDevice.dp2px(5));
                itemView.setElevation(TDevice.dp2px(5));
            }
            fl_container = (FrameLayout) itemView.findViewById(R.id.fl_container);
            tv_finance = (AdjustTextView) itemView.findViewById(R.id.tv_finance_kind);
            iv_finance = (ImageView) itemView.findViewById(R.id.iv_finance_kind);
        }
    }

}
