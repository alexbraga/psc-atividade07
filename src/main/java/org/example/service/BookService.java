package org.example.service;

import org.example.dao.BookDAO;
import org.example.exception.BookNotFoundException;
import org.example.model.Book;

import java.util.List;
import java.util.Optional;

public class BookService {
    private final BookDAO BOOK_DAO;
    private final List<Book> BOOK_LIST;

    public BookService(BookDAO bookDAO) {
        this.BOOK_DAO = bookDAO;
        this.BOOK_LIST = BOOK_DAO.findAll();
    }

    public List<Book> getBOOK_LIST() {
        return BOOK_LIST;
    }

    public List<Book> findAllAndIndex() {
        for (Book book : BOOK_LIST) {
            IndexService index = IndexService.getInstance();
            index.addToIndex(book);
        }

        return BOOK_LIST;
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

        if (bookOptional.isPresent()) {
            Book book = bookOptional.get();
            int bookIndex = BOOK_LIST.indexOf(book);
            BOOK_LIST.set(bookIndex, updatedBook);

            BOOK_DAO.saveAll(BOOK_LIST);

            IndexService index = IndexService.getInstance();
            index.removeFromIndex(book);
            index.addToIndex(updatedBook);

            return updatedBook;
        } else {
            throw new BookNotFoundException("Book not found");
        }
    }

    public void delete(String isbn) {
        Optional<Book> bookOptional = findByISBN(isbn);

        if (bookOptional.isPresent()) {
            Book book = bookOptional.get();
            int bookIndex = BOOK_LIST.indexOf(book);
            BOOK_LIST.remove(bookIndex);

            BOOK_DAO.saveAll(BOOK_LIST);

            IndexService index = IndexService.getInstance();
            index.removeFromIndex(book);

            System.out.println("Book deleted successfully");
        } else {
            System.out.println("Book not found");
        }
    }
}
