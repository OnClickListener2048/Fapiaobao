package com.pilipa.fapiaobao.ui.receipt_folder_image_select;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.pilipa.fapiaobao.R;
import com.pilipa.fapiaobao.account.AccountHelper;
import com.pilipa.fapiaobao.base.BaseApplication;
import com.pilipa.fapiaobao.net.Api;
import com.pilipa.fapiaobao.net.bean.me.MyInvoiceListBean;
import com.pilipa.fapiaobao.ui.deco.GridInset;
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

public class ReceiptActivityToken extends AppCompatActivity implements NormalAdpater.OnImageSelectListener, NormalAdpater.OnImageClickListener {
    private static final String TAG = "ReceiptActivityToken";

    public static final int REQUEST_CODE = 1000;
    //    public static final int RESULT_CODE_OK = 2000;
    public static final int RESULT_CODE_BACK = 2000;
    public static final String RESULT_RECEIPT_FOLDER = "result_receipt_folder";
    public static final String EXTRA_DATA_FROM_TOKEN = "extra_data_from_token";
//    public static final int RESULT_CODE_DELETE = 2000;

    @Bind(R.id.confirm)
    Button confirm;
    @Bind(R.id.recyclerview)
    RecyclerView recyclerview;
    @Bind(R.id.title)
    TextView title;
    @Bind(R.id.demand_back)
    ImageView demandBack;
    @Bind(R.id.iv_demand_share)
    ImageView ivDemandShare;
    private int mImageResize;
    private ArrayList<Image> arrayList;
    private NormalAdpater normal;
    private ArrayList<Image> images;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myreceipt_token);
        ButterKnife.bind(this);
        arrayList = new ArrayList<>();
        GridLayoutManager grid = new GridLayoutManager(this, 3);
        recyclerview.setLayoutManager(grid);
        int spacing = getResources().getDimensionPixelOffset(R.dimen.spacing);
        recyclerview.addItemDecoration(new GridInset(3, spacing, false));


        myInvoiceList();

    }

    private void myInvoiceList() {
        if (AccountHelper.getToken() != null && AccountHelper.getToken() != "") {
            Api.myInvoiceList(AccountHelper.getToken(), new Api.BaseViewCallback<MyInvoiceListBean>() {
                @Override
                public void setData(MyInvoiceListBean myInvoiceListBean) {
                    List<MyInvoiceListBean.DataBean> list = myInvoiceListBean.getData();
                    setUpData(list);
                    Log.d(TAG, "updateData:myInvoiceList success");
                }
            });
        } else {
            BaseApplication.showToast("登录超期");
        }
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
        normal = new NormalAdpater(images, getImageResize(this));
        Log.d(TAG, "setUpData:  normal = new NormalAdpater(images, getImageResize(this));");
        normal.setOnImageSelectListener(this);
        normal.setOnImageClickListener(this);
        recyclerview.setAdapter(normal);
    }

    @OnClick(R.id.confirm)
    public void onViewClicked() {
        if (arrayList != null && arrayList.size() != 0) {
            Log.d(TAG, "onViewClicked: " + arrayList.get(0).toString());
            Toast.makeText(this, arrayList.get(0).toString(), Toast.LENGTH_SHORT).show();
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
                    Log.d(TAG, "onActivityResult: previewactivity back to myreceiptactivity");
                    Bundle bundleExtra = data.getBundleExtra(NormalAdpater.EXTRA_BUNDLE);
                    ArrayList<Image> images = bundleExtra.getParcelableArrayList(NormalAdpater.EXTRA_ALL_DATA);
                    Log.d(TAG, "onActivityResult:images.get(0).position; " + images.get(0).position);
                    Log.d(TAG, "onActivityResult:images.get(0).isselected; " + images.get(0).isSelected);
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
}
