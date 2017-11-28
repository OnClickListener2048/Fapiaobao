package com.pilipa.fapiaobao.net.bean.invoice;

import java.util.List;

/**
 * Created by edz on 2017/10/29.
 */

public class AllInvoiceType {



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

        private InvoiceCategoryBean invoiceCategory;
        private List<InvoiceTypeListBean> invoiceTypeList;

        public InvoiceCategoryBean getInvoiceCategory() {
            return invoiceCategory;
        }

        public void setInvoiceCategory(InvoiceCategoryBean invoiceCategory) {
            this.invoiceCategory = invoiceCategory;
        }

        public List<InvoiceTypeListBean> getInvoiceTypeList() {
            return invoiceTypeList;
        }

        public void setInvoiceTypeList(List<InvoiceTypeListBean> invoiceTypeList) {
            this.invoiceTypeList = invoiceTypeList;
        }

        public static class InvoiceCategoryBean {
            /**
             * id : e54654c7a5c2431080bbef2ccec8abae
             * isNewRecord : false
             * remarks :
             * createDate : 2017-10-25 22:09:16
             * updateDate : 2017-11-10 13:59:52
             * value : 10
             * label : 交通费
             * type : invoice_category
             * description : 发票类别
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

        public static class InvoiceTypeListBean {
            /**
             * id : 83ff72c08d6b4aac8f83df71b8283df9
             * isNewRecord : false
             * remarks :
             * createDate : 2017-10-26 09:40:28
             * updateDate : 2017-11-10 14:26:45
             * category : 10
             * name : 过路过桥费
             * smallSize :
             * middleSize : https://www.youpiao8.cn/fapiaobao/userfiles/1/files/personal/invoiceType/2017/10/receipt_007.png
             * largeSize :
             * categorySort : 10
             * frequentFlag : 1
             * frequentSort : 0
             * selectedFlag : 1
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
            private int categorySort;
            private String frequentFlag;
            private int frequentSort;
            private String selectedFlag;

            public boolean isSelected() {
                return isSelected;
            }

            public void setSelected(boolean selected) {
                isSelected = selected;
            }

            private boolean isSelected;

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

            public int getCategorySort() {
                return categorySort;
            }

            public void setCategorySort(int categorySort) {
                this.categorySort = categorySort;
            }

            public String getFrequentFlag() {
                return frequentFlag;
            }

            public void setFrequentFlag(String frequentFlag) {
                this.frequentFlag = frequentFlag;
            }

            public int getFrequentSort() {
                return frequentSort;
            }

            public void setFrequentSort(int frequentSort) {
                this.frequentSort = frequentSort;
            }

            public String getSelectedFlag() {
                return selectedFlag;
            }

            public void setSelectedFlag(String selectedFlag) {
                this.selectedFlag = selectedFlag;
            }
        }
    }
}
