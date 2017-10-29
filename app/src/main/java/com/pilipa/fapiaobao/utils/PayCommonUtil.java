package com.pilipa.fapiaobao.utils;

import android.util.Log;

import com.example.mylibrary.utils.NetworkUtils;
import com.pilipa.fapiaobao.net.bean.WXBean;
import com.pilipa.fapiaobao.wxapi.Constants;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

/**
 * Created by edz on 2017/10/28.
 */

public class PayCommonUtil {
    public static String TAG = "PayCommonUtil";

    //随机字符串生成
    public static String getRandomString(int length) { //length表示生成字符串的长度
        String base = "abcdefghijklmnopqrstuvwxyz0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(base.length());
            sb.append(base.charAt(number));
        }
        return sb.toString();
    }

    //请求xml组装
    public static String getRequestXml(SortedMap<String, String> parameters) {
        StringBuffer sb = new StringBuffer();
        sb.append("<xml>");
        Set es = parameters.entrySet();
        Iterator it = es.iterator();
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            String key = (String) entry.getKey();
            String value = (String) entry.getValue();
            if ("attach".equalsIgnoreCase(key) || "body".equalsIgnoreCase(key) || "sign".equalsIgnoreCase(key)) {
                sb.append("<" + key + ">" + "<![CDATA[" + value + "]]></" + key + ">");
            } else {
                sb.append("<" + key + ">" + value + "</" + key + ">");
            }
        }
        sb.append("</xml>");
        return sb.toString();
    }

    //生成签名
    public static String createSign(String characterEncoding, SortedMap<String, String> parameters) {
        StringBuffer sb = new StringBuffer();
        Set es = parameters.entrySet();
        Iterator it = es.iterator();
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            String k = (String) entry.getKey();
            Object v = entry.getValue();
            if (null != v && !"".equals(v)
                    && !"sign".equals(k) && !"key".equals(k)) {
                sb.append(k + "=" + v + "&");
            }
        }
        sb.append("key=" + Constants.KEY);
        String sign = MD5Util.MD5Encode(sb.toString(), characterEncoding).toUpperCase();
        return sign;
    }

    //请求方法
    public static String httpsRequest(String requestUrl, String requestMethod, String outputStr) {
        try {

            URL url = new URL(requestUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setUseCaches(false);
            // 设置请求方式（GET/POST）
            conn.setRequestMethod(requestMethod);
            conn.setRequestProperty("content-type", "application/x-www-form-urlencoded");
            // 当outputStr不为null时向输出流写数据
            if (null != outputStr) {
                OutputStream outputStream = conn.getOutputStream();
                // 注意编码格式
                outputStream.write(outputStr.getBytes("UTF-8"));
                outputStream.close();
            }
            // 从输入流读取返回内容
            InputStream inputStream = conn.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String str = null;
            StringBuffer buffer = new StringBuffer();
            while ((str = bufferedReader.readLine()) != null) {
                buffer.append(str);
            }
            // 释放资源
            bufferedReader.close();
            inputStreamReader.close();
            inputStream.close();
            inputStream = null;
            conn.disconnect();
            return buffer.toString();
        } catch (ConnectException ce) {
            System.out.println("连接超时：{}" + ce);
        } catch (Exception e) {
            System.out.println("https请求异常：{}" + e);
        }
        return null;
    }

    public static SortedMap<String, String> wxPrePay(String total_fee) {
        SortedMap<String, String> parameterMap = new TreeMap<String, String>();
        parameterMap.put("appid", Constants.APP_ID);
        parameterMap.put("mch_id", Constants.PARTNER_ID);
        parameterMap.put("nonce_str", PayCommonUtil.getRandomString(32));
        parameterMap.put("body", "发票宝-充值");
        parameterMap.put("out_trade_no", "1231235641895");
        parameterMap.put("total_fee", "1");
        parameterMap.put("spbill_create_ip", NetworkUtils.getIPAddress(true));
        parameterMap.put("notify_url", "http://wxpay.wxutil.com/pub_v2/pay/notify.v2.php");
        parameterMap.put("trade_type", "APP");
        parameterMap.put("sign", createSign("UTF-8", parameterMap));
        return parameterMap;
    }

    public static WXBean parseXml(String xml) {
        try {
            InputStream inputStream = new ByteArrayInputStream(xml.getBytes("UTF-8"));
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(inputStream);
            org.w3c.dom.Element documentElement = document.getDocumentElement();

//            NodeList xml1 = documentElement.getElementsByTagName("xml");
//            for (int i = 0; i < xml1.getLength(); i++) {
//                Node item = xml1.item(i);
//                String nodeName = item.getNodeName();
//                Log.d(TAG, "parseXml: nodeName" + nodeName);
//                String nodeValue = item.getNodeValue();
//                Log.d(TAG, "parseXml: nodeValue" + nodeValue);
//            }

//            NodeList return_code = documentElement.getElementsByTagName("return_code");
//            String nodeName = return_code.item(0).getNodeName();
//            String nodeValue = return_code.item(0).getNodeValue();
//            String getFirstChild = return_code.item(0).getFirstChild().getNodeValue();
//            Log.d(TAG, "parseXml: getFirstChild"+getFirstChild);
//            String getLastChild = return_code.item(0).getLastChild().getNodeValue();
//            Log.d(TAG, "parseXml: getLastChild"+getLastChild);
//            Log.d(TAG, "parseXml:nodeName "+nodeName);
//            Log.d(TAG, "parseXml:nodeValue "+nodeValue);
            WXBean wxBean = new WXBean();
            NodeList return_code = documentElement.getElementsByTagName("return_code");
            NodeList return_msg = documentElement.getElementsByTagName("return_msg");
            NodeList appid = documentElement.getElementsByTagName("appid");
            NodeList mch_id = documentElement.getElementsByTagName("mch_id");
            NodeList nonce_str = documentElement.getElementsByTagName("nonce_str");
            NodeList sign = documentElement.getElementsByTagName("sign");
            NodeList result_code = documentElement.getElementsByTagName("result_code");
            NodeList prepay_id = documentElement.getElementsByTagName("prepay_id");
            NodeList trade_type = documentElement.getElementsByTagName("trade_type");

            wxBean.setReturn_code(return_code.item(0).getFirstChild().getNodeValue());
            wxBean.setReturn_msg(return_msg.item(0).getFirstChild().getNodeValue());
            wxBean.setAppid(appid.item(0).getFirstChild().getNodeValue());
            wxBean.setMch_id(mch_id.item(0).getFirstChild().getNodeValue());
            wxBean.setNonce_str(nonce_str.item(0).getFirstChild().getNodeValue());
            wxBean.setReturn_code(result_code.item(0).getFirstChild().getNodeValue());
            wxBean.setPrepay_id(prepay_id.item(0).getFirstChild().getNodeValue());
            wxBean.setTrade_type(trade_type.item(0).getFirstChild().getNodeValue());

            Log.d(TAG, "parseXml: " + wxBean.toString());
            return wxBean;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }





}
