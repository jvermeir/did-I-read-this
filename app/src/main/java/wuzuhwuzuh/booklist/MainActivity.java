package wuzuhwuzuh.booklist;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import static wuzuhwuzuh.booklist.R.*;

public class MainActivity extends AppCompatActivity implements OnClickListener {
    private Button scanBtn;
    private TextView contentTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout.activity_main);

        scanBtn = (Button) findViewById(id.scan_button);
        contentTxt = (TextView) findViewById(id.textView2);
        scanBtn.setOnClickListener(this);
    }

    static final int DIALOG_REQUEST = 1;

    @Override
    public void onClick(View v) {
        if (v.getId() == id.scan_button) {
            ScannerIntentIntegrator scanIntegrator = createScannerIntentIntegrator(this);
            AlertDialog alertDialog = scanIntegrator.initiateScan();
            startActivityForResult(getScanIntent(), DIALOG_REQUEST);
        }
    }

    protected ScannerIntentIntegrator createScannerIntentIntegrator(MainActivity activity) {
        return new DummyScanner(activity);
    }

    protected Intent getScanIntent() {
        return getDummyIntent();
    }

    private Intent getDummyIntent() {
        Intent intent = new Intent();
        intent.setAction("wuzuhwuzuh.booklist.MainActivity.class");
        intent.putExtra("SCAN_RESULT", "123456789");
        intent.putExtra("SCAN_RESULT_FORMAT", "format");
        intent.putExtra("SCAN_RESULT_ERROR_CORRECTION_LEVEL", "x");
        setResult(Activity.RESULT_OK, intent);
        return intent;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        if (requestCode == DIALOG_REQUEST) {
            ScannerIntentResult scanningResult = ScannerIntentIntegrator.parseActivityResult(requestCode, resultCode, intent);
            if (scanningResult != null) {
                processResult(scanningResult);
            } else {
                Toast toast = Toast.makeText(getApplicationContext(),
                        "No scan data received!", Toast.LENGTH_SHORT);
                toast.show();
            }
        }
    }

    private void processResult (ScannerIntentResult scanningResult) {
        String scanContent = scanningResult.getContents();
        contentTxt.setText("CONTENT: " + scanContent);
        // find out if book is in list
        // if so -> alert: book already read
        // if not -> ask if book should be added to list
    }
}

class DummyScanner extends ScannerIntentIntegrator {
    public DummyScanner(Activity activity) {
        super(activity, false);
    }

    public AlertDialog initiateScan() {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);

        builder.setMessage(string.dialog_message)
                .setTitle(string.dialog_title);

        AlertDialog dialog = builder.create();
        dialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // Alert dialog action goes here
                        // onClick button code here
                        activity.getIntent().putExtra("result", "action");
                        dialog.dismiss();
                    }
                });
        dialog.show();
        return dialog;
    }
}

