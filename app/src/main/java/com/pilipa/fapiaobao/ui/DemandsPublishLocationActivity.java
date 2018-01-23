package com.pilipa.fapiaobao.ui;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Looper;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.AppCompatSpinner;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SwitchCompat;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.method.ReplacementTransformationMethod;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.blog.www.guideview.Component;
import com.blog.www.guideview.Guide;
import com.blog.www.guideview.GuideBuilder;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.mylibrary.utils.KeyboardUtils;
import com.example.mylibrary.utils.RegexUtils;
import com.example.mylibrary.utils.ScreenUtils;
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
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.pilipa.fapiaobao.R;
import com.pilipa.fapiaobao.account.AccountHelper;
import com.pilipa.fapiaobao.adapter.me.PublishSpinnerAdapter;
import com.pilipa.fapiaobao.adapter.me.SearchCompaniesAdapter;
import com.pilipa.fapiaobao.adapter.publish.CompanyListAdapter;
import com.pilipa.fapiaobao.base.BaseApplication;
import com.pilipa.fapiaobao.base.BaseLocationActivity;
import com.pilipa.fapiaobao.entity.Company;
import com.pilipa.fapiaobao.net.Api;
import com.pilipa.fapiaobao.net.Constant;
import com.pilipa.fapiaobao.net.bean.LoginWithInfoBean;
import com.pilipa.fapiaobao.net.bean.base.BaseResponseBean;
import com.pilipa.fapiaobao.net.bean.invoice.DefaultInvoiceBean;
import com.pilipa.fapiaobao.net.bean.invoice.MacherBeanToken;
import com.pilipa.fapiaobao.net.bean.me.CompaniesBean;
import com.pilipa.fapiaobao.net.bean.me.CompanyDetailsBean;
import com.pilipa.fapiaobao.net.bean.me.NormalBean;
import com.pilipa.fapiaobao.net.bean.publish.BalanceBean;
import com.pilipa.fapiaobao.net.bean.publish.DemandsPublishBean;
import com.pilipa.fapiaobao.net.callback.JsonConvertor;
import com.pilipa.fapiaobao.receiver.WXPayReceiver;
import com.pilipa.fapiaobao.ui.component.SimpleComponent;
import com.pilipa.fapiaobao.ui.deco.FinanceItemDeco;
import com.pilipa.fapiaobao.ui.dialog.TimePickerDialog;
import com.pilipa.fapiaobao.ui.widget.CashierInputFilter;
import com.pilipa.fapiaobao.ui.widget.LabelsView;
import com.pilipa.fapiaobao.ui.widget.PreviewPopup;
import com.pilipa.fapiaobao.utils.DialogUtil;
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

public class DemandsPublishLocationActivity extends BaseLocationActivity implements CompoundButton.OnCheckedChangeListener, AdapterView.OnItemSelectedListener, LabelsView.OnLabelSelectChangeListener, CompanyListAdapter.OnCompanyClickListener, View.OnFocusChangeListener, PopupWindow.OnDismissListener, View.OnTouchListener, BaseQuickAdapter.OnItemClickListener {

    public static final int REQUEST_CODE = 300;
    public static final int REQUEST_CODE_FOR_MORE_TYPE = 400;
    public static final String RECEIPTELEC_DATA = "receiptElec";
    public static final String RECEIPTPAPERNORMAL_DATA = "receiptPaperNormal";
    public static final String RECEIPTPAPERSPECIAL_DATA = "receiptPaperSpecial";
    private static final String TAG = "DemandsPublishLocationActivity";
    private static final String DECODED_CONTENT_KEY = "codedContent";
    private static final String DECODED_BITMAP_KEY = "codedBitmap";
    private static final int REQUEST_ADD_COMPANY_INFO = 971;
    private static final int REQUEST_ALL_COMPANY_INFO = 321;
    private static final int REQUEST_CODE_SCAN = 0x0022;
    private static final double MAX_AMOUNT = 50000;
    public CompaniesBean companiesBean;
    @Bind(R.id.bg)
    public View mBg;
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
    RelativeLayout llAmount;
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
    SwitchCompat switchArea;
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
    @Bind(R.id.scrollview)
    NestedScrollView scrollview;
    @Bind(R.id.ll_publish_company_name)
    LinearLayout llPublishCompanyName;
    @Bind(R.id.line)
    View line;
    @Bind(R.id.iv_select_area)
    ImageView ivSelectArea;
    @Bind(R.id.line_above_popup)
    View lineAbovePopup;
    private boolean paperNormal;
    private boolean elec;
    private boolean paperSpecial;
    private TimePickerDialog dialog;
    private CityPickerView cityPicker;
    private CompaniesBean.DataBean dataBean;
    private ArrayList<DefaultInvoiceBean.DataBean> bean;
    private DemandsPublishBean.DemandPostageBean demandPostageBean;
    private CompanyListAdapter companyListAdapter;
    private View view;
    private String tempCompanyId;
    private AlertDialog alertDialog;
    private CityPickerView cityPickerAreaLimited;
    private List<DemandsPublishBean.DemandInvoiceTypeListBean> listBeen;
    private SearchCompaniesAdapter adapter;
    private PopupWindow popWnd;
    private TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            cancelSearching(s.toString());
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            if (s.length() >= 3) {
                startSearching(s.toString(), s.toString());
            }
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };
    private Dialog mDialogTip1;
    private Dialog mDialogTip2;
    private Dialog mScanDialog;
    private PreviewPopup mPreviewPopup;
    private boolean mIsShowExpressLimited;
    public WXPayReceiver wxPayReceiver = new WXPayReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (TextUtils.equals(intent.getAction(), WXPayReceiver.pay_success)) {
                publish();

            } else if (TextUtils.equals(intent.getAction(), WXPayReceiver.pay_fail)) {
                BaseApplication.showToast(getString(R.string.recharge_fail));
            }
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
        InputFilter[] cashierInputFilter = {new CashierInputFilter()};
        etAmount.setFilters(cashierInputFilter);
        etAmountRedbag.setFilters(cashierInputFilter);
        etExpressAmountMinimum.setFilters(cashierInputFilter);

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
        mIsShowExpressLimited = elec && !paperNormal && !paperSpecial;
        llExpressLimited.setVisibility(mIsShowExpressLimited ? View.GONE : View.VISIBLE);
        etAmountRedbag.setNextFocusForwardId(mIsShowExpressLimited ? R.id.et_publish_cautions : R.id.et_express_amount_minimum);
        int fourteenDaysMilliseconds = 14 * 24 * 60 * 60 * 1000;
        etDate.setText(TimeUtils.millis2String(System.currentTimeMillis() + fourteenDaysMilliseconds, TimeUtils.FORMAT));
        dialog = new TimePickerDialog(this);
        Switch.setChecked(true);
        Switch.setOnCheckedChangeListener(this);
        Switch.setTag("Switch");
        switchArea.setChecked(true);
        switchArea.setOnCheckedChangeListener(this);
        switchArea.setTag("SwitchArea");
        llEstimateRequest.setVisibility(View.GONE);
        llToggleSwitch.setVisibility(View.GONE);
        rbCod.setSelected(true);


        etPublishCompanyName.setOnFocusChangeListener(this);
        initPopup();

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
        if ("定位异常，请点击重新定位".equals(location)) {
            BaseApplication.showToast("定位异常，请开启定位功能或手动选择");
        }

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
        demandPostageBean.setProvince(BaseApplication.get("province", null));
        demandPostageBean.setCity(BaseApplication.get("city", null));
        demandPostageBean.setDistrict(BaseApplication.get("district", null));

        findAllLogisticsCompany();
        setUsusallyReceiptkind();
        requestForCompanies();
    }

    private void findAllLogisticsCompany() {
        String[] stringArray = getResources().getStringArray(R.array.express_array);
        spinner.setAdapter(new PublishSpinnerAdapter(stringArray));
    }

    private void setUsusallyReceiptkind() {
        LoginWithInfoBean loginBean = SharedPreferencesHelper.loadFormSource(this, LoginWithInfoBean.class);
        if (loginBean != null) {
            alreadyLogin(loginBean);
        } else {
            hasnLogin();
        }
    }

    private void hasnLogin() {
        Api.<DefaultInvoiceBean>findDefaultInvoiceType(new Api.BaseRawResponseWithCache<DefaultInvoiceBean>() {
            @Override
            public void onCacheSuccess(DefaultInvoiceBean allInvoiceType) {
                if (allInvoiceType.getData() != null && allInvoiceType.getData().size() > 0) {
                    bean = allInvoiceType.getData();
                    ArrayList<String> arrayReceipt = new ArrayList<>();
                    for (DefaultInvoiceBean.DataBean dataBean : bean) {
                        arrayReceipt.add(dataBean.getName());
                    }
                    labelsReceiptKind.setLabels(arrayReceipt);
                }
            }

            @Override
            public void onTokenInvalid() {

            }

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

    private void alreadyLogin(final LoginWithInfoBean loginBean) {
        Api.<DefaultInvoiceBean>findUserInvoiceType(loginBean.getData().getToken(), new Api.BaseRawResponseWithCache<DefaultInvoiceBean>() {
            @Override
            public void onCacheSuccess(DefaultInvoiceBean defaultInvoiceBean) {
                fillupData(defaultInvoiceBean, loginBean);
            }

            @Override
            public void onTokenInvalid() {
                findDefaultInvoiceType();
            }

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
            }

            @Override
            public void setData(DefaultInvoiceBean defaultInvoiceBean) {
                fillupData(defaultInvoiceBean, loginBean);
            }
        });
    }

    private void fillupData(DefaultInvoiceBean defaultInvoiceBean, LoginWithInfoBean loginBean) {
        if (defaultInvoiceBean.getData() != null && defaultInvoiceBean.getData().size() > 0) {
            bean = defaultInvoiceBean.getData();
            ArrayList<String> arrayReceipt = new ArrayList<>();
            for (DefaultInvoiceBean.DataBean dataBean : bean) {
                arrayReceipt.add(dataBean.getName());
            }
            labelsReceiptKind.setLabels(arrayReceipt);
        }
    }

    private void findDefaultInvoiceType() {
        Api.<DefaultInvoiceBean>findDefaultInvoiceType(new Api.BaseRawResponseWithCache<DefaultInvoiceBean>() {
            @Override
            public void onCacheSuccess(DefaultInvoiceBean allInvoiceType) {
                if (allInvoiceType.getData() != null && allInvoiceType.getData().size() > 0) {
                    bean = allInvoiceType.getData();
                    ArrayList<String> arrayReceipt = new ArrayList<>();
                    for (DefaultInvoiceBean.DataBean dataBean : bean) {
                        arrayReceipt.add(dataBean.getName());
                    }
                    labelsReceiptKind.setLabels(arrayReceipt);
                }
            }

            @Override
            public void onTokenInvalid() {

            }

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
//            , R.id.tv_area, R.id.iv_select_area
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
                        String date = dialog.getYear() + "-" + dialog.getMonth() + "-" + dialog.getDay();
                        long selectTime = TimeUtils.string2Millis(date, TimeUtils.FORMAT);
                        TLog.log("selectTime" + selectTime);
                        String nowString = TimeUtils.getNowString(TimeUtils.FORMAT);
                        long today = TimeUtils.string2Millis(nowString, TimeUtils.FORMAT);
                        TLog.log("selectTime - currentTimeMillis" + (selectTime - today));
                        if (selectTime - today < 0) {
                            BaseApplication.showToast(getString(R.string.cant_choose_last_day));
                            return;
                        }
                        etDate.setText(date);
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
                showTip1Dialog();
                break;
            case R.id.tv_express_limited:
                showTip2Dialog();
                break;
            default:
        }
    }

    private void publish() {

        if (checkParams()) {
            if (makeParams() != null) {
                AccountHelper.isTokenValid(new Api.BaseRawResponse<LoginWithInfoBean>() {
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

                    }

                    @Override
                    public void onTokenInvalid() {
                        login();
                    }

                    @Override
                    public void setData(LoginWithInfoBean loginWithInfoBean) {
                        AccountHelper.updateCustomer(loginWithInfoBean.getData().getCustomer());
                        createPopup();
                        mPreviewPopup.showPopupWindow(DemandsPublishLocationActivity.this);
                    }
                });
            }
        }
    }

    private boolean isAccountSufficient() {
        return !Switch.isChecked() || AccountHelper.getUser().getData().getCustomer().getAvailiableBalance() >= Double.valueOf(getTextViewValue(etAmountRedbag));
    }

    private void createPopup() {
        mPreviewPopup = new PreviewPopup.PopupBuilder(this)
                .setCompanyName(getTextViewValue(etPublishCompanyName))
                .setTexNumber(getTextViewValue(etPublishTexNumber))
                .setCompanyAddress(getTextViewValue(etPublishAddress))
                .setPhoneNumber(getTextViewValue(etPublishPhoneNumber))
                .setDepositBank(getTextViewValue(etPublishBank))
                .setDepositBankAccount(getTextViewValue(etPublishBankAccount))
                .setInvoiceKind(getInvoiceKinds())
                .setInvoiceType(getInvoiceTypes())
                .setDeadline(getTextViewValue(etDate))
                .setDemandAmount(getString(R.string.point_two, Double.valueOf(getTextViewValue(etAmount))))
                .setBonus(Switch.isChecked() ? getString(R.string.point_two, Double.valueOf(getTextViewValue(etAmountRedbag))) : "")
                .setAvailableBalance(getString(R.string.point_two, AccountHelper.getUser().getData().getCustomer().getAvailiableBalance()))
                .setBalanceSufficient(isAccountSufficient())
                .setInvoiceArea(switchArea.isChecked() ? getTextViewValue(tvAreaLimited) : "")
                .setIsShowExpressInfo(paperNormal || paperSpecial)
                .setReceiption(getTextViewValue(etReceptionName))
                .setReceiptionAddress(getTextViewValue(tvArea) + "  " + getTextViewValue(etAreaDetails))
                .setReceiptionPhoneNumber(getTextViewValue(etReceptionNumber))
                .setShowExpressLimited(!mIsShowExpressLimited)
                .setExpressLimited(mIsShowExpressLimited ? "" : getString(R.string.point_two, Double.valueOf(getTextViewValue(etExpressAmountMinimum))))
                .setCautions(getTextViewValue(etPublishCautions))
                .setOnBalanceInsufficientListener(new PreviewPopup.OnBalanceInsufficientListener() {
                    @Override
                    public void onBalanceInsufficient() {
                        mPreviewPopup.dismiss();
                        Intent intent = new Intent();
                        intent.setClass(DemandsPublishLocationActivity.this, RechargeActivity.class);
                        startActivityForResult(intent, REQUEST_CODE);
                    }
                })
                .setOnPublishListener(new PreviewPopup.OnPublishListener() {
                    @Override
                    public void onPublish() {
                        mPreviewPopup.dismiss();
                        final Gson gson = new GsonBuilder().serializeNulls().create();
                        Api.publish(gson.toJson(makeParams()), new Api.BaseRawResponse<BalanceBean>() {
                            @Override
                            public void onTokenInvalid() {
                                login();
                            }

                            @Override
                            public void onStart() {
                                btnPublishNow.setEnabled(false);
                                showProgressDialog();
                            }

                            @Override
                            public void onFinish() {
                                hideProgressDialog();
                                btnPublishNow.setEnabled(true);
                            }

                            @Override
                            public void onError() {
                                hideProgressDialog();
                            }

                            @Override
                            public void setData(BalanceBean balanceBean) {
                                Intent intent = new Intent();
                                if (balanceBean.getStatus() == Constant.REQUEST_SUCCESS) {
                                    intent.putExtra("demand", balanceBean.getData().getDemand());
                                    intent.setClass(DemandsPublishLocationActivity.this, PubSuccessActivity.class);
                                    startActivity(intent);
                                    finish();
                                } else if (balanceBean.getStatus() == Constant.INSUFFICIENT_ACCOUNT) {
                                    BaseApplication.showToast("账户余额不足，请先充值");
                                    intent.setClass(DemandsPublishLocationActivity.this, RechargeActivity.class);
                                    startActivityForResult(intent, REQUEST_CODE);
                                } else if (balanceBean.getStatus() == 885) {
                                    BaseApplication.showToast("可用金额不足，已发红包占用中");
                                    intent.setClass(DemandsPublishLocationActivity.this, RechargeActivity.class);
                                    startActivityForResult(intent, REQUEST_CODE);
                                } else if (balanceBean.getStatus() == 400) {
                                    BaseApplication.showToast("截止日期不能小于当前时间");
                                } else if (balanceBean.getStatus() == 406) {
                                    BaseApplication.showToast("红包不能超过需求总金额的5%");
                                }
                            }
                        });
                    }
                }).setOnBackToReviseListener(new PreviewPopup.OnBackToReviseListener() {
                    @Override
                    public void onBackToRevice() {
                        mPreviewPopup.dismiss();
                    }
                }).build();
    }

    private String getInvoiceTypes() {
        String type = "";
        String concat = "";
        for (DemandsPublishBean.DemandInvoiceTypeListBean demandInvoiceTypeListBean : listBeen) {
            concat = concat + type.concat(demandInvoiceTypeListBean.getInvoiceType().getName() + " ");
        }
        return concat;
    }

    private String getInvoiceKinds() {
        String invoiceKinds = "";
        if (paperNormal) {
            invoiceKinds = invoiceKinds.concat(getString(R.string.paper_normal_receipt));
        }

        if (paperSpecial) {
            invoiceKinds = invoiceKinds.concat(" " + getString(R.string.paper_special_receipt));
        }

        if (elec) {
            invoiceKinds = invoiceKinds.concat(" " + getString(R.string.paper_elec_receipt));
        }
        return invoiceKinds;
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

        Api.companyCreate(company, AccountHelper.getToken(), new Api.BaseRawResponse<NormalBean>() {
            @Override
            public void onTokenInvalid() {

            }

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

            }

            @Override
            public void setData(NormalBean normalBean) {
                if (normalBean.getStatus() == Constant.REQUEST_SUCCESS) {
                    BaseApplication.showToast(getString(R.string.add_success));
                    setResult(RESULT_OK);
                    Log.d(TAG, "createCompany;success");
                }
            }
        });
    }

    private void requestForMoreTypes() {
        Intent intent = new Intent();
        Bundle bundle = new Bundle();
        bundle.putSerializable(com.pilipa.fapiaobao.ui.constants.Constant.MORE_TYPES, bean);
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
                    BaseApplication.showToast(getString(R.string.insufficient_account));
                } else if (RESULT_OK == resultCode) {
                }
                break;
            case REQUEST_CODE_FOR_MORE_TYPE:
                if (resultCode == RESULT_OK) {
                    if (data != null) {
                        Bundle bundle = data.getBundleExtra(MoreTypesActivity.EXTRA_BUNDLE);
                        bean = (ArrayList<DefaultInvoiceBean.DataBean>) bundle.getSerializable(com.pilipa.fapiaobao.ui.constants.Constant.MORE_TYPES);
                        ArrayList<String> arrayReceipt = new ArrayList<>();
                        for (DefaultInvoiceBean.DataBean dataBean : bean) {
                            arrayReceipt.add(dataBean.getName());
                        }
                        labelsReceiptKind.setLabels(arrayReceipt);
                    }
                }
                break;
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
                    TLog.log("getStringExtra(DECODED_CONTENT_KEY) codedContent " + content);

                    if (content.contains("fapiaobao")) {
                        TLog.d("content", content);
                        try {
                            String[] split = content.split("\\?");
                            String[] split1 = null;
                            split1 = split[1].split("=");
                            TLog.d("REQUEST_CODE_SCAN", split[1]);
                            Api.companyDetails(split1[1], new Api.BaseViewCallback<CompanyDetailsBean>() {

                                private CompanyDetailsBean.DataBean data;

                                @Override
                                public void setData(CompanyDetailsBean companyDetailsBean) {

                                    try {
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
                                    } catch (Exception e) {
                                        showScanDialog();
                                    }
                                }
                            });
                        } catch (Exception e) {
                            e.printStackTrace();
                            showScanDialog();
                        }

                    } else {
                        showScanDialog();
                    }
                }
                break;
            default:
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
        scrollview.requestFocus();
    }

    private DemandsPublishBean makeParams() {
        LoginWithInfoBean loginWithInfoBean = SharedPreferencesHelper.loadFormSource(this, LoginWithInfoBean.class);
        DemandsPublishBean bean = new DemandsPublishBean();

        if (switchArea.isChecked()) {
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
        companyBean.setAccount(getTextViewValue(etPublishBankAccount));
        companyBean.setAddress(getTextViewValue(etPublishAddress));
        companyBean.setTaxno(getTextViewValue(etPublishTexNumber).toUpperCase());
        companyBean.setName(getTextViewValue(etPublishCompanyName));
        companyBean.setDepositBank(getTextViewValue(etPublishBank));
        companyBean.setPhone(getTextViewValue(etPublishPhoneNumber));
        bean.setCompany(companyBean);

        bean.setDemandInvoiceTypeList(listBeen);

        demandPostageBean.setAddress(getTextViewValue(etAreaDetails));
        demandPostageBean.setProvince(getTextViewValue(tvArea));
        demandPostageBean.setPhone(getTextViewValue(etReceptionNumber));
        demandPostageBean.setReceiver(getTextViewValue(etReceptionName));
        demandPostageBean.setTelephone(getTextViewValue(etReceptionNumber));

        BaseApplication.set("etAreaDetails", getTextViewValue(etAreaDetails));
        BaseApplication.set("tvArea", getTextViewValue(tvArea));
        BaseApplication.set("etReceptionNumber", getTextViewValue(etReceptionNumber));
        BaseApplication.set("etReceptionName", getTextViewValue(etReceptionName));


        bean.setDemandPostage(demandPostageBean);

        bean.setAttentions(getTextViewValue(etPublishCautions));
        bean.setDeadline(getTextViewValue(etDate));
        if (paperNormal || paperSpecial) {
            bean.setMailMinimum(Double.valueOf(getTextViewValue(etExpressAmountMinimum)));
        }
        if (Switch.isChecked()) {
            if (!getTextViewValue(etAmountRedbag).isEmpty()) {
                bean.setTotalBonus(Double.parseDouble(getTextViewValue(etAmountRedbag)));
            } else {
                bean.setTotalBonus(0);
            }
        } else {
            bean.setTotalBonus(0);
        }
        if (!getTextViewValue(etAmount).isEmpty()) {
            bean.setTotalAmount(Double.parseDouble(getTextViewValue(etAmount)));
        }
        TLog.log(bean.toString());
        return bean;
    }

    private boolean checkInvoiceTypes() {
        llMoreCompanyTypes.requestFocus();
        listBeen = new ArrayList<>();
        for (DefaultInvoiceBean.DataBean defaultBean : this.bean) {
            if (defaultBean.isSelect()) {
                DemandsPublishBean.DemandInvoiceTypeListBean demandInvoiceTypeListBean = new DemandsPublishBean.DemandInvoiceTypeListBean();
                DemandsPublishBean.DemandInvoiceTypeListBean.InvoiceTypeBeanX invoiceTypeBeanX = new DemandsPublishBean.DemandInvoiceTypeListBean.InvoiceTypeBeanX();
                invoiceTypeBeanX.setId(defaultBean.getId());
                invoiceTypeBeanX.setName(defaultBean.getName());
                demandInvoiceTypeListBean.setInvoiceType(invoiceTypeBeanX);
                listBeen.add(demandInvoiceTypeListBean);
            }
        }
        if (listBeen.size() == 0) {
            llMoreCompanyTypes.requestFocus();
            llMoreCompanyTypes.setBackgroundResource(R.drawable.bg_error_filling);
            sooothScrollToView(llMoreCompanyTypes);
            BaseApplication.showToast("请指定一种发票类型");
            return false;
        } else {
            llMoreCompanyTypes.setBackground(null);
            return true;
        }
    }

    private boolean checkParams() {
        if (llAddCompanyInfo.getVisibility() == View.VISIBLE) {
            llAddCompanyInfo.setBackgroundResource(R.drawable.bg_error_filling);
            sooothScrollToView(llAddCompanyInfo);
            BaseApplication.showToast("请选择或添加一家开票单位信息");
            return false;
        } else {
            llAddCompanyInfo.setBackground(null);
        }

        if (checkIfIsEmpty(etPublishCompanyName)) {
            BaseApplication.showToast("单位名称不能为空");
            return false;
        } else {
            resetBackground(etPublishCompanyName);
        }

        if (checkIfIsEmpty(etPublishTexNumber)) {
            BaseApplication.showToast("税号不能为空");
            return false;
        } else {
            resetBackground(etPublishTexNumber);
        }

        if (paperSpecial) {
            String str = "请完善必填信息";
            if (checkIfIsEmpty(etPublishAddress)) {
                BaseApplication.showToast(str);
                return false;
            } else {
                resetBackground(etPublishAddress);
            }
            if (checkIfIsEmpty(etPublishPhoneNumber)) {
                BaseApplication.showToast(str);
                return false;
            } else {
                resetBackground(etPublishPhoneNumber);
            }
            if (checkIfIsEmpty(etPublishBank)) {
                BaseApplication.showToast(str);
                return false;
            } else {
                resetBackground(etPublishBank);
            }
            if (checkIfIsEmpty(etPublishBankAccount)) {
                BaseApplication.showToast(str);
                return false;
            } else {
                resetBackground(etPublishBankAccount);
            }

        }

        if (!checkInvoiceTypes()) {
            return false;
        }

        if (checkIfIsEmpty(etAmount)) {
            BaseApplication.showToast("需求总额不能为空");
            return false;
        }
        if (Double.valueOf(getTextViewValue(etAmount)) > MAX_AMOUNT) {
            BaseApplication.showToast("单次需求总额不得超过50000元");
            sooothScrollToView(etAmount);
            setErrorBackground(etAmount);
            return false;
        }
        if (Switch.isChecked()) {
            if (checkIfIsEmpty(etAmountRedbag)) {
                BaseApplication.showToast("悬赏红包不能为空");
                sooothScrollToView(etAmountRedbag);
                return false;
            }

            if ((Double.valueOf(getTextViewValue(etAmountRedbag))
                    > Double.valueOf(getTextViewValue(etAmount)) * 0.05)) {
                BaseApplication.showToast("悬赏红包不能超过需求总额的5%");
                setErrorBackground(etAmountRedbag);
                sooothScrollToView(etAmountRedbag);
                return false;
            } else {
                etAmountRedbag.setBackgroundResource(R.drawable.shape_rect_demand_info);
            }
        }


        if (switchArea.isChecked()) {
            tvAreaLimited.requestFocus();
            if ("定位失败，点击选择地区".equals(tvAreaLimited.getText().toString())) {
                BaseApplication.showToast("限制开票区域定位异常，请开启定位功能或手动选择开票地区");
                sooothScrollToView(switchArea);
                cityPickerAreaLimited.show();
                return false;
            }
        }

        if (llExpressLimited.getVisibility() == View.VISIBLE) {
            if (checkIfIsEmpty(etExpressAmountMinimum)) {
                BaseApplication.showToast("最少邮寄限额不能为空");
                return false;
            }

            if (!etExpressAmountMinimum.getText().toString().isEmpty()
                    && Double.valueOf(getTextViewValue(etExpressAmountMinimum))
                    > Double.valueOf(getTextViewValue(etAmount))) {
                BaseApplication.showToast("最少寄送限额必须小于等于需求总额");
                sooothScrollToView(etExpressAmountMinimum);
                return false;
            }
        }


        if (paperNormal || paperSpecial) {
            if (view.getVisibility() == View.VISIBLE) {
                if (checkIfIsEmpty(etReceptionName)) {
                    BaseApplication.showToast("收件人姓名不能为空");
                    return false;
                } else {
                    resetBackground(etReceptionName);
                }

                if (checkIfIsEmpty(etReceptionNumber)) {
                    BaseApplication.showToast("收件人手机号不能为空");
                    return false;
                } else if (!RegexUtils.isTel(getTextViewValue(etReceptionNumber)) && !RegexUtils.isMobileExact(getTextViewValue(etReceptionNumber))) {
                    BaseApplication.showToast("格式不正确，请填写“区号-座机号”或“手机号码”哦~");
                    setErrorBackground(etReceptionNumber);
                    return false;
                } else {
                    resetBackground(etReceptionNumber);
                }


                if (checkIfIsEmpty(tvArea)) {
                    BaseApplication.showToast("所在地区不能为空");
                    cityPicker.show();
                    return false;
                } else {
                    resetBackground(tvArea);
                }

                if (checkIfIsEmpty(etAreaDetails)) {
                    BaseApplication.showToast("详细地址不能为空");
                    return false;
                } else {
                    resetBackground(etAreaDetails);
                }
            }
        }
        return true;
    }

    private void setErrorBackground(View view) {
        view.setBackgroundResource(R.drawable.bg_error_filling);
    }

    private void resetBackground(View view) {
        view.setBackgroundDrawable(null);
    }

    private boolean checkIfIsEmpty(TextView editText) {
        boolean b = TextUtils.isEmpty(editText.getText());
        scrollview.setSmoothScrollingEnabled(true);
        scrollview.smoothScrollTo(0, getPixelsWithinScrollView(editText) + editText.getHeight() - ScreenUtils.getScreenHeight() / 2);
        if (b) {
            if (editText instanceof EditText) {
                editText.requestFocus();
            }
            editText.setBackgroundResource(R.drawable.bg_error_filling);
        } else {
            editText.setBackgroundResource(R.drawable.shape_rect_demand_info);
        }
        return b;
    }

    private void sooothScrollToView(View view) {
        scrollview.setSmoothScrollingEnabled(true);
        scrollview.smoothScrollTo(0, getPixelsWithinScrollView(view) + view.getHeight() - ScreenUtils.getScreenHeight() / 2);
    }

    private String getTextViewValue(TextView view) {
//        boolean b = (view == etAmountRedbag
//                || view == etAmount
//                || view == etExpressAmountMinimum)
//                && view.getText().toString().trim().endsWith(getString(R.string.dot));
//        if (b) {
//            return view.getText().toString().replace(getString(R.string.dot), "");
//        }
        return view.getText().toString().trim();
    }

    private int getPixelsWithinScrollView(View view) {
        int totalPixels = 0;
        totalPixels += view.getTop();
        ViewGroup viewGroup = (ViewGroup) view.getParent();
        if (viewGroup instanceof NestedScrollView) {
            return totalPixels;
        } else {
            totalPixels += getPixelsWithinScrollView(viewGroup);
        }
        return totalPixels;
    }


    private void showTip1Dialog() {
        if (mDialogTip1 == null) {
            mDialogTip1 = DialogUtil.getInstance().createDialog(this, 0, R.layout.layout_extimate_tip1, new DialogUtil.OnKnownListener() {
                @Override
                public void onKnown(View view) {
                    mDialogTip1.dismiss();
                }
            }, null, null);
        }
        showDialog(mDialogTip1);
    }

    private void showTip2Dialog() {
        if (mDialogTip2 == null) {
            mDialogTip2 = DialogUtil.getInstance().createDialog(this, 0, R.layout.layout_extimate_tip2, new DialogUtil.OnKnownListener() {
                @Override
                public void onKnown(View view) {
                    mDialogTip2.dismiss();
                }
            }, null, null);
        }
        showDialog(mDialogTip2);
    }

    private void showScanDialog() {
        if (mScanDialog == null) {
            mScanDialog = DialogUtil.getInstance().createDialog(this, 0, R.layout.dialog_scan_tip, new DialogUtil.OnKnownListener() {
                @Override
                public void onKnown(View view) {

                }
            }, null, null);
        }
        showDialog(mScanDialog);
    }

    private void requestForCompanies() {

        Api.companiesList(AccountHelper.getToken(), this, new Api.BaseRawResponse<CompaniesBean>() {


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

            }

            @Override
            public void onTokenInvalid() {

            }

            @Override
            public void setData(CompaniesBean companiesBean) {
                DemandsPublishLocationActivity.this.companiesBean = companiesBean;
                if (companiesBean.getStatus() == Constant.REQUEST_SUCCESS && companiesBean.getData() != null) {
                    if (companiesBean.getData().size() == 1) {
                        updateCompanyInfo(companiesBean.getData().get(0));

                    } else {
                        companyListAdapter.addCompanyInfo(companiesBean.getData());
                        if (companiesBean.getData().size() > 5) {
                            moreCompany.setVisibility(View.VISIBLE);
                        }
                    }

                } else if (companiesBean.getStatus() == Constant.REQUEST_NO_CONTENT) {

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


    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if ("SwitchArea".equals(buttonView.getTag())) {
            llArea.setVisibility(isChecked ? View.VISIBLE : View.GONE);
            llEstimateRequest.setVisibility(isChecked ? View.GONE : View.VISIBLE);
        } else if ("Switch".equals(buttonView.getTag())) {
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
        CityConfig cityConfig = new CityConfig.Builder(DemandsPublishLocationActivity.this)
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


        CityConfig cityConfig2 = new CityConfig.Builder(DemandsPublishLocationActivity.this)
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

        builder.addComponent(new SimpleComponent() {
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
        guide.show(DemandsPublishLocationActivity.this);
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
                BaseApplication.set("Is_First_In_Publish", false);
            }
        });

        builder1.addComponent(new SimpleComponent() {
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
        guide.show(DemandsPublishLocationActivity.this);
    }


    @Override
    public void onBackPressed() {
        if (mPreviewPopup != null && mPreviewPopup.isShowing()) {
            mPreviewPopup.dismiss();
            return;
        }
        if (!alertDialog.isShowing()) {
            TLog.log("alertDialog.show();");
            alertDialog.show();
        } else {
            TLog.log("alertDialog.hide();");
            alertDialog.hide();
        }
    }

    private void startSearching(String companyName, String tag) {
        Api.searchCompanies(companyName, tag, new JsonConvertor<BaseResponseBean<List<com.pilipa.fapiaobao.net.bean.me.search.CompaniesBean>>>() {

            @Override
            public void onSuccess(Response<BaseResponseBean<List<com.pilipa.fapiaobao.net.bean.me.search.CompaniesBean>>> response) {
                adapter.setNewData(response.body().getData());
                if (response.body().getData().size() > 4) {
                    popWnd.setHeight((int) TDevice.dp2px(200));
                } else {
                    popWnd.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
                }
                popWnd.update();

                popWnd.showAtLocation(llPublishCompanyName
                        , Gravity.NO_GRAVITY
                        , llPublishCompanyName.getLeft()
                        , scrollview.getTop()
                                + getPixelsWithinScrollView(lineAbovePopup)
                                + lineAbovePopup.getHeight()
                                + llPublishCompanyName.getHeight());
            }

            @Override
            protected void onNoContent() {
                super.onNoContent();
                popWnd.dismiss();
            }
        });
    }

    private void cancelSearching(String tag) {
        TLog.log("cancelSearching===" + tag);
        OkGo.cancelTag(OkGo.getInstance().getOkHttpClient(), tag);
    }

    private void initPopup() {
        View popupContentView = LayoutInflater.from(this).inflate(R.layout.layout_search_companies, null);
        RecyclerView recyclerView1 = (RecyclerView) popupContentView.findViewById(R.id.recyclerView);
        recyclerView1.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recyclerView1.setItemAnimator(new DefaultItemAnimator());
        recyclerView1.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        adapter = new SearchCompaniesAdapter(R.layout.item_search_companies);
        adapter.setOnItemClickListener(this);
        recyclerView1.setAdapter(adapter);
        popWnd = new PopupWindow(this);
        popWnd.setContentView(popupContentView);
        popWnd.setBackgroundDrawable(getResources().getDrawable(R.drawable.shape_search_companies_pop));
        scrollview.post(new Runnable() {
            @Override
            public void run() {
                TLog.d(TAG, " popWnd.setWidth(viewGroup.getMeasuredWidth());----" + scrollview.getMeasuredWidth());
                popWnd.setWidth((scrollview.getMeasuredWidth() - (int) TDevice.dp2px(30)));
            }
        });
        popWnd.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        popWnd.setInputMethodMode(PopupWindow.INPUT_METHOD_NEEDED);
        popWnd.setOutsideTouchable(true);
        popWnd.setTouchable(true);
        popWnd.setTouchInterceptor(this);
        popWnd.setOnDismissListener(this);
        popWnd.update();
    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        if (hasFocus) {
            etPublishCompanyName.addTextChangedListener(textWatcher);
        }
    }

    @Override
    public void onDismiss() {

    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        KeyboardUtils.hideSoftInput(this);
        return false;
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        etPublishCompanyName.removeTextChangedListener(textWatcher);
        com.pilipa.fapiaobao.net.bean.me.search.CompaniesBean companiesBean = (com.pilipa.fapiaobao.net.bean.me.search.CompaniesBean) adapter.getItem(position);
        if (companiesBean != null) {
            etPublishCompanyName.setText(companiesBean.getNsrmc());
            etPublishTexNumber.setText(companiesBean.getNsrsbh());
        }
        popWnd.dismiss();
        etPublishAddress.requestFocus();
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        if (mPreviewPopup != null && mPreviewPopup.isShowing()) {
            return false;
        }
        return super.dispatchTouchEvent(event);
    }
}
