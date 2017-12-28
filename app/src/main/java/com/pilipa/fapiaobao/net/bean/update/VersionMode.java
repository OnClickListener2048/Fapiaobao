package com.pilipa.fapiaobao.net.bean.update;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by edz on 2017/11/10.
 */

public class VersionMode implements Parcelable{

    /**
     * status : 200
     * msg : OK
     * data : {"id":"1a3d5fbe9b854281b2342d2b714d6f59","isNewRecord":false,"remarks":"hahahahhahaha","createDate":"2017-11-10 09:46:00","updateDate":"2017-11-10 09:46:00","url":"/fapiaobao/userfiles/1/files/version/2017/11/214319884360945.zip","version":"18","forced":"1"}
     */

    private int status;
    private String msg;
    private DataBean data;

    protected VersionMode(Parcel in) {
        status = in.readInt();
        msg = in.readString();
    }

    public static final Creator<VersionMode> CREATOR = new Creator<VersionMode>() {
        @Override
        public VersionMode createFromParcel(Parcel in) {
            return new VersionMode(in);
        }

        @Override
        public VersionMode[] newArray(int size) {
            return new VersionMode[size];
        }
    };

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
        dest.writeInt(status);
        dest.writeString(msg);
    }

    public static class DataBean implements Parcelable{
        /**
         * id : 1a3d5fbe9b854281b2342d2b714d6f59
         * isNewRecord : false
         * remarks : hahahahhahaha
         * createDate : 2017-11-10 09:46:00
         * updateDate : 2017-11-10 09:46:00
         * url : /fapiaobao/userfiles/1/files/version/2017/11/214319884360945.zip
         * version : 18
         * forced : 1
         */

        private String id;
        private boolean isNewRecord;
        private String remarks;
        private String createDate;
        private String updateDate;

        protected DataBean(Parcel in) {
            id = in.readString();
            isNewRecord = in.readByte() != 0;
            remarks = in.readString();
            createDate = in.readString();
            updateDate = in.readString();
            url = in.readString();
            size = in.readInt();
            version = in.readString();
            forced = in.readString();
        }

        public static final Creator<DataBean> CREATOR = new Creator<DataBean>() {
            @Override
            public DataBean createFromParcel(Parcel in) {
                return new DataBean(in);
            }

            @Override
            public DataBean[] newArray(int size) {
                return new DataBean[size];
            }
        };

        public int getSize() {
            return size;
        }

        public void setSize(int size) {
            this.size = size;
        }

        private String url;
        private int size;
        private String version;
        private String forced;

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
            dest.writeString(id);
            dest.writeByte((byte) (isNewRecord ? 1 : 0));
            dest.writeString(remarks);
            dest.writeString(createDate);
            dest.writeString(updateDate);
            dest.writeString(url);
            dest.writeInt(size);
            dest.writeString(version);
            dest.writeString(forced);
        }
    }
}
