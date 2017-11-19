package com.gionee.bloodsoulnote.webviewdetail.presenter;

import com.gionee.bloodsoulnote.webviewdetail.IContract.IWebComment;
import com.gionee.bloodsoulnote.webviewdetail.bean.CommentBean;
import com.gionee.bloodsoulnote.webviewdetail.model.WebCommentModel;

import java.util.List;

public class WebCommentPresenter implements IWebComment.IPresenter {

    private IWebComment.IView mView;
    private IWebComment.IModel mModel;

    public WebCommentPresenter(IWebComment.IView view) {
        this.mView = view;
        this.mView.bindPresenter(this);
        this.mView.initView();
        this.mModel = new WebCommentModel();
    }

    public static WebCommentPresenter bindPresenter(IWebComment.IView view) {
        return new WebCommentPresenter(view);
    }

    @Override
    public void loadMoreComments() {
        this.mModel.loadWebComments(new IWebComment.IModel.OnLoadCommentListener() {
            @Override
            public void onLoadBefore() {
                mView.onLoadBefore();
            }

            @Override
            public void onLoadAfter() {
                mView.onLoadEnd();
            }

            @Override
            public void onResult(List<CommentBean> comments) {
                mView.updateNewData(comments);
            }

            @Override
            public void onFailed(String info) {
                mView.onLoadFailed();
            }
        });
    }

    @Override
    public void requestLike() {
        mModel.requestLike();
    }
}
