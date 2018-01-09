package com.pilipa.fapiaobao.net.bean.base;

/**
 * Created by edz on 2018/1/9.
 */

public class SimpleResponse {

    private int status;
    private String msg;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public BaseResponseBean toBaseResponseBean() {
        BaseResponseBean baseResponseBean = new BaseResponseBean();
        baseResponseBean.setStatus(status);
        baseResponseBean.setMsg(msg);
        return baseResponseBean;
    }
}
