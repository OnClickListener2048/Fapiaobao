package com.pilipa.fapiaobao.databinding.main;

import android.view.View;

import com.pilipa.fapiaobao.R;
import com.pilipa.fapiaobao.databinding.FragmentDatabindingNaviBinding;
import com.pilipa.fapiaobao.databinding.base.fragment.BaseDatabindingFragment;
import com.pilipa.fapiaobao.databinding.main.viewmodel.NaviViewModel;
import com.pilipa.fapiaobao.databinding.me.MeDatabindingFragment;
import com.pilipa.fapiaobao.databinding.supply.SupplyDatabindingFragment;

/**
 * Created by edz on 2018/4/3.
 */

public class NavigationFragment extends BaseDatabindingFragment {


    @Override
    protected void initWidget(View root) {
        FragmentDatabindingNaviBinding fragmentDatabindingNaviBinding = (FragmentDatabindingNaviBinding) getVB();
        NaviViewModel naviViewModel = new NaviViewModel(fragmentDatabindingNaviBinding);
        fragmentDatabindingNaviBinding.setNaviviewmodel(naviViewModel);

        fragmentDatabindingNaviBinding.navItemTex.init(R.drawable.selector_finance_tab,
                R.string.main_tab_name_tex,
                SupplyDatabindingFragment.class);
        fragmentDatabindingNaviBinding.navItemMe.init(R.drawable.selector_me_tab,
                R.string.main_tab_name_me,
                MeDatabindingFragment.class);

        naviViewModel.setup(R.id.main_container, fragmentDatabindingNaviBinding.navItemTex);
    }


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_databinding_navi;
    }


}
