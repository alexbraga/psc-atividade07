package org.example.service;

import org.example.dao.BookDAO;
import org.example.model.Book;

import java.util.List;
import java.util.Optional;

public class BookService {
    private final BookDAO BOOK_DAO;

    public BookService(BookDAO bookDAO) {
        this.BOOK_DAO = bookDAO;
    }

    public List<Book> findAllAndIndex() {
        List<Book> bookList = BOOK_DAO.findAll();

        for (Book book : bookList) {
            IndexService index = IndexService.getInstance();
            index.addToIndex(book);
        }

        return bookList;
    }

    public void saveAllAndIndex(List<Book> bookList) {
        BOOK_DAO.saveAll(bookList);

        for (Book book : bookList) {
            IndexService index = IndexService.getInstance();
            index.addToIndex(book);
        }
    }

    public List<Book> findBooks(String searchQuery) {
        IndexService index = IndexService.getInstance();
        return index.searchIndex(searchQuery);
    }

    public Optional<Book> findByISBN(String isbn) {
        IndexService index = IndexService.getInstance();
        return index.findByISBN(isbn);
    }

    public Book update(String isbn, Book updatedBook) {
        Optional<Book> bookOptional = findByISBN(isbn);
        List<Book> bookList = BOOK_DAO.findAll();

        if (bookOptional.isPresent()) {
            Book book = bookOptional.get();
            int bookIndex = bookList.indexOf(book);
            bookList.set(bookIndex, updatedBook);

            BOOK_DAO.saveAll(bookList);

            IndexService index = IndexService.getInstance();
            index.removeFromIndex(book);
            index.addToIndex(updatedBook);

            return updatedBook;
        } else {
            throw new BookNotFoundException("Book not found");
        }
    }
}
