package com.gionee.bloodsoulnote.webviewdetail.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.gionee.bloodsoulnote.R;

public class WebPageView extends LinearLayout implements View.OnClickListener {

    private WebDetailView mWebDetailView;

    private LinearLayout mCommentBottomBar;
    private ImageView mBack;
    private ImageView mShare;
    private ImageView mMutilWindow;
    private EditText mCommentBar;
    private NumImageView mCommentNum;

    public WebPageView(Context context) {
        super(context);
        init(context);
    }

    public WebPageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {
        LayoutInflater.from(context).inflate(R.layout.layout_web_page, this);
        mWebDetailView = (WebDetailView) findViewById(R.id.web_detail_view);
        mCommentBottomBar = (LinearLayout) findViewById(R.id.web_bottom_bar);
        mBack = (ImageView) findViewById(R.id.back);
        mShare = (ImageView) findViewById(R.id.share);
        mMutilWindow = (ImageView) findViewById(R.id.mutil_window);
        mCommentBar = (EditText) findViewById(R.id.comment_bar);
        mCommentNum = (NumImageView) findViewById(R.id.comment_num);

        mBack.setOnClickListener(this);
        mShare.setOnClickListener(this);
        mMutilWindow.setOnClickListener(this);
        mCommentNum.setOnClickListener(this);
        mCommentBar.setOnClickListener(this);
    }

    public void loadUrl(String url) {
        mWebDetailView.loadUrl(url);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back:
                break;
            case R.id.share:
                break;
            case R.id.comment_num:
                break;
            case R.id.mutil_window:
                break;
            case R.id.comment_bar:
                break;
        }
    }
}
