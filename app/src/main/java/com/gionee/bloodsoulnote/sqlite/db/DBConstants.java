package com.gionee.bloodsoulnote.sqlite.db;

public class DBConstants {

    public static final String TABLENAME_NEWS_STREAM = "news";
    public static final String TABLENAME_NEWS_CHANNEL = "channels";

    // public
    public static final int ZERO = 0;
    public static final String COLUMN_ID = "_id";

    // channel
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_STATUS = "status"; // 1 opened

    // news
    public static final String COLUMN_TITLE = "title";
    public static final String COLUMN_ELAPSE_TIME = "elapse_time";
    public static final String COLUMN_POST_TIME = "post_time";
    public static final String COLUMN_URL = "url";
    public static final String COLUMN_FROM = "supplier";
    public static final String COLUMN_IMAGES = "images";
    public static final String COLUMN_TYPE = "type";
    public static final String COLUMN_SOURCE_TYPE = "source_type";
    public static final String COLUMN_DOC_ID = "doc_id";
    public static final String COLUMN_ORG_URL = "org_url";
    public static final String COLUMN_TAB_ID = "tab_id";
    public static final String COLUMN_TAG_ID = "tag_id";
    public static final String COLUMN_PV = "pv";
    public static final String COLUMN_COMMENT_NUM = "comment_num";
    public static final String COLUMN_LABEL = "label";
    public static final String COLUMN_EXPOSURE_URL = "exposure_url";
    public static final String COLUMN_CLICK_URL = "click_url";
    public static final String COLUMN_WIDTH = "width";
    public static final String COLUMN_HEIGHT = "height";
    public static final String COLUMN_PLAY_COUNT = "play_count";
    public static final String COLUMN_PLAY_TIME = "runtime";
    public static final String COLUMN_TAB_TYPE = "tab_type";
    public static final String COLUMN_UPDATE_TIME = "update_time";
    public static final String COLUMN_READED_STATE = "readed_state";
    public static final String COLUMN_PARTS = "parts";
    public static final String COLUMN_STAMP_TIME = "stamp_time";
    public static final String COLUMN_FORMA_TIME = "format_time";
    public static final String COLUMN_OTHER_ID = "other_id";
    public static final String COLUMN_ITEM_FORM = "item_form";
    public static final String COLUMN_AD_TYPE = "ad_type";
    public static final String COLUMN_STYLE_TYPE = "style_type";
    public static final String COLUMN_VIDEO = "video";
    public static final String COLUMN_OTHER_AD_ID = "other_ad_id";
    public static final String COLUMN_OTHER_SHOW = "other_show";
    public static final String COLUMN_OTHER_CLICK = "other_click";
    public static final String COLUMN_SELF_SHOW = "self_show";
    public static final String COLUMN_SELF_CLICK = "self_click";
    public static final String COLUMN_POSITION = "position";
    public static final String COLUMN_HIDE_LINE = "hide_line";
    public static final String COLUMN_OTHER_TAB_ID = "other_tab_id";
    public static final String COLUMN_PARTNER = "partner";
    public static final String COLUMN_REFRESH_TYPE = "refresh_type";

    public static final int STATE_UNREADED = 0;
    public static final int STATE_HAS_READED = 1;


}
