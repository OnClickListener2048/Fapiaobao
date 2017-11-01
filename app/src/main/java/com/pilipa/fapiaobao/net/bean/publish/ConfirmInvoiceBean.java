package com.pilipa.fapiaobao.net.bean.publish;

/**
 * Created by edz on 2017/11/1.
 */

public class ConfirmInvoiceBean {

    /**
     * status : 200
     * msg : OK
     * data : {"id":"12eb0f73a4ff4cde811a30aa872c18ed","isNewRecord":false,"createDate":"2017-11-01 13:47:51","updateDate":"2017-11-01 14:56:50","order":{"id":"94dd9e0524544ea29c592912640ec3bd","isNewRecord":false},"demand":{"id":"3a89fd54d244485ca08b5c3b42401688","isNewRecord":false},"variety":"3","url":"https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1509526066491&di=41dddfc05a78ae644a9695eef54ef1dd&imgtype=0&src=http%3A%2F%2Fphotocdn.sohu.com%2F20100208%2FImg270139378.jpg","state":"3"}
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
         * id : 12eb0f73a4ff4cde811a30aa872c18ed
         * isNewRecord : false
         * createDate : 2017-11-01 13:47:51
         * updateDate : 2017-11-01 14:56:50
         * order : {"id":"94dd9e0524544ea29c592912640ec3bd","isNewRecord":false}
         * demand : {"id":"3a89fd54d244485ca08b5c3b42401688","isNewRecord":false}
         * variety : 3
         * url : https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1509526066491&di=41dddfc05a78ae644a9695eef54ef1dd&imgtype=0&src=http%3A%2F%2Fphotocdn.sohu.com%2F20100208%2FImg270139378.jpg
         * state : 3
         */

        private String id;
        private boolean isNewRecord;
        private String createDate;
        private String updateDate;
        private OrderBean order;
        private DemandBean demand;
        private String variety;
        private String url;
        private String state;

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

        public OrderBean getOrder() {
            return order;
        }

        public void setOrder(OrderBean order) {
            this.order = order;
        }

        public DemandBean getDemand() {
            return demand;
        }

        public void setDemand(DemandBean demand) {
            this.demand = demand;
        }

        public String getVariety() {
            return variety;
        }

        public void setVariety(String variety) {
            this.variety = variety;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state;
        }

        public static class OrderBean {
            /**
             * id : 94dd9e0524544ea29c592912640ec3bd
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

        public static class DemandBean {
            /**
             * id : 3a89fd54d244485ca08b5c3b42401688
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
