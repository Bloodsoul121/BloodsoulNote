package com.gionee.bloodsoulnote.rxjava.rx2;

import android.support.annotation.NonNull;

import com.gionee.bloodsoulnote.rxjava.event.EventMsg;
import com.orhanobut.logger.Logger;

import io.reactivex.Flowable;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.processors.FlowableProcessor;
import io.reactivex.processors.PublishProcessor;
import io.reactivex.schedulers.Schedulers;

public class RxBus5 {

    private final FlowableProcessor<Object> mBus;

    private RxBus5() {
        Logger.i("RxBus5", "create");
        mBus = PublishProcessor.create().toSerialized();
    }

    public static RxBus5 getDefault() {
        return Holder.sInstance;
    }

    private static class Holder {
        private static RxBus5 sInstance = new RxBus5();
    }

    public void post(@NonNull EventMsg obj) {
        mBus.onNext(obj);
    }

    public <T> Flowable<T> register(Class<T> clz) {
        return mBus.ofType(clz);
    }

    public <T> Disposable register(Class<T> clz, Consumer<T> consumer, Consumer<Throwable> error) {
        return register(clz, Schedulers.newThread(), AndroidSchedulers.mainThread(), consumer, error);
    }

    public <T> Disposable register(Class<T> clz, Scheduler subscribeOn, Scheduler observeOn, Consumer<T> consumer) {
        return register(clz, subscribeOn, observeOn, consumer, null);
    }

    public <T> Disposable register(Class<T> clz, Scheduler subscribeOn, Scheduler observeOn, Consumer<T> consumer, Consumer<Throwable> error) {
        Flowable<T> tFlowable = mBus.ofType(clz);
        if (subscribeOn != null) {
            tFlowable = tFlowable.subscribeOn(subscribeOn);
        }
        if (observeOn != null) {
            tFlowable = tFlowable.observeOn(observeOn);
        }
        if (error == null) {
            return tFlowable.subscribe(consumer);
        }
        return tFlowable.subscribe(consumer, error);
    }

    public void unregister(Disposable disposable) {
        if (disposable != null && !disposable.isDisposed()) {
            disposable.dispose();
        }
    }

    public void unregisterAll() {
        mBus.onComplete();
    }

    public boolean hasSubscribers() {
        return mBus.hasSubscribers();
    }

}
