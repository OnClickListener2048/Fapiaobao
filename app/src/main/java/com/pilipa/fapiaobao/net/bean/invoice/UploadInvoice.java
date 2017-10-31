package com.pilipa.fapiaobao.net.bean.invoice;

import java.util.List;

/**
 * Created by edz on 2017/10/31.
 */

public class UploadInvoice {


    /**
     * invoiceImageList : [{"id":"string","picture":"string"}]
     * orderId : string
     * token : string
     * variety : string
     */

    private String orderId;
    private String token;
    private String variety;
    private List<InvoiceImageListBean> invoiceImageList;

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getVariety() {
        return variety;
    }

    public void setVariety(String variety) {
        this.variety = variety;
    }

    public List<InvoiceImageListBean> getInvoiceImageList() {
        return invoiceImageList;
    }

    public void setInvoiceImageList(List<InvoiceImageListBean> invoiceImageList) {
        this.invoiceImageList = invoiceImageList;
    }

    public static class InvoiceImageListBean {
        /**
         * id : string
         * picture : string
         */

        private String id;
        private String picture;

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
    }
}
