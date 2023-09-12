package com.mehedi.controller;

import com.mehedi.dto.BookReviewRequest;
import com.mehedi.entity.BookReview;
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
        Long userId = 2L; //*******
        bookReviewService.createReview(
                bookId,
                userId,
                reviewRequest.getRating(),
                reviewRequest.getComment()
        );
        return new ResponseEntity<>("Review created successfully", HttpStatus.CREATED);
    }

    @GetMapping
    public List<BookReview> getReviewsAndRatings(@PathVariable Long bookId) {
        return bookReviewService.getReviewsByBookId(bookId);
    }
}
