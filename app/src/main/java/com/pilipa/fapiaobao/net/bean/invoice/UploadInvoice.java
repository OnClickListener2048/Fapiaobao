package com.pilipa.fapiaobao.net.bean.invoice;

import java.util.List;

/**
 * Created by edz on 2017/10/31.
 */

public class UploadInvoice {

    /**
     * invoiceVarietyList : [{"invoiceImageList":[{"id":"string","picture":"string"}],"variety":"string"}]
     * orderId : string
     * token : string
     */

    private String orderId;
    private String token;
    private List<InvoiceVarietyListBean> invoiceVarietyList;

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

    public List<InvoiceVarietyListBean> getInvoiceVarietyList() {
        return invoiceVarietyList;
    }

    public void setInvoiceVarietyList(List<InvoiceVarietyListBean> invoiceVarietyList) {
        this.invoiceVarietyList = invoiceVarietyList;
    }

    public static class InvoiceVarietyListBean {
        /**
         * invoiceImageList : [{"id":"string","picture":"string"}]
         * variety : string
         */

        private String variety;
        private List<InvoiceImageListBean> invoiceImageList;

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
}
