package org.example.ui;

import org.example.controller.BookController;
import org.example.exception.BookAlreadyExistsException;
import org.example.exception.BookNotFoundException;
import org.example.model.Book;

import java.util.List;
import java.util.Scanner;

public class ConsoleInterface {
    private final Scanner SCANNER;
    private final BookController CONTROLLER;

    public ConsoleInterface(Scanner scanner, BookController controller) {
        this.SCANNER = scanner;
        this.CONTROLLER = controller;
    }

    public void mainMenu() {
        int option;
        do {
            System.out.println("What would you like to do:\n");
            System.out.println("\t1. List all books");
            System.out.println("\t2. Add a new book");
            System.out.println("\t3. Search books");
            System.out.println("\t4. Edit a book");
            System.out.println("\t5. Delete a book");
            System.out.println("\t6. Exit");
            System.out.print("\nPlease, insert the desired option [1-6]: ");

            option = Integer.parseInt(SCANNER.nextLine());

            switch (option) {
                case 1:
                    listAllBooks();
                    break;
                case 2:
                    addNewBook();
                    break;
                case 3:
                    searchBooks();
                    break;
                case 4:
                    updateBook();
                    break;
                case 5:
                    deleteBook();
                    break;
                case 6:
                    System.out.println("Exiting the program...");
                    break;
                default:
                    System.out.println("Invalid option. Please, try again");
                    break;
            }
        } while (option != 6);
    }

    private void listAllBooks() {
        try {
            List<Book> bookList = CONTROLLER.getAll();

            System.out.println();
            for (Book book : bookList) {
                System.out.println(book);
            }
            System.out.println();
        } catch (Exception e) {
            System.out.println("\nError: " + e.getMessage() + "\n");
        }
    }

    private void addNewBook() {
        try {
            System.out.println("\nEnter the book details below. Please, note that 'ISBN-13' is a required field\n");

            System.out.print("Title: ");
            String title = SCANNER.nextLine();

            System.out.print("Author: ");
            String author = SCANNER.nextLine();

            System.out.print("Genre: ");
            String genre = SCANNER.nextLine();

            System.out.print("Publisher: ");
            String publisher = SCANNER.nextLine();

            System.out.print("Edition: ");
            int edition = Integer.parseInt(SCANNER.nextLine());

            System.out.print("Number Of Pages: ");
            int numOfPages = Integer.parseInt(SCANNER.nextLine());

            System.out.print("ISBN-13: ");
            String isbn = SCANNER.nextLine();

            Book newBook = new Book(title, author, genre, publisher, edition, numOfPages, isbn);
            System.out.println("\nBook successfully added:");
            System.out.println(CONTROLLER.saveBook(newBook) + "\n");
        } catch (BookAlreadyExistsException | BookNotFoundException e) {
            System.out.println("\nError: " + e.getMessage() + "\n");
        }
    }

    private void searchBooks() {
        try {
            System.out.println("\nEnter your search query below. You can search for words in book title, author name, genre, publisher or ISBN-13:");
            String searchQuery = SCANNER.nextLine();

            List<Book> bookList = CONTROLLER.findBooks(searchQuery);

            System.out.println("\nFound books:\n");
            for (Book book : bookList) {
                System.out.println(book);
            }
            System.out.println();
        } catch (BookNotFoundException e) {
            System.out.println("\nError: " + e.getMessage() + "\n");
        }
    }

    private void updateBook() {
        System.out.print("\nEnter the ISBN-13 of the book you want to edit: ");
        String isbn = SCANNER.nextLine();

        try {
            Book existingBook = CONTROLLER.findByISBN(isbn).orElseThrow(() -> new BookNotFoundException("Book not found"));

            System.out.println("\n" + existingBook);
            String answer;

            do {
                System.out.print("\nDo you want to proceed? [Y/n]: ");
                answer = SCANNER.nextLine();

                if (answer.equalsIgnoreCase("y")) {
                    System.out.println("\nEnter the new values for the book fields below. If you don't wish to change some field, leave it empty and press Enter");

                    System.out.print("Title [" + existingBook.getTitle() + "]: ");
                    String title = SCANNER.nextLine();
                    if (!title.isEmpty()) {
                        existingBook.setTitle(title);
                    }

                    System.out.print("Author [" + existingBook.getAuthor() + "]: ");
                    String author = SCANNER.nextLine();
                    if (!author.isEmpty()) {
                        existingBook.setAuthor(author);
                    }

                    System.out.print("Genre [" + existingBook.getGenre() + "]: ");
                    String genre = SCANNER.nextLine();
                    if (!genre.isEmpty()) {
                        existingBook.setGenre(genre);
                    }

                    System.out.print("Publisher [" + existingBook.getPublisher() + "]: ");
                    String publisher = SCANNER.nextLine();
                    if (!publisher.isEmpty()) {
                        existingBook.setPublisher(publisher);
                    }

                    System.out.print("Edition [" + existingBook.getEdition() + "]: ");
                    String edition = SCANNER.nextLine();
                    if (!edition.isEmpty()) {
                        existingBook.setEdition(Integer.parseInt(edition));
                    }

                    System.out.print("Number Of Pages [" + existingBook.getNumberOfPages() + "]: ");
                    String numOfPages = SCANNER.nextLine();
                    if (!numOfPages.isEmpty()) {
                        existingBook.setNumberOfPages(Integer.parseInt(numOfPages));
                    }

                    Book updatedBook = CONTROLLER.updateBook(isbn, existingBook);
                    System.out.println("\nBook updated successfully:");
                    System.out.println(updatedBook + "\n");
                } else if (answer.equalsIgnoreCase("n")) {
                    System.out.println();
                    mainMenu();
                } else {
                    System.out.println("Invalid option. Please, try again");
                }
            } while (!answer.equalsIgnoreCase("y") && !answer.equalsIgnoreCase("n"));
        } catch (BookNotFoundException e) {
            System.out.println("\nError: " + e.getMessage() + "\n");
        }
    }

    private void deleteBook() {
        System.out.print("\nEnter the ISBN-13 of the book you want to delete: ");
        String isbn = SCANNER.nextLine();

        try {
            Book existingBook = CONTROLLER.findByISBN(isbn).orElseThrow(() -> new BookNotFoundException("Book not found"));

            System.out.println("\n" + existingBook);
            String answer;

            do {
                System.out.print("\nDo you want to proceed? This action cannot be undone [Y/n]: ");
                answer = SCANNER.nextLine();

                if (answer.equalsIgnoreCase("y")) {
                    CONTROLLER.deleteBook(isbn);
                    System.out.println();
                } else if (answer.equalsIgnoreCase("n")) {
                    System.out.println();
                    mainMenu();
                } else {
                    System.out.println("Invalid option. Please, try again");
                }
            } while (!answer.equalsIgnoreCase("y") && !answer.equalsIgnoreCase("n"));
        } catch (BookNotFoundException e) {
            System.out.println("\nError: " + e.getMessage() + "\n");
        }
    }
}
