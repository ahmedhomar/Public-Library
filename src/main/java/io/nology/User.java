package io.nology;

public class User extends Account {

    private Book borrowedBook; // book that user is currently borrowing

    public User(String name) {
        super(name); // call superclass constructor

    }


    public void borrowBook(Book book) {
        if (borrowedBook == null && book.getBorrowedBy() == null) { // if user has no book and book is not borrowed
            borrowedBook = book; // set user's book to book
            book.setBorrowedBy(this);
        }

    }

    public void returnBook() {
        if (borrowedBook != null && borrowedBook.getBorrowedBy() != null) { // if user has book and book is borrowed
            borrowedBook.setBorrowedBy(null); // set book's borrower to null
            borrowedBook = null; // set user's book to null
        }
    }

    @Override // override superclass method
    public String toString() {
        return getName();
    } // return user's name


}
