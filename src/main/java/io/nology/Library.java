package io.nology;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.function.Predicate;

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

    // add a user to the library

public User getUser(String name) {
    if (!users.containsKey(name)) {
        users.put(name, new User(name));
    }
    return users.get(name);
}

    public ArrayList<Book> lookupBooks(Predicate<Book> lookupFn) {
        ArrayList<Book> result = new ArrayList<>();
        for (Book book : books.values()) {
            if (lookupFn.test(book)) {
                result.add(book);
            }
        }
        return result;
    }





}
