package com.gionee.bloodsoulnote.webviewdetail.view;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewStub;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.gionee.bloodsoulnote.R;
import com.gionee.bloodsoulnote.webviewdetail.IContract.IWebPage;
import com.gionee.bloodsoulnote.webviewdetail.base.ViewHolder;
import com.gionee.bloodsoulnote.webviewdetail.bean.CommentBean;
import com.gionee.bloodsoulnote.webviewdetail.bean.WebpageBean;
import com.gionee.bloodsoulnote.webviewdetail.presenter.WebPagePresenter;
import com.gionee.bloodsoulnote.webviewdetail.util.AnimatorUtil;

public class WebPageView extends LinearLayout implements IWebPage.IView,
                                                        CommentView.OnNeedOpenCommentDetailListener,
                                                        CommentDetailView.OnCommentDetailClickListener,
                                                        DiscussView.OnDiscussViewClickListener,
                                                        WebPageBottomBar.OnWebPageBottomBarClickListener, View.OnClickListener {

    private Context mContext;

    private IWebPage.IPresenter mPresenter;

    private WebDetailView mWebDetailView;

    private WebPageBottomBar mCommentBottomBar;

    private DiscussView mDiscussView;

    private CommentDetailView mCommentDetailView;

    private Toast mToast;

    private int mWidth;
    private View mDiscussBg;

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
//        mPresenter.loadWebpageInfo();
    }

    @Override
    public void bindPresenter(IWebPage.IPresenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void initView() {
        mWebDetailView = (WebDetailView) findViewById(R.id.web_detail_view);

        ViewStub webPageBottomBar = (ViewStub) findViewById(R.id.viewstub_web_page_bottom_bar);
        if (isToggleOpen()) {
            webPageBottomBar.inflate();
            initWebCommentVIew();
        }
    }

    private boolean isToggleOpen() {
        return true;
    }

    private void initWebCommentVIew() {
        // bottom bar
        mCommentBottomBar = (WebPageBottomBar) findViewById(R.id.web_bottom_bar);
        // discuss box
        mDiscussView = (DiscussView) findViewById(R.id.discuss_view);
        // comment detail view
        mCommentDetailView = (CommentDetailView) findViewById(R.id.comment_detail_view);
        mDiscussBg = findViewById(R.id.discuss_background);
        initEvent();
    }

    private void initEvent() {
        mWebDetailView.setOnNeedOpenCommentDetailListener(this);
        mCommentBottomBar.setOnWebPageBottomBarClickListener(this);
        mDiscussView.setOnDiscussViewClickListener(this);
        mCommentDetailView.setOnCommentDetailClickListener(this);
        mDiscussBg.setOnClickListener(this);
    }

    @Override
    public void onLoadWebInfoBefore() {

    }

    @Override
    public void onLoadWebInfoAfter() {

    }

    @Override
    public void onLoadWebInfoResult(WebpageBean webpageInfo) {

    }

    @Override
    public void onLoadWebInfoFailed() {

    }

    @Override
    public void onDiscussViewClickCancel() {
        showBottomBar();
    }

    @Override
    public void onDiscussViewClickPublish(String comment) {
        if (TextUtils.isEmpty(comment)) {
            toast("发表不能为空");
            return;
        }
        mPresenter.publish(comment);
        mDiscussView.clear();
    }

    @Override
    public void onCommentDetailViewClickBack(CommentBean data, boolean isDataChange) {

    }

    @Override
    public void onCommentDetailViewSwipeFinish(CommentBean data, boolean isDataChange) {
        mCommentDetailView.setVisibility(GONE);
    }

    @Override
    public void onNeedOpenCommentDetailView(ViewHolder viewHolder, CommentBean data, int position) {
        // 打开评论详情页
        mCommentDetailView.setVisibility(VISIBLE);
        mCommentDetailView.OpenCommentDetail(data, mWidth <= 0 ? getWidth() : mWidth);
    }

    @Override
    public void onBottomBarClickBack() {
        toast("返回");
    }

    @Override
    public void onBottomBarClickComment() {
        showDiscussBox();
    }

    @Override
    public void onBottomBarClickShare() {
        toast("分享");
    }

    @Override
    public void onBottomBarClickCommentNum() {
        toast("打开评论区");
    }

    @Override
    public void onBottomBarClickMutilWindow() {
        toast("多窗口");
    }

    @Override
    public void publishSucceed(CommentBean bean) {
        // 发表成功
        showBottomBar();
        toast(getResources().getString(R.string.publish_succeed));
        // 更新评论区, 滑到最新评论区
        updateSelfComment(bean);
    }

    @Override
    public void publishFailed() {
        // 发表失败
        toast(getResources().getString(R.string.publish_failed));
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = getWidth();
    }

    private void updateSelfComment(CommentBean bean) {
        mWebDetailView.updateSelfComment(bean);
    }

    private void showDiscussBox() {
        mDiscussView.showDiscussBox();
        mCommentBottomBar.setVisibility(GONE);
        showDiscussBg();
    }

    private void showBottomBar() {
        mDiscussView.hideDiscussBox();
        mCommentBottomBar.setVisibility(VISIBLE);
        hideDiscussBg();
    }

    private void showDiscussBg() {
        AnimatorUtil.startAlphaAnimator(mDiscussBg, 0f, 1.0f, VISIBLE);
    }

    private void hideDiscussBg() {
        AnimatorUtil.startAlphaAnimator(mDiscussBg, 1.0f, 0f, GONE);
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

    public boolean onBackCliked() {
        if (mCommentDetailView != null) {
            if (mCommentDetailView.onBackCliked()) {
                return true;
            }
            if (mCommentDetailView.getVisibility() == VISIBLE) {
                mCommentDetailView.hide();
                return true;
            }
        }
        if (mDiscussView != null && mDiscussView.getVisibility() == VISIBLE) {
            showBottomBar();
            return true;
        }
        return false;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.discuss_background:
                showBottomBar();
                break;
        }
    }
}
