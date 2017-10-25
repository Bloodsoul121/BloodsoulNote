package com.gionee.bloodsoulnote.ipc.contentprovider;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by cgz on 17-10-25.
 */

public class DBOpenHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "game.db";

    private static final int VERSION = 1;

    public static final String GAME_TABLE_NAME = "game";

    private static final String CREATE_GAME_TABLE = "create table if not exists " + GAME_TABLE_NAME +"(_id integer primary key," + "name TEXT, "+"describe TEXT)";

    public DBOpenHelper(Context context) {
        super(context, DB_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_GAME_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
