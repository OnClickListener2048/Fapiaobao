package com.pilipa.fapiaobao.ui.fragment;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.example.mylibrary.utils.TLog;
import com.lzy.okgo.OkGo;
import com.makeramen.roundedimageview.RoundedImageView;
import com.pilipa.fapiaobao.R;
import com.pilipa.fapiaobao.account.AccountHelper;
import com.pilipa.fapiaobao.base.BaseApplication;
import com.pilipa.fapiaobao.base.BaseFragment;
import com.pilipa.fapiaobao.net.Api;
import com.pilipa.fapiaobao.net.Constant;
import com.pilipa.fapiaobao.net.bean.LoginWithInfoBean;
import com.pilipa.fapiaobao.net.bean.me.MessageListBean;
import com.pilipa.fapiaobao.ui.CompanyManagerActivity;
import com.pilipa.fapiaobao.ui.CreditRatingActivity;
import com.pilipa.fapiaobao.ui.FeedbackActivity;
import com.pilipa.fapiaobao.ui.HistoryActivity2;
import com.pilipa.fapiaobao.ui.MessageCenterActivity;
import com.pilipa.fapiaobao.ui.MyWalletActivity;
import com.pilipa.fapiaobao.ui.Op;
import com.pilipa.fapiaobao.ui.ReceiptFolderActivity;
import com.pilipa.fapiaobao.ui.UserInfoActivity;
import com.pilipa.fapiaobao.utils.ButtonUtils;
import com.pilipa.fapiaobao.utils.SharedPreferencesHelper;
import com.pilipa.fapiaobao.utils.TDevice;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.pilipa.fapiaobao.net.Constant.INSTRUCTION;
import static com.pilipa.fapiaobao.net.Constant.REQUEST_SUCCESS;

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
    @Bind(R.id.red_new_dot)
    ImageView red_new_dot;
    int[] LevelIcon=new int[]{R.mipmap.star0,R.mipmap.star1,R.mipmap.star2,R.mipmap.star3,R.mipmap.star4,R.mipmap.star5
                              ,R.mipmap.star6,R.mipmap.star7,R.mipmap.star8,R.mipmap.star9,R.mipmap.star10};

    private BroadcastReceiver mBoradcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals(BaseApplication.PUSH_RECEIVE)) {
                if (red_new_dot != null) {
                    red_new_dot.setVisibility(View.VISIBLE);
                }
            }
        }
    };

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
    public void onDestroy() {
        super.onDestroy();
        try {
            mContext.unregisterReceiver(mBoradcastReceiver);
        } catch (IllegalArgumentException i) {
            i.printStackTrace();
        }

    }


    @Override
    protected void initWidget(View root) {
        super.initWidget(root);

        initBroadcast();
    }

    private void initBroadcast() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(BaseApplication.PUSH_RECEIVE);
        mContext.registerReceiver(mBoradcastReceiver, intentFilter);
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
                intent.putExtra("url", Constant.WIKI);
                startActivity(intent);
            }
                break;
            case R.id.helpCenter:
            {
                Intent intent  =new Intent(getContext(), Op.class);
                intent.putExtra("url",INSTRUCTION);
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
                if(AccountHelper.getToken() != null){
                    switch (view.getId()) {
                        case R.id.tv_userName:
                        case R.id.img_head_me:
                            if (!ButtonUtils.isFastDoubleClick(R.id.tv_userName)||
                                    !ButtonUtils.isFastDoubleClick(R.id.img_head_me)) {
                                startActivity(new Intent(getContext(), UserInfoActivity.class));
                            }
                            break;
                        case R.id.notification:
                            if (!ButtonUtils.isFastDoubleClick(R.id.notification)) {
                                red_new_dot.setVisibility(View.GONE);
                                BaseApplication.set(BaseApplication.PUSH_RECEIVE, false);
                                startActivity(new Intent(getContext(), MessageCenterActivity.class));
                            }
                            break;
                        case R.id.btn_mPublish:
                            if (!ButtonUtils.isFastDoubleClick(R.id.btn_mPublish)) {
                                startActivity(new Intent(getContext(), HistoryActivity2.class));
                            }
                            break;
                        case R.id.btn_manager:
                            if (!ButtonUtils.isFastDoubleClick(R.id.btn_manager)) {
                                startActivity(new Intent(getContext(), CompanyManagerActivity.class));
                            }
                            break;
                        case R.id.btn_receipt_folder:
                            if (!ButtonUtils.isFastDoubleClick(R.id.btn_receipt_folder)) {
                                startActivity(new Intent(getContext(), ReceiptFolderActivity.class));
                            }
                            break;
                        case R.id.tv_creditRating:
                            if (!ButtonUtils.isFastDoubleClick(R.id.tv_creditRating)) {
                                startActivity(new Intent(getContext(), CreditRatingActivity.class));
                            }
                            break;
                    }
                }else{
                    login();
                }
            }
        }
    }
    RequestManager requestManager;


    @Override
    public void onPause() {
        super.onPause();
        OkGo.cancelTag(OkGo.getInstance().getOkHttpClient(),"loginByToken");
    }
    private void messageList() {
        if (TDevice.hasInternet()) {
            AccountHelper.isTokenValid(new Api.BaseViewCallback<LoginWithInfoBean>() {
                @Override
                public void setData(LoginWithInfoBean loginWithInfoBean) {
                    if (loginWithInfoBean.getStatus() == 200) {
                        Api.messageList(loginWithInfoBean.getData().getToken(), new Api.BaseViewCallback<MessageListBean>() {
                            @Override
                            public void setData(MessageListBean messageListBean) {
                                boolean hasNewMsg = false;
                                if(messageListBean.getStatus() == REQUEST_SUCCESS){
                                    List<MessageListBean.DataBean> data = messageListBean.getData();
                                    for (int i = 0; i <data.size() ; i++) {
                                        if(data.get(i).getUnreadMessages() > 0 ){
                                            hasNewMsg =true;
                                            TLog.d("MainActivity","message center had new message");
                                            break;
                                        }else{
                                            TLog.d("MainActivity","message center no message1");
                                        }
                                    }
                                    if(red_new_dot!=null)
                                    if(hasNewMsg){
                                        red_new_dot.setVisibility(View.VISIBLE);
                                    }else{
                                        red_new_dot.setVisibility(View.GONE);
                                    }
                                }
                            }
                        });
                    }
                }
            });
        }
    }
    @Override
    public void onResume() {
//        messageList();
         requestManager = Glide.with(mContext);
        red_new_dot.setVisibility(BaseApplication.get(BaseApplication.PUSH_RECEIVE, false) ? View.VISIBLE : View.GONE);
        AccountHelper.isTokenValid(new Api.BaseViewCallback<LoginWithInfoBean>() {

            @Override
            public void setData(LoginWithInfoBean loginWithInfoBean) {
                if (loginWithInfoBean.getStatus() == 200) {
                    SharedPreferencesHelper.save(mContext, loginWithInfoBean);
                        LoginWithInfoBean.DataBean.CustomerBean customer = loginWithInfoBean.getData().getCustomer();
                        if(tvUserName != null){
                            tvUserName.setText(customer.getNickname());
                            tvCreditRating.setText("积分："+customer.getCreditScore());
                            tvBouns.setText(String.format("%.2f", customer.getAmount()));//钱包金额
                            Log.d("IMAGE_HEAD",customer.getHeadimg());
                            String thumbnail = customer.getHeadimg().replace("invoice","thumbnail");

                            requestManager
                                    .load(thumbnail)
                                    .asBitmap()
                                    .placeholder(R.mipmap.ic_head_circle_default_small_)
                                    .thumbnail(0.1f)
                                    .into(imageHead);
                            imgLevelIcon.setImageResource(LevelIcon[customer.getCreditLevel()]);
                        }
                }else if(loginWithInfoBean.getStatus() == 701){
                    if(tvUserName == null) {
                        return;
                    }
                    tvUserName.setText("");
                    tvCreditRating.setText("积分："+"");
                    tvBouns.setText("0 ");//钱包金额
                    imgLevelIcon.setImageResource(LevelIcon[0]);
                    requestManager
                            .load("")
                            .asBitmap()
                            .placeholder(R.mipmap.ic_head_circle_default_small_)
                            .thumbnail(0.1f)
                            .into(imageHead);
                }
            }
        });
        super.onResume();
    }
}
