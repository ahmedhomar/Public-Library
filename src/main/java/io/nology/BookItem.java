package io.nology;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BookItem extends Book {
    private String author;


    public BookItem(String title, String author) {
        super(title);
        this.author = author;
    }

    public static BookItem parseDef(String bookRepresentation) { // "title" by author
        Pattern pattern = Pattern.compile("\"(.*)\" by (.*)"); // "title" by author
        Matcher matcher = pattern.matcher(bookRepresentation); // "title" by author
        if (matcher.find()) { // if the string matches the pattern
            return new BookItem(matcher.group(1), matcher.group(2)); // create a new BookItem with the title and author
        } else {
            return null;
        }
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    @Override
    public String toString() {
        return String.format("\"%s\" by %s", getTitle(), author);      // "title" by author
    }
}
