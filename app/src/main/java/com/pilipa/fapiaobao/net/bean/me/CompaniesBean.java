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
     * data : [{"id":"43c8035b659a49fb8e464bc9d4333e0f","isNewRecord":false,"createDate":"2017-11-07 09:00:08","updateDate":"2017-11-07 09:00:08","customer":{"id":"91f5fa30a8f64d62a6bd17baaa14645d","isNewRecord":false,"amount":0,"bonus":0,"frozen":0,"creditScore":0,"creditLevel":0,"beginAmount":0,"endAmount":0,"beginBonus":0,"endBonus":0,"beginFrozen":0,"endFrozen":0},"name":"大理","taxno":"123456","address":"大理一夜","phone":"2934343","depositBank":"大女当嫁点解点解","account":"123456789"},{"id":"6a0ef1e7000442139e24c040730f8dd2","isNewRecord":false,"createDate":"2017-11-01 11:38:27","updateDate":"2017-11-01 11:38:27","customer":{"id":"91f5fa30a8f64d62a6bd17baaa14645d","isNewRecord":false,"amount":0,"bonus":0,"frozen":0,"creditScore":0,"creditLevel":0,"beginAmount":0,"endAmount":0,"beginBonus":0,"endBonus":0,"beginFrozen":0,"endFrozen":0},"name":"天津波康数码","taxno":"57568765828856","address":"天津和平","phone":"022-8758469","depositBank":"建设银行","account":"6825524665425853"},{"id":"856f48071da345b49781b38e03b06d57","isNewRecord":false,"createDate":"2017-11-07 09:00:13","updateDate":"2017-11-07 09:00:13","customer":{"id":"91f5fa30a8f64d62a6bd17baaa14645d","isNewRecord":false,"amount":0,"bonus":0,"frozen":0,"creditScore":0,"creditLevel":0,"beginAmount":0,"endAmount":0,"beginBonus":0,"endBonus":0,"beginFrozen":0,"endFrozen":0},"name":"大理","taxno":"123456","address":"大理一夜","phone":"2934343","depositBank":"大女当嫁点解点解","account":"123456789"},{"id":"fb736c0bed9949f7881dfc4e486805f2","isNewRecord":false,"createDate":"2017-11-02 10:30:11","updateDate":"2017-11-02 10:30:11","customer":{"id":"91f5fa30a8f64d62a6bd17baaa14645d","isNewRecord":false,"amount":0,"bonus":0,"frozen":0,"creditScore":0,"creditLevel":0,"beginAmount":0,"endAmount":0,"beginBonus":0,"endBonus":0,"beginFrozen":0,"endFrozen":0},"name":"柏迪士尼","taxno":"50578880058","address":"北京","phone":"022-585755","depositBank":"建设银行","account":"1867678580889"},{"id":"ff0bd16a16074b7a905e028bf2341a6f","isNewRecord":false,"createDate":"2017-11-02 10:32:16","updateDate":"2017-11-02 10:32:16","customer":{"id":"91f5fa30a8f64d62a6bd17baaa14645d","isNewRecord":false,"amount":0,"bonus":0,"frozen":0,"creditScore":0,"creditLevel":0,"beginAmount":0,"endAmount":0,"beginBonus":0,"endBonus":0,"beginFrozen":0,"endFrozen":0},"name":"天津同仁医院","taxno":"2685468542","address":"天津","phone":"55545599","depositBank":"建设银行","account":"4268568005799"}]
     */

    private int status;
    private String msg;
    private List<DataBean> data;

    protected CompaniesBean(Parcel in) {
        status = in.readInt();
        msg = in.readString();
        data = in.createTypedArrayList(DataBean.CREATOR);
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
        dest.writeTypedList(data);
    }

    public static class DataBean implements Parcelable{
        /**
         * id : 43c8035b659a49fb8e464bc9d4333e0f
         * isNewRecord : false
         * createDate : 2017-11-07 09:00:08
         * updateDate : 2017-11-07 09:00:08
         * customer : {"id":"91f5fa30a8f64d62a6bd17baaa14645d","isNewRecord":false,"amount":0,"bonus":0,"frozen":0,"creditScore":0,"creditLevel":0,"beginAmount":0,"endAmount":0,"beginBonus":0,"endBonus":0,"beginFrozen":0,"endFrozen":0}
         * name : 大理
         * taxno : 123456
         * address : 大理一夜
         * phone : 2934343
         * depositBank : 大女当嫁点解点解
         * account : 123456789
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
        }

        public static class CustomerBean {
            /**
             * id : 91f5fa30a8f64d62a6bd17baaa14645d
             * isNewRecord : false
             * amount : 0
             * bonus : 0
             * frozen : 0
             * creditScore : 0
             * creditLevel : 0
             * beginAmount : 0
             * endAmount : 0
             * beginBonus : 0
             * endBonus : 0
             * beginFrozen : 0
             * endFrozen : 0
             */

            private String id;
            private boolean isNewRecord;
            private int amount;
            private int bonus;
            private int frozen;
            private int creditScore;
            private int creditLevel;
            private int beginAmount;
            private int endAmount;
            private int beginBonus;
            private int endBonus;
            private int beginFrozen;
            private int endFrozen;

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

            public int getAmount() {
                return amount;
            }

            public void setAmount(int amount) {
                this.amount = amount;
            }

            public int getBonus() {
                return bonus;
            }

            public void setBonus(int bonus) {
                this.bonus = bonus;
            }

            public int getFrozen() {
                return frozen;
            }

            public void setFrozen(int frozen) {
                this.frozen = frozen;
            }

            public int getCreditScore() {
                return creditScore;
            }

            public void setCreditScore(int creditScore) {
                this.creditScore = creditScore;
            }

            public int getCreditLevel() {
                return creditLevel;
            }

            public void setCreditLevel(int creditLevel) {
                this.creditLevel = creditLevel;
            }

            public int getBeginAmount() {
                return beginAmount;
            }

            public void setBeginAmount(int beginAmount) {
                this.beginAmount = beginAmount;
            }

            public int getEndAmount() {
                return endAmount;
            }

            public void setEndAmount(int endAmount) {
                this.endAmount = endAmount;
            }

            public int getBeginBonus() {
                return beginBonus;
            }

            public void setBeginBonus(int beginBonus) {
                this.beginBonus = beginBonus;
            }

            public int getEndBonus() {
                return endBonus;
            }

            public void setEndBonus(int endBonus) {
                this.endBonus = endBonus;
            }

            public int getBeginFrozen() {
                return beginFrozen;
            }

            public void setBeginFrozen(int beginFrozen) {
                this.beginFrozen = beginFrozen;
            }

            public int getEndFrozen() {
                return endFrozen;
            }

            public void setEndFrozen(int endFrozen) {
                this.endFrozen = endFrozen;
            }
        }
    }
}
