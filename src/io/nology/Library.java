package io.nology;

import java.util.HashMap;

public class Library {
    private final HashMap<Integer, Book> books;

    // constructor
    public Library() {
        books = new HashMap<>();
    }

    // add a book to the library
    public void registerBook(Book book) {
        if (!books.containsKey(book.getNumber())) {
            books.put(book.getNumber(), book);
        }
    }

}
