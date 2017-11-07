package com.pilipa.fapiaobao.net.bean.me;

/**
 * Created by edz on 2017/10/30.
 */

public class RejectInvoiceBean {


    /**
     * status : 200
     * msg : OK
     * data : {"id":"7cd77f9e2a3241c5ad3ae0a46472489d","isNewRecord":false,"createDate":"2017-11-07 09:12:41","updateDate":"2017-11-07 09:12:41","order":{"id":"97d1de9787b345b687fbf62c153c2f6a","isNewRecord":false,"amount":0,"bonus":0},"orderInvoice":{"id":"008fe4d840d7440eaa6e10f53e552c4b","isNewRecord":false,"createDate":"2017-11-06 10:32:17","updateDate":"2017-11-07 09:12:41","order":{"id":"97d1de9787b345b687fbf62c153c2f6a","isNewRecord":false,"amount":0,"bonus":0},"demand":{"id":"a758b26e0ca34de4a3851cd1bfe54554","isNewRecord":false,"totalAmount":0,"leftAmount":0,"totalBonus":0,"leftBonus":0,"mailMinimum":0,"beginTotalAmount":0,"endTotalAmount":0,"beginLeftAmount":0,"endLeftAmount":0,"beginTotalBonus":0,"endTotalBonus":0,"beginLeftBonus":0,"endLeftBonus":0,"beginMailMinimum":0,"endMailMinimum":0},"variety":"1","amount":100,"bonus":50,"url":"/invoice/f270f1e2dc4645e6a9161deedc1e0d01.jpg","state":"3","beginAmount":0,"endAmount":0,"beginBonus":0,"endBonus":0},"type":"1","reason":"1"}
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
         * id : 7cd77f9e2a3241c5ad3ae0a46472489d
         * isNewRecord : false
         * createDate : 2017-11-07 09:12:41
         * updateDate : 2017-11-07 09:12:41
         * order : {"id":"97d1de9787b345b687fbf62c153c2f6a","isNewRecord":false,"amount":0,"bonus":0}
         * type : 1
         * reason : 1
         */

        private String id;
        private boolean isNewRecord;
        private String createDate;
        private String updateDate;
        private OrderBean order;
        private String type;
        private String reason;

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

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getReason() {
            return reason;
        }

        public void setReason(String reason) {
            this.reason = reason;
        }

        public static class OrderBean {
            /**
             * id : 97d1de9787b345b687fbf62c153c2f6a
             * isNewRecord : false
             * amount : 0
             * bonus : 0
             */

            private String id;
            private boolean isNewRecord;
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
        }
    }
}
