package com.pilipa.fapiaobao.net.bean.wx;

/**
 * Created by edz on 2017/10/30.
 */

public class PrepayBean {

    /**
     * status : 200
     * msg : OK
     * data : {"unifiedOrderResult":{"returnCode":"SUCCESS","returnMsg":"OK","resultCode":"SUCCESS","appid":"wxb704e8dd773cc94e","mchId":"1490725712","nonceStr":"LTT2tsRRRaRlIcPu","sign":"9047B834D68C54C191757E07472D0D1D","xmlString":"<xml><return_code><![CDATA[SUCCESS]]><\/return_code>\n<return_msg><![CDATA[OK]]><\/return_msg>\n<appid><![CDATA[wxb704e8dd773cc94e]]><\/appid>\n<mch_id><![CDATA[1490725712]]><\/mch_id>\n<nonce_str><![CDATA[LTT2tsRRRaRlIcPu]]><\/nonce_str>\n<sign><![CDATA[9047B834D68C54C191757E07472D0D1D]]><\/sign>\n<result_code><![CDATA[SUCCESS]]><\/result_code>\n<prepay_id><![CDATA[wx2017103020260275a811a4710629671496]]><\/prepay_id>\n<trade_type><![CDATA[APP]]><\/trade_type>\n<\/xml>","prepayId":"wx2017103020260275a811a4710629671496","tradeType":"APP"},"timestamp":"1509366360"}
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
         * unifiedOrderResult : {"returnCode":"SUCCESS","returnMsg":"OK","resultCode":"SUCCESS","appid":"wxb704e8dd773cc94e","mchId":"1490725712","nonceStr":"LTT2tsRRRaRlIcPu","sign":"9047B834D68C54C191757E07472D0D1D","xmlString":"<xml><return_code><![CDATA[SUCCESS]]><\/return_code>\n<return_msg><![CDATA[OK]]><\/return_msg>\n<appid><![CDATA[wxb704e8dd773cc94e]]><\/appid>\n<mch_id><![CDATA[1490725712]]><\/mch_id>\n<nonce_str><![CDATA[LTT2tsRRRaRlIcPu]]><\/nonce_str>\n<sign><![CDATA[9047B834D68C54C191757E07472D0D1D]]><\/sign>\n<result_code><![CDATA[SUCCESS]]><\/result_code>\n<prepay_id><![CDATA[wx2017103020260275a811a4710629671496]]><\/prepay_id>\n<trade_type><![CDATA[APP]]><\/trade_type>\n<\/xml>","prepayId":"wx2017103020260275a811a4710629671496","tradeType":"APP"}
         * timestamp : 1509366360
         */

        private UnifiedOrderResultBean unifiedOrderResult;
        private String timestamp;

        public UnifiedOrderResultBean getUnifiedOrderResult() {
            return unifiedOrderResult;
        }

        public void setUnifiedOrderResult(UnifiedOrderResultBean unifiedOrderResult) {
            this.unifiedOrderResult = unifiedOrderResult;
        }

        public String getTimestamp() {
            return timestamp;
        }

        public void setTimestamp(String timestamp) {
            this.timestamp = timestamp;
        }

        public static class UnifiedOrderResultBean {
            /**
             * returnCode : SUCCESS
             * returnMsg : OK
             * resultCode : SUCCESS
             * wxb704e8dd773cc94e
             * appid : wxb704e8dd773cc94e
             * mchId : 1490725712
             * nonceStr : LTT2tsRRRaRlIcPu
             * sign : 9047B834D68C54C191757E07472D0D1D
             * xmlString : <xml><return_code><![CDATA[SUCCESS]]></return_code>
             <return_msg><![CDATA[OK]]></return_msg>
             <appid><![CDATA[wxb704e8dd773cc94e]]></appid>
             <mch_id><![CDATA[1490725712]]></mch_id>
             <nonce_str><![CDATA[LTT2tsRRRaRlIcPu]]></nonce_str>
             <sign><![CDATA[9047B834D68C54C191757E07472D0D1D]]></sign>
             <result_code><![CDATA[SUCCESS]]></result_code>
             <prepay_id><![CDATA[wx2017103020260275a811a4710629671496]]></prepay_id>
             <trade_type><![CDATA[APP]]></trade_type>
             </xml>
             * prepayId : wx2017103020260275a811a4710629671496
             * tradeType : APP
             */

            private String returnCode;
            private String returnMsg;
            private String resultCode;
            private String appid;
            private String mchId;
            private String nonceStr;
            private String sign;
            private String xmlString;
            private String prepayId;
            private String tradeType;

            public String getReturnCode() {
                return returnCode;
            }

            public void setReturnCode(String returnCode) {
                this.returnCode = returnCode;
            }

            public String getReturnMsg() {
                return returnMsg;
            }

            public void setReturnMsg(String returnMsg) {
                this.returnMsg = returnMsg;
            }

            public String getResultCode() {
                return resultCode;
            }

            public void setResultCode(String resultCode) {
                this.resultCode = resultCode;
            }

            public String getAppid() {
                return appid;
            }

            public void setAppid(String appid) {
                this.appid = appid;
            }

            public String getMchId() {
                return mchId;
            }

            public void setMchId(String mchId) {
                this.mchId = mchId;
            }

            public String getNonceStr() {
                return nonceStr;
            }

            public void setNonceStr(String nonceStr) {
                this.nonceStr = nonceStr;
            }

            public String getSign() {
                return sign;
            }

            public void setSign(String sign) {
                this.sign = sign;
            }

            public String getXmlString() {
                return xmlString;
            }

            public void setXmlString(String xmlString) {
                this.xmlString = xmlString;
            }

            public String getPrepayId() {
                return prepayId;
            }

            public void setPrepayId(String prepayId) {
                this.prepayId = prepayId;
            }

            public String getTradeType() {
                return tradeType;
            }

            public void setTradeType(String tradeType) {
                this.tradeType = tradeType;
            }
        }
    }
}
