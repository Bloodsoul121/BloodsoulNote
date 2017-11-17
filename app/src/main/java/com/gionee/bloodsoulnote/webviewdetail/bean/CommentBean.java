package com.gionee.bloodsoulnote.webviewdetail.bean;

import java.util.List;

public class CommentBean {

    private String name;

    // 唯一id
    private String id;
    // 评论内容
    private String comment;
    // 评论组 id , 比如 最新组, 最热组
    private String groupId;
    // 该评论下的, 其余回复
    private List<CommentDetailBean> details;

    public List<CommentDetailBean> getDetails() {
        return details;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDetails(List<CommentDetailBean> details) {
        this.details = details;
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
