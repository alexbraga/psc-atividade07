package org.example.controller;

import org.example.exception.BookAlreadyExistsException;
import org.example.exception.BookNotFoundException;
import org.example.model.Book;
import org.example.service.BookService;

import java.util.List;

public class BookController {
    private BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    public Book saveBook(Book book) throws BookAlreadyExistsException, BookNotFoundException {
        if (book == null || book.getIsbn13() == null || book.getIsbn13().isEmpty()) {
            throw new IllegalArgumentException("Book or ISBN-13 cannot be null or empty");
        } else if (book.getIsbn13().length() != 13) {
            throw new IllegalArgumentException("ISBN-13 must have exactly 13 digits");
        }

        return bookService.saveBook(book);
    }

    public List<Book> getAll() throws BookNotFoundException {
        return bookService.getAll();
    }

    public List<Book> findBooks(String searchQuery) throws BookNotFoundException {
        return bookService.findBooks(searchQuery);
    }

    public Book updateBook(String isbn, Book updatedBook) throws BookNotFoundException {
        if (isbn == null || updatedBook == null || updatedBook.getIsbn13().isEmpty()) {
            throw new IllegalArgumentException("Book or ISBN-13 cannot be null or empty");
        }

        isbn = isbn.replaceAll("[^0-9]", "");
        String updatedBookIsbn = updatedBook.getIsbn13().replaceAll("[^0-9]", "");

        if (isbn.length() != 13 || updatedBookIsbn.length() != 13) {
            throw new IllegalArgumentException("ISBN-13 must have exactly 13 digits");
        }

        return bookService.update(isbn, updatedBook);
    }

    public void deleteBook(String isbn) throws BookNotFoundException {
        if (isbn == null) {
            throw new IllegalArgumentException("ISBN-13 cannot be null or empty");
        }

        isbn = isbn.replaceAll("[^0-9]", "");

        if (isbn.length() != 13) {
            throw new IllegalArgumentException("ISBN-13 must have exactly 13 digits");
        }

        bookService.delete(isbn);
    }
}
