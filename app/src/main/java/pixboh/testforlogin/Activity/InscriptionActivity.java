package pixboh.testforlogin.Activity;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import pixboh.testforlogin.Entity.Personne;
import pixboh.testforlogin.HelperSqllite.ContractDB;
import pixboh.testforlogin.HelperSqllite.SQLhelperSubClass;
import pixboh.testforlogin.R;
import pixboh.testforlogin.WebService.RetrofitBuilder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by PIXBOH on 09/02/2017.
 */

public class InscriptionActivity extends Activity {
    Animation anim;
    SQLhelperSubClass helper;
    SQLiteDatabase bdd;
    private EditText editTextNom,editTextPrenom,editTextUsername,editTextPassword,editTextConfPassword,editTextEmail,editTextNumero;
    private Button buttonValider,buttonRetourner,buttonImageclient;
    public static int REQUEST_CODE_TO_GET_IMAGE=5;

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
        buttonImageclient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intnt=new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intnt,REQUEST_CODE_TO_GET_IMAGE);
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
        buttonImageclient=(Button)findViewById(R.id.imageid);

     }

    public Personne nouveauInscrit(){
        String[] mDonnees={
                editTextPrenom.getText().toString(),
                editTextNom.getText().toString(),
                editTextUsername.getText().toString(),
                editTextEmail.getText().toString(),
                editTextNumero.getText().toString(),
                editTextPassword.getText().toString(),
                editTextConfPassword.getText().toString()};
        int errorTest =0;
        String passwordTest;
               for(int i=0;i<mDonnees.length;i++) {

                   if (mDonnees[i].isEmpty() && i != 4) {
                       errorTest++;
                   }
               }
                   if(errorTest ==1){

                       Toast.makeText(this,"Ce champ ne peut etre vide",Toast.LENGTH_SHORT).show();  return null;}
                   if(errorTest >1){ Toast.makeText(this,"Ces champs ne peuvent etre vide",Toast.LENGTH_SHORT).show();   return null;}
                   if(!mDonnees[5].equals(mDonnees[6])){
                       anim= AnimationUtils.loadAnimation(InscriptionActivity.this,R.anim.errorentry);

                       editTextPassword.startAnimation(anim);
                       editTextPassword.startAnimation(anim);
                       Toast.makeText(this,"Les mots de passe doivent correspondre",Toast.LENGTH_SHORT).show();
                       return null;
                   }else{
                       passwordTest =mDonnees[5];
                   }
                   if(mDonnees[3].contains("@")==false && mDonnees[3].contains(".")==false){
                       anim= AnimationUtils.loadAnimation(InscriptionActivity.this,R.anim.errorentry);
                       editTextEmail.startAnimation(anim);
                       Toast.makeText(this,"L'email n'est pas valide.",Toast.LENGTH_SHORT).show();
                       return null;
                   }
        return new Personne(mDonnees[0],mDonnees[3], passwordTest,mDonnees[4],mDonnees[5],mDonnees[2]);
    }

    class valideinscription implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            if(nouveauInscrit()!=null){
            ContractDB.ajouterPersonne(bdd,nouveauInscrit());
                RetrofitBuilder.createService().addNewPersonne(nouveauInscrit()).enqueue(new Callback<Boolean>() {

                    @Override
                    public void onResponse(Call<Boolean> call, Response<Boolean> response) {

                        if(response.code()==200){
                            Log.e("coool","cool");
                        }
                    }

                    @Override
                    public void onFailure(Call<Boolean> call, Throwable t) {
                       t.printStackTrace();
                    }
                });


            }

        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==REQUEST_CODE_TO_GET_IMAGE && resultCode==RESULT_OK && data!=null){
            Uri selectedImage = data.getData();
            String[] filepathcolumn={MediaStore.Images.Media.DATA};
            Cursor cursor=getContentResolver().query(selectedImage,filepathcolumn,null,null,null);
            int columnIndex=cursor.getColumnIndex(filepathcolumn[0]);
            String picturePath=cursor.getString(columnIndex);
            cursor.close();




        }
    }

}
