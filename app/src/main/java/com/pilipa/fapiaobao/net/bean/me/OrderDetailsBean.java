package com.pilipa.fapiaobao.net.bean.me;

import java.util.List;

/**
 * Created by edz on 2017/10/31.
 */

public class OrderDetailsBean {


    /**
     * status : 200
     * msg : OK
     * data : {"invoiceType":{"id":"43f1faf2cfee4f508d02c36975dfa06d","isNewRecord":false,"remarks":"","createDate":"2017-10-29 12:28:39","updateDate":"2017-10-29 12:28:39","category":"4","name":"会议费","smallSize":"","middleSize":"/fapiaobao/userfiles/1/files/personal/invoiceType/2017/10/receipt_011.png","largeSize":"","categorySort":0,"frequentFlag":"1","frequentSort":0},"invoiceList":[{"id":"48ed7096c39811e785ef00163e089be9","isNewRecord":false,"updateDate":"2017-11-08 16:19:49","order":{"id":"962858652b054c2c917c1e33e0e746d6","isNewRecord":false,"amount":0,"bonus":0},"demand":{"id":"a4e62566ecda49cfb4e1434ab56f0d79","isNewRecord":false,"totalAmount":0,"leftAmount":0,"totalBonus":0,"leftBonus":0,"mailMinimum":0,"beginTotalAmount":0,"endTotalAmount":0,"beginLeftAmount":0,"endLeftAmount":0,"beginTotalBonus":0,"endTotalBonus":0,"beginLeftBonus":0,"endLeftBonus":0,"beginMailMinimum":0,"endMailMinimum":0},"variety":"3","amount":10,"bonus":6,"url":"http://39.106.4.193/fapiaobao/upload/invoice/0fa31b5100cc4ad1b7d409d66eee6eca.jpg","state":"3","beginAmount":0,"endAmount":0,"beginBonus":0,"endBonus":0,"invoiceReject":{"id":"4a739f2f20224589abedf564e42b422a","isNewRecord":false,"createDate":"2017-11-08 16:19:49","updateDate":"2017-11-08 16:19:49","order":{"id":"962858652b054c2c917c1e33e0e746d6","isNewRecord":false,"amount":0,"bonus":0},"orderInvoice":{"id":"48ed7096c39811e785ef00163e089be9","isNewRecord":false,"amount":0,"bonus":0,"beginAmount":0,"endAmount":0,"beginBonus":0,"endBonus":0},"type":"1","reason":"测试驳回"}},{"id":"67626be7c39811e785ef00163e089be9","isNewRecord":false,"updateDate":"2017-11-08 11:20:42","order":{"id":"962858652b054c2c917c1e33e0e746d6","isNewRecord":false,"amount":0,"bonus":0},"demand":{"id":"a4e62566ecda49cfb4e1434ab56f0d79","isNewRecord":false,"totalAmount":0,"leftAmount":0,"totalBonus":0,"leftBonus":0,"mailMinimum":0,"beginTotalAmount":0,"endTotalAmount":0,"beginLeftAmount":0,"endLeftAmount":0,"beginTotalBonus":0,"endTotalBonus":0,"beginLeftBonus":0,"endLeftBonus":0,"beginMailMinimum":0,"endMailMinimum":0},"variety":"2","amount":10,"bonus":4,"url":"http://39.106.4.193/fapiaobao/upload/invoice/0fa31b5100cc4ad1b7d409d66eee6eca.jpg","state":"3","beginAmount":0,"endAmount":0,"beginBonus":0,"endBonus":0,"invoiceReject":{"id":"b746ce3a406943868e7baf69d4930f12","isNewRecord":false,"createDate":"2017-11-08 11:20:42","updateDate":"2017-11-08 11:20:42","order":{"id":"962858652b054c2c917c1e33e0e746d6","isNewRecord":false,"amount":0,"bonus":0},"orderInvoice":{"id":"67626be7c39811e785ef00163e089be9","isNewRecord":false,"amount":0,"bonus":0,"beginAmount":0,"endAmount":0,"beginBonus":0,"endBonus":0},"type":"6","reason":"vhhh"}},{"id":"9805062c23f34370a27613852ffd41bc","isNewRecord":false,"createDate":"2017-11-06 11:33:06","updateDate":"2017-11-06 17:28:50","order":{"id":"962858652b054c2c917c1e33e0e746d6","isNewRecord":false,"amount":0,"bonus":0},"demand":{"id":"a4e62566ecda49cfb4e1434ab56f0d79","isNewRecord":false,"totalAmount":0,"leftAmount":0,"totalBonus":0,"leftBonus":0,"mailMinimum":0,"beginTotalAmount":0,"endTotalAmount":0,"beginLeftAmount":0,"endLeftAmount":0,"beginTotalBonus":0,"endTotalBonus":0,"beginLeftBonus":0,"endLeftBonus":0,"beginMailMinimum":0,"endMailMinimum":0},"variety":"1","amount":10,"bonus":7,"url":"http://39.106.4.193/fapiaobao/upload/invoice/0fa31b5100cc4ad1b7d409d66eee6eca.jpg","state":"3","beginAmount":0,"endAmount":0,"beginBonus":0,"endBonus":0,"invoiceReject":{"id":"ddf5118588ce4068b1e2e7aa0c362e86","isNewRecord":false,"createDate":"2017-11-06 17:28:50","updateDate":"2017-11-06 17:28:50","order":{"id":"962858652b054c2c917c1e33e0e746d6","isNewRecord":false,"amount":0,"bonus":0},"orderInvoice":{"id":"9805062c23f34370a27613852ffd41bc","isNewRecord":false,"amount":0,"bonus":0,"beginAmount":0,"endAmount":0,"beginBonus":0,"endBonus":0},"type":"5"}}],"invoiceCount":3,"bonus":7,"amount":70,"postage":{"id":"552e5f9a40534afb968cec4259f62af3","isNewRecord":false,"receiver":"string","telephone":"string","phone":"string","province":"string","city":"string","district":"string","address":"string","email":"string","logisticsCompany":"string"},"company":{"id":"fa5c505adc8b491a9b49cf3bd2a741ec","isNewRecord":false,"createDate":"2017-11-01 12:17:44","updateDate":"2017-11-01 12:17:44","customer":{"id":"d2f885adde454362a754be7749d9ea8e","isNewRecord":false,"amount":0,"bonus":0,"frozen":0,"creditScore":0,"creditLevel":0,"beginAmount":0,"endAmount":0,"beginBonus":0,"endBonus":0,"beginFrozen":0,"endFrozen":0},"name":"string","taxno":"string","address":"string","phone":"string","depositBank":"string","account":"string","qrcode":"string"},"orderState":"4","needMail":false}
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
         * invoiceType : {"id":"43f1faf2cfee4f508d02c36975dfa06d","isNewRecord":false,"remarks":"","createDate":"2017-10-29 12:28:39","updateDate":"2017-10-29 12:28:39","category":"4","name":"会议费","smallSize":"","middleSize":"/fapiaobao/userfiles/1/files/personal/invoiceType/2017/10/receipt_011.png","largeSize":"","categorySort":0,"frequentFlag":"1","frequentSort":0}
         * invoiceList : [{"id":"48ed7096c39811e785ef00163e089be9","isNewRecord":false,"updateDate":"2017-11-08 16:19:49","order":{"id":"962858652b054c2c917c1e33e0e746d6","isNewRecord":false,"amount":0,"bonus":0},"demand":{"id":"a4e62566ecda49cfb4e1434ab56f0d79","isNewRecord":false,"totalAmount":0,"leftAmount":0,"totalBonus":0,"leftBonus":0,"mailMinimum":0,"beginTotalAmount":0,"endTotalAmount":0,"beginLeftAmount":0,"endLeftAmount":0,"beginTotalBonus":0,"endTotalBonus":0,"beginLeftBonus":0,"endLeftBonus":0,"beginMailMinimum":0,"endMailMinimum":0},"variety":"3","amount":10,"bonus":6,"url":"http://39.106.4.193/fapiaobao/upload/invoice/0fa31b5100cc4ad1b7d409d66eee6eca.jpg","state":"3","beginAmount":0,"endAmount":0,"beginBonus":0,"endBonus":0,"invoiceReject":{"id":"4a739f2f20224589abedf564e42b422a","isNewRecord":false,"createDate":"2017-11-08 16:19:49","updateDate":"2017-11-08 16:19:49","order":{"id":"962858652b054c2c917c1e33e0e746d6","isNewRecord":false,"amount":0,"bonus":0},"orderInvoice":{"id":"48ed7096c39811e785ef00163e089be9","isNewRecord":false,"amount":0,"bonus":0,"beginAmount":0,"endAmount":0,"beginBonus":0,"endBonus":0},"type":"1","reason":"测试驳回"}},{"id":"67626be7c39811e785ef00163e089be9","isNewRecord":false,"updateDate":"2017-11-08 11:20:42","order":{"id":"962858652b054c2c917c1e33e0e746d6","isNewRecord":false,"amount":0,"bonus":0},"demand":{"id":"a4e62566ecda49cfb4e1434ab56f0d79","isNewRecord":false,"totalAmount":0,"leftAmount":0,"totalBonus":0,"leftBonus":0,"mailMinimum":0,"beginTotalAmount":0,"endTotalAmount":0,"beginLeftAmount":0,"endLeftAmount":0,"beginTotalBonus":0,"endTotalBonus":0,"beginLeftBonus":0,"endLeftBonus":0,"beginMailMinimum":0,"endMailMinimum":0},"variety":"2","amount":10,"bonus":4,"url":"http://39.106.4.193/fapiaobao/upload/invoice/0fa31b5100cc4ad1b7d409d66eee6eca.jpg","state":"3","beginAmount":0,"endAmount":0,"beginBonus":0,"endBonus":0,"invoiceReject":{"id":"b746ce3a406943868e7baf69d4930f12","isNewRecord":false,"createDate":"2017-11-08 11:20:42","updateDate":"2017-11-08 11:20:42","order":{"id":"962858652b054c2c917c1e33e0e746d6","isNewRecord":false,"amount":0,"bonus":0},"orderInvoice":{"id":"67626be7c39811e785ef00163e089be9","isNewRecord":false,"amount":0,"bonus":0,"beginAmount":0,"endAmount":0,"beginBonus":0,"endBonus":0},"type":"6","reason":"vhhh"}},{"id":"9805062c23f34370a27613852ffd41bc","isNewRecord":false,"createDate":"2017-11-06 11:33:06","updateDate":"2017-11-06 17:28:50","order":{"id":"962858652b054c2c917c1e33e0e746d6","isNewRecord":false,"amount":0,"bonus":0},"demand":{"id":"a4e62566ecda49cfb4e1434ab56f0d79","isNewRecord":false,"totalAmount":0,"leftAmount":0,"totalBonus":0,"leftBonus":0,"mailMinimum":0,"beginTotalAmount":0,"endTotalAmount":0,"beginLeftAmount":0,"endLeftAmount":0,"beginTotalBonus":0,"endTotalBonus":0,"beginLeftBonus":0,"endLeftBonus":0,"beginMailMinimum":0,"endMailMinimum":0},"variety":"1","amount":10,"bonus":7,"url":"http://39.106.4.193/fapiaobao/upload/invoice/0fa31b5100cc4ad1b7d409d66eee6eca.jpg","state":"3","beginAmount":0,"endAmount":0,"beginBonus":0,"endBonus":0,"invoiceReject":{"id":"ddf5118588ce4068b1e2e7aa0c362e86","isNewRecord":false,"createDate":"2017-11-06 17:28:50","updateDate":"2017-11-06 17:28:50","order":{"id":"962858652b054c2c917c1e33e0e746d6","isNewRecord":false,"amount":0,"bonus":0},"orderInvoice":{"id":"9805062c23f34370a27613852ffd41bc","isNewRecord":false,"amount":0,"bonus":0,"beginAmount":0,"endAmount":0,"beginBonus":0,"endBonus":0},"type":"5"}}]
         * invoiceCount : 3
         * bonus : 7
         * amount : 70
         * postage : {"id":"552e5f9a40534afb968cec4259f62af3","isNewRecord":false,"receiver":"string","telephone":"string","phone":"string","province":"string","city":"string","district":"string","address":"string","email":"string","logisticsCompany":"string"}
         * company : {"id":"fa5c505adc8b491a9b49cf3bd2a741ec","isNewRecord":false,"createDate":"2017-11-01 12:17:44","updateDate":"2017-11-01 12:17:44","customer":{"id":"d2f885adde454362a754be7749d9ea8e","isNewRecord":false,"amount":0,"bonus":0,"frozen":0,"creditScore":0,"creditLevel":0,"beginAmount":0,"endAmount":0,"beginBonus":0,"endBonus":0,"beginFrozen":0,"endFrozen":0},"name":"string","taxno":"string","address":"string","phone":"string","depositBank":"string","account":"string","qrcode":"string"}
         * orderState : 4
         * needMail : false
         */

        private InvoiceTypeBean invoiceType;
        private int invoiceCount;
        private double bonus;
        private double amount;
        private PostageBean postage;
        private CompanyBean company;
        private String orderState;
        private boolean needMail;
        private String favoriteId;
        private double mailMinimum;

        public double getMailMinimum() {
            return mailMinimum;
        }

        public void setMailMinimum(double mailMinimum) {
            this.mailMinimum = mailMinimum;
        }

        public String getFavoriteId() {
            return favoriteId;
        }

        public void setFavoriteId(String favoriteId) {
            this.favoriteId = favoriteId;
        }

        private List<InvoiceListBean> invoiceList;

        public InvoiceTypeBean getInvoiceType() {
            return invoiceType;
        }

        public void setInvoiceType(InvoiceTypeBean invoiceType) {
            this.invoiceType = invoiceType;
        }

        public int getInvoiceCount() {
            return invoiceCount;
        }

        public void setInvoiceCount(int invoiceCount) {
            this.invoiceCount = invoiceCount;
        }

        public double getBonus() {
            return bonus;
        }

        public void setBonus(double bonus) {
            this.bonus = bonus;
        }

        public double getAmount() {
            return amount;
        }

        public void setAmount(double amount) {
            this.amount = amount;
        }

        public PostageBean getPostage() {
            return postage;
        }

        public void setPostage(PostageBean postage) {
            this.postage = postage;
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

        public List<InvoiceListBean> getInvoiceList() {
            return invoiceList;
        }

        public void setInvoiceList(List<InvoiceListBean> invoiceList) {
            this.invoiceList = invoiceList;
        }

        public static class InvoiceTypeBean {
            /**
             * id : 43f1faf2cfee4f508d02c36975dfa06d
             * isNewRecord : false
             * remarks :
             * createDate : 2017-10-29 12:28:39
             * updateDate : 2017-10-29 12:28:39
             * category : 4
             * name : 会议费
             * smallSize :
             * middleSize : /fapiaobao/userfiles/1/files/personal/invoiceType/2017/10/receipt_011.png
             * largeSize :
             * categorySort : 0
             * frequentFlag : 1
             * frequentSort : 0
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
            private int frequentSort;

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

            public int getFrequentSort() {
                return frequentSort;
            }

            public void setFrequentSort(int frequentSort) {
                this.frequentSort = frequentSort;
            }
        }

        public static class PostageBean {
            /**
             * id : 552e5f9a40534afb968cec4259f62af3
             * isNewRecord : false
             * receiver : string
             * telephone : string
             * phone : string
             * province : string
             * city : string
             * district : string
             * address : string
             * email : string
             * logisticsCompany : string
             */

            private String id;
            private boolean isNewRecord;
            private String receiver;
            private String telephone;
            private String phone;
            private String province;
            private String city;
            private String district;
            private String address;
            private String email;
            private String logisticsCompany;

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

            public String getReceiver() {
                return receiver;
            }

            public void setReceiver(String receiver) {
                this.receiver = receiver;
            }

            public String getTelephone() {
                return telephone;
            }

            public void setTelephone(String telephone) {
                this.telephone = telephone;
            }

            public String getPhone() {
                return phone;
            }

            public void setPhone(String phone) {
                this.phone = phone;
            }

            public String getProvince() {
                return province;
            }

            public void setProvince(String province) {
                this.province = province;
            }

            public String getCity() {
                return city;
            }

            public void setCity(String city) {
                this.city = city;
            }

            public String getDistrict() {
                return district;
            }

            public void setDistrict(String district) {
                this.district = district;
            }

            public String getAddress() {
                return address;
            }

            public void setAddress(String address) {
                this.address = address;
            }

            public String getEmail() {
                return email;
            }

            public void setEmail(String email) {
                this.email = email;
            }

            public String getLogisticsCompany() {
                return logisticsCompany;
            }

            public void setLogisticsCompany(String logisticsCompany) {
                this.logisticsCompany = logisticsCompany;
            }
        }

        public static class CompanyBean {
            /**
             * id : fa5c505adc8b491a9b49cf3bd2a741ec
             * isNewRecord : false
             * createDate : 2017-11-01 12:17:44
             * updateDate : 2017-11-01 12:17:44
             * customer : {"id":"d2f885adde454362a754be7749d9ea8e","isNewRecord":false,"amount":0,"bonus":0,"frozen":0,"creditScore":0,"creditLevel":0,"beginAmount":0,"endAmount":0,"beginBonus":0,"endBonus":0,"beginFrozen":0,"endFrozen":0}
             * name : string
             * taxno : string
             * address : string
             * phone : string
             * depositBank : string
             * account : string
             * qrcode : string
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

            public static class CustomerBean {
                /**
                 * id : d2f885adde454362a754be7749d9ea8e
                 * isNewRecord : false
                 * amount : 0
                 * bonus : 0
                 * frozen : 0
                 * creditScore : 0
                 * creditLevel : 0
                 * beginAmount : 0
                 * endAmount : 0
                 * beginBonus : 0
                 * endBonus : 0
                 * beginFrozen : 0
                 * endFrozen : 0
                 */

                private String id;
                private boolean isNewRecord;
                private double amount;
                private double bonus;
                private double frozen;
                private int creditScore;
                private int creditLevel;
                private double beginAmount;
                private double endAmount;
                private double beginBonus;
                private double endBonus;
                private double beginFrozen;
                private double endFrozen;

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

                public double getAmount() {
                    return amount;
                }

                public void setAmount(double amount) {
                    this.amount = amount;
                }

                public double getBonus() {
                    return bonus;
                }

                public void setBonus(double bonus) {
                    this.bonus = bonus;
                }

                public double getFrozen() {
                    return frozen;
                }

                public void setFrozen(double frozen) {
                    this.frozen = frozen;
                }

                public int getCreditScore() {
                    return creditScore;
                }

                public void setCreditScore(int creditScore) {
                    this.creditScore = creditScore;
                }

                public int getCreditLevel() {
                    return creditLevel;
                }

                public void setCreditLevel(int creditLevel) {
                    this.creditLevel = creditLevel;
                }

                public double getBeginAmount() {
                    return beginAmount;
                }

                public void setBeginAmount(double beginAmount) {
                    this.beginAmount = beginAmount;
                }

                public double getEndAmount() {
                    return endAmount;
                }

                public void setEndAmount(double endAmount) {
                    this.endAmount = endAmount;
                }

                public double getBeginBonus() {
                    return beginBonus;
                }

                public void setBeginBonus(double beginBonus) {
                    this.beginBonus = beginBonus;
                }

                public double getEndBonus() {
                    return endBonus;
                }

                public void setEndBonus(double endBonus) {
                    this.endBonus = endBonus;
                }

                public double getBeginFrozen() {
                    return beginFrozen;
                }

                public void setBeginFrozen(double beginFrozen) {
                    this.beginFrozen = beginFrozen;
                }

                public double getEndFrozen() {
                    return endFrozen;
                }

                public void setEndFrozen(double endFrozen) {
                    this.endFrozen = endFrozen;
                }
            }
        }

        public static class InvoiceListBean {
            /**
             * id : 48ed7096c39811e785ef00163e089be9
             * isNewRecord : false
             * updateDate : 2017-11-08 16:19:49
             * order : {"id":"962858652b054c2c917c1e33e0e746d6","isNewRecord":false,"amount":0,"bonus":0}
             * demand : {"id":"a4e62566ecda49cfb4e1434ab56f0d79","isNewRecord":false,"totalAmount":0,"leftAmount":0,"totalBonus":0,"leftBonus":0,"mailMinimum":0,"beginTotalAmount":0,"endTotalAmount":0,"beginLeftAmount":0,"endLeftAmount":0,"beginTotalBonus":0,"endTotalBonus":0,"beginLeftBonus":0,"endLeftBonus":0,"beginMailMinimum":0,"endMailMinimum":0}
             * variety : 3
             * amount : 10
             * bonus : 6
             * url : http://39.106.4.193/fapiaobao/upload/invoice/0fa31b5100cc4ad1b7d409d66eee6eca.jpg
             * state : 3
             * beginAmount : 0
             * endAmount : 0
             * beginBonus : 0
             * endBonus : 0
             * invoiceReject : {"id":"4a739f2f20224589abedf564e42b422a","isNewRecord":false,"createDate":"2017-11-08 16:19:49","updateDate":"2017-11-08 16:19:49","order":{"id":"962858652b054c2c917c1e33e0e746d6","isNewRecord":false,"amount":0,"bonus":0},"orderInvoice":{"id":"48ed7096c39811e785ef00163e089be9","isNewRecord":false,"amount":0,"bonus":0,"beginAmount":0,"endAmount":0,"beginBonus":0,"endBonus":0},"type":"1","reason":"测试驳回"}
             * createDate : 2017-11-06 11:33:06
             */

            private String id;
            private boolean isNewRecord;
            private String updateDate;
            private OrderBean order;
            private DemandBean demand;
            private String variety;
            private double amount;
            private double bonus;
            private String url;
            private String state;
            private double beginAmount;
            private double endAmount;
            private double beginBonus;
            private double endBonus;
            private String logisticsCompany;
            private String logisticsTradeno;
            private InvoiceRejectBean invoiceReject;
            private String createDate;

            public String getLogisticsCompany() {
                return logisticsCompany;
            }

            public void setLogisticsCompany(String logisticsCompany) {
                this.logisticsCompany = logisticsCompany;
            }

            public String getLogisticsTradeno() {
                return logisticsTradeno;
            }

            public void setLogisticsTradeno(String logisticsTradeno) {
                this.logisticsTradeno = logisticsTradeno;
            }

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

            public String getUpdateDate() {
                return updateDate;
            }

            public void setUpdateDate(String updateDate) {
                this.updateDate = updateDate;
            }

            public OrderBean getOrder() {
                return order;
            }

            public void setOrder(OrderBean order) {
                this.order = order;
            }

            public DemandBean getDemand() {
                return demand;
            }

            public void setDemand(DemandBean demand) {
                this.demand = demand;
            }

            public String getVariety() {
                return variety;
            }

            public void setVariety(String variety) {
                this.variety = variety;
            }

            public double getAmount() {
                return amount;
            }

            public void setAmount(double amount) {
                this.amount = amount;
            }

            public double getBonus() {
                return bonus;
            }

            public void setBonus(double bonus) {
                this.bonus = bonus;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }

            public String getState() {
                return state;
            }

            public void setState(String state) {
                this.state = state;
            }

            public double getBeginAmount() {
                return beginAmount;
            }

            public void setBeginAmount(double beginAmount) {
                this.beginAmount = beginAmount;
            }

            public double getEndAmount() {
                return endAmount;
            }

            public void setEndAmount(double endAmount) {
                this.endAmount = endAmount;
            }

            public double getBeginBonus() {
                return beginBonus;
            }

            public void setBeginBonus(double beginBonus) {
                this.beginBonus = beginBonus;
            }

            public double getEndBonus() {
                return endBonus;
            }

            public void setEndBonus(double endBonus) {
                this.endBonus = endBonus;
            }

            public InvoiceRejectBean getInvoiceReject() {
                return invoiceReject;
            }

            public void setInvoiceReject(InvoiceRejectBean invoiceReject) {
                this.invoiceReject = invoiceReject;
            }

            public String getCreateDate() {
                return createDate;
            }

            public void setCreateDate(String createDate) {
                this.createDate = createDate;
            }

            public static class OrderBean {
                /**
                 * id : 962858652b054c2c917c1e33e0e746d6
                 * isNewRecord : false
                 * amount : 0
                 * bonus : 0
                 */

                private String id;
                private boolean isNewRecord;
                private double amount;
                private double bonus;

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

                public double getAmount() {
                    return amount;
                }

                public void setAmount(double amount) {
                    this.amount = amount;
                }

                public double getBonus() {
                    return bonus;
                }

                public void setBonus(double bonus) {
                    this.bonus = bonus;
                }
            }

            public static class DemandBean {
                /**
                 * id : a4e62566ecda49cfb4e1434ab56f0d79
                 * isNewRecord : false
                 * totalAmount : 0
                 * leftAmount : 0
                 * totalBonus : 0
                 * leftBonus : 0
                 * mailMinimum : 0
                 * beginTotalAmount : 0
                 * endTotalAmount : 0
                 * beginLeftAmount : 0
                 * endLeftAmount : 0
                 * beginTotalBonus : 0
                 * endTotalBonus : 0
                 * beginLeftBonus : 0
                 * endLeftBonus : 0
                 * beginMailMinimum : 0
                 * endMailMinimum : 0
                 */

                private String id;
                private boolean isNewRecord;
                private double totalAmount;
                private double leftAmount;
                private double totalBonus;
                private double leftBonus;
                private double mailMinimum;
                private double beginTotalAmount;
                private double endTotalAmount;
                private double beginLeftAmount;
                private double endLeftAmount;
                private double beginTotalBonus;
                private double endTotalBonus;
                private double beginLeftBonus;
                private double endLeftBonus;
                private double beginMailMinimum;
                private double endMailMinimum;

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

                public double getTotalAmount() {
                    return totalAmount;
                }

                public void setTotalAmount(double totalAmount) {
                    this.totalAmount = totalAmount;
                }

                public double getLeftAmount() {
                    return leftAmount;
                }

                public void setLeftAmount(double leftAmount) {
                    this.leftAmount = leftAmount;
                }

                public double getTotalBonus() {
                    return totalBonus;
                }

                public void setTotalBonus(double totalBonus) {
                    this.totalBonus = totalBonus;
                }

                public double getLeftBonus() {
                    return leftBonus;
                }

                public void setLeftBonus(double leftBonus) {
                    this.leftBonus = leftBonus;
                }

                public double getMailMinimum() {
                    return mailMinimum;
                }

                public void setMailMinimum(double mailMinimum) {
                    this.mailMinimum = mailMinimum;
                }

                public double getBeginTotalAmount() {
                    return beginTotalAmount;
                }

                public void setBeginTotalAmount(double beginTotalAmount) {
                    this.beginTotalAmount = beginTotalAmount;
                }

                public double getEndTotalAmount() {
                    return endTotalAmount;
                }

                public void setEndTotalAmount(double endTotalAmount) {
                    this.endTotalAmount = endTotalAmount;
                }

                public double getBeginLeftAmount() {
                    return beginLeftAmount;
                }

                public void setBeginLeftAmount(double beginLeftAmount) {
                    this.beginLeftAmount = beginLeftAmount;
                }

                public double getEndLeftAmount() {
                    return endLeftAmount;
                }

                public void setEndLeftAmount(double endLeftAmount) {
                    this.endLeftAmount = endLeftAmount;
                }

                public double getBeginTotalBonus() {
                    return beginTotalBonus;
                }

                public void setBeginTotalBonus(double beginTotalBonus) {
                    this.beginTotalBonus = beginTotalBonus;
                }

                public double getEndTotalBonus() {
                    return endTotalBonus;
                }

                public void setEndTotalBonus(double endTotalBonus) {
                    this.endTotalBonus = endTotalBonus;
                }

                public double getBeginLeftBonus() {
                    return beginLeftBonus;
                }

                public void setBeginLeftBonus(double beginLeftBonus) {
                    this.beginLeftBonus = beginLeftBonus;
                }

                public double getEndLeftBonus() {
                    return endLeftBonus;
                }

                public void setEndLeftBonus(double endLeftBonus) {
                    this.endLeftBonus = endLeftBonus;
                }

                public double getBeginMailMinimum() {
                    return beginMailMinimum;
                }

                public void setBeginMailMinimum(double beginMailMinimum) {
                    this.beginMailMinimum = beginMailMinimum;
                }

                public double getEndMailMinimum() {
                    return endMailMinimum;
                }

                public void setEndMailMinimum(double endMailMinimum) {
                    this.endMailMinimum = endMailMinimum;
                }
            }

            public static class InvoiceRejectBean {
                /**
                 * id : 4a739f2f20224589abedf564e42b422a
                 * isNewRecord : false
                 * createDate : 2017-11-08 16:19:49
                 * updateDate : 2017-11-08 16:19:49
                 * order : {"id":"962858652b054c2c917c1e33e0e746d6","isNewRecord":false,"amount":0,"bonus":0}
                 * orderInvoice : {"id":"48ed7096c39811e785ef00163e089be9","isNewRecord":false,"amount":0,"bonus":0,"beginAmount":0,"endAmount":0,"beginBonus":0,"endBonus":0}
                 * type : 1
                 * reason : 测试驳回
                 */

                private String id;
                private boolean isNewRecord;
                private String createDate;
                private String updateDate;
                private OrderBeanX order;
                private OrderInvoiceBean orderInvoice;
                private String type;
                private String reason;

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

                public OrderBeanX getOrder() {
                    return order;
                }

                public void setOrder(OrderBeanX order) {
                    this.order = order;
                }

                public OrderInvoiceBean getOrderInvoice() {
                    return orderInvoice;
                }

                public void setOrderInvoice(OrderInvoiceBean orderInvoice) {
                    this.orderInvoice = orderInvoice;
                }

                public String getType() {
                    return type;
                }

                public void setType(String type) {
                    this.type = type;
                }

                public String getReason() {
                    return reason;
                }

                public void setReason(String reason) {
                    this.reason = reason;
                }

                public static class OrderBeanX {
                    /**
                     * id : 962858652b054c2c917c1e33e0e746d6
                     * isNewRecord : false
                     * amount : 0
                     * bonus : 0
                     */

                    private String id;
                    private boolean isNewRecord;
                    private double amount;
                    private double bonus;

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

                    public double getAmount() {
                        return amount;
                    }

                    public void setAmount(double amount) {
                        this.amount = amount;
                    }

                    public double getBonus() {
                        return bonus;
                    }

                    public void setBonus(double bonus) {
                        this.bonus = bonus;
                    }
                }

                public static class OrderInvoiceBean {
                    /**
                     * id : 48ed7096c39811e785ef00163e089be9
                     * isNewRecord : false
                     * amount : 0
                     * bonus : 0
                     * beginAmount : 0
                     * endAmount : 0
                     * beginBonus : 0
                     * endBonus : 0
                     */

                    private String id;
                    private boolean isNewRecord;
                    private double amount;
                    private double bonus;
                    private double beginAmount;
                    private double endAmount;
                    private double beginBonus;
                    private double endBonus;

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

                    public double getAmount() {
                        return amount;
                    }

                    public void setAmount(double amount) {
                        this.amount = amount;
                    }

                    public double getBonus() {
                        return bonus;
                    }

                    public void setBonus(double bonus) {
                        this.bonus = bonus;
                    }

                    public double getBeginAmount() {
                        return beginAmount;
                    }

                    public void setBeginAmount(double beginAmount) {
                        this.beginAmount = beginAmount;
                    }

                    public double getEndAmount() {
                        return endAmount;
                    }

                    public void setEndAmount(double endAmount) {
                        this.endAmount = endAmount;
                    }

                    public double getBeginBonus() {
                        return beginBonus;
                    }

                    public void setBeginBonus(double beginBonus) {
                        this.beginBonus = beginBonus;
                    }

                    public double getEndBonus() {
                        return endBonus;
                    }

                    public void setEndBonus(double endBonus) {
                        this.endBonus = endBonus;
                    }
                }
            }
        }
    }
}
