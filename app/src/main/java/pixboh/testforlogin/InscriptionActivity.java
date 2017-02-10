package pixboh.testforlogin;

import android.app.Activity;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by PIXBOH on 09/02/2017.
 */

public class InscriptionActivity extends Activity {
    private EditText editTextNom,editTextPrenom,editTextUsername,editTextPassword,editTextConfPassword,editTextEmail,editTextNumero;
    private Button buttonValider,buttonRetourner;
    Animation anim;
    SQLhelperSubClass helper;
    SQLiteDatabase bdd;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signuplayout);
        helper=new SQLhelperSubClass(this);
        bdd=helper.getWritableDatabase();
        findMyViews();
        anim= AnimationUtils.loadAnimation(InscriptionActivity.this,R.anim.errorentry);
        buttonValider.setOnClickListener(new valideinscription());
        buttonRetourner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
    public void findMyViews(){
        editTextNom=(EditText)findViewById(R.id.editTextnom);
        editTextEmail=(EditText)findViewById(R.id.editTextInsEmail);
        editTextPrenom=(EditText)findViewById(R.id.editTextInsPreNom);
        editTextNumero=(EditText)findViewById(R.id.editTextinsNumero);
        editTextPassword=(EditText)findViewById(R.id.editTextInsPassword);
        editTextConfPassword=(EditText)findViewById(R.id.editTextInsConfPassword);
        editTextUsername=(EditText)findViewById(R.id.editTextInsUsername);
        buttonRetourner=(Button)findViewById(R.id.buttonInsRetournee);
        buttonValider=(Button)findViewById(R.id.buttonInsValide);
        buttonValider.setOnClickListener(new valideinscription());

     }
    class valideinscription implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            if(nouveauInscrit()!=null){
            ContractDB.ajouterPersonne(bdd,nouveauInscrit());}

        }
    }
    public  Personne nouveauInscrit(){
        String[] mDonnees={
                editTextPrenom.getText().toString(),
                editTextNom.getText().toString(),
                editTextUsername.getText().toString(),
                editTextEmail.getText().toString(),
                editTextNumero.getText().toString(),
                editTextPassword.getText().toString(),
                editTextConfPassword.getText().toString()};
        int compteur=0;
        String password;
               for(int i=0;i<mDonnees.length;i++) {

                   if (mDonnees[i].isEmpty() && i != 4) {
                       compteur++;
                   }
               }
                   if(compteur==1){

                       Toast.makeText(this,"Ce champ ne peut etre vide",Toast.LENGTH_SHORT).show();  return null;}
                   if(compteur>1){ Toast.makeText(this,"Ces champs ne peuvent etre vide",Toast.LENGTH_SHORT).show();   return null;}
                   if(!mDonnees[5].equals(mDonnees[6])){
                       anim= AnimationUtils.loadAnimation(InscriptionActivity.this,R.anim.errorentry);

                       editTextPassword.startAnimation(anim);
                       editTextPassword.startAnimation(anim);
                       Toast.makeText(this,"Les mots de passe doivent correspondre",Toast.LENGTH_SHORT).show();
                       return null;
                   }else{
                       password=mDonnees[5];
                   }
                   if(mDonnees[3].contains("@")==false && mDonnees[3].contains(".")==false){
                       anim= AnimationUtils.loadAnimation(InscriptionActivity.this,R.anim.errorentry);
                       editTextEmail.startAnimation(anim);
                       Toast.makeText(this,"L'email n'est pas valide.",Toast.LENGTH_SHORT).show();
                       return null;
                    }

                  

                return new Personne(mDonnees[0],mDonnees[3],password,mDonnees[4],mDonnees[5],mDonnees[2]);
    }
}