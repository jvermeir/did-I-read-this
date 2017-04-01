package wuzuhwuzuh.did_i_read_this;

import android.app.Activity;
import android.os.Environment;
import android.widget.Toast;

import java.beans.IndexedPropertyChangeEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.Exchanger;

public class ListOfBooks {

    private static final Set<String> books = new HashSet<>();
    private static final String STORE_FILE_NAME = "books.txt";

    static {
        readBookStore();
    }

    public static String[] getListOfBooks() {
        String[] result = new String[books.size()];
        return books.toArray(result);
    }

    public static String[] addBookToList(String newBook, Activity activity) {
        books.add(newBook);
        writeBookStore(activity);
        return getListOfBooks();
    }

    public static boolean isInList(String book) {
        return books.contains(book);
    }

    private static void writeBookStore(Activity activity) {
        try {
            File file = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + '/' + STORE_FILE_NAME);
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

    private static void readBookStore() {
        try {
            File file = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + '/' + STORE_FILE_NAME);
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

