package com.gionee.bloodsoulnote.webview;

import android.content.Context;
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
        mWebView.loadUrl("https://www.baidu.com");
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
}
