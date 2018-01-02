package com.pilipa.fapiaobao.ui;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mylibrary.utils.TLog;
import com.google.gson.Gson;
import com.just.agentwebX5.AgentWeb;
import com.just.agentwebX5.ChromeClientCallbackManager;
import com.just.agentwebX5.DownLoadResultListener;
import com.pilipa.fapiaobao.R;
import com.pilipa.fapiaobao.account.AccountHelper;
import com.pilipa.fapiaobao.base.BaseActivity;
import com.pilipa.fapiaobao.base.BaseApplication;
import com.pilipa.fapiaobao.net.Api;
import com.pilipa.fapiaobao.net.bean.invoice.MacherBeanToken;
import com.pilipa.fapiaobao.net.bean.me.FavoriteCompanyBean;
import com.pilipa.fapiaobao.net.bean.me.NormalBean;
import com.pilipa.fapiaobao.ui.constants.Constant;
import com.pilipa.fapiaobao.utils.WebViewUtils;
import com.tencent.smtt.export.external.interfaces.ConsoleMessage;
import com.tencent.smtt.export.external.interfaces.JsPromptResult;
import com.tencent.smtt.export.external.interfaces.JsResult;
import com.tencent.smtt.export.external.interfaces.WebResourceError;
import com.tencent.smtt.export.external.interfaces.WebResourceRequest;
import com.tencent.smtt.export.external.interfaces.WebResourceResponse;
import com.tencent.smtt.sdk.DownloadListener;
import com.tencent.smtt.sdk.WebChromeClient;
import com.tencent.smtt.sdk.WebView;
import com.tencent.smtt.sdk.WebViewClient;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;

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
    FrameLayout ll;
    String TAG = Op.class.getSimpleName();
    private  MacherBeanToken.DataBean.CompanyBean companyInfo;




    WebViewClient webViewClient = new WebViewClient() {
        @Override
        public void onLoadResource(WebView view, String url) {
            super.onLoadResource(view, url);
            TLog.d(TAG,"onLoadResource");
        }

        @Override
        public void onPageStarted(WebView webView, String s, Bitmap bitmap) {
            super.onPageStarted(webView, s, bitmap);
            TLog.d(TAG,"onPageStarted");
        }

        @Override
        public void onReceivedHttpError(WebView webView, WebResourceRequest webResourceRequest, WebResourceResponse webResourceResponse) {
            super.onReceivedHttpError(webView, webResourceRequest, webResourceResponse);
            TLog.d(TAG,"onReceivedHttpError");
        }

        @Override
        public void onReceivedError(WebView webView, int i, String s, String s1) {
            super.onReceivedError(webView, i, s, s1);
            TLog.d(TAG,"onReceivedError");
        }

        @Override
        public void onReceivedError(WebView webView, WebResourceRequest webResourceRequest, WebResourceError webResourceError) {
            super.onReceivedError(webView, webResourceRequest, webResourceError);
            TLog.d(TAG,"onReceivedError(webView, webResourceRequest, webResourceError);");
        }

        @Override
        public void onTooManyRedirects(WebView webView, Message message, Message message1) {
            super.onTooManyRedirects(webView, message, message1);
            TLog.d(TAG,"onTooManyRedirects");
        }

        @Override
        public void onPageFinished(final WebView view, final String url) {
            super.onPageFinished(view, url);
            TLog.d(TAG,"onPageFinished");
            TLog.log("companyInfo"+ companyInfo);
            if (companyInfo != null) {
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
                            "            {selector:'#invTitle', value:'" + companyInfo.getName() + "'},\n" +
                            "            {selector:'#invTaxNo', value:'" + companyInfo.getTaxno() + "'},\n" +
                            "            {selector:'#invAddrPhone', value:'" + companyInfo.getAddress() + companyInfo.getPhone() + "'},\n" +
                            "            {selector:'#invBank',value:'" + companyInfo.getDepositBank() + companyInfo.getAccount() + "'},\n" +
                            "            {selector:'#hxEmail',value:'" + AccountHelper.getUser().getData().getCustomer().getEmail() + "'},\n" +
                            "            {selector:'#mobile',value:'" + AccountHelper.getUser().getData().getCustomer().getTelephone() + "'}\n" +
                            "        ]);");
                } else if (url.contains("starbucks")) {
                    TLog.d(TAG, "else if (url.contains(\"starbucks\")) {");
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
                            "            {selector:'#invoiceTitle', value:'" + companyInfo.getName() + "'},\n" +
                            "            {selector:'#taxpayerNumber', value:'" + companyInfo.getTaxno() + "'},\n" +
                            "            {selector:'#telephoneNumber', value:'" + companyInfo.getPhone() + "'},\n" +
                            "            {selector:'#address', value:'" + companyInfo.getAddress() + "'},\n" +
                            "            {selector:'#depositBank',value:'" + companyInfo.getDepositBank() + "'},\n" +
                            "            {selector:'#bankAccount',value:'" + companyInfo.getAccount() + "'},\n" +
                            "            {selector:'#mailAccount',value:'" + AccountHelper.getUser().getData().getCustomer().getEmail() + "'},\n" +
                            "            {selector:'#cellPhoneNumber',value:'" + AccountHelper.getUser().getData().getCustomer().getTelephone() + "'}\n" +
                            "        ]);");
                }

            } else {
//                if (url.contains("invoiceWrite") || url.contains("yumchina")) {
//                    if (!Constant.NOTOKEN.equals(AccountHelper.getToken())) {
//                        Api.favoriteCompanyList(AccountHelper.getToken(), this, new Api.BaseRawResponse<FavoriteCompanyBean>() {
//                            @Override
//                            public void onStart() {
//                                showProgressDialog();
//                            }
//
//                            @Override
//                            public void onFinish() {
//                                hideProgressDialog();
//                            }
//
//                            @Override
//                            public void onError() {
//
//                            }
//
//                            @Override
//                            public void onTokenInvalid() {
//
//                            }
//
//                            @Override
//                            public void setData(final FavoriteCompanyBean companiesBean) {
//                                if (companiesBean.getData()!= null && companiesBean.getData().size() == 1) {
//                                    fillInWithSingleCompany(makeCompany(companiesBean.getData().get(0)), view, url);
//                                }
//
//                                if (companiesBean.getData() != null && companiesBean.getData().size() > 1) {
//                                    if (!popWnd.isShowing()) {
//                                        if (!isFinishing()) {
//                                            CompanySpinnerAdapter companySpinnerAdapter = new CompanySpinnerAdapter(companiesBean.getData());
//                                            listView.setAdapter(companySpinnerAdapter);
//
//                                            popWnd.showAtLocation(View.inflate(Op.this, R.layout.activity_web, null), Gravity.CENTER, 0, 0);
//
//                                            listView.setOnItemClickListener(new OnItemClickListener() {
//                                                @Override
//                                                public void onItemClick(AdapterView<?> parent, View view2, int position, long id) {
//                                                    fillInWithSingleCompany(makeCompany(companiesBean.getData().get(position)), view, url);
//                                                    popWnd.dismiss();
//                                                }
//                                            });
//                                        }
//                                    }
//                                }
//                            }
//                        });
//                    }
//                }
            }
        }
    };
    private ListView listView;
    private PopupWindow popWnd;

    private void initPopup() {
        View popupContentView = LayoutInflater.from(this).inflate(R.layout.layout_spinner, null);
        listView = (ListView) popupContentView.findViewById(R.id.listview);
        popWnd = new PopupWindow(this);
        popWnd.setAnimationStyle(R.style.download_alert_layout_style);
        popWnd.setContentView(popupContentView);
        popWnd.setBackgroundDrawable(getResources().getDrawable(R.drawable.bg_rect));
        popWnd.setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);
        popWnd.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        popWnd.setOutsideTouchable(true);
    }

    private static class CompanySpinnerAdapter extends BaseAdapter {

        private final List<FavoriteCompanyBean.DataBean> data;

        public CompanySpinnerAdapter(List<FavoriteCompanyBean.DataBean> data) {
            this.data = data;
        }

        @Override
        public int getCount() {
            return data.size();
        }

        @Override
        public Object getItem(int position) {
            return data.get(position);
        }

        @Override
        public long getItemId(int position) {
            return data.get(position).hashCode();
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater _LayoutInflater = LayoutInflater.from(parent.getContext());
            LinearLayout ll = (LinearLayout) _LayoutInflater.inflate(R.layout.item_simple_text_spinner, null);
            TextView textView = (TextView) ll.findViewById(R.id.spinner_item);
            textView.setTextColor(Color.parseColor("#434343"));
            textView.setText(data.get(position).getName());
            return ll;
        }
    }

    private String tag;

    private MacherBeanToken.DataBean.CompanyBean makeCompany(FavoriteCompanyBean.DataBean companiesBean) {
        MacherBeanToken.DataBean.CompanyBean companyBean = new MacherBeanToken.DataBean.CompanyBean();
        companyBean.setAccount(companiesBean.getAccount());
        companyBean.setAddress(companiesBean.getAddress());
        companyBean.setDepositBank(companiesBean.getDepositBank());
        companyBean.setId(companiesBean.getId());
        companyBean.setIsNewRecord(companiesBean.isIsNewRecord());
        companyBean.setName(companiesBean.getName());
        companyBean.setPhone(companiesBean.getPhone());
        companyBean.setTaxno(companiesBean.getTaxno());
        return companyBean;
    }

    private void fillInWithSingleCompany(MacherBeanToken.DataBean.CompanyBean companyBean, WebView view, String url) {
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
                    "            {selector:'#invTitle', value:'" + companyBean.getName() + "'},\n" +
                    "            {selector:'#invTaxNo', value:'" + companyBean.getTaxno() + "'},\n" +
                    "            {selector:'#invAddrPhone', value:'" + companyBean.getAddress() + companyBean.getPhone() + "'},\n" +
                    "            {selector:'#invBank',value:'" + companyBean.getDepositBank() + companyBean.getAccount() + "'},\n" +
                    "            {selector:'#hxEmail',value:'" + AccountHelper.getUser().getData().getCustomer().getEmail() + "'},\n" +
                    "            {selector:'#mobile',value:'" + AccountHelper.getUser().getData().getCustomer().getTelephone() + "'}\n" +
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
                    "            {selector:'#invoiceTitle', value:'" + companyBean.getName() + "'},\n" +
                    "            {selector:'#taxpayerNumber', value:'" + companyBean.getTaxno() + "'},\n" +
                    "            {selector:'#telephoneNumber', value:'" + companyBean.getPhone() + "'},\n" +
                    "            {selector:'#address', value:'" + companyBean.getAddress() + "'},\n" +
                    "            {selector:'#depositBank',value:'" + companyBean.getDepositBank() + "'},\n" +
                    "            {selector:'#bankAccount',value:'" + companyBean.getAccount() + "'},\n" +
                    "            {selector:'#mailAccount',value:'" + AccountHelper.getUser().getData().getCustomer().getEmail() + "'},\n" +
                    "            {selector:'#cellPhoneNumber',value:'" + AccountHelper.getUser().getData().getCustomer().getTelephone() + "'}\n" +
                    "        ]);");
        } else if (true) {

        }
    }

    WebChromeClient webChromeClient = new WebChromeClient() {
        @Override
        public boolean onJsAlert(WebView view, String url, String message, JsResult result) {
            return super.onJsAlert(view, url, message, result);
        }

        @Override
        public boolean onJsConfirm(WebView webView, String s, String s1, JsResult jsResult) {
            TLog.d(TAG,s);
            TLog.d(TAG,s1);
            TLog.d(TAG,"JsResult"+jsResult.toString());
            return super.onJsConfirm(webView, s, s1, jsResult);
        }

        @Override
        public boolean onJsBeforeUnload(WebView webView, String s, String s1, JsResult jsResult) {
            TLog.d(TAG,s);
            TLog.d(TAG,s1);
            TLog.d(TAG,"JsResult"+jsResult.toString());
            return super.onJsBeforeUnload(webView, s, s1, jsResult);
        }

        @Override
        public boolean onJsPrompt(WebView webView, String s, String s1, String s2, JsPromptResult jsPromptResult) {
            TLog.d(TAG,s);
            TLog.d(TAG,s1);
            TLog.d(TAG,s2);
            TLog.d(TAG,"JsResult"+jsPromptResult.toString());
            return super.onJsPrompt(webView, s, s1, s2, jsPromptResult);
        }

        @Override
        public boolean onJsTimeout() {
            return super.onJsTimeout();
        }

        @Override
        public boolean onConsoleMessage(ConsoleMessage consoleMessage) {
            TLog.d(TAG,consoleMessage.message());
            return super.onConsoleMessage(consoleMessage);
        }
    };
    private AgentWeb go;
    private boolean isFromUploadReceiptActivity;

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
        companyInfo = getIntent().getParcelableExtra(Constant.COMPANY_INFO);
        isFromUploadReceiptActivity = getIntent().getBooleanExtra(Constant.IS_FROM_UPLOADRECEIPT_ACTIVITY, false);
        tag = getIntent().getStringExtra(Constant.TAG);
        AgentWeb.PreAgentWeb preAgentWeb = WebViewUtils.init(this, ll, this, webViewClient, webChromeClient, null);
        String url = getIntent().getStringExtra("url");
        go = preAgentWeb.go(url);
        initPopup();

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
            HashMap<String, String> params = new HashMap<>();
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
                Api.transform_pdf(url, new Api.BaseViewCallbackWithOnStart<NormalBean>() {
                    @Override
                    public void onStart() {
                        showProgressDialog();
                    }

                    @Override
                    public void onFinish() {
                        hideProgressDialog();
                    }

                    @Override
                    public void onError() {
                        hideProgressDialog();
                    }

                    @Override
                    public void setData(NormalBean normalBean) {
                        if (normalBean.getStatus() == com.pilipa.fapiaobao.net.Constant.REQUEST_SUCCESS) {
                            TLog.d(TAG,normalBean.getData());
                            Intent intent = new Intent();
                            intent.putExtra(Constant.PDF_EXTRA, normalBean.getData());
                            intent.putExtra(Constant.TAG, tag);
                            intent.putExtra(Constant.IS_FROM_UPLOADRECEIPT_ACTIVITY, isFromUploadReceiptActivity);
                            intent.setClass(Op.this, PdfPreviewActivity.class);
                            startActivity(intent);
                        }
                        if (normalBean.getStatus() == Constant.INVILDE_URL) {
                            BaseApplication.showToast(normalBean.getMsg());
                        }
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
