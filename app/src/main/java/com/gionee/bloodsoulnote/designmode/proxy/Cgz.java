package com.gionee.bloodsoulnote.designmode.proxy;

import android.util.Log;

/**
 * Created by cgz on 17-10-21.
 */

public class Cgz implements IShop {

    @Override
    public void buy() {
        Log.i("Cgz", "我要买东西");
    }
}
