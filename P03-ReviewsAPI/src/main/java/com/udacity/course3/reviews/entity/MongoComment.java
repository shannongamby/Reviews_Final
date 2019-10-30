package com.udacity.course3.reviews.entity;

public class MongoComment {

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
}
