package com.pilipa.fapiaobao.net.bean.me;

import java.util.List;

/**
 * Created by edz on 2017/10/30.
 */

public class MyInvoiceListBean {

    /**
     * status : 200
     * msg : OK
     * data : [{"id":"de73d831aef7440ea86153334df0c079","isNewRecord":false,"createDate":"2017-10-28 16:33:54","updateDate":"2017-10-28 16:33:54","customer":{"id":"8a55ea81e4b948399a59ee179c0321f3","isNewRecord":false},"url":"http://192.168.1.205:8181/fapiaobao/upload/invoice/89c0877ca0f140809210dfb6892d534a.jpg"}]
     */

    private int status;
    private String msg;
    private List<DataBean> data;

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

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : de73d831aef7440ea86153334df0c079
         * isNewRecord : false
         * createDate : 2017-10-28 16:33:54
         * updateDate : 2017-10-28 16:33:54
         * customer : {"id":"8a55ea81e4b948399a59ee179c0321f3","isNewRecord":false}
         * url : http://192.168.1.205:8181/fapiaobao/upload/invoice/89c0877ca0f140809210dfb6892d534a.jpg
         */

        private String id;
        private boolean isNewRecord;
        private String createDate;
        private String updateDate;
        private CustomerBean customer;
        private String url;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public boolean isIsNewRecord() {
            return isNewRecord;
        }

        public void setIsNewRecord(boolean isNewRecord) {
            this.isNewRecord = isNewRecord;
        }

        public String getCreateDate() {
            return createDate;
        }

        public void setCreateDate(String createDate) {
            this.createDate = createDate;
        }

        public String getUpdateDate() {
            return updateDate;
        }

        public void setUpdateDate(String updateDate) {
            this.updateDate = updateDate;
        }

        public CustomerBean getCustomer() {
            return customer;
        }

        public void setCustomer(CustomerBean customer) {
            this.customer = customer;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public static class CustomerBean {
            /**
             * id : 8a55ea81e4b948399a59ee179c0321f3
             * isNewRecord : false
             */

            private String id;
            private boolean isNewRecord;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public boolean isIsNewRecord() {
                return isNewRecord;
            }

            public void setIsNewRecord(boolean isNewRecord) {
                this.isNewRecord = isNewRecord;
            }
        }
    }
}
