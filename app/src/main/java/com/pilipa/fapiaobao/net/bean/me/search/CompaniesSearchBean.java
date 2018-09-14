package com.pilipa.fapiaobao.net.bean.me.search;

import java.util.List;

/**
 * Created by edz on 2018/1/5.
 */

public class CompaniesSearchBean {


    /**
     * status : 200
     * msg : OK
     * data : [{"nsrsbh":"911202247328197401","nsrmc":"天津恩爱环保有限公司"},{"nsrsbh":"91120104600553055K","nsrmc":"天津爱普生有限公司"},{"nsrsbh":"91120116569301027H","nsrmc":"爱康金融服务(天津)有限公司"},{"nsrsbh":"91120111340871195G","nsrmc":"天津市爱心养老服务有限公司"},{"nsrsbh":"91120111730358629K","nsrmc":"天津市博爱制药有限公司"}]
     */

    private int status;
    private String msg;
    private List<CompaniesBean> data;

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

    public List<CompaniesBean> getData() {
        return data;
    }

    public void setData(List<CompaniesBean> data) {
        this.data = data;
    }


}
