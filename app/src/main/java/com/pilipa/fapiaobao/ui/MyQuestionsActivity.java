package com.pilipa.fapiaobao.ui;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.mylibrary.utils.KeyboardUtils;
import com.example.mylibrary.utils.TLog;
import com.example.mylibrary.utils.TimeUtils;
import com.pilipa.fapiaobao.R;
import com.pilipa.fapiaobao.account.AccountHelper;
import com.pilipa.fapiaobao.adapter.FeedbackAdapterWrapper;
import com.pilipa.fapiaobao.adapter.FeedbackMessagesAdapter;
import com.pilipa.fapiaobao.base.BaseActivity;
import com.pilipa.fapiaobao.base.BaseApplication;
import com.pilipa.fapiaobao.net.Api;
import com.pilipa.fapiaobao.net.Constant;
import com.pilipa.fapiaobao.net.bean.me.FeedBackBean;
import com.pilipa.fapiaobao.net.bean.me.FeedbackMessageBean;
import com.pilipa.fapiaobao.ui.widget.EmptyRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by edz on 2017/12/5.
 */

public class MyQuestionsActivity extends BaseActivity implements FeedbackMessagesAdapter.OnItemResponseListener{
    private static final String TAG = "MyQuestionsActivity";
    @Bind(R.id.recyclerView)
    EmptyRecyclerView recyclerView;
    @Bind(R.id.et_feedback)
    EditText etFeedback;
    @Bind(R.id.feedback_send)
    ImageView feedbackSend;
    private Dialog mDialog;
    private FeedbackMessagesAdapter feedbackMessagesAdapter;
    private int pageSize = 10;
    private int pageNo = 1;
    private boolean isLoadingMore;
    private FeedbackAdapterWrapper normalAdapterWrapper;
    private ProgressBar progressBar;
    private TextView textView_loading;
    private int totalPageNo;
    private boolean responding;
    private List<FeedbackMessageBean.DataBean.ListBean.SuggestionListBean> data;
    private FeedbackMessageBean.DataBean.ListBean.SuggestionListBean suggestionBean;
    private RecyclerView.Adapter adapter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_myquestions;
    }

    @OnClick({R.id.feedback_back})
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.feedback_back: {
                finish();
            }
            break;
        }
    }

    @Override
    public void initView() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false) {
            @Override
            public void collectAdjacentPrefetchPositions(int dx, int dy, RecyclerView.State state, LayoutPrefetchRegistry layoutPrefetchRegistry) {
                super.collectAdjacentPrefetchPositions(dx, dy, state, layoutPrefetchRegistry);
                try {
                    super.collectAdjacentPrefetchPositions(dx, dy, state, layoutPrefetchRegistry);
                } catch (IllegalArgumentException e) {
                    TLog.e(TAG, "catch exception");
                }
            }
        };
        linearLayoutManager.setSmoothScrollbarEnabled(true);
        linearLayoutManager.setAutoMeasureEnabled(true);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setHasFixedSize(true);
        recyclerView.setNestedScrollingEnabled(false);
    }

    @Override
    public void initData() {
        feedbackMessagesAdapter = new FeedbackMessagesAdapter();
        feedbackMessagesAdapter.setOnItemResponseListener(this);
        normalAdapterWrapper = new FeedbackAdapterWrapper(feedbackMessagesAdapter);
        View footerView = LayoutInflater.from(this).inflate(R.layout.footer_refreshing_feedback, null);
        normalAdapterWrapper.addFooterView(footerView);
        View headerView = LayoutInflater.from(this).inflate(R.layout.header_feedback, null);
        normalAdapterWrapper.addHeaderView(headerView);
        View emptyView = View.inflate(this, R.layout.layout_details_empty_view, null);
        emptyView.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
        recyclerView.setEmptyView(emptyView);
        recyclerView.setAdapter(normalAdapterWrapper);
        progressBar = (ProgressBar) footerView.findViewById(R.id.loading_progress);
        textView_loading = (TextView) footerView.findViewById(R.id.loading);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    LinearLayoutManager manager = (LinearLayoutManager) recyclerView.getLayoutManager();
                    int lastVisiblePosition = manager.findLastVisibleItemPosition();
                    if (lastVisiblePosition >= manager.getItemCount() - 1) {
                        //TODO loading more
                        if (!isLoadingMore) {
                            if (pageNo > totalPageNo) {
                                progressBar.setVisibility(View.GONE);
                                textView_loading.setText(getString(R.string.no_more));
                                return;
                            }
                            getSuggestion(pageNo, pageSize, "", "", AccountHelper.getToken(), new Api.BaseViewCallbackWithOnStart<FeedbackMessageBean>() {
                                @Override
                                public void onStart() {
                                    isLoadingMore = true;
                                    progressBar.setVisibility(View.VISIBLE);
                                    textView_loading.setText(getString(R.string.loading));
                                }

                                @Override
                                public void onFinish() {
                                    isLoadingMore = false;
                                }

                                @Override
                                public void onError() {
                                    isLoadingMore = false;
                                }

                                @Override
                                public void setData(FeedbackMessageBean feedbackMessageBean) {
                                    if (feedbackMessageBean.getStatus() == 200) {
                                        feedbackMessagesAdapter.addData(feedbackMessageBean.getData().getList());
                                    }
                                    feedbackMessagesAdapter.notifyDataSetChanged();
                                    normalAdapterWrapper.notifyDataSetChanged();
                                    pageNo++;
                                }
                            });
                        }
                    }
                }
            }
        });
        getSuggestion(pageNo, pageSize, "", "", AccountHelper.getToken(), new Api.BaseViewCallbackWithOnStart<FeedbackMessageBean>() {
            @Override
            public void onStart() {

            }

            @Override
            public void onFinish() {

            }

            @Override
            public void onError() {
                progressBar.setVisibility(View.GONE);
                textView_loading.setText(getString(R.string.server_error));
            }

            @Override
            public void setData(FeedbackMessageBean feedbackMessageBean) {
                if (feedbackMessageBean.getStatus() == 200) {
                    feedbackMessagesAdapter.initData(feedbackMessageBean.getData().getList());
                    normalAdapterWrapper.notifyDataSetChanged();
                    totalPageNo = feedbackMessageBean.getData().getTotalPage();
                    pageNo++;
                    if (feedbackMessageBean.getData().getList().size()<pageSize) {
                        progressBar.setVisibility(View.GONE);
                        textView_loading.setText(getString(R.string.no_more));
                    }
                } else if (feedbackMessageBean.getStatus() == Constant.REQUEST_NO_CONTENT) {
                    progressBar.setVisibility(View.GONE);
                    textView_loading.setText(getString(R.string.no_more));
                }
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }


    public void getSuggestion(int pageNo, int pageSize, String id, String suggestion, String token, Api.BaseViewCallbackWithOnStart baseViewCallbackWithOnStart) {
        Api.getSuggestions(pageNo, pageSize, id, suggestion, token, this, baseViewCallbackWithOnStart);
    }

    private void setDialog() {
        mDialog = new Dialog(this, R.style.BottomDialog);
        mDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                MyQuestionsActivity.this.finish();
            }
        });
        RelativeLayout root = (RelativeLayout) LayoutInflater.from(this).inflate(
                R.layout.layout_feedback_tip, null);
        root.findViewById(R.id.rl_root).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDialog.dismiss();
            }
        });
        //初始化视图
        mDialog.setContentView(root);
        Window dialogWindow = mDialog.getWindow();
        dialogWindow.setGravity(Gravity.CENTER);
//        dialogWindow.setWindowAnimations(R.style.dialogstyle); // 添加动画
        WindowManager.LayoutParams lp = dialogWindow.getAttributes(); // 获取对话框当前的参数值
        lp.x = 0; // 新位置X坐标
        lp.y = 0; // 新位置Y坐标
        lp.width = (int) getResources().getDisplayMetrics().widthPixels; // 宽度
        root.measure(0, 0);
        lp.height = root.getMeasuredHeight();

        lp.alpha = 9f; // 透明度
        dialogWindow.setAttributes(lp);
        mDialog.show();
    }

    private void suggestion(final String str) {
        Api.suggestion("", str, AccountHelper.getToken(), new Api.BaseViewCallback<FeedBackBean>() {
            @Override
            public void setData(FeedBackBean feedBackBean) {
                if (feedBackBean.getStatus() == 200) {

                    feedback(feedBackBean.getData());
                    etFeedback.setText(null);
//                                setDialog();
                }
                Log.d("", "initData:suggestion success");
            }
        });
    }

    private void feedback(String id) {
        FeedbackMessageBean.DataBean dataBean = new FeedbackMessageBean.DataBean();
        FeedbackMessageBean.DataBean.ListBean listBean = new FeedbackMessageBean.DataBean.ListBean();
        List<FeedbackMessageBean.DataBean.ListBean.SuggestionListBean> list = new ArrayList<>();
        FeedbackMessageBean.DataBean.ListBean.SuggestionListBean suggestionBean = new FeedbackMessageBean.DataBean.ListBean.SuggestionListBean();
        suggestionBean.setAvatar(AccountHelper.getUser().getData().getCustomer().getHeadimg());
        suggestionBean.setCreateTime(TimeUtils.getNowString(TimeUtils.DEFAULT_FORMAT));
        TLog.log(" suggestionBean.setMessage(etFeedback.getText().toString().trim());" + etFeedback.getText().toString().trim());
        suggestionBean.setMessage(etFeedback.getText().toString().trim());
        suggestionBean.setNickname(AccountHelper.getUser().getData().getCustomer().getNickname());
        suggestionBean.setType("1");
        suggestionBean.setSuggestionId(id);
        list.add(suggestionBean);
        listBean.setSuggestionList(list);
        List<FeedbackMessageBean.DataBean.ListBean> listBeanList = new ArrayList<>();
        listBeanList.add(listBean);
        dataBean.setList(listBeanList);

        feedbackMessagesAdapter.insertData(listBeanList);
        normalAdapterWrapper.notifyDataSetChanged();
        recyclerView.scrollToPosition(0);
    }

    @OnClick(R.id.feedback_send)
    public void onViewClicked() {
        if (TextUtils.isEmpty(etFeedback.getText())) {
            BaseApplication.showToast("请先填写要反馈的内容~");
            return;
        }
        if (responding) {
            response(data, suggestionBean, adapter);
        } else {
            suggestion(etFeedback.getText().toString());
        }

    }


    @Override
    public void onItemResponse(List<FeedbackMessageBean.DataBean.ListBean.SuggestionListBean> data, FeedbackMessageBean.DataBean.ListBean.SuggestionListBean suggestionBean, RecyclerView.Adapter adapter) {
        responding = true;
        this.data = data;
        this.suggestionBean = suggestionBean;
        this.adapter = adapter;
        KeyboardUtils.showSoftInput(this);
        etFeedback.setHint("to " + suggestionBean.getNickname());
        etFeedback.requestFocus();
    }

    private void response(final List<FeedbackMessageBean.DataBean.ListBean.SuggestionListBean> data, final FeedbackMessageBean.DataBean.ListBean.SuggestionListBean suggestionBean, final RecyclerView.Adapter adapter) {
        Api.suggestion(suggestionBean.getSuggestionId(), etFeedback.getText().toString(), AccountHelper.getToken(), new Api.BaseViewCallback<FeedBackBean>() {
            @Override
            public void setData(FeedBackBean feedBackBean) {
                if (feedBackBean.getStatus() == 200) {
                    responseBack(data, suggestionBean, adapter, feedBackBean);
                    etFeedback.setText(null);
                }
                TLog.d(TAG, "initData:suggestion success");
            }
        });
    }

    private void responseBack(List<FeedbackMessageBean.DataBean.ListBean.SuggestionListBean> data, FeedbackMessageBean.DataBean.ListBean.SuggestionListBean suggestionBean, RecyclerView.Adapter adapter, FeedBackBean feedBackBean) {

        FeedbackMessageBean.DataBean.ListBean.SuggestionListBean suggestionBeanOrigin = new FeedbackMessageBean.DataBean.ListBean.SuggestionListBean();
        suggestionBeanOrigin.setAvatar(AccountHelper.getUser().getData().getCustomer().getHeadimg());
        suggestionBeanOrigin.setCreateTime(TimeUtils.getNowString(TimeUtils.DEFAULT_FORMAT));
        suggestionBeanOrigin.setMessage(etFeedback.getText().toString().trim());
        suggestionBeanOrigin.setNickname(AccountHelper.getUser().getData().getCustomer().getNickname());
        suggestionBeanOrigin.setType("1");
        suggestionBeanOrigin.setSuggestionId(suggestionBean.getSuggestionId());
        data.add(suggestionBeanOrigin);
        adapter.notifyDataSetChanged();
        responding = false;
        etFeedback.setHint("");
    }

}