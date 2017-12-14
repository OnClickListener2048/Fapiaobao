package com.pilipa.fapiaobao.ui;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.mylibrary.utils.TLog;
import com.pilipa.fapiaobao.R;
import com.pilipa.fapiaobao.adapter.FeedbackAdapterWrapper;
import com.pilipa.fapiaobao.adapter.FeedbackMessagesAdapter;
import com.pilipa.fapiaobao.base.BaseActivity;
import com.pilipa.fapiaobao.net.Api;
import com.pilipa.fapiaobao.net.Constant;
import com.pilipa.fapiaobao.net.bean.me.FeedbackMessageBean;
import com.pilipa.fapiaobao.ui.widget.EmptyRecyclerView;
import com.rengwuxian.materialedittext.MaterialEditText;
import com.rengwuxian.materialedittext.validation.METValidator;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by edz on 2017/12/5.
 */

public class SearchActivity extends BaseActivity {
    private static final String TAG = "SearchActivity";
    @Bind(R.id.title)
    TextView title;
    @Bind(R.id._back)
    ImageView Back;
    @Bind(R.id.met_feedback)
    MaterialEditText metFeedback;
    @Bind(R.id.search)
    ImageView search;
    @Bind(R.id.recyclerView)
    EmptyRecyclerView recyclerView;
    private int pageSize = 20;
    private int pageNo = 1;
    private FeedbackMessagesAdapter feedbackMessagesAdapter;
    private FeedbackAdapterWrapper normalAdapterWrapper;
    private ProgressBar progressBar;
    private TextView textView_loading;
    private int totalPageNo;
    private boolean isLoadingMore;
    private View footerView;
    private View headerView;
    private View emptyView;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_search;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id._back, R.id.search})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id._back:
                finish();
                break;
            case R.id.search:
                if (metFeedback.validate()) {
                    getSuggestion(pageNo, pageSize, "", metFeedback.getText().toString().trim(), "", new Api.BaseViewCallbackWithOnStart<FeedbackMessageBean>() {
                        @Override
                        public void onStart() {
                            emptyView.setVisibility(View.GONE);
                            recyclerView.setVisibility(View.VISIBLE);
                            pageNo = 1;
                            headerView.setVisibility(View.VISIBLE);
                            footerView.setVisibility(View.VISIBLE);
                            progressBar.setVisibility(View.VISIBLE);
                            textView_loading.setText(getString(R.string.loading));
                        }

                        @Override
                        public void onFinish() {
                            LinearLayoutManager layoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
                            int lastVisibleItemPosition = layoutManager.findLastVisibleItemPosition();
                            TLog.log("lastVisibleItemPosition"+lastVisibleItemPosition);
                            TLog.log("normalAdapterWrapper.getItemCount()"+normalAdapterWrapper.getItemCount());
                            if (lastVisibleItemPosition+2 == normalAdapterWrapper.getItemCount()) {
                                footerView.setVisibility(View.GONE);
                            }
                        }

                        @Override
                        public void onError() {

                        }

                        @Override
                        public void setData(FeedbackMessageBean feedbackMessageBean) {
                            if (feedbackMessageBean.getStatus() == 200) {
                                recyclerView.setVisibility(View.VISIBLE);
                                emptyView.setVisibility(View.GONE);
                                normalAdapterWrapper.addFooterView(footerView);
                                normalAdapterWrapper.addHeaderView(headerView);
                                List<FeedbackMessageBean.DataBean.ListBean> list = feedbackMessageBean.getData().getList();
                                for (FeedbackMessageBean.DataBean.ListBean listBean : list) {
                                    listBean.setHighlightString(metFeedback.getText().toString().trim());
                                }
                                feedbackMessagesAdapter.clearAndInitData(list);
                                normalAdapterWrapper.notifyDataSetChanged();
                                totalPageNo = feedbackMessageBean.getData().getTotalPage();
                                pageNo++;
                                if (feedbackMessageBean.getData().getList().size()<pageSize) {
                                    progressBar.setVisibility(View.GONE);
                                    textView_loading.setText(getString(R.string.already_reach_the_bottom));
                                }
                            } else if (feedbackMessageBean.getStatus() == Constant.REQUEST_NO_CONTENT) {
//                                normalAdapterWrapper.removeFooterView();
//                                normalAdapterWrapper.removeHeaderView();
                                recyclerView.setVisibility(View.GONE);
                                emptyView.setVisibility(View.VISIBLE);
                                feedbackMessagesAdapter.clearData();
                                normalAdapterWrapper.notifyDataSetChanged();
                                progressBar.setVisibility(View.INVISIBLE);
                                textView_loading.setText(getString(R.string.already_reach_the_bottom));
                            }
                        }
                    });
                }
                break;
        }
    }

    @Override
    public void initData() {
        super.initData();
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(final RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    LinearLayoutManager manager = (LinearLayoutManager) recyclerView.getLayoutManager();
                    int lastVisiblePosition = manager.findLastVisibleItemPosition();
                    if (lastVisiblePosition >= manager.getItemCount() - 1) {
                        //TODO loading more
                        if (!isLoadingMore) {
                            if (pageNo > totalPageNo) {
                                progressBar.setVisibility(View.INVISIBLE);
                                textView_loading.setText(getString(R.string.already_reach_the_bottom));
                                return;
                            }
                            getSuggestion(pageNo, pageSize, "", metFeedback.getText().toString().trim(), "", new Api.BaseViewCallbackWithOnStart<FeedbackMessageBean>() {
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
                                        List<FeedbackMessageBean.DataBean.ListBean> list = feedbackMessageBean.getData().getList();
                                        for (FeedbackMessageBean.DataBean.ListBean listBean : list) {
                                            listBean.setHighlightString(metFeedback.getText().toString().trim());
                                        }
                                        feedbackMessagesAdapter.addData(list);
                                        feedbackMessagesAdapter.notifyDataSetChanged();
                                        normalAdapterWrapper.notifyDataSetChanged();
                                        pageNo++;
                                    }

                                }
                            });
                        }
                    }
                }
            }
        });
    }

    @Override
    public void initView() {
        super.initView();
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
        metFeedback.addValidator(new METValidator("请输入内容来进行搜索~") {
            @Override
            public boolean isValid(@NonNull CharSequence text, boolean isEmpty) {
                return !isEmpty;
            }
        });
        feedbackMessagesAdapter = new FeedbackMessagesAdapter(false);
        normalAdapterWrapper = new FeedbackAdapterWrapper(feedbackMessagesAdapter);
        footerView = LayoutInflater.from(this).inflate(R.layout.footer_refreshing_feedback, null);
        normalAdapterWrapper.addFooterView(footerView);
        headerView = LayoutInflater.from(this).inflate(R.layout.header_feedback, null);
        normalAdapterWrapper.addHeaderView(headerView);
        recyclerView.setAdapter(normalAdapterWrapper);
        emptyView = findViewById(R.id.empty_view);
        emptyView.setVisibility(View.GONE);
        progressBar = (ProgressBar) footerView.findViewById(R.id.loading_progress);
        textView_loading = (TextView) footerView.findViewById(R.id.loading);

        headerView.setVisibility(View.INVISIBLE);
        footerView.setVisibility(View.INVISIBLE);
    }


    public void getSuggestion(int pageNo, int pageSize, String id, String suggestion, String token, Api.BaseViewCallbackWithOnStart baseViewCallbackWithOnStart) {
        Api.getSuggestions(pageNo, pageSize, id, suggestion, token, this, baseViewCallbackWithOnStart);
    }



}
