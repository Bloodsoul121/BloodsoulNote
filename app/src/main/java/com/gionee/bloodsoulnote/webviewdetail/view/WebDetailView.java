package com.gionee.bloodsoulnote.webviewdetail.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.ViewStub;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Scroller;

import com.gionee.bloodsoulnote.R;
import com.gionee.bloodsoulnote.webviewdetail.bean.CommentBean;

public class WebDetailView extends ScrollView {

    private Context mContext;

    private LinearLayout mContainer;

    private WebView mWebView;

    private CommentView mCommentView;

    public OnScrollChangeListener onScrollChangeListener;

    private int mHeight;

    private Scroller mScroller;

    private ViewStub mViewstubCommentView;

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
        setOverScrollMode(OVER_SCROLL_NEVER);
        mScroller = new Scroller(context);

        mContainer = (LinearLayout) LayoutInflater.from(context).inflate(R.layout.layout_web_detail, this, false);
        addView(mContainer);

        mWebView = (WebView) findViewById(R.id.webview);
        initWebView(mWebView);

        mViewstubCommentView = (ViewStub) findViewById(R.id.viewstub_comment_view);


        // 判断开关, 最好是 webview 加载 finish 后再操作 // TODO: 17-11-18
        if (isToggleOpen()) {
            mViewstubCommentView.inflate();
            initCommentView();
        }
    }

    private boolean isToggleOpen() {
        return true;
    }

    private void initCommentView() {
        mCommentView = (CommentView) mContainer.findViewById(R.id.web_comment_view);
        mCommentView.bindParentViewGroup(this);
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

    public void updateSelfComment(CommentBean bean) {
        // 更新用户发表的评论 // TODO: 17-11-16

        // 并滚动到用户评论区
        smoothScrollToMostNewTop();
    }

    private void smoothScrollToMostNewTop() {
        smoothScrollToMost(0, mHeight);
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        if(t + getHeight() >= mContainer.getHeight() && onScrollChangeListener != null){
            onScrollChangeListener.onScrollBottom();
        }
        if((t == 0 || t + getHeight() > mContainer.getHeight()) && onScrollChangeListener != null){
            onScrollChangeListener.onScrollTop();
        }
    }

    public interface OnScrollChangeListener{
        void onScrollChange(WebDetailView view,int x,int y,int oldx,int oldy);
        void onScrollTop();
        void onScrollBottom();
    }

    public void setOnScrollChangeListener(OnScrollChangeListener onScrollChangeListener){
        this.onScrollChangeListener = onScrollChangeListener;
    }

    public void setOnNeedOpenCommentDetailListener(CommentView.OnNeedOpenCommentDetailListener onNeedOpenCommentDetailListener) {
        if (mCommentView != null) {
            mCommentView.setOnNeedOpenCommentDetailListener(onNeedOpenCommentDetailListener);
        }
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        if (mWebView != null) {
            mHeight = mWebView.getHeight();
        }
    }

    @Override
    public void computeScroll() {
        super.computeScroll();
        if (mScroller.computeScrollOffset()) {
            scrollTo(mScroller.getCurrX(), mScroller.getCurrY());
            invalidate();
        }
    }

    public void smoothScrollToMost(int destX, int destY){
        int scrollX = getScrollX();
        int scrollY = getScrollY();
        int deltaX = destX - scrollX;
        int deltaY = destY - scrollY;
        mScroller.startScroll(scrollX, scrollY, deltaX, deltaY, 500);
        invalidate();
    }
}
