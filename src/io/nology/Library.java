package io.nology;

import java.util.HashMap;

public class Library {
    private final HashMap<Integer, Book> books;
    private HashMap<String, User> users;
    // constructor
    public Library() {
        books = new HashMap<>();
        users = new HashMap<>();
    }

    // add a book to the library
    public void registerBook(Book book) {
        if (!books.containsKey(book.getNumber())) {
            books.put(book.getNumber(), book);
        }
    }
public User getUser(String name) {
    if (!users.containsKey(name)) {
        users.put(name, new User(name));
    }
    return users.get(name);
}

}
