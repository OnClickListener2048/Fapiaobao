package com.pilipa.fapiaobao.net;

import android.support.annotation.NonNull;
import android.util.Log;

import com.example.mylibrary.utils.TLog;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;
import com.lzy.okgo.request.base.Request;
import com.pilipa.fapiaobao.base.BaseApplication;
import com.pilipa.fapiaobao.entity.Company;
import com.pilipa.fapiaobao.net.bean.LoginBean;
import com.pilipa.fapiaobao.net.bean.LoginWithInfoBean;
import com.pilipa.fapiaobao.net.bean.ShortMessageBean;
import com.pilipa.fapiaobao.net.bean.invoice.AllInvoiceType;
import com.pilipa.fapiaobao.net.bean.invoice.AllInvoiceVariety;
import com.pilipa.fapiaobao.net.bean.invoice.CompanyCollectBean;
import com.pilipa.fapiaobao.net.bean.invoice.DefaultInvoiceBean;
import com.pilipa.fapiaobao.net.bean.invoice.MacherBeanToken;
import com.pilipa.fapiaobao.net.bean.invoice.OrderBean;
import com.pilipa.fapiaobao.net.bean.invoice.UploadProcessing;
import com.pilipa.fapiaobao.net.bean.me.CompaniesBean;
import com.pilipa.fapiaobao.net.bean.me.CompanyDetailsBean;
import com.pilipa.fapiaobao.net.bean.me.CreditInfoBean;
import com.pilipa.fapiaobao.net.bean.me.FavoriteCompanyBean;
import com.pilipa.fapiaobao.net.bean.me.MyInvoiceListBean;
import com.pilipa.fapiaobao.net.bean.me.NegativeCreditInfoBean;
import com.pilipa.fapiaobao.net.bean.me.NormalBean;
import com.pilipa.fapiaobao.net.bean.me.OrderDetailsBean;
import com.pilipa.fapiaobao.net.bean.me.OrderListBean;
import com.pilipa.fapiaobao.net.bean.me.UpdateCustomerBean;
import com.pilipa.fapiaobao.net.bean.publish.BalanceBean;
import com.pilipa.fapiaobao.net.bean.publish.ConfirmInvoiceBean;
import com.pilipa.fapiaobao.net.bean.publish.DemandDetails;
import com.pilipa.fapiaobao.net.bean.publish.DemandsListBean;
import com.pilipa.fapiaobao.net.bean.publish.ExpressCompanyBean;
import com.pilipa.fapiaobao.net.bean.wx.PrepayBean;
import com.pilipa.fapiaobao.net.callback.JsonCallBack;
import com.pilipa.fapiaobao.utils.PayCommonUtil;
import com.pilipa.fapiaobao.wxapi.Constants;

import org.json.JSONObject;

import static com.pilipa.fapiaobao.net.Constant.BIND;
import static com.pilipa.fapiaobao.net.Constant.COMPANIES_LIST;
import static com.pilipa.fapiaobao.net.Constant.COMPANY_INFO;
import static com.pilipa.fapiaobao.net.Constant.CONFIRM_INVOICE;
import static com.pilipa.fapiaobao.net.Constant.CREATE_COMPANY;
import static com.pilipa.fapiaobao.net.Constant.CREATE_ORDER;
import static com.pilipa.fapiaobao.net.Constant.DELETE_COMPANY;
import static com.pilipa.fapiaobao.net.Constant.DO_MATCH_DEMAND;
import static com.pilipa.fapiaobao.net.Constant.FAVORITE_COMPANY;
import static com.pilipa.fapiaobao.net.Constant.FAVORITE_COMPANY_CREATE;
import static com.pilipa.fapiaobao.net.Constant.FAVORITE_COMPANY_LIST;
import static com.pilipa.fapiaobao.net.Constant.FAVORITE_COMPANY_REMOVE;
import static com.pilipa.fapiaobao.net.Constant.FIND_ALL_EXPRESS_COMPANY;
import static com.pilipa.fapiaobao.net.Constant.FIND_ALL_INVIICE_TYPE;
import static com.pilipa.fapiaobao.net.Constant.FIND_ALL_INVOICE_VARIETY;
import static com.pilipa.fapiaobao.net.Constant.FIND_CREDIT_HISTORY;
import static com.pilipa.fapiaobao.net.Constant.FIND_CREDIT_INFO;
import static com.pilipa.fapiaobao.net.Constant.FIND_CREDIT_NEGATIVE_HISTORY;
import static com.pilipa.fapiaobao.net.Constant.FIND_DEFAULT_FREQUENTLY_INVOICE_TYPE;
import static com.pilipa.fapiaobao.net.Constant.FIND_FREQUENTLY_INVOICE_TYPE;
import static com.pilipa.fapiaobao.net.Constant.LOGIN_BY_TOKEN;
import static com.pilipa.fapiaobao.net.Constant.MY_INVOICE_LIST;
import static com.pilipa.fapiaobao.net.Constant.ORDER_LIST;
import static com.pilipa.fapiaobao.net.Constant.PUBLISH;
import static com.pilipa.fapiaobao.net.Constant.REJECT_INVOICE;
import static com.pilipa.fapiaobao.net.Constant.RELOAD;
import static com.pilipa.fapiaobao.net.Constant.SHAT_DOWN_EARLY;
import static com.pilipa.fapiaobao.net.Constant.SHORT_MESSAGE_VERIFY;
import static com.pilipa.fapiaobao.net.Constant.SHOW_ORDER_DETAIL;
import static com.pilipa.fapiaobao.net.Constant.UBIND;
import static com.pilipa.fapiaobao.net.Constant.UPDATE_CUSTOMER;
import static com.pilipa.fapiaobao.net.Constant.UPDATE_INVOICE_TYPE;
import static com.pilipa.fapiaobao.net.Constant.UPLOAD_INVOICE;
import static com.pilipa.fapiaobao.net.Constant.UPLOAD_MY_INVOICE;
import static com.pilipa.fapiaobao.net.Constant.USER_ISSUED_DETAILS;
import static com.pilipa.fapiaobao.net.Constant.USER_ISSUED_LIST;
import static com.pilipa.fapiaobao.net.Constant.USER_LOGIN;
import static com.pilipa.fapiaobao.net.Constant.WX_RECHARGE;


/**
 * Created by lyt on 2017/10/12.
 */

public class Api {

    static String TAG = "api";

    public static void bindWX(String token, final BaseViewCallback baseViewCallback) {
        OkGo.<LoginBean>get(String.format(BIND, token)).execute(new JsonCallBack<LoginBean>(LoginBean.class) {
            @Override
            public void onSuccess(Response<LoginBean> response) {
                if ("OK".equals(response.body().getMsg())) {
                    baseViewCallback.setData(response.body());
                }
            }
        });
    }

    /**
     * 获取用户信用历史记录
     *
     * @param token
     * @param pageNo
     * @param pageSize
     * @param baseViewCallback
     */
    public static void findCreditHistory(String token, String pageNo, String pageSize, final BaseViewCallback baseViewCallback) {
        OkGo.<NegativeCreditInfoBean>get(String.format(FIND_CREDIT_HISTORY, token, pageNo, pageSize)).execute(new JsonCallBack<NegativeCreditInfoBean>(NegativeCreditInfoBean.class) {
            @Override
            public void onSuccess(Response<NegativeCreditInfoBean> response) {
                if ("OK".equals(response.body().getMsg())) {
                    baseViewCallback.setData(response.body());
                }
            }
        });
    }

    /**
     * 获取用户信用信息
     *
     * @param token
     * @param baseViewCallback
     */
    public static void findCreditInfo(String token, final BaseViewCallback baseViewCallback) {
        OkGo.<CreditInfoBean>get(String.format(FIND_CREDIT_INFO, token)).execute(new JsonCallBack<CreditInfoBean>(CreditInfoBean.class) {
            @Override
            public void onSuccess(Response<CreditInfoBean> response) {
                if ("OK".equals(response.body().getMsg())) {
                    baseViewCallback.setData(response.body());
                }
            }
        });
    }

    /**
     * 获取用户信用历史负面记录
     *
     * @param token
     * @param pageNo
     * @param pageSize
     * @param baseViewCallback
     */
    public static void findCreditNegativeHistory(String token, String pageNo, String pageSize, final BaseViewCallback baseViewCallback) {
        OkGo.<NegativeCreditInfoBean>get(String.format(FIND_CREDIT_NEGATIVE_HISTORY, token, pageNo, pageSize)).execute(new JsonCallBack<NegativeCreditInfoBean>(NegativeCreditInfoBean.class) {
            @Override
            public void onSuccess(Response<NegativeCreditInfoBean> response) {
                if ("OK".equals(response.body().getMsg())) {
                    baseViewCallback.setData(response.body());
                }
            }
        });
    }

    /**
     * 登录
     *
     * @param platform
     * @param credenceName
     * @param credenceCode
     * @param deviceToken
     * @param baseViewCallback
     */
    public static void login(String platform, String credenceName, String credenceCode, String deviceToken, final BaseViewCallback baseViewCallback) {
        OkGo.<LoginWithInfoBean>get(String.format(USER_LOGIN, platform, credenceName, credenceCode, deviceToken)).execute(new JsonCallBack<LoginWithInfoBean>(LoginWithInfoBean.class) {
            @Override
            public void onSuccess(Response<LoginWithInfoBean> response) {
                if ("OK".equals(response.body().getMsg())) {
                    baseViewCallback.setData(response.body());
                }

                if ("短信验证码错误".equals(response.body().getMsg()) && 704 == response.body().getStatus()) {
                    BaseApplication.showToast("验证码错误");
                }
            }
        });
    }

    public static void loginByToken(String token, final BaseViewCallback baseViewCallback) {
        OkGo.<LoginWithInfoBean>get(String.format(LOGIN_BY_TOKEN, token)).execute(new JsonCallBack<LoginWithInfoBean>(LoginWithInfoBean.class) {
            @Override
            public void onSuccess(Response<LoginWithInfoBean> response) {
                if (response.isSuccessful()) {
                    baseViewCallback.setData(response.body());
                }

            }
        });
    }

    /**
     * 发送短信验证码
     *
     * @param moible
     * @param baseViewCallback
     */
    public static void sendMessageToVerify(String moible, final BaseViewCallback baseViewCallback) {
        OkGo.<ShortMessageBean>get(String.format(SHORT_MESSAGE_VERIFY, moible)).execute(new JsonCallBack<ShortMessageBean>(ShortMessageBean.class) {
            @Override
            public void onSuccess(Response<ShortMessageBean> response) {
                if ("OK".equals(response.body().getMsg())) {
                    baseViewCallback.setData(response.body());
                }
            }
        });
    }

    public static void uBindWX(String token, final BaseViewCallback baseViewCallback) {
        OkGo.<LoginBean>get(String.format(UBIND, token)).execute(new JsonCallBack<LoginBean>(LoginBean.class) {
            @Override
            public void onSuccess(Response<LoginBean> response) {
                if ("OK".equals(response.body().getMsg())) {
                    baseViewCallback.setData(response.body());
                }
            }
        });
    }

    public static void updateCustomer(String token,LoginWithInfoBean.DataBean.CustomerBean customer, final BaseViewCallback baseViewCallback) {
        JSONObject data = JsonCreator.setCustomerData(customer, token);
        OkGo.<UpdateCustomerBean>post(UPDATE_CUSTOMER)
                .upJson(data)
                .execute(new JsonCallBack<UpdateCustomerBean>(UpdateCustomerBean.class) {
                    @Override
                    public void onSuccess(Response<UpdateCustomerBean> response) {
                        baseViewCallback.setData(response.body());
                    }
                });
    }


    /**
     * 获取用户代理的公司列表
     *
     * @param token
     * @param baseViewCallback
     */

    public static void companiesList(String token, final BaseViewCallback baseViewCallback) {
        OkGo.<CompaniesBean>get(String.format(COMPANIES_LIST, token)).execute(new JsonCallBack<CompaniesBean>(CompaniesBean.class) {
            @Override
            public void onSuccess(Response<CompaniesBean> response) {
                if ("OK".equals(response.body().getMsg())) {
                    baseViewCallback.setData(response.body());
                }
            }
        });
    }

    /**
     * 根据companyId查询公司
     *
     * @param id
     * @param baseViewCallback
     */
    public static void companyDetails(String id, final BaseViewCallback baseViewCallback) {
        OkGo.<CompanyDetailsBean>get(String.format(COMPANY_INFO, id)).execute(new JsonCallBack<CompanyDetailsBean>(CompanyDetailsBean.class) {
            @Override
            public void onSuccess(Response<CompanyDetailsBean> response) {
                if ("OK".equals(response.body().getMsg())) {
                    baseViewCallback.setData(response.body());
                }
            }
        });
    }

    /**
     * 添加用户代理公司
     *
     * @param company
     * @param token
     * @param baseViewCallback
     */
    public static void companyCreate(Company company, String token, final BaseViewCallback baseViewCallback) {
        JSONObject map = JsonCreator.setCompanyData(company, token);
        OkGo.<NormalBean>post(CREATE_COMPANY)
                .upJson(map)
                .execute(new JsonCallBack<NormalBean>(NormalBean.class) {
                    @Override
                    public void onSuccess(Response<NormalBean> response) {
                        if ("OK".equals(response.body().getMsg())) {
                            baseViewCallback.setData(response.body());
                        }
                    }
                });
    }

    /**
     * 删除用户代理公司
     *
     * @param id
     * @param token
     * @param baseViewCallback
     */
    public static void deleteCompany(String id, String token, final BaseViewCallback baseViewCallback) {
        OkGo.<NormalBean>delete(String.format(DELETE_COMPANY, id, token)).execute(new JsonCallBack<NormalBean>(NormalBean.class) {
            @Override
            public void onSuccess(Response<NormalBean> response) {
                if ("OK".equals(response.body().getMsg())) {
                    baseViewCallback.setData(response.body());
                }
            }
        });
    }

    /**
     * 获取用户收藏的公司列表
     *
     * @param token
     * @param baseViewCallback
     */
    public static void favoriteCompanyList(String token, final BaseViewCallback baseViewCallback) {
        OkGo.<FavoriteCompanyBean>get(String.format(FAVORITE_COMPANY_LIST, token)).execute(new JsonCallBack<FavoriteCompanyBean>(FavoriteCompanyBean.class) {
            @Override
            public void onSuccess(Response<FavoriteCompanyBean> response) {
                if ("OK".equals(response.body().getMsg())) {
                    baseViewCallback.setData(response.body());
                }
            }
        });
    }

    /**
     *  收藏需求开票的公司
     * @param favCompany
     * @param baseViewCallback
     */
    public static void favCompanyCreate(CompanyCollectBean favCompany, final BaseViewCallback baseViewCallback) {
        Gson gson = new Gson();
        String jsonFavCompany = gson.toJson(favCompany);
        OkGo.<NormalBean>post(FAVORITE_COMPANY_CREATE)
                .upJson(jsonFavCompany)
                .execute(new JsonCallBack<NormalBean>(NormalBean.class) {
                    @Override
                    public void onSuccess(Response<NormalBean> response) {
                        if ("OK".equals(response.body().getMsg())) {
                            baseViewCallback.setData(response.body());
                        }
                    }
                });
    }

    /**
     * 删除用户收藏公司
     *
     * @param id
     * @param token
     * @param baseViewCallback
     */
    public static void deleteFavoriteCompany(String id, String token, final BaseViewCallback baseViewCallback) {

        OkGo.<NormalBean>delete(String.format(FAVORITE_COMPANY_REMOVE, id, token))
                .execute(new JsonCallBack<NormalBean>(NormalBean.class) {
                    @Override
                    public void onSuccess(Response<NormalBean> response) {
                        if ("OK".equals(response.body().getMsg())) {
                            baseViewCallback.setData(response.body());
                        }
                    }
                });
    }

    /**
     * 获取用户发布的需求列表
     *
     * @param token
     * @param baseViewCallback
     */
    public static void demandsList(String token,String state, final BaseViewCallback baseViewCallback) {
        OkGo.<DemandsListBean>get(String.format(USER_ISSUED_LIST,state,token)).execute(new JsonCallBack<DemandsListBean>(DemandsListBean.class) {
            @Override
            public void onSuccess(Response<DemandsListBean> response) {
                if ("OK".equals(response.body().getMsg())) {
                    baseViewCallback.setData(response.body());
                }
            }
        });
    }

    /**
     * 获取用户发布的需求详细信息
     *
     * @param token
     * @param demandId
     * @param baseViewCallback
     */
    public static void demandDetails(String token, String demandId, final BaseViewCallback baseViewCallback) {
        OkGo.<DemandDetails>get(String.format(USER_ISSUED_DETAILS, demandId, token)).execute(new JsonCallBack<DemandDetails>(DemandDetails.class) {
            @Override
            public void onSuccess(Response<DemandDetails> response) {
                if ("OK".equals(response.body().getMsg())) {
                    baseViewCallback.setData(response.body());
                }
            }
        });
    }

    /**
     * 提前关闭需求
     * @param token
     * @param demandId
     * @param baseViewCallback
     */
    public static void shatDownEarly(String token, String demandId, final BaseViewCallback baseViewCallback) {
        OkGo.<NormalBean>get(String.format(SHAT_DOWN_EARLY,demandId,token)).execute(new JsonCallBack<NormalBean>(NormalBean.class) {
            @Override
            public void onSuccess(Response<NormalBean> response) {
                if ("OK".equals(response.body().getMsg())) {
                    baseViewCallback.setData(response.body());
                }
            }
        });
    }

    public static void wxPay(String total_fee, final BaseViewCallback baseViewCallback) {
        OkGo.<String>post(Constants.PREPAY_URL).upString(PayCommonUtil.getRequestXml(PayCommonUtil.wxPrePay(total_fee))).execute(new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                baseViewCallback.setData(response.body());
            }
        });
    }

    /**
     * @param baseViewCallback
     */
    public static void findAllInvoice(final BaseViewCallbackWithOnStart baseViewCallback) {
        OkGo.<AllInvoiceType>get(FIND_ALL_INVIICE_TYPE).execute(new JsonCallBack<AllInvoiceType>(AllInvoiceType.class) {
            @Override
            public void onSuccess(Response<AllInvoiceType> response) {
                if (response.isSuccessful() && response.body().getMsg().equals("OK")) {
                    baseViewCallback.setData(response.body());
                }


            }

            @Override
            public void onError(Response<AllInvoiceType> response) {
                super.onError(response);
                baseViewCallback.onError();
            }

            @Override
            public void onStart(Request<AllInvoiceType, ? extends Request> request) {
                super.onStart(request);
                baseViewCallback.onStart();
            }

            @Override
            public void onFinish() {
                super.onFinish();
                baseViewCallback.onFinish();

            }
        });
    }

    public static void estimateRedBag(HttpParams httpParams, final BaseViewCallbackWithOnStart baseViewCallback) {

    }

    /**
     * @param token
     * @param invoiceType
     * @param baseViewCallback
     */
    public static void updateInvoiceType(@NonNull String token, @NonNull String invoiceType, @NonNull final BaseViewCallback baseViewCallback) {
        String url = String.format(UPDATE_INVOICE_TYPE, token, invoiceType);
        OkGo.<String>get(url).execute(new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                if (response.isSuccessful()) {
                    baseViewCallback.setData(response.body());
                }
            }
        });
    }

    /**
     *
     * @param baseViewCallback
     */
    public static void findAllLogisticsCompany(final BaseViewCallback baseViewCallback) {
        OkGo.<ExpressCompanyBean>get(FIND_ALL_EXPRESS_COMPANY).execute(new JsonCallBack<ExpressCompanyBean>(ExpressCompanyBean.class) {
            @Override
            public void onSuccess(Response<ExpressCompanyBean> response) {
                if (response.isSuccessful()) {
                    if (response.body().getMsg().equals("OK")) {
                        baseViewCallback.setData(response.body());
                    }
                }
            }
        });
    }

    /**
     *
     * @param baseViewCallback
     * @param <T>
     */
    public static <T> void findDefaultInvoiceType(final BaseViewCallback baseViewCallback) {
        OkGo.<T>get(FIND_DEFAULT_FREQUENTLY_INVOICE_TYPE).execute(new JsonCallBack<T>(DefaultInvoiceBean.class) {
            @Override
            public void onSuccess(Response<T> response) {
                if (response.isSuccessful()) {
                    baseViewCallback.setData(response.body());
                }
            }
        });
    }

    /**
     *
     * @param token
     * @param baseViewCallback
     * @param <T>
     */
    public static <T> void findUserInvoiceType(String token, final BaseViewCallback baseViewCallback) {
        String url = String.format(FIND_FREQUENTLY_INVOICE_TYPE, token);
        OkGo.<T>get(url).execute(new JsonCallBack<T>(DefaultInvoiceBean.class) {
            @Override
            public void onSuccess(Response<T> response) {
                if (response.isSuccessful()) {
                    baseViewCallback.setData(response.body());
                }
            }
        });
    }
    //GET /rest/order/doMatchDemand/{invoiceType}/{amount}/{varieties}/{city}

    /**
     *
     * @param invoiceType
     * @param amount
     * @param varieties
     * @param city
     * @param baseViewCallback
     */
    public static void doMatchDemand(String invoiceType, Double amount, String varieties, String city, final BaseViewCallback baseViewCallback) {
        String url = String.format(DO_MATCH_DEMAND, invoiceType, amount);
//        OkGo.<MacherBeanToken>get(url).execute(new JsonCallBack<MacherBeanToken>(MacherBeanToken.class) {
//            @Override
//            public void onSuccess(Response<MacherBeanToken> response) {
//                if (response.isSuccessful() && response.body().getStatus() == 200) {
//                    Log.d(TAG, "doMatchDemandonSuccess: "+response.body());
//                    baseViewCallback.setData(response.body());
//                }
//            }
//
//        });

        OkGo.<MacherBeanToken>post(url)
                .upJson(JsonCreator.matcher(varieties,city))
                .execute(new JsonCallBack<MacherBeanToken>(MacherBeanToken.class) {
            @Override
            public void onSuccess(Response<MacherBeanToken> response) {
                if (response.isSuccessful()) {
                        Log.d(TAG, "doMatchDemandonSuccess: "+response.body());
                        baseViewCallback.setData(response.body());
                }
            }
        });
    }


    /**
     * 我的发票列表
     * @param token
     * @param baseViewCallback
     */
    public static void myInvoiceList(String token, final BaseViewCallback baseViewCallback) {
        OkGo.<MyInvoiceListBean>get(String.format(MY_INVOICE_LIST, token)).execute(new JsonCallBack<MyInvoiceListBean>(MyInvoiceListBean.class) {
            @Override
            public void onSuccess(Response<MyInvoiceListBean> response) {
                    baseViewCallback.setData(response.body());
            }
        });
    }

    /**
     * 我的发票夹
     * @param token
     * @param pageNo
     * @param pageSize
     * @param baseViewCallback
     */
    public static void orderList(String token,String pageNo,String pageSize, final BaseViewCallback baseViewCallback) {
        OkGo.<OrderListBean>get(String.format(ORDER_LIST, token,pageNo,pageSize)).execute(new JsonCallBack<OrderListBean>(OrderListBean.class) {
            @Override
            public void onSuccess(Response<OrderListBean> response) {
                if ("OK".equals(response.body().getMsg())) {
                    baseViewCallback.setData(response.body());
                }
            }
        });
    }
    public static void confirmInvoice(String token,String orderInvoiceId, final BaseViewCallback baseViewCallback) {
        OkGo.<ConfirmInvoiceBean>get(String.format(CONFIRM_INVOICE,token,orderInvoiceId)).execute(new JsonCallBack<ConfirmInvoiceBean>(ConfirmInvoiceBean.class) {
            @Override
            public void onSuccess(Response<ConfirmInvoiceBean> response) {
                if ("OK".equals(response.body().getMsg())) {
                    baseViewCallback.setData(response.body());
                }
            }
        });
    }
    public static void rejectInvoice(String token,String orderInvoiceId,String amount,String rejectType, final BaseViewCallback baseViewCallback) {
        JSONObject data = JsonCreator.setReject(token,orderInvoiceId,amount,rejectType);
        OkGo.<NormalBean>post(REJECT_INVOICE)
                .upJson(data)
                .execute(new JsonCallBack<NormalBean>(NormalBean.class) {
            @Override
            public void onSuccess(Response<NormalBean> response) {
                if ("OK".equals(response.body().getMsg())) {
                    baseViewCallback.setData(response.body());
                }
            }
        });
    }
    public static void showOrderDetail(String token,String orderID, final BaseViewCallback baseViewCallback) {
        OkGo.<OrderDetailsBean>get(String.format(SHOW_ORDER_DETAIL, token,orderID)).execute(new JsonCallBack<OrderDetailsBean>(OrderDetailsBean.class) {
            @Override
            public void onSuccess(Response<OrderDetailsBean> response) {
                if ("OK".equals(response.body().getMsg())) {
                    baseViewCallback.setData(response.body());
                }
            }
        });
    }
    public static void uploadInvoice(String token, String pic, final BaseViewCallback baseViewCallback) {
        JSONObject data = JsonCreator.setInvoice(null,pic,null,token);
        OkGo.<NormalBean>post(UPLOAD_INVOICE)
                .upJson(data)
                .execute(new JsonCallBack<NormalBean>(NormalBean.class) {
                    @Override
                    public void onSuccess(Response<NormalBean> response) {
                        baseViewCallback.setData(response.body());
                    }
                });
    }

    public static void wxRecharge(String token, String ip, double amount, final BaseViewCallback b) {
        String url = String.format(WX_RECHARGE, token, ip, amount);
        OkGo.<PrepayBean>get(url).execute(new JsonCallBack<PrepayBean>(PrepayBean.class) {

            @Override
            public void onSuccess(Response<PrepayBean> response) {
                if (response.isSuccessful() && response.body().getStatus() == 200) {
                    b.setData(response.body());
                }
            }
        });
    }


    public static void judgeCompanyIsCollcted(String companyId, String token, final BaseViewCallback baseViewCallback) {
        String url = String.format(FAVORITE_COMPANY, companyId, token);
        OkGo.<NormalBean>get(url).execute(new JsonCallBack<NormalBean>(NormalBean.class) {
            @Override
            public void onSuccess(Response<NormalBean> response) {
                baseViewCallback.setData(response.body());
            }
        });
    }

    //http://192.168.1.205:8181/fapiaobao/rest/order/createOrder/{token}/{demandId}/{invoiceType}/{amount}
    public static void createOrder(String token, String demandid, String invocieType, double amount, final BaseViewCallbackWithOnStart baseViewCallbackWithOnStart) {
        String url = String.format(CREATE_ORDER, token, demandid, invocieType, amount);
        OkGo.<OrderBean>get(url).execute(new JsonCallBack<OrderBean>(OrderBean.class) {

            @Override
            public void onSuccess(Response<OrderBean> response) {
                if (response.isSuccessful()) {
                    baseViewCallbackWithOnStart.setData(response.body());
                }
            }

            @Override
            public void onStart(Request<OrderBean, ? extends Request> request) {
                super.onStart(request);
                baseViewCallbackWithOnStart.onStart();
            }

            @Override
            public void onFinish() {
                super.onFinish();
                baseViewCallbackWithOnStart.onFinish();
            }
        });
    }

    /**
     * 获取所有发票种类
     */
    public static void findAllInvoiceVariety(final BaseViewCallback baseViewCallback) {
        OkGo.<AllInvoiceVariety>get(FIND_ALL_INVOICE_VARIETY).execute(new JsonCallBack<AllInvoiceVariety>(AllInvoiceVariety.class) {
            @Override
            public void onSuccess(Response<AllInvoiceVariety> response) {
                if (response.isSuccessful()&&response.body().getStatus()==200) {
                    if (response.body().getData()!= null&& response.body().getData().size()>0) {
                        baseViewCallback.setData(response.body());
                    }
                }
            }
        });
    }

    public static void uploadReceipt(String json, final BaseViewCallbackWithOnStart baseViewCallbackWithOnStart) {
        OkGo.<UploadProcessing>post(UPLOAD_INVOICE).upJson(json).execute(new JsonCallBack<UploadProcessing>(UploadProcessing.class) {
            @Override
            public void onSuccess(Response<UploadProcessing> response) {
                if (response.isSuccessful()) {
                    if (response.body().getStatus() == 200) {
                        BaseApplication.showToast("上传成功");
                        baseViewCallbackWithOnStart.setData(response.body());
                    }
                }

            }

            @Override
            public void onFinish() {
                super.onFinish();
                baseViewCallbackWithOnStart.onFinish();
            }

            @Override
            public void onStart(Request<UploadProcessing, ? extends Request> request) {
                super.onStart(request);
                baseViewCallbackWithOnStart.onStart();
            }

            @Override
            public void onError(Response<UploadProcessing> response) {
                super.onError(response);
                baseViewCallbackWithOnStart.onError();

            }
        });
    }

    public static void uploadLocalReceipt(String json) {
        OkGo.<String>post(UPLOAD_MY_INVOICE).upJson(json).execute(new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                TLog.log(response.body());
                BaseApplication.showToast("上传成功");
            }
        });
    }

    public static void publish(String json, final BaseViewCallbackWithOnStart baseViewCallbackWithOnStart) {
        OkGo.<BalanceBean>post(PUBLISH).upJson(json).execute(new JsonCallBack<BalanceBean>(BalanceBean.class) {

            @Override
            public void onStart(Request<BalanceBean, ? extends Request> request) {
                super.onStart(request);
                baseViewCallbackWithOnStart.onStart();
            }

            @Override
            public void onFinish() {
                super.onFinish();
                baseViewCallbackWithOnStart.onFinish();
            }

            @Override
            public void onSuccess(Response<BalanceBean> response) {
                if (response.isSuccessful()) {
                    baseViewCallbackWithOnStart.setData(response.body());
                }
            }
        });
    }

    public static void wxRecharge1(String token, String ip, double amount, final BaseViewCallback b) {
        String url = String.format(WX_RECHARGE, token, ip, amount);
        OkGo.<String>get(url).execute(new StringCallback() {

            @Override
            public void onSuccess(Response<String> response) {
                if (response.isSuccessful()) {
                   TLog.log(response.body());
                }
            }
        });
    }

    public static void reload(String token, final BaseViewCallback baseViewCallback) {
        String url = String.format(RELOAD, token);
        OkGo.<NormalBean>get(url).execute(new JsonCallBack<NormalBean>(NormalBean.class) {
            @Override
            public void onSuccess(Response<NormalBean> response) {
                baseViewCallback.setData(response.body());
            }
        });
    }


    public interface BaseViewCallback<T> {
        void setData(T t);
    }

    public interface BaseViewCallbackWithOnStart<T> extends BaseViewCallback<T> {
        void onStart();

        void onFinish();

        void onError();
    }


}
