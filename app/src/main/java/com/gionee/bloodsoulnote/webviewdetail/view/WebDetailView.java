package com.gionee.bloodsoulnote.webviewdetail.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import com.gionee.bloodsoulnote.R;

public class WebDetailView extends ScrollView {

    private Context mContext;

    private LinearLayout mContainer;

    private WebView mWebView;

    private CommentView mCommentView;

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

        mContainer = (LinearLayout) LayoutInflater.from(context).inflate(R.layout.layout_web_detail, this, false);
        addView(mContainer);

        mCommentView = (CommentView) mContainer.findViewById(R.id.web_comment_view);

    }

    private void initWebView(WebView webView) {
        webView.setWebViewClient(new WebViewClient());
        webView.setWebChromeClient(new WebChromeClient());
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
    }

    public void loadUrl(String url) {
        if (mWebView != null) {
            mWebView.loadUrl(url);
        }
    }

    public WebView getWebView() {
        return mWebView;
    }

    public CommentView getWebCommentView() {
        return mCommentView;
    }

    public void updateSelfComment() {

    }
}
