package com.pilipa.fapiaobao.net;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.pilipa.fapiaobao.net.bean.LoginBean;
import com.pilipa.fapiaobao.net.bean.ShortMessageBean;
import com.pilipa.fapiaobao.net.bean.invoice.AllInvoiceType;
import com.pilipa.fapiaobao.net.callback.JsonCallBack;
import com.pilipa.fapiaobao.utils.PayCommonUtil;
import com.pilipa.fapiaobao.wxapi.Constants;

/**
 * Created by lyt on 2017/10/12.
 */

public class Api {

    public static void sendMessageToVerify(String moible, final BaseViewCallback baseViewCallback) {
        OkGo.<ShortMessageBean>get(String.format(Constant.SHORT_MESSAGE_VERIFY, moible)).execute(new JsonCallBack<ShortMessageBean>(ShortMessageBean.class) {
            @Override
            public void onSuccess(Response<ShortMessageBean> response) {
                if ("OK".equals(response.body().getMsg())) {
                    baseViewCallback.setData(response.body());
                }
            }
        });
    }
    public static void login(String platform,String credenceName,String credenceCode, final BaseViewCallback baseViewCallback) {
        OkGo.<LoginBean>get(String.format(Constant.SHORT_MESSAGE_VERIFY, platform,credenceName,credenceCode)).execute(new JsonCallBack<LoginBean>(LoginBean.class) {
            @Override
            public void onSuccess(Response<LoginBean> response) {
                if ("OK".equals(response.body().getMsg())) {
                    baseViewCallback.setData(response.body());
                }
            }
        });
    }


    public static void wxPay(String total_fee, final BaseViewCallback baseViewCallback) {
        OkGo.<String>post(Constants.PREPAY_URL).upString(PayCommonUtil.getRequestXml(PayCommonUtil.wxPrePay(total_fee))).execute(new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                baseViewCallback.setData(response.body());
            }
        });
    }

    public static void findAllInvoice(final BaseViewCallback baseViewCallback) {
        OkGo.<AllInvoiceType>get(Constant.FIND_ALL_INVIICE_TYPE).execute(new JsonCallBack<AllInvoiceType>(AllInvoiceType.class) {
            @Override
            public void onSuccess(Response<AllInvoiceType> response) {
                if (response.isSuccessful()&&response.body().getMsg().equals("OK")) {
                    baseViewCallback.setData(response.body());
                }
            }
        });
    }



    public interface BaseViewCallback<T> {
        void setData(T t);
    }

}
