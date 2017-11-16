package com.pilipa.fapiaobao.net.bean.me;

/**
 * Created by edz on 2017/10/29.
 */

public class NormalBean {


    /**
     * status : 200
     * msg : OK
     */

    private int status;
    private String msg;
    private String data;

    public String getData() {
        return data;
    }

    public void setData(String data) {
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
