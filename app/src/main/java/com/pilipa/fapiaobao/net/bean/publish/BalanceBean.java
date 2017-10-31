package com.pilipa.fapiaobao.net.bean.publish;

/**
 * Created by edz on 2017/10/31.
 */

public class BalanceBean {

    /**
     * status : 888
     * msg : 账户余额不足
     * data : {"amount":0}
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
         * amount : 0
         */

        private int amount;

        public int getAmount() {
            return amount;
        }

        public void setAmount(int amount) {
            this.amount = amount;
        }
    }
}
