package com.gionee.bloodsoulnote.sqlite.db;

import android.content.ContentValues;
import android.database.Cursor;
import android.text.TextUtils;

import com.gionee.bloodsoulnote.sqlite.bean.NewsBean;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.gionee.bloodsoulnote.sqlite.db.DBConstants.COLUMN_AD_TYPE;
import static com.gionee.bloodsoulnote.sqlite.db.DBConstants.COLUMN_CLICK_URL;
import static com.gionee.bloodsoulnote.sqlite.db.DBConstants.COLUMN_COMMENT_NUM;
import static com.gionee.bloodsoulnote.sqlite.db.DBConstants.COLUMN_FORMA_TIME;
import static com.gionee.bloodsoulnote.sqlite.db.DBConstants.COLUMN_HEIGHT;
import static com.gionee.bloodsoulnote.sqlite.db.DBConstants.COLUMN_ID;
import static com.gionee.bloodsoulnote.sqlite.db.DBConstants.COLUMN_IMAGES;
import static com.gionee.bloodsoulnote.sqlite.db.DBConstants.COLUMN_ITEM_FORM;
import static com.gionee.bloodsoulnote.sqlite.db.DBConstants.COLUMN_LABEL;
import static com.gionee.bloodsoulnote.sqlite.db.DBConstants.COLUMN_ORG_URL;
import static com.gionee.bloodsoulnote.sqlite.db.DBConstants.COLUMN_OTHER_AD_ID;
import static com.gionee.bloodsoulnote.sqlite.db.DBConstants.COLUMN_OTHER_CLICK;
import static com.gionee.bloodsoulnote.sqlite.db.DBConstants.COLUMN_OTHER_ID;
import static com.gionee.bloodsoulnote.sqlite.db.DBConstants.COLUMN_OTHER_SHOW;
import static com.gionee.bloodsoulnote.sqlite.db.DBConstants.COLUMN_OTHER_TAB_ID;
import static com.gionee.bloodsoulnote.sqlite.db.DBConstants.COLUMN_PARTNER;
import static com.gionee.bloodsoulnote.sqlite.db.DBConstants.COLUMN_PARTS;
import static com.gionee.bloodsoulnote.sqlite.db.DBConstants.COLUMN_PLAY_COUNT;
import static com.gionee.bloodsoulnote.sqlite.db.DBConstants.COLUMN_PLAY_TIME;
import static com.gionee.bloodsoulnote.sqlite.db.DBConstants.COLUMN_POSITION;
import static com.gionee.bloodsoulnote.sqlite.db.DBConstants.COLUMN_PV;
import static com.gionee.bloodsoulnote.sqlite.db.DBConstants.COLUMN_READED_STATE;
import static com.gionee.bloodsoulnote.sqlite.db.DBConstants.COLUMN_REFRESH_TYPE;
import static com.gionee.bloodsoulnote.sqlite.db.DBConstants.COLUMN_SELF_CLICK;
import static com.gionee.bloodsoulnote.sqlite.db.DBConstants.COLUMN_SELF_SHOW;
import static com.gionee.bloodsoulnote.sqlite.db.DBConstants.COLUMN_SOURCE_TYPE;
import static com.gionee.bloodsoulnote.sqlite.db.DBConstants.COLUMN_STAMP_TIME;
import static com.gionee.bloodsoulnote.sqlite.db.DBConstants.COLUMN_STYLE_TYPE;
import static com.gionee.bloodsoulnote.sqlite.db.DBConstants.COLUMN_TAB_ID;
import static com.gionee.bloodsoulnote.sqlite.db.DBConstants.COLUMN_TITLE;
import static com.gionee.bloodsoulnote.sqlite.db.DBConstants.COLUMN_UPDATE_TIME;
import static com.gionee.bloodsoulnote.sqlite.db.DBConstants.COLUMN_URL;
import static com.gionee.bloodsoulnote.sqlite.db.DBConstants.COLUMN_WIDTH;
import static com.gionee.bloodsoulnote.sqlite.db.DBConstants.STATE_HAS_READED;
import static com.gionee.bloodsoulnote.sqlite.db.DBConstants.STATE_UNREADED;
import static com.gionee.bloodsoulnote.sqlite.db.DBConstants.TABLENAME_NEWS_STREAM;

public class NewsDBDao extends BaseDBDao<NewsBean> {

    private static final String IMAGE_SPLIT = ";";

    @Override
    public String getTableName() {
        return TABLENAME_NEWS_STREAM;
    }

    @Override
    public ContentValues getValues(NewsBean item) {
        if (item == null) {
            return null;
        }

        ContentValues values = new ContentValues();
        int id = item.getID();
        if (id > DBConstants.ZERO) {
            values.put(COLUMN_ID, id);
        }

        if (!TextUtils.isEmpty(item.getTitle())) {
            values.put(COLUMN_TITLE, item.getTitle());
        }

        if (!TextUtils.isEmpty(item.getUrl())) {
            values.put(COLUMN_URL, item.getUrl());
        }

        if (item.getPlayTimes() > 0) {
            values.put(COLUMN_PLAY_TIME, item.getPlayTimes());
        }

        if (item.getPlayCounts() > 0) {
            values.put(COLUMN_PLAY_COUNT, item.getPlayCounts());
        }

        if (item.getImages() != null && item.getImages().size() > 0) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < item.getImages().size(); i++) {
                sb.append(item.getImages().get(i));
                sb.append(";");
            }
            values.put(COLUMN_IMAGES, sb.toString());
        }

        if (!TextUtils.isEmpty(item.getOrgUrl())) {
            values.put(COLUMN_ORG_URL, item.getOrgUrl());
        }

        if (!TextUtils.isEmpty(item.getTabId())) {
            values.put(COLUMN_TAB_ID, item.getTabId());
        }

        if (item.getPV() > 0) {
            values.put(COLUMN_PV, item.getPV());
        }

        if (item.getCommentNum() > 0) {
            values.put(COLUMN_COMMENT_NUM, item.getCommentNum());
        }

        if (!TextUtils.isEmpty(item.getLabel())) {
            values.put(COLUMN_LABEL, item.getLabel());
        }

        if (!TextUtils.isEmpty(item.getClickUrl())) {
            values.put(COLUMN_CLICK_URL, item.getClickUrl());
        }

        if (item.getWidth() > 0) {
            values.put(COLUMN_WIDTH, item.getWidth());
        }

        if (item.getHeight() > 0) {
            values.put(COLUMN_HEIGHT, item.getHeight());
        }

        if (item.getUpdateTime() > 0) {
            values.put(COLUMN_UPDATE_TIME, item.getUpdateTime());
        }

        // 2017.2.15
        if (!TextUtils.isEmpty(item.getParts())) {
            values.put(COLUMN_PARTS, item.getParts());
        }

        if (item.getStampTime() > 0) {
            values.put(COLUMN_STAMP_TIME, item.getStampTime());
        }

        if (!TextUtils.isEmpty(item.getFormatTime())) {
            values.put(COLUMN_FORMA_TIME, item.getFormatTime());
        }

        if (item.getOtherId() > 0) {
            values.put(COLUMN_OTHER_ID, item.getOtherId());
        }

        if (!TextUtils.isEmpty(item.getItemForm())) {
            values.put(COLUMN_ITEM_FORM, item.getItemForm());
        }

        if (item.getAdType() > 0) {
            values.put(COLUMN_AD_TYPE, item.getAdType());
        }

        if (item.getStyleType() > 0) {
            values.put(COLUMN_STYLE_TYPE, item.getStyleType());
        }

        if (item.getOtherAdId() > 0) {
            values.put(COLUMN_OTHER_AD_ID, item.getOtherAdId());
        }

        if (!TextUtils.isEmpty(item.getOtherShow())) {
            values.put(COLUMN_OTHER_SHOW, item.getOtherShow());
        }

        if (!TextUtils.isEmpty(item.getOtherClick())) {
            values.put(COLUMN_OTHER_CLICK, item.getOtherClick());
        }

        if (!TextUtils.isEmpty(item.getSelfShow())) {
            values.put(COLUMN_SELF_SHOW, item.getSelfShow());
        }

        if (!TextUtils.isEmpty(item.getSelfClick())) {
            values.put(COLUMN_SELF_CLICK, item.getSelfClick());
        }

        if (item.getPosition() > -1) {
            values.put(COLUMN_POSITION, item.getPosition());
        }

        if (!TextUtils.isEmpty(item.getSourceType())) {
            values.put(COLUMN_SOURCE_TYPE, item.getSourceType());
        }

        if (!TextUtils.isEmpty(item.getOtherTabId())) {
            values.put(COLUMN_OTHER_TAB_ID, item.getOtherTabId());
        }

        if (item.getPartnerId() > -1) {
            values.put(COLUMN_PARTNER, item.getPartnerId());
        }

        if (!TextUtils.isEmpty(item.getRefreshType())) {
            values.put(COLUMN_REFRESH_TYPE, item.getRefreshType());
        }

        int state = item.isRead() ? STATE_HAS_READED : STATE_UNREADED;
        values.put(COLUMN_READED_STATE, state);

        return values;
    }

    @Override
    public NewsBean createBean(Cursor cursor) {
        NewsBean item = new NewsBean();

        item.setPlayCounts(cursor.getLong(cursor.getColumnIndex(COLUMN_PLAY_COUNT)));
        item.setPlayTimes(cursor.getLong(cursor.getColumnIndex(COLUMN_PLAY_TIME)));
        item.setUpdateTime(cursor.getLong(cursor.getColumnIndex(COLUMN_UPDATE_TIME)));
        item.setTabId(cursor.getString(cursor.getColumnIndex(COLUMN_TAB_ID)));
        item.setClickUrl(cursor.getString(cursor.getColumnIndex(COLUMN_CLICK_URL)));

        item.setID(cursor.getInt(cursor.getColumnIndex(COLUMN_ID)));
        item.setUrl(cursor.getString(cursor.getColumnIndex(COLUMN_URL)));
        item.setOrgUrl(cursor.getString(cursor.getColumnIndex(COLUMN_ORG_URL)));
        item.setTitle(cursor.getString(cursor.getColumnIndex(COLUMN_TITLE)));
        item.setPV(cursor.getInt(cursor.getColumnIndex(COLUMN_PV)));
        item.setCommentNum(cursor.getInt(cursor.getColumnIndex(COLUMN_COMMENT_NUM)));
        item.setLabel(cursor.getString(cursor.getColumnIndex(COLUMN_LABEL)));
        item.setWidth(cursor.getInt(cursor.getColumnIndex(COLUMN_WIDTH)));
        item.setHeight(cursor.getInt(cursor.getColumnIndex(COLUMN_HEIGHT)));
        item.setImages(getImages(cursor.getString(cursor.getColumnIndex(COLUMN_IMAGES))));
        int state = cursor.getInt(cursor.getColumnIndex(COLUMN_READED_STATE));
        item.setRead((state == STATE_HAS_READED));

        item.setParts(cursor.getString(cursor.getColumnIndex(COLUMN_PARTS)));
        item.setStampTime(cursor.getInt(cursor.getColumnIndex(COLUMN_STAMP_TIME)));
        item.setFormatTime(cursor.getString(cursor.getColumnIndex(COLUMN_FORMA_TIME)));
        item.setOtherId(cursor.getLong(cursor.getColumnIndex(COLUMN_OTHER_ID)));
        item.setItemForm(cursor.getString(cursor.getColumnIndex(COLUMN_ITEM_FORM)));
        item.setAdType(cursor.getInt(cursor.getColumnIndex(COLUMN_AD_TYPE)));
        item.setSourceType(cursor.getString(cursor.getColumnIndex(COLUMN_SOURCE_TYPE)));
        item.setStyleType(cursor.getInt(cursor.getColumnIndex(COLUMN_STYLE_TYPE)));
        item.setOtherAdId(cursor.getInt(cursor.getColumnIndex(COLUMN_OTHER_AD_ID)));
        item.setOtherShow(cursor.getString(cursor.getColumnIndex(COLUMN_OTHER_SHOW)));
        item.setOtherClick(cursor.getString(cursor.getColumnIndex(COLUMN_OTHER_CLICK)));
        item.setSelfShow(cursor.getString(cursor.getColumnIndex(COLUMN_SELF_SHOW)));
        item.setSelfClick(cursor.getString(cursor.getColumnIndex(COLUMN_SELF_CLICK)));
        item.setPosition(cursor.getInt(cursor.getColumnIndex(COLUMN_POSITION)));
        item.setOtherTabId(cursor.getString(cursor.getColumnIndex(COLUMN_OTHER_TAB_ID)));
        item.setPartnerId(cursor.getInt(cursor.getColumnIndex(COLUMN_PARTNER)));
        item.setRefreshType(cursor.getString(cursor.getColumnIndex(COLUMN_REFRESH_TYPE)));
        return item;
    }

    private List<String> getImages(String images) {
        if (images == null) {
            return null;
        }
        List<String> imageList = new ArrayList<>();
        if (images.contains(IMAGE_SPLIT)) {
            String[] tmp = images.split(IMAGE_SPLIT);
            Collections.addAll(imageList, tmp);
        } else {
            imageList.add(images);
        }
        return imageList;
    }
}
