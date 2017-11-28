package com.pilipa.fapiaobao.net;

import android.support.annotation.NonNull;
import android.util.Log;

import com.example.mylibrary.utils.TLog;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.FileCallback;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Progress;
import com.lzy.okgo.model.Response;
import com.lzy.okgo.request.base.Request;
import com.pilipa.fapiaobao.base.BaseApplication;
import com.pilipa.fapiaobao.base.BaseFragment;
import com.pilipa.fapiaobao.entity.Company;
import com.pilipa.fapiaobao.net.bean.LoginBean;
import com.pilipa.fapiaobao.net.bean.LoginWithInfoBean;
import com.pilipa.fapiaobao.net.bean.RejectTypeBean;
import com.pilipa.fapiaobao.net.bean.ShortMessageBean;
import com.pilipa.fapiaobao.net.bean.invoice.AllInvoiceType;
import com.pilipa.fapiaobao.net.bean.invoice.AllInvoiceVariety;
import com.pilipa.fapiaobao.net.bean.invoice.CompanyCollectBean;
import com.pilipa.fapiaobao.net.bean.invoice.DefaultInvoiceBean;
import com.pilipa.fapiaobao.net.bean.invoice.MacherBeanToken;
import com.pilipa.fapiaobao.net.bean.invoice.OrderBean;
import com.pilipa.fapiaobao.net.bean.invoice.RedBagBean;
import com.pilipa.fapiaobao.net.bean.invoice.UploadProcessing;
import com.pilipa.fapiaobao.net.bean.me.AmountHistoryBean;
import com.pilipa.fapiaobao.net.bean.me.CompaniesBean;
import com.pilipa.fapiaobao.net.bean.me.CompanyDetailsBean;
import com.pilipa.fapiaobao.net.bean.me.CreditHistroyBean;
import com.pilipa.fapiaobao.net.bean.me.CreditInfoBean;
import com.pilipa.fapiaobao.net.bean.me.FavBean;
import com.pilipa.fapiaobao.net.bean.me.FavoriteCompanyBean;
import com.pilipa.fapiaobao.net.bean.me.FeedBackBean;
import com.pilipa.fapiaobao.net.bean.me.MessageDetailsBean;
import com.pilipa.fapiaobao.net.bean.me.MessageListBean;
import com.pilipa.fapiaobao.net.bean.me.MyInvoiceListBean;
import com.pilipa.fapiaobao.net.bean.me.NegativeCreditInfoBean;
import com.pilipa.fapiaobao.net.bean.me.NormalBean;
import com.pilipa.fapiaobao.net.bean.me.OrderDetailsBean;
import com.pilipa.fapiaobao.net.bean.me.OrderListBean;
import com.pilipa.fapiaobao.net.bean.me.RejectInvoiceBean;
import com.pilipa.fapiaobao.net.bean.me.UpdateCustomerBean;
import com.pilipa.fapiaobao.net.bean.publish.BalanceBean;
import com.pilipa.fapiaobao.net.bean.publish.ConfirmInvoiceBean;
import com.pilipa.fapiaobao.net.bean.publish.DemandDetails;
import com.pilipa.fapiaobao.net.bean.publish.DemandsListBean;
import com.pilipa.fapiaobao.net.bean.publish.ExpressCompanyBean;
import com.pilipa.fapiaobao.net.bean.update.VersionMode;
import com.pilipa.fapiaobao.net.bean.wx.PrepayBean;
import com.pilipa.fapiaobao.net.callback.JsonCallBack;
import com.pilipa.fapiaobao.utils.PayCommonUtil;
import com.pilipa.fapiaobao.utils.TDevice;
import com.pilipa.fapiaobao.wxapi.Constants;

import org.json.JSONObject;

import java.io.File;

import static com.pilipa.fapiaobao.net.Constant.AMOUNT_HISTORY;
import static com.pilipa.fapiaobao.net.Constant.BIND;
import static com.pilipa.fapiaobao.net.Constant.COMPANIES_LIST;
import static com.pilipa.fapiaobao.net.Constant.COMPANY_INFO;
import static com.pilipa.fapiaobao.net.Constant.CONFIRM_DEMAND;
import static com.pilipa.fapiaobao.net.Constant.CONFIRM_INVOICE;
import static com.pilipa.fapiaobao.net.Constant.CREATE_COMPANY;
import static com.pilipa.fapiaobao.net.Constant.CREATE_ORDER;
import static com.pilipa.fapiaobao.net.Constant.DELETE_COMPANY;
import static com.pilipa.fapiaobao.net.Constant.DELETE_MY_INVOICE;
import static com.pilipa.fapiaobao.net.Constant.DO_MATCH_DEMAND;
import static com.pilipa.fapiaobao.net.Constant.ESTIMATE;
import static com.pilipa.fapiaobao.net.Constant.FAVORITE_COMPANY;
import static com.pilipa.fapiaobao.net.Constant.FAVORITE_COMPANY_CREATE;
import static com.pilipa.fapiaobao.net.Constant.FAVORITE_COMPANY_LIST;
import static com.pilipa.fapiaobao.net.Constant.FAVORITE_COMPANY_REMOVE;
import static com.pilipa.fapiaobao.net.Constant.FIND_ALL_EXPRESS_COMPANY;
import static com.pilipa.fapiaobao.net.Constant.FIND_ALL_INVIICE_TYPE;
import static com.pilipa.fapiaobao.net.Constant.FIND_ALL_INVOICE_VARIETY;
import static com.pilipa.fapiaobao.net.Constant.FIND_ALL_REJECT_TYPE;
import static com.pilipa.fapiaobao.net.Constant.FIND_CREDIT_HISTORY;
import static com.pilipa.fapiaobao.net.Constant.FIND_CREDIT_INFO;
import static com.pilipa.fapiaobao.net.Constant.FIND_CREDIT_NEGATIVE_HISTORY;
import static com.pilipa.fapiaobao.net.Constant.FIND_DEFAULT_FREQUENTLY_INVOICE_TYPE;
import static com.pilipa.fapiaobao.net.Constant.FIND_FREQUENTLY_INVOICE_TYPE;
import static com.pilipa.fapiaobao.net.Constant.LOGIN_BY_TOKEN;
import static com.pilipa.fapiaobao.net.Constant.LOGOUT_BY_TOKEN;
import static com.pilipa.fapiaobao.net.Constant.MAIL_INVOICE;
import static com.pilipa.fapiaobao.net.Constant.MESSAGE_DETAILS;
import static com.pilipa.fapiaobao.net.Constant.MESSAGE_MESSAGES;
import static com.pilipa.fapiaobao.net.Constant.MESSAGE_READ;
import static com.pilipa.fapiaobao.net.Constant.MESSAGE_REMOVE;
import static com.pilipa.fapiaobao.net.Constant.MY_INVOICE_LIST;
import static com.pilipa.fapiaobao.net.Constant.ORDER_LIST;
import static com.pilipa.fapiaobao.net.Constant.PUBLISH;
import static com.pilipa.fapiaobao.net.Constant.REJECT_INVOICE;
import static com.pilipa.fapiaobao.net.Constant.RELOAD;
import static com.pilipa.fapiaobao.net.Constant.SHAT_DOWN_EARLY;
import static com.pilipa.fapiaobao.net.Constant.SHORT_MESSAGE_VERIFY;
import static com.pilipa.fapiaobao.net.Constant.SHOW_ORDER_DETAIL;
import static com.pilipa.fapiaobao.net.Constant.SUGGESTION;
import static com.pilipa.fapiaobao.net.Constant.UBIND;
import static com.pilipa.fapiaobao.net.Constant.UPDATE_CUSTOMER;
import static com.pilipa.fapiaobao.net.Constant.UPDATE_INVOICE_TYPE;
import static com.pilipa.fapiaobao.net.Constant.UPLOAD_INVOICE;
import static com.pilipa.fapiaobao.net.Constant.UPLOAD_MY_INVOICE;
import static com.pilipa.fapiaobao.net.Constant.URL_UPDATE;
import static com.pilipa.fapiaobao.net.Constant.USER_ISSUED_DETAILS;
import static com.pilipa.fapiaobao.net.Constant.USER_ISSUED_LIST;
import static com.pilipa.fapiaobao.net.Constant.USER_LOGIN;
import static com.pilipa.fapiaobao.net.Constant.WITHDRAW;
import static com.pilipa.fapiaobao.net.Constant.WX_RECHARGE;


/**
 * Created by lyt on 2017/10/12.
 */

public class Api {

    static String TAG = "api";

    public static void bindWX(String customerId,String platform,String code, final BaseViewCallback baseViewCallback) {
        OkGo.<NormalBean>get(String.format(BIND, customerId,platform,code)).execute(new JsonCallBack<NormalBean>(NormalBean.class) {
            @Override
            public void onSuccess(Response<NormalBean> response) {
                    baseViewCallback.setData(response.body());
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
        OkGo.<CreditHistroyBean>get(String.format(FIND_CREDIT_HISTORY, token, pageNo, pageSize)).execute(new JsonCallBack<CreditHistroyBean>(CreditHistroyBean.class) {
            @Override
            public void onSuccess(Response<CreditHistroyBean> response) {
                    baseViewCallback.setData(response.body());
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
                    baseViewCallback.setData(response.body());
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
                    baseViewCallback.setData(response.body());
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
                if ("OK".equals(response.body().getMsg()) && 200 == response.body().getStatus()) {
                    baseViewCallback.setData(response.body());
                }

                if ("短信验证码错误".equals(response.body().getMsg()) && 704 == response.body().getStatus()) {
                    BaseApplication.showToast("验证码错误");
                }
            }
        });
    }
    public static void logoutByToken(String token, final BaseViewCallback baseViewCallback) {
        OkGo.<NormalBean>get(String.format(LOGOUT_BY_TOKEN, token)).execute(new JsonCallBack<NormalBean>(NormalBean.class) {
            @Override
            public void onSuccess(Response<NormalBean> response) {
                    baseViewCallback.setData(response.body());
            }
        });
    }
    public static void loginByToken(String token, final BaseViewCallback baseViewCallback) {
        OkGo.<LoginWithInfoBean>get(String.format(LOGIN_BY_TOKEN, token)).tag("loginByToken").execute(new JsonCallBack<LoginWithInfoBean>(LoginWithInfoBean.class) {
            @Override
            public void onSuccess(Response<LoginWithInfoBean> response) {
                if (response.isSuccessful()) {
                    if (response.body().getStatus() == 400) {
                        BaseApplication.showToast("没有找到业务数据");
                    } else {
                        baseViewCallback.setData(response.body());
                    }
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
                    baseViewCallback.setData(response.body());
            }
        });
    }

    public static void uBindWX(String token, final BaseViewCallback baseViewCallback) {
        OkGo.<LoginBean>get(String.format(UBIND, token)).execute(new JsonCallBack<LoginBean>(LoginBean.class) {
            @Override
            public void onSuccess(Response<LoginBean> response) {
                    baseViewCallback.setData(response.body());
            }
        });
    }

    /**
     * 更新用户信息
     * @param token
     * @param customer
     * @param baseViewCallback
     */
    public static void updateCustomer(String token,LoginWithInfoBean.DataBean.CustomerBean customer, final BaseViewCallbackWithOnStart baseViewCallback) {
        JSONObject data = JsonCreator.setCustomerData(customer, token);
        OkGo.<UpdateCustomerBean>post(UPDATE_CUSTOMER)
                .upJson(data)
                .execute(new JsonCallBack<UpdateCustomerBean>(UpdateCustomerBean.class) {
                    @Override
                    public void onSuccess(Response<UpdateCustomerBean> response) {
                        baseViewCallback.setData(response.body());
                    }
                    @Override
                    public void onError(Response<UpdateCustomerBean> response) {
                        super.onError(response);
                        baseViewCallback.onError();
                    }
                    @Override
                    public void onStart(Request<UpdateCustomerBean, ? extends Request> request) {
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

    /**
     * 获取用户代理的公司列表
     *
     * @param token
     * @param baseViewCallback
     */

    public static void companiesList(String token, final BaseViewCallback baseViewCallback) {
        OkGo.<CompaniesBean>get(String.format(COMPANIES_LIST, token)).tag("companiesList").execute(new JsonCallBack<CompaniesBean>(CompaniesBean.class) {
            @Override
            public void onSuccess(Response<CompaniesBean> response) {
                    baseViewCallback.setData(response.body());
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
                    baseViewCallback.setData(response.body());
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
                            baseViewCallback.setData(response.body());
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
                    baseViewCallback.setData(response.body());
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
                    baseViewCallback.setData(response.body());
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
        OkGo.<FavBean>post(FAVORITE_COMPANY_CREATE)
                .upJson(jsonFavCompany)
                .execute(new JsonCallBack<FavBean>(FavBean.class) {
                    @Override
                    public void onSuccess(Response<FavBean> response) {
                            baseViewCallback.setData(response.body());
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

        OkGo.<FavBean>delete(String.format(FAVORITE_COMPANY_REMOVE, id, token))
                .execute(new JsonCallBack<FavBean>(FavBean.class) {
                    @Override
                    public void onSuccess(Response<FavBean> response) {
                            baseViewCallback.setData(response.body());
                    }
                });
    }

    /**
     * 获取用户发布的需求列表
     *
     * @param token
     * @param baseViewCallback
     */
    public static void demandsList(String token, String state, BaseFragment baseFragment, final BaseViewCallback baseViewCallback) {
        OkGo.<DemandsListBean>get(String.format(USER_ISSUED_LIST,state,token)).tag(baseFragment).execute(new JsonCallBack<DemandsListBean>(DemandsListBean.class) {
            @Override
            public void onSuccess(Response<DemandsListBean> response) {
                    int status = response.body().getStatus();
                        TLog.log("status == REQUEST_SUCCESS");
                        baseViewCallback.setData(response.body());
            }
        });
    }

    /**
     * 获取用户发布的需求详细信息
     *
     * @param token
     * @param demandId
     */

    public static void demandDetails(String token, String demandId, final BaseViewCallbackWithOnStart baseViewCallbackWithOnStart ){
        OkGo.<DemandDetails>get(String.format(USER_ISSUED_DETAILS, demandId, token))
                .tag("demandDetails")
                .execute(new JsonCallBack<DemandDetails>(DemandDetails.class) {
            @Override
            public void onSuccess(Response<DemandDetails> response) {
                 baseViewCallbackWithOnStart.setData(response.body());
            }

            @Override
            public void onFinish() {
                super.onFinish();
                baseViewCallbackWithOnStart.onFinish();
            }

            @Override
            public void onStart(Request<DemandDetails, ? extends Request> request) {
                super.onStart(request);
                baseViewCallbackWithOnStart.onStart();
            }

            @Override
            public void onError(Response<DemandDetails> response) {
                super.onError(response);
                baseViewCallbackWithOnStart.onError();

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
                    baseViewCallback.setData(response.body());
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
    public static void findAllInvoice(String token,final BaseViewCallbackWithOnStart baseViewCallback) {
        if (TDevice.hasInternet()) {

            OkGo.<AllInvoiceType>post(FIND_ALL_INVIICE_TYPE)
                    .upJson(JsonCreator.allInvoice(token))
                    .execute(new JsonCallBack<AllInvoiceType>(AllInvoiceType.class) {
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
        } else {
            BaseApplication.showToast("请检查您的网络~");
        }

    }
    public static void confirmDemand(String token,String demandId,final BaseViewCallback baseViewCallback) {
        if (TDevice.hasInternet()) {
            OkGo.<NormalBean>get(String.format(CONFIRM_DEMAND,token,demandId)).execute(new JsonCallBack<NormalBean>(NormalBean.class) {

                @Override
                public void onSuccess(Response<NormalBean> response) {
                    baseViewCallback.setData(response.body());
                }
            });
        } else {
            BaseApplication.showToast("请检查您的网络~");
        }

    }
    public static void estimateRedBag(String token, String demandId, double amount, final BaseViewCallbackWithOnStart baseViewCallback) {
        String url = String.format(ESTIMATE, token, demandId, amount);
        OkGo.<RedBagBean>get(url).execute(new JsonCallBack<RedBagBean>(RedBagBean.class) {
            @Override
            public void onSuccess(Response<RedBagBean> response) {
                if (response.isSuccessful()) {
                    baseViewCallback.setData(response.body());
                }
            }

            @Override
            public void onStart(Request<RedBagBean, ? extends Request> request) {
                super.onStart(request);
                baseViewCallback.onStart();
            }

            @Override
            public void onFinish() {
                super.onFinish();
                baseViewCallback.onFinish();
            }

            @Override
            public void onError(Response<RedBagBean> response) {
                super.onError(response);
                baseViewCallback.onError();

            }
        });
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
                        baseViewCallback.setData(response.body());
                }
            }
        });
    }

    /**
     *
     * @param baseViewCallback
     * @param <T>
     */
    public static <T> void findDefaultInvoiceType(final BaseViewCallbackWithOnStart baseViewCallback) {
        if (TDevice.hasInternet()) {
            OkGo.<T>get(FIND_DEFAULT_FREQUENTLY_INVOICE_TYPE).execute(new JsonCallBack<T>(DefaultInvoiceBean.class) {
                @Override
                public void onSuccess(Response<T> response) {
                    if (response.isSuccessful()) {
                        baseViewCallback.setData(response.body());
                    }
                }

                @Override
                public void onStart(Request<T, ? extends Request> request) {
                    super.onStart(request);
                    baseViewCallback.onStart();
                }

                @Override
                public void onFinish() {
                    super.onFinish();
                    baseViewCallback.onFinish();
                }

                @Override
                public void onError(Response<T> response) {
                    super.onError(response);
                    baseViewCallback.onError();
                }
            });
        } else {
            BaseApplication.showToast("请检查您的网络~");
        }

    }

    /**
     *
     * @param token
     * @param baseViewCallback
     * @param <T>
     */
    public static <T> void findUserInvoiceType(String token, final BaseViewCallbackWithOnStart baseViewCallback) {
        if (TDevice.hasInternet()) {

            String url = String.format(FIND_FREQUENTLY_INVOICE_TYPE, token);
            OkGo.<T>get(url).tag("findUserInvoiceType").execute(new JsonCallBack<T>(DefaultInvoiceBean.class) {
                @Override
                public void onSuccess(Response<T> response) {
                    if (response.isSuccessful()) {
                        baseViewCallback.setData(response.body());
                    }
                }

                @Override
                public void onError(Response<T> response) {
                    super.onError(response);
                    baseViewCallback.onError();
                }

                @Override
                public void onFinish() {
                    super.onFinish();
                    baseViewCallback.onFinish();
                }

                @Override
                public void onStart(Request<T, ? extends Request> request) {
                    super.onStart(request);
                    baseViewCallback.onStart();
                }
            });
        } else {
            BaseApplication.showToast("请检查您的网络~");
        }
    }

    /**
     *
     * @param invoiceType
     * @param amount
     * @param varieties
     * @param city
     * @param baseViewCallback
     */
    public static void doMatchDemand(String invoiceType, Double amount, String varieties, String city,String companyId, final BaseViewCallbackWithOnStart baseViewCallback) {
        String url = String.format(DO_MATCH_DEMAND, invoiceType, amount);


        OkGo.<MacherBeanToken>post(url)
                .upJson(JsonCreator.matcher(varieties,city,companyId))
                .execute(new JsonCallBack<MacherBeanToken>(MacherBeanToken.class) {
            @Override
            public void onSuccess(Response<MacherBeanToken> response) {
                if (response.isSuccessful()) {
                        Log.d(TAG, "doMatchDemandonSuccess: "+response.body());
                        baseViewCallback.setData(response.body());
                }
            }

                    @Override
                    public void onError(Response<MacherBeanToken> response) {
                        super.onError(response);
                        baseViewCallback.onError();
                    }

                    @Override
                    public void onFinish() {
                        super.onFinish();
                        baseViewCallback.onFinish();
                    }

                    @Override
                    public void onStart(Request<MacherBeanToken, ? extends Request> request) {
                        super.onStart(request);
                        baseViewCallback.onStart();
                    }

                });




    }


    /**
     * 我的发票列表
     * @param token
     * @param baseViewCallback
     */
    public static void myInvoiceList(String token, Object baseActivity, final BaseViewCallback baseViewCallback) {
        OkGo.<MyInvoiceListBean>get(String.format(MY_INVOICE_LIST, token)).tag(baseActivity).execute(new JsonCallBack<MyInvoiceListBean>(MyInvoiceListBean.class) {
            @Override
            public void onSuccess(Response<MyInvoiceListBean> response) {
                    baseViewCallback.setData(response.body());
            }
        });
    }
    /**
     * 邮寄发票
     * @param token
     * @param baseViewCallback
     */
    public static void mailInvoice(String token,String orderId,String logisticsCompany,String trackingNumber, final BaseViewCallback baseViewCallback) {
        OkGo.<NormalBean>get(String.format(MAIL_INVOICE, token,orderId,logisticsCompany,trackingNumber)).execute(new JsonCallBack<NormalBean>(NormalBean.class) {
            @Override
            public void onSuccess(Response<NormalBean> response) {
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
                    baseViewCallback.setData(response.body());
            }
        });
    }

    /**
     * 确认发票
     * @param token
     * @param orderInvoiceId
     * @param baseViewCallback
     */
    public static void confirmInvoice(String token,String orderInvoiceId, final BaseViewCallback baseViewCallback) {
        OkGo.<ConfirmInvoiceBean>get(String.format(CONFIRM_INVOICE,token,orderInvoiceId)).execute(new JsonCallBack<ConfirmInvoiceBean>(ConfirmInvoiceBean.class) {
            @Override
            public void onSuccess(Response<ConfirmInvoiceBean> response) {
                    baseViewCallback.setData(response.body());
            }
        });
    }

    /**
     * 驳回发票
     * @param token
     * @param orderInvoiceId
     * @param amount
     * @param rejectType
     * @param reason
     * @param baseViewCallback
     */
    public static void rejectInvoice(String token,String orderInvoiceId,String amount,String rejectType,String reason, final BaseViewCallback baseViewCallback) {
        OkGo.<RejectInvoiceBean>post(String.format(REJECT_INVOICE,token,orderInvoiceId,Double.parseDouble(amount),rejectType))
                .upJson(JsonCreator.setReject(reason))
                .execute(new JsonCallBack<RejectInvoiceBean>(RejectInvoiceBean.class) {
            @Override
            public void onSuccess(Response<RejectInvoiceBean> response) {
                    baseViewCallback.setData(response.body());
            }
        });
    }

    /**
     * 提供详情
     * @param token
     * @param orderID
     * @param baseViewCallback
     */
    public static void showOrderDetail(String token,String orderID,final BaseViewCallbackWithOnStart baseViewCallback) {

        OkGo.<OrderDetailsBean>get(String.format(SHOW_ORDER_DETAIL, token,orderID))
                .tag("showOrderDetail").execute(new JsonCallBack<OrderDetailsBean>(OrderDetailsBean.class) {
            @Override
            public void onSuccess(Response<OrderDetailsBean> response) {
                    baseViewCallback.setData(response.body());
            }

            @Override
            public void onStart(Request<OrderDetailsBean, ? extends Request> request) {
                super.onStart(request);
                baseViewCallback.onStart();
            }

            @Override
            public void onFinish() {
                super.onFinish();
                baseViewCallback.onFinish();
            }

            @Override
            public void onError(Response<OrderDetailsBean> response) {
                super.onError(response);
                baseViewCallback.onError();
            }
        });
    }

    /**
     * 消息中心列表
     * @param token
     * @param baseViewCallback
     */
    public static void messageList(String token, final BaseViewCallback baseViewCallback) {
        OkGo.<MessageListBean>get(String.format(MESSAGE_MESSAGES, token)).execute(new JsonCallBack<MessageListBean>(MessageListBean.class) {
            @Override
            public void onSuccess(Response<MessageListBean> response) {
                    baseViewCallback.setData(response.body());

            }
        });
    } /**
     * 消息中心详情
     * @param token
     * @param baseViewCallback
     */
    public static void messageDetails(String type ,String token, final BaseViewCallback baseViewCallback) {
        OkGo.<MessageDetailsBean>get(String.format(MESSAGE_DETAILS,type, token)).execute(new JsonCallBack<MessageDetailsBean>(MessageDetailsBean.class) {
            @Override
            public void onSuccess(Response<MessageDetailsBean> response) {
                    baseViewCallback.setData(response.body());

            }
        });
    }
    /**
     * 消息已读
     * @param token
     * @param type
     * @param baseViewCallback
     */
    public static void messageRead(String token,String type, final BaseViewCallback baseViewCallback) {
        OkGo.<NormalBean>get(String.format(MESSAGE_READ,type,token)).execute(new JsonCallBack<NormalBean>(NormalBean.class) {
            @Override
            public void onSuccess(Response<NormalBean> response) {
                    baseViewCallback.setData(response.body());
            }
        });
    }
    /**
     * 删除消息
     * @param token
     * @param customerId
     * @param baseViewCallback
     */
    public static void messageRemove(String token,String customerId, final BaseViewCallback baseViewCallback) {
        OkGo.<OrderDetailsBean>delete(String.format(MESSAGE_REMOVE,customerId,token)).execute(new JsonCallBack<OrderDetailsBean>(OrderDetailsBean.class) {
            @Override
            public void onSuccess(Response<OrderDetailsBean> response) {
                    baseViewCallback.setData(response.body());
            }
        });
    }
    /**
     * 删除我的发票
     * @param token
     * @param invoiceId
     * @param baseViewCallback
     */
    public static void deleteMyInvoice(String token,String invoiceId, final BaseViewCallbackWithOnStart baseViewCallback) {
        OkGo.<NormalBean>delete(String.format(DELETE_MY_INVOICE,token,invoiceId)).execute(new JsonCallBack<NormalBean>(NormalBean.class) {
            @Override
            public void onSuccess(Response<NormalBean> response) {
                    baseViewCallback.setData(response.body());
            }

            @Override
            public void onStart(Request<NormalBean, ? extends Request> request) {
                super.onStart(request);
                baseViewCallback.onStart();
            }

            @Override
            public void onFinish() {
                super.onFinish();
                baseViewCallback.onFinish();
            }

            @Override
            public void onError(Response<NormalBean> response) {
                super.onError(response);
                baseViewCallback.onError();
            }
        });
    }
    /**
     * 意见反馈
     * @param token
     * @param baseViewCallback
     */
    public static void suggestion(String token,String suggestion, final BaseViewCallback baseViewCallback) {
        OkGo.<FeedBackBean>post(String.format(SUGGESTION,token))
                .upJson(JsonCreator.suggestion(suggestion))
                .execute(new JsonCallBack<FeedBackBean>(FeedBackBean.class) {
            @Override
            public void onSuccess(Response<FeedBackBean> response) {
                    baseViewCallback.setData(response.body());
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

    /**
     * 充值
     * @param token
     * @param ip
     * @param amount
     * @param b
     */
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
    public static void amountHistory(String token,String pageNo,String pageSize, final BaseViewCallback b) {
        String url = String.format(AMOUNT_HISTORY,token,pageNo,pageSize);
        OkGo.<AmountHistoryBean>get(url).execute(new JsonCallBack<AmountHistoryBean>(AmountHistoryBean.class) {
            @Override
            public void onSuccess(Response<AmountHistoryBean> response) {
                    b.setData(response.body());
            }
        });
    }
    public static void withdaw(String token, String accountType,String ip, double amount, String openid, final BaseViewCallback b) {
        String url = String.format(WITHDRAW, token,accountType, ip, amount,openid);
        OkGo.<PrepayBean>get(url).execute(new JsonCallBack<PrepayBean>(PrepayBean.class) {

            @Override
            public void onSuccess(Response<PrepayBean> response) {
                    b.setData(response.body());
            }
        });
    }


    public static void judgeCompanyIsCollcted(String companyId, String token, final BaseViewCallback baseViewCallback) {
        String url = String.format(FAVORITE_COMPANY, companyId, token);
        OkGo.<FavBean>get(url).execute(new JsonCallBack<FavBean>(FavBean.class) {
            @Override
            public void onSuccess(Response<FavBean> response) {
                baseViewCallback.setData(response.body());
            }
        });
    }

    public static void createOrder(String json, final BaseViewCallbackWithOnStart baseViewCallbackWithOnStart) {
        OkGo.<OrderBean>post(CREATE_ORDER).upJson(json).execute(new JsonCallBack<OrderBean>(OrderBean.class) {

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
            public void onError(Response<OrderBean> response) {
                super.onError(response);
                baseViewCallbackWithOnStart.onError();
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

    public static void uploadLocalReceipt(String json,final BaseViewCallbackWithOnStart baseViewCallbackWithOnStart) {

        OkGo.<NormalBean>post(UPLOAD_MY_INVOICE).upJson(json).execute(new JsonCallBack<NormalBean>(NormalBean.class) {

            @Override
            public void onStart(Request<NormalBean, ? extends Request> request) {
                super.onStart(request);
                baseViewCallbackWithOnStart.onStart();
            }

            @Override
            public void onFinish() {
                super.onFinish();
                baseViewCallbackWithOnStart.onFinish();
            }

            @Override
            public void onSuccess(Response<NormalBean> response) {
                if (response.isSuccessful()) {
                    baseViewCallbackWithOnStart.setData(response.body());
                }
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




    /**
     * 红包充入余额
     * @param token
     * @param baseViewCallback
     */
    public static void reload(String token, final BaseViewCallback baseViewCallback) {
        String url = String.format(RELOAD, token);
        OkGo.<NormalBean>get(url).execute(new JsonCallBack<NormalBean>(NormalBean.class) {
            @Override
            public void onSuccess(Response<NormalBean> response) {
                baseViewCallback.setData(response.body());
            }
        });
    }
    /**
     * 获取所有发票驳回类型
     * @param baseViewCallback
     */
    public static void findAllRejectType(final BaseViewCallback baseViewCallback) {
        OkGo.<RejectTypeBean>get(FIND_ALL_REJECT_TYPE).execute(new JsonCallBack<RejectTypeBean>(RejectTypeBean.class) {
            @Override
            public void onSuccess(Response<RejectTypeBean> response) {
                    baseViewCallback.setData(response.body());
            }
        });
    }


    public static void downloadApk(String url ,final BaseViewCallBackWithProgress baseViewCallback) {
        if (TDevice.sdcardExit()) {
            OkGo.<File>get(url).execute(new FileCallback(TDevice.DEFAULT_SAVE_FILE_PATH, "fapiaobao.apk") {
                @Override
                public void onSuccess(Response<File> response) {
                    if (response.body() != null) {
                        baseViewCallback.setData(response.body());
                    }
                }

                @Override
                public void downloadProgress(Progress progress) {
                    super.downloadProgress(progress);
                    baseViewCallback.setProgress(progress);
                }

                @Override
                public void onStart(Request<File, ? extends Request> request) {
                    super.onStart(request);
                    baseViewCallback.onStart(request);
                }

                @Override
                public void onFinish() {
                    super.onFinish();
                    baseViewCallback.onFinish();
                }
            });
        }

    }

    public static void getUpdateInfo(final BaseViewCallback baseViewCallback) {
        OkGo.<VersionMode>get(URL_UPDATE)
                .execute(new JsonCallBack<VersionMode>(VersionMode.class) {

                    @Override
                    public void onSuccess(Response<VersionMode> response) {
                        if (response.body() != null) {
                            if (response.isSuccessful()) {
                                baseViewCallback.setData(response.body());
                            }
                        }
                    }
                });
    }

    public interface BaseViewCallback<T> {
        void setData(T t);
    }

    public interface BaseViewCallbackPromote<T>{
        void setData(T t);

        void noData(T t);
    }



    public interface BaseViewCallbackWithOnStart<T> extends BaseViewCallback<T> {
        void onStart();

        void onFinish();

        void onError();
    }



    public interface BaseViewCallBackWithProgress<T> extends BaseViewCallback<T> {
        void setProgress(Progress progress);

        void onStart(Request<File, ? extends Request> request);

        void onFinish();

    }


}
