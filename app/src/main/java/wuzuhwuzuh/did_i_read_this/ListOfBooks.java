package wuzuhwuzuh.did_i_read_this;

import java.util.HashSet;
import java.util.Set;

/**
 * Placeholder for book database
 */

public class ListOfBooks {

    private static final Set<String> books = new HashSet<>();

    public static String[] getListOfBooks() {
        String[] result=new String[books.size()];
        return books.toArray(result);
    }

    public static String[] addBookToList(String newBook) {
        books.add(newBook);
        return getListOfBooks();
    }

    public static boolean isInList(String book) {
        return books.contains(book);
    }
}

