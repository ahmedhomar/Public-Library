package io.nology;

public class BookItem extends Book {
    private String author;


    public BookItem(String title, String author) {
        super(title);
        this.author = author;
    }
    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    @Override
    public String toString() {
        return String.format("\"%s\" by %s", getTitle(), author);
    }
}
