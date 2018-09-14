package com.pilipa.fapiaobao.ui.zxing;

import android.content.Intent;
import android.text.TextUtils;

import com.pilipa.fapiaobao.R;
import com.pilipa.fapiaobao.base.BaseApplication;

import io.github.xudaojie.qrcodelib.CaptureActivity;

/**
 * Created by edz on 2018/1/25.
 */

public class SimpleCaptureActivity extends CaptureActivity {

    @Override
    protected void handleResult(String resultString) {
        super.handleResult(resultString);
        if (TextUtils.isEmpty(resultString)) {
            BaseApplication.showToast(R.string.scan_failed);
            restartPreview();
        } else {
            Intent intent = getIntent();
            intent.putExtra("codedContent", resultString);
            intent.putExtra("codedBitmap", resultString);
            setResult(RESULT_OK, intent);
            finish();
        }
    }
}
