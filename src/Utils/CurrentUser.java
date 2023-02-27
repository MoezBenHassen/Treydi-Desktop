package Utils;

import Entities.Utilisateur;
import Utils.Enums.Roles;

import java.util.Date;

public class CurrentUser {

    private static CurrentUser instance;
    private Utilisateur currentUser;

    public Utilisateur getCurrentUser() {
        return currentUser;
    }

    private CurrentUser(Utilisateur currentUser) {
        this.currentUser = currentUser;
    }
    private int id_user;
    private String password,nom,prenom,email,adresse,avatar_url;
    public Roles role;
    private int score;
    private Date date_naissance;


    //Admin
    public CurrentUser(int id_user, String password, String nom, String prenom, String email, String adresse, String avatar_url, Roles role) {
        this.id_user = id_user;
        this.password = password;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.adresse = adresse;
        this.avatar_url = avatar_url;
        this.role = role;
    }
    public static CurrentUser getInstance(int id_user, String password, String nom, String prenom, String email, String adresse, String avatar_url, Roles role) {
        if (instance == null)
            instance = new CurrentUser(id_user,password,nom,prenom,email,adresse,avatar_url,role);
        return instance;
    }

    //Trader

    public CurrentUser(int id_user, String password, String nom, String prenom, String email, String adresse, String avatar_url, Roles role, int score, Date date_naissance) {
        this.id_user = id_user;
        this.password = password;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.adresse = adresse;
        this.avatar_url = avatar_url;
        this.role = role;
        this.score = score;
        this.date_naissance = date_naissance;
    }
    public static CurrentUser getInstance(int id_user, String password, String nom, String prenom, String email, String adresse, String avatar_url, Roles role ,int score, Date date_naissance) {
        if (instance == null)
            instance = new CurrentUser(id_user,password,nom,prenom,email,adresse,avatar_url,role,score,date_naissance);
        return instance;
    }
    //livreur
    /*
    public CurrentUser(int id_user, String password, String nom, String prenom, String email, String adresse, String avatar_url, Roles role) {
        this.id_user = id_user;
        this.password = password;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.adresse = adresse;
        this.avatar_url = avatar_url;
        this.role = role;
    }
     */

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
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

    public Roles getRole() {
        return role;
    }

    public void setRole(Roles role) {
        this.role = role;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public Date getDate_naissance() {
        return date_naissance;
    }

    public void setDate_naissance(Date date_naissance) {
        this.date_naissance = date_naissance;
    }
    public static void clearInstance() {
        instance = null;
    }
    public static CurrentUser getInstance() {
        return instance;
    }
    public static void setInstance(Utilisateur user) {
        instance=new CurrentUser(user);
    }
}
