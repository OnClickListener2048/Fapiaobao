package com.pilipa.fapiaobao.net.bean;

/**
 * Created by edz on 2017/10/30.
 */

public class LoginWithInfoBean {

    /**
     * status : 200
     * msg : OK
     * data : {"customer":{"id":"6ee15c894b1a435d9c24025b324e17f7","isNewRecord":false,"remarks":"","createDate":"2017-10-28 14:58:27","updateDate":"2017-10-30 13:01:10","nickname":"王加宁","telephone":"17695548799","headimg":"--","openid":"sadsdsad","gender":"1","birthday":"1992-10-25","amount":50,"bonus":50,"frozen":50,"frequentType":"46930352ad15484dbe667867a9abca99,b1579e8ebfab4e398d6f6d640fae0889,b207f5257cd948bb8f7176eede36b583,bac478bb52e6440fa745e15d53e42282","creditScore":50,"creditLevel":5},"expiresTime":1509339716491,"token":"3e82fd37-ec6f-464a-b122-567868fcfcaf"}
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
         * customer : {"id":"6ee15c894b1a435d9c24025b324e17f7","isNewRecord":false,"remarks":"","createDate":"2017-10-28 14:58:27","updateDate":"2017-10-30 13:01:10","nickname":"王加宁","telephone":"17695548799","headimg":"--","openid":"sadsdsad","gender":"1","birthday":"1992-10-25","amount":50,"bonus":50,"frozen":50,"frequentType":"46930352ad15484dbe667867a9abca99,b1579e8ebfab4e398d6f6d640fae0889,b207f5257cd948bb8f7176eede36b583,bac478bb52e6440fa745e15d53e42282","creditScore":50,"creditLevel":5}
         * expiresTime : 1509339716491
         * token : 3e82fd37-ec6f-464a-b122-567868fcfcaf
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
             * remarks :
             * createDate : 2017-10-28 14:58:27
             * updateDate : 2017-10-30 13:01:10
             * nickname : 王加宁
             * telephone : 17695548799
             * headimg : --
             * openid : sadsdsad
             * gender : 1
             * birthday : 1992-10-25
             * amount : 50
             * bonus : 50
             * frozen : 50
             * frequentType : 46930352ad15484dbe667867a9abca99,b1579e8ebfab4e398d6f6d640fae0889,b207f5257cd948bb8f7176eede36b583,bac478bb52e6440fa745e15d53e42282
             * creditScore : 50
             * creditLevel : 5
             */

            private String id;
            private boolean isNewRecord;
            private String remarks;
            private String createDate;
            private String updateDate;
            private String nickname;
            private String telephone;
            private String headimg;
            private String openid;
            private String gender;
            private String birthday;
            private double amount;
            private double bonus;
            private double frozen;
            private String frequentType;
            private String creditScore;
            private String email;
            private int creditLevel;

            public String getEmail() {
                if ("".equals(email)) {
                    return null;
                }

                if (email == null) {
                    return "";
                }
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

            public String getNickname() {
                return nickname;
            }

            public void setNickname(String nickname) {
                this.nickname = nickname;
            }

            public String getTelephone() {
                return telephone;
            }

            public void setTelephone(String telephone) {
                this.telephone = telephone;
            }

            public String getHeadimg() {
                return headimg;
            }

            public void setHeadimg(String headimg) {
                this.headimg = headimg;
            }

            public String getOpenid() {
                return openid;
            }

            public void setOpenid(String openid) {
                this.openid = openid;
            }

            public String getGender() {
                return gender;
            }

            public void setGender(String gender) {
                this.gender = gender;
            }

            public String getBirthday() {
                return birthday;
            }

            public void setBirthday(String birthday) {
                this.birthday = birthday;
            }

            public double getAmount() {
                return amount;
            }

            public void setAmount(double amount) {
                this.amount = amount;
            }

            public double getBonus() {
                return bonus;
            }

            public void setBonus(double bonus) {
                this.bonus = bonus;
            }

            public double getFrozen() {
                return frozen;
            }

            public void setFrozen(double frozen) {
                this.frozen = frozen;
            }

            public String getFrequentType() {
                return frequentType;
            }

            public void setFrequentType(String frequentType) {
                this.frequentType = frequentType;
            }

            public String getCreditScore() {
                return creditScore;
            }

            public void setCreditScore(String creditScore) {
                this.creditScore = creditScore;
            }

            public int getCreditLevel() {
                return creditLevel;
            }

            public void setCreditLevel(int creditLevel) {
                this.creditLevel = creditLevel;
            }
        }
    }
}
