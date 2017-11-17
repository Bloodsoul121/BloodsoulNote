package com.gionee.bloodsoulnote.webviewdetail.presenter;

import com.gionee.bloodsoulnote.webviewdetail.IContract.IWebCommentDetail;

public class WebCommentDetailPresenter implements IWebCommentDetail.IPresenter{

    private IWebCommentDetail.IView mView;

    public WebCommentDetailPresenter(IWebCommentDetail.IView view) {
        this.mView = view;
        this.mView.bindPresenter(this);
        this.mView.initView();
    }

    public static WebCommentDetailPresenter bindPresenter(IWebCommentDetail.IView view) {
        return new WebCommentDetailPresenter(view);
    }


    @Override
    public void publish(String comment) {
        // 判断用户是否已经登录
        if (isUserNeedLogin()) {
            // 如果没有登录, 则登录

        } else if (isUserDiscussLimited()){
            // 是否是黑用户
            mView.publishFailed();
        } else {
            // 发表成功
            mView.publishSucceed(comment);
        }
    }


    private boolean isUserNeedLogin() {
        return false;
    }

    private boolean isUserDiscussLimited() {
        return false;
    }
}
