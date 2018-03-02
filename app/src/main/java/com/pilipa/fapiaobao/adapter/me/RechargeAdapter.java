package com.pilipa.fapiaobao.adapter.me;

import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.pilipa.fapiaobao.R;

import java.util.List;

/**
 * Created by edz on 2018/3/7.
 */

public class RechargeAdapter extends BaseQuickAdapter<String, BaseViewHolder> {
    private int space = 256;
    private int delete = 255;
    private OnValueClickListener mOnValueClickListener;

    public RechargeAdapter(@Nullable List<String> data) {
        super(data);

    }

    public void setOnValueClickListener(OnValueClickListener onValueClickListener) {
        this.mOnValueClickListener = onValueClickListener;
    }

    @Override
    protected void convert(BaseViewHolder helper, final String item) {
        if (helper.getItemViewType() != space && helper.getItemViewType() != delete) {
            helper.setText(R.id.tv_value, item);
        }

        helper.setOnClickListener(R.id.tv_value, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mOnValueClickListener.onValueClick(item);
            }
        });

    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == space) {
            return new BaseViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recharge_value_space, null));
        } else if (viewType == delete) {
            return new BaseViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recharge_value_delete, null));
        } else {
            return new BaseViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recharge_value, null));
        }
    }

    @Override
    public int getItemViewType(int position) {
        if ("space".equals(getItem(position))) {
            return space;
        }

        if ("delete".equals(getItem(position))) {
            return delete;
        }
        return super.getItemViewType(position);
    }


    public interface OnValueClickListener {
        void onValueClick(String value);
    }
}
