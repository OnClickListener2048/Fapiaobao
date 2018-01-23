package com.pilipa.fapiaobao.ui.receipt_folder_image_select;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.pilipa.fapiaobao.R;
import com.pilipa.fapiaobao.adapter.supply.PreviewPagerAdapter;
import com.pilipa.fapiaobao.ui.fragment.PreviewImageFragment;
import com.pilipa.fapiaobao.ui.model.Image;
import com.pilipa.fapiaobao.ui.receipt_folder_image_select.adapter.NormalAdpater;
import com.pilipa.fapiaobao.ui.widget.PreviewViewpager;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by edz on 2017/10/18.
 */

public class PreviewActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener, CompoundButton.OnCheckedChangeListener {

    private static final String TAG = "PreviewActivity";
    protected int mPreviousPos = -1;
    @Bind(R.id.preview_viewpager)
    PreviewViewpager previewViewpager;
    @Bind(R.id.back)
    TextView back;
    @Bind(R.id.click)
    CheckBox click;
    @Bind(R.id.delete)
    TextView delete;
    private ArrayList<Image> allList;
    private int currentPosition;
    private PreviewPagerAdapter previewPagerAdapter;
    private ArrayList<PreviewImageFragment> FragmentList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receipt_folder_preview);
        ButterKnife.bind(this);
        click.setVisibility(View.GONE);
        Bundle bundleExtra = getIntent().getBundleExtra(NormalAdpater.EXTRA_BUNDLE);
        allList = bundleExtra.getParcelableArrayList(NormalAdpater.EXTRA_ALL_DATA);
        FragmentList = new ArrayList<>();
        for (Image image : allList) {
            Log.d(TAG, "onCreate: " + image.position);
            FragmentList.add(PreviewImageFragment.newInstance(image));
        }
        currentPosition = bundleExtra.getInt(NormalAdpater.EXTRA_CURRENT_POSITION);
        mPreviousPos = currentPosition;
//        if (allList.get(currentPosition).isSelected) {
//            click.setText("已选中");
//        } else {
//            click.setText("未选中");
//        }
        click.setChecked(allList.get(currentPosition).isSelected);
        delete.setVisibility(View.GONE);
        previewPagerAdapter = new PreviewPagerAdapter(getSupportFragmentManager(), FragmentList);
        previewViewpager.setAdapter(previewPagerAdapter);
        previewViewpager.setCurrentItem(currentPosition);
        previewViewpager.setOnPageChangeListener(this);
        click.setOnCheckedChangeListener(this);
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
//        if (allList.get(position).isSelected) {
//            click.setText("已选中");
//        } else {
//            click.setText("未选中");
//        }
        click.setChecked(allList.get(position).isSelected);

        Log.d(TAG, "onPageSelected: mPreviousPos" + mPreviousPos);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @OnClick({R.id.back, R.id.click, R.id.delete})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back:
                Intent intent = new Intent();
                Bundle bundle = new Bundle();
                bundle.putParcelableArrayList(NormalAdpater.EXTRA_ALL_DATA, allList);

                intent.putExtra(NormalAdpater.EXTRA_BUNDLE, bundle);
                setResult(ReceiptActivityToken.RESULT_CODE_BACK, intent);
                Log.d(TAG, "onViewClicked: allList.get(mPreviousPos).position" + allList.get(mPreviousPos).position);
                Log.d(TAG, "onViewClicked: allList.get(mPreviousPos).isSelected" + allList.get(mPreviousPos).isSelected);
                finish();
                break;
//            case R.id.click:
//                if (click.getText().equals("已选中")) {
//                    click.setText("未选中");
//                    allList.get(mPreviousPos).isSelected = false;
//                } else if (click.getText().equals("未选中")) {
//                    click.setText("已选中");
//                    allList.get(mPreviousPos).isSelected = true;
//                }
//
//                Log.d(TAG, "onViewClicked:    allList.get(mPreviousPos).isSelected ----" + allList.get(mPreviousPos).isSelected);
//                break;
            case R.id.delete:
                Log.d(TAG, "onViewClicked: mPreviousPos" + mPreviousPos);
                allList.remove(mPreviousPos);
                previewPagerAdapter.remove(mPreviousPos);
                if (allList.size() == 0 && previewPagerAdapter.arrayList.size() == 0) {
                    Intent intent2 = new Intent();
                    Bundle bundle2 = new Bundle();

                    bundle2.putParcelableArrayList(NormalAdpater.EXTRA_ALL_DATA, allList);
                    intent2.putExtra(NormalAdpater.EXTRA_BUNDLE, bundle2);
                    setResult(ReceiptActivityToken.RESULT_CODE_BACK, intent2);
                    finish();
                }

                break;
            default:
                break;
        }
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        Log.d(TAG, "onCheckedChanged: allList.get(mPreviousPos)" + mPreviousPos);
        Log.d(TAG, "onCheckedChanged:  allList.get(mPreviousPos).isSelected = isChecked;" + isChecked);
        Log.d(TAG, "onCheckedChanged:  allList.get(mPreviousPos).position" + allList.get(mPreviousPos).position);
        allList.get(mPreviousPos).isSelected = isChecked;
    }
}
