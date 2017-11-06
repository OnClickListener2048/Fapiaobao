package com.pilipa.fapiaobao.ui;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.text.TextUtils;
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

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.example.mylibrary.utils.TLog;
import com.lljjcoder.city_20170724.CityPickerView;
import com.lljjcoder.city_20170724.bean.CityBean;
import com.lljjcoder.city_20170724.bean.DistrictBean;
import com.lljjcoder.city_20170724.bean.ProvinceBean;
import com.pilipa.fapiaobao.R;
import com.pilipa.fapiaobao.adapter.ExtimatePagerAdapter;
import com.pilipa.fapiaobao.base.BaseActivity;
import com.pilipa.fapiaobao.base.BaseApplication;
import com.pilipa.fapiaobao.net.Api;
import com.pilipa.fapiaobao.net.bean.invoice.AllInvoiceVariety;
import com.pilipa.fapiaobao.net.bean.invoice.MacherBeanToken;
import com.pilipa.fapiaobao.ui.fragment.FinanceFragment;
import com.pilipa.fapiaobao.ui.widget.LabelsView;
import com.pilipa.fapiaobao.utils.TDevice;
import com.tmall.ultraviewpager.UltraViewPager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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
    @Bind(R.id.hasRedbag)
    LinearLayout llhasRedbag;
    @Bind(R.id.other_demand)
    Button otherDemand;
    @Bind(R.id.noredbag)
    LinearLayout llnoredbag;
    @Bind(R.id.labels)
    LabelsView labels;
    @Bind(R.id.locating)
    TextView locating;
    @Bind(R.id.select_other_area)
    TextView selectOtherArea;
    @Bind(R.id.reset_filter)
    Button resetFilter;
    @Bind(R.id.ll_estimate_caution)
    LinearLayout llEstimateCaution;
    @Bind(R.id.filter_condition_top)
    TextView filterConditionTop;
    @Bind(R.id.reset_filter_top)
    TextView resetFilterTop;
    @Bind(R.id.ll_filter_condition_top)
    LinearLayout llFilterConditionTop;
    @Bind(R.id.filter_condition_bottom)
    TextView filterConditionBottom;
    @Bind(R.id.ll_filter_condition_bottom)
    LinearLayout llFilterConditionBottom;
    @Bind(R.id.ll_filter_types_location)
    LinearLayout llFilterTypesLocation;
    String locate = "";
    int type = 0;
    public static final int REQUEST_CODE_ESTIMATE = 500;
    @Bind(R.id.tv_label)
    TextView tvLabel;
    private String label = "";
    private HashMap<String, String> httpParams;
    private int currentItem = 0;
    private MacherBeanToken matchBean;
    private Double amount;
    public AMapLocationClient mLocationClient = null;
    public AMapLocationClientOption mLocationOption = new AMapLocationClientOption();
    public AMapLocationListener mLocationListener = new AMapLocationListener() {


        @Override
        public void onLocationChanged(AMapLocation aMapLocation) {
            if (aMapLocation != null) {
                if (aMapLocation.getErrorCode() == 0) {
                    //可在其中解析amapLocation获取相应内容。
                    TLog.log(aMapLocation.toString());
                    TLog.log(aMapLocation.getCity());
                    TLog.log(aMapLocation.getProvince());
                    setUpAddress(aMapLocation);
                } else {
                    //定位失败时，可通过ErrCode（错误码）信息来确定失败的原因，errInfo是错误信息，详见错误码表。
                    Log.e("AmapError", "location Error, ErrCode:"
                            + aMapLocation.getErrorCode() + ", errInfo:"
                            + aMapLocation.getErrorInfo());
                    if (!TDevice.hasInternet()) {
                        locating.setText("定位失败,请检查网络");
                    }
                }
            }
        }
    };
    private CityPickerView cityPicker;
    private ArrayList<String> arrayListSelectedReceiptKind;
    private String name;

    private void setUpAddress(AMapLocation aMapLocation) {
        locating.setText(aMapLocation.getCity());
        locate = aMapLocation.getCity();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_estimate;
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void initView() {
        llFilterTypesLocation.setVisibility(View.VISIBLE);
        llFilterConditionTop.setVisibility(View.GONE);
        llFilterConditionBottom.setVisibility(View.GONE);
        llEstimateCaution.setVisibility(View.VISIBLE);
        filter.setVisibility(View.GONE);
        llhasRedbag.setVisibility(View.VISIBLE);
        llnoredbag.setVisibility(View.GONE);
        estimatePlease.setVisibility(View.VISIBLE);
        llBonus.setVisibility(View.GONE);
        httpParams = new HashMap<>();
        label = getIntent().getStringExtra(FinanceFragment.EXTRA_DATA_LABEL);
        name = getIntent().getStringExtra(FinanceFragment.EXTRA_DATA_LABEL_NAME);
        tvLabel.setText(name);
        httpParams.put("invoiceType", label);
        llConfirmCaution.setVisibility(View.GONE);
        llFilterKey.setVisibility(View.GONE);
//        new Thread() {
//            @Override
//            public void run() {
//                addFragment(dlContainer.getId(), FilterFragment.newInstance(new Bundle()));
//
//            }
//        }.start();


        ultraViewpager.setScrollMode(UltraViewPager.ScrollMode.HORIZONTAL);
        ultraViewpager.disableAutoScroll();
        ultraViewpager.disableIndicator();
        ultraViewpager.setOnPageChangeListener(this);

        tonext.setEnabled(true);
        tolast.setEnabled(false);
        initAMap();
        initCityPicker();
        initInvoiceTypes();
    }

    private void initInvoiceTypes() {
        arrayListSelectedReceiptKind = new ArrayList<>();
        Api.findAllInvoiceVariety(new Api.BaseViewCallback<AllInvoiceVariety>() {
            @Override
            public void setData(AllInvoiceVariety allInvoiceVariety) {
                if (allInvoiceVariety.getData() != null && allInvoiceVariety.getData().size() > 0) {
                    List<AllInvoiceVariety.DataBean> data = allInvoiceVariety.getData();
                    for (AllInvoiceVariety.DataBean dataBean : data) {
                        arrayListSelectedReceiptKind.add(dataBean.getLabel());

                    }
                    labels.setLabels(arrayListSelectedReceiptKind);
                }
            }
        });

        labels.setOnLabelSelectChangeListener(new LabelsView.OnLabelSelectChangeListener() {
            @Override
            public void onLabelSelectChange(View label, String labelText, boolean isSelect, int position) {
                if (isSelect) {
                    type = position + 1;
                }
            }
        });
    }

    public void updateButtonStatus() {

        if (matchBean != null) {
            if (matchBean.getData() != null) {
                if (matchBean.getData().size() - 1 == currentItem) {
                    tonext.setEnabled(false);
                    tolast.setEnabled(true);
                } else if (currentItem > 0 && currentItem < matchBean.getData().size()) {
                    tonext.setEnabled(true);
                    tolast.setEnabled(true);
                } else if (currentItem == 0) {
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


    @OnClick({R.id.reset_filter_top, R.id.test_redbag, R.id.go, R.id.tolast, R.id.tonext, R.id.filter, R.id.other_demand, R.id.select_other_area, R.id.reset_filter})
    public void onViewClicked(View view) {
        Intent intent = new Intent();
        switch (view.getId()) {
            case R.id.test_redbag:
                estimate();
                break;
            case R.id.go:

                MacherBeanToken.DataBean dataBean = matchBean.getData().get(currentItem);
                intent.putExtra("demandsId", dataBean.getDemandId());
                intent.putExtra("amount", amount);
                intent.putExtra("bonus", dataBean.getBonus());
                intent.putExtra("company_info", dataBean.getCompany());
                intent.setClass(EstimateActivity.this, ConfirmActivity.class);
                startActivity(intent);

//                if (AccountHelper.getToken() != null && AccountHelper.getToken() != "") {
//                    AccountHelper.isTokenValid(new Api.BaseViewCallback<LoginWithInfoBean>() {
//                        @Override
//                        public void setData(LoginWithInfoBean normalBean) {
//                            if (normalBean.getStatus() == 200) {
//
//                                SharedPreferencesHelper.save(EstimateActivity.this, matchBean.getData().get(currentItem));
//
//
//                                Api.createOrder(AccountHelper.getToken(), matchBean.getData().get(currentItem).getDemandId(), label, amount, new Api.BaseViewCallbackWithOnStart<OrderBean>() {
//                                    @Override
//                                    public void onStart() {
//                                        showProgressDialog();
//                                    }
//
//                                    @Override
//                                    public void onFinish() {
//                                        hideProgressDialog();
//                                    }
//
//                                    @Override
//                                    public void onError() {
//                                        hideProgressDialog();
//                                    }
//
//                                    @Override
//                                    public void setData(OrderBean o) {
//                                        if (o.getStatus() == 200) {
//                                            BaseApplication.showToast("创建订单成功");
//                                            SharedPreferencesHelper.save(EstimateActivity.this, o);
//                                            MacherBeanToken.DataBean dataBean = matchBean.getData().get(currentItem);
//                                            Intent intent = new Intent();
//                                            intent.putExtra("amount", amount);
//                                            intent.putExtra("bonus", dataBean.getBonus());
//                                            intent.putExtra("company_info", dataBean.getCompany());
//                                            intent.putExtra("order", o.getData().getOrderId());
//                                            intent.setClass(EstimateActivity.this, ConfirmActivity.class);
//                                            startActivity(intent);
//                                        }
//                                    }
//                                });
//                            } else {
//                                BaseApplication.showToast("token验证失败请重新登陆");
//                                startActivity(new Intent(EstimateActivity.this, LoginActivity.class));
//                                finish();
//                            }
//                        }
//                    });
//                }


                break;
            case R.id.tolast:
                ultraViewpager.setCurrentItem(currentItem - 1, true);
                updateButtonStatus();
                break;
            case R.id.tonext:
                ultraViewpager.scrollNextPage();
                updateButtonStatus();
                break;
            case R.id.filter:
                if (dl.isDrawerOpen(Gravity.END)) {
                    dl.closeDrawer(Gravity.END);
                } else {
                    dl.openDrawer(Gravity.END);
                }
                break;
            case R.id.other_demand:
                finish();
                break;
            case R.id.select_other_area:
                if (cityPicker.isShow()) cityPicker.hide();
                else cityPicker.show();
                break;
            case R.id.reset_filter:
            case R.id.reset_filter_top:
                intent.setClass(this, FilterActivity.class);
                startActivityForResult(intent, REQUEST_CODE_ESTIMATE);
                break;
        }
    }

    private void estimate() {
        if (type == 0) {
            BaseApplication.showToast("请选择发票类型");
            return;
        }
        filterConditionTop.setText(locate + "\u3000" + arrayListSelectedReceiptKind.get(type - 1));
        filterConditionBottom.setText(locate + "\u3000" + arrayListSelectedReceiptKind.get(type - 1));


        if (TextUtils.isEmpty(etEstimate.getText())) {
            BaseApplication.showToast("请输入正确的发票金额");
            return;
        }
        String trim = etEstimate.getText().toString().trim();
        amount = Double.valueOf(trim);
        if (!(amount > 0) && amount == 0) {
            BaseApplication.showToast("请输入正确的发票金额");
            return;
        } else {
            Api.doMatchDemand(label, amount, String.valueOf(type), locate, new Api.BaseViewCallback<MacherBeanToken>() {

                @Override
                public void setData(MacherBeanToken matchBean) {
                    TLog.log(matchBean.getStatus() + "");
                    if (matchBean.getStatus() == 200) {
                        llEstimateCaution.setVisibility(View.GONE);
                        llFilterConditionTop.setVisibility(View.VISIBLE);
                        llFilterTypesLocation.setVisibility(View.GONE);
                        llFilterKey.setVisibility(View.GONE);
                        llConfirmCaution.setVisibility(View.VISIBLE);
                        estimatePlease.setVisibility(View.GONE);
                        llBonus.setVisibility(View.VISIBLE);
                        EstimateActivity.this.matchBean = matchBean;
                        tonext.setEnabled(matchBean.getData().size() != 1);
                        bonus.setText(matchBean.getData().get(0).getBonus() + "");
                        setUpData(matchBean);
                    } else if (matchBean.getStatus() == 400) {
                        llhasRedbag.setVisibility(View.GONE);
                        llnoredbag.setVisibility(View.VISIBLE);
                        filter.setVisibility(View.GONE);
                        llFilterConditionBottom.setVisibility(View.VISIBLE);
                    }
                }
            });
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case REQUEST_CODE_ESTIMATE:
                if (resultCode == RESULT_OK) {
                    locate = data.getStringExtra("locate");
                    type = data.getIntExtra("type", 0);
                    estimate();
                }
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
//        filterKey.setText("");
//        for (String s : arrayListKind) {
//            filterKey.append(s);
//            filterKey.append("\u3000");
//
//        }
        String param = new String();


        for (int i = 0; i < arrayListKind.size(); i++) {
            String s = arrayListKind.get(i);
            if (i == arrayListKind.size()) {
                param.concat(s);
            } else {
                param.concat(s);
                param.concat(",");
            }

        }
        httpParams.put("varieties", param);
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

    @OnClick(R.id.estimate_back)
    public void onViewClicked() {
        finish();
    }

    private void initAMap() {
        //初始化定位
        mLocationClient = new AMapLocationClient(BaseApplication.context());
        //设置定位回调监听
        mLocationClient.setLocationListener(mLocationListener);
        //设置定位模式为AMapLocationMode.Hight_Accuracy，高精度模式。
        mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
        //获取一次定位结果：
        //该方法默认为false。
        mLocationOption.setOnceLocation(true);
        //获取最近3s内精度最高的一次定位结果：
        //设置setOnceLocationLatest(boolean b)接口为true，启动定位时SDK会返回最近3s内精度最高的一次定位结果。如果设置其为true，setOnceLocation(boolean b)接口也会被设置为true，反之不会，默认为false。
        mLocationOption.setOnceLocationLatest(true);
        //设置是否返回地址信息（默认返回地址信息）
        mLocationOption.setNeedAddress(true);
        //单位是毫秒，默认30000毫秒，建议超时时间不要低于8000毫秒。
        mLocationOption.setHttpTimeOut(20000);
        //关闭缓存机制
        mLocationOption.setLocationCacheEnable(false);
        //给定位客户端对象设置定位参数
        mLocationClient.setLocationOption(mLocationOption);
        //启动定位
        mLocationClient.startLocation();
    }


    private void initCityPicker() {
        cityPicker = new CityPickerView.Builder(this)
                .setData(BaseApplication.mProvinceBeanArrayList)
                .textSize(15)
                .title("其他省市选择")
                .backgroundPop(0xa0000000)
                .titleBackgroundColor("#ffffff")
                .titleTextColor("#000000")
                .backgroundPop(0x0000000)
                .confirTextColor("#000000")
                .cancelTextColor("#000000")
                .city("天津市")
                .textColor(Color.parseColor("#000000"))
                .provinceCyclic(false)
                .cityCyclic(false)
                .districtCyclic(false)
                .visibleItemsCount(5)
                .itemPadding(20)
                .onlyShowProvinceAndCity(true)
                .build();

        cityPicker.setOnCityItemClickListener(new CityPickerView.OnCityItemClickListener() {
            @Override
            public void onSelected(ProvinceBean province, CityBean city, DistrictBean district) {
                //返回结果
                //ProvinceBean 省份信息
                //CityBean     城市信息
                //DistrictBean 区县信息
                locating.setText(city.getName());
                locate = city.getName();
                cityPicker.hide();

            }

            @Override
            public void onCancel() {
                cityPicker.hide();
            }
        });

    }


}
