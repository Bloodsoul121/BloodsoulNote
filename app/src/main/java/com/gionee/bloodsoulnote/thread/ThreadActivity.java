package com.gionee.bloodsoulnote.thread;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.gionee.bloodsoulnote.R;

public class ThreadActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thread);
    }

    public void clickBtn1(View view) {
        TestThread testThread = new TestThread();
        testThread.test1();
    }

    public void clickBtn2(View view) {
        TestThread testThread = new TestThread();
        testThread.test2();
    }
}
