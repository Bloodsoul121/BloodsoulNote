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
//        mWebPageView.loadUrl("http://open.toutiao.com/a6481001378507391501/?utm_campaign=open&utm_me" +
//                "dium=webview&utm_source=jinlillq&gy=6a34728859daba3b82658245ff487ec1dc5f5dd797b8ca" +
//                "0e193832951f91bb9bb44df72beed182c35e257bdc10e52e174ed3b1f952877344c55b65516c8b" +
//                "5196&crypt=8158&item_id=6481001378507391501&&openudid=9de131f6e96feb36 ");
    }

    @Override
    public void onBackPressed() {
        if (mWebPageView.onBackCliked()) {
            return;
        }
        super.onBackPressed();
    }
}
