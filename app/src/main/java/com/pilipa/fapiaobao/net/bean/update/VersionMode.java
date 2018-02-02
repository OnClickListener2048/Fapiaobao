package com.pilipa.fapiaobao.net.bean.update;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by edz on 2017/11/10.
 */

public class VersionMode implements Parcelable {


    public static final Parcelable.Creator<VersionMode> CREATOR = new Parcelable.Creator<VersionMode>() {
        @Override
        public VersionMode createFromParcel(Parcel source) {
            return new VersionMode(source);
        }

        @Override
        public VersionMode[] newArray(int size) {
            return new VersionMode[size];
        }
    };
    /**
     * status : 200
     * msg : OK
     * data : {"id":"3a00e98fa670452585df38a574fb01a2","isNewRecord":false,"remarks":"","createDate":"2018-01-30 14:37:15","updateDate":"2018-01-30 14:37:15","url":"/fapiaobao/userfiles/1/files/version/fapiaobao.apk","version":"100","systype":"0","size":8817402,"forced":"0"}
     */

    private int status;
    private String msg;
    private DataBean data;

    public VersionMode() {
    }

    protected VersionMode(Parcel in) {
        this.status = in.readInt();
        this.msg = in.readString();
        this.data = in.readParcelable(DataBean.class.getClassLoader());
    }

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.status);
        dest.writeString(this.msg);
        dest.writeParcelable(this.data, flags);
    }

    public static class DataBean implements Parcelable {
        public static final Creator<DataBean> CREATOR = new Creator<DataBean>() {
            @Override
            public DataBean createFromParcel(Parcel source) {
                return new DataBean(source);
            }

            @Override
            public DataBean[] newArray(int size) {
                return new DataBean[size];
            }
        };
        /**
         * id : 3a00e98fa670452585df38a574fb01a2
         * isNewRecord : false
         * remarks :
         * createDate : 2018-01-30 14:37:15
         * updateDate : 2018-01-30 14:37:15
         * url : /fapiaobao/userfiles/1/files/version/fapiaobao.apk
         * version : 100
         * systype : 0
         * size : 8817402
         * forced : 0
         */

        private String id;
        private boolean isNewRecord;
        private String remarks;
        private String createDate;
        private String updateDate;
        private String url;
        private String version;
        private String systype;
        private int size;
        private String forced;

        public DataBean() {
        }

        protected DataBean(Parcel in) {
            this.id = in.readString();
            this.isNewRecord = in.readByte() != 0;
            this.remarks = in.readString();
            this.createDate = in.readString();
            this.updateDate = in.readString();
            this.url = in.readString();
            this.version = in.readString();
            this.systype = in.readString();
            this.size = in.readInt();
            this.forced = in.readString();
        }

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

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getVersion() {
            return version;
        }

        public void setVersion(String version) {
            this.version = version;
        }

        public String getSystype() {
            return systype;
        }

        public void setSystype(String systype) {
            this.systype = systype;
        }

        public int getSize() {
            return size;
        }

        public void setSize(int size) {
            this.size = size;
        }

        public String getForced() {
            return forced;
        }

        public void setForced(String forced) {
            this.forced = forced;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.id);
            dest.writeByte(this.isNewRecord ? (byte) 1 : (byte) 0);
            dest.writeString(this.remarks);
            dest.writeString(this.createDate);
            dest.writeString(this.updateDate);
            dest.writeString(this.url);
            dest.writeString(this.version);
            dest.writeString(this.systype);
            dest.writeInt(this.size);
            dest.writeString(this.forced);
        }
    }
}
