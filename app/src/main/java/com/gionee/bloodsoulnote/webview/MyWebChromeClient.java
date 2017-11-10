package com.gionee.bloodsoulnote.webview;

import android.graphics.Bitmap;
import android.util.Log;
import android.view.View;
import android.webkit.ConsoleMessage;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebView;

public class MyWebChromeClient extends WebChromeClient {

    @Override
    public void onProgressChanged(WebView view, int newProgress) {
        super.onProgressChanged(view, newProgress);
    }

    @Override
    public void onReceivedTitle(WebView view, String title) {
        Log.i("MyWebChromeClient", "onReceivedTitle title --> " + title);
        super.onReceivedTitle(view, title);
    }

    @Override
    public void onReceivedIcon(WebView view, Bitmap icon) {
        Log.i("MyWebChromeClient", "onReceivedIcon --> ");
        super.onReceivedIcon(view, icon);
    }

    @Override
    public boolean onJsAlert(WebView view, String url, String message, JsResult result) {
        Log.i("MyWebChromeClient", "onJsAlert message --> " + message);
        return super.onJsAlert(view, url, message, result);
    }

    @Override
    public void onShowCustomView(View view, CustomViewCallback callback) {
        Log.i("MyWebChromeClient", "onShowCustomView --> ");
        super.onShowCustomView(view, callback);
    }

    @Override
    public void onHideCustomView() {
        Log.i("MyWebChromeClient", "onHideCustomView --> ");
        super.onHideCustomView();
    }

    @Override
    public View getVideoLoadingProgressView() {
        Log.i("MyWebChromeClient", "getVideoLoadingProgressView --> ");
        return super.getVideoLoadingProgressView();
    }

    @Override
    public Bitmap getDefaultVideoPoster() {
        Log.i("MyWebChromeClient", "getDefaultVideoPoster --> ");
        return super.getDefaultVideoPoster();
    }

    @Override
    public void onReceivedTouchIconUrl(WebView view, String url, boolean precomposed) {
        Log.i("MyWebChromeClient", "onReceivedTouchIconUrl --> " + precomposed);
        super.onReceivedTouchIconUrl(view, url, precomposed);
    }

    @Override
    public void onCloseWindow(WebView window) {
        Log.i("MyWebChromeClient", "onCloseWindow --> ");
        super.onCloseWindow(window);
    }

    @Override
    public boolean onConsoleMessage(ConsoleMessage consoleMessage) {
        Log.i("MyWebChromeClient", "onConsoleMessage --> ");
        return super.onConsoleMessage(consoleMessage);
    }

    @Override
    public void onShowCustomView(View view, int requestedOrientation, CustomViewCallback callback) {
        Log.i("MyWebChromeClient", "onShowCustomView --> ");
        super.onShowCustomView(view, requestedOrientation, callback);
    }

    @Override
    public void onRequestFocus(WebView view) {
        Log.i("MyWebChromeClient", "onRequestFocus --> ");
        super.onRequestFocus(view);
    }


}
