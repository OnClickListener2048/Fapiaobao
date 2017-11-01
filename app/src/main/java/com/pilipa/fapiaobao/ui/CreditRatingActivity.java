package com.pilipa.fapiaobao.ui;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.pilipa.fapiaobao.R;
import com.pilipa.fapiaobao.account.AccountHelper;
import com.pilipa.fapiaobao.base.BaseActivity;
import com.pilipa.fapiaobao.net.Api;
import com.pilipa.fapiaobao.net.bean.me.CreditInfoBean;
import com.pilipa.fapiaobao.net.bean.me.NegativeCreditInfoBean;
import com.pilipa.fapiaobao.ui.widget.ColorArcProgressBar;

import butterknife.Bind;
import butterknife.OnClick;

import static com.pilipa.fapiaobao.net.Constant.REQUEST_SUCCESS;

/**
 * Created by wjn on 2017/10/23.
 */

public class CreditRatingActivity extends BaseActivity {
    private static final String TAG = "CreditRatingActivity";

    @Bind(R.id.bar2)
    ColorArcProgressBar colorArcProgressBar;
    @Bind(R.id.tv_lastChange)
    TextView tvLastChange;
    @Bind(R.id.tv_creditHistory)
    TextView tvCreditHistory;
    @Override
    protected int getLayoutId() {
        return R.layout.activity_credit_rating;
    }

    @OnClick({R.id.details_back,R.id.tv_negative,R.id.tv_rules})
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.details_back:{
                finish();
            }break;
            case R.id.tv_negative:{
                startActivity(new Intent(this,NegetiveActivity.class));
            }break;
            case R.id.tv_rules:{
                startActivity(new Intent(this,NegetiveActivity.class));
            }break;
        }
    }

    @Override
    public void initView() {
        colorArcProgressBar.setCurrentValues(1);
    }

    @Override
    public void initData() {
        findCreditInfo();
        findCreditHistory("0","10");
        findCreditNegativeHistory("0","10");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    public void findCreditInfo(){
        if (AccountHelper.getToken() != null && AccountHelper.getToken() != "") {
            Api.findCreditInfo(AccountHelper.getToken(),new Api.BaseViewCallback<CreditInfoBean>() {
                @Override
                public void setData(CreditInfoBean creditInfoBean) {
                    if(creditInfoBean.getStatus() == REQUEST_SUCCESS){
                        float rating = creditInfoBean.getData().getRanking();
                        colorArcProgressBar.setCurrentValues(rating);
                        Log.d(TAG, "findCreditInfo"+rating);
                    }
                }
            });
        }
    }
    public void findCreditHistory(String pageNo,String pageSize){
        if (AccountHelper.getToken() != null && AccountHelper.getToken() != "") {
            Api.findCreditHistory(AccountHelper.getToken(),pageNo,pageSize,new Api.BaseViewCallback<NegativeCreditInfoBean>() {
                @Override
                public void setData(NegativeCreditInfoBean companiesBean) {
                    if(companiesBean.getStatus() == REQUEST_SUCCESS){
                        Log.d(TAG, "findCreditHistory"+"");
                    }
                }
            });
        }
    }
    public void findCreditNegativeHistory(String pageNo,String pageSize){
        if (AccountHelper.getToken() != null && AccountHelper.getToken() != "") {
            Api.findCreditNegativeHistory(AccountHelper.getToken(),pageNo,pageSize,new Api.BaseViewCallback<NegativeCreditInfoBean>() {
                @Override
                public void setData(NegativeCreditInfoBean companiesBean) {
                    if(companiesBean.getStatus() == REQUEST_SUCCESS){
                        Log.d(TAG, "findCreditNegativeHistory"+"");
                    }
                }
            });
        }
    }

}
