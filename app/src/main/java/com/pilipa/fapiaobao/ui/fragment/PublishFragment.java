package com.pilipa.fapiaobao.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.pilipa.fapiaobao.R;
import com.pilipa.fapiaobao.base.BaseFragment;
import com.pilipa.fapiaobao.ui.HistoryActivity2;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by lyt on 2017/10/13.
 */

public class PublishFragment extends BaseFragment {
    @Bind(R.id.tv_publish_pub_history)
    TextView tvPublishPubHistory;
    @Bind(R.id.tv_publish_normal_receipt)
    TextView tvPublishNormalReceipt;
    @Bind(R.id.tv_publish_special_receipt)
    TextView tvPublishSpecialReceipt;
    @Bind(R.id.tv_publish_normal_and_special_receipt)
    TextView tvPublishNormalAndSpecialReceipt;
    @Bind(R.id.fl_publish_container)
    FrameLayout flPublishContainer;
    private SpecialReceiptFragment specialReceiptFragment;
    private NormalReceiptFragment normalReceiptFragment;
    private NormalSpecialFragment normalSpecialFragment;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_publish;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    protected void initWidget(View root) {
        super.initWidget(root);
        specialReceiptFragment = new SpecialReceiptFragment();
        normalReceiptFragment = new NormalReceiptFragment();
        normalSpecialFragment = new NormalSpecialFragment();
        addFragment(R.id.fl_publish_container, normalReceiptFragment);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @OnClick({R.id.tv_publish_pub_history
            , R.id.tv_publish_normal_receipt
            , R.id.tv_publish_special_receipt
            , R.id.tv_publish_normal_and_special_receipt
            , R.id.fl_publish_container})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_publish_pub_history:
                startActivity(new Intent(getContext(), HistoryActivity2.class));
                break;
            case R.id.tv_publish_normal_receipt:
                addFragment(R.id.fl_publish_container, normalReceiptFragment);
                break;
            case R.id.tv_publish_special_receipt:
                addFragment(R.id.fl_publish_container, specialReceiptFragment);
                break;
            case R.id.tv_publish_normal_and_special_receipt:
                addFragment(R.id.fl_publish_container, normalSpecialFragment);
                break;
            case R.id.fl_publish_container:
                break;
        }
    }
}
