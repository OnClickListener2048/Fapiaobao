package com.pilipa.fapiaobao.ui.fragment;

import android.Manifest;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;

import com.example.mylibrary.utils.TLog;
import com.pilipa.fapiaobao.R;
import com.pilipa.fapiaobao.adapter.UploadReceiptAdapter;
import com.pilipa.fapiaobao.base.BaseApplication;
import com.pilipa.fapiaobao.base.BaseFragment;
import com.pilipa.fapiaobao.compat.MediaStoreCompat;
import com.pilipa.fapiaobao.ui.FillUpActivity;
import com.pilipa.fapiaobao.ui.PreviewActivity;
import com.pilipa.fapiaobao.ui.UploadReceiptActivity;
import com.pilipa.fapiaobao.ui.deco.GridInset;
import com.pilipa.fapiaobao.ui.model.Image;
import com.pilipa.fapiaobao.ui.receipt_folder_image_select.ReceiptActivityToken;
import com.pilipa.fapiaobao.utils.BitmapUtils;
import com.pilipa.fapiaobao.utils.ReceiptDiff;
import com.tbruyelle.rxpermissions2.RxPermissions;
import com.zhihu.matisse.Matisse;
import com.zhihu.matisse.MimeType;
import com.zhihu.matisse.engine.impl.GlideEngine;
import com.zhihu.matisse.internal.entity.CaptureStrategy;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

import static android.app.Activity.RESULT_OK;


/**
 * Created by edz on 2017/10/21.
 */

public class UploadNormalReceiptFragment extends BaseFragment implements UploadReceiptAdapter.OnImageClickListener, UploadReceiptAdapter.OnImageSelectListener, UploadReceiptAdapter.OnPhotoCapture, View.OnClickListener {
    private static final String TAG = "UploadNormalReceiptFragment";
    public static final int REQUEST_CODE_CAPTURE = 10;
    public static final int REQUEST_CODE_CHOOSE = 20;
    public static final String EXTRA_ALL_DATA = "EXTRA_ALL_DATA";
    public static final String EXTRA_CURRENT_POSITION = "EXTRA_CURRENT_POSITION";
    public static final String EXTRA_BUNDLE = "EXTRA_BUNDLE";
    public static final int REQUEST_CODE_IMAGE_CLICK = 30;
    public static final int RESULT_CODE_BACK = 40;
    public static final int REQUEST_CODE_FROM_ELEC = 50;
    public static final int REQUEST_CODE_AMOUNT = 60;


    @Bind(R.id.rv_upload_receipt)
    RecyclerView rvUploadReceipt;
    private int mImageResize;
    private ArrayList<Image> images;
    private MediaStoreCompat mediaStoreCompat;
    private int mPreviousPosition = -1;
    private Dialog mCameraDialog;
    private boolean is_elec_data;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_upload_receipt;
    }

    public static UploadNormalReceiptFragment newInstance(Bundle b) {
        UploadNormalReceiptFragment u = new UploadNormalReceiptFragment();
        u.setArguments(b);
        return u;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        Bundle arguments = getArguments();
        is_elec_data = arguments.getBoolean(UploadReceiptActivity.IS_ELEC_RECEIPT_DATA, false);
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
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
        rvUploadReceipt.addItemDecoration(new GridInset(3, spacing, true));
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
        Intent intent = new Intent(mContext, PreviewActivity.class);
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList(EXTRA_ALL_DATA, allItemList);
        TLog.log("position" + position);
        TLog.log("image.position" + image.position);
        bundle.putInt(EXTRA_CURRENT_POSITION, image.position);
        intent.putExtra(EXTRA_BUNDLE, bundle);
        startActivityForResult(intent, REQUEST_CODE_IMAGE_CLICK);
    }

    @Override
    public void onImageSelect(Image image) {

    }

    @Override
    public void capture() {
        if (is_elec_data) {
            setElecDialog();
        } else {

        setDialog();
        }
    }

    private void setElecDialog() {
        mCameraDialog = new Dialog(getActivity(), R.style.BottomDialog);
        LinearLayout root = (LinearLayout) LayoutInflater.from(getActivity()).inflate(
                R.layout.elec_data_bottom, null);
        //初始化视图
        root.findViewById(R.id.btn_choose_img).setOnClickListener(this);
        root.findViewById(R.id.btn_open_camera).setOnClickListener(this);
        root.findViewById(R.id.btn_cancel).setOnClickListener(this);
        root.findViewById(R.id.btn_choose_img_receipt_folder).setOnClickListener(this);
        root.findViewById(R.id.btn_shopping_stamp).setOnClickListener(this);

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
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE_CAPTURE&&resultCode == RESULT_OK) {
            Uri contentUri = mediaStoreCompat.getCurrentPhotoUri();
            String path = mediaStoreCompat.getCurrentPhotoPath();
//            try {
//                TLog.log("path"+path);
//                MediaStore.Images.Media.insertImage(getActivity().getContentResolver(), path, new File(path).getName(), null);
//                getActivity().sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE,contentUri));
//            } catch (NullPointerException e) {
//                e.printStackTrace();
//            } catch (FileNotFoundException e) {
//                e.printStackTrace();
//            }
            Image image = new Image();
            image.isFromNet = false;
            image.name = new File(path).getName();
            image.isCapture = false;
            image.path = path;
            image.position = mPreviousPosition;
            image.uri = contentUri;
            ArrayList<Image> arrayList = new ArrayList<>();
            arrayList.add(image);
            Intent intent = new Intent();
            intent.setClass(mContext, FillUpActivity.class);
            intent.putParcelableArrayListExtra("images", arrayList);
            startActivityForResult(intent,REQUEST_CODE_AMOUNT);
//            images.add(image);
//            UploadReceiptAdapter uploadReceiptAdapter = (UploadReceiptAdapter) rvUploadReceipt.getAdapter();
//            uploadReceiptAdapter.notifyItemInserted(mPreviousPosition);
//            mPreviousPosition = images.size();

            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
                getActivity().revokeUriPermission(contentUri,
                        Intent.FLAG_GRANT_WRITE_URI_PERMISSION | Intent.FLAG_GRANT_READ_URI_PERMISSION);
            }
        } else if (REQUEST_CODE_IMAGE_CLICK == requestCode) {
            switch (resultCode) {
                case RESULT_CODE_BACK:
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
            ArrayList<Image> arrayList = new ArrayList<>();
            for (Uri uri : uris) {
                if (uri == null) {
                    BaseApplication.showToast("图片格式不符，请上传其他的发票~");
                    return;
                }
                String path = BitmapUtils.getRealFilePath(getActivity(),uri);

                Image image = new Image();
                image.isCapture = false;
                image.position = mPreviousPosition;
                mPreviousPosition++;
                image.uri = uri;
                image.path = path;
                image.isFromNet = false;
                arrayList.add(image);
//                UploadReceiptAdapter uploadReceiptAdapter = (UploadReceiptAdapter) rvUploadReceipt.getAdapter();
//                uploadReceiptAdapter.notifyItemInserted(mPreviousPosition);
            }

            Intent intent = new Intent();
            intent.putParcelableArrayListExtra("images", arrayList);
            intent.setClass(mContext, FillUpActivity.class);
            startActivityForResult(intent,REQUEST_CODE_AMOUNT);

        } else if (requestCode ==REQUEST_CODE_FROM_ELEC && resultCode == RESULT_OK) {
            Bundle bundleExtra = data.getBundleExtra(ReceiptActivityToken.EXTRA_DATA_FROM_TOKEN);
            ArrayList<Image> parcelableArrayList = bundleExtra.getParcelableArrayList(ReceiptActivityToken.RESULT_RECEIPT_FOLDER);

            if (parcelableArrayList != null && parcelableArrayList.size() == 0) {
                return;
            }
            ArrayList<Image> arrayList = new ArrayList<>();
            for (Image image : parcelableArrayList) {
                image.position = mPreviousPosition;
                arrayList.add(image);
                mPreviousPosition++;
//                UploadReceiptAdapter uploadReceiptAdapter = (UploadReceiptAdapter) rvUploadReceipt.getAdapter();
//                uploadReceiptAdapter.notifyItemInserted(mPreviousPosition);
            }
            Intent intent = new Intent();
            intent.setClass(mContext, FillUpActivity.class);
            intent.putParcelableArrayListExtra("images", arrayList);
            startActivityForResult(intent,REQUEST_CODE_AMOUNT);
        } else if (requestCode == REQUEST_CODE_AMOUNT) {
            if (resultCode == RESULT_OK) {
                ArrayList<Image> arrayList = data.getParcelableArrayListExtra("images");
                mPreviousPosition += arrayList.size();
                images.addAll(arrayList);
                UploadReceiptAdapter uploadReceiptAdapter = (UploadReceiptAdapter) rvUploadReceipt.getAdapter();
                uploadReceiptAdapter.notifyItemRangeInserted(mPreviousPosition,arrayList.size());

            }
        }
    }

    private void setDialog() {
        mCameraDialog = new Dialog(getActivity(), R.style.BottomDialog);
        LinearLayout root = (LinearLayout) LayoutInflater.from(getActivity()).inflate(
                R.layout.dialog_bottom, null);
        //初始化视图
        root.findViewById(R.id.btn_choose_img).setOnClickListener(this);
        root.findViewById(R.id.btn_open_camera).setOnClickListener(this);
        root.findViewById(R.id.btn_cancel).setOnClickListener(this);
        mCameraDialog.setContentView(root);
        Window dialogWindow = mCameraDialog.getWindow();
        dialogWindow.setGravity(Gravity.BOTTOM);
//        dialogWindow.setWindowAnimations(R.style.dialogstyle); // 添加动画
        WindowManager.LayoutParams lp = dialogWindow.getAttributes(); // 获取对话框当前的参数值
        lp.x = 0; // 新位置X坐标
        lp.y = 0; // 新位置Y坐标
        lp.width =  getResources().getDisplayMetrics().widthPixels; // 宽度
        root.measure(0, 0);
        lp.height = root.getMeasuredHeight();

        lp.alpha = 9f; // 透明度
        dialogWindow.setAttributes(lp);
        mCameraDialog.show();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_choose_img:
                openMedia();
                mCameraDialog.dismiss();
                break;
            case R.id.btn_open_camera:
                if (MediaStoreCompat.hasCameraFeature(getActivity())) {
                    onSaveInstanceState(getArguments());
                    RxPermissions rxPermissions = new RxPermissions(getActivity());
                    rxPermissions.request(Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.CAMERA).subscribe(new Observer<Boolean>() {
                        @Override
                        public void onSubscribe(Disposable d) {

                        }

                        @Override
                        public void onNext(Boolean aBoolean) {
                            if (aBoolean) {
                                mediaStoreCompat.dispatchCaptureIntent(getActivity(), REQUEST_CODE_CAPTURE);
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
                mCameraDialog.dismiss();
                break;
            case R.id.btn_cancel:
                mCameraDialog.dismiss();
                break;
            case R.id.btn_choose_img_receipt_folder:
                startActivityForResult(new Intent(mContext, ReceiptActivityToken.class),REQUEST_CODE_FROM_ELEC);
                mCameraDialog.dismiss();
                break;
            case R.id.btn_shopping_stamp:
                UploadReceiptActivity activity = (UploadReceiptActivity) getActivity();
                activity.scan();
                mCameraDialog.dismiss();
                break;

            default:
        }
    }

    public ArrayList<Image> getCurrentImages(){
        if (images!= null) {
            return images;
        }
        return null;
    }
}
