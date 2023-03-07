package Utils;

import Utils.Enums.Roles;

import java.util.Date;

public class CurrentUser {
    // Add fields specific to Trader
    private int score;
    private Date date_naissance;
    private String sessionId;

    private static CurrentUser instance;

    private int id_user;
    private String password, nom, prenom, email, adresse, avatar_url;
    private Roles role;
    private int archived;

    // Private constructor to prevent instantiation from outside the class
    private CurrentUser(String password, String nom, String prenom, String email, String adresse, String avatar_url, Roles role, int id_user) {
        this.password = password;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.adresse = adresse;
        this.avatar_url = avatar_url;
        this.role = role;
        this.id_user =id_user;
    }

    public static CurrentUser getInstance(String password, String nom, String prenom, String email, String adresse, String avatar_url, Roles role, int id_user) {
        if (instance == null) {
            // Synchronize here to ensure that only one instance is created
            synchronized (CurrentUser.class) {
                if (instance == null) {
                    instance = new CurrentUser(password, nom, prenom, email, adresse, avatar_url, role, id_user);
                }
            }
        }
        return instance;
    }

    public static CurrentUser getInstance() {
        return instance;
    }

    // Add methods specific to Trader
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

    public static void setInstance(CurrentUser instance) {
        CurrentUser.instance = instance;
    }

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

    public int getArchived() {
        return archived;
    }

    public void setArchived(int archived) {
        this.archived = archived;
    }

    // Override toString method to include Trader specific fields
    @Override
    public String toString() {
        if (this.role == Roles.trader) {
            return "\nTrader{" +
                    "id_user=" + id_user +
                    ", password='" + password + '\'' +
                    ", nom='" + nom + '\'' +
                    ", prenom='" + prenom + '\'' +
                    ", email='" + email + '\'' +
                    ", adresse='" + adresse + '\'' +
                    ", avatar_url='" + avatar_url + '\'' +
                    ", role=" + role +
                    ", score=" + score +
                    ", date_naissance=" + date_naissance +
                    '}';
        } else {
            return "\nCurrentUser{" +
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
}

