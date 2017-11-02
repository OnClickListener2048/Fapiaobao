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
import android.text.TextUtils;
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

import com.example.mylibrary.utils.TLog;
import com.example.mylibrary.utils.TimeUtils;
import com.google.gson.Gson;
import com.lljjcoder.city_20170724.CityPickerView;
import com.lljjcoder.city_20170724.bean.CityBean;
import com.lljjcoder.city_20170724.bean.DistrictBean;
import com.lljjcoder.city_20170724.bean.ProvinceBean;
import com.pilipa.fapiaobao.R;
import com.pilipa.fapiaobao.account.AccountHelper;
import com.pilipa.fapiaobao.adapter.PublishSpinnerAdapter;
import com.pilipa.fapiaobao.base.BaseActivity;
import com.pilipa.fapiaobao.base.BaseApplication;
import com.pilipa.fapiaobao.net.Api;
import com.pilipa.fapiaobao.net.bean.LoginWithInfoBean;
import com.pilipa.fapiaobao.net.bean.invoice.DefaultInvoiceBean;
import com.pilipa.fapiaobao.net.bean.me.CompaniesBean;
import com.pilipa.fapiaobao.net.bean.publish.BalanceBean;
import com.pilipa.fapiaobao.net.bean.publish.DemandsPublishBean;
import com.pilipa.fapiaobao.net.bean.publish.ExpressCompanyBean;
import com.pilipa.fapiaobao.ui.dialog.TimePickerDialog;
import com.pilipa.fapiaobao.ui.widget.LabelsView;
import com.pilipa.fapiaobao.utils.SharedPreferencesHelper;
import com.pilipa.fapiaobao.utils.TDevice;

import java.util.ArrayList;
import java.util.List;

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
    public CompaniesBean companiesBean;
    private String invoiceVarieties;
    private CompaniesBean.DataBean dataBean;
    private LoginWithInfoBean loginBean;
    private DefaultInvoiceBean bean;
    private DemandsPublishBean.DemandPostageBean demandPostageBean;
    public static final int REQUEST_CODE = 300;

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


        setUpReceiptParams();

        new Thread(){
            @Override
            public void run() {
                Looper.prepare();
                initCityPicker();
            }
        }.start();

        spinner.setOnItemSelectedListener(this);
    }

    private String setUpReceiptParams() {
        if (paperNormal && paperSpecial && elec) {
            invoiceVarieties = "1,2,3";
            return invoiceVarieties;
        } else if (paperSpecial && paperSpecial) {
            invoiceVarieties = "1,2";
            return invoiceVarieties;
        } else if (paperSpecial && elec) {
            invoiceVarieties = "2,3";
            return invoiceVarieties;
        } else if (paperNormal && elec) {
            invoiceVarieties = "1,3";
            return invoiceVarieties;
        } else if (paperNormal) {
            invoiceVarieties = "1";
            return invoiceVarieties;
        } else if (paperSpecial) {
            invoiceVarieties = "2";
            return invoiceVarieties;
        } else if (elec) {
            invoiceVarieties = "3";
            return invoiceVarieties;
        } else {
            return null;
        }
    }

    @Override
    public void initData() {
        demandPostageBean = new DemandsPublishBean.DemandPostageBean();
        Api.findAllLogisticsCompany(new Api.BaseViewCallback<ExpressCompanyBean>() {
            @Override
            public void setData(ExpressCompanyBean expressCompanyBean) {
                Log.d(TAG, "setData: initDatainitDatainitDatainitDatainitDatainitDatainitDatainitDatainitDatainitDatainitData");
                spinner.setAdapter(new PublishSpinnerAdapter(expressCompanyBean));
            }
        });

        setUsusallyReceiptkind();
        requestForCompanies();
    }

    private void setUsusallyReceiptkind() {
        loginBean = SharedPreferencesHelper.loadFormSource(this, LoginWithInfoBean.class);
        if (loginBean != null) {
            Api.<DefaultInvoiceBean>findUserInvoiceType(loginBean.getData().getToken(), new Api.BaseViewCallback<DefaultInvoiceBean>() {
                @Override
                public void setData(DefaultInvoiceBean defaultInvoiceBean) {
                    if (defaultInvoiceBean.getData() != null && defaultInvoiceBean.getData().size() > 0) {
                        bean = defaultInvoiceBean;
                        ArrayList<String> arrayReceipt = new ArrayList<>();
                        for (DefaultInvoiceBean.DataBean dataBean : bean.getData()) {
                            arrayReceipt.add(dataBean.getName());
                        }
                        labelsReceiptKind.setLabels(arrayReceipt);
                    }
                }
            });
        } else {
            Api.<DefaultInvoiceBean>findDefaultInvoiceType(new Api.BaseViewCallback<DefaultInvoiceBean>() {
                @Override
                public void setData(DefaultInvoiceBean allInvoiceType) {
                    if (allInvoiceType.getData() != null && allInvoiceType.getData().size() > 0) {
                        bean = allInvoiceType;
                        ArrayList<String> arrayReceipt = new ArrayList<>();
                        for (DefaultInvoiceBean.DataBean dataBean : bean.getData()) {
                            arrayReceipt.add(dataBean.getName());
                        }
                        labelsReceiptKind.setLabels(arrayReceipt);
                    }
                }
            });
        }
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
                if (companiesBean == null || companiesBean.getStatus() == 400) {
                    BaseApplication.showToast("没有收藏过公司");
                } else {
                    if (mCameraDialog.isShowing()) {
                        mCameraDialog.hide();
                    } else {
                        mCameraDialog.show();
                    }
                }
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
                checkParams();
                Gson gson = new Gson();
                Api.publish(gson.toJson(makeParams()), new Api.BaseViewCallbackWithOnStart<BalanceBean>() {
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
                    public void setData(BalanceBean balanceBean) {
                        Intent intent = new Intent();
                        if (balanceBean.getStatus() == 200) {
                            intent.putExtra("demand", balanceBean.getData().getDemand());
                            intent.setClass(DemandsPublishActivity.this, PubSuccessActivity.class);
                            startActivity(intent);
                        } else if (balanceBean.getStatus() == 888) {
                            BaseApplication.showToast("账户余额不足，请先充值");
                            intent.setClass(DemandsPublishActivity.this, RechargeActivity.class);
                            startActivityForResult(intent,REQUEST_CODE);

                        }
                    }
                });
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case REQUEST_CODE:
                if (requestCode == RESULT_CANCELED) {
                    finish();
                }
        }
    }

    private DemandsPublishBean makeParams() {
        LoginWithInfoBean loginWithInfoBean = SharedPreferencesHelper.loadFormSource(this, LoginWithInfoBean.class);
        DemandsPublishBean bean = new DemandsPublishBean();
        bean.setInvoiceVarieties(setUpReceiptParams());
        if (dataBean!= null) {
            bean.setId(dataBean.getId());
        }
        bean.setToken(loginWithInfoBean.getData().getToken());
        DemandsPublishBean.CompanyBean companyBean = new DemandsPublishBean.CompanyBean();
        CompaniesBean.DataBean dataBean = SharedPreferencesHelper.loadFormSource(this, CompaniesBean.DataBean.class);
        if (dataBean != null) {
            companyBean.setId(dataBean.getId());
        }

        companyBean.setAccount(etPublishBankAccount.getText().toString().trim());
        companyBean.setAddress(etPublishAddress.getText().toString().trim());
        companyBean.setTaxno(etPublishTexNumber.getText().toString().trim());
        companyBean.setName(etPublishCompanyName.getText().toString().trim());
        companyBean.setDepositBank(etPublishBank.getText().toString().trim());
        companyBean.setPhone(etPublishPhoneNumber.getText().toString().trim());
        bean.setCompany(companyBean);

        List<DemandsPublishBean.DemandInvoiceTypeListBean> listBeen = new ArrayList<>();

        for (DefaultInvoiceBean.DataBean defaultBean : this.bean.getData()) {
            DemandsPublishBean.DemandInvoiceTypeListBean demandInvoiceTypeListBean = new DemandsPublishBean.DemandInvoiceTypeListBean();
            demandInvoiceTypeListBean.setId(defaultBean.getId());
            listBeen.add(demandInvoiceTypeListBean);
        }

        bean.setDemandInvoiceTypeList(listBeen);

        demandPostageBean.setAddress(etAreaDetails.getText().toString().trim());
        demandPostageBean.setProvince(tvArea.getText().toString());
        demandPostageBean.setPhone(etReceptionNumber.getText().toString().trim());
        demandPostageBean.setReceiver(etReceptionName.getText().toString().trim());
        demandPostageBean.setTelephone(etReceptionNumber.getText().toString().trim());


        bean.setDemandPostage(demandPostageBean);

        bean.setAttentions(etPublishCautions.getText().toString().trim());
        bean.setDeadline(etDate.getText().toString().trim());
        bean.setMailMinimum(Integer.valueOf(etExpressAmountMinimum.getText().toString().trim()));
        bean.setTotalBonus(Integer.valueOf(etAmountRedbag.getText().toString().trim()));
        bean.setTotalAmount(Integer.valueOf(etAmount.getText().toString().trim()));
        TLog.log(bean.toString());
        return bean;
    }

    private void checkParams() {

        if (!checkIfIsEmpty(etAmount)) {
            BaseApplication.showToast(etAmount.getHint() + "不能为空");
            return;
        }
        if (Switch.isChecked()) {
            if (!checkIfIsEmpty(etAmountRedbag)) {
                BaseApplication.showToast(etAmountRedbag.getHint() + "不能为空");
                return;
            }
        }
        if (!checkIfIsEmpty(etExpressAmountMinimum)) {
            BaseApplication.showToast(etExpressAmountMinimum.getHint() + "不能为空");
            return;
        }
        if (!checkIfIsEmpty(etPublishCompanyName)) {
            BaseApplication.showToast(etPublishCompanyName.getHint() + "不能为空");
            return;
        }
        if (!checkIfIsEmpty(etAreaDetails)) {
            BaseApplication.showToast(etAreaDetails.getHint() + "不能为空");
            return;
        }
        if (paperNormal || paperSpecial) {
            if (!checkIfIsEmpty(etReceptionNumber)) {
                BaseApplication.showToast(etReceptionNumber.getHint() + "不能为空");
                return;
            }
            if (!checkIfIsEmpty(etReceptionName)) {
                BaseApplication.showToast(etReceptionName.getHint() + "不能为空");
                return;
            }
            if (!checkIfIsEmpty(etPublishTexNumber)) {
                BaseApplication.showToast(etPublishTexNumber.getHint() + "不能为空");
                return;
            }

            if (!checkIfIsEmpty(etAreaDetails)) {
                BaseApplication.showToast(etAreaDetails.getHint() + "不能为空");
                return;
            }
        }


        if (paperSpecial) {
            if (!checkIfIsEmpty(etPublishAddress)) {
                BaseApplication.showToast(etPublishAddress.getHint() + "不能为空");
                return;
            }
            if (!checkIfIsEmpty(etPublishPhoneNumber)) {
                BaseApplication.showToast(etPublishPhoneNumber.getHint() + "不能为空");
                return;
            }
            if (checkIfIsEmpty(etPublishBankAccount)) {
                BaseApplication.showToast(etPublishBankAccount.getHint() + "不能为空");
                return;
            }
            if (!checkIfIsEmpty(etPublishBank)) {
                BaseApplication.showToast(etPublishBank.getHint() + "不能为空");
                return;
            }
        }
    }

    private boolean checkIfIsEmpty(EditText editText) {
        if (TextUtils.isEmpty(editText.getText())) {
            return true;
        } else {
            return false;
        }
    }

    private void requestForCompanies() {
        AccountHelper.isTokenValid(new Api.BaseViewCallback<LoginWithInfoBean>() {
            @Override
            public void setData(LoginWithInfoBean loginWithInfoBean) {
                Api.companiesList(loginWithInfoBean.getData().getToken(), new Api.BaseViewCallback<CompaniesBean>() {


                    @Override
                    public void setData(CompaniesBean companiesBean) {
                        DemandsPublishActivity.this.companiesBean = companiesBean;
                        if (companiesBean.getStatus() == 200 && companiesBean.getData() != null) {
                            setDialog(companiesBean.getData());
                        } else if (companiesBean.getStatus() == 400) {
                            setDialog(companiesBean.getData());
                        }
                    }
                });
            }
        });

    }

    private void setDialog(final List<CompaniesBean.DataBean> data) {
        mCameraDialog = new Dialog(this, R.style.BottomDialog);
        LinearLayout root = (LinearLayout) LayoutInflater.from(this).inflate(
                R.layout.company_dialog_bottom, null);
        //初始化视图
        LinearLayout ll_container = (LinearLayout) root.findViewById(R.id.ll_container);
        for (int i = 0; i < data.size(); i++) {
            final TextView textView = new TextView(this);
            if (data.size() > 0) {
                dataBean = data.get(i);
                textView.setText(dataBean.getName());
                textView.setLayoutParams(new ActionBarOverlayLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                textView.setGravity(Gravity.CENTER_HORIZONTAL);
                textView.setTextSize(TDevice.spToPx(getResources(), 12));
                textView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        SharedPreferencesHelper.save(DemandsPublishActivity.this, dataBean);
                        etPublishCompanyName.setText(dataBean.getName());
                        etPublishAddress.setText(dataBean.getAddress());
                        etPublishTexNumber.setText(dataBean.getTaxno());
                        etPublishPhoneNumber.setText(dataBean.getPhone());
                        etPublishBank.setText(dataBean.getDepositBank());
                        etPublishBankAccount.setText(dataBean.getAccount());
                        mCameraDialog.dismiss();
                    }
                });
                ll_container.addView(textView);
            }

        }
        root.findViewById(R.id.btn_cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCameraDialog.dismiss();
            }
        });
        root.findViewById(R.id.btn_clear).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                etPublishCompanyName.setText("");
                etPublishAddress.setText("");
                etPublishTexNumber.setText("");
                etPublishPhoneNumber.setText("");
                etPublishBank.setText("");
                etPublishBankAccount.setText("");
                SharedPreferencesHelper.remove(DemandsPublishActivity.this, CompaniesBean.DataBean.class);
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
//        mCameraDialog.show();
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        llAmount.setVisibility(isChecked?View.VISIBLE:View.GONE);

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        TextView textView = (TextView) view;
        demandPostageBean.setLogisticsCompany(textView.getText().toString().trim());
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
                demandPostageBean.setProvince(province.getName());
                demandPostageBean.setCity(city.getName());
                demandPostageBean.setDistrict(district.getName());
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
