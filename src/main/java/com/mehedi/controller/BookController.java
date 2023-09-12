package com.mehedi.controller;

import com.mehedi.dto.BookWithUserDTO;
import com.mehedi.entity.Book;
import com.mehedi.exception.BookNotFoundException;
import com.mehedi.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {
    @Autowired
    private BookService bookService;

    @PostMapping("/create")
    public ResponseEntity<Book> createBook(@RequestBody Book book) {
        Book createdBook = bookService.createBook(book);
        return new ResponseEntity<>(createdBook, HttpStatus.CREATED);
    }

    @PutMapping("/update/{bookId}")
    public ResponseEntity<?> updateBook(
            @PathVariable Long bookId,
            @RequestBody Book updatedBook
    ) {
        try {
            Book updated = bookService.updateBook(bookId, updatedBook);
            return new ResponseEntity<>(updated, HttpStatus.OK);
        } catch (BookNotFoundException ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception ex) {
            return new ResponseEntity<>("Failed to update the book.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/delete/{bookId}")
    public ResponseEntity<?> deleteBook(@PathVariable Long bookId) {
        try {
            bookService.deleteBook(bookId);
            return new ResponseEntity<>("Book deleted successfully", HttpStatus.OK);
        } catch (BookNotFoundException ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception ex) {
            return new ResponseEntity<>("Failed to delete the book.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

//    @GetMapping("/all")
//    public ResponseEntity<?> getAllBooks() {
//        try {
//            List<Book> books = bookService.getAllBooks();
//            return new ResponseEntity<>(books, HttpStatus.OK);
//        } catch (Exception ex) {
//            return new ResponseEntity<>("Failed to fetch books." + ex, HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }
    @GetMapping("/all")
    public ResponseEntity<?> getAllBooks() {
        try {
            List<Book> books = bookService.getAllBooks();
            return new ResponseEntity<>(books, HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>("Failed to fetch books." + ex, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
//    @GetMapping("/all")
//    public ResponseEntity<?> getAllBooks() {
//        try {
//            List<BookWithUserDTO> booksWithUser = bookService.getAllBooksWithUserDetails();
////            List<BookWithUserDTO> booksWithUser = bookService.getAllBooks();
//            return new ResponseEntity<>(booksWithUser, HttpStatus.OK);
//        } catch (Exception ex) {
//            return new ResponseEntity<>("Failed to fetch books." + ex, HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }

}