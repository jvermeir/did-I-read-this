package wuzuhwuzuh.did_i_read_this;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;

public class ListViewLoaderActivity extends ListActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        addBookToList();
        this.setListAdapter(new ArrayAdapter<>(this, R.layout.list_item, R.id.label, ListOfBooks.getListOfBooks()));
    }

    private void addBookToList() {
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String barcode = extras.get(MainActivity.BARCODE_VALUE).toString();
            if (barcode != null) {
                ListOfBooks.addBookToList(barcode);
            }
        }
    }

}