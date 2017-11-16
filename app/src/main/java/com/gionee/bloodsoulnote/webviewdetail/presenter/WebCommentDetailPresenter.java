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


}
