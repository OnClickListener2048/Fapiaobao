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


/**
 * Created by edz on 2018/1/11.
 */

public class DialogUtil {
    private static DialogUtil dialogUtil;
    private LinearLayout mRoot;

    private DialogUtil() {

    }

    public static DialogUtil getInstance() {
        if (dialogUtil == null) {
            dialogUtil = new DialogUtil();
        }
        return dialogUtil;
    }

    public View getRootView() {
        return mRoot;
    }

    public void setRootContentText(int id, String s) {
        if (mRoot != null) {
            TextView textView = (TextView) mRoot.findViewById(id);
            textView.setText(s);
        }
    }

    public Dialog createDialog(Context context, int style, int layoutRes, final OnKnownListener onKnownListener, final OnConfirmListener onConfirmListener, final OnCancelListener onCancelListener) {
        Dialog dialog = new Dialog(context, R.style.BottomDialog);
        mRoot = (LinearLayout) LayoutInflater.from(context).inflate(
                layoutRes, null);
        View iKnow = mRoot.findViewById(R.id.i_know);
        if (iKnow != null) {
            iKnow.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onKnownListener != null) {

                        onKnownListener.onKnown(v);
                    }
                }
            });
        }
        View confirm = mRoot.findViewById(R.id.confirm);
        if (confirm != null) {
            confirm.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onConfirmListener != null) {

                        onConfirmListener.onConfirm(v);
                    }
                }
            });
        }
        View cancel = mRoot.findViewById(R.id.cancel);
        if (cancel != null) {
            cancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onCancelListener != null) {

                        onCancelListener.onCancel(v);
                    }
                }
            });
        }
        setContentView(dialog, mRoot);
        return dialog;
    }

    public Dialog createBottomDialog(Context context,
                                     final OnDialogDismissListener onDialogDismissListener,
                                     final OnMediaOpenListener onMediaOpenListener,
                                     final OnReceiptFolderOpenListener onReceiptFolderOpenListener,
                                     final OnPhotoTakeListener onPhotoTakeListener,
                                     final OnShoppingStampOpenListener onShoppingStampOpenListener) {
        Dialog dialog = new Dialog(context, R.style.bottomDialog);
        LinearLayout root;
        root = (LinearLayout) LayoutInflater.from(context).inflate(
                R.layout.dialog_bottom, null);
        View cancel = root.findViewById(R.id.btn_cancel);
        View takePhoto = root.findViewById(R.id.btn_open_camera);
        View openMeida = root.findViewById(R.id.btn_choose_img);
        View receiptFolder = root.findViewById(R.id.btn_choose_img_receipt_folder);
        View shoppingStamp = root.findViewById(R.id.btn_shopping_stamp);
        if (onDialogDismissListener == null) {
            cancel.setVisibility(View.GONE);
        } else {
            cancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onDialogDismissListener.onDialogDismiss(v);
                }
            });
        }

        if (onMediaOpenListener == null) {
            openMeida.setVisibility(View.GONE);
        } else {
            openMeida.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onMediaOpenListener.onMediaOpen(v);
                }
            });
        }

        if (onPhotoTakeListener == null) {
            takePhoto.setVisibility(View.GONE);
        } else {
            takePhoto.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onPhotoTakeListener.onPhotoTake(v);
                }
            });
        }

        if (onReceiptFolderOpenListener == null) {
            receiptFolder.setVisibility(View.GONE);
        } else {
            receiptFolder.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onReceiptFolderOpenListener.onReceiptFolderOpen(v);
                }
            });
        }

        if (onShoppingStampOpenListener == null) {
            shoppingStamp.setVisibility(View.GONE);
        } else {
            shoppingStamp.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onShoppingStampOpenListener.onShoppingStampOpen(v);
                }
            });
        }


        setContentView(dialog, root, Gravity.BOTTOM);
        return dialog;
    }

    private void setContentView(Dialog dialog, View root, int bottom) {
        dialog.setContentView(root);
        Window dialogWindow = dialog.getWindow();
        if (dialogWindow == null) return;
        dialogWindow.setGravity(bottom);
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        lp.x = 0;
        lp.y = 0;
        lp.width = BaseApplication.context().getResources().getDisplayMetrics().widthPixels;
        root.measure(0, 0);
        lp.height = root.getMeasuredHeight();
        lp.alpha = 9f;
        dialogWindow.setAttributes(lp);
    }


    private void setContentView(Dialog dialog, View rootView) {
        dialog.setCanceledOnTouchOutside(false);
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

    public interface OnDialogDismissListener {
        void onDialogDismiss(View view);
    }

    public interface OnMediaOpenListener {
        void onMediaOpen(View view);
    }

    public interface OnReceiptFolderOpenListener {
        void onReceiptFolderOpen(View view);
    }

    public interface OnPhotoTakeListener {
        void onPhotoTake(View view);
    }

    public interface OnShoppingStampOpenListener {
        void onShoppingStampOpen(View view);
    }


    public interface OnBottonDialogItemClickListener {
        void onDialogDismiss(View view);

        void onMediaOpen(View view);

        void onPhotoTake(View view);
    }


}
