package wuzuhwuzuh.did_i_read_this;

import android.app.Activity;
import android.app.ListActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;

public class BookListActivity extends ListActivity {
    ArrayAdapter<String> mAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String[] books = BookStore.getListOfBooks(this);
        final Activity bookListActivity = this;
        mAdapter = new ArrayAdapter<>(this, R.layout.list_item, R.id.label, new ArrayList<String>(Arrays.asList(books)));
        this.setListAdapter(mAdapter);
        ListView listView = getListView();
        SwipeDismissListViewTouchListener touchListener =
                new SwipeDismissListViewTouchListener(
                        listView,
                        new SwipeDismissListViewTouchListener.DismissCallbacks() {
                            @Override
                            public boolean canDismiss(int position) {
                                return true;
                            }

                            @Override
                            public void onDismiss(ListView listView, int[] reverseSortedPositions) {
                                for (int position : reverseSortedPositions) {
                                    String itemToRemove = mAdapter.getItem(position);
                                    mAdapter.remove(itemToRemove);
                                    BookStore.removeBookFromList(itemToRemove, bookListActivity);
                                }
                                mAdapter.notifyDataSetChanged();
                            }
                        });
        listView.setOnTouchListener(touchListener);
        listView.setOnScrollListener(touchListener.makeScrollListener());
    }

}