package com.gionee.bloodsoulnote.rxjava;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.gionee.bloodsoulnote.R;
import com.gionee.bloodsoulnote.rxjava.event.EventMsg;
import com.gionee.bloodsoulnote.rxjava.event.TestEvent1;
import com.gionee.bloodsoulnote.rxjava.event.TestEvent2;
import com.gionee.bloodsoulnote.rxjava.rx2.RxBus2;
import com.gionee.bloodsoulnote.rxjava.rx2.RxBus5;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

public class RxJavaActivity extends AppCompatActivity {

    private static final String TAG = "RxJavaActivity";
    private Disposable mDisposable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx_java);
    }

    public void clickBtn1(View view) {
        RxBus2.getDefault().subscribeDefault(new Consumer<EventMsg>() {
            @Override
            public void accept(EventMsg eventMsg) throws Exception {
                Log.i(TAG, "1 data ---> " + eventMsg.data);
            }
        });
    }

    public void clickBtn2(View view) {
        RxBus2.getDefault().postDefault(new TestEvent1("TestEvent1"));
    }

    public void clickBtn3(View view) {
        RxBus2.getDefault().subscribe(1, new Consumer<EventMsg>() {
            @Override
            public void accept(EventMsg eventMsg) throws Exception {
                Log.i(TAG, "3 data ---> " + eventMsg.data);
            }
        });
    }

    public void clickBtn4(View view) {
        RxBus2.getDefault().post(new TestEvent2("TestEvent2"));
    }

    public void clickBtn5(View view) {
        RxBus2.getDefault().subscribe(1, new Consumer<EventMsg>() {
            @Override
            public void accept(EventMsg eventMsg) throws Exception {
                Log.i(TAG, "5 data ---> " + eventMsg.data);
            }
        });
    }

    public void clickBtn6(View view) {
        RxBus2.getDefault().post(new TestEvent2("TestEvent2"));
    }

//11-08 15:11:49.051 3860-3860/com.gionee.bloodsoulnote I/RxJavaActivity: 1 data ---> TestEvent1
//11-08 15:11:56.283 3860-3860/com.gionee.bloodsoulnote I/RxJavaActivity: 5 data ---> TestEvent2
//11-08 15:11:51.432 3860-3860/com.gionee.bloodsoulnote I/RxJavaActivity: 5 data ---> TestEvent2


    public void clickBtn7(View view) {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            list.add("item - " + i);
        }
        Observable.fromArray(list, list)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<String>>() {
                    @Override
                    public void accept(List<String> strings) throws Exception {
                        Logger.i(strings.toString());
                    }
                });
        Observable.just(list)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<String>>() {
                    @Override
                    public void accept(List<String> strings) throws Exception {
                        Logger.i(strings.toString());
                    }
                });
        Observable.just(list)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<String>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(List<String> value) {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    public void clickBtn8(View view) {
        mDisposable = RxBus5.getDefault().register(TestEvent1.class, null, AndroidSchedulers.mainThread(), new Consumer<TestEvent1>() {
            @Override
            public void accept(TestEvent1 testEvent1) throws Exception {
                Logger.i("rx5 data TestEvent1 " + testEvent1.data);
            }
        });

        RxBus5.getDefault().register(TestEvent2.class)
                .subscribe(new Consumer<TestEvent2>() {
                    @Override
                    public void accept(TestEvent2 testEvent2) throws Exception {
                        Logger.i("rx5 data testEvent2 " + testEvent2.data);
                    }
                });
    }

    public void clickBtn9(View view) {
        RxBus5.getDefault().post(new TestEvent1("lalallalal"));
    }

    public void clickBtn10(View view) {
        RxBus5.getDefault().unregister(mDisposable);
    }

    public void clickBtn11(View view) {
        RxBus5.getDefault().unregisterAll();
    }
}
