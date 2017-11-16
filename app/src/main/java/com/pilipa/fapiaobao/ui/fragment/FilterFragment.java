package com.pilipa.fapiaobao.ui.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Looper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.example.mylibrary.utils.TLog;

import com.lljjcoder.Interface.OnCityItemClickListener;
import com.lljjcoder.bean.CityBean;
import com.lljjcoder.bean.DistrictBean;
import com.lljjcoder.bean.ProvinceBean;
import com.lljjcoder.citywheel.CityConfig;
import com.lljjcoder.citywheel.CityPickerView;
import com.pilipa.fapiaobao.R;
import com.pilipa.fapiaobao.base.BaseApplication;
import com.pilipa.fapiaobao.base.BaseFragment;
import com.pilipa.fapiaobao.ui.DemandsPublishActivity;
import com.pilipa.fapiaobao.ui.EstimateActivity;
import com.pilipa.fapiaobao.ui.model.StaticDataCreator;
import com.pilipa.fapiaobao.ui.widget.LabelsView;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by edz on 2017/10/23.
 */

public class FilterFragment extends BaseFragment {

    @Bind(R.id.title)
    TextView title;
    @Bind(R.id.estimate_back)
    ImageView estimateBack;
    @Bind(R.id.confirm)
    TextView confirm;
    @Bind(R.id.labels_receipt_kind)
    LabelsView labelsReceiptKind;
    @Bind(R.id.labels_area)
    LabelsView labelsArea;
    @Bind(R.id.others_province)
    RelativeLayout othersProvince;
    private CityPickerView cityPicker;
    private EstimateActivity estimateActivity;
    private ArrayList<String> arrayListSelectedReceiptKind;
    private String area;
    //声明AMapLocationClient类对象
    //声明定位回调监听器
    //声明AMapLocationClientOption对象
    private ArrayList<String> arrayListReceiptKind = new ArrayList<>();
    private ArrayList<String> arrayListReceiptArea = new ArrayList<>();
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
                }
            }
        }


    };


    private void setUpAddress(AMapLocation aMapLocation) {
        arrayListReceiptArea.add(aMapLocation.getProvince());
        labelsArea.setLabels(arrayListReceiptArea);
        labelsArea.setSelects(0);
    }

    public static FilterFragment newInstance(Bundle bundle) {
        FilterFragment filterFragment = new FilterFragment();
        filterFragment.setArguments(bundle);
        return filterFragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_filter;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @Override
    protected void initWidget(View root) {
        super.initWidget(root);
        estimateActivity = (EstimateActivity) getActivity();
        new Thread() {
            @Override
            public void run() {
                Looper.prepare();
                initCityPicker();
            }
        }.start();
        initLabels();
        initAMap();

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

    private void initLabels() {
        arrayListSelectedReceiptKind = new ArrayList<>();
        arrayListReceiptKind.addAll(StaticDataCreator.initReceiptKindData(mContext));
        labelsReceiptKind.setLabels(arrayListReceiptKind);
        labelsReceiptKind.setSelects(0);


        labelsReceiptKind.setOnLabelSelectChangeListener(new LabelsView.OnLabelSelectChangeListener() {
            @Override
            public void onLabelSelectChange(View label, String labelText, boolean isSelect, int position) {
                if (isSelect) {
                    if ("不限".equals(labelText)) {
                        arrayListSelectedReceiptKind.clear();
                        arrayListSelectedReceiptKind.add("不限");
                    } else {
                        if (!arrayListSelectedReceiptKind.contains("不限")) {
                            arrayListSelectedReceiptKind.add(labelText);
                        }
                    }
                } else {
                    if ("不限".equals(labelText)) {
                        arrayListSelectedReceiptKind.remove("不限");
                        ArrayList<Integer> selectLabels = labelsReceiptKind.getSelectLabels();
                        for (Integer selectLabel : selectLabels) {
                            String s = labelsReceiptKind.getLabels().get(selectLabel);
                            if (!arrayListSelectedReceiptKind.contains(s)) {
                                arrayListSelectedReceiptKind.add(s);
                            }
                        }
                    }

                }
            }
        });
    }

    private void initCityPicker() {
        CityConfig cityConfig = new CityConfig.Builder(getActivity())
                .title("选择地区")
                .titleBackgroundColor("#E9E9E9")
                .textSize(15)
                .titleTextColor("#585858")
                .textColor("0xFF585858")
                .confirTextColor("#0000FF")
                .cancelTextColor("#000000")
                .province("直辖市")
                .city("天津")
                .district("红桥区")
                .visibleItemsCount(5)
                .provinceCyclic(false)
                .cityCyclic(false)
                .districtCyclic(false)
                .itemPadding(5)
                .setCityInfoType(CityConfig.CityInfoType.BASE)
                .setCityWheelType(CityConfig.WheelType.PRO_CITY_DIS)
                .build();

        cityPicker = new CityPickerView(cityConfig);

        cityPicker.setOnCityItemClickListener(new OnCityItemClickListener() {
            @Override
            public void onSelected(ProvinceBean province, CityBean city, DistrictBean district) {
                super.onSelected(province, city, district);
                //返回结果
                //ProvinceBean 省份信息
                //CityBean     城市信息
                //DistrictBean 区县信息
                if (!arrayListReceiptArea.contains(city.getName())) {
                    arrayListReceiptArea.add(city.getName());
                }

                labelsArea.setLabels(arrayListReceiptArea);
                cityPicker.hide();
            }

            @Override
            public void onCancel() {
                super.onCancel();
                cityPicker.hide();
            }
        });




    }

    @OnClick({R.id.estimate_back, R.id.confirm})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.estimate_back:
                estimateActivity.closeDrawer();
                break;
            case R.id.confirm:
                if (labelsArea != null && labelsArea.getSelectLabels().size() > 0) {
                    area = labelsArea.getLabels().get(labelsArea.getSelectLabels().get(0));
                } else {
                    area = "";
                }

                estimateActivity.setFilterKeys(arrayListSelectedReceiptKind, area);
                estimateActivity.closeDrawer();
                break;
        }
    }

    @OnClick(R.id.others_province)
    public void onViewClicked() {
        cityPicker.show();
    }
}
