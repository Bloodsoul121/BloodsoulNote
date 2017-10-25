package com.gionee.bloodsoulnote.ipc.contentprovider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import static com.gionee.bloodsoulnote.ipc.contentprovider.DBOpenHelper.GAME_TABLE_NAME;

/**
 * Created by cgz on 17-10-25.
 */

public class GameProvider extends ContentProvider {

    private static final String AUTHORITIES = "com.bloodsoul.game";

    public static final Uri GAME_CONTENT_URI = Uri.parse("content://" + AUTHORITIES + "/" + GAME_TABLE_NAME);

    private static final UriMatcher mUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

    private SQLiteDatabase mDb;

    private Context mContext;

    static {
        mUriMatcher.addURI(AUTHORITIES, GAME_TABLE_NAME, 0);
    }

    @Override
    public boolean onCreate() {
        mContext = getContext();
        mDb = new DBOpenHelper(getContext()).getWritableDatabase();
        new Thread() {
            @Override
            public void run() {
                mDb.execSQL("delete from " + GAME_TABLE_NAME);
                mDb.execSQL("insert into game values(1,'九阴真经ol','最好玩的武侠网游');");
            }
        }.start();
        return false;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {

        int code = mUriMatcher.match(uri);

        Cursor cursor = mDb.query(GAME_TABLE_NAME, projection, selection, selectionArgs, null, sortOrder, null);
        return cursor;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        mDb.insert(GAME_TABLE_NAME, null, values);
        mContext.getContentResolver().notifyChange(uri, null);
        return null;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }
}
