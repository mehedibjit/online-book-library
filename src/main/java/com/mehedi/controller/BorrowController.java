package com.mehedi.controller;

import com.mehedi.dto.BorrowRequest;
import com.mehedi.entity.Book;
import com.mehedi.entity.BookBorrow;
import com.mehedi.entity.BookReview;
import com.mehedi.entity.User;
import com.mehedi.exception.BookNotFoundException;
import com.mehedi.exception.BookReturnException;
import com.mehedi.exception.BookUnavailableException;
import com.mehedi.exception.UserNotFoundException;
import com.mehedi.repository.BookBorrowRepository;
import com.mehedi.service.BookService;
import com.mehedi.service.BorrowService;
import com.mehedi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Optional;

@RestController
@RequestMapping("/books")
public class BorrowController {
    @Autowired
    private BorrowService borrowService;

    @Autowired
    private BookBorrowRepository bookBorrowRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private BookService bookService;


    // Endpoint to borrow a book
//    @PostMapping("/{bookId}/borrow")
//    public ResponseEntity<?> borrowBook(@PathVariable Long bookId, @RequestBody BorrowRequest borrowRequest) {
//        try {
//            Long userId = 2L;
//            // Validate and process the borrow request
//            borrowService.borrowBook(bookId, userId, borrowRequest.getDueDate());
//
//            // Create a BookBorrow entry
//            BookBorrow bookBorrow = new BookBorrow();
//            bookBorrow.setUserId(borrowRequest.getUserId());
//            bookBorrow.setBookId(bookId);
//            bookBorrow.setBorrowDate(LocalDate.now());
//            // Set the due date in bookBorrow based on borrowRequest.getDueDate()
//
//            bookBorrowRepository.save(bookBorrow);
//
//            return new ResponseEntity<>("Book borrowed successfully", HttpStatus.OK);
//        } catch (Exception ex) {
//            return new ResponseEntity<>("Failed to borrow the book. " + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }

    @PostMapping("/{bookId}/borrow")
    public ResponseEntity<?> borrowBook(@PathVariable Long bookId, @RequestBody BorrowRequest borrowRequest) {
        try {
            Long userId = 2L; //*****
            // Call the borrowService to borrow the book
            borrowService.borrowBook(bookId, userId, borrowRequest.getDueDate());

            // Create a BookBorrow entry to store the borrow history
//            BookBorrow bookBorrow = new BookBorrow();
//
//            Optional<User> optionalUser = userService.findUserById(userId);
//            Optional<Book> optionalBook = bookService.findBookById(bookId);
//
//            User user = optionalUser.orElseThrow(() -> new UserNotFoundException("User not found with id: " + userId));
//
//            Book book = optionalBook.orElseThrow(() -> new BookNotFoundException("Book not found with id: " + bookId));
//            bookBorrow.setUser(user);
//            bookBorrow.setBook(book);
//
//            bookBorrow.setBorrowDate(LocalDate.now());
//
//
//
//            // Save the borrow history to the database
//            bookBorrowRepository.save(bookBorrow);

            return new ResponseEntity<>("Book borrowed successfully", HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>("Failed to borrow the book: " + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    //    @PostMapping("/{bookId}/borrow")
//    public ResponseEntity<?> borrowBook(
//            @PathVariable Long bookId,
//            @RequestBody BorrowRequest borrowRequest
//    ) {
//        try {
////            borrowService.borrowBook(bookId, borrowRequest.getUserId());
//            LocalDate borrowDate = LocalDate.now();
//            borrowService.borrowBook(bookId, 2L, borrowDate, borrowRequest.getDueDate());
//            return new ResponseEntity<>("Book borrowed successfully", HttpStatus.OK);
//        } catch (BookNotFoundException ex) {
//            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
//        } catch (BookUnavailableException ex) {
//            return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
//        } catch (Exception ex) {
//            return new ResponseEntity<>("Failed to borrow the book. " + ex, HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }
    @PutMapping("/{bookId}/return")
    public ResponseEntity<String> returnBook(@PathVariable Long bookId) {
        // Assuming you have the user's ID or can retrieve it from the authentication
        Long userId = 2L; // Replace with the actual user's ID or retrieve it as needed

        try {
            borrowService.returnBook(bookId, userId);
            return new ResponseEntity<>("Book returned successfully", HttpStatus.OK);
        } catch (BookReturnException e) {
            // Handle the exception for when the book cannot be returned
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (BookNotFoundException | UserNotFoundException e) {
            // Handle the exceptions for when the book or user is not found
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}
