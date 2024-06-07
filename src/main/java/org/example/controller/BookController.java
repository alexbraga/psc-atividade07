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

    public Book saveBook(Book book) throws BookAlreadyExistsException {
        return bookService.saveBook(book);
    }

    public List<Book> getAll() throws BookNotFoundException {
        return bookService.getAll();
    }
}
