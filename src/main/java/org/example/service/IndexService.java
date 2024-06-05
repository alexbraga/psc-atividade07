package org.example.service;

import org.example.model.Book;

import java.util.*;

public class IndexService {
    private static IndexService instance;
    private final Map<String, List<Book>> INDEX;
    private static final List<String> ENGLISH_STOP_WORDS = Arrays.asList(
            "a", "about", "among", "an", "and", "at", "but", "for", "from", "i", "in", "into", "is", "it", "me", "of",
            "on", "or", "that", "the", "these", "this", "those", "throughout", "to", "towards", "until", "upon", "with", "&"
    );
    private static final List<String> PORTUGUESE_STOP_WORDS = Arrays.asList(
            "acerca", "até", "com", "da", "de", "do", "e", "em", "entre", "eu", "mas", "na", "no", "ou", "para", "que", "sobre", "&"
    );

    private IndexService() {
        this.INDEX = new HashMap<>();
    }

    public static IndexService getInstance() {
        if (instance == null) {
            instance = new IndexService();
        }

        return instance;
    }

    public void addToIndex(Book book) {
        // Split the title, author and publisher into individual words
        String[] titleWords = book.getTitle().toLowerCase().split("\\s+");
        String[] authorWords = book.getAuthor().toLowerCase().split("\\s+");
        String[] publisherWords = book.getPublisher().toLowerCase().split("\\s+");

        // Index the book by each word in its title, author's name and publisher
        indexBookByWords(book, titleWords);
        indexBookByWords(book, authorWords);
        indexBookByWords(book, publisherWords);

        String genre = book.getGenre().toLowerCase();
        String isbn = book.getIsbn13().toLowerCase();

        // If the genre/isbn isn't already indexed, create new ArrayList and add the related book, otherwise just add the book
        INDEX.computeIfAbsent(genre, k -> new ArrayList<>()).add(book);
        INDEX.computeIfAbsent(isbn, k -> new ArrayList<>()).add(book);
    }

    private void indexBookByWords(Book book, String[] wordList) {
        for (String word : wordList) {
            word = word.replaceAll("[^a-z0-9]", "");

            if (!ENGLISH_STOP_WORDS.contains(word) && !PORTUGUESE_STOP_WORDS.contains(word) && word.length() > 1) {
                // If the word isn't already indexed, create new ArrayList and add the related book, otherwise just add the book
                INDEX.computeIfAbsent(word, k -> new ArrayList<>()).add(book);
            }
        }
    }

    public Map<String, List<Book>> getINDEX() {
        return INDEX;
    }
}
