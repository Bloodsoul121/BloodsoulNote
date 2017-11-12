package com.gionee.bloodsoulnote.webviewdetail.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

public class WebDetailView extends ScrollView
{

    private WebView mWebView;
    private WebCommentView mCommentView;
    private Context mContext;
    private LinearLayout mContainer;

    public WebDetailView(Context context) {
        super(context);
        init(context);
    }

    public WebDetailView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {
        mContext = context;

        mContainer = new LinearLayout(context);
        mContainer.setOrientation(LinearLayout.VERTICAL);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                                                                               ViewGroup.LayoutParams.MATCH_PARENT);
        mContainer.setLayoutParams(layoutParams);
        addView(mContainer);

        mWebView = new WebView(context);
        initWebView(mWebView);
//        mContainer.addView(mWebView);

        initCommentView();
    }

    private void initWebView(WebView webView) {
        webView.setWebViewClient(new WebViewClient());
        webView.setWebChromeClient(new WebChromeClient());
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
    }

    private void initCommentView() {
        mCommentView = new WebCommentView(mContext);
        mContainer.addView(mCommentView);
        mCommentView.bindWebview(mWebView);
    }

    public void loadUrl(String url) {
        mWebView.loadUrl(url);
    }

    public WebView getWebView() {
        return mWebView;
    }

    public WebCommentView getWebCommentView() {
        return mCommentView;
    }

    @Override
    protected void onScrollChanged(int x, int y, int oldx, int oldy) {
        super.onScrollChanged(x, y, oldx, oldy);
        Log.i("bloodsoul", "onScrollChanged --> " + x + ", " + y + ", " + oldx + ", " + oldy);
    }
}
