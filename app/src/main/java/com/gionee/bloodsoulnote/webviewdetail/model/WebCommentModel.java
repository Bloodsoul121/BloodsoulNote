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
                        // // TODO: 17-11-14  模拟3秒的加载
                        Thread.currentThread().sleep(1000);
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

    @Override
    public void requestLike() {

    }

    private List<CommentBean> getWebComments() {
        List<CommentBean> datas = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            CommentBean bean = new CommentBean();
            bean.setName("name - " + i);
            bean.setComment("评论区 - " +
                    "Named and variadic parameters can be used in the same function, simply by placing the variadic ellipsis after the list of named parameters. \n" +
                    "可以在同一个函数中同时使用命名参数和可变参数，只需要将可变参数的省略号放在命名参数列表之后。Named and variadic parameters can be used in the same function, simply by placing the variadic ellipsis after the list of named parameters. \n" +
                    "可以在同一个函数中同时使用命名参数和可变参数，只需要将可变参数的省略号放在命名参数列表之后。vNamed and variadic parameters can be used in the same function, simply by placing the variadic ellipsis after the list of named parameters. \n" +
                    "可以在同一个函数中同时使用命名参数和可变参数，只需要将可变参数的省略号放在命名参数列表之后。" + i);
            bean.setGroupId("最热评论");
            bean.setId("" + i);

            List<CommentBean> details = new ArrayList<>();
            for (int j = 0; j < 3; j++) {
                CommentBean detailBean = new CommentBean();
                detailBean.setName("detail name " + j);
                detailBean.setComment("评论区 - " + i + j);
                detailBean.setGroupId("最热评论");
                detailBean.setId("" + i + "" + j);
                details.add(detailBean);
            }
            bean.setDetails(details);

            datas.add(bean);
        }
        for (int i = 5; i < 10; i++) {
            CommentBean bean = new CommentBean();
            bean.setName("name - " + i);
            bean.setComment("评论区 - " + i);
            bean.setGroupId("最新评论");
            bean.setId("" + i);

            List<CommentBean> details = new ArrayList<>();
            for (int j = 0; j < 3; j++) {
                CommentBean detailBean = new CommentBean();
                detailBean.setName("detail name " + j);
                detailBean.setComment("评论区 - " + i + j);
                detailBean.setGroupId("最新评论");
                detailBean.setId("" + i + "" + j);
                details.add(detailBean);
            }
            bean.setDetails(details);

            datas.add(bean);
        }
        return datas;
    }

}
