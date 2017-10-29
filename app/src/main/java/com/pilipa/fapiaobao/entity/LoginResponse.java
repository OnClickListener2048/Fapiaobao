package com.pilipa.fapiaobao.entity;


import com.google.gson.annotations.SerializedName;

public class LoginResponse {

    @SerializedName("id")
    private int id;

    @SerializedName("isNewRecord")
    private String isNewRecord;

    @SerializedName("createDate")
    private String createDate;

    @SerializedName("updateDate")
    private String updateDate;

    @SerializedName("telephone")
    private String telephone;

    @SerializedName("frequentType")
    private String frequentType;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIsNewRecord() {
        return isNewRecord;
    }

    public void setIsNewRecord(String isNewRecord) {
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

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getFrequentType() {
        return frequentType;
    }

    public void setFrequentType(String frequentType) {
        this.frequentType = frequentType;
    }
}
