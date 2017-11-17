package com.gionee.bloodsoulnote.webviewdetail;

import android.app.Activity;
import android.os.Bundle;

import com.gionee.bloodsoulnote.R;
import com.gionee.bloodsoulnote.webviewdetail.view.WebPageView;

public class WebviewDetailActivity
        extends Activity
{

    private WebPageView  mWebPageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview_detail);

        initView();
    }

    private void initView() {
        mWebPageView = (WebPageView) findViewById(R.id.web_page_view);
        mWebPageView.loadUrl("https://www.baidu.com");
    }

    @Override
    public void onBackPressed() {
        if (mWebPageView.onBackCliked()) {
            return;
        }
        super.onBackPressed();
    }
}
