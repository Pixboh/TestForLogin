package pixboh.testforlogin.Activity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.IOException;
import java.util.List;

import pixboh.testforlogin.Entity.Personne;
import pixboh.testforlogin.HelperSqllite.SQLhelperSubClass;
import pixboh.testforlogin.R;
import pixboh.testforlogin.WebService.RetrofitBuilder;

public class MainActivity extends AppCompatActivity {
    SQLhelperSubClass helper;
    SQLiteDatabase bdd;
    private EditText editTextUsername;
    private EditText editTextPassword;
    private Button buttonConection;
    private Button buttonInscrire;



    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.e("LOg de ", "DEMARRAGE DE L'ACTIVITE MAINACTIVITY");
        helper = new SQLhelperSubClass(this);
        bdd = helper.getWritableDatabase();
        findMyViews();

        buttonInscrire.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(new Intent(MainActivity.this, InscriptionActivity.class));

                    }
                });
        buttonConection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //On recupere les donné qu'on donne en parametre a l'AsychTask
                new CheckLogged().execute(editTextUsername.getText().toString(),editTextPassword.getText().toString());


            }
        });
    }
// Lier l'interface graphique avec le code java
    public void findMyViews() {
        editTextPassword = (EditText) findViewById(R.id.edittextpassword);
        editTextUsername = (EditText) findViewById(R.id.edittextusername);
        buttonConection = (Button) findViewById(R.id.buttonLogin);
        buttonInscrire = (Button) findViewById(R.id.buttonInscrire);


    }


    @Override
    protected void onStop() {
        super.onStop();
        RetrofitBuilder.createService().getListPersonne().cancel();

    }

    // Verifie l'identifiant et le  mot de passe saisies par l'utilisateur en arriere plan avec un AsynchTask
    class CheckLogged extends AsyncTask<String, Void, Integer> {
        //Identifiant pour savoir le type d'erreur renvoyé
        private static final int WRONG_USERNAME_CODE=2;
        private static final int WRONG_PASSWORD_CODE =3;
        private static final int CORRECT_LOGIN_CODE=1;


        @Override
        protected Integer doInBackground(String... params) {
            try {
                List<Personne> personneList = RetrofitBuilder.createService().getListPersonne().execute().body();
                for (int i = 0; i < personneList.size(); i++) {
                    if (params[0].equalsIgnoreCase(personneList.get(i).getUsername()) && params[1].equalsIgnoreCase(personneList.get(i).getMot_de_passe())) {
                        return CORRECT_LOGIN_CODE;
                    } else if (params[0].equalsIgnoreCase(personneList.get(i).getUsername())) {
                        return WRONG_USERNAME_CODE;
                    }
                 if (params[1].equalsIgnoreCase(personneList.get(i).getMot_de_passe())) {
                        return WRONG_PASSWORD_CODE;
                    }

                }
            } catch (IOException e1) {
                e1.printStackTrace();
            }

           return null;
        }

        @Override
        protected void onPostExecute(Integer integer) {
            super.onPostExecute(integer);
//            Gestion des erreurs renvoyé par le background thread!!
            if(integer==1) {
                startActivity(new Intent(MainActivity.this, ListCompteActivity.class));
                editTextPassword.setText("");
                editTextUsername.setText("");
                finish();
            }
            if(integer==WRONG_USERNAME_CODE){
                editTextUsername.startAnimation(AnimationUtils.loadAnimation(MainActivity.this, R.anim.errorentry));
                Toast.makeText(MainActivity.this, "Username Incorrect?", Toast.LENGTH_SHORT).show();

            }
            if(integer== WRONG_PASSWORD_CODE){
                editTextPassword.startAnimation(AnimationUtils.loadAnimation(MainActivity.this, R.anim.errorentry));
                Toast.makeText(MainActivity.this, "Mot de passe Incorrect?", Toast.LENGTH_SHORT).show();

            }
        }
    }
}





//    TEST DE CONNEXION AVEC LE SQLLITE
    /*    public boolean testlog(SQLiteDatabase bdd) {
        String username_test = String.valueOf(editTextUsername.getEditableText());
        String password_test = String.valueOf(editTextPassword.getEditableText());


        Cursor cursor = bdd.rawQuery("SELECT * FROM " + ContractDB.DBtable.NOM_TABLE, null);
        try {
            if (cursor.moveToFirst()) {
                while (!cursor.isAfterLast()) {
                    if (cursor.getString(cursor.getColumnIndex(ContractDB.DBtable.COLUMN_USERNAME)).equals(username_test)) {
                        if (cursor.getString(cursor.getColumnIndex(ContractDB.DBtable.COLUMN_PASSWORD)).equals(password_test)) {
                            cursor.close();
                            return true;
                        } else {

                            editTextPassword.startAnimation(AnimationUtils.loadAnimation(this, R.anim.errorentry));
                            Toast.makeText(this, "Mot de passe Incorrect?", Toast.LENGTH_SHORT).show();
                            return false;
                        }
                    }
                      cursor.moveToNext();
                }
            }

        } finally {
            cursor.close();
        }
        editTextUsername.startAnimation(AnimationUtils.loadAnimation(this, R.anim.errorentry));
        Toast.makeText(this, "Username Incorrect?", Toast.LENGTH_SHORT).show();
        return false;
    }*/



