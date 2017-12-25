package com.gionee.bloodsoulnote.testmap;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.gionee.bloodsoulnote.R;

import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;

public class TestMapActivity extends AppCompatActivity {

    private Map<Integer, MapTest> mMaps = new HashMap<>();

    private MapTest mMapTest;

    private WeakReference<MapTest> mMapTestTemp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_map);
    }

    public void clickBtn1(View view) {
        MapTest mapTest = new MapTest();
        mMaps.put(1, mapTest);
        this.mMapTest = mapTest;
        this.mMapTestTemp = new WeakReference<>(mapTest);
        MapInstance.getInstance().setObject(new MapTest());
    }

    public void clickBtn2(View view) {
        for (MapTest v : mMaps.values()) {
            v = null;
        }
        mMaps.clear();
    }

    public void clickBtn3(View view) {
        Log.i("TestMapActivity", " ---> " + mMaps.size());
        Log.i("TestMapActivity", " ---> " + (mMapTest == null));
        Log.i("TestMapActivity", " ---> " + (mMapTestTemp == null));
    }
}
