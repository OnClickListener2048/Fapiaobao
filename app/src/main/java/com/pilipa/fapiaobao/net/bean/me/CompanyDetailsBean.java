package com.pilipa.fapiaobao.net.bean.me;

import java.io.Serializable;

/**
 * Created by edz on 2017/10/29.
 */

public class CompanyDetailsBean implements Serializable{


    /**
     * status : 200
     * msg : OK
     * data : {"id":"1d660812a99e4b439cdcb4ed9946b271","isNewRecord":false,"createDate":"2017-10-28 15:10:22","updateDate":"2017-10-28 15:10:22","customer":{"id":"6ee15c894b1a435d9c24025b324e17f7","isNewRecord":false},"name":"天津爱丽丝有限公司","taxno":"4844645646464646464644","address":"天津市海河西路","phone":"022-18548488","depositBank":"建设银行","account":"8448489489485448456","qrcode":"www.baidu.com","isDefault":"0"}
     */

    private int status;
    private String msg;
    private DataBean data;

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

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : 1d660812a99e4b439cdcb4ed9946b271
         * isNewRecord : false
         * createDate : 2017-10-28 15:10:22
         * updateDate : 2017-10-28 15:10:22
         * customer : {"id":"6ee15c894b1a435d9c24025b324e17f7","isNewRecord":false}
         * name : 天津爱丽丝有限公司
         * taxno : 4844645646464646464644
         * address : 天津市海河西路
         * phone : 022-18548488
         * depositBank : 建设银行
         * account : 8448489489485448456
         * qrcode : www.baidu.com
         * isDefault : 0
         */

        private String id;
        private boolean isNewRecord;
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
        private String isDefault;

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

        public String getIsDefault() {
            return isDefault;
        }

        public void setIsDefault(String isDefault) {
            this.isDefault = isDefault;
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
