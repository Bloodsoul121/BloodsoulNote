package com.gionee.bloodsoulnote.rxjava.rx2;

import android.util.Log;
import android.util.SparseArray;

import com.gionee.bloodsoulnote.rxjava.event.EventMsg;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.subjects.PublishSubject;

public class RxBus2 {

    private static volatile RxBus2 sInstance;

    private SparseArray<PublishSubject<EventMsg>> mCache = new SparseArray<>();

    private PublishSubject<EventMsg> mMsgPublishSubject;

    private RxBus2() {}

    public static RxBus2 getDefault() {
        if (sInstance == null) {
            synchronized (RxBus2.class) {
                if (sInstance == null) {
                    sInstance = new RxBus2();
                }
            }
        }
        return sInstance;
    }

    public synchronized void post(EventMsg eventMsg) {
        int size = mCache.size();
        for (int i = 0; i < size; ++i) {
            postIndividual(mCache.keyAt(i), eventMsg);
        }
    }

    private void postIndividual(int type, EventMsg eventMsg) {
        PublishSubject subject = mCache.get(type);
        if (subject == null) {
            Log.d("RxEventMsg", "没找到观察者 for type = " + type);
            return;
        }
        subject.onNext(eventMsg);
    }

    public synchronized void post(int type, EventMsg eventMsg) {
        PublishSubject subject = mCache.get(type);
        if (subject == null) {
            Log.d("RxEventMsg", "没找到观察者 for type = " + type);
            return;
        }
        subject.onNext(eventMsg);
    }

    public synchronized void postDefault(EventMsg eventMsg) {
        mMsgPublishSubject.onNext(eventMsg);
    }

    public final Disposable subscribe(int type, Consumer<? super EventMsg> onNext) {
        PublishSubject subject = PublishSubject.create();
        subject.observeOn(AndroidSchedulers.mainThread());
        Disposable disposable = subject.subscribe(onNext);
        mCache.put(type, subject);
        return disposable;
    }

    public synchronized final Disposable subscribeDefault(Consumer<? super EventMsg> onNext) {
        if (mMsgPublishSubject == null) {
            mMsgPublishSubject = PublishSubject.create();
            mMsgPublishSubject.observeOn(AndroidSchedulers.mainThread());
        }

        Disposable disposable = mMsgPublishSubject.subscribe(onNext);
        return disposable;
    }

}
