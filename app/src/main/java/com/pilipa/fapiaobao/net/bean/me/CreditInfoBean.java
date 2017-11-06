package com.pilipa.fapiaobao.net.bean.me;

/**
 * Created by edz on 2017/10/29.
 */

public class CreditInfoBean {


    /**
     * status : 200
     * msg : OK
     * data : {"ranking":0,"creditLevel":5}
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
         * ranking : 0
         * creditLevel : 5
         */

        private int ranking;
        private int creditLevel;
        private int lastCreditScore;

        public int getRanking() {
            return ranking;
        }

        public void setRanking(int ranking) {
            this.ranking = ranking;
        }

        public int getCreditLevel() {
            return creditLevel;
        }

        public void setCreditLevel(int creditLevel) {
            this.creditLevel = creditLevel;
        }

        public int getLastCreditScore() {
            return lastCreditScore;
        }

        public void setLastCreditScore(int lastCreditScore) {
            this.lastCreditScore = lastCreditScore;
        }
    }
}
