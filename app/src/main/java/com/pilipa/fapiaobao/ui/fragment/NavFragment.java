package com.pilipa.fapiaobao.ui.fragment;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.pilipa.fapiaobao.R;
import com.pilipa.fapiaobao.account.AccountHelper;
import com.pilipa.fapiaobao.base.BaseFragment;
import com.pilipa.fapiaobao.ui.LoginActivity;
import com.pilipa.fapiaobao.ui.PubActivity;
import com.pilipa.fapiaobao.ui.constants.Constant;
import com.pilipa.fapiaobao.ui.widget.NavigationButton;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.pilipa.fapiaobao.ui.fragment.model.Constant.START_ACTIVITY_FROM_DETAILS;

/**
 * Created by lyt on 2017/10/13.
 */

public class NavFragment extends BaseFragment{



    @Bind(R.id.nav_item_tex)
    public NavigationButton navItemTex;
    @Bind(R.id.nav_item_me)
    public  NavigationButton navItemMe;
    @Bind(R.id.nav_item_publish)
    public ImageView navItemPublish;
    private Context mContext;
    private int mContainerId;
    private FragmentManager mFragmentManager;
    private NavigationButton mCurrentNavButton;
    private OnNavigationReselectListener mOnNavigationReselectListener;
    private BroadcastReceiver mBroadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (START_ACTIVITY_FROM_DETAILS.equals(intent.getAction())) {
                doSelect(navItemTex);
            }
        }
    };
    public NavFragment() {
        // Required empty public constructor
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_nav;
    }


    @Override
    protected void initWidget(View root) {
        super.initWidget(root);
        navItemTex.init(R.drawable.selector_finance_tab,
                R.string.main_tab_name_tex,
                FinanceFragment.class);

//        navItemPublish.init(R.mipmap.ic_launcher,
//                R.string.main_tab_name_publish,
//                PublishFragment.class);

        navItemMe.init(R.drawable.selector_me_tab,
                R.string.main_tab_name_me,
                MeFragment.class);


    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(START_ACTIVITY_FROM_DETAILS);
        context.registerReceiver(mBroadcastReceiver, intentFilter);
    }



    public void setup(Context context, FragmentManager fragmentManager, int contentId, OnNavigationReselectListener listener) {
        mContext = context;
        mFragmentManager = fragmentManager;
        mContainerId = contentId;
        mOnNavigationReselectListener = listener;

        // do clear
        clearOldFragment();
        // do select first
        doSelect(navItemTex);
    }


    @SuppressWarnings("RestrictedApi")
    private void clearOldFragment() {
        FragmentTransaction transaction = mFragmentManager.beginTransaction();
        List<Fragment> fragments = mFragmentManager.getFragments();
        if (transaction == null || fragments == null || fragments.size() == 0)
            return;
        boolean doCommit = false;
        for (Fragment fragment : fragments) {
            if (fragment != this && fragment != null) {
                transaction.remove(fragment);
                doCommit = true;
            }
        }
        if (doCommit)
            transaction.commitNow();
    }

    private void doSelect(NavigationButton newNavButton) {
        // If the new navigation is me info fragment, we intercept it
        /*
        if (newNavButton == mNavMe) {
            if (interceptMessageSkip())
                return;
        }
        */

        NavigationButton oldNavButton = null;
        if (mCurrentNavButton != null) {
            oldNavButton = mCurrentNavButton;
            if (oldNavButton == newNavButton) {
                onReselect(oldNavButton);
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
                Fragment fragment = Fragment.instantiate(mContext,
                        newNavButton.getClx().getName(), null);
                ft.add(mContainerId, fragment, newNavButton.getTag());
                newNavButton.setFragment(fragment);
            } else {
                ft.attach(newNavButton.getFragment());
            }
        }
        ft.commitAllowingStateLoss();
    }

    /**
     * 拦截底部点击，当点击个人按钮时进行消息跳转
     */
    private boolean interceptMessageSkip() {
        return false;
    }

    private void onReselect(NavigationButton navigationButton) {
        OnNavigationReselectListener listener = mOnNavigationReselectListener;
        if (listener != null) {
            listener.onReselect(navigationButton);
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
//        final Context contextThemeWrapper = new ContextThemeWrapper(getActivity(), R.style.NavFragmentBackground);
//        LayoutInflater localInflater = inflater.cloneInContext(contextThemeWrapper);
        getContext().getTheme().applyStyle(R.style.NavFragmentBackground, true);
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
        mContext.unregisterReceiver(mBroadcastReceiver);
    }



    @OnClick({R.id.nav_item_tex, R.id.nav_item_publish, R.id.nav_item_me})
    public void onViewClicked(View view) {
        if (view instanceof NavigationButton) {
            NavigationButton nav = (NavigationButton) view;
            doSelect(nav);
        } else {
//            navItemPublish.setEnabled(false);
//                AccountHelper.isTokenValid(new Api.BaseViewCallback<LoginWithInfoBean>() {
//                    @Override
//                    public void setData(LoginWithInfoBean loginWithInfoBean) {
//                        navItemPublish.setEnabled(true);
//                        if (loginWithInfoBean.getStatus()==200) {
//                            PubActivity.show(mContext);
//                        } else if (loginWithInfoBean.getStatus()==701) {
//                            Intent intent = new Intent(mContext, LoginActivity.class);
//                            intent.setAction(Constant.LOGIN_TO_PUBLISH);
//                            startActivity(intent);
//                        }
//                    }
//                });

            if ("notoken".equals(AccountHelper.getToken())) {
                Intent intent = new Intent(mContext, LoginActivity.class);
                intent.setAction(Constant.LOGIN_TO_PUBLISH);
                startActivity(intent);
            } else {
                PubActivity.show(mContext);
            }
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void initData() {
        super.initData();
    }

    public interface OnNavigationReselectListener {
        void onReselect(NavigationButton navigationButton);
    }

}
