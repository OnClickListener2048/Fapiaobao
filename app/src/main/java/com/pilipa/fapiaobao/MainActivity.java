package com.pilipa.fapiaobao;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.DownloadListener;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.just.library.AgentWeb;
import com.just.library.ChromeClientCallbackManager;
import com.pilipa.fapiaobao.zxing.android.CaptureActivity;
import com.tencent.tauth.Tencent;

public class MainActivity extends AppCompatActivity implements View.OnClickListener,DownloadListener {

    private Button scanBtn;
    private TextView resultTv;
    private static final String DECODED_CONTENT_KEY = "codedContent";
    private static final String DECODED_BITMAP_KEY = "codedBitmap";


    private static final int REQUEST_CODE_SCAN = 0x0000;

    private AgentWeb.PreAgentWeb mAgentWeb;
    private ChromeClientCallbackManager.ReceivedTitleCallback mCallback = new ChromeClientCallbackManager.ReceivedTitleCallback() {
        @Override
        public void onReceivedTitle(WebView view, String title) {

        }
    };
    private ViewGroup mLinearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();

        // AgentWeb 保持了 WebView 的使用 ，
        mLinearLayout = (ViewGroup) findViewById(R.id.ll);
        mAgentWeb = AgentWeb.with(this)//
                .setAgentWebParent(mLinearLayout, new LinearLayout.LayoutParams(-1, -1))//
                .useDefaultIndicator()//
                .defaultProgressBarColor()
                .setReceivedTitleCallback(mCallback)
                .setWebChromeClient(mWebChromeClient)
                .setWebViewClient(mWebViewClient)
                .setSecutityType(AgentWeb.SecurityType.strict)
                .createAgentWeb()//
                .ready();


    }

    //WebViewClient
    private WebViewClient mWebViewClient = new WebViewClient() {
        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            //do you  work
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
//            view.loadUrl("javascript:(function() { document.getElementById('invoiceTitle').value = 'adasdadadsadsada'; })()");
//            view.loadUrl("javascript:(function() { var formList = document.getElementsByTagName('form');" +
//                    " var inputList = document.getElementsByTagName('input');"+
//                    "document.getElementById('taxpayerNumber').value = inputList.length;" +
//                    "for (var i=0;i<inputList.length;i++) {" +
//                    "            var element = inputList[i];" +
//                    "            element.value = '2131231fsdfaf'" +
//                    "        } })()");
        }

        @Override
        public void onLoadResource(WebView view, String url) {
            super.onLoadResource(view, url);
        }
    };
    //WebChromeClient
    private WebChromeClient mWebChromeClient = new WebChromeClient() {
        @Override
        public void onProgressChanged(WebView view, int newProgress) {
            //do you work
        }


    };
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



    private void initView() {
        scanBtn = (Button) findViewById(R.id.scanBtn);
        resultTv = (TextView) findViewById(R.id.resultTv);

        scanBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.scanBtn:

                startActivityForResult(new Intent(this, CaptureActivity.class), REQUEST_CODE_SCAN);
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
                mAgentWeb.go(content);
            }
        }
    }


    @Override
    public void onDownloadStart(String url, String userAgent, String contentDisposition, String mimetype, long contentLength) {
        Toast.makeText(this, "start download", Toast.LENGTH_SHORT).show();
    }
}
