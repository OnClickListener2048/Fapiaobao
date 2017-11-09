package com.pilipa.fapiaobao.net.bean.publish;

import java.util.List;

/**
 * Created by edz on 2017/10/29.
 */

public class DemandDetails {


    /**
     * status : 200
     * msg : OK
     * data : {"invoiceNameList":["会议费","会议费"],"receivedNum":0,"publishDate":"2017-11-02","receivedAmount":0,"demand":{"isNewRecord":true,"company":{"isNewRecord":true,"name":"string","taxno":"string","address":"string","phone":"string","depositBank":"string","account":"string","qrcode":"string"},"demandPostage":{"isNewRecord":true,"receiver":"string","telephone":"string","phone":"string","province":"string","city":"string","district":"string","address":"string","email":"string"},"invoiceVarieties":"1,2,3","areaRestrict":"1","province":"string","city":"天津市","deadline":"2018-11-16","totalAmount":1000,"leftAmount":311,"totalBonus":100,"leftBonus":31.1,"mailMinimum":0,"attentions":"请务必上传真实发票","state":"0","beginTotalAmount":0,"endTotalAmount":0,"beginLeftAmount":0,"endLeftAmount":0,"beginTotalBonus":0,"endTotalBonus":0,"beginLeftBonus":0,"endLeftBonus":0,"beginMailMinimum":0,"endMailMinimum":0,"publishDate":"2017-11-02"},"orderInvoiceList":[{"id":"c3007bb7094540e1bd3849eb6a33a55e","isNewRecord":false,"createDate":"2017-11-06 11:15:30","updateDate":"2017-11-07 09:40:58","order":{"id":"d1467ab24b434977be9047cf7afdb11a","isNewRecord":false,"amount":0,"bonus":0},"demand":{"id":"a4e62566ecda49cfb4e1434ab56f0d79","isNewRecord":false,"totalAmount":0,"leftAmount":0,"totalBonus":0,"leftBonus":0,"mailMinimum":0,"beginTotalAmount":0,"endTotalAmount":0,"beginLeftAmount":0,"endLeftAmount":0,"beginTotalBonus":0,"endTotalBonus":0,"beginLeftBonus":0,"endLeftBonus":0,"beginMailMinimum":0,"endMailMinimum":0},"variety":"1","amount":1,"bonus":5,"url":"/invoice/f5e5539aaccf4aed8697653d7872a259.jpg","state":"3","beginAmount":0,"endAmount":0,"beginBonus":0,"endBonus":0,"reason":"刚刚给"},{"id":"9e5cd5e25cbf40d0939238088e3a3af7","isNewRecord":false,"createDate":"2017-11-06 11:17:43","updateDate":"2017-11-07 09:38:42","order":{"id":"157990bcc096418c9c8095a7cd352d08","isNewRecord":false,"amount":0,"bonus":0},"demand":{"id":"a4e62566ecda49cfb4e1434ab56f0d79","isNewRecord":false,"totalAmount":0,"leftAmount":0,"totalBonus":0,"leftBonus":0,"mailMinimum":0,"beginTotalAmount":0,"endTotalAmount":0,"beginLeftAmount":0,"endLeftAmount":0,"beginTotalBonus":0,"endTotalBonus":0,"beginLeftBonus":0,"endLeftBonus":0,"beginMailMinimum":0,"endMailMinimum":0},"variety":"1","amount":1,"bonus":5,"url":"/invoice/4a7f31b525634d978c2ae63062571f5e.jpg","state":"3","beginAmount":0,"endAmount":0,"beginBonus":0,"endBonus":0,"reason":"不好好"},{"id":"2ed7cba4c3e94a1388310b57084bb2c4","isNewRecord":false,"createDate":"2017-11-06 11:11:24","updateDate":"2017-11-06 16:24:18","order":{"id":"efa13a6efdb042ef8e9165df0e7b323f","isNewRecord":false,"amount":0,"bonus":0},"demand":{"id":"a4e62566ecda49cfb4e1434ab56f0d79","isNewRecord":false,"totalAmount":0,"leftAmount":0,"totalBonus":0,"leftBonus":0,"mailMinimum":0,"beginTotalAmount":0,"endTotalAmount":0,"beginLeftAmount":0,"endLeftAmount":0,"beginTotalBonus":0,"endTotalBonus":0,"beginLeftBonus":0,"endLeftBonus":0,"beginMailMinimum":0,"endMailMinimum":0},"variety":"1","amount":10,"bonus":5,"url":"/invoice/b9a017b7ce934cd7ace28289d814288e.jpg","state":"3","beginAmount":0,"endAmount":0,"beginBonus":0,"endBonus":0},{"id":"9805062c23f34370a27613852ffd41bc","isNewRecord":false,"createDate":"2017-11-06 11:33:06","updateDate":"2017-11-06 17:28:50","order":{"id":"962858652b054c2c917c1e33e0e746d6","isNewRecord":false,"amount":0,"bonus":0},"demand":{"id":"a4e62566ecda49cfb4e1434ab56f0d79","isNewRecord":false,"totalAmount":0,"leftAmount":0,"totalBonus":0,"leftBonus":0,"mailMinimum":0,"beginTotalAmount":0,"endTotalAmount":0,"beginLeftAmount":0,"endLeftAmount":0,"beginTotalBonus":0,"endTotalBonus":0,"beginLeftBonus":0,"endLeftBonus":0,"beginMailMinimum":0,"endMailMinimum":0},"variety":"1","amount":10,"bonus":7,"url":"/invoice/0fa31b5100cc4ad1b7d409d66eee6eca.jpg","state":"3","beginAmount":0,"endAmount":0,"beginBonus":0,"endBonus":0}]}
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
         * invoiceNameList : ["会议费","会议费"]
         * receivedNum : 0
         * publishDate : 2017-11-02
         * receivedAmount : 0
         * demand : {"isNewRecord":true,"company":{"isNewRecord":true,"name":"string","taxno":"string","address":"string","phone":"string","depositBank":"string","account":"string","qrcode":"string"},"demandPostage":{"isNewRecord":true,"receiver":"string","telephone":"string","phone":"string","province":"string","city":"string","district":"string","address":"string","email":"string"},"invoiceVarieties":"1,2,3","areaRestrict":"1","province":"string","city":"天津市","deadline":"2018-11-16","totalAmount":1000,"leftAmount":311,"totalBonus":100,"leftBonus":31.1,"mailMinimum":0,"attentions":"请务必上传真实发票","state":"0","beginTotalAmount":0,"endTotalAmount":0,"beginLeftAmount":0,"endLeftAmount":0,"beginTotalBonus":0,"endTotalBonus":0,"beginLeftBonus":0,"endLeftBonus":0,"beginMailMinimum":0,"endMailMinimum":0,"publishDate":"2017-11-02"}
         * orderInvoiceList : [{"id":"c3007bb7094540e1bd3849eb6a33a55e","isNewRecord":false,"createDate":"2017-11-06 11:15:30","updateDate":"2017-11-07 09:40:58","order":{"id":"d1467ab24b434977be9047cf7afdb11a","isNewRecord":false,"amount":0,"bonus":0},"demand":{"id":"a4e62566ecda49cfb4e1434ab56f0d79","isNewRecord":false,"totalAmount":0,"leftAmount":0,"totalBonus":0,"leftBonus":0,"mailMinimum":0,"beginTotalAmount":0,"endTotalAmount":0,"beginLeftAmount":0,"endLeftAmount":0,"beginTotalBonus":0,"endTotalBonus":0,"beginLeftBonus":0,"endLeftBonus":0,"beginMailMinimum":0,"endMailMinimum":0},"variety":"1","amount":1,"bonus":5,"url":"/invoice/f5e5539aaccf4aed8697653d7872a259.jpg","state":"3","beginAmount":0,"endAmount":0,"beginBonus":0,"endBonus":0,"reason":"刚刚给"},{"id":"9e5cd5e25cbf40d0939238088e3a3af7","isNewRecord":false,"createDate":"2017-11-06 11:17:43","updateDate":"2017-11-07 09:38:42","order":{"id":"157990bcc096418c9c8095a7cd352d08","isNewRecord":false,"amount":0,"bonus":0},"demand":{"id":"a4e62566ecda49cfb4e1434ab56f0d79","isNewRecord":false,"totalAmount":0,"leftAmount":0,"totalBonus":0,"leftBonus":0,"mailMinimum":0,"beginTotalAmount":0,"endTotalAmount":0,"beginLeftAmount":0,"endLeftAmount":0,"beginTotalBonus":0,"endTotalBonus":0,"beginLeftBonus":0,"endLeftBonus":0,"beginMailMinimum":0,"endMailMinimum":0},"variety":"1","amount":1,"bonus":5,"url":"/invoice/4a7f31b525634d978c2ae63062571f5e.jpg","state":"3","beginAmount":0,"endAmount":0,"beginBonus":0,"endBonus":0,"reason":"不好好"},{"id":"2ed7cba4c3e94a1388310b57084bb2c4","isNewRecord":false,"createDate":"2017-11-06 11:11:24","updateDate":"2017-11-06 16:24:18","order":{"id":"efa13a6efdb042ef8e9165df0e7b323f","isNewRecord":false,"amount":0,"bonus":0},"demand":{"id":"a4e62566ecda49cfb4e1434ab56f0d79","isNewRecord":false,"totalAmount":0,"leftAmount":0,"totalBonus":0,"leftBonus":0,"mailMinimum":0,"beginTotalAmount":0,"endTotalAmount":0,"beginLeftAmount":0,"endLeftAmount":0,"beginTotalBonus":0,"endTotalBonus":0,"beginLeftBonus":0,"endLeftBonus":0,"beginMailMinimum":0,"endMailMinimum":0},"variety":"1","amount":10,"bonus":5,"url":"/invoice/b9a017b7ce934cd7ace28289d814288e.jpg","state":"3","beginAmount":0,"endAmount":0,"beginBonus":0,"endBonus":0},{"id":"9805062c23f34370a27613852ffd41bc","isNewRecord":false,"createDate":"2017-11-06 11:33:06","updateDate":"2017-11-06 17:28:50","order":{"id":"962858652b054c2c917c1e33e0e746d6","isNewRecord":false,"amount":0,"bonus":0},"demand":{"id":"a4e62566ecda49cfb4e1434ab56f0d79","isNewRecord":false,"totalAmount":0,"leftAmount":0,"totalBonus":0,"leftBonus":0,"mailMinimum":0,"beginTotalAmount":0,"endTotalAmount":0,"beginLeftAmount":0,"endLeftAmount":0,"beginTotalBonus":0,"endTotalBonus":0,"beginLeftBonus":0,"endLeftBonus":0,"beginMailMinimum":0,"endMailMinimum":0},"variety":"1","amount":10,"bonus":7,"url":"/invoice/0fa31b5100cc4ad1b7d409d66eee6eca.jpg","state":"3","beginAmount":0,"endAmount":0,"beginBonus":0,"endBonus":0}]
         */

        private int receivedNum;
        private String publishDate;
        private double receivedAmount;
        private DemandBean demand;
        private List<String> invoiceNameList;
        private List<OrderInvoiceListBean> orderInvoiceList;

        public int getReceivedNum() {
            return receivedNum;
        }

        public void setReceivedNum(int receivedNum) {
            this.receivedNum = receivedNum;
        }

        public String getPublishDate() {
            return publishDate;
        }

        public void setPublishDate(String publishDate) {
            this.publishDate = publishDate;
        }

        public double getReceivedAmount() {
            return receivedAmount;
        }

        public void setReceivedAmount(double receivedAmount) {
            this.receivedAmount = receivedAmount;
        }

        public DemandBean getDemand() {
            return demand;
        }

        public void setDemand(DemandBean demand) {
            this.demand = demand;
        }

        public List<String> getInvoiceNameList() {
            return invoiceNameList;
        }

        public void setInvoiceNameList(List<String> invoiceNameList) {
            this.invoiceNameList = invoiceNameList;
        }

        public List<OrderInvoiceListBean> getOrderInvoiceList() {
            return orderInvoiceList;
        }

        public void setOrderInvoiceList(List<OrderInvoiceListBean> orderInvoiceList) {
            this.orderInvoiceList = orderInvoiceList;
        }

        public static class DemandBean {
            /**
             * isNewRecord : true
             * company : {"isNewRecord":true,"name":"string","taxno":"string","address":"string","phone":"string","depositBank":"string","account":"string","qrcode":"string"}
             * demandPostage : {"isNewRecord":true,"receiver":"string","telephone":"string","phone":"string","province":"string","city":"string","district":"string","address":"string","email":"string"}
             * invoiceVarieties : 1,2,3
             * areaRestrict : 1
             * province : string
             * city : 天津市
             * deadline : 2018-11-16
             * totalAmount : 1000
             * leftAmount : 311
             * totalBonus : 100
             * leftBonus : 31.1
             * mailMinimum : 0
             * attentions : 请务必上传真实发票
             * state : 0
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
             * publishDate : 2017-11-02
             */

            private boolean isNewRecord;
            private CompanyBean company;
            private DemandPostageBean demandPostage;
            private String invoiceVarieties;
            private String areaRestrict;
            private String province;
            private String city;
            private String deadline;
            private double totalAmount;
            private double leftAmount;
            private double totalBonus;
            private double leftBonus;
            private double mailMinimum;
            private String attentions;
            private String state;
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
            private String publishDate;

            public boolean isIsNewRecord() {
                return isNewRecord;
            }

            public void setIsNewRecord(boolean isNewRecord) {
                this.isNewRecord = isNewRecord;
            }

            public CompanyBean getCompany() {
                return company;
            }

            public void setCompany(CompanyBean company) {
                this.company = company;
            }

            public DemandPostageBean getDemandPostage() {
                return demandPostage;
            }

            public void setDemandPostage(DemandPostageBean demandPostage) {
                this.demandPostage = demandPostage;
            }

            public String getInvoiceVarieties() {
                return invoiceVarieties;
            }

            public void setInvoiceVarieties(String invoiceVarieties) {
                this.invoiceVarieties = invoiceVarieties;
            }

            public String getAreaRestrict() {
                return areaRestrict;
            }

            public void setAreaRestrict(String areaRestrict) {
                this.areaRestrict = areaRestrict;
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

            public String getDeadline() {
                return deadline;
            }

            public void setDeadline(String deadline) {
                this.deadline = deadline;
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

            public String getAttentions() {
                return attentions;
            }

            public void setAttentions(String attentions) {
                this.attentions = attentions;
            }

            public String getState() {
                return state;
            }

            public void setState(String state) {
                this.state = state;
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

            public String getPublishDate() {
                return publishDate;
            }

            public void setPublishDate(String publishDate) {
                this.publishDate = publishDate;
            }

            public static class CompanyBean {
                /**
                 * isNewRecord : true
                 * name : string
                 * taxno : string
                 * address : string
                 * phone : string
                 * depositBank : string
                 * account : string
                 * qrcode : string
                 */

                private boolean isNewRecord;
                private String name;
                private String taxno;
                private String address;
                private String phone;
                private String depositBank;
                private String account;
                private String qrcode;

                public boolean isIsNewRecord() {
                    return isNewRecord;
                }

                public void setIsNewRecord(boolean isNewRecord) {
                    this.isNewRecord = isNewRecord;
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
            }

            public static class DemandPostageBean {
                /**
                 * isNewRecord : true
                 * receiver : string
                 * telephone : string
                 * phone : string
                 * province : string
                 * city : string
                 * district : string
                 * address : string
                 * email : string
                 */

                private boolean isNewRecord;
                private String receiver;
                private String telephone;
                private String phone;
                private String province;
                private String city;
                private String district;
                private String address;
                private String email;

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
            }
        }

        public static class OrderInvoiceListBean {
            /**
             * id : c3007bb7094540e1bd3849eb6a33a55e
             * isNewRecord : false
             * createDate : 2017-11-06 11:15:30
             * updateDate : 2017-11-07 09:40:58
             * order : {"id":"d1467ab24b434977be9047cf7afdb11a","isNewRecord":false,"amount":0,"bonus":0}
             * demand : {"id":"a4e62566ecda49cfb4e1434ab56f0d79","isNewRecord":false,"totalAmount":0,"leftAmount":0,"totalBonus":0,"leftBonus":0,"mailMinimum":0,"begdoubleotalAmount":0,"endTotalAmount":0,"beginLeftAmount":0,"endLeftAmount":0,"beginTotalBonus":0,"endTotalBonus":0,"beginLeftBonus":0,"endLeftBonus":0,"beginMailMinimum":0,"endMailMinimum":0}
             * variety : 1
             * amount : 1
             * bonus : 5
             * url : /invoice/f5e5539aaccf4aed8697653d7872a259.jpg
             * state : 3
             * beginAmount : 0
             * endAmount : 0
             * beginBonus : 0
             * endBonus : 0
             * reason : 刚刚给
             */

            private String id;
            private boolean isNewRecord;
            private String createDate;
            private String updateDate;
            private OrderBean order;
            private DemandBeanX demand;
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
            private String reason;

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

            public OrderBean getOrder() {
                return order;
            }

            public void setOrder(OrderBean order) {
                this.order = order;
            }

            public DemandBeanX getDemand() {
                return demand;
            }

            public void setDemand(DemandBeanX demand) {
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

            public String getReason() {
                return reason;
            }

            public void setReason(String reason) {
                this.reason = reason;
            }

            public static class OrderBean {
                /**
                 * id : d1467ab24b434977be9047cf7afdb11a
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

            public static class DemandBeanX {
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
        }
    }
}
