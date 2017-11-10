package com.pilipa.fapiaobao.ui;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.v7.widget.ActionBarOverlayLayout;
import android.support.v7.widget.AppCompatSpinner;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SwitchCompat;
import android.text.TextUtils;
import android.text.method.ReplacementTransformationMethod;
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
import android.widget.Toast;

import com.example.mylibrary.utils.EncodeUtils;
import com.example.mylibrary.utils.TLog;
import com.example.mylibrary.utils.TimeUtils;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.lljjcoder.city_20170724.CityPickerView;
import com.lljjcoder.city_20170724.bean.CityBean;
import com.lljjcoder.city_20170724.bean.DistrictBean;
import com.lljjcoder.city_20170724.bean.ProvinceBean;
import com.pilipa.fapiaobao.R;
import com.pilipa.fapiaobao.account.AccountHelper;
import com.pilipa.fapiaobao.adapter.CompanyListAdapter;
import com.pilipa.fapiaobao.adapter.PublishSpinnerAdapter;
import com.pilipa.fapiaobao.base.BaseActivity;
import com.pilipa.fapiaobao.base.BaseApplication;
import com.pilipa.fapiaobao.entity.Company;
import com.pilipa.fapiaobao.net.Api;
import com.pilipa.fapiaobao.net.bean.LoginWithInfoBean;
import com.pilipa.fapiaobao.net.bean.invoice.DefaultInvoiceBean;
import com.pilipa.fapiaobao.net.bean.invoice.MacherBeanToken;
import com.pilipa.fapiaobao.net.bean.me.CompaniesBean;
import com.pilipa.fapiaobao.net.bean.me.NormalBean;
import com.pilipa.fapiaobao.net.bean.publish.BalanceBean;
import com.pilipa.fapiaobao.net.bean.publish.DemandsPublishBean;
import com.pilipa.fapiaobao.net.bean.publish.ExpressCompanyBean;
import com.pilipa.fapiaobao.receiver.WXPayReceiver;
import com.pilipa.fapiaobao.ui.deco.FinanceItemDeco;
import com.pilipa.fapiaobao.ui.dialog.TimePickerDialog;
import com.pilipa.fapiaobao.ui.widget.LabelsView;
import com.pilipa.fapiaobao.utils.SharedPreferencesHelper;
import com.pilipa.fapiaobao.utils.TDevice;
import com.pilipa.fapiaobao.zxing.android.CaptureActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by edz on 2017/10/26.
 */

public class DemandsPublishActivity extends BaseActivity implements CompoundButton.OnCheckedChangeListener, AdapterView.OnItemSelectedListener, LabelsView.OnLabelSelectChangeListener, CompanyListAdapter.OnCompanyClickListener {

    private static final String TAG = "DemandsPublishActivity";


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
    @Bind(R.id.title)
    TextView title;
    @Bind(R.id.upload_back)
    ImageView uploadBack;
    @Bind(R.id.upload_scan)
    ImageView uploadScan;
    @Bind(R.id.changeCompanyinfo)
    TextView changeCompanyinfo;
    @Bind(R.id.more_company)
    TextView moreCompany;
    @Bind(R.id.ll_company_info)
    LinearLayout llCompanyInfo;
    @Bind(R.id.recyclerview)
    RecyclerView recyclerview;
    @Bind(R.id.ll_add_company_info)
    LinearLayout llAddCompanyInfo;
    @Bind(R.id.btn_add_company_info)
    LinearLayout btnAddCompanyInfo;
    @Bind(R.id.tv_receive_deadline)
    TextView tvReceiveDeadline;
    @Bind(R.id.tv_express_limited)
    TextView tvExpressLimited;
    @Bind(R.id.ll_toggle_switch)
    LinearLayout llToggleSwitch;
    @Bind(R.id.SwitchArea)
    SwitchCompat SwitchArea;
    @Bind(R.id.ll_area)
    LinearLayout llArea;
    @Bind(R.id.ll_estimate_request)
    LinearLayout llEstimateRequest;
    @Bind(R.id.tv_area_limited)
    TextView tvAreaLimited;
    @Bind(R.id.ll_area_limited)
    LinearLayout llAreaLimited;
    @Bind(R.id.ll_express_limited)
    LinearLayout llExpressLimited;
    @Bind(R.id.ll_select_area)
    LinearLayout llSelectArea;
    @Bind(R.id.ll_date)
    LinearLayout llDate;
    private boolean paperNormal;
    private boolean elec;
    private boolean paperSpecial;
    private Dialog mCameraDialog;
    private int fourteen_days_miliseconds = 14 * 24 * 60 * 60 * 1000;
    private TimePickerDialog dialog;
    private CityPickerView cityPicker;
    private static Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

        }
    };
    public CompaniesBean companiesBean;
    private String invoiceVarieties;
    private CompaniesBean.DataBean dataBean;
    private LoginWithInfoBean loginBean;
    private ArrayList<DefaultInvoiceBean.DataBean> bean;
    private DemandsPublishBean.DemandPostageBean demandPostageBean;
    public static final int REQUEST_CODE = 300;
    public static final int REQUEST_CODE_FOR_MORE_TYPE = 400;
    private CompanyListAdapter companyListAdapter;
    private static final String DECODED_CONTENT_KEY = "codedContent";
    private static final String DECODED_BITMAP_KEY = "codedBitmap";
    private static final int REQUEST_ADD_COMPANY_INFO = 971;
    private static final int REQUEST_ALL_COMPANY_INFO = 321;
    private static final int REQUEST_CODE_SCAN = 0x0022;
    public static final String RECEIPTELEC_DATA = "receiptElec";
    public static final String RECEIPTPAPERNORMAL_DATA = "receiptPaperNormal";
    public static final String RECEIPTPAPERSPECIAL_DATA = "receiptPaperSpecial";
    private Dialog mTipDialog;

    private static final double MAX_AMOUNT = 50000;

    public WXPayReceiver wxPayReceiver = new WXPayReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (TextUtils.equals(intent.getAction(), WXPayReceiver.pay_success)) {
                publish();

            } else if (TextUtils.equals(intent.getAction(), WXPayReceiver.pay_fail)) {
                BaseApplication.showToast("充值失败");

            }
        }
    };
    private boolean isAreaLimited = false;
    private View view;

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
        changeCompanyinfo.setVisibility(View.GONE);
        moreCompany.setVisibility(View.GONE);
        llCompanyInfo.setVisibility(View.GONE);
        llAddCompanyInfo.setVisibility(View.VISIBLE);
        paperNormal = intent.getBooleanExtra(PubActivity.RECEIPTPAPERNORMAL_DATA, false);
        elec = intent.getBooleanExtra(PubActivity.RECEIPTELEC_DATA, false);
        paperSpecial = intent.getBooleanExtra(PubActivity.RECEIPTPAPERSPECIAL_DATA, false);
        paperNormalReceipt.setVisibility(paperNormal ? View.VISIBLE : View.GONE);
        paperElecReceipt.setVisibility(elec ? View.VISIBLE : View.GONE);
        paperSpecialReceipt.setVisibility(paperSpecial ? View.VISIBLE : View.GONE);

        view = findViewById(R.id.publish_receive_receipt_info);
        view.setVisibility(paperNormal || intent.getBooleanExtra(PubActivity.RECEIPTPAPERSPECIAL_DATA, false) ? View.VISIBLE : View.GONE);
        tvEtPublishBankMustFill.setText(paperSpecial ? "必填" : "选填");
        tvPublishAddressMustFill.setText(paperSpecial ? "必填" : "选填");
        tvPublishBankAccountMustFill.setText(paperSpecial ? "必填" : "选填");
        tvPublishPhoneNumberMustFill.setText(paperSpecial ? "必填" : "选填");
        llAreaLimited.setVisibility(elec && !paperNormal && !paperSpecial ? View.GONE : View.VISIBLE);
        llExpressLimited.setVisibility(elec && !paperNormal && !paperSpecial ? View.GONE : View.VISIBLE);

        etDate.setText(TimeUtils.millis2String(System.currentTimeMillis() + fourteen_days_miliseconds, TimeUtils.FORMAT));
        dialog = new TimePickerDialog(this);
        Switch.setChecked(true);
        Switch.setOnCheckedChangeListener(this);
        Switch.setTag("Switch");
        SwitchArea.setChecked(true);
        SwitchArea.setOnCheckedChangeListener(this);
        SwitchArea.setTag("SwitchArea");
        llEstimateRequest.setVisibility(View.GONE);
        llToggleSwitch.setVisibility(View.GONE);
        rbCod.setSelected(true);


        setUpReceiptParams();

        new Thread() {
            @Override
            public void run() {
                Looper.prepare();
                initCityPicker();
            }
        }.start();

        spinner.setOnItemSelectedListener(this);

        labelsReceiptKind.setOnLabelSelectChangeListener(this);

        recyclerview.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        companyListAdapter = new CompanyListAdapter(false);
        companyListAdapter.setOnCompanyClickListener(this);
        recyclerview.addItemDecoration(new FinanceItemDeco(this, LinearLayoutManager.VERTICAL, (int) TDevice.dipToPx(getResources(), 1), getResources().getColor(R.color.gray_hint)));
        recyclerview.setAdapter(companyListAdapter);

        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(WXPayReceiver.pay_fail);
        intentFilter.addAction(WXPayReceiver.pay_success);

        registerReceiver(wxPayReceiver, intentFilter);
        String location = BaseApplication.get("location", "定位失败");
        tvAreaLimited.setText(location);

        etPublishTexNumber.setTransformationMethod(new ReplacementTransformationMethod() {
            @Override
            protected char[] getOriginal() {
                char[] originalCharArr = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
                return originalCharArr;
            }

            @Override
            protected char[] getReplacement() {
                char[] replacementCharArr = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};
                return replacementCharArr;
            }
        });

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(wxPayReceiver);
    }

    private String setUpReceiptParams() {
        if (paperNormal && paperSpecial && elec) {
            invoiceVarieties = "1,2,3";
            return invoiceVarieties;
        } else if (paperNormal && paperSpecial) {
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
                        bean = defaultInvoiceBean.getData();
                        ArrayList<String> arrayReceipt = new ArrayList<>();
                        for (DefaultInvoiceBean.DataBean dataBean : bean) {
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
                        bean = allInvoiceType.getData();
                        ArrayList<String> arrayReceipt = new ArrayList<>();
                        for (DefaultInvoiceBean.DataBean dataBean : bean) {
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
            , R.id.btn_publish_now
            , R.id.changeCompanyinfo
            , R.id.more_company
            , R.id.ll_add_company_info
            , R.id.btn_add_company_info
            , R.id.ll_select_area
            , R.id.tv_receive_deadline
            , R.id.ll_date
            , R.id.tv_express_limited})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.title:
                break;
            case R.id.upload_back:
                finish();
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
                //TODO 扫码
                startActivityForResult(new Intent(this, CaptureActivity.class), REQUEST_CODE_SCAN);

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
                requestForMoreTypes();
                break;
            case R.id.labels_receipt_kind:
                break;
            case R.id.ll_date:
            case R.id.et_date:
            case R.id.iv_select_date:
                //TODO
                dialog.showDatePickerDialog(new TimePickerDialog.TimePickerDialogInterface() {
                    @Override
                    public void positiveListener() {
                        etDate.setText(dialog.getYear() + "-" + dialog.getMonth() + "-" + dialog.getDay());
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
            case R.id.ll_select_area:
            case R.id.iv_select_area:
                cityPicker.show();
                break;
            case R.id.et_area_details:
                break;
            case R.id.et_publish_cautions:
                break;
            case R.id.btn_publish_now:
                publish();

                break;
            case R.id.changeCompanyinfo:
            case R.id.more_company:
                Intent intent = new Intent();
                intent.setClass(this, CompanySelectActivity.class);
                startActivityForResult(intent, REQUEST_ALL_COMPANY_INFO);
                break;
            case R.id.ll_add_company_info:
                break;
            case R.id.btn_add_company_info:
                addCompanyInfo();
                break;
            case R.id.tv_receive_deadline:
                setTipDialog(R.layout.layout_extimate_tip1);
                break;
            case R.id.tv_express_limited:
                setTipDialog(R.layout.layout_extimate_tip2);
                break;
        }
    }

    private void publish() {
        if (checkParams()) {
            if (makeParams() != null) {
                Gson gson = new GsonBuilder().serializeNulls().create();
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
                            finish();
                        } else if (balanceBean.getStatus() == 888) {
                            BaseApplication.showToast("账户余额不足，请先充值");
                            intent.setClass(DemandsPublishActivity.this, RechargeActivity.class);
                            startActivityForResult(intent, REQUEST_CODE);

                        }
                    }
                });
            }

        }
    }

    private void addCompanyInfo() {
        Intent intent = new Intent();
        intent.putExtra(RECEIPTELEC_DATA, elec);
        intent.putExtra(RECEIPTPAPERSPECIAL_DATA, paperSpecial);
        intent.putExtra(RECEIPTPAPERNORMAL_DATA, paperNormal);
        intent.putExtra("isPublish", true);
        intent.setClass(this, AddCompanyInfoActivity.class);
        startActivityForResult(intent, REQUEST_ADD_COMPANY_INFO);
    }


    private void createCompany(final Company company) {
        AccountHelper.isTokenValid(new Api.BaseViewCallback<LoginWithInfoBean>() {
            @Override
            public void setData(LoginWithInfoBean loginWithInfoBean) {
                if (loginWithInfoBean.getStatus() == 200) {
                    Api.companyCreate(company, AccountHelper.getToken(), new Api.BaseViewCallback<NormalBean>() {
                        @Override
                        public void setData(NormalBean normalBean) {
                            if (normalBean.getStatus() == 200) {
                                Toast.makeText(DemandsPublishActivity.this, "添加成功", Toast.LENGTH_SHORT).show();
                                setResult(RESULT_OK);
                                Log.d(TAG, "createCompany;success");
                            }
                        }
                    });
                }
            }
        });
    }

    private void requestForMoreTypes() {
        Intent intent = new Intent();
        Bundle bundle = new Bundle();
        bundle.putSerializable("new_data", bean);
        intent.putExtra(MoreTypesActivity.EXTRA_BUNDLE, bundle);
        intent.setClass(this, MoreTypesActivity.class);
        startActivityForResult(intent, REQUEST_CODE_FOR_MORE_TYPE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case REQUEST_CODE:
                if (requestCode == RESULT_CANCELED) {
                    BaseApplication.showToast("没钱");
                } else if (RESULT_OK == requestCode) {
                    publish();
                }
            case REQUEST_CODE_FOR_MORE_TYPE:
                if (resultCode == RESULT_OK) {
                    Bundle bundle = data.getBundleExtra(MoreTypesActivity.EXTRA_BUNDLE);
                    bean = (ArrayList<DefaultInvoiceBean.DataBean>) bundle.getSerializable("new_data");
                    ArrayList<String> arrayReceipt = new ArrayList<>();
                    for (DefaultInvoiceBean.DataBean dataBean : bean) {
                        arrayReceipt.add(dataBean.getName());
                    }
                    labelsReceiptKind.setLabels(arrayReceipt);
                }
            case REQUEST_ADD_COMPANY_INFO:
                if (resultCode == RESULT_OK) {
                    requestForCompanies();
                }
                break;
            case REQUEST_ALL_COMPANY_INFO:
                if (resultCode == RESULT_OK) {
                    Bundle bundleExtra = data.getBundleExtra(CompanySelectActivity.EXTRA_BUNDLE);
                    CompaniesBean.DataBean databean = bundleExtra.getParcelable(CompanySelectActivity.EXTRA_SELECT_COMPANY);
                    if (databean != null) {
                        llAddCompanyInfo.setVisibility(View.GONE);
                        llCompanyInfo.setVisibility(View.VISIBLE);
                        changeCompanyinfo.setVisibility(View.VISIBLE);
                        updateCompanyInfo(databean);
                    }
                }
                break;
            case REQUEST_CODE_SCAN:
                if (resultCode == RESULT_OK) {
                    String content = data.getStringExtra(DECODED_CONTENT_KEY);
                    TLog.log("DECODED_CONTENT_KEY" + content);
                    Bitmap bitmap = data.getParcelableExtra(DECODED_BITMAP_KEY);
                    Gson gson = new Gson();
                    String s = EncodeUtils.urlDecode(content);
                    TypeToken<MacherBeanToken.DataBean.CompanyBean> typeToken = TypeToken.get(MacherBeanToken.DataBean.CompanyBean.class);
                    MacherBeanToken.DataBean.CompanyBean companyBean = gson.fromJson(s, typeToken.getType());
                    updateCompanyInfo(companyBean);
                }
        }
    }

    private void updateCompanyInfo(MacherBeanToken.DataBean.CompanyBean companyBean) {
        etPublishCompanyName.setText(companyBean.getName());
        etPublishAddress.setText(companyBean.getAddress());
        etPublishTexNumber.setText(companyBean.getTaxno());
        etPublishPhoneNumber.setText(companyBean.getPhone());
        etPublishBank.setText(companyBean.getDepositBank());
        etPublishBankAccount.setText(companyBean.getAccount());
        llCompanyInfo.setVisibility(View.VISIBLE);
        llAddCompanyInfo.setVisibility(View.GONE);
        changeCompanyinfo.setVisibility(View.VISIBLE);
        moreCompany.setVisibility(View.GONE);
        uploadScan.setVisibility(View.GONE);
        ivDotsMoreCompany.setVisibility(View.GONE);

        Company company = new Company();
        company.setName(companyBean.getName());
        company.setTaxno(companyBean.getTaxno());
        company.setAddress(companyBean.getAddress());
        company.setPhone(companyBean.getPhone());
        company.setDepositBank(companyBean.getDepositBank());
        company.setAccount(companyBean.getAccount());
        createCompany(company);

    }

    private void updateCompanyInfo(CompaniesBean.DataBean databean) {
        etPublishCompanyName.setText(databean.getName());
        etPublishAddress.setText(databean.getAddress());
        etPublishTexNumber.setText(databean.getTaxno());
        etPublishPhoneNumber.setText(databean.getPhone());
        etPublishBank.setText(databean.getDepositBank());
        etPublishBankAccount.setText(databean.getAccount());
        llCompanyInfo.setVisibility(View.VISIBLE);
        llAddCompanyInfo.setVisibility(View.GONE);
        changeCompanyinfo.setVisibility(View.VISIBLE);
        moreCompany.setVisibility(View.GONE);
        uploadScan.setVisibility(View.GONE);
        ivDotsMoreCompany.setVisibility(View.GONE);
    }

    private DemandsPublishBean makeParams() {
        LoginWithInfoBean loginWithInfoBean = SharedPreferencesHelper.loadFormSource(this, LoginWithInfoBean.class);
        DemandsPublishBean bean = new DemandsPublishBean();
        if (SwitchArea.isChecked()) {
            bean.setAreaRestrict("1");
            bean.setCity(tvAreaLimited.getText().toString());
        } else {
            bean.setAreaRestrict("0");
        }
        bean.setInvoiceVarieties(setUpReceiptParams());
        if (dataBean != null) {
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

        for (DefaultInvoiceBean.DataBean defaultBean : this.bean) {
            if (defaultBean.isSelect()) {
                DemandsPublishBean.DemandInvoiceTypeListBean demandInvoiceTypeListBean = new DemandsPublishBean.DemandInvoiceTypeListBean();
                DemandsPublishBean.DemandInvoiceTypeListBean.InvoiceTypeBeanX invoiceTypeBeanX = new DemandsPublishBean.DemandInvoiceTypeListBean.InvoiceTypeBeanX();
                invoiceTypeBeanX.setId(defaultBean.getId());
                demandInvoiceTypeListBean.setInvoiceType(invoiceTypeBeanX);
                listBeen.add(demandInvoiceTypeListBean);
            }


        }
        if (listBeen.size() == 0) {
            BaseApplication.showToast("请指定一种发票类型");
            return null;
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
        if (!elec && paperNormal || paperSpecial) {
            bean.setMailMinimum(Integer.valueOf(etExpressAmountMinimum.getText().toString().trim()));
        }
        if(!etAmountRedbag.getText().toString().trim().isEmpty()){
            bean.setTotalBonus(Integer.valueOf(etAmountRedbag.getText().toString().trim()));
        }
        if(!etAmount.getText().toString().trim().isEmpty()){
            bean.setTotalAmount(Integer.valueOf(etAmount.getText().toString().trim()));
        }
        TLog.log(bean.toString());
        return bean;
    }

    private boolean checkParams() {

        if (!checkIfIsEmpty(etAmount)) {
            BaseApplication.showToast(etAmount.getHint() + "不能为空");
            return false;
        }
        if (Double.valueOf(etAmount.getText().toString().trim()) > MAX_AMOUNT) {
            BaseApplication.showToast("单次需求总额不得超过50000元");
            return false;
        }
        if (Switch.isChecked()) {
            if (!checkIfIsEmpty(etAmountRedbag)) {
                BaseApplication.showToast(etAmountRedbag.getHint() + "不能为空");
                return false;
            }

            if ((Double.valueOf(etAmountRedbag.getText().toString().trim())
                    > Double.valueOf(etAmount.getText().toString().trim()) * 0.1)) {
                BaseApplication.showToast("悬赏红包不能超过需求总额的10%");
                return false;
            }
        }


        if (!checkIfIsEmpty(etPublishCompanyName)) {
            BaseApplication.showToast(etPublishCompanyName.getHint() + "不能为空");
            return false;
        }
        if (!checkIfIsEmpty(etAreaDetails) && view.getVisibility() == View.VISIBLE) {
            BaseApplication.showToast(etAreaDetails.getHint() + "不能为空");
            return false;
        }
        if (paperNormal || paperSpecial) {
            if (!checkIfIsEmpty(etReceptionNumber) && view.getVisibility() == View.VISIBLE) {
                BaseApplication.showToast(etReceptionNumber.getHint() + "不能为空");
                return false;
            }
            if (!checkIfIsEmpty(etReceptionName) && view.getVisibility() == View.VISIBLE) {
                BaseApplication.showToast(etReceptionName.getHint() + "不能为空");
                return false;
            }
            if (!checkIfIsEmpty(etPublishTexNumber)) {
                BaseApplication.showToast(etPublishTexNumber.getHint() + "不能为空");
                return false;
            }

            if (!checkIfIsEmpty(etAreaDetails)) {
                BaseApplication.showToast(etAreaDetails.getHint() + "不能为空");
                return false;
            }

            if (!checkIfIsEmpty(etExpressAmountMinimum) && llExpressLimited.getVisibility() == View.VISIBLE) {
                BaseApplication.showToast(etExpressAmountMinimum.getHint() + "不能为空");
                return false;
            }
        }

        if (paperSpecial) {
            if (!checkIfIsEmpty(etPublishAddress)) {
                BaseApplication.showToast(etPublishAddress.getHint() + "不能为空");
                return false;
            }
            if (!checkIfIsEmpty(etPublishPhoneNumber)) {
                BaseApplication.showToast(etPublishPhoneNumber.getHint() + "不能为空");
                return false;
            }
            if (!checkIfIsEmpty(etPublishBankAccount)) {
                BaseApplication.showToast(etPublishBankAccount.getHint() + "不能为空");
                return false;
            }
            if (!checkIfIsEmpty(etPublishBank)) {
                BaseApplication.showToast(etPublishBank.getHint() + "不能为空");
                return false;
            }
        }
        return true;
    }

    private boolean checkIfIsEmpty(EditText editText) {
        return !TextUtils.isEmpty(editText.getText());
    }


    private void setTipDialog(@NonNull int res) {
        mTipDialog = new Dialog(this, R.style.BottomDialog);
        LinearLayout root = (LinearLayout) LayoutInflater.from(this).inflate(
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

    private void requestForCompanies() {
        AccountHelper.isTokenValid(new Api.BaseViewCallback<LoginWithInfoBean>() {
            @Override
            public void setData(LoginWithInfoBean loginWithInfoBean) {
                Api.companiesList(loginWithInfoBean.getData().getToken(), new Api.BaseViewCallback<CompaniesBean>() {


                    @Override
                    public void setData(CompaniesBean companiesBean) {
                        DemandsPublishActivity.this.companiesBean = companiesBean;
                        if (companiesBean.getStatus() == 200 && companiesBean.getData() != null) {
                            companyListAdapter.addCompanyInfo(companiesBean.getData());
                            if (companiesBean.getData().size() > 5) {
                                moreCompany.setVisibility(View.VISIBLE);
                            }
                        } else if (companiesBean.getStatus() == 400) {

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
        if (buttonView.getTag().equals("SwitchArea")) {
            llArea.setVisibility(isChecked ? View.VISIBLE : View.GONE);
            llEstimateRequest.setVisibility(isChecked ? View.GONE : View.VISIBLE);
        } else if (buttonView.getTag().equals("Switch")) {
            llAmount.setVisibility(isChecked ? View.VISIBLE : View.GONE);
            llToggleSwitch.setVisibility(isChecked ? View.GONE : View.VISIBLE);
        }


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
                .onlyShowProvinceAndCity(false)
                .build();

        cityPicker.setOnCityItemClickListener(new CityPickerView.OnCityItemClickListener() {
            @Override
            public void onSelected(ProvinceBean province, CityBean city, DistrictBean district) {
                //返回结果
                //ProvinceBean 省份信息
                //CityBean     城市信息
                //DistrictBean 区县信息
                if (isAreaLimited) {
                    tvAreaLimited.setText(city.getName() + "市");
                    isAreaLimited = false;
                } else {
                    demandPostageBean.setProvince(province.getName());
                    demandPostageBean.setCity(city.getName());
                    demandPostageBean.setDistrict(district.getName());
                    tvArea.setText(province.getName() + "-" + city.getName() + "-" + district.getName());
                }
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
        if (bean != null && bean.size() > 0) {
            DefaultInvoiceBean.DataBean dataBean = bean.get(position);
            dataBean.setSelect(isSelect);
        }
    }

    @Override
    public void onCompanyClick(CompaniesBean.DataBean dataBean) {
        updateCompanyInfo(dataBean);
    }


    @OnClick(R.id.tv_area_limited)
    public void onViewClicked() {
        isAreaLimited = true;
        cityPicker.show();
    }
}
