package com.pilipa.fapiaobao.ui;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
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

import com.blog.www.guideview.Component;
import com.blog.www.guideview.Guide;
import com.blog.www.guideview.GuideBuilder;
import com.example.mylibrary.utils.RegexUtils;
import com.example.mylibrary.utils.TLog;
import com.example.mylibrary.utils.TimeUtils;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.lljjcoder.Interface.OnCityItemClickListener;
import com.lljjcoder.bean.CityBean;
import com.lljjcoder.bean.DistrictBean;
import com.lljjcoder.bean.ProvinceBean;
import com.lljjcoder.citywheel.CityConfig;
import com.lljjcoder.citywheel.CityPickerView;
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
import com.pilipa.fapiaobao.net.bean.me.CompanyDetailsBean;
import com.pilipa.fapiaobao.net.bean.me.NormalBean;
import com.pilipa.fapiaobao.net.bean.publish.BalanceBean;
import com.pilipa.fapiaobao.net.bean.publish.DemandsPublishBean;
import com.pilipa.fapiaobao.net.bean.publish.ExpressCompanyBean;
import com.pilipa.fapiaobao.receiver.WXPayReceiver;
import com.pilipa.fapiaobao.ui.component.SimpleComponent;
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
    @Bind(R.id.ll_more_company_types)
    LinearLayout llMoreCompanyTypes;
    private boolean paperNormal;
    private boolean elec;
    private boolean paperSpecial;
    private Dialog mCameraDialog;
    private TimePickerDialog dialog;
    private CityPickerView cityPicker;
    private static Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

        }
    };
    public CompaniesBean companiesBean;
    private CompaniesBean.DataBean dataBean;
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
    private View view;
    private String tempCompanyId;
    private AlertDialog alertDialog;
    private CityPickerView cityPickerAreaLimited;

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
//        llAreaLimited.setVisibility(elec && !paperNormal && !paperSpecial ? View.GONE : View.VISIBLE);
        llExpressLimited.setVisibility(elec && !paperNormal && !paperSpecial ? View.GONE : View.VISIBLE);

        int fourteen_days_miliseconds = 14 * 24 * 60 * 60 * 1000;
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
        initDialog();
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
        String location = BaseApplication.get("location", "定位失败，点击选择地区");
        tvAreaLimited.setText(location);

        etPublishTexNumber.setTransformationMethod(new ReplacementTransformationMethod() {
            @Override
            protected char[] getOriginal() {
                return new char[]{'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
            }

            @Override
            protected char[] getReplacement() {
                return new char[]{'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};
            }
        });



        etAreaDetails.setText(BaseApplication.get("etAreaDetails", null));
        tvArea.setText(BaseApplication.get("tvArea", null));
        etReceptionNumber.setText(BaseApplication.get("etReceptionNumber", null));
        etReceptionName.setText(BaseApplication.get("etReceptionName", null));
    }


    private void initDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(false);
        builder.setMessage(getString(R.string.demand_publish_quit));
        builder.setTitle(getString(R.string.demand_publish_title));


        builder.setPositiveButton(getString(R.string.confirm), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });
        builder.setNegativeButton(getString(R.string.cancel), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        alertDialog = builder.create();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(wxPayReceiver);
        alertDialog.dismiss();
    }

    private String setUpReceiptParams() {
        String invoiceVarieties;
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
        demandPostageBean.setProvince(BaseApplication.get("province",null));
        demandPostageBean.setCity(BaseApplication.get("city",null));
        demandPostageBean.setDistrict(BaseApplication.get("district",null));
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
        LoginWithInfoBean loginBean = SharedPreferencesHelper.loadFormSource(this, LoginWithInfoBean.class);
        if (loginBean != null) {
            Api.<DefaultInvoiceBean>findUserInvoiceType(loginBean.getData().getToken(), new Api.BaseViewCallbackWithOnStart<DefaultInvoiceBean>() {
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
            Api.<DefaultInvoiceBean>findDefaultInvoiceType(new Api.BaseViewCallbackWithOnStart<DefaultInvoiceBean>() {
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

                if (!alertDialog.isShowing()) {
                    alertDialog.show();
                }
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
                final Gson gson = new GsonBuilder().serializeNulls().create();
                AccountHelper.isTokenValid(new Api.BaseViewCallback<LoginWithInfoBean>() {
                    @Override
                    public void setData(LoginWithInfoBean loginWithInfoBean) {
                        if (loginWithInfoBean.getStatus() == 200) {
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
                                    } else if (balanceBean.getStatus() == 885) {
                                        BaseApplication.showToast("可用金额不足，已发红包占用中");
                                        intent.setClass(DemandsPublishActivity.this, RechargeActivity.class);
                                        startActivityForResult(intent, REQUEST_CODE);
                                    }
                                }
                            });
                        } else if (loginWithInfoBean.getStatus() == 701) {
                            BaseApplication.showToast("登录超时");
                            login();
                        } else {
                            BaseApplication.showToast("服务器超时");
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
                if (RESULT_CANCELED == resultCode) {
                    BaseApplication.showToast("余额不足");
                } else if (RESULT_OK == resultCode) {
                }
            case REQUEST_CODE_FOR_MORE_TYPE:
                if (resultCode == RESULT_OK) {
                    if (data != null) {
                        Bundle bundle = data.getBundleExtra(MoreTypesActivity.EXTRA_BUNDLE);
                        bean = (ArrayList<DefaultInvoiceBean.DataBean>) bundle.getSerializable("new_data");
                        ArrayList<String> arrayReceipt = new ArrayList<>();
                        for (DefaultInvoiceBean.DataBean dataBean : bean) {
                            arrayReceipt.add(dataBean.getName());
                        }
                        labelsReceiptKind.setLabels(arrayReceipt);
                    }
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
                    TLog.log("content" + content);
                    String[] split = content.split("\\?");
                    String[] split1 = split[1].split("=");
                    Api.companyDetails(split1[1], new Api.BaseViewCallback<CompanyDetailsBean>() {

                        private CompanyDetailsBean.DataBean data;

                        @Override
                        public void setData(CompanyDetailsBean companyDetailsBean) {
                            MacherBeanToken.DataBean.CompanyBean companyBean = new MacherBeanToken.DataBean.CompanyBean();
                            data = companyDetailsBean.getData();
                            companyBean.setAccount(data.getAccount());
                            companyBean.setAddress(data.getAddress());
                            companyBean.setDepositBank(data.getDepositBank());
                            companyBean.setId(data.getId());
                            companyBean.setIsNewRecord(data.isIsNewRecord());
                            companyBean.setName(data.getName());
                            companyBean.setPhone(data.getPhone());
                            companyBean.setTaxno(data.getTaxno());
                            updateCompanyInfo(companyBean);
                        }
                    });
//
                }
        }
    }

    private void updateCompanyInfo(MacherBeanToken.DataBean.CompanyBean companyBean) {
        tempCompanyId = companyBean.getId();
        etPublishCompanyName.setText(companyBean.getName());
        etPublishAddress.setText(companyBean.getAddress());
        etPublishTexNumber.setText(companyBean.getTaxno().toUpperCase());
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
        tempCompanyId = databean.getId();
        etPublishCompanyName.setText(databean.getName());
        etPublishAddress.setText(databean.getAddress());
        etPublishTexNumber.setText(databean.getTaxno().toUpperCase());
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
        bean.setCompanyId(tempCompanyId);
        bean.setToken(loginWithInfoBean.getData().getToken());
        DemandsPublishBean.CompanyBean companyBean = new DemandsPublishBean.CompanyBean();
        CompaniesBean.DataBean dataBean = SharedPreferencesHelper.loadFormSource(this, CompaniesBean.DataBean.class);
        if (dataBean != null) {
            companyBean.setId(dataBean.getId());
        }
        companyBean.setId(tempCompanyId);
        companyBean.setAccount(etPublishBankAccount.getText().toString().trim());
        companyBean.setAddress(etPublishAddress.getText().toString().trim());
        companyBean.setTaxno(etPublishTexNumber.getText().toString().trim().toUpperCase());
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

        BaseApplication.set("etAreaDetails", etAreaDetails.getText().toString().trim());
        BaseApplication.set("tvArea", tvArea.getText().toString().trim());
        BaseApplication.set("etReceptionNumber", etReceptionNumber.getText().toString().trim());
        BaseApplication.set("etReceptionName", etReceptionName.getText().toString().trim());


        bean.setDemandPostage(demandPostageBean);

        bean.setAttentions(etPublishCautions.getText().toString().trim());
        bean.setDeadline(etDate.getText().toString().trim());
        if (paperNormal||paperSpecial) {
            bean.setMailMinimum(Double.valueOf(etExpressAmountMinimum.getText().toString().trim()));
        }
        if (Switch.isChecked()) {
            if (!etAmountRedbag.getText().toString().trim().isEmpty()) {
                bean.setTotalBonus(Double.parseDouble(etAmountRedbag.getText().toString().trim()));
            } else {
                bean.setTotalBonus(0);
            }
        } else {
            bean.setTotalBonus(0);
        }
        if (!etAmount.getText().toString().trim().isEmpty()) {
            bean.setTotalAmount(Double.parseDouble(etAmount.getText().toString().trim()));
        }
        TLog.log(bean.toString());
        return bean;
    }

    private boolean checkParams() {

        if (!checkIfIsEmpty(etAmount)) {
            BaseApplication.showToast("需求总额不能为空");
            return false;
        }
        if (Double.valueOf(etAmount.getText().toString().trim()) > MAX_AMOUNT) {
            BaseApplication.showToast("单次需求总额不得超过50000元");
            return false;
        }
        if (!etExpressAmountMinimum.getText().toString().isEmpty()
                && Double.valueOf(etExpressAmountMinimum.getText().toString().trim())
                > Double.valueOf(etAmount.getText().toString().trim())) {
            BaseApplication.showToast("最少寄送限额必须小于等于需求总额");
            return false;
        }

        if (Switch.isChecked()) {
            if (!checkIfIsEmpty(etAmountRedbag)) {
                BaseApplication.showToast("悬赏红包不能为空");
                return false;
            }

            if ((Double.valueOf(etAmountRedbag.getText().toString().trim())
                    > Double.valueOf(etAmount.getText().toString().trim()) * 0.1)) {
                BaseApplication.showToast("悬赏红包不能超过需求总额的10%");
                return false;
            }
        }


        if (!checkIfIsEmpty(etPublishCompanyName)) {
            BaseApplication.showToast("单位名称不能为空");
            return false;
        }
        if (!checkIfIsEmpty(etAreaDetails) && view.getVisibility() == View.VISIBLE) {
            BaseApplication.showToast("详细地址不能为空");
            return false;
        }
        if (paperNormal || paperSpecial) {
            if (view.getVisibility() == View.VISIBLE) {
                if (!checkIfIsEmpty(etReceptionNumber)) {
                    BaseApplication.showToast("收件人手机号不能为空");
                    return false;
                } else if (!RegexUtils.isMobileExact(etReceptionNumber.getText().toString().trim())) {
                    BaseApplication.showToast("请填写正确的手机号码");
                    return false;
                }
            }
            if (view.getVisibility() == View.VISIBLE) {
                if (!checkIfIsEmpty(etReceptionName)) {
                    BaseApplication.showToast("收件人姓名不能为空");
                    return false;
                }
            }

            if (!checkIfIsEmpty(etPublishTexNumber)) {
                BaseApplication.showToast("税号不能为空");
                return false;
            }

            if (!checkIfIsEmpty(etAreaDetails)) {
                BaseApplication.showToast("详细地址不能为空");
                return false;
            }

            if (!checkIfIsEmpty(etExpressAmountMinimum) && llExpressLimited.getVisibility() == View.VISIBLE) {
                BaseApplication.showToast("最少邮寄限额不能为空");
                return false;
            }

            if (TextUtils.isEmpty(tvArea.getText())) {
                BaseApplication.showToast("所在地区不能为空");
                return false;
            }
        }

        if (paperSpecial) {
            String str = "请完善必填信息";
            if (!checkIfIsEmpty(etPublishAddress)) {
                BaseApplication.showToast(str);
                return false;
            }
            if (!checkIfIsEmpty(etPublishPhoneNumber)) {
                BaseApplication.showToast(str);
                return false;
            }
            if (!checkIfIsEmpty(etPublishBankAccount)) {
                BaseApplication.showToast(str);
                return false;
            }
            if (!checkIfIsEmpty(etPublishBank)) {
                BaseApplication.showToast(str);
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
                            if (companiesBean.getData().size() == 1) {
                                updateCompanyInfo(companiesBean.getData().get(0));

                            } else {
                                companyListAdapter.addCompanyInfo(companiesBean.getData());
                                if (companiesBean.getData().size() > 5) {
                                    moreCompany.setVisibility(View.VISIBLE);
                                }
                            }

                        } else if (companiesBean.getStatus() == 400) {

                        }

                        if (BaseApplication.get("Is_First_In_Publish", true)) {
                            btnAddCompanyInfo.post(new Runnable() {
                                @Override
                                public void run() {
                                    showGuideView();
                                }
                            });
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
        LinearLayout linearLayout = (LinearLayout) view;

        TextView textView = (TextView) linearLayout.findViewById(R.id.spinner_item);
        demandPostageBean.setLogisticsCompany(textView.getText().toString().trim());
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }


    private void initCityPicker() {
        CityConfig cityConfig = new CityConfig.Builder(DemandsPublishActivity.this)
                .title("选择地区")
                .titleBackgroundColor("#E9E9E9")
                .textSize(15)
                .confirTextColor("#000000")
                .cancelTextColor("#000000")
                .province("直辖市")
                .city("天津")
                .district("和平区")
                .visibleItemsCount(5)
                .provinceCyclic(true)
                .cityCyclic(true)
                .districtCyclic(true)
                .itemPadding(5)
                .setCityInfoType(CityConfig.CityInfoType.BASE)
                .setCityWheelType(CityConfig.WheelType.PRO_CITY_DIS)
                .build();


        CityConfig cityConfig2 = new CityConfig.Builder(DemandsPublishActivity.this)
                .title("选择地区")
                .titleBackgroundColor("#E9E9E9")
                .textSize(15)
                .confirTextColor("#000000")
                .cancelTextColor("#000000")
                .province("直辖市")
                .city("天津")
                .district("和平区")
                .visibleItemsCount(5)
                .provinceCyclic(true)
                .cityCyclic(true)
                .districtCyclic(true)
                .itemPadding(5)
                .setCityInfoType(CityConfig.CityInfoType.BASE)
                .setCityWheelType(CityConfig.WheelType.PRO_CITY)
                .build();

        cityPicker = new CityPickerView(cityConfig);
        cityPickerAreaLimited = new CityPickerView(cityConfig2);
        cityPickerAreaLimited.setOnCityItemClickListener(new OnCityItemClickListener() {
            @Override
            public void onSelected(ProvinceBean province, CityBean city) {
                super.onSelected(province, city);
                tvAreaLimited.setText(city.getName() + "市");
                cityPickerAreaLimited.hide();
            }

            @Override
            public void onCancel() {
                super.onCancel();
                cityPickerAreaLimited.hide();
            }
        });

        cityPicker.setOnCityItemClickListener(new OnCityItemClickListener() {
            @Override
            public void onSelected(ProvinceBean province, CityBean city, DistrictBean district) {
                super.onSelected(province, city, district);
                //返回结果
                //ProvinceBean 省份信息
                //CityBean     城市信息
                //DistrictBean 区县信息

                    demandPostageBean.setProvince(province.getName());
                    demandPostageBean.setCity(city.getName());
                    demandPostageBean.setDistrict(district.getName());

                    BaseApplication.set("province", province.getName());
                    BaseApplication.set("city", city.getName());
                    BaseApplication.set("district", district.getName());
                    tvArea.setText(province.getName() + "-" + city.getName() + "-" + district.getName());

                cityPicker.hide();
            }

            @Override
            public void onCancel() {
                super.onCancel();
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
        cityPickerAreaLimited.show();
    }

    public void showGuideView() {
        GuideBuilder builder = new GuideBuilder();
        builder.setTargetView(btnAddCompanyInfo)
                .setAlpha(150)
                .setHighTargetCorner(20)
                .setHighTargetGraphStyle(Component.ROUNDRECT)
                .setHighTargetPadding(10)
                .setOverlayTarget(false)
                .setOutsideTouchable(false);

        builder.setOnVisibilityChangedListener(new GuideBuilder.OnVisibilityChangedListener() {
            @Override
            public void onShown() {
            }

            @Override
            public void onDismiss() {
                llMoreCompanyTypes.post(new Runnable() {
                    @Override
                    public void run() {
                        showGuideView2();

                    }
                });
            }
        });

        builder.addComponent(new SimpleComponent(){
            @Override
            public View getView(LayoutInflater inflater) {
                ImageView imageView = new ImageView(inflater.getContext());
                imageView.setImageResource(R.mipmap.demand_publish_01);
                return imageView;
            }

            @Override
            public int getXOffset() {
                return super.getXOffset();
            }

            @Override
            public int getYOffset() {
                return 10;
            }
        });
        Guide guide = builder.createGuide();
        guide.setShouldCheckLocInWindow(false);
        guide.show(DemandsPublishActivity.this);
    }


    public void showGuideView2() {


        final GuideBuilder builder1 = new GuideBuilder();
        builder1.setTargetView(llMoreCompanyTypes)
                .setAlpha(150)
                .setHighTargetGraphStyle(Component.ROUNDRECT)
                .setOverlayTarget(false)
                .setOutsideTouchable(false);
        builder1.setOnVisibilityChangedListener(new GuideBuilder.OnVisibilityChangedListener() {
            @Override
            public void onShown() {
            }

            @Override
            public void onDismiss() {
                BaseApplication.set("Is_First_In_Publish",false);
            }
        });

        builder1.addComponent(new SimpleComponent(){
            @Override
            public View getView(LayoutInflater inflater) {
                ImageView imageView = new ImageView(inflater.getContext());
                imageView.setImageResource(R.mipmap.demand_publish_02);
                return imageView;
            }

            @Override
            public int getXOffset() {
                return -60;
            }

            @Override
            public int getYOffset() {
                return -250;
            }
        });
        Guide guide = builder1.createGuide();
        guide.setShouldCheckLocInWindow(false);
        guide.show(DemandsPublishActivity.this);
    }

    @Override
    public void onBackPressed() {
        if (!alertDialog.isShowing()) {
            TLog.log("alertDialog.show();");
            alertDialog.show();
        } else {
            TLog.log("alertDialog.hide();");
            alertDialog.hide();
        }
    }
}
