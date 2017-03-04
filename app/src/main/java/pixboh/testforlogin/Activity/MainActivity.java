package pixboh.testforlogin.Activity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

import pixboh.testforlogin.Entity.Personne;
import pixboh.testforlogin.HelperSqllite.ContractDB;
import pixboh.testforlogin.HelperSqllite.SQLhelperSubClass;
import pixboh.testforlogin.R;
import pixboh.testforlogin.WebService.RetrofitBuilder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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
        debugging();
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

                if (testlog(bdd)) {
                    startActivity(new Intent(MainActivity.this, ListCompteActivity.class));
                   editTextUsername.setText("");
                    editTextPassword.setText("");
                }

            }
        });
    }

    public void findMyViews() {
        editTextPassword = (EditText) findViewById(R.id.edittextpassword);
        editTextUsername = (EditText) findViewById(R.id.edittextusername);
        buttonConection = (Button) findViewById(R.id.buttonLogin);
        buttonInscrire = (Button) findViewById(R.id.buttonInscrire);


    }

    public boolean testlog(SQLiteDatabase bdd) {
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
    }




    void debugging(){

            RetrofitBuilder.createService().getListPersonne().enqueue(new Callback<List<Personne>>() {
                @Override
                public void onResponse(Call<List<Personne>> call, Response<List<Personne>> response) {
                    Log.e("nom : ",response.body().get(0).getNom());
                }

                @Override
                public void onFailure(Call<List<Personne>> call, Throwable t) {
                    t.printStackTrace();

                }
            });




    }
}
