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

import io.reactivex.functions.Consumer;

public class RxJavaActivity extends AppCompatActivity {

    private static final String TAG = "RxJavaActivity";

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
//11-08 15:11:51.432 3860-3860/com.gionee.bloodsoulnote I/RxJavaActivity: 5 data ---> TestEvent2
//11-08 15:11:56.283 3860-3860/com.gionee.bloodsoulnote I/RxJavaActivity: 5 data ---> TestEvent2
}
