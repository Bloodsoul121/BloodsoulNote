package com.gionee.bloodsoulnote.designmode.observer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.gionee.bloodsoulnote.R;

public class ObserverActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_observer);
    }

    private void subscribe() {
        ConcreteSubject subject = new ConcreteSubject();

        Observer guolin = new WeixinObserver("郭霖");
        Observer hongyang = new WeixinObserver("洪洋");

        subject.attach(guolin);
        subject.attach(hongyang);

        subject.notify("刘望舒的专栏更新了");
    }

}
