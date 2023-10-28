package com.mehedi.controller;

import com.mehedi.entity.BookReservation;
import com.mehedi.entity.User;
import com.mehedi.exception.BookReservationException;
import com.mehedi.exception.ReservationNotFoundException;
import com.mehedi.exception.UnauthorizedUserException;
import com.mehedi.repository.UserRepository;
import com.mehedi.service.BookReservationService;
import com.mehedi.service.UserAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books/{bookId}")
public class BookReservationController {
    @Autowired
    private BookReservationService reservationService;

    @Autowired
    private UserAuthService userAuthService;

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/reserve")
    public ResponseEntity<String> reserveBook(@PathVariable Long bookId) {
        reservationService.reserveBook(bookId);
        return new ResponseEntity<>("Book reserved successfully.", HttpStatus.OK);
    }

    @DeleteMapping("/cancel-reservation")
    public ResponseEntity<?> cancelReservation(@PathVariable Long bookId) {
//        Long userId = 2L;
//        Long userId = userAuthService.getUserId();
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        User user = userRepository.findByEmail(authentication.getName()).get();
        try {
            reservationService.cancelReservation(bookId);
            return ResponseEntity.ok("Reservation canceled successfully.");
        } catch (ReservationNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Reservation not found.");
        } catch (UnauthorizedUserException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("You are not authorized to cancel this reservation.");
        } catch (BookReservationException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to cancel reservation: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to cancel reservation. " + e.getMessage());
        }
    }
}
