package com.pilipa.fapiaobao.ui;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.pilipa.fapiaobao.R;
import com.pilipa.fapiaobao.adapter.UploadReceiptAdapter;
import com.pilipa.fapiaobao.base.BaseActivity;
import com.pilipa.fapiaobao.compat.MediaStoreCompat;
import com.pilipa.fapiaobao.ui.deco.GridInset;
import com.pilipa.fapiaobao.ui.model.Image;
import com.pilipa.fapiaobao.utils.ReceiptDiff;
import com.tbruyelle.rxpermissions2.RxPermissions;
import com.zhihu.matisse.Matisse;
import com.zhihu.matisse.MimeType;
import com.zhihu.matisse.engine.impl.GlideEngine;
import com.zhihu.matisse.internal.entity.CaptureStrategy;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by edz on 2017/10/20.
 */

public class UploadReceiptActivity extends BaseActivity implements
        UploadReceiptAdapter.OnPhotoCapture
        , UploadReceiptAdapter.OnImageClickListener, UploadReceiptAdapter.OnImageSelectListener {
    private static final String TAG = UploadReceiptActivity.class.getSimpleName();

    public static final int REQUEST_CODE_CAPTURE = 0x0003;
    public static final int REQUEST_CODE_IMAGE_CLICK = 0x0004;
    public static final int RESULT_CODE_BACK = 0x0005;
    public static final int REQUEST_CODE_CHOOSE = 0x0006;
    public static final String EXTRA_ALL_DATA = "EXTRA_ALL_DATA";
    public static final String EXTRA_CURRENT_POSITION = "EXTRA_CURRENT_POSITION";
    public static final String EXTRA_BUNDLE = "EXTRA_BUNDLE";

    @Bind(R.id.rv_upload_receipt)
    RecyclerView rvUploadReceipt;
    @Bind(R.id.media)
    TextView media;
    @Bind(R.id.capture)
    TextView capture;
    private int mImageResize;
    private ArrayList<Image> images;
    private MediaStoreCompat mediaStoreCompat;
    private int mPreviousPosition = -1;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_upload_receipt;
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void initView() {
        mediaStoreCompat = new MediaStoreCompat(this);
        GridLayoutManager grid = new GridLayoutManager(this, 3){
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };
        rvUploadReceipt.setLayoutManager(grid);
        int spacing = getResources().getDimensionPixelOffset(R.dimen.spacing);
        rvUploadReceipt.addItemDecoration(new GridInset(3, spacing, false));
        Image image = new Image();
        image.isFromNet = false;
        image.isCapture = true;
        images = new ArrayList<>();
        images.add(image);
        mPreviousPosition = images.size();
        UploadReceiptAdapter uploadReceiptAdapter = new UploadReceiptAdapter(images, getImageResize(this));
        uploadReceiptAdapter.setOnImageClickListener(this);
        uploadReceiptAdapter.setOnImageSelectListener(this);
        uploadReceiptAdapter.setOnPhotoCapture(this);
        rvUploadReceipt.setAdapter(uploadReceiptAdapter);

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

    @OnClick({R.id.media, R.id.capture})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.media:
                openMedia();
                break;
            case R.id.capture:
                if (MediaStoreCompat.hasCameraFeature(this)) {
                    mediaStoreCompat.dispatchCaptureIntent(this, REQUEST_CODE_CAPTURE);
                }
                break;
            default:
        }
    }

    private void openMedia() {
        RxPermissions rxPermissions = new RxPermissions(this);
        rxPermissions.request(Manifest.permission.WRITE_EXTERNAL_STORAGE).subscribe(new Observer<Boolean>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Boolean aBoolean) {
                if (aBoolean) {
                    Matisse.from(UploadReceiptActivity.this)
                            .choose(MimeType.of(MimeType.JPEG, MimeType.PNG))
                            .countable(true)
                            .captureStrategy(
                                    new CaptureStrategy(true, "com.pilipa.fapiaobao.fileprovider"))
                            .maxSelectable(9)
                            .gridExpectedSize(
                                    getResources().getDimensionPixelSize(R.dimen.grid_expected_size))
                            .restrictOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)
                            .thumbnailScale(0.4f)
                            .imageEngine(new GlideEngine())
                            .forResult(REQUEST_CODE_CHOOSE);
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
//        if (resultCode != RESULT_OK) {
//            return;
//        }

        if (requestCode == REQUEST_CODE_CAPTURE) {
            Uri contentUri = mediaStoreCompat.getCurrentPhotoUri();
            String path = mediaStoreCompat.getCurrentPhotoPath();
            try {
                MediaStore.Images.Media.insertImage(getContentResolver(), path, new File(path).getName(), null);
                sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE,contentUri));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            Image image = new Image();
            image.isFromNet = false;
            image.name = new File(path).getName();
            image.isCapture = false;
            Log.d(TAG, "onActivityResult: image.name " + image.name);
            image.position = mPreviousPosition;
            Log.d(TAG, "onActivityResult: image.position" + image.position);
            image.uri = contentUri;
            Log.d(TAG, "onActivityResult:  image.uri" + image.uri);
            images.add(image);
            UploadReceiptAdapter uploadReceiptAdapter = (UploadReceiptAdapter) rvUploadReceipt.getAdapter();
            uploadReceiptAdapter.notifyItemInserted(mPreviousPosition);
            mPreviousPosition = images.size();

            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
                UploadReceiptActivity.this.revokeUriPermission(contentUri,
                        Intent.FLAG_GRANT_WRITE_URI_PERMISSION | Intent.FLAG_GRANT_READ_URI_PERMISSION);
            }
        } else if (REQUEST_CODE_IMAGE_CLICK == requestCode) {
            Log.d(TAG, "onActivityResult:else if (REQUEST_CODE_IMAGE_CLICK == requestCode) { ");
            switch (resultCode) {
                case RESULT_CODE_BACK:
                    Log.d(TAG, "onActivityResult: previewactivity back to myreceiptactivity");
                    Bundle bundleExtra = data.getBundleExtra(EXTRA_BUNDLE);
                    ArrayList<Image> images = bundleExtra.getParcelableArrayList(EXTRA_ALL_DATA);
                    UploadReceiptAdapter uploadReceiptAdapter = (UploadReceiptAdapter) rvUploadReceipt.getAdapter();
                    uploadReceiptAdapter.refresh(images);
                    DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(new ReceiptDiff(this.images, images), true);
                    diffResult.dispatchUpdatesTo(uploadReceiptAdapter);
                    this.images = images;
                    mPreviousPosition = images.size();
                    break;
                default:
            }
        } else if (requestCode == REQUEST_CODE_CHOOSE && resultCode == RESULT_OK) {
            List<Uri> uris = Matisse.obtainResult(data);
            for (Uri uri : uris) {
                Image image = new Image();
                image.isCapture = false;
                image.position = mPreviousPosition;
                mPreviousPosition++;
                image.uri = uri;
                image.isFromNet = false;
                images.add(image);
                UploadReceiptAdapter uploadReceiptAdapter = (UploadReceiptAdapter) rvUploadReceipt.getAdapter();
                uploadReceiptAdapter.notifyItemInserted(mPreviousPosition);
            }

        }
    }

    private int getImageResize(Context context) {

        if (mImageResize == 0) {
            RecyclerView.LayoutManager lm = rvUploadReceipt.getLayoutManager();
            int spanCount = ((GridLayoutManager) lm).getSpanCount();
            int screenWidth = context.getResources().getDisplayMetrics().widthPixels;
            int availableWidth = screenWidth - context.getResources().getDimensionPixelSize(
                    R.dimen.spacing) * (spanCount - 1);
            mImageResize = availableWidth / spanCount;
            mImageResize = (int) (mImageResize * 0.5);
        }
        Log.d(TAG, "getImageResize: " + mImageResize);
        return mImageResize;
    }

    @Override
    public void capture() {
        Log.d(TAG, "capture: ");
    }

    @Override
    public void onImageClick(ArrayList<Image> allItemList, int position) {
        Image image = allItemList.get(position);
        Intent intent = new Intent(this, PreviewActivity.class);
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList(EXTRA_ALL_DATA, allItemList);
        bundle.putInt(EXTRA_CURRENT_POSITION, image.position);
        intent.putExtra(EXTRA_BUNDLE, bundle);
        Log.d(TAG, "onImageClick: image.position" + image.position);
        startActivityForResult(intent, REQUEST_CODE_IMAGE_CLICK);
    }

    @Override
    public void onImageSelect(Image image) {

    }
}
