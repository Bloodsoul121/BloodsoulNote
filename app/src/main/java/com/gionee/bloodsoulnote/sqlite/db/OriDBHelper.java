package com.gionee.bloodsoulnote.sqlite.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class OriDBHelper extends SQLiteOpenHelper {

    private static final String TAG = "OriDBHelper";

    private static final String DB_NAME = "orisqlite.db";

    private static final int VERSION = 1;

    private static final String GAME_TABLE_NAME = "orisqlite";

    private static final String CREATE_GAME_TABLE = "create table if not exists " + GAME_TABLE_NAME +"(_id integer primary key," + "name TEXT, "+"describe TEXT)";

    public OriDBHelper(Context context) {
        super(context, DB_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_GAME_TABLE);
        createNewsStreamTable(db);
        createNewsChannelTable(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    private void createNewsChannelTable(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS " + DBConstants.TABLENAME_NEWS_CHANNEL + " ("
                + DBConstants.COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + DBConstants.COLUMN_NAME + " TEXT,"
                + DBConstants.COLUMN_TAB_ID + " TEXT,"
                + DBConstants.COLUMN_STATUS + " INTEGER,"
                + DBConstants.COLUMN_POSITION + " INTEGER"
                + ");"
        );
        Log.d(TAG, "createTable news channels succeed");
    }

    private void createNewsStreamTable(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS " + DBConstants.TABLENAME_NEWS_STREAM + " ("
                + DBConstants.COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + DBConstants.COLUMN_CLICK_URL + " TEXT,"
                + DBConstants.COLUMN_COMMENT_NUM + " INTEGER,"
                + DBConstants.COLUMN_DOC_ID + " INTEGER,"
                + DBConstants.COLUMN_ELAPSE_TIME + " TEXT,"
                + DBConstants.COLUMN_EXPOSURE_URL + " TEXT,"
                + DBConstants.COLUMN_FROM + " TEXT,"
                + DBConstants.COLUMN_IMAGES + " TEXT,"
                + DBConstants.COLUMN_LABEL + " TEXT,"
                + DBConstants.COLUMN_ORG_URL + " TEXT,"
                + DBConstants.COLUMN_POST_TIME + " INTEGER,"
                + DBConstants.COLUMN_PV + " INTEGER,"
                + DBConstants.COLUMN_TAB_ID + " TEXT,"
                + DBConstants.COLUMN_TAG_ID + " TEXT,"
                + DBConstants.COLUMN_TITLE + " TEXT,"
                + DBConstants.COLUMN_TYPE + " INTEGER,"
                + DBConstants.COLUMN_URL + " TEXT,"
                + DBConstants.COLUMN_WIDTH + " INTEGER,"
                + DBConstants.COLUMN_HEIGHT + " INTEGER,"
                + DBConstants.COLUMN_PLAY_COUNT + " INTEGER,"
                + DBConstants.COLUMN_PLAY_TIME + " INTEGER,"
                + DBConstants.COLUMN_TAB_TYPE + " TEXT,"
                + DBConstants.COLUMN_UPDATE_TIME + " INTEGER,"
                + DBConstants.COLUMN_READED_STATE + " INTEGER,"
                + DBConstants.COLUMN_PARTS + " TEXT,"
                + DBConstants.COLUMN_STAMP_TIME + " INTEGER,"
                + DBConstants.COLUMN_FORMA_TIME + " TEXT,"
                + DBConstants.COLUMN_OTHER_ID + " INTEGER,"
                + DBConstants.COLUMN_ITEM_FORM + " TEXT,"
                + DBConstants.COLUMN_AD_TYPE + " INTEGER,"
                + DBConstants.COLUMN_STYLE_TYPE + " INTEGER,"
                + DBConstants.COLUMN_VIDEO + " TEXT,"
                + DBConstants.COLUMN_OTHER_AD_ID + " INTEGER,"
                + DBConstants.COLUMN_OTHER_SHOW + " TEXT,"
                + DBConstants.COLUMN_OTHER_CLICK + " TEXT,"
                + DBConstants.COLUMN_SELF_SHOW + " TEXT,"
                + DBConstants.COLUMN_SELF_CLICK + " TEXT,"
                + DBConstants.COLUMN_POSITION + " INTEGER,"
                + DBConstants.COLUMN_HIDE_LINE + " INTEGER,"
                + DBConstants.COLUMN_SOURCE_TYPE + " TEXT,"
                + DBConstants.COLUMN_OTHER_TAB_ID + " TEXT,"
                + DBConstants.COLUMN_REFRESH_TYPE + " TEXT,"
                + DBConstants.COLUMN_PARTNER + " INTEGER"
                + ");"
        );
        Log.d(TAG, "createTable news succeed");
    }
}
