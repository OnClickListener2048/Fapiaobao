package com.pilipa.fapiaobao.net.bean.publish;

/**
 * Created by edz on 2017/10/31.
 */

public class BalanceBean {


    /**
     * status : 200
     * msg : OK
     * data : {"demand":"83fdbbc1a6c74e6daa61222ac4abfb2d"}
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
         * demand : 83fdbbc1a6c74e6daa61222ac4abfb2d
         */

        private String demand;

        public String getDemand() {
            return demand;
        }

        public void setDemand(String demand) {
            this.demand = demand;
        }
    }
}
