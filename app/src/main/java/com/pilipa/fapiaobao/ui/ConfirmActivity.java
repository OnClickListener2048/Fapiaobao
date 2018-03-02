package com.pilipa.fapiaobao.ui;

import android.Manifest;
import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.mylibrary.utils.RegexUtils;
import com.example.mylibrary.utils.TLog;
import com.pilipa.fapiaobao.R;
import com.pilipa.fapiaobao.account.AccountHelper;
import com.pilipa.fapiaobao.base.BaseActivity;
import com.pilipa.fapiaobao.base.BaseApplication;
import com.pilipa.fapiaobao.compat.MediaStoreCompat;
import com.pilipa.fapiaobao.net.Api;
import com.pilipa.fapiaobao.net.bean.LoginWithInfoBean;
import com.pilipa.fapiaobao.net.bean.invoice.CompanyCollectBean;
import com.pilipa.fapiaobao.net.bean.invoice.MacherBeanToken;
import com.pilipa.fapiaobao.net.bean.me.FavBean;
import com.pilipa.fapiaobao.net.bean.me.MyInvoiceListBean;
import com.pilipa.fapiaobao.ui.constants.Constant;
import com.pilipa.fapiaobao.ui.fragment.FinanceFragment;
import com.pilipa.fapiaobao.ui.model.Image;
import com.pilipa.fapiaobao.ui.receipt_folder_image_select.ReceiptActivityToken;
import com.pilipa.fapiaobao.ui.zxing.SimpleCaptureActivity;
import com.pilipa.fapiaobao.utils.BitmapUtils;
import com.pilipa.fapiaobao.utils.ButtonUtils;
import com.pilipa.fapiaobao.utils.DialogUtil;
import com.pilipa.fapiaobao.utils.SharedPreferencesHelper;
import com.pilipa.fapiaobao.zxing.encode.CodeCreator;
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
import butterknife.OnClick;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by edz on 2017/10/24.
 */

public class ConfirmActivity extends BaseActivity {

    public static final int REQUEST_CODE_CAPTURE = 10;
    public static final int REQUEST_CODE_CHOOSE = 20;
    public static final String EXTRA_ALL_DATA = "EXTRA_ALL_DATA";
    public static final String EXTRA_CURRENT_POSITION = "EXTRA_CURRENT_POSITION";
    public static final String EXTRA_BUNDLE = "EXTRA_BUNDLE";
    public static final String PAPER_NORMAL_RECEIPT_DATA = "paper_normal_receipt_data";
    public static final String PAPER_SPECIAL_RECEIPT_DATA = "paper_special_receipt_data";
    public static final String PAPER_ELEC_RECEIPT_DATA = "paper_elec_receipt_data";
    public static final int REQUEST_CODE_IMAGE_CLICK = 30;
    public static final int RESULT_CODE_BACK = 40;
    public static final int REQUEST_CODE_FROM_ELEC = 50;
    public static final int REQUEST_CODE_AMOUNT = 60;
    public static final String TAG = ConfirmActivity.class.getSimpleName();
    private static final String DECODED_CONTENT_KEY = "codedContent";
    private static final int REQUEST_CODE_SCAN = 0x0002;
    @Bind(R.id.title)
    TextView title;
    @Bind(R.id.confirm_back)
    ImageView confirmBack;
    @Bind(R.id.filter)
    TextView filter;
    @Bind(R.id.collect)
    ImageView collect;
    @Bind(R.id.qr)
    ImageView qr;
    @Bind(R.id.look_directly)
    TextView lookDirectly;
    @Bind(R.id.translate)
    LinearLayout translate;
    @Bind(R.id.company_name)
    TextView companyName;
    @Bind(R.id.tex_number)
    TextView texNumber;
    @Bind(R.id.company_address)
    TextView companyAddress;
    @Bind(R.id.number)
    TextView number;
    @Bind(R.id.bank)
    TextView bank;
    @Bind(R.id.bank_account)
    TextView bankAccount;
    @Bind(R.id.translate_details)
    LinearLayout translateDetails;
    @Bind(R.id.upload_receipt)
    Button uploadReceipt;
    boolean isCollected;
    private  MacherBeanToken.DataBean.CompanyBean company_info;
    private String label;
    private String demandsId;
    private double amount;
    private String order;
    private double bonus;
    private int type;
    private String favoriteId;
    private Dialog mDialog;
    private MediaStoreCompat mMediaStoreCompat;
    private int mPreviousPosition = -1;
    private ArrayList<Image> images;
    private Dialog mDialog2;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_confirm;
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void initView() {
        translate.setVisibility(View.VISIBLE);
        translateDetails.setVisibility(View.GONE);
    }

    @Override
    public void initData() {
        type = getIntent().getIntExtra("type", 0);
        order = getIntent().getStringExtra("order");
        amount = getIntent().getDoubleExtra("amount",0);
        bonus = getIntent().getDoubleExtra("bonus",0);
        demandsId = getIntent().getStringExtra("demandsId");
        label = getIntent().getStringExtra(FinanceFragment.EXTRA_DATA_LABEL);
        company_info = getIntent().getParcelableExtra(Constant.COMPANY_INFO);
        companyName.setText(company_info.getName());
        companyAddress.setText(company_info.getAddress());
        texNumber.setText(company_info.getTaxno());
        number.setText(company_info.getPhone());
        bank.setText(company_info.getDepositBank());
        bankAccount.setText(company_info.getAccount());
        Image image = new Image();
        image.isFromNet = false;
        image.isCapture = true;
        images = new ArrayList<>();
        images.add(image);

        try {
            String content = new String(company_info.getQrcode().getBytes("UTF-8"), "ISO-8859-1");
            TLog.log("content-----------"+content);
            Bitmap qrCode = CodeCreator.createQRCode(this,content);
            qr.setImageBitmap(qrCode);
        } catch (Exception e) {
            BaseApplication.showToast("二维码生成失败");
            e.printStackTrace();
        }

        LoginWithInfoBean loginWithInfoBean = SharedPreferencesHelper.loadFormSource(this, LoginWithInfoBean.class);
        if (loginWithInfoBean != null) {
            Api.judgeCompanyIsCollcted(company_info.getId(), loginWithInfoBean.getData().getToken(), new Api.BaseViewCallback<FavBean>() {
                @Override
                public void setData(FavBean s) {
                    if(s.getFavoriteId() != null){
                        favoriteId = s.getFavoriteId();
                    }
                    if (s.getStatus() == 200) {
                        //TODO 设置收藏图片
                        isCollected = false;
                        collect.setImageResource(R.mipmap.collect);
                    } else if (s.getStatus() == 701 && s.getMsg().equals("token验证失败")) {
                        startActivity(new Intent(ConfirmActivity.this, LoginActivity.class));
                    } else if (s.getStatus() == 400) {
                        collect.setImageResource(R.mipmap.collected);
                        isCollected = true;
                    }
                }
            });
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    private void showDialog() {
        mDialog = DialogUtil.getInstance().createBottomDialog(this, new DialogUtil.OnDialogDismissListener() {
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
        }, null, new DialogUtil.OnPhotoTakeListener() {
            @Override
            public void onPhotoTake(View view) {
                takePhoto();
                mDialog.dismiss();
            }
        }, null);
        showDialog(mDialog);
    }

    private void showElecDialog(boolean b) {
        mDialog = DialogUtil.getInstance().createBottomDialog(this, new DialogUtil.OnDialogDismissListener() {
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
                startActivityForResult(new Intent(ConfirmActivity.this, ReceiptActivityToken.class), REQUEST_CODE_FROM_ELEC);
                mDialog.dismiss();
            }
        } : null, null, new DialogUtil.OnShoppingStampOpenListener() {
            @Override
            public void onShoppingStampOpen(View view) {
                scan();
                mDialog.dismiss();
            }
        });
        showDialog(mDialog);
    }


    private void takePhoto() {
        if (MediaStoreCompat.hasCameraFeature(this)) {
            RxPermissions rxPermissions = new RxPermissions(this);
            rxPermissions.request(Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA).subscribe(new Observer<Boolean>() {
                @Override
                public void onSubscribe(Disposable d) {

                }

                @Override
                public void onNext(Boolean aBoolean) {
                    if (aBoolean) {
                        mMediaStoreCompat = new MediaStoreCompat(ConfirmActivity.this);
                        mMediaStoreCompat.dispatchCaptureIntent(ConfirmActivity.this, REQUEST_CODE_CAPTURE);
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
    }

    public void scan() {
        Intent intent = new Intent(this, SimpleCaptureActivity.class);
        startActivityForResult(intent, REQUEST_CODE_SCAN);
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
                    Matisse.from(ConfirmActivity.this)
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
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE_CAPTURE && resultCode == RESULT_OK) {
            Uri contentUri = mMediaStoreCompat.getCurrentPhotoUri();
            String path = mMediaStoreCompat.getCurrentPhotoPath();
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
            intent.setClass(ConfirmActivity.this, FillUpActivity.class);
            intent.putParcelableArrayListExtra("images", arrayList);
            startActivityForResult(intent, REQUEST_CODE_AMOUNT);

            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
                ConfirmActivity.this.revokeUriPermission(contentUri,
                        Intent.FLAG_GRANT_WRITE_URI_PERMISSION | Intent.FLAG_GRANT_READ_URI_PERMISSION);
            }
        } else if (REQUEST_CODE_IMAGE_CLICK == requestCode) {
            switch (resultCode) {
                case RESULT_CODE_BACK:
                    Bundle bundleExtra = data.getBundleExtra(EXTRA_BUNDLE);
                    ArrayList<Image> images = bundleExtra.getParcelableArrayList(EXTRA_ALL_DATA);
//                    UploadReceiptAdapter uploadReceiptAdapter = (UploadReceiptAdapter) rvUploadReceipt.getAdapter();
//                    uploadReceiptAdapter.refresh(images);
//                    DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(new ReceiptDiff(this.images, images), true);
//                    diffResult.dispatchUpdatesTo(uploadReceiptAdapter);
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
                String path = BitmapUtils.getRealFilePath(ConfirmActivity.this, uri);
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
            intent.setClass(ConfirmActivity.this, FillUpActivity.class);
            startActivityForResult(intent, REQUEST_CODE_AMOUNT);

        } else if (requestCode == REQUEST_CODE_FROM_ELEC && resultCode == RESULT_OK) {
            resultFromElec(data);
        } else if (requestCode == REQUEST_CODE_AMOUNT) {
            if (resultCode == RESULT_OK) {
                ArrayList<Image> arrayList = data.getParcelableArrayListExtra("images");
                mPreviousPosition += arrayList.size();
                images.addAll(arrayList);
//                UploadReceiptAdapter uploadReceiptAdapter = (UploadReceiptAdapter) rvUploadReceipt.getAdapter();
//                uploadReceiptAdapter.notifyItemRangeInserted(mPreviousPosition,arrayList.size());
                goNextPage();
            }
        } else if (requestCode == REQUEST_CODE_SCAN) {
            if (resultCode == Activity.RESULT_OK) {
                String content = data.getStringExtra(DECODED_CONTENT_KEY);
                TLog.log(content);
                if (RegexUtils.isURL(content) || content.contains("http")) {
                    Intent intent = new Intent();
                    intent.putExtra(Constant.COMPANY_INFO, company_info);
                    intent.putExtra(Constant.IS_FROM_UPLOADRECEIPT_ACTIVITY, true);
                    intent.putExtra(Constant.DEMANDS_ID, demandsId);
                    intent.setClass(this, Op.class);
                    intent.putExtra("url", content);
                    startActivity(intent);
                } else {
                    showTipDialog();
                }
            }
        }
    }

    private void showTipDialog() {
        if (mDialog2 == null) {
            mDialog2 = DialogUtil.getInstance().createDialog(this, 0, R.layout.layout_scan_tip, new DialogUtil.OnKnownListener() {
                @Override
                public void onKnown(View view) {
                    mDialog2.dismiss();
                }
            }, null, null);
            View rootView = DialogUtil.getInstance().getRootView();
            if (rootView != null) {
                TextView textView = (TextView) rootView.findViewById(R.id.scan_tip);
                textView.setText(getString(R.string.cannot_parse_this_kind_of_code_from_now_on));
            }
        }
        showDialog(mDialog2);
    }

    private void goNextPage() {
        Intent intent = new Intent(this, UploadReceiptPreviewActivity.class);
        intent.putExtra(FinanceFragment.EXTRA_DATA_LABEL, label);
        intent.putExtra("type", type);
        intent.putExtra("amount", amount);
        intent.putExtra("bonus", bonus);
        intent.putExtra("demandsId", demandsId);
        intent.putExtra("company_id", company_info.getId());
        intent.putExtra("company_info", company_info);
        Bundle bundle = new Bundle();
        if (images.size() <= 1) {
            BaseApplication.showToast(getString(R.string.please_upload_invoice_atleast_one));
            return;

        }
        if (type == 1) {
            bundle.putParcelableArrayList(PAPER_NORMAL_RECEIPT_DATA, images);
        }

        if (type == 2) {
            bundle.putParcelableArrayList(PAPER_SPECIAL_RECEIPT_DATA, images);
        }

        if (type == 3) {
            bundle.putParcelableArrayList(PAPER_ELEC_RECEIPT_DATA, images);
        }

        intent.putExtra(TAG, bundle);
        intent.putExtra(FinanceFragment.EXTRA_DATA_LABEL, label);
        startActivity(intent);
    }

    public void resultFromElec(Intent data) {
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
        intent.setClass(ConfirmActivity.this, FillUpActivity.class);
        intent.putParcelableArrayListExtra("images", arrayList);
        startActivityForResult(intent, REQUEST_CODE_AMOUNT);
    }

    @OnClick({R.id.confirm_back, R.id.filter, R.id.collect, R.id.qr, R.id.look_directly, R.id.translate, R.id.company_name, R.id.tex_number, R.id.company_address, R.id.number, R.id.bank, R.id.bank_account, R.id.translate_details, R.id.upload_receipt})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.confirm_back:
                this.finish();
                break;
            case R.id.filter:
                break;
            case R.id.collect:
                if (!ButtonUtils.isFastDoubleClick(R.id.collect)) {

                                if (isCollected) {
                                    //删除收藏公司id为 表主键
                                    Api.deleteFavoriteCompany(favoriteId, AccountHelper.getToken(), new Api.BaseRawResponse<FavBean>() {
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

                                        }

                                        @Override
                                        public void onTokenInvalid() {
                                            login();
                                        }

                                        @Override
                                        public void setData(FavBean normalBean) {
                                            if (normalBean.getStatus() == 200) {
                                                isCollected = false;
                                                collect.setImageResource(R.mipmap.collect);
                                            }
                                        }
                                    });
                                } else {
                                    CompanyCollectBean companyCollectBean = new CompanyCollectBean();
                                    CompanyCollectBean.CompanyBean companyBean = new CompanyCollectBean.CompanyBean();
                                    companyBean.setId(company_info.getId());
                                    companyCollectBean.setCompany(companyBean);
                                    companyCollectBean.setToken(AccountHelper.getToken());

                                    Api.favCompanyCreate(companyCollectBean, new Api.BaseViewCallbackWithOnStart<FavBean>() {
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

                                        }

                                        @Override
                                        public void setData(FavBean normalBean) {
                                            if (normalBean.getStatus() == 200) {
                                                BaseApplication.showToast("收藏成功");
                                                isCollected = true;
                                                favoriteId = normalBean.getFavoriteId();
                                                collect.setImageResource(R.mipmap.collected);
                                            }
                                        }
                                    });
                                }
                }
                break;
            case R.id.qr:
                break;
            case R.id.look_directly:
                translate.setVisibility(View.GONE);
                translateDetails.setVisibility(View.VISIBLE);
                break;
            case R.id.translate:
                break;
            case R.id.company_name:
                break;
            case R.id.tex_number:
                break;
            case R.id.company_address:
                break;
            case R.id.number:
                break;
            case R.id.bank:
                break;
            case R.id.bank_account:
                break;
            case R.id.translate_details:
                translate.setVisibility(View.VISIBLE);
                translateDetails.setVisibility(View.GONE);
                break;
            case R.id.upload_receipt:
                if (type == 3) {
                    accquireReceiptFolderData();
                } else {
                    showDialog();
                }

//                Intent intent = new Intent();
//                intent.putExtra("type", type);
//                intent.putExtra("amount", amount);
//                intent.putExtra("bonus", bonus);
//                intent.putExtra("demandsId", demandsId);
//                intent.putExtra("company_id", company_info.getId());
//                intent.putExtra(Constant.COMPANY_INFO, company_info);
//                intent.putExtra(FinanceFragment.EXTRA_DATA_LABEL, label);
//                intent.setClass(this, UploadReceiptActivity.class);
//                startActivity(intent);
                break;
            default:
        }
    }

    private void accquireReceiptFolderData() {
        Api.myInvoiceList(AccountHelper.getToken(), this, new Api.BaseRawResponse<MyInvoiceListBean>() {
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

}
