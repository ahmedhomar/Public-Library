package io.nology;

public class User extends Person {
    private Book borrowedBook;

    public User(String borrowedBook) {
        this.borrowedBook = null;
    }


    public Book getBorrowedBook() {
        return borrowedBook;
    }

    public void setBorrowedBook(Book borrowedBook) {
        this.borrowedBook = borrowedBook;
    }

    public void borrowBook(Book book) {
        if (borrowedBook == null && book.borrowedBy == null) {
            borrowedBook = book;
            book.borrowedBy = this;
        }
    }




}
