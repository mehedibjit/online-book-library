package com.mehedi.service;

import com.mehedi.entity.Book;
import com.mehedi.entity.BookReview;
import com.mehedi.entity.User;
import com.mehedi.exception.BookNotFoundException;
import com.mehedi.exception.UserNotFoundException;
import com.mehedi.repository.BookRepository;
import com.mehedi.repository.BookReviewRepository;
import com.mehedi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookReviewService {

    private final BookReviewRepository reviewRepository;
    private final BookRepository bookRepository;
    private final UserRepository userRepository;

    @Autowired
    public BookReviewService(BookReviewRepository reviewRepository, BookRepository bookRepository, UserRepository userRepository) {
        this.reviewRepository = reviewRepository;
        this.bookRepository = bookRepository;
        this.userRepository = userRepository;
    }

    public void createReview(Long bookId, Long userId, int rating, String comment) {
        Optional<Book> optionalBook = bookRepository.findByBookId(bookId);
        Optional<User> optionalUser = userRepository.findByUserId(userId);

        if (optionalBook.isPresent() && optionalUser.isPresent()) {
            Book book = optionalBook.get();
            User user = optionalUser.get();

            BookReview review = new BookReview();
            review.setBook(book);
            review.setUser(user);
            review.setRating(rating);
            review.setComment(comment);

            reviewRepository.save(review);
        } else {
            if (!optionalBook.isPresent()) {
                throw new BookNotFoundException("Book not found with id: " + bookId);
            }
            if (!optionalUser.isPresent()) {
                throw new UserNotFoundException("User not found with id: " + userId);
            }
        }
    }

    public List<BookReview> getReviewsByBookId(Long bookId) {
        return reviewRepository.findByBookId(bookId);
    }
}
