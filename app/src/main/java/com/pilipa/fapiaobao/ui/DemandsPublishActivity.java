package com.pilipa.fapiaobao.ui;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v7.widget.ActionBarOverlayLayout;
import android.support.v7.widget.AppCompatSpinner;
import android.support.v7.widget.SwitchCompat;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.mylibrary.utils.TimeUtils;
import com.lljjcoder.city_20170724.CityPickerView;
import com.lljjcoder.city_20170724.bean.CityBean;
import com.lljjcoder.city_20170724.bean.DistrictBean;
import com.lljjcoder.city_20170724.bean.ProvinceBean;
import com.pilipa.fapiaobao.R;
import com.pilipa.fapiaobao.adapter.PublishSpinnerAdapter;
import com.pilipa.fapiaobao.base.BaseActivity;
import com.pilipa.fapiaobao.base.BaseApplication;
import com.pilipa.fapiaobao.net.Api;
import com.pilipa.fapiaobao.net.bean.publish.ExpressCompanyBean;
import com.pilipa.fapiaobao.ui.dialog.TimePickerDialog;
import com.pilipa.fapiaobao.ui.widget.LabelsView;
import com.pilipa.fapiaobao.utils.TDevice;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by edz on 2017/10/26.
 */

public class DemandsPublishActivity extends BaseActivity implements CompoundButton.OnCheckedChangeListener, AdapterView.OnItemSelectedListener {

    private static final String TAG  = "DemandsPublishActivity";


    @Bind(R.id.paper_elec_receipt)
    TextView paperElecReceipt;
    @Bind(R.id.paper_special_receipt)
    TextView paperSpecialReceipt;
    @Bind(R.id.paper_normal_receipt)
    TextView paperNormalReceipt;
    @Bind(R.id.iv_dots_more_company)
    ImageView ivDotsMoreCompany;
    @Bind(R.id.et_publish_company_name)
    EditText etPublishCompanyName;
    @Bind(R.id.et_publish_tex_number)
    EditText etPublishTexNumber;
    @Bind(R.id.et_publish_address)
    EditText etPublishAddress;
    @Bind(R.id.tv_publish_address_must_fill)
    TextView tvPublishAddressMustFill;
    @Bind(R.id.et_publish_phone_number)
    EditText etPublishPhoneNumber;
    @Bind(R.id.tv_publish_phone_number_must_fill)
    TextView tvPublishPhoneNumberMustFill;
    @Bind(R.id.et_publish_bank)
    EditText etPublishBank;
    @Bind(R.id.tv_et_publish_bank_must_fill)
    TextView tvEtPublishBankMustFill;
    @Bind(R.id.et_publish_bank_account)
    EditText etPublishBankAccount;
    @Bind(R.id.tv_publish_bank_account_must_fill)
    TextView tvPublishBankAccountMustFill;
    @Bind(R.id.tv_more_kind_receipt)
    TextView tvMoreKindReceipt;
    @Bind(R.id.labels_receipt_kind)
    LabelsView labelsReceiptKind;
    @Bind(R.id.et_date)
    TextView etDate;
    @Bind(R.id.iv_select_date)
    ImageView ivSelectDate;
    @Bind(R.id.et_amount)
    EditText etAmount;
    @Bind(R.id.Switch)
    SwitchCompat Switch;
    @Bind(R.id.et_amount_redbag)
    EditText etAmountRedbag;
    @Bind(R.id.ll_amount)
    LinearLayout llAmount;
    @Bind(R.id.et_express_amount_minimum)
    EditText etExpressAmountMinimum;
    @Bind(R.id.rb_cod)
    ImageView rbCod;
    @Bind(R.id.spinner)
    AppCompatSpinner spinner;
    @Bind(R.id.et_reception_name)
    EditText etReceptionName;
    @Bind(R.id.et_reception_number)
    EditText etReceptionNumber;
    @Bind(R.id.tv_area)
    TextView tvArea;
    @Bind(R.id.iv_select_area)
    ImageView ivSelectArea;
    @Bind(R.id.et_area_details)
    EditText etAreaDetails;
    @Bind(R.id.et_publish_cautions)
    EditText etPublishCautions;
    @Bind(R.id.btn_publish_now)
    Button btnPublishNow;
    private boolean paperNormal;
    private boolean elec;
    private boolean paperSpecial;
    private Dialog mCameraDialog;
    private int fourteen_days_miliseconds = 14 * 24 * 60 * 60 * 1000;
    private TimePickerDialog dialog;
    private CityPickerView cityPicker;
    private static Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

        }
    };

    @Override
    protected int getLayoutId() {
        return R.layout.activity_supply_publish;
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void initView() {
        Intent intent = getIntent();

        paperNormal = intent.getBooleanExtra(PubActivity.RECEIPTPAPERNORMAL_DATA, false);
        paperNormalReceipt.setVisibility(paperNormal ? View.VISIBLE : View.GONE);
        elec = intent.getBooleanExtra(PubActivity.RECEIPTELEC_DATA, false);
        paperElecReceipt.setVisibility(elec ? View.VISIBLE : View.GONE);
        paperSpecial = intent.getBooleanExtra(PubActivity.RECEIPTPAPERSPECIAL_DATA, false);
        paperSpecialReceipt.setVisibility(paperSpecial ? View.VISIBLE : View.GONE);
        findViewById(R.id.publish_receive_receipt_info).setVisibility(paperNormal || intent.getBooleanExtra(PubActivity.RECEIPTPAPERSPECIAL_DATA, false) ? View.VISIBLE : View.GONE);
        tvEtPublishBankMustFill.setText(paperSpecial ? "必填" : "选填");
        tvPublishAddressMustFill.setText(paperSpecial ? "必填" : "选填");
        tvPublishBankAccountMustFill.setText(paperSpecial ? "必填" : "选填");
        tvPublishPhoneNumberMustFill.setText(paperSpecial ? "必填" : "选填");


        etDate.setText(TimeUtils.millis2String(System.currentTimeMillis()+fourteen_days_miliseconds,TimeUtils.FORMAT));
        dialog = new TimePickerDialog(this);
        Switch.setChecked(true);
        Switch.setOnCheckedChangeListener(this);

        rbCod.setSelected(true);

        spinner.setOnItemSelectedListener(this);



        new Thread(){
            @Override
            public void run() {
                Looper.prepare();
                initCityPicker();
            }
        }.start();
    }

    @Override
    public void initData() {
        Api.findAllLogisticsCompany(new Api.BaseViewCallback<ExpressCompanyBean>() {
            @Override
            public void setData(ExpressCompanyBean expressCompanyBean) {
                Log.d(TAG, "setData: initDatainitDatainitDatainitDatainitDatainitDatainitDatainitDatainitDatainitDatainitData");
                spinner.setAdapter(new PublishSpinnerAdapter(expressCompanyBean));
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }



    @OnClick({R.id.title, R.id.upload_back, R.id.upload_scan, R.id.paper_elec_receipt
            , R.id.paper_special_receipt, R.id.paper_normal_receipt, R.id.iv_dots_more_company
            , R.id.tv_publish_address_must_fill, R.id.tv_publish_phone_number_must_fill
            , R.id.tv_et_publish_bank_must_fill, R.id.tv_publish_bank_account_must_fill
            , R.id.tv_more_kind_receipt, R.id.labels_receipt_kind
            , R.id.iv_select_date, R.id.et_amount, R.id.Switch
            , R.id.ll_amount, R.id.rb_cod
            , R.id.tv_area, R.id.iv_select_area
            , R.id.btn_publish_now})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.title:
                break;
            case R.id.upload_back:
                break;
            case R.id.upload_scan:
                break;
            case R.id.paper_elec_receipt:
                break;
            case R.id.paper_special_receipt:
                break;
            case R.id.paper_normal_receipt:
                break;
            case R.id.iv_dots_more_company:
                //TODO 打开公司名列表
                setDialog();
                break;
            case R.id.et_publish_company_name:
                break;
            case R.id.et_publish_tex_number:
                break;
            case R.id.et_publish_address:
                break;
            case R.id.tv_publish_address_must_fill:
                break;
            case R.id.et_publish_phone_number:
                break;
            case R.id.tv_publish_phone_number_must_fill:
                break;
            case R.id.et_publish_bank:
                break;
            case R.id.tv_et_publish_bank_must_fill:
                break;
            case R.id.et_publish_bank_account:
                break;
            case R.id.tv_publish_bank_account_must_fill:
                break;
            case R.id.tv_more_kind_receipt:
                //TODO 更多类型
                break;
            case R.id.labels_receipt_kind:
                break;
            case R.id.et_date:
                break;
            case R.id.iv_select_date:
                //TODO
                dialog.showDatePickerDialog(new TimePickerDialog.TimePickerDialogInterface() {
                    @Override
                    public void positiveListener() {
                        etDate.setText(dialog.getYear()+"-"+dialog.getMonth()+"-"+dialog.getDay());
                    }

                    @Override
                    public void negativeListener() {

                    }
                });
                break;
            case R.id.et_amount:
                break;
            case R.id.Switch:

                break;
            case R.id.et_amount_redbag:
                break;
            case R.id.ll_amount:
                break;
            case R.id.et_express_amount_minimum:
                break;
            case R.id.rb_cod:
                rbCod.setSelected(!rbCod.isSelected());
                break;
            case R.id.et_reception_name:
                break;
            case R.id.et_reception_number:
                break;
            case R.id.tv_area:
                break;
            case R.id.iv_select_area:
                cityPicker.show();
                break;
            case R.id.et_area_details:
                break;
            case R.id.et_publish_cautions:
                break;
            case R.id.btn_publish_now:
                break;
        }
    }

    private void setDialog() {
        mCameraDialog = new Dialog(this, R.style.BottomDialog);
        LinearLayout root = (LinearLayout) LayoutInflater.from(this).inflate(
                R.layout.company_dialog_bottom, null);
        //初始化视图
        LinearLayout ll_container = (LinearLayout) root.findViewById(R.id.ll_container);
        for (int i = 0; i <= 5; i++) {
            final TextView textView = new TextView(this);
            textView.setText(i + "");
            textView.setLayoutParams(new ActionBarOverlayLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            textView.setGravity(Gravity.CENTER_HORIZONTAL);
            textView.setTextSize(TDevice.spToPx(getResources(), 12));
            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    BaseApplication.showToast(textView.getText() + "");
                }
            });
            ll_container.addView(textView);
        }
        root.findViewById(R.id.btn_cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCameraDialog.dismiss();
            }
        });
        mCameraDialog.setContentView(root);
        Window dialogWindow = mCameraDialog.getWindow();
        dialogWindow.setGravity(Gravity.BOTTOM);
//        dialogWindow.setWindowAnimations(R.style.dialogstyle); // 添加动画
        WindowManager.LayoutParams lp = dialogWindow.getAttributes(); // 获取对话框当前的参数值
        lp.x = 0; // 新位置X坐标
        lp.y = 0; // 新位置Y坐标
        lp.width = (int) getResources().getDisplayMetrics().widthPixels; // 宽度
        root.measure(0, 0);
        lp.height = root.getMeasuredHeight();

        lp.alpha = 9f; // 透明度
        dialogWindow.setAttributes(lp);
        mCameraDialog.show();
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        llAmount.setVisibility(isChecked?View.VISIBLE:View.GONE);

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        TextView textView = (TextView) view;
        BaseApplication.showToast(textView.getText().toString().trim());
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }



    private void initCityPicker() {
        cityPicker = new CityPickerView.Builder(this)
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
                .onlyShowProvinceAndCity(false)
                .build();

        cityPicker.setOnCityItemClickListener(new CityPickerView.OnCityItemClickListener() {
            @Override
            public void onSelected(ProvinceBean province, CityBean city, DistrictBean district) {
                //返回结果
                //ProvinceBean 省份信息
                //CityBean     城市信息
                //DistrictBean 区县信息
                tvArea.setText(province.getName()+"-"+city.getName()+"-"+district.getName());

                cityPicker.hide();
            }

            @Override
            public void onCancel() {
                cityPicker.hide();
            }
        });

    }
}
