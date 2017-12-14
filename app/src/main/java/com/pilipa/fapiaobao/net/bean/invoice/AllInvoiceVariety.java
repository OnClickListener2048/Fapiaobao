package com.pilipa.fapiaobao.net.bean.invoice;

import java.io.Serializable;
import java.util.List;

/**
 * Created by edz on 2017/10/31.
 */

public class AllInvoiceVariety implements Serializable{

    /**
     * status : 200
     * msg : OK
     * data : [{"id":"34a7fa6314904a8bac27c949c17a4088","isNewRecord":false,"remarks":"","createDate":"2017-10-26 16:08:28","updateDate":"2017-10-26 16:08:28","value":"1","label":"纸质普票","type":"invoice_variety","description":"发票种类","sort":10,"parentId":"0"},{"id":"87662bf7f6e143aeaa14069cf35ee263","isNewRecord":false,"remarks":"","createDate":"2017-10-26 16:08:58","updateDate":"2017-10-26 16:08:58","value":"2","label":"纸质专票","type":"invoice_variety","description":"发票种类","sort":20,"parentId":"0"},{"id":"1d11920eee1b40db838b31bee72f7ae1","isNewRecord":false,"remarks":"","createDate":"2017-10-26 16:09:28","updateDate":"2017-10-26 16:09:28","value":"3","label":"电子普票","type":"invoice_variety","description":"发票种类","sort":30,"parentId":"0"}]
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

    public static class DataBean implements Serializable{
        /**
         * id : 34a7fa6314904a8bac27c949c17a4088
         * isNewRecord : false
         * remarks :
         * createDate : 2017-10-26 16:08:28
         * updateDate : 2017-10-26 16:08:28
         * value : 1
         * label : 纸质普票
         * type : invoice_variety
         * description : 发票种类
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
