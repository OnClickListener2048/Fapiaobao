package com.pilipa.fapiaobao.ui;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mylibrary.utils.ImageUtils;
import com.example.mylibrary.utils.TLog;
import com.lzy.okgo.OkGo;
import com.pilipa.fapiaobao.R;
import com.pilipa.fapiaobao.account.AccountHelper;
import com.pilipa.fapiaobao.adapter.MyInvoiceNameAdapter;
import com.pilipa.fapiaobao.base.BaseActivity;
import com.pilipa.fapiaobao.base.BaseApplication;
import com.pilipa.fapiaobao.net.Api;
import com.pilipa.fapiaobao.net.Constant;
import com.pilipa.fapiaobao.net.bean.LoginWithInfoBean;
import com.pilipa.fapiaobao.net.bean.me.NormalBean;
import com.pilipa.fapiaobao.net.bean.publish.DemandDetails;
import com.pilipa.fapiaobao.ui.fragment.DemandsDetailsReceiptFragment;
import com.pilipa.fapiaobao.ui.fragment.DemandsDetailsReceiptFragment2;
import com.pilipa.fapiaobao.ui.fragment.DemandsDetailsReceiptFragment3;
import com.pilipa.fapiaobao.ui.model.Image;
import com.pilipa.fapiaobao.ui.widget.HorizontalListView;
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

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.pilipa.fapiaobao.R.id.btn_shut_down_early;
import static com.pilipa.fapiaobao.net.Constant.REQUEST_SUCCESS;
import static com.pilipa.fapiaobao.net.Constant.STATE_COMPETENT;
import static com.pilipa.fapiaobao.net.Constant.STATE_CONFIRMING;
import static com.pilipa.fapiaobao.net.Constant.STATE_DEMAND_CLOSE;
import static com.pilipa.fapiaobao.net.Constant.STATE_DEMAND_FINISH;
import static com.pilipa.fapiaobao.net.Constant.STATE_DEMAND_ING;
import static com.pilipa.fapiaobao.net.Constant.STATE_INCOMPETENT;
import static com.pilipa.fapiaobao.net.Constant.STATE_MAILING;
import static com.pilipa.fapiaobao.net.Constant.VARIETY_GENERAL_ELECTRON;
import static com.pilipa.fapiaobao.net.Constant.VARIETY_GENERAL_PAPER;
import static com.pilipa.fapiaobao.net.Constant.VARIETY_SPECIAL_PAPER;

/**
 * Created by lyt on 2017/10/16.
 */

public class DemandActivity extends BaseActivity{
    private static final String TAG = "DemandActivity";
    @Bind(R.id.container_paper_normal_receipt)
    FrameLayout containerPaperNormalReceipt;
    @Bind(R.id.container_paper_special_receipt)
    FrameLayout containerPaperSpecialReceipt;
    @Bind(R.id.container_paper_elec_receipt)
    FrameLayout containerPaperElecReceipt;
    @Bind(R.id.translate_details)
    LinearLayout translateDetails;
    @Bind(R.id.translate)
    LinearLayout translate;
    @Bind(R.id.tv_bounsAmount)
    TextView tvBounsAmount;
    @Bind(R.id.tv_leftAmount)
    TextView tvLeftAmount;
    @Bind(R.id.tv_attentions)
    TextView tvAttentions;
    @Bind(R.id.tv_deadline)
    TextView tvDeadline;
    @Bind(R.id.tv_amount)
    TextView tvAmount;
    @Bind(R.id.tv_publishTime)
    TextView tvPublishTime;
    @Bind(R.id.tv_receiver)
    TextView tvReceiver;
    @Bind(R.id.tv_telephone)
    TextView tvTelephone;
    @Bind(R.id.tv_phone)
    TextView tvPhone;
    @Bind(R.id.tv_address)
    TextView tvAddress;
    @Bind(R.id.company_name)
    TextView companyName;
    @Bind(R.id.tex_number)
    TextView texNumber;
    @Bind(R.id.company_address)
    TextView companyAddress;
    @Bind(R.id.number)
    TextView number;
    @Bind(R.id.bank_account)
    TextView bankAccount;
    @Bind(R.id.bank)
    TextView bank;
    @Bind(R.id.tv_AlreadyCollected)
    TextView tvAlreadyCollected;
    @Bind(R.id.btn_shut_down_early)
    TextView btnShutDownEarly;
    @Bind(R.id.img_state)
    ImageView img_state;
    @Bind(R.id.tv_receive)
    TextView tv_receive;
    @Bind(R.id.horizontalListView)
    HorizontalListView horizontalListView;
    @Bind(R.id.ll_receiptlist)
    LinearLayout ll_receiptlist;
    @Bind(R.id.ll_no_record)
    LinearLayout ll_no_record;
    @Bind(R.id.tv_num_1)
    TextView tv_num_1;
    @Bind(R.id.tv_num_2)
    TextView tv_num_2;
    @Bind(R.id.tv_num_3)
    TextView tv_num_3;
    @Bind(R.id.qr)
    ImageView qr;
    @Bind(R.id.tv_low_limit)
    TextView tv_low_limit;
    @Bind(R.id.ll_receive)
    LinearLayout ll_receive;
    @Bind(R.id.ll_container_paper_normal_receipt)
    LinearLayout container_paper_normal_receipt;
    @Bind(R.id.ll_container_paper_special_receipt)
    LinearLayout container_paper_special_receipt;
    @Bind(R.id.ll_container_paper_elec_receipt)
    LinearLayout container_paper_elec_receipt;
    private UMWeb web;
    //发票类型
    @Bind(R.id.paper_normal_receipt)
    TextView paperNormalReceipt;
    @Bind(R.id.paper_special_receipt)
    TextView paperSpecialReceipt;
    @Bind(R.id.paper_elec_receipt)
    TextView paperElecReceipt;


    List<DemandDetails.DataBean.OrderInvoiceListBean> mDataList = new ArrayList<>();
    List<String> mList = new ArrayList<>();
    private boolean isShow = true;//当前详情是否显示

    public static final String PAPER_NORMAL_RECEIPT_DATA = "paper_normal_receipt_data";
    public static final String PAPER_SPECIAL_RECEIPT_DATA = "paper_special_receipt_data";
    public static final String PAPER_ELEC_RECEIPT_DATA = "paper_elec_receipt_data";

    private String demandId;

    private ArrayList<Image> images;
    private DemandsDetailsReceiptFragment paperNormalReceiptFragment;
    private DemandsDetailsReceiptFragment2 paperSpecialReceiptFragment;
    private DemandsDetailsReceiptFragment3 paperElecReceiptFragment;
    private MyInvoiceNameAdapter invoiceNameAdapter;
    ArrayList<Image> images_qualified ;
    private Dialog mCameraDialog;
    private Dialog mTipDialog;
    private Dialog mDialog;
    private boolean isCanShutDown = false;//能否提前关闭

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
    @Override
    protected int getLayoutId() {
        return R.layout.activity_demand;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        TLog.log(TAG+"onActivityResult1");
        TLog.log(TAG+"requestCode"+requestCode);
        TLog.log(TAG+"resultCode"+resultCode);
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);
        switch (resultCode) {
            case DemandsDetailsReceiptFragment.RESULT_CODE_BACK:
                TLog.log(TAG+"onActivityResult3");
                demandDetails(demandId,false);
                break;
            default:
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    @OnClick({R.id.demand_back, R.id.fl_change, btn_shut_down_early, R.id.link_to_telephone, R.id.link_to_phone, R.id.tv_qualified_list, R.id.iv_demand_share})
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_demand_share: {
                setDialog();
            }
            break;
            case R.id.tv_qualified_list: {
                if (images_qualified!=null && images_qualified.size() != 0) {
                    Intent intent = new Intent(DemandActivity.this, QualifiedInvoiceActivity.class);
                    intent.putParcelableArrayListExtra("images_qualified",images_qualified);
                    startActivity(intent);
                } else {
                    Toast.makeText(DemandActivity.this, "暂无确认合格发票", Toast.LENGTH_SHORT).show();
                }
            }
            break;
            case R.id.demand_back: {
                finish();
            }
            break;
            case btn_shut_down_early: {
                setShutDialog(isCanShutDown);

            }
            break;
            case R.id.fl_change: {
                if (isShow) {
                    translateDetails.setVisibility(View.GONE);
                    translate.setVisibility(View.VISIBLE);
                    isShow = !isShow;
                } else {
                    translateDetails.setVisibility(View.VISIBLE);
                    translate.setVisibility(View.GONE);
                    isShow = !isShow;
                }
            }
            break;
        }
    }

    @Override
    public void initView() {
        invoiceNameAdapter = new MyInvoiceNameAdapter(this);
        horizontalListView.setAdapter(invoiceNameAdapter);

        web = new UMWeb(Constant.MATCH);
        web.setTitle("伙伴们，多余的发票也能挣红包了~");//标题
        UMImage umImage = new UMImage(this, R.mipmap.icon);
        web.setThumb(umImage);  //缩略图
        web.setDescription("伙伴们，多余的发票也能挣红包了~");//描述

        api = WXAPIFactory.createWXAPI(this, Constants.APP_ID);
        api.registerApp(Constants.APP_ID);
    }

    private void setUpData(List<DemandDetails.DataBean.OrderInvoiceListBean> results, boolean isSetList) {
        Log.d(TAG, "setUpData:   private void setUpData(ArrayList<model.ResultsBean> body) {");
        isCanShutDown = true;
        if (results.size() == 0) {
            isCanShutDown = true;
            ll_no_record.setVisibility(View.VISIBLE);
            ll_receiptlist.setVisibility(View.GONE);
        } else {

            ll_no_record.setVisibility(View.GONE);
            ll_receiptlist.setVisibility(View.VISIBLE);
            for (DemandDetails.DataBean.OrderInvoiceListBean result : results) {
                if(STATE_CONFIRMING.equals(result.getState())
                        ||STATE_MAILING.equals(result.getState())){//收到票 有未完成发票
                    isCanShutDown = false;
                }
            }
            Log.d(TAG, "all receipt is checked  isCanShutDown  "+isCanShutDown);
            images = new ArrayList<>();
            for (DemandDetails.DataBean.OrderInvoiceListBean result : results) {
                Log.d(TAG, "setUpData:  for (model.ResultsBean result : body) {");
                Image image = new Image();
                image.isSelected = false;
                image.name = result.getId();
                image.path = result.getUrl();
                image.position = -1;
                image.isCapture = false;
                image.isFromNet = true;
                image.state = result.getState();
                image.amount = String.valueOf(result.getAmount());
                image.logisticsTradeno = result.getLogisticsTradeno();
                image.logisticsCompany = result.getLogisticsCompany();
                image.variety = result.getVariety();
                image.reason = result.getReason();
                images.add(image);
            }

            ArrayList<Image> images1 = new ArrayList<>();
            ArrayList<Image> images2 = new ArrayList<>();
            ArrayList<Image> images3 = new ArrayList<>();
            images_qualified= new ArrayList<>();
            for (int i = 0; i < images.size(); i++) {
                if (STATE_COMPETENT.equals(images.get(i).state)) {
                    images_qualified.add(images.get(i));//合格发票
                }
                if(!STATE_INCOMPETENT .equals(images.get(i).state)){
                    if (VARIETY_GENERAL_PAPER.equals(images.get(i).variety)) {
                        images1.add(images.get(i));
                    } else if (VARIETY_SPECIAL_PAPER.equals(images.get(i).variety)) {
                        images2.add(images.get(i));
                    } else if (VARIETY_GENERAL_ELECTRON.equals(images.get(i).variety)) {
                        images3.add(images.get(i));
                    }
                }
            }

            tv_num_1.setText(String.format(getResources().getString(R.string.paper_normal_receipt_num), images1.size()));
            tv_num_2.setText(String.format(getResources().getString(R.string.paper_special_receipt_num), images2.size()));
            tv_num_3.setText(String.format(getResources().getString(R.string.paper_elec_receipt_num), images3.size()));
            if (isSetList) {
                Bundle bundle = new Bundle();
                bundle.putParcelableArrayList(PAPER_NORMAL_RECEIPT_DATA, images1);
                paperNormalReceiptFragment = DemandsDetailsReceiptFragment.newInstance(bundle);
                addCaptureFragment2(R.id.container_paper_normal_receipt, paperNormalReceiptFragment);
                bundle.putParcelableArrayList(PAPER_SPECIAL_RECEIPT_DATA, images2);
                paperSpecialReceiptFragment = DemandsDetailsReceiptFragment2.newInstance(bundle);
                addCaptureFragment2(R.id.container_paper_special_receipt, paperSpecialReceiptFragment);
                bundle.putParcelableArrayList(PAPER_ELEC_RECEIPT_DATA, images3);
                paperElecReceiptFragment = DemandsDetailsReceiptFragment3.newInstance(bundle);
                addCaptureFragment2(R.id.container_paper_elec_receipt, paperElecReceiptFragment);
            }

            if(images1.size()==0){
                container_paper_normal_receipt.setVisibility(View.GONE);
            }
            if(images2.size()==0){
                container_paper_special_receipt.setVisibility(View.GONE);
            }
            if(images3.size()==0){
                container_paper_elec_receipt.setVisibility(View.GONE);
            }

            if(images1.size()==0&&images2.size()==0&&images3.size()==0){
                ll_no_record.setVisibility(View.VISIBLE);
                ll_receiptlist.setVisibility(View.GONE);
            }
        }
    }

    @Override
    public void initData() {
        demandId = getIntent().getStringExtra("demandId");
        Log.d(TAG, "initData:demandDetails demandId" + demandId);
        if (demandId != null) {
            demandDetails(demandId,true);
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
    @Override
    protected void onPause() {
        OkGo.cancelTag(OkGo.getInstance().getOkHttpClient(),"demandDetails");
        super.onPause();
    }
    public void demandDetails(String demandId, final boolean isSetList) {
        if (AccountHelper.getToken() != null && AccountHelper.getToken() != "") {
            String token = AccountHelper.getToken();
            Api.demandDetails(token, demandId, new Api.BaseViewCallbackWithOnStart<DemandDetails>() {
                @Override
                public void onStart() {
                    showProgressDialog();
                }
                @Override
                public void onFinish() {
                    hideProgressDialog();
                }
                @Override
                public void onError() {
                    hideProgressDialog();
                }
                @Override
                public void setData(DemandDetails demandDetails) {
                    if (demandDetails.getStatus() == REQUEST_SUCCESS) {
                        Log.d(TAG, "demandDetails success");
                        DemandDetails.DataBean bean = demandDetails.getData();
                        mList.clear();
                        mList.addAll(bean.getInvoiceNameList());
                        invoiceNameAdapter.initData(mList);

                        tvBounsAmount.setText(String.format("%.2f", bean.getDemand().getTotalBonus()));
                        tvAmount.setText(String.format("%.2f", bean.getDemand().getTotalAmount()));
                        tvLeftAmount.setText(String.format("%.2f", bean.getDemand().getLeftBonus()));
                        tvPublishTime.setText("发布时间：" + bean.getDemand().getPublishDate());
                        tvDeadline.setText("截止日期：" + bean.getDemand().getDeadline());
                        tvAttentions.setText(bean.getDemand().getAttentions().isEmpty() ? "无" : bean.getDemand().getAttentions());
                        tv_receive.setText(String.format("%.2f", bean.getReceivedAmount()));
                        tvAlreadyCollected.setText(String.valueOf(bean.getReceivedNum()));
                        tv_low_limit.setText(String.format("%.2f",bean.getDemand().getMailMinimum())+ "元");
                        String district = null;
                        if(bean.getDemand().getDemandPostage().getDistrict()!=null){
                            district = bean.getDemand().getDemandPostage().getDistrict()+ " ";
                        }
                        String city = null;
                        if(bean.getDemand().getDemandPostage().getCity()!=null){
                            city = bean.getDemand().getDemandPostage().getCity()+" ";
                        }
                        tvAddress.setText(city+ district + bean.getDemand().getDemandPostage().getAddress());

                        if (!VARIETY_GENERAL_ELECTRON.equals(bean.getDemand().getInvoiceVarieties())) {
                            ll_receive.setVisibility(View.VISIBLE);

                            tvPhone.setText(bean.getDemand().getDemandPostage().getPhone());
                            tvTelephone.setText(bean.getDemand().getDemandPostage().getTelephone());
                            tvReceiver.setText(bean.getDemand().getDemandPostage().getReceiver());
                        } else {
                            ll_receive.setVisibility(View.GONE);
                        }
                        if (bean.getDemand().getCompany() != null) {
                            companyName.setText(bean.getDemand().getCompany().getName());
                            number.setText(bean.getDemand().getCompany().getPhone());
                            companyAddress.setText(bean.getDemand().getCompany().getAddress());
                            bankAccount.setText(bean.getDemand().getCompany().getAccount());
                            bank.setText(bean.getDemand().getCompany().getDepositBank());
                            texNumber.setText(bean.getDemand().getCompany().getTaxno());

                            try {
                                String content = new String(bean.getDemand().getCompany().getQrcode().getBytes("UTF-8"), "ISO-8859-1");
                                TLog.log("content-----------"+content);
                                Bitmap qrCode = CodeCreator.createQRCode(DemandActivity.this,content);
                                qr.setImageBitmap(qrCode);
                            } catch (Exception e) {
                                BaseApplication.showToast("二维码生成失败");
                                e.printStackTrace();
                            }
                        }
                        //发票列表
                            mDataList.clear();
                            mDataList.addAll(bean.getOrderInvoiceList());
                            setUpData(mDataList,isSetList);


                        if (STATE_DEMAND_ING.equals(bean.getDemand().getState())) {
                            img_state.setImageResource(R.mipmap.on_the_way);
                            btnShutDownEarly.setVisibility(View.VISIBLE);
                        } else if (STATE_DEMAND_FINISH.equals(bean.getDemand().getState())) {
                            img_state.setImageResource(R.mipmap.finish);
                            btnShutDownEarly.setVisibility(View.GONE);
                        } else if (STATE_DEMAND_CLOSE.equals(bean.getDemand().getState())) {
                            img_state.setImageResource(R.mipmap.close);
                            btnShutDownEarly.setVisibility(View.GONE);
                        }
                        //发票类型列表
                        String sourceStr = bean.getDemand().getInvoiceVarieties();
                        if(sourceStr != null){
                            if(sourceStr.contains("1")){paperNormalReceipt.setVisibility(View.VISIBLE);}
                            if(sourceStr.contains("2")) {paperSpecialReceipt.setVisibility(View.VISIBLE);}
                            if(sourceStr.contains("3")){paperElecReceipt.setVisibility(View.VISIBLE);}
                        }

                    }
                }
            });
        }
    }

    private void shatDownEarly(final String id) {
        AccountHelper.isTokenValid(new Api.BaseViewCallback<LoginWithInfoBean>() {
            @Override
            public void setData(LoginWithInfoBean normalBean) {
                if (normalBean.getStatus() == 200) {
                    Api.shatDownEarly(AccountHelper.getToken(), id, new Api.BaseViewCallback<NormalBean>() {
                        @Override
                        public void setData(NormalBean normalBean) {
                            Toast.makeText(DemandActivity.this, "需求已关闭", Toast.LENGTH_SHORT).show();
                            demandDetails(id,false);
                            DemandActivity.this.finish();
                            Log.d(TAG, "updateData:shatDownEarly success");
                        }
                    });
                } else {
                    startActivity(new Intent(DemandActivity.this, LoginActivity.class));
                    finish();
                }
            }
        });
    }


    private void setDialog() {
        mCameraDialog = new Dialog(DemandActivity.this, R.style.BottomDialog);
        LinearLayout root = (LinearLayout) LayoutInflater.from(DemandActivity.this).inflate(
                R.layout.layout_share_, null);
        //初始化视图
        root.findViewById(R.id.weixin).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WXWebpageObject wxWebpageObject = new WXWebpageObject();
                wxWebpageObject.webpageUrl = Constant.MATCH;

                WXMediaMessage wxMediaMessage = new WXMediaMessage(wxWebpageObject);
                wxMediaMessage.title= getString(R.string.share_pub_title);
                wxMediaMessage.description = getString(R.string.share_pub_description);
                wxMediaMessage.thumbData = ImageUtils.drawable2Bytes(getResources().getDrawable(R.mipmap.share_redbag), Bitmap.CompressFormat.JPEG);

                SendMessageToWX.Req req = new SendMessageToWX.Req();
                req.transaction = String.valueOf(System.currentTimeMillis());
                req.message = wxMediaMessage;

                req.scene = SendMessageToWX.Req.WXSceneSession;
                api.sendReq(req);
                mCameraDialog.dismiss();
            }
        });
        root.findViewById(R.id.weibo).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new ShareAction(DemandActivity.this)
                        .setPlatform(SHARE_MEDIA.SINA)//传入平台
                        .withMedia(web)
                        .setCallback(umShareListener)//回调监听器
                        .share();
                mCameraDialog.dismiss();
            }
        });
        root.findViewById(R.id.moments).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new ShareAction(DemandActivity.this)
                        .setPlatform(SHARE_MEDIA.WEIXIN_CIRCLE)//传入平台
                        .withMedia(web)
                        .setCallback(umShareListener)//回调监听器
                        .share();
                mCameraDialog.dismiss();
            }
        });
        root.findViewById(R.id.qq).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new ShareAction(DemandActivity.this)
                        .setPlatform(SHARE_MEDIA.QQ)//传入平台
                        .withMedia(web)
                        .setCallback(umShareListener)//回调监听器
                        .share();
                mCameraDialog.dismiss();
            }
        });
        root.findViewById(R.id.qzone).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new ShareAction(DemandActivity.this)
                        .setPlatform(SHARE_MEDIA.QZONE)//传入平台
                        .withMedia(web)
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
    private void setShutDialog(boolean isCanShutDown) {
        mDialog = new Dialog(DemandActivity.this, R.style.BottomDialog);
        LinearLayout root;
        if(isCanShutDown){
            root = (LinearLayout) LayoutInflater.from(DemandActivity.this).inflate(
                    R.layout.layout_shutdown1_tip, null);
            root.findViewById(R.id.btn_cancel).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mDialog.dismiss();
                }
            });
            root.findViewById(R.id.btn_confirm).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (demandId != null) {
                        shatDownEarly(demandId);
                    }
                }
            });
        }else{
            root = (LinearLayout) LayoutInflater.from(DemandActivity.this).inflate(
                    R.layout.layout_shutdown2_tip, null);
            root.findViewById(R.id.btn_confirm).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mDialog.dismiss();
                }
            });
        }
        mDialog.setContentView(root);
        Window dialogWindow = mDialog.getWindow();
        dialogWindow.setGravity(Gravity.CENTER);
//        dialogWindow.setWindowAnimations(R.style.dialogstyle); // 添加动画
        WindowManager.LayoutParams lp = dialogWindow.getAttributes(); // 获取对话框当前的参数值
        lp.x = 0; // 新位置X坐标
        lp.y = 0; // 新位置Y坐标
        lp.width = getResources().getDisplayMetrics().widthPixels; // 宽度
        root.measure(0, 0);
        lp.height = root.getMeasuredHeight();

        lp.alpha = 9f; // 透明度
        dialogWindow.setAttributes(lp);
        mDialog.show();
    }
}
