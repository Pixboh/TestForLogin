package pixboh.testforlogin;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    private EditText editTextUsername;
    private EditText editTextPassword;
    private Button buttonConection;
    private Button buttonInscrire;
    SQLhelperSubClass helper;
    SQLiteDatabase bdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        helper=new SQLhelperSubClass(this);
        bdd=helper.getWritableDatabase();
        findMyViews();
        buttonInscrire.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,InscriptionActivity.class));
            }
        });
        buttonConection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,ListCompteActivity.class));
            }
        });
    }
    public void findMyViews(){
        editTextPassword=(EditText)findViewById(R.id.editTextInsPassword);
        editTextUsername=(EditText) findViewById(R.id.edittextusername);
        buttonConection=(Button)findViewById(R.id.buttonLogin);
        buttonInscrire=(Button)findViewById(R.id.buttonInscrire);

    }
}
