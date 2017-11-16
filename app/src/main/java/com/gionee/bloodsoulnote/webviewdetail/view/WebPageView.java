package com.gionee.bloodsoulnote.webviewdetail.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.gionee.bloodsoulnote.R;
import com.gionee.bloodsoulnote.webviewdetail.IContract.IWebPage;
import com.gionee.bloodsoulnote.webviewdetail.base.ViewHolder;
import com.gionee.bloodsoulnote.webviewdetail.bean.CommentBean;
import com.gionee.bloodsoulnote.webviewdetail.presenter.WebPagePresenter;

public class WebPageView extends RelativeLayout implements IWebPage.IView {

    private Context mContext;

    private IWebPage.IPresenter mPresenter;

    private WebDetailView mWebDetailView;

    private WebPageBottomBar mCommentBottomBar;

    private DiscussView mDiscussView;

    private CommentDetailView mCommentDetailView;

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
    public void bindPresenter(IWebPage.IPresenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void initView() {
        mWebDetailView = (WebDetailView) findViewById(R.id.web_detail_view);
        // bottom bar
        mCommentBottomBar = (WebPageBottomBar) findViewById(R.id.web_bottom_bar);
        // discuss box
        mDiscussView = (DiscussView) findViewById(R.id.discuss_view);
        // comment detail view
        mCommentDetailView = (CommentDetailView) findViewById(R.id.comment_detail_view);
        initEvent();
    }

    private void initEvent() {
        mWebDetailView.setOnNeedOpenCommentDetailListener(new CommentView.OnNeedOpenCommentDetailListener() {
            @Override
            public void onNeedOpenCommentDetail(ViewHolder viewHolder, CommentBean data, int position) {
                // 打开评论详情页
                toast("打开评论详情页");
                OpenCommentDetail(data, position);
            }
        });
        mCommentBottomBar.setOnWebPageBottomBarClickListener(new WebPageBottomBar.OnWebPageBottomBarClickListener() {
            @Override
            public void onClickBack() {
                toast("返回");
            }

            @Override
            public void onClickCommentBar() {
                showDiscussBox();
            }

            @Override
            public void onClickShare() {
                toast("分享");
            }

            @Override
            public void onClickCommentNum() {
                toast("打开评论区");
            }

            @Override
            public void onClickMutilWindow() {
                toast("多窗口");
            }
        });
        mDiscussView.setOnDiscussViewClickListener(new DiscussView.OnDiscussViewClickListener() {
            @Override
            public void onCancelClick() {
                showBottomBar();
            }

            @Override
            public void onPublishComment(String comment) {
                mPresenter.publish(comment);
            }
        });
        mCommentDetailView.setOnCommentDetailClickListener(new CommentDetailView.OnCommentDetailClickListener() {
            @Override
            public void onClickTopBack() {

            }

            @Override
            public void onViewFinish() {
                mCommentDetailView.setVisibility(GONE);
            }
        });
    }

    private void OpenCommentDetail(CommentBean data, int position) {
        mCommentDetailView.setVisibility(VISIBLE);
        mCommentDetailView.OpenCommentDetail();
        Log.i("WebPageView", "OpenCommentDetail --> " + mCommentDetailView.getLeft());
    }

    @Override
    public void publishSucceed() {
        // 发表成功
        showBottomBar();
        toast(getResources().getString(R.string.publish_succeed));
        // 更新评论区, 滑到最新评论区
        updateSelfComment();
    }

    @Override
    public void publishFailed() {
        // 发表失败
        toast(getResources().getString(R.string.publish_failed));
    }

    private void updateSelfComment() {
        mWebDetailView.updateSelfComment();
    }

    private void showDiscussBox() {
        mDiscussView.showDiscussBox();
        mCommentBottomBar.setVisibility(GONE);
    }

    private void showBottomBar() {
        mDiscussView.hideDiscussBox();
        mCommentBottomBar.setVisibility(VISIBLE);
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
