package com.gionee.bloodsoulnote.webviewdetail.model;

import com.gionee.bloodsoulnote.webviewdetail.IContract.IWebPage;
import com.gionee.bloodsoulnote.webviewdetail.bean.WebpageBean;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import io.reactivex.schedulers.Schedulers;

public class WebPageModel implements IWebPage.IModel {

    public WebPageModel() {}

    @Override
    public void loadWebpageInfo(final OnLoadCommentBarListener onLoadCommentBarListener) {
        Observable.just("loadWebpageInfo")
                .filter(new Predicate<String>() {
                    @Override
                    public boolean test(String s) throws Exception {
                        return true;
                    }
                })
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        onLoadCommentBarListener.onLoadBefore();
                    }
                })
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(Schedulers.io())
                .map(new Function<String, WebpageBean>() {
                    @Override
                    public WebpageBean apply(String type) throws Exception {
                        return getWebPageInfo();
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .doOnComplete(new Action() {
                    @Override
                    public void run() throws Exception {
                        onLoadCommentBarListener.onLoadAfter();
                    }
                })
                .subscribe(new Consumer<WebpageBean>() {
                    @Override
                    public void accept(WebpageBean webpageInfo) throws Exception {
                        onLoadCommentBarListener.onResult(webpageInfo);
                    }
                });
    }

    private WebpageBean getWebPageInfo() {
        WebpageBean bean = new WebpageBean();
        bean.setCommentNum("503");
        return bean;
    }


}
