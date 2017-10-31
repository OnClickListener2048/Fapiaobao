package com.pilipa.fapiaobao.ui.fragment;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.pilipa.fapiaobao.R;
import com.pilipa.fapiaobao.account.AccountHelper;
import com.pilipa.fapiaobao.base.BaseFragment;
import com.pilipa.fapiaobao.net.Api;
import com.pilipa.fapiaobao.net.bean.me.CompanyDetailsBean;
import com.pilipa.fapiaobao.net.bean.me.NormalBean;
import com.pilipa.fapiaobao.ui.PubSuccessActivity;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;

import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.pilipa.fapiaobao.net.Constant.REQUEST_SUCCESS;

/**
 * Created by lyt on 2017/10/17.
 */

public class MyCompanyDetailsPagerFragment extends BaseFragment{
    private static final String TAG = "MyCompanyDetailsPagerFragment";
    private TextView tv_companyName,tv_receptCode,tv_address,tv_phoneNum,tv_bankName,tv_account;
    private Dialog mCameraDialog;
    private UMShareListener umShareListener= new UMShareListener() {
        @Override
        public void onStart(SHARE_MEDIA share_media) {

        }

        @Override
        public void onResult(SHARE_MEDIA share_media) {
            Toast.makeText(getActivity(), "分享成功", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onError(SHARE_MEDIA share_media, Throwable throwable) {
            Toast.makeText(getActivity(), "分享失败", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onCancel(SHARE_MEDIA share_media) {
            Toast.makeText(getActivity(), "分享取消", Toast.LENGTH_SHORT).show();
        }
    };

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_company_details;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        return rootView;
    }
    public static MyCompanyDetailsPagerFragment newInstance(Bundle bundle) {
        MyCompanyDetailsPagerFragment myCompanyDetailsPagerFragment = new MyCompanyDetailsPagerFragment();
        myCompanyDetailsPagerFragment.setArguments(bundle);
        return myCompanyDetailsPagerFragment;
    }
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Bundle arguments = getArguments();
        String companyId =(String) arguments.get("companyId");

        getCompanyDetails(companyId);
        tv_companyName = (TextView) view.findViewById(R.id.tv_companyName);
        tv_receptCode = (TextView) view.findViewById(R.id.tv_receptCode);
        tv_address = (TextView) view.findViewById(R.id.tv_address);
        tv_phoneNum = (TextView) view.findViewById(R.id.tv_phoneNum);
        tv_bankName = (TextView) view.findViewById(R.id.tv_bankName);
        tv_account = (TextView) view.findViewById(R.id.tv_account);


        ImageView img_qr_code1 = (ImageView) view.findViewById(R.id.img_qr_code1);
        LinearLayout ll_qr_code2 = (LinearLayout) view.findViewById(R.id.ll_qr_code2);

//        if(companyId.equals("1")){
//            ll_qr_code2.setVisibility(View.VISIBLE);
//            img_qr_code1.setVisibility(View.GONE);
//        }else{
//            img_qr_code1.setVisibility(View.VISIBLE);
//            ll_qr_code2.setVisibility(View.GONE);
//        }

    }

    @OnClick({R.id.img_details_viewpager_share, R.id.img_details_viewpager_delete,R.id.img_details_viewpager_next})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img_details_viewpager_share:
                break;
            case R.id.img_details_viewpager_delete:
                break;
            case R.id.img_details_viewpager_next:

                break;
        }
    }

    private void setDialog() {
        mCameraDialog = new Dialog(getActivity(), R.style.BottomDialog);
        LinearLayout root = (LinearLayout) LayoutInflater.from(getActivity()).inflate(
                R.layout.layout_share_, null);
        //初始化视图
        root.findViewById(R.id.weixin).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new ShareAction(getActivity())
                        .setPlatform(SHARE_MEDIA.WEIXIN)//传入平台
                        .withText("hello")//分享内容
                        .setCallback(umShareListener)//回调监听器
                        .share();
                mCameraDialog.dismiss();
            }
        });
        root.findViewById(R.id.weibo).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new ShareAction(getActivity())
                        .setPlatform(SHARE_MEDIA.SINA)//传入平台
                        .withText("hello")//分享内容
                        .setCallback(umShareListener)//回调监听器
                        .share();
                mCameraDialog.dismiss();
            }
        });
        root.findViewById(R.id.moments).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new ShareAction(getActivity())
                        .setPlatform(SHARE_MEDIA.WEIXIN_CIRCLE)//传入平台
                        .withText("hello")//分享内容
                        .setCallback(umShareListener)//回调监听器
                        .share();
                mCameraDialog.dismiss();
            }
        });
        root.findViewById(R.id.qq).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new ShareAction(getActivity())
                        .setPlatform(SHARE_MEDIA.QQ)//传入平台
                        .withText("hello")//分享内容
                        .setCallback(umShareListener)//回调监听器
                        .share();
                mCameraDialog.dismiss();
            }
        });
        root.findViewById(R.id.qzone).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new ShareAction(getActivity())
                        .setPlatform(SHARE_MEDIA.QZONE)//传入平台
                        .withText("hello")//分享内容
                        .setCallback(umShareListener)//回调监听器
                        .share();
                mCameraDialog.dismiss();
            }
        });
        root.findViewById(R.id.btn_cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCameraDialog.dismiss();
            }
        });
        mCameraDialog.setContentView(root);
        Window dialogWindow = mCameraDialog.getWindow();
        dialogWindow.setGravity(Gravity.BOTTOM);
//        dialogWindow.setWindowAnimations(R.style.dialogstyle); // 添加动画
        WindowManager.LayoutParams lp = dialogWindow.getAttributes(); // 获取对话框当前的参数值
        lp.x = 0; // 新位置X坐标
        lp.y = 0; // 新位置Y坐标
        lp.width = (int) getResources().getDisplayMetrics().widthPixels; // 宽度
        root.measure(0, 0);
        lp.height = root.getMeasuredHeight();

        lp.alpha = 9f; // 透明度
        dialogWindow.setAttributes(lp);
        mCameraDialog.show();
    }

    @Override
    protected void initData() {
        super.initData();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    public  void getCompanyDetails(String id){
            Api.companyDetails(id, new Api.BaseViewCallback<CompanyDetailsBean>() {
                @Override
                public void setData(CompanyDetailsBean companyDetailsBean) {
                    if (companyDetailsBean.getStatus() == REQUEST_SUCCESS) {
                        CompanyDetailsBean.DataBean bean = companyDetailsBean.getData();
                        tv_companyName.setText(bean.getName());
                        tv_receptCode.setText(bean.getTaxno());
                        tv_address.setText(bean.getAddress());
                        tv_phoneNum.setText(bean.getPhone());
                        tv_bankName.setText(bean.getDepositBank());
                        tv_account.setText(bean.getAccount());
                        Log.d(TAG, "getCompanyDetails" + bean.getName().toString());
                    }
                }
            });
    }
    public void deleteCompany(String id){
        if (AccountHelper.getToken() != null && AccountHelper.getToken() != "") {
            Api.deleteCompany(id, AccountHelper.getToken(), new Api.BaseViewCallback<NormalBean>() {
                @Override
                public void setData(NormalBean normalBean) {
                    if (normalBean.getStatus() == REQUEST_SUCCESS) {
                        Log.d(TAG, "getCompanyDetails success");
                    }
                }
            });
        }
    }
}
