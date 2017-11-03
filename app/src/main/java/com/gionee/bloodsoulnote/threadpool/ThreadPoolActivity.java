package com.gionee.bloodsoulnote.threadpool;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.gionee.bloodsoulnote.R;

public class ThreadPoolActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thread_pool);
    }

    public void clickBtn1(View view) {
        ThreadPoolUtil.getInstance().execute();
    }

    public void clickBtn2(View view) {

    }

    public void clickBtn3(View view) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ThreadPoolUtil.getInstance().recycle();
    }
}
