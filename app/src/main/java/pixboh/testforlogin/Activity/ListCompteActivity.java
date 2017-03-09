package pixboh.testforlogin.Activity;

import android.app.Activity;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import pixboh.testforlogin.Adapter.AdapterPersonnalise;
import pixboh.testforlogin.Entity.Personne;
import pixboh.testforlogin.HelperSqllite.SQLhelperSubClass;
import pixboh.testforlogin.R;
import pixboh.testforlogin.WebService.RetrofitBuilder;
import retrofit2.Response;

/**
 * Created by PIXBOH on 09/02/2017.
 */

public class ListCompteActivity extends Activity {
    ArrayList<Personne> pList;
RecyclerView recyclerView;
    SQLhelperSubClass helper;
    SQLiteDatabase bdd;
    AdapterPersonnalise adapterPersonnalise;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.comptesdispo);
        helper=new SQLhelperSubClass(this);
        bdd=helper.getWritableDatabase();
        recyclerView=(RecyclerView)findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        pList =new ArrayList<>();
        new FillRecycleviewOnBackGround().execute();


//        On charge notre liste depuis la base de donnee heberge et non avec Sqllite
       /*

        Log.e("LIst", pList.toString());
              //remplirList(bdd);
         adapterPersonnalise=new AdapterPersonnalise(pList);
       recyclerView.setAdapter(adapterPersonnalise);*/


    }

    class FillRecycleviewOnBackGround extends AsyncTask<Void,Void,List<Personne>>{

        @Override
        protected List<Personne> doInBackground(Void... params) {
            try {
                Response<List<Personne>> response = RetrofitBuilder.createService().getListPersonne().execute();
                if(response.code()==200){
                    return response.body();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(List<Personne> personnes) {
            super.onPostExecute(personnes);
            pList.addAll(personnes);
            adapterPersonnalise = new AdapterPersonnalise(pList);
            recyclerView.setAdapter(adapterPersonnalise);

        }
    }











    /*    public void remplirList(SQLiteDatabase bd){

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
                pList.add(personne);
                cursor.moveToNext();

            }
        }
        }finally {
            cursor.close();
        }
    }*/




}
