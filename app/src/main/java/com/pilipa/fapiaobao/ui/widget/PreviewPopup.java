package com.pilipa.fapiaobao.ui.widget;

import android.app.Activity;
import android.graphics.drawable.ColorDrawable;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.example.mylibrary.utils.ScreenUtils;
import com.pilipa.fapiaobao.R;
import com.pilipa.fapiaobao.ui.DemandsPublishLocationActivity;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by edz on 2018/1/17.
 */

public class PreviewPopup extends PopupWindow implements PopupWindow.OnDismissListener {


    private final DemandsPublishLocationActivity activity;
    @Bind(R.id.company_name)
    TextView mCompanyName;
    @Bind(R.id.tex_number)
    TextView mTexNumber;
    @Bind(R.id.company_address)
    TextView mCompanyAddress;
    @Bind(R.id.phone_number)
    TextView mPhoneNumber;
    @Bind(R.id.bank_name)
    TextView mBankName;
    @Bind(R.id.bank_account)
    TextView mBankAccount;
    @Bind(R.id.redundant_info)
    LinearLayout mRedundantInfo;
    @Bind(R.id.tv_invoice_type)
    TextView mTvInvoiceType;
    @Bind(R.id.tv_tex_kind)
    TextView mTvTexKind;
    @Bind(R.id.tv_deadline)
    TextView mTvDeadline;
    @Bind(R.id.tv_amount)
    TextView mTvAmount;
    @Bind(R.id.tv_redbag_amount)
    TextView mTvRedbagAmount;
    @Bind(R.id.tv_available_balance)
    TextView mTvAvailableBalance;
    @Bind(R.id.go_recharge)
    TextView mGoRecharge;
    @Bind(R.id.tv_area)
    TextView mTvArea;
    @Bind(R.id.tv_cautions)
    TextView mTvCautions;
    @Bind(R.id.tv_ensure_to_publish)
    TextView mTvEnsureToPublish;
    @Bind(R.id.tv_back_to_revise)
    TextView mTvBackToRevise;
    @Bind(R.id.ll_company_address)
    LinearLayout mLlCompanyAddress;
    @Bind(R.id.ll_phone_number)
    LinearLayout mLlPhoneNumber;
    @Bind(R.id.ll_bank_name)
    LinearLayout mLlBankName;
    @Bind(R.id.ll_bank_account)
    LinearLayout mLlBankAccount;
    @Bind(R.id.ll_is_available_balance_sufficient)
    LinearLayout mLlIsAvailableBalanceSufficient;
    @Bind(R.id.ll_express_info)
    LinearLayout mLlExpressInfo;
    @Bind(R.id.tv_express_way)
    TextView mTvExpressWay;
    @Bind(R.id.tv_recipients)
    TextView mTvRecipients;
    @Bind(R.id.tv_contact)
    TextView mTvContact;
    @Bind(R.id.tv_address_details)
    TextView mTvAddressDetails;
    @Bind(R.id.tv_express_limited)
    TextView mTvExpressLimited;
    @Bind(R.id.ll_express_limited)
    LinearLayout mLlExpressLimited;

    private PreviewPopup(PopupBuilder popupBuilder) {
        super(popupBuilder.getActivity());
        this.activity = (DemandsPublishLocationActivity) popupBuilder.getActivity();
        initView(popupBuilder);
    }

    private void initView(PopupBuilder popupBuilder) {
        LayoutInflater inflater = LayoutInflater.from(popupBuilder.getActivity());
        View contentView = inflater.inflate(R.layout.popup_publish_preview, null);
        this.setContentView(contentView);
        ButterKnife.bind(this, contentView);
        int h = ScreenUtils.getScreenHeight();
        int w = ScreenUtils.getScreenWidth();
        // 设置SelectPicPopupWindow的View
        // 设置SelectPicPopupWindow弹出窗体的宽
        this.setWidth(w * 4 / 5);
        // 设置SelectPicPopupWindow弹出窗体的高
        this.setHeight(h * 4 / 5);
        // 设置SelectPicPopupWindow弹出窗体可点击
        this.setFocusable(false);
        this.setOutsideTouchable(false);
        // 刷新状态
        this.update();
        // 实例化一个ColorDrawable颜色为半透明
        ColorDrawable dw = new ColorDrawable();
        // 点back键和其他地方使其消失,设置了这个才能触发OnDismisslistener ，设置其他控件变化等操作
        this.setBackgroundDrawable(dw);
        setAnimationStyle(R.style.previewPopupAnimation);
        setOnDismissListener(this);
        initData(popupBuilder);
    }

    private void initData(final PopupBuilder popupBuilder) {
        mCompanyName.setText(popupBuilder.getCompanyName());
        mTexNumber.setText(popupBuilder.getTexNumber());
        handleData(popupBuilder.getCompanyAddress(), mLlCompanyAddress, mCompanyAddress);
        handleData(popupBuilder.getPhoneNumber(), mLlPhoneNumber, mPhoneNumber);
        handleData(popupBuilder.getDepositBank(), mLlBankName, mBankName);
        handleData(popupBuilder.getDepositBankAccount(), mLlBankAccount, mBankAccount);
        mTvInvoiceType.setText(popupBuilder.getInvoiceKind());
        mTvTexKind.setText(popupBuilder.getInvoiceType());
        mTvDeadline.setText(popupBuilder.getDeadline());
        mTvAmount.setText(activity.getString(R.string.suffix_yuan, popupBuilder.getDemandAmount()));
        mTvRedbagAmount.setText(checkEmpty(popupBuilder.getBonus()) ? "无" : activity.getString(R.string.suffix_yuan, popupBuilder.getBonus()));
        mTvAvailableBalance.setText(checkEmpty(popupBuilder.getAvailableBalance()) ? "0元" : activity.getString(R.string.suffix_yuan, popupBuilder.getAvailableBalance()));
        mLlIsAvailableBalanceSufficient.setVisibility(popupBuilder.isBalanceSufficient() ? View.GONE : View.VISIBLE);
        if (!popupBuilder.isBalanceSufficient()) {
            mLlIsAvailableBalanceSufficient.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    popupBuilder.getOnBalanceInsufficientListener().onBalanceInsufficient();
                }
            });
        }
        mTvArea.setText(checkEmpty(popupBuilder.getInvoiceArea()) ? "无" : popupBuilder.getInvoiceArea());
        if (popupBuilder.getIsShowExpressInfo()) {
            mLlExpressInfo.setVisibility(View.VISIBLE);
            mTvExpressWay.setText(activity.getString(R.string.cod));
            mTvRecipients.setText(popupBuilder.getReceiption());
            mTvAddressDetails.setText(popupBuilder.getReceiptionAddress());
            mTvContact.setText(popupBuilder.getReceiptionPhoneNumber());
        } else {
            mLlExpressInfo.setVisibility(View.GONE);
        }
        mTvCautions.setText(checkEmpty(popupBuilder.getCautions()) ? "无" : popupBuilder.getCautions());
        mTvBackToRevise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupBuilder.getOnBackToReviseListener().onBackToRevice();
            }
        });

        mTvEnsureToPublish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupBuilder.getOnPublishListener().onPublish();
            }
        });
        mLlExpressLimited.setVisibility(popupBuilder.isShowExpressLimited() ? View.VISIBLE : View.GONE);
        if (checkEmpty(popupBuilder.getExpressLimited())) {
            mTvExpressLimited.setText("无");
        } else {
            mTvExpressLimited.setText(activity.getString(R.string.suffix_yuan, popupBuilder.getExpressLimited()));
        }

    }

    private void handleData(String content, ViewGroup viewGroup, TextView textView) {
        if (checkEmpty(content)) {
            viewGroup.setVisibility(View.GONE);
        } else {
            viewGroup.setVisibility(View.VISIBLE);
            textView.setText(content);
        }

    }

    private boolean checkEmpty(String string) {
        return TextUtils.isEmpty(string);
    }

    public void showPopupWindow(DemandsPublishLocationActivity activity) {
        if (!this.isShowing()) {
            activity.mBg.setVisibility(View.VISIBLE);
            activity.mBg.startAnimation(AnimationUtils.loadAnimation(activity, R.anim.anim_popup_preview_bg_in));
            showAtLocation(activity.getWindow().getDecorView(), Gravity.CENTER, 0, 0);
        } else {
            this.dismiss();
        }
    }


    @Override
    public void onDismiss() {
        activity.mBg.startAnimation(AnimationUtils.loadAnimation(activity, R.anim.anim_popup_preview_bg_out));
        activity.mBg.setVisibility(View.GONE);
    }

    public interface OnPublishListener {
        void onPublish();
    }

    public interface OnBackToReviseListener {
        void onBackToRevice();
    }

    public interface OnBalanceInsufficientListener {
        void onBalanceInsufficient();
    }

    public static class PopupBuilder {
        private Activity mActivity;
        private String mCompanyName;
        private String mTexNumber;
        private String mCompanyAddress;
        private String mPhoneNumber;
        private String mDepositBank;
        private String mDepositBankAccount;
        private String mInvoiceKind;
        private String mInvoiceType;
        private String mDeadline;
        private String mDemandAmount;
        private String mBonus;
        private String mAvailableBalance;
        private boolean mIsBalanceSufficient;
        private String mInvoiceArea;
        private boolean mIsShowExpressInfo;
        private String mExpressWay;
        private String mReceiption;
        private String mReceiptionPhoneNumber;
        private String mReceiptionAddress;
        private String mCautions;
        private OnBackToReviseListener mOnBackToReviseListener;
        private OnPublishListener mOnPublishListener;
        private OnBalanceInsufficientListener mOnBalanceInsufficientListener;
        private boolean mIsShowExpressLimited;
        private String mExpressLimited;

        public PopupBuilder(Activity activity) {
            mActivity = activity;
        }

        public boolean isShowExpressLimited() {
            return mIsShowExpressLimited;
        }

        public PreviewPopup.PopupBuilder setShowExpressLimited(boolean showExpressLimited) {
            mIsShowExpressLimited = showExpressLimited;
            return this;
        }

        public String getExpressLimited() {
            return mExpressLimited;
        }

        public PopupBuilder setExpressLimited(String expressLimited) {
            mExpressLimited = expressLimited;
            return this;
        }

        public OnBalanceInsufficientListener getOnBalanceInsufficientListener() {
            return mOnBalanceInsufficientListener;
        }

        public PopupBuilder setOnBalanceInsufficientListener(OnBalanceInsufficientListener onBalanceInsufficientListener) {
            mOnBalanceInsufficientListener = onBalanceInsufficientListener;
            return this;
        }

        public OnBackToReviseListener getOnBackToReviseListener() {
            return mOnBackToReviseListener;
        }

        public PopupBuilder setOnBackToReviseListener(OnBackToReviseListener onBackToReviseListener) {
            mOnBackToReviseListener = onBackToReviseListener;
            return this;
        }

        public OnPublishListener getOnPublishListener() {
            return mOnPublishListener;
        }

        public PopupBuilder setOnPublishListener(OnPublishListener onPublishListener) {
            mOnPublishListener = onPublishListener;
            return this;
        }

        public String getCompanyName() {
            return mCompanyName;
        }

        public PopupBuilder setCompanyName(String companyName) {
            mCompanyName = companyName;
            return this;
        }

        public String getTexNumber() {
            return mTexNumber;
        }

        public PopupBuilder setTexNumber(String texNumber) {
            mTexNumber = texNumber;
            return this;
        }

        public String getCompanyAddress() {
            return mCompanyAddress;
        }

        public PopupBuilder setCompanyAddress(String companyAddress) {
            mCompanyAddress = companyAddress;
            return this;
        }

        public String getPhoneNumber() {
            return mPhoneNumber;
        }

        public PopupBuilder setPhoneNumber(String phoneNumber) {
            mPhoneNumber = phoneNumber;
            return this;
        }

        public String getDepositBank() {
            return mDepositBank;
        }

        public PopupBuilder setDepositBank(String depositBank) {
            mDepositBank = depositBank;
            return this;
        }

        public String getDepositBankAccount() {
            return mDepositBankAccount;
        }

        public PopupBuilder setDepositBankAccount(String depositBankAccount) {
            mDepositBankAccount = depositBankAccount;
            return this;
        }

        public String getInvoiceKind() {
            return mInvoiceKind;
        }

        public PopupBuilder setInvoiceKind(String invoiceKind) {
            mInvoiceKind = invoiceKind;
            return this;
        }

        public String getInvoiceType() {
            return mInvoiceType;
        }

        public PopupBuilder setInvoiceType(String invoiceType) {
            mInvoiceType = invoiceType;
            return this;
        }

        public String getDeadline() {
            return mDeadline;
        }

        public PopupBuilder setDeadline(String deadline) {
            mDeadline = deadline;
            return this;
        }

        public String getDemandAmount() {
            return mDemandAmount;
        }

        public PopupBuilder setDemandAmount(String demandAmount) {
            mDemandAmount = demandAmount;
            return this;
        }

        public String getBonus() {
            return mBonus;
        }

        public PopupBuilder setBonus(String bonus) {
            mBonus = bonus;
            return this;
        }

        public String getAvailableBalance() {
            return mAvailableBalance;
        }

        public PopupBuilder setAvailableBalance(String availableBalance) {
            mAvailableBalance = availableBalance;
            return this;
        }

        public boolean isBalanceSufficient() {
            return mIsBalanceSufficient;
        }

        public PopupBuilder setBalanceSufficient(boolean balanceSufficient) {
            mIsBalanceSufficient = balanceSufficient;
            return this;
        }

        public String getInvoiceArea() {
            return mInvoiceArea;
        }

        public PopupBuilder setInvoiceArea(String invoiceArea) {
            mInvoiceArea = invoiceArea;
            return this;
        }

        public boolean getIsShowExpressInfo() {
            return mIsShowExpressInfo;
        }

        public PopupBuilder setIsShowExpressInfo(boolean isShowExpressInfo) {
            mIsShowExpressInfo = isShowExpressInfo;
            return this;
        }

        public String getExpressWay() {
            return mExpressWay;
        }

        public PopupBuilder setExpressWay(String expressWay) {
            mExpressWay = expressWay;
            return this;
        }

        public String getReceiption() {
            return mReceiption;
        }

        public PopupBuilder setReceiption(String receiption) {
            mReceiption = receiption;
            return this;
        }

        public String getReceiptionPhoneNumber() {
            return mReceiptionPhoneNumber;
        }

        public PopupBuilder setReceiptionPhoneNumber(String receiptionPhoneNumber) {
            mReceiptionPhoneNumber = receiptionPhoneNumber;
            return this;
        }

        public String getReceiptionAddress() {
            return mReceiptionAddress;
        }

        public PopupBuilder setReceiptionAddress(String receiptionAddress) {
            mReceiptionAddress = receiptionAddress;
            return this;
        }

        public String getCautions() {
            return mCautions;
        }

        public PopupBuilder setCautions(String cautions) {
            mCautions = cautions;
            return this;
        }

        public Activity getActivity() {
            return mActivity;
        }

        public PreviewPopup build() {
            return new PreviewPopup(this);
        }
    }


}
