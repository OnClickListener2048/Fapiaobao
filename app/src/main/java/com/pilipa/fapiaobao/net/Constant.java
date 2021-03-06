package com.pilipa.fapiaobao.net;

/**
 * Created by lyt on 2017/10/12.
 */

public class Constant {


    public static final String VERSION_BASE_URL = "https://www.youpiao8.cn";
    public static final String BASE_URL = VERSION_BASE_URL + "/fapiaobao/rest/";


    public static final int REQUEST_SUCCESS = 200;
    public static final int REQUEST_NO_CONTENT = 400;
    public static final int TOKEN_INVALIDE = 701;
    public static final int INSUFFICIENT_ACCOUNT = 888;
    /*登陆类型*/
    public static final String LOGIN_PLATFORM_MSG = "0";
    public static final String LOGIN_PLATFORM_WX= "1";
    public static final String LOGIN_PLATFORM_QQ = "2";
    /*性别*/
    public static final String GENDER_MALE = "1";
    public static final String GENDER_FEMALE = "2";
    public static final String GENDER_SECRECY = "3";
    /*我的发布 状态*/
    public static final String STATE_DEMAND_ING = "0";
    public static final String STATE_DEMAND_FINISH = "1";
    public static final String STATE_DEMAND_CLOSE    = "2";
    /*发票类型*/
    public static final String VARIETY_GENERAL_PAPER = "1";//纸质普票
    public static final String VARIETY_SPECIAL_PAPER = "2";//"纸质专票
    public static final String VARIETY_GENERAL_ELECTRON    = "3";//电子普票
    /*发票确认状态*/
    public static final String STATE_CONFIRMING = "1";//确认中
    public static final String STATE_COMPETENT = "2";//"查验合格
    public static final String STATE_INCOMPETENT    = "3";//查验不合格
    public static final String STATE_MAILING    = "4";//邮寄中
    /*消息中心*/
    public static final String MSG_TYPE_NEWCOME_INVOICE    = "1";//新到发票
    public static final String MSG_TYPE_GOT_BONUS    = "2";//红包到帐
    public static final String MSG_TYPE_INCOMPETENT_INVOICE    = "3";//不合格发票
    public static final String MSG_TYPE_SERVICE_NOTIFICATION    = "4";//服务通知
    /*红包状态*/
    public static final String STATE_FLYING    = "1";//红包飞来中
    public static final String STATE_GOT_ALL    = "2";//红包到帐
    public static final String STATE_GOT_PARTIALITY    = "3";//部分到帐
    public static final String STATE_GONE    = "4";//红包飞走了
    /*提现方式*/
    public static final String ACCOUNT_TYPE_WALLET = "1";//我的钱包
    public static final String ACCOUNT_TYPE_RED = "2";//"红包
    public static final String ACCOUNT_TYPE_WX    = "3";//微信账户
    /*消息中心 推送类型*/
    public static final String NEWCOME_INVOICE = "1";
    public static final String GOT_BONUS = "2";
    public static final String INCOMPETENT_INVOICE = "3";
    public static final String SERVICE_NOTIFICATION = "4";
    public static final String NEW_DEMAND = "5";
    public static final String INVOICE_POST = "6";
    public static final String INVOICE_BABY_RESPONSE = "7";
    public static final String WELCOME_NOTIFICATION = "8";





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
    /*根据companyId查询公司*/
    public static final String COMPANY_SEARCH = BASE_URL + "company/search/%s";

    /**
     *customer-rest-controller : 用户登录
     */

    /*绑定第三方平台用户*/
    public static final String BIND = BASE_URL + "customer/bind/%s/%s/%s/%s";
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
    /*发送短信验证码 */
    public static final String SUGGESTION = BASE_URL + "customer/suggestion";
    /*解绑第三方平台用户*/
    public static final String UBIND = BASE_URL + "customer/unbind/%s/%s/%s";
    /*修改用户信息*/
    public static final String UPDATE_CUSTOMER = BASE_URL + "customer/updateCustomer";
    /*退出登录*/
    public static final String LOGOUT_BY_TOKEN = BASE_URL + "customer/logoutByToken/%s";
    /*个人意见记录*/
    public static final String SUGGESTION_HISTORY = BASE_URL + "customer/findSuggestionHistory/%s/%s";
    /**
     * demand-rest-controller : 需求发布
     */

    /*需求发布 */
    public static final String PUBLISH = BASE_URL+"/demand/publish";

    /**
     *favorite-rest-controller : 收藏公司
     */


    /*查验是否收藏的公司 */
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

    /*获取消息详情  */
    public static final String MESSAGE_DETAILS= BASE_URL + "message/detail/%s/%s";
    /*获取消息列表+详情  */
    public static final String MESSAGE_MESSAGES= BASE_URL + "message/messages/%s";
    /*改变消息状态为已读   */
    public static final String MESSAGE_READ= BASE_URL + "message/read/%s/%s";
    /*删除   */
    public static final String MESSAGE_REMOVE= BASE_URL + "message/remove/%s/%s";

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
    public static final String CREATE_ORDER= BASE_URL + "order/createOrder";
    /*我的发票删除  */
    public static final String DELETE_MY_INVOICE= BASE_URL + "order/deleteMyInvoice/%s/%s";
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
    public static final String UPLOAD_MY_INVOICE= BASE_URL + "order/uploadMyInvoice";
    /*估算红包*/
    public static final String ESTIMATE= BASE_URL + "order/estimate/%s/%s/%s";
    /*确认开票*/
    public static final String CONFIRM_DEMAND= BASE_URL + "order/confirmDemand/%s/%s";
    /*pdf转换*/
    public static final String TRANSFORM_PDF= BASE_URL + "order/changePdfToJpg";
    /*上传pdf转化的图片至我的发票*/
    public static final String UPLOAD_PDF= BASE_URL + "order/uploadPdfToMyInvoice";
    /**
     * system-rest-controller : 系统管理
     */
    /*获取发票默认常用类型*/
    public static final String FIND_DEFAULT_FREQUENTLY_INVOICE_TYPE = BASE_URL + "system/findDefaultFrequentlyInvoiceType";
    /*获取所有发票驳回类型*/
    public static final String FIND_ALL_REJECT_TYPE = BASE_URL + "system/findAllRejectType";
    /*获取用户发票常用类型*/
    public static final String FIND_FREQUENTLY_INVOICE_TYPE = BASE_URL + "system/findFrequentlyInvoiceType/%s";
    /* 获取所有发票种类*/
    public static final String FIND_ALL_INVOICE_VARIETY = BASE_URL+"system/findAllInvoiceVariety";


    /*获取所有物流公司*/
    public static final String FIND_ALL_EXPRESS_COMPANY = BASE_URL+"system/findAllLogisticsCompany";
    /*获取所有发票类型种类 */
    public static final String FIND_ALL_INVIICE_TYPE = BASE_URL + "system/findAllInvoiceType";
    /*分享加积分*/
    public static final String SHARE_SCORE_ADD = BASE_URL + "share/credit/score/%s";


    /**
     *test-rest-controller : 测试
     */


    /**
     * 微信支付
     */
    public static final String WX_RECHARGE = BASE_URL+"wxpay/recharge/%s/%s/%s";
    /*明细*/
    public static final String AMOUNT_HISTORY = BASE_URL+"wxpay/amountHistory/%s/%s/%s";
    /*提现到余额*/
    public static final String RELOAD = BASE_URL+"wxpay/bonusToAccount/%s";
    /*提现*/
    public static final String WITHDRAW = BASE_URL+"wxpay/withdraw/%s/%s/%s/%s/%s";

    /**
     * 用户注册协议
     */
    public static final String REGISTRATION = VERSION_BASE_URL + "/fapiaobao/doc/registration";
    /**
     * 用户充值协议
     */
    public static final String RECHARGE = VERSION_BASE_URL + "/fapiaobao/doc/recharge";
    /**
     * 发票百科
     */
    public static final String WIKI = VERSION_BASE_URL + "/fapiaobao/wiki";
    /**
     * 发票宝匹配需求(分享,提供发票分享需要bonus参数)
     */
    public static final String MATCH = "https://www.youpiao8.cn/fapiaobao/guide/match";
    /**
     * 发票宝开票信息
     */
    public static final String BILLINGINFO = VERSION_BASE_URL + "/fapiaobao/share/billingInfo";
    /**
     * 发票宝使用说明
     */
    public static final String INSTRUCTION = VERSION_BASE_URL + "/fapiaobao/instruction";
    /**
     * 发票宝规则解读
     */
    public static final String READINGRULES = "https://www.youpiao8.cn/fapiaobao/readingRules";
    /**
     * 版本更新接口
     */
    public static final String URL_UPDATE = BASE_URL+"version/info";

    /**
     * 记录log
     */
    public static final String LOG_RECORD = BASE_URL+"log/android";

}
