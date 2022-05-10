package io.nology;

public class User extends Account {

    private Book borrowedBook;

    public User(String name) {
        super(name);

        this.borrowedBook = null;
    }


    public Book getBorrowedBook() {
        return borrowedBook;
    }

    public void setBorrowedBook(Book borrowedBook) {
        this.borrowedBook = borrowedBook;
    }

    public void borrowBook(Book book) {
        if (borrowedBook == null && book.getBorrowedBy() == null) {
            borrowedBook = book;
            book.setBorrowedBy(this);
        }


    }

    @Override
    public String toString() {
        return getName();
    }


    public void returnBook() {
        if (borrowedBook != null && borrowedBook.getBorrowedBy() != null) {
            borrowedBook.setBorrowedBy(null);
            borrowedBook = null;
        }
    }
}
