package com.pilipa.fapiaobao.ui.receipt_folder_image_select;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;
import com.example.mylibrary.utils.TLog;
import com.pilipa.fapiaobao.R;
import com.pilipa.fapiaobao.account.AccountHelper;
import com.pilipa.fapiaobao.base.BaseActivity;
import com.pilipa.fapiaobao.base.BaseNoNetworkActivity;
import com.pilipa.fapiaobao.net.Api;
import com.pilipa.fapiaobao.net.bean.me.MyInvoiceListBean;
import com.pilipa.fapiaobao.ui.deco.GridInset;
import com.pilipa.fapiaobao.ui.fragment.ProgressDialogFragment;
import com.pilipa.fapiaobao.ui.model.Image;
import com.pilipa.fapiaobao.ui.receipt_folder_image_select.adapter.NormalAdpater;
import com.pilipa.fapiaobao.utils.ReceiptDiff;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author dagou
 * @date 2017/10/17
 */

public class ReceiptActivityToken extends BaseNoNetworkActivity implements NormalAdpater.OnImageSelectListener, NormalAdpater.OnImageClickListener {
    private static final String TAG = "ReceiptActivityToken";

    public static final int REQUEST_CODE = 1000;
    //    public static final int RESULT_CODE_OK = 2000;
    public static final int RESULT_CODE_BACK = 2000;
    public static final String RESULT_RECEIPT_FOLDER = "result_receipt_folder";
    public static final String EXTRA_DATA_FROM_TOKEN = "extra_data_from_token";
    @Bind(R.id.title)
    TextView title;
    @Bind(R.id.demand_back)
    ImageView demandBack;
    @Bind(R.id.iv_demand_share)
    ImageView ivDemandShare;
    @Bind(R.id.recyclerview)
    RecyclerView recyclerview;
    @Bind(R.id.confirm)
    Button confirm;
    @Bind(R.id.ll_elec_receipt)
    LinearLayout llElecReceipt;
    @Bind(R.id.iv_bg)
    ImageView ivBg;
    @Bind(R.id.tv_line1)
    TextView tvLine1;
    @Bind(R.id.rl_no_elec_receipt)
    RelativeLayout rlNoElecReceipt;
    @Bind(R.id.animation_view)
    LottieAnimationView animationView;
    @Bind(R.id.rl_loading)
    RelativeLayout rlLoading;
//    public static final int RESULT_CODE_DELETE = 2000;


    private int mImageResize;
    private ArrayList<Image> arrayList;
    private ArrayList<Image> images;
    private ProgressDialogFragment progressDialogFragment;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_myreceipt_token;
    }

      @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
    }

    @Override
    public void onNoNetworkLayoutClicks(View view) {
        myInvoiceList();
    }

    private void myInvoiceList() {
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
                showNetWorkErrorLayout();
            }

            @Override
            public void onTokenInvalid() {
                login();
            }

            @Override
            public void setData(MyInvoiceListBean myInvoiceListBean) {
                hideNetWorkErrorLayout();
                if (myInvoiceListBean.getStatus() == 200) {
                    llElecReceipt.setVisibility(View.VISIBLE);
                    rlNoElecReceipt.setVisibility(View.GONE);
                    List<MyInvoiceListBean.DataBean> list = myInvoiceListBean.getData();
                    if (list != null && list.size() > 0) {
                        setUpData(list);
                        Log.d(TAG, "updateData:myInvoiceList success");
                    }
                } else if (myInvoiceListBean.getStatus() == 400) {
                    llElecReceipt.setVisibility(View.GONE);
                    rlNoElecReceipt.setVisibility(View.VISIBLE);
                }
            }
        });
    }

    private int getImageResize(Context context) {
        if (mImageResize == 0) {
            RecyclerView.LayoutManager lm = recyclerview.getLayoutManager();
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

    private void setUpData(List<MyInvoiceListBean.DataBean> body) {
        Log.d(TAG, "setUpData:   private void setUpData(ArrayList<model.ResultsBean> body) {");
        images = new ArrayList<>();
        for (MyInvoiceListBean.DataBean result : body) {
            Log.d(TAG, "setUpData:  for (model.ResultsBean result : body) {");
            Image image = new Image();
            image.isFromNet = true;
            image.isSelected = false;
            image.isCapture = false;
            image.name = result.getId();
            image.path = result.getUrl();
            image.position = -1;
            image.id = result.getId();
            images.add(image);
        }
        Log.d(TAG, "setUpData: images.size" + images.size());
        NormalAdpater normal = new NormalAdpater(images, getImageResize(this));
        Log.d(TAG, "setUpData:  normal = new NormalAdpater(images, getImageResize(this));");
        normal.setOnImageSelectListener(this);
        normal.setOnImageClickListener(this);
        recyclerview.setAdapter(normal);
    }

    @OnClick(R.id.confirm)
    public void onViewClicked() {
        if (arrayList != null && arrayList.size() != 0) {
            Log.d(TAG, "onViewClicked: " + arrayList.get(0).toString());
            Intent intent = new Intent();
            Bundle bundle = new Bundle();
            bundle.putParcelableArrayList(RESULT_RECEIPT_FOLDER, arrayList);
            intent.putExtra(EXTRA_DATA_FROM_TOKEN, bundle);
            setResult(RESULT_OK, intent);
            finish();
        }
    }

    @Override
    public void onImageSelect(Image image) {
        Log.d(TAG, "onImageSelect: ");
        if (arrayList != null) {
            Log.d(TAG, "onImageSelect:arrayList != null ");
            if (image.isSelected) {
                Log.d(TAG, "onImageSelect: add image.position" + image.position);
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
    public void onImageClick(ArrayList<Image> allItemList, int position) {
        Image image = allItemList.get(position);
        Intent intent = new Intent(this, PreviewActivity.class);
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList(NormalAdpater.EXTRA_ALL_DATA, allItemList);
        bundle.putInt(NormalAdpater.EXTRA_CURRENT_POSITION, image.position);
        intent.putExtra(NormalAdpater.EXTRA_BUNDLE, bundle);
        startActivityForResult(intent, REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE) {
            switch (resultCode) {
                case RESULT_CODE_BACK:
                    TLog.d(TAG, "onActivityResult: previewactivity back to myreceiptactivity");
                    Bundle bundleExtra = data.getBundleExtra(NormalAdpater.EXTRA_BUNDLE);
                    ArrayList<Image> images = bundleExtra.getParcelableArrayList(NormalAdpater.EXTRA_ALL_DATA);
                    TLog.d(TAG, "onActivityResult:images.get(0).position; " + images.get(0).position);
                    TLog.d(TAG, "onActivityResult:images.get(0).isselected; " + images.get(0).isSelected);
                    NormalAdpater normalAdpater = (NormalAdpater) recyclerview.getAdapter();
                    normalAdpater.refresh(images);
                    DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(new ReceiptDiff(this.images, images), true);
                    diffResult.dispatchUpdatesTo(normalAdpater);
                    break;

                default:
                    break;
            }
        }
    }

    @OnClick({R.id.demand_back, R.id.iv_demand_share})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.demand_back:
                finish();
                break;
            case R.id.iv_demand_share:
                break;
        }
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void initView() {
        arrayList = new ArrayList<>();
        GridLayoutManager grid = new GridLayoutManager(this, 3);
        recyclerview.setLayoutManager(grid);
        int spacing = getResources().getDimensionPixelOffset(R.dimen.spacing);
        recyclerview.addItemDecoration(new GridInset(3, spacing, false));
        llElecReceipt.setVisibility(View.GONE);
        rlNoElecReceipt.setVisibility(View.GONE);
    }

    @Override
    public void initData() {
        myInvoiceList();
    }

    @Override
    public void initDataInResume() {

    }
}
