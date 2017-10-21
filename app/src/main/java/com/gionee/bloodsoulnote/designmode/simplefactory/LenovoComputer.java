package com.gionee.bloodsoulnote.designmode.simplefactory;

import android.util.Log;

/**
 * Created by cgz on 17-10-21.
 */

public class LenovoComputer extends IComputer {
    @Override
    public void start() {
        Log.i("simplefactory", "LenovoComputer");
    }
}
