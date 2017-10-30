package com.pilipa.fapiaobao.net.bean.publish;

import java.util.List;

/**
 * Created by edz on 2017/10/29.
 */

public class ExpressCompanyBean {

    /**
     * status : 200
     * msg : OK
     * data : [{"id":"98cf011c49114c7baf11a20d52853954","isNewRecord":false,"remarks":"","createDate":"2017-10-27 21:30:17","updateDate":"2017-10-27 21:30:17","value":"1","label":"圆通","type":"logistics_company","description":"物流公司","sort":10,"parentId":"0"},{"id":"bd247fcc45754249b6af169026d70fce","isNewRecord":false,"remarks":"","createDate":"2017-10-27 16:23:07","updateDate":"2017-10-27 21:30:49","value":"2","label":"顺丰","type":"logistics_company","description":"物流公司","sort":20,"parentId":"0"}]
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
         * id : 98cf011c49114c7baf11a20d52853954
         * isNewRecord : false
         * remarks :
         * createDate : 2017-10-27 21:30:17
         * updateDate : 2017-10-27 21:30:17
         * value : 1
         * label : 圆通
         * type : logistics_company
         * description : 物流公司
         * sort : 10
         * parentId : 0
         */

        private String id;
        private boolean isNewRecord;
        private String remarks;
        private String createDate;
        private String updateDate;
        private String value;
        private String label;
        private String type;
        private String description;
        private int sort;
        private String parentId;

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

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        public String getLabel() {
            return label;
        }

        public void setLabel(String label) {
            this.label = label;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public int getSort() {
            return sort;
        }

        public void setSort(int sort) {
            this.sort = sort;
        }

        public String getParentId() {
            return parentId;
        }

        public void setParentId(String parentId) {
            this.parentId = parentId;
        }
    }
}
