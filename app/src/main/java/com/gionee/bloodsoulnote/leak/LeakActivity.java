package com.gionee.bloodsoulnote.leak;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.gionee.bloodsoulnote.NoteApplication;
import com.gionee.bloodsoulnote.R;

public class LeakActivity extends AppCompatActivity {

    private MyAsyncTask mMyAsyncTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leak);
    }

    public void clickBtn1(View view) {
        startAsyncTask();
        finish();
    }

    private void startAsyncTask() {
        mMyAsyncTask = new MyAsyncTask(this);
        mMyAsyncTask.execute();
    }

    private static class MyAsyncTask extends AsyncTask<Void, Void, Void> {

        private final Context context;  // 会泄漏

        public MyAsyncTask(Context context) {
            this.context = context;
        }

        @Override
        protected Void doInBackground(Void... params) {
            while (true) ;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        NoteApplication.getRefWatcher(this).watch(this);
    }


}
