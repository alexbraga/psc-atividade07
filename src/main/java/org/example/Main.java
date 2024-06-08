package org.example;

import org.example.controller.BookController;
import org.example.dao.BookDAO;
import org.example.service.BookService;
import org.example.ui.ConsoleInterface;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        BookDAO bookDAO = new BookDAO();
        BookService bookService = new BookService(bookDAO);
        BookController controller = new BookController(bookService);
        ConsoleInterface ui = new ConsoleInterface(scanner, controller);

        ui.mainMenu();
        scanner.close();
    }
}