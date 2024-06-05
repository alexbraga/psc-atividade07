package org.example.dao;

import org.example.model.Book;
import org.example.service.IndexService;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class BookDAO {
    private static final String FILE_NAME = "books.txt";

    public List<Book> findAllAndIndex() {
        List<Book> books = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;

            while ((line = reader.readLine()) != null) {
                String[] bookData = line.split("; ");

                if (bookData.length == 7) {
                    Book book = new Book(
                            bookData[0].split(": ", 2)[1],
                            bookData[1].split(": ")[1],
                            bookData[2].split(": ")[1],
                            bookData[3].split(": ", 2)[1],
                            Integer.parseInt(bookData[4].split(": ")[1]),
                            Integer.parseInt(bookData[5].split(": ")[1]),
                            bookData[6].split(": ")[1]
                    );

                    books.add(book);
                    IndexService index = IndexService.getInstance();
                    index.addToIndex(book);
                }
            }
        } catch (IOException e) {
            System.err.println("Error loading data: " + e.getMessage());
        }

        return books;
    }

    public void saveAll(List<Book> books) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME, false))) {
            for (Book book : books) {
                writer.write(String.format("Title: %s; Author: %s; Genre: %s; Publisher: %s; Edition: %d; Number of Pages: %d; ISBN-13: %s",
                        book.getTitle(),
                        book.getAuthor(),
                        book.getGenre(),
                        book.getPublisher(),
                        book.getEdition(),
                        book.getNumberOfPages(),
                        book.getIsbn13()
                ));

                writer.write(System.lineSeparator());
            }
        } catch (IOException e) {
            System.err.println("Error saving data: " + e.getMessage());
        }
    }

    public List<Book> findBooks(String searchQuery) {
        IndexService indexService = IndexService.getInstance();
        return indexService.searchIndex(searchQuery);
    }
}
