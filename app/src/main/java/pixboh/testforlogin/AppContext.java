package pixboh.testforlogin;

import android.app.Application;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by PIXBOH on 09/02/2017.
 */

public class AppContext extends Application {
    SQLhelperSubClass helper;
    SQLiteDatabase bdd;
    @Override
    public void onCreate() {
        super.onCreate();
        helper=new SQLhelperSubClass(this);
        bdd=helper.getWritableDatabase();



    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }
}
