package org.example.controller;

import org.example.exception.BookAlreadyExistsException;
import org.example.model.Book;
import org.example.service.BookService;

import java.util.List;

public class BookController {
    private BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    public Book saveBook(Book book) throws BookAlreadyExistsException {
        if (bookService.findByISBN(book.getIsbn13()).isPresent()) {
            throw new BookAlreadyExistsException("Conflict: ISBN already in use!");
        }

        List<Book> bookList = bookService.getBOOK_LIST();
        bookList.add(book);
        bookService.saveAllAndIndex(bookList);

        return book;
    }
}
