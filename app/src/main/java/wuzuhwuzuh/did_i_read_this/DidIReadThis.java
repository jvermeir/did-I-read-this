package wuzuhwuzuh.did_i_read_this;

import android.app.Application;
import android.content.Context;

public class DidIReadThis extends Application {

    private static Context context;

    public void onCreate() {
        super.onCreate();
        DidIReadThis.context = getApplicationContext();
    }

    public static Context getAppContext() {
        return DidIReadThis.context;
    }
}
