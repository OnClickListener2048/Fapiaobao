package com.pilipa.fapiaobao.ui.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.pilipa.fapiaobao.R;
import com.pilipa.fapiaobao.base.BaseFragment;
import com.pilipa.fapiaobao.net.bean.invoice.MacherBeanToken;
import com.pilipa.fapiaobao.net.bean.invoice.MatchBean;
import com.pilipa.fapiaobao.ui.widget.LabelsView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by edz on 2017/10/30.
 */

public class EstimatePagerFragment extends BaseFragment {
    static String TAG = "EstimatePagerFragment";
    @Bind(R.id.publish_cautions)
    TextView publishCautions;
    @Bind(R.id.labels)
    LabelsView labels;
    private MacherBeanToken.DataBean dataBean;

    public static EstimatePagerFragment newInstance(Bundle bundle) {
        Log.d(TAG, "EstimatePagerFragmentnewInstance: ");
        EstimatePagerFragment estimatePagerFragment = new EstimatePagerFragment();
        estimatePagerFragment.setArguments(bundle);
        return estimatePagerFragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.item_estimate_matcher;
    }

    @Override
    protected void initWidget(View root) {
        super.initWidget(root);
        if (dataBean != null) {
            Log.d(TAG, "initWidget: dataBean != null");
            publishCautions.setText(dataBean.getAttentions());
            List<MacherBeanToken.DataBean.InvoiceVarietiesBean> invoiceVarieties = dataBean.getInvoiceVarieties();
            ArrayList<String> labelList = new ArrayList<>();
            if (invoiceVarieties != null&&invoiceVarieties.size()>0) {
                Log.d(TAG, "initWidget: invoiceVarieties != null&&invoiceVarieties.size()>0");
                for (MacherBeanToken.DataBean.InvoiceVarietiesBean invoiceVariety : invoiceVarieties) {
                    if (invoiceVariety.getInvoiceType() != null) {
                        Log.d(TAG, "initWidget: (invoiceVariety.getInvoiceType() != null)");
                        labelList.add(invoiceVariety.getInvoiceType().getName());
                    }
                }
            }
            labels.setLabels(labelList);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        Bundle arguments = getArguments();
        dataBean = arguments.getParcelable("estimate_item");
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
