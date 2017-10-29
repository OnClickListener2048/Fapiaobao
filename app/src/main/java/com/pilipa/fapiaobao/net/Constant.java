package com.pilipa.fapiaobao.net;

/**
 * Created by lyt on 2017/10/12.
 */

public class Constant {

    /**
     *
     company-rest-controller : 代理公司
     */


    /**
     *customer-rest-controller : 用户登录
     */
    public static final String LOGIN_PLATFORM_MSG = "0";
    public static final String LOGIN_PLATFORM_WX= "1";
    public static final String LOGIN_PLATFORM_QQ = "2";

    public static final String BASE_URL = "http://192.168.1.205:8181/fapiaobao/rest/";
    public static final String SHORT_MESSAGE_VERIFY = BASE_URL + "customer/shortMessage/%s";
    public static final String USER_LOGIN = BASE_URL + "customer/login/%s/%s/%s";

    /**
     *demand-rest-controller : 需求发布
     */

    /**
     *favorite-rest-controller : 收藏公司
     */

    /**
     *message-rest-controller : 消息中心
     */

    /**
     *my-publish-rest-controller : 我的发布
     */

    /**
     *order-rest-controller : 员工待办
     */

    /**
     * system-rest-controller : 系统管理
     */

    //获取所有发票类型种类
    //http://192.168.1.205:8181/fapiaobao/rest/system/findAllInvoiceType
    public static final String FIND_ALL_INVIICE_TYPE = BASE_URL + "system/findAllInvoiceType";

    /**
     *test-rest-controller : 测试
     */

}
