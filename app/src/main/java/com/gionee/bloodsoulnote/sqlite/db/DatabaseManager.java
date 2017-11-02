package com.gionee.bloodsoulnote.sqlite.db;

import android.database.sqlite.SQLiteDatabase;

import com.gionee.bloodsoulnote.NoteApplication;

import java.util.concurrent.atomic.AtomicInteger;

public class DatabaseManager {

    private AtomicInteger openCounter;

    private OriDBHelper dbHelper;

    private SQLiteDatabase database;

    private DatabaseManager() {
        this.openCounter = new AtomicInteger();
        this.dbHelper = new OriDBHelper(NoteApplication.getInstance().getApplicationContext());
    }

    public static DatabaseManager getInstance() {
        return DatabaseManagerHolder.sDatabaseManager;
    }

    private static class DatabaseManagerHolder{

        private static DatabaseManager sDatabaseManager = new DatabaseManager();

        public DatabaseManagerHolder() {}
    }

    public synchronized SQLiteDatabase openDatabase() {
        if(this.openCounter.incrementAndGet() == 1) {
            this.database = this.dbHelper.getWritableDatabase();
        }
        return this.database;
    }

    public synchronized void closeDatabase() {
        if(this.openCounter.decrementAndGet() == 0 && this.database != null) {
            this.database.close();
        }
    }

}
