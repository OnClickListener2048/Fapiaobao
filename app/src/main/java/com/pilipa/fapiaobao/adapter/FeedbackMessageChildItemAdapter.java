package com.pilipa.fapiaobao.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.mylibrary.utils.TLog;
import com.pilipa.fapiaobao.R;
import com.pilipa.fapiaobao.net.bean.me.FeedbackMessageBean;
import com.pilipa.fapiaobao.ui.constants.Constant;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by edz on 2017/12/4.
 */

public class FeedbackMessageChildItemAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final String TAG = "FeedbackMessageChildItemAdapter";
    private final boolean isSHownResponseButtonAndSupplementButton;
    Context mContext;
    private List<FeedbackMessageBean.DataBean.ListBean.SuggestionListBean> data;
    private boolean hasAnswer;
    private OnItemResponseListener onItemResponseListener;
    private boolean isShownResponseButton = true;
    private String highlightString;

    public FeedbackMessageChildItemAdapter(boolean isShownResponseButton, boolean isSHownResponseButtonAndSupplementButton) {
        this.isShownResponseButton = isShownResponseButton;
        this.isSHownResponseButtonAndSupplementButton = isSHownResponseButtonAndSupplementButton;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        TLog.d(TAG,"public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {");
        mContext = parent.getContext();
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_feedback_child, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        TLog.d(TAG," public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {");
        final FeedbackMessageBean.DataBean.ListBean.SuggestionListBean suggestionBean = data.get(position);
        ViewHolder viewHolder = (ViewHolder) holder;
        viewHolder.tvQuestionerData.setText(suggestionBean.getCreateTime());
        if (Constant.TYPE_ANSWER.equals(suggestionBean.getType())) {
            hasAnswer = true;
            viewHolder.tvAnswerName.setText("票宝回复");
            viewHolder.civAvatar.setVisibility(View.INVISIBLE);
            viewHolder.tvAnswerName.setVisibility(View.VISIBLE);
            viewHolder.tvQuestionerName.setVisibility(View.GONE);

        } else if (Constant.TYPE_QUESTIONER.equals(suggestionBean.getType())) {
            viewHolder.civAvatar.setVisibility(View.VISIBLE);
            viewHolder.tvAnswerName.setVisibility(View.GONE);
            viewHolder.tvQuestionerName.setVisibility(View.VISIBLE);
            viewHolder.tvQuestionerName.setText(suggestionBean.getNickname());
            Glide.with(mContext)
                    .load(suggestionBean
                            .getAvatar())
                    .asBitmap().centerCrop()
                    .error(R.mipmap.error_small)
                    .placeholder(R.mipmap.loading_small)
                    .into(viewHolder.civAvatar);
        }

        if (position == 0) {
            viewHolder.llAvatar.setVisibility(View.VISIBLE);
        } else {
            viewHolder.llAvatar.setVisibility(View.INVISIBLE);
        }

        if (isShownResponseButton) {
            if (isSHownResponseButtonAndSupplementButton) {
                if (position == data.size() - 1) {
                    if (hasAnswer) {
                        viewHolder.feedbackResponse.setVisibility(View.VISIBLE);
                        viewHolder.feedbackSupplement.setVisibility(View.GONE);
                    } else {
                        viewHolder.feedbackResponse.setVisibility(View.GONE);
                        viewHolder.feedbackSupplement.setVisibility(View.VISIBLE);
                    }
                } else {
                    viewHolder.feedbackResponse.setVisibility(View.GONE);
                    viewHolder.feedbackSupplement.setVisibility(View.GONE);
                }
            } else {
                viewHolder.feedbackResponse.setVisibility(View.GONE);
                viewHolder.feedbackSupplement.setVisibility(View.GONE);
            }
        } else {
            viewHolder.feedbackResponse.setVisibility(View.GONE);
            viewHolder.feedbackSupplement.setVisibility(View.GONE);
        }

        String message = suggestionBean.getMessage();
        TLog.d(TAG, "message" + message);
        if (highlightString != null && !TextUtils.isEmpty(highlightString)) {
            if (message.contains(highlightString)) {
                int messageLength = message.length();
                TLog.d(TAG, "int messageLength = message.length();" + messageLength);
                int highlightStringLength = highlightString.length();
                TLog.d(TAG, "int highlightStringLength = highlightString.length();" + highlightStringLength);
                int recyclerCount = messageLength / highlightStringLength;
                TLog.d(TAG, " int recyclerCount = messageLength / highlightStringLength;" + recyclerCount);
                int result = -1;
                SpannableString spannableString = new SpannableString(message);  //获取按钮上的文字
                for (int i = 0; i < recyclerCount; i++) {
                    result = message.indexOf(highlightString, result + 1);
                    TLog.d(TAG, " result = message.indexOf(highlightString, result);" + result);
                    if (result != -1) {
                        ForegroundColorSpan span = new ForegroundColorSpan(mContext.getResources().getColor(R.color.main_style));
                        spannableString.setSpan(span, result, result + highlightStringLength, Spannable.SPAN_INCLUSIVE_INCLUSIVE);
                    }
                }
                viewHolder.tvContent.setText(spannableString);
            } else {
                viewHolder.tvContent.setText(message);
            }
        } else {
            viewHolder.tvContent.setText(message);
        }
        viewHolder.feedbackSupplement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemResponseListener.onItemResponse(data, suggestionBean, FeedbackMessageChildItemAdapter.this, false);
            }
        });

        viewHolder.feedbackResponse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemResponseListener.onItemResponse(data, suggestionBean, FeedbackMessageChildItemAdapter.this, true);
            }
        });
    }

    public void initData(List<FeedbackMessageBean.DataBean.ListBean.SuggestionListBean> suggestionList, String highlightString) {
        TLog.d(TAG, "public void initData(List<FeedbackMessageBean.DataBean.ListBean.SuggestionListBean> suggestionList, String highlightString) {");
        TLog.d(TAG, " if (data!= null) {");
        this.data = suggestionList;
        this.highlightString = highlightString;
        notifyDataSetChanged();
    }

    interface OnItemResponseListener {
        void onItemResponse(List<FeedbackMessageBean.DataBean.ListBean.SuggestionListBean> data, FeedbackMessageBean.DataBean.ListBean.SuggestionListBean suggestionBean, RecyclerView.Adapter adapter, boolean isResponse);
    }

    public void setOnItemResponseListener(OnItemResponseListener onItemResponseListener) {
        this.onItemResponseListener = onItemResponseListener;
    }


    public void addData(List<FeedbackMessageBean.DataBean.ListBean.SuggestionListBean> data) {
        if (this.data == null) {
            data = new ArrayList<>();
        }
        this.data.addAll(data);
        notifyDataSetChanged();
    }

    public void initData(List<FeedbackMessageBean.DataBean.ListBean.SuggestionListBean> data) {
        TLog.d(TAG, "public void initData(List<FeedbackMessageBean.DataBean.ListBean.SuggestionListBean> data) {");
            TLog.d(TAG," if (data!= null) {");
            this.data = data;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if (data!= null) {
            TLog.d(TAG,"if (data!= null) {");
            return data.size();
        }
        return 0;
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        @Bind(R.id.civ_avatar)
        CircleImageView civAvatar;
        @Bind(R.id.tv_answer_name)
        TextView tvAnswerName;
        @Bind(R.id.tv_questioner_name)
        TextView tvQuestionerName;
        @Bind(R.id.tv_questioner_data)
        TextView tvQuestionerData;
        @Bind(R.id.tv_content)
        TextView tvContent;
        @Bind(R.id.feedback_response)
        ImageView feedbackResponse;
        @Bind(R.id.feedback_supplement)
        ImageView feedbackSupplement;
        @Bind(R.id.ll_questioner)
        LinearLayout llQuestioner;
        @Bind(R.id.ll_avatar)
        LinearLayout llAvatar;
        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
