package wuzuhwuzuh.did_i_read_this;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class GetBarcodeTestActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView scanResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_barcode);
        scanResult = (TextView) findViewById(R.id.scanResult);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.scan) {
            onActivityResult(1,2,new Intent());
        }
    }

    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        String scanContent = "3600029145";
        scanResult.setText("CONTENT: " + scanContent);
        Toast toast = Toast.makeText(getApplicationContext(),
                "dummy scanner says: "+scanContent, Toast.LENGTH_SHORT);
        toast.show();
        Bundle extras = new Bundle();
        extras.putString("content", scanContent);
        intent.putExtras(extras);
        setResult(RESULT_OK, intent);
        finish();
    }

}
