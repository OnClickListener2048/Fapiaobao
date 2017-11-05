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
     * data : {"invoiceNameList":["会议票","过路过桥费票"],"receivedNum":5,"receivedAmount":51,"demand":{"isNewRecord":true,"company":{"isNewRecord":true,"name":"河北牛逼有限公司","taxno":"456456465465456465456","address":"河北石家庄","phone":"15046670220","depositBank":"河北银行","account":"655486456486456456","qrcode":"www.baidu.com"},"demandPostage":{"isNewRecord":true,"receiver":"李阳","telephone":"022-454654","phone":"150456456456","province":"河北省","city":"石家庄","district":"和平区","address":"xx小区","email":"9078904089@qq.com"},"invoiceVarieties":"1,2,3","areaRestrict":"1","province":"河北","city":"石家庄","deadline":"2017-11-05","totalAmount":49999,"leftAmount":49999,"totalBonus":10,"leftBonus":10,"mailMinimum":0,"attentions":"注意事项","state":"0"},"orderInvoiceList":[{"id":"11bb1075d22d49e9a6f0bc417c138f14","isNewRecord":false,"remarks":"","createDate":"2017-11-01 13:47:51","updateDate":"2017-11-02 17:16:02","order":{"id":"94dd9e0524544ea29c592912640ec3bd","isNewRecord":false},"demand":{"id":"342e8276f8ba44808c26ce9e06c1f282","isNewRecord":false},"variety":"3","amount":10,"bonus":1,"url":"https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1509526066491&di=41dddfc05a78ae644a9695eef54ef1dd&imgtype=0&src=http%3A%2F%2Fphotocdn.sohu.com%2F20100208%2FImg270139378.jpg","logisticsCompany":"","logisticsTradeno":"","state":"1"},{"id":"12eb0f73a4ff4cde811a30aa872c18ed","isNewRecord":false,"remarks":"","createDate":"2017-11-01 13:47:51","updateDate":"2017-11-02 17:15:57","order":{"id":"94dd9e0524544ea29c592912640ec3bd","isNewRecord":false},"demand":{"id":"342e8276f8ba44808c26ce9e06c1f282","isNewRecord":false},"variety":"3","amount":10,"bonus":1,"url":"https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1509526066491&di=41dddfc05a78ae644a9695eef54ef1dd&imgtype=0&src=http%3A%2F%2Fphotocdn.sohu.com%2F20100208%2FImg270139378.jpg","logisticsCompany":"","logisticsTradeno":"","state":"2"},{"id":"1b4e93743bfb4ced9fcf5b3297a5af2e","isNewRecord":false,"remarks":"","createDate":"2017-11-01 15:55:01","updateDate":"2017-11-01 15:55:01","order":{"id":"ea998f681ede4845adbceb740e664a07","isNewRecord":false},"demand":{"id":"342e8276f8ba44808c26ce9e06c1f282","isNewRecord":false},"variety":"3","amount":100,"bonus":1,"url":"https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1509526066491&di=41dddfc05a78ae644a9695eef54ef1dd&imgtype=0&src=http%3A%2F%2Fphotocdn.sohu.com%2F20100208%2FImg270139378.jpg","logisticsCompany":"京东","logisticsTradeno":"1564564564564564","state":"3"},{"id":"9683f6e753f949b78a8aec8229b63c22","isNewRecord":false,"remarks":"","createDate":"2017-11-01 15:28:06","updateDate":"2017-11-01 15:28:06","order":{"id":"ee61139200ff43b8a523b9fd02d599cc","isNewRecord":false},"demand":{"id":"342e8276f8ba44808c26ce9e06c1f282","isNewRecord":false},"variety":"3","amount":10,"bonus":1,"url":"https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1509526066491&di=41dddfc05a78ae644a9695eef54ef1dd&imgtype=0&src=http%3A%2F%2Fphotocdn.sohu.com%2F20100208%2FImg270139378.jpg","logisticsCompany":"京东","logisticsTradeno":"1564564564564564","state":"4"},{"id":"a3119d5de6274aaa8babf0fc542a7ccc","isNewRecord":false,"remarks":"","createDate":"2017-11-01 13:46:11","updateDate":"2017-11-02 17:16:16","order":{"id":"94dd9e0524544ea29c592912640ec3bd","isNewRecord":false},"demand":{"id":"342e8276f8ba44808c26ce9e06c1f282","isNewRecord":false},"variety":"2","amount":11,"bonus":1,"url":"https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1509526066491&di=41dddfc05a78ae644a9695eef54ef1dd&imgtype=0&src=http%3A%2F%2Fphotocdn.sohu.com%2F20100208%2FImg270139378.jpg","logisticsCompany":"","logisticsTradeno":"","state":"1"},{"id":"b0ff6b9cc68446d698aa2dc04e77edf7","isNewRecord":false,"remarks":"","createDate":"2017-11-01 13:32:45","updateDate":"2017-11-02 17:29:33","order":{"id":"ed7f671863d3492ab21cc6ee4911a9ed","isNewRecord":false},"demand":{"id":"342e8276f8ba44808c26ce9e06c1f282","isNewRecord":false},"variety":"2","amount":10,"bonus":1,"url":"https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1509526066491&di=41dddfc05a78ae644a9695eef54ef1dd&imgtype=0&src=http%3A%2F%2Fphotocdn.sohu.com%2F20100208%2FImg270139378.jpg","logisticsCompany":"","logisticsTradeno":"","state":"2"},{"id":"b226df97165b452a9d0e8327e99e3d0c","isNewRecord":false,"remarks":"","createDate":"2017-11-02 12:56:30","updateDate":"2017-11-02 12:56:30","order":{"id":"cac28a5a8c0448fb8633e8b09c99269b","isNewRecord":false},"demand":{"id":"342e8276f8ba44808c26ce9e06c1f282","isNewRecord":false},"variety":"2","amount":10,"bonus":1,"url":"/invoice/58eca96ccfbf40a5975b3c47510cf200.jpg","logisticsCompany":"","logisticsTradeno":"","state":"3"}]}
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
         * invoiceNameList : ["会议票","过路过桥费票"]
         * receivedNum : 5
         * receivedAmount : 51
         * demand : {"isNewRecord":true,"company":{"isNewRecord":true,"name":"河北牛逼有限公司","taxno":"456456465465456465456","address":"河北石家庄","phone":"15046670220","depositBank":"河北银行","account":"655486456486456456","qrcode":"www.baidu.com"},"demandPostage":{"isNewRecord":true,"receiver":"李阳","telephone":"022-454654","phone":"150456456456","province":"河北省","city":"石家庄","district":"和平区","address":"xx小区","email":"9078904089@qq.com"},"invoiceVarieties":"1,2,3","areaRestrict":"1","province":"河北","city":"石家庄","deadline":"2017-11-05","totalAmount":49999,"leftAmount":49999,"totalBonus":10,"leftBonus":10,"mailMinimum":0,"attentions":"注意事项","state":"0"}
         * orderInvoiceList : [{"id":"11bb1075d22d49e9a6f0bc417c138f14","isNewRecord":false,"remarks":"","createDate":"2017-11-01 13:47:51","updateDate":"2017-11-02 17:16:02","order":{"id":"94dd9e0524544ea29c592912640ec3bd","isNewRecord":false},"demand":{"id":"342e8276f8ba44808c26ce9e06c1f282","isNewRecord":false},"variety":"3","amount":10,"bonus":1,"url":"https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1509526066491&di=41dddfc05a78ae644a9695eef54ef1dd&imgtype=0&src=http%3A%2F%2Fphotocdn.sohu.com%2F20100208%2FImg270139378.jpg","logisticsCompany":"","logisticsTradeno":"","state":"1"},{"id":"12eb0f73a4ff4cde811a30aa872c18ed","isNewRecord":false,"remarks":"","createDate":"2017-11-01 13:47:51","updateDate":"2017-11-02 17:15:57","order":{"id":"94dd9e0524544ea29c592912640ec3bd","isNewRecord":false},"demand":{"id":"342e8276f8ba44808c26ce9e06c1f282","isNewRecord":false},"variety":"3","amount":10,"bonus":1,"url":"https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1509526066491&di=41dddfc05a78ae644a9695eef54ef1dd&imgtype=0&src=http%3A%2F%2Fphotocdn.sohu.com%2F20100208%2FImg270139378.jpg","logisticsCompany":"","logisticsTradeno":"","state":"2"},{"id":"1b4e93743bfb4ced9fcf5b3297a5af2e","isNewRecord":false,"remarks":"","createDate":"2017-11-01 15:55:01","updateDate":"2017-11-01 15:55:01","order":{"id":"ea998f681ede4845adbceb740e664a07","isNewRecord":false},"demand":{"id":"342e8276f8ba44808c26ce9e06c1f282","isNewRecord":false},"variety":"3","amount":100,"bonus":1,"url":"https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1509526066491&di=41dddfc05a78ae644a9695eef54ef1dd&imgtype=0&src=http%3A%2F%2Fphotocdn.sohu.com%2F20100208%2FImg270139378.jpg","logisticsCompany":"京东","logisticsTradeno":"1564564564564564","state":"3"},{"id":"9683f6e753f949b78a8aec8229b63c22","isNewRecord":false,"remarks":"","createDate":"2017-11-01 15:28:06","updateDate":"2017-11-01 15:28:06","order":{"id":"ee61139200ff43b8a523b9fd02d599cc","isNewRecord":false},"demand":{"id":"342e8276f8ba44808c26ce9e06c1f282","isNewRecord":false},"variety":"3","amount":10,"bonus":1,"url":"https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1509526066491&di=41dddfc05a78ae644a9695eef54ef1dd&imgtype=0&src=http%3A%2F%2Fphotocdn.sohu.com%2F20100208%2FImg270139378.jpg","logisticsCompany":"京东","logisticsTradeno":"1564564564564564","state":"4"},{"id":"a3119d5de6274aaa8babf0fc542a7ccc","isNewRecord":false,"remarks":"","createDate":"2017-11-01 13:46:11","updateDate":"2017-11-02 17:16:16","order":{"id":"94dd9e0524544ea29c592912640ec3bd","isNewRecord":false},"demand":{"id":"342e8276f8ba44808c26ce9e06c1f282","isNewRecord":false},"variety":"2","amount":11,"bonus":1,"url":"https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1509526066491&di=41dddfc05a78ae644a9695eef54ef1dd&imgtype=0&src=http%3A%2F%2Fphotocdn.sohu.com%2F20100208%2FImg270139378.jpg","logisticsCompany":"","logisticsTradeno":"","state":"1"},{"id":"b0ff6b9cc68446d698aa2dc04e77edf7","isNewRecord":false,"remarks":"","createDate":"2017-11-01 13:32:45","updateDate":"2017-11-02 17:29:33","order":{"id":"ed7f671863d3492ab21cc6ee4911a9ed","isNewRecord":false},"demand":{"id":"342e8276f8ba44808c26ce9e06c1f282","isNewRecord":false},"variety":"2","amount":10,"bonus":1,"url":"https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1509526066491&di=41dddfc05a78ae644a9695eef54ef1dd&imgtype=0&src=http%3A%2F%2Fphotocdn.sohu.com%2F20100208%2FImg270139378.jpg","logisticsCompany":"","logisticsTradeno":"","state":"2"},{"id":"b226df97165b452a9d0e8327e99e3d0c","isNewRecord":false,"remarks":"","createDate":"2017-11-02 12:56:30","updateDate":"2017-11-02 12:56:30","order":{"id":"cac28a5a8c0448fb8633e8b09c99269b","isNewRecord":false},"demand":{"id":"342e8276f8ba44808c26ce9e06c1f282","isNewRecord":false},"variety":"2","amount":10,"bonus":1,"url":"/invoice/58eca96ccfbf40a5975b3c47510cf200.jpg","logisticsCompany":"","logisticsTradeno":"","state":"3"}]
         */

        private int receivedNum;
        private BigDecimal receivedAmount;
        private DemandBean demand;
        private List<String> invoiceNameList;
        private List<OrderInvoiceListBean> orderInvoiceList;

        public int getReceivedNum() {
            return receivedNum;
        }

        public void setReceivedNum(int receivedNum) {
            this.receivedNum = receivedNum;
        }

        public BigDecimal getReceivedAmount() {
            return receivedAmount;
        }

        public void setReceivedAmount(BigDecimal receivedAmount) {
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
             * company : {"isNewRecord":true,"name":"河北牛逼有限公司","taxno":"456456465465456465456","address":"河北石家庄","phone":"15046670220","depositBank":"河北银行","account":"655486456486456456","qrcode":"www.baidu.com"}
             * demandPostage : {"isNewRecord":true,"receiver":"李阳","telephone":"022-454654","phone":"150456456456","province":"河北省","city":"石家庄","district":"和平区","address":"xx小区","email":"9078904089@qq.com"}
             * invoiceVarieties : 1,2,3
             * areaRestrict : 1
             * province : 河北
             * city : 石家庄
             * deadline : 2017-11-05
             * totalAmount : 49999
             * leftAmount : 49999
             * totalBonus : 10
             * leftBonus : 10
             * mailMinimum : 0
             * attentions : 注意事项
             * state : 0
             */

            private boolean isNewRecord;
            private CompanyBean company;
            private DemandPostageBean demandPostage;
            private String invoiceVarieties;
            private String areaRestrict;
            private String province;
            private String city;
            private String deadline;
            private BigDecimal totalAmount;
            private BigDecimal leftAmount;
            private BigDecimal totalBonus;
            private BigDecimal leftBonus;
            private BigDecimal mailMinimum;
            private String attentions;
            private String state;

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

            public static class CompanyBean {
                /**
                 * isNewRecord : true
                 * name : 河北牛逼有限公司
                 * taxno : 456456465465456465456
                 * address : 河北石家庄
                 * phone : 15046670220
                 * depositBank : 河北银行
                 * account : 655486456486456456
                 * qrcode : www.baidu.com
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
                 * receiver : 李阳
                 * telephone : 022-454654
                 * phone : 150456456456
                 * province : 河北省
                 * city : 石家庄
                 * district : 和平区
                 * address : xx小区
                 * email : 9078904089@qq.com
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
             * id : 11bb1075d22d49e9a6f0bc417c138f14
             * isNewRecord : false
             * remarks :
             * createDate : 2017-11-01 13:47:51
             * updateDate : 2017-11-02 17:16:02
             * order : {"id":"94dd9e0524544ea29c592912640ec3bd","isNewRecord":false}
             * demand : {"id":"342e8276f8ba44808c26ce9e06c1f282","isNewRecord":false}
             * variety : 3
             * amount : 10
             * bonus : 1
             * url : https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1509526066491&di=41dddfc05a78ae644a9695eef54ef1dd&imgtype=0&src=http%3A%2F%2Fphotocdn.sohu.com%2F20100208%2FImg270139378.jpg
             * logisticsCompany :
             * logisticsTradeno :
             * state : 1
             */

            private String id;
            private boolean isNewRecord;
            private String remarks;
            private String createDate;
            private String updateDate;
            private OrderBean order;
            private DemandBeanX demand;
            private String variety;
            private BigDecimal amount;
            private BigDecimal bonus;
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

            public BigDecimal getAmount() {
                return amount;
            }

            public void setAmount(BigDecimal amount) {
                this.amount = amount;
            }

            public BigDecimal getBonus() {
                return bonus;
            }

            public void setBonus(BigDecimal bonus) {
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
                 * id : 94dd9e0524544ea29c592912640ec3bd
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
                 * id : 342e8276f8ba44808c26ce9e06c1f282
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
