package com.pilipa.fapiaobao.ui;

import android.Manifest;
import android.content.Intent;
import android.content.pm.ActivityInfo;
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
import com.pilipa.fapiaobao.adapter.PreviewPagerAdapter;
import com.pilipa.fapiaobao.base.BaseActivity;
import com.pilipa.fapiaobao.ui.fragment.DemandsDetailsReceiptFragment;
import com.pilipa.fapiaobao.ui.fragment.PreviewImageFragment;
import com.pilipa.fapiaobao.ui.fragment.UploadNormalReceiptFragment;
import com.pilipa.fapiaobao.ui.fragment.UploadPreviewReceiptFragment;
import com.pilipa.fapiaobao.ui.model.Image;
import com.pilipa.fapiaobao.ui.widget.PreviewViewpager;
import com.pilipa.fapiaobao.utils.BitmapUtils;
import com.pilipa.fapiaobao.utils.StreamUtils;
import com.tbruyelle.rxpermissions2.RxPermissions;
import com.zhihu.matisse.Matisse;
import com.zhihu.matisse.MimeType;
import com.zhihu.matisse.engine.impl.GlideEngine;
import com.zhihu.matisse.internal.entity.CaptureStrategy;

import java.io.File;
import java.util.ArrayList;
import java.util.concurrent.Future;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

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
    @Bind(R.id.qualified)
    TextView qualified;
    @Bind(R.id.unqualified)
    TextView unqualified;
    @Bind(R.id.ll_select_qualified)
    LinearLayout llSelectQualified;
    @Bind(R.id.save_to_media)
    TextView saveToMedia;
    @Bind(R.id.ll_save_to_media)
    LinearLayout llSaveToMedia;
    private ArrayList<Image> allList;
    private int currentPosition;
    protected int mPreviousPos = -1;
    private PreviewPagerAdapter previewPagerAdapter;
    private ArrayList<PreviewImageFragment> FragmentList;

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
    }

    @Override
    public void initData() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.delete, R.id.back, R.id.click, R.id.tolast, R.id.tonext, R.id.qualified, R.id.unqualified, R.id.save_to_media})
    public void onViewClicked(View view) {
        switch (view.getId()) {
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
                }

                break;
            case R.id.tonext:
                if (mPreviousPos <= FragmentList.size()) {
                    previewViewpager.setCurrentItem(mPreviousPos +1);
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
