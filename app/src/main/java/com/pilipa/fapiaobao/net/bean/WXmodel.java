package com.pilipa.fapiaobao.net.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by edz on 2017/10/26.
 */

public class WXmodel implements Parcelable{

    /**
     * access_token : ACCESS_TOKEN
     * expires_in : 7200
     * refresh_token : REFRESH_TOKEN
     * openid : OPENID
     * scope : SCOPE
     * unionid : o6_bmasdasdsad6_2sgVt7hMZOPfL
     */

    private String access_token;
    private int expires_in;
    private String refresh_token;
    private String openid;
    private String scope;
    private String unionid;

    protected WXmodel(Parcel in) {
        access_token = in.readString();
        expires_in = in.readInt();
        refresh_token = in.readString();
        openid = in.readString();
        scope = in.readString();
        unionid = in.readString();
    }

    public static final Creator<WXmodel> CREATOR = new Creator<WXmodel>() {
        @Override
        public WXmodel createFromParcel(Parcel in) {
            return new WXmodel(in);
        }

        @Override
        public WXmodel[] newArray(int size) {
            return new WXmodel[size];
        }
    };

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public int getExpires_in() {
        return expires_in;
    }

    public void setExpires_in(int expires_in) {
        this.expires_in = expires_in;
    }

    public String getRefresh_token() {
        return refresh_token;
    }

    public void setRefresh_token(String refresh_token) {
        this.refresh_token = refresh_token;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public String getUnionid() {
        return unionid;
    }

    public void setUnionid(String unionid) {
        this.unionid = unionid;
    }

    @Override
    public String toString() {
        return "WXmodel{" +
                "access_token='" + access_token + '\'' +
                ", expires_in=" + expires_in +
                ", refresh_token='" + refresh_token + '\'' +
                ", openid='" + openid + '\'' +
                ", scope='" + scope + '\'' +
                ", unionid='" + unionid + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(access_token);
        dest.writeInt(expires_in);
        dest.writeString(refresh_token);
        dest.writeString(openid);
        dest.writeString(scope);
        dest.writeString(unionid);
    }
}
