package com.gionee.bloodsoulnote.webview;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.gionee.bloodsoulnote.R;

public class WebviewActivity extends AppCompatActivity {

    public static final String WEBVIEW_URL = "webview_url";

    private MyWebview mWebView;

    private EditText mEt;

    private static final String baidu = "https://www.baidu.com";

    private static final String videoUrl = "http://open.toutiao.com/a6486343222829253134/?utm_campaign=open&utm_med" +
            "ium=webview&utm_source=jinlillq&gy=b71c171a4d00a5a04a03aacbaf63765cf6ae5f774d62e0" +
            "9f39431061693971e14f104332b5a337dfbb889454f1a0e9baf42de2df23699c07e5a91549bb535b07" +
            "&crypt=7748&item_id=6486343222829253134&&openudid=9de131f6e96feb36";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_webview);
        initView();
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
    }

    private void initView() {
        mEt = (EditText) findViewById(R.id.edittext);
        mWebView = (MyWebview) findViewById(R.id.webview);

        mWebView.initWebSetting();
        mWebView.initClient();
//        mWebView.loadUrl(videoUrl);
        mWebView.loadUrl("http://open.toutiao.com/q6489605088124862733/?utm_campaign=open&utm_medium=webview&utm_source=jinli_fyp_api&group_id=6491460185456902414&gy=8120fa23c9947d0a040c738069f2bcc7ba1c846fd3f0a8b202f99714666a0ee0a11a7c84ac65c548d7279509320e0183&crypt=2979&item_id=6491460185456902414&&openudid=25e1890fc3394576 ");
    }

    /**
     * 加载网页
     */
    public void go(View view) {
        String url = mEt.getText().toString().trim();
        mWebView.loadUrl(url);
        Log.i("WebviewActivity", "load url : " + url);

        //自动弹出键盘
        InputMethodManager inputManager = (InputMethodManager) this.getSystemService(Context.INPUT_METHOD_SERVICE);
//        inputManager.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
        //强制隐藏Android输入法窗口
         inputManager.hideSoftInputFromWindow(mEt.getWindowToken(),0);
    }

    @Override
    public void onBackPressed() {
        if (mWebView.canGoBack()) {
            mWebView.goBack();
            return;
        }
        super.onBackPressed();
    }

    public void goBrowser(View view) {
        String url = "https://www.baidu.com";

        Intent intent = new Intent();
        intent.setAction("com.gionee.intent.action.WEB_SEARCH");
        intent.addCategory("android.intent.category.BROWSABLE");
        intent.addCategory("android.intent.category.DEFAULT");
        intent.setData(Uri.parse(url));
        intent.setClassName("com.android.browser","com.android.browser.InnBrowserActivity");
        this.startActivity(intent);
    }
}
