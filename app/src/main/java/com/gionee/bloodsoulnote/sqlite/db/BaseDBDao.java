package com.gionee.bloodsoulnote.sqlite.db;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseDBDao<T> {

    private static final String TAG = "BaseDBDao";

    public void insert(T t) {
        synchronized (BaseDBDao.class) {
            try {
                SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();
                db.insert(getTableName(), null, getValues(t));
            } catch (Exception e) {
                Log.d(TAG, "insert e --> " + e);
            } finally {
                DatabaseManager.getInstance().closeDatabase();
            }
        }
    }

    public void insert(List<T> list) {
        synchronized (BaseDBDao.class) {
            SQLiteDatabase db = null;
            try {
                db = DatabaseManager.getInstance().openDatabase();
                db.beginTransaction();
                for (T t : list) {
                    db.insert(getTableName(), null, getValues(t));
                }
                db.setTransactionSuccessful();
            } catch (Exception e) {
                Log.d(TAG, "insert e --> " + e);
            } finally {
                if (db != null) {
                    if(db.inTransaction()) {
                        db.endTransaction();
                    }
                }
                DatabaseManager.getInstance().closeDatabase();
            }
        }
    }

    public List<T> query(String[] columns, String selection, String[] selectionArgs, String sortOrder, String limit) {
        synchronized (BaseDBDao.class) {
            List<T> list = new ArrayList<>();
            Cursor cursor = null;
            SQLiteDatabase db = null;
            try {
                db = DatabaseManager.getInstance().openDatabase();
                db.beginTransaction();
                cursor = db.query(getTableName(), columns, selection, selectionArgs, null, null, sortOrder, limit);

                if (cursor == null) {
                    return list;
                }

                while (cursor.moveToNext()) {
                    T item = createBean(cursor);
                    list.add(item);
                }
                db.setTransactionSuccessful();
            } catch (Exception e) {
                Log.d(TAG, "query e --> " + e);
            } finally {
                if (db != null) {
                    if(db.inTransaction()) {
                        db.endTransaction();
                    }
                }
                closeCursor(cursor);
                DatabaseManager.getInstance().closeDatabase();
            }
            return list;
        }
    }

    public void delete(String whereClause, String[] whereArgs) {
        synchronized (BaseDBDao.class) {
            try {
                SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();
                db.delete(getTableName(), whereClause, whereArgs);
            } catch (Exception e) {
                Log.d(TAG, "delete e --> " + e);
            } finally {
                DatabaseManager.getInstance().closeDatabase();
            }
        }
    }

    public void update(T t, String whereClause, String[] whereArgs) {
        synchronized (BaseDBDao.class) {
            try {
                SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();
                db.update(getTableName(), getValues(t), whereClause, whereArgs);
            } catch (Exception e) {
                Log.d(TAG, "update e --> " + e);
            } finally {
                DatabaseManager.getInstance().closeDatabase();
            }
        }
    }

    public abstract String getTableName();

    public abstract ContentValues getValues(T item);

    public abstract T createBean(Cursor cursor);

    protected void closeCursor(Cursor cursor) {
        if (cursor != null) {
            cursor.close();
        }
    }

}
