package com.pilipa.fapiaobao.net.bean.me.demandlist;


import java.util.List;

/**
 * Created by edz on 2018/3/14.
 */

public class DemandListItem {

    /**
     * name : 天津爱维医院
     * list : [{"id":"0c93cd2754634f718c11379ba8ab8aa4","isNewRecord":false,"remarks":"无","createDate":"2018-03-12 17:44:47","updateDate":"2018-03-12 17:44:47","company":{"id":"d6c2b5aa921c4bffb3196de684e911b5","isNewRecord":false,"name":"天津爱维医院"},"customer":{"id":"d7fd32bbeac1473db8ca2130cb6a4ac7","isNewRecord":false,"amount":0,"bonus":0,"frozen":0,"creditScore":0,"creditLevel":0,"beginAmount":0,"endAmount":0,"beginBonus":0,"endBonus":0,"beginFrozen":0,"endFrozen":0},"demandPostage":{"id":"45eb3f0074c840bcbe4237ca59fd9d58","isNewRecord":false},"invoiceVarieties":"3","areaRestrict":"0","deadline":"2018-03-26","totalAmount":1000,"leftAmount":1000,"totalBonus":0,"leftBonus":0,"mailMinimum":0,"attentions":"4亿嘻嘻","state":"0","beginTotalAmount":0,"endTotalAmount":0,"beginLeftAmount":0,"endLeftAmount":0,"beginTotalBonus":0,"endTotalBonus":0,"beginLeftBonus":0,"endLeftBonus":0,"beginMailMinimum":0,"endMailMinimum":0,"receivedAmount":0,"leftDate":11}]
     */

    private String name;
    private List<ListBean> list;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ListBean {
        /**
         * id : 0c93cd2754634f718c11379ba8ab8aa4
         * isNewRecord : false
         * remarks : 无
         * createDate : 2018-03-12 17:44:47
         * updateDate : 2018-03-12 17:44:47
         * company : {"id":"d6c2b5aa921c4bffb3196de684e911b5","isNewRecord":false,"name":"天津爱维医院"}
         * customer : {"id":"d7fd32bbeac1473db8ca2130cb6a4ac7","isNewRecord":false,"amount":0,"bonus":0,"frozen":0,"creditScore":0,"creditLevel":0,"beginAmount":0,"endAmount":0,"beginBonus":0,"endBonus":0,"beginFrozen":0,"endFrozen":0}
         * demandPostage : {"id":"45eb3f0074c840bcbe4237ca59fd9d58","isNewRecord":false}
         * invoiceVarieties : 3
         * areaRestrict : 0
         * deadline : 2018-03-26
         * totalAmount : 1000
         * leftAmount : 1000
         * totalBonus : 0
         * leftBonus : 0
         * mailMinimum : 0
         * attentions : 4亿嘻嘻
         * state : 0
         * beginTotalAmount : 0
         * endTotalAmount : 0
         * beginLeftAmount : 0
         * endLeftAmount : 0
         * beginTotalBonus : 0
         * endTotalBonus : 0
         * beginLeftBonus : 0
         * endLeftBonus : 0
         * beginMailMinimum : 0
         * endMailMinimum : 0
         * receivedAmount : 0
         * leftDate : 11
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
        private String deadline;
        private double totalAmount;
        private double leftAmount;
        private double totalBonus;
        private double leftBonus;
        private double mailMinimum;
        private String attentions;
        private String state;
        private double beginTotalAmount;
        private double endTotalAmount;
        private double beginLeftAmount;
        private double endLeftAmount;
        private double beginTotalBonus;
        private double endTotalBonus;
        private double beginLeftBonus;
        private double endLeftBonus;
        private double beginMailMinimum;
        private double endMailMinimum;
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

        public String getDeadline() {
            return deadline;
        }

        public void setDeadline(String deadline) {
            this.deadline = deadline;
        }

        public double getTotalAmount() {
            return totalAmount;
        }

        public void setTotalAmount(int totalAmount) {
            this.totalAmount = totalAmount;
        }

        public double getLeftAmount() {
            return leftAmount;
        }

        public void setLeftAmount(int leftAmount) {
            this.leftAmount = leftAmount;
        }

        public double getTotalBonus() {
            return totalBonus;
        }

        public void setTotalBonus(int totalBonus) {
            this.totalBonus = totalBonus;
        }

        public double getLeftBonus() {
            return leftBonus;
        }

        public void setLeftBonus(int leftBonus) {
            this.leftBonus = leftBonus;
        }

        public double getMailMinimum() {
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

        public double getBeginTotalAmount() {
            return beginTotalAmount;
        }

        public void setBeginTotalAmount(int beginTotalAmount) {
            this.beginTotalAmount = beginTotalAmount;
        }

        public double getEndTotalAmount() {
            return endTotalAmount;
        }

        public void setEndTotalAmount(int endTotalAmount) {
            this.endTotalAmount = endTotalAmount;
        }

        public double getBeginLeftAmount() {
            return beginLeftAmount;
        }

        public void setBeginLeftAmount(int beginLeftAmount) {
            this.beginLeftAmount = beginLeftAmount;
        }

        public double getEndLeftAmount() {
            return endLeftAmount;
        }

        public void setEndLeftAmount(int endLeftAmount) {
            this.endLeftAmount = endLeftAmount;
        }

        public double getBeginTotalBonus() {
            return beginTotalBonus;
        }

        public void setBeginTotalBonus(int beginTotalBonus) {
            this.beginTotalBonus = beginTotalBonus;
        }

        public double getEndTotalBonus() {
            return endTotalBonus;
        }

        public void setEndTotalBonus(int endTotalBonus) {
            this.endTotalBonus = endTotalBonus;
        }

        public double getBeginLeftBonus() {
            return beginLeftBonus;
        }

        public void setBeginLeftBonus(int beginLeftBonus) {
            this.beginLeftBonus = beginLeftBonus;
        }

        public double getEndLeftBonus() {
            return endLeftBonus;
        }

        public void setEndLeftBonus(int endLeftBonus) {
            this.endLeftBonus = endLeftBonus;
        }

        public double getBeginMailMinimum() {
            return beginMailMinimum;
        }

        public void setBeginMailMinimum(int beginMailMinimum) {
            this.beginMailMinimum = beginMailMinimum;
        }

        public double getEndMailMinimum() {
            return endMailMinimum;
        }

        public void setEndMailMinimum(int endMailMinimum) {
            this.endMailMinimum = endMailMinimum;
        }

        public double getReceivedAmount() {
            return receivedAmount;
        }

        public void setReceivedAmount(int receivedAmount) {
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
             * id : d6c2b5aa921c4bffb3196de684e911b5
             * isNewRecord : false
             * name : 天津爱维医院
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
             * id : d7fd32bbeac1473db8ca2130cb6a4ac7
             * isNewRecord : false
             * amount : 0
             * bonus : 0
             * frozen : 0
             * creditScore : 0
             * creditLevel : 0
             * beginAmount : 0
             * endAmount : 0
             * beginBonus : 0
             * endBonus : 0
             * beginFrozen : 0
             * endFrozen : 0
             */

            private String id;
            private boolean isNewRecord;
            private double amount;
            private double bonus;
            private double frozen;
            private double creditScore;
            private double creditLevel;
            private double beginAmount;
            private double endAmount;
            private double beginBonus;
            private double endBonus;
            private double beginFrozen;
            private double endFrozen;

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

            public double getAmount() {
                return amount;
            }

            public void setAmount(int amount) {
                this.amount = amount;
            }

            public double getBonus() {
                return bonus;
            }

            public void setBonus(int bonus) {
                this.bonus = bonus;
            }

            public double getFrozen() {
                return frozen;
            }

            public void setFrozen(int frozen) {
                this.frozen = frozen;
            }

            public double getCreditScore() {
                return creditScore;
            }

            public void setCreditScore(int creditScore) {
                this.creditScore = creditScore;
            }

            public double getCreditLevel() {
                return creditLevel;
            }

            public void setCreditLevel(int creditLevel) {
                this.creditLevel = creditLevel;
            }

            public double getBeginAmount() {
                return beginAmount;
            }

            public void setBeginAmount(int beginAmount) {
                this.beginAmount = beginAmount;
            }

            public double getEndAmount() {
                return endAmount;
            }

            public void setEndAmount(int endAmount) {
                this.endAmount = endAmount;
            }

            public double getBeginBonus() {
                return beginBonus;
            }

            public void setBeginBonus(int beginBonus) {
                this.beginBonus = beginBonus;
            }

            public double getEndBonus() {
                return endBonus;
            }

            public void setEndBonus(int endBonus) {
                this.endBonus = endBonus;
            }

            public double getBeginFrozen() {
                return beginFrozen;
            }

            public void setBeginFrozen(int beginFrozen) {
                this.beginFrozen = beginFrozen;
            }

            public double getEndFrozen() {
                return endFrozen;
            }

            public void setEndFrozen(int endFrozen) {
                this.endFrozen = endFrozen;
            }
        }

        public static class DemandPostageBean {
            /**
             * id : 45eb3f0074c840bcbe4237ca59fd9d58
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

