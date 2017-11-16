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
     * data : [{"id":"a9380222214c4ef9aa5530875db1dce0","isNewRecord":false,"createDate":"2017-11-16 15:19:47","updateDate":"2017-11-16 15:19:47","customer":{"id":"2c11bbe4a22d4e4dbeb94afee7e5fd08","isNewRecord":false,"amount":0,"bonus":0,"frozen":0,"creditScore":0,"creditLevel":0,"beginAmount":0,"endAmount":0,"beginBonus":0,"endBonus":0,"beginFrozen":0,"endFrozen":0},"name":"大王鑫公司","taxno":"0123456789","address":"王大鑫道五号","phone":"9876543210","depositBank":"天津建设银行","account":"123456789321564","qrcode":"http://39.106.4.193/fapiaobao/fapiaobao/share/billingInfo?id=a9380222214c4ef9aa5530875db1dce0&name=大王鑫公司&taxno=0123456789&address=王大鑫道五号&phone=9876543210&depositBank=天津建设银行&account=123456789321564"}]
     */

    private int status;
    private String msg;
    private List<DataBean> data;

    protected CompaniesBean(Parcel in) {
        status = in.readInt();
        msg = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(status);
        dest.writeString(msg);
    }

    @Override
    public int describeContents() {
        return 0;
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

    public static class DataBean implements Parcelable{
        /**
         * id : a9380222214c4ef9aa5530875db1dce0
         * isNewRecord : false
         * createDate : 2017-11-16 15:19:47
         * updateDate : 2017-11-16 15:19:47
         * customer : {"id":"2c11bbe4a22d4e4dbeb94afee7e5fd08","isNewRecord":false,"amount":0,"bonus":0,"frozen":0,"creditScore":0,"creditLevel":0,"beginAmount":0,"endAmount":0,"beginBonus":0,"endBonus":0,"beginFrozen":0,"endFrozen":0}
         * name : 大王鑫公司
         * taxno : 0123456789
         * address : 王大鑫道五号
         * phone : 9876543210
         * depositBank : 天津建设银行
         * account : 123456789321564
         * qrcode : http://39.106.4.193/fapiaobao/fapiaobao/share/billingInfo?id=a9380222214c4ef9aa5530875db1dce0&name=大王鑫公司&taxno=0123456789&address=王大鑫道五号&phone=9876543210&depositBank=天津建设银行&account=123456789321564
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

        protected DataBean(Parcel in) {
            id = in.readString();
            isNewRecord = in.readByte() != 0;
            createDate = in.readString();
            updateDate = in.readString();
            customer = in.readParcelable(CustomerBean.class.getClassLoader());
            name = in.readString();
            taxno = in.readString();
            address = in.readString();
            phone = in.readString();
            depositBank = in.readString();
            account = in.readString();
            qrcode = in.readString();
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
            dest.writeParcelable(customer, flags);
            dest.writeString(name);
            dest.writeString(taxno);
            dest.writeString(address);
            dest.writeString(phone);
            dest.writeString(depositBank);
            dest.writeString(account);
            dest.writeString(qrcode);
        }

        public static class CustomerBean implements Parcelable{
            /**
             * id : 2c11bbe4a22d4e4dbeb94afee7e5fd08
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

            protected CustomerBean(Parcel in) {
                id = in.readString();
                isNewRecord = in.readByte() != 0;
                amount = in.readInt();
                bonus = in.readInt();
                frozen = in.readInt();
                creditScore = in.readInt();
                creditLevel = in.readInt();
                beginAmount = in.readInt();
                endAmount = in.readInt();
                beginBonus = in.readInt();
                endBonus = in.readInt();
                beginFrozen = in.readInt();
                endFrozen = in.readInt();
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

            @Override
            public int describeContents() {
                return 0;
            }

            @Override
            public void writeToParcel(Parcel dest, int flags) {
                dest.writeString(id);
                dest.writeByte((byte) (isNewRecord ? 1 : 0));
                dest.writeInt(amount);
                dest.writeInt(bonus);
                dest.writeInt(frozen);
                dest.writeInt(creditScore);
                dest.writeInt(creditLevel);
                dest.writeInt(beginAmount);
                dest.writeInt(endAmount);
                dest.writeInt(beginBonus);
                dest.writeInt(endBonus);
                dest.writeInt(beginFrozen);
                dest.writeInt(endFrozen);
            }
        }
    }
}
