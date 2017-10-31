package com.pilipa.fapiaobao.net.bean.invoice;

/**
 * Created by edz on 2017/10/30.
 */

public class CompanyCollectBean {

    /**
     * company : {"id":"string"}
     */

    private CompanyBean company;
    private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public CompanyBean getCompany() {
        return company;
    }

    public void setCompany(CompanyBean company) {
        this.company = company;
    }

    public static class CompanyBean {
        /**
         * id : string
         */

        private String id;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }
    }
}
