package wuzuhwuzuh.did_i_read_this;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    public static final String EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setDisplayMessageActivityClass(TestDisplayMessageActivity.class);
        setContentView(R.layout.activity_main);
    }

    private Class displayMessageActivityClass = TestDisplayMessageActivity.class;

    public void setDisplayMessageActivityClass(Class displayMessageActivityClass) {
        this.displayMessageActivityClass = displayMessageActivityClass;
    }

    public void sendMessage(View view) {
        Intent intent = new Intent(this, displayMessageActivityClass);
        EditText editText = (EditText) findViewById(R.id.editText);
        String message = editText.getText().toString();
        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);
    }

    public void showList(View view) {
        Intent intent = new Intent(this, ListViewLoaderActivity.class);
        startActivity(intent);
    }

}
