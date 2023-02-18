package Entities;
import Utils.Enums.Roles;

public class utilisateur {

    private int id_user;
    private String password,nom,prenom,email,adresse,avatar_url;
    public static Roles role;

    public utilisateur(String password, String nom, String prenom, String email, String adresse, String avatar_url, Roles role) {

        this.password = password;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.adresse = adresse;
        this.avatar_url = avatar_url;
        this.role = role;
    }

    public utilisateur() {
    }

    public int getId_user() {
        return id_user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public String getAvatar_url() {
        return avatar_url;
    }

    public void setAvatar_url(String avatar_url) {
        this.avatar_url = avatar_url;
    }

    public static Roles getRole() {
        return role;
    }

    public  void setRole(Roles role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "utilisateur{" +
                "id_user=" + id_user +
                ", password='" + password + '\'' +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", email='" + email + '\'' +
                ", adresse='" + adresse + '\'' +
                ", avatar_url='" + avatar_url + '\'' +
                ", role=" + role +
                '}';
    }
}
