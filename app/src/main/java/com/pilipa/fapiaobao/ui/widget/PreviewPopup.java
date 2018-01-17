package com.pilipa.fapiaobao.ui.widget;

import android.app.Activity;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.example.mylibrary.utils.ScreenUtils;
import com.pilipa.fapiaobao.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by edz on 2018/1/17.
 */

public class PreviewPopup extends PopupWindow implements PopupWindow.OnDismissListener {


    private final Activity activity;
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

    public PreviewPopup(Activity activity) {
        super(activity);
        this.activity = activity;
        initView(activity);
    }

    private void initView(Activity context) {
        LayoutInflater inflater = LayoutInflater.from(context);
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
        this.setFocusable(true);
        this.setOutsideTouchable(false);
        // 刷新状态
        this.update();
        // 实例化一个ColorDrawable颜色为半透明
        ColorDrawable dw = new ColorDrawable();
        // 点back键和其他地方使其消失,设置了这个才能触发OnDismisslistener ，设置其他控件变化等操作
        this.setBackgroundDrawable(dw);
        setAnimationStyle(R.style.DialogAnimation);
        setOnDismissListener(this);
    }

    public void showPopupWindow(Activity activity) {
        if (!this.isShowing()) {
            // 以下拉方式显示popupwindow
            WindowManager.LayoutParams lp = activity.getWindow().getAttributes();
            lp.alpha = 0.3f;
            activity.getWindow().setAttributes(lp);
            showAtLocation(activity.getWindow().getDecorView(), Gravity.CENTER, 0, 0);
        } else {
            this.dismiss();
        }
    }


    @Override
    public void onDismiss() {
        WindowManager.LayoutParams lp = activity.getWindow().getAttributes();
        lp.alpha = 1f;
        activity.getWindow().setAttributes(lp);
    }
}
