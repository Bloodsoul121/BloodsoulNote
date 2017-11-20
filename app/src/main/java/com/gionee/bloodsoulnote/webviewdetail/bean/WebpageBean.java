package com.gionee.bloodsoulnote.webviewdetail.bean;

public class WebpageBean {

    private String commentNum;

    private boolean isCollected;

    public boolean isCollected() {
        return isCollected;
    }

    public void setCollected(boolean collected) {
        isCollected = collected;
    }

    public String getCommentNum() {
        return commentNum;
    }

    public void setCommentNum(String commentNum) {
        this.commentNum = commentNum;
    }
}
