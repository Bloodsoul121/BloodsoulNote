package con.gionee.bloodsoulnote.webview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.EditText;

import con.gionee.bloodsoulnote.R;

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
    }

}
