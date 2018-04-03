package com.pilipa.fapiaobao.databinding.supply;

import android.view.View;

import com.pilipa.fapiaobao.R;
import com.pilipa.fapiaobao.databinding.base.fragment.BaseTitleDatabindingFragment;
import com.pilipa.fapiaobao.databinding.base.viewmodel.BaseTitleViewModel;

/**
 * Created by edz on 2018/4/4.
 */

public class SupplyDatabindingFragment extends BaseTitleDatabindingFragment {


    @Override
    protected void initWidget(View root) {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_databinding_supply;
    }


    @Override
    protected void initTitleBar(BaseTitleViewModel baseTitleViewModel) {
        baseTitleViewModel.mResLeftIcon.set(R.mipmap.scan);
        baseTitleViewModel.mResRightIcon.set(R.mipmap.notification);
        baseTitleViewModel.mResTitle.set(getString(R.string.app_name));
    }
}
