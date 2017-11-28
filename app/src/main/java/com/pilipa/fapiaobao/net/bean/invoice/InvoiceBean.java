package com.pilipa.fapiaobao.net.bean.invoice;

/**
 * Created by edz on 2017/11/28.
 */

public class InvoiceBean {
    private String id;
    private String name;
    private String smallSize;
    private String middleSize;
    private String largeSize;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSmallSize() {
        return smallSize;
    }

    public void setSmallSize(String smallSize) {
        this.smallSize = smallSize;
    }

    public String getMiddleSize() {
        return middleSize;
    }

    public void setMiddleSize(String middleSize) {
        this.middleSize = middleSize;
    }

    public String getLargeSize() {
        return largeSize;
    }

    public void setLargeSize(String largeSize) {
        this.largeSize = largeSize;
    }
}
