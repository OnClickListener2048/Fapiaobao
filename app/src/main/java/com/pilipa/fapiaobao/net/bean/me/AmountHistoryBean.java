package com.pilipa.fapiaobao.net.bean.me;

import java.util.List;

/**
 * Created by edz on 2017/11/6.
 */

public class AmountHistoryBean {

    /**
     * status : 200
     * msg : OK
     * data : [{"id":"f01a9a0b7e2742df9a66bec7d9b34514","isNewRecord":false,"createDate":"2017-11-06 13:37:53","updateDate":"2017-11-06 13:37:53","customer":{"id":"91f5fa30a8f64d62a6bd17baaa14645d","isNewRecord":false},"fee":0,"fromwho":"3","towho":"1","wxTradeNo":"WX20171106131153029966656","title":"充值成功","subTitle":"微信支付"},{"id":"9debe6a0ddaa4ec59d15bf4fe17b882f","isNewRecord":false,"createDate":"2017-11-06 10:49:22","updateDate":"2017-11-06 10:49:22","customer":{"id":"771a60569ec5480eb588419c1851079b","isNewRecord":false},"fee":0,"fromwho":"3","towho":"1","wxTradeNo":"WX20171106101121811966656","title":"充值成功","subTitle":"微信支付"},{"id":"09f43c6ea3e848d38a6f61c5aa5247c7","isNewRecord":false,"createDate":"2017-11-05 19:28:59","updateDate":"2017-11-05 19:28:59","customer":{"id":"91f5fa30a8f64d62a6bd17baaa14645d","isNewRecord":false},"fee":0,"fromwho":"2","towho":"1","title":"红包充入余额","subTitle":"红包支付"},{"id":"1b3f65dd505641ba83c02c68045b47f4","isNewRecord":false,"createDate":"2017-11-05 19:28:59","updateDate":"2017-11-05 19:28:59","customer":{"id":"91f5fa30a8f64d62a6bd17baaa14645d","isNewRecord":false},"fee":0,"fromwho":"1","towho":"2","title":"红包奖励扣取","subTitle":"余额扣取"},{"id":"14ea9be8501b4acbb5ca536f9832e9d5","isNewRecord":false,"createDate":"2017-11-05 19:26:41","updateDate":"2017-11-05 19:26:41","customer":{"id":"91f5fa30a8f64d62a6bd17baaa14645d","isNewRecord":false},"fee":0,"fromwho":"1","towho":"2","title":"红包奖励扣取","subTitle":"余额扣取"},{"id":"292808408ebe4ace9497bc6def1eaeb5","isNewRecord":false,"createDate":"2017-11-05 19:26:41","updateDate":"2017-11-05 19:26:41","customer":{"id":"91f5fa30a8f64d62a6bd17baaa14645d","isNewRecord":false},"fee":0,"fromwho":"2","towho":"1","title":"红包充入余额","subTitle":"红包支付"},{"id":"585ee03f668a43ebab18b1c22675d6f9","isNewRecord":false,"createDate":"2017-11-03 17:21:33","updateDate":"2017-11-03 17:21:33","customer":{"id":"771a60569ec5480eb588419c1851079b","isNewRecord":false},"fee":0,"fromwho":"1","towho":"2","title":"红包奖励扣取","subTitle":"余额扣取"},{"id":"64104d32d8304ddf92399eaa633315c8","isNewRecord":false,"createDate":"2017-11-03 17:21:33","updateDate":"2017-11-03 17:21:33","customer":{"id":"771a60569ec5480eb588419c1851079b","isNewRecord":false},"fee":0,"fromwho":"2","towho":"1","title":"红包充入余额","subTitle":"红包支付"},{"id":"08b98cf0b5bd4adf9c20bf46abca005e","isNewRecord":false,"createDate":"2017-11-03 17:06:10","updateDate":"2017-11-03 17:06:10","customer":{"id":"771a60569ec5480eb588419c1851079b","isNewRecord":false},"fee":0,"fromwho":"3","towho":"1","wxTradeNo":"WX20171103171109066966656","title":"充值成功","subTitle":"微信支付"},{"id":"fbaa1fb47d1549709740c7c564909f1a","isNewRecord":false,"createDate":"2017-11-02 16:55:47","updateDate":"2017-11-02 16:55:47","customer":{"id":"771a60569ec5480eb588419c1851079b","isNewRecord":false},"fee":0,"fromwho":"3","towho":"1","wxTradeNo":"WX20171102161146790966656","title":"充值成功","subTitle":"微信支付"}]
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
         * id : f01a9a0b7e2742df9a66bec7d9b34514
         * isNewRecord : false
         * createDate : 2017-11-06 13:37:53
         * updateDate : 2017-11-06 13:37:53
         * customer : {"id":"91f5fa30a8f64d62a6bd17baaa14645d","isNewRecord":false}
         * fee : 0
         * fromwho : 3
         * towho : 1
         * wxTradeNo : WX20171106131153029966656
         * title : 充值成功
         * subTitle : 微信支付
         */

        private String id;
        private boolean isNewRecord;
        private String createDate;
        private String updateDate;
        private CustomerBean customer;
        private double fee;
        private String fromwho;
        private String towho;
        private String wxTradeNo;
        private String title;
        private String subTitle;

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

        public CustomerBean getCustomer() {
            return customer;
        }

        public void setCustomer(CustomerBean customer) {
            this.customer = customer;
        }

        public double getFee() {
            return fee;
        }

        public void setFee(double fee) {
            this.fee = fee;
        }

        public String getFromwho() {
            return fromwho;
        }

        public void setFromwho(String fromwho) {
            this.fromwho = fromwho;
        }

        public String getTowho() {
            return towho;
        }

        public void setTowho(String towho) {
            this.towho = towho;
        }

        public String getWxTradeNo() {
            return wxTradeNo;
        }

        public void setWxTradeNo(String wxTradeNo) {
            this.wxTradeNo = wxTradeNo;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getSubTitle() {
            return subTitle;
        }

        public void setSubTitle(String subTitle) {
            this.subTitle = subTitle;
        }

        public static class CustomerBean {
            /**
             * id : 91f5fa30a8f64d62a6bd17baaa14645d
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
