package wuzuhwuzuh.did_i_read_this;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import static wuzuhwuzuh.did_i_read_this.MainActivity.BARCODE_VALUE;

public class GetBarcodeActivity extends AppCompatActivity implements View.OnClickListener {

    protected final boolean testmode = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_barcode);
        if (!testmode) {
            IntentIntegrator scanIntegrator = new IntentIntegrator(this);
            scanIntegrator.initiateScan();
        }
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.scanButton) {
            IntentIntegrator scanIntegrator = new IntentIntegrator(this);
            scanIntegrator.initiateScan();
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        IntentResult scanningResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, intent);
        if (scanningResult != null) {
            String scanContent = scanningResult.getContents();
            processScanResult(intent, scanContent);
        } else {
            Toast toast = Toast.makeText(getApplicationContext(), "No scan data received!", Toast.LENGTH_SHORT);
            toast.show();
        }
    }

    protected void processScanResult(Intent intent, String scanContent) {
        Bundle extras = new Bundle();
        extras.putString(BARCODE_VALUE, scanContent);
        intent.putExtras(extras);
        setResult(RESULT_OK, intent);
        finish();
    }
}
