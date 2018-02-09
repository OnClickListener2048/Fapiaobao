package com.pilipa.fapiaobao.ui;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.method.ReplacementTransformationMethod;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.mylibrary.utils.KeyboardUtils;
import com.example.mylibrary.utils.TLog;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.pilipa.fapiaobao.R;
import com.pilipa.fapiaobao.account.AccountHelper;
import com.pilipa.fapiaobao.adapter.me.SearchCompaniesAdapter;
import com.pilipa.fapiaobao.base.BaseActivity;
import com.pilipa.fapiaobao.base.BaseApplication;
import com.pilipa.fapiaobao.entity.Company;
import com.pilipa.fapiaobao.net.Api;
import com.pilipa.fapiaobao.net.bean.base.BaseResponseBean;
import com.pilipa.fapiaobao.net.bean.invoice.MacherBeanToken;
import com.pilipa.fapiaobao.net.bean.me.CompanyDetailsBean;
import com.pilipa.fapiaobao.net.bean.me.NormalBean;
import com.pilipa.fapiaobao.net.bean.me.search.CompaniesBean;
import com.pilipa.fapiaobao.net.callback.JsonConvertor;
import com.pilipa.fapiaobao.ui.constants.Constant;
import com.pilipa.fapiaobao.ui.zxing.SimpleCaptureActivity;
import com.pilipa.fapiaobao.utils.ButtonUtils;
import com.pilipa.fapiaobao.utils.DialogUtil;
import com.pilipa.fapiaobao.utils.TDevice;

import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by wjn on 2017/10/23.
 */

public class AddCompanyInfoActivity extends BaseActivity implements BaseQuickAdapter.OnItemClickListener, View.OnTouchListener, View.OnFocusChangeListener, PopupWindow.OnDismissListener {
    private static final String TAG = "AddCompanyInfoActivity";
    private static final int REQUEST_CODE_SCAN = 0x0033;
    private static final String DECODED_CONTENT_KEY = "codedContent";
    private static final String DECODED_BITMAP_KEY = "codedBitmap";
    @Bind(R.id.edt_company_name)
    EditText edtCompany_name;
    @Bind(R.id.edt_taxno)
    EditText edtTaxno;
    @Bind(R.id.edt_company_address)
    EditText edtCompanyAddress;
    @Bind(R.id.edt_company_number)
    EditText edtCompanyNumber;
    @Bind(R.id.edt_bank_name)
    EditText edtBankName;
    @Bind(R.id.edt_bank_account)
    EditText edtBankAccount;
    @Bind(R.id.tv_mustfill_company_name)
    TextView tvMustfillCompanyName;
    @Bind(R.id.tv_mustfill_texno)
    TextView tvMustfillTexno;
    @Bind(R.id.tv_mustfill_address)
    TextView tvMustfillAddress;
    @Bind(R.id.tv_mustfill_company_phone)
    TextView tvMustfillCompanyPhone;
    @Bind(R.id.tv_mustfill_bank_name)
    TextView tvMustfillBankName;
    @Bind(R.id.tv_mustfill_bank_number)
    TextView tvMustfillBankNumber;
    @Bind(R.id.btn_save)
    Button btnSave;
    @Bind(R.id.cardView)
    CardView cardView;
    @Bind(R.id.ll_company_name)
    LinearLayout llCompanyName;
    @Bind(R.id.et_dumb)
    EditText mEtDumb;
    private PopupWindow popWnd;
    private SearchCompaniesAdapter adapter;

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
    private Dialog scanDialog;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_company_info_add;
    }


    @OnClick({R.id.add_back, R.id.btn_save, R.id.img_scan})
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.img_scan: {
                startActivityForResult(new Intent(this, SimpleCaptureActivity.class), REQUEST_CODE_SCAN);
            }
            break;
            case R.id.add_back: {
                finish();
            }
            break;
            case R.id.btn_save: {
                if (!ButtonUtils.isFastDoubleClick(R.id.btn_save)) {
                    addCompany();
                }
            }
            break;
            default:
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case REQUEST_CODE_SCAN:

                if (resultCode == RESULT_OK) {

                    String codedContent = data.getStringExtra(DECODED_CONTENT_KEY);

                    TLog.log("getStringExtra(DECODED_CONTENT_KEY) codedContent " + codedContent);
                    if (codedContent.contains(Constant.PROJECT_NAME)) {
                        try {
                            String[] split = codedContent.split("\\?");
                            String[] split1 = split[1].split("=");
                            TLog.d("REQUEST_CODE_SCAN", split[1]);

                            Api.companyDetails(split1[1], new Api.BaseViewCallback<CompanyDetailsBean>() {

                                private CompanyDetailsBean.DataBean data;

                                @Override
                                public void setData(CompanyDetailsBean companyDetailsBean) {
                                    MacherBeanToken.DataBean.CompanyBean companyBean = new MacherBeanToken.DataBean.CompanyBean();
                                    data = companyDetailsBean.getData();
                                    if (data != null) {
                                        companyBean.setAccount(data.getAccount());
                                        companyBean.setAddress(data.getAddress());
                                        companyBean.setDepositBank(data.getDepositBank());
                                        companyBean.setId(data.getId());
                                        companyBean.setIsNewRecord(data.isIsNewRecord());
                                        companyBean.setName(data.getName());
                                        companyBean.setPhone(data.getPhone());
                                        companyBean.setTaxno(data.getTaxno());
                                    }
                                    try {
                                        updateCompanyinfo(companyBean);
                                    } catch (Exception e) {
                                        showDialog(scanDialog);
                                    }
                                }
                            });
                        } catch (Exception e) {
                            e.printStackTrace();
                            showDialog(scanDialog);
                        }
                    } else {
                        showDialog(scanDialog);
                    }

                }
                break;
            default:
        }
    }

    private void updateCompanyinfo(MacherBeanToken.DataBean.CompanyBean companyBean) {
        edtCompany_name.setText(companyBean.getName());
        edtTaxno.setText(companyBean.getTaxno().toUpperCase());
        edtCompanyAddress.setText(companyBean.getAddress());
        edtCompanyNumber.setText(companyBean.getPhone());
        edtBankName.setText(companyBean.getDepositBank());
        edtBankAccount.setText(companyBean.getAccount());
    }

    @Override
    public void initView() {
        edtTaxno.setTransformationMethod(new ReplacementTransformationMethod() {
            @Override
            protected char[] getOriginal() {
                return new char[]{'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
            }

            @Override
            protected char[] getReplacement() {
                return new char[]{'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};
            }
        });


        resets();

        initPopup();

        initDialog();
    }

    private void initDialog() {
        scanDialog = DialogUtil.getInstance().createDialog(this, R.style.BottomDialog, R.layout.layout_scan_tip, new DialogUtil.OnKnownListener() {
            @Override
            public void onKnown(View view) {
                scanDialog.dismiss();
            }
        }, null, null);

    }


    private void startSearching(String companyName, String tag) {
        Api.searchCompanies(companyName, tag, new JsonConvertor<BaseResponseBean<List<CompaniesBean>>>() {

            @Override
            public void onSuccess(Response<BaseResponseBean<List<CompaniesBean>>> response) {
                adapter.setNewData(response.body().getData());
                if (response.body().getData().size() > 4) {
                    popWnd.setHeight((int) TDevice.dp2px(200));
                } else {
                    popWnd.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
                }
                popWnd.update();
                popWnd.showAtLocation(cardView
                        , Gravity.NO_GRAVITY
                        , cardView.getLeft()
                        , llCompanyName.getPaddingBottom()
                                + llCompanyName.getPaddingTop()
                                + llCompanyName.getBottom()
                                + cardView.getTop());
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
        cardView.post(new Runnable() {
            @Override
            public void run() {
                popWnd.setWidth(cardView.getMeasuredWidth());
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

    private void resets() {
        tvMustfillTexno.setVisibility(View.GONE);
        tvMustfillAddress.setVisibility(View.GONE);
        tvMustfillBankName.setVisibility(View.GONE);
        tvMustfillBankNumber.setVisibility(View.GONE);
        tvMustfillCompanyName.setVisibility(View.GONE);
        tvMustfillCompanyPhone.setVisibility(View.GONE);

        boolean isFromPublish = getIntent().getBooleanExtra("isPublish", false);
        boolean paperSpecial = getIntent().getBooleanExtra(DemandsPublishLocationActivity.RECEIPTPAPERSPECIAL_DATA, false);

        if (isFromPublish) {
            tvMustfillTexno.setVisibility(View.VISIBLE);
            tvMustfillAddress.setVisibility(View.VISIBLE);
            tvMustfillBankName.setVisibility(View.VISIBLE);
            tvMustfillBankNumber.setVisibility(View.VISIBLE);
            tvMustfillCompanyName.setVisibility(View.VISIBLE);
            tvMustfillCompanyPhone.setVisibility(View.VISIBLE);

            if (paperSpecial) {
                tvMustfillTexno.setText("必填");
                tvMustfillAddress.setText("必填");
                tvMustfillBankName.setText("必填");
                tvMustfillBankNumber.setText("必填");
                tvMustfillCompanyName.setText("必填");
                tvMustfillCompanyPhone.setText("必填");
            } else {
                tvMustfillTexno.setText("必填");
                tvMustfillAddress.setText("选填");
                tvMustfillAddress.setVisibility(View.GONE);
                tvMustfillBankName.setText("选填");
                tvMustfillBankName.setVisibility(View.GONE);
                tvMustfillBankNumber.setText("选填");
                tvMustfillBankNumber.setVisibility(View.GONE);
                tvMustfillCompanyName.setText("必填");
                tvMustfillCompanyPhone.setText("选填");
                tvMustfillCompanyPhone.setVisibility(View.GONE);
            }
        }

        edtCompany_name.setOnFocusChangeListener(this);
    }

    @Override
    public void initData() {

    }

    public void addCompany() {
        if (TextUtils.isEmpty(edtCompany_name.getText())) {
            BaseApplication.showToast("请输入单位名称");
            return;
        }
        if (TextUtils.isEmpty(edtTaxno.getText())) {
            BaseApplication.showToast("请输入税号".intern());
            return;
        }

        Company company = new Company();
        company.setName(edtCompany_name.getText().toString().trim());
        company.setTaxno(edtTaxno.getText().toString().trim().toUpperCase());
        company.setAddress(edtCompanyAddress.getText().toString().trim());
        company.setPhone(edtCompanyNumber.getText().toString().trim());
        company.setDepositBank(edtBankName.getText().toString().trim());
        company.setAccount(edtBankAccount.getText().toString().trim());
        createCompany(company);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    private void createCompany(final Company company) {


        Api.companyCreate(company, AccountHelper.getToken(), new Api.BaseRawResponse<NormalBean>() {
            @Override
            public void onTokenInvalid() {

            }

            @Override
            public void onStart() {
                btnSave.setEnabled(false);
            }

            @Override
            public void onFinish() {
                btnSave.setEnabled(true);
            }

            @Override
            public void onError() {
                btnSave.setEnabled(true);
            }

            @Override
            public void setData(NormalBean normalBean) {
                if (normalBean.getStatus() == com.pilipa.fapiaobao.net.Constant.REQUEST_SUCCESS) {
                    BaseApplication.showToast(getString(R.string.add_success));
                    setResult(RESULT_OK);
                    AddCompanyInfoActivity.this.finish();
                    TLog.d(TAG, "createCompany;success");
                } else {
                    BaseApplication.showToast(normalBean.getMsg());
                }
            }
        });
    }


    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        edtCompany_name.removeTextChangedListener(textWatcher);
        CompaniesBean companiesBean = (CompaniesBean) adapter.getItem(position);
        if (companiesBean != null) {
            edtCompany_name.setText(companiesBean.getName());
            edtTaxno.setText(companiesBean.getTaxId());
            edtCompanyAddress.setText(companiesBean.getLocation());
            edtBankAccount.setText(companiesBean.getBankAccount());
            edtBankName.setText(companiesBean.getBank());
            edtCompanyNumber.setText(companiesBean.getFixedPhone());
        }
        popWnd.dismiss();
        edtBankAccount.requestFocus();
    }


    @Override
    public boolean onTouch(View v, MotionEvent event) {
        KeyboardUtils.hideSoftInput(this);
        return false;
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        TLog.d(TAG, "keyCode" + keyCode);
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
        TLog.d(TAG, "onKey" + keyCode);
        return super.onKey(dialog, keyCode, event);
    }

    @Override
    public boolean onKeyLongPress(int keyCode, KeyEvent event) {
        TLog.d(TAG, "onKeyLongPress" + keyCode);
        return super.onKeyLongPress(keyCode, event);
    }

    @Override
    public boolean onKeyShortcut(int keyCode, KeyEvent event) {
        TLog.d(TAG, "onKeyShortcut" + keyCode);
        return super.onKeyShortcut(keyCode, event);
    }

    @Override
    public boolean onKeyMultiple(int keyCode, int repeatCount, KeyEvent event) {
        TLog.d(TAG, "onKeyMultiple" + keyCode);
        return super.onKeyMultiple(keyCode, repeatCount, event);
    }

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        TLog.d(TAG, "onKeyUp" + keyCode);
        return super.onKeyUp(keyCode, event);
    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        TLog.d(TAG, "onFocusChange---hasFocus---" + hasFocus);
        if (hasFocus) {
            edtCompany_name.addTextChangedListener(textWatcher);
        }
    }

    @Override
    public void onDismiss() {
    }
}
