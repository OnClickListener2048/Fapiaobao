package com.pilipa.fapiaobao.net.bean.wx;

/**
 * Created by edz on 2017/10/30.
 */

public class PrepayBean {

    /**
     * status : 200
     * msg : OK
     * data : {"sign":"1E588752908FC76CEEDAC7EA72A04F23","prepayId":"wx20171031184901706da76c0f0578275566","partnerId":"1490725712","appId":"wxb704e8dd773cc94e","packageValue":"Sign=WXPay","timeStamp":"1509446940","nonceStr":"1509446940884"}
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
         * sign : 1E588752908FC76CEEDAC7EA72A04F23
         * prepayId : wx20171031184901706da76c0f0578275566
         * partnerId : 1490725712
         * appId : wxb704e8dd773cc94e
         * packageValue : Sign=WXPay
         * timeStamp : 1509446940
         * nonceStr : 1509446940884
         */

        private String sign;
        private String prepayId;
        private String partnerId;
        private String appId;
        private String packageValue;
        private String timeStamp;
        private String nonceStr;

        public String getSign() {
            return sign;
        }

        public void setSign(String sign) {
            this.sign = sign;
        }

        public String getPrepayId() {
            return prepayId;
        }

        public void setPrepayId(String prepayId) {
            this.prepayId = prepayId;
        }

        public String getPartnerId() {
            return partnerId;
        }

        public void setPartnerId(String partnerId) {
            this.partnerId = partnerId;
        }

        public String getAppId() {
            return appId;
        }

        public void setAppId(String appId) {
            this.appId = appId;
        }

        public String getPackageValue() {
            return packageValue;
        }

        public void setPackageValue(String packageValue) {
            this.packageValue = packageValue;
        }

        public String getTimeStamp() {
            return timeStamp;
        }

        public void setTimeStamp(String timeStamp) {
            this.timeStamp = timeStamp;
        }

        public String getNonceStr() {
            return nonceStr;
        }

        public void setNonceStr(String nonceStr) {
            this.nonceStr = nonceStr;
        }
    }
}
