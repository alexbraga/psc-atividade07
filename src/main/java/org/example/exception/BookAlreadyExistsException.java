package org.example.exception;

public class BookAlreadyExistsException extends Exception {
    public BookAlreadyExistsException(String message) {
        super(message);
    }
}
