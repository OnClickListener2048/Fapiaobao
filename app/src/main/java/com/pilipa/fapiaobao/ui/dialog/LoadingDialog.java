package com.pilipa.fapiaobao.ui.dialog;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.pilipa.fapiaobao.R;

/**
 * Created by edz on 2017/12/26.
 */

public class LoadingDialog extends ProgressDialog {

    private TextView tv_description;
    private AnimationDrawable ad;

    public LoadingDialog(Context context) {
        super(context);
    }

    public LoadingDialog(Context context, int theme) {
        super(context, theme);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init(getContext());
    }

    private void init(Context context) {
        setCancelable(true);
        setCanceledOnTouchOutside(false);
        setContentView(R.layout.layout_loading);//loading的xml文件
        WindowManager.LayoutParams params = getWindow().getAttributes();
        params.width = WindowManager.LayoutParams.WRAP_CONTENT;
        params.height = WindowManager.LayoutParams.WRAP_CONTENT;
        getWindow().setAttributes(params);
        tv_description = (TextView) findViewById(R.id.tv_load_dialog);
        ImageView imageLoading = (ImageView) findViewById(R.id.pb_load);
        ad = (AnimationDrawable) imageLoading.getDrawable();
    }
    @Override
    public void show() {//开启
        super.show();
        if (ad!= null) {
        ad.start();
        }
    }
    @Override
    public void dismiss() {//关闭
        super.dismiss();
        if (ad!= null) {
        ad.stop();
        }
    }

    public void setMessage(String message) {
        if (tv_description!= null) {
            tv_description.setText(message);
        }
    }


}
