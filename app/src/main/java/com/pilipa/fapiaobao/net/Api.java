package com.pilipa.fapiaobao.net;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.pilipa.fapiaobao.net.bean.ShortMessageBean;
import com.pilipa.fapiaobao.net.callback.JsonCallBack;

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


    public interface BaseViewCallback<T> {
        void setData(T t);
    }

}
