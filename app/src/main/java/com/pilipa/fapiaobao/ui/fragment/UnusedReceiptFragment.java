package com.pilipa.fapiaobao.ui.fragment;

import android.Manifest;
import android.app.Dialog;
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
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.pilipa.fapiaobao.R;
import com.pilipa.fapiaobao.account.AccountHelper;
import com.pilipa.fapiaobao.adapter.UnusedReceiptAdapter;
import com.pilipa.fapiaobao.base.BaseActivity;
import com.pilipa.fapiaobao.base.BaseApplication;
import com.pilipa.fapiaobao.base.BaseFragment;
import com.pilipa.fapiaobao.compat.MediaStoreCompat;
import com.pilipa.fapiaobao.net.Api;
import com.pilipa.fapiaobao.net.bean.LoginWithInfoBean;
import com.pilipa.fapiaobao.net.bean.me.MyInvoiceListBean;
import com.pilipa.fapiaobao.net.bean.me.NormalBean;
import com.pilipa.fapiaobao.net.bean.me.UploadLocalReceipt;
import com.pilipa.fapiaobao.ui.LoginActivity;
import com.pilipa.fapiaobao.ui.ReceiptFolderActivity;
import com.pilipa.fapiaobao.ui.UnusedPreviewActivity;
import com.pilipa.fapiaobao.ui.deco.GridInset;
import com.pilipa.fapiaobao.ui.model.Image;
import com.pilipa.fapiaobao.utils.AnimationConfig;
import com.pilipa.fapiaobao.utils.ReceiptDiff;
import com.pilipa.fapiaobao.utils.SharedPreferencesHelper;
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
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

import static android.app.Activity.RESULT_OK;

/**
 * Created by edz on 2017/10/27.
 */

public class UnusedReceiptFragment extends BaseFragment implements UnusedReceiptAdapter.OnImageClickListener
        , UnusedReceiptAdapter.OnImageSelectListener, UnusedReceiptAdapter.OnPhotoCapture,View.OnClickListener
        ,UnusedReceiptAdapter.OnImageLongClickListener{
    private static final String TAG = "UnusedReceiptFragment";
    public static final int REQUEST_CODE_CAPTURE = 10;
    public static final int REQUEST_CODE_CHOOSE = 20;
    public static final String EXTRA_ALL_DATA = "EXTRA_ALL_DATA";
    public static final String EXTRA_CURRENT_POSITION = "EXTRA_CURRENT_POSITION";
    public static final String EXTRA_BUNDLE = "EXTRA_BUNDLE";
    public static final int REQUEST_CODE_IMAGE_CLICK = 30;
    public static final int RESULT_CODE_BACK = 40;

    private int mImageResize;
    private ArrayList<Image> images;
    private MediaStoreCompat mediaStoreCompat;
    private int mPreviousPosition = -1;
    private Dialog mCameraDialog;
    private Dialog mDelDialog;
    private UnusedReceiptAdapter unusedReceiptAdapter;

    @Bind(R.id.recyclerview)
    RecyclerView recyclerview;
    private ArrayList<Image> arrayList;

    public static UnusedReceiptFragment newInstance(Bundle bundle) {
        UnusedReceiptFragment unusedReceiptFragment = new UnusedReceiptFragment();
        unusedReceiptFragment.setArguments(bundle);
        return unusedReceiptFragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_unused;
    }


    @Override
    protected void initWidget(View root) {
        super.initWidget(root);
        arrayList = new ArrayList<>();
        mediaStoreCompat = new MediaStoreCompat(getActivity(),this);
        GridLayoutManager grid = new GridLayoutManager(getActivity(), 3){
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };
        recyclerview.setLayoutManager(grid);
        int spacing = getResources().getDimensionPixelOffset(R.dimen.spacing);
        recyclerview.addItemDecoration(new GridInset(3, spacing, true));
        Image image = new Image();
        image.isFromNet = false;
        image.isCapture = true;
        images = new ArrayList<>();
        images.add(image);
        mPreviousPosition = images.size();
    }

    @Override
    protected void initData() {
        super.initData();
        myInvoiceList();

    }

    private void setUpData(List<MyInvoiceListBean.DataBean> results) {
        for (MyInvoiceListBean.DataBean result : results) {
            Image image = new Image();
            image.isFromNet = true;
            image.path = result.getUrl();
            image.isCapture = false;
            image.isSelected = false;
            image.name = result.getId();
            images.add(image);
        }
        mPreviousPosition = images.size();
        unusedReceiptAdapter = new UnusedReceiptAdapter(images, getImageResize(getActivity()));
        unusedReceiptAdapter.setOnImageClickListener(this);
        unusedReceiptAdapter.setOnImageSelectListener(this);
        unusedReceiptAdapter.setOnImageLongClickListener(this);
        unusedReceiptAdapter.setOnPhotoCapture(this);
        recyclerview.setAdapter(unusedReceiptAdapter);
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

    private void openMedia() {
        RxPermissions rxPermissions = new RxPermissions(getActivity());
        rxPermissions.request(Manifest.permission.WRITE_EXTERNAL_STORAGE).subscribe(new Observer<Boolean>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Boolean aBoolean) {
                if (aBoolean) {
                    Matisse.from(UnusedReceiptFragment.this)
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

    private int getImageResize(Context context) {

        if (mImageResize == 0 && recyclerview !=null) {
            RecyclerView.LayoutManager lm = recyclerview.getLayoutManager();
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
        Intent intent = new Intent(mContext, UnusedPreviewActivity.class);
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList(EXTRA_ALL_DATA, allItemList);
        bundle.putInt(EXTRA_CURRENT_POSITION, image.position);
        intent.putExtra(EXTRA_BUNDLE, bundle);
        startActivityForResult(intent, REQUEST_CODE_IMAGE_CLICK);
    }

    @Override
    public void onImageSelect(Image image) {
        Log.d(TAG, "onImageSelect: ");
        if (arrayList != null) {
            Log.d(TAG, "onImageSelect:arrayList != null ");
            if (image.isSelected) {
                Log.d(TAG, "onImageSelect: add");
                arrayList.add(image);
            } else {
                if (arrayList.contains(image)) {
                    arrayList.remove(image);
                    Log.d(TAG, "onImageSelect: remove");
                }
            }
        }
    }

    @Override
    public void capture() {
        AccountHelper.isTokenValid(new Api.BaseViewCallback<LoginWithInfoBean>() {
            @Override
            public void setData(LoginWithInfoBean loginWithInfoBean) {
                if (loginWithInfoBean.getStatus() == 200) {
                    setDialog();
                } else {
                    startActivity(new Intent(mContext, LoginActivity.class));
                }
            }
        });

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
            image.position = mPreviousPosition;
            image.uri = contentUri;
            images.add(image);
            UnusedReceiptAdapter unusedReceiptAdapter = (UnusedReceiptAdapter) recyclerview.getAdapter();
            unusedReceiptAdapter.notifyItemInserted(mPreviousPosition);
            mPreviousPosition = images.size();

            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
                getActivity().revokeUriPermission(contentUri,
                        Intent.FLAG_GRANT_WRITE_URI_PERMISSION | Intent.FLAG_GRANT_READ_URI_PERMISSION);
            }

            LoginWithInfoBean loginWithInfoBean = SharedPreferencesHelper.loadFormSource(mContext, LoginWithInfoBean.class);

            UploadLocalReceipt uploadLocalReceipt = new UploadLocalReceipt();
            uploadLocalReceipt.setToken(loginWithInfoBean.getData().getToken());
            List<String> imageList = new ArrayList<>();
            imageList.add(upLoadReceipt(image.uri));
            uploadLocalReceipt.setPictureList(imageList);
            Gson gson = new Gson();
            Api.uploadLocalReceipt(gson.toJson(uploadLocalReceipt), new Api.BaseViewCallbackWithOnStart<NormalBean>() {
                @Override
                public void onStart() {
                    ((BaseActivity)getActivity()).showProgressDialog();
                }

                @Override
                public void onFinish() {
                    ((BaseActivity)getActivity()).hideProgressDialog();
                }

                @Override
                public void onError() {
                    ((BaseActivity)getActivity()).hideProgressDialog();
                }
                @Override
                public void setData(NormalBean response) {
//                    TLog.log(response.body());
                    BaseApplication.showToast("上传成功");
                }
            });
        } else if (REQUEST_CODE_IMAGE_CLICK == requestCode) {
            switch (resultCode) {
                case RESULT_CODE_BACK:
                    Bundle bundleExtra = data.getBundleExtra(EXTRA_BUNDLE);
                    ArrayList<Image> images = bundleExtra.getParcelableArrayList(EXTRA_ALL_DATA);
                    UnusedReceiptAdapter unusedReceiptAdapter = (UnusedReceiptAdapter) recyclerview.getAdapter();
                    unusedReceiptAdapter.refresh(images);
                    DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(new ReceiptDiff(this.images, images), true);
                    diffResult.dispatchUpdatesTo(unusedReceiptAdapter);
                    this.images = images;
                    mPreviousPosition = images.size();
                    break;
                default:
            }
        } else if (requestCode == REQUEST_CODE_CHOOSE && resultCode == RESULT_OK) {
            List<Uri> uris = Matisse.obtainResult(data);
            List<String> imageList = new ArrayList<>();
            for (Uri uri : uris) {
                Image image = new Image();
                image.isCapture = false;
                image.position = mPreviousPosition;
                mPreviousPosition++;
                image.uri = uri;
                image.isFromNet = false;
                images.add(image);
                imageList.add(upLoadReceipt(uri));
                UnusedReceiptAdapter unusedReceiptAdapter = (UnusedReceiptAdapter) recyclerview.getAdapter();
                unusedReceiptAdapter.notifyItemInserted(mPreviousPosition);
            }


            LoginWithInfoBean loginWithInfoBean = SharedPreferencesHelper.loadFormSource(mContext, LoginWithInfoBean.class);
            UploadLocalReceipt uploadLocalReceipt = new UploadLocalReceipt();
            uploadLocalReceipt.setToken(loginWithInfoBean.getData().getToken());

            uploadLocalReceipt.setPictureList(imageList);
            Gson gson = new Gson();
            Api.uploadLocalReceipt(gson.toJson(uploadLocalReceipt), new Api.BaseViewCallbackWithOnStart<NormalBean>() {
                @Override
                public void onStart() {
                    ((BaseActivity)getActivity()).showProgressDialog();
                }

                @Override
                public void onFinish() {
                    ((BaseActivity)getActivity()).hideProgressDialog();
                }

                @Override
                public void onError() {
                    ((BaseActivity)getActivity()).hideProgressDialog();
                }
                @Override
                public void setData(NormalBean response) {
//                    TLog.log(response.body());
                    BaseApplication.showToast("上传成功");
                }
            });
        }
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


    private void myInvoiceList(){
        AccountHelper.isTokenValid(new Api.BaseViewCallback<LoginWithInfoBean>() {
            @Override
            public void setData(LoginWithInfoBean loginWithInfoBean) {
                if (loginWithInfoBean.getStatus() == 200) {
                    Api.myInvoiceList(AccountHelper.getToken() , new Api.BaseViewCallback<MyInvoiceListBean>() {
                        @Override
                        public void setData(MyInvoiceListBean myInvoiceListBean) {
                            List<MyInvoiceListBean.DataBean> list = new ArrayList<>();
                            if(myInvoiceListBean.getStatus() == 200){
                                list=myInvoiceListBean.getData();
                                setUpData(list);
                            }else{
                                setUpData(list);
                            }
                            Log.d(TAG, "updateData:myInvoiceList success");
                        }
                    });
                }else {
                    startActivity(new Intent(mContext, LoginActivity.class));
                }
            }
        });
    }

    @Override
    public void onImageLongClick(View view,Image image,int pos) {
        AnimationConfig.shake(mContext,view);
        setDelDialog(image,pos);
        Log.d(TAG, "updateData:onImageLongClick "+image.name);
    }
    private void setDelDialog(final Image image,final int pos) {
        mDelDialog = new Dialog(getActivity(), R.style.BottomDialog);
        LinearLayout root = (LinearLayout) LayoutInflater.from(getActivity()).inflate(
                R.layout.layout_delete_tip, null);
        TextView tv_title = (TextView) root.findViewById(R.id.tv_title);
        tv_title.setText("确定要删除该发票吗？");
        //初始化视图
        root.findViewById(R.id.btn_cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDelDialog.dismiss();
            }
        });
        root.findViewById(R.id.btn_confirm).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteMyInvoice(image.name,pos);
            }
        });
        mDelDialog.setContentView(root);
        Window dialogWindow = mDelDialog.getWindow();
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
        mDelDialog.show();
    }
    private void deleteMyInvoice(final String invoiceId,final int pos) {
        AccountHelper.isTokenValid(new Api.BaseViewCallback<LoginWithInfoBean>() {
            @Override
            public void setData(LoginWithInfoBean loginWithInfoBean) {
                 final ReceiptFolderActivity activity = (ReceiptFolderActivity) getActivity();
                if (loginWithInfoBean.getStatus() == 200) {
                    Api.deleteMyInvoice(AccountHelper.getToken(),invoiceId, new Api.BaseViewCallbackWithOnStart<NormalBean>() {
                        @Override
                        public void onStart() {
                            activity.showProgressDialog();
                        }

                        @Override
                        public void onFinish() {
                            activity.hideProgressDialog();
                        }

                        @Override
                        public void onError() {
                            activity.hideProgressDialog();
                            BaseApplication.showToast("删除失败");
                        }

                        @Override
                        public void setData(NormalBean normalBean) {
                            if(normalBean.getStatus() == 200){
                                mDelDialog.dismiss();
                                unusedReceiptAdapter.delete(pos);
                                BaseApplication.showToast("删除成功");
                            }
                            Log.d("", "initData:deleteMyInvoice success");
                        }
                    });
                }else {
                    startActivity(new Intent(mContext, LoginActivity.class));
                }
            }
        });
    }
}
