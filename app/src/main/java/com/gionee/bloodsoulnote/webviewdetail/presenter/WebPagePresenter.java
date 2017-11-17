package com.gionee.bloodsoulnote.webviewdetail.presenter;

import com.gionee.bloodsoulnote.webviewdetail.IContract.IWebPage;
import com.gionee.bloodsoulnote.webviewdetail.bean.WebpageBean;
import com.gionee.bloodsoulnote.webviewdetail.model.WebPageModel;

public class WebPagePresenter implements IWebPage.IPresenter{

    private IWebPage.IView mView;
    private WebPageModel mModel;

    public WebPagePresenter(IWebPage.IView view) {
        this.mView = view;
        this.mView.bindPresenter(this);
        this.mView.initView();
        this.mModel = new WebPageModel();
    }

    public static WebPagePresenter bindPresenter(IWebPage.IView view) {
        return new WebPagePresenter(view);
    }

    @Override
    public void loadWebpageInfo() {
        this.mModel.loadWebpageInfo(new IWebPage.IModel.OnLoadCommentBarListener() {
            @Override
            public void onLoadBefore() {
                mView.onLoadWebInfoBefore();
            }

            @Override
            public void onLoadAfter() {
                mView.onLoadWebInfoAfter();
            }

            @Override
            public void onResult(WebpageBean webpageInfo) {
                mView.onLoadWebInfoResult(webpageInfo);
            }

            @Override
            public void onFailed(String info) {
                mView.onLoadWebInfoFailed();
            }
        });
    }

    @Override
    public void publish(String publishContent) {
        // 判断用户是否已经登录
        if (isUserNeedLogin()) {
            // 如果没有登录, 则登录

        } else if (isUserDiscussLimited()){
            // 是否是黑用户
            mView.publishFailed();
        } else {
            // 发表成功
            mView.publishSucceed();
        }

    }

    private boolean isUserNeedLogin() {
        return false;
    }

    private boolean isUserDiscussLimited() {
        return false;
    }
}
