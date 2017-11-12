package com.gionee.bloodsoulnote.webviewdetail.model;

import android.util.Log;

import com.gionee.bloodsoulnote.webviewdetail.I.IWebComment;
import com.gionee.bloodsoulnote.webviewdetail.bean.CommentBean;

import java.util.ArrayList;
import java.util.List;

public class WebCommentModel implements IWebComment.IModel {

    public WebCommentModel() {}

    @Override
    public void loadComment(OnLoadCommentListener onLoadCommentListener) {
        // // TODO: 2017/11/12
        // 用rxjava
        onLoadCommentListener.onResult(initData());
    }

    private List<CommentBean> initData() {
        Log.i("bloodsoul", "initData");
        List<CommentBean> datas = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            CommentBean bean = new CommentBean();
            bean.setComment("评论区 - " + i);
            datas.add(bean);
        }
        return datas;
    }
}
