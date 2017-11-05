package com.pilipa.fapiaobao.ui;

import android.Manifest;
import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.Target;
import com.pilipa.fapiaobao.AppOperator;
import com.pilipa.fapiaobao.R;
import com.pilipa.fapiaobao.account.AccountHelper;
import com.pilipa.fapiaobao.adapter.MyRejectTypeAdapter;
import com.pilipa.fapiaobao.adapter.PreviewPagerAdapter;
import com.pilipa.fapiaobao.base.BaseActivity;
import com.pilipa.fapiaobao.base.BaseApplication;
import com.pilipa.fapiaobao.net.Api;
import com.pilipa.fapiaobao.net.bean.LoginWithInfoBean;
import com.pilipa.fapiaobao.net.bean.RejectTypeBean;
import com.pilipa.fapiaobao.net.bean.me.NormalBean;
import com.pilipa.fapiaobao.net.bean.publish.ConfirmInvoiceBean;
import com.pilipa.fapiaobao.ui.fragment.DemandsDetailsReceiptFragment;
import com.pilipa.fapiaobao.ui.fragment.PreviewImageFragment;
import com.pilipa.fapiaobao.ui.fragment.UploadNormalReceiptFragment;
import com.pilipa.fapiaobao.ui.fragment.UploadPreviewReceiptFragment;
import com.pilipa.fapiaobao.ui.model.Image;
import com.pilipa.fapiaobao.ui.widget.PreviewViewpager;
import com.pilipa.fapiaobao.utils.BitmapUtils;
import com.pilipa.fapiaobao.utils.StreamUtils;
import com.tbruyelle.rxpermissions2.RxPermissions;

import java.io.File;
import java.util.ArrayList;
import java.util.concurrent.Future;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

import static com.pilipa.fapiaobao.net.Constant.STATE_COMPETENT;
import static com.pilipa.fapiaobao.net.Constant.STATE_CONFIRMING;
import static com.pilipa.fapiaobao.net.Constant.STATE_INCOMPETENT;
import static com.pilipa.fapiaobao.net.Constant.STATE_MAILING;
import static com.pilipa.fapiaobao.net.Constant.VARIETY_GENERAL_ELECTRON;

/**
 * Created by edz on 2017/10/20.
 */

public class DemandsDetailsPreviewActivity extends BaseActivity implements ViewPager.OnPageChangeListener {
    private static final String TAG = "DemandsDetailsPreviewActivity";
    @Bind(R.id.preview_viewpager)
    PreviewViewpager previewViewpager;
    @Bind(R.id.delete)
    TextView delete;
    @Bind(R.id.back)
    ImageView back;
    @Bind(R.id.click)
    TextView click;
    @Bind(R.id.tolast)
    ImageView tolast;
    @Bind(R.id.tonext)
    ImageView tonext;
    @Bind(R.id.express_info)
    TextView expressInfo;
    @Bind(R.id.express_no)
    TextView expressNo;
    @Bind(R.id.mSpinner)
    Spinner mSpinner;
    @Bind(R.id.edt_reason_reject)
    EditText edt_reason_reject;
    @Bind(R.id.edt_amount_reject)
    EditText edt_amount_reject;

    TextView saveToMedia;
    @Bind(R.id.layout_qualified_item)
    LinearLayout layout_qualified_item;
    @Bind(R.id.layout_unqualified_item)
    LinearLayout layout_unqualified_item;
    @Bind(R.id.layout_willchecked_item)
    LinearLayout layout_willchecked_item;
    @Bind(R.id.layout_reject_item)
    LinearLayout layout_reject_item;

    @Bind(R.id.ll_express_info)
    LinearLayout llExpressInfo;
    @Bind(R.id.ll_no_info)
    LinearLayout llNoInfo;
    @Bind(R.id.tv_tip)//顶部tip
            TextView tv_tip;
    @Bind(R.id.tv_reject_reason)//不合格理由
            TextView tv_reject_reason;
    private MyRejectTypeAdapter mSpinnerAdapter;


    private ArrayList<Image> allList;
    private int currentPosition;
    protected int mPreviousPos = -1;
    private PreviewPagerAdapter previewPagerAdapter;
    private ArrayList<PreviewImageFragment> FragmentList;
    private Image currentImage;

    private int REJECT_START = 0;
    private int REJECT_FINISH = 1;
    @Override
    protected int getLayoutId() {
        return R.layout.activity_demands_details_preview;
    }


    @Override
    public void initView() {
        Bundle bundleExtra = getIntent().getBundleExtra(UploadNormalReceiptFragment.EXTRA_BUNDLE);
        boolean aBoolean = bundleExtra.getBoolean(UploadPreviewReceiptFragment.IS_SHOW_SELECT_AND_DELETE, true);
        if (aBoolean) {
            delete.setVisibility(View.VISIBLE);
            click.setVisibility(View.VISIBLE);
        } else {
            delete.setVisibility(View.INVISIBLE);
            click.setVisibility(View.INVISIBLE);
        }
        allList = bundleExtra.getParcelableArrayList(UploadNormalReceiptFragment.EXTRA_ALL_DATA);
        FragmentList = new ArrayList<>();
        for (Image image : allList) {
            if (!image.isCapture) {
                FragmentList.add(PreviewImageFragment.newInstance(image));
            }
        }
        currentPosition = bundleExtra.getInt(UploadNormalReceiptFragment.EXTRA_CURRENT_POSITION);
        Log.d(TAG, "initView: currentPosition" + currentPosition);
        boolean isFromDemands = bundleExtra.getBoolean(DemandsDetailsReceiptFragment.IS_FROM_DEMANDS, false);
        mPreviousPos = isFromDemands ? currentPosition : currentPosition - 1;
        previewPagerAdapter = new PreviewPagerAdapter(getSupportFragmentManager(), FragmentList);
        previewViewpager.setAdapter(previewPagerAdapter);
        previewViewpager.setCurrentItem(mPreviousPos);
        previewViewpager.setOnPageChangeListener(this);
        currentImage = allList.get(mPreviousPos);
        expressInfo.setText(currentImage.logisticsCompany);
        expressNo.setText(currentImage.logisticsTradeno);
        setLayout(currentImage);
    }

    @Override
    public void initData() {
        mSpinnerAdapter = new MyRejectTypeAdapter(DemandsDetailsPreviewActivity.this);
        mSpinner.setAdapter(mSpinnerAdapter);
        findAllRejectType();
    }

    public void setLayout(Image image) {
        tv_tip.setText("");
        llExpressInfo.setVisibility(View.GONE);
        llNoInfo.setVisibility(View.GONE);
        switch (image.state) {
            case STATE_CONFIRMING://
                layout_qualified_item.setVisibility(View.GONE);
                layout_unqualified_item.setVisibility(View.GONE);
                layout_willchecked_item.setVisibility(View.VISIBLE);
                layout_reject_item.setVisibility(View.GONE);
                if (!VARIETY_GENERAL_ELECTRON.equals(image.variety)) {//纸质票
                    tv_tip.setText(getResources().getString(R.string.tip_wait_mail));
                } else {
                    tv_tip.setText(getResources().getString(R.string.tip_wait_dowload));
                }
                break;
            case STATE_COMPETENT://ok
                layout_qualified_item.setVisibility(View.VISIBLE);
                layout_willchecked_item.setVisibility(View.GONE);
                layout_unqualified_item.setVisibility(View.GONE);
                layout_reject_item.setVisibility(View.GONE);
                break;
            case STATE_INCOMPETENT:
                layout_qualified_item.setVisibility(View.GONE);
                layout_willchecked_item.setVisibility(View.GONE);
                layout_reject_item.setVisibility(View.GONE);
                layout_unqualified_item.setVisibility(View.VISIBLE);
                break;
            case STATE_MAILING:
                layout_qualified_item.setVisibility(View.GONE);
                layout_unqualified_item.setVisibility(View.GONE);
                layout_willchecked_item.setVisibility(View.VISIBLE);
                layout_reject_item.setVisibility(View.GONE);
                if (image.logisticsTradeno != null) {//有无邮寄信息
                    llExpressInfo.setVisibility(View.VISIBLE);
                    llNoInfo.setVisibility(View.GONE);
                } else {
                    llExpressInfo.setVisibility(View.GONE);
                    llNoInfo.setVisibility(View.VISIBLE);
                }
                break;
        }
        if ("provided".equals(image.from)) {
            //提供详情点击预览界面
            layout_qualified_item.setVisibility(View.GONE);
            layout_unqualified_item.setVisibility(View.GONE);
            layout_willchecked_item.setVisibility(View.GONE);
            layout_reject_item.setVisibility(View.GONE);
        }
    }

    @OnClick({R.id.delete,
            R.id.back,
            R.id.click,
            R.id.tolast,
            R.id.tonext,
            R.id.confirm_back,
            R.id.tv_cancel_reject,
            R.id.tv_Unqualified,
            R.id.tv_Unqualified_reject,
            R.id.tv_qualified,
            R.id.save_to_media})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.confirm_back: {
                DemandsDetailsPreviewActivity.this.finish();
            }
            break;
            case R.id.tv_Unqualified:
            case R.id.tv_cancel_reject: {
                changeLayout();
            }
            break;
            case R.id.tv_qualified: {
                AccountHelper.isTokenValid(new Api.BaseViewCallback<LoginWithInfoBean>() {
                    @Override
                    public void setData(LoginWithInfoBean loginWithInfoBean) {
                        if (loginWithInfoBean.getStatus() == 200) {
                            Api.confirmInvoice(AccountHelper.getToken(), currentImage.name, new Api.BaseViewCallback<ConfirmInvoiceBean>() {
                                @Override
                                public void setData(ConfirmInvoiceBean confirmInvoiceBean) {
                                    if (confirmInvoiceBean.getStatus() == 200) {
                                        BaseApplication.showToast("确认成功");
                                        DemandsDetailsPreviewActivity.this.finish();
                                    }
                                }
                            });
                        } else {
                            BaseApplication.showToast("token验证失败请重新登录");
                            startActivity(new Intent(DemandsDetailsPreviewActivity.this, LoginActivity.class));
                            finish();
                        }
                    }
                });
            }
            break;
            case R.id.tv_Unqualified_reject: {
                setRejectDialog(REJECT_START);
            }
            break;
            case R.id.delete:
                Log.d(TAG, "before allList.remove(mPreviousPos+1);allList.size()" + allList.size());
                allList.remove(mPreviousPos + 1);
                previewPagerAdapter.remove(mPreviousPos);
                Log.d(TAG, "after---onViewClicked: allList.size()=========" + allList.size());
                Log.d(TAG, "onViewClicked: previewPagerAdapter.arrayList.size()=========" + previewPagerAdapter.arrayList.size());
                if (allList.size() == 0 || previewPagerAdapter.arrayList.size() == 0) {
                    Intent intent2 = new Intent();
                    Bundle bundle2 = new Bundle();
                    bundle2.putParcelableArrayList(UploadNormalReceiptFragment.EXTRA_ALL_DATA, allList);
                    intent2.putExtra(UploadNormalReceiptFragment.EXTRA_BUNDLE, bundle2);
                    setResult(UploadNormalReceiptFragment.RESULT_CODE_BACK, intent2);
                    finish();
                }
                break;
            case R.id.back:
                Intent intent = new Intent();
                Bundle bundle = new Bundle();
                bundle.putParcelableArrayList(UploadNormalReceiptFragment.EXTRA_ALL_DATA, allList);
                intent.putExtra(UploadNormalReceiptFragment.EXTRA_BUNDLE, bundle);
                setResult(UploadNormalReceiptFragment.RESULT_CODE_BACK, intent);
                finish();
                break;
            case R.id.click:
                break;
            case R.id.tolast:
                if (mPreviousPos >= 1) {
                    previewViewpager.setCurrentItem(mPreviousPos - 1);
                    Log.d("mPreviousPos  last >>>>>>", mPreviousPos + "");
                    int pos = mPreviousPos;
                    if (pos <= allList.size() - 1) {
                        currentImage = allList.get(pos);
                        expressInfo.setText(currentImage.logisticsCompany);
                        expressNo.setText(currentImage.logisticsTradeno);
                        setLayout(currentImage);
                    }
                }
                break;
            case R.id.tonext:
                if (mPreviousPos <= FragmentList.size()) {
                    previewViewpager.setCurrentItem(mPreviousPos + 1);
                    int pos = mPreviousPos + 1;
                    if (pos - 1 >= 0) {
                        Log.d("mPreviousPos  next >>>>>>", pos - 1 + "");
                        currentImage = allList.get(pos - 1);
                        expressInfo.setText(currentImage.logisticsCompany);
                        expressNo.setText(currentImage.logisticsTradeno);
                        setLayout(currentImage);
                    }
                }
                break;
            case R.id.save_to_media:
                requestPermission();
                break;
            default:
        }
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_finish:{
                DemandsDetailsPreviewActivity.this.finish();
            }
            case R.id.btn_cancel1:
            case R.id.btn_cancel2:{
                mDialog.cancel();
            }break;
            case R.id.btn_confirm:
                AccountHelper.isTokenValid(new Api.BaseViewCallback<LoginWithInfoBean>() {
                    @Override
                    public void setData(LoginWithInfoBean normalBean) {
                        if (normalBean.getStatus() == 200) {
                            edt_reason_reject.getText().toString().trim();
                            RejectTypeBean.DataBean data = (RejectTypeBean.DataBean) mSpinner.getSelectedItem();
                            Api.rejectInvoice(AccountHelper.getToken()
                                    , currentImage.name
                                    , edt_amount_reject.getText().toString().trim()
                                    , data.getValue()
                                    , edt_reason_reject.getText().toString().trim()
                                    , new Api.BaseViewCallback<NormalBean>() {
                                        @Override
                                        public void setData(NormalBean normalBean) {
                                            if (normalBean.getStatus() == 200) {
                                                setRejectDialog(REJECT_FINISH);
                                            }
                                        }
                                    });
                        } else {
                            BaseApplication.showToast("token验证失败请重新登录");
                            startActivity(new Intent(DemandsDetailsPreviewActivity.this, LoginActivity.class));
                            finish();
                        }
                    }
                });
                break;
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    private void changeLayout() {
        if (layout_reject_item.getVisibility() == View.VISIBLE) {
            layout_reject_item.setVisibility(View.GONE);
            layout_willchecked_item.setVisibility(View.VISIBLE);
        } else {
            layout_reject_item.setVisibility(View.VISIBLE);
            layout_willchecked_item.setVisibility(View.GONE);
        }
    }

    private void requestPermission() {
        final RxPermissions rxPermissions = new RxPermissions(this);
        rxPermissions.request(Manifest.permission.WRITE_EXTERNAL_STORAGE).subscribe(new Observer<Boolean>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Boolean aBoolean) {
                if (aBoolean) {
                    saveToFile();
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

    private void saveToFile() {
        if (!Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            Toast.makeText(this, R.string.gallery_save_file_not_have_external_storage, Toast.LENGTH_SHORT).show();
            return;
        }

        final Future<File> future = Glide.with(this)
                .load(allList.get(mPreviousPos).path)
                .downloadOnly(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL);

        AppOperator.runOnThread(new Runnable() {
            @Override
            public void run() {
                try {
                    File sourceFile = future.get();
                    if (sourceFile == null || !sourceFile.exists())
                        return;
                    String extension = BitmapUtils.getExtension(sourceFile.getAbsolutePath());
                    String extDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES)
                            .getAbsolutePath() + File.separator + "发票宝";
                    File extDirFile = new File(extDir);
                    if (!extDirFile.exists()) {
                        if (!extDirFile.mkdirs()) {
                            // If mk dir error
                            callSaveStatus(false, null);
                            return;
                        }
                    }
                    final File saveFile = new File(extDirFile, String.format("IMG_%s.%s", System.currentTimeMillis(), extension));
                    final boolean isSuccess = StreamUtils.copyFile(sourceFile, saveFile);
                    callSaveStatus(isSuccess, saveFile);
                } catch (Exception e) {
                    e.printStackTrace();
                    callSaveStatus(false, null);
                }
            }
        });
    }

    private void callSaveStatus(final boolean success, final File savePath) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (success) {
                    // notify
                    if (isDestroyed())
                        return;
                    Uri uri = Uri.fromFile(savePath);
                    sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, uri));
                    Toast.makeText(DemandsDetailsPreviewActivity.this, R.string.gallery_save_file_success, Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(DemandsDetailsPreviewActivity.this, R.string.gallery_save_file_failed, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void findAllRejectType() {
        Api.findAllRejectType(new Api.BaseViewCallback<RejectTypeBean>() {
            @Override
            public void setData(RejectTypeBean rejectTypeBean) {
                if (rejectTypeBean.getStatus() == 200) {
                    mSpinnerAdapter.initData(rejectTypeBean.getData());
                }
            }
        });
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        PreviewPagerAdapter adapter = (PreviewPagerAdapter) previewViewpager.getAdapter();
        if (mPreviousPos != -1 && mPreviousPos != position) {
            ((PreviewImageFragment) adapter.instantiateItem(previewViewpager, mPreviousPos)).resetView();
        }
        mPreviousPos = position;
        Log.d("onPageScrolled  pos >>>>>>", position + "");
        if (position >= 0) {
            currentImage = allList.get(position);
            expressInfo.setText(currentImage.logisticsCompany);
            expressNo.setText(currentImage.logisticsTradeno);
            setLayout(currentImage);
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
    private  Dialog mDialog;
    private void setRejectDialog(int step) {
        mDialog = new Dialog(this, R.style.BottomDialog);
        LinearLayout  root = null;
        if(step == REJECT_START){
            root = (LinearLayout) LayoutInflater.from(this).inflate(
                    R.layout.layout_reject1_tip, null);
            root.findViewById(R.id.btn_confirm).setOnClickListener(this);
            root.findViewById(R.id.btn_cancel1).setOnClickListener(this);
        }else if(step == REJECT_FINISH){
            root = (LinearLayout) LayoutInflater.from(this).inflate(
                    R.layout.layout_reject2_tip, null);
            root.findViewById(R.id.btn_finish).setOnClickListener(this);
            root.findViewById(R.id.btn_cancel2).setOnClickListener(this);
        }
        //初始化视图
        mDialog.setContentView(root);
        Window dialogWindow = mDialog.getWindow();
        dialogWindow.setGravity(Gravity.CENTER);
//        dialogWindow.setWindowAnimations(R.style.dialogstyle); // 添加动画
        WindowManager.LayoutParams lp = dialogWindow.getAttributes(); // 获取对话框当前的参数值
        lp.x = 0; // 新位置X坐标
        lp.y = 0; // 新位置Y坐标
        lp.width = (int) getResources().getDisplayMetrics().widthPixels; // 宽度
        root.measure(0, 0);
        lp.height = root.getMeasuredHeight();

        lp.alpha = 9f; // 透明度
        dialogWindow.setAttributes(lp);
        mDialog.show();
    }

}
