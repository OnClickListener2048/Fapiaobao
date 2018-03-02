package com.pilipa.fapiaobao.ui.fragment;

import android.Manifest;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mylibrary.utils.TLog;
import com.google.gson.Gson;
import com.pilipa.fapiaobao.R;
import com.pilipa.fapiaobao.account.AccountHelper;
import com.pilipa.fapiaobao.adapter.supply.UploadReceiptAdapter;
import com.pilipa.fapiaobao.base.BaseActivity;
import com.pilipa.fapiaobao.base.BaseApplication;
import com.pilipa.fapiaobao.base.BaseFragment;
import com.pilipa.fapiaobao.compat.MediaStoreCompat;
import com.pilipa.fapiaobao.net.Api;
import com.pilipa.fapiaobao.net.bean.invoice.OrderBean;
import com.pilipa.fapiaobao.net.bean.invoice.UploadInvoice;
import com.pilipa.fapiaobao.net.bean.invoice.UploadProcessing;
import com.pilipa.fapiaobao.net.bean.me.MyInvoiceListBean;
import com.pilipa.fapiaobao.ui.FillUpActivity;
import com.pilipa.fapiaobao.ui.PreviewActivity;
import com.pilipa.fapiaobao.ui.UploadReceiptPreviewActivity;
import com.pilipa.fapiaobao.ui.deco.GridInset;
import com.pilipa.fapiaobao.ui.model.Image;
import com.pilipa.fapiaobao.ui.receipt_folder_image_select.ReceiptActivityToken;
import com.pilipa.fapiaobao.utils.BitmapUtils;
import com.pilipa.fapiaobao.utils.DialogUtil;
import com.pilipa.fapiaobao.utils.ReceiptDiff;
import com.pilipa.fapiaobao.utils.SharedPreferencesHelper;
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

public class UploadPreviewReceiptFragment extends BaseFragment implements
        View.OnClickListener
        , UploadReceiptAdapter.OnImageClickListener
        , UploadReceiptAdapter.OnImageSelectListener
        , UploadReceiptAdapter.OnPhotoCapture {

    public static final int REQUEST_CODE_FROM_ELEC = 50;
    public static final int REQUEST_CODE_AMOUNT = 60;
    public static final int REQUEST_CODE_CAPTURE = 10;
    public static final int REQUEST_CODE_CHOOSE = 20;
    public static final String EXTRA_ALL_DATA = "EXTRA_ALL_DATA";
    public static final String EXTRA_CURRENT_POSITION = "EXTRA_CURRENT_POSITION";
    public static final String EXTRA_BUNDLE = "EXTRA_BUNDLE";
    public static final int REQUEST_CODE_IMAGE_CLICK = 30;
    public static final int RESULT_CODE_BACK = 40;
    public static final String IS_SHOW_SELECT_AND_DELETE = "is_show_select_and_delete";
    private static final int REQUEST_CODE_SCAN = 0x0002;
    private static final String TAG = "UploadPreviewReceiptFragment";
    public boolean isFinish = true;
    @Bind(R.id.rv_upload_receipt)
    RecyclerView rvUploadReceipt;
    private int mImageResize;
    private ArrayList<Image> images;
    private MediaStoreCompat mediaStoreCompat;
    private int mPreviousPosition = -1;
    private Dialog mCameraDialog;
    private String invoice_variety;
    private Dialog mBottomDialog;
    private ArrayList<Image> mArrayList;
    private Dialog mDialog;

    public static UploadPreviewReceiptFragment newInstance(Bundle b) {
        UploadPreviewReceiptFragment u = new UploadPreviewReceiptFragment();
        u.setArguments(b);
        return u;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_upload_receipt;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        Bundle arguments = getArguments();
        invoice_variety = arguments.getString("invoice_variety");
        if (images == null) {
            images = new ArrayList<>();
        }
        mArrayList = arguments.getParcelableArrayList(UploadReceiptPreviewActivity.PAPER_ELEC_RECEIPT_DATA);
        TLog.d(TAG, "mArrayList" + mArrayList);
        if (mArrayList == null) {
            mArrayList = arguments.getParcelableArrayList(UploadReceiptPreviewActivity.PAPER_SPECIAL_RECEIPT_DATA);
        }

        if (mArrayList == null) {
            mArrayList = arguments.getParcelableArrayList(UploadReceiptPreviewActivity.PAPER_NORMAL_RECEIPT_DATA);
        }
        this.images = mArrayList;

        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);

        return rootView;
    }

    @Override
    public void onStart() {
        super.onStart();


    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Intent intent = new Intent();
        intent.putExtra(ReceiptActivityToken.EXTRA_DATA_FROM_TOKEN, getArguments());
        resultFromElec(intent);
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
                    Matisse.from(UploadPreviewReceiptFragment.this)
                            .choose(MimeType.of(MimeType.JPEG, MimeType.PNG))
                            .countable(true)
                            .captureStrategy(
                                    new CaptureStrategy(true, MediaStoreCompat.authority))
                            .maxSelectable(9)
                            .gridExpectedSize(
                                    getResources().getDimensionPixelSize(R.dimen.grid_expected_size))
                            .restrictOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)
                            .thumbnailScale(0.4f)
                            .imageEngine(new GlideEngine())
                            .theme(R.style.Matisse_Pilipa)
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
        mediaStoreCompat = new MediaStoreCompat(getActivity(), this);
        GridLayoutManager grid = new GridLayoutManager(getActivity(), 3) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };
        rvUploadReceipt.setLayoutManager(grid);
        int spacing = getResources().getDimensionPixelOffset(R.dimen.spacing);
        rvUploadReceipt.addItemDecoration(new GridInset(3, spacing, true));
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
        bundle.putBoolean(IS_SHOW_SELECT_AND_DELETE,false);
        bundle.putParcelableArrayList(EXTRA_ALL_DATA, allItemList);
        bundle.putInt(EXTRA_CURRENT_POSITION, position+1);
        intent.putExtra(EXTRA_BUNDLE, bundle);
        startActivityForResult(intent, REQUEST_CODE_IMAGE_CLICK);
    }

    @Override
    public void onImageSelect(Image image) {

    }

    @Override
    public void capture() {
        if ("3".equals(invoice_variety)) {
            accquireReceiptFolderData();
        } else {
        showDialog();

        }
    }

    private void showElecDialog(boolean b) {
        mDialog = DialogUtil.getInstance().createBottomDialog(mContext, new DialogUtil.OnDialogDismissListener() {
            @Override
            public void onDialogDismiss(View view) {
                mDialog.dismiss();
            }
        }, new DialogUtil.OnMediaOpenListener() {
            @Override
            public void onMediaOpen(View view) {
                openMedia();
                mDialog.dismiss();
            }
        }, b ? new DialogUtil.OnReceiptFolderOpenListener() {
            @Override
            public void onReceiptFolderOpen(View view) {
                startActivityForResult(new Intent(mContext, ReceiptActivityToken.class), REQUEST_CODE_FROM_ELEC);
                mDialog.dismiss();
            }
        } : null, null, new DialogUtil.OnShoppingStampOpenListener() {
            @Override
            public void onShoppingStampOpen(View view) {
                UploadReceiptPreviewActivity activity = (UploadReceiptPreviewActivity) getActivity();
                activity.scan();
                mDialog.dismiss();
            }
        });
        showDialog(mDialog);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE_CAPTURE && resultCode == RESULT_OK) {
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
            startActivityForResult(intent, REQUEST_CODE_AMOUNT);


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
                String path = BitmapUtils.getRealFilePath(getActivity(), uri);
                Image image = new Image();
                image.isCapture = false;
                image.position = mPreviousPosition;
                mPreviousPosition++;
                image.uri = uri;
                image.path = path;
                image.isFromNet = false;
                arrayList.add(image);
            }

            Intent intent = new Intent();
            intent.putParcelableArrayListExtra("images", arrayList);
            intent.setClass(mContext, FillUpActivity.class);
            startActivityForResult(intent, REQUEST_CODE_AMOUNT);

        } else if (requestCode == REQUEST_CODE_FROM_ELEC && resultCode == RESULT_OK) {
            resultFromElec(data);
        } else if (requestCode == REQUEST_CODE_AMOUNT) {
            if (resultCode == RESULT_OK) {
                ArrayList<Image> arrayList = data.getParcelableArrayListExtra("images");
                mPreviousPosition += arrayList.size();
                images.addAll(arrayList);
                UploadReceiptAdapter uploadReceiptAdapter = (UploadReceiptAdapter) rvUploadReceipt.getAdapter();
                uploadReceiptAdapter.notifyItemRangeInserted(mPreviousPosition, arrayList.size());
            }
        }
    }

    public void resultFromElec(Intent data) {
        TLog.d(TAG, "resultFromElec");
        Bundle bundleExtra = data.getBundleExtra(ReceiptActivityToken.EXTRA_DATA_FROM_TOKEN);
        ArrayList<Image> parcelableArrayList = bundleExtra.getParcelableArrayList(ReceiptActivityToken.RESULT_RECEIPT_FOLDER);

        if (parcelableArrayList == null) return;

        if (parcelableArrayList.size() == 0) return;

        ArrayList<Image> arrayList = new ArrayList<>();
        for (Image image : parcelableArrayList) {
            image.position = mPreviousPosition;
            arrayList.add(image);
            mPreviousPosition++;
        }
        Intent intent = new Intent();
        intent.setClass(mContext, FillUpActivity.class);
        intent.putParcelableArrayListExtra("images", arrayList);
        startActivityForResult(intent, REQUEST_CODE_AMOUNT);
    }

//    @Override
//    public void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if (requestCode == REQUEST_CODE_CAPTURE && resultCode == RESULT_OK) {
//            Uri contentUri = mediaStoreCompat.getCurrentPhotoUri();
//            String path = mediaStoreCompat.getCurrentPhotoPath();
//            try {
//                MediaStore.Images.Media.insertImage(getActivity().getContentResolver(), path, new File(path).getName(), null);
//                getActivity().sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, contentUri));
//            } catch (FileNotFoundException e) {
//                e.printStackTrace();
//            }
//            Image image = new Image();
//            image.isFromNet = false;
//            image.name = new File(path).getName();
//            image.isCapture = false;
//            image.position = mPreviousPosition;
//            image.uri = contentUri;
//            images.add(image);
//            UploadReceiptAdapter uploadReceiptAdapter = (UploadReceiptAdapter) rvUploadReceipt.getAdapter();
//            uploadReceiptAdapter.notifyItemInserted(mPreviousPosition);
//            mPreviousPosition = images.size();
//
//            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
//                getActivity().revokeUriPermission(contentUri,
//                        Intent.FLAG_GRANT_WRITE_URI_PERMISSION | Intent.FLAG_GRANT_READ_URI_PERMISSION);
//            }
//        } else if (REQUEST_CODE_IMAGE_CLICK == requestCode) {
//            switch (resultCode) {
//                case RESULT_CODE_BACK:
//                    Bundle bundleExtra = data.getBundleExtra(EXTRA_BUNDLE);
//                    ArrayList<Image> images = bundleExtra.getParcelableArrayList(EXTRA_ALL_DATA);
//                    UploadReceiptAdapter uploadReceiptAdapter = (UploadReceiptAdapter) rvUploadReceipt.getAdapter();
//                    uploadReceiptAdapter.refresh(images);
//                    DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(new ReceiptDiff(this.images, images), true);
//                    diffResult.dispatchUpdatesTo(uploadReceiptAdapter);
//                    this.images = images;
//                    mPreviousPosition = images.size();
//                    break;
//                default:
//            }
//        } else if (requestCode == REQUEST_CODE_CHOOSE && resultCode == RESULT_OK) {
//            List<Uri> uris = Matisse.obtainResult(data);
//            for (Uri uri : uris) {
//                Image image = new Image();
//                image.isCapture = false;
//                image.position = mPreviousPosition;
//                mPreviousPosition++;
//                image.uri = uri;
//                image.isFromNet = false;
//                images.add(image);
//                UploadReceiptAdapter uploadReceiptAdapter = (UploadReceiptAdapter) rvUploadReceipt.getAdapter();
//                uploadReceiptAdapter.notifyItemInserted(mPreviousPosition);
//            }
//        }
//    }

    private void showDialog() {
        if (mBottomDialog == null) {
            mBottomDialog = DialogUtil.getInstance().createBottomDialog(mContext, new DialogUtil.OnDialogDismissListener() {
                @Override
                public void onDialogDismiss(View view) {
                    mBottomDialog.dismiss();
                }
            }, new DialogUtil.OnMediaOpenListener() {
                @Override
                public void onMediaOpen(View view) {
                    openMedia();
                    mBottomDialog.dismiss();
                }
            }, null, new DialogUtil.OnPhotoTakeListener() {
                @Override
                public void onPhotoTake(View view) {
                    if (MediaStoreCompat.hasCameraFeature(getActivity())) {
                        mediaStoreCompat.dispatchCaptureIntent(getActivity(), REQUEST_CODE_CAPTURE);
                    }
                    mBottomDialog.dismiss();
                }
            }, null);
        }
        showDialog(mBottomDialog);
    }

    private void accquireReceiptFolderData() {
        Api.myInvoiceList(AccountHelper.getToken(), this, new Api.BaseRawResponse<MyInvoiceListBean>() {
            @Override
            public void onStart() {
                ((BaseActivity) getActivity()).showProgressDialog();
            }

            @Override
            public void onFinish() {
                ((BaseActivity) getActivity()).hideProgressDialog();
            }

            @Override
            public void onError() {
            }

            @Override
            public void onTokenInvalid() {
                login();
            }

            @Override
            public void setData(MyInvoiceListBean myInvoiceListBean) {
                if (myInvoiceListBean.getStatus() == 200) {
                    List<MyInvoiceListBean.DataBean> list = myInvoiceListBean.getData();
                    if (list != null && list.size() > 0) {
                        showElecDialog(true);
                    } else {
                        showElecDialog(false);
                    }
                } else if (myInvoiceListBean.getStatus() == 400) {
                    showElecDialog(false);
                }
            }
        });
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
                    mediaStoreCompat.dispatchCaptureIntent(getActivity(), REQUEST_CODE_CAPTURE);
                }
                mCameraDialog.dismiss();
                break;
            case R.id.btn_cancel:
                mCameraDialog.dismiss();
                break;

            default:
        }
    }

    public ArrayList<Image> getCurrentImages() {
        if (images != null) {
            return images;
        }
        return null;
    }

    public void uploadReceipt() {

                OrderBean orderBean = SharedPreferencesHelper.loadFormSource(mContext, OrderBean.class);
                final UploadInvoice uploadInvoice = new UploadInvoice();
                uploadInvoice.setOrderId(orderBean.getData().getOrderId());
                uploadInvoice.setToken(AccountHelper.getToken());
                uploadInvoice.setVariety(invoice_variety);
                List<UploadInvoice.InvoiceImageListBean> listBeen = new ArrayList<UploadInvoice.InvoiceImageListBean>();
                for (Image image : images) {
                    if (!image.isCapture) {
                            UploadInvoice.InvoiceImageListBean invoiceImageListBean = new UploadInvoice.InvoiceImageListBean();
                        if (image.isFromNet) {
                           invoiceImageListBean.setId(image.name);
                        } else {
                            invoiceImageListBean.setPicture(upLoadReceipt(image));
                        }
                        listBeen.add(invoiceImageListBean);
                    }
                }

                uploadInvoice.setInvoiceImageList(listBeen);
                Gson gson = new Gson();
                String json = gson.toJson(uploadInvoice);
                final UploadReceiptPreviewActivity uploadReceiptPreviewActivity = (UploadReceiptPreviewActivity) getActivity();
                Api.uploadReceipt(json, new Api.BaseViewCallbackWithOnStart<UploadProcessing>() {
                    @Override
                    public void onStart() {
                        isFinish = false;
                        uploadReceiptPreviewActivity.showProgressDialog();
                    }

                    @Override
                    public void onFinish() {
                        isFinish = true;
                        uploadReceiptPreviewActivity.hideProgressDialog();
                    }

                    @Override
                    public void onError() {
                        isFinish = true;
                        uploadReceiptPreviewActivity.hideProgressDialog();
                    }

                    @Override
                    public void setData(UploadProcessing uploadProcessing) {
                        if (uploadProcessing.getStatus() == 200) {
                            uploadReceiptPreviewActivity.onPubSuccess();
                            if (!uploadReceiptPreviewActivity.isFinishing()) {
                                uploadReceiptPreviewActivity.finish();
                            }

                        }

                    }
                });
            }

}
