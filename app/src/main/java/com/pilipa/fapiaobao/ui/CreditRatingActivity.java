package com.pilipa.fapiaobao.ui;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.pilipa.fapiaobao.R;
import com.pilipa.fapiaobao.account.AccountHelper;
import com.pilipa.fapiaobao.base.BaseActivity;
import com.pilipa.fapiaobao.net.Api;
import com.pilipa.fapiaobao.net.bean.me.CreditInfoBean;
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
    @Bind(R.id.star)
    ImageView star;
    int[] LevelIcon=new int[]{R.mipmap.dstar1,R.mipmap.dstar1,R.mipmap.dstar2,R.mipmap.dstar3,R.mipmap.dstar4,R.mipmap.dstar5
            ,R.mipmap.dstar6,R.mipmap.dstar7,R.mipmap.dstar8,R.mipmap.dstar9,R.mipmap.dstar10};

    @Override
    protected int getLayoutId() {
        return R.layout.activity_credit_rating;
    }

    @OnClick({R.id.details_back,R.id.tv_negative,R.id.tv_rules,R.id.tv_creditHistory})
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_creditHistory:{
                startActivity(new Intent(this,CreditHistoryActivity.class));
            }break;
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
                        int lastCreditScore = creditInfoBean.getData().getLastCreditScore();
                        if(lastCreditScore >= 0){
                            tvLastChange.setText("+"+lastCreditScore+"");
                        }else{
                            tvLastChange.setText(lastCreditScore+"");
                        }
                        star.setImageResource(LevelIcon[creditInfoBean.getData().getCreditLevel()]);
                        Log.d(TAG, "findCreditInfo"+rating);
                    }
                }
            });
        }
    }



}
