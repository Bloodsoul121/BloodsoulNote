package com.gionee.bloodsoulnote.sqlite.db;

import android.content.ContentValues;
import android.database.Cursor;
import android.text.TextUtils;

import com.gionee.bloodsoulnote.sqlite.bean.NewsChannelBean;

import static com.gionee.bloodsoulnote.sqlite.db.DBConstants.COLUMN_ID;
import static com.gionee.bloodsoulnote.sqlite.db.DBConstants.COLUMN_NAME;
import static com.gionee.bloodsoulnote.sqlite.db.DBConstants.COLUMN_POSITION;
import static com.gionee.bloodsoulnote.sqlite.db.DBConstants.COLUMN_STATUS;
import static com.gionee.bloodsoulnote.sqlite.db.DBConstants.COLUMN_TAB_ID;
import static com.gionee.bloodsoulnote.sqlite.db.DBConstants.TABLENAME_NEWS_CHANNEL;

public class ChannelDBDao extends BaseDBDao<NewsChannelBean> {

    @Override
    public String getTableName() {
        return TABLENAME_NEWS_CHANNEL;
    }

    @Override
    public ContentValues getValues(NewsChannelBean item) {
        if (item == null) {
            return null;
        }

        ContentValues values = new ContentValues();
        if (item.getID() > DBConstants.ZERO) {
            values.put(COLUMN_ID, item.getID());
        }

        if (!TextUtils.isEmpty(item.getName())) {
            values.put(COLUMN_NAME, item.getName());
        }

        if (!TextUtils.isEmpty(item.getTabId())) {
            values.put(COLUMN_TAB_ID, item.getTabId());
        }

        if (item.getStatus() == NewsChannelBean.NO_STATUS || item.getStatus() == NewsChannelBean.NEW_ONE) {
            values.put(COLUMN_POSITION, item.getPosition());
            values.put(COLUMN_STATUS, item.isOpened() ? 1 : 2);
        }

        return values;
    }

    @Override
    public NewsChannelBean createBean(Cursor cursor) {
        NewsChannelBean item = new NewsChannelBean();
        item.setID(cursor.getInt(cursor.getColumnIndex(COLUMN_ID)));
        item.setName(cursor.getString(cursor.getColumnIndex(COLUMN_NAME)));
        int status = cursor.getInt(cursor.getColumnIndex(COLUMN_STATUS));
        item.setIsOpened(status == 1);
        item.setTabId(cursor.getString(cursor.getColumnIndex(COLUMN_TAB_ID)));
        item.setPosition(cursor.getInt(cursor.getColumnIndex(COLUMN_POSITION)));
        return item;
    }

}
