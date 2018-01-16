package com.pilipa.fapiaobao.adapter.me;

import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.pilipa.fapiaobao.R;


/**
 * Created by edz on 2017/12/1.
 */

public class FeedbackAdapterWrapper extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private FeedbackMessagesAdapter mAdapter;
    private View mHeaderView;
    private View mFooterView;
    public FeedbackAdapterWrapper(FeedbackMessagesAdapter adapter){
        mAdapter = adapter;
    }

    @Override
    public int getItemViewType(int position) {
        if(position == 0){
            return FeedbackAdapterWrapper.ITEM_TYPE.HEADER.ordinal();
        } else if(position == mAdapter.getItemCount() + 1){
            return FeedbackAdapterWrapper.ITEM_TYPE.FOOTER.ordinal();
        } else{
            return FeedbackAdapterWrapper.ITEM_TYPE.NORMAL.ordinal();
        }
    }

    @Override
    public int getItemCount() {
        return mAdapter.getItemCount() + 2;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if(position == 0){
            if (mHeaderView == null) {
                mAdapter.onBindViewHolder(holder, 0);
            }
            return;
        } else if(position == mAdapter.getItemCount() + 1){
            if (mFooterView == null) {
                mAdapter.onBindViewHolder(holder, mAdapter.getItemCount() + 1);
            }
            TextView textView = (TextView) holder.itemView.findViewById(R.id.loading);
            RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            holder.itemView.setLayoutParams(layoutParams);
            textView.setGravity(Gravity.CENTER);
            return;
        } else{
            mAdapter.onBindViewHolder(holder, position - 1);
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(viewType == FeedbackAdapterWrapper.ITEM_TYPE.HEADER.ordinal()){
            return new RecyclerView.ViewHolder(mHeaderView) {};
        } else if(viewType == FeedbackAdapterWrapper.ITEM_TYPE.FOOTER.ordinal()){
            return new RecyclerView.ViewHolder(mFooterView) {};
        } else{
            return mAdapter.onCreateViewHolder(parent,viewType);
        }
    }

    public void addHeaderView(View view){
        this.mHeaderView = view;
    }

    public void addFooterView(View view){
        this.mFooterView = view;
    }

    public void removeHeaderView() {
        mHeaderView = null;
        notifyDataSetChanged();
    }

    public void removeFooterView() {
        mFooterView = null;
        notifyDataSetChanged();
    }

    enum ITEM_TYPE {
        HEADER,
        FOOTER,
        NORMAL
    }
}
