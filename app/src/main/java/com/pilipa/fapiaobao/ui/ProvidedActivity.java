package com.pilipa.fapiaobao.ui;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.pilipa.fapiaobao.R;
import com.pilipa.fapiaobao.account.AccountHelper;
import com.pilipa.fapiaobao.adapter.PublishSpinnerAdapter;
import com.pilipa.fapiaobao.base.BaseActivity;
import com.pilipa.fapiaobao.base.BaseApplication;
import com.pilipa.fapiaobao.net.Api;
import com.pilipa.fapiaobao.net.bean.LoginWithInfoBean;
import com.pilipa.fapiaobao.net.bean.TestBean;
import com.pilipa.fapiaobao.net.bean.invoice.CompanyCollectBean;
import com.pilipa.fapiaobao.net.bean.me.NormalBean;
import com.pilipa.fapiaobao.net.bean.me.OrderDetailsBean;
import com.pilipa.fapiaobao.net.bean.publish.ExpressCompanyBean;
import com.pilipa.fapiaobao.ui.fragment.DemandsDetailsReceiptFragment;
import com.pilipa.fapiaobao.ui.fragment.DemandsDetailsReceiptFragment2;
import com.pilipa.fapiaobao.ui.fragment.DemandsDetailsReceiptFragment3;
import com.pilipa.fapiaobao.ui.model.Image;
import com.pilipa.fapiaobao.utils.SharedPreferencesHelper;
import com.pilipa.fapiaobao.zxing.android.CaptureActivity;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.pilipa.fapiaobao.net.Constant.REQUEST_SUCCESS;
import static com.pilipa.fapiaobao.net.Constant.STATE_FLYING;
import static com.pilipa.fapiaobao.net.Constant.STATE_GONE;
import static com.pilipa.fapiaobao.net.Constant.STATE_GOT_ALL;
import static com.pilipa.fapiaobao.net.Constant.STATE_GOT_PARTIALITY;
import static com.pilipa.fapiaobao.net.Constant.VARIETY_GENERAL_ELECTRON;
import static com.pilipa.fapiaobao.net.Constant.VARIETY_GENERAL_PAPER;
import static com.pilipa.fapiaobao.net.Constant.VARIETY_SPECIAL_PAPER;

/**
 * Created by wjn on 2017/10/24.
 */

public class ProvidedActivity extends BaseActivity {
    private static final String TAG = "ProvidedActivity";

    @Bind(R.id.container_paper_normal_receipt)
    FrameLayout containerPaperNormalReceipt;
    @Bind(R.id.container_paper_special_receipt)
    FrameLayout containerPaperSpecialReceipt;
    @Bind(R.id.container_paper_elec_receipt)
    FrameLayout containerPaperElecReceipt;
    @Bind(R.id.btn_confirm)
    Button btnConfirm;
    @Bind(R.id.tv_invoiceType)
    TextView tvInvoiceType;
    @Bind(R.id.tv_arrival_state)
    TextView tvArrivalState;

    @Bind(R.id.tv_receiver)
    TextView tvReceiver;
    @Bind(R.id.tv_telephone)
    TextView tvTelephone;
    @Bind(R.id.tv_phone)
    TextView tvPhone;
    @Bind(R.id.tv_publish_address)
    TextView tvPublishAddress;
    @Bind(R.id.edt_oddNumber)
    EditText edtOddNumber;

    @Bind(R.id.receipt_number)
    TextView receiptNumber;
    @Bind(R.id.receipt_money)
    TextView receiptMoney;
    @Bind(R.id.estimate_money)//预计红包数
    TextView estimateMoney;
    @Bind(R.id.continue_to_upload)
    TextView continueToUpload;

    @Bind(R.id.collect)
    ImageView collect;
    @Bind(R.id.company_name)
    TextView companyName;
    @Bind(R.id.tex_number)
    TextView texNumber;
    @Bind(R.id.company_address)
    TextView companyAddress;
    @Bind(R.id.number)
    TextView number;
    @Bind(R.id.bank_account)
    TextView bankAccount;
    @Bind(R.id.bank)
    TextView bank;

    public static final String PAPER_NORMAL_RECEIPT_DATA = "paper_normal_receipt_data";
    public static final String PAPER_SPECIAL_RECEIPT_DATA = "paper_special_receipt_data";
    public static final String PAPER_ELEC_RECEIPT_DATA = "paper_elec_receipt_data";


    private ArrayList<Image> images;
    private DemandsDetailsReceiptFragment paperNormalReceiptFragment;
    private DemandsDetailsReceiptFragment2 paperSpecialReceiptFragment;
    private DemandsDetailsReceiptFragment3 paperElecReceiptFragment;
    private static final int REQUEST_CODE_SCAN = 0x0000;
    @Bind(R.id.translate_details)
    LinearLayout translateDetails;
    @Bind(R.id.translate)
    LinearLayout translate;
    @Bind(R.id.mSpinner)
    Spinner mSpinner;
    private boolean isShow = false;//当前详情是否显示
    boolean isCollected;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_provided;
    }

    @OnClick({R.id.provided_back, R.id.fl_change, R.id.btn_confirm,R.id.collect,R.id.btn_mailing,R.id.btn_scan})

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_scan: {
                startActivityForResult(new Intent(this, CaptureActivity.class), REQUEST_CODE_SCAN);
            }break;
            case R.id.btn_mailing: {
                mailInvoice();
            }break;
            case R.id.provided_back: {
                finish();
            } break;
            case R.id.btn_confirm: {

            } break;
            case R.id.fl_change: {
                if (isShow) {
                    translateDetails.setVisibility(View.VISIBLE);
                    translate.setVisibility(View.GONE);
                    isShow = !isShow;
                } else {
                    translateDetails.setVisibility(View.GONE);
                    translate.setVisibility(View.VISIBLE);
                    isShow = !isShow;
                }
            }
            break;
            case R.id.collect:
                AccountHelper.isTokenValid(new Api.BaseViewCallback<LoginWithInfoBean>() {

                    @Override
                    public void setData(LoginWithInfoBean normalBean) {
                        if (normalBean.getStatus() == 200) {
                            if (isCollected) {
                                Api.deleteFavoriteCompany(CompanyId, AccountHelper.getToken(), new Api.BaseViewCallback<NormalBean>() {
                                    @Override
                                    public void setData(NormalBean normalBean) {
                                        if (normalBean.getStatus() == 200) {
                                            BaseApplication.showToast("删除收藏成功");
                                            isCollected = false;
                                            collect.setImageResource(R.mipmap.collect);
                                        }
                                    }
                                });
                            } else {
                                CompanyCollectBean companyCollectBean = new CompanyCollectBean();
                                CompanyCollectBean.CompanyBean companyBean = new CompanyCollectBean.CompanyBean();
                                companyBean.setId(CompanyId);
                                companyCollectBean.setCompany(companyBean);
                                companyCollectBean.setToken(normalBean.getData().getToken());

                                Api.favCompanyCreate(companyCollectBean, new Api.BaseViewCallback<NormalBean>() {
                                    @Override
                                    public void setData(NormalBean normalBean) {
                                        if (normalBean.getStatus() == 200) {
                                            BaseApplication.showToast("收藏成功");
                                            isCollected = true;
                                            collect.setImageResource(R.mipmap.collected);
                                        }
                                    }
                                });
                            }
                        } else {
                            BaseApplication.showToast("token验证失败请重新登录");
                            startActivity(new Intent(ProvidedActivity.this, LoginActivity.class));
                            finish();
                        }
                    }
                });


                break;
        }
    }

    @Override
    public void initView() {
        Api.findAllLogisticsCompany(new Api.BaseViewCallback<ExpressCompanyBean>() {
            @Override
            public void setData(ExpressCompanyBean expressCompanyBean) {
                Log.d(TAG, "setData: initDatainitDatainitDatainitDatainitDatainitDatainitDatainitDatainitDatainitDatainitData");
                mSpinner.setAdapter(new PublishSpinnerAdapter(expressCompanyBean));
            }
        });

//        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.simple_spinner_item);
//        String level[] = getResources().getStringArray(R.array.express);//资源文件
//        for (int i = 0; i < level.length; i++) {
//            adapter.add(level[i]);
//        }
//        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        mSpinner.setAdapter(adapter);
    }

    private void setUpData(ArrayList<TestBean.ResultsBean> results) {
        Log.d(TAG, "setUpData:   private void setUpData(ArrayList<model.ResultsBean> body) {");
        images = new ArrayList<>();
        for (TestBean.ResultsBean result : results) {
            Log.d(TAG, "setUpData:  for (model.ResultsBean result : body) {");
            Image image = new Image();
            image.isSelected = false;
            image.name = result.get_id();
            image.path = result.getUrl();
            image.position = -1;
            image.isCapture = false;
            image.isFromNet = true;
//            image.state = result.getState();
//            image.variety = result.getVariety();
            images.add(image);
        }
        ArrayList<Image> images1 =new ArrayList<>();
        ArrayList<Image> images2 =new ArrayList<>();
        ArrayList<Image> images3 =new ArrayList<>();
        for (int i = 0; i <images.size() ; i++) {
            if(VARIETY_GENERAL_PAPER.equals(images.get(i).variety)){
                images1.add(images.get(i));
            }else if(VARIETY_SPECIAL_PAPER.equals(images.get(i).variety)){
                images2.add(images.get(i));
            }else if(VARIETY_GENERAL_ELECTRON.equals(images.get(i).variety)){
                images3.add(images.get(i));
            }
        }
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList(PAPER_NORMAL_RECEIPT_DATA, images1);
        paperNormalReceiptFragment = DemandsDetailsReceiptFragment.newInstance(bundle);
        addCaptureFragment(R.id.container_paper_normal_receipt, paperNormalReceiptFragment);


        bundle.putParcelableArrayList(PAPER_SPECIAL_RECEIPT_DATA, images2);
        paperSpecialReceiptFragment = DemandsDetailsReceiptFragment2.newInstance(bundle);
        addCaptureFragment(R.id.container_paper_special_receipt, paperSpecialReceiptFragment);

        bundle.putParcelableArrayList(PAPER_ELEC_RECEIPT_DATA, images3);
        paperElecReceiptFragment = DemandsDetailsReceiptFragment3.newInstance(bundle);
        addCaptureFragment(R.id.container_paper_elec_receipt, paperElecReceiptFragment);
    }

    String CompanyId;
    String orderId;
    @Override
    public void initData() {
        orderId = getIntent().getStringExtra("OrderId");
        CompanyId = getIntent().getStringExtra("CompanyId");
        Log.d(TAG, "initData:showOrderDetail orderID" + orderId);
        showOrderDetail(orderId);
        LoginWithInfoBean loginWithInfoBean = SharedPreferencesHelper.loadFormSource(this, LoginWithInfoBean.class);
        if (loginWithInfoBean != null) {
            Api.judgeCompanyIsCollcted(CompanyId, loginWithInfoBean.getData().getToken(), new Api.BaseViewCallback<NormalBean>() {
                @Override
                public void setData(NormalBean s) {
                    if (s != null && s.getStatus() == 200) {
                        //TODO 设置收藏图片
                        isCollected = false;
                        collect.setImageResource(R.mipmap.collect);
                    } else if (s.getStatus() == 701 && s.getMsg().equals("token验证失败")) {
                        startActivity(new Intent(ProvidedActivity.this, LoginActivity.class));
                        finish();
                    } else if (s.getStatus() == 400) {
                        collect.setImageResource(R.mipmap.collected);
                        isCollected = true;
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
    public void mailInvoice(){
        AccountHelper.isTokenValid(new Api.BaseViewCallback<LoginWithInfoBean>() {
            @Override
            public void setData(LoginWithInfoBean loginWithInfoBean) {
                if (loginWithInfoBean.getStatus() == 200) {
                    ExpressCompanyBean.DataBean bean = (ExpressCompanyBean.DataBean)mSpinner.getSelectedItem();
                    Api.mailInvoice(AccountHelper.getToken(), orderId,bean.getLabel()
                            , edtOddNumber.getText().toString(), new Api.BaseViewCallback<NormalBean>() {
                        @Override
                        public void setData(NormalBean normalBean) {
                            BaseApplication.showToast("确认邮寄");
                        }
                    });
                } else {
                    BaseApplication.showToast("token验证失败请重新登陆");
                    startActivity(new Intent(ProvidedActivity.this, LoginActivity.class));
                    finish();
                }
            }
        });
    }
    public void showOrderDetail(String orderID) {
        if (AccountHelper.getToken() != null && AccountHelper.getToken() != "") {
            Api.showOrderDetail(AccountHelper.getToken(), orderID, new Api.BaseViewCallback<OrderDetailsBean>() {
                @Override
                public void setData(OrderDetailsBean orderDetailsBean) {
                    if (orderDetailsBean.getStatus() == REQUEST_SUCCESS) {
                        OrderDetailsBean.DataBean bean = orderDetailsBean.getData();
                        tvInvoiceType.setText(bean.getInvoiceType().getName());
                        if(STATE_FLYING.equals(bean.getOrderState())){
                            tvArrivalState.setText("红包飞来中");
                            tvArrivalState.setTextColor(getResources().getColor(R.color.red));
                        }else if(STATE_GOT_ALL.equals(bean.getOrderState())){
                            tvArrivalState.setText("红包到帐");
                            tvArrivalState.setTextColor(Color.GRAY);
                        }else if(STATE_GOT_PARTIALITY.equals(bean.getOrderState())){
                            tvArrivalState.setText("部分到帐");
                            tvArrivalState.setTextColor(Color.BLUE);
                        }else if(STATE_GONE.equals(bean.getOrderState())){
                            tvArrivalState.setText("红包飞走了");
                            tvArrivalState.setTextColor(Color.GREEN);
                        }
                        if(bean.getPostage() != null){
                            tvReceiver.setText(bean.getPostage().getReceiver());
                            tvTelephone.setText(bean.getPostage().getTelephone());
                            tvPhone.setText(bean.getPostage().getPhone());
                            tvPublishAddress.setText(bean.getPostage().getAddress());
//                        edtOddNumber.setText();
                        }
                        estimateMoney.setText(bean.getBonus() + "");
                        receiptNumber.setText(bean.getInvoiceCount() + "");

//                            receiptMoney.setText();
//                            continueToUpload.setText();
                        if(bean.getCompany() != null){
                            companyName.setText(bean.getCompany().getName());
                            number.setText(bean.getCompany().getPhone());
                            companyAddress.setText(bean.getCompany().getAddress());
                            bankAccount.setText(bean.getCompany().getAccount());
                            bank.setText(bean.getCompany().getDepositBank());
                            texNumber.setText(bean.getCompany().getTaxno());
                        }

                        Log.d(TAG, "showOrderDetail success");
                    }
                }
            });
        }
    }


}
