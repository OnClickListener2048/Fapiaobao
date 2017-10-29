package com.pilipa.fapiaobao.net.bean.me;

import java.util.List;

/**
 * Created by edz on 2017/10/29.
 */

public class OrderListBean {

    /**
     * status : 200
     * msg : OK
     * data : [{"id":"1","isNewRecord":false,"demand":{"id":"1","isNewRecord":false},"customer":{"id":"3","isNewRecord":false},"amount":200,"bonus":20}]
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
         * demand : {"id":"1","isNewRecord":false}
         * customer : {"id":"3","isNewRecord":false}
         * amount : 200
         * bonus : 20
         */

        private String id;
        private boolean isNewRecord;
        private DemandBean demand;
        private CustomerBean customer;
        private int amount;
        private int bonus;

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

        public static class DemandBean {
            /**
             * id : 1
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
             * id : 3
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
