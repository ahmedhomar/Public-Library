package io.nology;

public abstract class Book {
    private String author;
    private String number;
    private String title;
    private String genre;
    private String subGenre;
    private String publisher;
    private User borrowedBy;

    public Book(String author, String title) { //constructor
        this.author = author; //initialize the fields
        this.title = title;

    }


    public User getBorrowedBy() { //getter
        return borrowedBy;
    }

    public void setBorrowedBy(User borrowedBy) { //setter
        this.borrowedBy = borrowedBy;
    }

    public String getNumber() {
        return number;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setNumber(String number) {
        this.number = number;
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
