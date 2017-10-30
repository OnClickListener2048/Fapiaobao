package com.pilipa.fapiaobao.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lzy.okgo.model.HttpParams;
import com.pilipa.fapiaobao.R;
import com.pilipa.fapiaobao.base.BaseActivity;
import com.pilipa.fapiaobao.base.BaseApplication;
import com.pilipa.fapiaobao.ui.fragment.FilterFragment;
import com.pilipa.fapiaobao.ui.fragment.FinanceFragment;
import com.pilipa.fapiaobao.ui.widget.LabelsView;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author edz
 * @date 2017/10/23
 */

public class EstimateActivity extends BaseActivity {

    @Bind(R.id.dl_container)
    FrameLayout dlContainer;
    @Bind(R.id.dl)
    DrawerLayout dl;
    @Bind(R.id.filter)
    TextView filter;
    @Bind(R.id.estimate_back)
    ImageView estimateBack;
    @Bind(R.id.et_estimate)
    EditText etEstimate;
    @Bind(R.id.estimate_please)
    RelativeLayout estimatePlease;
    @Bind(R.id.test_redbag)
    Button testRedbag;
    @Bind(R.id.publish_cautions)
    TextView publishCautions;
    @Bind(R.id.labels)
    LabelsView labels;
    @Bind(R.id.go)
    Button go;
    @Bind(R.id.confirm_caution)
    LinearLayout llConfirmCaution;
    @Bind(R.id.filter_key)
    TextView filterKey;
    @Bind(R.id.ll_filter_key)
    LinearLayout llFilterKey;
    private String label = "";
    private HttpParams httpParams;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_estimate;
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void initView() {
        httpParams = new HttpParams();
        label = getIntent().getStringExtra(FinanceFragment.EXTRA_DATA_LABEL);
        httpParams.put("invoiceType", label);
        llConfirmCaution.setVisibility(View.GONE);
        llFilterKey.setVisibility(View.GONE);
        new Thread() {
            @Override
            public void run() {
                addFragment(dlContainer.getId(), FilterFragment.newInstance(new Bundle()));
            }
        }.start();

    }

    @Override
    public void initData() {

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick(R.id.filter)
    public void onViewClicked() {
        if (dl.isDrawerOpen(Gravity.END)) {
            dl.closeDrawer(Gravity.END);
        } else {

            dl.openDrawer(Gravity.END);
        }

    }

    @OnClick({R.id.test_redbag, R.id.go})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.test_redbag:
                String trim = etEstimate.getText().toString().trim();
                Double amount = Double.valueOf(trim);
                if (!(amount > 0)) {
                    BaseApplication.showToast("请输入正确的发票金额");
                    return;
                } else {
                    httpParams.put("amount",amount);

                }


                llFilterKey.setVisibility(View.GONE);
                llConfirmCaution.setVisibility(View.VISIBLE);
                break;
            case R.id.go:
                startActivity(new Intent(this, ConfirmActivity.class));
                break;
        }
    }

    public void closeDrawer() {
        if (dl != null) {
            if (dl.isDrawerOpen(Gravity.END)) {
                dl.closeDrawer(Gravity.END);
            }
        }
    }

    public void setFilterKeys(ArrayList<String> arrayListKind, String area) {
        filterKey.setText("");
        for (String s : arrayListKind) {
            filterKey.append(s);
            filterKey.append("\u3000");

        }
        String param = new String();
        for (String s : arrayListKind) {
            param.concat(s).concat(",");
        }
        httpParams.put("varieties", param);
        filterKey.append(area);
        filterKey.append("\u3000");
        httpParams.put("city", area);

        llFilterKey.setVisibility(View.VISIBLE);
    }
}
