package com.pilipa.fapiaobao.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mylibrary.utils.TLog;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.just.agentwebX5.AgentWeb;
import com.just.agentwebX5.ChromeClientCallbackManager;
import com.just.agentwebX5.DownLoadResultListener;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.FileCallback;
import com.lzy.okgo.model.Response;
import com.lzy.okgo.request.base.Request;
import com.pilipa.fapiaobao.R;
import com.pilipa.fapiaobao.account.AccountHelper;
import com.pilipa.fapiaobao.base.BaseActivity;
import com.pilipa.fapiaobao.net.bean.invoice.MacherBeanToken;
import com.pilipa.fapiaobao.utils.WebViewUtils;
import com.tencent.smtt.export.external.interfaces.JsResult;
import com.tencent.smtt.sdk.DownloadListener;
import com.tencent.smtt.sdk.QbSdk;
import com.tencent.smtt.sdk.ValueCallback;
import com.tencent.smtt.sdk.WebChromeClient;
import com.tencent.smtt.sdk.WebView;
import com.tencent.smtt.sdk.WebViewClient;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.HashMap;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by lyt on 2017/10/13.
 */

public class Op extends BaseActivity implements
        DownloadListener
        , ChromeClientCallbackManager.ReceivedTitleCallback, DownLoadResultListener , com.tencent.smtt.export.external.interfaces.DownloadListener{

    @Bind(R.id.title)
    TextView title;
    @Bind(R.id.webView_back)
    ImageView webViewBack;
    @Bind(R.id.ll)
    LinearLayout ll;
    String TAG = Op.class.getSimpleName();
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
    private AgentWeb go;
    private HashMap<String, String> params;

//    public static final Stringjsondata = "{ pkgName:\"com.example.thirdfile\", "
//            +"className:\"com.example.thirdfile.IntentActivity\","
//
//            +"thirdCtx: {pp:123},"
//
//            +"menuItems:"
//
//            +"["
//
//            +"{id:0,iconResId:"+ R.drawable.ic_launcher +",text:\"menu0\"},
//
//    {id:1,iconResId:" + R.drawable.bookmark_edit_icon + ",text:\"menu1\"},
//
//        {id:2,iconResId:"+ R.drawable.bookmark_folder_icon +",text:\"菜单 2\"}"
//
//                + "]"
//
//                + " }";



    @Override
    protected int getLayoutId() {
        return R.layout.activity_web;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ButterKnife.bind(this);
        company_info = getIntent().getParcelableExtra("company_info");
        TLog.log(" company_info = getIntent().getParcelableExtra(\"company_info\");");
        AgentWeb.PreAgentWeb preAgentWeb = WebViewUtils.init(this, ll, this, webViewClient, webChromeClient, null);
        String url = getIntent().getStringExtra("url");
        go = preAgentWeb.go(url);


            JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("pkgName", "com.pilipa.fapiaobao.ui");
            jsonObject.put("className", "com.pilipa.fapiaobao.ui.UploadReceiptActivity");
            JSONObject jsonObjectThirdCtx = new JSONObject();
            jsonObject.put("thirdCtx", jsonObjectThirdCtx);
            JSONArray jsonArray = new JSONArray();
            JSONObject jsonObjectMenu = new JSONObject();
            jsonObjectMenu.put("id", 1);
            jsonObjectMenu.put("iconResId", R.mipmap.add_company);
            jsonObjectMenu.put("text", "菜单1");
            jsonArray.put(jsonObjectMenu);
            jsonObject.put("menuItems", jsonArray);
            Gson gson = new Gson();
            String jsondata = gson.toJson(jsonObject);
            params = new HashMap<>();
            params.put("style", "1");
            params.put("local", "true");
            params.put("menuData", jsondata);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        WebView webView = go.getWebCreator().get();

        webView.setDownloadListener(new DownloadListener() {
            @Override
            public void onDownloadStart(String url, String userAgent, String s2, String s3, long l) {
                TLog.d(TAG,"onDownloadStartonDownloadStartonDownloadStart");
                OkGo.<File>get(url).execute(new FileCallback() {
                    @Override
                    public void onSuccess(Response<File> response) {
                        int i = QbSdk.openFileReader(Op.this, response.body().getAbsolutePath(), params, new ValueCallback<String>() {
                            @Override
                            public void onReceiveValue(String s) {
                                Log.d("op.class", "onReceiveValue: " + s);
                            }
                        });
                        switch (i) {
                            case 1:
                                TLog.d(TAG,"用qq浏览器打开");
                                break;
                            case 2:
                                TLog.d(TAG,"用MiniQb打开");
                                break;
                            case 3:
                                TLog.d(TAG,"调起阅读器弹窗");
                                break;
                            case -1:
                                TLog.d(TAG,"打开失败");
                                break;
                        }

                    }

                    @Override
                    public void onStart(Request<File, ? extends Request> request) {
                        super.onStart(request);
                        showProgressDialog();
                    }


                    @Override
                    public void onFinish() {
                        super.onFinish();
                        hideProgressDialog();
                    }
                });
            }
        });
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
        TLog.d(TAG,"agentWeb onDownloadStart");
        Toast.makeText(this, "start download", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDownloadStart(String s, String s1, byte[] bytes, String s2, String s3, String s4, long l, String s5, String s6) {
        TLog.d(TAG,"tencent ondownloadstart");
    }

    @Override
    public void onDownloadVideo(String s, long l, int i) {
        TLog.d(TAG,"tencent ondownloadstart");
    }

    /**
     * 接收到webview title
     *
     * @param view
     * @param title
     */
    @Override
    public void onReceivedTitle(WebView view, String title) {
        TLog.d(TAG,"onReceivedTitle");
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
        TLog.d(TAG,"success"+path);
        TLog.d(TAG,"public void success(String path) {");
        TLog.d(TAG,path);


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
        TLog.d(TAG,"path"+path);
        TLog.d(TAG,"resUrl"+resUrl);
        TLog.d(TAG,"cause"+cause);
        TLog.d(TAG,"Throwable e"+e.getMessage());
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
