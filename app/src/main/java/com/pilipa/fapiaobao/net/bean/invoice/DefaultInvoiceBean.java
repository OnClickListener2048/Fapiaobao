package com.pilipa.fapiaobao.net.bean.invoice;

import java.util.List;

/**
 * Created by edz on 2017/10/29.
 */

public class DefaultInvoiceBean {

    /**
     * status : 200
     * msg : OK
     * data : [{"id":"43f1faf2cfee4f508d02c36975dfa06d","isNewRecord":false,"remarks":"","createDate":"2017-10-29 12:28:39","updateDate":"2017-10-29 12:28:39","category":"4","name":"会议票","smallSize":"","middleSize":"http://192.168.1.205:8181/fapiaobao/fapiaobao/userfiles/1/files/personal/invoiceType/2017/10/receipt_011.png","largeSize":"","frequentFlag":"1"},{"id":"46930352ad15484dbe667867a9abca99","isNewRecord":false,"remarks":"","createDate":"2017-10-26 09:43:17","updateDate":"2017-10-29 12:20:50","category":"4","name":"住宿票","smallSize":"","middleSize":"http://192.168.1.205:8181/fapiaobao/fapiaobao/userfiles/1/files/personal/invoiceType/2017/10/receipt_002.png","largeSize":"","categorySort":10,"frequentFlag":"1"},{"id":"46a421bf1c0e4fa0a5f8260334096b92","isNewRecord":false,"remarks":"","createDate":"2017-10-29 12:28:59","updateDate":"2017-10-29 12:28:59","category":"4","name":"快递票","smallSize":"","middleSize":"http://192.168.1.205:8181/fapiaobao/fapiaobao/userfiles/1/files/personal/invoiceType/2017/10/receipt_012.png","largeSize":"","frequentFlag":"1"},{"id":"83ff72c08d6b4aac8f83df71b8283df9","isNewRecord":false,"remarks":"","createDate":"2017-10-26 09:40:28","updateDate":"2017-10-29 12:21:53","category":"3","name":"过路过桥费票","smallSize":"","middleSize":"http://192.168.1.205:8181/fapiaobao/fapiaobao/userfiles/1/files/personal/invoiceType/2017/10/receipt_007.png","largeSize":"","categorySort":10,"frequentFlag":"1"},{"id":"8d50e4acbf9540bc8c3e275cceba0e20","isNewRecord":false,"remarks":"","createDate":"2017-10-26 09:39:00","updateDate":"2017-10-29 12:23:50","category":"4","name":"通讯票","smallSize":"","middleSize":"http://192.168.1.205:8181/fapiaobao/fapiaobao/userfiles/1/files/personal/invoiceType/2017/10/receipt_005.png","largeSize":"","categorySort":10,"frequentFlag":"1"},{"id":"8d8cdbb335b84e0a89c92e75e1f0c1c3","isNewRecord":false,"remarks":"","createDate":"2017-10-29 12:28:00","updateDate":"2017-10-29 12:28:00","category":"4","name":"礼品票","smallSize":"","middleSize":"http://192.168.1.205:8181/fapiaobao/fapiaobao/userfiles/1/files/personal/invoiceType/2017/10/receipt_009.png","largeSize":"","frequentFlag":"1"},{"id":"b1579e8ebfab4e398d6f6d640fae0889","isNewRecord":false,"remarks":"","createDate":"2017-10-26 09:08:02","updateDate":"2017-10-29 12:25:42","category":"1","name":"出租车票","smallSize":"","middleSize":"http://192.168.1.205:8181/fapiaobao/fapiaobao/userfiles/1/files/personal/invoiceType/2017/10/receipt_001.png","largeSize":"","categorySort":10,"frequentFlag":"1"},{"id":"b207f5257cd948bb8f7176eede36b583","isNewRecord":false,"remarks":"","createDate":"2017-10-26 09:38:30","updateDate":"2017-10-29 12:25:04","category":"1","name":"地铁票","smallSize":"","middleSize":"http://192.168.1.205:8181/fapiaobao/fapiaobao/userfiles/1/files/personal/invoiceType/2017/10/receipt_004.png","largeSize":"","categorySort":20,"frequentFlag":"1"},{"id":"b720c4f54fdc4fbea5f853870969cda7","isNewRecord":false,"remarks":"","createDate":"2017-10-29 12:27:01","updateDate":"2017-10-29 12:27:01","category":"4","name":"办公用品票","smallSize":"","middleSize":"http://192.168.1.205:8181/fapiaobao/fapiaobao/userfiles/1/files/personal/invoiceType/2017/10/receipt_006.png","largeSize":"","frequentFlag":"1"},{"id":"bac478bb52e6440fa745e15d53e42282","isNewRecord":false,"remarks":"","createDate":"2017-10-26 09:43:36","updateDate":"2017-10-29 12:07:14","category":"4","name":"餐饮票","smallSize":"","middleSize":"http://192.168.1.205:8181/fapiaobao/fapiaobao/userfiles/1/files/personal/invoiceType/2017/10/receipt_003.png","largeSize":"","categorySort":20,"frequentFlag":"1"},{"id":"c717e02aa40b4b3f8334efbf336f8fac","isNewRecord":false,"remarks":"","createDate":"2017-10-29 12:27:37","updateDate":"2017-10-29 12:27:37","category":"4","name":"装修票","smallSize":"","middleSize":"http://192.168.1.205:8181/fapiaobao/fapiaobao/userfiles/1/files/personal/invoiceType/2017/10/receipt_008.png","largeSize":"","frequentFlag":"1"},{"id":"d620153d5c584cc0b54a5acb1191c733","isNewRecord":false,"remarks":"","createDate":"2017-10-29 12:28:20","updateDate":"2017-10-29 12:28:20","category":"4","name":"食品票","smallSize":"","middleSize":"http://192.168.1.205:8181/fapiaobao/fapiaobao/userfiles/1/files/personal/invoiceType/2017/10/receipt_010.png","largeSize":"","frequentFlag":"1"}]
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
         * id : 43f1faf2cfee4f508d02c36975dfa06d
         * isNewRecord : false
         * remarks :
         * createDate : 2017-10-29 12:28:39
         * updateDate : 2017-10-29 12:28:39
         * category : 4
         * name : 会议票
         * smallSize :
         * middleSize : http://192.168.1.205:8181/fapiaobao/fapiaobao/userfiles/1/files/personal/invoiceType/2017/10/receipt_011.png
         * largeSize :
         * frequentFlag : 1
         * categorySort : 10
         */

        private String id;
        private boolean isNewRecord;
        private String remarks;
        private String createDate;
        private String updateDate;
        private String category;
        private String name;
        private String smallSize;
        private String middleSize;
        private String largeSize;
        private String frequentFlag;
        private int categorySort;

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

        public String getRemarks() {
            return remarks;
        }

        public void setRemarks(String remarks) {
            this.remarks = remarks;
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

        public String getCategory() {
            return category;
        }

        public void setCategory(String category) {
            this.category = category;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getSmallSize() {
            return smallSize;
        }

        public void setSmallSize(String smallSize) {
            this.smallSize = smallSize;
        }

        public String getMiddleSize() {
            return middleSize;
        }

        public void setMiddleSize(String middleSize) {
            this.middleSize = middleSize;
        }

        public String getLargeSize() {
            return largeSize;
        }

        public void setLargeSize(String largeSize) {
            this.largeSize = largeSize;
        }

        public String getFrequentFlag() {
            return frequentFlag;
        }

        public void setFrequentFlag(String frequentFlag) {
            this.frequentFlag = frequentFlag;
        }

        public int getCategorySort() {
            return categorySort;
        }

        public void setCategorySort(int categorySort) {
            this.categorySort = categorySort;
        }
    }
}
