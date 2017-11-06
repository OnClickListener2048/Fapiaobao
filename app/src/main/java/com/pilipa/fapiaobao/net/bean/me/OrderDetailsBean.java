package com.pilipa.fapiaobao.net.bean.me;

import java.util.List;

/**
 * Created by edz on 2017/10/31.
 */

public class OrderDetailsBean {


    /**
     * status : 200
     * msg : OK
     * data : {"invoiceType":{"id":"43f1faf2cfee4f508d02c36975dfa06d","isNewRecord":false,"remarks":"","createDate":"2017-10-29 12:28:39","updateDate":"2017-10-29 12:28:39","category":"4","name":"会议费","smallSize":"","middleSize":"/fapiaobao/userfiles/1/files/personal/invoiceType/2017/10/receipt_011.png","largeSize":"","frequentFlag":"1"},"invoiceList":[{"id":"008fe4d840d7440eaa6e10f53e552c4b","isNewRecord":false,"createDate":"2017-11-06 10:32:17","updateDate":"2017-11-06 10:32:17","order":{"id":"97d1de9787b345b687fbf62c153c2f6a","isNewRecord":false},"demand":{"id":"a758b26e0ca34de4a3851cd1bfe54554","isNewRecord":false},"variety":"1","amount":500,"bonus":50,"url":"http://39.106.4.193/fapiaobao/upload/invoice/f270f1e2dc4645e6a9161deedc1e0d01.jpg","state":"1"}],"invoiceCount":1,"bonus":50,"postage":{"id":"47349c2a5648420e8b73b96d27f49b17","isNewRecord":false,"receiver":"string","telephone":"string","phone":"string","province":"string","city":"string","district":"string","address":"string","email":"string","logisticsCompany":"string"},"company":{"id":"55868770a8f140b5941031f954184cf0","isNewRecord":false,"createDate":"2017-11-01 12:11:42","updateDate":"2017-11-01 12:11:42","customer":{"id":"d2f885adde454362a754be7749d9ea8e","isNewRecord":false},"name":"string","taxno":"string","address":"string","phone":"string","depositBank":"string","account":"string","qrcode":"string"},"orderState":"1","needMail":false}
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
         * invoiceType : {"id":"43f1faf2cfee4f508d02c36975dfa06d","isNewRecord":false,"remarks":"","createDate":"2017-10-29 12:28:39","updateDate":"2017-10-29 12:28:39","category":"4","name":"会议费","smallSize":"","middleSize":"/fapiaobao/userfiles/1/files/personal/invoiceType/2017/10/receipt_011.png","largeSize":"","frequentFlag":"1"}
         * invoiceList : [{"id":"008fe4d840d7440eaa6e10f53e552c4b","isNewRecord":false,"createDate":"2017-11-06 10:32:17","updateDate":"2017-11-06 10:32:17","order":{"id":"97d1de9787b345b687fbf62c153c2f6a","isNewRecord":false},"demand":{"id":"a758b26e0ca34de4a3851cd1bfe54554","isNewRecord":false},"variety":"1","amount":500,"bonus":50,"url":"http://39.106.4.193/fapiaobao/upload/invoice/f270f1e2dc4645e6a9161deedc1e0d01.jpg","state":"1"}]
         * invoiceCount : 1
         * bonus : 50.0
         * postage : {"id":"47349c2a5648420e8b73b96d27f49b17","isNewRecord":false,"receiver":"string","telephone":"string","phone":"string","province":"string","city":"string","district":"string","address":"string","email":"string","logisticsCompany":"string"}
         * company : {"id":"55868770a8f140b5941031f954184cf0","isNewRecord":false,"createDate":"2017-11-01 12:11:42","updateDate":"2017-11-01 12:11:42","customer":{"id":"d2f885adde454362a754be7749d9ea8e","isNewRecord":false},"name":"string","taxno":"string","address":"string","phone":"string","depositBank":"string","account":"string","qrcode":"string"}
         * orderState : 1
         * needMail : false
         */

        private InvoiceTypeBean invoiceType;
        private int invoiceCount;
        private double bonus;
        private PostageBean postage;
        private CompanyBean company;
        private String orderState;
        private boolean needMail;
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

            public String getFrequentFlag() {
                return frequentFlag;
            }

            public void setFrequentFlag(String frequentFlag) {
                this.frequentFlag = frequentFlag;
            }
        }

        public static class PostageBean {
            /**
             * id : 47349c2a5648420e8b73b96d27f49b17
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
             * id : 55868770a8f140b5941031f954184cf0
             * isNewRecord : false
             * createDate : 2017-11-01 12:11:42
             * updateDate : 2017-11-01 12:11:42
             * customer : {"id":"d2f885adde454362a754be7749d9ea8e","isNewRecord":false}
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

        public static class InvoiceListBean {
            /**
             * id : 008fe4d840d7440eaa6e10f53e552c4b
             * isNewRecord : false
             * createDate : 2017-11-06 10:32:17
             * updateDate : 2017-11-06 10:32:17
             * order : {"id":"97d1de9787b345b687fbf62c153c2f6a","isNewRecord":false}
             * demand : {"id":"a758b26e0ca34de4a3851cd1bfe54554","isNewRecord":false}
             * variety : 1
             * amount : 500.0
             * bonus : 50.0
             * url : http://39.106.4.193/fapiaobao/upload/invoice/f270f1e2dc4645e6a9161deedc1e0d01.jpg
             * state : 1
             */

            private String id;
            private boolean isNewRecord;
            private String createDate;
            private String updateDate;
            private OrderBean order;
            private DemandBean demand;
            private String variety;
            private double amount;
            private double bonus;
            private String url;
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

            public static class OrderBean {
                /**
                 * id : 97d1de9787b345b687fbf62c153c2f6a
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

            public static class DemandBean {
                /**
                 * id : a758b26e0ca34de4a3851cd1bfe54554
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
