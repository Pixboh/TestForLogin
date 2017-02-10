package pixboh.testforlogin;

/**
 * Created by PIXBOH on 09/02/2017.
 */

public class Personne  {
    private String prenom;
    private String nom;
    private String numero_tel;
    private String mot_de_passe;
    private String email;
    public String username;

    public Personne(String prenom, String email, String mot_de_passe, String numero_tel, String nom,String username) {
        this.prenom = prenom;
        this.email = email;
        this.mot_de_passe = mot_de_passe;
        this.numero_tel = numero_tel;
        this.nom = nom;
        this.username=username;
    }

    public Personne() {
    }

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

}
