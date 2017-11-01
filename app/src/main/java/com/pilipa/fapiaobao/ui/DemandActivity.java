package com.pilipa.fapiaobao.ui;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.pilipa.fapiaobao.R;
import com.pilipa.fapiaobao.account.AccountHelper;
import com.pilipa.fapiaobao.base.BaseActivity;
import com.pilipa.fapiaobao.base.BaseApplication;
import com.pilipa.fapiaobao.net.Api;
import com.pilipa.fapiaobao.net.bean.me.NormalBean;
import com.pilipa.fapiaobao.net.bean.publish.DemandDetails;
import com.pilipa.fapiaobao.ui.fragment.DemandsDetailsReceiptFragment;
import com.pilipa.fapiaobao.ui.fragment.DemandsDetailsReceiptFragment2;
import com.pilipa.fapiaobao.ui.fragment.DemandsDetailsReceiptFragment3;
import com.pilipa.fapiaobao.ui.model.Image;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.pilipa.fapiaobao.R.id.btn_shut_down_early;
import static com.pilipa.fapiaobao.net.Constant.REQUEST_SUCCESS;
import static com.pilipa.fapiaobao.net.Constant.STATE_DEMAND_ING;
import static com.pilipa.fapiaobao.net.Constant.VARIETY_GENERAL_ELECTRON;
import static com.pilipa.fapiaobao.net.Constant.VARIETY_GENERAL_PAPER;
import static com.pilipa.fapiaobao.net.Constant.VARIETY_SPECIAL_PAPER;

/**
 * Created by lyt on 2017/10/16.
 */

public class DemandActivity extends BaseActivity {
    private static final String TAG = "DemandActivity";
    @Bind(R.id.container_paper_normal_receipt)
    FrameLayout containerPaperNormalReceipt;
    @Bind(R.id.container_paper_special_receipt)
    FrameLayout containerPaperSpecialReceipt;
    @Bind(R.id.container_paper_elec_receipt)
    FrameLayout containerPaperElecReceipt;
    @Bind(R.id.translate_details)
    LinearLayout translateDetails;
    @Bind(R.id.translate)
    LinearLayout translate;
    @Bind(R.id.tv_bounsAmount)
    TextView tvBounsAmount;
    @Bind(R.id.tv_leftAmount)
    TextView tvLeftAmount;
    @Bind(R.id.tv_attentions)
    TextView tvAttentions;
    @Bind(R.id.tv_deadline)
    TextView tvDeadline;
    @Bind(R.id.tv_amount)
    TextView tvAmount;
    @Bind(R.id.tv_publishTime)
    TextView tvPublishTime;
    @Bind(R.id.tv_receiver)
    TextView tvReceiver;
    @Bind(R.id.tv_telephone)
    TextView tvTelephone;
    @Bind(R.id.tv_phone)
    TextView tvPhone;
    @Bind(R.id.tv_address)
    TextView tvAddress;
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
    @Bind(R.id.tv_AlreadyCollected)
    TextView tvAlreadyCollected;
    @Bind(R.id.btn_shut_down_early)
    TextView btnShutDownEarly;

    List<DemandDetails.DataBean.OrderInvoiceListBean> mDataList = new ArrayList<>();
    private boolean isShow =false;//当前详情是否显示

    public static final String PAPER_NORMAL_RECEIPT_DATA = "paper_normal_receipt_data";
    public static final String PAPER_SPECIAL_RECEIPT_DATA = "paper_special_receipt_data";
    public static final String PAPER_ELEC_RECEIPT_DATA = "paper_elec_receipt_data";

    private String demandId;

    private ArrayList<Image> images;
    private DemandsDetailsReceiptFragment paperNormalReceiptFragment;
    private DemandsDetailsReceiptFragment2 paperSpecialReceiptFragment;
    private DemandsDetailsReceiptFragment3 paperElecReceiptFragment;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_demand;
    }

    @OnClick({R.id.demand_back,R.id.fl_change, btn_shut_down_early})
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.demand_back: {
                finish();
            }
            break;
            case btn_shut_down_early: {
                if(demandId != null){
                    shatDownEarly(demandId);
                }
            }
            break;
            case R.id.fl_change:{
                if(isShow){
                    translateDetails.setVisibility(View.VISIBLE);
                    translate.setVisibility(View.GONE);
                    isShow = !isShow;
                }else{
                    translateDetails.setVisibility(View.GONE);
                    translate.setVisibility(View.VISIBLE);
                    isShow = !isShow;
                }
            }break;
        }
    }

    @Override
    public void initView() {
//        OkGo.<TestBean>get("http://gank.io/api/data/福利/5/1").execute(new JsonCallBack<TestBean>(TestBean.class) {
//            @Override
//            public void onSuccess(Response<TestBean> response) {
//                TestBean body = response.body();
//                if (!body.isError()) {
//                    setUpData(body.getResults());
//                }
//            }
//        });
    }

    private void setUpData( List<DemandDetails.DataBean.OrderInvoiceListBean> results) {
        Log.d(TAG, "setUpData:   private void setUpData(ArrayList<model.ResultsBean> body) {");
        images = new ArrayList<>();
        for (DemandDetails.DataBean.OrderInvoiceListBean result : results) {
            Log.d(TAG, "setUpData:  for (model.ResultsBean result : body) {");
            Image image = new Image();
            image.isSelected = false;
            image.name = result.getId();
            image.path = result.getUrl();
            image.position = -1;
            image.isCapture = false;
            image.isFromNet = true;
            image.state = result.getState();
            image.logisticsTradeno = result.getLogisticsTradeno();
            image.logisticsCompany = result.getLogisticsCompany();
            image.variety = result.getVariety();
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


    @Override
    public void initData() {
        demandId =  getIntent().getStringExtra("demandId");
        Log.d(TAG, "initData:demandDetails demandId"+demandId);
    }

    @Override
    protected void onResume() {
        if(demandId != null){
            demandDetails(demandId);
        }
        Log.d(TAG, "onResume:demandDetails demandId"+demandId);

        super.onResume();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
    public void demandDetails(String demandId){
        if (AccountHelper.getToken() != null && AccountHelper.getToken() != "") {
            String token =AccountHelper.getToken();
            Api.demandDetails(token,demandId,new Api.BaseViewCallback<DemandDetails>() {
                @Override
                public void setData(DemandDetails demandDetails) {
                    if(demandDetails.getStatus() == REQUEST_SUCCESS){
                        DemandDetails.DataBean bean =   demandDetails.getData();
                        String left_bouns = getResources().getString(R.string.left_bouns);

                        tvBounsAmount.setText(bean.getDemand().getTotalBonus()+"元");
//                        tvAlreadyCollected.setText(bean.getDemand().getLeftAmount()+"");
                        tvAmount.setText(bean.getDemand().getTotalAmount()+"元");
                        tvDeadline.setText(bean.getDemand().getDeadline());
                        tvLeftAmount.setText(String.format(left_bouns, bean.getDemand().getLeftBonus()));
//                        tvPublishTime.setText(bean.getDemandPostage());
                        tvAttentions.setText("1."+bean.getDemand().getAttentions());
                        if(bean.getDemand().getDemandPostage() != null){
                            tvAddress.setText(bean.getDemand().getDemandPostage().getAddress());
//                            tvPhone.setText(bean.getDemand().getDemandPostage().getPhone());
                            tvTelephone.setText(bean.getDemand().getDemandPostage().getEmail());
                            tvReceiver.setText(bean.getDemand().getDemandPostage().getReceiver());
                        }
                        if( bean.getDemand().getCompany() != null){
                            companyName.setText(bean.getDemand().getCompany().getName());
                            number.setText(bean.getDemand().getCompany().getPhone());
                            companyAddress.setText(bean.getDemand().getCompany().getAddress());
                            bankAccount.setText(bean.getDemand().getCompany().getAccount());
                            bank.setText(bean.getDemand().getCompany().getDepositBank());
                            texNumber.setText(bean.getDemand().getCompany().getTaxno());
                        }
                        //发票列表
                        mDataList.clear();
                        mDataList.addAll(bean.getOrderInvoiceList());
//                        List<DemandDetails.DataBean.OrderInvoiceListBean> list = bean.getOrderInvoiceList();
                        setUpData(mDataList);
                        String collected_amount = getResources().getString(R.string.collected_amount);
                        tvAlreadyCollected.setText(String.format(collected_amount, mDataList.size()));

                        if(STATE_DEMAND_ING.equals(bean.getDemand().getState())){
                            btnShutDownEarly.setVisibility(View.VISIBLE);
                        }else{
                            btnShutDownEarly.setVisibility(View.GONE);
                        }


                        Log.d(TAG, "demandDetails success");
                    }
                }
            });
        }
    }
    private void shatDownEarly(String id){
        if (AccountHelper.getToken() != null && AccountHelper.getToken() != "") {
                Api.shatDownEarly(AccountHelper.getToken(),id, new Api.BaseViewCallback<NormalBean>() {
                    @Override
                    public void setData(NormalBean normalBean) {
                        Toast.makeText(DemandActivity.this,"提前关闭 success",Toast.LENGTH_SHORT).show();
                        DemandActivity.this.finish();
                        Log.d(TAG, "updateData:shatDownEarly success");
                    }
                });
        }else{
            BaseApplication.showToast("登录超期");
        }
    }
}
