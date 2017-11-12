package com.pilipa.fapiaobao.net.bean.me;

import java.util.List;

/**
 * Created by edz on 2017/11/6.
 */

public class MessageListBean  {


    /**
     * status : 200
     * msg : OK
     * data : [{"id":"43243242234gs4df191ba27f3fb1a4fee","isNewRecord":false,"createDate":"2017-11-08 11:00:33","message":{"id":"qewwqeqwewqewqewqewqewqewq","isNewRecord":false,"type":"2","content":"红包到账 20张","demand":{"id":"247b44d14a444a9394baa4b322f33fb7","isNewRecord":false,"totalAmount":0,"leftAmount":0,"totalBonus":0,"leftBonus":0,"mailMinimum":0,"beginTotalAmount":0,"endTotalAmount":0,"beginLeftAmount":0,"endLeftAmount":0,"beginTotalBonus":0,"endTotalBonus":0,"beginLeftBonus":0,"endLeftBonus":0,"beginMailMinimum":0,"endMailMinimum":0}},"readFlag":"0"},{"id":"retertertretewrggewtttttttttttttttttwtewt","isNewRecord":false,"createDate":"2017-11-08 11:00:33","message":{"id":"382e88db6534234324324324324324","isNewRecord":false,"type":"4","content":"服务通知 20张","demand":{"id":"247b44d14a444a9394baa4b322f33fb7","isNewRecord":false,"totalAmount":0,"leftAmount":0,"totalBonus":0,"leftBonus":0,"mailMinimum":0,"beginTotalAmount":0,"endTotalAmount":0,"beginLeftAmount":0,"endLeftAmount":0,"beginTotalBonus":0,"endTotalBonus":0,"beginLeftBonus":0,"endLeftBonus":0,"beginMailMinimum":0,"endMailMinimum":0}},"readFlag":"0"},{"id":"trewtrewtewtewt5ytrtryerteryteryeryye","isNewRecord":false,"createDate":"2017-11-08 11:00:33","message":{"id":"382e88db653423432432432wqewq","isNewRecord":false,"type":"3","content":"不合格发票 20张","demand":{"id":"247b44d14a444a9394baa4b322f33fb7","isNewRecord":false,"totalAmount":0,"leftAmount":0,"totalBonus":0,"leftBonus":0,"mailMinimum":0,"beginTotalAmount":0,"endTotalAmount":0,"beginLeftAmount":0,"endLeftAmount":0,"beginTotalBonus":0,"endTotalBonus":0,"beginLeftBonus":0,"endLeftBonus":0,"beginMailMinimum":0,"endMailMinimum":0}},"readFlag":"0"},{"id":"uyuyouyyuytg647675444f3242333423","isNewRecord":false,"createDate":"2017-11-08 11:00:33","message":{"id":"382e88db6534234324432432432324","isNewRecord":false,"type":"4","content":"服务通知 20张","demand":{"id":"247b44d14a444a9394baa4b322f33fb7","isNewRecord":false,"totalAmount":0,"leftAmount":0,"totalBonus":0,"leftBonus":0,"mailMinimum":0,"beginTotalAmount":0,"endTotalAmount":0,"beginLeftAmount":0,"endLeftAmount":0,"beginTotalBonus":0,"endTotalBonus":0,"beginLeftBonus":0,"endLeftBonus":0,"beginMailMinimum":0,"endMailMinimum":0}},"readFlag":"0"},{"id":"uyuyouyyuytg64ghfghgfh2333423","isNewRecord":false,"createDate":"2017-11-08 11:00:33","message":{"id":"382e88db65a94df191b54654564654","isNewRecord":false,"type":"1","content":"新到发票 20张","demand":{"id":"247b44d14a444a9394baa4b322f33fb7","isNewRecord":false,"totalAmount":0,"leftAmount":0,"totalBonus":0,"leftBonus":0,"mailMinimum":0,"beginTotalAmount":0,"endTotalAmount":0,"beginLeftAmount":0,"endLeftAmount":0,"beginTotalBonus":0,"endTotalBonus":0,"beginLeftBonus":0,"endLeftBonus":0,"beginMailMinimum":0,"endMailMinimum":0}},"readFlag":"0"}]
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
         * id : 43243242234gs4df191ba27f3fb1a4fee
         * isNewRecord : false
         * createDate : 2017-11-08 11:00:33
         * message : {"id":"qewwqeqwewqewqewqewqewqewq","isNewRecord":false,"type":"2","content":"红包到账 20张","demand":{"id":"247b44d14a444a9394baa4b322f33fb7","isNewRecord":false,"totalAmount":0,"leftAmount":0,"totalBonus":0,"leftBonus":0,"mailMinimum":0,"beginTotalAmount":0,"endTotalAmount":0,"beginLeftAmount":0,"endLeftAmount":0,"beginTotalBonus":0,"endTotalBonus":0,"beginLeftBonus":0,"endLeftBonus":0,"beginMailMinimum":0,"endMailMinimum":0}}
         * readFlag : 0
         */
        private String id;
        private boolean isNewRecord;
        private String createDate;
        private MessageBean message;
        private String readFlag;

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

        public MessageBean getMessage() {
            return message;
        }

        public void setMessage(MessageBean message) {
            this.message = message;
        }

        public String getReadFlag() {
            return readFlag;
        }

        public void setReadFlag(String readFlag) {
            this.readFlag = readFlag;
        }

        public static class MessageBean {
            /**
             * id : qewwqeqwewqewqewqewqewqewq
             * isNewRecord : false
             * type : 2
             * content : 红包到账 20张
             * demand : {"id":"247b44d14a444a9394baa4b322f33fb7","isNewRecord":false,"totalAmount":0,"leftAmount":0,"totalBonus":0,"leftBonus":0,"mailMinimum":0,"beginTotalAmount":0,"endTotalAmount":0,"beginLeftAmount":0,"endLeftAmount":0,"beginTotalBonus":0,"endTotalBonus":0,"beginLeftBonus":0,"endLeftBonus":0,"beginMailMinimum":0,"endMailMinimum":0}
             */

            private String id;
            private boolean isNewRecord;
            private String type;
            private String content;
            private DemandBean demand;

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

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public DemandBean getDemand() {
                return demand;
            }

            public void setDemand(DemandBean demand) {
                this.demand = demand;
            }

            public static class DemandBean {
                /**
                 * id : 247b44d14a444a9394baa4b322f33fb7
                 * isNewRecord : false
                 * totalAmount : 0
                 * leftAmount : 0
                 * totalBonus : 0
                 * leftBonus : 0
                 * mailMinimum : 0
                 * beginTotalAmount : 0
                 * endTotalAmount : 0
                 * beginLeftAmount : 0
                 * endLeftAmount : 0
                 * beginTotalBonus : 0
                 * endTotalBonus : 0
                 * beginLeftBonus : 0
                 * endLeftBonus : 0
                 * beginMailMinimum : 0
                 * endMailMinimum : 0
                 */

                private String id;
                private boolean isNewRecord;
                private int totalAmount;
                private int leftAmount;
                private int totalBonus;
                private int leftBonus;
                private int mailMinimum;
                private int beginTotalAmount;
                private int endTotalAmount;
                private int beginLeftAmount;
                private int endLeftAmount;
                private int beginTotalBonus;
                private int endTotalBonus;
                private int beginLeftBonus;
                private int endLeftBonus;
                private int beginMailMinimum;
                private int endMailMinimum;

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

                public int getTotalAmount() {
                    return totalAmount;
                }

                public void setTotalAmount(int totalAmount) {
                    this.totalAmount = totalAmount;
                }

                public int getLeftAmount() {
                    return leftAmount;
                }

                public void setLeftAmount(int leftAmount) {
                    this.leftAmount = leftAmount;
                }

                public int getTotalBonus() {
                    return totalBonus;
                }

                public void setTotalBonus(int totalBonus) {
                    this.totalBonus = totalBonus;
                }

                public int getLeftBonus() {
                    return leftBonus;
                }

                public void setLeftBonus(int leftBonus) {
                    this.leftBonus = leftBonus;
                }

                public int getMailMinimum() {
                    return mailMinimum;
                }

                public void setMailMinimum(int mailMinimum) {
                    this.mailMinimum = mailMinimum;
                }

                public int getBeginTotalAmount() {
                    return beginTotalAmount;
                }

                public void setBeginTotalAmount(int beginTotalAmount) {
                    this.beginTotalAmount = beginTotalAmount;
                }

                public int getEndTotalAmount() {
                    return endTotalAmount;
                }

                public void setEndTotalAmount(int endTotalAmount) {
                    this.endTotalAmount = endTotalAmount;
                }

                public int getBeginLeftAmount() {
                    return beginLeftAmount;
                }

                public void setBeginLeftAmount(int beginLeftAmount) {
                    this.beginLeftAmount = beginLeftAmount;
                }

                public int getEndLeftAmount() {
                    return endLeftAmount;
                }

                public void setEndLeftAmount(int endLeftAmount) {
                    this.endLeftAmount = endLeftAmount;
                }

                public int getBeginTotalBonus() {
                    return beginTotalBonus;
                }

                public void setBeginTotalBonus(int beginTotalBonus) {
                    this.beginTotalBonus = beginTotalBonus;
                }

                public int getEndTotalBonus() {
                    return endTotalBonus;
                }

                public void setEndTotalBonus(int endTotalBonus) {
                    this.endTotalBonus = endTotalBonus;
                }

                public int getBeginLeftBonus() {
                    return beginLeftBonus;
                }

                public void setBeginLeftBonus(int beginLeftBonus) {
                    this.beginLeftBonus = beginLeftBonus;
                }

                public int getEndLeftBonus() {
                    return endLeftBonus;
                }

                public void setEndLeftBonus(int endLeftBonus) {
                    this.endLeftBonus = endLeftBonus;
                }

                public int getBeginMailMinimum() {
                    return beginMailMinimum;
                }

                public void setBeginMailMinimum(int beginMailMinimum) {
                    this.beginMailMinimum = beginMailMinimum;
                }

                public int getEndMailMinimum() {
                    return endMailMinimum;
                }

                public void setEndMailMinimum(int endMailMinimum) {
                    this.endMailMinimum = endMailMinimum;
                }
            }
        }
    }
}
