package com.pilipa.fapiaobao.databinding.main.viewmodel;

import android.databinding.ViewDataBinding;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;

import com.example.mylibrary.utils.LogUtils;
import com.pilipa.fapiaobao.databinding.FragmentDatabindingNaviBinding;
import com.pilipa.fapiaobao.databinding.base.viewmodel.BaseViewModel;
import com.pilipa.fapiaobao.databinding.main.NavigationFragment;
import com.pilipa.fapiaobao.ui.widget.NavigationButton;

import java.util.List;

/**
 * Created by edz on 2018/4/3.
 */

public class NaviViewModel extends BaseViewModel {

    private FragmentManager mFragmentManager;

    private int mContainerId;

    private NavigationButton mCurrentNavButton;


    public NaviViewModel(ViewDataBinding viewDataBinding) {
        super(viewDataBinding);
    }

    public void setup(int contentId, NavigationButton navItemTex) {
        mFragmentManager = getDatabindingActivity().getSupportFragmentManager();
        mContainerId = contentId;

        // do clear
        clearOldFragment();
        // do select first
        doSelect(navItemTex);
    }

    private void doSelect(NavigationButton newNavButton) {
        NavigationButton oldNavButton = null;

        if (mCurrentNavButton != null) {
            oldNavButton = mCurrentNavButton;
            if (oldNavButton == newNavButton) {
                return;
            }
            oldNavButton.setSelected(false);
        }
        newNavButton.setSelected(true);
        doTabChanged(oldNavButton, newNavButton);
        mCurrentNavButton = newNavButton;
    }

    private void doTabChanged(NavigationButton oldNavButton, NavigationButton newNavButton) {
        FragmentTransaction ft = mFragmentManager.beginTransaction();
        if (oldNavButton != null) {
            if (oldNavButton.getFragment() != null) {
                ft.detach(oldNavButton.getFragment());
            }
        }
        if (newNavButton != null) {
            if (newNavButton.getFragment() == null) {
                Fragment fragment = Fragment.instantiate(getDatabindingActivity(),
                        newNavButton.getClx().getName(), null);
                ft.add(mContainerId, fragment, newNavButton.getTag());
                newNavButton.setFragment(fragment);
            } else {
                ft.attach(newNavButton.getFragment());
            }
        }
        ft.commitAllowingStateLoss();
    }

    private void clearOldFragment() {
        FragmentTransaction transaction = mFragmentManager.beginTransaction();
        List<Fragment> fragments = mFragmentManager.getFragments();
        if (transaction == null || fragments == null || fragments.size() == 0)
            return;
        boolean doCommit = false;
        for (Fragment fragment : fragments) {
            if (!(fragment instanceof NavigationFragment) && fragment != null) {
                transaction.remove(fragment);
                doCommit = true;
            }
        }
        if (doCommit)
            transaction.commitNow();
    }

    public void onSupplyClick(View view) {
        LogUtils.d("onSupplyClick");
        doSelect(((FragmentDatabindingNaviBinding) getVb()).navItemTex);
    }

    public void onPublishClick(View view) {
        LogUtils.d("onPublishClick");
    }

    public void onMeClick(View view) {
        LogUtils.d("onMeClick");
        doSelect(((FragmentDatabindingNaviBinding) getVb()).navItemMe);
    }



}
