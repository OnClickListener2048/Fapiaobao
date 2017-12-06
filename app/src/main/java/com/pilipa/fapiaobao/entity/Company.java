package com.pilipa.fapiaobao.entity;


import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;
import com.pilipa.fapiaobao.net.bean.me.FavoriteCompanyBean;

import java.util.List;

public class Company implements Parcelable{

    @SerializedName("id")
    private String id;
    @SerializedName("account")
    private String account;
    @SerializedName("address")
    private String address;
    @SerializedName("customer")
    private Customer customer;
    @SerializedName("depositBank")
    private String depositBank;
    @SerializedName("isDefault")
    private int isDefault;
    @SerializedName("name")
    private String name;
    @SerializedName("phone")
    private String phone;
    @SerializedName("qrcode")
    private String qrcode;
    @SerializedName("taxno")
    private String taxno;
    private List<?> InvoiceTypeList;

    public Company() {

    }

    protected Company(Parcel in) {
        id = in.readString();
        account = in.readString();
        address = in.readString();
        depositBank = in.readString();
        isDefault = in.readInt();
        name = in.readString();
        phone = in.readString();
        qrcode = in.readString();
        taxno = in.readString();
    }

    public static final Creator<Company> CREATOR = new Creator<Company>() {
        @Override
        public Company createFromParcel(Parcel in) {
            return new Company(in);
        }

        @Override
        public Company[] newArray(int size) {
            return new Company[size];
        }
    };

    public List<?> getInvoiceTypeList() {
        return InvoiceTypeList;
    }

    public void setInvoiceTypeList(List<?> invoiceTypeList) {
        InvoiceTypeList = invoiceTypeList;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public String getDepositBank() {
        return depositBank;
    }

    public void setDepositBank(String depositBank) {
        this.depositBank = depositBank;
    }

    public int getIsDefault() {
        return isDefault;
    }

    public void setIsDefault(int isDefault) {
        this.isDefault = isDefault;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getQrcode() {
        return qrcode;
    }

    public void setQrcode(String qrcode) {
        this.qrcode = qrcode;
    }

    public String getTaxno() {
        return taxno;
    }

    public void setTaxno(String taxno) {
        this.taxno = taxno;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(account);
        dest.writeString(address);
        dest.writeString(depositBank);
        dest.writeInt(isDefault);
        dest.writeString(name);
        dest.writeString(phone);
        dest.writeString(qrcode);
        dest.writeString(taxno);
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
