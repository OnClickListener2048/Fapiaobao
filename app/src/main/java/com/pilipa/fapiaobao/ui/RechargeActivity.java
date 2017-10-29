package com.pilipa.fapiaobao.ui;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.mylibrary.utils.TimeUtils;
import com.pilipa.fapiaobao.R;
import com.pilipa.fapiaobao.base.BaseActivity;
import com.pilipa.fapiaobao.net.Api;
import com.pilipa.fapiaobao.net.bean.WXBean;
import com.pilipa.fapiaobao.utils.PayCommonUtil;
import com.pilipa.fapiaobao.wxapi.Constants;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import java.util.SortedMap;
import java.util.TreeMap;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by lyt on 2017/10/24.
 */

public class RechargeActivity extends BaseActivity {
    @Bind(R.id.tv_recharge_10)
    TextView tv_recharge_10;
    @Bind(R.id.tv_recharge_30)
    TextView tv_recharge_30;
    @Bind(R.id.tv_recharge_100)
    TextView tv_recharge_100;
    @Bind(R.id.tv_recharge_500)
    TextView tv_recharge_500;
    @Bind(R.id.go_recharge)
    Button goRecharge;
    private IWXAPI api;
    @Override
    protected int getLayoutId() {
        return R.layout.activity_rechange;
    }

    @OnClick({R.id._back, R.id.tv_recharge_10, R.id.tv_recharge_30, R.id.tv_recharge_100, R.id.tv_recharge_500})
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id._back: {
                finish();
            }
            break;
            case R.id.tv_recharge_10: {
                selected(1);
            }
            break;
            case R.id.tv_recharge_30: {
                selected(2);
            }
            break;
            case R.id.tv_recharge_100: {
                selected(3);
            }
            break;
            case R.id.tv_recharge_500: {
                selected(4);
            }
            break;
        }
    }

    @Override
    public void initView() {
        api = WXAPIFactory.createWXAPI(this, Constants.APP_ID);
        api.registerApp(Constants.APP_ID);
    }

    @Override
    public void initData() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
        selected(1);

    }

    public void selected(int selectedId) {
        tv_recharge_10.setSelected(selectedId == 1);
        tv_recharge_30.setSelected(selectedId == 2);
        tv_recharge_100.setSelected(selectedId == 3);
        tv_recharge_500.setSelected(selectedId == 4);
    }


    @OnClick(R.id.go_recharge)
    public void onViewClicked() {
        PayReq request = new PayReq();

        request.appId = Constants.APP_ID;

        request.partnerId = Constants.PARTNER_ID;

        request.prepayId= "1101000000140415649af9fc314aa427";

        request.packageValue = "Sign=WXPay";

        request.nonceStr= "1101000000140429eb40476f8896f4c9";

        request.timeStamp= "1398746574";

        request.sign= "7FFECB600D7157C5AA49810D2D8F28BC2811827B";

        Api.wxPay("1", new Api.BaseViewCallback<String>() {
            @Override
            public void setData(String s) {
                Log.d(" Api.wxPay", "setData: s" + s);
                WXBean wxBean = PayCommonUtil.parseXml(s);

                PayReq request = new PayReq();

                request.appId = wxBean.getAppid();

                request.partnerId = wxBean.getMch_id();

                request.prepayId= wxBean.getPrepay_id();

                request.packageValue = "Sign=WXPay";

                request.nonceStr= wxBean.getNonce_str();

                request.timeStamp= System.currentTimeMillis()+"";

                SortedMap<String, String> parameterMap  = new TreeMap<String, String>();
                parameterMap.put("appid", wxBean.getAppid());
                parameterMap.put("partnerid", wxBean.getMch_id());
                parameterMap.put("prepayid", wxBean.getPrepay_id());
                parameterMap.put("package", "Sign=WXPay");
                parameterMap.put("noncestr", PayCommonUtil.getRandomString(32));
                parameterMap.put("timestamp", System.currentTimeMillis()+"");


                request.sign= PayCommonUtil.createSign("UTF-8",parameterMap);

                api.sendReq(request);

            }
        });

    }
}
