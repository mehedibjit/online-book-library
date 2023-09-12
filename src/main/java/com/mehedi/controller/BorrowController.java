package com.mehedi.controller;

import com.mehedi.dto.BorrowRequest;
import com.mehedi.exception.BookNotFoundException;
import com.mehedi.exception.BookReturnException;
import com.mehedi.exception.BookUnavailableException;
import com.mehedi.exception.UserNotFoundException;
import com.mehedi.service.BookService;
import com.mehedi.service.BorrowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/books")
public class BorrowController {
    @Autowired
    private BorrowService borrowService;

    @PostMapping("/{bookId}/borrow")
    public ResponseEntity<?> borrowBook(
            @PathVariable Long bookId,
            @RequestBody BorrowRequest borrowRequest
    ) {
        try {
//            borrowService.borrowBook(bookId, borrowRequest.getUserId());
            borrowService.borrowBook(bookId, 1L, borrowRequest.getDueDate());
            return new ResponseEntity<>("Book borrowed successfully", HttpStatus.OK);
        } catch (BookNotFoundException ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
        } catch (BookUnavailableException ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception ex) {
            return new ResponseEntity<>("Failed to borrow the book. " + ex, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
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
