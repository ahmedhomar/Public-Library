package io.nology;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

public class Library {
    private HashMap<Integer, Book> repository;
    private HashMap<String, User> users;

    // constructor
    public Library() {
        repository = new HashMap<>();
        users = new HashMap<>();
    }

    // add a book to the library
    public void registerBook(Book book) {
        if (!repository.containsKey(book.getNumber())) {
            repository.put(book.getNumber(), book);
        }
    }

    // add a user to the library

    public User getUser(String name) {
        if (!users.containsKey(name)) {
            users.put(name, new User(name));
        }
        return users.get(name);
    }

    // get a book from the library
    public ArrayList<Book> lookupBooks(Predicate<Book> lookupFn) { // lookup books that match the given predicate
        ArrayList<Book> result = new ArrayList<>();
        for (Book book : repository.values()) {
            if (lookupFn.test(book)) {
                result.add(book);
            }
        }
        return result;
    }

    public static void outputBooks(ArrayList<Book> bookList, List<String> output, Function<ArrayList<Book>, String> multipleMatchOutput) {
        if (bookList.isEmpty()) {
            output.add("No such book exists");
        } else if (bookList.size() == 1) {
            output.add(bookList.get(0).toString());
            output.add(String.format("ID: %s", bookList.get(0).getNumber()));
            if (bookList.get(0).getBorrowedBy() != null) {
                output.add(String.format("Borrowed by: %s", bookList.get(0).getBorrowedBy().toString()));
            }
        } else {
            if (multipleMatchOutput == null) {
                return;
            }
            output.add(multipleMatchOutput.apply(bookList));
            int availableCount = 0;
            for (Book book : bookList) {
                if (book.getBorrowedBy() == null) {
                    availableCount++;
                }
            }
            output.add(String.format("%d book(s) available", availableCount));
        }
    }


}
