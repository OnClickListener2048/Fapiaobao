package com.pilipa.fapiaobao.net.bean.me;

import java.util.List;

/**
 * Created by edz on 2017/10/31.
 */

public class OrderDetailsBean {


    /**
     * status : 200
     * msg : OK
     * data : {"invoiceType":{"id":"43f1faf2cfee4f508d02c36975dfa06d","isNewRecord":false,"remarks":"","createDate":"2017-10-29 12:28:39","updateDate":"2017-10-29 12:28:39","category":"4","name":"会议票","smallSize":"","middleSize":"/fapiaobao/userfiles/1/files/personal/invoiceType/2017/10/receipt_011.png","largeSize":"","frequentFlag":"1"},"invoiceList":[{"invoiceVariety":"纸质专票","invoiceList":[{"id":"a3119d5de6274aaa8babf0fc542a7ccc","isNewRecord":false,"createDate":"2017-11-01 13:46:11","updateDate":"2017-11-01 13:46:11","order":{"id":"94dd9e0524544ea29c592912640ec3bd","isNewRecord":false},"demand":{"id":"3a89fd54d244485ca08b5c3b42401688","isNewRecord":false},"variety":"2","url":"http://192.168.1.205:8181/fapiaobao/uploadhttps://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1509526066491&di=41dddfc05a78ae644a9695eef54ef1dd&imgtype=0&src=http%3A%2F%2Fphotocdn.sohu.com%2F20100208%2FImg270139378.jpg","state":"1"},{"id":"ba4517b1d7274665b7e0ede516948695","isNewRecord":false,"createDate":"2017-11-01 13:46:11","updateDate":"2017-11-01 13:46:11","order":{"id":"94dd9e0524544ea29c592912640ec3bd","isNewRecord":false},"demand":{"id":"3a89fd54d244485ca08b5c3b42401688","isNewRecord":false},"variety":"2","url":"http://192.168.1.205:8181/fapiaobao/uploadhttps://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1509526066491&di=41dddfc05a78ae644a9695eef54ef1dd&imgtype=0&src=http%3A%2F%2Fphotocdn.sohu.com%2F20100208%2FImg270139378.jpg","state":"3"}]},{"invoiceVariety":"电子普票","invoiceList":[{"id":"11bb1075d22d49e9a6f0bc417c138f14","isNewRecord":false,"createDate":"2017-11-01 13:47:51","updateDate":"2017-11-01 13:47:51","order":{"id":"94dd9e0524544ea29c592912640ec3bd","isNewRecord":false},"demand":{"id":"3a89fd54d244485ca08b5c3b42401688","isNewRecord":false},"variety":"3","url":"http://192.168.1.205:8181/fapiaobao/uploadhttps://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1509526066491&di=41dddfc05a78ae644a9695eef54ef1dd&imgtype=0&src=http%3A%2F%2Fphotocdn.sohu.com%2F20100208%2FImg270139378.jpg","state":"2"},{"id":"12eb0f73a4ff4cde811a30aa872c18ed","isNewRecord":false,"createDate":"2017-11-01 13:47:51","updateDate":"2017-11-01 13:47:51","order":{"id":"94dd9e0524544ea29c592912640ec3bd","isNewRecord":false},"demand":{"id":"3a89fd54d244485ca08b5c3b42401688","isNewRecord":false},"variety":"3","url":"http://192.168.1.205:8181/fapiaobao/uploadhttps://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1509526066491&di=41dddfc05a78ae644a9695eef54ef1dd&imgtype=0&src=http%3A%2F%2Fphotocdn.sohu.com%2F20100208%2FImg270139378.jpg","state":"3"},{"id":"fe2dc38a077646bb99e5c4b2b59510f2","isNewRecord":false,"createDate":"2017-11-01 13:47:51","updateDate":"2017-11-01 13:47:51","order":{"id":"94dd9e0524544ea29c592912640ec3bd","isNewRecord":false},"demand":{"id":"3a89fd54d244485ca08b5c3b42401688","isNewRecord":false},"variety":"3","url":"http://192.168.1.205:8181/fapiaobao/uploadhttps://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1509526066491&di=41dddfc05a78ae644a9695eef54ef1dd&imgtype=0&src=http%3A%2F%2Fphotocdn.sohu.com%2F20100208%2FImg270139378.jpg","state":"1"}]}],"invoiceCount":5,"bonus":1,"postage":{"id":"ad6d94be51cd49a09ebe562edc6ffd1c","isNewRecord":false,"receiver":"王大锤","telephone":"13612132927","phone":"022-8758469","province":"天津","city":"天津","district":"天津","address":"天津"},"company":{"id":"dfd0ac29b8a94d08a5619a4115279569","isNewRecord":false,"createDate":"2017-11-01 11:26:21","updateDate":"2017-11-01 11:26:21","customer":{"id":"98213a99b6c440e8982d2cd4e56f8584","isNewRecord":false},"name":"天津波康数码","taxno":"57568765828856","address":"天津和平","phone":"022-8758469","depositBank":"建设银行","account":"6825524665425853"},"orderState":"1","needMail":true}
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
         * invoiceType : {"id":"43f1faf2cfee4f508d02c36975dfa06d","isNewRecord":false,"remarks":"","createDate":"2017-10-29 12:28:39","updateDate":"2017-10-29 12:28:39","category":"4","name":"会议票","smallSize":"","middleSize":"/fapiaobao/userfiles/1/files/personal/invoiceType/2017/10/receipt_011.png","largeSize":"","frequentFlag":"1"}
         * invoiceList : [{"invoiceVariety":"纸质专票","invoiceList":[{"id":"a3119d5de6274aaa8babf0fc542a7ccc","isNewRecord":false,"createDate":"2017-11-01 13:46:11","updateDate":"2017-11-01 13:46:11","order":{"id":"94dd9e0524544ea29c592912640ec3bd","isNewRecord":false},"demand":{"id":"3a89fd54d244485ca08b5c3b42401688","isNewRecord":false},"variety":"2","url":"http://192.168.1.205:8181/fapiaobao/uploadhttps://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1509526066491&di=41dddfc05a78ae644a9695eef54ef1dd&imgtype=0&src=http%3A%2F%2Fphotocdn.sohu.com%2F20100208%2FImg270139378.jpg","state":"1"},{"id":"ba4517b1d7274665b7e0ede516948695","isNewRecord":false,"createDate":"2017-11-01 13:46:11","updateDate":"2017-11-01 13:46:11","order":{"id":"94dd9e0524544ea29c592912640ec3bd","isNewRecord":false},"demand":{"id":"3a89fd54d244485ca08b5c3b42401688","isNewRecord":false},"variety":"2","url":"http://192.168.1.205:8181/fapiaobao/uploadhttps://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1509526066491&di=41dddfc05a78ae644a9695eef54ef1dd&imgtype=0&src=http%3A%2F%2Fphotocdn.sohu.com%2F20100208%2FImg270139378.jpg","state":"3"}]},{"invoiceVariety":"电子普票","invoiceList":[{"id":"11bb1075d22d49e9a6f0bc417c138f14","isNewRecord":false,"createDate":"2017-11-01 13:47:51","updateDate":"2017-11-01 13:47:51","order":{"id":"94dd9e0524544ea29c592912640ec3bd","isNewRecord":false},"demand":{"id":"3a89fd54d244485ca08b5c3b42401688","isNewRecord":false},"variety":"3","url":"http://192.168.1.205:8181/fapiaobao/uploadhttps://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1509526066491&di=41dddfc05a78ae644a9695eef54ef1dd&imgtype=0&src=http%3A%2F%2Fphotocdn.sohu.com%2F20100208%2FImg270139378.jpg","state":"2"},{"id":"12eb0f73a4ff4cde811a30aa872c18ed","isNewRecord":false,"createDate":"2017-11-01 13:47:51","updateDate":"2017-11-01 13:47:51","order":{"id":"94dd9e0524544ea29c592912640ec3bd","isNewRecord":false},"demand":{"id":"3a89fd54d244485ca08b5c3b42401688","isNewRecord":false},"variety":"3","url":"http://192.168.1.205:8181/fapiaobao/uploadhttps://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1509526066491&di=41dddfc05a78ae644a9695eef54ef1dd&imgtype=0&src=http%3A%2F%2Fphotocdn.sohu.com%2F20100208%2FImg270139378.jpg","state":"3"},{"id":"fe2dc38a077646bb99e5c4b2b59510f2","isNewRecord":false,"createDate":"2017-11-01 13:47:51","updateDate":"2017-11-01 13:47:51","order":{"id":"94dd9e0524544ea29c592912640ec3bd","isNewRecord":false},"demand":{"id":"3a89fd54d244485ca08b5c3b42401688","isNewRecord":false},"variety":"3","url":"http://192.168.1.205:8181/fapiaobao/uploadhttps://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1509526066491&di=41dddfc05a78ae644a9695eef54ef1dd&imgtype=0&src=http%3A%2F%2Fphotocdn.sohu.com%2F20100208%2FImg270139378.jpg","state":"1"}]}]
         * invoiceCount : 5
         * bonus : 1
         * postage : {"id":"ad6d94be51cd49a09ebe562edc6ffd1c","isNewRecord":false,"receiver":"王大锤","telephone":"13612132927","phone":"022-8758469","province":"天津","city":"天津","district":"天津","address":"天津"}
         * company : {"id":"dfd0ac29b8a94d08a5619a4115279569","isNewRecord":false,"createDate":"2017-11-01 11:26:21","updateDate":"2017-11-01 11:26:21","customer":{"id":"98213a99b6c440e8982d2cd4e56f8584","isNewRecord":false},"name":"天津波康数码","taxno":"57568765828856","address":"天津和平","phone":"022-8758469","depositBank":"建设银行","account":"6825524665425853"}
         * orderState : 1
         * needMail : true
         */

        private InvoiceTypeBean invoiceType;
        private int invoiceCount;
        private int bonus;
        private PostageBean postage;
        private CompanyBean company;
        private String orderState;
        private boolean needMail;
        private List<InvoiceListBeanX> invoiceList;

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

        public int getBonus() {
            return bonus;
        }

        public void setBonus(int bonus) {
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

        public List<InvoiceListBeanX> getInvoiceList() {
            return invoiceList;
        }

        public void setInvoiceList(List<InvoiceListBeanX> invoiceList) {
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
             * name : 会议票
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
             * id : ad6d94be51cd49a09ebe562edc6ffd1c
             * isNewRecord : false
             * receiver : 王大锤
             * telephone : 13612132927
             * phone : 022-8758469
             * province : 天津
             * city : 天津
             * district : 天津
             * address : 天津
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
        }

        public static class CompanyBean {
            /**
             * id : dfd0ac29b8a94d08a5619a4115279569
             * isNewRecord : false
             * createDate : 2017-11-01 11:26:21
             * updateDate : 2017-11-01 11:26:21
             * customer : {"id":"98213a99b6c440e8982d2cd4e56f8584","isNewRecord":false}
             * name : 天津波康数码
             * taxno : 57568765828856
             * address : 天津和平
             * phone : 022-8758469
             * depositBank : 建设银行
             * account : 6825524665425853
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

            public static class CustomerBean {
                /**
                 * id : 98213a99b6c440e8982d2cd4e56f8584
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

        public static class InvoiceListBeanX {
            /**
             * invoiceVariety : 纸质专票
             * invoiceList : [{"id":"a3119d5de6274aaa8babf0fc542a7ccc","isNewRecord":false,"createDate":"2017-11-01 13:46:11","updateDate":"2017-11-01 13:46:11","order":{"id":"94dd9e0524544ea29c592912640ec3bd","isNewRecord":false},"demand":{"id":"3a89fd54d244485ca08b5c3b42401688","isNewRecord":false},"variety":"2","url":"http://192.168.1.205:8181/fapiaobao/uploadhttps://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1509526066491&di=41dddfc05a78ae644a9695eef54ef1dd&imgtype=0&src=http%3A%2F%2Fphotocdn.sohu.com%2F20100208%2FImg270139378.jpg","state":"1"},{"id":"ba4517b1d7274665b7e0ede516948695","isNewRecord":false,"createDate":"2017-11-01 13:46:11","updateDate":"2017-11-01 13:46:11","order":{"id":"94dd9e0524544ea29c592912640ec3bd","isNewRecord":false},"demand":{"id":"3a89fd54d244485ca08b5c3b42401688","isNewRecord":false},"variety":"2","url":"http://192.168.1.205:8181/fapiaobao/uploadhttps://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1509526066491&di=41dddfc05a78ae644a9695eef54ef1dd&imgtype=0&src=http%3A%2F%2Fphotocdn.sohu.com%2F20100208%2FImg270139378.jpg","state":"3"}]
             */

            private String invoiceVariety;
            private List<InvoiceListBean> invoiceList;

            public String getInvoiceVariety() {
                return invoiceVariety;
            }

            public void setInvoiceVariety(String invoiceVariety) {
                this.invoiceVariety = invoiceVariety;
            }

            public List<InvoiceListBean> getInvoiceList() {
                return invoiceList;
            }

            public void setInvoiceList(List<InvoiceListBean> invoiceList) {
                this.invoiceList = invoiceList;
            }

            public static class InvoiceListBean {
                /**
                 * id : a3119d5de6274aaa8babf0fc542a7ccc
                 * isNewRecord : false
                 * createDate : 2017-11-01 13:46:11
                 * updateDate : 2017-11-01 13:46:11
                 * order : {"id":"94dd9e0524544ea29c592912640ec3bd","isNewRecord":false}
                 * demand : {"id":"3a89fd54d244485ca08b5c3b42401688","isNewRecord":false}
                 * variety : 2
                 * url : http://192.168.1.205:8181/fapiaobao/uploadhttps://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1509526066491&di=41dddfc05a78ae644a9695eef54ef1dd&imgtype=0&src=http%3A%2F%2Fphotocdn.sohu.com%2F20100208%2FImg270139378.jpg
                 * state : 1
                 */

                private String id;
                private boolean isNewRecord;
                private String createDate;
                private String updateDate;
                private OrderBean order;
                private DemandBean demand;
                private String variety;
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

                public static class DemandBean {
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
}
