package com.pilipa.fapiaobao.net.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by edz on 2017/10/28.
 */

public class RejectTypeBean implements Serializable {


    /**
     * status : 200
     * msg : OK
     * data : [{"id":"8515c7b0f1b74f9282883a17ae7483b1","isNewRecord":false,"remarks":"","createDate":"2017-11-01 11:15:03","updateDate":"2017-11-01 11:15:03","value":"1","label":"票据信息不符","type":"reject_type","description":"发票驳回类型","sort":10,"parentId":"0"},{"id":"06e5b9a2303149b0883fca3b7a404869","isNewRecord":false,"remarks":"","createDate":"2017-11-01 11:15:32","updateDate":"2017-11-01 11:16:10","value":"2","label":"开票信息错误","type":"reject_type","description":"发票驳回类型","sort":20,"parentId":"0"},{"id":"46dae71b3e2141efb2a3d8c977254b1e","isNewRecord":false,"remarks":"","createDate":"2017-11-01 11:16:01","updateDate":"2017-11-01 11:16:01","value":"3","label":"票面金额不符","type":"reject_type","description":"发票驳回类型","sort":30,"parentId":"0"},{"id":"c3e7eac03ccf4a099d08590cb4a69da6","isNewRecord":false,"remarks":"","createDate":"2017-11-01 11:17:25","updateDate":"2017-11-01 11:17:25","value":"4","label":"发票已过期","type":"reject_type","description":"发票驳回类型","sort":40,"parentId":"0"},{"id":"4b0b8f3a8eeb472ea89981b286e7ddbc","isNewRecord":false,"remarks":"","createDate":"2017-11-01 11:17:55","updateDate":"2017-11-01 11:17:55","value":"5","label":"发票已被使用","type":"reject_type","description":"发票驳回类型","sort":50,"parentId":"0"},{"id":"16216d2be15a4c8a834ffa07162333c6","isNewRecord":false,"remarks":"","createDate":"2017-11-01 11:18:32","updateDate":"2017-11-01 11:18:32","value":"6","label":"其它","type":"reject_type","description":"发票驳回类型","sort":60,"parentId":"0"}]
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

    public static class DataBean implements Serializable {
        /**
         * id : 8515c7b0f1b74f9282883a17ae7483b1
         * isNewRecord : false
         * remarks :
         * createDate : 2017-11-01 11:15:03
         * updateDate : 2017-11-01 11:15:03
         * value : 1
         * label : 票据信息不符
             * type : reject_type
         * description : 发票驳回类型
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
