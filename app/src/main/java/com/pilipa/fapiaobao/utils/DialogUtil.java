package com.pilipa.fapiaobao.utils;

import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.pilipa.fapiaobao.R;
import com.pilipa.fapiaobao.base.BaseApplication;
import com.pilipa.fapiaobao.ui.DemandsDetailsPreviewActivity;


/**
 * Created by edz on 2018/1/11.
 */

public class DialogUtil {
    private static DialogUtil dialogUtil;

    private DialogUtil() {

    }

    public static DialogUtil getInstance() {
        if (dialogUtil == null) {
            dialogUtil = new DialogUtil();
        }
        return dialogUtil;
    }

    public Dialog createExpressDialog(Context context, final OnKnownListener onKnownListener) {
        Dialog dialog = new Dialog(context, R.style.BottomDialog);
        LinearLayout root;
        root = (LinearLayout) LayoutInflater.from(context).inflate(
                R.layout.dialog_expressing, null);
        root.findViewById(R.id.i_know).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onKnownListener.onKnown(v);
            }
        });
        setContentView(dialog, root);
        return dialog;
    }

    public Dialog createScanDialog(Context context, final OnKnownListener onKnownListener) {
        final Dialog scanDialog = new Dialog(context, R.style.BottomDialog);
        LinearLayout root = (LinearLayout) LayoutInflater.from(context).inflate(
                R.layout.layout_scan_tip, null);
        TextView tv = (TextView) root.findViewById(R.id.scan_tip);
        tv.setText("添加单位信息，目前仅支持发票宝生成的单位信息二维码的扫描");
        //初始化视图
        root.findViewById(R.id.btn_confirm).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onKnownListener.onKnown(v);
            }
        });
        setContentView(scanDialog, root);
        return scanDialog;
    }

    public Dialog createDeleteCompanyDialog(Context context, final OnConfirmListener onConfirmListener, final OnCancelListener onCancelListener) {
        final Dialog mDialog = new Dialog(context, R.style.BottomDialog);
        LinearLayout root;
        root = (LinearLayout) LayoutInflater.from(context).inflate(
                R.layout.layout_delete_tip, null);
        root.findViewById(R.id.btn_cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onCancelListener.onCancel(v);
            }
        });
        root.findViewById(R.id.btn_confirm).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onConfirmListener.onConfirm(v);
            }
        });
        setContentView(mDialog, root);
        return mDialog;
    }

    public Dialog canShutDownEarlyDialog(Context context, final OnCancelListener onCancelListener, final OnConfirmListener onConfirmListener) {
        Dialog mDialog = new Dialog(context, R.style.BottomDialog);
        LinearLayout root;
        root = (LinearLayout) LayoutInflater.from(context).inflate(
                R.layout.layout_shutdown1_tip, null);
        root.findViewById(R.id.btn_cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onCancelListener.onCancel(v);
            }
        });
        root.findViewById(R.id.btn_confirm).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onConfirmListener.onConfirm(v);
            }
        });

        setContentView(mDialog, root);
        return mDialog;
    }

    public Dialog canNotShutDownEarlyDialog(Context context, final OnKnownListener onKnownListener) {
        Dialog mDialog = new Dialog(context, R.style.BottomDialog);
        LinearLayout root;
        root = (LinearLayout) LayoutInflater.from(context).inflate(
                R.layout.layout_shutdown2_tip, null);
        root.findViewById(R.id.btn_confirm).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onKnownListener.onKnown(v);
            }
        });
        setContentView(mDialog, root);
        return mDialog;
    }

    public Dialog rejectionStep1Dialog(Context context) {
        Dialog mDialog = new Dialog(this, R.style.BottomDialog);
        LinearLayout root = null;
        if (step == REJECT_START) {
            changeLayout();
            root = (LinearLayout) LayoutInflater.from(this).inflate(
                    R.layout.layout_reject1_tip, null);
            root.findViewById(R.id.btn_confirm).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mDialog.dismiss();
                }
            });
        } else if (step == REJECT_FINISH) {
            root = (LinearLayout) LayoutInflater.from(this).inflate(
                    R.layout.layout_reject2_tip, null);
            root.findViewById(R.id.btn_finish).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    DemandsDetailsPreviewActivity.this.finish();
                }
            });
        }
        //初始化视图
        mDialog.setContentView(root);
        mDialog.setCanceledOnTouchOutside(false);
        Window dialogWindow = mDialog.getWindow();
        dialogWindow.setGravity(Gravity.CENTER);
//        dialogWindow.setWindowAnimations(R.style.dialogstyle); // 添加动画
        WindowManager.LayoutParams lp = dialogWindow.getAttributes(); // 获取对话框当前的参数值
        lp.x = 0; // 新位置X坐标
        lp.y = 0; // 新位置Y坐标
        lp.width = (int) BaseApplication.context().getResources().getDisplayMetrics().widthPixels; // 宽度
        root.measure(0, 0);
        lp.height = root.getMeasuredHeight();

        lp.alpha = 9f; // 透明度
        dialogWindow.setAttributes(lp);
        mDialog.show();//TODO ?
    }

    public Dialog rejectionStep2Dialog(Context context) {

    }


    private void setContentView(Dialog dialog, View rootView) {
        dialog.setContentView(rootView);
        Window dialogWindow = dialog.getWindow();
        dialogWindow.setGravity(Gravity.CENTER);
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        lp.x = 0;
        lp.y = 0;
        lp.width = BaseApplication.context().getResources().getDisplayMetrics().widthPixels;
        rootView.measure(0, 0);
        lp.height = rootView.getMeasuredHeight();
        lp.alpha = 9f;
        dialogWindow.setAttributes(lp);
    }

    public interface OnKnownListener {
        void onKnown(View view);
    }

    public interface OnConfirmListener {
        void onConfirm(View view);
    }

    public interface OnCancelListener {
        void onCancel(View view);
    }



}
