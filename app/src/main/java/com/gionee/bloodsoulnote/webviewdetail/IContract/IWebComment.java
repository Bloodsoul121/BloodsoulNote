package com.gionee.bloodsoulnote.webviewdetail.IContract;

import android.webkit.WebView;

import com.gionee.bloodsoulnote.webviewdetail.bean.CommentBean;

import java.util.List;

public interface IWebComment {

    public interface IView {
        // 初始化
        void initView();
        // 绑定webview
        void bindWebview(WebView webView);
        // 绑定presenter
        void bindPresenter(IPresenter presenter);
        // 刷新添加的数据
        void updateNewData(List<CommentBean> datas);
        // 刷新单个item数据
        void refreshItemData(int position);
        // 删除单个item数据
        void deleteItemData(int position);
    }

    public interface IPresenter {
        void loadMoreComments();
    }

    public interface IModel {

        void loadWebComments(OnLoadCommentListener onLoadCommentListener);

        interface OnLoadCommentListener {
            void onLoadBefore();
            void onLoadAfter();
            void onResult(List<CommentBean> comments);
            void onFailed(String info);
        }
    }

}
