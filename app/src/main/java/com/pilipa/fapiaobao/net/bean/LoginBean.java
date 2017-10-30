package com.pilipa.fapiaobao.net.bean;

/**
 * Created by edz on 2017/10/28.
 */

public class LoginBean {


    /**
     * status : 200
     * msg : OK
     * data : {"customer":{"id":"99943d3fc0a54188af72e5a12c80f905","isNewRecord":false,"createDate":"2017-10-28 17:19:42","updateDate":"2017-10-28 17:19:42","telephone":"13114917491","frequentType":"46930352ad15484dbe667867a9abca99,b1579e8ebfab4e398d6f6d640fae0889,b207f5257cd948bb8f7176eede36b583,bac478bb52e6440fa745e15d53e42282"},"expiresTime":1509286520554,"token":"96c9e1e6-add1-4a3c-a9ba-28959b703698"}
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
         * customer : {"id":"99943d3fc0a54188af72e5a12c80f905","isNewRecord":false,"createDate":"2017-10-28 17:19:42","updateDate":"2017-10-28 17:19:42","telephone":"13114917491","frequentType":"46930352ad15484dbe667867a9abca99,b1579e8ebfab4e398d6f6d640fae0889,b207f5257cd948bb8f7176eede36b583,bac478bb52e6440fa745e15d53e42282"}
         * expiresTime : 1509286520554
         * token : 96c9e1e6-add1-4a3c-a9ba-28959b703698
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
             * id : 99943d3fc0a54188af72e5a12c80f905
             * isNewRecord : false
             * createDate : 2017-10-28 17:19:42
             * updateDate : 2017-10-28 17:19:42
             * telephone : 13114917491
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
