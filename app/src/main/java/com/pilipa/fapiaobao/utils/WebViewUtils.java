package com.pilipa.fapiaobao.utils;

import android.app.Activity;
import android.view.ViewGroup;

import android.widget.LinearLayout;

import com.just.agentwebX5.AgentWeb;
import com.just.agentwebX5.ChromeClientCallbackManager;
import com.just.agentwebX5.DownLoadResultListener;
import com.tencent.smtt.sdk.WebChromeClient;
import com.tencent.smtt.sdk.WebViewClient;

/**
 * Created by lyt on 2017/10/13.
 */

public class WebViewUtils {

    private WebViewUtils() {

    }

    public static AgentWeb.PreAgentWeb init(Activity activity
            , ViewGroup viewGroup
            , ChromeClientCallbackManager.ReceivedTitleCallback callback
            , WebViewClient webViewClient
            , WebChromeClient webChromeClient
    , DownLoadResultListener downLoadResultListener) {


        return AgentWeb.with(activity)//
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
    }



}
