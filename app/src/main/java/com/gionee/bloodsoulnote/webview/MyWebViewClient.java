package com.gionee.bloodsoulnote.webview;

import android.graphics.Bitmap;
import android.util.Log;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MyWebViewClient extends WebViewClient {

    private static final String TAG = "WebviewActivity:Client";

    public MyWebViewClient() {}

    @Override
    public void onPageStarted(WebView view, String url, Bitmap favicon) {
        super.onPageStarted(view, url, favicon);
        Log.i(TAG, "onPageStarted : " + url);
    }

    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        Log.i(TAG, "shouldOverrideUrlLoading : " + url);
        return false;
    }

    @Override
    public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
        Log.i(TAG, "shouldOverrideUrlLoading : ");
        return super.shouldOverrideUrlLoading(view, request);
    }

    @Override
    public void onPageFinished(WebView view, String url) {
        super.onPageFinished(view, url);
        Log.i(TAG, "onPageFinished : " + url);
        loadJS(view);
    }

    private void loadJS(WebView webView) {
        String jsUrl = "https://t-3g-asset.gionee.com/apps/3g/third_party/xinxiliu_detailpage_ad.js";

        String jsCode = "(function(){\n" +
                "\t\t\tvar gjs = document.createElement(\"script\");\n" +
                "\t\t\tgjs.src = \"" + jsUrl + "\";\n" +
                "\t\t\tvar s = document.getElementsByTagName(\"body\");\n" +
                "\t\t\talert(s);\n" +
                "\t\t\ts[0].appendChild(gjs);\n" +
                "\t\t})();";

        String jsParamsJson = "{\"lon\":\"0.0\",\"client_id\":\"RLGAzSUVtu4n2MdhUSrV5g\\u003d\\u003d\",\"dpi\":\"480\",\"carrier\":\"0\",\"coordinate_type\":\"1\",\"city\":\"深圳\",\"connect_type\":\"2\",\"apilevel\":\"23\",\"imei\":\"000000000000001\",\"app_list\":\"\",\"bss_id\":\"70:f9:6d:d5:0d:f1\",\"mcc\":\"460\",\"channel\":\"2\",\"type\":\"60\",\"android_id\":\"9de131f6e96feb36\",\"app_ver\":\"5.4.3\",\"mac\":\"00:08:22:0c:fd:00\",\"adslot_w\":\"0\",\"h\":\"1920\",\"w\":\"1080\",\"package_name\":\"com.android.browser\",\"cuid\":\"2DF2D59218BA7DDFAF07C14FB25D589A\",\"device_id\":\"vJDoIb+NjatJYppWr3j9gZJ3ueFmX9wRHaa4ZLdQQFg\\u003d\",\"ua\":\"Mozilla_5_0__Linux__U__Android_6_0__zh_cn__Build_MRA58K___AppleWebKit_534_30__KHTML_like_Gecko__Version_4_0_Chrome_50_0_0_0_Mobile_Safari_534_30_GIONEE_GN8002_GN8002_RV_5_0_16\",\"ip\":\"192.168.37.13 \n" +
                "\",\"adslot_h\":\"1920\",\"os_version\":\"6.0\",\"lat\":\"0.0\",\"model\":\"GN8002\",\"lac\":\"2\"}";

//        String js = jsCode.replace("{{config}}", jsParamsJson);

//        String js2 = "(function(){var videoEls = document.querySelectorAll('video');var status = 0, isVideo = false;showLocalNativeTip();function showLocalNativeTip(){for(var i = 0; i < videoEls.length; i++){webviewListenerVideo(videoEls[i],i);}function webviewListenerVideo(el,index){el.addEventListener('play',function(){status = 1;window.HtmlStatisticJSInterface.video(el.src);},false);el.addEventListener('pause',function(){status = 0;},false);el.addEventListener('ended',function(){status = 0;},false);el.addEventListener('timeupdate',function(){if(status === 2){window.HtmlStatisticJSInterface.showToast();status = 0;} },false);}}function netchangecallback(){status = 2;}window.netchangecallback = netchangecallback;})();";

//        webView.loadUrl("javascript:" + jsCode);
    }

    @Override
    public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
        super.onReceivedError(view, errorCode, description, failingUrl);
        Log.i(TAG, "onReceivedError : " + errorCode);
    }

    @Override
    public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
        Log.i(TAG, "onReceivedError : " + error);
        super.onReceivedError(view, request, error);
    }
}
