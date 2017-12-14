package com.pilipa.fapiaobao.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.blog.www.guideview.Guide;
import com.blog.www.guideview.GuideBuilder;
import com.example.mylibrary.utils.TLog;
import com.jakewharton.rxbinding2.widget.RxTextView;
import com.jakewharton.rxbinding2.widget.TextViewAfterTextChangeEvent;
import com.lljjcoder.Interface.OnCityItemClickListener;
import com.lljjcoder.bean.CityBean;
import com.lljjcoder.bean.DistrictBean;
import com.lljjcoder.bean.ProvinceBean;
import com.lljjcoder.citywheel.CityConfig;
import com.lljjcoder.citywheel.CityPickerView;
import com.pilipa.fapiaobao.AppOperator;
import com.pilipa.fapiaobao.Constants.Config;
import com.pilipa.fapiaobao.R;
import com.pilipa.fapiaobao.account.AccountHelper;
import com.pilipa.fapiaobao.adapter.ExtimatePagerAdapter;
import com.pilipa.fapiaobao.base.BaseApplication;
import com.pilipa.fapiaobao.base.LocationBaseActivity;
import com.pilipa.fapiaobao.net.Api;
import com.pilipa.fapiaobao.net.bean.invoice.AllInvoiceVariety;
import com.pilipa.fapiaobao.net.bean.invoice.MacherBeanToken;
import com.pilipa.fapiaobao.net.bean.me.NormalBean;
import com.pilipa.fapiaobao.ui.component.SimpleComponent;
import com.pilipa.fapiaobao.ui.fragment.EstimatePagerFragment;
import com.pilipa.fapiaobao.ui.fragment.FinanceFragment;
import com.pilipa.fapiaobao.ui.widget.CashierInputFilter;
import com.pilipa.fapiaobao.ui.widget.LabelsView;
import com.pilipa.fapiaobao.utils.TDevice;
import com.tmall.ultraviewpager.UltraViewPager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * @author edz
 * @date 2017/10/23
 */

public class EstimateActivity extends LocationBaseActivity implements ViewPager.OnPageChangeListener {
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
    private double amount;

    private CityPickerView cityPicker;
    private ArrayList<String> arrayListSelectedReceiptKind;
    private String name;
    private String companyId;
    private String demandId;
    public ExtimatePagerAdapter adapter;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_estimate;
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void initView() {
        String location = BaseApplication.get("location", "定位失败，点击选择地区");
        locating.setText(location);
        locate = location;
        go.setVisibility(View.GONE);
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
        companyId = getIntent().getStringExtra("companyId");
        tvLabel.setText(name);
        httpParams.put("invoiceType", label);
        llConfirmCaution.setVisibility(View.GONE);
        llFilterKey.setVisibility(View.GONE);
        ultraViewpager.setScrollMode(UltraViewPager.ScrollMode.HORIZONTAL);
        ultraViewpager.disableAutoScroll();
        ultraViewpager.disableIndicator();
        ultraViewpager.setOnPageChangeListener(this);
        tonext.setEnabled(true);
        tolast.setEnabled(false);
        delayInitCityPicker();

        initInvoiceTypes();

        InputFilter[] cashierInputFilter ={new CashierInputFilter()};
        etEstimate.setFilters(cashierInputFilter);

        RxTextView
                .afterTextChangeEvents(etEstimate)
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<TextViewAfterTextChangeEvent>() {
                    @Override
                    public void accept(@NonNull TextViewAfterTextChangeEvent textViewAfterTextChangeEvent) throws Exception {
                        Editable s = textViewAfterTextChangeEvent.editable();
                        String temp = s.toString();
                        int posDot = temp.indexOf(".");
                        if (posDot <= 0) return;
                        if (temp.length() - posDot - 1 > 2) {
                            s.delete(posDot + 3, posDot + 4);
                        }
                    }
                });


        updateInvoiceType();
    }

    private void delayInitCityPicker() {

        Observable.create(new ObservableOnSubscribe<Object>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<Object> e) throws Exception {
                initCityPicker();
                e.onComplete();
            }
        }).subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe();
    }

    private void updateInvoiceType() {

        Observable.timer(500, TimeUnit.MILLISECONDS)
                .take(1)
                .subscribeOn(Schedulers.from(AppOperator.getExecutor()))
                .observeOn(Schedulers.from(AppOperator.getExecutor()))
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(@NonNull Long aLong) throws Exception {

                    }
                });

        Api.updateInvoiceType(AccountHelper.getToken(), label, new Api.BaseViewCallback<String>() {
            @Override
            public void setData(String s) {
                Log.d(TAG, "setData: s" + s);
            }
        });
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
                } else {
                    type = 0;
                }
            }
        });
    }

    public void updateButtonStatus() {
        if (matchBean != null) {
            if (matchBean.getData() != null) {
                if (matchBean.getData().size() == 1) {
                    TLog.log("matchBean.getData().size() == 1" + matchBean.getData().size());
                    tonext.setEnabled(false);
                    tolast.setEnabled(false);
                } else if (matchBean.getData().size() - 1 == currentItem) {
                    TLog.log("matchBean.getData().size() - 1 == currentItem" + matchBean.getData().size());
                    tonext.setEnabled(false);
                    tolast.setEnabled(true);
                } else if (currentItem > 0 && currentItem < matchBean.getData().size()) {
                    TLog.log("currentItem > 0 && currentItem < matchBean.getData().size()" + matchBean.getData().size());
                    tonext.setEnabled(true);
                    tolast.setEnabled(true);
                } else if (currentItem == 0) {
                    TLog.log("currentItem == 0" + matchBean.getData().size());
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
        boolean isShow = BaseApplication.get(Config.IS_SHOW_ESTIMATE, false);
        if(!isShow){
            etEstimate.post(new Runnable() {
                @Override
                public void run() {
                    showGuideView(etEstimate);
                }
            });
        }
    }


    @OnClick({R.id.reset_filter_top, R.id.test_redbag,R.id.locating, R.id.go, R.id.tolast, R.id.tonext, R.id.filter, R.id.other_demand, R.id.select_other_area, R.id.reset_filter})
    public void onViewClicked(View view) {
       final Intent intent = new Intent();
        switch (view.getId()) {
            case R.id.test_redbag:
                estimate();
                break;
            case R.id.go:
                go.setEnabled(false);
                Api.confirmDemand(AccountHelper.getToken(),demandId, new Api.BaseRawResponse<NormalBean>() {
                    @Override
                    public void onStart() {

                    }

                    @Override
                    public void onFinish() {

                    }

                    @Override
                    public void onError() {

                    }

                    @Override
                    public void onTokenInvalid() {
                        login();
                    }

                    @Override
                    public void setData(NormalBean normalBean) {
                        go.setEnabled(true);
                        if (normalBean.getStatus() == 200) {
                            MacherBeanToken.DataBean dataBean = matchBean.getData().get(currentItem);
                            intent.putExtra("type", type);
                            intent.putExtra("demandsId", dataBean.getDemandId());
                            intent.putExtra("amount", amount);
                            intent.putExtra("bonus", dataBean.getBonus());
                            intent.putExtra("company_info", dataBean.getCompany());
                            intent.putExtra("company_id", dataBean.getCompany().getId());
                            intent.putExtra(FinanceFragment.EXTRA_DATA_LABEL, label);
                            if (type == 3) {
                                intent.setClass(EstimateActivity.this, UploadReceiptActivity.class);
                            } else {
                                intent.setClass(EstimateActivity.this, ConfirmActivity.class);
                            }
                            startActivity(intent);
                        } else if (normalBean.getStatus() == 401) {
                            BaseApplication.showToast(normalBean.getMsg());
                        }
                    }
                });
                break;
            case R.id.tolast:
                ultraViewpager.setCurrentItem(currentItem - 1, true);
                bonus.setText(String.valueOf(matchBean.getData().get(currentItem).getBonus()));
                updateButtonStatus();
                break;
            case R.id.tonext:
                ultraViewpager.scrollNextPage();
                bonus.setText(String.valueOf(matchBean.getData().get(currentItem).getBonus()));
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
                else {
                    try {
                        cityPicker.show();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                break;
            case R.id.reset_filter:
            case R.id.reset_filter_top:
                intent.setClass(this, FilterActivity.class);
                startActivityForResult(intent, REQUEST_CODE_ESTIMATE);
                break;
            case R.id.locating:
                if (cityPicker.isShow()) cityPicker.hide();
                else {
                    try {
                        cityPicker.show();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                break;
        }
    }

    private void estimate() {
        if ("定位失败，点击选择地区".equals(locating.getText().toString())) {
            BaseApplication.showToast("定位异常，请开启定位功能或手动选择");
            return;
        }

        if (type == 0) {
            BaseApplication.showToast("请选择发票类型");
            return;
        }
        filterConditionTop.setText(locate + "\u3000" + arrayListSelectedReceiptKind.get(type - 1));
        filterConditionBottom.setText(locate + "\u3000" + arrayListSelectedReceiptKind.get(type - 1)+"\u3000"+name);


        if (TextUtils.isEmpty(etEstimate.getText())) {
            BaseApplication.showToast("请输入正确的发票金额");
            return;
        }
        String trim = etEstimate.getText().toString().trim();
        TLog.log("trim"+trim);
        amount = Double.valueOf(trim);
        TLog.log("amount"+amount);
        if (!(amount > 0) && amount == 0) {
            BaseApplication.showToast("请输入正确的发票金额");
        } else {
            TLog.log(" Api.doMatchDemand(label, amount"+amount);

            Api.doMatchDemand(label, amount, String.valueOf(type), locate,companyId,new Api.BaseViewCallbackWithOnStart<MacherBeanToken>() {

                @Override
                public void onStart() {
                    showProgressDialog();
                }

                @Override
                public void onFinish() {
                    hideProgressDialog();
                }

                @Override
                public void onError() {
                    hideProgressDialog();
                }

                @Override
                public void setData(MacherBeanToken matchBean) {
                    TLog.log(matchBean.getStatus() + "");
                    if (matchBean.getStatus() == 200) {
                        go.setVisibility(View.VISIBLE);
                        llhasRedbag.setVisibility(View.VISIBLE);
                        llnoredbag.setVisibility(View.GONE);
                        llEstimateCaution.setVisibility(View.GONE);
                        llFilterConditionTop.setVisibility(View.VISIBLE);
                        llFilterTypesLocation.setVisibility(View.GONE);
                        llFilterKey.setVisibility(View.GONE);
                        llConfirmCaution.setVisibility(View.VISIBLE);
                        estimatePlease.setVisibility(View.GONE);
                        llBonus.setVisibility(View.VISIBLE);
                        EstimateActivity.this.matchBean = matchBean;
                        tonext.setEnabled(matchBean.getData().size() != 1);
                        bonus.setText(String.valueOf(matchBean.getData().get(0).getBonus()));
                        demandId = matchBean.getData().get(0).getDemandId();
                        setUpData(matchBean);

                    } else if (matchBean.getStatus() == 400) {
                        go.setVisibility(View.GONE);
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
        getSupportFragmentManager().popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
        List<EstimatePagerFragment> list = new ArrayList<>();
        List<MacherBeanToken.DataBean> data = matchBean.getData();
        for (MacherBeanToken.DataBean dataBean : data) {
            Bundle bundle = new Bundle();
            bundle.putParcelable("estimate_item", dataBean);
            TLog.log("EstimatePagerFragment.newInstance(bundle)");
            list.add(EstimatePagerFragment.newInstance(bundle));
        }
        if (adapter != null) {
            ultraViewpager.getViewPager().setCurrentItem(0, true);
            ultraViewpager.setCurrentItem(0, true);
            currentItem = 0;
        }

        ultraViewpager.setOffscreenPageLimit(data.size() - 1);
//        ultraViewpager.setAdapter(new ExtimatePagerAdapter(getSupportFragmentManager(), matchBean));
        adapter = new ExtimatePagerAdapter(getSupportFragmentManager(), list);
        ultraViewpager.setAdapter(adapter);

        updateButtonStatus();
    }



    public void closeDrawer() {
        if (dl != null) {
            if (dl.isDrawerOpen(Gravity.END)) {
                dl.closeDrawer(Gravity.END);
            }
        }
    }

    public void setFilterKeys(ArrayList<String> arrayListKind, String area) {
        String param = "";


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
        bonus.setText(String.valueOf(matchBean.getData().get(currentItem).getBonus()));
        demandId = matchBean.getData().get(currentItem).getDemandId();
        TLog.d(TAG,"onPageSelected demandId"+demandId);

        updateButtonStatus();
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @OnClick(R.id.estimate_back)
    public void onViewClicked() {
        finish();
    }




    private void initCityPicker() {
        CityConfig cityConfig = new CityConfig.Builder(EstimateActivity.this)
                .title("选择地区")
                .textSize(15)
                .confirTextColor("#000000")
                .cancelTextColor("#000000")
                .province("直辖市")
                .city("天津")
                .district("和平区")
                .visibleItemsCount(5)
                .provinceCyclic(false)
                .cityCyclic(false)
                .districtCyclic(false)
                .itemPadding(5)
                .setCityInfoType(CityConfig.CityInfoType.BASE)
                .setCityWheelType(CityConfig.WheelType.PRO_CITY)
                .build();


        cityPicker = new CityPickerView(cityConfig);

        cityPicker.setOnCityItemClickListener(new OnCityItemClickListener() {

            @Override
            public void onSelected(ProvinceBean province) {
                super.onSelected(province);
            }

            @Override
            public void onSelected(ProvinceBean province, CityBean city) {
                super.onSelected(province, city);
                TLog.log("public void onSelectedpublic void onSelectedpublic void onSelectedpublic void onSelected");
                //返回结果
                //ProvinceBean 省份信息
                //CityBean     城市信息
                //DistrictBean 区县信息
                locating.setText(city.getName()+"市");
                locate = city.getName()+"市";
                cityPicker.hide();
            }

            @Override
            public void onSelected(ProvinceBean province, CityBean city, DistrictBean district) {
                super.onSelected(province, city, district);

            }

            @Override
            public void onCancel() {
                cityPicker.hide();
            }
        });

    }

    public void showGuideView(View targetView) {
        GuideBuilder builder = new GuideBuilder();
        builder.setTargetView(targetView)
                .setAlpha(150)
                .setHighTargetCorner(0)
                .setHighTargetPadding(0)
                .setOverlayTarget(false)
                .setOutsideTouchable(false);
        builder.setOnVisibilityChangedListener(new GuideBuilder.OnVisibilityChangedListener() {
            @Override
            public void onShown() {
            }

            @Override
            public void onDismiss() {
                showGuideView2(testRedbag);
            }
        });
        builder.addComponent(new SimpleComponent(){
            @Override
            public View getView(LayoutInflater inflater) {
                ImageView imageView = new ImageView(inflater.getContext());
                imageView.setImageResource(R.mipmap.estimate_002);
                return imageView;
            }

            @Override
            public int getXOffset() {
                return -(int) TDevice.dp2px(-25);
            }

            @Override
            public int getYOffset() {
                return super.getYOffset();
            }
        });
        Guide guide = builder.createGuide();
        guide.setShouldCheckLocInWindow(false);
        guide.show(this);
    }
    public void showGuideView2(View targetView) {
        GuideBuilder builder = new GuideBuilder();
        builder.setTargetView(targetView)
                .setAlpha(150)
                .setHighTargetCorner(100)
                .setHighTargetPadding(10)
                .setOverlayTarget(false)
                .setOutsideTouchable(false);
        builder.setOnVisibilityChangedListener(new GuideBuilder.OnVisibilityChangedListener() {
            @Override
            public void onShown() {
            }

            @Override
            public void onDismiss() {
                BaseApplication.set(Config.IS_SHOW_ESTIMATE, true);
            }
        });
        builder.addComponent(new SimpleComponent(){
            @Override
            public View getView(LayoutInflater inflater) {
                ImageView imageView = new ImageView(inflater.getContext());
                imageView.setImageResource(R.mipmap.estimate_001);
                return imageView;
            }

            @Override
            public int getYOffset() {
                return 10;
            }
        });
        Guide guide = builder.createGuide();
        guide.setShouldCheckLocInWindow(false);
        guide.show(this);
    }
}
