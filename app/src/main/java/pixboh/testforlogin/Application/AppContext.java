package pixboh.testforlogin.Application;

import android.app.Application;
import android.util.Log;

/**
 * Created by PIXBOH on 04/03/2017.
 */

public class AppContext extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Log.e("LOg de ","DEMARRAGE DE L'APPLICATION");
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        Log.e("LOg de ","ARRET DE L'APPLICATION");
    }
}
