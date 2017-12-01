package com.gionee.bloodsoulnote;

import android.app.Application;

import com.orhanobut.logger.Logger;

public class NoteApplication extends Application {

    private static NoteApplication sInstance;

    public static NoteApplication getInstance() {
        return sInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        sInstance = this;
//        refWatcher= setupLeakCanary();

        Logger.init("logger");
    }

//    private RefWatcher setupLeakCanary() {
//        if (LeakCanary.isInAnalyzerProcess(this)) {
//            return RefWatcher.DISABLED;
//        }
//        return LeakCanary.install(this);
//    }
//
//    public static RefWatcher getRefWatcher(Context context) {
//        NoteApplication leakApplication = (NoteApplication) context.getApplicationContext();
//        return leakApplication.refWatcher;
//    }

}
