package com.pilipa.fapiaobao.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.pilipa.fapiaobao.R;
import com.pilipa.fapiaobao.base.BaseActivity;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by edz on 2017/10/25.
 */

public class PubActivity extends BaseActivity {

    @Bind(R.id.select_receipt_elec)
    ImageView selectReceiptElec;
    @Bind(R.id.receipt_elec)
    FrameLayout receiptElec;
    @Bind(R.id.select_receipt_paper_normal)
    ImageView selectReceiptPaperNormal;
    @Bind(R.id.receipt_paper_normal)
    FrameLayout receiptPaperNormal;
    @Bind(R.id.select_receipt_paper_special)
    ImageView selectReceiptPaperSpecial;
    @Bind(R.id.receipt_paper_special)
    FrameLayout receiptPaperSpecial;

    public static void show(Context context) {
        context.startActivity(new Intent(context, PubActivity.class));
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_publish;
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void initView() {

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

    @OnClick({R.id.select_receipt_elec, R.id.receipt_elec, R.id.select_receipt_paper_normal, R.id.receipt_paper_normal, R.id.select_receipt_paper_special, R.id.receipt_paper_special})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.receipt_elec:
                receiptElec.setSelected(!receiptElec.isSelected());
                updateSelection();
                break;
            case R.id.receipt_paper_normal:
                receiptPaperNormal.setSelected(!receiptPaperNormal.isSelected());
                updateSelection();
                break;
            case R.id.receipt_paper_special:
                receiptPaperSpecial.setSelected(!receiptPaperSpecial.isSelected());
                updateSelection();
                break;
        }
    }

    private void updateSelection() {
        selectReceiptElec.setVisibility(receiptElec.isSelected()?View.VISIBLE:View.INVISIBLE);
        selectReceiptPaperNormal.setVisibility(receiptPaperNormal.isSelected()?View.VISIBLE:View.INVISIBLE);
        selectReceiptPaperSpecial.setVisibility(receiptPaperSpecial.isSelected()?View.VISIBLE:View.INVISIBLE);

    }
}
