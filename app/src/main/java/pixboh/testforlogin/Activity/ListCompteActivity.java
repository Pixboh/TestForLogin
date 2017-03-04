package pixboh.testforlogin.Activity;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.ArrayList;

import pixboh.testforlogin.Adapter.AdapterPersonnalise;
import pixboh.testforlogin.Entity.Personne;
import pixboh.testforlogin.HelperSqllite.ContractDB;
import pixboh.testforlogin.HelperSqllite.SQLhelperSubClass;
import pixboh.testforlogin.R;
import pixboh.testforlogin.WebService.RetrofitBuilder;

/**
 * Created by PIXBOH on 09/02/2017.
 */

public class ListCompteActivity extends Activity {
    ArrayList<Personne> p;
RecyclerView recyclerView;
    SQLhelperSubClass helper;
    SQLiteDatabase bdd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.comptesdispo);
        helper=new SQLhelperSubClass(this);
        bdd=helper.getWritableDatabase();
        recyclerView=(RecyclerView)findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
         p =new ArrayList<>();
        Log.e("LIst",p.toString());
               remplirList(bdd);
        AdapterPersonnalise adapterPersonnalise=new AdapterPersonnalise(p);
        recyclerView.setAdapter(adapterPersonnalise);


    }
    public void remplirList(SQLiteDatabase bd){

        Cursor cursor=bd.rawQuery("SELECT * FROM "+ ContractDB.DBtable.NOM_TABLE,null);
        try{   if(cursor.moveToFirst()){
            while (!cursor.isAfterLast()){

                Personne personne=new Personne();
               personne.setNom(cursor.getString(cursor.getColumnIndex(ContractDB.DBtable.COLUMN_NOM)));
                personne.setEmail(cursor.getString(cursor.getColumnIndex(ContractDB.DBtable.COLUMN_EMAIL)));
             personne.setUsername(cursor.getString(cursor.getColumnIndex(ContractDB.DBtable.COLUMN_USERNAME)));
                personne.setMot_de_passe(cursor.getString(cursor.getColumnIndex(ContractDB.DBtable.COLUMN_PASSWORD)));
               personne.setPrenom(cursor.getString(cursor.getColumnIndex(ContractDB.DBtable.COLUMN_PRENOM)));
                personne.setNumero_tel(cursor.getString(cursor.getColumnIndex(ContractDB.DBtable.COLUMN_NUMERO)));
                p.add(personne);
                cursor.moveToNext();

            }
        }
        }finally {
            cursor.close();
        }
    }
}
