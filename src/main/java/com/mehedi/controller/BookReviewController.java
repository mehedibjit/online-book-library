package com.mehedi.controller;

import com.mehedi.dto.BookReviewRequest;
import com.mehedi.dto.ReviewRequest;
import com.mehedi.entity.BookReview;
import com.mehedi.exception.ReviewNotFoundException;
import com.mehedi.exception.UnauthorizedUserException;
import com.mehedi.service.BookReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books/{bookId}/reviews")
public class BookReviewController {

    private final BookReviewService bookReviewService;

    @Autowired
    public BookReviewController(BookReviewService bookReviewService) {
        this.bookReviewService = bookReviewService;
    }

    @PostMapping("/create")
    public ResponseEntity<String> createReview(
            @PathVariable Long bookId,
            @RequestBody BookReviewRequest reviewRequest
    ) {
        Long userId = 1L; //*******
        bookReviewService.createReview(
                bookId,
                userId,
                reviewRequest.getRating(),
                reviewRequest.getComment()
        );
        return new ResponseEntity<>("Review created successfully", HttpStatus.CREATED);
    }

//    @GetMapping
//    public List<BookReview> getReviewsAndRatings(@PathVariable Long bookId) {
//        return bookReviewService.getReviewsByBookId(bookId);
//    }
//    @GetMapping
//    public ResponseEntity<List<BookReview>> getReviewsAndRatingsByBookId(@PathVariable Long bookId) {
//        List<BookReview> reviews = bookReviewService.getReviewsAndRatingsByBookId(bookId);
//        return new ResponseEntity<>(reviews, HttpStatus.OK);
//    }

    @PutMapping("/{reviewId}/update")
    public ResponseEntity<String> updateReviewAndRating(
            @PathVariable Long reviewId,
//            @RequestBody Integer newRating,
//            @RequestBody String newComment
            @RequestBody ReviewRequest reviewRequest
            ) {
        Integer newRating = reviewRequest.getRating();
        String newComment = reviewRequest.getComment();
        bookReviewService.updateReviewAndRating(reviewId, newRating, newComment);
        return new ResponseEntity<>("Review updated successfully", HttpStatus.OK);
    }

    @DeleteMapping("/{reviewId}/delete")
    public ResponseEntity<String> deleteReview(
            @PathVariable Long bookId,
            @PathVariable Long reviewId
    ) {
        Long userId = 1L;
        try {
            bookReviewService.deleteReview(bookId, reviewId, userId);
            return ResponseEntity.ok("Review deleted successfully");
        } catch (ReviewNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (UnauthorizedUserException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());
        }
    }
}
