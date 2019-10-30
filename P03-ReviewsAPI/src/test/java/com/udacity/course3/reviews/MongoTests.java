package com.udacity.course3.reviews;

import com.udacity.course3.reviews.entity.MongoComment;
import com.udacity.course3.reviews.entity.MongoReview;
import com.udacity.course3.reviews.repository.MongoReviewRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;


@DataMongoTest
@RunWith(SpringRunner.class)
public class MongoTests {

    @Autowired
    MongoReviewRepository mongoReviewRepository;

    @Test
    public void testMongoReviews() {
        MongoComment comment = new MongoComment();
        comment.setCommentId(111);
        comment.setContent("A mongo comment");

        MongoReview review = new MongoReview();
        review.setReviewId(222);
        review.setProductId(333);
        review.setReviewContent("A mongo review");
        review.addComment(comment);

        mongoReviewRepository.save(review);

        MongoReview savedReview = mongoReviewRepository.findAll().get(0);
        MongoComment savedComment = savedReview.getComments().get(0);
        assertEquals(222, savedReview.getReviewId().intValue());
        assertEquals(333, savedReview.getProductId().intValue());
        assertEquals("A mongo review", savedReview.getReviewContent());
        assertEquals(111, savedComment.getCommentId().intValue());
        assertEquals("A mongo comment", savedComment.getContent());
    }
}
