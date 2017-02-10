package pixboh.testforlogin;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by PIXBOH on 09/02/2017.
 */

public class SQLhelperSubClass extends SQLiteOpenHelper {
    public static int VERSION=1;
    public static String DATABASE_NAME="personnes.db";



    public SQLhelperSubClass(Context context) {

        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(ContractDB.DBtable.CREATE_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE  "+ ContractDB.DBtable.NOM_TABLE);
        onCreate(db);

    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db,newVersion,oldVersion);
    }
}
