package com.pilipa.fapiaobao;

import android.support.v4.app.FragmentManager;
import android.view.View;

import com.pilipa.fapiaobao.base.BaseActivity;
import com.pilipa.fapiaobao.base.BaseApplication;
import com.pilipa.fapiaobao.ui.LoginActivity;
import com.pilipa.fapiaobao.ui.fragment.NavFragment;
import com.pilipa.fapiaobao.ui.widget.NavigationButton;

public class MainActivity extends BaseActivity implements NavFragment.OnNavigationReselectListener {

    private NavFragment mNavBar;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void initView() {
        FragmentManager manager = getSupportFragmentManager();
        mNavBar = ((NavFragment) manager.findFragmentById(R.id.fag_nav));
        mNavBar.setup(this,manager,R.id.main_container,this);
    }

    @Override
    public void initData() {

    }

    @Override
    public void onReselect(NavigationButton navigationButton) {

    }
}
