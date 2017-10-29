package com.pilipa.fapiaobao.net.bean;

/**
 * Created by edz on 2017/10/28.
 */

public class LoginBean {


    /**
     * status : 200
     * msg : OK
     * data : {"customer":{"id":"6ee15c894b1a435d9c24025b324e17f7","isNewRecord":false,"createDate":"2017-10-28 14:58:26","updateDate":"2017-10-28 14:58:26","telephone":"17695548799","frequentType":"46930352ad15484dbe667867a9abca99,b1579e8ebfab4e398d6f6d640fae0889,b207f5257cd948bb8f7176eede36b583,bac478bb52e6440fa745e15d53e42282"},"expiresTime":1509173906693,"token":"eb7aceb4-15d1-4ecf-a502-28d169f58e85"}
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
         * customer : {"id":"6ee15c894b1a435d9c24025b324e17f7","isNewRecord":false,"createDate":"2017-10-28 14:58:26","updateDate":"2017-10-28 14:58:26","telephone":"17695548799","frequentType":"46930352ad15484dbe667867a9abca99,b1579e8ebfab4e398d6f6d640fae0889,b207f5257cd948bb8f7176eede36b583,bac478bb52e6440fa745e15d53e42282"}
         * expiresTime : 1509173906693
         * token : eb7aceb4-15d1-4ecf-a502-28d169f58e85
         */

        private CustomerBean customer;
        private long expiresTime;
        private String token;

        public CustomerBean getCustomer() {
            return customer;
        }

        public void setCustomer(CustomerBean customer) {
            this.customer = customer;
        }

        public long getExpiresTime() {
            return expiresTime;
        }

        public void setExpiresTime(long expiresTime) {
            this.expiresTime = expiresTime;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public static class CustomerBean {
            /**
             * id : 6ee15c894b1a435d9c24025b324e17f7
             * isNewRecord : false
             * createDate : 2017-10-28 14:58:26
             * updateDate : 2017-10-28 14:58:26
             * telephone : 17695548799
             * frequentType : 46930352ad15484dbe667867a9abca99,b1579e8ebfab4e398d6f6d640fae0889,b207f5257cd948bb8f7176eede36b583,bac478bb52e6440fa745e15d53e42282
             */

            private String id;
            private boolean isNewRecord;
            private String createDate;
            private String updateDate;
            private String telephone;
            private String frequentType;

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

            public String getTelephone() {
                return telephone;
            }

            public void setTelephone(String telephone) {
                this.telephone = telephone;
            }

            public String getFrequentType() {
                return frequentType;
            }

            public void setFrequentType(String frequentType) {
                this.frequentType = frequentType;
            }
        }
    }
}
