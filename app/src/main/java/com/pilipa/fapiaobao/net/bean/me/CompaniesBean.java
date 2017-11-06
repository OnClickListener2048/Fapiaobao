package com.pilipa.fapiaobao.net.bean.me;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by edz on 2017/10/29.
 */

public class CompaniesBean implements Parcelable{

    /**
     * status : 200
     * msg : OK
     * data : [{"id":"1d660812a99e4b439cdcb4ed9946b271","isNewRecord":false,"createDate":"2017-10-28 15:10:22","updateDate":"2017-10-28 15:10:22","customer":{"id":"6ee15c894b1a435d9c24025b324e17f7","isNewRecord":false},"name":"天津爱丽丝有限公司","taxno":"4844645646464646464644","address":"天津市海河西路","phone":"022-18548488","depositBank":"建设银行","account":"8448489489485448456","qrcode":"www.baidu.com","isDefault":"0"}]
     */

    private int status;
    private String msg;
    private List<DataBean> data;

    protected CompaniesBean(Parcel in) {
        status = in.readInt();
        msg = in.readString();
    }

    public static final Creator<CompaniesBean> CREATOR = new Creator<CompaniesBean>() {
        @Override
        public CompaniesBean createFromParcel(Parcel in) {
            return new CompaniesBean(in);
        }

        @Override
        public CompaniesBean[] newArray(int size) {
            return new CompaniesBean[size];
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

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
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

    public static class DataBean implements Parcelable {
        /**
         * id : 1d660812a99e4b439cdcb4ed9946b271
         * isNewRecord : false
         * createDate : 2017-10-28 15:10:22
         * updateDate : 2017-10-28 15:10:22
         * customer : {"id":"6ee15c894b1a435d9c24025b324e17f7","isNewRecord":false}
         * name : 天津爱丽丝有限公司
         * taxno : 4844645646464646464644
         * address : 天津市海河西路
         * phone : 022-18548488
         * depositBank : 建设银行
         * account : 8448489489485448456
         * qrcode : www.baidu.com
         * isDefault : 0
         */

        private String id;
        private boolean isNewRecord;
        private String createDate;
        private String updateDate;
        private CustomerBean customer;
        private String name;
        private String taxno;
        private String address;
        private String phone;
        private String depositBank;
        private String account;
        private String qrcode;
        private String isDefault;

        protected DataBean(Parcel in) {
            id = in.readString();
            isNewRecord = in.readByte() != 0;
            createDate = in.readString();
            updateDate = in.readString();
            name = in.readString();
            taxno = in.readString();
            address = in.readString();
            phone = in.readString();
            depositBank = in.readString();
            account = in.readString();
            qrcode = in.readString();
            isDefault = in.readString();
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

        public CustomerBean getCustomer() {
            return customer;
        }

        public void setCustomer(CustomerBean customer) {
            this.customer = customer;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getTaxno() {
            return taxno;
        }

        public void setTaxno(String taxno) {
            this.taxno = taxno;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getDepositBank() {
            return depositBank;
        }

        public void setDepositBank(String depositBank) {
            this.depositBank = depositBank;
        }

        public String getAccount() {
            return account;
        }

        public void setAccount(String account) {
            this.account = account;
        }

        public String getQrcode() {
            return qrcode;
        }

        public void setQrcode(String qrcode) {
            this.qrcode = qrcode;
        }

        public String getIsDefault() {
            return isDefault;
        }

        public void setIsDefault(String isDefault) {
            this.isDefault = isDefault;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(id);
            dest.writeByte((byte) (isNewRecord ? 1 : 0));
            dest.writeString(createDate);
            dest.writeString(updateDate);
            dest.writeString(name);
            dest.writeString(taxno);
            dest.writeString(address);
            dest.writeString(phone);
            dest.writeString(depositBank);
            dest.writeString(account);
            dest.writeString(qrcode);
            dest.writeString(isDefault);
        }

        public static class CustomerBean implements Parcelable{
            /**
             * id : 6ee15c894b1a435d9c24025b324e17f7
             * isNewRecord : false
             */

            private String id;
            private boolean isNewRecord;

            protected CustomerBean(Parcel in) {
                id = in.readString();
                isNewRecord = in.readByte() != 0;
            }

            public static final Creator<CustomerBean> CREATOR = new Creator<CustomerBean>() {
                @Override
                public CustomerBean createFromParcel(Parcel in) {
                    return new CustomerBean(in);
                }

                @Override
                public CustomerBean[] newArray(int size) {
                    return new CustomerBean[size];
                }
            };

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

            @Override
            public int describeContents() {
                return 0;
            }

            @Override
            public void writeToParcel(Parcel dest, int flags) {
                dest.writeString(id);
                dest.writeByte((byte) (isNewRecord ? 1 : 0));
            }
        }
    }
}
