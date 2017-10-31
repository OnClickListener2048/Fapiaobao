package com.pilipa.fapiaobao.net.bean.publish;

import java.util.List;

/**
 * Created by edz on 2017/10/29.
 */

public class DemandDetails {


    /**
     * status : 200
     * msg : OK
     * data : {"demand":{"isNewRecord":true,"remarks":"","createDate":"2017-10-27 15:29:57","updateDate":"2017-10-26 15:29:57","company":{"isNewRecord":true,"name":"天津爱康鼎科技有限公司","taxno":"320400137674452","address":"天津市红桥区海河华鼎大厦","phone":"022-12554551","depositBank":"中国民生银行天津分行","account":"55555555555555555","qrcode":"www.sdsadsasd.dsada","isDefault":"1"},"demandPostage":{"isNewRecord":true,"receiver":"朱宏超","province":"内蒙","city":"赤峰","district":"阿发","address":"西撒的撒","email":"fasf564f@qq.com"},"invoiceVarieties":"1,2,3","areaRestrict":"1","province":"天津市","city":"天津市","deadline":"2017-11-02 00:00:00","totalAmount":1000,"leftAmount":500,"totalBonus":200,"leftBonus":50,"mailMinimum":100,"attentions":"尽快送到","state":"2","closeDate":"2017-10-31 00:00:00"},"orderInvoiceList":[{"id":"1","isNewRecord":false,"remarks":"","createDate":"2017-10-27 23:25:26","updateDate":"2017-10-04 23:25:20","order":{"id":"1","isNewRecord":false},"demand":{"id":"2716a03d6221477a886438e6aebef4ed","isNewRecord":false},"variety":"1","amount":99,"bonus":9,"url":"http://pic2.ooopic.com/12/22/94/37bOOOPICc7_1024.jpg","logisticsCompany":"50","logisticsTradeno":"50","state":"5"},{"id":"2","isNewRecord":false,"order":{"id":"1","isNewRecord":false},"demand":{"id":"2716a03d6221477a886438e6aebef4ed","isNewRecord":false},"amount":101,"bonus":11,"url":"http://pic2.ooopic.com/12/22/94/37bOOOPICc7_1024.jpg"},{"id":"3","isNewRecord":false,"order":{"id":"2","isNewRecord":false},"demand":{"id":"2716a03d6221477a886438e6aebef4ed","isNewRecord":false},"amount":100,"bonus":10,"url":"http://pic2.ooopic.com/12/22/94/37bOOOPICc7_1024.jpg"},{"id":"4","isNewRecord":false,"order":{"id":"2","isNewRecord":false},"demand":{"id":"2716a03d6221477a886438e6aebef4ed","isNewRecord":false},"amount":150,"bonus":15,"url":"http://pic2.ooopic.com/12/22/94/37bOOOPICc7_1024.jpg"},{"id":"5","isNewRecord":false,"order":{"id":"2","isNewRecord":false},"demand":{"id":"2716a03d6221477a886438e6aebef4ed","isNewRecord":false},"amount":50,"bonus":5,"url":"http://pic2.ooopic.com/12/22/94/37bOOOPICc7_1024.jpg"}]}
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
         * demand : {"isNewRecord":true,"remarks":"","createDate":"2017-10-27 15:29:57","updateDate":"2017-10-26 15:29:57","company":{"isNewRecord":true,"name":"天津爱康鼎科技有限公司","taxno":"320400137674452","address":"天津市红桥区海河华鼎大厦","phone":"022-12554551","depositBank":"中国民生银行天津分行","account":"55555555555555555","qrcode":"www.sdsadsasd.dsada","isDefault":"1"},"demandPostage":{"isNewRecord":true,"receiver":"朱宏超","province":"内蒙","city":"赤峰","district":"阿发","address":"西撒的撒","email":"fasf564f@qq.com"},"invoiceVarieties":"1,2,3","areaRestrict":"1","province":"天津市","city":"天津市","deadline":"2017-11-02 00:00:00","totalAmount":1000,"leftAmount":500,"totalBonus":200,"leftBonus":50,"mailMinimum":100,"attentions":"尽快送到","state":"2","closeDate":"2017-10-31 00:00:00"}
         * orderInvoiceList : [{"id":"1","isNewRecord":false,"remarks":"","createDate":"2017-10-27 23:25:26","updateDate":"2017-10-04 23:25:20","order":{"id":"1","isNewRecord":false},"demand":{"id":"2716a03d6221477a886438e6aebef4ed","isNewRecord":false},"variety":"1","amount":99,"bonus":9,"url":"http://pic2.ooopic.com/12/22/94/37bOOOPICc7_1024.jpg","logisticsCompany":"50","logisticsTradeno":"50","state":"5"},{"id":"2","isNewRecord":false,"order":{"id":"1","isNewRecord":false},"demand":{"id":"2716a03d6221477a886438e6aebef4ed","isNewRecord":false},"amount":101,"bonus":11,"url":"http://pic2.ooopic.com/12/22/94/37bOOOPICc7_1024.jpg"},{"id":"3","isNewRecord":false,"order":{"id":"2","isNewRecord":false},"demand":{"id":"2716a03d6221477a886438e6aebef4ed","isNewRecord":false},"amount":100,"bonus":10,"url":"http://pic2.ooopic.com/12/22/94/37bOOOPICc7_1024.jpg"},{"id":"4","isNewRecord":false,"order":{"id":"2","isNewRecord":false},"demand":{"id":"2716a03d6221477a886438e6aebef4ed","isNewRecord":false},"amount":150,"bonus":15,"url":"http://pic2.ooopic.com/12/22/94/37bOOOPICc7_1024.jpg"},{"id":"5","isNewRecord":false,"order":{"id":"2","isNewRecord":false},"demand":{"id":"2716a03d6221477a886438e6aebef4ed","isNewRecord":false},"amount":50,"bonus":5,"url":"http://pic2.ooopic.com/12/22/94/37bOOOPICc7_1024.jpg"}]
         */

        private DemandBean demand;
        private List<OrderInvoiceListBean> orderInvoiceList;

        public DemandBean getDemand() {
            return demand;
        }

        public void setDemand(DemandBean demand) {
            this.demand = demand;
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
             * remarks :
             * createDate : 2017-10-27 15:29:57
             * updateDate : 2017-10-26 15:29:57
             * company : {"isNewRecord":true,"name":"天津爱康鼎科技有限公司","taxno":"320400137674452","address":"天津市红桥区海河华鼎大厦","phone":"022-12554551","depositBank":"中国民生银行天津分行","account":"55555555555555555","qrcode":"www.sdsadsasd.dsada","isDefault":"1"}
             * demandPostage : {"isNewRecord":true,"receiver":"朱宏超","province":"内蒙","city":"赤峰","district":"阿发","address":"西撒的撒","email":"fasf564f@qq.com"}
             * invoiceVarieties : 1,2,3
             * areaRestrict : 1
             * province : 天津市
             * city : 天津市
             * deadline : 2017-11-02 00:00:00
             * totalAmount : 1000
             * leftAmount : 500
             * totalBonus : 200
             * leftBonus : 50
             * mailMinimum : 100
             * attentions : 尽快送到
             * state : 2
             * closeDate : 2017-10-31 00:00:00
             */

            private boolean isNewRecord;
            private String remarks;
            private String createDate;
            private String updateDate;
            private CompanyBean company;
            private DemandPostageBean demandPostage;
            private String invoiceVarieties;
            private String areaRestrict;
            private String province;
            private String city;
            private String deadline;
            private int totalAmount;
            private int leftAmount;
            private int totalBonus;
            private int leftBonus;
            private int mailMinimum;
            private String attentions;
            private String state;
            private String closeDate;

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

            public int getTotalAmount() {
                return totalAmount;
            }

            public void setTotalAmount(int totalAmount) {
                this.totalAmount = totalAmount;
            }

            public int getLeftAmount() {
                return leftAmount;
            }

            public void setLeftAmount(int leftAmount) {
                this.leftAmount = leftAmount;
            }

            public int getTotalBonus() {
                return totalBonus;
            }

            public void setTotalBonus(int totalBonus) {
                this.totalBonus = totalBonus;
            }

            public int getLeftBonus() {
                return leftBonus;
            }

            public void setLeftBonus(int leftBonus) {
                this.leftBonus = leftBonus;
            }

            public int getMailMinimum() {
                return mailMinimum;
            }

            public void setMailMinimum(int mailMinimum) {
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

            public String getCloseDate() {
                return closeDate;
            }

            public void setCloseDate(String closeDate) {
                this.closeDate = closeDate;
            }

            public static class CompanyBean {
                /**
                 * isNewRecord : true
                 * name : 天津爱康鼎科技有限公司
                 * taxno : 320400137674452
                 * address : 天津市红桥区海河华鼎大厦
                 * phone : 022-12554551
                 * depositBank : 中国民生银行天津分行
                 * account : 55555555555555555
                 * qrcode : www.sdsadsasd.dsada
                 * isDefault : 1
                 */

                private boolean isNewRecord;
                private String name;
                private String taxno;
                private String address;
                private String phone;
                private String depositBank;
                private String account;
                private String qrcode;
                private String isDefault;

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

                public String getIsDefault() {
                    return isDefault;
                }

                public void setIsDefault(String isDefault) {
                    this.isDefault = isDefault;
                }
            }

            public static class DemandPostageBean {
                /**
                 * isNewRecord : true
                 * receiver : 朱宏超
                 * province : 内蒙
                 * city : 赤峰
                 * district : 阿发
                 * address : 西撒的撒
                 * email : fasf564f@qq.com
                 */

                private boolean isNewRecord;
                private String receiver;
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
             * id : 1
             * isNewRecord : false
             * remarks :
             * createDate : 2017-10-27 23:25:26
             * updateDate : 2017-10-04 23:25:20
             * order : {"id":"1","isNewRecord":false}
             * demand : {"id":"2716a03d6221477a886438e6aebef4ed","isNewRecord":false}
             * variety : 1
             * amount : 99
             * bonus : 9
             * url : http://pic2.ooopic.com/12/22/94/37bOOOPICc7_1024.jpg
             * logisticsCompany : 50
             * logisticsTradeno : 50
             * state : 5
             */

            private String id;
            private boolean isNewRecord;
            private String remarks;
            private String createDate;
            private String updateDate;
            private OrderBean order;
            private DemandBeanX demand;
            private String variety;
            private int amount;
            private int bonus;
            private String url;
            private String logisticsCompany;
            private String logisticsTradeno;
            private String state;

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

            public int getAmount() {
                return amount;
            }

            public void setAmount(int amount) {
                this.amount = amount;
            }

            public int getBonus() {
                return bonus;
            }

            public void setBonus(int bonus) {
                this.bonus = bonus;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }

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

            public String getState() {
                return state;
            }

            public void setState(String state) {
                this.state = state;
            }

            public static class OrderBean {
                /**
                 * id : 1
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

            public static class DemandBeanX {
                /**
                 * id : 2716a03d6221477a886438e6aebef4ed
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
