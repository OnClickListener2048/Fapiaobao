package com.pilipa.fapiaobao.net.bean.me;

/**
 * Created by edz on 2017/10/31.
 */

public class OrderDetailsBean {

    /**
     * status : 200
     * msg : OK
     * data : {"invoiceType":{"id":"46930352ad15484dbe667867a9abca99","isNewRecord":false,"remarks":"","createDate":"2017-10-26 09:43:17","updateDate":"2017-10-29 12:20:50","category":"4","name":"住宿票","smallSize":"","middleSize":"/fapiaobao/userfiles/1/files/personal/invoiceType/2017/10/receipt_002.png","largeSize":"","categorySort":10,"frequentFlag":"1"},"bonus":5,"company":{"id":"4b58dc46526b4d0bbef2c868edec1fd9","isNewRecord":false,"remarks":"同仁堂药业","createDate":"2017-10-24 17:42:20","updateDate":"2017-10-24 17:42:20","customer":{"id":"be3c4b4a0885457e9b72a86c54b58ee2","isNewRecord":false},"name":"北京同仁堂","taxno":"564564564564","address":"北京朝阳","phone":"022-1545454","depositBank":"中国农业银行","account":"256486456456456","qrcode":"w4dsadwda","isDefault":"1"},"orderState":"1","needMail":false}
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
         * invoiceType : {"id":"46930352ad15484dbe667867a9abca99","isNewRecord":false,"remarks":"","createDate":"2017-10-26 09:43:17","updateDate":"2017-10-29 12:20:50","category":"4","name":"住宿票","smallSize":"","middleSize":"/fapiaobao/userfiles/1/files/personal/invoiceType/2017/10/receipt_002.png","largeSize":"","categorySort":10,"frequentFlag":"1"}
         * bonus : 5
         * company : {"id":"4b58dc46526b4d0bbef2c868edec1fd9","isNewRecord":false,"remarks":"同仁堂药业","createDate":"2017-10-24 17:42:20","updateDate":"2017-10-24 17:42:20","customer":{"id":"be3c4b4a0885457e9b72a86c54b58ee2","isNewRecord":false},"name":"北京同仁堂","taxno":"564564564564","address":"北京朝阳","phone":"022-1545454","depositBank":"中国农业银行","account":"256486456456456","qrcode":"w4dsadwda","isDefault":"1"}
         * orderState : 1
         * needMail : false
         */

        private InvoiceTypeBean invoiceType;
        private int bonus;
        private CompanyBean company;
        private String orderState;
        private boolean needMail;

        public InvoiceTypeBean getInvoiceType() {
            return invoiceType;
        }

        public void setInvoiceType(InvoiceTypeBean invoiceType) {
            this.invoiceType = invoiceType;
        }

        public int getBonus() {
            return bonus;
        }

        public void setBonus(int bonus) {
            this.bonus = bonus;
        }

        public CompanyBean getCompany() {
            return company;
        }

        public void setCompany(CompanyBean company) {
            this.company = company;
        }

        public String getOrderState() {
            return orderState;
        }

        public void setOrderState(String orderState) {
            this.orderState = orderState;
        }

        public boolean isNeedMail() {
            return needMail;
        }

        public void setNeedMail(boolean needMail) {
            this.needMail = needMail;
        }

        public static class InvoiceTypeBean {
            /**
             * id : 46930352ad15484dbe667867a9abca99
             * isNewRecord : false
             * remarks :
             * createDate : 2017-10-26 09:43:17
             * updateDate : 2017-10-29 12:20:50
             * category : 4
             * name : 住宿票
             * smallSize :
             * middleSize : /fapiaobao/userfiles/1/files/personal/invoiceType/2017/10/receipt_002.png
             * largeSize :
             * categorySort : 10
             * frequentFlag : 1
             */

            private String id;
            private boolean isNewRecord;
            private String remarks;
            private String createDate;
            private String updateDate;
            private String category;
            private String name;
            private String smallSize;
            private String middleSize;
            private String largeSize;
            private int categorySort;
            private String frequentFlag;

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

            public String getCategory() {
                return category;
            }

            public void setCategory(String category) {
                this.category = category;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getSmallSize() {
                return smallSize;
            }

            public void setSmallSize(String smallSize) {
                this.smallSize = smallSize;
            }

            public String getMiddleSize() {
                return middleSize;
            }

            public void setMiddleSize(String middleSize) {
                this.middleSize = middleSize;
            }

            public String getLargeSize() {
                return largeSize;
            }

            public void setLargeSize(String largeSize) {
                this.largeSize = largeSize;
            }

            public int getCategorySort() {
                return categorySort;
            }

            public void setCategorySort(int categorySort) {
                this.categorySort = categorySort;
            }

            public String getFrequentFlag() {
                return frequentFlag;
            }

            public void setFrequentFlag(String frequentFlag) {
                this.frequentFlag = frequentFlag;
            }
        }

        public static class CompanyBean {
            /**
             * id : 4b58dc46526b4d0bbef2c868edec1fd9
             * isNewRecord : false
             * remarks : 同仁堂药业
             * createDate : 2017-10-24 17:42:20
             * updateDate : 2017-10-24 17:42:20
             * customer : {"id":"be3c4b4a0885457e9b72a86c54b58ee2","isNewRecord":false}
             * name : 北京同仁堂
             * taxno : 564564564564
             * address : 北京朝阳
             * phone : 022-1545454
             * depositBank : 中国农业银行
             * account : 256486456456456
             * qrcode : w4dsadwda
             * isDefault : 1
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

            public String getIsDefault() {
                return isDefault;
            }

            public void setIsDefault(String isDefault) {
                this.isDefault = isDefault;
            }

            public static class CustomerBean {
                /**
                 * id : be3c4b4a0885457e9b72a86c54b58ee2
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
}
