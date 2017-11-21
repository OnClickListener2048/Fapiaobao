package com.pilipa.fapiaobao.net.bean.invoice;

import java.util.List;

/**
 * Created by edz on 2017/11/6.
 */

public class UploadInvoiceToken {

    /**
     * demandId : string
     * invoiceList : [{"amount":0,"id":"string","picture":"string","variety":"string"}]
     * invoiceType : string
     * token : string
     * totalAmount : 0
     */

    private String demandId;
    private String invoiceType;
    private String token;
    private double totalAmount;
    private List<InvoiceListBean> invoiceList;

    public String getDemandId() {
        return demandId;
    }

    public void setDemandId(String demandId) {
        this.demandId = demandId;
    }

    public String getInvoiceType() {
        return invoiceType;
    }

    public void setInvoiceType(String invoiceType) {
        this.invoiceType = invoiceType;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public List<InvoiceListBean> getInvoiceList() {
        return invoiceList;
    }

    public void setInvoiceList(List<InvoiceListBean> invoiceList) {
        this.invoiceList = invoiceList;
    }

    public static class InvoiceListBean {
        /**
         * amount : 0
         * id : string
         * picture : string
         * variety : string
         */

        private double amount;
        private String id;
        private String picture;
        private String variety;

        public double getAmount() {
            return amount;
        }

        public void setAmount(double amount) {
            this.amount = amount;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getPicture() {
            return picture;
        }

        public void setPicture(String picture) {
            this.picture = picture;
        }

        public String getVariety() {
            return variety;
        }

        public void setVariety(String variety) {
            this.variety = variety;
        }
    }
}
