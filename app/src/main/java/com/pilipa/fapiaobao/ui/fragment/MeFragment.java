package com.pilipa.fapiaobao.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.mylibrary.utils.TLog;
import com.makeramen.roundedimageview.RoundedImageView;
import com.pilipa.fapiaobao.R;
import com.pilipa.fapiaobao.account.AccountHelper;
import com.pilipa.fapiaobao.base.BaseFragment;
import com.pilipa.fapiaobao.net.Api;
import com.pilipa.fapiaobao.net.bean.LoginWithInfoBean;
import com.pilipa.fapiaobao.ui.CompanyManagerActivity;
import com.pilipa.fapiaobao.ui.CreditRatingActivity;
import com.pilipa.fapiaobao.ui.FeedbackActivity;
import com.pilipa.fapiaobao.ui.HistoryActivity2;
import com.pilipa.fapiaobao.ui.MessageCenterActivity;
import com.pilipa.fapiaobao.ui.MyWalletActivity;
import com.pilipa.fapiaobao.ui.ReceiptFolderActivity;
import com.pilipa.fapiaobao.ui.UserInfoActivity;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by lyt on 2017/10/13.
 */

public class MeFragment extends BaseFragment{
    @Bind(R.id.img_head)
    RoundedImageView imageHead;
    @Bind(R.id.tv_userName)
    TextView tvUserName;
    @Bind(R.id.tv_creditRating)
    TextView tvCreditRating;
    @Bind(R.id.tv_bouns)
    TextView tvBouns;
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_me;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        setImageFromNet(imageHead,"URL",R.mipmap.ic_head_circle_default_small_);
        return rootView;
    }
    @Override
    public void initData(){
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
    @OnClick({R.id.tv_userName
            ,R.id.img_head
            ,R.id.btn_mPublish
            ,R.id.btn_manager
            ,R.id.btn_receipt_folder
            ,R.id.my_wallet
            ,R.id.tv_creditRating
            ,R.id.tv_feedback
            ,R.id.notification})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_userName:
            case R.id.img_head:
                startActivity(new Intent(getContext(), UserInfoActivity.class));
                break;
            case R.id.notification:
                startActivity(new Intent(getContext(), MessageCenterActivity.class));
                break;
            case R.id.btn_mPublish:
                startActivity(new Intent(getContext(), HistoryActivity2.class));
                break;
            case R.id.btn_manager:
                startActivity(new Intent(getContext(), CompanyManagerActivity.class));
                break;
            case R.id.btn_receipt_folder:
                startActivity(new Intent(getContext(), ReceiptFolderActivity.class));
                break;
            case R.id.my_wallet:
                startActivity(new Intent(getContext(), MyWalletActivity.class));
                break;
            case R.id.tv_feedback:
                startActivity(new Intent(getContext(), FeedbackActivity.class));
                break;
            case R.id.tv_creditRating:
                startActivity(new Intent(getContext(), CreditRatingActivity.class));
                break;
        }
    }

    @Override
    public void onResume() {
        TLog.log("onResume");
        AccountHelper.isTokenValid(new Api.BaseViewCallback<LoginWithInfoBean>() {
            @Override
            public void setData(LoginWithInfoBean loginWithInfoBean) {
                LoginWithInfoBean.DataBean.CustomerBean customer = loginWithInfoBean.getData().getCustomer();
                String endFormat = mContext.getResources().getString(R.string.integral);
                tvCreditRating.setText(String.format(endFormat, customer.getCreditScore()));
//          tvUserName.setText(customer.getCreditLevel());
                tvUserName.setText(customer.getNickname());
                tvBouns.setText(customer.getBonus()+"");
            }
        });



        super.onResume();
    }
}
