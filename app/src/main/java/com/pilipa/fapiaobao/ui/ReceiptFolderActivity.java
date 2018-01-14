package com.pilipa.fapiaobao.ui;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;

import com.example.mylibrary.utils.RegexUtils;
import com.pilipa.fapiaobao.R;
import com.pilipa.fapiaobao.account.AccountHelper;
import com.pilipa.fapiaobao.adapter.TabPageIndicatorAdapter;
import com.pilipa.fapiaobao.base.BaseActivity;
import com.pilipa.fapiaobao.base.BaseApplication;
import com.pilipa.fapiaobao.net.Api;
import com.pilipa.fapiaobao.net.bean.invoice.MacherBeanToken;
import com.pilipa.fapiaobao.net.bean.me.FavoriteCompanyBean;
import com.pilipa.fapiaobao.ui.constants.Constant;
import com.pilipa.fapiaobao.ui.fragment.ProvidePagerFragment;
import com.pilipa.fapiaobao.ui.fragment.UnusedReceiptFragment;
import com.pilipa.fapiaobao.ui.model.StaticDataCreator;
import com.pilipa.fapiaobao.utils.DialogUtil;
import com.pilipa.fapiaobao.zxing.android.CaptureActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by wjn on 2017/10/23.
 */

public class ReceiptFolderActivity extends BaseActivity implements TabLayout.OnTabSelectedListener {

    private static final int REQUEST_CODE_SCAN = 0x0067;
    private static final String DECODED_CONTENT_KEY = "codedContent";
    private static final String DECODED_BITMAP_KEY = "codedBitmap";
    @Bind(R.id.tl_tabLayout)
    TabLayout tlTabLayout;
    @Bind(R.id.vp_verpager)
    ViewPager vpVerpager;
    @Bind(R.id.img_scan)
    ImageView imgScan;
    String TAG = ReceiptFolderActivity.class.getSimpleName();
    private Dialog scanDialog;
    private Dialog mScanDialog;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_receipt_folder;
    }

    @OnClick({R.id.folder_back,R.id.img_scan})
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.folder_back:{
                finish();
            }break;
            case R.id.img_scan:{
                startActivityForResult(new Intent(this, CaptureActivity.class), REQUEST_CODE_SCAN);
            }break;
        }
    }

    @Override
    public void initView() {
        List list = StaticDataCreator.initReceiptFolderTabData(BaseApplication.context());
        List<Fragment> fragmentList = new ArrayList<>();
        fragmentList.add(new ProvidePagerFragment());
        fragmentList.add(UnusedReceiptFragment.newInstance(new Bundle()));
        vpVerpager.setAdapter(new TabPageIndicatorAdapter(getSupportFragmentManager(), list, fragmentList));
        tlTabLayout.setupWithViewPager(vpVerpager);
        tlTabLayout.setOnTabSelectedListener(this);


        boolean booleanExtra = getIntent().getBooleanExtra(Constant.CHOOSE_RECEIPT_FOLDER, false);
        if (booleanExtra) {
            vpVerpager.setCurrentItem(1);
        }
    }

    @Override
    public void initData() {
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case REQUEST_CODE_SCAN:
                if (resultCode == Activity.RESULT_OK) {
                    String content = data.getStringExtra(DECODED_CONTENT_KEY);
                    if (RegexUtils.isURL(content) || content.contains("http")) {
                        checkFavCompanies(content);
                    }else{
                        showScanDialog();
                    }
                }
                break;
            default:
        }
    }

    private void checkFavCompanies(final String content) {
        Api.favoriteCompanyList(AccountHelper.getToken(), this, new Api.BaseRawResponse<FavoriteCompanyBean>() {
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

            }

            @Override
            public void setData(FavoriteCompanyBean favoriteCompanyBean) {
                Intent intent = new Intent();
                intent.putExtra("url", content);
                intent.putExtra(Constant.TAG, TAG);
                if (favoriteCompanyBean == null || favoriteCompanyBean.getData() == null || favoriteCompanyBean.getData().size() == 0) {
                    intent.setClass(ReceiptFolderActivity.this, Op.class);
                    startActivity(intent);
                } else if (favoriteCompanyBean.getData().size() == 1) {
                    intent.setClass(ReceiptFolderActivity.this, Op.class);
                    intent.putExtra(Constant.COMPANY_INFO, makeCompany(favoriteCompanyBean.getData().get(0)));
                    startActivity(intent);
                } else {
                    intent.putExtra(Constant.COMPANIES_BEAN, favoriteCompanyBean);
                    intent.setClass(ReceiptFolderActivity.this, FavCompanyChooseActivity.class);
                    startActivity(intent);
                }
            }
        });
    }

    private MacherBeanToken.DataBean.CompanyBean makeCompany(FavoriteCompanyBean.DataBean companiesBean) {
        MacherBeanToken.DataBean.CompanyBean companyBean = new MacherBeanToken.DataBean.CompanyBean();
        companyBean.setAccount(companiesBean.getAccount());
        companyBean.setAddress(companiesBean.getAddress());
        companyBean.setDepositBank(companiesBean.getDepositBank());
        companyBean.setId(companiesBean.getId());
        companyBean.setIsNewRecord(companiesBean.isIsNewRecord());
        companyBean.setName(companiesBean.getName());
        companyBean.setPhone(companiesBean.getPhone());
        companyBean.setTaxno(companiesBean.getTaxno());
        return companyBean;
    }

    private void showScanDialog() {
        if (mScanDialog == null) {
            mScanDialog = DialogUtil.getInstance().createDialog(this, 0, R.layout.layout_scan_tip, new DialogUtil.OnKnownListener() {
                @Override
                public void onKnown(View view) {
                    mScanDialog.dismiss();
                }
            }, null, null);
        }
        showDialog(mScanDialog);
    }

//    public void setScanDialog() {
//        scanDialog = new Dialog(this, R.style.BottomDialog);
//        LinearLayout root = (LinearLayout) LayoutInflater.from(this).inflate(
//                R.layout.layout_scan_tip, null);
//        //初始化视图
//        root.findViewById(R.id.btn_confirm).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                scanDialog.dismiss();
//            }
//        });
//        scanDialog.setContentView(root);
//        Window dialogWindow = scanDialog.getWindow();
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
//        scanDialog.show();
//    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }


    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        if(tlTabLayout.getSelectedTabPosition() == 0){
            imgScan.setVisibility(View.GONE);
        }else{
            imgScan.setVisibility(View.VISIBLE);
        }
        vpVerpager.setCurrentItem(tlTabLayout.getSelectedTabPosition());
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }
}
