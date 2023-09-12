package com.mehedi.service;
import com.mehedi.entity.Book;
import com.mehedi.exception.BookNotFoundException;
import com.mehedi.exception.BookServiceException;
import com.mehedi.exception.DuplicateBookException;
import com.mehedi.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {
    @Autowired
    private BookRepository bookRepository;

    public Book createBook(Book book) {
        // Check for null values in required fields
        if (book.getTitle() == "" || book.getAuthor() == "" || "".equals(book.getAvailabilityStatus())) {
            throw new IllegalArgumentException("Title, author, and availability status cannot be null.");
        }

        // Check for duplicate book by title and author
        if(bookRepository.existsByTitleAndAuthor(book.getTitle(), book.getAuthor())) {
            throw new DuplicateBookException("A book with the same title and author already exists.");
        }
        return bookRepository.save(book);
    }

    public Book updateBook(Long bookId, Book updatedBook) {
        Book existingBook = bookRepository.findById(bookId)
                .orElseThrow(() -> new BookNotFoundException("Book not found with id: " + bookId));

        // Update the properties of the existing book
        existingBook.setTitle(updatedBook.getTitle());
        existingBook.setAuthor(updatedBook.getAuthor());
        existingBook.setAvailabilityStatus(updatedBook.getAvailabilityStatus());
        existingBook.setDueDate(updatedBook.getDueDate());

        // Save the updated book
        return bookRepository.save(existingBook);
    }

    public void deleteBook(Long bookId) {
        Book existingBook = bookRepository.findById(bookId)
                .orElseThrow(() -> new BookNotFoundException("Book not found with id: " + bookId));
        bookRepository.delete(existingBook);
    }

    public List<Book> getAllBooks() {
        try {
            return bookRepository.findAll();
        } catch (Exception ex) {
            throw new BookServiceException("Failed to fetch books.", ex);
        }
    }

    public Optional<Book> findBookById(Long bookId) {
        return bookRepository.findById(bookId);
    }
}
