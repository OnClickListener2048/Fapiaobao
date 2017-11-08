package com.pilipa.fapiaobao.net.bean.me;

/**
 * Created by edz on 2017/11/6.
 */

public class FeedBackBean {


    /**
     * status : 200
     * msg : OK
     * data : 个人意见提交成功
     */

    private int status;
    private String msg;
    private String data;

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

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
