package io.nology;

public abstract class Book {
    private String number;
    private String title;

    private String genre;
    private String subGenre;
    private String publisher;
    private User borrowedBy;

    public Book(String title) { //constructor
        this.title = title;
        this.number = null;
        this.borrowedBy = null;
    } // end constructor


    public User getBorrowedBy() { //getter
        return borrowedBy;
    }

    public void setBorrowedBy(User borrowedBy) { //setter
        this.borrowedBy = borrowedBy;
    }



    public String  getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }



    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getSubGenre() {
        return subGenre;
    }

    public void setSubGenre(String subGenre) {
        this.subGenre = subGenre;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }


}
