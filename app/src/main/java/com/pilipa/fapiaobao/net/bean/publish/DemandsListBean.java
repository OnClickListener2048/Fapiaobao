package com.pilipa.fapiaobao.net.bean.publish;

import java.util.List;

/**
 * Created by edz on 2017/10/29.
 */

public class DemandsListBean {

    /**
     * status : 200
     * msg : OK
     * data : [{"id":"b0b7dc32986c4d31a4e79b2a4c7ed7b6","isNewRecord":false,"remarks":"","createDate":"2017-10-29 19:44:27","updateDate":"2017-10-29 19:44:27","company":{"id":"e73859da2a4448069bd9f85887c890d2","isNewRecord":false,"name":"天津爱康鼎科技有限公司"},"customer":{"id":"6ee15c894b1a435d9c24025b324e17f7","isNewRecord":false},"demandPostage":{"id":"2a5535d08e1345bbae823dfefbb4f770","isNewRecord":false},"invoiceVarieties":"1","areaRestrict":"s","province":"saasd","city":"sdsa","deadline":"2017-10-29 00:00:00","totalAmount":1000,"leftAmount":10,"totalBonus":10,"leftBonus":1,"mailMinimum":10,"attentions":"1232131","state":"1","closeDate":"2017-10-29 00:00:00"},{"id":"b0b7dc32986c4d31a4e79b2a4c7ed7b7","isNewRecord":false,"remarks":"","createDate":"2017-10-29 19:44:27","updateDate":"2017-10-29 19:44:27","company":{"id":"e73859da2a4448069bd9f85887c890d2","isNewRecord":false,"name":"天津爱康鼎科技有限公司"},"customer":{"id":"6ee15c894b1a435d9c24025b324e17f7","isNewRecord":false},"demandPostage":{"id":"2a5535d08e1345bbae823dfefbb4f770","isNewRecord":false},"invoiceVarieties":"1","areaRestrict":"s","province":"saasd","city":"sdsa","deadline":"2017-10-29 00:00:00","totalAmount":1000,"leftAmount":10,"totalBonus":10,"leftBonus":1,"mailMinimum":10,"attentions":"1232131","state":"1","closeDate":"2017-10-29 00:00:00"},{"id":"b0b7dc32986c4d31a4e79b2a4c7ed7b9","isNewRecord":false,"remarks":"","createDate":"2017-10-29 19:44:27","updateDate":"2017-10-29 19:44:27","company":{"id":"e73859da2a4448069bd9f85887c890d2","isNewRecord":false,"name":"天津爱康鼎科技有限公司"},"customer":{"id":"6ee15c894b1a435d9c24025b324e17f7","isNewRecord":false},"demandPostage":{"id":"2a5535d08e1345bbae823dfefbb4f770","isNewRecord":false},"invoiceVarieties":"1","areaRestrict":"s","province":"saasd","city":"sdsa","deadline":"2017-10-29 00:00:00","totalAmount":1000,"leftAmount":10,"totalBonus":10,"leftBonus":1,"mailMinimum":10,"attentions":"1232131","state":"1","closeDate":"2017-10-29 00:00:00"}]
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
         * id : b0b7dc32986c4d31a4e79b2a4c7ed7b6
         * isNewRecord : false
         * remarks :
         * createDate : 2017-10-29 19:44:27
         * updateDate : 2017-10-29 19:44:27
         * company : {"id":"e73859da2a4448069bd9f85887c890d2","isNewRecord":false,"name":"天津爱康鼎科技有限公司"}
         * customer : {"id":"6ee15c894b1a435d9c24025b324e17f7","isNewRecord":false}
         * demandPostage : {"id":"2a5535d08e1345bbae823dfefbb4f770","isNewRecord":false}
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

        private String id;
        private boolean isNewRecord;
        private String remarks;
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
        private int totalAmount;
        private int leftAmount;
        private int totalBonus;
        private int leftBonus;
        private int mailMinimum;
        private String attentions;
        private String state;
        private String closeDate;

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
             * id : e73859da2a4448069bd9f85887c890d2
             * isNewRecord : false
             * name : 天津爱康鼎科技有限公司
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
             * id : 6ee15c894b1a435d9c24025b324e17f7
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
             * id : 2a5535d08e1345bbae823dfefbb4f770
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
