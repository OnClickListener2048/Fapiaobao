package com.pilipa.fapiaobao.net.bean.publish;

/**
 * Created by edz on 2017/10/29.
 */

public class DemandDetails {

    /**
     * status : 200
     * msg : OK
     * data : {"isNewRecord":true,"remarks":"","createDate":"2017-10-29 19:44:27","updateDate":"2017-10-29 19:44:27","company":{"isNewRecord":true,"name":"天津爱康鼎科技有限公司","taxno":"320400137674452","address":"天津市红桥区海河华鼎大厦","phone":"022-12554551","depositBank":"中国民生银行天津分行","account":"55555555555555555","qrcode":"www.sdsadsasd.dsada","isDefault":"1"},"demandPostage":{"isNewRecord":true,"receiver":"朱宏超","province":"内蒙","city":"赤峰","district":"阿发","address":"西撒的撒","email":"fasf564f@qq.com"},"invoiceVarieties":"1","areaRestrict":"s","province":"saasd","city":"sdsa","deadline":"2017-10-29 00:00:00","totalAmount":1000,"leftAmount":10,"totalBonus":10,"leftBonus":1,"mailMinimum":10,"attentions":"1232131","state":"1","closeDate":"2017-10-29 00:00:00"}
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
         * isNewRecord : true
         * remarks :
         * createDate : 2017-10-29 19:44:27
         * updateDate : 2017-10-29 19:44:27
         * company : {"isNewRecord":true,"name":"天津爱康鼎科技有限公司","taxno":"320400137674452","address":"天津市红桥区海河华鼎大厦","phone":"022-12554551","depositBank":"中国民生银行天津分行","account":"55555555555555555","qrcode":"www.sdsadsasd.dsada","isDefault":"1"}
         * demandPostage : {"isNewRecord":true,"receiver":"朱宏超","province":"内蒙","city":"赤峰","district":"阿发","address":"西撒的撒","email":"fasf564f@qq.com"}
         * invoiceVarieties : 1
         * areaRestrict : s
         * province : saasd
         * city : sdsa
         * deadline : 2017-10-29 00:00:00
         * totalAmount : 1000
         * leftAmount : 10
         * totalBonus : 10
         * leftBonus : 1
         * mailMinimum : 10
         * attentions : 1232131
         * state : 1
         * closeDate : 2017-10-29 00:00:00
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
        private double totalAmount;
        private double leftAmount;
        private double totalBonus;
        private double leftBonus;
        private double mailMinimum;
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
}
