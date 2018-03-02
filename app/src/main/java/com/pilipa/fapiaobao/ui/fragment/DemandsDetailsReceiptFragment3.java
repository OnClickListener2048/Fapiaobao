package com.pilipa.fapiaobao.ui.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mylibrary.utils.TLog;
import com.pilipa.fapiaobao.R;
import com.pilipa.fapiaobao.adapter.supply.DemandsDetailsReceiptAdapter;
import com.pilipa.fapiaobao.base.BaseFragment;
import com.pilipa.fapiaobao.ui.DemandsDetailsPreviewActivity;
import com.pilipa.fapiaobao.ui.UploadReceiptPreviewActivity;
import com.pilipa.fapiaobao.ui.deco.GridInset;
import com.pilipa.fapiaobao.ui.model.Image;
import com.pilipa.fapiaobao.utils.ReceiptDiff;

import java.lang.reflect.Field;
import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

import static android.app.Activity.RESULT_OK;


/**
 * Created by edz on 2017/10/21.
 */

public class DemandsDetailsReceiptFragment3 extends BaseFragment implements
        DemandsDetailsReceiptAdapter.OnImageSelectListener, DemandsDetailsReceiptAdapter.OnImageClickListener, DemandsDetailsReceiptAdapter.OnPhotoCapture {


    public static final int REQUEST_CODE_CAPTURE = 10;
    public static final int REQUEST_CODE_CHOOSE = 20;
    public static final String EXTRA_ALL_DATA = "EXTRA_ALL_DATA";
    public static final String EXTRA_CURRENT_POSITION = "EXTRA_CURRENT_POSITION";
    public static final String EXTRA_BUNDLE = "EXTRA_BUNDLE";
    public static final int REQUEST_CODE_IMAGE_CLICK = 30;
    public static final int RESULT_CODE_BACK = 40;
    public static final String IS_SHOW_SELECT_AND_DELETE = "is_show_select_and_delete";
    public static final String IS_FROM_DEMANDS = "is_from_demands";
    private static final String TAG = "DemandsDetailsReceiptFragment3";
    @Bind(R.id.rv_upload_receipt)
    RecyclerView rvUploadReceipt;
    private int mImageResize;
    private ArrayList<Image> images;
    private int mPreviousPosition = -1;
    private DemandsDetailsReceiptAdapter uploadReceiptAdapter;

    public static DemandsDetailsReceiptFragment3 newInstance(Bundle b) {
        DemandsDetailsReceiptFragment3 u = new DemandsDetailsReceiptFragment3();
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
        ArrayList<Image> arrayList;
        if (images == null) {
            images = new ArrayList<>();
        }
        arrayList = arguments.getParcelableArrayList(UploadReceiptPreviewActivity.PAPER_ELEC_RECEIPT_DATA);
        Log.d(TAG, "onCreateView: ");
        if (arrayList != null) {
            for (Image image : arrayList) {
                if (!image.isCapture) {
                    images.add(image);
                }
            }
        }
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);

        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
        try {
            Field childFragmentManager = Fragment.class.getDeclaredField("mChildFragmentManager");
            childFragmentManager.setAccessible(true);
            childFragmentManager.set(this, null);
        } catch (NoSuchFieldException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    protected void initWidget(View root) {
        super.initWidget(root);
        GridLayoutManager grid = new GridLayoutManager(getActivity(), 3) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };
        rvUploadReceipt.setLayoutManager(grid);
        int spacing = getResources().getDimensionPixelOffset(R.dimen.spacing);
        rvUploadReceipt.addItemDecoration(new GridInset(3, spacing, false));
        mPreviousPosition = images.size();
        uploadReceiptAdapter = new DemandsDetailsReceiptAdapter(images, getImageResize(getActivity()));
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
        Intent intent = new Intent(mContext, DemandsDetailsPreviewActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("activity", getArguments().getString("activity"));
        bundle.putBoolean(IS_SHOW_SELECT_AND_DELETE,false);
        bundle.putParcelableArrayList(EXTRA_ALL_DATA, allItemList);
        bundle.putInt(EXTRA_CURRENT_POSITION, image.position);
        bundle.putBoolean(IS_FROM_DEMANDS,true);
        intent.putExtra(EXTRA_BUNDLE, bundle);
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
        TLog.log(TAG+"onActivityResult3");
        super.onActivityResult(requestCode, resultCode, data);
        TLog.log(TAG+"onActivityResult4");

        if (requestCode == REQUEST_CODE_CAPTURE && resultCode == RESULT_OK) {

        } else if (REQUEST_CODE_IMAGE_CLICK == requestCode) {
            switch (resultCode) {
                case RESULT_CODE_BACK:
                    Bundle bundleExtra = data.getBundleExtra(EXTRA_BUNDLE);
                    ArrayList<Image> images = bundleExtra.getParcelableArrayList(EXTRA_ALL_DATA);
                    uploadReceiptAdapter = (DemandsDetailsReceiptAdapter) rvUploadReceipt.getAdapter();
                    uploadReceiptAdapter.refresh(images);
                    DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(new ReceiptDiff(this.images, images), true);
                    diffResult.dispatchUpdatesTo(uploadReceiptAdapter);
                    this.images = images;
                    mPreviousPosition = images.size();
                    break;
                default:
            }
        } else if (requestCode == REQUEST_CODE_CHOOSE && resultCode == RESULT_OK) {

        }

    }

}
