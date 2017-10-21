package com.pilipa.fapiaobao.ui.fragment;

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
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.pilipa.fapiaobao.R;
import com.pilipa.fapiaobao.adapter.UploadReceiptAdapter;
import com.pilipa.fapiaobao.base.BaseFragment;
import com.pilipa.fapiaobao.compat.MediaStoreCompat;
import com.pilipa.fapiaobao.ui.PreviewActivity;
import com.pilipa.fapiaobao.ui.UploadReceiptActivity;
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

import static android.app.Activity.RESULT_OK;
import static com.pilipa.fapiaobao.ui.UploadReceiptActivity.EXTRA_ALL_DATA;
import static com.pilipa.fapiaobao.ui.UploadReceiptActivity.EXTRA_BUNDLE;
import static com.pilipa.fapiaobao.ui.UploadReceiptActivity.EXTRA_CURRENT_POSITION;
import static com.pilipa.fapiaobao.ui.UploadReceiptActivity.REQUEST_CODE_CAPTURE;
import static com.pilipa.fapiaobao.ui.UploadReceiptActivity.REQUEST_CODE_CHOOSE;
import static com.pilipa.fapiaobao.ui.UploadReceiptActivity.REQUEST_CODE_IMAGE_CLICK;
import static com.pilipa.fapiaobao.ui.UploadReceiptActivity.RESULT_CODE_BACK;

/**
 * Created by edz on 2017/10/21.
 */

public class UploadNormalReceiptFragment extends BaseFragment implements UploadReceiptAdapter.OnImageClickListener, UploadReceiptAdapter.OnImageSelectListener, UploadReceiptAdapter.OnPhotoCapture {
    private static final String TAG = "UploadNormalReceiptFragment";
    @Bind(R.id.media)
    TextView media;
    @Bind(R.id.capture)
    TextView capture;
    @Bind(R.id.rv_upload_receipt)
    RecyclerView rvUploadReceipt;
    private int mImageResize;
    private ArrayList<Image> images;
    private MediaStoreCompat mediaStoreCompat;
    private int mPreviousPosition = -1;
    @Override
    protected int getLayoutId() {
        return R.layout.activity_upload_receipt;
    }

    public static UploadNormalReceiptFragment newInstance(Bundle b) {
        UploadNormalReceiptFragment u = new UploadNormalReceiptFragment();
        u.setArguments(b);
        return u;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @OnClick({R.id.media, R.id.capture})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.media:
                openMedia();
                break;
            case R.id.capture:
                if (MediaStoreCompat.hasCameraFeature(getActivity())) {
                    mediaStoreCompat.dispatchCaptureIntent(getActivity(), REQUEST_CODE_CAPTURE);
                }
                break;
            default:
                break;
        }
    }

    private void openMedia() {
        RxPermissions rxPermissions = new RxPermissions(getActivity());
        rxPermissions.request(Manifest.permission.WRITE_EXTERNAL_STORAGE).subscribe(new Observer<Boolean>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Boolean aBoolean) {
                if (aBoolean) {
                    Matisse.from(UploadNormalReceiptFragment.this)
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
    protected void initWidget(View root) {
        super.initWidget(root);
        mediaStoreCompat = new MediaStoreCompat(getActivity(),this);
        GridLayoutManager grid = new GridLayoutManager(getActivity(), 3){
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
        UploadReceiptAdapter uploadReceiptAdapter = new UploadReceiptAdapter(images, getImageResize(getActivity()));
        uploadReceiptAdapter.setOnImageClickListener(this);
        uploadReceiptAdapter.setOnImageSelectListener(this);
        uploadReceiptAdapter.setOnPhotoCapture(this);
        rvUploadReceipt.setAdapter(uploadReceiptAdapter);
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
        return mImageResize;
    }

    @Override
    public void onImageClick(ArrayList<Image> allItemList, int position) {
        Image image = allItemList.get(position);
        Intent intent = new Intent(getActivity(), PreviewActivity.class);
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

    @Override
    public void capture() {

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d(TAG, "onActivityResult: ");
//        if (resultCode != RESULT_OK) {
//            return;
//        }

        if (requestCode == REQUEST_CODE_CAPTURE&&resultCode == RESULT_OK) {
            Uri contentUri = mediaStoreCompat.getCurrentPhotoUri();
            String path = mediaStoreCompat.getCurrentPhotoPath();
            try {
                MediaStore.Images.Media.insertImage(getActivity().getContentResolver(), path, new File(path).getName(), null);
                getActivity().sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE,contentUri));
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
                getActivity().revokeUriPermission(contentUri,
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
}
