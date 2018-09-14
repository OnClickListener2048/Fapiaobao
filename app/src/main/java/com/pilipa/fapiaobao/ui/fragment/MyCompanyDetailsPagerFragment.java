package com.pilipa.fapiaobao.ui.fragment;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.mylibrary.utils.ActivityUtils;
import com.example.mylibrary.utils.TLog;
import com.pilipa.fapiaobao.AppOperator;
import com.pilipa.fapiaobao.R;
import com.pilipa.fapiaobao.adapter.me.MyCompanyDetailsAdapter;
import com.pilipa.fapiaobao.base.BaseApplication;
import com.pilipa.fapiaobao.base.BaseFragment;
import com.pilipa.fapiaobao.entity.Company;
import com.pilipa.fapiaobao.net.Api;
import com.pilipa.fapiaobao.net.bean.me.CompanyDetailsBean;
import com.pilipa.fapiaobao.net.bean.me.FavoriteCompanyBean;
import com.pilipa.fapiaobao.ui.CompanyDetailsActivity;
import com.pilipa.fapiaobao.ui.CompanyManagerActivity;
import com.pilipa.fapiaobao.ui.EstimateLocationActivity;
import com.pilipa.fapiaobao.ui.FavCompanyDetailsActivity;
import com.pilipa.fapiaobao.zxing.encode.CodeCreator;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

import static com.pilipa.fapiaobao.net.Constant.REQUEST_SUCCESS;
import static com.pilipa.fapiaobao.ui.fragment.model.Constant.START_ACTIVITY_FROM_DETAILS;

/**
 * Created by lyt on 2017/10/17.
 */

public class MyCompanyDetailsPagerFragment extends BaseFragment implements MyCompanyDetailsAdapter.ItemClickListener {
    private static final String TAG = "MyCompanyDetailsPagerFragment";
    @Bind(R.id.view_recycler)
    RecyclerView viewRecycler;

    private TextView tv_companyName, tv_receptCode, tv_address, tv_phoneNum, tv_bankName, tv_account;

    private ImageView img_details_viewpager_next;
    private String companyId;
    private String deleteId;

    private Company company;
    private OnDelClickListener onDelClickListener;
    private OnNextClickListener onNextClickListener;

    public static MyCompanyDetailsPagerFragment newInstance(Bundle bundle) {
        MyCompanyDetailsPagerFragment myCompanyDetailsPagerFragment = new MyCompanyDetailsPagerFragment();
        myCompanyDetailsPagerFragment.setArguments(bundle);
        return myCompanyDetailsPagerFragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_company_details;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    protected void initWidget(View root) {
        super.initWidget(root);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        tv_companyName = (TextView) view.findViewById(R.id.tv_companyName);
        tv_receptCode = (TextView) view.findViewById(R.id.tv_receptCode);
        tv_address = (TextView) view.findViewById(R.id.tv_address);
        tv_phoneNum = (TextView) view.findViewById(R.id.tv_phoneNum);
        tv_bankName = (TextView) view.findViewById(R.id.tv_bankName);
        tv_account = (TextView) view.findViewById(R.id.tv_account);
        LinearLayout ll_we_need = (LinearLayout) view.findViewById(R.id.ll_we_need);
        TextView tv_newNeed = (TextView) view.findViewById(R.id.tv_newNeed);
        final ImageView img_qr_code1 = (ImageView) view.findViewById(R.id.img_qr_code1);
        final ImageView img_qr_code2 = (ImageView) view.findViewById(R.id.img_qr_code2);
        LinearLayout ll_qr_code2 = (LinearLayout) view.findViewById(R.id.ll_qr_code2);
        viewRecycler.setLayoutManager(new GridLayoutManager(mContext, 3));
        Bundle arguments = getArguments();
        company = arguments.getParcelable("company");
        if (company != null) {
            tv_companyName.setText(company.getName());
            tv_receptCode.setText(company.getTaxno());
            tv_address.setText(company.getAddress());
            tv_phoneNum.setText(company.getPhone());
            tv_bankName.setText(company.getDepositBank());
            tv_account.setText(company.getAccount());
            TLog.d("qrcode", company.getQrcode() + "");
            if (company.getInvoiceTypeList() != null) {
                ll_qr_code2.setVisibility(View.VISIBLE);
                img_qr_code1.setVisibility(View.GONE);
                ll_we_need.setVisibility(View.VISIBLE);
                tv_newNeed.setVisibility(View.VISIBLE);
                initChildViews((List<FavoriteCompanyBean.DataBean.InvoiceTypeListBean>) company.getInvoiceTypeList());
            } else {
                ll_we_need.setVisibility(View.GONE);
                tv_newNeed.setVisibility(View.GONE);
                img_qr_code1.setVisibility(View.VISIBLE);
                ll_qr_code2.setVisibility(View.GONE);
            }
        }

        Log.d(TAG, "Company getQrcode" + company.getQrcode());
        ((CompanyDetailsActivity) getActivity()).setShareContent(company.getQrcode());



        //TODO 截取qrCode中 companyId
        String[] temp = null;
        temp = company.getQrcode().split("id=");
        companyId = temp[1];
        //删除的ID
        deleteId = company.getId();
        Log.d(TAG, "deleteId" + deleteId);
        Observable.create(new ObservableOnSubscribe<Bitmap>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<Bitmap> e) throws Exception {
                String content = new String(company.getQrcode().getBytes("UTF-8"), "ISO-8859-1");
                TLog.log("content-----------" + content);
                Bitmap qrCode = CodeCreator.createQRCode(mContext, content);
                e.onNext(qrCode);
                e.onComplete();
            }
        }).subscribeOn(Schedulers.from(AppOperator.getExecutor()))
                .observeOn(AndroidSchedulers.mainThread())
                .doOnError(new Consumer<Throwable>() {
                    @Override
                    public void accept(@NonNull Throwable throwable) throws Exception {
                        BaseApplication.showToast("二维码生成失败");
                        throwable.printStackTrace();
                    }
                })
                .subscribe(new Consumer<Bitmap>() {
                    @Override
                    public void accept(@NonNull Bitmap bitmap) throws Exception {
                        img_qr_code1.setImageBitmap(bitmap);
                        img_qr_code2.setImageBitmap(bitmap);
                    }
                });
    }

    private void initChildViews(List<FavoriteCompanyBean.DataBean.InvoiceTypeListBean> list) {
        ViewGroup.MarginLayoutParams lp = new ViewGroup.MarginLayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        lp.leftMargin = 8;
        lp.rightMargin = 8;
        lp.topMargin = 5;
        lp.bottomMargin = 5;

        MyCompanyDetailsAdapter myCompanyDetailsAdapter = new MyCompanyDetailsAdapter(list);
        myCompanyDetailsAdapter.setOnItemClickListener(this);
        viewRecycler.setAdapter(myCompanyDetailsAdapter);

    }



    @OnClick({R.id.img_details_viewpager_share, R.id.img_details_viewpager_delete, R.id.img_details_viewpager_next})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img_details_viewpager_share:
                ((CompanyDetailsActivity) getActivity()).setDialog(company.getQrcode());
                break;
            case R.id.img_details_viewpager_delete:
                if (onDelClickListener != null) {
                    onDelClickListener.onDelClick(deleteId);
                }
                break;
            case R.id.img_details_viewpager_next:
                if (onNextClickListener != null) {
                    onNextClickListener.onNextClick();
                }
                break;
            default:
        }
    }



    @Override
    protected void initData() {
        super.initData();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    public void getCompanyDetails(String id) {
        Api.companyDetails(id, new Api.BaseViewCallback<CompanyDetailsBean>() {
            @Override
            public void setData(CompanyDetailsBean companyDetailsBean) {
                if (companyDetailsBean.getStatus() == REQUEST_SUCCESS) {
                    CompanyDetailsBean.DataBean bean = companyDetailsBean.getData();
                    tv_companyName.setText(bean.getName());
                    tv_receptCode.setText(bean.getTaxno());
                    tv_address.setText(bean.getAddress());
                    tv_phoneNum.setText(bean.getPhone());
                    tv_bankName.setText(bean.getDepositBank());
                    tv_account.setText(bean.getAccount());
                    Log.d(TAG, "getCompanyDetails" + bean.getName().toString());
                }
            }
        });
    }

    public void setOnDelClickListener(OnDelClickListener onDelClickListener) {
        this.onDelClickListener = onDelClickListener;
    }

    @Override
    public void onItemClick(FavoriteCompanyBean.DataBean.InvoiceTypeListBean dataBean) {
        TLog.log("dataBean.getName()"+dataBean.getName());
        TLog.log("dataBean.getId()"+dataBean.getId());

        Intent intent = new Intent(mContext, EstimateLocationActivity.class);
        intent.putExtra("companyId", companyId);
        intent.putExtra(FinanceFragment.EXTRA_DATA_LABEL, dataBean.getId());
        intent.putExtra(FinanceFragment.EXTRA_DATA_LABEL_NAME, dataBean.getName());
        mContext.startActivity(intent);
        Intent intent1 = new Intent();
        intent1.setAction(START_ACTIVITY_FROM_DETAILS);
        mContext.sendBroadcast(intent1);
        ActivityUtils.finishActivity(FavCompanyDetailsActivity.class);
        ActivityUtils.finishActivity(CompanyManagerActivity.class);
    }

    public void setOnNextClickListener(OnNextClickListener onNextClickListener) {
        this.onNextClickListener = onNextClickListener;
    }

    public interface OnDelClickListener {
        void onDelClick(String companyId);
    }

    public interface OnNextClickListener {
        void onNextClick();
    }

}
