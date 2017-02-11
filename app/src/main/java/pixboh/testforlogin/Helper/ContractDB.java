package pixboh.testforlogin.Helper;

import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;

import pixboh.testforlogin.Entity.Personne;

/**
 * Created by PIXBOH on 09/02/2017.
 */

public class ContractDB {
    private ContractDB(){

    }

    public static void ajouterPersonne(SQLiteDatabase bdd, Personne p){
        bdd.execSQL("INSERT INTO "+ ContractDB.DBtable.NOM_TABLE+" VALUES (null, '"+p.getPrenom()+"' ,'"+p.getNom()+"', '"+p.getNumero_tel()+"' ,'"+p.getEmail()+"' " +
                " ,'"+p.getMot_de_passe()+"' , '"+p.getUsername()+"' )");

    }

    public static class  DBtable implements BaseColumns {
        public static String NOM_TABLE="personnes";
        public static String COLUMN_NOM="noms";
        public static String COLUMN_PRENOM="prenoms";
        public static String COLUMN_NUMERO="numeros";
        public static String COLUMN_USERNAME="username";
        public static String COLUMN_EMAIL="emails";
        public static String COLUMN_PASSWORD="password";
        public static  String CREATE_TABLE="CREATE TABLE  " +
                NOM_TABLE+" (" +
                DBtable._ID+" INTEGER PRIMARY KEY AUTOINCREMENT,  " +
                COLUMN_PRENOM+" TEXT NOT NULL, " +
                COLUMN_NOM+" TEXT NOT NULL, " +
                COLUMN_NUMERO+" TEXT , " +
                COLUMN_EMAIL+" TEXT , " +
                COLUMN_PASSWORD+" TEXT , " +
                COLUMN_USERNAME+" TEXT )";

    }
}
