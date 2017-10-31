package com.pilipa.fapiaobao.net;

import com.pilipa.fapiaobao.entity.Company;
import com.pilipa.fapiaobao.net.bean.LoginWithInfoBean;

import org.json.JSONObject;

import java.util.HashMap;

/**
 * Created by lyt on 2017/10/12.
 */

public class JsonCreator {
    public static JSONObject setCustomerData(LoginWithInfoBean.DataBean.CustomerBean customer, String token){
        HashMap map = new HashMap();
        map.put("birthday",customer.getBirthday());
        map.put("gender",customer.getGender());
        map.put("headimg",customer.getHeadimg());
        map.put("nickname",customer.getNickname());
        map.put("telephone",customer.getTelephone());
        map.put("token",token);
        return new JSONObject(map);
    }
    public static JSONObject setCompanyData(Company company, String token){
        HashMap map = new HashMap();
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
    public static JSONObject setInvoice(String orderId,String picture,String variety,String token){
        HashMap map = new HashMap();
        map.put("orderId",orderId);
        map.put("picture",picture);
        map.put("variety",variety);
        map.put("token",token);
        return new JSONObject(map);
    }


}
