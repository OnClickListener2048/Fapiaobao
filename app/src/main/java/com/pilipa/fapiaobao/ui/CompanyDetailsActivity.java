package com.pilipa.fapiaobao.ui;

import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.pilipa.fapiaobao.R;
import com.pilipa.fapiaobao.account.AccountHelper;
import com.pilipa.fapiaobao.adapter.CompanyDetailsAdapter;
import com.pilipa.fapiaobao.base.BaseActivity;
import com.pilipa.fapiaobao.base.BaseApplication;
import com.pilipa.fapiaobao.entity.Company;
import com.pilipa.fapiaobao.net.Api;
import com.pilipa.fapiaobao.net.bean.me.CompaniesBean;
import com.pilipa.fapiaobao.net.bean.me.NormalBean;
import com.pilipa.fapiaobao.ui.fragment.MyCompanyDetailsPagerFragment;
import com.pilipa.fapiaobao.utils.DialogUtil;
import com.umeng.socialize.UMShareAPI;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.pilipa.fapiaobao.net.Constant.REQUEST_SUCCESS;

/**
 * Created by wjn on 2017/10/23.
 */

public class CompanyDetailsActivity extends BaseActivity implements MyCompanyDetailsPagerFragment.OnDelClickListener,MyCompanyDetailsPagerFragment.OnNextClickListener {
    public BroadcastReceiver mBroadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals("remove")) {
                finish();
            }
        }
    };
    protected int mPreviousPos = 0;
    @Bind(R.id.vp_verpager)
    ViewPager mViewPager;
    CompanyDetailsAdapter companyDetailsAdapter;
    ArrayList<CompaniesBean.DataBean> companyList;
    private ArrayList<MyCompanyDetailsPagerFragment> FragmentList;
    private Dialog deleteCompanyDialog;
    private String deleteId;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_company_details;
    }

    @OnClick({R.id.details_back,R.id.img_add})
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.details_back:{
                finish();
            }break;
            case R.id.img_add:{
                startActivity(new Intent(this,AddCompanyInfoActivity.class));
            }break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void initView() {
        companyList = getIntent().getParcelableArrayListExtra("companyList");
        mPreviousPos= getIntent().getIntExtra("mPreviousPos",0);

        FragmentList  = new ArrayList<>();
        for (int i = 0; i <companyList.size() ; i++) {
            Bundle b = new Bundle();
            Company company = new Company();
            company.setId(companyList.get(i).getId());
            company.setName(companyList.get(i).getName());
            company.setTaxno(companyList.get(i).getTaxno());
            company.setAddress(companyList.get(i).getAddress());
            company.setPhone(companyList.get(i).getPhone());
            company.setDepositBank(companyList.get(i).getDepositBank());
            company.setAccount(companyList.get(i).getAccount());
            company.setQrcode(companyList.get(i).getQrcode());

            b.putParcelable("company",company);
            MyCompanyDetailsPagerFragment fragment1 = MyCompanyDetailsPagerFragment.newInstance(b);
            fragment1.setOnDelClickListener(this);
            fragment1.setOnNextClickListener(this);
            FragmentList.add(fragment1);
        }
        companyDetailsAdapter = new CompanyDetailsAdapter(this,getSupportFragmentManager(), FragmentList);
        mViewPager.setAdapter(companyDetailsAdapter);
        mViewPager.setCurrentItem(mPreviousPos);

        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("remove");

        registerReceiver(mBroadcastReceiver, intentFilter);
        initDialog();
    }

    private void initDialog() {
        deleteCompanyDialog = DialogUtil.getInstance().createDeleteCompanyDialog(this, new DialogUtil.OnConfirmListener() {
            @Override
            public void onConfirm(View view) {
                deleteCompany(deleteId);
            }
        }, new DialogUtil.OnCancelListener() {
            @Override
            public void onCancel(View view) {
                deleteCompanyDialog.dismiss();
            }
        });
    }

    @Override
    public void initData() {
    }
    @Override
    protected void onDestroy() {
        unregisterReceiver(mBroadcastReceiver);

        super.onDestroy();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @Override
    public void onDelClick(String deleteId) {
        mPreviousPos =  mViewPager.getCurrentItem();
        this.deleteId = deleteId;
        showDialog(deleteCompanyDialog);
    }
    public void deleteCompany(String id) {
            Api.deleteCompany(id, AccountHelper.getToken(), new Api.BaseRawResponse<NormalBean>() {
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
                public void onTokenInvalid() {

                }

                @Override
                public void setData(NormalBean normalBean) {
                    if (normalBean.getStatus() == REQUEST_SUCCESS) {
                        setResult(RESULT_OK);
                        deleteCompanyDialog.dismiss();
                        companyDetailsAdapter.remove(mPreviousPos);
                        BaseApplication.showToast("删除成功");


                    }
                }
            });
    }
    @Override
    public void onNextClick() {

    }
//    Dialog mDialog;
//    private void setDialog(final String deleteId) {
//        mDialog = new Dialog(CompanyDetailsActivity.this, R.style.BottomDialog);
//        LinearLayout root;
//        root = (LinearLayout) LayoutInflater.from(CompanyDetailsActivity.this).inflate(
//                R.layout.layout_delete_tip, null);
//        root.findViewById(R.id.btn_cancel).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                mDialog.dismiss();
//            }
//        });
//        root.findViewById(R.id.btn_confirm).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                deleteCompany(deleteId);
//            }
//        });
//        mDialog.setContentView(root);
//        Window dialogWindow = mDialog.getWindow();
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
//        mDialog.show();
//    }
}
