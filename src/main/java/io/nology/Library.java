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

    private final HashMap<String,Book> repository;
    private final HashMap<String, User> users;

    // constructor
    public Library() throws IOException {
        InputStream getLocalJsonFile = new FileInputStream("src/main/java/io/nology/books.json"); // get the local json file
        repository = new ObjectMapper().readValue(getLocalJsonFile, HashMap.class); // read the file and store the books in the repository

        users = new HashMap<>();
    }

    // add a book to the library
    public void registerBook(Book book) {
        if (!repository.containsKey(book.getNumber())) { // if the book does not exist, add it to the repository
            repository.put(book.getNumber(), book);
        }
    }

    // add a user to the library

    public User getUser(String name) {
        if (!users.containsKey(name)) { // if user does not exist, create a new one
            users.put(name, new User(name));
        }
        return users.get(name);
    }

    // get a book from the library
    public List<Book> lookupBooks(Predicate<Book> lookupFn) { // lookup books that match the given predicate
        List<Book> result = new ArrayList<>();
        for (Book book : repository.values()) {
            if (lookupFn.test(book)) { // if the book matches the predicate, add it to the result
                result.add(book);
            }
        }
        return result;
    }

    public static void outputBooks(List<Book> bookList, List<String> output, Function<List<Book>, String> multipleMatchOutput) { // output the books that match the given predicate
        if (bookList.isEmpty()) {
            output.add("No such book exists");
        } else if (bookList.size() == 1) {
            output.add(bookList.get(0).toString());
            output.add(String.format("ID: %s", bookList.get(0).getNumber())); // get the book's ID
            if (bookList.get(0).getBorrowedBy() != null) {
                output.add(String.format("Borrowed by: %s", bookList.get(0).getBorrowedBy().toString()));
            }
        } else {
            if (multipleMatchOutput == null) { // if no output function is given, use the default one
                return;
            }
            output.add(multipleMatchOutput.apply(bookList)); // use the output function
            int availableCount = 0;
            for (Book book : bookList) {
                if (book.getBorrowedBy() == null) { // count the number of available books
                    availableCount++; // if the book is available, increase the count
                }
            }
            output.add(String.format("%d book(s) available", availableCount)); // print the number of available books
        }
    }

    public static List<String> simulateLibrary(List<String> instructions) throws IOException {
        Library library = new Library();
        List<String> output = new ArrayList<>();

        for (String instruction : instructions) {
            String[] splitResult = instruction.split(" ", 2); // split the instruction into two parts
            switch (splitResult[0]) {
                case "register":
                    splitResult = splitResult[1].split(" ", 3); // split the instruction into three parts
                    Book newBook = null;
                    if (splitResult[0].equals("book")) { // if the book is a book
                        newBook = BookItem.parseDef(splitResult[2]); // parse the book definition
                    }
                    if (newBook != null) { // if the book is valid, add it to the library
                        splitResult[1] = newBook.getNumber(); // set the book's ID
                        library.registerBook(newBook);
                    }
                    break;
                case "lookup":
                    splitResult = splitResult[1].split(" ", 2);
                    final String lookupParameter = splitResult[1];
                    switch (splitResult[0]) {
                        case "id": {
                            List<Book> bookList = library.lookupBooks((book) -> book.getNumber().equals(lookupParameter)); // lookup books with the given ID
                            outputBooks(bookList, output, null); // output the books
                            break;
                        }
                        case "title": {
                            List<Book> bookList = library.lookupBooks(book -> book.getTitle().equals(lookupParameter)); // look up the books that match the given predicate
                            outputBooks(bookList, output, outputBookList -> String.format("%d books match the title: %s", outputBookList.size(), lookupParameter)); // print the number of books that match the title
                            break;
                        }
                        case "author": {
                            List<Book> bookList = library.lookupBooks(book -> (book instanceof BookItem) && ((BookItem) (book)).getAuthor().equals(lookupParameter)); // get the books that match the author
                            outputBooks(bookList, output, outputBookList -> String.format("%d books match the author: %s", outputBookList.size(), lookupParameter)); // print the number of books that match the author
                            break;
                        }
                    }
                    break;
                case "borrow":
                    splitResult = splitResult[1].split(" ", 2);
                    if (library.repository.containsKey(splitResult[0])) { // if the book is registered
                        library.getUser(splitResult[1]).borrowBook(library.repository.get(splitResult[0]));  // borrow the book
                    }
                    break;
                case "return":
                    library.getUser(splitResult[1]).returnBook(); // return the book
                    break;
            }
        }
        return output;
    }





}
