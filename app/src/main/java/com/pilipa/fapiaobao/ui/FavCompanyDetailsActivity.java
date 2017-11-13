package com.pilipa.fapiaobao.ui;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;

import com.pilipa.fapiaobao.R;
import com.pilipa.fapiaobao.account.AccountHelper;
import com.pilipa.fapiaobao.adapter.CompanyDetailsAdapter;
import com.pilipa.fapiaobao.base.BaseActivity;
import com.pilipa.fapiaobao.entity.Company;
import com.pilipa.fapiaobao.net.Api;
import com.pilipa.fapiaobao.net.bean.me.FavBean;
import com.pilipa.fapiaobao.net.bean.me.FavoriteCompanyBean;
import com.pilipa.fapiaobao.ui.fragment.MyCompanyDetailsPagerFragment;
import com.umeng.socialize.UMShareAPI;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.pilipa.fapiaobao.net.Constant.REQUEST_SUCCESS;

/**
 * Created by wjn on 2017/10/23.
 */

public class FavCompanyDetailsActivity extends BaseActivity implements MyCompanyDetailsPagerFragment.OnDelClickListener
        ,MyCompanyDetailsPagerFragment.OnNextClickListener {
    @Bind(R.id.vp_verpager)
    ViewPager mViewPager;
    CompanyDetailsAdapter companyDetailsAdapter;
    private ArrayList<MyCompanyDetailsPagerFragment> FragmentList;
    protected int mPreviousPos = 0;
    ArrayList<FavoriteCompanyBean.DataBean> companyList;
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
        if(requestCode == 1000){

        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void initView() {
          companyList = getIntent().getParcelableArrayListExtra("favCompanyList");
        mPreviousPos = getIntent().getIntExtra("mPreviousPos",0);
        FragmentList  = new ArrayList<>();
        for (int i = 0; i <companyList.size() ; i++) {
            Bundle b = new Bundle();
            Company company = new Company();
            company.setName(companyList.get(i).getName());
            company.setTaxno(companyList.get(i).getTaxno());
            company.setAddress(companyList.get(i).getAddress());
            company.setPhone(companyList.get(i).getPhone());
            company.setDepositBank(companyList.get(i).getDepositBank());
            company.setAccount(companyList.get(i).getAccount());
            company.setInvoiceTypeList(companyList.get(i).getInvoiceTypeList());

            b.putParcelable("company",company);
            MyCompanyDetailsPagerFragment fragment1 = MyCompanyDetailsPagerFragment.newInstance(b);
            fragment1.setOnDelClickListener(this);
            FragmentList.add(fragment1);
        }
        companyDetailsAdapter = new CompanyDetailsAdapter(getSupportFragmentManager(), FragmentList);
        mViewPager.setAdapter(companyDetailsAdapter);
        mViewPager.setCurrentItem(mPreviousPos);
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

    @Override
    public void onDelClick() {
        mPreviousPos =  mViewPager.getCurrentItem();
        setDialog();
    }
    public void deleteFavCompany(String id){
        Api.deleteFavoriteCompany(id, AccountHelper.getToken(),new Api.BaseViewCallback<FavBean>() {
            @Override
            public void setData(FavBean normalBean) {
                if(normalBean.getStatus() == REQUEST_SUCCESS){
                    companyDetailsAdapter.remove(mPreviousPos);
                    mDialog.dismiss();
                }
            }
        });
    }

    @Override
    public void onNextClick() {

    }
    Dialog mDialog;
    private void setDialog() {
          mDialog = new Dialog(FavCompanyDetailsActivity.this, R.style.BottomDialog);
        LinearLayout root;
            root = (LinearLayout) LayoutInflater.from(FavCompanyDetailsActivity.this).inflate(
                    R.layout.layout_delete_tip, null);
            root.findViewById(R.id.btn_cancel).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mDialog.dismiss();
                }
            });
            root.findViewById(R.id.btn_confirm).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    deleteFavCompany(companyList.get(mPreviousPos).getId());
                }
            });
        mDialog.setContentView(root);
        Window dialogWindow = mDialog.getWindow();
        dialogWindow.setGravity(Gravity.CENTER);
//        dialogWindow.setWindowAnimations(R.style.dialogstyle); // 添加动画
        WindowManager.LayoutParams lp = dialogWindow.getAttributes(); // 获取对话框当前的参数值
        lp.x = 0; // 新位置X坐标
        lp.y = 0; // 新位置Y坐标
        lp.width = (int) getResources().getDisplayMetrics().widthPixels; // 宽度
        root.measure(0, 0);
        lp.height = root.getMeasuredHeight();

        lp.alpha = 9f; // 透明度
        dialogWindow.setAttributes(lp);
        mDialog.show();
    }
}
