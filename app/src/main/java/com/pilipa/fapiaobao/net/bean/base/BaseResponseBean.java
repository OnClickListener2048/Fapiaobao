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
    private T data;

    public T getData() {
        return data;
    }

    public void setData(T data) {
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
