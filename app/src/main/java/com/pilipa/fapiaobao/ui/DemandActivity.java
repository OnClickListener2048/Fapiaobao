package com.pilipa.fapiaobao.ui;

import android.Manifest;
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
import com.pilipa.fapiaobao.base.BaseApplication;
import com.pilipa.fapiaobao.base.BaseNoNetworkActivity;
import com.pilipa.fapiaobao.net.Api;
import com.pilipa.fapiaobao.net.Constant;
import com.pilipa.fapiaobao.net.bean.me.NormalBean;
import com.pilipa.fapiaobao.net.bean.publish.DemandDetails;
import com.pilipa.fapiaobao.ui.fragment.DemandsDetailsReceiptFragment;
import com.pilipa.fapiaobao.ui.fragment.DemandsDetailsReceiptFragment2;
import com.pilipa.fapiaobao.ui.fragment.DemandsDetailsReceiptFragment3;
import com.pilipa.fapiaobao.ui.model.Image;
import com.pilipa.fapiaobao.ui.widget.HorizontalListView;
import com.pilipa.fapiaobao.utils.DialogUtil;
import com.pilipa.fapiaobao.wxapi.Constants;
import com.pilipa.fapiaobao.zxing.encode.CodeCreator;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
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

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

import static com.pilipa.fapiaobao.R.id.btn_shut_down_early;
import static com.pilipa.fapiaobao.base.BaseApplication.SHARE_SOCORE;
import static com.pilipa.fapiaobao.base.BaseApplication.set;
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

public class DemandActivity extends BaseNoNetworkActivity {
    public static final String PAPER_NORMAL_RECEIPT_DATA = "paper_normal_receipt_data";
    public static final String PAPER_SPECIAL_RECEIPT_DATA = "paper_special_receipt_data";
    public static final String PAPER_ELEC_RECEIPT_DATA = "paper_elec_receipt_data";
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
    //发票类型
    @Bind(R.id.paper_normal_receipt)
    TextView paperNormalReceipt;
    @Bind(R.id.paper_special_receipt)
    TextView paperSpecialReceipt;
    @Bind(R.id.paper_elec_receipt)
    TextView paperElecReceipt;
    @Bind(R.id.tv_qualified_list)
    TextView tvQualifiedList;
    List<DemandDetails.DataBean.OrderInvoiceListBean> mDataList = new ArrayList<>();
    List<String> mList = new ArrayList<>();
    ArrayList<Image> images_qualified;
    @Bind(R.id.smartRefreshLayout)
    SmartRefreshLayout smartRefreshLayout;
    private UMWeb web;
    private boolean isShow = true;//当前详情是否显示
    private String demandId;
    private MyInvoiceNameAdapter invoiceNameAdapter;
    private Dialog mCameraDialog;
    private boolean isCanShutDown = false;//能否提前关闭
    private UMShareAPI umShareAPI;

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
                    TLog.d(TAG, "updateData:shareScoreAdd success");
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
    private Dialog canShutDownEarlyDialog;
    private Dialog canNotShutDownEarlyDialog;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_demand;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        TLog.log(TAG + "onActivityResult1");
        TLog.log(TAG + "requestCode" + requestCode);
        TLog.log(TAG + "resultCode" + resultCode);
        umShareAPI.onActivityResult(requestCode, resultCode, data);
        switch (resultCode) {
            case DemandsDetailsReceiptFragment.RESULT_CODE_BACK:
                TLog.log(TAG + "onActivityResult3");
                demandDetails(demandId, false);
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
                if (images_qualified != null && images_qualified.size() != 0) {
                    Intent intent = new Intent(DemandActivity.this, QualifiedInvoiceActivity.class);
                    intent.putParcelableArrayListExtra("images_qualified", images_qualified);
                    startActivity(intent);
                } else {
                    Toast.makeText(DemandActivity.this, "您还没有确认过任何发票", Toast.LENGTH_SHORT).show();
                }
            }
            break;
            case R.id.demand_back: {
                setResult(RESULT_OK);
                finish();
            }
            break;
            case btn_shut_down_early: {
                if (isCanShutDown) {
                    showDialog(canShutDownEarlyDialog);
                } else {
                    showDialog(canNotShutDownEarlyDialog);
                }
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
            default:
        }
    }

    @Override
    public void initView() {
        umShareAPI = UMShareAPI.get(this);
        invoiceNameAdapter = new MyInvoiceNameAdapter(this);
        horizontalListView.setAdapter(invoiceNameAdapter);

        web = new UMWeb(Constant.MATCH);
        web.setTitle(getString(R.string.share_demand_title));//标题
        UMImage umImage = new UMImage(this, R.mipmap.icon);
        web.setThumb(umImage);  //缩略图
        web.setDescription(getString(R.string.share_demand_title));//描述

        api = WXAPIFactory.createWXAPI(this, Constants.APP_ID);
        api.registerApp(Constants.APP_ID);
        initSmartRefreshLayout();

        initDialog();
    }

    private void initDialog() {

        canShutDownEarlyDialog = DialogUtil.getInstance().createDialog(this, R.style.BottomDialog, R.layout.layout_shutdown1_tip, null, new DialogUtil.OnConfirmListener() {
            @Override
            public void onConfirm(View view) {
                canShutDownEarlyDialog.dismiss();
                if (demandId != null) {
                    shatDownEarly(demandId);
                }
            }
        }, new DialogUtil.OnCancelListener() {
            @Override
            public void onCancel(View view) {
                canShutDownEarlyDialog.dismiss();
            }
        });


        canNotShutDownEarlyDialog = DialogUtil.getInstance().createDialog(this, R.style.BottomDialog, R.layout.layout_shutdown2_tip, new DialogUtil.OnKnownListener() {
            @Override
            public void onKnown(View view) {
                canNotShutDownEarlyDialog.dismiss();
            }
        }, null, null);
    }

    private void initSmartRefreshLayout() {
        smartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                demandDetails(demandId, true);
            }
        });

        smartRefreshLayout.setDisableContentWhenRefresh(true);
    }

    private void setUpData(List<DemandDetails.DataBean.OrderInvoiceListBean> results, boolean isSetList) {
        Log.d(TAG, "setUpData:   private void setUpData(ArrayList<model.ResultsBean> body) {");
        isCanShutDown = true;
        //是否收到发票  未收到则显示默认图
        if (results.size() == 0) {
            isCanShutDown = true;
            ll_no_record.setVisibility(View.VISIBLE);
            ll_receiptlist.setVisibility(View.GONE);
        } else {
            ll_no_record.setVisibility(View.GONE);
            ll_receiptlist.setVisibility(View.VISIBLE);

            ArrayList<Image> images = new ArrayList<>();
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
//                image.amount = String.format("%.2f",result.getAmount());
                image.amount = getString(R.string.point_two, result.getAmount());
                image.logisticsTradeno = result.getLogisticsTradeno();
                image.logisticsCompany = result.getLogisticsCompany();
                image.variety = result.getVariety();
                image.reason = result.getReason();
                images.add(image);
                //是否有未确认的发票 当前是否可关闭
                if (STATE_CONFIRMING.equals(result.getState())
                        || STATE_MAILING.equals(result.getState())) {//收到票 有未完成发票
                    isCanShutDown = false;
                }
            }
            Log.d(TAG, "all receipt is checked  isCanShutDown  " + isCanShutDown);

            ArrayList<Image> images1 = new ArrayList<>();
            ArrayList<Image> images2 = new ArrayList<>();
            ArrayList<Image> images3 = new ArrayList<>();
            images_qualified = new ArrayList<>();
            //拆分发票集合
            for (int i = 0; i < images.size(); i++) {
                if (STATE_COMPETENT.equals(images.get(i).state)) {
                    images_qualified.add(images.get(i));//合格发票集合
                }
                if (!STATE_INCOMPETENT.equals(images.get(i).state)) {
                    if (VARIETY_GENERAL_PAPER.equals(images.get(i).variety)) {
                        images1.add(images.get(i));
                    } else if (VARIETY_SPECIAL_PAPER.equals(images.get(i).variety)) {
                        images2.add(images.get(i));
                    } else if (VARIETY_GENERAL_ELECTRON.equals(images.get(i).variety)) {
                        images3.add(images.get(i));
                    }
                }
            }

            TLog.d(TAG, "images1.size()" + images1.size());
            TLog.d(TAG, "images2.size()" + images2.size());
            TLog.d(TAG, "images3.size()" + images3.size());

            tv_num_1.setText(String.format(getResources().getString(R.string.paper_normal_receipt_num), images1.size()));
            tv_num_2.setText(String.format(getResources().getString(R.string.paper_special_receipt_num), images2.size()));
            tv_num_3.setText(String.format(getResources().getString(R.string.paper_elec_receipt_num), images3.size()));
            if (isSetList) {//是否需要刷新发票列表
                Bundle bundle = new Bundle();
                bundle.putParcelableArrayList(PAPER_NORMAL_RECEIPT_DATA, images1);
                DemandsDetailsReceiptFragment paperNormalReceiptFragment = DemandsDetailsReceiptFragment.newInstance(bundle);
                addCaptureFragment2(R.id.container_paper_normal_receipt, paperNormalReceiptFragment);
                bundle.putParcelableArrayList(PAPER_SPECIAL_RECEIPT_DATA, images2);
                DemandsDetailsReceiptFragment2 paperSpecialReceiptFragment = DemandsDetailsReceiptFragment2.newInstance(bundle);
                addCaptureFragment2(R.id.container_paper_special_receipt, paperSpecialReceiptFragment);
                bundle.putParcelableArrayList(PAPER_ELEC_RECEIPT_DATA, images3);
                DemandsDetailsReceiptFragment3 paperElecReceiptFragment = DemandsDetailsReceiptFragment3.newInstance(bundle);
                addCaptureFragment2(R.id.container_paper_elec_receipt, paperElecReceiptFragment);
            }
            //隐藏空发票的类型
            if (images1.size() == 0) {
                container_paper_normal_receipt.setVisibility(View.GONE);
            } else {
                container_paper_normal_receipt.setVisibility(View.VISIBLE);
            }
            if (images2.size() == 0) {
                container_paper_special_receipt.setVisibility(View.GONE);
            } else {
                container_paper_special_receipt.setVisibility(View.VISIBLE);
            }
            if (images3.size() == 0) {
                container_paper_elec_receipt.setVisibility(View.GONE);
            } else {
                container_paper_elec_receipt.setVisibility(View.VISIBLE);
            }

            if (images1.size() == 0 && images2.size() == 0 && images3.size() == 0) {
                ll_no_record.setVisibility(View.VISIBLE);
                ll_receiptlist.setVisibility(View.GONE);
            } else {
                ll_no_record.setVisibility(View.GONE);
                ll_receiptlist.setVisibility(View.VISIBLE);
            }

            Log.d(TAG, "initData: images_qualified" + images_qualified.size());
            if (images_qualified.size() == 0) {
                tvQualifiedList.setVisibility(View.GONE);
            } else {
                tvQualifiedList.setVisibility(View.VISIBLE);
            }
        }
    }

    @Override
    public void initData() {
        demandId = getIntent().getStringExtra("demandId");
        Log.d(TAG, "initData:demandDetails demandId" + demandId);
        if (demandId != null) {
            smartRefreshLayout.autoRefresh(10);
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @Override
    public void onNoNetworkLayoutClicks(View view) {
        initData();
    }

    @Override
    protected void onPause() {
        OkGo.cancelTag(OkGo.getInstance().getOkHttpClient(), "demandDetails");
        super.onPause();
    }

    public void demandDetails(String demandId, final boolean isSetList) {
        if (AccountHelper.getToken() != null && !Objects.equals(AccountHelper.getToken(), "")) {
            String token = AccountHelper.getToken();
            Api.demandDetails(token, demandId, new Api.BaseRawResponse<DemandDetails>() {
                @Override
                public void onTokenInvalid() {
                    hideNetWorkErrorLayout();
                }

                @Override
                public void onStart() {
//                    smartRefreshLayout.autoRefresh(10);
                }

                @Override
                public void onFinish() {
                    hideProgressDialog();
                    smartRefreshLayout.finishRefresh();
                }

                @Override
                public void onError() {
                    showNetWorkErrorLayout();
                }

                @Override
                public void setData(DemandDetails demandDetails) {
                    hideNetWorkErrorLayout();
                    if (demandDetails.getStatus() == REQUEST_SUCCESS) {
                        Log.d(TAG, "demandDetails success");
                        DemandDetails.DataBean bean = demandDetails.getData();
                        mList.clear();
                        mList.addAll(bean.getInvoiceNameList());
                        invoiceNameAdapter.initData(mList);

                        tvBounsAmount.setText(String.valueOf(getString(R.string.point_two, bean.getDemand().getTotalBonus())));
                        tvAmount.setText(String.valueOf(getString(R.string.point_two, bean.getDemand().getTotalAmount())));
                        tvLeftAmount.setText(String.valueOf(getString(R.string.point_two, bean.getDemand().getLeftBonus())));
                        tvPublishTime.setText(getString(R.string.publish_date, bean.getDemand().getPublishDate()));
                        tvDeadline.setText(getString(R.string.deadline_date, bean.getDemand().getDeadline()));
                        tvAttentions.setText(bean.getDemand().getAttentions().isEmpty() ? "无" : bean.getDemand().getAttentions());
                        tv_receive.setText(String.valueOf(getString(R.string.point_two, bean.getReceivedAmount())));
                        tvAlreadyCollected.setText(String.valueOf(bean.getReceivedNum()));
                        tv_low_limit.setText(getString(R.string.end_with_yuan, getString(R.string.point_two, bean.getDemand().getMailMinimum())));
                        //地址信息判断
                        String district = null;
                        if (bean.getDemand().getDemandPostage().getDistrict() != null) {
                            district = bean.getDemand().getDemandPostage().getDistrict() + " ";
                        }
                        String city = null;
                        if (bean.getDemand().getDemandPostage().getCity() != null) {
                            city = bean.getDemand().getDemandPostage().getCity() + " ";
                        }
                        tvAddress.setText(city + district + bean.getDemand().getDemandPostage().getAddress());

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
                                TLog.log("content-----------" + content);
                                Bitmap qrCode = CodeCreator.createQRCode(DemandActivity.this, content);
                                qr.setImageBitmap(qrCode);
                            } catch (Exception e) {
                                BaseApplication.showToast(getString(R.string.qrcode_create_fail));
                                e.printStackTrace();
                            }
                        }
                        //发票列表
                        mDataList.clear();
                        mDataList.addAll(bean.getOrderInvoiceList());
                        setUpData(mDataList, isSetList);


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
                        if (sourceStr != null) {
                            if (sourceStr.contains("1")) {
                                paperNormalReceipt.setVisibility(View.VISIBLE);
                            }
                            if (sourceStr.contains("2")) {
                                paperSpecialReceipt.setVisibility(View.VISIBLE);
                            }
                            if (sourceStr.contains("3")) {
                                paperElecReceipt.setVisibility(View.VISIBLE);
                            }
                        }

                    }
                }
            });
        }
    }

    private void shatDownEarly(final String id) {

        Api.shatDownEarly(AccountHelper.getToken(), id, new Api.BaseRawResponse<NormalBean>() {
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
                showNetWorkErrorLayout();
            }

            @Override
            public void onTokenInvalid() {
                hideNetWorkErrorLayout();
                login();
                finish();
            }

            @Override
            public void setData(NormalBean normalBean) {
                hideNetWorkErrorLayout();
                BaseApplication.showToast(getString(R.string.demand_closed));
                setResult(RESULT_OK);
                DemandActivity.this.finish();
                Log.d(TAG, "updateData:shatDownEarly success");
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
                if (api.isWXAppInstalled()) {
                    WXWebpageObject wxWebpageObject = new WXWebpageObject();
                    wxWebpageObject.webpageUrl = Constant.MATCH;

                    WXMediaMessage wxMediaMessage = new WXMediaMessage(wxWebpageObject);
                    wxMediaMessage.title = getString(R.string.share_pub_title);
                    wxMediaMessage.description = getString(R.string.share_pub_description);
                    wxMediaMessage.thumbData = ImageUtils.drawable2Bytes(getResources().getDrawable(R.mipmap.share_redbag), Bitmap.CompressFormat.JPEG);

                    SendMessageToWX.Req req = new SendMessageToWX.Req();
                    req.transaction = String.valueOf(System.currentTimeMillis());
                    req.message = wxMediaMessage;


                    req.scene = SendMessageToWX.Req.WXSceneSession;
                    api.sendReq(req);
                    mCameraDialog.dismiss();
                    //记录用户分享状态
                    set(SHARE_SOCORE, true);
                } else {
                    BaseApplication.showToast(getString(R.string.please_install_WX_app));
                }
            }
        });
        root.findViewById(R.id.weibo).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (umShareAPI.isInstall(DemandActivity.this, SHARE_MEDIA.SINA)) {
                    new ShareAction(DemandActivity.this)
                            .setPlatform(SHARE_MEDIA.SINA)//传入平台
                            .withMedia(web)
                            .setCallback(umShareListener)//回调监听器
                            .share();
                    mCameraDialog.dismiss();
                } else {
                    BaseApplication.showToast(getString(R.string.please_install_WEIBO_app));
                }
            }
        });
        root.findViewById(R.id.moments).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (umShareAPI.isInstall(DemandActivity.this, SHARE_MEDIA.WEIXIN_CIRCLE)) {
                    new ShareAction(DemandActivity.this)
                            .setPlatform(SHARE_MEDIA.WEIXIN_CIRCLE)//传入平台
                            .withMedia(web)
                            .setCallback(umShareListener)//回调监听器
                            .share();
                    mCameraDialog.dismiss();
                } else {
                    BaseApplication.showToast(getString(R.string.please_install_WX_app));
                }
            }
        });
        root.findViewById(R.id.qq).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (umShareAPI.isInstall(DemandActivity.this, SHARE_MEDIA.QQ)) {
                    RxPermissions rxPermissions = new RxPermissions(DemandActivity.this);
                    rxPermissions.request(Manifest.permission.READ_EXTERNAL_STORAGE).subscribe(new Observer<Boolean>() {
                        @Override
                        public void onSubscribe(Disposable d) {
                        }

                        @Override
                        public void onNext(Boolean aBoolean) {
                            if (aBoolean) {
                                TLog.d(" if (umShareAPI.isInstall(getActivity(), SHARE_MEDIA.QQ)) {", "");
                                new ShareAction(DemandActivity.this)
                                        .setPlatform(SHARE_MEDIA.QQ)//传入平台
                                        .withMedia(web)
                                        .setCallback(umShareListener)//回调监听器
                                        .share();
                                mCameraDialog.dismiss();
                            }
                        }

                        @Override
                        public void onError(Throwable e) {

                        }

                        @Override
                        public void onComplete() {

                        }
                    });
                } else {
                    BaseApplication.showToast(getString(R.string.please_install_QQ_app));
                }

            }
        });
        root.findViewById(R.id.qzone).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (umShareAPI.isInstall(DemandActivity.this, SHARE_MEDIA.QZONE)) {
                    new ShareAction(DemandActivity.this)
                            .setPlatform(SHARE_MEDIA.QZONE)//传入平台
                            .withMedia(web)
                            .setCallback(umShareListener)//回调监听器
                            .share();
                    mCameraDialog.dismiss();
                } else {
                    BaseApplication.showToast(getString(R.string.please_install_Qzone_app));
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
    public void initDataInResume() {

    }
}
