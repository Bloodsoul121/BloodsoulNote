package com.gionee.bloodsoulnote.webviewdetail.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.gionee.bloodsoulnote.R;
import com.gionee.bloodsoulnote.webviewdetail.util.NumFormatUtil;

public class WebPageBottomBar extends RelativeLayout implements View.OnClickListener {

    private TextView mCommentNum;

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
        mCommentNum = (TextView) findViewById(R.id.comment_num);
        back.setOnClickListener(this);
        mShare.setOnClickListener(this);
        mutilWindow.setOnClickListener(this);
        mCommentNum.setOnClickListener(this);
        commentBar.setOnClickListener(this);
    }

    public void updateCommentNum(String num) {
        mCommentNum.setText(NumFormatUtil.formatNum(num));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back:
                if (mOnWebPageBottomBarClickListener != null) {
                    mOnWebPageBottomBarClickListener.onBottomBarClickBack();
                }
                break;
            case R.id.share:
                if (mOnWebPageBottomBarClickListener != null) {
                    mOnWebPageBottomBarClickListener.onBottomBarClickShare();
                }
                break;
            case R.id.comment_num:
                if (mOnWebPageBottomBarClickListener != null) {
                    mOnWebPageBottomBarClickListener.onBottomBarClickCommentNum();
                }
                break;
            case R.id.mutil_window:
                if (mOnWebPageBottomBarClickListener != null) {
                    mOnWebPageBottomBarClickListener.onBottomBarClickMutilWindow();
                }
                break;
            case R.id.comment_bar:
                if (mOnWebPageBottomBarClickListener != null) {
                    mOnWebPageBottomBarClickListener.onBottomBarClickComment();
                }
                break;
        }
    }

    interface OnWebPageBottomBarClickListener{
        void onBottomBarClickBack();
        void onBottomBarClickComment();
        void onBottomBarClickShare();
        void onBottomBarClickCommentNum();
        void onBottomBarClickMutilWindow();
    }

    public void setOnWebPageBottomBarClickListener(OnWebPageBottomBarClickListener onWebPageBottomBarClickListener) {
        this.mOnWebPageBottomBarClickListener = onWebPageBottomBarClickListener;
    }

}
