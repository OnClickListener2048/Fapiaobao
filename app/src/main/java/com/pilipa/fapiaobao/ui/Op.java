package com.pilipa.fapiaobao.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mylibrary.utils.TLog;

import com.just.agentwebX5.AgentWeb;
import com.just.agentwebX5.ChromeClientCallbackManager;
import com.just.agentwebX5.DownLoadResultListener;
import com.pilipa.fapiaobao.R;
import com.pilipa.fapiaobao.account.AccountHelper;
import com.pilipa.fapiaobao.net.bean.invoice.MacherBeanToken;
import com.pilipa.fapiaobao.utils.WebViewUtils;
import com.tencent.smtt.export.external.interfaces.JsResult;
import com.tencent.smtt.sdk.DownloadListener;
import com.tencent.smtt.sdk.QbSdk;
import com.tencent.smtt.sdk.ValueCallback;
import com.tencent.smtt.sdk.WebChromeClient;
import com.tencent.smtt.sdk.WebView;
import com.tencent.smtt.sdk.WebViewClient;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by lyt on 2017/10/13.
 */

public class Op extends AppCompatActivity implements
        DownloadListener
        , ChromeClientCallbackManager.ReceivedTitleCallback, DownLoadResultListener {

    @Bind(R.id.title)
    TextView title;
    @Bind(R.id.webView_back)
    ImageView webViewBack;
    @Bind(R.id.ll)
    LinearLayout ll;
    private  MacherBeanToken.DataBean.CompanyBean company_info;
    WebViewClient webViewClient = new WebViewClient() {
        @Override
        public void onLoadResource(WebView view, String url) {
            super.onLoadResource(view, url);
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            TLog.log("company_info"+company_info);
            if (company_info != null) {
                if (url.contains("yumchina")) {
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
                            "            {selector:'#invTitle', value:'" + company_info.getName() + "'},\n" +
                            "            {selector:'#invTaxNo', value:'" + company_info.getTaxno() + "'},\n" +
                            "            {selector:'#invAddrPhone', value:'" + company_info.getAddress() + company_info.getPhone() + "'},\n" +
                            "            {selector:'#invBank',value:'" + company_info.getDepositBank() + company_info.getAccount() + "'},\n" +
                            "            {selector:'#hxEmail',value:'" + AccountHelper.getUser().getData().getCustomer().getEmail() + "'},\n" +
                            "            {selector:'#mobile',value:'" + AccountHelper.getUserCustormer().getTelephone() + "'}\n" +
                            "        ]);");
                } else if (url.contains("starbucks")) {
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
                            "            {selector:'#invoiceTitle', value:'" + company_info.getName() + "'},\n" +
                            "            {selector:'#taxpayerNumber', value:'" + company_info.getTaxno() + "'},\n" +
                            "            {selector:'#telephoneNumber', value:'" + company_info.getPhone() + "'},\n" +
                            "            {selector:'#address', value:'" + company_info.getAddress() + "'},\n" +
                            "            {selector:'#depositBank',value:'" + company_info.getDepositBank() + "'},\n" +
                            "            {selector:'#bankAccount',value:'" + company_info.getAccount() + "'},\n" +
                            "            {selector:'#mailAccount',value:'" + AccountHelper.getUser().getData().getCustomer().getEmail() + "'},\n" +
                            "            {selector:'#cellPhoneNumber',value:'" + AccountHelper.getUserCustormer().getTelephone() + "'}\n" +
                            "        ]);");
                } else if (true) {

                }

            }
        }
    };

    WebChromeClient webChromeClient = new WebChromeClient() {
        @Override
        public boolean onJsAlert(WebView view, String url, String message, JsResult result) {
            return super.onJsAlert(view, url, message, result);
        }
    };
    private AgentWeb.PreAgentWeb preAgentWeb;
    private AgentWeb go;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);
        ButterKnife.bind(this);
        company_info = getIntent().getParcelableExtra("company_info");
        TLog.log(" company_info = getIntent().getParcelableExtra(\"company_info\");");
        preAgentWeb = WebViewUtils.init(this, ll, this, webViewClient, webChromeClient, this);
        String url = getIntent().getStringExtra("url");
        go = preAgentWeb.go(url);

    }


    /**
     * 监听下载
     *
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
     *
     * @param view
     * @param title
     */
    @Override
    public void onReceivedTitle(WebView view, String title) {
        this.title.setText(title);
        TLog.log(title);
    }

    /**
     * 文件下载成功
     *
     * @param path
     */
    @Override
    public void success(String path) {
        TLog.log("public void success(String path) {");
        TLog.log(path);

        QbSdk.openFileReader(this, path, null, new ValueCallback<String>() {
            @Override
            public void onReceiveValue(String s) {
                Log.d("op.class", "onReceiveValue: "+s);
            }
        });
    }

    /**
     * 文件下载失败
     *
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

    @OnClick(R.id.webView_back)
    public void onViewClicked() {
        if (go.getWebCreator().get().canGoBack()) {
            go.getWebCreator().get().goBack();
        } else {
            finish();
        }

    }
}
