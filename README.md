# Online Book Library Application

## Overview

This project is an Online Book Library application, focusing on the backend development of RESTful APIs. The application provides various endpoints for user management, book management, book borrowing and returning, book reservations, book reviews and ratings, and user history. The REST endpoints are secured using Spring Security with role-based authentication, and MySQL is used as the database.

## Features

### User Management

- **Register User**
  - Endpoint: `/user/register`
  - Description: Register a new user with user information such as firstName, lastName, email, password, and address.

- **User Login**
  - Endpoint: `/user/login`
  - Description: Login with user email and password, returning a token as a response.

- **Retrieve User Details**
  - Endpoint: `/users/{userId}`
  - Description: Retrieve user details by userId. Only users with ADMIN roles have access to this API.

- **Retrieve User Books**
  - Endpoint: `/users/{userId}/books`
  - Description: Retrieve the books borrowed or owned by a specific user. Accessible by the user or an ADMIN.

- **Retrieve User Borrowed Books**
  - Endpoint: `/users/{userId}/borrowed-books`
  - Description: Retrieve the books currently borrowed by a specific user. Accessible by the user or an ADMIN.

### Books Management

- **Create Book**
  - Endpoint: `/books/create`
  - Description: Add a new book to the database. User must have "ADMIN" role.

- **Update Book**
  - Endpoint: `/books/update`
  - Description: Update a book's data in the database. User must have "ADMIN" role.

- **Delete Book**
  - Endpoint: `/books/delete`
  - Description: Delete a book from the database. User must have "ADMIN" role.

- **Fetch All Books**
  - Endpoint: `/books/all`
  - Description: Fetch and show all the books data stored in the database. User must have "ADMIN" or "CUSTOMER" role.

### Book Borrowing/Returning

- **Borrow Book**
  - Endpoint: `/books/{bookId}/borrow`
  - Description: Allow users (CUSTOMER) to borrow a book by bookId. Track book availability and due date.

- **Return Book**
  - Endpoint: `/books/{bookId}/return`
  - Description: Allow users (CUSTOMER) to return a borrowed book by bookId. Update the book's status accordingly.

### Book Reservation (Optional)

- **Reserve Book**
  - Endpoint: `/books/{bookId}/reserve`
  - Description: Allow users (CUSTOMER) to reserve a book that is currently unavailable. Notify the user when the book becomes available.

- **Cancel Reservation**
  - Endpoint: `/books/{bookId}/cancel-reservation`
  - Description: Allow users (CUSTOMER) to cancel a book reservation. Update the reservation status accordingly.

### Book Reviews and Ratings

- **Retrieve Reviews and Ratings**
  - Endpoint: `/books/{bookId}/reviews`
  - Description: Retrieve reviews and ratings for a specific book by bookId.

- **Create Review and Rating**
  - Endpoint: `/books/{bookId}/reviews/create`
  - Description: Allow users (CUSTOMER) to create a review and rating for a book.

- **Update Review and Rating**
  - Endpoint: `/books/{bookId}/reviews/{reviewId}/update`
  - Description: Allow users (CUSTOMER) to update their own review and rating for a book.

- **Delete Review and Rating**
  - Endpoint: `/books/{bookId}/reviews/{reviewId}/delete`
  - Description: Allow users (CUSTOMER) to delete their own review and rating for a book.

### User History

- **View Borrowing History**
  - Endpoint: `/users/{userId}/history`
  - Description: Allow users to view their borrowing history, including borrowed books, due dates, and return dates.
