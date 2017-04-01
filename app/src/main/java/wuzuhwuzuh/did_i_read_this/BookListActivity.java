package wuzuhwuzuh.did_i_read_this;

import android.app.ListActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;

public class BookListActivity extends ListActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setListAdapter(new ArrayAdapter<>(this, R.layout.list_item, R.id.label, BookStore.getListOfBooks(this)));
    }

}