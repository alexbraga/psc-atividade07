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
        IndexService indexService = IndexService.getInstance();
        return indexService.searchIndex(searchQuery);
    }

    public Optional<Book> findByISBN(String isbn) {
        IndexService indexService = IndexService.getInstance();
        return indexService.findByISBN(isbn);
    }
}
