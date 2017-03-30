package wuzuhwuzuh.did_i_read_this;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class GetBarcodeActivity extends AppCompatActivity implements View.OnClickListener {

    protected TextView scanResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_barcode);
        scanResult = (TextView) findViewById(R.id.scanResult);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.scan) {
            IntentIntegrator scanIntegrator = new IntentIntegrator(this);
            scanIntegrator.initiateScan();
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        IntentResult scanningResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, intent);
        if (scanningResult != null) {
            String scanContent = scanningResult.getContents();
            processScanningResult(intent, scanContent);
        } else {
            Toast toast = Toast.makeText(getApplicationContext(), "No scan data received!", Toast.LENGTH_SHORT);
            toast.show();
        }
    }

    protected void processScanningResult(Intent intent, String scanContent) {
            scanResult.setText("CONTENT: " + scanContent);
            Bundle extras = new Bundle();
            extras.putString("content", scanContent);
            intent.putExtras(extras);
            setResult(RESULT_OK, intent);
            finish();
    }
}
