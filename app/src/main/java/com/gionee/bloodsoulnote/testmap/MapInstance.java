package com.gionee.bloodsoulnote.testmap;

import android.util.Log;

import java.lang.ref.WeakReference;

/**
 * Created by cgz on 17-12-12.
 */

public class MapInstance {

    private WeakReference<Object> object;

    private MapInstance(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    Log.i("MapInstance", " --> " + (object == null));
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();

    }

    public static MapInstance getInstance() {
        return Holder.sInstance;
    }

    private static class Holder {
        private static final MapInstance sInstance = new MapInstance();
    }

    public void setObject(Object object) {
        this.object = new WeakReference<>(object);
    }



}
