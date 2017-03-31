package wuzuhwuzuh.did_i_read_this;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class GetBarcodeTestActivity extends GetBarcodeActivity {

    protected final boolean testmode=true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        onActivityResult(1, 2, new Intent());
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.scanButton) {
            onActivityResult(1, 2, new Intent());
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        processScanResult(intent, "123");
        finish();
    }
}
