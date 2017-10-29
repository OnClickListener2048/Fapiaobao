package com.pilipa.fapiaobao.entity;


import com.google.gson.annotations.SerializedName;

public class Company {

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
}
