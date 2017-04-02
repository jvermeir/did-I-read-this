package wuzuhwuzuh.did_i_read_this;

import android.app.Activity;
import android.content.Context;
import android.os.Environment;
import android.util.ArraySet;
import android.widget.Toast;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.HashSet;
import java.util.Set;

import static android.content.Context.*;
import static android.content.Context.MODE_PRIVATE;

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

    public static boolean isInList(String book, Activity activity) {
        if (books == null) {
            readBookStore(activity);
        }
        return books.contains(book);
    }

    private static void writeBookStore(Activity activity) {
        OutputStream os = null;
        try {
            os = DidIReadThis.getAppContext().openFileOutput(STORE_FILE_NAME, MODE_PRIVATE);
            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(os));
            for (String book : books) {
                out.write(book + "\n");
            }
            out.flush();
            out.close();
        } catch (Exception e) {
            Toast.makeText(activity, "Adding book failed", Toast.LENGTH_SHORT).show();
        } finally {
            try {
                os.close();
            } catch (IOException e) {
                Toast.makeText(activity, "Adding book failed", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private static void readBookStore(Activity activity) {
        books = new ArraySet<>();
        InputStream is = null;
        try {
            is = DidIReadThis.getAppContext().openFileInput(STORE_FILE_NAME);
            int length = is.available();
            if (length > 0) {
                byte[] bytes = new byte[length];
                try {
                    is.read(bytes);
                } finally {
                    is.close();
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

