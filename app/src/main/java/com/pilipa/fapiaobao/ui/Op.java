package com.pilipa.fapiaobao.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.DownloadListener;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mylibrary.utils.TLog;
import com.just.library.AgentWeb;
import com.just.library.ChromeClientCallbackManager;
import com.just.library.DownLoadResultListener;
import com.pilipa.fapiaobao.R;
import com.pilipa.fapiaobao.account.AccountHelper;
import com.pilipa.fapiaobao.net.bean.invoice.MacherBeanToken;
import com.pilipa.fapiaobao.utils.WebViewUtils;

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
            if (company_info != null) {
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
                    "            {selector:'#invoiceTitle', value:'"+company_info.getName()+"'},\n" +
                    "            {selector:'#taxpayerNumber', value:'"+company_info.getTaxno()+"'},\n" +
                    "            {selector:'#telephoneNumber', value:'"+company_info.getPhone()+"'},\n" +
                    "            {selector:'#address', value:'"+company_info.getAddress()+"'},\n" +
                    "            {selector:'#depositBank',value:'"+company_info.getDepositBank()+"'},\n" +
                    "            {selector:'#bankAccount',value:'"+company_info.getAccount()+"'}\n" +
                    "            {selector:'#bankAccount',value:'"+ AccountHelper.getUser().getData().getCustomer().getTelephone()+"'}\n" +
                    "        ]);");
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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);
        ButterKnife.bind(this);
        company_info = getIntent().getParcelableExtra("company_info");
        TLog.log(" company_info = getIntent().getParcelableExtra(\"company_info\");");
        preAgentWeb = WebViewUtils.init(this, ll, this, webViewClient, webChromeClient, this);
        String url = getIntent().getStringExtra("url");
        preAgentWeb.go(url);
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
        TLog.log(path);
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
        finish();
    }
}
