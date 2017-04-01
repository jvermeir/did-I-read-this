package wuzuhwuzuh.did_i_read_this;

import android.app.Activity;
import android.os.Environment;
import android.util.ArraySet;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.HashSet;
import java.util.Set;

public class BookStore {

    private static Set<String> books = null;
    private static final String STORE_FILE_NAME = "books.txt";

    public static String[] getListOfBooks(Activity activity) {
        if (books == null) {
            readBookStore(activity);
        }
        String[] result = new String[books.size()];
        return books.toArray(result);
    }

    public static String[] removeBookFromList(String bookToDelete, Activity activity) {
        if (books == null) {
            books = new ArraySet<>();
        } else {
            books.remove(bookToDelete);
        }
        writeBookStore(activity);
        return getListOfBooks(activity);
    }

    public static String[] addBookToList(String newBook, Activity activity) {
        if (books == null) {
            readBookStore(activity);
        }
        books.add(newBook);
        writeBookStore(activity);
        return getListOfBooks(activity);
    }

    public static boolean isInList(String book) {
        return books.contains(book);
    }

    private static void writeBookStore(Activity activity) {
        try {
            File file = new File(activity
                    .getApplicationContext().getFileStreamPath(STORE_FILE_NAME)
                    .getPath());
            FileOutputStream fos = new FileOutputStream(file);
            try {
                for (String book : books) {
                    fos.write((book + "\n").getBytes());
                }
            } finally {
                fos.close();
            }
        } catch (Exception e) {
            Toast.makeText(activity, "Adding book failed", Toast.LENGTH_SHORT).show();
        }
    }

    private static void readBookStore(Activity activity) {
        books = new ArraySet<>();
        try {
            File file = new File(activity
                    .getApplicationContext().getFileStreamPath(STORE_FILE_NAME)
                    .getPath());
            if (file.exists()) {
                int length = (int) file.length();
                byte[] bytes = new byte[length];
                FileInputStream in = new FileInputStream(file);
                try {
                    in.read(bytes);
                } finally {
                    in.close();
                }
                String contents = new String(bytes);
                String[] lines = contents.split("\n");
                for (String line : lines) {
                    books.add(line);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

