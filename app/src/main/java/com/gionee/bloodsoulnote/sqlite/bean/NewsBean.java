package com.gionee.bloodsoulnote.sqlite.bean;

public class NewsBean extends BaseNewsBean {

    protected int mID;
    private long mUpdateTime;
    private boolean mIsRead;

    public NewsBean() {
    }

    public int getID() {
        return mID;
    }

    public void setID(int ID) {
        mID = ID;
    }

    public long getUpdateTime() {
        return mUpdateTime;
    }

    public void setUpdateTime(long updateTime) {
        mUpdateTime = updateTime;
    }

    public boolean isRead() {
        return mIsRead;
    }

    public void setRead(boolean read) {
        mIsRead = read;
    }

    @Override
    public String toString() {
        return "NewsBean{" +
                "mID=" + mID +
                ", mUpdateTime=" + mUpdateTime +
                ", mIsRead=" + mIsRead +
                "} " + super.toString();
    }

}
