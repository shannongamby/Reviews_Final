package com.udacity.course3.reviews.controller;

import com.udacity.course3.reviews.entity.Comment;
import com.udacity.course3.reviews.entity.MongoComment;
import com.udacity.course3.reviews.entity.MongoReview;
import com.udacity.course3.reviews.entity.Review;
import com.udacity.course3.reviews.repository.CommentRepository;
import com.udacity.course3.reviews.repository.MongoReviewRepository;
import com.udacity.course3.reviews.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

/**
 * Spring REST controller for working with comment entity.
 */
@RestController
@RequestMapping("/comments")
public class CommentsController {

    @Autowired
    private CommentRepository commentRepository;
    private ReviewRepository reviewRepository;
    private MongoReviewRepository mongoReviewRepository;

    /**
     * Creates a comment for a review.
     * <p>
     * 1. Add argument for comment entity. Use {@link RequestBody} annotation.
     * 2. Check for existence of review.
     * 3. If review not found, return NOT_FOUND.
     * 4. If found, save comment.
     *
     * @param reviewId The id of the review.
     */
    @RequestMapping(value = "/reviews/{reviewId}", method = RequestMethod.POST)
    public ResponseEntity<?> createCommentForReview(@PathVariable("reviewId") Integer reviewId, @Valid @RequestBody Comment comment) {
        Optional<Review> review = reviewRepository.findById(reviewId);
        if (review.isPresent()) {
            comment.setReviewId(review.get());
            commentRepository.save(comment);


            Optional<MongoReview> optionalMongoReview = mongoReviewRepository.findById(reviewId);
            if (optionalMongoReview.isPresent()) {
                MongoComment mongoComment = new MongoComment();
                mongoComment.setCommentId(comment.getCommentId());
                mongoComment.setContent(comment.getCommentContent());

                MongoReview mongoReview = optionalMongoReview.get();
                mongoReview.addComment(mongoComment);
                mongoReviewRepository.save(mongoReview);
            }


            return new ResponseEntity<>(HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * List comments for a review.
     * <p>
     * 2. Check for existence of review.
     * 3. If review not found, return NOT_FOUND.
     * 4. If found, return list of comments.
     *
     * @param reviewId The id of the review.
     */
    @RequestMapping(value = "/reviews/{reviewId}", method = RequestMethod.GET)
    public List<?> listCommentsForReview(@PathVariable("reviewId") Integer reviewId) {
        return commentRepository.findAllByReviewId(reviewId);
    }
}