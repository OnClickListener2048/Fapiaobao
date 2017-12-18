package com.pilipa.fapiaobao.ui.model;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by edz on 2017/10/20.
 */

public class Image implements Parcelable {
    public String id;
    public String path;
    public String name;
    public int position;
    public boolean isSelected = false;
    public boolean isFromNet;
    public boolean isCapture;
    public boolean isPdf;
    public String variety;
    public String state;
    public String logisticsCompany;
    public String logisticsTradeno;
    public Uri uri;
    public String amount;
    public String bonus;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public boolean isFromNet() {
        return isFromNet;
    }

    public void setFromNet(boolean fromNet) {
        isFromNet = fromNet;
    }

    public boolean isCapture() {
        return isCapture;
    }

    public void setCapture(boolean capture) {
        isCapture = capture;
    }

    public boolean isPdf() {
        return isPdf;
    }

    public void setPdf(boolean pdf) {
        isPdf = pdf;
    }

    public String getVariety() {
        return variety;
    }

    public void setVariety(String variety) {
        this.variety = variety;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getLogisticsCompany() {
        return logisticsCompany;
    }

    public void setLogisticsCompany(String logisticsCompany) {
        this.logisticsCompany = logisticsCompany;
    }

    public String getLogisticsTradeno() {
        return logisticsTradeno;
    }

    public void setLogisticsTradeno(String logisticsTradeno) {
        this.logisticsTradeno = logisticsTradeno;
    }

    public Uri getUri() {
        return uri;
    }

    public void setUri(Uri uri) {
        this.uri = uri;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getBonus() {
        return bonus;
    }

    public void setBonus(String bonus) {
        this.bonus = bonus;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public static Creator<Image> getCREATOR() {
        return CREATOR;
    }

    public String from;
    public String reason;
    public Image() {

    }


    protected Image(Parcel in) {
        id = in.readString();
        path = in.readString();
        name = in.readString();
        position = in.readInt();
        isSelected = in.readByte() != 0;
        isFromNet = in.readByte() != 0;
        isCapture = in.readByte() != 0;
        isPdf = in.readByte() != 0;
        variety = in.readString();
        state = in.readString();
        logisticsCompany = in.readString();
        logisticsTradeno = in.readString();
        uri = in.readParcelable(Uri.class.getClassLoader());
        amount = in.readString();
        bonus = in.readString();
        from = in.readString();
        reason = in.readString();
    }

    public static final Creator<Image> CREATOR = new Creator<Image>() {
        @Override
        public Image createFromParcel(Parcel in) {
            return new Image(in);
        }

        @Override
        public Image[] newArray(int size) {
            return new Image[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(path);
        dest.writeString(name);
        dest.writeInt(position);
        dest.writeByte((byte) (isSelected ? 1 : 0));
        dest.writeByte((byte) (isFromNet ? 1 : 0));
        dest.writeByte((byte) (isCapture ? 1 : 0));
        dest.writeByte((byte) (isPdf ? 1 : 0));
        dest.writeString(variety);
        dest.writeString(state);
        dest.writeString(logisticsCompany);
        dest.writeString(logisticsTradeno);
        dest.writeParcelable(uri, flags);
        dest.writeString(amount);
        dest.writeString(bonus);
        dest.writeString(from);
        dest.writeString(reason);
    }
}
