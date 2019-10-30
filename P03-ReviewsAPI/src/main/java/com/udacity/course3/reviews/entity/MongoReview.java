package com.udacity.course3.reviews.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document(collection = "reviews")
public class MongoReview {
    @Id
    private Integer reviewId;
    private String reviewContent;
    private List<MongoComment> commentDocuments = new ArrayList<>();

    public Integer getReviewId() {
        return reviewId;
    }

    public void setReviewId(Integer reviewId) {
        this.reviewId = reviewId;
    }

    public String getReviewContent() {
        return reviewContent;
    }

    public void setReviewContent(String reviewContent) {
        this.reviewContent = reviewContent;
    }

    public List<MongoComment> getComments() {
        return commentDocuments;
    }

    public void add(MongoComment comment) {
        this.commentDocuments.add(comment);
    }
}
