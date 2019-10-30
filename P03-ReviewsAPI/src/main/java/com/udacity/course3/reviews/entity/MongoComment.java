package com.udacity.course3.reviews.entity;

import org.springframework.data.annotation.Id;

public class MongoComment {

    @Id
    private Integer commentId;
    private String content;

    public MongoComment() {
    }

    public MongoComment(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setCommentId(Integer commentId) {
        this.commentId = commentId;
    }

    public Integer getCommentId() {
        return commentId;
    }
}
