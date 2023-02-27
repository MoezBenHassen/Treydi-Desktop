package Entities;

public class Utilisateur {
    static int loginid = 0 ;
    static int logintype = 0 ;

    public static int getLoginid() {
        return loginid;
    }

    public static void setLoginid(int x) {
        loginid = x;
    }

    public static int getLogintype() {
        return logintype;
    }

    public static void setLogintype(int x) {
        logintype = x;
    }

    private int id_user;
    private String nom, prenom, avatar_url, email, adresse;

    public Utilisateur() {
    }

    public Utilisateur(String nom, String prenom, String avatar_url, String email, String adresse) {
        this.nom = nom;
        this.prenom = prenom;
        this.avatar_url = avatar_url;
        this.email = email;
        this.adresse = adresse;
    }

    public Utilisateur(int id_user, String nom, String prenom, String avatar_url, String email, String adresse) {
        this.id_user = id_user;
        this.nom = nom;
        this.prenom = prenom;
        this.avatar_url = avatar_url;
        this.email = email;
        this.adresse = adresse;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getAvatar_url() {
        return avatar_url;
    }

    public void setAvatar_url(String avatar_url) {
        this.avatar_url = avatar_url;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    @Override
    public String toString() {
        return "Utilisateur{" +
                "id_user=" + id_user +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", avatar_url='" + avatar_url + '\'' +
                ", email='" + email + '\'' +
                ", adresse='" + adresse + '\'' +
                '}';
    }
}