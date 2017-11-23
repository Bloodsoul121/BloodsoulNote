package com.gionee.bloodsoulnote.customview3.webview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.gionee.bloodsoulnote.R;
import com.gionee.bloodsoulnote.webview.MyWebChromeClient;
import com.gionee.bloodsoulnote.webview.MyWebViewClient;

public class ScrollWebviewActivity extends AppCompatActivity {

    private WebView mWebView;

    private WebSettings mSettings;

    private float startx;
    private float starty;
    private float offsetx;
    private float offsety;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scroll_webview);

        mWebView = (WebView) findViewById(R.id.webview);
        mWebView.setWebViewClient(new MyWebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                return super.shouldOverrideUrlLoading(view, url);
            }
        });
        mWebView.setWebChromeClient(new MyWebChromeClient(){
            @Override
            public void onReceivedTitle(WebView view, String title) {
                super.onReceivedTitle(view, title);
            }
        });

        mSettings = mWebView.getSettings();
        mSettings.setJavaScriptEnabled(true);
        mSettings.setDomStorageEnabled(true);

        mWebView.loadUrl("https://www.baidu.com");

        mWebView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        v.getParent().requestDisallowInterceptTouchEvent(true);
                        startx = event.getX();
                        starty = event.getY();
                        break;
                    case MotionEvent.ACTION_MOVE:
                        offsetx = Math.abs(event.getX() - startx);
                        offsety = Math.abs(event.getY() - starty);
                        if (offsetx < offsety) {
                            v.getParent().requestDisallowInterceptTouchEvent(true);
                        } else {
                            v.getParent().requestDisallowInterceptTouchEvent(false);
                        }
                        break;
                    default:
                        break;
                }
                return false;
            }
        });
    }
}
