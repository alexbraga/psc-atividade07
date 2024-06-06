package org.example.model;

public class Book {
    private String title;
    private String author;
    private String genre;
    private String publisher;
    private int edition;
    private int numberOfPages;
    private String isbn13;

    public Book(
            String title,
            String author,
            String genre,
            String publisher,
            int edition,
            int numberOfPages,
            String isbn13
    ) {
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.publisher = publisher;
        this.edition = edition;
        this.numberOfPages = numberOfPages;
        this.isbn13 = isbn13;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getIsbn13() {
        return isbn13;
    }

    public void setIsbn13(String isbn13) {
        this.isbn13 = isbn13;
    }

    public int getNumberOfPages() {
        return numberOfPages;
    }

    public void setNumberOfPages(int numberOfPages) {
        this.numberOfPages = numberOfPages;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public int getEdition() {
        return edition;
    }

    public void setEdition(int edition) {
        this.edition = edition;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Book book = (Book) obj;
        return isbn13.equals(book.isbn13);
    }

    @Override
    public String toString() {
        return String.format("Title: %s; Author: %s; Genre: %s; Publisher: %s; Edition: %d; Number of Pages: %d; ISBN-13: %s",
                this.getTitle(),
                this.getAuthor(),
                this.getGenre(),
                this.getPublisher(),
                this.getEdition(),
                this.getNumberOfPages(),
                this.getIsbn13()
        );
    }
}
