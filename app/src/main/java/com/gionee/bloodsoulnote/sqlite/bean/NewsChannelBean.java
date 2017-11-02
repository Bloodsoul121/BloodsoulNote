package com.gionee.bloodsoulnote.sqlite.bean;

import java.io.Serializable;

public class NewsChannelBean implements Serializable {

    public static final int NO_STATUS = -1;
    public static final int ORIGINAL_ONE = 0;
    public static final int NEW_ONE = 1;
    public static final int NEED_UPDATE = 2;
    public static final int NEED_DELETE = 3;

    private int mID;
    private String mName;
    private String mTabId;
    private boolean mIsOpened;
    private int mPosition;
    private boolean mIsSelected;

    private int mStatus = NO_STATUS;

    public NewsChannelBean() {
    }

    public int getID() {
        return mID;
    }

    public void setID(int mID) {
        this.mID = mID;
    }

    public String getName() {
        return mName;
    }

    public void setName(String mName) {
        this.mName = mName;
    }

    public String getTabId() {
        return mTabId;
    }

    public void setTabId(String mTabId) {
        this.mTabId = mTabId;
    }

    public boolean isOpened() {
        return mIsOpened;
    }

    public void setIsOpened(boolean mIsOpened) {
        this.mIsOpened = mIsOpened;
    }

    public int getPosition() {
        return mPosition;
    }

    public void setPosition(int mPosition) {
        this.mPosition = mPosition;
    }

    public void setIsSelected(boolean mIsSelected) {
        this.mIsSelected = mIsSelected;
    }

    public boolean getIsSelected() {
        return mIsSelected;
    }

    public int getStatus() {
        return mStatus;
    }

    public void setStatus(int mStatus) {
        this.mStatus = mStatus;
    }

    @Override
    public String toString() {
        return "NewsChannelBean{" +
                "mID=" + mID +
                ", mName='" + mName + '\'' +
                ", mTabId='" + mTabId + '\'' +
                ", mIsOpened=" + mIsOpened +
                ", mPosition=" + mPosition +
                ", mIsSelected=" + mIsSelected +
                ", mStatus=" + mStatus +
                '}';
    }
}
