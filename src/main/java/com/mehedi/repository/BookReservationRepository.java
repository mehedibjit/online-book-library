package com.mehedi.repository;

import com.mehedi.constatnts.ReservationStatus;
import com.mehedi.entity.Book;
import com.mehedi.entity.BookReservation;
import com.mehedi.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookReservationRepository extends JpaRepository<BookReservation, Long> {
    List<BookReservation> findByUser(User user);
    List<BookReservation> findByBook(Book book);
    List<BookReservation> findByBookAndReservationStatus(Book book, ReservationStatus status);
    List<BookReservation> findByUserAndReservationStatus(User user, ReservationStatus status);
    Optional<BookReservation> findByBookAndUser(Book book, User user);
}
