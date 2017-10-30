package com.pilipa.fapiaobao.net.bean.me;

import java.util.List;

/**
 * Created by edz on 2017/10/29.
 */

public class OrderListBean {

    /**
     * status : 200
     * msg : OK
     * data : [{"id":"1","isNewRecord":false,"createDate":"2017-10-30 14:59:01","updateDate":"2017-10-25 14:59:08","demand":{"id":"b0b7dc32986c4d31a4e79b2a4c7ed7b6","isNewRecord":false},"customer":{"id":"6ee15c894b1a435d9c24025b324e17f7","isNewRecord":false},"company":{"id":"1d660812a99e4b439cdcb4ed9946b271","isNewRecord":false},"invoiceType":{"id":"b207f5257cd948bb8f7176eede36b583","isNewRecord":false,"name":"地铁票"},"amount":200,"bonus":20,"state":"0"},{"id":"2","isNewRecord":false,"createDate":"2017-10-26 14:59:04","updateDate":"2017-10-30 14:59:11","demand":{"id":"b0b7dc32986c4d31a4e79b2a4c7ed7b6","isNewRecord":false},"customer":{"id":"6ee15c894b1a435d9c24025b324e17f7","isNewRecord":false},"company":{"id":"1d660812a99e4b439cdcb4ed9946b271","isNewRecord":false},"invoiceType":{"id":"b207f5257cd948bb8f7176eede36b583","isNewRecord":false,"name":"地铁票"},"amount":300,"bonus":30,"state":"0"}]
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
         * id : 1
         * isNewRecord : false
         * createDate : 2017-10-30 14:59:01
         * updateDate : 2017-10-25 14:59:08
         * demand : {"id":"b0b7dc32986c4d31a4e79b2a4c7ed7b6","isNewRecord":false}
         * customer : {"id":"6ee15c894b1a435d9c24025b324e17f7","isNewRecord":false}
         * company : {"id":"1d660812a99e4b439cdcb4ed9946b271","isNewRecord":false}
         * invoiceType : {"id":"b207f5257cd948bb8f7176eede36b583","isNewRecord":false,"name":"地铁票"}
         * amount : 200
         * bonus : 20
         * state : 0
         */

        private String id;
        private boolean isNewRecord;
        private String createDate;
        private String updateDate;
        private DemandBean demand;
        private CustomerBean customer;
        private CompanyBean company;
        private InvoiceTypeBean invoiceType;
        private int amount;
        private int bonus;
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

        public DemandBean getDemand() {
            return demand;
        }

        public void setDemand(DemandBean demand) {
            this.demand = demand;
        }

        public CustomerBean getCustomer() {
            return customer;
        }

        public void setCustomer(CustomerBean customer) {
            this.customer = customer;
        }

        public CompanyBean getCompany() {
            return company;
        }

        public void setCompany(CompanyBean company) {
            this.company = company;
        }

        public InvoiceTypeBean getInvoiceType() {
            return invoiceType;
        }

        public void setInvoiceType(InvoiceTypeBean invoiceType) {
            this.invoiceType = invoiceType;
        }

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

        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state;
        }

        public static class DemandBean {
            /**
             * id : b0b7dc32986c4d31a4e79b2a4c7ed7b6
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

        public static class CompanyBean {
            /**
             * id : 1d660812a99e4b439cdcb4ed9946b271
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

        public static class InvoiceTypeBean {
            /**
             * id : b207f5257cd948bb8f7176eede36b583
             * isNewRecord : false
             * name : 地铁票
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
    }
}
