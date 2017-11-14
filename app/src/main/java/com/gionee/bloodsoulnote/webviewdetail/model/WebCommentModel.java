package com.gionee.bloodsoulnote.webviewdetail.model;

import com.gionee.bloodsoulnote.webviewdetail.IContract.IWebComment;
import com.gionee.bloodsoulnote.webviewdetail.bean.CommentBean;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import io.reactivex.schedulers.Schedulers;

public class WebCommentModel implements IWebComment.IModel {

    public WebCommentModel() {}

    @Override
    public void loadWebComments(final OnLoadCommentListener onLoadCommentListener) {
        Observable.just("loadWebComments")
                .filter(new Predicate<String>() {
                    @Override
                    public boolean test(String s) throws Exception {
                        return true;
                    }
                })
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        onLoadCommentListener.onLoadBefore();
                    }
                })
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(Schedulers.io())
                .map(new Function<String, List<CommentBean>>() {
                    @Override
                    public List<CommentBean> apply(String type) throws Exception {
                        return getWebComments();
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .doOnComplete(new Action() {
                    @Override
                    public void run() throws Exception {
                        onLoadCommentListener.onLoadAfter();
                    }
                })
                .subscribe(new Consumer<List<CommentBean>>() {
                    @Override
                    public void accept(List<CommentBean> webpageInfo) throws Exception {
                        onLoadCommentListener.onResult(webpageInfo);
                    }
                });
    }

    private List<CommentBean> getWebComments() {
        List<CommentBean> datas = new ArrayList<>();
//        for (int i = 0; i < 5; i++) {
//            CommentBean bean = new CommentBean();
//            bean.setComment("评论区 - " + i);
//            bean.setGroupId("hot");
//            datas.add(bean);
//        }
//        for (int i = 5; i < 15; i++) {
//            CommentBean bean = new CommentBean();
//            bean.setComment("评论区 - " + i);
//            bean.setGroupId("new");
//            datas.add(bean);
//        }
        return datas;
    }
}
