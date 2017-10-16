package com.gionee.bloodsoulnote;

import android.app.Application;

public class NoteApplication extends Application {

    private static NoteApplication sInstance;

    public static NoteApplication getInstance() {
        return sInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        sInstance = this;
    }

}
