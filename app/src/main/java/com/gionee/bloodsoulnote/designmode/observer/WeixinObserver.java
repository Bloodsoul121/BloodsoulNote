package com.gionee.bloodsoulnote.designmode.observer;

import android.util.Log;

/**
 * Created by cgz on 17-10-21.
 */

public class WeixinObserver implements Observer {

    private String name;

    public WeixinObserver(String name) {
        this.name = name;
    }

    @Override
    public void update(String message) {
        Log.i("WeixinObserver", name + "   update " + message);
    }

}
