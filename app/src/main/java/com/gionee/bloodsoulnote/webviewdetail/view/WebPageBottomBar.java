package com.gionee.bloodsoulnote.webviewdetail.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.gionee.bloodsoulnote.R;
import com.gionee.bloodsoulnote.webviewdetail.bean.WebpageBean;
import com.gionee.bloodsoulnote.webviewdetail.util.NumFormatUtil;

public class WebPageBottomBar extends RelativeLayout implements View.OnClickListener {

    private TextView mCommentNum;

    private ImageView mCollectIcon;

    private WebpageBean mWebpageBean;

    private boolean mIsCollected;

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
        mCollectIcon = (ImageView) findViewById(R.id.collect);
        ImageView share = (ImageView) findViewById(R.id.share);
        TextView commentBar = (TextView) findViewById(R.id.comment_bar);
        RelativeLayout commentNumGroup = (RelativeLayout) findViewById(R.id.comment_num_group);
        mCommentNum = (TextView) findViewById(R.id.comment_num);
        back.setOnClickListener(this);
        mCollectIcon.setOnClickListener(this);
        share.setOnClickListener(this);
        commentNumGroup.setOnClickListener(this);
        commentBar.setOnClickListener(this);
    }

    public void uppdateBottomBarStatus(WebpageBean webpageBean) {
        if (webpageBean == null) {
            return;
        }
        this.mWebpageBean = webpageBean;
        updateCommentNum(webpageBean.getCommentNum());
        updateCollectIcon(webpageBean.isCollected());
    }

    private void updateCommentNum(String num) {
        mCommentNum.setText(NumFormatUtil.formatNum(num));
    }

    private void updateCollectIcon(boolean isCollected) {
        this.mIsCollected = isCollected;
        mCollectIcon.setImageLevel(isCollected ? 1 : 0);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back:
                if (mOnWebPageBottomBarClickListener != null) {
                    mOnWebPageBottomBarClickListener.onBottomBarClickBack();
                }
                break;
            case R.id.collect:
                clickCollectIcon();
                if (mOnWebPageBottomBarClickListener != null) {
                    mOnWebPageBottomBarClickListener.onBottomBarClickCollect(mIsCollected);
                }
                break;
            case R.id.comment_num_group:
                if (mOnWebPageBottomBarClickListener != null) {
                    mOnWebPageBottomBarClickListener.onBottomBarClickCommentNum();
                }
                break;
            case R.id.share:
                if (mOnWebPageBottomBarClickListener != null) {
                    mOnWebPageBottomBarClickListener.onBottomBarClickShare();
                }
                break;
            case R.id.comment_bar:
                if (mOnWebPageBottomBarClickListener != null) {
                    mOnWebPageBottomBarClickListener.onBottomBarClickComment();
                }
                break;
        }
    }

    private void clickCollectIcon() {
        mCollectIcon.setImageLevel(mIsCollected ? 0 : 1);
        if (mIsCollected) {
            // 取消收藏, 请求
        } else {
            // 收藏, 请求 // TODO: 17-11-20
        }
        mIsCollected = !mIsCollected;
    }

    interface OnWebPageBottomBarClickListener{
        void onBottomBarClickBack();
        void onBottomBarClickComment();
        void onBottomBarClickShare();
        void onBottomBarClickCommentNum();
        void onBottomBarClickCollect(boolean isCollected);
    }

    public void setOnWebPageBottomBarClickListener(OnWebPageBottomBarClickListener onWebPageBottomBarClickListener) {
        this.mOnWebPageBottomBarClickListener = onWebPageBottomBarClickListener;
    }

}
