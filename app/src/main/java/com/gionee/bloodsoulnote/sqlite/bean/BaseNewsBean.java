package com.gionee.bloodsoulnote.sqlite.bean;

import java.util.List;

/**
 * 该类只处理网络字段
 */
public class BaseNewsBean {

    public static final int ITEM_TYPE_ONLY_TEXT = 1;
    public static final int ITEM_TYPE_TITLE_BIG_IMAGE = 2;
    public static final int ITEM_TYPE_TITLE_DOUBLE_IMAGE = 3;
    public static final int ITEM_TYPE_TITLE_THREE_IMAGE = 4;
    public static final int ITEM_TYPE_ONLY_IMAGE = 5;
    public static final int ITEM_TYPE_TEXT_IMAGE = 6;
    public static final int ITEM_TYPE_NO_IMAGE = 7;
    public static final int ITEM_TYPE_GROUP = 8;
    public static final int ITEM_TYPE_VIDEO = 9;
    public static final int ITEM_TYPE_GROUP_HEAD = 10;

    public static final int BANNER_ADS = 0;
    public static final int THIRD_ADS = 1;
    public static final int NEWS = 2;

    public static final int PARTNER_QQ = 1;
    public static final int PARTNER_TOUTIAO = 2;
    public static final int PARTNER_UC = 3;
    public static final int PARTNER_XIAOZHI = 4;

    protected String mTabId; // selfpin 公司后台对应的频道
    protected String mOtherTabId; // otherpin 第三方的频道
    protected int mPartnerId; // partner 对应的第三方
    protected String mRefreshType;

    protected String mParts;
    protected String mTitle;
    protected String mUrl;
    protected long mStampTime;
    protected String mFormatTime;
    protected long mOtherId;
    protected String mItemForm;
    protected String mOrgUrl;
    protected int mPV;
    protected String mLabel;
    protected int mCommentNum;
    protected int mAdType;
    protected String mSourceType;
    protected int mStyleType;
    protected int mOtherAdId;
    protected int mWidth;
    protected int mHeight;
    protected String mOtherShow;
    protected String mOtherClick;
    protected String mSelfShow;
    protected String mSelfClick;

    protected List<String> mVideo;
    protected long mPlayCounts;
    protected long mRunTimes;
    protected List<String> mImages;

    // ad
    protected int mPosition;
    protected String mClickUrl;

    public String getRefreshType() {
        return mRefreshType;
    }

    public void setRefreshType(String refreshType) {
        mRefreshType = refreshType;
    }

    public int getPartnerId() {
        return mPartnerId;
    }

    public void setPartnerId(int partnerId) {
        mPartnerId = partnerId;
    }

    public String getOtherTabId() {
        return mOtherTabId;
    }

    public void setOtherTabId(String otherTabId) {
        mOtherTabId = otherTabId;
    }

    public String getSourceType() {
        return mSourceType;
    }

    public void setSourceType(String type) {
        mSourceType = type;
    }

    public String getTabId() {
        return mTabId;
    }

    public void setTabId(String tabId) {
        mTabId = tabId;
    }

    public String getClickUrl() {
        return mClickUrl;
    }

    public void setClickUrl(String clickUrl) {
        mClickUrl = clickUrl;
    }

    public String getParts() {
        return mParts;
    }

    public void setParts(String parts) {
        mParts = parts;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public String getUrl() {
        return mUrl;
    }

    public void setUrl(String url) {
        mUrl = url;
    }

    public long getStampTime() {
        return mStampTime;
    }

    public void setStampTime(long stampTime) {
        mStampTime = stampTime;
    }

    public String getFormatTime() {
        return mFormatTime;
    }

    public void setFormatTime(String formatTime) {
        mFormatTime = formatTime;
    }

    public long getOtherId() {
        return mOtherId;
    }

    public void setOtherId(long otherId) {
        mOtherId = otherId;
    }

    public String getItemForm() {
        return mItemForm;
    }

    public void setItemForm(String itemForm) {
        mItemForm = itemForm;
    }

    public String getOrgUrl() {
        return mOrgUrl;
    }

    public void setOrgUrl(String orgUrl) {
        mOrgUrl = orgUrl;
    }

    public int getPV() {
        return mPV;
    }

    public void setPV(int PV) {
        mPV = PV;
    }

    public String getLabel() {
        return mLabel;
    }

    public void setLabel(String label) {
        mLabel = label;
    }

    public int getCommentNum() {
        return mCommentNum;
    }

    public void setCommentNum(int commentNum) {
        mCommentNum = commentNum;
    }

    public int getAdType() {
        return mAdType;
    }

    public void setAdType(int adType) {
        mAdType = adType;
    }

    public int getStyleType() {
        return mStyleType;
    }

    public void setStyleType(int styleType) {
        mStyleType = styleType;
    }

    public int getOtherAdId() {
        return mOtherAdId;
    }

    public void setOtherAdId(int otherAdId) {
        mOtherAdId = otherAdId;
    }

    public int getWidth() {
        return mWidth;
    }

    public void setWidth(int width) {
        mWidth = width;
    }

    public int getHeight() {
        return mHeight;
    }

    public void setHeight(int height) {
        mHeight = height;
    }

    public String getOtherShow() {
        return mOtherShow;
    }

    public void setOtherShow(String otherShow) {
        mOtherShow = otherShow;
    }

    public String getOtherClick() {
        return mOtherClick;
    }

    public void setOtherClick(String otherClick) {
        mOtherClick = otherClick;
    }

    public String getSelfShow() {
        return mSelfShow;
    }

    public void setSelfShow(String selfShow) {
        mSelfShow = selfShow;
    }

    public String getSelfClick() {
        return mSelfClick;
    }

    public void setSelfClick(String selfClick) {
        mSelfClick = selfClick;
    }

    public long getPlayCounts() {
        return mPlayCounts;
    }

    public void setPlayCounts(long playCounts) {
        mPlayCounts = playCounts;
    }

    public long getPlayTimes() {
        return mRunTimes;
    }

    public void setPlayTimes(long playTimes) {
        mRunTimes = playTimes;
    }

    public List<String> getImages() {
        return mImages;
    }

    public void setImages(List<String> images) {
        mImages = images;
    }

    public int getPosition() {
        return mPosition;
    }

    public void setPosition(int position) {
        mPosition = position;
    }

    @Override
    public String toString() {
        return "BaseNewsBean{" +
                "mTabId='" + mTabId + '\'' +
                ", mOtherTabId='" + mOtherTabId + '\'' +
                ", mPartnerId=" + mPartnerId +
                ", mRefreshType='" + mRefreshType + '\'' +
                ", mParts='" + mParts + '\'' +
                ", mTitle='" + mTitle + '\'' +
                ", mUrl='" + mUrl + '\'' +
                ", mStampTime=" + mStampTime +
                ", mFormatTime='" + mFormatTime + '\'' +
                ", mOtherId=" + mOtherId +
                ", mItemForm='" + mItemForm + '\'' +
                ", mOrgUrl='" + mOrgUrl + '\'' +
                ", mPV=" + mPV +
                ", mLabel='" + mLabel + '\'' +
                ", mCommentNum=" + mCommentNum +
                ", mAdType=" + mAdType +
                ", mSourceType='" + mSourceType + '\'' +
                ", mStyleType=" + mStyleType +
                ", mOtherAdId=" + mOtherAdId +
                ", mWidth=" + mWidth +
                ", mHeight=" + mHeight +
                ", mOtherShow='" + mOtherShow + '\'' +
                ", mOtherClick='" + mOtherClick + '\'' +
                ", mSelfShow='" + mSelfShow + '\'' +
                ", mSelfClick='" + mSelfClick + '\'' +
                ", mVideo=" + mVideo +
                ", mPlayCounts=" + mPlayCounts +
                ", mRunTimes=" + mRunTimes +
                ", mImages=" + mImages +
                ", mPosition=" + mPosition +
                ", mClickUrl='" + mClickUrl + '\'' +
                '}';
    }
}
