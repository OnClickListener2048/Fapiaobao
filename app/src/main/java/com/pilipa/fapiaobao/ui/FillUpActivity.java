package com.pilipa.fapiaobao.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.mylibrary.utils.TLog;
import com.pilipa.fapiaobao.R;
import com.pilipa.fapiaobao.adapter.PreviewFillupAdapter;
import com.pilipa.fapiaobao.adapter.PreviewPagerAdapter;
import com.pilipa.fapiaobao.base.BaseActivity;
import com.pilipa.fapiaobao.base.BaseApplication;
import com.pilipa.fapiaobao.ui.fragment.PreviewFillupFragment;
import com.pilipa.fapiaobao.ui.fragment.PreviewImageFragment;
import com.pilipa.fapiaobao.ui.model.Image;
import com.pilipa.fapiaobao.ui.widget.NoScrollViewpager;
import com.pilipa.fapiaobao.ui.widget.PreviewViewpager;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by edz on 2017/11/5.
 */

public class FillUpActivity extends BaseActivity implements ViewPager.OnPageChangeListener {

    @Bind(R.id.preview_viewpager)
    NoScrollViewpager previewViewpager;
    @Bind(R.id.sum_piece)
    TextView sumPiece;
    @Bind(R.id.sum_piece_per)
    TextView sumPiecePer;
    @Bind(R.id.btn_confirm)
    Button btnConfirm;
    @Bind(R.id.et_fillup)
    EditText etFillup;
    private ArrayList<Image> images;
    private ArrayList<PreviewFillupFragment> FragmentList;
    private PreviewFillupAdapter previewPagerAdapter;
    protected int mPreviousPos = 0;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_fillup;
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void initView() {
        images = getIntent().getParcelableArrayListExtra("images");

        FragmentList = new ArrayList<>();
        for (Image image : images) {
            if (!image.isCapture) {
                FragmentList.add(PreviewFillupFragment.newInstance(image));
            }
        }

        previewPagerAdapter = new PreviewFillupAdapter(getSupportFragmentManager(), FragmentList);
        previewViewpager.setAdapter(previewPagerAdapter);
        previewViewpager.setOnPageChangeListener(this);
        previewViewpager.setCurrentItem(0);
        previewViewpager.setNoScroll(true);
        previewViewpager.setHorizontalScrollBarEnabled(false);
        sumPiecePer.setText("1");
        sumPiece.setText(String.valueOf(images.size()));
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

    @OnClick(R.id.btn_confirm)
    public void onViewClicked() {
        if (!TextUtils.isEmpty(etFillup.getText())) {
            if (Double.valueOf(etFillup.getText().toString().trim()) > 0) {
                images.get(mPreviousPos).amount = etFillup.getText().toString().trim();
                TLog.log("mPreviousPos"+mPreviousPos);
                TLog.log("images.size()"+images.size());
                if (mPreviousPos <= images.size()) {
                    previewViewpager.setCurrentItem(mPreviousPos + 1);
                    if (mPreviousPos+1 == images.size()&&!TextUtils.isEmpty(etFillup.getText())) {
                        Intent intent = new Intent();
                        intent.putParcelableArrayListExtra("images", images);
                        setResult(RESULT_OK, intent);
                        finish();
                    }
                }
            } else {
                BaseApplication.showToast("请输入正确的票面金额");
            }
        } else {
            BaseApplication.showToast("请输入票面金额");
        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        PreviewFillupAdapter adapter = (PreviewFillupAdapter) previewViewpager.getAdapter();
        if (mPreviousPos != -1 && mPreviousPos != position) {
            ((PreviewFillupFragment) adapter.instantiateItem(previewViewpager, mPreviousPos)).resetView();
        }
        mPreviousPos = position;
        etFillup.setText(images.get(position).amount);
        sumPiecePer.setText(String.valueOf(mPreviousPos+1));
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
