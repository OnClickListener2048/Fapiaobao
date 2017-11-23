package com.pilipa.fapiaobao.ui;

import android.Manifest;
import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.mylibrary.utils.TLog;
import com.lzy.okgo.OkGo;
import com.pilipa.fapiaobao.R;
import com.pilipa.fapiaobao.account.AccountHelper;
import com.pilipa.fapiaobao.adapter.PublishSpinnerAdapter;
import com.pilipa.fapiaobao.base.BaseActivity;
import com.pilipa.fapiaobao.base.BaseApplication;
import com.pilipa.fapiaobao.net.Api;
import com.pilipa.fapiaobao.net.bean.LoginWithInfoBean;
import com.pilipa.fapiaobao.net.bean.RejectTypeBean;
import com.pilipa.fapiaobao.net.bean.invoice.CompanyCollectBean;
import com.pilipa.fapiaobao.net.bean.me.FavBean;
import com.pilipa.fapiaobao.net.bean.me.NormalBean;
import com.pilipa.fapiaobao.net.bean.me.OrderDetailsBean;
import com.pilipa.fapiaobao.net.bean.publish.ExpressCompanyBean;
import com.pilipa.fapiaobao.ui.fragment.DemandsDetailsReceiptFragment;
import com.pilipa.fapiaobao.ui.fragment.DemandsDetailsReceiptFragment2;
import com.pilipa.fapiaobao.ui.fragment.DemandsDetailsReceiptFragment3;
import com.pilipa.fapiaobao.ui.model.Image;
import com.pilipa.fapiaobao.utils.SharedPreferencesHelper;
import com.pilipa.fapiaobao.zxing.android.CaptureActivity;
import com.pilipa.fapiaobao.zxing.encode.CodeCreator;
import com.tbruyelle.rxpermissions2.RxPermissions;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

import static com.pilipa.fapiaobao.net.Constant.REQUEST_SUCCESS;
import static com.pilipa.fapiaobao.net.Constant.STATE_FLYING;
import static com.pilipa.fapiaobao.net.Constant.STATE_GONE;
import static com.pilipa.fapiaobao.net.Constant.STATE_GOT_ALL;
import static com.pilipa.fapiaobao.net.Constant.STATE_GOT_PARTIALITY;
import static com.pilipa.fapiaobao.net.Constant.STATE_INCOMPETENT;
import static com.pilipa.fapiaobao.net.Constant.STATE_MAILING;
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
    @Bind(R.id.received_bonus)//收到红包
    TextView receivedBonus;
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
    @Bind(R.id.tv_low_limit)
    TextView tv_low_limit;
    @Bind(R.id.ll_receiptlist)
    LinearLayout ll_receiptlist;
    @Bind(R.id.ll_no_record)
    LinearLayout ll_no_record;
    @Bind(R.id.qr)
    ImageView qr;
    @Bind(R.id.ll_container_paper_normal_receipt)
    LinearLayout container_paper_normal_receipt;
    @Bind(R.id.ll_container_paper_special_receipt)
    LinearLayout container_paper_special_receipt;
    @Bind(R.id.ll_container_paper_elec_receipt)
    LinearLayout container_paper_elec_receipt;
    public static final String PAPER_NORMAL_RECEIPT_DATA = "paper_normal_receipt_data";
    public static final String PAPER_SPECIAL_RECEIPT_DATA = "paper_special_receipt_data";
    public static final String PAPER_ELEC_RECEIPT_DATA = "paper_elec_receipt_data";

    private Dialog mTipDialog;
    private ArrayList<Image> images;
    private DemandsDetailsReceiptFragment paperNormalReceiptFragment;
    private DemandsDetailsReceiptFragment2 paperSpecialReceiptFragment;
    private DemandsDetailsReceiptFragment3 paperElecReceiptFragment;
    List<OrderDetailsBean.DataBean.InvoiceListBean> mDataList = new ArrayList<>();

    private static final int REQUEST_CODE_SCAN = 0x0000;
    @Bind(R.id.translate_details)
    LinearLayout translateDetails;
    @Bind(R.id.translate)
    LinearLayout translate;
    @Bind(R.id.mSpinner)
    Spinner mSpinner;
    @Bind(R.id.link_to_telephone)
    ImageView link_to_telephone;
    @Bind(R.id.link_to_phone)
    ImageView link_to_phone;
    @Bind(R.id.layout_mailing_information)
    CardView layout_mailing_information;
    @Bind(R.id.ll_logisticsInfo)
    LinearLayout ll_logisticsInfo;
    @Bind(R.id.btn_mailing)
    Button btnMailing;
    @Bind(R.id.btn_scan)
    ImageView btnScan;
    private boolean isShow = true;//当前详情是否显示
    private boolean isLogisticShow = false;//当前物流信息是否显示
    boolean isCollected;
    private String favoriteId;
    private List<RejectTypeBean.DataBean> list = new ArrayList<>();
    PublishSpinnerAdapter spinnerAdapter;
    @Override
    protected int getLayoutId() {
        return R.layout.activity_provided;
    }

    @OnClick({R.id.provided_back, R.id.fl_change
            , R.id.collect, R.id.btn_mailing, R.id.btn_scan, R.id.question
            , R.id.link_to_telephone, R.id.link_to_phone})

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.link_to_telephone: {
                if (tvTelephone.getText().toString() != null) {
                    callPhone(tvTelephone.getText().toString());
                }
            }
            break;
            case R.id.link_to_phone: {
                if (tvPhone.getText().toString() != null) {
                    callPhone(tvPhone.getText().toString());
                }
            }
            break;
            case R.id.question: {
                setTipDialog();
            }
            break;
            case R.id.btn_scan: {
                startActivityForResult(new Intent(this, CaptureActivity.class), REQUEST_CODE_SCAN);
            }
            break;
            case R.id.btn_mailing: {
                if (!edtOddNumber.getText().toString().isEmpty()) {
                    mailInvoice();
                } else {
                    BaseApplication.showToast("请填写邮寄单号");
                }
            }
            break;
            case R.id.provided_back: {
                finish();
            }
            break;
            case R.id.fl_change: {
                if (isShow) {
                    translateDetails.setVisibility(View.GONE);
                    translate.setVisibility(View.VISIBLE);
                    isShow = !isShow;
                } else {
                    translateDetails.setVisibility(View.VISIBLE);
                    translate.setVisibility(View.GONE);
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
                                Api.deleteFavoriteCompany(favoriteId, AccountHelper.getToken(), new Api.BaseViewCallback<FavBean>() {
                                    @Override
                                    public void setData(FavBean normalBean) {
                                        if (normalBean.getStatus() == 200) {
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

                                Api.favCompanyCreate(companyCollectBean, new Api.BaseViewCallback<FavBean>() {
                                    @Override
                                    public void setData(FavBean normalBean) {
                                        if (normalBean.getStatus() == 200) {
                                            BaseApplication.showToast("收藏成功");
                                            isCollected = true;
                                            favoriteId = normalBean.getFavoriteId();
                                            collect.setImageResource(R.mipmap.collected);
                                        }
                                    }
                                });
                            }
                        } else {
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
               spinnerAdapter =  new PublishSpinnerAdapter(expressCompanyBean);
                mSpinner.setAdapter(spinnerAdapter);
            }
        });

    }

    private void setTipDialog() {
        mTipDialog = new Dialog(this, R.style.BottomDialog);
        LinearLayout root = (LinearLayout) LayoutInflater.from(this).inflate(
                R.layout.layout_low_tip, null);
        root.findViewById(R.id.btn_cancel1).setOnClickListener(new View.OnClickListener() {
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

    private void setUpData(List<OrderDetailsBean.DataBean.InvoiceListBean> results) {
        Log.d(TAG, "setUpData:   private void setUpData(ArrayList<model.ResultsBean> body) {");
        images = new ArrayList<>();
        ll_receiptlist.setVisibility(View.VISIBLE);

        for (OrderDetailsBean.DataBean.InvoiceListBean result : results) {
            Log.d(TAG, "setUpData:  for (model.ResultsBean result : body) {");
            Image image = new Image();
            image.isSelected = false;
            image.name = result.getId();
            image.path = result.getUrl();
            image.position = -1;
            image.isCapture = false;
            image.isFromNet = true;
            image.amount = String.valueOf(result.getAmount());
            image.logisticsTradeno = result.getLogisticsTradeno();
            image.logisticsCompany = result.getLogisticsCompany();
            image.state = result.getState();
            image.variety = result.getVariety();

            //判断是否需要显示物流信息 view
            if (!VARIETY_GENERAL_ELECTRON.equals(image.variety) &&
                    image.logisticsTradeno == null
                    && STATE_MAILING.equals(image.state) ) {
                isLogisticShow = true;
            }
            // 判断驳回理由显示
            if (STATE_INCOMPETENT.equals(result.getState())) {
                if ("8".equals(result.getInvoiceReject().getType())) {
                    image.reason = result.getInvoiceReject().getReason();
                } else {
                    if(list!=null&&result.getInvoiceReject().getType()!= null){
                        RejectTypeBean.DataBean bean = list.get(Integer.parseInt(result.getInvoiceReject().getType())-1);
                        image.reason = bean.getLabel();
                    }
                }
            }

            image.from = "provided";//来自提供详情 预览图片无样式
            images.add(image);
        }

        //判断是否需要显示物流信息 view
        if (isLogisticShow) {
            ll_logisticsInfo.setVisibility(View.VISIBLE);
        } else {
            ll_logisticsInfo.setVisibility(View.GONE);
        }
        ArrayList<Image> images1 = new ArrayList<>();
        ArrayList<Image> images2 = new ArrayList<>();
        ArrayList<Image> images3 = new ArrayList<>();
        for (int i = 0; i < images.size(); i++) {
            if (VARIETY_GENERAL_PAPER.equals(images.get(i).variety)) {
                images1.add(images.get(i));
            } else if (VARIETY_SPECIAL_PAPER.equals(images.get(i).variety)) {
                images2.add(images.get(i));
            } else if (VARIETY_GENERAL_ELECTRON.equals(images.get(i).variety)) {
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
        if(images1.size()==0){
            container_paper_normal_receipt.setVisibility(View.GONE);
        }
        if(images2.size()==0){
            container_paper_special_receipt.setVisibility(View.GONE);
        }
        if(images3.size()==0){
            container_paper_elec_receipt.setVisibility(View.GONE);
        }

    }

    public void findAllRejectType() {
        Api.findAllRejectType(new Api.BaseViewCallback<RejectTypeBean>() {
            @Override
            public void setData(RejectTypeBean rejectTypeBean) {
                if (rejectTypeBean.getStatus() == 200) {
                    list.addAll(rejectTypeBean.getData());
                }
            }
        });
    }

    String CompanyId;
    String orderId;

    @Override
    public void initData() {
        orderId = getIntent().getStringExtra("OrderId");
        CompanyId = getIntent().getStringExtra("CompanyId");
        Log.d(TAG, "initData:showOrderDetail orderID" + orderId);
        findAllRejectType();
        checkFav(CompanyId);
        showOrderDetail(orderId);
    }
    public void checkFav(final String companyId) {
        LoginWithInfoBean loginWithInfoBean = SharedPreferencesHelper.loadFormSource(this, LoginWithInfoBean.class);
        if (loginWithInfoBean != null) {
            Api.judgeCompanyIsCollcted(companyId, loginWithInfoBean.getData().getToken(), new Api.BaseViewCallback<FavBean>() {
                @Override
                public void setData(FavBean s) {
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
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == REQUEST_CODE_SCAN) {
            String codedContent = data.getStringExtra("codedContent");
            edtOddNumber.setText(codedContent);
        }
    }

    @Override
    protected void onPause() {
        OkGo.cancelTag(OkGo.getInstance().getOkHttpClient(), "showOrderDetail");
        super.onPause();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    public void mailInvoice() {
        AccountHelper.isTokenValid(new Api.BaseViewCallback<LoginWithInfoBean>() {
            @Override
            public void setData(LoginWithInfoBean loginWithInfoBean) {
                if (loginWithInfoBean.getStatus() == 200) {
                    ExpressCompanyBean.DataBean bean = (ExpressCompanyBean.DataBean) mSpinner.getSelectedItem();
                    Api.mailInvoice(AccountHelper.getToken(), orderId, bean.getLabel()
                            , edtOddNumber.getText().toString(), new Api.BaseViewCallback<NormalBean>() {
                                @Override
                                public void setData(NormalBean normalBean) {
                                    if(normalBean.getStatus() == 200){
                                        showOrderDetail(orderId);
                                        BaseApplication.showToast(normalBean.getData());
                                    }
                                    if(normalBean.getStatus() == 886){
                                        BaseApplication.showToast(normalBean.getMsg());
                                    }
                                }
                            });
                } else {
                    startActivity(new Intent(ProvidedActivity.this, LoginActivity.class));
                    finish();
                }
            }
        });
    }

    public void showOrderDetail(String orderID) {
        if (AccountHelper.getToken() != null && AccountHelper.getToken() != "") {
            Api.showOrderDetail(AccountHelper.getToken(), orderID, new Api.BaseViewCallbackWithOnStart<OrderDetailsBean>() {
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
                public void setData(OrderDetailsBean orderDetailsBean) {
                    if (orderDetailsBean.getStatus() == REQUEST_SUCCESS) {
                        OrderDetailsBean.DataBean bean = orderDetailsBean.getData();
                        tvInvoiceType.setText(bean.getInvoiceType().getName());
                        favoriteId = bean.getFavoriteId();//收藏ID
                        //是否需要邮寄
                        if(bean.isNeedMail()){
                            btnMailing.setTextColor(getResources().getColor(R.color.main_style));
                            btnMailing.setEnabled(true);
                            btnMailing.setText(getResources().getString(R.string.express_mail));
                            btnMailing.setBackgroundResource(R.drawable.shape_receipt_type);
                            edtOddNumber.setEnabled(true);
                            mSpinner.setEnabled(true);
                            btnScan.setEnabled(true);
                        }else{
                            btnMailing.setTextColor(getResources().getColor(R.color.gray_hint));
                            btnMailing.setEnabled(false);
                            btnMailing.setText(getResources().getString(R.string.waitting_mail));
                            btnMailing.setBackgroundResource(R.drawable.shape_receipt_type_false);
                            edtOddNumber.setEnabled(false);
                            mSpinner.setEnabled(false);
                            btnScan.setEnabled(false);
                        }
                        if (STATE_FLYING.equals(bean.getOrderState())) {
                            tvArrivalState.setText("红包飞来中");
                            tvArrivalState.setTextColor(getResources().getColor(R.color.bouns_2));
                        } else if (STATE_GOT_ALL.equals(bean.getOrderState())) {
                            tvArrivalState.setText("红包到帐");
                            tvArrivalState.setTextColor(getResources().getColor(R.color.bouns_1));
                        } else if (STATE_GOT_PARTIALITY.equals(bean.getOrderState())) {
                            tvArrivalState.setText("部分到帐");
                            tvArrivalState.setTextColor(getResources().getColor(R.color.bouns_1));
                        } else if (STATE_GONE.equals(bean.getOrderState())) {
                            tvArrivalState.setText("红包飞走了");
                            tvArrivalState.setTextColor(getResources().getColor(R.color.bouns_3));
                        }

                        if (bean.getPostage() != null) {
                            tvReceiver.setText(bean.getPostage().getReceiver());
                            tvTelephone.setText(bean.getPostage().getTelephone());
                            tvPhone.setText(bean.getPostage().getPhone());
                            String district = null;
                            if(bean.getPostage().getDistrict()!=null && !bean.getPostage().getDistrict().isEmpty()){
                                district = bean.getPostage().getDistrict()+ " ";
                            }
                            String city = null;
                            if(bean.getPostage().getCity()!=null && !bean.getPostage().getCity().isEmpty()){
                                city = bean.getPostage().getCity()+" ";
                            }
                            tvPublishAddress.setText(city+ district + bean.getPostage().getAddress());
                            tv_low_limit.setText(new BigDecimal(bean.getMailMinimum()).setScale(2, BigDecimal.ROUND_HALF_UP) + "元");
                        }
                        receivedBonus.setText(String.format("%.2f", bean.getReceivedBonus()));
                        estimateMoney.setText(String.format("%.2f", bean.getBonus()));
                        receiptNumber.setText(String.valueOf(bean.getInvoiceCount()));
                        receiptMoney.setText(String.format("%.2f", bean.getAmount()));
                        if (bean.getCompany() != null) {
                            companyName.setText(bean.getCompany().getName());
                            number.setText(bean.getCompany().getPhone());
                            companyAddress.setText(bean.getCompany().getAddress());
                            bankAccount.setText(bean.getCompany().getAccount());
                            bank.setText(bean.getCompany().getDepositBank());
                            texNumber.setText(bean.getCompany().getTaxno());

                            try {
                                String content = new String(bean.getCompany().getQrcode().getBytes("UTF-8"), "ISO-8859-1");
                                TLog.log("content-----------"+content);
                                Bitmap qrCode = CodeCreator.createQRCode(ProvidedActivity.this,content);
                                qr.setImageBitmap(qrCode);
                            } catch (Exception e) {
                                BaseApplication.showToast("二维码生成失败");
                                e.printStackTrace();
                            }
                        }
                        if (bean.getInvoiceList() != null) {
                            for (OrderDetailsBean.DataBean.InvoiceListBean data : bean.getInvoiceList()) {
                                if (!VARIETY_GENERAL_ELECTRON.equals(data.getVariety())) {
                                    layout_mailing_information.setVisibility(View.VISIBLE);
                                    break;
                                }
                            }
                            mDataList.clear();
                            mDataList.addAll(orderDetailsBean.getData().getInvoiceList());
                            setUpData(mDataList);
                        }
                        Log.d(TAG, "showOrderDetail success");
                    }
                }
            });
        }
    }

    private void callPhone(final String phone) {
        Log.d(TAG, "callPhone:  RxPermissions request");

        RxPermissions rxPermissions = new RxPermissions(this);
        rxPermissions.request(Manifest.permission.CALL_PHONE).subscribe(new Observer<Boolean>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Boolean aBoolean) {
                if (aBoolean) {
                    Intent intent1 = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + phone));
                    intent1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent1);
                    if (ActivityCompat.checkSelfPermission(ProvidedActivity.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                        // TODO: Consider calling
                        //    ActivityCompat#requestPermissions
                        // here to request the missing permissions, and then overriding
                        //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                        //                                          int[] grantResults)
                        // to handle the case where the user grants the permission. See the documentation
                        // for ActivityCompat#requestPermissions for more details.
                        return;
                    }
                }
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
    }


}
