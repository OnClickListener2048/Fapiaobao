package com.pilipa.fapiaobao.ui;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.pilipa.fapiaobao.R;
import com.pilipa.fapiaobao.base.BaseActivity;
import com.pilipa.fapiaobao.net.Api;
import com.pilipa.fapiaobao.net.bean.LoginWithInfoBean;
import com.pilipa.fapiaobao.net.bean.TestBean;
import com.pilipa.fapiaobao.net.bean.publish.DemandDetails;
import com.pilipa.fapiaobao.net.callback.JsonCallBack;
import com.pilipa.fapiaobao.ui.fragment.DemandsDetailsReceiptFragment;
import com.pilipa.fapiaobao.ui.model.Image;
import com.pilipa.fapiaobao.utils.SharedPreferencesHelper;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.pilipa.fapiaobao.net.Constant.REQUEST_SUCCESS;

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


    private boolean isShow =false;//当前详情是否显示

    public static final String PAPER_NORMAL_RECEIPT_DATA = "paper_normal_receipt_data";
    public static final String PAPER_SPECIAL_RECEIPT_DATA = "paper_special_receipt_data";
    public static final String PAPER_ELEC_RECEIPT_DATA = "paper_elec_receipt_data";



    private ArrayList<Image> images;
    private DemandsDetailsReceiptFragment paperNormalReceiptFragment;
    private DemandsDetailsReceiptFragment paperSpecialReceiptFragment;
    private DemandsDetailsReceiptFragment paperElecReceiptFragment;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_demand;
    }

    @OnClick({R.id.demand_back,R.id.fl_change})
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.demand_back: {
                finish();
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
        OkGo.<TestBean>get("http://gank.io/api/data/福利/5/1").execute(new JsonCallBack<TestBean>(TestBean.class) {
            @Override
            public void onSuccess(Response<TestBean> response) {
                TestBean body = response.body();
                if (!body.isError()) {
                    setUpData(body.getResults());
                }
            }
        });
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
        String demandId =  getIntent().getStringExtra("demandId");
        Log.d(TAG, "initData:demandDetails demandId"+demandId);
        demandDetails(demandId);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
    public void demandDetails(String demandId){
        LoginWithInfoBean loginBean = SharedPreferencesHelper.loadFormSource(DemandActivity.this,LoginWithInfoBean.class);
        if(loginBean != null){
            String token = loginBean.getData().getToken();
            Log.d(TAG, "initData:demandDetails userToken"+token);
            Api.demandDetails(token,demandId,new Api.BaseViewCallback<DemandDetails>() {
                @Override
                public void setData(DemandDetails demandDetails) {
                    if(demandDetails.getStatus() == REQUEST_SUCCESS){
                        DemandDetails.DataBean bean =   demandDetails.getData();
                        tvBounsAmount.setText(bean.getTotalBonus()+"");
//                        tvAlreadyCollected.setText(bean.getTotalBonus()+"");
//                        tvAddress.setText(bean.getDemandPostage().getAddress());
                        tvAmount.setText(bean.getTotalAmount()+"");
                        tvDeadline.setText(bean.getDeadline());
                        tvLeftAmount.setText(bean.getLeftAmount()+"");
                        tvPhone.setText(bean.getCompany().getPhone());
//                        tvTelephone.setText(bean.getDemandPostage().getPhone());
//                        tvPublishTime.setText(bean.getDemandPostage());
//                        tvReceiver.setText(bean.getDemandPostage().getReceiver());
                        companyName.setText(bean.getCompany().getName());
                        number.setText(bean.getCompany().getPhone());
                        companyAddress.setText(bean.getCompany().getAddress());
                        bankAccount.setText(bean.getCompany().getAccount());
                        bank.setText(bean.getCompany().getDepositBank());
                        texNumber.setText(bean.getCompany().getTaxno());
                        Log.d(TAG, "demandDetails success");
                    }
                }
            });
        }
    }
}
