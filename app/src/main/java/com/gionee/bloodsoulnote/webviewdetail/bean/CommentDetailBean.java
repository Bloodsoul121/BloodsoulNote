package com.gionee.bloodsoulnote.webviewdetail.bean;

import java.util.List;

public class CommentDetailBean {

    private String name;

    private String id;

    private String comment;

    private String groupId;

    private List<CommentBean> replys;

    public List<CommentBean> getReplys() {
        return replys;
    }

    public void setReplys(List<CommentBean> replys) {
        this.replys = replys;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
