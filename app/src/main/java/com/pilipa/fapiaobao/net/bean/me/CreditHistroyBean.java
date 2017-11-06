package com.pilipa.fapiaobao.net.bean.me;

import java.util.List;

/**
 * Created by edz on 2017/11/6.
 */

public class CreditHistroyBean {

    /**
     * status : 200
     * msg : OK
     * data : [{"id":"91f5fa30a8f64d62a6bd17baaa14645d","isNewRecord":false,"createDate":"2017-11-06 17:43:14","updateDate":"2017-11-06 17:43:18","customer":{"id":"91f5fa30a8f64d62a6bd17baaa14645d","isNewRecord":false},"score":100,"type":"1"},{"id":"91f5fa30a8f64d62a6errreaaa14645d","isNewRecord":false,"remarks":"","createDate":"2017-11-06 17:43:14","updateDate":"2017-11-06 17:43:18","customer":{"id":"91f5fa30a8f64d62a6bd17baaa14645d","isNewRecord":false},"score":-10,"type":"1"}]
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
         * id : 91f5fa30a8f64d62a6bd17baaa14645d
         * isNewRecord : false
         * createDate : 2017-11-06 17:43:14
         * updateDate : 2017-11-06 17:43:18
         * customer : {"id":"91f5fa30a8f64d62a6bd17baaa14645d","isNewRecord":false}
         * score : 100
         * type : 1
         * remarks :
         */

        private String id;
        private boolean isNewRecord;
        private String createDate;
        private String updateDate;
        private CustomerBean customer;
        private int score;
        private String type;
        private String remarks;

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

        public int getScore() {
            return score;
        }

        public void setScore(int score) {
            this.score = score;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getRemarks() {
            return remarks;
        }

        public void setRemarks(String remarks) {
            this.remarks = remarks;
        }

        public static class CustomerBean {
            /**
             * id : 91f5fa30a8f64d62a6bd17baaa14645d
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
