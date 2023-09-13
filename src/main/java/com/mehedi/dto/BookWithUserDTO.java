package com.mehedi.dto;

import com.mehedi.entity.Book;
import com.mehedi.entity.User;

public class BookWithUserDTO {
    private Book book;
    private User user;

    public BookWithUserDTO(Book book, User user) {
        this.book = book;
        this.user = user;
    }
}