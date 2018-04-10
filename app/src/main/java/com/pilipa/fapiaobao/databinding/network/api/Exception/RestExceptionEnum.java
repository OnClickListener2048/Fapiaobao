package com.pilipa.fapiaobao.databinding.network.api.Exception;

/**
 * Created by edz on 2018/4/10.
 */

public enum RestExceptionEnum {
    REQUEST_NULL(400, "没有找到业务数据"),
    SERVER_ERROR(500, "服务器异常"),
    BEAN_VALIDATE_ERROR(580, "绑定对象数据异常"),

    IS_YOUR_DEMAND(401, "不能给自己提供发票哦~"),
    DEADLINE_LIMIT(402, "截止日期不能小于系统当前时间"),
    INVALID_DEMAND_AMOUNT(403, "发票总额不能为空,且不能小于0"),
    DEMAND_BONUS_LIMIT(406, "红包不能超过需求总金额的5%"),
    DEMAND_INVOICE_AMOUNT_EMPTY(407, "您的发票总额过低，试试提供更大金额"),
    DEMAND_INVOICE_TYPE_EMPTY(408, "暂时没有此类需求，试试其它种类吧~"),
    DEMAND_INVOICE_CITY_EMPTY(409, "该地区暂没有需求哦，试试其它地区吧~"),
    DEMAND_INVOICE_ALL_EMPTY(410, "您的发票金额、选择的种类与地区暂时没有需求哦~"),

    INVALID_URL(800, "无效的URL"),
    AMOUNT_NOT_ENOUGH_FROZEN(885, "账户余额不足,因存在冻结金额导致"),
    MAILMINIMUM_NOT_ENOUGH(886, "票面总金额不足最小寄送金额"),
    DEMAND_BONUS_NOT_ENOUGH(887, "您的红包被抢走了"),
    AMOUNT_NOT_ENOUGH(888, "账户余额不足"),
    EXCEED_MAXIMUM_AMOUNT(889, "超出单次需求最大额度"),
    INVOICE_CONFIRM_DUPLICATE(891, "发票重复确认"),
    INVOICE_REJECT_DUPLICATE(892, "发票重复驳回"),

    TOKEN_EXPIRED(700, "token过期"),
    TOKEN_ERROR(701, "token验证失败"),

    BIND_TYPE_ERROR(702, "不支持的绑定类型"),
    UNBIND_TYPE_ERROR(703, "不支持的解绑类型"),

    AUTH_REQUEST_ERROR(704, "短信验证码错误"),
    AUTH_NULL_ERROR(705, "没有找到用户"),
    LOGIN_TYPE_ERROR(706, "不支持的登录方式"),
    BIND_WECHAT_EXIST(707, "绑定的微信已注册"),
    BIND_PHONE_EXIST(708, "绑定的手机号已注册"),
    WECHAT_BIND_NONE(709, "没有绑定微信用户"),
    WECHAT_BIND_ERROR(710, "绑定的微信用户与登录信息不匹配");

    private int friendlyCode;
    private String friendlyMsg;

    RestExceptionEnum(int code, String message) {
        this.friendlyCode = code;
        this.friendlyMsg = message;
    }

    public int getCode() {
        return friendlyCode;
    }

    public void setCode(int code) {
        this.friendlyCode = code;
    }

    public String getMessage() {
        return friendlyMsg;
    }

    public void setMessage(String message) {
        this.friendlyMsg = message;
    }

}
