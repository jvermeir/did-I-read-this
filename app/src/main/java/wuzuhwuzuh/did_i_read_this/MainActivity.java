package wuzuhwuzuh.did_i_read_this;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    public static final String EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE";
    private EditText editText = null;
    public static final int GET_A_BARCODE = 1;
    // Use test version by default to avoid dependency on scanner
    private Class barcodeActivityClass = GetBarcodeActivity.class;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setBarcodeActivityClass(barcodeActivityClass);
        setContentView(R.layout.activity_main);
        editText = (EditText) findViewById(R.id.editText);
    }

    public void setBarcodeActivityClass(Class barcodeActivityClass) {
        this.barcodeActivityClass = barcodeActivityClass;
    }

    public void sendMessage(View view) {
        Intent intent = new Intent(this, barcodeActivityClass);
        startActivityForResult(intent, GET_A_BARCODE);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case GET_A_BARCODE:
                if (resultCode == RESULT_OK) {
                    Bundle res = data.getExtras();
                    // TODO extract constant
                    String result = res.getString("content");
                    editText.setText(result);
                }
                break;
        }
    }

    public void showList(View view) {
        Intent intent = new Intent(this, ListViewLoaderActivity.class);
        startActivity(intent);
    }
}
