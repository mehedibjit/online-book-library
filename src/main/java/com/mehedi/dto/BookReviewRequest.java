package com.mehedi.dto;

public class BookReviewRequest {

    private Long userId;
    private int rating;
    private String comment;

    // Constructors, getters, and setters

    public BookReviewRequest() {
    }

    public BookReviewRequest(Long userId, int rating, String comment) {
        this.userId = userId;
        this.rating = rating;
        this.comment = comment;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
