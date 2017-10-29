package com.pilipa.fapiaobao.entity;

/**
 * Created by edz on 2017/10/29.
 */

public class FavCompany {

    /**
     * company : {"id":"string"}
     * token : 27da7ebd-8982-4b39-b11d-973456a832af
     */

    private Company company;
    private String token;

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
