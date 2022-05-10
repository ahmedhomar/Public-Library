package io.nology;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

public class Library {

    private final HashMap<String, Book> repository;
    private final HashMap<String, User> users;

    // constructor
    public Library() throws IOException {
        InputStream getLocalJsonFile = new FileInputStream("src/main/java/io/nology/books.json");
        repository = new ObjectMapper().readValue(getLocalJsonFile, HashMap.class);
        System.out.println(repository);
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
        for (Object book : repository.values()) {
            if (lookupFn.test((Book) book)) {
                result.add((Book) book);
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

    public static ArrayList<String> simulateLibrary(List<String> instructions) throws IOException {
        Library library = new Library();
        ArrayList<String> output = new ArrayList<>();

        for (String instruction : instructions) {
            String[] splitResult = instruction.split(" ", 2);
            if (splitResult[0].equals("register")) {
                splitResult = splitResult[1].split(" ", 3);
                Book newBook = null;
                if (splitResult[0].equals("book")) {
                    newBook = BookItem.parseDef(splitResult[2]);
                }
                if (newBook != null) {
//                    newBook.getNumber() = splitResult[1];
                    library.registerBook(newBook);
                }
            } else if (splitResult[0].equals("lookup")) {
                splitResult = splitResult[1].split(" ", 2);
                final String lookupParameter = splitResult[1];
                switch (splitResult[0]) {
                    case "id": {
                        ArrayList<Book> bookList = library.lookupBooks((book) -> book.getNumber().equals(lookupParameter));
                        outputBooks(bookList, output, null);
                        break;
                    }
                    case "title": {
                        ArrayList<Book> bookList = library.lookupBooks((book) -> book.getTitle().equals(lookupParameter));
                        outputBooks(bookList, output, (outputBookList) -> String.format("%d books match the title: %s", outputBookList.size(), lookupParameter));
                        break;
                    }
                    case "author": {
                        ArrayList<Book> bookList = library.lookupBooks((book) -> (book instanceof BookItem) && ((BookItem) (book)).getAuthor().equals(lookupParameter));
                        outputBooks(bookList, output, (outputBookList) -> String.format("%d books match the author: %s", outputBookList.size(), lookupParameter));
                        break;
                    }
                }
            } else if (splitResult[0].equals("borrow")) {
                splitResult = splitResult[1].split(" ", 2);
                if (library.repository.containsKey(splitResult[0])) {
                    library.getUser(splitResult[1]).borrowBook((Book) library.repository.get(splitResult[0]));
                }
            } else if (splitResult[0].equals("return")) {
                library.getUser(splitResult[1]).returnBook();
            }
        }
        return output;
    }





}
