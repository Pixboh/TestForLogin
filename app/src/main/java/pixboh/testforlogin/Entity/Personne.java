package pixboh.testforlogin.Entity;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by PIXBOH on 09/02/2017.
 */

public class Personne implements Parcelable {
    private String prenom;
    private String nom;
    private String numero_tel;
    private String mot_de_passe;
    private String email;
    private String username;
    private String imageid;

    public Personne(String prenom, String nom, String numero_tel, String mot_de_passe, String email, String username, String imageid) {
        this.prenom = prenom;
        this.nom = nom;
        this.numero_tel = numero_tel;
        this.mot_de_passe = mot_de_passe;
        this.email = email;
        this.username = username;
        this.imageid = imageid;
    }

    public Personne(String prenom, String email, String mot_de_passe, String numero_tel, String nom, String username) {
        this.prenom = prenom;
        this.email = email;
        this.mot_de_passe = mot_de_passe;
        this.numero_tel = numero_tel;
        this.nom = nom;
        this.username=username;

    }

    public Personne() {
    }

    protected Personne(Parcel in) {
        prenom = in.readString();
        nom = in.readString();
        numero_tel = in.readString();
        mot_de_passe = in.readString();
        email = in.readString();
        username = in.readString();
        imageid = in.readString();
    }

    public static final Creator<Personne> CREATOR = new Creator<Personne>() {
        @Override
        public Personne createFromParcel(Parcel in) {
            return new Personne(in);
        }

        @Override
        public Personne[] newArray(int size) {
            return new Personne[size];
        }
    };

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Personne(String prenom, String nom, String numero_tel) {
        this.prenom = prenom;
        this.nom = nom;
        this.numero_tel = numero_tel;
    }

    public String getImageid() {
        return imageid;
    }

    public void setImageid(String imageid) {
        this.imageid = imageid;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMot_de_passe() {
        return mot_de_passe;
    }

    public void setMot_de_passe(String mot_de_passe) {
        this.mot_de_passe = mot_de_passe;
    }

    public String getNumero_tel() {
        return numero_tel;
    }

    public void setNumero_tel(String numero_tel) {
        this.numero_tel = numero_tel;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(prenom);
        dest.writeString(nom);
        dest.writeString(numero_tel);
        dest.writeString(mot_de_passe);
        dest.writeString(email);
        dest.writeString(username);
        dest.writeString(imageid);
    }
}
