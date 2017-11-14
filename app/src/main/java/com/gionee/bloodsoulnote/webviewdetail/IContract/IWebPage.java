package com.gionee.bloodsoulnote.webviewdetail.IContract;

import com.gionee.bloodsoulnote.webviewdetail.bean.WebpageBean;

public class IWebPage {

    public interface IView {
        // 初始化
        void initView();
        // 绑定presenter
        void bindPresenter(IPresenter presenter);
        // 发表成功
        void publishSucceed();
    }

    public interface IPresenter {
        void loadWebpageInfo();

        void publish(String publishContent);
    }

    public interface IModel {

        void loadWebpageInfo(OnLoadCommentBarListener onLoadCommentBarListener);

        interface OnLoadCommentBarListener {
            void onLoadBefore();
            void onLoadAfter();
            void onResult(WebpageBean webpageInfo);
            void onFailed(String info);
        }
    }

}
