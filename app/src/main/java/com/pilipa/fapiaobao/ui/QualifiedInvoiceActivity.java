package com.pilipa.fapiaobao.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.pilipa.fapiaobao.R;
import com.pilipa.fapiaobao.base.BaseActivity;
import com.pilipa.fapiaobao.ui.fragment.DemandsDetailsReceiptFragment;
import com.pilipa.fapiaobao.ui.fragment.DemandsDetailsReceiptFragment2;
import com.pilipa.fapiaobao.ui.fragment.DemandsDetailsReceiptFragment3;
import com.pilipa.fapiaobao.ui.model.Image;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.pilipa.fapiaobao.net.Constant.VARIETY_GENERAL_ELECTRON;
import static com.pilipa.fapiaobao.net.Constant.VARIETY_GENERAL_PAPER;
import static com.pilipa.fapiaobao.net.Constant.VARIETY_SPECIAL_PAPER;

/**
 * Created by edz on 2017/10/24.
 */

public class QualifiedInvoiceActivity extends BaseActivity {
    private static final String TAG = "QualifiedInvoiceActivity";
    @Bind(R.id.tv_num_1)
    TextView tv_num_1;
    @Bind(R.id.tv_num_2)
    TextView tv_num_2;
    @Bind(R.id.tv_num_3)
    TextView tv_num_3;
    @Bind(R.id.ll_receiptlist)
    LinearLayout ll_receiptlist;
    private DemandsDetailsReceiptFragment paperNormalReceiptFragment;
    private DemandsDetailsReceiptFragment2 paperSpecialReceiptFragment;
    private DemandsDetailsReceiptFragment3 paperElecReceiptFragment;
    ArrayList<Image> images_qualified = new ArrayList<>();
    public static final String PAPER_NORMAL_RECEIPT_DATA = "paper_normal_receipt_data";
    public static final String PAPER_SPECIAL_RECEIPT_DATA = "paper_special_receipt_data";
    public static final String PAPER_ELEC_RECEIPT_DATA = "paper_elec_receipt_data";

    @Override
    protected int getLayoutId() {
        return R.layout.activity_qualified_invoice;
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void initView() {
    }

    @Override
    public void initData() {
        images_qualified =  getIntent().getParcelableArrayListExtra("images_qualified");
        setUpData(images_qualified);
    }
    private void setUpData(List<Image> results) {
            ll_receiptlist.setVisibility(View.VISIBLE);
            ArrayList<Image> images1 = new ArrayList<>();
            ArrayList<Image> images2 = new ArrayList<>();
            ArrayList<Image> images3 = new ArrayList<>();
            for (int i = 0; i < results.size(); i++) {
                if (VARIETY_GENERAL_PAPER.equals(results.get(i).variety)) {
                    images1.add(results.get(i));
                } else if (VARIETY_SPECIAL_PAPER.equals(results.get(i).variety)) {
                    images2.add(results.get(i));
                } else if (VARIETY_GENERAL_ELECTRON.equals(results.get(i).variety)) {
                    images3.add(results.get(i));
                }
            }
        tv_num_1.setText(String.format(getResources().getString(R.string.paper_normal_receipt_num), images1.size()));
        tv_num_2.setText(String.format(getResources().getString(R.string.paper_special_receipt_num), images2.size()));
        tv_num_3.setText(String.format(getResources().getString(R.string.paper_elec_receipt_num), images3.size()));
            Bundle bundle = new Bundle();
            bundle.putParcelableArrayList(PAPER_NORMAL_RECEIPT_DATA, images1);
            paperNormalReceiptFragment = DemandsDetailsReceiptFragment.newInstance(bundle);
            addCaptureFragment(R.id.container_paper_normal_receipt, paperNormalReceiptFragment);

            bundle.putParcelableArrayList(PAPER_SPECIAL_RECEIPT_DATA, images2);
            paperSpecialReceiptFragment = DemandsDetailsReceiptFragment2.newInstance(bundle);
            addCaptureFragment(R.id.container_paper_special_receipt, paperSpecialReceiptFragment);

            bundle.putParcelableArrayList(PAPER_ELEC_RECEIPT_DATA, images3);
            paperElecReceiptFragment = DemandsDetailsReceiptFragment3.newInstance(bundle);
            addCaptureFragment(R.id.container_paper_elec_receipt, paperElecReceiptFragment);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id._back})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id._back:
                this.finish();
                break;
        }
    }
}
