package com.gionee.bloodsoulnote.webviewdetail.presenter;

import com.gionee.bloodsoulnote.webviewdetail.I.IWebComment;
import com.gionee.bloodsoulnote.webviewdetail.bean.CommentBean;
import com.gionee.bloodsoulnote.webviewdetail.model.WebCommentModel;

import java.util.List;

public class WebCommentPresenter implements IWebComment.IPresenter {

    private IWebComment.IView mView;
    private WebCommentModel   mModel;

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
        this.mModel.loadComment(new IWebComment.IModel.OnLoadCommentListener() {
            @Override
            public void onLoadBefore() {

            }

            @Override
            public void onLoadAfter() {

            }

            @Override
            public void onResult(List<CommentBean> comments) {
                mView.updateNewData(comments);
            }

            @Override
            public void onFailed(String info) {

            }
        });
    }
}
