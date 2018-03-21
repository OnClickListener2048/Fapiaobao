package com.pilipa.fapiaobao.ui;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.widget.NestedScrollView;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.mylibrary.utils.RegexUtils;
import com.example.mylibrary.utils.ToastUtils;
import com.lzy.okgo.model.Response;
import com.pilipa.fapiaobao.R;
import com.pilipa.fapiaobao.account.AccountHelper;
import com.pilipa.fapiaobao.base.BaseActivity;
import com.pilipa.fapiaobao.net.Api;
import com.pilipa.fapiaobao.net.bean.base.BaseResponseBean;
import com.pilipa.fapiaobao.net.callback.DialogJsonConverter;
import com.pilipa.fapiaobao.ui.fragment.DemandsDetailsReceiptFragment;
import com.pilipa.fapiaobao.ui.fragment.DemandsDetailsReceiptFragment2;
import com.pilipa.fapiaobao.ui.fragment.DemandsDetailsReceiptFragment3;
import com.pilipa.fapiaobao.ui.model.Image;
import com.pilipa.fapiaobao.utils.DialogUtil;

import org.json.JSONArray;
import org.json.JSONException;

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
    public static final String PAPER_NORMAL_RECEIPT_DATA = "paper_normal_receipt_data";
    public static final String PAPER_SPECIAL_RECEIPT_DATA = "paper_special_receipt_data";
    public static final String PAPER_ELEC_RECEIPT_DATA = "paper_elec_receipt_data";
    private static final String TAG = "QualifiedInvoiceActivity";
    @Bind(R.id.tv_num_1)
    TextView tv_num_1;
    @Bind(R.id.tv_num_2)
    TextView tv_num_2;
    @Bind(R.id.tv_num_3)
    TextView tv_num_3;
    @Bind(R.id.ll_receiptlist)
    LinearLayout ll_receiptlist;
    @Bind(R.id.ll_no_record)
    LinearLayout ll_no_record;
    @Bind(R.id.ll_container_paper_normal_receipt)
    LinearLayout container_paper_normal_receipt;
    @Bind(R.id.ll_container_paper_special_receipt)
    LinearLayout container_paper_special_receipt;
    @Bind(R.id.ll_container_paper_elec_receipt)
    LinearLayout container_paper_elec_receipt;
    @Bind(R.id.nestScrollView)
    NestedScrollView mNestedScrollView;
    @Bind(R.id.iv_mail)
    ImageView mIvMail;
    ArrayList<Image> images_qualified = new ArrayList<>();
    private DemandsDetailsReceiptFragment paperNormalReceiptFragment;
    private DemandsDetailsReceiptFragment2 paperSpecialReceiptFragment;
    private DemandsDetailsReceiptFragment3 paperElecReceiptFragment;
    private Dialog mDialog;
    private EditText mEtMail;
    private ArrayList<Image> mImages3;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_qualified_invoice;
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void initView() {
        mNestedScrollView.smoothScrollTo(0, 20);
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
        mImages3 = new ArrayList<>();
            for (int i = 0; i < results.size(); i++) {
                if (VARIETY_GENERAL_PAPER.equals(results.get(i).variety)) {
                    images1.add(results.get(i));
                } else if (VARIETY_SPECIAL_PAPER.equals(results.get(i).variety)) {
                    images2.add(results.get(i));
                } else if (VARIETY_GENERAL_ELECTRON.equals(results.get(i).variety)) {
                    mImages3.add(results.get(i));
                }
            }
        tv_num_1.setText(String.format(getResources().getString(R.string.paper_normal_receipt_num), images1.size()));
        tv_num_2.setText(String.format(getResources().getString(R.string.paper_special_receipt_num), images2.size()));
        tv_num_3.setText(String.format(getResources().getString(R.string.paper_elec_receipt_num), mImages3.size()));


            Bundle bundle = new Bundle();
            bundle.putParcelableArrayList(PAPER_NORMAL_RECEIPT_DATA, images1);
            paperNormalReceiptFragment = DemandsDetailsReceiptFragment.newInstance(bundle);
            addCaptureFragment(R.id.container_paper_normal_receipt, paperNormalReceiptFragment);

            bundle.putParcelableArrayList(PAPER_SPECIAL_RECEIPT_DATA, images2);
            paperSpecialReceiptFragment = DemandsDetailsReceiptFragment2.newInstance(bundle);
            addCaptureFragment(R.id.container_paper_special_receipt, paperSpecialReceiptFragment);

        bundle.putParcelableArrayList(PAPER_ELEC_RECEIPT_DATA, mImages3);
            paperElecReceiptFragment = DemandsDetailsReceiptFragment3.newInstance(bundle);
            addCaptureFragment(R.id.container_paper_elec_receipt, paperElecReceiptFragment);
        if(images1.size()==0){
            container_paper_normal_receipt.setVisibility(View.GONE);
        }
        if(images2.size()==0){
            container_paper_special_receipt.setVisibility(View.GONE);
        }
        if (mImages3.size() == 0) {
            container_paper_elec_receipt.setVisibility(View.GONE);
        }
        if (images1.size() == 0 && images2.size() == 0 && mImages3.size() == 0) {
            ll_no_record.setVisibility(View.VISIBLE);
            ll_receiptlist.setVisibility(View.GONE);
        }

        mIvMail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initDialog();
                showDialog(mDialog);
            }
        });
    }

    private void initDialog() {
        mDialog = DialogUtil.getInstance().createDialog(this, 0, R.layout.dialog_mail, null,
                new DialogUtil.OnConfirmListener() {
                    @Override
                    public void onConfirm(View view) {
                        mail();

                    }
                }, new DialogUtil.OnCancelListener() {
                    @Override
                    public void onCancel(View view) {
                        mDialog.dismiss();
                    }
                });
        mEtMail = (EditText) DialogUtil.getInstance().getRootView().findViewById(R.id.et_mail);
        mEtMail.setText(AccountHelper.getUser().getData().getCustomer().getEmail());
    }

    private void mail() {

        if (TextUtils.equals(getText(mEtMail), "")) {
            ToastUtils.showShort("请填写您的邮箱地址");
            return;
        }

        if (!RegexUtils.isEmail(getText(mEtMail))) {
            ToastUtils.showShort("邮箱格式不正确，检查是否有空格等特殊字符");
            return;
        }

        try {
            Api.mail(this, makeParams(), getText(mEtMail), new DialogJsonConverter<BaseResponseBean>(this) {

                @Override
                public void onSuccess(Response<BaseResponseBean> response) {
                    ToastUtils.showShort("发送成功，可在您的邮箱查看、下载发票了");
                }

                @Override
                public void onError(Response<BaseResponseBean> response) {
                    ToastUtils.showShort("发送成功，可在您的邮箱查看、下载发票了");
                }
            });
        } catch (JSONException e) {
            e.printStackTrace();
        }
        mDialog.dismiss();
    }

    private JSONArray makeParams() {
        JSONArray jsonArray = new JSONArray();
        for (Image image : mImages3) {
            String[] strings = image.path.split("upload/");
            jsonArray.put(strings[1]);
        }
        return jsonArray;
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
            default:
        }
    }
}
