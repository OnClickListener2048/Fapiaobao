package com.pilipa.fapiaobao.databinding.network.api;


import com.pilipa.fapiaobao.net.bean.base.BaseResponseBean;
import com.pilipa.fapiaobao.net.bean.me.demandlist.DemandListItem;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * <请描述这个类是干什么的>
 *
 * @author caiyk@cncn.com
 * @data: 2016/7/7 11:28
 * @version: V1.0
 */
public interface ApiService {

    @GET("customer/bind/{customerId}/{platform}/{code}/{authCode}")
    Observable<BaseResponseBean> bindWx(@Path("customerId") String customerId
            , @Path("platform") String platform
            , @Path("code") String code
            , @Path("authCode") String authCode);

    @GET("fapiaobao/rest/publish/demands/{state}/{token}")
    Observable<BaseResponseBean<List<DemandListItem>>> getDemandList(@Path("state") String state, @Path("token") String token);
}

