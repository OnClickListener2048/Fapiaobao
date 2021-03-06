package com.pilipa.fapiaobao.ui;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.example.mylibrary.utils.TLog;
import com.pilipa.fapiaobao.R;
import com.pilipa.fapiaobao.account.AccountHelper;
import com.pilipa.fapiaobao.adapter.supply.PreviewPagerAdapter;
import com.pilipa.fapiaobao.base.BaseActivity;
import com.pilipa.fapiaobao.base.BaseApplication;
import com.pilipa.fapiaobao.net.Api;
import com.pilipa.fapiaobao.net.Constant;
import com.pilipa.fapiaobao.net.bean.me.NormalBean;
import com.pilipa.fapiaobao.ui.fragment.DemandsDetailsReceiptFragment;
import com.pilipa.fapiaobao.ui.fragment.PreviewImageFragment;
import com.pilipa.fapiaobao.ui.fragment.UploadNormalReceiptFragment;
import com.pilipa.fapiaobao.ui.fragment.UploadPreviewReceiptFragment;
import com.pilipa.fapiaobao.ui.model.Image;
import com.pilipa.fapiaobao.ui.widget.PreviewViewpager;
import com.pilipa.fapiaobao.utils.DialogUtil;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by edz on 2017/10/20.
 */

public class UnusedPreviewActivity extends BaseActivity implements ViewPager.OnPageChangeListener, CompoundButton.OnCheckedChangeListener {
    private static final String TAG = "UnusedPreviewActivity";
    protected int mPreviousPos = -1;
    @Bind(R.id.preview_viewpager)
    PreviewViewpager previewViewpager;
    @Bind(R.id.delete)
    TextView delete;
    @Bind(R.id.back)
    TextView back;
    @Bind(R.id.click)
    CheckBox click;
    private ArrayList<Image> allList;
    private PreviewPagerAdapter previewPagerAdapter;
    private Dialog mDelDialog;
    private Image mImage;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_unused_preivew;
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void initView() {
        Bundle bundleExtra = getIntent().getBundleExtra(UploadNormalReceiptFragment.EXTRA_BUNDLE);
        boolean aBoolean = bundleExtra.getBoolean(UploadPreviewReceiptFragment.IS_SHOW_SELECT_AND_DELETE, true);
        if (aBoolean) {
            delete.setVisibility(View.VISIBLE);
            click.setVisibility(View.VISIBLE);
        } else {
            delete.setVisibility(View.INVISIBLE);
            click.setVisibility(View.INVISIBLE);
        }
        // click 选择按钮没有用，暂不显示
        click.setVisibility(View.GONE);

        allList = bundleExtra.getParcelableArrayList(UploadNormalReceiptFragment.EXTRA_ALL_DATA);
        int currentPosition = bundleExtra.getInt(UploadNormalReceiptFragment.EXTRA_CURRENT_POSITION);
        Log.d(TAG, "initView: currentPosition" + currentPosition);
        boolean isFromDemands = bundleExtra.getBoolean(DemandsDetailsReceiptFragment.IS_FROM_DEMANDS, false);
//        mPreviousPos = isFromDemands ? currentPosition : currentPosition - 1;
        mPreviousPos = currentPosition - 1;
        click.setChecked(allList.get(currentPosition).isSelected);

        ArrayList<PreviewImageFragment> fragmentList = new ArrayList<>();
        for (Image image : allList) {
            if (!image.isCapture) {
                mImage = image;
                fragmentList.add(PreviewImageFragment.newInstance(image));
            }
        }

        previewPagerAdapter = new PreviewPagerAdapter(getSupportFragmentManager(), fragmentList);
        previewViewpager.setAdapter(previewPagerAdapter);
        previewViewpager.setCurrentItem(mPreviousPos);
        previewViewpager.setOnPageChangeListener(this);
        click.setOnCheckedChangeListener(this);

        initDialog();
    }

    private void initDialog() {
        mDelDialog = DialogUtil.getInstance().createDialog(this, R.style.BottomDialog, R.layout.dialog_delete_receiptfolder_invoice, null, new DialogUtil.OnConfirmListener() {
            @Override
            public void onConfirm(View view) {
                mDelDialog.dismiss();
                deleteMyInvoice(allList.get(mPreviousPos + 1).name);
            }
        }, new DialogUtil.OnCancelListener() {
            @Override
            public void onCancel(View view) {
                mDelDialog.dismiss();
            }
        });
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
                showDialog(mDelDialog);
                break;
            case R.id.back:
                returnResult();
                finish();
                break;
            case R.id.click:
                click.setChecked(click.isChecked());
                allList.get(mPreviousPos + 1).isSelected = !click.isChecked();
                break;
            default:
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            returnResult();
        }
        return super.onKeyDown(keyCode, event);
    }

    protected void returnResult() {
        Intent intent = new Intent();
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList(UploadNormalReceiptFragment.EXTRA_ALL_DATA, allList);
        intent.putExtra(UploadNormalReceiptFragment.EXTRA_BUNDLE, bundle);
        setResult(UploadNormalReceiptFragment.RESULT_CODE_BACK, intent);
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
        click.setChecked(allList.get(position + 1).isSelected);
        mImage = allList.get(position + 1);
        Log.d(TAG, "onPageSelected:mPreviousPos " + mPreviousPos + " click.setChecked(allList.get(position+1).isSelected);" + allList.get(position + 1).isSelected);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if (buttonView instanceof CheckBox) {

            buttonView.setChecked(isChecked);
            allList.get(mPreviousPos + 1).isSelected = isChecked;

            Log.d(TAG, "onCheckedChanged:   allList.get(mPreviousPos)." + mPreviousPos);
        }
    }

//    private void setDelDialog(final Image image) {
//        mDelDialog = new Dialog(UnusedPreviewActivity.this, R.style.BottomDialog);
//        LinearLayout root = (LinearLayout) LayoutInflater.from(UnusedPreviewActivity.this).inflate(
//                R.layout.layout_delete_tip, null);
//        TextView tv_title = (TextView) root.findViewById(R.id.tv_title);
//        tv_title.setText(getString(R.string.do_you_real_wanna_delete_this_invoice));
//        //初始化视图
//        root.findViewById(R.id.btn_cancel).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                mDelDialog.dismiss();
//            }
//        });
//        root.findViewById(R.id.btn_confirm).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                deleteMyInvoice(image.name);
//            }
//        });
//        mDelDialog.setContentView(root);
//        Window dialogWindow = mDelDialog.getWindow();
//        dialogWindow.setGravity(Gravity.CENTER);
////        dialogWindow.setWindowAnimations(R.style.dialogstyle); // 添加动画
//        WindowManager.LayoutParams lp = dialogWindow.getAttributes(); // 获取对话框当前的参数值
//        lp.x = 0; // 新位置X坐标
//        lp.y = 0; // 新位置Y坐标
//        lp.width = (int) getResources().getDisplayMetrics().widthPixels; // 宽度
//        root.measure(0, 0);
//        lp.height = root.getMeasuredHeight();
//
//        lp.alpha = 9f; // 透明度
//        dialogWindow.setAttributes(lp);
//        mDelDialog.show();
//    }

    private void deleteMyInvoice(final String invoiceId) {

        Api.deleteMyInvoice(AccountHelper.getToken(), invoiceId, new Api.BaseRawResponse<NormalBean>() {
            @Override
            public void onTokenInvalid() {
                login();
            }

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
                hideProgressDialog();
                BaseApplication.showToast(getString(R.string.delete_fail));
            }

            @Override
            public void setData(NormalBean normalBean) {
                if (normalBean.getStatus() == Constant.REQUEST_SUCCESS) {
                    deleteLoc();
                    BaseApplication.showToast(getString(R.string.delete_success));
                }
                TLog.d("", "initData:deleteMyInvoice success");

            }
        });

    }

    private void deleteLoc() {
        TLog.d(TAG, "before allList.remove(mPreviousPos+1);allList.size()" + allList.size());
        allList.remove(mPreviousPos + 1);
        previewPagerAdapter.remove(mPreviousPos);
        TLog.d(TAG, "after---onViewClicked: allList.size()=========" + allList.size());
        TLog.d(TAG, "onViewClicked: previewPagerAdapter.arrayList.size()=========" + previewPagerAdapter.arrayList.size());
        if (allList.size() == 0 || previewPagerAdapter.arrayList.size() == 0) {
            returnResult();
            finish();
        }
    }
}
