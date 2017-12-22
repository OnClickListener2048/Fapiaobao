package com.pilipa.fapiaobao.ui.fragment;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.mylibrary.utils.TLog;
import com.example.mylibrary.utils.TimeUtils;
import com.pilipa.fapiaobao.R;
import com.pilipa.fapiaobao.base.BaseFragment;
import com.pilipa.fapiaobao.net.bean.invoice.MacherBeanToken;
import com.pilipa.fapiaobao.ui.EstimateLocationActivity;
import com.pilipa.fapiaobao.ui.widget.LabelsView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by edz on 2017/10/30.
 */

public class EstimatePagerFragment extends BaseFragment {
    static String TAG = "EstimatePagerFragment";
    @Bind(R.id.publish_cautions)
    TextView publishCautions;
    @Bind(R.id.labels)
    LabelsView labels;
    @Bind(R.id.date)
    TextView date;
    private MacherBeanToken.DataBean dataBean;
    private Dialog mTipDialog;
    private EstimateLocationActivity activity;

    public static EstimatePagerFragment newInstance(Bundle bundle) {
        EstimatePagerFragment estimatePagerFragment = new EstimatePagerFragment();
        estimatePagerFragment.setArguments(bundle);
        return estimatePagerFragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.item_estimate_matcher;
    }


    @Override
    public void onStart() {
        super.onStart();
        TLog.log("onStart");
        TLog.log("dataBean==null"+dataBean);
        if (dataBean != null) {
            date.setText(TimeUtils.millis2String(dataBean.getDeadline(), TimeUtils.FORMAT));
            publishCautions.setText(dataBean.getAttentions().isEmpty() ? "无" : dataBean.getAttentions().trim());
            List<MacherBeanToken.DataBean.InvoiceTypesBean> invoiceVarieties = dataBean.getInvoiceTypes();
            ArrayList<String> labelList = new ArrayList<>();
            if (invoiceVarieties != null && invoiceVarieties.size() > 0) {
                for (MacherBeanToken.DataBean.InvoiceTypesBean invoiceVariety : invoiceVarieties) {
                    if (invoiceVariety.getInvoiceType() != null) {
                        labelList.add(invoiceVariety.getInvoiceType().getName());
                    }
                }
            }
            TLog.log("labelList.toString()----------------"+labelList.toString());
            labels.setLabels(labelList);
        }
    }

    @Override
    protected void onBindViewBefore(View root) {
        super.onBindViewBefore(root);
        TLog.log("onBindViewBefore");
    }

    @Override
    protected void onRestartInstance(Bundle bundle) {
        super.onRestartInstance(bundle);
        TLog.log("onRestartInstance");
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        TLog.log("onAttach");

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TLog.log("onCreate");

    }

    @Override
    public void onDetach() {
        super.onDetach();
        TLog.log("onDetach");

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        TLog.log("onActivityCreated");

    }




    @Override
    public void onAttachFragment(Fragment childFragment) {
        super.onAttachFragment(childFragment);
        TLog.log("onAttachFragment");
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

    }

    @Override
    public void onPause() {
        super.onPause();
        TLog.log("onPause");
    }

    @Override
    protected void initWidget(View root) {
        super.initWidget(root);
        TLog.log("dataBean==null"+dataBean);
        if (dataBean != null) {
            date.setText(TimeUtils.millis2String(dataBean.getDeadline(), TimeUtils.FORMAT));
            publishCautions.setText(dataBean.getAttentions().trim());
            List<MacherBeanToken.DataBean.InvoiceTypesBean> invoiceVarieties = dataBean.getInvoiceTypes();
            ArrayList<String> labelList = new ArrayList<>();
            if (invoiceVarieties != null && invoiceVarieties.size() > 0) {
                for (MacherBeanToken.DataBean.InvoiceTypesBean invoiceVariety : invoiceVarieties) {
                    if (invoiceVariety.getInvoiceType() != null) {
                        labelList.add(invoiceVariety.getInvoiceType().getName());
                    }
                }
            }
            TLog.log("labelList.toString()----------------"+labelList.toString());
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
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @OnClick(R.id.date)
    public void onViewClicked() {
        setTipDialog(R.layout.layout_extimate_tip1);
    }

    private void setTipDialog(@NonNull int res) {
        mTipDialog = new Dialog(mContext, R.style.BottomDialog);
        LinearLayout root = (LinearLayout) LayoutInflater.from(mContext).inflate(
                res, null);
        root.findViewById(R.id.i_know).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mTipDialog.dismiss();
            }
        });
        mTipDialog.setContentView(root);
        Window dialogWindow = mTipDialog.getWindow();
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
        mTipDialog.show();
    }
}
