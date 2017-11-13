package com.pilipa.fapiaobao.net.bean.publish;

import java.util.List;

/**
 * Created by edz on 2017/10/29.
 */

public class DemandsPublishBean {

    @Override
    public String toString() {
        return "DemandsPublishBean{" +
                "areaRestrict='" + areaRestrict + '\'' +
                ", attentions='" + attentions + '\'' +
                ", beginCloseDate='" + beginCloseDate + '\'' +
                ", beginDeadline='" + beginDeadline + '\'' +
                ", beginLeftAmount=" + beginLeftAmount +
                ", beginLeftBonus=" + beginLeftBonus +
                ", beginMailMinimum=" + beginMailMinimum +
                ", beginTotalAmount=" + beginTotalAmount +
                ", beginTotalBonus=" + beginTotalBonus +
                ", city='" + city + '\'' +
                ", closeDate='" + closeDate + '\'' +
                ", company=" + company +
                ", createDate='" + createDate + '\'' +
                ", customer=" + customer +
                ", deadline='" + deadline + '\'' +
                ", demandPostage=" + demandPostage +
                ", endCloseDate='" + endCloseDate + '\'' +
                ", endDeadline='" + endDeadline + '\'' +
                ", endLeftAmount=" + endLeftAmount +
                ", endLeftBonus=" + endLeftBonus +
                ", endMailMinimum=" + endMailMinimum +
                ", endTotalAmount=" + endTotalAmount +
                ", endTotalBonus=" + endTotalBonus +
                ", id='" + id + '\'' +
                ", invoiceVarieties='" + invoiceVarieties + '\'' +
                ", isNewRecord=" + isNewRecord +
                ", leftAmount=" + leftAmount +
                ", leftBonus=" + leftBonus +
                ", mailMinimum=" + mailMinimum +
                ", orderInvoice=" + orderInvoice +
                ", province='" + province + '\'' +
                ", remarks='" + remarks + '\'' +
                ", state='" + state + '\'' +
                ", totalAmount=" + totalAmount +
                ", totalBonus=" + totalBonus +
                ", updateDate='" + updateDate + '\'' +
                ", token='" + token + '\'' +
                ", demandInvoiceTypeList=" + demandInvoiceTypeList +
                '}';
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    /**
     * areaRestrict : string
     * attentions : string
     * beginCloseDate : 2017-10-29T12:32:36.247Z
     * beginDeadline : 2017-10-29T12:32:36.247Z
     * beginLeftAmount : 0
     * beginLeftBonus : 0
     * beginMailMinimum : 0
     * beginTotalAmount : 0
     * beginTotalBonus : 0
     * city : string
     * closeDate : 2017-10-29T12:32:36.247Z
     * company : {"account":"string","address":"string","createDate":"2017-10-29T12:32:36.247Z","customer":{"amount":0,"beginAmount":0,"beginBirthday":"string","beginBonus":0,"beginFrozen":0,"birthday":"string","bonus":0,"createDate":"2017-10-29T12:32:36.247Z","creditLevel":0,"creditScore":0,"deviceToken":"string","endAmount":0,"endBirthday":"string","endBonus":0,"endFrozen":0,"frequentType":"string","frozen":0,"gender":"string","headimg":"string","id":"string","isNewRecord":true,"nickname":"string","openid":"string","remarks":"string","telephone":"string","updateDate":"2017-10-29T12:32:36.247Z"},"depositBank":"string","id":"string","isDefault":"string","isNewRecord":true,"name":"string","phone":"string","qrcode":"string","remarks":"string","taxno":"string","updateDate":"2017-10-29T12:32:36.247Z"}
     * createDate : 2017-10-29T12:32:36.247Z
     * customer : {"amount":0,"beginAmount":0,"beginBirthday":"string","beginBonus":0,"beginFrozen":0,"birthday":"string","bonus":0,"createDate":"2017-10-29T12:32:36.247Z","creditLevel":0,"creditScore":0,"deviceToken":"string","endAmount":0,"endBirthday":"string","endBonus":0,"endFrozen":0,"frequentType":"string","frozen":0,"gender":"string","headimg":"string","id":"string","isNewRecord":true,"nickname":"string","openid":"string","remarks":"string","telephone":"string","updateDate":"2017-10-29T12:32:36.247Z"}
     * deadline : 2017-10-29T12:32:36.247Z
     * demandInvoiceTypeList : [{"createDate":"2017-10-29T12:32:36.247Z","demand":{},"id":"string","invoiceType":{"category":"string","categorySort":0,"createDate":"2017-10-29T12:32:36.247Z","frequentFlag":"string","frequentSort":0,"id":"string","isNewRecord":true,"largeSize":"string","middleSize":"string","name":"string","remarks":"string","smallSize":"string","updateDate":"2017-10-29T12:32:36.247Z"},"isNewRecord":true,"remarks":"string","updateDate":"2017-10-29T12:32:36.247Z"}]
     * demandPostage : {"address":"string","city":"string","createDate":"2017-10-29T12:32:36.248Z","district":"string","email":"string","id":"string","isNewRecord":true,"phone":"string","province":"string","receiver":"string","remarks":"string","telephone":"string","updateDate":"2017-10-29T12:32:36.248Z"}
     * endCloseDate : 2017-10-29T12:32:36.248Z
     * endDeadline : 2017-10-29T12:32:36.248Z
     * endLeftAmount : 0
     * endLeftBonus : 0
     * endMailMinimum : 0
     * endTotalAmount : 0
     * endTotalBonus : 0
     * id : string
     * invoiceVarieties : string
     * isNewRecord : true
     * leftAmount : 0
     * leftBonus : 0
     * mailMinimum : 0
     * orderInvoice : {"amount":0,"beginAmount":0,"beginBonus":0,"bonus":0,"createDate":"2017-10-29T12:32:36.248Z","demand":{},"endAmount":0,"endBonus":0,"id":"string","isNewRecord":true,"logisticsCompany":"string","logisticsTradeno":"string","order":{"amount":0,"bonus":0,"company":{"account":"string","address":"string","createDate":"2017-10-29T12:32:36.248Z","customer":{"amount":0,"beginAmount":0,"beginBirthday":"string","beginBonus":0,"beginFrozen":0,"birthday":"string","bonus":0,"createDate":"2017-10-29T12:32:36.248Z","creditLevel":0,"creditScore":0,"deviceToken":"string","endAmount":0,"endBirthday":"string","endBonus":0,"endFrozen":0,"frequentType":"string","frozen":0,"gender":"string","headimg":"string","id":"string","isNewRecord":true,"nickname":"string","openid":"string","remarks":"string","telephone":"string","updateDate":"2017-10-29T12:32:36.248Z"},"depositBank":"string","id":"string","isDefault":"string","isNewRecord":true,"name":"string","phone":"string","qrcode":"string","remarks":"string","taxno":"string","updateDate":"2017-10-29T12:32:36.248Z"},"createDate":"2017-10-29T12:32:36.248Z","customer":{"amount":0,"beginAmount":0,"beginBirthday":"string","beginBonus":0,"beginFrozen":0,"birthday":"string","bonus":0,"createDate":"2017-10-29T12:32:36.248Z","creditLevel":0,"creditScore":0,"deviceToken":"string","endAmount":0,"endBirthday":"string","endBonus":0,"endFrozen":0,"frequentType":"string","frozen":0,"gender":"string","headimg":"string","id":"string","isNewRecord":true,"nickname":"string","openid":"string","remarks":"string","telephone":"string","updateDate":"2017-10-29T12:32:36.248Z"},"demand":{},"id":"string","invoiceType":{"category":"string","categorySort":0,"createDate":"2017-10-29T12:32:36.248Z","frequentFlag":"string","frequentSort":0,"id":"string","isNewRecord":true,"largeSize":"string","middleSize":"string","name":"string","remarks":"string","smallSize":"string","updateDate":"2017-10-29T12:32:36.248Z"},"isNewRecord":true,"orderInvoiceList":[{}],"remarks":"string","state":"string","updateDate":"2017-10-29T12:32:36.248Z"},"remarks":"string","state":"string","updateDate":"2017-10-29T12:32:36.248Z","url":"string","variety":"string"}
     * province : string
     * remarks : string
     * state : string
     * totalAmount : 0
     * totalBonus : 0
     * updateDate : 2017-10-29T12:32:36.248Z
     */

    private String companyId;
    private String areaRestrict;
    private String attentions;
    private String beginCloseDate;
    private String beginDeadline;
    private int beginLeftAmount;
    private int beginLeftBonus;
    private int beginMailMinimum;
    private int beginTotalAmount;
    private int beginTotalBonus;
    private String city;
    private String closeDate;
    private CompanyBean company;
    private String createDate;
    private CustomerBeanX customer;
    private String deadline;
    private DemandPostageBean demandPostage;
    private String endCloseDate;
    private String endDeadline;
    private int endLeftAmount;
    private int endLeftBonus;
    private int endMailMinimum;
    private int endTotalAmount;
    private int endTotalBonus;
    private String id;
    private String invoiceVarieties;
    private boolean isNewRecord;
    private int leftAmount;
    private int leftBonus;
    private int mailMinimum;
    private OrderInvoiceBean orderInvoice;
    private String province;
    private String remarks;
    private String state;
    private int totalAmount;
    private int totalBonus;
    private String updateDate;
    private String token;
    private List<DemandInvoiceTypeListBean> demandInvoiceTypeList;
    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }



    public String getAreaRestrict() {
        return areaRestrict;
    }

    public void setAreaRestrict(String areaRestrict) {
        this.areaRestrict = areaRestrict;
    }

    public String getAttentions() {
        return attentions;
    }

    public void setAttentions(String attentions) {
        this.attentions = attentions;
    }

    public String getBeginCloseDate() {
        return beginCloseDate;
    }

    public void setBeginCloseDate(String beginCloseDate) {
        this.beginCloseDate = beginCloseDate;
    }

    public String getBeginDeadline() {
        return beginDeadline;
    }

    public void setBeginDeadline(String beginDeadline) {
        this.beginDeadline = beginDeadline;
    }

    public int getBeginLeftAmount() {
        return beginLeftAmount;
    }

    public void setBeginLeftAmount(int beginLeftAmount) {
        this.beginLeftAmount = beginLeftAmount;
    }

    public int getBeginLeftBonus() {
        return beginLeftBonus;
    }

    public void setBeginLeftBonus(int beginLeftBonus) {
        this.beginLeftBonus = beginLeftBonus;
    }

    public int getBeginMailMinimum() {
        return beginMailMinimum;
    }

    public void setBeginMailMinimum(int beginMailMinimum) {
        this.beginMailMinimum = beginMailMinimum;
    }

    public int getBeginTotalAmount() {
        return beginTotalAmount;
    }

    public void setBeginTotalAmount(int beginTotalAmount) {
        this.beginTotalAmount = beginTotalAmount;
    }

    public int getBeginTotalBonus() {
        return beginTotalBonus;
    }

    public void setBeginTotalBonus(int beginTotalBonus) {
        this.beginTotalBonus = beginTotalBonus;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCloseDate() {
        return closeDate;
    }

    public void setCloseDate(String closeDate) {
        this.closeDate = closeDate;
    }

    public CompanyBean getCompany() {
        return company;
    }

    public void setCompany(CompanyBean company) {
        this.company = company;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public CustomerBeanX getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerBeanX customer) {
        this.customer = customer;
    }

    public String getDeadline() {
        return deadline;
    }

    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }

    public DemandPostageBean getDemandPostage() {
        return demandPostage;
    }

    public void setDemandPostage(DemandPostageBean demandPostage) {
        this.demandPostage = demandPostage;
    }

    public String getEndCloseDate() {
        return endCloseDate;
    }

    public void setEndCloseDate(String endCloseDate) {
        this.endCloseDate = endCloseDate;
    }

    public String getEndDeadline() {
        return endDeadline;
    }

    public void setEndDeadline(String endDeadline) {
        this.endDeadline = endDeadline;
    }

    public int getEndLeftAmount() {
        return endLeftAmount;
    }

    public void setEndLeftAmount(int endLeftAmount) {
        this.endLeftAmount = endLeftAmount;
    }

    public int getEndLeftBonus() {
        return endLeftBonus;
    }

    public void setEndLeftBonus(int endLeftBonus) {
        this.endLeftBonus = endLeftBonus;
    }

    public int getEndMailMinimum() {
        return endMailMinimum;
    }

    public void setEndMailMinimum(int endMailMinimum) {
        this.endMailMinimum = endMailMinimum;
    }

    public int getEndTotalAmount() {
        return endTotalAmount;
    }

    public void setEndTotalAmount(int endTotalAmount) {
        this.endTotalAmount = endTotalAmount;
    }

    public int getEndTotalBonus() {
        return endTotalBonus;
    }

    public void setEndTotalBonus(int endTotalBonus) {
        this.endTotalBonus = endTotalBonus;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getInvoiceVarieties() {
        return invoiceVarieties;
    }

    public void setInvoiceVarieties(String invoiceVarieties) {
        this.invoiceVarieties = invoiceVarieties;
    }

    public boolean isIsNewRecord() {
        return isNewRecord;
    }

    public void setIsNewRecord(boolean isNewRecord) {
        this.isNewRecord = isNewRecord;
    }

    public int getLeftAmount() {
        return leftAmount;
    }

    public void setLeftAmount(int leftAmount) {
        this.leftAmount = leftAmount;
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

    public OrderInvoiceBean getOrderInvoice() {
        return orderInvoice;
    }

    public void setOrderInvoice(OrderInvoiceBean orderInvoice) {
        this.orderInvoice = orderInvoice;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public int getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(int totalAmount) {
        this.totalAmount = totalAmount;
    }

    public int getTotalBonus() {
        return totalBonus;
    }

    public void setTotalBonus(int totalBonus) {
        this.totalBonus = totalBonus;
    }

    public String getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate;
    }

    public List<DemandInvoiceTypeListBean> getDemandInvoiceTypeList() {
        return demandInvoiceTypeList;
    }

    public void setDemandInvoiceTypeList(List<DemandInvoiceTypeListBean> demandInvoiceTypeList) {
        this.demandInvoiceTypeList = demandInvoiceTypeList;
    }

    public static class CompanyBean {
        @Override
        public String toString() {
            return "CompanyBean{" +
                    "account='" + account + '\'' +
                    ", address='" + address + '\'' +
                    ", createDate='" + createDate + '\'' +
                    ", customer=" + customer +
                    ", depositBank='" + depositBank + '\'' +
                    ", id='" + id + '\'' +
                    ", isDefault='" + isDefault + '\'' +
                    ", isNewRecord=" + isNewRecord +
                    ", name='" + name + '\'' +
                    ", phone='" + phone + '\'' +
                    ", qrcode='" + qrcode + '\'' +
                    ", remarks='" + remarks + '\'' +
                    ", taxno='" + taxno + '\'' +
                    ", updateDate='" + updateDate + '\'' +
                    '}';
        }

        /**
         * account : string
         * address : string
         * createDate : 2017-10-29T12:32:36.247Z
         * customer : {"amount":0,"beginAmount":0,"beginBirthday":"string","beginBonus":0,"beginFrozen":0,"birthday":"string","bonus":0,"createDate":"2017-10-29T12:32:36.247Z","creditLevel":0,"creditScore":0,"deviceToken":"string","endAmount":0,"endBirthday":"string","endBonus":0,"endFrozen":0,"frequentType":"string","frozen":0,"gender":"string","headimg":"string","id":"string","isNewRecord":true,"nickname":"string","openid":"string","remarks":"string","telephone":"string","updateDate":"2017-10-29T12:32:36.247Z"}
         * depositBank : string
         * id : string
         * isDefault : string
         * isNewRecord : true
         * name : string
         * phone : string
         * qrcode : string
         * remarks : string
         * taxno : string
         * updateDate : 2017-10-29T12:32:36.247Z
         */

        private String account;
        private String address;
        private String createDate;
        private CustomerBean customer;
        private String depositBank;
        private String id;
        private String isDefault;
        private boolean isNewRecord;
        private String name;
        private String phone;
        private String qrcode;
        private String remarks;
        private String taxno;
        private String updateDate;

        public String getAccount() {
            return account;
        }

        public void setAccount(String account) {
            this.account = account;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getCreateDate() {
            return createDate;
        }

        public void setCreateDate(String createDate) {
            this.createDate = createDate;
        }

        public CustomerBean getCustomer() {
            return customer;
        }

        public void setCustomer(CustomerBean customer) {
            this.customer = customer;
        }

        public String getDepositBank() {
            return depositBank;
        }

        public void setDepositBank(String depositBank) {
            this.depositBank = depositBank;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getIsDefault() {
            return isDefault;
        }

        public void setIsDefault(String isDefault) {
            this.isDefault = isDefault;
        }

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

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getQrcode() {
            return qrcode;
        }

        public void setQrcode(String qrcode) {
            this.qrcode = qrcode;
        }

        public String getRemarks() {
            return remarks;
        }

        public void setRemarks(String remarks) {
            this.remarks = remarks;
        }

        public String getTaxno() {
            return taxno;
        }

        public void setTaxno(String taxno) {
            this.taxno = taxno;
        }

        public String getUpdateDate() {
            return updateDate;
        }

        public void setUpdateDate(String updateDate) {
            this.updateDate = updateDate;
        }

        public static class CustomerBean {
            @Override
            public String toString() {
                return "CustomerBean{" +
                        "amount=" + amount +
                        ", beginAmount=" + beginAmount +
                        ", beginBirthday='" + beginBirthday + '\'' +
                        ", beginBonus=" + beginBonus +
                        ", beginFrozen=" + beginFrozen +
                        ", birthday='" + birthday + '\'' +
                        ", bonus=" + bonus +
                        ", createDate='" + createDate + '\'' +
                        ", creditLevel=" + creditLevel +
                        ", creditScore=" + creditScore +
                        ", deviceToken='" + deviceToken + '\'' +
                        ", endAmount=" + endAmount +
                        ", endBirthday='" + endBirthday + '\'' +
                        ", endBonus=" + endBonus +
                        ", endFrozen=" + endFrozen +
                        ", frequentType='" + frequentType + '\'' +
                        ", frozen=" + frozen +
                        ", gender='" + gender + '\'' +
                        ", headimg='" + headimg + '\'' +
                        ", id='" + id + '\'' +
                        ", isNewRecord=" + isNewRecord +
                        ", nickname='" + nickname + '\'' +
                        ", openid='" + openid + '\'' +
                        ", remarks='" + remarks + '\'' +
                        ", telephone='" + telephone + '\'' +
                        ", updateDate='" + updateDate + '\'' +
                        '}';
            }

            /**
             * amount : 0
             * beginAmount : 0
             * beginBirthday : string
             * beginBonus : 0
             * beginFrozen : 0
             * birthday : string
             * bonus : 0
             * createDate : 2017-10-29T12:32:36.247Z
             * creditLevel : 0
             * creditScore : 0
             * deviceToken : string
             * endAmount : 0
             * endBirthday : string
             * endBonus : 0
             * endFrozen : 0
             * frequentType : string
             * frozen : 0
             * gender : string
             * headimg : string
             * id : string
             * isNewRecord : true
             * nickname : string
             * openid : string
             * remarks : string
             * telephone : string
             * updateDate : 2017-10-29T12:32:36.247Z
             */

            private int amount;
            private int beginAmount;
            private String beginBirthday;
            private int beginBonus;
            private int beginFrozen;
            private String birthday;
            private int bonus;
            private String createDate;
            private int creditLevel;
            private int creditScore;
            private String deviceToken;
            private int endAmount;
            private String endBirthday;
            private int endBonus;
            private int endFrozen;
            private String frequentType;
            private int frozen;
            private String gender;
            private String headimg;
            private String id;
            private boolean isNewRecord;
            private String nickname;
            private String openid;
            private String remarks;
            private String telephone;
            private String updateDate;

            public int getAmount() {
                return amount;
            }

            public void setAmount(int amount) {
                this.amount = amount;
            }

            public int getBeginAmount() {
                return beginAmount;
            }

            public void setBeginAmount(int beginAmount) {
                this.beginAmount = beginAmount;
            }

            public String getBeginBirthday() {
                return beginBirthday;
            }

            public void setBeginBirthday(String beginBirthday) {
                this.beginBirthday = beginBirthday;
            }

            public int getBeginBonus() {
                return beginBonus;
            }

            public void setBeginBonus(int beginBonus) {
                this.beginBonus = beginBonus;
            }

            public int getBeginFrozen() {
                return beginFrozen;
            }

            public void setBeginFrozen(int beginFrozen) {
                this.beginFrozen = beginFrozen;
            }

            public String getBirthday() {
                return birthday;
            }

            public void setBirthday(String birthday) {
                this.birthday = birthday;
            }

            public int getBonus() {
                return bonus;
            }

            public void setBonus(int bonus) {
                this.bonus = bonus;
            }

            public String getCreateDate() {
                return createDate;
            }

            public void setCreateDate(String createDate) {
                this.createDate = createDate;
            }

            public int getCreditLevel() {
                return creditLevel;
            }

            public void setCreditLevel(int creditLevel) {
                this.creditLevel = creditLevel;
            }

            public int getCreditScore() {
                return creditScore;
            }

            public void setCreditScore(int creditScore) {
                this.creditScore = creditScore;
            }

            public String getDeviceToken() {
                return deviceToken;
            }

            public void setDeviceToken(String deviceToken) {
                this.deviceToken = deviceToken;
            }

            public int getEndAmount() {
                return endAmount;
            }

            public void setEndAmount(int endAmount) {
                this.endAmount = endAmount;
            }

            public String getEndBirthday() {
                return endBirthday;
            }

            public void setEndBirthday(String endBirthday) {
                this.endBirthday = endBirthday;
            }

            public int getEndBonus() {
                return endBonus;
            }

            public void setEndBonus(int endBonus) {
                this.endBonus = endBonus;
            }

            public int getEndFrozen() {
                return endFrozen;
            }

            public void setEndFrozen(int endFrozen) {
                this.endFrozen = endFrozen;
            }

            public String getFrequentType() {
                return frequentType;
            }

            public void setFrequentType(String frequentType) {
                this.frequentType = frequentType;
            }

            public int getFrozen() {
                return frozen;
            }

            public void setFrozen(int frozen) {
                this.frozen = frozen;
            }

            public String getGender() {
                return gender;
            }

            public void setGender(String gender) {
                this.gender = gender;
            }

            public String getHeadimg() {
                return headimg;
            }

            public void setHeadimg(String headimg) {
                this.headimg = headimg;
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

            public String getNickname() {
                return nickname;
            }

            public void setNickname(String nickname) {
                this.nickname = nickname;
            }

            public String getOpenid() {
                return openid;
            }

            public void setOpenid(String openid) {
                this.openid = openid;
            }

            public String getRemarks() {
                return remarks;
            }

            public void setRemarks(String remarks) {
                this.remarks = remarks;
            }

            public String getTelephone() {
                return telephone;
            }

            public void setTelephone(String telephone) {
                this.telephone = telephone;
            }

            public String getUpdateDate() {
                return updateDate;
            }

            public void setUpdateDate(String updateDate) {
                this.updateDate = updateDate;
            }
        }
    }

    public static class CustomerBeanX {
        @Override
        public String toString() {
            return "CustomerBeanX{" +
                    "amount=" + amount +
                    ", beginAmount=" + beginAmount +
                    ", beginBirthday='" + beginBirthday + '\'' +
                    ", beginBonus=" + beginBonus +
                    ", beginFrozen=" + beginFrozen +
                    ", birthday='" + birthday + '\'' +
                    ", bonus=" + bonus +
                    ", createDate='" + createDate + '\'' +
                    ", creditLevel=" + creditLevel +
                    ", creditScore=" + creditScore +
                    ", deviceToken='" + deviceToken + '\'' +
                    ", endAmount=" + endAmount +
                    ", endBirthday='" + endBirthday + '\'' +
                    ", endBonus=" + endBonus +
                    ", endFrozen=" + endFrozen +
                    ", frequentType='" + frequentType + '\'' +
                    ", frozen=" + frozen +
                    ", gender='" + gender + '\'' +
                    ", headimg='" + headimg + '\'' +
                    ", id='" + id + '\'' +
                    ", isNewRecord=" + isNewRecord +
                    ", nickname='" + nickname + '\'' +
                    ", openid='" + openid + '\'' +
                    ", remarks='" + remarks + '\'' +
                    ", telephone='" + telephone + '\'' +
                    ", updateDate='" + updateDate + '\'' +
                    '}';
        }

        /**
         * amount : 0
         * beginAmount : 0
         * beginBirthday : string
         * beginBonus : 0
         * beginFrozen : 0
         * birthday : string
         * bonus : 0
         * createDate : 2017-10-29T12:32:36.247Z
         * creditLevel : 0
         * creditScore : 0
         * deviceToken : string
         * endAmount : 0
         * endBirthday : string
         * endBonus : 0
         * endFrozen : 0
         * frequentType : string
         * frozen : 0
         * gender : string
         * headimg : string
         * id : string
         * isNewRecord : true
         * nickname : string
         * openid : string
         * remarks : string
         * telephone : string
         * updateDate : 2017-10-29T12:32:36.247Z
         */

        private int amount;
        private int beginAmount;
        private String beginBirthday;
        private int beginBonus;
        private int beginFrozen;
        private String birthday;
        private int bonus;
        private String createDate;
        private int creditLevel;
        private int creditScore;
        private String deviceToken;
        private int endAmount;
        private String endBirthday;
        private int endBonus;
        private int endFrozen;
        private String frequentType;
        private int frozen;
        private String gender;
        private String headimg;
        private String id;
        private boolean isNewRecord;
        private String nickname;
        private String openid;
        private String remarks;
        private String telephone;
        private String updateDate;

        public int getAmount() {
            return amount;
        }

        public void setAmount(int amount) {
            this.amount = amount;
        }

        public int getBeginAmount() {
            return beginAmount;
        }

        public void setBeginAmount(int beginAmount) {
            this.beginAmount = beginAmount;
        }

        public String getBeginBirthday() {
            return beginBirthday;
        }

        public void setBeginBirthday(String beginBirthday) {
            this.beginBirthday = beginBirthday;
        }

        public int getBeginBonus() {
            return beginBonus;
        }

        public void setBeginBonus(int beginBonus) {
            this.beginBonus = beginBonus;
        }

        public int getBeginFrozen() {
            return beginFrozen;
        }

        public void setBeginFrozen(int beginFrozen) {
            this.beginFrozen = beginFrozen;
        }

        public String getBirthday() {
            return birthday;
        }

        public void setBirthday(String birthday) {
            this.birthday = birthday;
        }

        public int getBonus() {
            return bonus;
        }

        public void setBonus(int bonus) {
            this.bonus = bonus;
        }

        public String getCreateDate() {
            return createDate;
        }

        public void setCreateDate(String createDate) {
            this.createDate = createDate;
        }

        public int getCreditLevel() {
            return creditLevel;
        }

        public void setCreditLevel(int creditLevel) {
            this.creditLevel = creditLevel;
        }

        public int getCreditScore() {
            return creditScore;
        }

        public void setCreditScore(int creditScore) {
            this.creditScore = creditScore;
        }

        public String getDeviceToken() {
            return deviceToken;
        }

        public void setDeviceToken(String deviceToken) {
            this.deviceToken = deviceToken;
        }

        public int getEndAmount() {
            return endAmount;
        }

        public void setEndAmount(int endAmount) {
            this.endAmount = endAmount;
        }

        public String getEndBirthday() {
            return endBirthday;
        }

        public void setEndBirthday(String endBirthday) {
            this.endBirthday = endBirthday;
        }

        public int getEndBonus() {
            return endBonus;
        }

        public void setEndBonus(int endBonus) {
            this.endBonus = endBonus;
        }

        public int getEndFrozen() {
            return endFrozen;
        }

        public void setEndFrozen(int endFrozen) {
            this.endFrozen = endFrozen;
        }

        public String getFrequentType() {
            return frequentType;
        }

        public void setFrequentType(String frequentType) {
            this.frequentType = frequentType;
        }

        public int getFrozen() {
            return frozen;
        }

        public void setFrozen(int frozen) {
            this.frozen = frozen;
        }

        public String getGender() {
            return gender;
        }

        public void setGender(String gender) {
            this.gender = gender;
        }

        public String getHeadimg() {
            return headimg;
        }

        public void setHeadimg(String headimg) {
            this.headimg = headimg;
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

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public String getOpenid() {
            return openid;
        }

        public void setOpenid(String openid) {
            this.openid = openid;
        }

        public String getRemarks() {
            return remarks;
        }

        public void setRemarks(String remarks) {
            this.remarks = remarks;
        }

        public String getTelephone() {
            return telephone;
        }

        public void setTelephone(String telephone) {
            this.telephone = telephone;
        }

        public String getUpdateDate() {
            return updateDate;
        }

        public void setUpdateDate(String updateDate) {
            this.updateDate = updateDate;
        }
    }

    public static class DemandPostageBean {
        @Override
        public String toString() {
            return "DemandPostageBean{" +
                    "address='" + address + '\'' +
                    ", city='" + city + '\'' +
                    ", createDate='" + createDate + '\'' +
                    ", district='" + district + '\'' +
                    ", email='" + email + '\'' +
                    ", id='" + id + '\'' +
                    ", isNewRecord=" + isNewRecord +
                    ", phone='" + phone + '\'' +
                    ", province='" + province + '\'' +
                    ", receiver='" + receiver + '\'' +
                    ", remarks='" + remarks + '\'' +
                    ", telephone='" + telephone + '\'' +
                    ", updateDate='" + updateDate + '\'' +
                    ", logisticsCompany='" + logisticsCompany + '\'' +
                    '}';
        }

        /**
         * address : string
         * city : string
         * createDate : 2017-10-29T12:32:36.248Z
         * district : string
         * email : string
         * id : string
         * isNewRecord : true
         * phone : string
         * province : string
         * receiver : string
         * remarks : string
         * telephone : string
         * updateDate : 2017-10-29T12:32:36.248Z
         */

        private String address;
        private String city;
        private String createDate;
        private String district;
        private String email;
        private String id;
        private boolean isNewRecord;
        private String phone;
        private String province;
        private String receiver;
        private String remarks;
        private String telephone;
        private String updateDate;
        private String logisticsCompany;


        public String getLogisticsCompany() {
            return logisticsCompany;
        }

        public void setLogisticsCompany(String logisticsCompany) {
            this.logisticsCompany = logisticsCompany;
        }


        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getCreateDate() {
            return createDate;
        }

        public void setCreateDate(String createDate) {
            this.createDate = createDate;
        }

        public String getDistrict() {
            return district;
        }

        public void setDistrict(String district) {
            this.district = district;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
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

        public String getReceiver() {
            return receiver;
        }

        public void setReceiver(String receiver) {
            this.receiver = receiver;
        }

        public String getRemarks() {
            return remarks;
        }

        public void setRemarks(String remarks) {
            this.remarks = remarks;
        }

        public String getTelephone() {
            return telephone;
        }

        public void setTelephone(String telephone) {
            this.telephone = telephone;
        }

        public String getUpdateDate() {
            return updateDate;
        }

        public void setUpdateDate(String updateDate) {
            this.updateDate = updateDate;
        }
    }

    public static class OrderInvoiceBean {
        @Override
        public String toString() {
            return "OrderInvoiceBean{" +
                    "amount=" + amount +
                    ", beginAmount=" + beginAmount +
                    ", beginBonus=" + beginBonus +
                    ", bonus=" + bonus +
                    ", createDate='" + createDate + '\'' +
                    ", demand=" + demand +
                    ", endAmount=" + endAmount +
                    ", endBonus=" + endBonus +
                    ", id='" + id + '\'' +
                    ", isNewRecord=" + isNewRecord +
                    ", logisticsCompany='" + logisticsCompany + '\'' +
                    ", logisticsTradeno='" + logisticsTradeno + '\'' +
                    ", order=" + order +
                    ", remarks='" + remarks + '\'' +
                    ", state='" + state + '\'' +
                    ", updateDate='" + updateDate + '\'' +
                    ", url='" + url + '\'' +
                    ", variety='" + variety + '\'' +
                    '}';
        }

        /**
         * amount : 0
         * beginAmount : 0
         * beginBonus : 0
         * bonus : 0
         * createDate : 2017-10-29T12:32:36.248Z
         * demand : {}
         * endAmount : 0
         * endBonus : 0
         * id : string
         * isNewRecord : true
         * logisticsCompany : string
         * logisticsTradeno : string
         * order : {"amount":0,"bonus":0,"company":{"account":"string","address":"string","createDate":"2017-10-29T12:32:36.248Z","customer":{"amount":0,"beginAmount":0,"beginBirthday":"string","beginBonus":0,"beginFrozen":0,"birthday":"string","bonus":0,"createDate":"2017-10-29T12:32:36.248Z","creditLevel":0,"creditScore":0,"deviceToken":"string","endAmount":0,"endBirthday":"string","endBonus":0,"endFrozen":0,"frequentType":"string","frozen":0,"gender":"string","headimg":"string","id":"string","isNewRecord":true,"nickname":"string","openid":"string","remarks":"string","telephone":"string","updateDate":"2017-10-29T12:32:36.248Z"},"depositBank":"string","id":"string","isDefault":"string","isNewRecord":true,"name":"string","phone":"string","qrcode":"string","remarks":"string","taxno":"string","updateDate":"2017-10-29T12:32:36.248Z"},"createDate":"2017-10-29T12:32:36.248Z","customer":{"amount":0,"beginAmount":0,"beginBirthday":"string","beginBonus":0,"beginFrozen":0,"birthday":"string","bonus":0,"createDate":"2017-10-29T12:32:36.248Z","creditLevel":0,"creditScore":0,"deviceToken":"string","endAmount":0,"endBirthday":"string","endBonus":0,"endFrozen":0,"frequentType":"string","frozen":0,"gender":"string","headimg":"string","id":"string","isNewRecord":true,"nickname":"string","openid":"string","remarks":"string","telephone":"string","updateDate":"2017-10-29T12:32:36.248Z"},"demand":{},"id":"string","invoiceType":{"category":"string","categorySort":0,"createDate":"2017-10-29T12:32:36.248Z","frequentFlag":"string","frequentSort":0,"id":"string","isNewRecord":true,"largeSize":"string","middleSize":"string","name":"string","remarks":"string","smallSize":"string","updateDate":"2017-10-29T12:32:36.248Z"},"isNewRecord":true,"orderInvoiceList":[{}],"remarks":"string","state":"string","updateDate":"2017-10-29T12:32:36.248Z"}
         * remarks : string
         * state : string
         * updateDate : 2017-10-29T12:32:36.248Z
         * url : string
         * variety : string
         */

        private int amount;
        private int beginAmount;
        private int beginBonus;
        private int bonus;
        private String createDate;
        private DemandBean demand;
        private int endAmount;
        private int endBonus;
        private String id;
        private boolean isNewRecord;
        private String logisticsCompany;
        private String logisticsTradeno;
        private OrderBean order;
        private String remarks;
        private String state;
        private String updateDate;
        private String url;
        private String variety;

        public int getAmount() {
            return amount;
        }

        public void setAmount(int amount) {
            this.amount = amount;
        }

        public int getBeginAmount() {
            return beginAmount;
        }

        public void setBeginAmount(int beginAmount) {
            this.beginAmount = beginAmount;
        }

        public int getBeginBonus() {
            return beginBonus;
        }

        public void setBeginBonus(int beginBonus) {
            this.beginBonus = beginBonus;
        }

        public int getBonus() {
            return bonus;
        }

        public void setBonus(int bonus) {
            this.bonus = bonus;
        }

        public String getCreateDate() {
            return createDate;
        }

        public void setCreateDate(String createDate) {
            this.createDate = createDate;
        }

        public DemandBean getDemand() {
            return demand;
        }

        public void setDemand(DemandBean demand) {
            this.demand = demand;
        }

        public int getEndAmount() {
            return endAmount;
        }

        public void setEndAmount(int endAmount) {
            this.endAmount = endAmount;
        }

        public int getEndBonus() {
            return endBonus;
        }

        public void setEndBonus(int endBonus) {
            this.endBonus = endBonus;
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

        public OrderBean getOrder() {
            return order;
        }

        public void setOrder(OrderBean order) {
            this.order = order;
        }

        public String getRemarks() {
            return remarks;
        }

        public void setRemarks(String remarks) {
            this.remarks = remarks;
        }

        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state;
        }

        public String getUpdateDate() {
            return updateDate;
        }

        public void setUpdateDate(String updateDate) {
            this.updateDate = updateDate;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getVariety() {
            return variety;
        }

        public void setVariety(String variety) {
            this.variety = variety;
        }

        public static class DemandBean {
        }

        public static class OrderBean {
            @Override
            public String toString() {
                return "OrderBean{" +
                        "amount=" + amount +
                        ", bonus=" + bonus +
                        ", company=" + company +
                        ", createDate='" + createDate + '\'' +
                        ", customer=" + customer +
                        ", demand=" + demand +
                        ", id='" + id + '\'' +
                        ", invoiceType=" + invoiceType +
                        ", isNewRecord=" + isNewRecord +
                        ", remarks='" + remarks + '\'' +
                        ", state='" + state + '\'' +
                        ", updateDate='" + updateDate + '\'' +
                        ", orderInvoiceList=" + orderInvoiceList +
                        '}';
            }

            /**
             * amount : 0
             * bonus : 0
             * company : {"account":"string","address":"string","createDate":"2017-10-29T12:32:36.248Z","customer":{"amount":0,"beginAmount":0,"beginBirthday":"string","beginBonus":0,"beginFrozen":0,"birthday":"string","bonus":0,"createDate":"2017-10-29T12:32:36.248Z","creditLevel":0,"creditScore":0,"deviceToken":"string","endAmount":0,"endBirthday":"string","endBonus":0,"endFrozen":0,"frequentType":"string","frozen":0,"gender":"string","headimg":"string","id":"string","isNewRecord":true,"nickname":"string","openid":"string","remarks":"string","telephone":"string","updateDate":"2017-10-29T12:32:36.248Z"},"depositBank":"string","id":"string","isDefault":"string","isNewRecord":true,"name":"string","phone":"string","qrcode":"string","remarks":"string","taxno":"string","updateDate":"2017-10-29T12:32:36.248Z"}
             * createDate : 2017-10-29T12:32:36.248Z
             * customer : {"amount":0,"beginAmount":0,"beginBirthday":"string","beginBonus":0,"beginFrozen":0,"birthday":"string","bonus":0,"createDate":"2017-10-29T12:32:36.248Z","creditLevel":0,"creditScore":0,"deviceToken":"string","endAmount":0,"endBirthday":"string","endBonus":0,"endFrozen":0,"frequentType":"string","frozen":0,"gender":"string","headimg":"string","id":"string","isNewRecord":true,"nickname":"string","openid":"string","remarks":"string","telephone":"string","updateDate":"2017-10-29T12:32:36.248Z"}
             * demand : {}
             * id : string
             * invoiceType : {"category":"string","categorySort":0,"createDate":"2017-10-29T12:32:36.248Z","frequentFlag":"string","frequentSort":0,"id":"string","isNewRecord":true,"largeSize":"string","middleSize":"string","name":"string","remarks":"string","smallSize":"string","updateDate":"2017-10-29T12:32:36.248Z"}
             * isNewRecord : true
             * orderInvoiceList : [{}]
             * remarks : string
             * state : string
             * updateDate : 2017-10-29T12:32:36.248Z
             */

            private int amount;
            private int bonus;
            private CompanyBeanX company;
            private String createDate;
            private CustomerBeanXXX customer;
            private DemandBeanX demand;
            private String id;
            private InvoiceTypeBean invoiceType;
            private boolean isNewRecord;
            private String remarks;
            private String state;
            private String updateDate;
            private List<OrderInvoiceListBean> orderInvoiceList;

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

            public CompanyBeanX getCompany() {
                return company;
            }

            public void setCompany(CompanyBeanX company) {
                this.company = company;
            }

            public String getCreateDate() {
                return createDate;
            }

            public void setCreateDate(String createDate) {
                this.createDate = createDate;
            }

            public CustomerBeanXXX getCustomer() {
                return customer;
            }

            public void setCustomer(CustomerBeanXXX customer) {
                this.customer = customer;
            }

            public DemandBeanX getDemand() {
                return demand;
            }

            public void setDemand(DemandBeanX demand) {
                this.demand = demand;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public InvoiceTypeBean getInvoiceType() {
                return invoiceType;
            }

            public void setInvoiceType(InvoiceTypeBean invoiceType) {
                this.invoiceType = invoiceType;
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

            public String getState() {
                return state;
            }

            public void setState(String state) {
                this.state = state;
            }

            public String getUpdateDate() {
                return updateDate;
            }

            public void setUpdateDate(String updateDate) {
                this.updateDate = updateDate;
            }

            public List<OrderInvoiceListBean> getOrderInvoiceList() {
                return orderInvoiceList;
            }

            public void setOrderInvoiceList(List<OrderInvoiceListBean> orderInvoiceList) {
                this.orderInvoiceList = orderInvoiceList;
            }

            public static class CompanyBeanX {
                @Override
                public String toString() {
                    return "CompanyBeanX{" +
                            "account='" + account + '\'' +
                            ", address='" + address + '\'' +
                            ", createDate='" + createDate + '\'' +
                            ", customer=" + customer +
                            ", depositBank='" + depositBank + '\'' +
                            ", id='" + id + '\'' +
                            ", isDefault='" + isDefault + '\'' +
                            ", isNewRecord=" + isNewRecord +
                            ", name='" + name + '\'' +
                            ", phone='" + phone + '\'' +
                            ", qrcode='" + qrcode + '\'' +
                            ", remarks='" + remarks + '\'' +
                            ", taxno='" + taxno + '\'' +
                            ", updateDate='" + updateDate + '\'' +
                            '}';
                }

                /**
                 * account : string
                 * address : string
                 * createDate : 2017-10-29T12:32:36.248Z
                 * customer : {"amount":0,"beginAmount":0,"beginBirthday":"string","beginBonus":0,"beginFrozen":0,"birthday":"string","bonus":0,"createDate":"2017-10-29T12:32:36.248Z","creditLevel":0,"creditScore":0,"deviceToken":"string","endAmount":0,"endBirthday":"string","endBonus":0,"endFrozen":0,"frequentType":"string","frozen":0,"gender":"string","headimg":"string","id":"string","isNewRecord":true,"nickname":"string","openid":"string","remarks":"string","telephone":"string","updateDate":"2017-10-29T12:32:36.248Z"}
                 * depositBank : string
                 * id : string
                 * isDefault : string
                 * isNewRecord : true
                 * name : string
                 * phone : string
                 * qrcode : string
                 * remarks : string
                 * taxno : string
                 * updateDate : 2017-10-29T12:32:36.248Z
                 */

                private String account;
                private String address;
                private String createDate;
                private CustomerBeanXX customer;
                private String depositBank;
                private String id;
                private String isDefault;
                private boolean isNewRecord;
                private String name;
                private String phone;
                private String qrcode;
                private String remarks;
                private String taxno;
                private String updateDate;

                public String getAccount() {
                    return account;
                }

                public void setAccount(String account) {
                    this.account = account;
                }

                public String getAddress() {
                    return address;
                }

                public void setAddress(String address) {
                    this.address = address;
                }

                public String getCreateDate() {
                    return createDate;
                }

                public void setCreateDate(String createDate) {
                    this.createDate = createDate;
                }

                public CustomerBeanXX getCustomer() {
                    return customer;
                }

                public void setCustomer(CustomerBeanXX customer) {
                    this.customer = customer;
                }

                public String getDepositBank() {
                    return depositBank;
                }

                public void setDepositBank(String depositBank) {
                    this.depositBank = depositBank;
                }

                public String getId() {
                    return id;
                }

                public void setId(String id) {
                    this.id = id;
                }

                public String getIsDefault() {
                    return isDefault;
                }

                public void setIsDefault(String isDefault) {
                    this.isDefault = isDefault;
                }

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

                public String getPhone() {
                    return phone;
                }

                public void setPhone(String phone) {
                    this.phone = phone;
                }

                public String getQrcode() {
                    return qrcode;
                }

                public void setQrcode(String qrcode) {
                    this.qrcode = qrcode;
                }

                public String getRemarks() {
                    return remarks;
                }

                public void setRemarks(String remarks) {
                    this.remarks = remarks;
                }

                public String getTaxno() {
                    return taxno;
                }

                public void setTaxno(String taxno) {
                    this.taxno = taxno;
                }

                public String getUpdateDate() {
                    return updateDate;
                }

                public void setUpdateDate(String updateDate) {
                    this.updateDate = updateDate;
                }

                public static class CustomerBeanXX {
                    @Override
                    public String toString() {
                        return "CustomerBeanXX{" +
                                "amount=" + amount +
                                ", beginAmount=" + beginAmount +
                                ", beginBirthday='" + beginBirthday + '\'' +
                                ", beginBonus=" + beginBonus +
                                ", beginFrozen=" + beginFrozen +
                                ", birthday='" + birthday + '\'' +
                                ", bonus=" + bonus +
                                ", createDate='" + createDate + '\'' +
                                ", creditLevel=" + creditLevel +
                                ", creditScore=" + creditScore +
                                ", deviceToken='" + deviceToken + '\'' +
                                ", endAmount=" + endAmount +
                                ", endBirthday='" + endBirthday + '\'' +
                                ", endBonus=" + endBonus +
                                ", endFrozen=" + endFrozen +
                                ", frequentType='" + frequentType + '\'' +
                                ", frozen=" + frozen +
                                ", gender='" + gender + '\'' +
                                ", headimg='" + headimg + '\'' +
                                ", id='" + id + '\'' +
                                ", isNewRecord=" + isNewRecord +
                                ", nickname='" + nickname + '\'' +
                                ", openid='" + openid + '\'' +
                                ", remarks='" + remarks + '\'' +
                                ", telephone='" + telephone + '\'' +
                                ", updateDate='" + updateDate + '\'' +
                                '}';
                    }

                    /**
                     * amount : 0
                     * beginAmount : 0
                     * beginBirthday : string
                     * beginBonus : 0
                     * beginFrozen : 0
                     * birthday : string
                     * bonus : 0
                     * createDate : 2017-10-29T12:32:36.248Z
                     * creditLevel : 0
                     * creditScore : 0
                     * deviceToken : string
                     * endAmount : 0
                     * endBirthday : string
                     * endBonus : 0
                     * endFrozen : 0
                     * frequentType : string
                     * frozen : 0
                     * gender : string
                     * headimg : string
                     * id : string
                     * isNewRecord : true
                     * nickname : string
                     * openid : string
                     * remarks : string
                     * telephone : string
                     * updateDate : 2017-10-29T12:32:36.248Z
                     */

                    private int amount;
                    private int beginAmount;
                    private String beginBirthday;
                    private int beginBonus;
                    private int beginFrozen;
                    private String birthday;
                    private int bonus;
                    private String createDate;
                    private int creditLevel;
                    private int creditScore;
                    private String deviceToken;
                    private int endAmount;
                    private String endBirthday;
                    private int endBonus;
                    private int endFrozen;
                    private String frequentType;
                    private int frozen;
                    private String gender;
                    private String headimg;
                    private String id;
                    private boolean isNewRecord;
                    private String nickname;
                    private String openid;
                    private String remarks;
                    private String telephone;
                    private String updateDate;

                    public int getAmount() {
                        return amount;
                    }

                    public void setAmount(int amount) {
                        this.amount = amount;
                    }

                    public int getBeginAmount() {
                        return beginAmount;
                    }

                    public void setBeginAmount(int beginAmount) {
                        this.beginAmount = beginAmount;
                    }

                    public String getBeginBirthday() {
                        return beginBirthday;
                    }

                    public void setBeginBirthday(String beginBirthday) {
                        this.beginBirthday = beginBirthday;
                    }

                    public int getBeginBonus() {
                        return beginBonus;
                    }

                    public void setBeginBonus(int beginBonus) {
                        this.beginBonus = beginBonus;
                    }

                    public int getBeginFrozen() {
                        return beginFrozen;
                    }

                    public void setBeginFrozen(int beginFrozen) {
                        this.beginFrozen = beginFrozen;
                    }

                    public String getBirthday() {
                        return birthday;
                    }

                    public void setBirthday(String birthday) {
                        this.birthday = birthday;
                    }

                    public int getBonus() {
                        return bonus;
                    }

                    public void setBonus(int bonus) {
                        this.bonus = bonus;
                    }

                    public String getCreateDate() {
                        return createDate;
                    }

                    public void setCreateDate(String createDate) {
                        this.createDate = createDate;
                    }

                    public int getCreditLevel() {
                        return creditLevel;
                    }

                    public void setCreditLevel(int creditLevel) {
                        this.creditLevel = creditLevel;
                    }

                    public int getCreditScore() {
                        return creditScore;
                    }

                    public void setCreditScore(int creditScore) {
                        this.creditScore = creditScore;
                    }

                    public String getDeviceToken() {
                        return deviceToken;
                    }

                    public void setDeviceToken(String deviceToken) {
                        this.deviceToken = deviceToken;
                    }

                    public int getEndAmount() {
                        return endAmount;
                    }

                    public void setEndAmount(int endAmount) {
                        this.endAmount = endAmount;
                    }

                    public String getEndBirthday() {
                        return endBirthday;
                    }

                    public void setEndBirthday(String endBirthday) {
                        this.endBirthday = endBirthday;
                    }

                    public int getEndBonus() {
                        return endBonus;
                    }

                    public void setEndBonus(int endBonus) {
                        this.endBonus = endBonus;
                    }

                    public int getEndFrozen() {
                        return endFrozen;
                    }

                    public void setEndFrozen(int endFrozen) {
                        this.endFrozen = endFrozen;
                    }

                    public String getFrequentType() {
                        return frequentType;
                    }

                    public void setFrequentType(String frequentType) {
                        this.frequentType = frequentType;
                    }

                    public int getFrozen() {
                        return frozen;
                    }

                    public void setFrozen(int frozen) {
                        this.frozen = frozen;
                    }

                    public String getGender() {
                        return gender;
                    }

                    public void setGender(String gender) {
                        this.gender = gender;
                    }

                    public String getHeadimg() {
                        return headimg;
                    }

                    public void setHeadimg(String headimg) {
                        this.headimg = headimg;
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

                    public String getNickname() {
                        return nickname;
                    }

                    public void setNickname(String nickname) {
                        this.nickname = nickname;
                    }

                    public String getOpenid() {
                        return openid;
                    }

                    public void setOpenid(String openid) {
                        this.openid = openid;
                    }

                    public String getRemarks() {
                        return remarks;
                    }

                    public void setRemarks(String remarks) {
                        this.remarks = remarks;
                    }

                    public String getTelephone() {
                        return telephone;
                    }

                    public void setTelephone(String telephone) {
                        this.telephone = telephone;
                    }

                    public String getUpdateDate() {
                        return updateDate;
                    }

                    public void setUpdateDate(String updateDate) {
                        this.updateDate = updateDate;
                    }
                }
            }

            public static class CustomerBeanXXX {
            }

            public static class DemandBeanX {
            }

            public static class InvoiceTypeBean {
            }

            public static class OrderInvoiceListBean {
            }
        }
    }

    public static class DemandInvoiceTypeListBean {
        /**
         * createDate : 2017-10-29T12:32:36.247Z
         * demand : {}
         * id : string
         * invoiceType : {"category":"string","categorySort":0,"createDate":"2017-10-29T12:32:36.247Z","frequentFlag":"string","frequentSort":0,"id":"string","isNewRecord":true,"largeSize":"string","middleSize":"string","name":"string","remarks":"string","smallSize":"string","updateDate":"2017-10-29T12:32:36.247Z"}
         * isNewRecord : true
         * remarks : string
         * updateDate : 2017-10-29T12:32:36.247Z
         */

        private String createDate;

        @Override
        public String toString() {
            return "DemandInvoiceTypeListBean{" +
                    "createDate='" + createDate + '\'' +
                    ", demand=" + demand +
                    ", id='" + id + '\'' +
                    ", invoiceType=" + invoiceType +
                    ", isNewRecord=" + isNewRecord +
                    ", remarks='" + remarks + '\'' +
                    ", updateDate='" + updateDate + '\'' +
                    '}';
        }

        private DemandBeanXX demand;
        private String id;
        private InvoiceTypeBeanX invoiceType;
        private boolean isNewRecord;
        private String remarks;
        private String updateDate;

        public String getCreateDate() {
            return createDate;
        }

        public void setCreateDate(String createDate) {
            this.createDate = createDate;
        }

        public DemandBeanXX getDemand() {
            return demand;
        }

        public void setDemand(DemandBeanXX demand) {
            this.demand = demand;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public InvoiceTypeBeanX getInvoiceType() {
            return invoiceType;
        }

        public void setInvoiceType(InvoiceTypeBeanX invoiceType) {
            this.invoiceType = invoiceType;
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

        public String getUpdateDate() {
            return updateDate;
        }

        public void setUpdateDate(String updateDate) {
            this.updateDate = updateDate;
        }

        public static class DemandBeanXX {
        }

        public static class InvoiceTypeBeanX {
            /**
             * category : string
             * categorySort : 0
             * createDate : 2017-10-29T12:32:36.247Z
             * frequentFlag : string
             * frequentSort : 0
             * id : string
             * isNewRecord : true
             * largeSize : string
             * middleSize : string
             * name : string
             * remarks : string
             * smallSize : string
             * updateDate : 2017-10-29T12:32:36.247Z
             */

            private String category;
            private int categorySort;
            private String createDate;
            private String frequentFlag;
            private int frequentSort;
            private String id;
            private boolean isNewRecord;
            private String largeSize;
            private String middleSize;
            private String name;
            private String remarks;

            @Override
            public String toString() {
                return "InvoiceTypeBeanX{" +
                        "category='" + category + '\'' +
                        ", categorySort=" + categorySort +
                        ", createDate='" + createDate + '\'' +
                        ", frequentFlag='" + frequentFlag + '\'' +
                        ", frequentSort=" + frequentSort +
                        ", id='" + id + '\'' +
                        ", isNewRecord=" + isNewRecord +
                        ", largeSize='" + largeSize + '\'' +
                        ", middleSize='" + middleSize + '\'' +
                        ", name='" + name + '\'' +
                        ", remarks='" + remarks + '\'' +
                        ", smallSize='" + smallSize + '\'' +
                        ", updateDate='" + updateDate + '\'' +
                        '}';
            }

            private String smallSize;
            private String updateDate;

            public String getCategory() {
                return category;
            }

            public void setCategory(String category) {
                this.category = category;
            }

            public int getCategorySort() {
                return categorySort;
            }

            public void setCategorySort(int categorySort) {
                this.categorySort = categorySort;
            }

            public String getCreateDate() {
                return createDate;
            }

            public void setCreateDate(String createDate) {
                this.createDate = createDate;
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

            public String getLargeSize() {
                return largeSize;
            }

            public void setLargeSize(String largeSize) {
                this.largeSize = largeSize;
            }

            public String getMiddleSize() {
                return middleSize;
            }

            public void setMiddleSize(String middleSize) {
                this.middleSize = middleSize;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getRemarks() {
                return remarks;
            }

            public void setRemarks(String remarks) {
                this.remarks = remarks;
            }

            public String getSmallSize() {
                return smallSize;
            }

            public void setSmallSize(String smallSize) {
                this.smallSize = smallSize;
            }

            public String getUpdateDate() {
                return updateDate;
            }

            public void setUpdateDate(String updateDate) {
                this.updateDate = updateDate;
            }
        }
    }
}
