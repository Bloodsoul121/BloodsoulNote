package com.gionee.bloodsoulnote.heap;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.gionee.bloodsoulnote.R;
import com.gionee.bloodsoulnote.heap.lrucache.ImgLruCache;

public class HeapActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_heap);
    }

    public void clickBtn1(View view) {
        ImgLruCache imgLruCache = new ImgLruCache();
        imgLruCache.evictAll();
    }
}
