package com.pilipa.fapiaobao.net.bean.invoice;

/**
 * Created by edz on 2017/11/6.
 */

public class RedBagBean {

    /**
     * status : 200
     * msg : OK
     * data : {"bonus":50}
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
         * bonus : 50.0
         */

        private double bonus;

        public double getBonus() {
            return bonus;
        }

        public void setBonus(double bonus) {
            this.bonus = bonus;
        }
    }
}
