package com.pilipa.fapiaobao.base;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.example.mylibrary.utils.ImageLoader;
import com.example.mylibrary.utils.TLog;
import com.lzy.okgo.OkGo;
import com.pilipa.fapiaobao.ui.LoginActivity;
import com.pilipa.fapiaobao.ui.fragment.ProgressDialogFragment;
import com.pilipa.fapiaobao.ui.model.Image;
import com.pilipa.fapiaobao.utils.BitmapUtils;

import java.io.IOException;
import java.io.Serializable;

import butterknife.ButterKnife;

import static com.pilipa.fapiaobao.utils.BitmapUtils.readPictureDegree;
import static com.pilipa.fapiaobao.utils.BitmapUtils.rotaingImageView;

/**
 * Created by lyt on 2017/10/13.
 */

public abstract class BaseFragment extends Fragment {
    protected Context mContext;
    protected View mRoot;
    protected Bundle mBundle;
    protected LayoutInflater mInflater;
    private RequestManager mImgLoader;
    private Fragment mFragment;
    private ProgressDialogFragment progressDialogFragment;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }



    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        TLog.d(getClass().getSimpleName(),"isVisibleToUser----------"+isVisibleToUser);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mContext = null;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBundle = getArguments();
        initBundle(mBundle);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (mRoot != null) {
            ViewGroup parent = (ViewGroup) mRoot.getParent();
            if (parent != null)
                parent.removeView(mRoot);
        } else {
            mRoot = inflater.inflate(getLayoutId(), container, false);
            mInflater = inflater;
            // Do something
            onBindViewBefore(mRoot);
            // Bind view
            ButterKnife.bind(this, mRoot);
            // Get savedInstanceState
            if (savedInstanceState != null)
                onRestartInstance(savedInstanceState);
            // Init
            initWidget(mRoot);
            initData();
            initDialogFragment();
        }
        return mRoot;
    }




    private void initDialogFragment() {
        progressDialogFragment = ProgressDialogFragment.newInstance();
    }

    public void showDialogFragment(String tag) {
        if (!progressDialogFragment.isInLayout() && !progressDialogFragment.isVisible()) {
            progressDialogFragment.show(getFragmentManager(), tag);
        }
    }

    protected void showDialog(Dialog dialog) {
        if (getActivity().isFinishing()) return;
        dialog.show();
    }

    public void hideDialogFragment(String tag) {
        String tag1 = progressDialogFragment.getTag();
        TLog.log("tag1"+tag1);
        TLog.log("progressDialogFragment.getTag()"+progressDialogFragment.getTag());
        TLog.log("progressDialogFragment.isInLayout()"+progressDialogFragment.isInLayout());
        if (TextUtils.equals(tag, tag1)) {
            progressDialogFragment.dismiss();
        }
    }

    protected void onBindViewBefore(View root) {
        // ...
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mImgLoader = null;
        mBundle = null;
        TLog.log("OkGo.cancelTag(OkGo.getInstance().getOkHttpClient(),this);"+this.getClass().getSimpleName());
        OkGo.cancelTag(OkGo.getInstance().getOkHttpClient(),this);
    }

    protected abstract int getLayoutId();

    protected void initBundle(Bundle bundle) {

    }

    protected void initWidget(View root) {

    }

    protected void initData() {

    }

    public void login() {
        BaseApplication.showToast("请先登录");
        startActivity(new Intent(mContext, LoginActivity.class));
    }

    public String upLoadReceipt(Image image) {
        Bitmap bm;
        Bitmap newbitmap = null;
        try {
            int degree = readPictureDegree(image.path);
            bm = BitmapUtils.getBitmapFormUri(getActivity(), image.uri);
            newbitmap = rotaingImageView(degree, bm);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return BitmapUtils.bitmapToBase64(newbitmap);
    }

    protected <T extends View> T findView(int viewId) {
        return (T) mRoot.findViewById(viewId);
    }

    protected <T extends Serializable> T getBundleSerializable(String key) {
        if (mBundle == null) {
            return null;
        }
        return (T) mBundle.getSerializable(key);
    }

    /**
     * 获取一个图片加载管理器
     *
     * @return RequestManager
     */
    public synchronized RequestManager getImgLoader() {
        if (mImgLoader == null)
            mImgLoader = Glide.with(this);
        return mImgLoader;
    }

    /***
     * 从网络中加载数据
     *
     * @param viewId   view的id
     * @param imageUrl 图片地址
     */
    protected void setImageFromNet(int viewId, String imageUrl) {
        setImageFromNet(viewId, imageUrl, 0);
    }

    /***
     * 从网络中加载数据
     *
     * @param viewId      view的id
     * @param imageUrl    图片地址
     * @param placeholder 图片地址为空时的资源
     */
    protected void setImageFromNet(int viewId, String imageUrl, int placeholder) {
        ImageView imageView = findView(viewId);
        setImageFromNet(imageView, imageUrl, placeholder);
    }

    /***
     * 从网络中加载数据
     *
     * @param imageView imageView
     * @param imageUrl  图片地址
     */
    protected void setImageFromNet(ImageView imageView, String imageUrl) {
        setImageFromNet(imageView, imageUrl, 0);
    }

    /***
     * 从网络中加载数据
     *
     * @param imageView   imageView
     * @param imageUrl    图片地址
     * @param placeholder 图片地址为空时的资源
     */
    protected void setImageFromNet(ImageView imageView, String imageUrl, int placeholder) {
        ImageLoader.loadImage(getImgLoader(), imageView, imageUrl, placeholder);
    }


    protected void setText(int viewId, String text) {
        TextView textView = findView(viewId);
        if (TextUtils.isEmpty(text)) {
            return;
        }
        textView.setText(text);
    }

    protected void setText(int viewId, String text, String emptyTip) {
        TextView textView = findView(viewId);
        if (TextUtils.isEmpty(text)) {
            textView.setText(emptyTip);
            return;
        }
        textView.setText(text);
    }

    protected void setTextEmptyGone(int viewId, String text) {
        TextView textView = findView(viewId);
        if (TextUtils.isEmpty(text)) {
            textView.setVisibility(View.GONE);
            return;
        }
        textView.setText(text);
    }

    protected <T extends View> T setGone(int id) {
        T view = findView(id);
        view.setVisibility(View.GONE);
        return view;
    }

    protected <T extends View> T setVisibility(int id) {
        T view = findView(id);
        view.setVisibility(View.VISIBLE);
        return view;
    }

    protected void showToast(String string) {
        BaseApplication.showToast(string);
    }

    protected void setInVisibility(int id) {
        findView(id).setVisibility(View.INVISIBLE);
    }

    protected void onRestartInstance(Bundle bundle) {

    }

    protected void addFragment(int frameLayoutId, Fragment fragment) {
        if (fragment != null) {
            FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
            if (fragment.isAdded()) {
                if (mFragment != null) {
                    transaction.hide(mFragment).show(fragment);
                } else {
                    transaction.show(fragment);
                }
            } else {
                if (mFragment != null) {
                    transaction.hide(mFragment).add(frameLayoutId, fragment);
                } else {
                    transaction.add(frameLayoutId, fragment);
                }
            }
            mFragment = fragment;
            transaction.commit();
        }
    }

    protected void addDrawerFragment(int frameLayoutId, Fragment fragment) {
        if (fragment != null) {
            FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
            if (fragment.isAdded()) {
                if (mFragment != null) {
                    transaction.show(fragment);
                }
            } else {
                if (mFragment != null) {
                    transaction.add(frameLayoutId, fragment);
                } else {
                    transaction.add(frameLayoutId, fragment);
                }
            }
            transaction.commit();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        OkGo.cancelTag(OkGo.getInstance().getOkHttpClient(),this);
    }


}
