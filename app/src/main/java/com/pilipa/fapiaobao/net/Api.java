package com.pilipa.fapiaobao.net;

import android.support.annotation.NonNull;

import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;
import com.pilipa.fapiaobao.entity.Company;
import com.pilipa.fapiaobao.entity.Customer;
import com.pilipa.fapiaobao.entity.FavCompany;
import com.pilipa.fapiaobao.net.bean.LoginBean;
import com.pilipa.fapiaobao.net.bean.LoginWithInfoBean;
import com.pilipa.fapiaobao.net.bean.ShortMessageBean;
import com.pilipa.fapiaobao.net.bean.invoice.AllInvoiceType;
import com.pilipa.fapiaobao.net.bean.me.CompaniesBean;
import com.pilipa.fapiaobao.net.bean.me.CompanyDetailsBean;
import com.pilipa.fapiaobao.net.bean.me.CreditInfoBean;
import com.pilipa.fapiaobao.net.bean.me.FavoriteCompanyBean;
import com.pilipa.fapiaobao.net.bean.me.NegativeCreditInfoBean;
import com.pilipa.fapiaobao.net.bean.me.NormalBean;
import com.pilipa.fapiaobao.net.bean.publish.DemandDetails;
import com.pilipa.fapiaobao.net.bean.publish.DemandsListBean;
import com.pilipa.fapiaobao.net.bean.publish.ExpressCompanyBean;
import com.pilipa.fapiaobao.net.callback.JsonCallBack;
import com.pilipa.fapiaobao.utils.PayCommonUtil;
import com.pilipa.fapiaobao.wxapi.Constants;

import org.json.JSONObject;

import static com.pilipa.fapiaobao.net.Constant.FIND_ALL_EXPRESS_COMPANY;
import static com.pilipa.fapiaobao.net.Constant.FIND_ALL_INVIICE_TYPE;
import static com.pilipa.fapiaobao.net.Constant.UPDATE_INVOICE_TYPE;

/**
 * Created by lyt on 2017/10/12.
 */

public class Api {

    public static void bindWX(String token, final BaseViewCallback baseViewCallback) {
        OkGo.<LoginBean>get(String.format(Constant.BIND, token)).execute(new JsonCallBack<LoginBean>(LoginBean.class) {
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
        OkGo.<NegativeCreditInfoBean>get(String.format(Constant.FIND_CREDIT_HISTORY, token, pageNo, pageSize)).execute(new JsonCallBack<NegativeCreditInfoBean>(NegativeCreditInfoBean.class) {
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
        OkGo.<CreditInfoBean>get(String.format(Constant.FIND_CREDIT_INFO, token)).execute(new JsonCallBack<CreditInfoBean>(CreditInfoBean.class) {
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
        OkGo.<NegativeCreditInfoBean>get(String.format(Constant.FIND_CREDIT_NEGATIVE_HISTORY, token, pageNo, pageSize)).execute(new JsonCallBack<NegativeCreditInfoBean>(NegativeCreditInfoBean.class) {
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
        OkGo.<LoginWithInfoBean>get(String.format(Constant.USER_LOGIN, platform, credenceName, credenceCode, deviceToken)).execute(new JsonCallBack<LoginWithInfoBean>(LoginWithInfoBean.class) {
            @Override
            public void onSuccess(Response<LoginWithInfoBean> response) {
                if ("OK".equals(response.body().getMsg())) {
                    baseViewCallback.setData(response.body());
                }
            }
        });
    }

    public static void loginByToken(String platform, String credenceName, String credenceCode, final BaseViewCallback baseViewCallback) {
        OkGo.<LoginBean>get(String.format(Constant.LOGIN_BY_TOKEN, platform, credenceName, credenceCode)).execute(new JsonCallBack<LoginBean>(LoginBean.class) {
            @Override
            public void onSuccess(Response<LoginBean> response) {
                if ("OK".equals(response.body().getMsg())) {
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
        OkGo.<ShortMessageBean>get(String.format(Constant.SHORT_MESSAGE_VERIFY, moible)).execute(new JsonCallBack<ShortMessageBean>(ShortMessageBean.class) {
            @Override
            public void onSuccess(Response<ShortMessageBean> response) {
                if ("OK".equals(response.body().getMsg())) {
                    baseViewCallback.setData(response.body());
                }
            }
        });
    }



    public static void uBindWX(String token, final BaseViewCallback baseViewCallback) {
        OkGo.<LoginBean>get(String.format(Constant.UBIND, token)).execute(new JsonCallBack<LoginBean>(LoginBean.class) {
            @Override
            public void onSuccess(Response<LoginBean> response) {
                if ("OK".equals(response.body().getMsg())) {
                    baseViewCallback.setData(response.body());
                }
            }
        });
    }

    public static void updateCustomer(String token, Customer customer, final BaseViewCallback baseViewCallback) {
        JSONObject map = JsonCreator.setCustomerData(customer,token);
        OkGo.patch(String.format(Constant.UPDATE_CUSTOMER))
                .upJson(map)
                .execute(new JsonCallBack<Object>(Object.class) {
                    @Override
                    public void onSuccess(Response<Object> response) {
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
        OkGo.<CompaniesBean>get(String.format(Constant.COMPANIES_LIST, token)).execute(new JsonCallBack<CompaniesBean>(CompaniesBean.class) {
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
        OkGo.<CompanyDetailsBean>get(String.format(Constant.COMPANY_INFO, id)).execute(new JsonCallBack<CompanyDetailsBean>(CompanyDetailsBean.class) {
            @Override
            public void onSuccess(Response<CompanyDetailsBean> response) {
                if ("OK".equals(response.body().getMsg())) {
                    baseViewCallback.setData(response.body());
                }
            }
        });
    }
    public static void companyCreate(Company company,String token,final BaseViewCallback baseViewCallback) {
        JSONObject map = JsonCreator.setCompanyData(company,token);
        OkGo.<NormalBean>post(Constant.CREATE_COMPANY)
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
        OkGo.<NormalBean>delete(String.format(Constant.DELETE_COMPANY, id, token)).execute(new JsonCallBack<NormalBean>(NormalBean.class) {
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
        OkGo.<FavoriteCompanyBean>get(String.format(Constant.FAVORITE_COMPANY_LIST, token)).execute(new JsonCallBack<FavoriteCompanyBean>(FavoriteCompanyBean.class) {
            @Override
            public void onSuccess(Response<FavoriteCompanyBean> response) {
                if ("OK".equals(response.body().getMsg())) {
                    baseViewCallback.setData(response.body());
                }
            }
        });
    }
    public static void favCompanyCreate(FavCompany favCompany, final BaseViewCallback baseViewCallback) {
        Gson gson = new Gson();
        String jsonFavCompany =gson.toJson(favCompany.toString());
        OkGo.<NormalBean>post(Constant.CREATE_COMPANY)
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

        OkGo.<NormalBean>delete(String.format(Constant.FAVORITE_COMPANY_REMOVE, id, token))
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
    public static void demandsList(String token, final BaseViewCallback baseViewCallback) {
        OkGo.<DemandsListBean>get(String.format(Constant.USER_ISSUED_LIST, token)).execute(new JsonCallBack<DemandsListBean>(DemandsListBean.class) {
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
        OkGo.<DemandDetails>get(String.format(Constant.USER_ISSUED_DETAILS, demandId, token)).execute(new JsonCallBack<DemandDetails>(DemandDetails.class) {
            @Override
            public void onSuccess(Response<DemandDetails> response) {
                if ("OK".equals(response.body().getMsg())) {
                    baseViewCallback.setData(response.body());
                }
            }
        });
    }

    public static void shatDownEarly(String token, String demandId, final BaseViewCallback baseViewCallback) {
        OkGo.<NormalBean>get(String.format(Constant.SHAT_DOWN_EARLY, demandId, token)).execute(new JsonCallBack<NormalBean>(NormalBean.class) {
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

    public static void findAllInvoice(final BaseViewCallback baseViewCallback) {
        OkGo.<AllInvoiceType>get(FIND_ALL_INVIICE_TYPE).execute(new JsonCallBack<AllInvoiceType>(AllInvoiceType.class) {
            @Override
            public void onSuccess(Response<AllInvoiceType> response) {
                if (response.isSuccessful()&&response.body().getMsg().equals("OK")) {
                    baseViewCallback.setData(response.body());
                }
            }
        });
    }

    public static void estimateRedBag(HttpParams httpParams, final BaseViewCallbackWithOnStart baseViewCallback) {

    }

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

//    public static<T> void findDefaultInvoiceType(final BaseViewCallback baseViewCallback) {
//        OkGo.<T>get(FIND_DEFAULT_FREQUENTLY_INVOICE_TYPE).execute(new JsonCallBack<T>(AllInvoiceType.class) {
//            @Override
//            public void onSuccess(Response<T> response) {
//                if (response.isSuccessful()) {
//                    baseViewCallback.setData(response.body());
//                }
//            }
//        });
//    }
//
//    public static<T> void findUserInvoiceType(String token,final BaseViewCallback baseViewCallback) {
//        String url = String.format(FIND_FREQUENTLY_INVOICE_TYPE, token);
//        OkGo.<T>get(url).execute(new JsonCallBack<T>(AllInvoiceType.class) {
//            @Override
//            public void onSuccess(Response<T> response) {
//                if (response.isSuccessful()) {
//                    baseViewCallback.setData(response.body());
//                }
//            }
//        });
//    }



    public interface BaseViewCallback<T> {
        void setData(T t);
    }

    public interface BaseViewCallbackWithOnStart extends BaseViewCallback {
        void onStart();

        void onFinish();

        void onError();
    }


}
