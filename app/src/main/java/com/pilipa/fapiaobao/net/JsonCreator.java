package com.pilipa.fapiaobao.net;

import com.pilipa.fapiaobao.entity.Company;
import com.pilipa.fapiaobao.net.bean.LoginWithInfoBean;

import org.json.JSONObject;

import java.util.HashMap;

/**
 * Created by lyt on 2017/10/12.
 */

class JsonCreator {
    /**
     *
     * @param customer
     * @param token
     * @return
     */
    static JSONObject setCustomerData(LoginWithInfoBean.DataBean.CustomerBean customer, String token){
        HashMap<String, String> map = new HashMap<>();
        map.put("birthday",customer.getBirthday());
        map.put("gender",customer.getGender());
        map.put("headimg",customer.getHeadimg());
        map.put("nickname",customer.getNickname());
        map.put("telephone",customer.getTelephone());
        map.put("token",token);
        map.put("email",customer.getEmail());
        return new JSONObject(map);
    }

    /**
     *
     * @param company
     * @param token
     * @return
     */
    static JSONObject setCompanyData(Company company, String token){
        HashMap<String, String> map = new HashMap<>();
        map.put("account",company.getAccount());
        map.put("address",company.getAddress());
        map.put("depositBank",company.getDepositBank());
        map.put("name",company.getName());
        map.put("phone",company.getPhone());
        map.put("qrcode",company.getQrcode());
        map.put("taxno",company.getTaxno());
        map.put("token",token);
        return new JSONObject(map);
    }

    /**
     *
     * @param orderId
     * @param picture
     * @param variety
     * @param token
     * @return
     */
    static JSONObject setInvoice(String orderId, String picture, String variety, String token){
        HashMap<String, String> map = new HashMap<>();
        map.put("orderId",orderId);
        map.put("picture",picture);
        map.put("variety",variety);
        map.put("token",token);
        return new JSONObject(map);
    }

    /**
     *
     * @param reason
     * @return
     */
    static JSONObject setReject(String reason){
        HashMap<String, String> map = new HashMap<>();
        map.put("reason",reason);
        return new JSONObject(map);
    }

    /**
     *
     * @param suggestion
     * @return
     */
    static JSONObject suggestion(String suggestion){
        HashMap<String, String> map = new HashMap<>();
        map.put("suggestion",suggestion);
        return new JSONObject(map);
    }

    /**
     *
     * @param varieties
     * @param city
     * @param companyId
     * @return
     */
    static JSONObject matcher(String varieties, String city, String companyId) {
        HashMap<String, String> map = new HashMap<>();
        map.put("variety", varieties);
        map.put("companyId", companyId);
        map.put("city", city);
        return new JSONObject(map);
    }

    /**
     *
     * @param token
     * @return
     */
    static JSONObject allInvoice(String token) {
        HashMap<String, String> map = new HashMap<>();
        map.put("token", token);
        return new JSONObject(map);
    }

    /**
     *
     * @param id
     * @param suggestion
     * @param token
     * @return
     */
    static JSONObject suggestions(String id, String suggestion, String token) {
        HashMap<String, String> map = new HashMap<>();
        map.put("id", id);
        map.put("suggestion", suggestion);
        map.put("token",token);
        return new JSONObject(map);
    }


    public static JSONObject log(String log) {
        HashMap<String,String> map = new HashMap();
        map.put("logs", log);
        return new JSONObject(map);
    }
}
