package org.example.dao;

import org.example.model.Book;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class BookDAO {
    private static final String FILE_NAME = "books.txt";

    public List<Book> findAll() {
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
                }
            }
        } catch (IOException e) {
            System.err.println("Error loading data: " + e.getMessage());
        }

        return books;
    }
}
