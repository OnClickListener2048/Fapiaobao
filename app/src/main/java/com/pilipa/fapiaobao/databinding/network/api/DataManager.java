package com.pilipa.fapiaobao.databinding.network.api;


import com.pilipa.fapiaobao.net.bean.me.demandlist.DemandListItem;

import java.util.List;

import io.reactivex.Observable;

/**
 * <请描述这个类是干什么的>
 *
 * @data: 2016/7/7 13:55
 * @version: V1.0
 */
public class DataManager extends ResponseHandle {

    public static Observable<List<DemandListItem>> getDemandList(String state, String token) {
        return Dao.getApiService().getDemandList(state, token)
                .flatMap(new ReadData())
                .compose(ResponseHandle.applySchedulersWithToken());
    }


}
