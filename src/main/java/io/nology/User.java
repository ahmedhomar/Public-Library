package io.nology;

public class User extends Person {
    private String username;
    private Book borrowedBook;

    public User(String username) {
        this.username = username;
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


//        public void returnBook() {
//            if (borrowedBook != null && borrowedBook.getBorrowedBy() != null) {
//                borrowedBook.setBorrowedBy(null);
//                borrowedBook = null;
//            }
//        }

    }

    @Override
    public String toString() {
        return name;
    }


}
