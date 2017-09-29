package com.gionee.bloodsoulnote.button;

import android.util.Log;

/**
 * Created by cgz on 17-9-28.
 */

public class Son extends Father{

    public void show() {
        a = 20;
        Log.i("showshow", "son a ---> " + a);
        Log.i("showshow", "father a ---> " + super.a);
    }

}
