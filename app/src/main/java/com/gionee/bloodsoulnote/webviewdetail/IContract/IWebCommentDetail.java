package com.gionee.bloodsoulnote.webviewdetail.IContract;

public class IWebCommentDetail {

    public interface IView {
        // 初始化
        void initView();
        // 绑定presenter
        void bindPresenter(IPresenter presenter);

        void publishFailed();

        void publishSucceed(String comment);
    }

    public interface IPresenter {

        void publish(String comment);
    }

    public interface IModel {


    }

}
