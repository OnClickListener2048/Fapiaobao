package com.pilipa.fapiaobao.ui;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.DownloadListener;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mylibrary.utils.TLog;
import com.just.library.AgentWeb;
import com.just.library.ChromeClientCallbackManager;
import com.just.library.DownLoadResultListener;
import com.pilipa.fapiaobao.R;
import com.pilipa.fapiaobao.utils.WebViewUtils;
import com.pilipa.fapiaobao.zxing.android.CaptureActivity;

/**
 * Created by lyt on 2017/10/13.
 */

public class Op extends AppCompatActivity implements
        View.OnClickListener
        ,DownloadListener
        , ChromeClientCallbackManager.ReceivedTitleCallback, DownLoadResultListener {

    private ViewGroup mLinearLayout;
    private Button scanBtn;
    private TextView resultTv;
    private static final String DECODED_CONTENT_KEY = "codedContent";
    private static final String DECODED_BITMAP_KEY = "codedBitmap";
    private static final int REQUEST_CODE_SCAN = 0x0000;

    WebViewClient webViewClient = new WebViewClient() {
        @Override
        public void onLoadResource(WebView view, String url) {
            super.onLoadResource(view, url);
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

            view.loadUrl("javascript:;(function(currentRules){\n" +
                    "            var jQueryUrl = 'https://cdn.bootcss.com/jquery/1.8.3/jquery.min.js';\n" +
                    "            function writeValue(conf) {\n" +
                    "                if(conf===undefined) {return;}\n" +
                    "                $(conf.selector).val(conf.value);\n" +
                    "            }\n" +
                    "            function injectScript(url, cb) {\n" +
                    "                url = url || jQueryUrl;\n" +
                    "                cb = cb || function(){};\n" +
                    "                var script = document.createElement('script');\n" +
                    "                script.src = url;\n" +
                    "                document.head.appendChild(script);\n" +
                    "                script.onload = function(){cb()}\n" +
                    "            }\n" +
                    "            function setFormVals() {\n" +
                    "                $.each(currentRules, function (index, item) {\n" +
                    "                    writeValue(item);\n" +
                    "                })\n" +
                    "            }\n" +
                    "            if(!window.$ && !window.jQuery) {\n" +
                    "                injectScript(jQueryUrl, setFormVals);\n" +
                    "            } else {\n" +
                    "                setFormVals();\n" +
                    "            }\n" +
                    "        })([\n" +
                    "            {selector:'#invoiceTitle', value:'抬头'},\n" +
                    "            {selector:'#taxpayerNumber', value:'税号'},\n" +
                    "            {selector:'#mailAccount', value:'邮箱'},\n" +
                    "            {selector:'#abc', value:'4444'}\n" +
                    "        ]);");
        }
    };

    WebChromeClient webChromeClient = new WebChromeClient(){
        @Override
        public boolean onJsAlert(WebView view, String url, String message, JsResult result) {
            return super.onJsAlert(view, url, message, result);
        }
    };
    private AgentWeb.PreAgentWeb preAgentWeb;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();

        // AgentWeb 保持了 WebView 的使用 ，
        mLinearLayout = (ViewGroup) findViewById(R.id.ll);


        preAgentWeb = WebViewUtils.init(this, mLinearLayout, this, webViewClient, webChromeClient,this);


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
                preAgentWeb.go(content);
            }
        }
    }

    /**
     * 监听下载
     * @param url
     * @param userAgent
     * @param contentDisposition
     * @param mimetype
     * @param contentLength
     */
    @Override
    public void onDownloadStart(String url, String userAgent, String contentDisposition, String mimetype, long contentLength) {
        Toast.makeText(this, "start download", Toast.LENGTH_SHORT).show();
    }

    /**
     * 接收到webview title
     * @param view
     * @param title
     */
    @Override
    public void onReceivedTitle(WebView view, String title) {
        TLog.log(title);
    }

    /**
     * 文件下载成功
     * @param path
     */
    @Override
    public void success(String path) {
        TLog.log(path);
    }

    /**
     * 文件下载失败
     * @param path
     * @param resUrl
     * @param cause
     * @param e
     */
    @Override
    public void error(String path, String resUrl, String cause, Throwable e) {
        TLog.log(path);
        TLog.log(resUrl);
        TLog.log(cause);

    }
}
