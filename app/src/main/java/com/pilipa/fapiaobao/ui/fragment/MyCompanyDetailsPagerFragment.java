package com.pilipa.fapiaobao.ui.fragment;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
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

import com.example.mylibrary.utils.EncodeUtils;
import com.example.mylibrary.utils.ImageUtils;
import com.example.mylibrary.utils.TLog;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.pilipa.fapiaobao.R;
import com.pilipa.fapiaobao.base.BaseApplication;
import com.pilipa.fapiaobao.base.BaseFragment;
import com.pilipa.fapiaobao.entity.Company;
import com.pilipa.fapiaobao.net.Api;
import com.pilipa.fapiaobao.net.Constant;
import com.pilipa.fapiaobao.net.bean.invoice.MacherBeanToken;
import com.pilipa.fapiaobao.net.bean.me.CompanyDetailsBean;
import com.pilipa.fapiaobao.net.bean.me.FavoriteCompanyBean;
import com.pilipa.fapiaobao.ui.EstimateActivity;
import com.pilipa.fapiaobao.ui.widget.XCFlowLayout;
import com.pilipa.fapiaobao.utils.TDevice;
import com.pilipa.fapiaobao.wxapi.Constants;
import com.pilipa.fapiaobao.zxing.encode.CodeCreator;
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

import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.pilipa.fapiaobao.net.Constant.REQUEST_SUCCESS;

/**
 * Created by lyt on 2017/10/17.
 */

public class MyCompanyDetailsPagerFragment extends BaseFragment {
    private static final String TAG = "MyCompanyDetailsPagerFragment";
    private TextView tv_companyName, tv_receptCode, tv_address, tv_phoneNum, tv_bankName, tv_account;
    private Dialog mCameraDialog;
    private XCFlowLayout flowLayout;
    private ImageView img_details_viewpager_next;
    private TextView tv_newNeed;
    private LinearLayout ll_we_need;


    private IWXAPI api;
    private UMShareListener umShareListener = new UMShareListener() {
        @Override
        public void onStart(SHARE_MEDIA share_media) {

        }

        @Override
        public void onResult(SHARE_MEDIA share_media) {
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
        web = new UMWeb(Constant.MATCH);
        web.setTitle("伙伴们，多余的发票也能挣红包了~");//标题
        UMImage umImage = new UMImage(getActivity(), R.mipmap.icon);
        web.setThumb(umImage);  //缩略图
        web.setDescription("伙伴们，多余的发票也能挣红包了~");//描述
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
        flowLayout = (XCFlowLayout) view.findViewById(R.id.flowLayout);
        ll_we_need = (LinearLayout) view.findViewById(R.id.ll_we_need);
        tv_newNeed = (TextView) view.findViewById(R.id.tv_newNeed);
        ImageView img_qr_code1 = (ImageView) view.findViewById(R.id.img_qr_code1);
        ImageView img_qr_code2 = (ImageView) view.findViewById(R.id.img_qr_code2);
        LinearLayout ll_qr_code2 = (LinearLayout) view.findViewById(R.id.ll_qr_code2);
        Bundle arguments = getArguments();
        Company company = arguments.getParcelable("company");
        if (company != null) {
            tv_companyName.setText(company.getName());
            tv_receptCode.setText(company.getTaxno());
            tv_address.setText(company.getAddress());
            tv_phoneNum.setText(company.getPhone());
            tv_bankName.setText(company.getDepositBank());
            tv_account.setText(company.getAccount());
            TLog.d("qrcode" , company.getQrcode()+"");
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
        Log.d(TAG,"Company id" +company.getId());

        try {
            String content = new String(company.getQrcode().getBytes("UTF-8"), "ISO-8859-1");
            TLog.log("content-----------"+content);
            Bitmap qrCode = CodeCreator.createQRCode(mContext,content);
            img_qr_code1.setImageBitmap(qrCode);
            img_qr_code2.setImageBitmap(qrCode);
        } catch (Exception e) {
            BaseApplication.showToast("二维码生成失败");
            e.printStackTrace();
        }
    }

    private void initChildViews(List<FavoriteCompanyBean.DataBean.InvoiceTypeListBean> list) {
        ViewGroup.MarginLayoutParams lp = new ViewGroup.MarginLayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        lp.leftMargin = 5;
        lp.rightMargin = 5;
        lp.topMargin = 5;
        lp.bottomMargin = 5;

        for (int i = 0; i < list.size(); i++) {
            TextView view = new TextView(mContext);
            view.setGravity(Gravity.CENTER);
            view.setText(list.get(i).getName());
            view.setTextSize(TDevice.px2dp(26));
            view.setPadding(5, 3, 5, 3);
            view.setTextColor(getResources().getColor(R.color.main_style));
            view.setBackgroundDrawable(getResources().getDrawable(R.drawable.selector_invoice_receipt_kind_bg));
            initEvents(view,list.get(i).getId());
            flowLayout.addView(view, lp);
        }
    }

    /**
     * 为每个view 添加点击事件
     */
    private void initEvents(final TextView tv,final String typeId) {
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, EstimateActivity.class);
                intent.putExtra(FinanceFragment.EXTRA_DATA_LABEL,typeId);
                intent.putExtra(FinanceFragment.EXTRA_DATA_LABEL_NAME,tv.getText().toString().trim());
                mContext.startActivity(intent);

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
                    onDelClickListener.onDelClick();
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
                    wxWebpageObject.webpageUrl = Constant.MATCH;

                    WXMediaMessage wxMediaMessage = new WXMediaMessage(wxWebpageObject);
                    wxMediaMessage.description = "伙伴们，多余的发票也能挣红包了~";
                    wxMediaMessage.title = "伙伴们，多余的发票也能挣红包了~";
                    wxMediaMessage.thumbData = ImageUtils.drawable2Bytes(getResources().getDrawable(R.mipmap.icon), Bitmap.CompressFormat.JPEG);

                    SendMessageToWX.Req req = new SendMessageToWX.Req();
                    req.transaction = String.valueOf(System.currentTimeMillis());
                    req.message = wxMediaMessage;

                    req.scene = SendMessageToWX.Req.WXSceneSession;
                    api.sendReq(req);
                    mCameraDialog.dismiss();
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
                if (umShareAPI.isInstall(getActivity(), SHARE_MEDIA.QQ)) {
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
    public interface OnDelClickListener {
        void onDelClick();
    }
    private OnNextClickListener onNextClickListener;
    public void setOnNextClickListener(OnNextClickListener onNextClickListener) {
        this.onNextClickListener = onNextClickListener;
    }
    public interface OnNextClickListener {
        void onNextClick();
    }
}
