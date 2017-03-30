package wuzuhwuzuh.did_i_read_this;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class GetBarcodeTestActivity extends GetBarcodeActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.scan) {
            onActivityResult(1, 2, new Intent());
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        processScanningResult(intent, "3600029145");
        finish();
    }
}
