package com.pilipa.fapiaobao.ui.fragment;

import android.Manifest;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
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

import com.example.mylibrary.utils.ActivityUtils;
import com.example.mylibrary.utils.ImageUtils;
import com.example.mylibrary.utils.TLog;
import com.pilipa.fapiaobao.AppOperator;
import com.pilipa.fapiaobao.R;
import com.pilipa.fapiaobao.account.AccountHelper;
import com.pilipa.fapiaobao.adapter.MyCompanyDetailsAdapter;
import com.pilipa.fapiaobao.base.BaseApplication;
import com.pilipa.fapiaobao.base.BaseFragment;
import com.pilipa.fapiaobao.entity.Company;
import com.pilipa.fapiaobao.net.Api;
import com.pilipa.fapiaobao.net.bean.me.CompanyDetailsBean;
import com.pilipa.fapiaobao.net.bean.me.FavoriteCompanyBean;
import com.pilipa.fapiaobao.net.bean.me.NormalBean;
import com.pilipa.fapiaobao.ui.CompanyManagerActivity;
import com.pilipa.fapiaobao.ui.EstimateLocationActivity;
import com.pilipa.fapiaobao.ui.FavCompanyDetailsActivity;
import com.pilipa.fapiaobao.wxapi.Constants;
import com.pilipa.fapiaobao.zxing.encode.CodeCreator;
import com.tbruyelle.rxpermissions2.RxPermissions;
import com.tencent.mm.opensdk.modelmsg.SendMessageToWX;
import com.tencent.mm.opensdk.modelmsg.WXMediaMessage;
import com.tencent.mm.opensdk.modelmsg.WXWebpageObject;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.media.UMWeb;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

import static com.pilipa.fapiaobao.base.BaseApplication.SHARE_SOCORE;
import static com.pilipa.fapiaobao.base.BaseApplication.set;
import static com.pilipa.fapiaobao.net.Constant.REQUEST_SUCCESS;
import static com.pilipa.fapiaobao.ui.fragment.model.Constant.START_ACTIVITY_FROM_DETAILS;

/**
 * Created by lyt on 2017/10/17.
 */

public class MyCompanyDetailsPagerFragment extends BaseFragment implements MyCompanyDetailsAdapter.ItemClickListener {
    private static final String TAG = "MyCompanyDetailsPagerFragment";
    @Bind(R.id.view_recycler)
    RecyclerView viewRecycler;

    private TextView tv_companyName, tv_receptCode, tv_address, tv_phoneNum, tv_bankName, tv_account;
    private Dialog mCameraDialog;
    private ImageView img_details_viewpager_next;
    private String companyId;
    private String deleteId;
    private IWXAPI api;
    private UMShareListener umShareListener = new UMShareListener() {
        @Override
        public void onStart(SHARE_MEDIA share_media) {

        }

        @Override
        public void onResult(SHARE_MEDIA share_media) {
            Api.shareScoreAdd(AccountHelper.getToken(), new Api.BaseViewCallback<NormalBean>() {
                @Override
                public void setData(NormalBean normalBean) {
                    Log.d(TAG, "updateData:shareScoreAdd success");
                }
            });
        }

        @Override
        public void onError(SHARE_MEDIA share_media, Throwable throwable) {
        }

        @Override
        public void onCancel(SHARE_MEDIA share_media) {
        }
    };
    private UMWeb web;
    private UMShareAPI umShareAPI;
    private Company company;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_company_details;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        api = WXAPIFactory.createWXAPI(getActivity(), Constants.APP_ID);
        api.registerApp(Constants.APP_ID);
        umShareAPI = UMShareAPI.get(mContext);
        return rootView;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        umShareAPI.release();
    }

    @Override
    protected void initWidget(View root) {
        super.initWidget(root);
    }

    public static MyCompanyDetailsPagerFragment newInstance(Bundle bundle) {
        MyCompanyDetailsPagerFragment myCompanyDetailsPagerFragment = new MyCompanyDetailsPagerFragment();
        myCompanyDetailsPagerFragment.setArguments(bundle);
        return myCompanyDetailsPagerFragment;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        tv_companyName = (TextView) view.findViewById(R.id.tv_companyName);
        tv_receptCode = (TextView) view.findViewById(R.id.tv_receptCode);
        tv_address = (TextView) view.findViewById(R.id.tv_address);
        tv_phoneNum = (TextView) view.findViewById(R.id.tv_phoneNum);
        tv_bankName = (TextView) view.findViewById(R.id.tv_bankName);
        tv_account = (TextView) view.findViewById(R.id.tv_account);
        LinearLayout ll_we_need = (LinearLayout) view.findViewById(R.id.ll_we_need);
        TextView tv_newNeed = (TextView) view.findViewById(R.id.tv_newNeed);
        final ImageView img_qr_code1 = (ImageView) view.findViewById(R.id.img_qr_code1);
        final ImageView img_qr_code2 = (ImageView) view.findViewById(R.id.img_qr_code2);
        LinearLayout ll_qr_code2 = (LinearLayout) view.findViewById(R.id.ll_qr_code2);
        viewRecycler.setLayoutManager(new GridLayoutManager(mContext, 3));
        Bundle arguments = getArguments();
        company = arguments.getParcelable("company");
        if (company != null) {
            tv_companyName.setText(company.getName());
            tv_receptCode.setText(company.getTaxno());
            tv_address.setText(company.getAddress());
            tv_phoneNum.setText(company.getPhone());
            tv_bankName.setText(company.getDepositBank());
            tv_account.setText(company.getAccount());
            TLog.d("qrcode", company.getQrcode() + "");
            if (company.getInvoiceTypeList() != null) {
                ll_qr_code2.setVisibility(View.VISIBLE);
                img_qr_code1.setVisibility(View.GONE);
                ll_we_need.setVisibility(View.VISIBLE);
                tv_newNeed.setVisibility(View.VISIBLE);
                initChildViews((List<FavoriteCompanyBean.DataBean.InvoiceTypeListBean>) company.getInvoiceTypeList());
            } else {
                ll_we_need.setVisibility(View.GONE);
                tv_newNeed.setVisibility(View.GONE);
                img_qr_code1.setVisibility(View.VISIBLE);
                ll_qr_code2.setVisibility(View.GONE);
            }
        }

        Log.d(TAG, "Company getQrcode" + company.getQrcode());
        //初始化 web
        web = new UMWeb(company.getQrcode());
        web.setTitle(getString(R.string.please_open_check));//标题
        UMImage umImage = new UMImage(getActivity(), R.mipmap.icon);
        web.setThumb(umImage);  //缩略图
        web.setDescription(getString(R.string.tohave_fapiaobao));//描述


        //TODO 截取qrCode中 companyId
        String[] temp = null;
        temp = company.getQrcode().split("id=");
        companyId = temp[1];
        //删除的ID
        deleteId = company.getId();
        Log.d(TAG, "deleteId" + deleteId);
        Observable.create(new ObservableOnSubscribe<Bitmap>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<Bitmap> e) throws Exception {
                String content = new String(company.getQrcode().getBytes("UTF-8"), "ISO-8859-1");
                TLog.log("content-----------" + content);
                Bitmap qrCode = CodeCreator.createQRCode(mContext, content);
                e.onNext(qrCode);
                e.onComplete();
            }
        }).subscribeOn(Schedulers.from(AppOperator.getExecutor()))
                .observeOn(AndroidSchedulers.mainThread())
                .doOnError(new Consumer<Throwable>() {
                    @Override
                    public void accept(@NonNull Throwable throwable) throws Exception {
                        BaseApplication.showToast("二维码生成失败");
                        throwable.printStackTrace();
                    }
                })
                .subscribe(new Consumer<Bitmap>() {
                    @Override
                    public void accept(@NonNull Bitmap bitmap) throws Exception {
                        img_qr_code1.setImageBitmap(bitmap);
                        img_qr_code2.setImageBitmap(bitmap);
                    }
                });
//        try {
//            String content = new String(company.getQrcode().getBytes("UTF-8"), "ISO-8859-1");
//            TLog.log("content-----------" + content);
//            Bitmap qrCode = CodeCreator.createQRCode(mContext, content);
//            img_qr_code1.setImageBitmap(qrCode);
//            img_qr_code2.setImageBitmap(qrCode);
//        } catch (Exception e) {
//            BaseApplication.showToast("二维码生成失败");
//            e.printStackTrace();
//        }
    }

    private void initChildViews(List<FavoriteCompanyBean.DataBean.InvoiceTypeListBean> list) {
        ViewGroup.MarginLayoutParams lp = new ViewGroup.MarginLayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        lp.leftMargin = 8;
        lp.rightMargin = 8;
        lp.topMargin = 5;
        lp.bottomMargin = 5;

        MyCompanyDetailsAdapter myCompanyDetailsAdapter = new MyCompanyDetailsAdapter(list);
        myCompanyDetailsAdapter.setOnItemClickListener(this);
        viewRecycler.setAdapter(myCompanyDetailsAdapter);

    }

    /**
     * 为每个view 添加点击事件
     */
    private void initEvents(final TextView tv, final String typeId) {
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, EstimateLocationActivity.class);
                intent.putExtra("companyId", companyId);
                intent.putExtra(FinanceFragment.EXTRA_DATA_LABEL, typeId);
                intent.putExtra(FinanceFragment.EXTRA_DATA_LABEL_NAME, tv.getText().toString().trim());
                mContext.startActivity(intent);
                Intent intent1 = new Intent();
                intent1.setAction(START_ACTIVITY_FROM_DETAILS);
                mContext.sendBroadcast(intent1);
                ActivityUtils.finishActivity(FavCompanyDetailsActivity.class);
                ActivityUtils.finishActivity(CompanyManagerActivity.class);
            }
        });
    }

    @OnClick({R.id.img_details_viewpager_share, R.id.img_details_viewpager_delete, R.id.img_details_viewpager_next})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img_details_viewpager_share:
                setDialog();
                break;
            case R.id.img_details_viewpager_delete:
                if (onDelClickListener != null) {
                    onDelClickListener.onDelClick(deleteId);
                }
                break;
            case R.id.img_details_viewpager_next:
                if (onNextClickListener != null) {
                    onNextClickListener.onNextClick();
                }
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
                if (api.isWXAppInstalled()) {
                    WXWebpageObject wxWebpageObject = new WXWebpageObject();
                    wxWebpageObject.webpageUrl = company.getQrcode();

                    WXMediaMessage wxMediaMessage = new WXMediaMessage(wxWebpageObject);
                    wxMediaMessage.title = getString(R.string.please_open_check);
                    wxMediaMessage.description = getString(R.string.tohave_fapiaobao);
                    wxMediaMessage.thumbData = ImageUtils.drawable2Bytes(getResources().getDrawable(R.mipmap.icon), Bitmap.CompressFormat.JPEG);

                    SendMessageToWX.Req req = new SendMessageToWX.Req();
                    req.transaction = String.valueOf(System.currentTimeMillis());
                    req.message = wxMediaMessage;

                    req.scene = SendMessageToWX.Req.WXSceneSession;
                    api.sendReq(req);
                    mCameraDialog.dismiss();
                    //记录用户分享状态
                    set(SHARE_SOCORE, true);
                } else {
                    BaseApplication.showToast("请安装微信客户端");
                }

            }
        });
        root.findViewById(R.id.weibo).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (umShareAPI.isInstall(getActivity(), SHARE_MEDIA.SINA)) {
                    new ShareAction(getActivity())
                            .setPlatform(SHARE_MEDIA.SINA)//传入平台
                            .withMedia(web)
                            .setCallback(umShareListener)//回调监听器
                            .share();
                    mCameraDialog.dismiss();
                } else {
                    BaseApplication.showToast("请安装新浪微博客户端");
                }

            }
        });
        root.findViewById(R.id.moments).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (umShareAPI.isInstall(getActivity(), SHARE_MEDIA.WEIXIN_CIRCLE)) {
                    new ShareAction(getActivity())
                            .setPlatform(SHARE_MEDIA.WEIXIN_CIRCLE)//传入平台
                            .withMedia(web)
                            .setCallback(umShareListener)//回调监听器
                            .share();
                    mCameraDialog.dismiss();
                } else {
                    BaseApplication.showToast("请安装微信客户端");
                }

            }
        });
        root.findViewById(R.id.qq).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RxPermissions rxPermissions = new RxPermissions(getActivity());
                rxPermissions.request(Manifest.permission.READ_EXTERNAL_STORAGE).subscribe(new Observer<Boolean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Boolean aBoolean) {
                        if (aBoolean) {
                            if (umShareAPI.isInstall(getActivity(), SHARE_MEDIA.QQ)) {
                                TLog.d(" if (umShareAPI.isInstall(getActivity(), SHARE_MEDIA.QQ)) {","");
                                new ShareAction(getActivity())
                                        .setPlatform(SHARE_MEDIA.QQ)//传入平台
                                        .withMedia(web)
                                        .setCallback(umShareListener)//回调监听器
                                        .share();
                                mCameraDialog.dismiss();
                            } else {
                                BaseApplication.showToast("请安装QQ客户端");
                            }
                        }
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });


            }
        });
        root.findViewById(R.id.qzone).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (umShareAPI.isInstall(getActivity(), SHARE_MEDIA.QZONE)) {
                    new ShareAction(getActivity())
                            .setPlatform(SHARE_MEDIA.QZONE)//传入平台
                            .withMedia(web)
                            .setCallback(umShareListener)//回调监听器
                            .share();
                    mCameraDialog.dismiss();
                } else {
                    BaseApplication.showToast("请安装QQ空间客户端");
                }
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

    public void getCompanyDetails(String id) {
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

    private OnDelClickListener onDelClickListener;

    public void setOnDelClickListener(OnDelClickListener onDelClickListener) {
        this.onDelClickListener = onDelClickListener;
    }

    @Override
    public void onItemClick(FavoriteCompanyBean.DataBean.InvoiceTypeListBean dataBean) {
        TLog.log("dataBean.getName()"+dataBean.getName());
        TLog.log("dataBean.getId()"+dataBean.getId());

        Intent intent = new Intent(mContext, EstimateLocationActivity.class);
        intent.putExtra("companyId", companyId);
        intent.putExtra(FinanceFragment.EXTRA_DATA_LABEL, dataBean.getId());
        intent.putExtra(FinanceFragment.EXTRA_DATA_LABEL_NAME, dataBean.getName());
        mContext.startActivity(intent);
        Intent intent1 = new Intent();
        intent1.setAction(START_ACTIVITY_FROM_DETAILS);
        mContext.sendBroadcast(intent1);
        ActivityUtils.finishActivity(FavCompanyDetailsActivity.class);
        ActivityUtils.finishActivity(CompanyManagerActivity.class);
    }

    public interface OnDelClickListener {
        void onDelClick(String companyId);
    }

    private OnNextClickListener onNextClickListener;

    public void setOnNextClickListener(OnNextClickListener onNextClickListener) {
        this.onNextClickListener = onNextClickListener;
    }

    public interface OnNextClickListener {
        void onNextClick();
    }

}
