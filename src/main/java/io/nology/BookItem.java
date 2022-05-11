package io.nology;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BookItem extends Book {

    public BookItem(String title, String author) {
        super(title, author);

    }


    public static BookItem parseDefinition(String bookRepresentation) { // "title" by author
        Pattern pattern = Pattern.compile("\"(.*)\" by (.*)"); // "title" by author
        Matcher matcher = pattern.matcher(bookRepresentation); // "title" by author
        if (matcher.find()) { // if the string matches the pattern
            return new BookItem(matcher.group(1), matcher.group(2)); // create a new BookItem with the title and author
        } else {
            return null;
        }
    }


    @Override
    public String toString() {
        return String.format("\"%s\" by %s", getTitle(), getAuthor());      // "title" by author
    }
}
