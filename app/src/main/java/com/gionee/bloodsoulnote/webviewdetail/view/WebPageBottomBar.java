package com.gionee.bloodsoulnote.webviewdetail.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.gionee.bloodsoulnote.R;

public class WebPageBottomBar extends RelativeLayout implements View.OnClickListener {

    private ImageView mCommentNum;

    private ImageView mShare;

    private OnWebPageBottomBarClickListener mOnWebPageBottomBarClickListener;

    public WebPageBottomBar(Context context) {
        super(context);
        init(context);
    }

    public WebPageBottomBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {
        LayoutInflater.from(context).inflate(R.layout.layout_web_page_bottom_bar, this);
        ImageView back = (ImageView) findViewById(R.id.back);
        mShare = (ImageView) findViewById(R.id.share);
        ImageView mutilWindow = (ImageView) findViewById(R.id.mutil_window);
        TextView commentBar = (TextView) findViewById(R.id.comment_bar);
        mCommentNum = (ImageView) findViewById(R.id.comment_num);
        back.setOnClickListener(this);
        mShare.setOnClickListener(this);
        mutilWindow.setOnClickListener(this);
        mCommentNum.setOnClickListener(this);
        commentBar.setOnClickListener(this);
    }

    public void updateCommentNum() {

    }

    public void updateShareIcon() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back:
                if (mOnWebPageBottomBarClickListener != null) {
                    mOnWebPageBottomBarClickListener.onClickBack();
                }
                break;
            case R.id.share:
                if (mOnWebPageBottomBarClickListener != null) {
                    mOnWebPageBottomBarClickListener.onClickShare();
                }
                break;
            case R.id.comment_num:
                if (mOnWebPageBottomBarClickListener != null) {
                    mOnWebPageBottomBarClickListener.onClickCommentNum();
                }
                break;
            case R.id.mutil_window:
                if (mOnWebPageBottomBarClickListener != null) {
                    mOnWebPageBottomBarClickListener.onClickMutilWindow();
                }
                break;
            case R.id.comment_bar:
                if (mOnWebPageBottomBarClickListener != null) {
                    mOnWebPageBottomBarClickListener.onClickCommentBar();
                }
                break;
        }
    }

    interface OnWebPageBottomBarClickListener{
        void onClickBack();
        void onClickCommentBar();
        void onClickShare();
        void onClickCommentNum();
        void onClickMutilWindow();
    }

    public void setOnWebPageBottomBarClickListener(OnWebPageBottomBarClickListener onWebPageBottomBarClickListener) {
        this.mOnWebPageBottomBarClickListener = onWebPageBottomBarClickListener;
    }

}
