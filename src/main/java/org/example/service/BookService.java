package org.example.service;

import org.example.dao.BookDAO;
import org.example.exception.BookAlreadyExistsException;
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
        findAllAndIndex();
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

    public Book saveBook(Book book) throws BookAlreadyExistsException {
        if (findByISBN(book.getIsbn13()).isPresent()) {
            throw new BookAlreadyExistsException("Conflict: ISBN already in use!");
        }

        BOOK_LIST.add(book);
        saveAllAndIndex(BOOK_LIST);

        return book;
    }

    public void saveAllAndIndex(List<Book> bookList) {
        BOOK_DAO.saveAll(bookList);

        for (Book book : bookList) {
            IndexService index = IndexService.getInstance();
            index.addToIndex(book);
        }
    }

    public List<Book> getAll() throws BookNotFoundException {
        if (BOOK_LIST.isEmpty()) {
            throw new BookNotFoundException("The 'books.txt' file is empty");
        }

        return BOOK_LIST;
    }

    public List<Book> findBooks(String searchQuery) throws BookNotFoundException {
        IndexService index = IndexService.getInstance();
        List<Book> foundBooks = index.searchIndex(searchQuery);

        if (foundBooks.isEmpty()) {
            throw new BookNotFoundException("Your search for '" + searchQuery + "' did not match any books. Try checking your spelling");
        }

        return foundBooks;
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
