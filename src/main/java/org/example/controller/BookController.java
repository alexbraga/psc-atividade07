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
}
