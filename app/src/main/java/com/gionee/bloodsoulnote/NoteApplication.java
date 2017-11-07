package com.gionee.bloodsoulnote;

import android.app.Application;
import android.content.Context;

import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;

public class NoteApplication extends Application {

    private RefWatcher refWatcher;

    private static NoteApplication sInstance;

    public static NoteApplication getInstance() {
        return sInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        sInstance = this;
        refWatcher= setupLeakCanary();
    }

    private RefWatcher setupLeakCanary() {
        if (LeakCanary.isInAnalyzerProcess(this)) {
            return RefWatcher.DISABLED;
        }
        return LeakCanary.install(this);
    }

    public static RefWatcher getRefWatcher(Context context) {
        NoteApplication leakApplication = (NoteApplication) context.getApplicationContext();
        return leakApplication.refWatcher;
    }

}
