package com.pilipa.fapiaobao.net.bean.base;

/**
 * Created by edz on 2017/12/11.
 */

public class BaseResponseBean<T> {

    /**
     * status : 701
     * msg : token验证失败
     */

    private int status;
    private String msg;
    private Object data;

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

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
}
