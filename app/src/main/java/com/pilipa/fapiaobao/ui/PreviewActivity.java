package com.pilipa.fapiaobao.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.pilipa.fapiaobao.R;
import com.pilipa.fapiaobao.adapter.PreviewPagerAdapter;
import com.pilipa.fapiaobao.base.BaseActivity;
import com.pilipa.fapiaobao.ui.fragment.PreviewImageFragment;
import com.pilipa.fapiaobao.ui.fragment.UploadNormalReceiptFragment;
import com.pilipa.fapiaobao.ui.model.Image;
import com.pilipa.fapiaobao.ui.widget.PreviewViewpager;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by edz on 2017/10/20.
 */

public class PreviewActivity extends BaseActivity implements ViewPager.OnPageChangeListener {
    private static final String TAG = "PreviewActivity";
    @Bind(R.id.preview_viewpager)
    PreviewViewpager previewViewpager;
    @Bind(R.id.delete)
    TextView delete;
    @Bind(R.id.back)
    TextView back;
    @Bind(R.id.click)
    TextView click;
    private ArrayList<Image> allList;
    private int currentPosition;
    protected int mPreviousPos = -1;
    private PreviewPagerAdapter previewPagerAdapter;
    private ArrayList<PreviewImageFragment> FragmentList;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_myreceipt_preview;
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void initView() {
        Bundle bundleExtra = getIntent().getBundleExtra(UploadReceiptActivity.EXTRA_BUNDLE);
        allList = bundleExtra.getParcelableArrayList(UploadReceiptActivity.EXTRA_ALL_DATA);
        FragmentList  = new ArrayList<>();
        for (Image image : allList) {
            if (!image.isCapture) {
                FragmentList.add(PreviewImageFragment.newInstance(image));
            }
        }
        currentPosition = bundleExtra.getInt(UploadReceiptActivity.EXTRA_CURRENT_POSITION);
        Log.d(TAG, "initView: currentPosition"+currentPosition);
        mPreviousPos = currentPosition-1;

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

    @OnClick({R.id.delete, R.id.back, R.id.click})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.delete:
                Log.d(TAG, "onViewClicked: mPreviousPos" + mPreviousPos);
                allList.remove(mPreviousPos+1);
                previewPagerAdapter.remove(mPreviousPos);
                Log.d(TAG, "onViewClicked: allList.size()========="+allList.size());
                Log.d(TAG, "onViewClicked: previewPagerAdapter.arrayList.size()========="+previewPagerAdapter.arrayList.size());
                if (allList.size() == 0 || previewPagerAdapter.arrayList.size() == 0) {
                    Intent intent2 = new Intent();
                    Bundle bundle2 = new Bundle();
                    bundle2.putParcelableArrayList(UploadReceiptActivity.EXTRA_ALL_DATA, allList);
                    intent2.putExtra(UploadReceiptActivity.EXTRA_BUNDLE, bundle2);
                    setResult(UploadNormalReceiptFragment.RESULT_CODE_BACK, intent2);
                    finish();
                }
                break;
            case R.id.back:
                Intent intent = new Intent();
                Bundle bundle = new Bundle();
                bundle.putParcelableArrayList(UploadReceiptActivity.EXTRA_ALL_DATA, allList);
                intent.putExtra(UploadReceiptActivity.EXTRA_BUNDLE, bundle);
                setResult(UploadNormalReceiptFragment.RESULT_CODE_BACK, intent);
                finish();
                break;
            case R.id.click:
                break;
            default:
        }
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
