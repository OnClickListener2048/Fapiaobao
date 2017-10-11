package com.pilipa.fapiaobao;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.mylibrary.utils.TLog;
import com.pilipa.fapiaobao.Constants.TencentConstant;
import com.pilipa.fapiaobao.zxing.android.CaptureActivity;
import com.tencent.bugly.crashreport.CrashReport;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.Tencent;
import com.tencent.tauth.UiError;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private Button scanBtn;
    private TextView resultTv;
    private static final String DECODED_CONTENT_KEY = "codedContent";
    private static final String DECODED_BITMAP_KEY = "codedBitmap";


    private static final int REQUEST_CODE_SCAN = 0x0000;
    private Tencent mTencent;
    private String Scope = "all";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTencent = Tencent.createInstance(TencentConstant.APP_ID, this.getApplicationContext());
        initView();

    }

//    {
//        "ret":0,
//            "pay_token":"xxxxxxxxxxxxxxxx",
//            "pf":"openmobile_android",
//            "expires_in":"7776000",
//            "openid":"xxxxxxxxxxxxxxxxxxx",
//            "pfkey":"xxxxxxxxxxxxxxxxxxx",
//            "msg":"sucess",
//            "access_token":"xxxxxxxxxxxxxxxxxxxxx"
//    }

    public void login()
    {
        mTencent = Tencent.createInstance(TencentConstant.APP_ID, this.getApplicationContext());
        if (!mTencent.isSessionValid())
        {
            mTencent.login(this, Scope, new IUiListener() {
                @Override
                public void onComplete(Object o) {
                    JSONObject jsonObject = (JSONObject) o;
                    try {
                        String ret = (String) jsonObject.get("ret");
                        String pf = (String) jsonObject.get("pf");
                        String expires_in = (String) jsonObject.get("expires_in");
                        String openid = (String) jsonObject.get("openid");
                        String pfkey = (String) jsonObject.get("pfkey");
                        String msg = (String) jsonObject.get("msg");
                        String access_token = (String) jsonObject.get("access_token");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    TLog.log("onComplete ");
                }

                @Override
                public void onError(UiError uiError) {
                    TLog.log("onError");
                }

                @Override
                public void onCancel() {
                    TLog.log("onCancel");
                }
            });
        }
    }

    private void initView() {
        scanBtn = (Button) findViewById(R.id.scanBtn);
        resultTv = (TextView) findViewById(R.id.resultTv);

        scanBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.scanBtn:

                login();

                break;
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // 扫描二维码/条码回传
        if (requestCode == REQUEST_CODE_SCAN && resultCode == RESULT_OK) {
            if (data != null) {

                String content = data.getStringExtra(DECODED_CONTENT_KEY);
                Bitmap bitmap = data.getParcelableExtra(DECODED_BITMAP_KEY);

                resultTv.setText("解码结果： \n" + content);

            }
        }
    }
}
