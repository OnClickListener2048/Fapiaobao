package com.pilipa.fapiaobao.databinding.network;

public class NetworkConfig {
    private static String BASE_URL;

    public static String getBaseUrl() {
        return BASE_URL;
    }

    public static void setBaseUrl(String url) {
        NetworkConfig.BASE_URL = url;
    }

}
