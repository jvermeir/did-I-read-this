package wuzuhwuzuh.did_i_read_this;

import android.app.ListActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;

public class ListViewLoaderActivity extends ListActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String[] list_items = getResources().getStringArray(R.array.list_items);

        this.setListAdapter(new ArrayAdapter<String>(this, R.layout.list_item, R.id.label, list_items));

    }
}