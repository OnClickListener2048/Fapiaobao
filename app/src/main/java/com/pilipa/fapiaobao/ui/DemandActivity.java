package com.pilipa.fapiaobao.ui;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.pilipa.fapiaobao.R;
import com.pilipa.fapiaobao.base.BaseActivity;
import com.pilipa.fapiaobao.net.bean.TestBean;
import com.pilipa.fapiaobao.net.callback.JsonCallBack;
import com.pilipa.fapiaobao.ui.fragment.DemandsDetailsReceiptFragment;
import com.pilipa.fapiaobao.ui.fragment.UploadPreviewReceiptFragment;
import com.pilipa.fapiaobao.ui.model.Image;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by lyt on 2017/10/16.
 */

public class DemandActivity extends BaseActivity {
    private static final String TAG = "DemandActivity";
    @Bind(R.id.container_paper_normal_receipt)
    FrameLayout containerPaperNormalReceipt;
    @Bind(R.id.container_paper_special_receipt)
    FrameLayout containerPaperSpecialReceipt;
    @Bind(R.id.container_paper_elec_receipt)
    FrameLayout containerPaperElecReceipt;

    public static final String PAPER_NORMAL_RECEIPT_DATA = "paper_normal_receipt_data";
    public static final String PAPER_SPECIAL_RECEIPT_DATA = "paper_special_receipt_data";
    public static final String PAPER_ELEC_RECEIPT_DATA = "paper_elec_receipt_data";



    private ArrayList<Image> images;
    private DemandsDetailsReceiptFragment paperNormalReceiptFragment;
    private DemandsDetailsReceiptFragment paperSpecialReceiptFragment;
    private DemandsDetailsReceiptFragment paperElecReceiptFragment;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_demand;
    }

    @OnClick({R.id.demand_back})
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.demand_back: {
                finish();
            }
            break;
        }
    }

    @Override
    public void initView() {
        OkGo.<TestBean>get("http://gank.io/api/data/福利/5/1").execute(new JsonCallBack<TestBean>(TestBean.class) {
            @Override
            public void onSuccess(Response<TestBean> response) {
                TestBean body = response.body();
                if (!body.isError()) {
                    setUpData(body.getResults());
                }
            }
        });
    }

    private void setUpData(ArrayList<TestBean.ResultsBean> results) {
        Log.d(TAG, "setUpData:   private void setUpData(ArrayList<model.ResultsBean> body) {");
        images = new ArrayList<>();
        for (TestBean.ResultsBean result : results) {
            Log.d(TAG, "setUpData:  for (model.ResultsBean result : body) {");
            Image image = new Image();
            image.isSelected = false;
            image.name = result.get_id();
            image.path = result.getUrl();
            image.position = -1;
            image.isCapture = false;
            image.isFromNet = true;
            images.add(image);
        }

        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList(PAPER_NORMAL_RECEIPT_DATA, images);
        paperNormalReceiptFragment = DemandsDetailsReceiptFragment.newInstance(bundle);
        addCaptureFragment(R.id.container_paper_normal_receipt, paperNormalReceiptFragment);


        bundle.putParcelableArrayList(PAPER_SPECIAL_RECEIPT_DATA, images);
        paperSpecialReceiptFragment = DemandsDetailsReceiptFragment.newInstance(bundle);
        addCaptureFragment(R.id.container_paper_special_receipt, paperSpecialReceiptFragment);

        bundle.putParcelableArrayList(PAPER_ELEC_RECEIPT_DATA, images);
        paperElecReceiptFragment = DemandsDetailsReceiptFragment.newInstance(bundle);
        addCaptureFragment(R.id.container_paper_elec_receipt, paperElecReceiptFragment);
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
}
