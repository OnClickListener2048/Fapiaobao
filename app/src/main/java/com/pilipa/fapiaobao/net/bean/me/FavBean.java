package com.pilipa.fapiaobao.net.bean.me;

import com.google.gson.annotations.SerializedName;

/**
 * Created by edz on 2017/10/29.
 */

public class FavBean {


    /**
     * status : 200
     * msg : OK
     */

    private int status;
    private String msg;
    @SerializedName("data")
    private String favoriteId;

    public String getFavoriteId() {
        return favoriteId;
    }

    public void setFavoriteId(String favoriteId) {
        this.favoriteId = favoriteId;
    }

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
}
