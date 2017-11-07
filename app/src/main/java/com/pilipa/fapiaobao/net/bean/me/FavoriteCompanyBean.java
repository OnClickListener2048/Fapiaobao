package com.pilipa.fapiaobao.net.bean.me;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by edz on 2017/10/29.
 */

public class FavoriteCompanyBean implements Parcelable{


    /**
     * status : 200
     * msg : OK
     * data : [{"id":"a5bc3de8279d4603968efd0c2f0b6007","isNewRecord":false,"createDate":"2017-11-06 14:37:51","updateDate":"2017-11-06 14:37:51","customer":{"id":"9bb6f5105c814bb580159e175fa1fae6","isNewRecord":false,"amount":0,"bonus":0,"frozen":0,"creditScore":0,"creditLevel":0,"beginAmount":0,"endAmount":0,"beginBonus":0,"endBonus":0,"beginFrozen":0,"endFrozen":0},"name":"北京同仁堂","taxno":"564564564564","address":"北京朝阳","phone":"022-1545454","depositBank":"中国农业银行","account":"256486456456456","qrcode":"w4dsadwda","invoiceTypeList":[{"id":"43f1faf2cfee4f508d02c36975dfa06d","isNewRecord":false,"name":"会议票","categorySort":0,"frequentSort":0}]},{"id":"ef1a3ae773894bc9abe6523451ee7eac","isNewRecord":false,"customer":{"id":"9bb6f5105c814bb580159e175fa1fae6","isNewRecord":false,"amount":0,"bonus":0,"frozen":0,"creditScore":0,"creditLevel":0,"beginAmount":0,"endAmount":0,"beginBonus":0,"endBonus":0,"beginFrozen":0,"endFrozen":0},"name":"河北牛逼有限公司","taxno":"456456465465456465456","address":"河北石家庄","phone":"15046670220","depositBank":"河北银行","account":"655486456486456456","qrcode":"www.baidu.com","invoiceTypeList":[{"id":"43f1faf2cfee4f508d02c36975dfa06d","isNewRecord":false,"name":"会议票","categorySort":0,"frequentSort":0},{"id":"83ff72c08d6b4aac8f83df71b8283df9","isNewRecord":false,"name":"过路过桥费票","categorySort":0,"frequentSort":0}]}]
     */

    private int status;
    private String msg;
    private List<DataBean> data;

    protected FavoriteCompanyBean(Parcel in) {
        status = in.readInt();
        msg = in.readString();
        data = in.createTypedArrayList(DataBean.CREATOR);
    }

    public static final Creator<FavoriteCompanyBean> CREATOR = new Creator<FavoriteCompanyBean>() {
        @Override
        public FavoriteCompanyBean createFromParcel(Parcel in) {
            return new FavoriteCompanyBean(in);
        }

        @Override
        public FavoriteCompanyBean[] newArray(int size) {
            return new FavoriteCompanyBean[size];
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

    public static class DataBean implements Parcelable {
        /**
         * id : a5bc3de8279d4603968efd0c2f0b6007
         * isNewRecord : false
         * createDate : 2017-11-06 14:37:51
         * updateDate : 2017-11-06 14:37:51
         * customer : {"id":"9bb6f5105c814bb580159e175fa1fae6","isNewRecord":false,"amount":0,"bonus":0,"frozen":0,"creditScore":0,"creditLevel":0,"beginAmount":0,"endAmount":0,"beginBonus":0,"endBonus":0,"beginFrozen":0,"endFrozen":0}
         * name : 北京同仁堂
         * taxno : 564564564564
         * address : 北京朝阳
         * phone : 022-1545454
         * depositBank : 中国农业银行
         * account : 256486456456456
         * qrcode : w4dsadwda
         * invoiceTypeList : [{"id":"43f1faf2cfee4f508d02c36975dfa06d","isNewRecord":false,"name":"会议票","categorySort":0,"frequentSort":0}]
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
        private List<InvoiceTypeListBean> invoiceTypeList;

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
            invoiceTypeList = in.createTypedArrayList(InvoiceTypeListBean.CREATOR);
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

        public List<InvoiceTypeListBean> getInvoiceTypeList() {
            return invoiceTypeList;
        }

        public void setInvoiceTypeList(List<InvoiceTypeListBean> invoiceTypeList) {
            this.invoiceTypeList = invoiceTypeList;
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
            dest.writeTypedList(invoiceTypeList);
        }

        public static class CustomerBean implements Parcelable {
            /**
             * id : 9bb6f5105c814bb580159e175fa1fae6
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

        public static class InvoiceTypeListBean implements Parcelable{
            /**
             * id : 43f1faf2cfee4f508d02c36975dfa06d
             * isNewRecord : false
             * name : 会议票
             * categorySort : 0
             * frequentSort : 0
             */

            private String id;
            private boolean isNewRecord;
            private String name;
            private int categorySort;
            private int frequentSort;

            protected InvoiceTypeListBean(Parcel in) {
                id = in.readString();
                isNewRecord = in.readByte() != 0;
                name = in.readString();
                categorySort = in.readInt();
                frequentSort = in.readInt();
            }

            public static final Creator<InvoiceTypeListBean> CREATOR = new Creator<InvoiceTypeListBean>() {
                @Override
                public InvoiceTypeListBean createFromParcel(Parcel in) {
                    return new InvoiceTypeListBean(in);
                }

                @Override
                public InvoiceTypeListBean[] newArray(int size) {
                    return new InvoiceTypeListBean[size];
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

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public int getCategorySort() {
                return categorySort;
            }

            public void setCategorySort(int categorySort) {
                this.categorySort = categorySort;
            }

            public int getFrequentSort() {
                return frequentSort;
            }

            public void setFrequentSort(int frequentSort) {
                this.frequentSort = frequentSort;
            }

            @Override
            public int describeContents() {
                return 0;
            }

            @Override
            public void writeToParcel(Parcel dest, int flags) {
                dest.writeString(id);
                dest.writeByte((byte) (isNewRecord ? 1 : 0));
                dest.writeString(name);
                dest.writeInt(categorySort);
                dest.writeInt(frequentSort);
            }
        }
    }
}
