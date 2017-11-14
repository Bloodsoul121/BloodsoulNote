package com.gionee.bloodsoulnote.webviewdetail.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.gionee.bloodsoulnote.R;
import com.gionee.bloodsoulnote.webviewdetail.IContract.IWebPage;
import com.gionee.bloodsoulnote.webviewdetail.presenter.WebPagePresenter;

public class WebPageView extends LinearLayout implements IWebPage.IView, View.OnClickListener {

    private Context mContext;

    private IWebPage.IPresenter mPresenter;

    private WebDetailView mWebDetailView;

    private LinearLayout mCommentBottomBar;

    private NumImageView mCommentNum;

    private RelativeLayout mDiscussContainer;

    private ToastEditText mDiscussEdit;

    private Toast mToast;

    public WebPageView(Context context) {
        super(context);
        init(context);
    }

    public WebPageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {
        mContext = context;
        LayoutInflater.from(context).inflate(R.layout.layout_web_page, this);
        WebPagePresenter.bindPresenter(this);
        mPresenter.loadWebpageInfo();
    }

    @Override
    public void initView() {
        mWebDetailView = (WebDetailView) findViewById(R.id.web_detail_view);
        // bottom bar
        mCommentBottomBar = (LinearLayout) findViewById(R.id.web_bottom_bar);
        ImageView back = (ImageView) findViewById(R.id.back);
        ImageView share = (ImageView) findViewById(R.id.share);
        ImageView mutilWindow = (ImageView) findViewById(R.id.mutil_window);
        EditText commentBar = (EditText) findViewById(R.id.comment_bar);
        mCommentNum = (NumImageView) findViewById(R.id.comment_num);
        back.setOnClickListener(this);
        share.setOnClickListener(this);
        mutilWindow.setOnClickListener(this);
        mCommentNum.setOnClickListener(this);
        commentBar.setOnClickListener(this);
        // discuss box
        mDiscussContainer = (RelativeLayout) findViewById(R.id.discuss_container);
        TextView discussCancel = (TextView) findViewById(R.id.discuss_cancel);
        TextView discussPublish = (TextView) findViewById(R.id.discuss_publish);
        mDiscussEdit = (ToastEditText) findViewById(R.id.discuss_edit);
        discussCancel.setOnClickListener(this);
        discussPublish.setOnClickListener(this);
    }

    @Override
    public void bindPresenter(IWebPage.IPresenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void publishSucceed() {
        // 发表成功
        showBottomBar();
        toast(getResources().getString(R.string.publish_succeed));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back:
                toast("返回");
                break;
            case R.id.share:
                toast("分享");
                break;
            case R.id.comment_num:
                toast("打开评论区");
                break;
            case R.id.mutil_window:
                toast("多窗口");
                break;
            case R.id.comment_bar:
                showDiscussBox();
                break;
            case R.id.discuss_cancel:
                showBottomBar();
                break;
            case R.id.discuss_publish:
                publish();
                break;
        }
    }

    private void publish() {
        String content = mDiscussEdit.getText().toString();
        Log.i("bloodsoul", "content " + content);
        mPresenter.publish(content);
    }

    private void showDiscussBox() {
        mDiscussContainer.setVisibility(VISIBLE);
        mDiscussEdit.setFocusable(true);
        mDiscussEdit.setFocusableInTouchMode(true);
        mDiscussEdit.requestFocus();
        hideBottomBar();
        showSoftKeyboard();
    }

    private void hideDiscussBox() {
        mDiscussContainer.setVisibility(GONE);
        mDiscussEdit.setFocusable(false);
        mDiscussEdit.setFocusableInTouchMode(false);
    }

    private void showBottomBar() {
        hideDiscussBox();
        hideSoftKeyboard();
        mCommentBottomBar.setVisibility(VISIBLE);
    }

    private void hideBottomBar() {
        mCommentBottomBar.setVisibility(GONE);
    }

    private void showSoftKeyboard() {
        InputMethodManager inputMethodManager = (InputMethodManager) mContext.getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.showSoftInput(mDiscussEdit, 0);
    }

    private void hideSoftKeyboard() {
        InputMethodManager inputMethodManager = (InputMethodManager) mContext.getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(mDiscussEdit.getWindowToken(), 0);
    }

    public void loadUrl(String url) {
        mWebDetailView.loadUrl(url);
    }

    private void toast(String msg) {
        if (mToast == null) {
            mToast = Toast.makeText(mContext, msg, Toast.LENGTH_SHORT);
        } else {
            mToast.setText(msg);
        }
        mToast.show();
    }
}
