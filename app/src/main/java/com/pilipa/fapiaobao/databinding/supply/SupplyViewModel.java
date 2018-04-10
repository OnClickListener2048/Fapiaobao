package com.pilipa.fapiaobao.databinding.supply;

import android.databinding.ViewDataBinding;
import android.view.View;

import com.example.mylibrary.utils.ToastUtils;
import com.pilipa.fapiaobao.databinding.base.viewmodel.BaseTitleViewModel;
import com.pilipa.fapiaobao.databinding.network.api.DataManager;
import com.pilipa.fapiaobao.databinding.network.api.consumer.FProgressObserver;
import com.pilipa.fapiaobao.net.bean.me.demandlist.DemandListItem;

import java.util.List;

/**
 * Created by edz on 2018/4/10.
 */

public class SupplyViewModel extends BaseTitleViewModel {
    public SupplyViewModel(ViewDataBinding viewDataBinding) {
        super(viewDataBinding);
    }

    public void onTextClick(View view) {
        DataManager.getDemandList("0", "9cda8b3d-eb0a-4e52-9ac6-d955684e2845")
                .subscribe(new FProgressObserver<List<DemandListItem>>(getDatabindingActivity()) {
                    @Override
                    protected void onSuccess(List<DemandListItem> demandListItems) {
                        ToastUtils.showShort("onSuccess");

                    }

                    @Override
                    protected void onNoContent() {
                        super.onNoContent();
                        ToastUtils.showShort("onNoContent");
                    }

                    @Override
                    protected void onTokenInvalide() {
                        super.onTokenInvalide();
                        ToastUtils.showShort("onTokenInvalide");
                    }
                });
    }

}
