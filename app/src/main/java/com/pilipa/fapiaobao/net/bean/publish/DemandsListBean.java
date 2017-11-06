package com.pilipa.fapiaobao.net.bean.publish;

import java.util.List;

/**
 * Created by edz on 2017/10/29.
 */

public class DemandsListBean {


    /**
     * status : 200
     * msg : OK
     * data : [{"id":"a4e62566ecda49cfb4e1434ab56f0d79","isNewRecord":false,"createDate":"2017-11-01 12:17:44","updateDate":"2017-11-05 15:24:38","company":{"id":"fa5c505adc8b491a9b49cf3bd2a741ec","isNewRecord":false,"name":"string"},"customer":{"id":"91f5fa30a8f64d62a6bd17baaa14645d","isNewRecord":false},"demandPostage":{"id":"552e5f9a40534afb968cec4259f62af3","isNewRecord":false},"invoiceVarieties":"1,2,3","areaRestrict":"1","province":"string","city":"天津市","deadline":"2018-11-16","totalAmount":1000,"leftAmount":311,"totalBonus":100,"leftBonus":31.1,"mailMinimum":0,"attentions":"请务必上传真实发票","state":"0","receivedAmount":689,"leftDate":374},{"id":"3a89fd54d244485ca08b5c3b42401688","isNewRecord":false,"createDate":"2017-11-01 11:52:32","updateDate":"2017-11-01 11:52:32","company":{"id":"d56165d958724fa38185a15e81caa054","isNewRecord":false,"name":"string"},"customer":{"id":"91f5fa30a8f64d62a6bd17baaa14645d","isNewRecord":false},"demandPostage":{"id":"1da7ae6e98df49ebb101340d470084e","isNewRecord":false},"invoiceVarieties":"1,2,3","areaRestrict":"1","province":"","city":"天津市","deadline":"2018-11-16","totalAmount":1000,"leftAmount":1010,"totalBonus":100,"leftBonus":110,"mailMinimum":0,"attentions":"放在门卫就行","state":"0","receivedAmount":-10,"leftDate":374},{"id":"6e398531b9374c4683eb94078eea96cf","isNewRecord":false,"createDate":"2017-11-01 11:43:50","updateDate":"2017-11-03 17:30:56","company":{"id":"d56165d958724fa38185a15e81caa054","isNewRecord":false,"name":"string"},"customer":{"id":"91f5fa30a8f64d62a6bd17baaa14645d","isNewRecord":false},"demandPostage":{"id":"33d874dce5444ba3a94165d787275297","isNewRecord":false},"invoiceVarieties":"1,2,3","areaRestrict":"1","province":"string","city":"天津市","deadline":"2018-11-16","totalAmount":1000,"leftAmount":218,"totalBonus":100,"leftBonus":21.8,"mailMinimum":0,"attentions":"票要干净,不能折损","state":"0","receivedAmount":782,"leftDate":374}]
     */

    private int status;
    private String msg;
    private List<DataBean> data;

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

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : a4e62566ecda49cfb4e1434ab56f0d79
         * isNewRecord : false
         * createDate : 2017-11-01 12:17:44
         * updateDate : 2017-11-05 15:24:38
         * company : {"id":"fa5c505adc8b491a9b49cf3bd2a741ec","isNewRecord":false,"name":"string"}
         * customer : {"id":"91f5fa30a8f64d62a6bd17baaa14645d","isNewRecord":false}
         * demandPostage : {"id":"552e5f9a40534afb968cec4259f62af3","isNewRecord":false}
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
         * receivedAmount : 689
         * leftDate : 374
         */

        private String id;
        private boolean isNewRecord;
        private String createDate;
        private String updateDate;
        private CompanyBean company;
        private CustomerBean customer;
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
        private double receivedAmount;
        private int leftDate;

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

        public CompanyBean getCompany() {
            return company;
        }

        public void setCompany(CompanyBean company) {
            this.company = company;
        }

        public CustomerBean getCustomer() {
            return customer;
        }

        public void setCustomer(CustomerBean customer) {
            this.customer = customer;
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

        public double getReceivedAmount() {
            return receivedAmount;
        }

        public void setReceivedAmount(double receivedAmount) {
            this.receivedAmount = receivedAmount;
        }

        public int getLeftDate() {
            return leftDate;
        }

        public void setLeftDate(int leftDate) {
            this.leftDate = leftDate;
        }

        public static class CompanyBean {
            /**
             * id : fa5c505adc8b491a9b49cf3bd2a741ec
             * isNewRecord : false
             * name : string
             */

            private String id;
            private boolean isNewRecord;
            private String name;

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

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }
        }

        public static class CustomerBean {
            /**
             * id : 91f5fa30a8f64d62a6bd17baaa14645d
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

        public static class DemandPostageBean {
            /**
             * id : 552e5f9a40534afb968cec4259f62af3
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
