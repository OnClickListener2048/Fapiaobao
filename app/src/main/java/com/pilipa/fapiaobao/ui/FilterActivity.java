package com.pilipa.fapiaobao.ui;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
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
import com.pilipa.fapiaobao.base.BaseActivity;
import com.pilipa.fapiaobao.base.BaseApplication;
import com.pilipa.fapiaobao.ui.model.StaticDataCreator;
import com.pilipa.fapiaobao.ui.widget.LabelsView;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by edz on 2017/11/5.
 */

public class FilterActivity extends BaseActivity implements LabelsView.OnLabelSelectChangeListener {

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
    String locate = "";
    int type = 0;
    private ArrayList<String> arrayListSelectedReceiptKind;
    private ArrayList<String> arrayListReceiptArea = new ArrayList<>();
    private ArrayList<String> arrayListReceiptKind = new ArrayList<>();
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
    private CityPickerView cityPicker;

    private void setUpAddress(AMapLocation aMapLocation) {
        arrayListReceiptArea.add(aMapLocation.getProvince());
        labelsArea.setLabels(arrayListReceiptArea);
        labelsArea.setSelects(0);
        locate = aMapLocation.getCity();

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_filter;
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void initView() {
        initAMap();
        initLabels();
        initCityPicker();

        labelsArea.setOnLabelSelectChangeListener(this);
        labelsReceiptKind.setOnLabelSelectChangeListener(new LabelsView.OnLabelSelectChangeListener() {
            @Override
            public void onLabelSelectChange(View label, String labelText, boolean isSelect, int position) {
                if (isSelect) {
                    type = position + 1;
                }
            }
        });
    }

    private void initLabels() {
        arrayListSelectedReceiptKind = new ArrayList<>();
        arrayListReceiptKind.addAll(StaticDataCreator.initReceiptKindData(this));
        labelsReceiptKind.setLabels(arrayListReceiptKind);

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

    @OnClick({R.id.estimate_back, R.id.confirm, R.id.others_province})
    public void onViewClicked(View view) {
        Intent intent = new Intent();
        switch (view.getId()) {
            case R.id.estimate_back:
                setResult(RESULT_CANCELED);
                finish();
                break;
            case R.id.confirm:
                intent.putExtra("locate", locate);
                intent.putExtra("type", type);
                setResult(RESULT_OK,intent);
                finish();
                break;
            case R.id.others_province:
                cityPicker.show();
                break;
        }
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
                if (!arrayListReceiptArea.contains(city.getName())) {
                    arrayListReceiptArea.add(city.getName());
                }

                labelsArea.setLabels(arrayListReceiptArea);
                labelsArea.setSelects(arrayListReceiptArea.size()-1);
                cityPicker.hide();
            }

            @Override
            public void onCancel() {
                cityPicker.hide();
            }
        });
    }

    @Override
    public void onLabelSelectChange(View label, String labelText, boolean isSelect, int position) {
        if (isSelect) {
            locate = labelText;
        }
    }
}
