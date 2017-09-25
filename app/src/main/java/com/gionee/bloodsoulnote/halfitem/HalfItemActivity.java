package com.gionee.bloodsoulnote.halfitem;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class HalfItemActivity extends AppCompatActivity {

    private ListView mListView;

    private TextView mStart;

    private TextView mEnd;

    private ExposureThread mExposureThread;

    private List<String> mDatas = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.gionee.bloodsoulnote.R.layout.activity_half_item);
        mStart = (TextView) findViewById(com.gionee.bloodsoulnote.R.id.half_item_start);
        mEnd = (TextView) findViewById(com.gionee.bloodsoulnote.R.id.half_item_end);
        mListView = (ListView) findViewById(com.gionee.bloodsoulnote.R.id.listview);
        init();

        mStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int start = mListView.getFirstVisiblePosition();
                Boolean itemDisplayHalf = isItemDisplayHalf(start, mListView);
                Log.i("HalfItemActivity", "start position : " + start + ", itemDisplayHalf - " + itemDisplayHalf);
            }
        });
        mEnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int end = mListView.getLastVisiblePosition();
                Boolean itemDisplayHalf = isItemDisplayHalf(end, mListView);
                Log.i("HalfItemActivity", "end position : " + end + ", itemDisplayHalf - " + itemDisplayHalf);
            }
        });
    }

    private void init() {
        for (int i = 0; i < 100; i++) {
            mDatas.add("item - " + i);
        }

        mListView.setAdapter(new BaseAdapter() {
            @Override
            public int getCount() {
                return mDatas.size();
            }

            @Override
            public Object getItem(int position) {
                return null;
            }

            @Override
            public long getItemId(int position) {
                return 0;
            }

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                ViewHolder holder;
                if (convertView == null) {
                    holder = new ViewHolder();
                    convertView = LayoutInflater.from(HalfItemActivity.this).inflate(com.gionee.bloodsoulnote.R.layout.half_item_item, null);
                    holder.tv = (TextView) convertView.findViewById(com.gionee.bloodsoulnote.R.id.half_item_tv);
                    convertView.setTag(holder);
                } else {
                    holder = (ViewHolder) convertView.getTag();
                }
                holder.tv.setText(mDatas.get(position));
                return convertView;
            }

            class ViewHolder {
                TextView tv;
            }

        });
    }

    private Boolean isItemDisplayHalf(int position, ListView listView){
        int start = listView.getFirstVisiblePosition(); ///获取可见区域的第一个索引
        int end = listView.getLastVisiblePosition(); //可见区域的最后一个索引

        Log.i("HalfItemActivity", "start : " + start + ", end : " + end + ", position : " + position);

        View child = listView.getChildAt(position - start);

        Log.i("HalfItemActivity", "getBottom : " + child.getBottom() + ", getTop : " + child.getTop() + ", getHeight/2 : " + child.getHeight()/2);

        if(position > start && position < end){
            return true;
        }else if(position == start){
            return Math.abs(child.getBottom()) > child.getHeight()/2;
        }else if(position == end){
            return Math.abs((listView.getHeight() - child.getTop())) > child.getHeight()/2;
        }else {
            return false;
        }
    }

    private class ExposureThread extends Thread {

        public boolean mIsRuning = true;

        @Override
        public void run() {
            while (mIsRuning) {
                try {
                    record();
                    sleep(700);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    return;
                }
            }
        }
    }

    private void record() {
        Log.i("HalfItemActivity", "record - " + System.currentTimeMillis());
    }

    public void startExposreRecord() {
        synchronized (ExposureThread.class) {
            if (mExposureThread == null) {
                mExposureThread = new ExposureThread();
                mExposureThread.start();
            }
        }
    }

    public void endExposreRecord() {
        synchronized (ExposureThread.class) {
            if (mExposureThread != null) {
                mExposureThread.mIsRuning = false;
                mExposureThread = null;
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        endExposreRecord();
    }
}
