package com.pilipa.fapiaobao.net.bean.publish;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by edz on 2017/10/29.
 */

public class DemandDetails {


    /**
     * status : 200
     * msg : OK
     * data : {"invoiceNameList":["会议票"],"demand":{"isNewRecord":true,"createDate":"2017-11-01 11:26:21","updateDate":"2017-11-02 10:36:15","company":{"isNewRecord":true,"name":"天津波康数码","taxno":"57568765828856","address":"天津和平","phone":"022-8758469","depositBank":"建设银行","account":"6825524665425853"},"demandPostage":{"isNewRecord":true,"receiver":"王大锤","province":"天津","city":"天津","district":"天津","address":"天津"},"invoiceVarieties":"1,2,3","areaRestrict":"1","city":"天津市","deadline":"2017-11-03","totalAmount":1000,"leftAmount":440,"totalBonus":100,"leftBonus":44,"mailMinimum":7,"attentions":"快递小哥辛苦了","state":"0","closeDate":"2017-12-03"},"orderInvoiceList":[{"id":"1b4e93743bfb4ced9fcf5b3297a5af2e","isNewRecord":false,"createDate":"2017-11-01 15:55:01","updateDate":"2017-11-01 15:55:01","order":{"id":"ea998f681ede4845adbceb740e664a07","isNewRecord":false},"demand":{"id":"3a2abba40a0b4a7fb059f95680fb17cd","isNewRecord":false},"variety":"3","url":"https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1509526066491&di=41dddfc05a78ae644a9695eef54ef1dd&imgtype=0&src=http%3A%2F%2Fphotocdn.sohu.com%2F20100208%2FImg270139378.jpg","logisticsCompany":"京东","logisticsTradeno":"1564564564564564","state":"1"},{"id":"2f6c7bbf64d14fea9392dcf2d729c2a5","isNewRecord":false,"createDate":"2017-11-01 15:23:20","updateDate":"2017-11-01 15:23:20","order":{"id":"d29766a8fe984e6eadfb3ef82853dc0a","isNewRecord":false},"demand":{"id":"3a2abba40a0b4a7fb059f95680fb17cd","isNewRecord":false},"variety":"3","url":"https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1509526066491&di=41dddfc05a78ae644a9695eef54ef1dd&imgtype=0&src=http%3A%2F%2Fphotocdn.sohu.com%2F20100208%2FImg270139378.jpg","logisticsCompany":"京东","logisticsTradeno":"1564564564564564","state":"1"},{"id":"315b660dc2db4ed8a024f0773d8943fe","isNewRecord":false,"createDate":"2017-11-01 15:48:24","updateDate":"2017-11-01 15:48:24","order":{"id":"fcb7b6add85b427d9c005402994c5d01","isNewRecord":false},"demand":{"id":"3a2abba40a0b4a7fb059f95680fb17cd","isNewRecord":false},"variety":"2","url":"https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1509526066491&di=41dddfc05a78ae644a9695eef54ef1dd&imgtype=0&src=http%3A%2F%2Fphotocdn.sohu.com%2F20100208%2FImg270139378.jpg","logisticsCompany":"京东","logisticsTradeno":"1564564564564564","state":"1"},{"id":"9683f6e753f949b78a8aec8229b63c22","isNewRecord":false,"createDate":"2017-11-01 15:28:06","updateDate":"2017-11-01 15:28:06","order":{"id":"ee61139200ff43b8a523b9fd02d599cc","isNewRecord":false},"demand":{"id":"3a2abba40a0b4a7fb059f95680fb17cd","isNewRecord":false},"variety":"3","url":"https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1509526066491&di=41dddfc05a78ae644a9695eef54ef1dd&imgtype=0&src=http%3A%2F%2Fphotocdn.sohu.com%2F20100208%2FImg270139378.jpg","logisticsCompany":"京东","logisticsTradeno":"1564564564564564","state":"1"},{"id":"c4e8385d7eca40d394a03dd2ce8b1bcf","isNewRecord":false,"createDate":"2017-11-01 15:30:08","updateDate":"2017-11-01 15:30:08","order":{"id":"1163b3a26f634f14a6736e13a3eec954","isNewRecord":false},"demand":{"id":"3a2abba40a0b4a7fb059f95680fb17cd","isNewRecord":false},"variety":"3","url":"https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1509526066491&di=41dddfc05a78ae644a9695eef54ef1dd&imgtype=0&src=http%3A%2F%2Fphotocdn.sohu.com%2F20100208%2FImg270139378.jpg","state":"1"},{"id":"e3dc90a0070241308fad9fa8596a4f5a","isNewRecord":false,"createDate":"2017-11-01 14:24:06","updateDate":"2017-11-01 14:24:06","order":{"id":"d5b7f19067c6493c9f136dde30486d69","isNewRecord":false},"demand":{"id":"3a2abba40a0b4a7fb059f95680fb17cd","isNewRecord":false},"variety":"2","url":"https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1509526066491&di=41dddfc05a78ae644a9695eef54ef1dd&imgtype=0&src=http%3A%2F%2Fphotocdn.sohu.com%2F20100208%2FImg270139378.jpg","logisticsCompany":"京东","logisticsTradeno":"1564564564564564","state":"1"},{"id":"fe2dc38a077646bb99e5c4b2b59510f2","isNewRecord":false,"createDate":"2017-11-01 13:47:51","updateDate":"2017-11-01 15:03:25","order":{"id":"94dd9e0524544ea29c592912640ec3bd","isNewRecord":false},"demand":{"id":"3a2abba40a0b4a7fb059f95680fb17cd","isNewRecord":false},"variety":"3","url":"https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1509526066491&di=41dddfc05a78ae644a9695eef54ef1dd&imgtype=0&src=http%3A%2F%2Fphotocdn.sohu.com%2F20100208%2FImg270139378.jpg","logisticsCompany":"京东","logisticsTradeno":"1564564564564564","state":"1"}]}
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
         * invoiceNameList : ["会议票"]
         * demand : {"isNewRecord":true,"createDate":"2017-11-01 11:26:21","updateDate":"2017-11-02 10:36:15","company":{"isNewRecord":true,"name":"天津波康数码","taxno":"57568765828856","address":"天津和平","phone":"022-8758469","depositBank":"建设银行","account":"6825524665425853"},"demandPostage":{"isNewRecord":true,"receiver":"王大锤","province":"天津","city":"天津","district":"天津","address":"天津"},"invoiceVarieties":"1,2,3","areaRestrict":"1","city":"天津市","deadline":"2017-11-03","totalAmount":1000,"leftAmount":440,"totalBonus":100,"leftBonus":44,"mailMinimum":7,"attentions":"快递小哥辛苦了","state":"0","closeDate":"2017-12-03"}
         * orderInvoiceList : [{"id":"1b4e93743bfb4ced9fcf5b3297a5af2e","isNewRecord":false,"createDate":"2017-11-01 15:55:01","updateDate":"2017-11-01 15:55:01","order":{"id":"ea998f681ede4845adbceb740e664a07","isNewRecord":false},"demand":{"id":"3a2abba40a0b4a7fb059f95680fb17cd","isNewRecord":false},"variety":"3","url":"https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1509526066491&di=41dddfc05a78ae644a9695eef54ef1dd&imgtype=0&src=http%3A%2F%2Fphotocdn.sohu.com%2F20100208%2FImg270139378.jpg","logisticsCompany":"京东","logisticsTradeno":"1564564564564564","state":"1"},{"id":"2f6c7bbf64d14fea9392dcf2d729c2a5","isNewRecord":false,"createDate":"2017-11-01 15:23:20","updateDate":"2017-11-01 15:23:20","order":{"id":"d29766a8fe984e6eadfb3ef82853dc0a","isNewRecord":false},"demand":{"id":"3a2abba40a0b4a7fb059f95680fb17cd","isNewRecord":false},"variety":"3","url":"https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1509526066491&di=41dddfc05a78ae644a9695eef54ef1dd&imgtype=0&src=http%3A%2F%2Fphotocdn.sohu.com%2F20100208%2FImg270139378.jpg","logisticsCompany":"京东","logisticsTradeno":"1564564564564564","state":"1"},{"id":"315b660dc2db4ed8a024f0773d8943fe","isNewRecord":false,"createDate":"2017-11-01 15:48:24","updateDate":"2017-11-01 15:48:24","order":{"id":"fcb7b6add85b427d9c005402994c5d01","isNewRecord":false},"demand":{"id":"3a2abba40a0b4a7fb059f95680fb17cd","isNewRecord":false},"variety":"2","url":"https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1509526066491&di=41dddfc05a78ae644a9695eef54ef1dd&imgtype=0&src=http%3A%2F%2Fphotocdn.sohu.com%2F20100208%2FImg270139378.jpg","logisticsCompany":"京东","logisticsTradeno":"1564564564564564","state":"1"},{"id":"9683f6e753f949b78a8aec8229b63c22","isNewRecord":false,"createDate":"2017-11-01 15:28:06","updateDate":"2017-11-01 15:28:06","order":{"id":"ee61139200ff43b8a523b9fd02d599cc","isNewRecord":false},"demand":{"id":"3a2abba40a0b4a7fb059f95680fb17cd","isNewRecord":false},"variety":"3","url":"https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1509526066491&di=41dddfc05a78ae644a9695eef54ef1dd&imgtype=0&src=http%3A%2F%2Fphotocdn.sohu.com%2F20100208%2FImg270139378.jpg","logisticsCompany":"京东","logisticsTradeno":"1564564564564564","state":"1"},{"id":"c4e8385d7eca40d394a03dd2ce8b1bcf","isNewRecord":false,"createDate":"2017-11-01 15:30:08","updateDate":"2017-11-01 15:30:08","order":{"id":"1163b3a26f634f14a6736e13a3eec954","isNewRecord":false},"demand":{"id":"3a2abba40a0b4a7fb059f95680fb17cd","isNewRecord":false},"variety":"3","url":"https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1509526066491&di=41dddfc05a78ae644a9695eef54ef1dd&imgtype=0&src=http%3A%2F%2Fphotocdn.sohu.com%2F20100208%2FImg270139378.jpg","state":"1"},{"id":"e3dc90a0070241308fad9fa8596a4f5a","isNewRecord":false,"createDate":"2017-11-01 14:24:06","updateDate":"2017-11-01 14:24:06","order":{"id":"d5b7f19067c6493c9f136dde30486d69","isNewRecord":false},"demand":{"id":"3a2abba40a0b4a7fb059f95680fb17cd","isNewRecord":false},"variety":"2","url":"https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1509526066491&di=41dddfc05a78ae644a9695eef54ef1dd&imgtype=0&src=http%3A%2F%2Fphotocdn.sohu.com%2F20100208%2FImg270139378.jpg","logisticsCompany":"京东","logisticsTradeno":"1564564564564564","state":"1"},{"id":"fe2dc38a077646bb99e5c4b2b59510f2","isNewRecord":false,"createDate":"2017-11-01 13:47:51","updateDate":"2017-11-01 15:03:25","order":{"id":"94dd9e0524544ea29c592912640ec3bd","isNewRecord":false},"demand":{"id":"3a2abba40a0b4a7fb059f95680fb17cd","isNewRecord":false},"variety":"3","url":"https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1509526066491&di=41dddfc05a78ae644a9695eef54ef1dd&imgtype=0&src=http%3A%2F%2Fphotocdn.sohu.com%2F20100208%2FImg270139378.jpg","logisticsCompany":"京东","logisticsTradeno":"1564564564564564","state":"1"}]
         */

        private DemandBean demand;
        private List<String> invoiceNameList;
        private List<OrderInvoiceListBean> orderInvoiceList;

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
             * createDate : 2017-11-01 11:26:21
             * updateDate : 2017-11-02 10:36:15
             * company : {"isNewRecord":true,"name":"天津波康数码","taxno":"57568765828856","address":"天津和平","phone":"022-8758469","depositBank":"建设银行","account":"6825524665425853"}
             * demandPostage : {"isNewRecord":true,"receiver":"王大锤","province":"天津","city":"天津","district":"天津","address":"天津"}
             * invoiceVarieties : 1,2,3
             * areaRestrict : 1
             * city : 天津市
             * deadline : 2017-11-03
             * totalAmount : 1000
             * leftAmount : 440
             * totalBonus : 100
             * leftBonus : 44
             * mailMinimum : 7
             * attentions : 快递小哥辛苦了
             * state : 0
             * closeDate : 2017-12-03
             */

            private boolean isNewRecord;
            private String createDate;
            private String updateDate;
            private CompanyBean company;
            private DemandPostageBean demandPostage;
            private String invoiceVarieties;
            private String areaRestrict;
            private String city;
            private String deadline;
            private BigDecimal totalAmount;
            private BigDecimal leftAmount;
            private BigDecimal totalBonus;
            private BigDecimal leftBonus;
            private BigDecimal mailMinimum;
            private String attentions;
            private String state;
            private String closeDate;

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

            public BigDecimal getTotalAmount() {
                return totalAmount;
            }

            public void setTotalAmount(BigDecimal totalAmount) {
                this.totalAmount = totalAmount;
            }

            public BigDecimal getLeftAmount() {
                return leftAmount;
            }

            public void setLeftAmount(BigDecimal leftAmount) {
                this.leftAmount = leftAmount;
            }

            public BigDecimal getTotalBonus() {
                return totalBonus;
            }

            public void setTotalBonus(BigDecimal totalBonus) {
                this.totalBonus = totalBonus;
            }

            public BigDecimal getLeftBonus() {
                return leftBonus;
            }

            public void setLeftBonus(BigDecimal leftBonus) {
                this.leftBonus = leftBonus;
            }

            public BigDecimal getMailMinimum() {
                return mailMinimum;
            }

            public void setMailMinimum(BigDecimal mailMinimum) {
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
                 * name : 天津波康数码
                 * taxno : 57568765828856
                 * address : 天津和平
                 * phone : 022-8758469
                 * depositBank : 建设银行
                 * account : 6825524665425853
                 */

                private boolean isNewRecord;
                private String name;
                private String taxno;
                private String address;
                private String phone;
                private String depositBank;
                private String account;

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
            }

            public static class DemandPostageBean {
                /**
                 * isNewRecord : true
                 * receiver : 王大锤
                 * province : 天津
                 * city : 天津
                 * district : 天津
                 * address : 天津
                 */

                private boolean isNewRecord;
                private String receiver;
                private String province;
                private String city;
                private String district;
                private String address;

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
            }
        }

        public static class OrderInvoiceListBean {
            /**
             * id : 1b4e93743bfb4ced9fcf5b3297a5af2e
             * isNewRecord : false
             * createDate : 2017-11-01 15:55:01
             * updateDate : 2017-11-01 15:55:01
             * order : {"id":"ea998f681ede4845adbceb740e664a07","isNewRecord":false}
             * demand : {"id":"3a2abba40a0b4a7fb059f95680fb17cd","isNewRecord":false}
             * variety : 3
             * url : https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1509526066491&di=41dddfc05a78ae644a9695eef54ef1dd&imgtype=0&src=http%3A%2F%2Fphotocdn.sohu.com%2F20100208%2FImg270139378.jpg
             * logisticsCompany : 京东
             * logisticsTradeno : 1564564564564564
             * state : 1
             */

            private String id;
            private boolean isNewRecord;
            private String createDate;
            private String updateDate;
            private OrderBean order;
            private DemandBeanX demand;
            private String variety;
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
                 * id : ea998f681ede4845adbceb740e664a07
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
                 * id : 3a2abba40a0b4a7fb059f95680fb17cd
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
