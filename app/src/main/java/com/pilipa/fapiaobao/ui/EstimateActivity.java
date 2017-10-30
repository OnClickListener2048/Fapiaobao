package com.pilipa.fapiaobao.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.pilipa.fapiaobao.R;
import com.pilipa.fapiaobao.adapter.ExtimatePagerAdapter;
import com.pilipa.fapiaobao.base.BaseActivity;
import com.pilipa.fapiaobao.base.BaseApplication;
import com.pilipa.fapiaobao.net.Api;
import com.pilipa.fapiaobao.net.bean.invoice.MacherBeanToken;
import com.pilipa.fapiaobao.net.bean.invoice.MatchBean;
import com.pilipa.fapiaobao.ui.fragment.FilterFragment;
import com.pilipa.fapiaobao.ui.fragment.FinanceFragment;
import com.tmall.ultraviewpager.UltraViewPager;

import java.util.ArrayList;
import java.util.HashMap;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author edz
 * @date 2017/10/23
 */

public class EstimateActivity extends BaseActivity implements ViewPager.OnPageChangeListener {
    String TAG = "EstimateActivity";

    @Bind(R.id.title)
    TextView title;
    @Bind(R.id.estimate_back)
    ImageView estimateBack;
    @Bind(R.id.filter)
    TextView filter;
    @Bind(R.id.et_estimate)
    EditText etEstimate;
    @Bind(R.id.estimate_please)
    RelativeLayout estimatePlease;
    @Bind(R.id.test_redbag)
    Button testRedbag;
    @Bind(R.id.filter_key)
    TextView filterKey;
    @Bind(R.id.ll_filter_key)
    LinearLayout llFilterKey;
    @Bind(R.id.ultra_viewpager)
    UltraViewPager ultraViewpager;
    @Bind(R.id.go)
    Button go;
    @Bind(R.id.confirm_caution)
    LinearLayout llConfirmCaution;
    @Bind(R.id.dl_container)
    FrameLayout dlContainer;
    @Bind(R.id.dl)
    DrawerLayout dl;
    @Bind(R.id.tolast)
    ImageView tolast;
    @Bind(R.id.tonext)
    ImageView tonext;
    @Bind(R.id.ll_bonus)
    LinearLayout llBonus;
    @Bind(R.id.bonus)
    TextView bonus;
    private String label = "";
    private HashMap<String, String> httpParams;
    private int currentItem = 0;
    private MacherBeanToken matchBean;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_estimate;
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void initView() {
        estimatePlease.setVisibility(View.VISIBLE);
        llBonus.setVisibility(View.GONE);
        httpParams = new HashMap<>();
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


        ultraViewpager.setScrollMode(UltraViewPager.ScrollMode.HORIZONTAL);
        ultraViewpager.disableAutoScroll();
        ultraViewpager.disableIndicator();
        ultraViewpager.setOnPageChangeListener(this);

        tonext.setEnabled(true);
        tolast.setEnabled(false);
    }

    public void updateButtonStatus() {

        if (matchBean != null) {
            Log.d(TAG, "updateButtonStatus: matchBean != null");
            if (matchBean.getData() != null) {
                Log.d(TAG, "updateButtonStatus:matchBean.getData() != null");
                if (matchBean.getData().size()-1 == currentItem) {
                    tonext.setEnabled(false);
                    Log.d(TAG, "updateButtonStatus: tonext.setEnabled(false);");

                    tolast.setEnabled(true);
                    Log.d(TAG, "updateButtonStatus:   tolast.setEnabled(true);");
                } else if (currentItem > 0 && currentItem < matchBean.getData().size()) {
                    Log.d(TAG, "updateButtonStatus:currentItem >= 0 && currentItem < matchBean.getData().size() ");
                    tonext.setEnabled(true);
                    tolast.setEnabled(true);
                } else if (currentItem == 0) {
                    Log.d(TAG, "updateButtonStatus: currentItem == 0");
                    tonext.setEnabled(true);
                    tolast.setEnabled(false);
                }
            }
        }
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

    @OnClick({R.id.test_redbag, R.id.go, R.id.tolast, R.id.tonext})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.test_redbag:
                String trim = etEstimate.getText().toString().trim();
                Double amount = Double.valueOf(trim);
                if (!(amount > 0)) {
                    BaseApplication.showToast("请输入正确的发票金额");
                    return;
                } else {
                    Api.doMatchDemand(label, amount, "1,2,3", "天津市", new Api.BaseViewCallback<MacherBeanToken>() {
                        @Override
                        public void setData(MacherBeanToken matchBean) {
                            llFilterKey.setVisibility(View.GONE);
                            llConfirmCaution.setVisibility(View.VISIBLE);
                            estimatePlease.setVisibility(View.GONE);
                            llBonus.setVisibility(View.VISIBLE);
                            EstimateActivity.this.matchBean = matchBean;
                            bonus.setText(matchBean.getData().get(0).getBonus()+"");
                            setUpData(matchBean);
                        }
                    });
                }
                break;
            case R.id.go:
                MacherBeanToken.DataBean dataBean = matchBean.getData().get(currentItem);
                Intent intent = new Intent();
                intent.putExtra("company_info", dataBean.getCompany());
                intent.setClass(this, ConfirmActivity.class);
                startActivity(intent);
                break;
            case R.id.tolast:
                ultraViewpager.setCurrentItem(currentItem - 1, true);
                updateButtonStatus();
                break;
            case R.id.tonext:
                ultraViewpager.scrollNextPage();
                updateButtonStatus();
                break;
        }
    }

    private void setUpData(MacherBeanToken matchBean) {
        Log.d(TAG, "setUpData:  ultraViewpager.setAdapter");
        ultraViewpager.setAdapter(new ExtimatePagerAdapter(getSupportFragmentManager(), matchBean));

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


        for (int i = 0; i <= arrayListKind.size(); i++) {
            String s = arrayListKind.get(i);
            if (i == arrayListKind.size()) {
                param.concat(s);
            } else {
                param.concat(s);
                param.concat(",");
            }

        }
        httpParams.put("varieties", param);
        filterKey.append(area);
//        filterKey.append("\u3000");
        httpParams.put("city", area);

        llFilterKey.setVisibility(View.VISIBLE);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        currentItem = position;
        updateButtonStatus();
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
