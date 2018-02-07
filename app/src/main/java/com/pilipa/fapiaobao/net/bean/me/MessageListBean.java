package com.pilipa.fapiaobao.net.bean.me;

import java.util.List;

/**
 * Created by edz on 2017/11/6.
 */

public class MessageListBean  {


    /**
     * status : 200
     * msg : OK
     * data : [{"messageType":"1","messageTypeName":"新到发票","newComeDate":1526526033000,"unreadMessages":1},{"messageType":"2","messageTypeName":"红包到账","newComeDate":1510455633000,"unreadMessages":1},{"messageType":"3","messageTypeName":"不合格发票","newComeDate":1511406033000,"unreadMessages":1},{"messageType":"4","messageTypeName":"服务通知","newComeDate":4424986833000,"unreadMessages":2}]
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
         * messageType : 1
         * messageTypeName : 新到发票
         * newComeDate : 1526526033000
         * unreadMessages : 1
         */

        private String messageType;
        private String messageTypeName;
        private long newComeDate;
        private double unreadMessages;

        public String getMessageType() {
            return messageType;
        }

        public void setMessageType(String messageType) {
            this.messageType = messageType;
        }

        public String getMessageTypeName() {
            return messageTypeName;
        }

        public void setMessageTypeName(String messageTypeName) {
            this.messageTypeName = messageTypeName;
        }

        public long getNewComeDate() {
            return newComeDate;
        }

        public void setNewComeDate(long newComeDate) {
            this.newComeDate = newComeDate;
        }

        public double getUnreadMessages() {
            return unreadMessages;
        }

        public void setUnreadMessages(int unreadMessages) {
            this.unreadMessages = unreadMessages;
        }
    }
}
