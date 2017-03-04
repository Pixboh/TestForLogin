package pixboh.testforlogin.WebService;

import java.util.List;

import pixboh.testforlogin.Entity.Personne;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by PIXBOH on 04/03/2017.
 */

public interface RequestInterface {
@GET("listdepersonnes.php")
    Call<List<Personne>> getListPersonne();
//    Recuperer une personne par son id
@POST("searchbyid.php")
    Call<Personne> getPersonneById(@Query("id") int id);

//    Supprimer une personne par son id
@POST("deletepersonnne.php")
    Call<Boolean> deleteById(@Query("id")  int id);

//    Requete pour ajouter une personne
    @POST("addpersonnne.php")
    Call<Boolean> addNewPersonne(@Query("newuser") Personne personne);

//    Creation de la table personne



}
