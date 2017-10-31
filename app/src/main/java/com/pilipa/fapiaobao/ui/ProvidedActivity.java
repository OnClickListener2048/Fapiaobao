package com.pilipa.fapiaobao.ui;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.pilipa.fapiaobao.R;
import com.pilipa.fapiaobao.account.AccountHelper;
import com.pilipa.fapiaobao.base.BaseActivity;
import com.pilipa.fapiaobao.net.Api;
import com.pilipa.fapiaobao.net.bean.TestBean;
import com.pilipa.fapiaobao.net.bean.me.OrderDetailsBean;
import com.pilipa.fapiaobao.net.callback.JsonCallBack;
import com.pilipa.fapiaobao.ui.fragment.DemandsDetailsReceiptFragment;
import com.pilipa.fapiaobao.ui.model.Image;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.pilipa.fapiaobao.net.Constant.REQUEST_SUCCESS;

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
    @Bind(R.id.btn_mailing)
    Button btnMailing;

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
    private DemandsDetailsReceiptFragment paperSpecialReceiptFragment;
    private DemandsDetailsReceiptFragment paperElecReceiptFragment;

    @Bind(R.id.translate_details)
    LinearLayout translateDetails;
    @Bind(R.id.translate)
    LinearLayout translate;
    @Bind(R.id.mSpinner)
    Spinner mSpinner;
    private boolean isShow = false;//当前详情是否显示

    @Override
    protected int getLayoutId() {
        return R.layout.activity_provided;
    }

    @OnClick({R.id.provided_back, R.id.fl_change, R.id.btn_confirm})

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.provided_back: {
                finish();
            }
            break;
            case R.id.btn_confirm: {

            }
            break;
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
        }
    }

    @Override
    public void initView() {
        OkGo.<TestBean>get("http://gank.io/api/data/福利/10/1").execute(new JsonCallBack<TestBean>(TestBean.class) {
            @Override
            public void onSuccess(Response<TestBean> response) {
                TestBean body = response.body();
                if (!body.isError()) {
                    setUpData(body.getResults());
                }
            }
        });


        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.simple_spinner_item);
        String level[] = getResources().getStringArray(R.array.express);//资源文件
        for (int i = 0; i < level.length; i++) {
            adapter.add(level[i]);
        }
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSpinner.setAdapter(adapter);
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
            images.add(image);
        }

        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList(PAPER_NORMAL_RECEIPT_DATA, images);
        paperNormalReceiptFragment = DemandsDetailsReceiptFragment.newInstance(bundle);
        addCaptureFragment(R.id.container_paper_normal_receipt, paperNormalReceiptFragment);


        bundle.putParcelableArrayList(PAPER_SPECIAL_RECEIPT_DATA, images);
        paperSpecialReceiptFragment = DemandsDetailsReceiptFragment.newInstance(bundle);
        addCaptureFragment(R.id.container_paper_special_receipt, paperSpecialReceiptFragment);

        bundle.putParcelableArrayList(PAPER_ELEC_RECEIPT_DATA, images);
        paperElecReceiptFragment = DemandsDetailsReceiptFragment.newInstance(bundle);
        addCaptureFragment(R.id.container_paper_elec_receipt, paperElecReceiptFragment);
    }


    @Override
    public void initData() {
        String orderId = getIntent().getStringExtra("OrderId");
        Log.d(TAG, "initData:showOrderDetail orderID" + orderId);
        showOrderDetail(orderId);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    public void showOrderDetail(String orderID) {
        if (AccountHelper.getToken() != null && AccountHelper.getToken() != "") {
            Api.showOrderDetail(AccountHelper.getToken(), orderID, new Api.BaseViewCallback<OrderDetailsBean>() {
                @Override
                public void setData(OrderDetailsBean orderDetailsBean) {
                    if (orderDetailsBean.getStatus() == REQUEST_SUCCESS) {
                        OrderDetailsBean.DataBean bean = orderDetailsBean.getData();
                        tvInvoiceType.setText(bean.getInvoiceType().getName());
                        if ("0".equals(bean.getOrderState())) {
                            tvArrivalState.setText("红包到账");
                            tvArrivalState.setTextColor(getResources().getColor(R.color.red));
                        } else if ("1".equals(bean.getOrderState())) {
                            tvArrivalState.setText("部分到账");
                            tvArrivalState.setTextColor(Color.YELLOW);
                        } else if ("2".equals(bean.getOrderState())) {
                            tvArrivalState.setText("红包飞走了");
                            tvArrivalState.setTextColor(Color.BLUE);
                        }
//                        tvReceiver.setText();
//                        tvTelephone.setText();
//                        tvPhone.setText();
//                        tvPublishAddress.setText();
//                        edtOddNumber.setText();

//                        receiptNumber.setText();
//                        receiptMoney.setText();
                        estimateMoney.setText(bean.getBonus() + "");
//                        continueToUpload.setText();
                        companyName.setText(bean.getCompany().getName());
                        number.setText(bean.getCompany().getPhone());
                        companyAddress.setText(bean.getCompany().getAddress());
                        bankAccount.setText(bean.getCompany().getAccount());
                        bank.setText(bean.getCompany().getDepositBank());
                        texNumber.setText(bean.getCompany().getTaxno());


                        Log.d(TAG, "showOrderDetail success");
                    }
                }
            });
        }
    }


}
