package com.udacity.course3.reviews.repository;

import com.udacity.course3.reviews.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Integer> {

    @Query("SELECT c FROM Comment c WHERE c.reviewId= :reviewId")
    List<?> findAllByReviewId(Integer reviewId);
}
