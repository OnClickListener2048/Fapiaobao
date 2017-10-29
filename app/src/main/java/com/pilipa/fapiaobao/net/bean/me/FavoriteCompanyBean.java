package com.pilipa.fapiaobao.net.bean.me;

import java.util.List;

/**
 * Created by edz on 2017/10/29.
 */

public class FavoriteCompanyBean {


    /**
     * status : 200
     * msg : OK
     * data : [{"id":"dc4a88a745482edf89a6702a9660094f","isNewRecord":false,"remarks":"","createDate":"2017-10-25 17:26:13","updateDate":"2017-10-25 17:26:13","customer":{"id":"6ee15c894b1a435d9c24025b324e17f7","isNewRecord":false},"name":"天津爱康鼎科技有限公司","taxno":"320400137674452","address":"天津市红桥区海河华鼎大厦","phone":"022-12554551","depositBank":"中国民生银行天津分行","account":"55555555555555555","qrcode":"www.sdsadsasd.dsada"},{"id":"dc4a88a745484edf89a6702a9660094f","isNewRecord":false,"remarks":"","createDate":"2017-10-25 17:26:13","updateDate":"2017-10-25 17:26:13","customer":{"id":"6ee15c894b1a435d9c24025b324e17f7","isNewRecord":false},"name":"天津爱康鼎科技有限公司","taxno":"320400137674452","address":"天津市红桥区海河华鼎大厦","phone":"022-12554551","depositBank":"中国民生银行天津分行","account":"55555555555555555","qrcode":"www.sdsadsasd.dsada"}]
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
         * id : dc4a88a745482edf89a6702a9660094f
         * isNewRecord : false
         * remarks :
         * createDate : 2017-10-25 17:26:13
         * updateDate : 2017-10-25 17:26:13
         * customer : {"id":"6ee15c894b1a435d9c24025b324e17f7","isNewRecord":false}
         * name : 天津爱康鼎科技有限公司
         * taxno : 320400137674452
         * address : 天津市红桥区海河华鼎大厦
         * phone : 022-12554551
         * depositBank : 中国民生银行天津分行
         * account : 55555555555555555
         * qrcode : www.sdsadsasd.dsada
         */

        private String id;
        private boolean isNewRecord;
        private String remarks;
        private String createDate;
        private String updateDate;
        private CustomerBean customer;
        private String name;
        private String taxno;
        private String address;
        private String phone;
        private String depositBank;
        private String account;
        private String qrcode;

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

        public String getRemarks() {
            return remarks;
        }

        public void setRemarks(String remarks) {
            this.remarks = remarks;
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

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getTaxno() {
            return taxno;
        }

        public void setTaxno(String taxno) {
            this.taxno = taxno;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getDepositBank() {
            return depositBank;
        }

        public void setDepositBank(String depositBank) {
            this.depositBank = depositBank;
        }

        public String getAccount() {
            return account;
        }

        public void setAccount(String account) {
            this.account = account;
        }

        public String getQrcode() {
            return qrcode;
        }

        public void setQrcode(String qrcode) {
            this.qrcode = qrcode;
        }

        public static class CustomerBean {
            /**
             * id : 6ee15c894b1a435d9c24025b324e17f7
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
