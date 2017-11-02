package com.pilipa.fapiaobao.net;

/**
 * Created by lyt on 2017/10/12.
 */

public class Constant {
    public static final String BASE_URL = "http://192.168.1.205:8181/fapiaobao/rest/";
//    public static final String BASE_URL = "http://39.106.18.205/fapiaobao/rest/";
//    public static final String BASE_URL = "http://39.106.4.193:8080/fapiaobao/rest/";

    public static final int REQUEST_SUCCESS = 200;


    public static final String LOGIN_PLATFORM_MSG = "0";
    public static final String LOGIN_PLATFORM_WX= "1";
    public static final String LOGIN_PLATFORM_QQ = "2";
    //性别
    public static final String GENDER_MALE = "1";
    public static final String GENDER_FEMALE = "2";
    public static final String GENDER_SECRECY = "3";

    public static final String STATE_DEMAND_ING = "0";
    public static final String STATE_DEMAND_FINISH = "1";
    public static final String STATE_DEMAND_CLOSE    = "2";

    public static final String VARIETY_GENERAL_PAPER = "1";//纸质普票
    public static final String VARIETY_SPECIAL_PAPER = "2";//"纸质专票
    public static final String VARIETY_GENERAL_ELECTRON    = "3";//电子普票

    public static final String STATE_CONFIRMING = "1";//确认中
    public static final String STATE_COMPETENT = "2";//"查验合格
    public static final String STATE_INCOMPETENT    = "3";//查验不合格
    public static final String STATE_MAILING    = "4";//邮寄中

    public static final String STATE_FLYING    = "1";//红包飞来中
    public static final String STATE_GOT_ALL    = "2";//红包到帐
    public static final String STATE_GOT_PARTIALITY    = "3";//部分到帐
    public static final String STATE_GONE    = "4";//红包飞走了




    /**
     company-rest-controller : 代理公司
     */
    /*获取用户代理的公司列表*/
    public static final String COMPANIES_LIST = BASE_URL + "company/companies/%s";
    /*添加用户代理公司*/
    public static final String CREATE_COMPANY= BASE_URL + "company/create";
    /*删除用户代理公司*/
    public static final String DELETE_COMPANY = BASE_URL + "company/remove/%s/%s";
    /*根据companyId查询公司*/
    public static final String COMPANY_INFO = BASE_URL + "company/%s";

    /**
     *customer-rest-controller : 用户登录
     */

    /*绑定第三方平台用户*/
    public static final String BIND = BASE_URL + "customer/bind/%s";
    /*获取用户信用历史记录*/
    public static final String FIND_CREDIT_HISTORY = BASE_URL + "customer/findCreditHistory/%s/%s/%s";
    /*获取用户信用信息 */
    public static final String FIND_CREDIT_INFO = BASE_URL + "customer/findCreditInfo/%s";
    /*获取用户信用历史负面记录*/
    public static final String FIND_CREDIT_NEGATIVE_HISTORY = BASE_URL + "customer/findCreditNegativeHistory/%s/%s/%s";
    /*用户登录*/
    public static final String USER_LOGIN = BASE_URL + "customer/login/%s/%s/%s/%s";
    /*使用令牌登录*/
    public static final String LOGIN_BY_TOKEN = BASE_URL + "customer/loginByToken/%s";
    /*发送短信验证码 */
    public static final String SHORT_MESSAGE_VERIFY = BASE_URL + "customer/shortMessage/%s";
    /*解绑第三方平台用户*/
    public static final String UBIND = BASE_URL + "customer/unbind/%s/%s/%s";
    /*修改用户信息*/
    public static final String UPDATE_CUSTOMER = BASE_URL + "customer/updateCustomer";

    /**
     * demand-rest-controller : 需求发布
     */

    /*需求发布 */
    public static final String PUBLISH = BASE_URL+"/demand/publish";

    /**
     *favorite-rest-controller : 收藏公司
     */

    /**
     * http://192.168.1.205:8181/fapiaobao/rest/favorite/check/{companyId}/{token}
     */

    public static final String FAVORITE_COMPANY = BASE_URL+"favorite/check/%s/%s";


    /*获取用户收藏的公司列表 */
    public static final String FAVORITE_COMPANY_LIST = BASE_URL + "favorite/companies/%s";
    /*收藏需求开票的公司 */
    public static final String FAVORITE_COMPANY_CREATE= BASE_URL + "favorite/create";
    /*删除用户收藏公司 */
    public static final String FAVORITE_COMPANY_REMOVE= BASE_URL + "favorite/remove/%s/%s";

    /**
     *message-rest-controller : 消息中心
     */

    /*获取消息分类信息列表  */
    public static final String GET_MESSAGE_INFO_LIST= BASE_URL + "message/getMessageInfoList/%s/%s";
    /*获取消息分类列表   */
    public static final String GET_MESSAGE_LIST= BASE_URL + "message/getMessageList/%s";


    /**
     *my-publish-rest-controller : 我的发布
     */

    /*提前关闭需求 */
    public static final String SHAT_DOWN_EARLY= BASE_URL + "publish/close/%s/%s";
    /*获取用户发布的需求列表 */
    public static final String USER_ISSUED_LIST= BASE_URL + "publish/demands/%s/%s";
    /*获取用户发布的需求详细信息 */
    public static final String USER_ISSUED_DETAILS= BASE_URL + "publish/detail/%s/%s";

    /**
     *order-rest-controller : 员工待办
     */

    /*确认发票 */
    public static final String CONFIRM_INVOICE= BASE_URL + "order/confirmInvoice/%s/%s";
    /*创建订单  */
    public static final String CREATE_ORDER= BASE_URL + "order/createOrder/%s/%s/%s/%s";
    /*匹配需求 */
    public static final String DO_MATCH_DEMAND= BASE_URL + "order/doMatchDemand/%s/%s";
    /*邮寄发票 */
    public static final String MAIL_INVOICE= BASE_URL + "order/mailInvoice/%s/%s/%s/%s";
    /*我的发票列表 */
    public static final String MY_INVOICE_LIST= BASE_URL + "order/myInvoiceList/%s/";
    /*我的发票夹  */
    public static final String ORDER_LIST= BASE_URL + "order/orderList/%s/%s/%s";
    /*驳回发票 */
    public static final String REJECT_INVOICE= BASE_URL + "order/rejectInvoice/%s/%s/%s/%s";
    /*订单详情  */
    public static final String SHOW_ORDER_DETAIL= BASE_URL + "order/showOrderDetail/%s/%s";
    /*更新常用的发票类型排序 */
    public static final String UPDATE_INVOICE_TYPE= BASE_URL + "order/updateInvoiceType/%s/%s";
    /*上传发票 */
    public static final String UPLOAD_INVOICE= BASE_URL + "order/uploadInvoice";
    /*上传我的发票 */
    //http://192.168.1.205:8181/fapiaobao/rest/order/uploadMyInvoice
    public static final String UPLOAD_MY_INVOICE= BASE_URL + "order/uploadMyInvoice";

    /**
     * system-rest-controller : 系统管理
     */
    /*
    获取发票默认常用类型
     */

    public static final String FIND_DEFAULT_FREQUENTLY_INVOICE_TYPE = BASE_URL + "system/findDefaultFrequentlyInvoiceType";
    /*获取所有发票驳回类型*/
    public static final String FIND_ALL_REJECT_TYPE = BASE_URL + "system/findAllRejectType";

    /*
    获取用户常用类型
    获取用户发票常用类型
*/
    public static final String FIND_FREQUENTLY_INVOICE_TYPE = BASE_URL + "system/findFrequentlyInvoiceType/%s";

    /**
     * 获取所有发票种类
     * http://192.168.1.205:8181/fapiaobao/rest/system/findAllInvoiceVariety
     */

    public static final String FIND_ALL_INVOICE_VARIETY = BASE_URL+"system/findAllInvoiceVariety";


    /**
     * 获取所有物流公司
     */
    public static final String FIND_ALL_EXPRESS_COMPANY = BASE_URL+"system/findAllLogisticsCompany";


    //获取所有发票类型种类
    //http://192.168.1.205:8181/fapiaobao/rest/system/findAllInvoiceType
    public static final String FIND_ALL_INVIICE_TYPE = BASE_URL + "system/findAllInvoiceType";


    /**
     *test-rest-controller : 测试
     */


    /**
     * 微信支付
     */
    public static final String WX_RECHARGE = BASE_URL+"wxpay/recharge/%s/%s/%s";

    /**
     * 红包充入余额
     * http://192.168.1.205:8181/fapiaobao/rest/wxpay/bonusToAccount/{token}
     */

    public static final String RELOAD = BASE_URL+"wxpay/bonusToAccount/%s";


}
