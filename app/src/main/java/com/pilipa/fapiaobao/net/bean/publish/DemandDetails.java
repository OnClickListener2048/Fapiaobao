package com.pilipa.fapiaobao.net.bean.publish;

import java.util.List;

/**
 * Created by edz on 2017/10/29.
 */

public class DemandDetails {


    /**
     * status : 200
     * msg : OK
     * data : {"invoiceNameList":[],"receivedNum":9,"publishDate":"2017-11-02","receivedAmount":90,"demand":{"isNewRecord":true,"company":{"isNewRecord":true,"name":"string","taxno":"string","address":"string","phone":"string","depositBank":"string","account":"string","qrcode":"string"},"demandPostage":{"isNewRecord":true,"receiver":"王鑫","telephone":"17978488989","phone":"022-45454545","province":"天津市","city":"天津市","district":"和平区","address":"大沽南路小区","email":"415648544@qq.com"},"invoiceVarieties":"1,2,3","areaRestrict":"1","province":"","city":"天津市","deadline":"2018-11-16","totalAmount":1000,"leftAmount":1010,"totalBonus":100,"leftBonus":110,"mailMinimum":0,"attentions":"放在门卫就行","state":"0","publishDate":"2017-11-02"},"orderInvoiceList":[{"id":"01db02f7aea9469b9bdb8ccb3affaa28","isNewRecord":false,"createDate":"2017-11-01 13:26:18","updateDate":"2017-11-01 15:44:41","order":{"id":"ed7f671863d3492ab21cc6ee4911a9ed","isNewRecord":false},"demand":{"id":"3a89fd54d244485ca08b5c3b42401688","isNewRecord":false},"variety":"2","amount":10,"bonus":10,"url":"https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1509526066491&di=41dddfc05a78ae644a9695eef54ef1dd&imgtype=0&src=http%3A%2F%2Fphotocdn.sohu.com%2F20100208%2FImg270139378.jpg","state":"4"},{"id":"11bb1075d22d49e9a6f0bc417c138f14","isNewRecord":false,"createDate":"2017-11-01 13:47:51","updateDate":"2017-11-02 17:16:02","order":{"id":"94dd9e0524544ea29c592912640ec3bd","isNewRecord":false},"demand":{"id":"3a89fd54d244485ca08b5c3b42401688","isNewRecord":false},"variety":"3","amount":10,"bonus":10,"url":"https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1509526066491&di=41dddfc05a78ae644a9695eef54ef1dd&imgtype=0&src=http%3A%2F%2Fphotocdn.sohu.com%2F20100208%2FImg270139378.jpg","state":"2"},{"id":"12eb0f73a4ff4cde811a30aa872c18ed","isNewRecord":false,"createDate":"2017-11-01 13:47:51","updateDate":"2017-11-02 17:15:57","order":{"id":"94dd9e0524544ea29c592912640ec3bd","isNewRecord":false},"demand":{"id":"3a89fd54d244485ca08b5c3b42401688","isNewRecord":false},"variety":"3","amount":10,"bonus":10,"url":"https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1509526066491&di=41dddfc05a78ae644a9695eef54ef1dd&imgtype=0&src=http%3A%2F%2Fphotocdn.sohu.com%2F20100208%2FImg270139378.jpg","state":"2"},{"id":"73eddca263cd40988c64423652fd31cb","isNewRecord":false,"createDate":"2017-11-01 13:26:13","updateDate":"2017-11-02 17:44:50","order":{"id":"ed7f671863d3492ab21cc6ee4911a9ed","isNewRecord":false},"demand":{"id":"3a89fd54d244485ca08b5c3b42401688","isNewRecord":false},"variety":"2","amount":10,"bonus":10,"url":"https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1509526066491&di=41dddfc05a78ae644a9695eef54ef1dd&imgtype=0&src=http%3A%2F%2Fphotocdn.sohu.com%2F20100208%2FImg270139378.jpg","state":"2"},{"id":"a3119d5de6274aaa8babf0fc542a7ccc","isNewRecord":false,"createDate":"2017-11-01 13:46:11","updateDate":"2017-11-02 17:16:16","order":{"id":"94dd9e0524544ea29c592912640ec3bd","isNewRecord":false},"demand":{"id":"3a89fd54d244485ca08b5c3b42401688","isNewRecord":false},"variety":"2","amount":10,"bonus":10,"url":"https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1509526066491&di=41dddfc05a78ae644a9695eef54ef1dd&imgtype=0&src=http%3A%2F%2Fphotocdn.sohu.com%2F20100208%2FImg270139378.jpg","state":"2"},{"id":"b0ff6b9cc68446d698aa2dc04e77edf7","isNewRecord":false,"createDate":"2017-11-01 13:32:45","updateDate":"2017-11-02 17:29:33","order":{"id":"ed7f671863d3492ab21cc6ee4911a9ed","isNewRecord":false},"demand":{"id":"3a89fd54d244485ca08b5c3b42401688","isNewRecord":false},"variety":"2","amount":10,"bonus":10,"url":"https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1509526066491&di=41dddfc05a78ae644a9695eef54ef1dd&imgtype=0&src=http%3A%2F%2Fphotocdn.sohu.com%2F20100208%2FImg270139378.jpg","state":"2"},{"id":"b226df97165b452a9d0e8327e99e3d0c","isNewRecord":false,"createDate":"2017-11-02 12:56:30","updateDate":"2017-11-05 18:18:43","order":{"id":"cac28a5a8c0448fb8633e8b09c99269b","isNewRecord":false},"demand":{"id":"3a89fd54d244485ca08b5c3b42401688","isNewRecord":false},"variety":"2","amount":10,"bonus":10,"url":"/invoice/58eca96ccfbf40a5975b3c47510cf200.jpg","state":"3"},{"id":"ba4517b1d7274665b7e0ede516948695","isNewRecord":false,"createDate":"2017-11-01 13:46:11","updateDate":"2017-11-02 16:09:53","order":{"id":"94dd9e0524544ea29c592912640ec3bd","isNewRecord":false},"demand":{"id":"3a89fd54d244485ca08b5c3b42401688","isNewRecord":false},"variety":"2","amount":10,"bonus":10,"url":"https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1509526066491&di=41dddfc05a78ae644a9695eef54ef1dd&imgtype=0&src=http%3A%2F%2Fphotocdn.sohu.com%2F20100208%2FImg270139378.jpg","state":"4"},{"id":"c04a1c20419e4709b2c6d2e91b571a61","isNewRecord":false,"createDate":"2017-11-01 13:26:18","updateDate":"2017-11-01 15:16:14","order":{"id":"ed7f671863d3492ab21cc6ee4911a9ed","isNewRecord":false},"demand":{"id":"3a89fd54d244485ca08b5c3b42401688","isNewRecord":false},"variety":"3","amount":10,"bonus":10,"url":"https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1509526066491&di=41dddfc05a78ae644a9695eef54ef1dd&imgtype=0&src=http%3A%2F%2Fphotocdn.sohu.com%2F20100208%2FImg270139378.jpg","state":"3"},{"id":"cf9c595a8cfd420abb9ce858c0ba0de3","isNewRecord":false,"createDate":"2017-11-01 13:26:19","updateDate":"2017-11-01 15:34:03","order":{"id":"ed7f671863d3492ab21cc6ee4911a9ed","isNewRecord":false},"demand":{"id":"3a89fd54d244485ca08b5c3b42401688","isNewRecord":false},"variety":"3","amount":10,"bonus":10,"url":"https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1509526066491&di=41dddfc05a78ae644a9695eef54ef1dd&imgtype=0&src=http%3A%2F%2Fphotocdn.sohu.com%2F20100208%2FImg270139378.jpg","logisticsTradeno":"1564564564564564","state":"2"},{"id":"e49f400055a641db9705853bfa675008","isNewRecord":false,"createDate":"2017-11-01 13:26:39","updateDate":"2017-11-02 17:16:33","order":{"id":"ed7f671863d3492ab21cc6ee4911a9ed","isNewRecord":false},"demand":{"id":"3a89fd54d244485ca08b5c3b42401688","isNewRecord":false},"variety":"1","amount":10,"bonus":10,"url":"https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1509526066491&di=41dddfc05a78ae644a9695eef54ef1dd&imgtype=0&src=http%3A%2F%2Fphotocdn.sohu.com%2F20100208%2FImg270139378.jpg","logisticsCompany":"京东","logisticsTradeno":"1564564564564564","state":"2"}]}
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
         * invoiceNameList : []
         * receivedNum : 9
         * publishDate : 2017-11-02
         * receivedAmount : 90
         * demand : {"isNewRecord":true,"company":{"isNewRecord":true,"name":"string","taxno":"string","address":"string","phone":"string","depositBank":"string","account":"string","qrcode":"string"},"demandPostage":{"isNewRecord":true,"receiver":"王鑫","telephone":"17978488989","phone":"022-45454545","province":"天津市","city":"天津市","district":"和平区","address":"大沽南路小区","email":"415648544@qq.com"},"invoiceVarieties":"1,2,3","areaRestrict":"1","province":"","city":"天津市","deadline":"2018-11-16","totalAmount":1000,"leftAmount":1010,"totalBonus":100,"leftBonus":110,"mailMinimum":0,"attentions":"放在门卫就行","state":"0","publishDate":"2017-11-02"}
         * orderInvoiceList : [{"id":"01db02f7aea9469b9bdb8ccb3affaa28","isNewRecord":false,"createDate":"2017-11-01 13:26:18","updateDate":"2017-11-01 15:44:41","order":{"id":"ed7f671863d3492ab21cc6ee4911a9ed","isNewRecord":false},"demand":{"id":"3a89fd54d244485ca08b5c3b42401688","isNewRecord":false},"variety":"2","amount":10,"bonus":10,"url":"https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1509526066491&di=41dddfc05a78ae644a9695eef54ef1dd&imgtype=0&src=http%3A%2F%2Fphotocdn.sohu.com%2F20100208%2FImg270139378.jpg","state":"4"},{"id":"11bb1075d22d49e9a6f0bc417c138f14","isNewRecord":false,"createDate":"2017-11-01 13:47:51","updateDate":"2017-11-02 17:16:02","order":{"id":"94dd9e0524544ea29c592912640ec3bd","isNewRecord":false},"demand":{"id":"3a89fd54d244485ca08b5c3b42401688","isNewRecord":false},"variety":"3","amount":10,"bonus":10,"url":"https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1509526066491&di=41dddfc05a78ae644a9695eef54ef1dd&imgtype=0&src=http%3A%2F%2Fphotocdn.sohu.com%2F20100208%2FImg270139378.jpg","state":"2"},{"id":"12eb0f73a4ff4cde811a30aa872c18ed","isNewRecord":false,"createDate":"2017-11-01 13:47:51","updateDate":"2017-11-02 17:15:57","order":{"id":"94dd9e0524544ea29c592912640ec3bd","isNewRecord":false},"demand":{"id":"3a89fd54d244485ca08b5c3b42401688","isNewRecord":false},"variety":"3","amount":10,"bonus":10,"url":"https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1509526066491&di=41dddfc05a78ae644a9695eef54ef1dd&imgtype=0&src=http%3A%2F%2Fphotocdn.sohu.com%2F20100208%2FImg270139378.jpg","state":"2"},{"id":"73eddca263cd40988c64423652fd31cb","isNewRecord":false,"createDate":"2017-11-01 13:26:13","updateDate":"2017-11-02 17:44:50","order":{"id":"ed7f671863d3492ab21cc6ee4911a9ed","isNewRecord":false},"demand":{"id":"3a89fd54d244485ca08b5c3b42401688","isNewRecord":false},"variety":"2","amount":10,"bonus":10,"url":"https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1509526066491&di=41dddfc05a78ae644a9695eef54ef1dd&imgtype=0&src=http%3A%2F%2Fphotocdn.sohu.com%2F20100208%2FImg270139378.jpg","state":"2"},{"id":"a3119d5de6274aaa8babf0fc542a7ccc","isNewRecord":false,"createDate":"2017-11-01 13:46:11","updateDate":"2017-11-02 17:16:16","order":{"id":"94dd9e0524544ea29c592912640ec3bd","isNewRecord":false},"demand":{"id":"3a89fd54d244485ca08b5c3b42401688","isNewRecord":false},"variety":"2","amount":10,"bonus":10,"url":"https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1509526066491&di=41dddfc05a78ae644a9695eef54ef1dd&imgtype=0&src=http%3A%2F%2Fphotocdn.sohu.com%2F20100208%2FImg270139378.jpg","state":"2"},{"id":"b0ff6b9cc68446d698aa2dc04e77edf7","isNewRecord":false,"createDate":"2017-11-01 13:32:45","updateDate":"2017-11-02 17:29:33","order":{"id":"ed7f671863d3492ab21cc6ee4911a9ed","isNewRecord":false},"demand":{"id":"3a89fd54d244485ca08b5c3b42401688","isNewRecord":false},"variety":"2","amount":10,"bonus":10,"url":"https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1509526066491&di=41dddfc05a78ae644a9695eef54ef1dd&imgtype=0&src=http%3A%2F%2Fphotocdn.sohu.com%2F20100208%2FImg270139378.jpg","state":"2"},{"id":"b226df97165b452a9d0e8327e99e3d0c","isNewRecord":false,"createDate":"2017-11-02 12:56:30","updateDate":"2017-11-05 18:18:43","order":{"id":"cac28a5a8c0448fb8633e8b09c99269b","isNewRecord":false},"demand":{"id":"3a89fd54d244485ca08b5c3b42401688","isNewRecord":false},"variety":"2","amount":10,"bonus":10,"url":"/invoice/58eca96ccfbf40a5975b3c47510cf200.jpg","state":"3"},{"id":"ba4517b1d7274665b7e0ede516948695","isNewRecord":false,"createDate":"2017-11-01 13:46:11","updateDate":"2017-11-02 16:09:53","order":{"id":"94dd9e0524544ea29c592912640ec3bd","isNewRecord":false},"demand":{"id":"3a89fd54d244485ca08b5c3b42401688","isNewRecord":false},"variety":"2","amount":10,"bonus":10,"url":"https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1509526066491&di=41dddfc05a78ae644a9695eef54ef1dd&imgtype=0&src=http%3A%2F%2Fphotocdn.sohu.com%2F20100208%2FImg270139378.jpg","state":"4"},{"id":"c04a1c20419e4709b2c6d2e91b571a61","isNewRecord":false,"createDate":"2017-11-01 13:26:18","updateDate":"2017-11-01 15:16:14","order":{"id":"ed7f671863d3492ab21cc6ee4911a9ed","isNewRecord":false},"demand":{"id":"3a89fd54d244485ca08b5c3b42401688","isNewRecord":false},"variety":"3","amount":10,"bonus":10,"url":"https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1509526066491&di=41dddfc05a78ae644a9695eef54ef1dd&imgtype=0&src=http%3A%2F%2Fphotocdn.sohu.com%2F20100208%2FImg270139378.jpg","state":"3"},{"id":"cf9c595a8cfd420abb9ce858c0ba0de3","isNewRecord":false,"createDate":"2017-11-01 13:26:19","updateDate":"2017-11-01 15:34:03","order":{"id":"ed7f671863d3492ab21cc6ee4911a9ed","isNewRecord":false},"demand":{"id":"3a89fd54d244485ca08b5c3b42401688","isNewRecord":false},"variety":"3","amount":10,"bonus":10,"url":"https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1509526066491&di=41dddfc05a78ae644a9695eef54ef1dd&imgtype=0&src=http%3A%2F%2Fphotocdn.sohu.com%2F20100208%2FImg270139378.jpg","logisticsTradeno":"1564564564564564","state":"2"},{"id":"e49f400055a641db9705853bfa675008","isNewRecord":false,"createDate":"2017-11-01 13:26:39","updateDate":"2017-11-02 17:16:33","order":{"id":"ed7f671863d3492ab21cc6ee4911a9ed","isNewRecord":false},"demand":{"id":"3a89fd54d244485ca08b5c3b42401688","isNewRecord":false},"variety":"1","amount":10,"bonus":10,"url":"https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1509526066491&di=41dddfc05a78ae644a9695eef54ef1dd&imgtype=0&src=http%3A%2F%2Fphotocdn.sohu.com%2F20100208%2FImg270139378.jpg","logisticsCompany":"京东","logisticsTradeno":"1564564564564564","state":"2"}]
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
             * demandPostage : {"isNewRecord":true,"receiver":"王鑫","telephone":"17978488989","phone":"022-45454545","province":"天津市","city":"天津市","district":"和平区","address":"大沽南路小区","email":"415648544@qq.com"}
             * invoiceVarieties : 1,2,3
             * areaRestrict : 1
             * province :
             * city : 天津市
             * deadline : 2018-11-16
             * totalAmount : 1000
             * leftAmount : 1010
             * totalBonus : 100
             * leftBonus : 110
             * mailMinimum : 0
             * attentions : 放在门卫就行
             * state : 0
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
                 * receiver : 王鑫
                 * telephone : 17978488989
                 * phone : 022-45454545
                 * province : 天津市
                 * city : 天津市
                 * district : 和平区
                 * address : 大沽南路小区
                 * email : 415648544@qq.com
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
             * id : 01db02f7aea9469b9bdb8ccb3affaa28
             * isNewRecord : false
             * createDate : 2017-11-01 13:26:18
             * updateDate : 2017-11-01 15:44:41
             * order : {"id":"ed7f671863d3492ab21cc6ee4911a9ed","isNewRecord":false}
             * demand : {"id":"3a89fd54d244485ca08b5c3b42401688","isNewRecord":false}
             * variety : 2
             * amount : 10
             * bonus : 10
             * url : https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1509526066491&di=41dddfc05a78ae644a9695eef54ef1dd&imgtype=0&src=http%3A%2F%2Fphotocdn.sohu.com%2F20100208%2FImg270139378.jpg
             * state : 4
             * logisticsTradeno : 1564564564564564
             * logisticsCompany : 京东
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
            private String logisticsTradeno;
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

            public String getLogisticsTradeno() {
                return logisticsTradeno;
            }

            public void setLogisticsTradeno(String logisticsTradeno) {
                this.logisticsTradeno = logisticsTradeno;
            }

            public String getLogisticsCompany() {
                return logisticsCompany;
            }

            public void setLogisticsCompany(String logisticsCompany) {
                this.logisticsCompany = logisticsCompany;
            }

            public static class OrderBean {
                /**
                 * id : ed7f671863d3492ab21cc6ee4911a9ed
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
                 * id : 3a89fd54d244485ca08b5c3b42401688
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
