package com.pilipa.fapiaobao.ui;

import android.Manifest;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.Target;
import com.pilipa.fapiaobao.AppOperator;
import com.pilipa.fapiaobao.R;
import com.pilipa.fapiaobao.account.AccountHelper;
import com.pilipa.fapiaobao.adapter.PreviewPagerAdapter;
import com.pilipa.fapiaobao.base.BaseActivity;
import com.pilipa.fapiaobao.base.BaseApplication;
import com.pilipa.fapiaobao.net.Api;
import com.pilipa.fapiaobao.net.bean.LoginWithInfoBean;
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
    @Bind(R.id.ll_express_info)
    LinearLayout llExpressInfo;
//    @Bind(R.id.qualified)
//    TextView qualified;
//    @Bind(R.id.unqualified)
//    TextView unqualified;
//    @Bind(R.id.ll_select_qualified)
//    LinearLayout llSelectQualified;
    @Bind(R.id.save_to_media)
    TextView saveToMedia;
    @Bind(R.id.ll_save_to_media)
    LinearLayout llSaveToMedia;
    @Bind(R.id.layout_noInfo_item)
    LinearLayout layout_noInfo_item;
    @Bind(R.id.layout_qualified_item)
    LinearLayout layout_qualified_item;
    @Bind(R.id.layout_willchecked_item)
    LinearLayout layout_willchecked_item;
    @Bind(R.id.layout_reject_item)
    LinearLayout layout_reject_item;
    private ArrayList<Image> allList;
    private int currentPosition;
    protected int mPreviousPos = -1;
    private PreviewPagerAdapter previewPagerAdapter;
    private ArrayList<PreviewImageFragment> FragmentList;

    private Image currentImage;
    @Override
    protected int getLayoutId() {
        return R.layout.activity_demands_details_preview;
    }

    @Override
    public void onClick(View v) {

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
        setLayout(currentImage.state);
    }

    @Override
    public void initData() {

    }
    public void setLayout(String state){
        switch (state){
            case STATE_CONFIRMING:
                layout_noInfo_item.setVisibility(View.VISIBLE);
                layout_qualified_item.setVisibility(View.GONE);
                layout_willchecked_item.setVisibility(View.GONE);
                layout_reject_item.setVisibility(View.GONE);
                break;
            case STATE_COMPETENT://ok
                layout_noInfo_item.setVisibility(View.GONE);
                layout_qualified_item.setVisibility(View.VISIBLE);
                layout_willchecked_item.setVisibility(View.GONE);
                layout_reject_item.setVisibility(View.GONE);
                break;
            case STATE_INCOMPETENT:
                layout_noInfo_item.setVisibility(View.VISIBLE);
                layout_qualified_item.setVisibility(View.GONE);
                layout_willchecked_item.setVisibility(View.GONE);
                layout_reject_item.setVisibility(View.GONE);
                break;
            case STATE_MAILING:
                layout_noInfo_item.setVisibility(View.GONE);
                layout_qualified_item.setVisibility(View.GONE);
                layout_willchecked_item.setVisibility(View.VISIBLE);
                layout_reject_item.setVisibility(View.GONE);
                break;
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.delete,
            R.id.back,
            R.id.click,
            R.id.tolast,
            R.id.tonext,
            R.id.qualified,
            R.id.unqualified,
            R.id.tv_Unqualified1,
            R.id.tv_Unqualified2,
            R.id.tv_cancel_reject,
            R.id.tv_CheckEligibility1,
            R.id.save_to_media})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_CheckEligibility1:{
                AccountHelper.isTokenValid(new Api.BaseViewCallback<LoginWithInfoBean>() {
                    @Override
                    public void setData(LoginWithInfoBean loginWithInfoBean) {
                        if (loginWithInfoBean.getStatus() == 200) {
                                Api.confirmInvoice(AccountHelper.getToken(),currentImage.name, new Api.BaseViewCallback<ConfirmInvoiceBean>() {
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
            }break;
            case R.id.tv_Unqualified1:
            case R.id.tv_cancel_reject:{
                  changeLayout();
            }break;
            case R.id.tv_Unqualified2:{
                AccountHelper.isTokenValid(new Api.BaseViewCallback<LoginWithInfoBean>() {
                    @Override
                    public void setData(LoginWithInfoBean normalBean) {
                        if (normalBean.getStatus() == 200) {
                            Api.rejectInvoice(AccountHelper.getToken(),"","","", new Api.BaseViewCallback<NormalBean>() {
                                @Override
                                public void setData(NormalBean normalBean) {
                                    if (normalBean.getStatus() == 200) {
                                        BaseApplication.showToast("驳回成功");
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
            }break;
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
                    Log.d("mPreviousPos  last >>>>>>",mPreviousPos  +"");
                    int pos =   mPreviousPos ;
                    if(pos  <= allList.size() - 1){
                        currentImage = allList.get(pos);
                        expressInfo.setText(currentImage.logisticsCompany);
                        expressNo.setText(currentImage.logisticsTradeno);
                        setLayout(currentImage.state);
                    }
                }
                break;
            case R.id.tonext:
                if (mPreviousPos <= FragmentList.size()) {
                    previewViewpager.setCurrentItem(mPreviousPos +1);
                    int pos =   mPreviousPos +1;
                    if(pos - 1 >= 0){
                        Log.d("mPreviousPos  next >>>>>>",pos-1+"");
                        currentImage = allList.get(pos-1);
                        expressInfo.setText(currentImage.logisticsCompany);
                        expressNo.setText(currentImage.logisticsTradeno);
                        setLayout(currentImage.state);
                    }
                }
                break;
            case R.id.qualified:
                break;
            case R.id.unqualified:
                break;
            case R.id.save_to_media:
                requestPermission();
                break;
            default:
        }
    }

    private void changeLayout(){
        if(layout_noInfo_item.getVisibility() == View.VISIBLE){
            layout_noInfo_item.setVisibility(View.GONE);
            layout_reject_item.setVisibility(View.VISIBLE);
        }else{
            layout_noInfo_item.setVisibility(View.VISIBLE);
            layout_reject_item.setVisibility(View.GONE);
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
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }



}
