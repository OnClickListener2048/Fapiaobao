package com.pilipa.fapiaobao.net.bean.invoice;

/**
 * Created by edz on 2017/10/30.
 */

public class OrderBean {

    /**
     * status : 200
     * msg : OK
     * data : {"orderId":"b3a950bd956e4e7aa0991cd1266d0f6e"}
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
         * orderId : b3a950bd956e4e7aa0991cd1266d0f6e
         */

        private String orderId;

        public String getOrderId() {
            return orderId;
        }

        public void setOrderId(String orderId) {
            this.orderId = orderId;
        }
    }
}
