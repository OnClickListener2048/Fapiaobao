package com.pilipa.fapiaobao.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lljjcoder.Interface.OnCityItemClickListener;
import com.lljjcoder.bean.CityBean;
import com.lljjcoder.bean.DistrictBean;
import com.lljjcoder.bean.ProvinceBean;
import com.lljjcoder.citywheel.CityConfig;
import com.lljjcoder.citywheel.CityPickerView;
import com.pilipa.fapiaobao.R;
import com.pilipa.fapiaobao.base.BaseApplication;
import com.pilipa.fapiaobao.base.BaseLocationActivity;
import com.pilipa.fapiaobao.ui.model.StaticDataCreator;
import com.pilipa.fapiaobao.ui.widget.LabelsView;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by edz on 2017/11/5.
 */

public class FilterLocationActivity extends BaseLocationActivity {

    @Bind(R.id.title)
    TextView title;
    @Bind(R.id.estimate_back)
    ImageView estimateBack;
    @Bind(R.id.confirm)
    TextView confirm;
    @Bind(R.id.labels_receipt_kind)
    LabelsView labelsReceiptKind;
    @Bind(R.id.others_province)
    RelativeLayout othersProvince;
    String locate = "";
    int type = 0;
    @Bind(R.id.tv_area)
    TextView tvArea;
    private ArrayList<String> arrayListReceiptKind = new ArrayList<>();

    private CityPickerView cityPicker;

    private void setUpAddress() {
        tvArea.setText(BaseApplication.get("location", "定位失败，点击选择地区"));
        locate = BaseApplication.get("location", "定位失败，点击选择地区");
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

        type = getIntent().getIntExtra("type", -1);
        locate = getIntent().getStringExtra("location");
        initLabels();
        initCityPicker(locate);
        labelsReceiptKind.setOnLabelSelectChangeListener(new LabelsView.OnLabelSelectChangeListener() {
            @Override
            public void onLabelSelectChange(View label, String labelText, boolean isSelect, int position) {
                if (isSelect) {
                    type = position + 1;
                } else {
                    type = 0;
                }
            }
        });

        if (type != -1) {
            labelsReceiptKind.setSelects(type - 1);
        }

        tvArea.setText(locate);
    }

    private void initLabels() {
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
                if ("定位失败，点击选择地区".equals(tvArea.getText().toString())) {
                    BaseApplication.showToast("限制开票区域定位异常，请开启定位功能或手动选择开票地区");
                    cityPicker.show();
                    return;
                }
                intent.putExtra("locate", locate);
                if (type == 0) {
                    BaseApplication.showToast("请选择发票种类" +
                            "");
                    return;
                }
                intent.putExtra("type", type);
                setResult(RESULT_OK, intent);
                finish();
                break;
            case R.id.others_province:
                try {
                    cityPicker.show();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
        }
    }


    private void initCityPicker(String locate) {
        CityConfig cityConfig = new CityConfig.Builder(FilterLocationActivity.this)
                .title("选择地区")
                .titleBackgroundColor("#E9E9E9")
                .textSize(15)
                .confirTextColor("#000000")
                .cancelTextColor("#000000")
                .province("天津")
                .city("天津")
                .district("红桥区")
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
            public void onSelected(ProvinceBean province, CityBean city, DistrictBean district) {
                super.onSelected(province, city, district);

            }

            @Override
            public void onSelected(ProvinceBean province, CityBean city) {
                super.onSelected(province, city);
                FilterLocationActivity.this.locate = city.getName() + "市";
                tvArea.setText(city.getName() + "市");
                cityPicker.hide();
            }

            @Override
            public void onCancel() {
                super.onCancel();
                cityPicker.hide();
            }
        });


    }

}
