package com.gionee.bloodsoulnote.rxjava.rx2;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.subjects.PublishSubject;
import io.reactivex.subjects.Subject;

public class RxBus3 {

    private static volatile RxBus3 mInstance;

    private final Subject<Object> subject = PublishSubject.create().toSerialized();

    private Disposable dispoable;

    private RxBus3() {}

    public static RxBus3 getInstance() {
        if (mInstance == null) {
            synchronized (RxBus3.class) {
                if (mInstance == null) {
                    mInstance = new RxBus3();
                }
            }
        }
        return mInstance;
    }


    /**
     * 发送事件
     *
     * @param object
     */
    public void send(Object object) {
        subject.onNext(object);
    }


    /**
     * @param classType
     * @param <T>
     * @return
     */
    public <T> Observable<T> toObservale(Class<T> classType) {
        return subject.ofType(classType);
    }


    /**
     * 订阅
     *
     * @param bean
     * @param consumer
     */
    public void subscribe(Class bean, Consumer consumer) {
        dispoable = toObservale(bean).subscribe(consumer);
    }

    /**
     * 取消订阅
     */
    public void unSubcribe() {
        if (dispoable != null && dispoable.isDisposed()) {
            dispoable.dispose();
        }

    }

}