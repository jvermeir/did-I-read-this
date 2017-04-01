package wuzuhwuzuh.did_i_read_this;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public static final String BARCODE_VALUE = "barcode_value";
    private EditText barcodeText = null;
    public static final int GET_A_BARCODE = 1;
    private Class barcodeActivityClass = GetBarcodeTestActivity.class;
//    private Class barcodeActivityClass = GetBarcodeActivity.class;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setBarcodeActivityClass(barcodeActivityClass);
        setContentView(R.layout.activity_main);
        barcodeText = (EditText) findViewById(R.id.editText);
    }

    public void setBarcodeActivityClass(Class barcodeActivityClass) {
        this.barcodeActivityClass = barcodeActivityClass;
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case GET_A_BARCODE:
                if (resultCode == RESULT_OK) {
                    barcodeText.setText(getBarcodeFromIntent(data));
                    if (bookInList(barcodeText.getText().toString())) {
                        Toast.makeText(this, "Book is in list already", Toast.LENGTH_LONG).show();
                    }
                }
                break;
        }
    }

    private String getBarcodeFromIntent(Intent data) {
        String barcode = null;
        Bundle extras = data.getExtras();
        if (extras != null) {
            barcode = extras.get(MainActivity.BARCODE_VALUE).toString();
        }
        return barcode;
    }

    public void getBarcode(View view) {
        Intent intent = new Intent(this, barcodeActivityClass);
        startActivityForResult(intent, GET_A_BARCODE);
    }

    public void showList(View view) {
        Intent intent = new Intent(this, ListViewLoaderActivity.class);
        startActivity(intent);
    }

    public void processBarcode(View view) {
        String barcode = barcodeText.getText().toString();
        if (bookInList(barcode)) {
            processExistingBook();
        } else {
            processNewBook(barcode);
        }
        Intent intent = new Intent(this, ListViewLoaderActivity.class);
        startActivity(intent);
    }

    private void processNewBook(String barcode) {
        ListOfBooks.addBookToList(barcode, this);
    }

    private void processExistingBook() {
        Toast.makeText(this, "Book is in list already", Toast.LENGTH_LONG).show();
        // TODO disable add book button
    }

    private boolean bookInList(String book) {
        return ListOfBooks.isInList(book);
    }

}
