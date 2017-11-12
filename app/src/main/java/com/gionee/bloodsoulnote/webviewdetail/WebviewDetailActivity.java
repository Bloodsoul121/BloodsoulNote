package com.gionee.bloodsoulnote.webviewdetail;

import android.app.Activity;
import android.os.Bundle;

import com.gionee.bloodsoulnote.R;
import com.gionee.bloodsoulnote.webviewdetail.view.WebDetailView;

public class WebviewDetailActivity
        extends Activity
{

    private WebDetailView  mWebDetailView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview_detail);

        initView();
    }

    private void initView() {
        mWebDetailView = (WebDetailView) findViewById(R.id.web_detail_view);
        mWebDetailView.loadUrl("https://www.baidu.com");
    }

}
