package com.pilipa.fapiaobao.utils;

import android.app.Activity;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;

import com.just.library.AgentWeb;
import com.just.library.ChromeClientCallbackManager;
import com.just.library.DownLoadResultListener;

/**
 * Created by lyt on 2017/10/13.
 */

public class WebViewUtils {

    private static AgentWeb.PreAgentWeb mAgentWeb;
    private static Activity activity;

    private WebViewUtils() {

    }

    public static AgentWeb.PreAgentWeb init(Activity activity
            , ViewGroup viewGroup
            , ChromeClientCallbackManager.ReceivedTitleCallback callback
            , WebViewClient webViewClient
            , WebChromeClient webChromeClient
    , DownLoadResultListener downLoadResultListener) {

        mAgentWeb = AgentWeb.with(activity)//
                .setAgentWebParent(viewGroup, new LinearLayout.LayoutParams(-1, -1))//
                .useDefaultIndicator()//
                .defaultProgressBarColor()
                .addDownLoadResultListener(downLoadResultListener)
                .setReceivedTitleCallback(callback)
                .setWebChromeClient(webChromeClient)
                .setWebViewClient(webViewClient)
                .setSecutityType(AgentWeb.SecurityType.strict)
                .createAgentWeb()//
                .ready();



        return mAgentWeb;
    }



}
