package com.pilipa.fapiaobao.adapter;

import android.content.Context;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mylibrary.utils.TLog;
import com.pilipa.fapiaobao.R;
import com.pilipa.fapiaobao.account.AccountHelper;
import com.pilipa.fapiaobao.net.bean.me.FeedbackMessageBean;
import com.pilipa.fapiaobao.ui.deco.DividerItemDeco;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by edz on 2017/12/1.
 */

public class FeedbackMessagesAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements FeedbackMessageChildItemAdapter.OnItemResponseListener {
    private static final String TAG = "FeedbackMessagesAdapter";
    Context mContext;
    private List<FeedbackMessageBean.DataBean.ListBean> data;
    private OnItemResponseListener onItemResponseListener;
    private boolean isShownResponseButton = true;

    public FeedbackMessagesAdapter() {
    }

    public FeedbackMessagesAdapter(boolean b) {
        isShownResponseButton = b;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        TLog.d(TAG, "RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {");
        mContext = parent.getContext();
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_feedback, parent, false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        TLog.d(TAG, "public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {");
        Holder itemHolder = (Holder) holder;
        if (this.data != null) {
        FeedbackMessageBean.DataBean.ListBean dataBean = data.get(position);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext);
        linearLayoutManager.setSmoothScrollbarEnabled(true);
        linearLayoutManager.setAutoMeasureEnabled(true);
        itemHolder.recyclerView.setLayoutManager(linearLayoutManager);
        itemHolder.recyclerView.setNestedScrollingEnabled(false);
        itemHolder.recyclerView.setHasFixedSize(true);
        FeedbackMessageChildItemAdapter feedbackMessageChildItemAdapter = new FeedbackMessageChildItemAdapter(isShownResponseButton,TextUtils.equals(dataBean.getCustomerId(), AccountHelper.getUser().getData().getCustomer().getId()));
        feedbackMessageChildItemAdapter.setOnItemResponseListener(this);
        itemHolder.recyclerView.setAdapter(feedbackMessageChildItemAdapter);
        itemHolder.recyclerView.addItemDecoration(new DividerItemDeco(mContext, DividerItemDecoration.VERTICAL));
            TLog.d(TAG, "feedbackMessageChildItemAdapter.initData(dataBean.getSuggestionList());");
            if (dataBean.getHighlightString() != null && !TextUtils.isEmpty(dataBean.getHighlightString())) {
                feedbackMessageChildItemAdapter.initData(dataBean.getSuggestionList(), dataBean.getHighlightString());
                TLog.d(TAG,"feedbackMessageChildItemAdapter.initData(dataBean.getSuggestionList(), dataBean.getHighlightString());");
            } else {
                TLog.d(TAG,"feedbackMessageChildItemAdapter.initData(dataBean.getSuggestionList());");
                feedbackMessageChildItemAdapter.initData(dataBean.getSuggestionList());
            }
        }
    }

    public void addData(List<FeedbackMessageBean.DataBean.ListBean> data) {
        TLog.d(TAG,"public void addData(List<FeedbackMessageBean.DataBean> data) {");
        if (this.data == null) {
            this.data = new ArrayList<>();
        }
        this.data.addAll(data);
        notifyDataSetChanged();
    }

    public void clearData() {
        if (data != null) {
            data.clear();
            notifyDataSetChanged();

        }
    }

    public void insertData(List<FeedbackMessageBean.DataBean.ListBean> data) {
        TLog.d(TAG,"public void addData(List<FeedbackMessageBean.DataBean> data) {");
        if (this.data == null) {
            this.data = new ArrayList<>();
        }
        this.data.addAll(0,data);
        notifyItemInserted(this.data.size());
    }

    public void initData(List<FeedbackMessageBean.DataBean.ListBean> data) {
        TLog.d(TAG,"public void initData(List<FeedbackMessageBean.DataBean> data) {");
        this.data = data;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if (data != null) {
            TLog.d(TAG, "getItemCount" + data.size());
            return data.size();
        }
        return 0;
    }

    public void clearAndInitData(List<FeedbackMessageBean.DataBean.ListBean> list) {
        if (data != null) {
            data.clear();
            this.data.addAll(list);
        } else {
            data = new ArrayList<>();
            data.addAll(list);
        }
        notifyDataSetChanged();
    }

    @Override
    public void onItemResponse(List<FeedbackMessageBean.DataBean.ListBean.SuggestionListBean> data, FeedbackMessageBean.DataBean.ListBean.SuggestionListBean suggestionBean, RecyclerView.Adapter adapter, boolean b) {
        onItemResponseListener.onItemResponse(data, suggestionBean, adapter, b);
    }



    public interface OnItemResponseListener {
        void onItemResponse(List<FeedbackMessageBean.DataBean.ListBean.SuggestionListBean> data, FeedbackMessageBean.DataBean.ListBean.SuggestionListBean suggestionBean, RecyclerView.Adapter adapter, boolean b);
    }

    public void setOnItemResponseListener(OnItemResponseListener onItemResponseListener) {
        this.onItemResponseListener = onItemResponseListener;
    }


    static class Holder extends RecyclerView.ViewHolder {
        @Bind(R.id.recyclerView)
        RecyclerView recyclerView;

        Holder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
