package com.pilipa.fapiaobao.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.lzy.okgo.OkGo;
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
import com.pilipa.fapiaobao.ui.LoginActivity;
import com.pilipa.fapiaobao.ui.MessageCenterActivity;
import com.pilipa.fapiaobao.ui.MyWalletActivity;
import com.pilipa.fapiaobao.ui.Op;
import com.pilipa.fapiaobao.ui.ReceiptFolderActivity;
import com.pilipa.fapiaobao.ui.UserInfoActivity;
import com.pilipa.fapiaobao.utils.SharedPreferencesHelper;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by lyt on 2017/10/13.
 */

public class MeFragment extends BaseFragment{
    @Bind(R.id.img_head_me)
    RoundedImageView imageHead;
    @Bind(R.id.tv_userName)
    TextView tvUserName;
    @Bind(R.id.tv_creditRating)
    TextView tvCreditRating;
    @Bind(R.id.tv_bouns)
    TextView tvBouns;
    @Bind(R.id.LevelIcon)
    ImageView imgLevelIcon;

    int[] LevelIcon=new int[]{R.mipmap.star0,R.mipmap.star1,R.mipmap.star2,R.mipmap.star3,R.mipmap.star4,R.mipmap.star5
                              ,R.mipmap.star6,R.mipmap.star7,R.mipmap.star8,R.mipmap.star9,R.mipmap.star10};

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_me;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
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
            ,R.id.img_head_me
            ,R.id.btn_mPublish
            ,R.id.btn_manager
            ,R.id.btn_receipt_folder
            ,R.id.my_wallet
            ,R.id.tv_creditRating
            ,R.id.tv_feedback
            ,R.id.encyclopedia
            ,R.id.helpCenter
            ,R.id.notification})
    public void onViewClicked(final View view) {
        switch (view.getId()){
            case R.id.encyclopedia:
            {
                Intent intent  =new Intent(getContext(), Op.class);
                intent.putExtra("url","http://39.106.4.193/fapiaobao/doc/wiki");
                startActivity(intent);
            }
                break;
            case R.id.helpCenter:
            {
                Intent intent  =new Intent(getContext(), Op.class);
                intent.putExtra("url","");
                startActivity(intent);
            }
                break;
            case R.id.tv_feedback:
                startActivity(new Intent(getContext(), FeedbackActivity.class));
                break;
            case R.id.my_wallet:
                startActivity(new Intent(getContext(), MyWalletActivity.class));
                break;
            default: {
                AccountHelper.isTokenValid(new Api.BaseViewCallback<LoginWithInfoBean>() {
                    @Override
                    public void setData(LoginWithInfoBean normalBean) {
                        if (normalBean.getStatus() == 200) {
                            switch (view.getId()) {
                                case R.id.tv_userName:
                                case R.id.img_head_me:
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
                                case R.id.tv_creditRating:
                                    startActivity(new Intent(getContext(), CreditRatingActivity.class));
                                    break;
                            }
                        } else {
                            startActivity(new Intent(mContext, LoginActivity.class));
                        }
                    }
                });
            }
        }
    }
    RequestManager requestManager;


    @Override
    public void onPause() {
        OkGo.cancelTag(OkGo.getInstance().getOkHttpClient(),"loginByToken");
        super.onPause();
    }

    @Override
    public void onResume() {
         requestManager = Glide.with(mContext);

        AccountHelper.isTokenValid(new Api.BaseViewCallback<LoginWithInfoBean>() {

            @Override
            public void setData(LoginWithInfoBean loginWithInfoBean) {
                if (loginWithInfoBean.getStatus() == 200) {
                    SharedPreferencesHelper.save(mContext, loginWithInfoBean);
                        LoginWithInfoBean.DataBean.CustomerBean customer = loginWithInfoBean.getData().getCustomer();
                        if(customer != null){
                            tvUserName.setText(customer.getNickname());
                            tvCreditRating.setText("积分："+customer.getCreditScore());
                            tvBouns.setText(String.format("%.2f", customer.getAmount())+"元");//钱包金额
                            Log.d("IMAGE_HEAD",customer.getHeadimg());
                            requestManager
                                    .load(customer.getHeadimg())
                                    .asBitmap()
                                    .placeholder(R.mipmap.ic_head_circle_default_small_)
                                    .thumbnail(0.1f)
                                    .into(imageHead);
                            imgLevelIcon.setImageResource(LevelIcon[customer.getCreditLevel()]);
                        }
                }
            }
        });
        super.onResume();
    }
}
