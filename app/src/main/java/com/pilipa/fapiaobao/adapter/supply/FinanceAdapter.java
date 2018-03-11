package com.pilipa.fapiaobao.adapter.supply;

import android.app.Dialog;
import android.content.Context;
import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.pilipa.fapiaobao.R;
import com.pilipa.fapiaobao.net.Constant;
import com.pilipa.fapiaobao.net.bean.invoice.DefaultInvoiceBean;
import com.pilipa.fapiaobao.ui.widget.AdjustTextView;
import com.pilipa.fapiaobao.utils.DialogUtil;
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
    private Context mMContext;
    private Dialog mDialog;

    public FinanceAdapter(DefaultInvoiceBean allInvoiceType) {
        this.allInvoiceType = allInvoiceType;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mMContext = parent.getContext();
        with = Glide.with(parent.getContext());
        View view = LayoutInflater.from(mMContext).inflate(R.layout.item_finance, parent, false);
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
                if (middleSize != null) {
                    if (Constant.VERSION_BASE_URL.equals(middleSize)) {
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
                }


                financeholder.tv_finance.setText(dataBean.getName());
            }
            financeholder.fl_container.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    DefaultInvoiceBean.DataBean dataBean = allInvoiceType.getData().get(holder.getAdapterPosition());
                    if ("yes".equals(dataBean.getRemarks())) {
                        onLabelClickListener.onLabelClick(dataBean.getId());
                        onLabelClickListener.onLabelNameClick(dataBean.getId(), dataBean.getName());
                    } else {
                        showDialog();
                    }

                }
            });
        }
    }

    private void showDialog() {
        mDialog = DialogUtil.getInstance().createDialog(mMContext, 0, R.layout.dialog_estimate_tips, new DialogUtil.OnKnownListener() {
            @Override
            public void onKnown(View view) {
                mDialog.dismiss();
            }
        }, null, null);
        DialogUtil.getInstance().setRootContentText(R.id.scan_tip, "暂时没有财务人员需要这类发票哦~");
        mDialog.show();
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

    @Override
    public int getItemCount() {
        if (allInvoiceType.getData().size()>12) {
            return 12;
        }
        return allInvoiceType.getData().size();
    }

    public interface OnLabelClickListener {
        void onLabelClick(String label);

        void onLabelNameClick(String label, String name);
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
